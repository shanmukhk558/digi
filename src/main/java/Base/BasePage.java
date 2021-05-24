package Base;

import java.lang.reflect.Constructor;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utility.ReportLog;

public abstract class BasePage {

	WebDriver driver = null;
	Properties prop = null;
	WebDriverWait wait = null;

	public BasePage() {

	}

	public BasePage(WebDriver driver) {
		this.driver = driver;
		verifyPageLoad();
	}

	protected WebDriver getDriver() {
		return this.driver;
	}

	public void setDrivevr(WebDriver driver) {
		this.driver = driver;
	}

	// abstract methods
	public abstract void verifyPageLoad();

	// to get an element
	protected WebElement getElement(By by, String controlName) {
		ReportLog.LOG("Finding element: " + controlName);
		wait = new WebDriverWait(driver, Long.valueOf(System.getProperty("explicitwait")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		WebElement element = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = ("arguments[0].setAttribute('style','border:2px solid " + (System.getProperty("higlightcolor"))
				+ "');");
		js.executeScript(script, element);
		return element;
	}

	// to get  elements
	protected List<WebElement> getElements(By by, String controlName) {
		ReportLog.LOG("Finding elements: " + controlName);
		wait = new WebDriverWait(getDriver(), Long.valueOf(System.getProperty("explicitwait")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		List<WebElement> elements = getDriver().findElements(by);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		String script = ("arguments[0].setAttribute('style','border:2px solid " + (System.getProperty("higlightcolor"))
				+ "');");
		for (WebElement each : elements) {
			js.executeScript(script, each);
		}
		return elements;
	}

	// to click on element
	protected void click(By by, String controlName) {
		WebElement ele = getElement(by, controlName);
		ReportLog.LOG("clicking on element: " + controlName);
		ele.click();
		ReportLog.LOG("clicked");
	}

	// to enter data
	protected void setText(By by, String content, String controlName) {
		WebElement ele = getElement(by, controlName);
		ReportLog.LOG("Entering text as:  " + content);
		ele.sendKeys(content);
		ReportLog.LOG("Text entered");
	}

	// verify element present or not on the page
	protected boolean IsElementPresent(By by, String controlName) {
		boolean elementPresent = true;
		WebElement ele;
		ReportLog.LOG("Verifying element availability: " + controlName);
		try {
			ele = getElement(by, controlName);
		} catch (Exception ex) {
			elementPresent = false;
		}
		ReportLog.LOG("Element is available");
		return elementPresent;
	}

	// get text from list of elements
	protected List<String> getDataFromElementList(By by, String controlName) {
		List<WebElement> list = getElements(by, controlName);
		List<String> data = new ArrayList<String>();
		for (WebElement eachElement : list) {
			data.add(eachElement.getText());
		}
		return data;
	}

	protected void SelectDropdownOptionByText(By by, String optionTitle, String controlName) {
		Select dropdown = new Select(getElement(by, controlName));
		Assert.assertNotNull(dropdown, "Dropdown control is null");
		ReportLog.LOG("Selecting option from dropdown: " + optionTitle);
		dropdown.selectByVisibleText(optionTitle);
		ReportLog.LOG("Dropdown is Selected");
	}

	protected String getRandomString(int length) {
		String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	protected String getRandomNumber(int length) {
		String AB = "0123456789";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}



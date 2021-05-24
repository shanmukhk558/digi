package Pages.Enterprise;

import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Base.BasePage;
import Utility.ReportLog;

public class CommonPage {

	private static String sMenuItem = "//div[@id='bs-megadropdown-tabs']//a[contains(text(),'@@')]";
	public static Object verifyPageNavig;
	private static final By byNaviagationText = By.xpath("//div[@class='breadcrumbs']//li[@class='active']");
	private static final By byAddNew = By.xpath("//a[text()='ADD NEW']");
	private static final By bySuccessMessage = By.xpath("//div[contains(@style,'green')]");

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static <T extends BasePage> T selectMenu(Class<?> cls, WebDriver driver, String Title, String option)
			throws Exception {
		// select menu title
		ReportLog.LOG("Clicking on Menu: " + Title);
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(System.getProperty("explicitwait")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sMenuItem.replace("@@", Title))));
		driver.findElement(By.xpath(sMenuItem.replace("@@", Title))).click();

		// click menu option
		ReportLog.LOG("Clicking on option: " + option);
		wait = new WebDriverWait(driver, Long.valueOf(System.getProperty("explicitwait")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sMenuItem.replace("@@", option))));
		driver.findElement(By.xpath(sMenuItem.replace("@@", option))).click();

		// creating generic object to return
		Class<?> servicename = Class.forName(cls.getName());
		Constructor<?>[] constructors = servicename.getDeclaredConstructors();
		return (T) constructors[1].newInstance(driver);
	}

	@SuppressWarnings("deprecation")
	public static void verifyPageNavigationText(WebDriver driver, String title) {
		ReportLog.LOG("Verifying navigation title: " + title);
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(System.getProperty("explicitwait")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byNaviagationText));
		WebElement naviagationElement = driver.findElement(byNaviagationText);
		Assert.assertTrue(naviagationElement.getText().contains(title),
				"Expected (contains): " + title + " | Actual: " + naviagationElement.getText());
	}

	public static <T extends BasePage> T clickAddNew(Class<?> cls, WebDriver driver) throws Exception {
		ReportLog.LOG("Clicking on ADD NEW button");
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(System.getProperty("explicitwait")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byAddNew));
		driver.findElement(byAddNew).click();

		// creatinng generic object to return
		Class<?> servicename = Class.forName(cls.getName());
		Constructor<?>[] constructors = servicename.getDeclaredConstructors();
		return (T) constructors[1].newInstance(driver);
	}

	public static String getSuccessMessage(WebDriver driver, String title) {
		ReportLog.LOG("Getting " + title + " text");
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(System.getProperty("explicitwait")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bySuccessMessage));
		WebElement successElement = driver.findElement(bySuccessMessage);
		Assert.assertNotNull(successElement, "Success element is not displayed");
		return successElement.getText();
	}

	public static Date convertStringToDate(String sDate, String sFormate) throws Exception {
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = df.parse(sDate);
		return date;
	}
}

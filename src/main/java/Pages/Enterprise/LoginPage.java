package Pages.Enterprise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends Base.BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	public void verifyPageLoad() {
		//Assert.assertNotNull(getElement(byPageTitle, "Login page title"), "Login page title validation is failed");
	}
	
	//locators
	private  By byLogin = By.linkText("Log in");
	private By bypageTitle = By.xpath("//div[@class='page-title']/h1");
	private By byUserNameTextbox = By.xpath("//input[@class='email']");
	private By byPasswordTextbox = By.xpath("//input[@class='password']");
	private By byLoginButton = By.xpath("//input[@value='Log in']");
	
	
	public   void click() {
		click(byLogin, "Login");
	}
	
	public boolean verifyText() {
		IsElementPresent(bypageTitle, "PageTitle");
		return true;
	}
	
	
	public Homepage performUserLogin(String userName, String password) {
		setText(byUserNameTextbox, userName, "Username Textbox");
		setText(byPasswordTextbox, password, "Password Textbox");
		click(byLoginButton, "Login Button");
		return new Homepage(getDriver());
	}

	@Override
	protected WebDriver getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

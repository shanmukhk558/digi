package Enterprise;

import org.testng.annotations.Test;

import Pages.Enterprise.DashboardPage;
import Pages.Enterprise.LoginPage;
import Utility.Testdata;

public class LoginTest extends Base.BaseTest {
	
	@Test
	public void ValidateUserLoginWithValidUserNameAndValidPassword() {
		//LoginPage LoginPage= new LoginPage(driver);
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.click();
		loginPage.verifyText();
		loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
	}
	
	 
	
}

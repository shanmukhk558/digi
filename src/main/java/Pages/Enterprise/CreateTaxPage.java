package Pages.Enterprise;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Utility.Testdata;

public abstract class CreateTaxPage extends Base.BasePage{
	
	public CreateTaxPage(WebDriver driver) {
		super(driver);
	}
	
	
	@Test
	
	private void ValidateCreateTaxpagewithvaliddata() throws Exception {
		// TODO Auto-generated method stub
		LoginPage loginPage = new LoginPage(getDriver());
        loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
        CommonPage.verifyPageNavigationText(getDriver(), "Taxpage");
	
	}
}

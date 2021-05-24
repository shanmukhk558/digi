package Enterprise;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Pages.Enterprise.CommonPage;
import Pages.Enterprise.LoginPage;
import Pages.Enterprise.TaxParameterPage;
import Utility.Testdata;

public class TaxParametersTests {


@Test

public void VerifyTaxParameterspage() throws Exception {
	LoginPage loginPage = new LoginPage(getDriver());
    loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
    CommonPage.selectMenu(TaxParameterPage.class, getDriver(), "Payments Info", "Taxparameters");
    CommonPage.verifyPageNavigationText(getDriver(), "Taxparameters");
}

private WebDriver getDriver() {
	// TODO Auto-generated method stub
	return null;
}



}


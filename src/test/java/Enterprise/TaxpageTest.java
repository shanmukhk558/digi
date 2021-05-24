package Enterprise;

import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.Enterprise.CommonPage;
import Pages.Enterprise.CreateStorePage;
import Pages.Enterprise.LoginPage;
import Pages.Enterprise.StorePage;
import Pages.Enterprise.TaxPage;
import Utility.Testdata;

public class TaxpageTest extends BaseTest{
	
	@Test(testName = "DO-360")
	public void ValidateAllTheColumnsInTheTaxPage() throws Exception{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
		TaxPage taxPage = CommonPage.selectMenu(TaxPage.class, getDriver(), "Payments Info", "TaxPage");
		taxPage.verifyColumnTitles();
		taxPage.verifySNoColumnData();
		taxPage.verifyStatusColumn();
	}
	
}

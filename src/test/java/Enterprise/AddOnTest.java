package Enterprise;

import org.testng.annotations.Test;

import Pages.Enterprise.AddOnPage;
import Pages.Enterprise.CommonPage;
import Pages.Enterprise.CreateAddOnPage;
import Pages.Enterprise.LoginPage;
import Utility.Testdata;

public class AddOnTest extends Base.BaseTest {
	
	@Test(testName = "DO-372")
	public void ValidateCreateAddOnForm() throws Exception {
		
	LoginPage loginPage = new LoginPage(getDriver());
	loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
	CommonPage.selectMenu(AddOnPage.class, getDriver(), "Menu Management", "Add On");
	CreateAddOnPage CreateAddOnPage = CommonPage.clickAddNew(CreateAddOnPage.class, getDriver());
	CreateAddOnPage.verifyCreateAddOnFromContols();
	
	}
	
	@Test(testName = "DO-373")
	public void VerifyMandateryColumnsWithValidData() throws Exception {
		
	LoginPage loginPage = new LoginPage(getDriver());
	loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
	CommonPage.selectMenu(AddOnPage.class, getDriver(), "Menu Management", "Add On");
	CreateAddOnPage CreateAddOnPage = CommonPage.clickAddNew(CreateAddOnPage.class, getDriver());
	CommonPage.verifyPageNavigationText(getDriver(), "New");
	CreateAddOnPage.VerifyMandateryColumnsCreateAddonform();
	
	}
	
	@Test(testName = "DO-377")
	public void VerifyAllColumnsWithValidData() throws Exception {
		
	LoginPage loginPage = new LoginPage(getDriver());
	loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
	CommonPage.selectMenu(AddOnPage.class, getDriver(), "Menu Management", "Add On");
	CreateAddOnPage CreateAddOnPage = CommonPage.clickAddNew(CreateAddOnPage.class, getDriver());
	CommonPage.verifyPageNavigationText(getDriver(), "New");
	CreateAddOnPage.ValidateCreateAddOnpagewithvaliddata();
	
	}
}

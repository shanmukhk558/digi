package Enterprise;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Pages.Enterprise.CommonPage;
import Pages.Enterprise.CreateStorePage;
import Pages.Enterprise.DashboardPage;
import Pages.Enterprise.LoginPage;
import Pages.Enterprise.StorePage;
import Utility.Testdata;

public class StoreTests extends Base.BaseTest {
	
	//@Test
	public void ValidateStoreNaviagationText() throws Exception {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
		StorePage storePage = CommonPage.selectMenu(StorePage.class, getDriver(), "Client", "Stores");
		CommonPage.verifyPageNavigationText(getDriver(), "Store");
	}
	
	@Test
	public void VerifyCreateButtonWithValidDataInAllColumns() throws Exception{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
		CommonPage.selectMenu(StorePage.class, getDriver(), "Client", "Stores");
		CreateStorePage createStorePage = CommonPage.clickAddNew(CreateStorePage.class, getDriver());
		CommonPage.verifyPageNavigationText(getDriver(), "New");
		createStorePage.createStore();
	}
	//@Test
		public void Validatecreatstoreformwithvaliddata() throws Exception {
			LoginPage loginPage = new LoginPage(getDriver());
			loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
		    CommonPage.selectMenu(StorePage.class, getDriver(), "Client", "Stores");
			CommonPage.verifyPageNavigationText(getDriver(), "new");
			StorePage.verifyTitlecolumn();
			StorePage.verifyDescriptioncolumn();
			StorePage.verifyCreatebutton();
			
		}
		//@Test
		public void Validatecreatstoreformwithvaliddata1() throws Exception {
		selectMenu(StorePage.class, getDriver(), "Client", 
		 "Stores");
		CommonPage.clickAddNew(CreateStorePage.class, 
		 getDriver());
		CommonPage.verifyPageNavigationText(getDriver(), "new");
		
}

		private void selectMenu(Class<StorePage> class1, WebDriver driver, String string, String string2) {
			// TODO Auto-generated method stub
			
		}
}

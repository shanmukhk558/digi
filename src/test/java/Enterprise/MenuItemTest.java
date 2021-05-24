package Enterprise;

import org.testng.annotations.Test;

import Pages.Enterprise.CommonPage;
import Pages.Enterprise.CreateMenuItemPage;
import Pages.Enterprise.LoginPage;
import Pages.Enterprise.MenuItemPage;
import Utility.Testdata;

public class MenuItemTest extends Base.BaseTest {

	private String name;
	private String newType;

	@Test(testName = "DO-115")
	public void ValidateCreateMenuItemForm() throws Exception {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
		CommonPage.selectMenu(MenuItemPage.class, getDriver(), "Menu Management", "Menu Item");
		CreateMenuItemPage CreateMenuItemPage = CommonPage.clickAddNew(CreateMenuItemPage.class, getDriver());
		CreateMenuItemPage.verifyCreateMenuItemFormControls();
	}

	@Test(testName = "DO-123")
	public void ValidateUserIsNotAbleToCreateMenuItemWithOutMandatoryFields() throws Exception {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
		CommonPage.selectMenu(MenuItemPage.class, getDriver(), "Menu Management", "Menu Item");
		CreateMenuItemPage CreateMenuItemPage = CommonPage.clickAddNew(CreateMenuItemPage.class, getDriver());
		CreateMenuItemPage.verifyCreateButtonWithOutMandatoryFields();
	}

	@Test(testName = "DO-125")
	public void ValidateCreateMenuItemWithValidData() throws Exception {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
		CommonPage.selectMenu(MenuItemPage.class, getDriver(), "Menu Management", "Menu Item");
		CreateMenuItemPage createMenuItemPage = CommonPage.clickAddNew(CreateMenuItemPage.class, getDriver());
		MenuItemPage menuItemPage = createMenuItemPage.verifyCreateNewMenutItemWithValidData();
		menuItemPage.verifySuccessMessageText();
		menuItemPage.verifyNewMenuItemNameInTheList(createMenuItemPage.menuItemTitle);
		menuItemPage.verifyNewMenuItemTypeInTheList(createMenuItemPage.menuItemTitle, createMenuItemPage.menuItemType);
	}

	@Test(testName = "DO-145")
	public void validateNavigationTitleInTheHeaderMenuOfMenuItem() throws Exception {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
		MenuItemPage MenuItemPage = CommonPage.selectMenu(MenuItemPage.class, getDriver(), "Menu Management",
				"Menu Item");
		MenuItemPage.verifyNavigationTitleOfMenuItemPage();
	}

	@Test(testName = "DO-136")
	public void CheckAllTheColumnsInTheMenuItemPage() throws Exception {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performUserLogin(Testdata.adminuser, Testdata.adminpassword);
		MenuItemPage MenuItemPage = CommonPage.selectMenu(MenuItemPage.class, getDriver(), "Menu Management",
				"Menu Item");
		MenuItemPage.verifyAllColumnsTitles();
		MenuItemPage.verifySNoColumnData();
		MenuItemPage.verifyImageColumnData();
		MenuItemPage.verifyNameColumnData();
		MenuItemPage.verifyDescriptionColumnData();
		MenuItemPage.verifyTypeColumnData();
		MenuItemPage.verifyStatusColumnData();
		MenuItemPage.verifyUpdatedColumnData();
		MenuItemPage.verifyEditIconActionColumn();
		MenuItemPage.verifyDeleteIconActionColumn();
	}
}

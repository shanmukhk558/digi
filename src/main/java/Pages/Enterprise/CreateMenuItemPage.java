package Pages.Enterprise;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utility.ReportLog;

public class CreateMenuItemPage extends Base.BasePage {

	public CreateMenuItemPage() {

	}

	public CreateMenuItemPage(WebDriver driver) {
		super(driver);
	}

	public void verifyPageLoad() {

	}

	// globale variables
	public String menuItemTitle;
	public String menuItemType;
	
	// Locaters
	By byCreateMenuItemTitle = By.xpath("//h5[text()='Create Menu Item']");
	By byNameTextbox = By.id("txtName");
	By byDescriptionTextbox = By.id("txtDescription");
	By byNutritionTextbox = By.id("txtNutrition");
	By byIngredientsTextbox = By.id("txtIngredients");
	By byAllergiesTextbox = By.id("txtAllergies");
	By bySelectType = By.id("txtType");
	By byCreateButton = By.xpath("//input[@value='Create']");
	
	public void verifyCreateMenuItemFormControls() {
		CommonPage.verifyPageNavigationText(getDriver(), "New");
		ReportLog.PASS(" Verify Page Navigation Text Successfuly ");
		Assert.assertNotNull(getElement(byCreateMenuItemTitle, "Create MenuItem Title"),
				"Create MenuItem Title validation is Failed");
		ReportLog.PASS("Create MenuItem Title is Verified Successfully");
		Assert.assertTrue(getElement(bySelectType, "Select Type").getTagName().equals("select"),
				"SelectType is not a Dropdown");
		Assert.assertTrue(getElement(byNameTextbox, "Name Textbox").getAttribute("type").equals("text"),
				"Name is not a textbox");
		Assert.assertTrue(getElement(byDescriptionTextbox, "Description Textbox").getAttribute("type").equals("text"),
				"Description is not a textbox");
		Assert.assertTrue(getElement(byNutritionTextbox, "Nutrition Textbox").getAttribute("type").equals("text"),
				"Nutrition is not a textbox");
		Assert.assertTrue(getElement(byIngredientsTextbox, "Ingredients Textbox").getAttribute("type").equals("text"),
				"Ingredients is not a textbox");
		Assert.assertTrue(getElement(byAllergiesTextbox, "Allergies Textbox").getAttribute("type").equals("text"),
				"Allergies is not a textbox");
		ReportLog.PASS(" Verify Create MenuItem Form Controls Successfully ");
	}

	public void verifyCreateButtonWithOutMandatoryFields() throws InterruptedException {
		click(byCreateButton, "Create");
		getDriver().switchTo().alert();
		ReportLog.LOG(" Verify POP-UP Alert is Opened ");
		String alertMessage = getDriver().switchTo().alert().getText();
		Assert.assertTrue(alertMessage.equals("Please select the Type"), "Alert Message is not matched");
		ReportLog.LOG(" Verify Alert Message is Successfully ");
		getDriver().switchTo().alert().accept();
		WebElement Create = getElement(byCreateButton, "Create button");
		Assert.assertTrue(Create.isDisplayed(), "Create button is not visible and seems empty record is submitted");
		Select Type = new Select(getDriver().findElement(By.id("txtType")));
		WebElement Name = getElement(byNameTextbox, "Name Textbox");
		Assert.assertNotNull(Name.getAttribute("data-val-required").equals("The NAME field is required."),
				"Name is not mandatory");
		WebElement Description = getElement(byDescriptionTextbox, "Description Textbox");
		Assert.assertNotNull(Description.getAttribute("data-val-required").equals("The DESCRIPTION field is required."),
				"Description is not mandatory");
		ReportLog.PASS("Verify Create Button With Out Mandatory Data Successfully");
	}

	public MenuItemPage verifyCreateNewMenutItemWithValidData() {
		Select Type = new Select(getDriver().findElement(By.id("txtType")));
		List<WebElement> weblist = Type.getOptions();
		int index = 0;
		if (weblist.size() > 1)
		{
			Random num = new Random();
			index = num.nextInt(weblist.size() - 1);
		}
		if (index >= 0) {
			Type.selectByIndex(index);
			menuItemType =  Type.getFirstSelectedOption().getText();
		}
		menuItemTitle = getRandomString(8); // replace with random string
		setText(byNameTextbox, menuItemTitle, "Name");
		setText(byDescriptionTextbox, getRandomString(8), "Description");
		setText(byNutritionTextbox, getRandomString(8), "Nutrition");
		setText(byIngredientsTextbox, getRandomString(8), "Ingresdients");
		setText(byAllergiesTextbox, getRandomString(8), "Allergies");
		click(byCreateButton, "Create");
		return new MenuItemPage(getDriver());
	}
}

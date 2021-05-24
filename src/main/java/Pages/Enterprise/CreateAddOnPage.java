package Pages.Enterprise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CreateAddOnPage extends Base.BasePage{
	
	public CreateAddOnPage() {
		
	}
	
	public CreateAddOnPage(WebDriver driver) {
		super(driver);
	}

	public void verifyPageLoad() {
		
	}
	
	//Locater 
	By byNameTextbox = By.id("txtName");
	By byDetailTextbox = By.id("txtDetails");	
	By byPriceTextbox = By.id("txtPrice");
	By byCreateButton = By.xpath("//input[@value='Create']");
		
	public void verifyCreateAddOnFromContols() {
		
	//Selecting Items in a Multiple SELECT elements
	Select Type = new Select(getDriver().findElement(By.id("ddlType")));
	Type.selectByVisibleText("TOPINGS");
	Type.selectByVisibleText("EXTRAS");
					
	CommonPage.verifyPageNavigationText(getDriver(), "New");
	Assert.assertTrue(getElement(byNameTextbox, "Name Textbox").getAttribute("type").equals("text"), "Name is not a textbox");
	Assert.assertTrue(getElement(byDetailTextbox, "Detail Textbox").getAttribute("type").equals("text"), "Detail is not a textbox");
	Assert.assertTrue(getElement(byPriceTextbox, "Price Textbox").getAttribute("type").equals("text"), "Price is not a textbox");
	
	}	
	
	public AddOnPage VerifyMandateryColumnsCreateAddonform() {
				
	click(byCreateButton, "Create");
	CommonPage.verifyPageNavigationText(getDriver(), "New");
	Select Type = new Select(getDriver().findElement(By.id("ddlType")));
	Type.selectByVisibleText("TOPINGS");
	Type.selectByVisibleText("EXTRAS");
	Type.selectByIndex(1);
	setText(byNameTextbox, "kumar", "Name Textbox");
	setText(byDetailTextbox, "art", "Detail Textbox");
	return new AddOnPage(getDriver());
			
	}
	
	public void ValidateCreateAddOnpagewithvaliddata() {
		
	//Selecting Items in a Multiple SELECT elements
	Select Type = new Select(getDriver().findElement(By.id("ddlType")));
	Type.selectByVisibleText("TOPINGS");
	Type.selectByVisibleText("EXTRAS");
		
	setText(byNameTextbox, "fdys", "Name");
	setText(byDetailTextbox, "eass", "Details");
	setText(byPriceTextbox, "eass", "Price");
	click(byCreateButton, "Create");
	
	}
}
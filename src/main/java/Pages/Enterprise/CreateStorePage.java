package Pages.Enterprise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateStorePage extends Base.BasePage{
	
	public CreateStorePage() {
	}
	
	public CreateStorePage(WebDriver driver) {
		super(driver);
	}

	public void verifyPageLoad() {
			
	}
	
	//locators
	private final By byTitleTextbox = By.id("txtTitle");
	private final By byDetailsTextbox = By.id("txtDetails");
	private final By byCreateButton = By.xpath("//input[@value='Create']");
	 
	
	
	
	//methods
	public StorePage createStore() {
		setText(byTitleTextbox, "new Store", "Title Textbox");
		setText(byDetailsTextbox, "Details", "Details Textbox");
		click(byCreateButton, "Create");
		return new StorePage(getDriver());
	}
	
	

}

package Pages.Enterprise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Homepage extends Base.BasePage {
	
	private By byuserid = By.xpath("//div[@class='header-links']/ul/li");
	private By byshoppingcart = By.xpath("//li[@id='topcartlink']");
	
	
	public Homepage(WebDriver driver) {
		
	}

	public boolean VerifyUser() {
		IsElementPresent(byuserid, "testdemowebshop@gmail.com");
		return true;
	}
		
	public void cart() {
		click(byshoppingcart, "shoppingcart");
	}

	@Override
	public void verifyPageLoad() {
		
	}

}

package Pages.Enterprise;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utility.ReportLog;

public class TaxPage extends Base.BasePage{
	
	public TaxPage() {
	}
	
	public TaxPage(WebDriver driver) {
		super(driver);
	}
	
	public void verifyPageLoad() {
		
	}
	
	

	//locators
	By byColumnTitles = By.xpath("//table[@id='tbldata']//th");
	
	String columnData = "//table[@id='tbldata']//td[@@]";
	
	
	public void verifyColumnTitles() {
		//validate column titles
		List<WebElement> columnTitles = getElements(byColumnTitles, "Column titles");
		ReportLog.PASS("Validating column title SNO ");
		Assert.assertTrue(columnTitles.get(0).getText().equalsIgnoreCase("SNo"), "SNo column title is not matched");
		ReportLog.PASS("Validating column title Ttile ");
		Assert.assertTrue(columnTitles.get(1).getText().equalsIgnoreCase("TITLE"), "TITLE column title is not matched");
		ReportLog.PASS("Validating column title STATUS ");
		Assert.assertTrue(columnTitles.get(2).getText().equalsIgnoreCase("STATUS"), "STATUS column title is not matched");
		ReportLog.PASS("Validating column title UPDATED ");
		Assert.assertTrue(columnTitles.get(3).getText().equalsIgnoreCase("UPDATED"), "UPDATED column title is not matched");
		ReportLog.PASS("Validating column title ACTIONS ");
		Assert.assertTrue(columnTitles.get(4).getText().equalsIgnoreCase("ACTIONS"), "ACTIONS column title is not matched");
	}
	
	public void verifySNoColumnData() {
		List<String> sNoValues = getDataFromElementList(By.xpath(columnData.replace("@@", "1")), "SNo Column");
		List<Integer> convertSNo = sNoValues.stream().map(Integer::parseInt).collect(Collectors.toList());
		List<Integer> sortedOrder = convertSNo.stream().collect(Collectors.toList());
		Collections.sort(sortedOrder);
		Assert.assertEquals(convertSNo, sortedOrder, "SNo  is not in ascending order");		
	}
	
	public void verifyStatusColumn() {
		List<String> statusValues = getDataFromElementList(By.xpath(columnData.replace("@@", "3")), "Status Column");
		for(String each : statusValues) {
			Assert.assertTrue((each.equals("ACTIVE") || each.equals("INACTIVE")), "Status column data is mismatched");
		}
	}

	public void verifySuccssMessageText() {
		String message = CommonPage.getSuccessMessage(getDriver(), "Tax page Success message");
		ReportLog.LOG("Verifying success message text");
		Assert.assertTrue(message.equalsIgnoreCase("TaxPage Inserted Successfully"),
				"Success messagge is not matched");
		ReportLog.PASS("Success message is verified");
	}
}


package Pages.Enterprise;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utility.ReportLog;

public class MenuItemPage extends Base.BasePage {
	public MenuItemPage() {

	}

	public MenuItemPage(WebDriver driver) {
		super(driver);
	}

	public void verifyPageLoad() {

	}

	// locators
	String columnData = "//table[@id='tbldata']//td[@@]";
	By byColumnTitles = By.xpath("//table[@id='tbldata']//th");
	By byMenuItemNaviagationText = By
			.xpath("//div[@class='breadcrumbs']//ol[@class='breadcrumb breadcrumb1 animated wow slideInLeft']");
	By byImageColumn = By.xpath("//img");
	By byEdit = By.xpath("//div//span[contains(@class,'edit')]");
	By byDelete = By.xpath("//div//span[contains(@class,'trash')]");
	String TitleType = "//table[@id='tbldata']//td[3][text()='@@']/following-sibling::td[2]";
	

	public void verifySuccessMessageText() {
		String message = CommonPage.getSuccessMessage(getDriver(), "Menu Item Success Message");
		ReportLog.LOG("Verifying Success Message Text");
		Assert.assertTrue(message.equals("MenuItem Inserted Successfully"), "Success Message is not Matched");
		ReportLog.PASS("Success Message is Verified");
	}

	public void verifyNewMenuItemNameInTheList(String newTitle) {
		List<String> titles = getDataFromElementList(By.xpath(columnData.replace("@@", "3")), "Name Column");
		boolean match = titles.stream().anyMatch(s -> s.contains(newTitle));
		ReportLog.LOG("Looking for New Mentu Item Title in the list: " + newTitle);
		Assert.assertTrue(match, "New Title is Not Available in the Menu Item list");
		ReportLog.PASS(newTitle + " is displayed in the list");
	}

	public void verifyNewMenuItemTypeInTheList(String name, String newType) {
		WebElement Type = getElement(By.xpath(TitleType.replace("@@", name)), "Type Column");
		Assert.assertEquals(Type.getText(), newType, "Expected type " + newType + " is not matching with actual type " + Type.getText());		
		ReportLog.PASS(Type + " is displayed in the list");
	}
	
	public void verifyNavigationTitleOfMenuItemPage() {
		Assert.assertNotNull(getElement(byMenuItemNaviagationText, "MenuItem Navigation Title"),
				"MenuItem Navigation Title Validation is Failed");
		ReportLog.PASS(" Verified Navigation Title of Menu Item is Successfully ");
	}

	public void verifyAllColumnsTitles() {
		List<WebElement> columnTitles = getElements(byColumnTitles, "Column titles");
		Assert.assertTrue(columnTitles.get(0).getText().equalsIgnoreCase("SNo"), "SNo column title is not matched");
		Assert.assertTrue(columnTitles.get(1).getText().equalsIgnoreCase("Image"), "Image column title is not matched");
		Assert.assertTrue(columnTitles.get(2).getText().equalsIgnoreCase("Name"), "Name column title is not matched");
		Assert.assertTrue(columnTitles.get(3).getText().equalsIgnoreCase("Description"),
				"Description column title is not matched");
		Assert.assertTrue(columnTitles.get(4).getText().equalsIgnoreCase("Type"), "Type column title is not matched");
		Assert.assertTrue(columnTitles.get(5).getText().equalsIgnoreCase("Status"),
				"Status column title is not matched");
		Assert.assertTrue(columnTitles.get(6).getText().equalsIgnoreCase("Updated"),
				"Updated column title is not matched");
		Assert.assertTrue(columnTitles.get(7).getText().equalsIgnoreCase("Action"),
				"Action column title is not matched");
		ReportLog.PASS(" Verify All Columns Titles is Successfully ");
	}

	public void verifySNoColumnData() {
		List<String> sNoValues = getDataFromElementList(By.xpath(columnData.replace("@@", "1")), "SNo Column");
		List<Integer> convertSNo = sNoValues.stream().map(Integer::parseInt).collect(Collectors.toList());
		List<Integer> sortedOrder = convertSNo.stream().collect(Collectors.toList());
		Collections.sort(sortedOrder);
		Assert.assertEquals(convertSNo, sortedOrder, "SNo  is not in Ascending Order");
		ReportLog.PASS("SNo Column is Verified");
	}

	public void verifyImageColumnData() {
		List<WebElement> ImageValues = getElements(By.xpath(columnData.replace("@@", "2")), "Image Column");
		for (WebElement each : ImageValues) {
			WebElement ImageIcon = each.findElement(byImageColumn).findElement(byImageColumn);
			Assert.assertNotNull(ImageIcon, "Image Icon is null");
		}
		ReportLog.PASS("Image Column is Verified");
	}

	public void verifyNameColumnData() {
		List<String> NameColumn = getDataFromElementList(By.xpath(columnData.replace("@@", "3")), "Name Column");
		for (String each : NameColumn) {
			Assert.assertNotNull(each, "Name Column is Not Null");
		}
		ReportLog.PASS("Name Column is Verified");
	}

	public void verifyDescriptionColumnData() {
		List<String> DescriptionColumn = getDataFromElementList(By.xpath(columnData.replace("@@", "4")),
				"Description Column");
		for (String each : DescriptionColumn) {
			Assert.assertNotNull(each, "Description Column is Not Null");
		}
		ReportLog.PASS("Description Column is Verified");
	}

	public void verifyTypeColumnData() {
		List<WebElement> TypeValues = getElements(By.xpath(columnData.replace("@@", "5")), "Type Column");
		for (WebElement each : TypeValues) {
			if (each.getText().equals("VEG")) {
				Assert.assertNotEquals(each.getAttribute("style").contains("color:green"), "VEG is not in green Color");
			} else if (each.getText().equals("NON-VEG")) {
				Assert.assertNotEquals(each.getAttribute("style").contains("color:red"), "VEG is not in red Color");
			}
		}
		ReportLog.PASS("Type Column is verified");
	}

	public void verifyStatusColumnData() {
		List<WebElement> StatusValues = getElements(By.xpath(columnData.replace("@@", "6")), "Status Column");
		for (WebElement each : StatusValues)
			if (each.getText().equals("ACTIVE")) {
				Assert.assertNotEquals(each.getAttribute("style").contains("color:green"),
						"ACTIVE is not in green Color");
			} else if (each.getText().equals("INACTIVE")) {
				Assert.assertNotEquals(each.getAttribute("style").contains("color:red"),
						"INACTIVE is not in red Color");
			}
		ReportLog.PASS("Status Column is verified");
	}

	public void verifyUpdatedColumnData() throws Exception {
		List<String> dates = getDataFromElementList(By.xpath(columnData.replace("@@", "7")), "Updated Column");
		for (int index = 0; index < dates.size(); index++) {
			String sDate = dates.get(index);
			Assert.assertNotNull(CommonPage.convertStringToDate(sDate, "dd-MMM-yyyy"),
					"Date is not in correct format at row " + (index + 1));
		}
		ReportLog.PASS("Updated Column is Verified");
	}

	public void verifyEditIconActionColumn() {
		List<WebElement> editIcon = getElements(By.xpath(columnData.replace("@@", "8")), "EditAction Column");
		for (int index = 0; index < editIcon.size(); index++) {
			Assert.assertNotNull(editIcon.get(index), "Edit Icon is null at " + (index + 1));
		}
		ReportLog.PASS("EditAction Column is Verified");
	}

	public void verifyDeleteIconActionColumn() {
		List<WebElement> ActionValues = getElements(By.xpath(columnData.replace("@@", "8")), "DeleteAction Column");
		for (WebElement each : ActionValues) {
			WebElement deleteIcon = each.findElement(byDelete);
			Assert.assertNotNull(deleteIcon, "Delete Icon is null");
		}
		ReportLog.PASS("DeleteAction Column is Verified");
	}
}

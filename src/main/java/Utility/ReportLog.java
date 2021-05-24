package Utility;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportLog {
	public static ExtentTest extentTest;
	public static ExtentReports extentReport;
	
	public static void initializeReport() {
		String currentDateTime =  String.valueOf(new SimpleDateFormat("MMddyyyyhhmmss").format(new Date()));
		extentReport = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\ExtentReportResults_"+ currentDateTime +".html");
	}

	public static void startTest(String testTitle) {
		extentTest = extentReport.startTest(testTitle);
	}
	
	public static void LOG(String message) {
		extentTest.log(LogStatus.INFO, message);
	}

	public static void PASS(String stepName) {
		extentTest.log(LogStatus.PASS, stepName);
	}
	
	public static void FAIL(String stepName) {
		extentTest.log(LogStatus.FAIL, stepName);
	}
	
	public static void WARNING(String stepName) {
		extentTest.log(LogStatus.WARNING, stepName);
	}
}

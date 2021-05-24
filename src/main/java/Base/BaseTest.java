package Base;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;

import Utility.ReportLog;
import Utility.Testdata;

public class BaseTest {
	private WebDriver driver = null;

	protected Properties prop = null;
	Testdata testdata = null;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@BeforeSuite
	public void doBeforeSuite() throws Exception{
		String resourcePath = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\" + System.getProperty("env")+".properties";
		testdata = new Testdata();
		testdata.loadEnvironmentData(resourcePath);
		ReportLog.initializeReport();
	}

	@AfterSuite
	public void doAfterSuite() {
		ReportLog.extentReport.flush();
	}

	@BeforeClass
	public void doBeforeClass() {
	}

	@AfterClass
	public void doAfterClass() {

	}

	@BeforeMethod
	public void doBeforeMethod(Method method) {
		ReportLog.startTest(method.getName());
		initializeDriver();
	}

	@AfterMethod
	public void doAfterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) { // Test passed with out any interruption
			ReportLog.PASS("Test Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {// test failed due to exception
			ReportLog.FAIL("Test Failed");
			ReportLog.FAIL(result.getThrowable().getLocalizedMessage());
		} else if (result.getStatus() == ITestResult.SKIP) { // test skipped for any reason
			ReportLog.WARNING("Test Skipped");
			ReportLog.FAIL(result.getSkipCausedBy().toString());
		}		
		if (getDriver() != null) {
			ReportLog.PASS("Closing web driver");
			getDriver().close();
			getDriver().quit();
			ReportLog.PASS("Web driver is closed");
		}
		ReportLog.extentReport.endTest(ReportLog.extentTest);
		ReportLog.LOG("Test End");
	}

	@BeforeTest
	public void doBeforeTest() {
	}

	@AfterTest
	public void doAfterTest() {
	}	

	private void initializeDriver() {
		String browser = System.getProperty("browserName");
		ReportLog.LOG("Launching browser: " + browser);
		System.out.println("Launching driver: " + browser);
		if (browser.toUpperCase().equalsIgnoreCase("IE")) {
			ReportLog.LOG("Setting up Internet explorer options");
			InternetExplorerOptions capabilities = new InternetExplorerOptions();
			// capabilities.setCapability("browser.download.dir","c:\\downloads");
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			setDriver(new InternetExplorerDriver(capabilities));
			ReportLog.LOG("IE Driver is started");

		} else if (browser.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver","S:\\Technology\\TTGACMX\\QA\\QA All\\Selenium and Eclipse Files\\IEDriverServer_Win32_3.4.0\\chromedriver.exe");
			setDriver(new ChromeDriver());
			ReportLog.LOG("Chrome Driver is started");

		} else if (browser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.chrome.driver",
//					"S:\\Technology\\TTGACMX\\QA\\QA All\\Selenium and Eclipse Files\\IEDriverServer_Win32_3.4.0\\geckodriver.exe");
//			CommonVariables.driver = new FirefoxDriver();
//			System.out.println("Firefox Driver started");
//			CommonVariables.driver.manage().window().maximize(); // maximize the window (just looks better and
//																	// easier to follow)
//			CommonVariables.setParentWindowID(CommonVariables.driver.getWindowHandle());

		} else if (browser.equalsIgnoreCase("edge")) {
//			System.setProperty("webdriver.chrome.driver",
//					"S:\\Technology\\TTGACMX\\QA\\QA All\\Selenium and Eclipse Files\\IEDriverServer_Win32_3.4.0\\MicrosoftWebDriver.exe");
//			CommonVariables.driverEdge = new EdgeDriver();
//			System.out.println("Edge Driver started");
//			CommonVariables.driverEdge.manage().window().maximize(); // maximize the window (just looks better and
			// easier to follow)

		} 
		
		getDriver().manage().timeouts().implicitlyWait(Long.valueOf(System.getProperty("implicitWait")), TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		ReportLog.LOG("Launching application: " + Testdata.appurl);
		getDriver().navigate().to(Testdata.appurl);
	}

	public void loadBackgroundData() {

	}
}

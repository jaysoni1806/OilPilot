package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.Listeners.ExtentReportListener;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.Utility;

import DriverManager.BrowserFactory;
import DriverManager.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Logger log = Logger.getLogger(TestBase.class);
	public static SoftAssert softAssert = new SoftAssert();
	public static Properties props = new Properties();

	@BeforeSuite
	public void prerequisite() throws IOException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/Resource/log4j.properties");
		FileReader reader = new FileReader(System.getProperty("user.dir") + "/accessories.properties");
		props.load(reader);
	}

	@BeforeClass
	public void assignObjects() {
		driver = new BrowserFactory("chrome").getDriver();
		WebDriverManager.setDriver(driver);
	}

	@BeforeTest
	public void initializeExtentReport() {
		ExtentReportManager.initializeExtentReport();
	}

	@BeforeMethod
	public void seperateCase() {
		log.info("------------------------------------------------------------------------");
	}

	@AfterTest
	public void flush() {
		ExtentReportListener.flush();
	}

	@AfterClass
	public void closeBrowser() {
		WebDriverManager.getDriver().quit();
	}

	@AfterSuite
	public void closeAssertion() {
		softAssert.assertAll();
		WebDriverManager.removeDriver();

	}

	public void waitAfterTest() {
		Utility utility = Utility.utility();
		utility.WaitFor2Second();
	}

}

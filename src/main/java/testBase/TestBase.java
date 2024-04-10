package testBase;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.Listeners.ExtentReportListener;
import com.commonUtil.ExtentReportManager;

import pageClass.CompanyAssetsPage;
import pageClass.DashBoardPage;
import pageClass.FiledAssetsPage;
import pageClass.LeaseAssetsPage;
import pageClass.LeaseGroupAssetsPage;
import pageClass.LoginPage;
import pageClass.OperatorAssetspage;
import pageClass.TankAssetsPage;
import pageClass.TankBatteryAssetsPage;
import pageClass.commonLocatorsRepo;

public class TestBase {

	public static WebDriver driver;
	public LoginPage login;
	public DashBoardPage dashboard;
	public CompanyAssetsPage cmpAstsOp;
	public FiledAssetsPage fieldAstsop;
	public commonLocatorsRepo commLocators;
	public LeaseGroupAssetsPage lease_groupAstop;
	public LeaseAssetsPage leaseAstop;
	public TankBatteryAssetsPage tankBatteryAstop;
	public TankAssetsPage tankAssetsop;
	public OperatorAssetspage operatorAssetsop;
	public static Logger log = Logger.getLogger(TestBase.class);
	public static SoftAssert softAssert = new SoftAssert();

	@BeforeSuite
	public void prerequisite() {
		driver = new ChromeDriver();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/Resource/log4j.properties");
		log.info("************************************************************************");
		log.info("Chrome browser launched.");

		driver.get("https://oilman-website.apps.openxcell.dev/login/");
		log.info("Hit Oilman URL. ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@BeforeClass
	public void assignObjectForClass() {
		login = new LoginPage(driver);
		dashboard = new DashBoardPage(driver);
		cmpAstsOp = new CompanyAssetsPage(driver);
		fieldAstsop = new FiledAssetsPage(driver);
		commLocators = new commonLocatorsRepo(driver);
		lease_groupAstop = new LeaseGroupAssetsPage(driver);
		leaseAstop = new LeaseAssetsPage(driver);
		tankBatteryAstop = new TankBatteryAssetsPage(driver);
		tankAssetsop = new TankAssetsPage(driver);
		operatorAssetsop = new OperatorAssetspage(driver);
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

	@AfterSuite
	public void closeBrowser() {
		softAssert.assertAll();
		driver.close();
	}

}

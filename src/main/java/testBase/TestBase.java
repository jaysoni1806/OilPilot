package testBase;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageClass.CompanyAssetsOperations;
import pageClass.DashBoardPage;
import pageClass.LoginPage;

public class TestBase {
	
	public static WebDriver driver;
	public LoginPage login;
	public DashBoardPage dashboard;
	public CompanyAssetsOperations cmpAstsOp;
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReport;
	public static ExtentTest test;
	/*public String methodName = new Exception().getStackTrace()[0].getMethodName();
	public String classname = new Exception().getStackTrace()[0].getClassName();*/
	
	@BeforeSuite
	public void prerequisite() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("** Chrome browser launched.");
		driver.get("https://oilman-website.apps.openxcell.dev/login/");
		System.out.println("** Hit Oilman URL. ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("");
	
	}
	@BeforeClass
	public void assignObjectForClass()
	{
		login = new LoginPage(driver);
		dashboard = new DashBoardPage(driver);
		cmpAstsOp =  new CompanyAssetsOperations(driver);
		
	}
	@BeforeTest
	public void initializeExtentReport() {
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/OilmanExtentReport.html");
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		extentSparkReporter.config().setDocumentTitle("Oilman Test Report");
		extentSparkReporter.config().setReportName("Oilman Test Execution Report");
		extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd,yyyy, hh:mm a");
		extentReport = new ExtentReports();
		extentReport.attachReporter(extentSparkReporter);
	}
	@AfterTest
	public void flush() {
		extentReport.flush();
	}
	public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}

package testBase;

import java.io.File;
import java.time.Duration;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.Listeners.ExtentReportListener;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageClass.CompanyAssetsOperations;
import pageClass.DashBoardPage;
import pageClass.LoginPage;

public class TestBase {
	
	public static WebDriver driver;
	public LoginPage login;
	public DashBoardPage dashboard;
	public CompanyAssetsOperations cmpAstsOp;
	public static Logger log; 
	
	@BeforeSuite
	public void prerequisite() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/Resource/log4j.properties");
		log = Logger.getLogger(getClass().getName());
		log.info("************************************************************************");
		log.info("Chrome browser launched.");
		
		driver.get("https://oilman-website.apps.openxcell.dev/login/");
		log.info("Hit Oilman URL. ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
		
		ExtentReportManager.initializeExtentReport();
	}
	@BeforeMethod
	public void seperateCase()
	{
		log.info("------------------------------------------------------------------------");
	}
	@AfterTest
	public void flush() {
		ExtentReportListener.flush();
	}
	@AfterSuite
	public void closeBrowser() {
		driver.close();
	}
	
	
	public static String screenShot(WebDriver driver,String filename) {
		  TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		  File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		  String destination = System.getProperty("user.dir")+"/ScreenShot/"+filename+"_"+commonUtil.getCurrentTime()+".png";
		  File finalDestination= new File(destination);
		  try {
		   FileUtils.copyFile(source, finalDestination);
		  } catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.getMessage();
		  }
		  return destination;
		}
	
}

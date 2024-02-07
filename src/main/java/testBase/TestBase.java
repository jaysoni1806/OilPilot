package testBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

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
		ExtentReportManager.initializeExtentReport();
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

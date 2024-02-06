package pageClass;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.commonUtil.Utility;

import testBase.TestBase;

public class LoginPage {
	WebDriver driver;
	Utility utility;
	public WebDriverWait wait;
	String currentUrl;
	public DashBoardPage dashBoard;
	
	
	@FindBy(xpath = "//label[text()='Email']/following-sibling::div/input") public WebElement email;
	@FindBy(xpath = "//input[@type='password']") public WebElement pass;
	@FindBy(xpath = "//button[@type='submitButton']") public WebElement sbtBtn;
	@FindBy(xpath = "//div[contains(@class,'css-acwcvw')]//p[contains(@id,'helper-text')]") public List<WebElement> emailError;
	@FindBy(xpath = "//p[@id='auth-login-v2-password-helper-text']") public List<WebElement> passError;
	@FindBy(xpath = "//div[contains(@class,'react-hot-toast')]//div[contains(@role,'status')]") public List<WebElement> credErr;
	@FindBy(xpath = "//span[@role='progressbar']") public WebElement progressbar;
	
	
	
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		dashBoard =  new DashBoardPage(driver);	
	}

	
	public void validLoginTest(String username, String password) {
		
		try {
			email.click();
			TestBase.test.log(Status.PASS, "Clicked on Email field.");
			
			
			utility.ClearTextBox(email);
			TestBase.test.log(Status.PASS, "Clear Email textbox.");
			
			
			utility.SendValues(email, username);
			TestBase.test.log(Status.PASS, "Enter Email id.");

			
			pass.click();
			TestBase.test.log(Status.PASS, "Clicked on Password field.");
			
			
			utility.ClearTextBox(pass);
			TestBase.test.log(Status.PASS, "Clear Password textbox.");
		
			
			utility.SendValues(pass, password);
			TestBase.test.log(Status.PASS, "Enter Password.");
			
			
			utility.Submit(sbtBtn);
			TestBase.test.log(Status.PASS, "Cliked on Submit button.");
		
			utility.waitForSometime(dashBoard.dashboard);
			TestBase.test.log(Status.PASS, "Wait until Dashboard visible.");
			
			Assert.assertEquals(driver.getCurrentUrl(), "https://oilman-website.apps.openxcell.dev/dashboard/");
			TestBase.test.log(Status.PASS, "User Logged successfully.");
		} catch (Exception e) {
			TestBase.test.log(Status.FAIL, "Login Failed due to ->"+e);
		}
		
	
	}
	/*public void loginTest(String username, String password) throws InterruptedException {
		
		email.click();
		utility.ClearTextBox(email);
		utility.SendValues(email, username);
		
		pass.click();
		utility.ClearTextBox(pass);
		utility.SendValues(pass, password);
		utility.Submit(sbtBtn);
		Thread.sleep(2000);
	
		if(sbtBtn.isEnabled()) {
			if(emailError.size() > 0) {
				//Assert.assertEquals(((WebElement) emailError).getText(), "email must be a valid email");
				System.out.println("Email must be a valid email.");
			}
			else if(passError.size() > 0) {
				//Assert.assertEquals(((WebElement) passError).getText(), "password must be at least 5 characters");
				System.out.println("Password must be at least 5 characters");
			}
		}
		else{
			try {
				wait.until(ExpectedConditions.invisibilityOf(progressbar));
				if(sbtBtn.isDisplayed()!= true) {
					currentUrl =  driver.getCurrentUrl();
					if(currentUrl.equalsIgnoreCase("https://oilman-website.apps.openxcell.dev/dashboard")) {
						System.out.println("User Successfully Logged In.");
					}
					else	
						System.out.println("Invalid credentials");
				}
			}
			
			catch (Exception e) {
				// TODO: handle exception
			}
		}
			
	}
	
	public DashBoardPage dashBoardPage(WebDriver driver) {
		return new DashBoardPage(driver);
	}*/

}

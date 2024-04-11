package pageClass;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonUtil.ApplicationException;
import com.commonUtil.Utility;

import testBase.TestBase;

public class LoginPage {
	WebDriver driver;
	Utility utility;
	public WebDriverWait wait;
	String currentUrl;
	public DashBoardPage dashBoard;
	public static Logger log = Logger.getLogger(LoginPage.class);

	@FindBy(xpath = "//label[text()='Email']/following-sibling::div/input")
	public WebElement email;
	@FindBy(xpath = "//input[@type='password']")
	public WebElement pass;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement sbtBtn;
	@FindBy(xpath = "//div[contains(@class,'css-acwcvw')]//p[contains(@id,'helper-text')]")
	public List<WebElement> emailError;
	@FindBy(xpath = "//p[@id='auth-login-v2-password-helper-text']")
	public List<WebElement> passError;
	@FindBy(xpath = "//div[contains(@class,'react-hot-toast')]//div[contains(@role,'status')]")
	public List<WebElement> credErr;
	@FindBy(xpath = "//span[@role='progressbar']")
	public WebElement progressbar;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		dashBoard = new DashBoardPage(driver);
	}

	public void enterEmail(String username) throws ApplicationException {
		if (email.isDisplayed()) {
			email.click();
			utility.ClearTextBox(email);
			utility.SendValues(email, username);
			log.info("Enter Email id.");

		} else {
			throw new ApplicationException("Exception Occured", "Email filed is not display.");
		}

	}

	public void enterPAssword(String password) throws ApplicationException {
		if (pass.isDisplayed()) {

			pass.click();
			utility.ClearTextBox(pass);
			utility.SendValues(pass, password);
			log.info("Enter Password.");
		} else {
			throw new ApplicationException("Exception Occured", "Password filed is not display.");
		}
	}

	public void clickSubmitButton() throws ApplicationException {
		if (sbtBtn.isEnabled()) {
			utility.Submit(sbtBtn);
			log.info("Cliked on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Login submit button is disable.");
		}
	}

	public void validateLoginSuccesfull() throws ApplicationException {

		utility.waitForSometime(dashBoard.dashboard);
		if (driver.getCurrentUrl().equals("https://oilman-website.apps.openxcell.dev/dashboard/")) {
			TestBase.softAssert.assertEquals(driver.getCurrentUrl(),
					"https://oilman-website.apps.openxcell.dev/dashboard/");
			log.info("Logged successfully navigated on Dashboard");
		} else {
			throw new ApplicationException("Exception Occured", "Dashboard URL is not present.");
		}

	}

}

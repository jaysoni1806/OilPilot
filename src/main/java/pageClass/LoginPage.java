package pageClass;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commonUtil.ApplicationException;
import com.commonUtil.Utility;

import DriverManager.WebDriverManager;
import testBase.TestBase;

public class LoginPage {
	public WebDriverWait wait;
	String currentUrl;
	Utility utility;
	commonLocatorsRepo commLocators;
	DashBoardPage dashboard;
	public static Logger log = Logger.getLogger(LoginPage.class);

	@FindBy(xpath = "//label[text()='Email']/following-sibling::div/input")
	public WebElement email;
	@FindBy(xpath = "//input[@type='password']")
	public WebElement pass;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement sbtBtn;
	@FindBy(xpath = "//form//p[contains(@class,'Mui-error')]")
	public WebElement emailError;
	@FindBy(xpath = "//p[@id='auth-login-v2-password-helper-text']")
	public WebElement passError;
	@FindBy(xpath = "//div[contains(@class,'react-hot-toast')]//div[contains(@role,'status')]")
	public List<WebElement> credErr;
	@FindBy(xpath = "//span[@role='progressbar']")
	public WebElement progressbar;

	public LoginPage() {
		PageFactory.initElements(WebDriverManager.getDriver(), this);
		utility = Utility.utility();
		commLocators = commonLocatorsRepo.LocatorsRepo();
		dashboard = DashBoardPage.dashBoardPage();
	}

	public void enterEmail(String username) throws ApplicationException {
		// Utility utility = Utility.utility();
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
		// Utility utility = Utility.utility();
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
		// Utility utility = Utility.utility();
		if (sbtBtn.isEnabled()) {
			utility.Submit(sbtBtn);
			log.info("Cliked on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Login submit button is disable.");
		}
	}

	public void validateLogin(String flag) throws ApplicationException {
		// Utility utility = Utility.utility();
		// DashBoardPage dashBoard = DashBoardPage.dashBoardPage();
		if (flag.equals("loginWithInvalidCredentials")) {

			utility.waitForSometime(dashboard.toastMessage);
			if (dashboard.toastMessage.isDisplayed()) {
				TestBase.softAssert.assertEquals(dashboard.toastMessage.getText(),
						TestBase.props.getProperty("INVALID_CRED_TOAST"));
				log.info("User with this username does not exist.");
			} else {
				throw new ApplicationException("Exception Occured", "Validation toast not present.");
			}
		} else if (flag.equals("validLoginTest")) {

			utility.waitForSometime(dashboard.dashboard);
			if (dashboard.dashboard.isDisplayed()) {
				TestBase.softAssert.assertEquals(WebDriverManager.getDriver().getCurrentUrl(),
						TestBase.props.getProperty("DASHBOARD_URL"));
				log.info("Logged successfully navigated on Dashboard");
			} else {
				throw new ApplicationException("Exception Occured", "Dashboard URL is not present.");
			}

		} else if (flag.equals("loginWithUnformattedEmail")) {
			utility.waitForSometime(emailError);
			if (emailError.isDisplayed()) {
				TestBase.softAssert.assertEquals(emailError.getText(), TestBase.props.getProperty("WRONG_EMAIL"));
				log.info("Email must be a valid email.");
			} else {
				throw new ApplicationException("Exception Occured", "Validation toast not present.");
			}
		} else if (flag.equals("loginWithLessThenFiveCharPassword")) {
			utility.waitForSometime(passError);
			if (passError.isDisplayed()) {
				TestBase.softAssert.assertEquals(passError.getText(),
						TestBase.props.getProperty("LESS_THEN_5_CHAR_PASSWORD"));
				log.info("Password must be at least 5 characters.");
			} else {
				throw new ApplicationException("Exception Occured", "Validation toast not present.");
			}
		}

	}

	public static LoginPage loginpage() {
		return new LoginPage();
	}

}

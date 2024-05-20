package testPackage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.Utility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageClass.LoginPage;
import testBase.TestBase;

public class LoginTest extends TestBase {
	LoginPage login;

	@DataProvider(name = "LoginTestData")
	public Object[][] getTestData(Method method) throws JsonParseException, JsonMappingException, IOException {

		List<Map<String, String>> maps = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(new File(System.getProperty("user.dir") + "/DataFile/Users.json"));

		node = node.path(method.getName());
		maps = mapper.readValue(node.toString(), new TypeReference<List<Map<String, String>>>() {
		});

		return maps.stream().map(object -> new Map[] { object }).toArray(Map[][]::new);
	}

	@Test(priority = 1, dataProvider = "LoginTestData", description = "Test login with unformatted email.")
	public void loginWithUnformattedEmail(Map<String, String> value) throws ApplicationException {
		login = LoginPage.loginpage();

		ExtentReportManager.extentTest.get().log(Status.PASS, "Entered unformatted email id.");
		login.enterEmail(value.get("username"));

		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter password.");
		login.enterPAssword(value.get("password"));

		ExtentReportManager.extentTest.get().log(Status.PASS, "Cliked on Submit button.");
		login.clickSubmitButton();

		login.validateLogin(value.get("flag"));
		ExtentReportManager.extentTest.get().log(Status.PASS, "Email must be a valid email.");
		waitAfterTest();
	}

	@Test(priority = 2, dataProvider = "LoginTestData", description = "Test login with less then 5 character password")
	public void loginWithLessThenFiveCharPassword(Map<String, String> value) throws ApplicationException {
		ExtentReportManager.extentTest.get().log(Status.PASS, "Entered email id.");
		login.enterEmail(value.get("username"));

		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter less then 5 character password.");
		login.enterPAssword(value.get("password"));

		ExtentReportManager.extentTest.get().log(Status.PASS, "Cliked on Submit button.");
		login.clickSubmitButton();

		login.validateLogin(value.get("flag"));
		ExtentReportManager.extentTest.get().log(Status.PASS, "Password must be at least 5 characters.");
		waitAfterTest();
	}

	@Test(priority = 3, dataProvider = "LoginTestData", description = "Test login with unregistered user.")
	public void loginWithUnRegisteredUser(Map<String, String> value) throws ApplicationException {
		ExtentReportManager.extentTest.get().log(Status.PASS, "Entered unregistered email id.");
		login.enterEmail(value.get("username"));

		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter password.");
		login.enterPAssword(value.get("password"));

		ExtentReportManager.extentTest.get().log(Status.PASS, "Cliked on Submit button.");
		login.clickSubmitButton();

		login.validateLogin(value.get("flag"));
		ExtentReportManager.extentTest.get().log(Status.PASS, "User with this username does not exist.");
		waitAfterTest();
	}

	@Test(priority = 4, dataProvider = "LoginTestData", description = "Test valid login")
	public void validLoginTest(Map<String, String> value) throws ApplicationException {

		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter Email id.");
		login.enterEmail(value.get("username"));

		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter Password.");
		login.enterPAssword(value.get("password"));

		ExtentReportManager.extentTest.get().log(Status.PASS, "Cliked on Submit button.");
		login.clickSubmitButton();

		login.validateLogin(value.get("flag"));
		ExtentReportManager.extentTest.get().log(Status.PASS, "Logged successfully and navigated on Dashboard");
		waitAfterTest();
	}

	public void waitAfterTest() {
		Utility utility = Utility.utility();
		utility.WaitFor2Second();
	}

}

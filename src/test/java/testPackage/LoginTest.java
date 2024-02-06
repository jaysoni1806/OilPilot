package testPackage;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import testBase.TestBase;

public class LoginTest extends TestBase {

	//String methodName = new Exception().getStackTrace()[0].getMethodName();
	
	@DataProvider(name = "LoginTestData")
	public Object[][] gettestData() 
	{
		return new Object[][] {{"jay.soni+manager@openxcell.com", "QA@12345"}};
	}
	
	@Test(dataProvider = "LoginTestData")
	public void validLoginTest(String email,String password) {
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		getSaltString();
		test = extentReport.createTest(methodName, "Verify login with the valid user.");
		login.validLoginTest(email, password);
	}
	

	/*
	@BeforeSuite
	public void testInitialise() {
		prerequisite();
		loginPage = new LoginPage(driver);
		//dashBoardPage = loginPage.dashBoardPage(driver);
	}
	
	@DataProvider(name = "LoginTestData")
	public Object[][] gettestData() throws IOException
	{
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(new File(System.getProperty("user.dir")+ File.separator +"DataFile/Users.json"));
		maps = mapper.readValue(node.toString(), new TypeReference<List<Map<String, String>>>() {
		});
		return maps.stream().map(Object -> new Map[] {Object}).toArray(Map[][]::new);	
	}
	
	@Test(dataProvider = "LoginTestData")
	public void loginPageTest(Map<String, String> data) throws InterruptedException {
		loginPage.loginTest(data.get("username"),data.get("password"));
		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
		wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.asset));
		dashBoardPage.assetClick();
		dashBoardPage.tankBatteryClick();
	}
	*/
//	@AfterTest
//	public void tearDown() {
//		driver.close();
//	}

}

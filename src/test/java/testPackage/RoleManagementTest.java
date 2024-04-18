package testPackage;

import org.openqa.selenium.WebDriver;

import testBase.TestBase;

public class RoleManagementTest extends TestBase {

	private static WebDriver driver2;

	// @Test
	public void laucnNewWindow() {
		driver2.get("https://oilman-website.apps.openxcell.dev/login/");

		// It will return the parent window name as a String
		String parent = driver.getWindowHandle();

		// switch to the parent window
		driver.switchTo().window(parent);
	}

}

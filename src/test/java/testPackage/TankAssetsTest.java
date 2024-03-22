package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class TankAssetsTest extends TestBase {
	public String tank_name = commonUtil.getRandomString(4);

	@Test(priority = 0, description = "Test create Tank assets.")
	public void createTank() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Tank");
		ExtentReportManager.test.log(Status.PASS, "Click on Tank menu.");
		tankAssetsop.clickAddButtonAndVerifyCreateTankSceenIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and verify Add Tank screen.");
	}

}

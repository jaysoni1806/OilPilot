package testPackage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import pageClass.DashBoardPage;
import pageClass.LoginPage;
import pageClass.TankAssetsPage;
import testBase.TestBase;

public class TankAssetsTest extends TestBase {
	public String tank_name = commonUtil.getRandomString(4);

	TankAssetsPage tankAssetsop;
	DashBoardPage dashboard;

	@BeforeClass
	@Parameters({ "username", "password" })
	public void validLoginTest(String uname, String pass) throws ApplicationException {

		LoginPage login = LoginPage.loginpage();

		login.enterEmail(uname);
		login.enterPAssword(pass);
		login.clickSubmitButton();
		login.validateLogin(props.getProperty("LOGIN_FLAG"));
		waitAfterTest();
	}

	@Test(priority = 0, description = "Test create tank assets.")
	public void createTank() throws ApplicationException, InterruptedException {
		tankAssetsop = TankAssetsPage.tankAssetsPage();
		dashboard = DashBoardPage.dashBoardPage();

		dashboard.clickOnAssets();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Tank");
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Tank menu.");
		tankAssetsop.clickAddButtonAndVerifyCreateTankSceenIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Add button and verify Add Tank screen.");
		tankAssetsop.enterRequiredDetailsToAddTank(tank_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Entered required details for tank.");
		tankAssetsop.clickOnSubmitAndVerifyTankIsCreateOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Click on Submit button and verify that tank is created or not. ");
	}

	@Test(priority = 1, description = "Test edit tank assets.")
	public void editTank() throws ApplicationException, InterruptedException {
		tankAssetsop.clickEdit_actionUnderThePerent_actionandVerifyEditTankScreenIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify the edit tank screen present or not.");
		tankAssetsop.enterNewTankName();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter new tank name.");
		tankAssetsop.clickOnSubmitAndVerifyTankIsUpdatedOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Click on Submit button and verify that tank is updated or not. ");
	}

	@Test(priority = 2, description = "Test delete tank assets.")
	public void deleteTank() throws ApplicationException, InterruptedException {
		tankAssetsop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		tankAssetsop.verifyTheTankIsDeletedorNotAfterConfirm();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify that the tank is Delete or not after confirm.");
		tankAssetsop.verifyTheTankListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all tank.");
	}

}

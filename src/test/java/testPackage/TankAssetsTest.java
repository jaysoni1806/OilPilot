package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class TankAssetsTest extends TestBase {
	public String tank_name = commonUtil.getRandomString(4);

	@Test(priority = 0, description = "Test create tank assets.")
	public void createTank() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Tank");
		ExtentReportManager.test.log(Status.PASS, "Click on Tank menu.");
		tankAssetsop.clickAddButtonAndVerifyCreateTankSceenIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and verify Add Tank screen.");
		tankAssetsop.enterRequiredDetailsToAddTank(tank_name);
		ExtentReportManager.test.log(Status.PASS, "Entered required details for tank.");
		tankAssetsop.clickOnSubmitAndVerifyTankIsCreateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button and verify that tank is created or not. ");
	}

	@Test(priority = 1, description = "Test edit tank assets.")
	public void editTank() throws ApplicationException, InterruptedException {
		tankAssetsop.clickEdit_actionUnderThePerent_actionandVerifyEditTankScreenIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the edit tank screen present or not.");
		tankAssetsop.enterNewTankName();
		ExtentReportManager.test.log(Status.PASS, "Enter new tank name.");
		tankAssetsop.clickOnSubmitAndVerifyTankIsUpdatedOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button and verify that tank is updated or not. ");
	}

	@Test(priority = 2, description = "Test delete tank assets.")
	public void deleteTank() throws ApplicationException, InterruptedException {
		tankAssetsop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		tankAssetsop.verifyTheTankIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the tank is Delete or not after confirm.");
		tankAssetsop.verifyTheTankListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all tank.");
	}

}

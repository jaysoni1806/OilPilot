package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class WellAssetsTest extends TestBase {
	public String well_name = "QA Well " + commonUtil.getRandomNumber(3);

	@Test(priority = 0, description = "Test create well assets.")
	public void createWell() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Well");
		ExtentReportManager.test.log(Status.PASS, "Click on Well menu.");
		wellAssetsop.clickAddButtonAndVerifyCreateWellSceenIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on add button and verify add tank screen.");
		wellAssetsop.enterRequiredDetailsToAddWell(well_name);
		ExtentReportManager.test.log(Status.PASS, "Entered required details for well.");
		wellAssetsop.clickOnSubmitAndVerifyWellIsCreateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button and verify that well is created or not. ");
	}

	@Test(priority = 1, description = "Test edit well assets.")
	public void editWell() throws ApplicationException, InterruptedException {
		wellAssetsop.clickEdit_actionUnderThePerent_actionandVerifyEditWellScreenIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the edit well screen present or not.");
		wellAssetsop.enterNewWellName();
		ExtentReportManager.test.log(Status.PASS, "Enter new tank name.");
		wellAssetsop.clickOnSubmitAndVerifyWellIsUpdatedOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button and verify that well is updated or not. ");
	}

	@Test(priority = 2, description = "Test delete well assets.")
	public void deleteWell() throws ApplicationException, InterruptedException {
		wellAssetsop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		wellAssetsop.verifyTheWellIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the well is Delete or not after confirm.");
		wellAssetsop.verifyTheWellListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all well.");
	}

}

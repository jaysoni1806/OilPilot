package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class TankBatteryAssetsTest extends TestBase {
	public String tankBattery_name = commonUtil.getRandomString(4);

	@Test(priority = 0, description = "Test create Tank Battery assets.")
	public void createTankBattery() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");

		dashboard.clickOnMenuItem("Tank Battery");
		ExtentReportManager.test.log(Status.PASS, "Click on Tank Battery menu.");

		tankBatteryAstop.clickAddButtonAndVerifyCreateTankBatteryHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and Verify Create Tank Battery half card.");

		tankBatteryAstop.enterTankBatteryName(tankBattery_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Tank Battery name.");

		tankBatteryAstop.selectLease();
		ExtentReportManager.test.log(Status.PASS, "Select Lease.");

		tankBatteryAstop.enterLatValue();
		ExtentReportManager.test.log(Status.PASS, "Enter Latitude value.");

		tankBatteryAstop.enterLongValue();
		ExtentReportManager.test.log(Status.PASS, "Enter Longitude value.");

		tankBatteryAstop.clickAddButtonForCreateNewTankBattery();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");

		tankBatteryAstop.verifyTheTankBatteryIsCreatedOrnot();
		ExtentReportManager.test.log(Status.PASS, "'" + tankBattery_name + "'" + " Tank Battery successfully created.");

	}

	@Test(priority = 1, description = "Test Search Tank Battery.")
	public void searchTankBattery() throws ApplicationException, InterruptedException {
		tankBatteryAstop.enterTankBatteryNameinSearchBox(tankBattery_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Tank Battery name in searchbox");
		tankBatteryAstop.verifySearchedTankbatteryIsExistsOrNot(tankBattery_name);
		ExtentReportManager.test.log(Status.PASS, "Verify the searched Tank Battery is exists or not.");
		tankBatteryAstop.verifyTheTankBatteryListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Tank Battery.");
	}

	@Test(priority = 2, description = "Test edit Tank Battery assets.")
	public void EditTankBattery() throws ApplicationException, InterruptedException {
		tankBatteryAstop.searchLease(1);
		ExtentReportManager.test.log(Status.PASS, "Search recently created Tank Battery for Edit");
		tankBatteryAstop.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the Edit action button is present or not.");
		tankBatteryAstop.enterTankBatteryName();
		ExtentReportManager.test.log(Status.PASS, "Enter Updated Tank Battery name.");

		tankBatteryAstop.clickOnSubmitAndVerifyThatTheTankBAtteryIsUpdateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify that the Tank Battery is update or not.");
		tankBatteryAstop.verifyTheTankBatteryListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Tank Battery.");
	}

	@Test(priority = 3, description = "Test Delete Tank Battery assets.")
	public void deleteSublease() throws ApplicationException, InterruptedException {
		tankBatteryAstop.searchLease(2);
		ExtentReportManager.test.log(Status.PASS, "Search Tank Battery for Delete");
		tankBatteryAstop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		tankBatteryAstop.verifyTheTankBatteryIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the Tank Battery is Delete or not after confirm.");
		tankBatteryAstop.verifyTheTankBatteryListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Tank Battery.");
	}

}

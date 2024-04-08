package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class LeaseGroupAssetsTest extends TestBase {
	public String lease_group_name = commonUtil.getRandomString(4);

	@Test(priority = 0, description = "Test create Lease Group assets.")
	public void createLeaseGroup() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Lease Group");
		ExtentReportManager.test.log(Status.PASS, "Click on Lease Group menu.");
		lease_groupAstop.clickAddButtonAndVerifyCreateLeaseGroupHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and Verify Create Lease Group half card.");
		lease_groupAstop.enterLeaseGroupName(lease_group_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Lease Group name.");
		lease_groupAstop.selectPumper();
		ExtentReportManager.test.log(Status.PASS, "Select pumper");
		lease_groupAstop.selectCompany();
		ExtentReportManager.test.log(Status.PASS, "Select company");
		lease_groupAstop.clickAddButtonForCreateNewLeaseGroup();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");
		lease_groupAstop.verifyTheLeaseGroupIsCreatedOrnot();
		ExtentReportManager.test.log(Status.PASS, "'" + lease_group_name + "'" + " Lease Group successfully created.");
	}

	@Test(priority = 1, description = "Test Search Lease Group.")
	public void searchLeaseGroup() throws ApplicationException, InterruptedException {
		lease_groupAstop.enterLeaseGroupNameinSearchBox(lease_group_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Lease Group name in searchbox");
		lease_groupAstop.verifySearchedLeaseGroupIsExistsOrNot(lease_group_name);
		ExtentReportManager.test.log(Status.PASS, "Verify the searched Lease Group is exists or not.");
		lease_groupAstop.verifyTheLeaseGroupListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Lease Group.");
	}

	@Test(priority = 2, description = "Test edit Lease Group assets.")
	public void EditLeaseGroup() throws ApplicationException, InterruptedException {
		lease_groupAstop.searchLeaseGroup(1);
		ExtentReportManager.test.log(Status.PASS, "Search recently created Lease Group for Edit");
		lease_groupAstop.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the Edit action button is present or not.");
		lease_groupAstop.enterNewLeaseGroupName();
		ExtentReportManager.test.log(Status.PASS, "Enter updated Lease Group name.");
		lease_groupAstop.clickOnSubmitAndVerifyThatTheLeaseGroupIsUpdateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify that the lease is update or not.");
		lease_groupAstop.verifyTheLeaseGroupListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Lease Group.");
	}

	@Test(priority = 3, description = "Test Delete Lease Group assets.")
	public void deleteLeaseGroup() throws ApplicationException, InterruptedException {
		lease_groupAstop.searchLeaseGroup(2);
		ExtentReportManager.test.log(Status.PASS, "Search Lease Group for Delete");
		lease_groupAstop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		lease_groupAstop.verifyTheLeaseGroupIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the lease group is Delete or not after confirm.");
		lease_groupAstop.verifyTheLeaseGroupListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Lease Group.");
	}

}

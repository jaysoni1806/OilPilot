package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class LeaseAssetsTest extends TestBase {
	public String Lease_name = commonUtil.getRandomString(4);

	@Test(priority = 0, description = "Test create Lease assets.")
	public void createLease() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Lease");
		ExtentReportManager.test.log(Status.PASS, "Click on Lease menu.");
		leaseAstop.clickAddButtonAndVerifyCreateLeaseHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and Verify Create Lease half card.");
		leaseAstop.enterLeaseName(Lease_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Lease name.");
		leaseAstop.selectPumper();
		ExtentReportManager.test.log(Status.PASS, "Select pumper");
		leaseAstop.selectCompany();
		ExtentReportManager.test.log(Status.PASS, "Select company");
		leaseAstop.clickAddButtonForCreateNewLease();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");
		leaseAstop.verifyTheLeaseIsCreatedOrnot();
		ExtentReportManager.test.log(Status.PASS, "'" + Lease_name + "'" + " Lease successfully created.");
	}

	@Test(priority = 1, description = "Test Search Lease.")
	public void searchLease() throws ApplicationException, InterruptedException {
		leaseAstop.enterLeaseNameinSearchBox(Lease_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Lease name in searchbox");
		leaseAstop.verifySearchedLeaseIsExistsOrNot(Lease_name);
		ExtentReportManager.test.log(Status.PASS, "Verify the searched Lease is exists or not.");
		leaseAstop.verifyTheLeaseListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Field.");
	}

	@Test(priority = 2, description = "Test edit Lease assets.")
	public void EditLease() throws ApplicationException, InterruptedException {
		leaseAstop.searchLease(1);
		ExtentReportManager.test.log(Status.PASS, "Search recently created Lease for Edit");
		leaseAstop.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the Edit action button is present or not.");
		leaseAstop.enterNewLeaseName();
		ExtentReportManager.test.log(Status.PASS, "Enter Updated lease name.");
		leaseAstop.clickOnSubmitAndVerifyThatTheLeaseIsUpdateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify that the lease is update or not.");
		leaseAstop.verifyTheLeaseListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all field.");
	}

	@Test(priority = 3, description = "Test Delete Lease assets.")
	public void deleteLease() throws ApplicationException, InterruptedException {
		leaseAstop.searchLease(2);
		ExtentReportManager.test.log(Status.PASS, "Search Lease for Delete");
		leaseAstop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		leaseAstop.verifyTheLeaseIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the field is Delete or not afte confirm.");
		leaseAstop.verifyTheLeaseListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all companies.");
	}

}

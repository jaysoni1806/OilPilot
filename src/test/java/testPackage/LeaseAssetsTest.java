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

		leaseAstop.selectLease();
		ExtentReportManager.test.log(Status.PASS, "Select lease.");

		leaseAstop.enterNRIValue();
		ExtentReportManager.test.log(Status.PASS, "Enter NRI value.");

		leaseAstop.enterTaxRateValue();
		ExtentReportManager.test.log(Status.PASS, "Enter Tax Rate value.");

		leaseAstop.selectField();
		ExtentReportManager.test.log(Status.PASS, "Enter field value.");

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
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Lease.");
	}

	@Test(priority = 2, description = "Test edit Lease assets.")
	public void EditLease() throws ApplicationException, InterruptedException {
		leaseAstop.searchLease(1);
		ExtentReportManager.test.log(Status.PASS, "Search recently created Lease for Edit");
		leaseAstop.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the Edit action button is present or not.");
		leaseAstop.enterNewLeaseName();
		ExtentReportManager.test.log(Status.PASS, "Enter Updated Lease name.");
		leaseAstop.clickOnSubmitAndVerifyThatTheLeaseIsUpdateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify that the Lease is update or not.");
		leaseAstop.verifyTheLeaseListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Lease.");
	}

	@Test(priority = 3, description = "Test Delete Lease assets.")
	public void deleteLease() throws ApplicationException, InterruptedException {
		leaseAstop.searchLease(2);
		ExtentReportManager.test.log(Status.PASS, "Search Lease for Delete");
		leaseAstop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		leaseAstop.verifyTheLeaseIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the Lease is Delete or not after confirm.");
		leaseAstop.verifyTheLeaseListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Lease.");
	}

}

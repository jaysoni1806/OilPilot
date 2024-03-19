package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class SubLeaseAssetsTest extends TestBase {
	public String subLease_name = commonUtil.getRandomString(4);

	@Test(priority = 0, description = "Test create Sublease assets.")
	public void createLease() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");

		dashboard.clickOnMenuItem("Sublease");
		ExtentReportManager.test.log(Status.PASS, "Click on Sublease menu.");

		subleaseAstop.clickAddButtonAndVerifyCreateSubLeaseHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and Verify Create Sublease half card.");

		subleaseAstop.enterLeaseName(subLease_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Sublease name.");

		subleaseAstop.selectLease();
		ExtentReportManager.test.log(Status.PASS, "Select lease.");

		subleaseAstop.enterNRIValue();
		ExtentReportManager.test.log(Status.PASS, "Enter NRI value.");

		subleaseAstop.enterTaxRateValue();
		ExtentReportManager.test.log(Status.PASS, "Enter Tax Rate value.");

		subleaseAstop.selectField();
		ExtentReportManager.test.log(Status.PASS, "Enter field value.");

		subleaseAstop.clickAddButtonForCreateNewSublease();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");

		subleaseAstop.verifyTheSubleaseIsCreatedOrnot();
		ExtentReportManager.test.log(Status.PASS, "'" + subLease_name + "'" + " Sublease successfully created.");

	}

	@Test(priority = 1, description = "Test Search Sublease.")
	public void searchSublease() throws ApplicationException, InterruptedException {
		subleaseAstop.enterSubleaseNameinSearchBox(subLease_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Sublease name in searchbox");
		subleaseAstop.verifySearchedSubleaseIsExistsOrNot(subLease_name);
		ExtentReportManager.test.log(Status.PASS, "Verify the searched Sublease is exists or not.");
		subleaseAstop.verifyTheSubleaseListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Sublease.");
	}

	@Test(priority = 2, description = "Test edit Sublease assets.")
	public void editField() throws ApplicationException, InterruptedException {
		subleaseAstop.searchLease(1);
		ExtentReportManager.test.log(Status.PASS, "Search recently created Sublease for Edit");
		subleaseAstop.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the Edit action button is present or not.");
		subleaseAstop.enterNewSubleaseName();
		ExtentReportManager.test.log(Status.PASS, "Enter Updated Sublease name.");

		subleaseAstop.clickOnSubmitAndVerifyThatTheLeaseIsUpdateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify that the Sublease is update or not.");
		subleaseAstop.verifyTheSubleaseListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Sublease.");
	}

	@Test(priority = 3, description = "Test Delete Lease assets.")
	public void deleteLease() throws ApplicationException, InterruptedException {
		subleaseAstop.searchLease(2);
		ExtentReportManager.test.log(Status.PASS, "Search Sublease for Delete");
		subleaseAstop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		subleaseAstop.verifyTheSubleaseIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the field is Delete or not afte confirm.");
		subleaseAstop.verifyTheSubleaseListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Sublease.");
	}

}

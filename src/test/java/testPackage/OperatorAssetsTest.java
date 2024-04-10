package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class OperatorAssetsTest extends TestBase {
	public String operatore_name = commonUtil.getRandomString(10);

	@Test(priority = 0, description = "Test create Operator assets.")
	public void createOperator() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Operator");
		ExtentReportManager.test.log(Status.PASS, "Click on Operator menu.");
		operatorAssetsop.clickAddButtonAndVerifyCreateOperatorHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on add button and verify create operator half card.");
		operatorAssetsop.enterOperatorName(operatore_name);
		ExtentReportManager.test.log(Status.PASS, "Enter operator name.");
		operatorAssetsop.enterOperatorPhone();
		ExtentReportManager.test.log(Status.PASS, "Enter operator phone.");
		operatorAssetsop.enterOperatorEmail();
		ExtentReportManager.test.log(Status.PASS, "Enter operator phone.");
		operatorAssetsop.enterOperatorAddress();
		ExtentReportManager.test.log(Status.PASS, "Enter operator address.");
		operatorAssetsop.selectStatus();
		ExtentReportManager.test.log(Status.PASS, "Status selected.");
		operatorAssetsop.clickAddButtonForCreateNewLease();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");
		operatorAssetsop.verifyTheLeaseIsCreatedOrnot();
		ExtentReportManager.test.log(Status.PASS, "'" + operatore_name + "'" + " operator successfully created.");
	}

	@Test(priority = 1, description = "Test search operator.")
	public void searchOperator() throws ApplicationException, InterruptedException {
		operatorAssetsop.enterOperatorNameinSearchBox(operatore_name);
		ExtentReportManager.test.log(Status.PASS, "Enter operator name in searchbox");
		operatorAssetsop.verifySearchedOperatorIsExistsOrNot(operatore_name);
		ExtentReportManager.test.log(Status.PASS, "Verify the searched operator is exists or not.");
		operatorAssetsop.verifyTheOperatorListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all operator.");
	}

	@Test(priority = 2, description = "Test edit operator assets.")
	public void EditOperator() throws ApplicationException, InterruptedException {
		operatorAssetsop.searchOperator(1);
		ExtentReportManager.test.log(Status.PASS, "Search recently created Lease for Edit");
		operatorAssetsop.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the Edit half card is present or not when click edit.");
		operatorAssetsop.enterNewOperatorName();
		ExtentReportManager.test.log(Status.PASS, "Enter updated operator name.");
		operatorAssetsop.clickOnSubmitAndVerifyThatTheOperatorIsUpdateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify that the Lease is update or not.");
		operatorAssetsop.verifyTheOperatorListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Lease.");
	}

	@Test(priority = 3, description = "Test delete operator assets.")
	public void deleteOperator() throws ApplicationException, InterruptedException {
		operatorAssetsop.searchOperator(2);
		ExtentReportManager.test.log(Status.PASS, "Search operator for delete");
		operatorAssetsop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		operatorAssetsop.verifyTheOperatorIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the operator is delete or not after confirm.");
		operatorAssetsop.verifyTheOperatorListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all operator.");
	}

}

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
import pageClass.OperatorAssetspage;
import testBase.TestBase;

public class OperatorAssetsTest extends TestBase {
	public String operatore_name = commonUtil.getRandomString(10);

	OperatorAssetspage operatorAssetsop;
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

	@Test(priority = 0, description = "Test create Operator assets.")
	public void createOperator() throws ApplicationException, InterruptedException {
		operatorAssetsop = OperatorAssetspage.operatorAssetsPage();
		dashboard = DashBoardPage.dashBoardPage();

		dashboard.clickOnAssets();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Operator");
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Operator menu.");
		operatorAssetsop.clickAddButtonAndVerifyCreateOperatorHalfCardIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Click on add button and verify create operator half card.");
		operatorAssetsop.enterOperatorName(operatore_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter operator name.");
		operatorAssetsop.enterOperatorPhone();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter operator phone.");
		operatorAssetsop.enterOperatorEmail();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter operator email.");
		operatorAssetsop.enterOperatorAddress();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter operator address.");
		operatorAssetsop.selectStatus();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Status selected.");
		operatorAssetsop.clickAddButtonForCreateNewLease();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on submit button.");
		operatorAssetsop.verifyTheLeaseIsCreatedOrnot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"'" + operatore_name + "'" + " operator successfully created.");
	}

	@Test(priority = 1, description = "Test search operator.")
	public void searchOperator() throws ApplicationException, InterruptedException {
		operatorAssetsop.enterOperatorNameinSearchBox(operatore_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter operator name in searchbox");
		operatorAssetsop.verifySearchedOperatorIsExistsOrNot(operatore_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify the searched operator is exists or not.");
		operatorAssetsop.verifyTheOperatorListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all operator.");
	}

	@Test(priority = 2, description = "Test edit operator assets.")
	public void editOperator() throws ApplicationException, InterruptedException {
		operatorAssetsop.searchOperator(1);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Search recently created operator for Edit");
		operatorAssetsop.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Verify the Edit half card is present or not when click edit.");
		operatorAssetsop.enterNewOperatorName();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter updated operator name.");
		operatorAssetsop.clickOnSubmitAndVerifyThatTheOperatorIsUpdateOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify that the operator is update or not.");
		operatorAssetsop.verifyTheOperatorListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all operator.");
	}

	@Test(priority = 3, description = "Test delete operator assets.")
	public void deleteOperator() throws ApplicationException, InterruptedException {
		operatorAssetsop.searchOperator(2);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Search operator for delete");
		operatorAssetsop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		operatorAssetsop.verifyTheOperatorIsDeletedorNotAfterConfirm();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Verify that the operator is delete or not after confirm.");
		operatorAssetsop.verifyTheOperatorListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all operator.");
	}

}

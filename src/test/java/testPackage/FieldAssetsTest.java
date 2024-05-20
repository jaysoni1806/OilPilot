package testPackage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import pageClass.DashBoardPage;
import pageClass.FiledAssetsPage;
import pageClass.LoginPage;
import testBase.TestBase;

public class FieldAssetsTest extends TestBase {
	public String field_name = commonUtil.getRandomString(8);
	public String sonris_id = commonUtil.getRandomNumber(4);

	FiledAssetsPage fieldAstsop;
	DashBoardPage dashboard;

	@BeforeClass
	// @Parameters({ "username", "password" })
	public void validLoginTest() throws ApplicationException {

		LoginPage login = LoginPage.loginpage();

		login.enterEmail(props.getProperty("USERNAME"));
		login.enterPAssword(props.getProperty("PASSWORD"));
		login.clickSubmitButton();
		login.validateLogin(props.getProperty("LOGIN_FLAG"));
		waitAfterTest();
	}

	@Test(priority = 0, description = "Test create Field assets.")
	public void createField() throws ApplicationException, InterruptedException {
		fieldAstsop = FiledAssetsPage.fieldAssetsPage();
		dashboard = DashBoardPage.dashBoardPage();
		dashboard.clickOnAssets();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Field");
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Field menu.");
		fieldAstsop.clickAddButtonAndVerifyAddFieldHalfCardIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Add button and Verify Add Field half card.");
		fieldAstsop.EnterFiledDetails(field_name, sonris_id);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Entered Field details");
		fieldAstsop.clickAddButtonForCreateNewCompany();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Submit button.");
		fieldAstsop.verifyTheFleldIsCreatedOrnot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "'" + field_name + "'" + " Field successfully created.");
	}

	@Test(priority = 1, description = "Test Search Field.")
	public void searchFleld() throws ApplicationException, InterruptedException {
		fieldAstsop.enterFieldNameinSearchBox(field_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter Field name in searchbox");
		fieldAstsop.verifySearchedFieldIsExistsOrNot(field_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify the searched Field is exists or not.");
		fieldAstsop.verifyTheFieldListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all Field.");
	}

	@Test(priority = 2, description = "Test edit Field assets.")
	public void editField() throws ApplicationException, InterruptedException {
		fieldAstsop.searchRecentAddedFieldForEdit();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Search recently created Field for Edit");
		fieldAstsop.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify the Edit action button is present or not.");
		fieldAstsop.enterNewFieldName();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter Updated field name.");
		fieldAstsop.clickOnsubmitAndVerifyThatTheFieldIsUpdateOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify that the field is update or not.");
		fieldAstsop.verifyTheFieldListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all field.");
	}

	@Test(priority = 3, description = "Test Delete Field assets.")
	public void deleteField() throws ApplicationException, InterruptedException {
		fieldAstsop.searchRecentAddedFieldForDelete();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Received recently updated field for Delete.");
		fieldAstsop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		fieldAstsop.verifyTheFieldIsDeletedorNotAfterConfirm();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify that the field is Delete or not after confirm.");
		fieldAstsop.verifyTheFieldListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all companies.");
	}

}

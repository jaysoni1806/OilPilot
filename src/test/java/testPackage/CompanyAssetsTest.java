package testPackage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import pageClass.CompanyAssetsPage;
import pageClass.DashBoardPage;
import pageClass.LoginPage;
import testBase.TestBase;

public class CompanyAssetsTest extends TestBase {
	public String Company_name;
	public static CompanyAssetsPage cmpAstsOp;
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

	@Test(priority = 0, description = "Test create Company assets.")
	public void createCompany() throws ApplicationException, InterruptedException {

		cmpAstsOp = CompanyAssetsPage.companyAssetsPage();
		dashboard = DashBoardPage.dashBoardPage();

		Company_name = props.getProperty("COMPANY_NAME") + commonUtil.getRandomNumber(3);
		dashboard.clickOnAssets();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Company");
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Company menu.");
		cmpAstsOp.clickAddButtonAndVerifyCreateCompnayHalfCardIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Click on Add button and Verify Create Company half card.");
		cmpAstsOp.enterCompanyName(Company_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter Company name.");
		cmpAstsOp.clickAddButtonForCreateNewCompany();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Submit button.");
		cmpAstsOp.verifyTheCompanyIsCreatedOrnot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"'" + Company_name + "'" + " Company successfully created.");

	}

	@Test(priority = 1, description = "Test Search company.")
	public void searchCompany() throws ApplicationException, InterruptedException {
		cmpAstsOp.enterCompanyNameinSearchBox(Company_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter Company name in searchbox");
		cmpAstsOp.verifySearchedCompanyIsExistsOrNot(Company_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify the searched company is exists or not.");
		cmpAstsOp.verifyTheCompanyListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all companies.");
	}

	@Test(priority = 2, description = "Test Edit Company assets.")
	public void editCompany() throws ApplicationException, InterruptedException {
		cmpAstsOp.searchRecentAddedCompanyForEdit();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Search recently created company for Edit");
		cmpAstsOp.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify the Edit action button is present or not.");
		cmpAstsOp.enterNewCompanyName();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter Updated company name.");
		cmpAstsOp.clickOnsubmitAndVerifyThatTheCompanyIsUpdateOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify that the company is update or not.");
		cmpAstsOp.verifyTheCompanyListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all companies.");
	}

	@Test(priority = 3, description = "Test Delete Company assets.")
	public void deleteCompany() throws ApplicationException, InterruptedException {
		cmpAstsOp.searchRecentUpdatedCompanyForDelete();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Received recently updated company for Delete.");
		cmpAstsOp.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		cmpAstsOp.verifyTheCompanyIsDeletedorNotAfterConfirm();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify that the compnay is Delete or not afte confirm.");
		cmpAstsOp.verifyTheCompanyListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all companies.");
	}

}

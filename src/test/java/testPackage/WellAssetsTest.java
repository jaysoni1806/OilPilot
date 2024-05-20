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
import pageClass.WellAssetsPage;
import testBase.TestBase;

public class WellAssetsTest extends TestBase {
	public String well_name = "QA Well " + commonUtil.getRandomNumber(3);
	WellAssetsPage wellAssetsop;

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

	@Test(priority = 0, description = "Test create well assets.")
	public void createWell() throws ApplicationException, InterruptedException {
		wellAssetsop = WellAssetsPage.wellAssetsPage();
		dashboard = DashBoardPage.dashBoardPage();

		dashboard.clickOnAssets();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Well");
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on Well menu.");
		wellAssetsop.clickAddButtonAndVerifyCreateWellSceenIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Click on add button and verify add tank screen.");
		wellAssetsop.enterRequiredDetailsToAddWell(well_name);
		ExtentReportManager.extentTest.get().log(Status.PASS, "Entered required details for well.");
		wellAssetsop.clickOnSubmitAndVerifyWellIsCreateOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Click on Submit button and verify that well is created or not. ");
	}

	@Test(priority = 1, description = "Test edit well assets.")
	public void editWell() throws ApplicationException, InterruptedException {
		wellAssetsop.clickEdit_actionUnderThePerent_actionandVerifyEditWellScreenIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify the edit well screen present or not.");
		wellAssetsop.enterNewWellName();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Enter new tank name.");
		wellAssetsop.clickOnSubmitAndVerifyWellIsUpdatedOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Click on Submit button and verify that well is updated or not. ");
	}

	@Test(priority = 2, description = "Test delete well assets.")
	public void deleteWell() throws ApplicationException, InterruptedException {
		wellAssetsop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.extentTest.get().log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		wellAssetsop.verifyTheWellIsDeletedorNotAfterConfirm();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Verify that the well is Delete or not after confirm.");
		wellAssetsop.verifyTheWellListAfterClearSearchBox();
		ExtentReportManager.extentTest.get().log(Status.PASS, "Clear searchbox to get all well.");
	}

}

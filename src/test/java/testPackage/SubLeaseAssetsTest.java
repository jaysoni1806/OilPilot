package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class SubLeaseAssetsTest extends TestBase {
	public String subLease_name = commonUtil.getRandomString(4);

	@Test(priority = 0, dependsOnMethods = { "validlogin" }, description = "Test create Sublease assets.")
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

}

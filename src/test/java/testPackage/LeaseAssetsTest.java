package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;

import testBase.TestBase;

public class LeaseAssetsTest extends TestBase {

	@Test(priority = 0, description = "Test create Company assets.")
	public void createCompany() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Lease");
		ExtentReportManager.test.log(Status.PASS, "Click on Lease menu.");
		leaseAstop.clickAddButtonAndVerifyCreateLeaseHalfCardIsPresentOrNot();
		leaseAstop.selectCompany();
	}

}

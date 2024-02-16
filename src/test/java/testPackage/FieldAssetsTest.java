package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class FieldAssetsTest extends TestBase {
	public String field_name = commonUtil.getRandomString(8);
	public String sonris_id = commonUtil.getRandomNumber(4);

	@Test
	public void createField() throws ApplicationException, InterruptedException {
		fieldAstsop.verifyTheScreen();
		ExtentReportManager.test.log(Status.PASS, "Navigate to Filed Screen.");
		fieldAstsop.clickAddButtonAndVerifyAddFieldHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and Verify Add Field half card.");
		fieldAstsop.EnterFiledDetails(field_name, sonris_id);
		ExtentReportManager.test.log(Status.PASS, "Entered Field details");
		fieldAstsop.clickAddButtonForCreateNewCompany();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");
		fieldAstsop.verifyTheFleldIsCreatedOrnot();
		ExtentReportManager.test.log(Status.PASS, "'" + field_name + "'" + " Field successfully created.");

	}

}

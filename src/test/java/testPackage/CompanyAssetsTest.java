package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class CompanyAssetsTest extends TestBase{
	public String Company_name = commonUtil.getSaltString();
	String classname = new Exception().getStackTrace()[0].getClassName();
	
	@Test(priority = 0)
	public void createCompany() {
		System.out.println("** Test Company assets. ");
		cmpAstsOp.createCompany(Company_name);
		ExtentReportManager.test.log(Status.INFO, "Createing new company as assets and verifying");
	}
	@Test(priority = 1)
	public void searchCompany() {
		cmpAstsOp.searchCompany(Company_name);
		ExtentReportManager.test.log(Status.INFO, "Searching a company and verifying");
	}
	@Test(priority = 2)
	public void editCompany()
	{
		cmpAstsOp.editCompany(Company_name);
	}
	@Test(priority = 3)
	public void deleteCompany()
	{
		cmpAstsOp.deleteCompany();
	}
}
 
package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class CompanyAssetsTest extends TestBase{
	public String Company_name = commonUtil.getRandomString();
	
	
	@Test(priority = 0,description = "Test Company assets.")
	public void createCompany() {
		ExtentReportManager.test.log(Status.INFO, "Test Company assets.");
		log.info("Test Company assets.");
		cmpAstsOp.createCompany(Company_name);
	}
	@Test(priority = 1,description = "Test Search company.")
	public void searchCompany() {
		ExtentReportManager.test.log(Status.INFO, "Test search company assets.");
		log.info("Test search company assets.");
		cmpAstsOp.searchCompany(Company_name);
	}
	@Test(priority = 2,description ="Test Edit Company assets.")
	public void editCompany()
	{
		ExtentReportManager.test.log(Status.INFO, "Test Edit Company assets.");
		log.info("Test Edit Company assets.");
		cmpAstsOp.editCompany(Company_name);
	}
	@Test(priority = 3,description = "Test Delete Company assets.")
	public void deleteCompany()
	{
		ExtentReportManager.test.log(Status.INFO, "Test Delete Company assets.");
		log.info("Test Delete Company assets.");
		cmpAstsOp.deleteCompany();
	}
}
 
package pageClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.Utility;

import testBase.TestBase;

public class CompanyAssetsOperations {

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	String updatedComp;

	public CompanyAssetsOperations(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
	}

	@FindBy(xpath = "//h5[text()='Company']")
	private WebElement headerCompany;
	@FindBy(xpath = "//span[@role='progressbar']")
	private WebElement progrssbasCompany;
	@FindBy(xpath = "//div[contains(@class,'css-1b8fkut')]//button")
	private WebElement addBtton;
	@FindBy(xpath = "//h5[text()='Add Company']")
	private WebElement popupAddCompany;
	@FindBy(xpath = "//input[@placeholder='Company name']")
	private WebElement inputCompanyName;
	// @FindBy (xpath = "//input[@placeholder='Company
	// name']/parent::div/parent::div/following-sibling::div/button[1]")private
	// WebElement addCompny_SubmitButton;
	@FindBy(xpath = "//h5[text()='Add Company']/parent::div/following-sibling::div//button[@type='submit']")
	private WebElement addCompny_SubmitButton;
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement inputSearch;
	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]//div[@data-field='actions']/button")
	private WebElement actionBtton;
	@FindBy(xpath = "//ul[contains(@class,'MuiMenu-list')]/parent::div[not(contains(@style,'visibility'))]//li[text()='Edit']")
	private WebElement editAction;
	@FindBy(xpath = "//ul[contains(@class,'MuiMenu-list')]/parent::div[not(contains(@style,'visibility'))]//li[text()='Delete']")
	private WebElement deleteAction;
	@FindBy(xpath = "//h5[text()='Edit Company']")
	private WebElement popupEditCompany;
	@FindBy(xpath = "//input[@placeholder='company name']")
	private WebElement inputEditCompanyName;
	@FindBy(xpath = "//h5[text()='Edit Company']/parent::div/following-sibling::div//button[@type='submit']")
	private WebElement editCompny_SubmitButton;
	@FindBy(xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Company']//button[text()='Yes']")
	private WebElement deleteConfirmYesButton;

	public void createCompany(String CmpName) {

		utility.WaitUntilElementIsNotClickable(dashboard.asset, 10);
		ExtentReportManager.test.log(Status.INFO, "Waiting until Assets manu is clickable.");
		utility.WaitFor2Second();

		utility.Submit(dashboard.asset);
		ExtentReportManager.test.log(Status.PASS, "Click on Assets menu.");
		TestBase.log.info("Click on Assets menu.");
		utility.WaitFor2Second();

		utility.WaitUntilElementIsNotClickable(dashboard.list_Company, 10);
		ExtentReportManager.test.log(Status.INFO, "Waiting until Company manu is clickable.");
		utility.Submit(dashboard.list_Company);
		ExtentReportManager.test.log(Status.PASS, "Click on Company list item.");
		TestBase.log.info("Click on Company list item.");

		utility.WaitUntilElementIsNotVisible(progrssbasCompany, 10);
		ExtentReportManager.test.log(Status.INFO, "Waiting until Add button is clickable.");
		utility.Submit(addBtton);
		ExtentReportManager.test.log(Status.PASS, "Click on Company Add button.");
		TestBase.log.info("Click on Company Add button.");

		utility.WaitForASecond(popupAddCompany, 10);
		ExtentReportManager.test.log(Status.INFO, "Waiting until Add company half card is present.");
		TestBase.log.info("Add company half card is present.");

		utility.ClearTextBox(inputCompanyName);
		ExtentReportManager.test.log(Status.PASS, "Clear the Company text box.");
		TestBase.log.info("Clear the Company text box.");

		utility.SendValues(inputCompanyName, CmpName);
		ExtentReportManager.test.log(Status.PASS, "Enter Company name.");
		TestBase.log.info("Enter Company name.");

		utility.WaitUntilElementIsNotClickable(addCompny_SubmitButton, 10);
		ExtentReportManager.test.log(Status.INFO, "Waiting until Add submit button is clickable.");
		utility.Submit(addCompny_SubmitButton);
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");
		TestBase.log.info("Click on Submit button.");

		utility.waitUntilToastPresent(dashboard.toastMessage);
		ExtentReportManager.test.log(Status.INFO, "Waiting until success toast present.");
		Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully created the Company.");
		ExtentReportManager.test.log(Status.PASS, "'" + CmpName + "'" + " Company successfully created.");
		TestBase.log.info("'" + CmpName + "'" + " Company successfully created.");
		utility.WaitFor2Second();

	}

	public void searchCompany(String CmpName) {

		utility.ClearTextBox(inputSearch);
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox.");
		TestBase.log.info("Clear searchbox.");

		utility.SendValues(inputSearch, CmpName);
		ExtentReportManager.test.log(Status.PASS, "Entered company name in searchbox.");
		TestBase.log.info("Entered company name in searchbox.");

		utility.WaitFor2Second();

		List<WebElement> searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
		if (searchRecords.size() > 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement companyRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if (CmpName.equals(companyRow.getText())) {
					ExtentReportManager.test.log(Status.PASS, "Record searched successfully.");
					TestBase.log.info("Record searched successfully.");
				}
			}
		} else {
			ExtentReportManager.test.log(Status.PASS, "No Record presented yet.");
			TestBase.log.info("No Record presented yet.");
		}
		clearSearchBox();
		utility.WaitFor2Second();
	}

	public void editCompany(String CmpName) {

		search(CmpName);
		ExtentReportManager.test.log(Status.PASS, "Received recently added company for Edit.");
		TestBase.log.info("Received recently added company for Edit.");

		utility.WaitUntilElementIsNotClickable(actionBtton, 5);
		ExtentReportManager.test.log(Status.INFO, "Waiting until action button is clickable.");
		TestBase.log.info("Waiting until action button is clickable.");

		utility.Submit(actionBtton);
		ExtentReportManager.test.log(Status.PASS, "Click action button.");
		TestBase.log.info("Click action button.");

		utility.WaitUntilElementIsNotClickable(editAction, 2);
		ExtentReportManager.test.log(Status.INFO, "Waiting until edit button is clickable.");
		TestBase.log.info("Waiting until edit button is clickable.");

		utility.Submit(editAction);
		ExtentReportManager.test.log(Status.PASS, "Action menu presented, Cliked Edit action.");
		TestBase.log.info("Action menu presented, Cliked Edit action.");

		utility.WaitForASecond(popupEditCompany, 10);
		ExtentReportManager.test.log(Status.PASS, "Edit Company half screen present.");
		TestBase.log.info("Edit Company half screen present.");

		updatedComp = CmpName.concat("Edited");

		utility.Submit(inputEditCompanyName);
		ExtentReportManager.test.log(Status.PASS, "Click on Company name.");
		TestBase.log.info("Click on Company name.");
		utility.WaitFor2Second();

		utility.ClearTextBox(inputEditCompanyName);
		ExtentReportManager.test.log(Status.PASS, "Clear text box.");
		TestBase.log.info("Clear text box.");
		utility.WaitFor2Second();

		utility.SendValues(inputEditCompanyName, updatedComp);
		ExtentReportManager.test.log(Status.PASS, "Input upgreaded Company name.");
		TestBase.log.info("Input upgreaded Company name.");

		utility.WaitUntilElementIsNotClickable(editCompny_SubmitButton, 10);
		utility.Submit(editCompny_SubmitButton);
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");
		TestBase.log.info("Click on Submit button.");

		utility.waitUntilToastPresent(dashboard.toastMessage);
		Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully updated the Company.");
		ExtentReportManager.test.log(Status.PASS, "'" + updatedComp + "'" + " Company update successfully created.");
		TestBase.log.info("'" + updatedComp + "'" + " Company update successfully created.");

		clearSearchBox();
		utility.WaitFor2Second();
	}

	public void deleteCompany() {
		search(updatedComp);
		ExtentReportManager.test.log(Status.PASS, "Received recently updated company for Delete.");
		TestBase.log.info("Received recently updated company for Delete.");

		utility.WaitUntilElementIsNotClickable(actionBtton, 5);
		utility.Submit(actionBtton);
		ExtentReportManager.test.log(Status.PASS, "Cliked action button.");
		TestBase.log.info("Cliked action button.");

		utility.WaitUntilElementIsNotClickable(deleteAction, 2);
		utility.Submit(deleteAction);
		ExtentReportManager.test.log(Status.PASS, "Action menu presented, Cliked Delete action.");
		TestBase.log.info("Action menu presented, Cliked Delete action.");

		utility.WaitUntilElementIsNotClickable(deleteConfirmYesButton, 2);
		ExtentReportManager.test.log(Status.INFO, "Waiting for delete confirmation popup.");
		TestBase.log.info("Waiting for delete confirmation popup.");

		utility.Submit(deleteConfirmYesButton);
		ExtentReportManager.test.log(Status.INFO, "Cliked on Yes button to confirm delete record.");
		TestBase.log.info("Cliked on Yes button to confirm delete record.");

		utility.waitUntilToastPresent(dashboard.toastMessage);
		Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully deleted the Company.");
		ExtentReportManager.test.log(Status.INFO, "Cliked on Yes button to confirm delete record.");
		TestBase.log.info("'" + updatedComp + "'" + " Company has been deleted successfully.");

		clearSearchBox();
		utility.WaitFor2Second();

	}

	public void clearSearchBox() {
		utility.Submit(inputSearch);
		utility.ClearTextBox(inputSearch);
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to retrive all records.");
		TestBase.log.info("Clear searchbox to retrive all records.");

	}

	public void search(String CmpName) {
		utility.ClearTextBox(inputSearch);

		utility.SendValues(inputSearch, CmpName);

		utility.WaitFor2Second();

		List<WebElement> searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
		if (searchRecords.size() > 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement companyRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if (CmpName.equals(companyRow.getText())) {
					ExtentReportManager.test.log(Status.INFO, "Searching recently added company.");
					TestBase.log.info("'" + updatedComp + "'" + " Searching recently added company.");
				}
			}
		} else {
			System.out.println("- ");
			ExtentReportManager.test.log(Status.INFO, "No Record presented yet.");
			TestBase.log.info("No Record presented yet.");
		}
	}

}

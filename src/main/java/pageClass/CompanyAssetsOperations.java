package pageClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.Utility;

import testBase.TestBase;

public class CompanyAssetsOperations {

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	String updatedComp;
	public String Company_name;
	public String expactedToastMessage;

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

	private List<WebElement> searchRecords;

	public void clickOnAssets() throws ApplicationException {
		if (dashboard.asset.isDisplayed()) {
			utility.WaitUntilElementIsNotClickable(dashboard.asset, 10);
			TestBase.log.info("Waiting until Assets manu is clickable.");
			utility.Submit(dashboard.asset);
			TestBase.log.info("Click on Assets menu.");
		} else {
			throw new ApplicationException("Exception Occured", "Assets is not display.");
		}
	}

	public void clickOnComapnyMenuItem() throws ApplicationException {
		if (dashboard.list_Company.isDisplayed()) {
			utility.WaitUntilElementIsNotClickable(dashboard.list_Company, 5);
			TestBase.log.info("Wait until Company manu is clickable.");
			utility.Submit(dashboard.list_Company);
			TestBase.log.info("Click on Company list item.");

		} else if (dashboard.assets_CollapseHidden.isDisplayed()) {
			TestBase.log.info("Assets menu has been collepse");
			clickOnAssets();
			TestBase.log.info("Call Click Assets functionagain");
		}

		else {
			throw new ApplicationException("Exception Occured", "Company Assets is not display.");
		}
	}

	public void clickAddButtonAndVerifyCreateCompnayHalfCardIsPresentOrNot() throws ApplicationException {
		searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));

		utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		TestBase.log.info("Wait until loading company list.");

		if (addBtton.isDisplayed()) {
			utility.Submit(addBtton);
			TestBase.log.info("Click on Company Add button.");

			utility.WaitForASecond(popupAddCompany, 10);
			TestBase.log.info("Add company half card is present.");
		} else {
			throw new ApplicationException("Exception Occured", "Add button is not Present or Not Clickable.");
		}
	}

	public void enterCompanyName(String CmpName) throws ApplicationException {
		Company_name = CmpName;

		if (inputCompanyName.isDisplayed()) {
			utility.ClearTextBox(inputCompanyName);
			TestBase.log.info("Clear the Company text box.");

			utility.SendValues(inputCompanyName, CmpName);
			TestBase.log.info("Enter Company name.");
		} else {
			throw new ApplicationException("Exception Occured", "Add company input field is not present.");
		}

	}

	public void clickAddButtonForCreateNewCompany() throws ApplicationException {

		if (addCompny_SubmitButton.isDisplayed()) {
			utility.Submit(addCompny_SubmitButton);
			TestBase.log.info("Click on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Submit button field is not present.");
		}

	}

	public void verifyTheCompanyIsCreatedOrnot() throws ApplicationException {

		utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
		enterCompanyNameinSearchBox(Company_name);
		verifySearchedCompanyIsExistsOrNot(Company_name);
		clearSearchBox();
		utility.WaitFor2Second();
		utility.WaitFor2Second();
	}

	public void enterCompanyNameinSearchBox(String CmpName) throws ApplicationException {
		if (inputSearch.isDisplayed()) {
			utility.ClearTextBox(inputSearch);
			TestBase.log.info("Clear searchbox.");

			utility.SendValues(inputSearch, CmpName);
			TestBase.log.info("Entered company name in searchbox.");
		} else {
			throw new ApplicationException("Exception Occured", "Search box is not present.");
		}
		utility.WaitFor2Second();
	}

	public void verifySearchedCompanyIsExistsOrNot(String CmpName) throws ApplicationException {
		searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));

		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement companyRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if (CmpName.equals(companyRow.getText())) {
					TestBase.log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void verifyTheCompanyListAfterClearSearchBox() throws ApplicationException {
		if (inputSearch.isDisplayed()) {
			clearSearchBox();
			searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
			if (searchRecords.size() >= 1) {
				TestBase.log.info("Retrive all records");
			} else {
				throw new ApplicationException("Exception Occured", "Records are not present.");
			}
		} else {
			throw new ApplicationException("Exception Occured", "Search box is not present.");
		}
	}

	public void searchRecentAddedCompanyForEdit(String CmpName) throws ApplicationException {
		enterCompanyNameinSearchBox(CmpName);
		verifySearchedCompanyIsExistsOrNot(CmpName);
	}

	public void clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot() throws ApplicationException {
		if (actionBtton.isDisplayed()) {
			clickOnActionButton();
			if (editAction.isDisplayed()) {
				clickOnEditActionButton();
				if (popupEditCompany.isDisplayed()) {
					TestBase.log.info("Edit Company half screen present.");
				} else {
					throw new ApplicationException("Exception Occured", "Edit Company half screen is not present.");
				}
			} else {
				throw new ApplicationException("Exception Occured", "Edit button is not present.");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void enterNewCompanyName(String CmpName) throws ApplicationException {
		updatedComp = CmpName.concat("Edited");

		if (inputEditCompanyName.isDisplayed()) {
			utility.Submit(inputEditCompanyName);
			TestBase.log.info("Click on Company name.");
			utility.WaitFor2Second();

			utility.ClearTextBox(inputEditCompanyName);
			TestBase.log.info("Clear text box.");
			utility.WaitFor2Second();

			utility.SendValues(inputEditCompanyName, updatedComp);
			TestBase.log.info("Input upgreaded Company name.");

		} else {
			throw new ApplicationException("Exception Occured", "Company inputbox is not present.");
		}
	}

	public void clickOnsubmitAndVerifyThatTheCompanyIsUpdateOrNot() throws ApplicationException {
		if (editCompny_SubmitButton.isDisplayed()) {
			utility.Submit(editCompny_SubmitButton);
			TestBase.log.info("Click on Submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);
			if (dashboard.toastMessage.isDisplayed()) {
				Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully updated the Company.");
				TestBase.log.info("'" + updatedComp + "'" + " Company update successfully.");
			} else {
				throw new ApplicationException("Exception Occured", "Company is not Updated.");
			}
		} else {
			throw new ApplicationException("Exception Occured", "Update button in not present.");
		}
	}

	public void searchRecentUpdatedCompanyForDelete() throws ApplicationException {
		enterCompanyNameinSearchBox(updatedComp);
		verifySearchedCompanyIsExistsOrNot(updatedComp);
	}

	public void clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot()
			throws ApplicationException {
		if (actionBtton.isDisplayed()) {
			clickOnActionButton();
			if (deleteAction.isDisplayed()) {
				clickOnDeleteActionButton();
				if (deleteConfirmYesButton.isDisplayed()) {
					TestBase.log.info("Delete confirmation popup is present.");
				} else {
					throw new ApplicationException("Exception Occured", "Delete confirmation popup is not present.");
				}
			} else {
				throw new ApplicationException("Exception Occured", "Delete button is not present.");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void verifyTheCompanyIsDeletedorNotAfterConfirm() throws ApplicationException {

		utility.Submit(deleteConfirmYesButton);
		TestBase.log.info("Cliked on Yes button to confirm delete record.");
		utility.waitUntilToastPresent(dashboard.toastMessage);

		if (dashboard.toastMessage.isDisplayed()) {
			String expactedToastMessage = dashboard.toastMessage.getText();
			utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
			enterCompanyNameinSearchBox(updatedComp);
			searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));

			if (searchRecords.size() == 0) {
				Assert.assertEquals(expactedToastMessage, "We have successfully deleted the Company.");
				TestBase.log.info("'" + updatedComp + "'" + " Company has been deleted successfully.");
			} else {

			}

		} else {
			throw new ApplicationException("Exception Occured", "Still record is not present.");
		}

	}

	public void clearSearchBox() {
		utility.WaitFor2Second();
		utility.Submit(inputSearch);
		utility.ClearTextBox(inputSearch);
		utility.WaitFor2Second();
	}

	public void clickOnActionButton() {
		utility.WaitUntilElementIsNotClickable(actionBtton, 5);
		TestBase.log.info("Waiting until action button is clickable.");

		utility.Submit(actionBtton);
		TestBase.log.info("Click action button.");
	}

	public void clickOnEditActionButton() {
		utility.WaitUntilElementIsNotClickable(editAction, 2);
		TestBase.log.info("Waiting until edit button is clickable.");

		utility.Submit(editAction);
		TestBase.log.info("Action menu presented, Cliked Edit action.");
	}

	public void clickOnDeleteActionButton() {
		utility.WaitUntilElementIsNotClickable(deleteAction, 2);
		TestBase.log.info("Waiting until delete button is clickable.");

		utility.Submit(deleteAction);
		TestBase.log.info("Action menu presented, Cliked delete action.");
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

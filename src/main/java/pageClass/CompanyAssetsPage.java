package pageClass;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.commonUtil.ApplicationException;
import com.commonUtil.Utility;

import DriverManager.WebDriverManager;
import testBase.TestBase;

public class CompanyAssetsPage {

	public static String updatedComp;
	public static String Company_name;
	public String expactedToastMessage;
	Utility utility;
	commonLocatorsRepo commLocators;
	DashBoardPage dashboard;
	public static Logger log = Logger.getLogger(CompanyAssetsPage.class);

	public CompanyAssetsPage() {
		PageFactory.initElements(WebDriverManager.getDriver(), this);
		utility = Utility.utility();
		commLocators = commonLocatorsRepo.LocatorsRepo();
		dashboard = DashBoardPage.dashBoardPage();
	}

	@FindBy(xpath = "//input[@placeholder='Company name']")
	private WebElement inputCompanyName;
	// @FindBy (xpath = "//input[@placeholder='Company
	// name']/parent::div/parent::div/following-sibling::div/button[1]")private
	// WebElement addCompny_SubmitButton;
	@FindBy(xpath = "//h5[text()='Add Company']/parent::div/following-sibling::div//button[@type='submit']")
	private WebElement addCompny_SubmitButton;
	@FindBy(xpath = "//h5[text()='Edit Company']")
	private WebElement popupEditCompany;
	@FindBy(xpath = "//input[@placeholder='company name']")
	private WebElement inputEditCompanyName;
	@FindBy(xpath = "//h5[text()='Edit Company']/parent::div/following-sibling::div//button[@type='submit']")
	private WebElement editCompny_SubmitButton;
	@FindBy(xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Company']//button[text()='Yes']")
	private WebElement deleteConfirmYesButton;
	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;

	public void clickAddButtonAndVerifyCreateCompnayHalfCardIsPresentOrNot() throws ApplicationException {
		// Utility utility = Utility.utility();
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		// searchRecords =
		// driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
		utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		log.info("Wait until loading company list.");
		commLocators.clickAddButton();
	}

	public void enterCompanyName(String CmpName) throws ApplicationException {
		// Utility utility = Utility.utility();
		Company_name = CmpName;

		if (inputCompanyName.isDisplayed()) {
			utility.ClearTextBox(inputCompanyName);
			log.info("Clear the Company text box.");

			utility.SendValues(inputCompanyName, Company_name);
			log.info("Enter Company name.");
		} else {
			throw new ApplicationException("Exception Occured", "Add company input field is not present.");
		}

	}

	public void clickAddButtonForCreateNewCompany() throws ApplicationException {
		// Utility utility = Utility.utility();

		if (addCompny_SubmitButton.isDisplayed()) {
			utility.Submit(addCompny_SubmitButton);
			log.info("Click on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Submit button field is not present.");
		}

	}

	public void verifyTheCompanyIsCreatedOrnot() throws ApplicationException, InterruptedException {
		// Utility utility = Utility.utility();
		// DashBoardPage dashboard = DashBoardPage.dashBoardPage();
		utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
		enterCompanyNameinSearchBox(Company_name);
		verifySearchedCompanyIsExistsOrNot(Company_name);
		// utility.clearSearchBox();
	}

	public void enterCompanyNameinSearchBox(String CmpName) throws ApplicationException, InterruptedException {
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		commLocators.Search(CmpName);
	}

	public void verifySearchedCompanyIsExistsOrNot(String CmpName) throws ApplicationException {
		// searchRecords =
		// driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));

		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement companyRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if (CmpName.equals(companyRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void verifyTheCompanyListAfterClearSearchBox() throws ApplicationException, InterruptedException {
		// Utility utility = Utility.utility();
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		if (commLocators.inputSearch.isDisplayed()) {
			utility.clearSearchBox(commLocators.inputSearch);
			Thread.sleep(3000);
			// searchRecords =
			// driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
			// utility.WaitUntilListOfElementIsVisible(searchRecords, 5);

			if (searchRecords.size() >= 1) {
				log.info("Retrive all records");
			} else {
				throw new ApplicationException("Exception Occured", "Records are not present.");
			}
		} else {
			throw new ApplicationException("Exception Occured", "Search box is not present.");
		}
	}

	public void searchRecentAddedCompanyForEdit() throws ApplicationException, InterruptedException {
		enterCompanyNameinSearchBox(Company_name);
		verifySearchedCompanyIsExistsOrNot(Company_name);
	}

	public void clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot() throws ApplicationException {
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			if (commLocators.editAction.isDisplayed()) {
				commLocators.clickOnEditActionButton();
				if (popupEditCompany.isDisplayed()) {
					log.info("Edit Company half screen present.");
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

	public void enterNewCompanyName() throws ApplicationException {
		// Utility utility = Utility.utility();
		updatedComp = Company_name.concat("Edited");

		if (inputEditCompanyName.isDisplayed()) {
			utility.Submit(inputEditCompanyName);
			log.info("Click on Company name.");
			utility.WaitFor2Second();

			utility.ClearTextBox(inputEditCompanyName);
			log.info("Clear text box.");
			utility.WaitFor2Second();

			utility.SendValues(inputEditCompanyName, updatedComp);
			log.info("Input upgreaded Company name.");

		} else {
			throw new ApplicationException("Exception Occured", "Company inputbox is not present.");
		}
	}

	public void clickOnsubmitAndVerifyThatTheCompanyIsUpdateOrNot() throws ApplicationException {
		// Utility utility = Utility.utility();
		// DashBoardPage dashboard = DashBoardPage.dashBoardPage();
		if (editCompny_SubmitButton.isDisplayed()) {
			utility.Submit(editCompny_SubmitButton);
			log.info("Click on Submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);

			if (dashboard.toastMessage.isDisplayed()) {
				Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully updated the Company.");
				log.info("'" + updatedComp + "'" + " Company update successfully.");
			} else {
				throw new ApplicationException("Exception Occured", "Company is not Updated.");
			}
		} else {
			throw new ApplicationException("Exception Occured", "Update button in not present.");
		}
	}

	public void searchRecentUpdatedCompanyForDelete() throws ApplicationException, InterruptedException {
		enterCompanyNameinSearchBox(updatedComp);
		verifySearchedCompanyIsExistsOrNot(updatedComp);
	}

	public void clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot()
			throws ApplicationException {
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			if (commLocators.deleteAction.isDisplayed()) {
				commLocators.clickOnDeleteActionButton();
				if (deleteConfirmYesButton.isDisplayed()) {
					log.info("Delete confirmation popup is present.");
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

	public void verifyTheCompanyIsDeletedorNotAfterConfirm() throws ApplicationException, InterruptedException {
		// Utility utility = Utility.utility();
		// DashBoardPage dashboard = DashBoardPage.dashBoardPage();
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		utility.Submit(deleteConfirmYesButton);
		log.info("Cliked on Yes button to confirm delete record.");
		utility.waitUntilToastPresent(dashboard.toastMessage);

		if (dashboard.toastMessage.isDisplayed()) {
			String actual = dashboard.toastMessage.getText();
			utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
			enterCompanyNameinSearchBox(updatedComp);
			// searchRecords =
			// driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));

			if (commLocators.noRow.isDisplayed()) {
				TestBase.softAssert.assertEquals(actual, "We have successfully deleted the Company.");
				log.info("'" + updatedComp + "'" + " Company has been deleted successfully.");
			} else {

			}

		} else {
			throw new ApplicationException("Exception Occured", "Still record is not present.");
		}

	}

	public static CompanyAssetsPage companyAssetsPage() {
		return new CompanyAssetsPage();
	}

}

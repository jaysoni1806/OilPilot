package pageClass;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.commonUtil.ApplicationException;
import com.commonUtil.Utility;

public class LeaseAssetsPage {
	public static Logger log = Logger.getLogger(LeaseAssetsPage.class);

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;
	public String LeaseName;
	public String updatedLease;

	public LeaseAssetsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
		commLocators = new commonLocatorsRepo(driver);
	}

	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//div[text()='Select company']")
	WebElement companyDropDown;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter Name']")
	private WebElement inputLeaseName;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='button']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;
	@FindBy(xpath = "//h5[text()='Edit Lease']")
	private WebElement popupEditLease;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//input[@placeholder='Enter Name']")
	private WebElement inputEditLeaseName;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement editLease_SubmitButton;
	@FindBy(xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Lease']//button[text()='Yes']")
	private WebElement deleteConfirmYesButton;

	public void clickAddButtonAndVerifyCreateLeaseHalfCardIsPresentOrNot() throws ApplicationException {
		try {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		}
		log.info("Wait until loading lease list.");
		commLocators.clickAddButton();
	}

	public void enterLeaseName(String lease_name) throws ApplicationException {
		// TODO Auto-generated method stub
		LeaseName = lease_name;

		if (inputLeaseName.isDisplayed()) {
			utility.ClearTextBox(inputLeaseName);
			log.info("Clear the Lease text box.");

			utility.SendValues(inputLeaseName, LeaseName);
			log.info("Enter Lease name.");
		} else {
			throw new ApplicationException("Exception Occured", "Add lease input field is not present.");
		}
	}

	public void selectCompany() throws ApplicationException {
		commLocators.selectValue("Select Company", "Company", cancelBtn);
		log.info("Company selected.");
	}

	public void selectPumper() throws ApplicationException {
		commLocators.selectValue("Select Pumper", "Pumper", cancelBtn);
		log.info("Pumper selected.");
	}

	public void clickAddButtonForCreateNewLease() throws ApplicationException {

		if (submitBtn.isDisplayed()) {
			utility.Submit(submitBtn);
			log.info("Click on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Submit button is not present.");
		}

	}

	public void verifyTheLeaseIsCreatedOrnot() throws ApplicationException, InterruptedException {

		utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
		enterLeaseNameinSearchBox(LeaseName);
		verifySearchedLeaseIsExistsOrNot(LeaseName);
		// utility.clearSearchBox();
	}

	public void enterLeaseNameinSearchBox(String lease_Name) throws ApplicationException, InterruptedException {
		commLocators.Search(lease_Name);
	}

	public void verifySearchedLeaseIsExistsOrNot(String lease_name) throws ApplicationException {
		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement filedsRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if (lease_name.equals(filedsRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void verifyTheLeaseListAfterClearSearchBox() throws ApplicationException, InterruptedException {
		if (commLocators.inputSearch.isDisplayed()) {
			utility.clearSearchBox(commLocators.inputSearch);
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);

			if (searchRecords.size() >= 1) {
				log.info("Retrive all records");
			} else {
				throw new ApplicationException("Exception Occured", "Records are not present.");
			}
		} else {
			throw new ApplicationException("Exception Occured", "Search box is not present.");
		}
	}

	public void searchLease(int flag) throws ApplicationException, InterruptedException {

		if (flag == 1) {
			enterLeaseNameinSearchBox(LeaseName);
			verifySearchedLeaseIsExistsOrNot(LeaseName);
		}
		if (flag == 2) {
			enterLeaseNameinSearchBox(updatedLease);
			verifySearchedLeaseIsExistsOrNot(updatedLease);
		}
	}

	public void clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot() throws ApplicationException {
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			if (commLocators.editAction.isDisplayed()) {
				commLocators.clickOnEditActionButton();
				if (popupEditLease.isDisplayed()) {
					log.info("Edit Lease half screen present.");
				} else {
					throw new ApplicationException("Exception Occured", "Edit Lease half screen is not present.");
				}
			} else {
				throw new ApplicationException("Exception Occured", "Edit button is not present.");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void enterNewLeaseName() throws ApplicationException {
		updatedLease = LeaseName.concat("Edited");

		if (inputEditLeaseName.isDisplayed()) {
			utility.Submit(inputEditLeaseName);
			log.info("Click on Lease name.");
			utility.WaitFor2Second();

			utility.ClearTextBox(inputEditLeaseName);
			log.info("Clear text box.");
			utility.WaitFor2Second();

			utility.SendValues(inputEditLeaseName, updatedLease);
			log.info("Input upgreaded Lease name.");

		} else {
			throw new ApplicationException("Exception Occured", "Lease inputbox is not present.");
		}
	}

	public void clickOnSubmitAndVerifyThatTheLeaseIsUpdateOrNot() throws ApplicationException {
		if (editLease_SubmitButton.isDisplayed()) {
			utility.Submit(editLease_SubmitButton);
			log.info("Click on Submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);

			if (dashboard.toastMessage.isDisplayed()) {
				Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully updated the Lease.");
				log.info("'" + updatedLease + "'" + " Lease update successfully.");
			} else {
				throw new ApplicationException("Exception Occured", "Lease is not Updated.");
			}
		} else {
			throw new ApplicationException("Exception Occured", "Update button in not present.");
		}
	}

	public void clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot()
			throws ApplicationException {
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

	public void verifyTheLeaseIsDeletedorNotAfterConfirm() throws ApplicationException, InterruptedException {

		utility.Submit(deleteConfirmYesButton);
		log.info("Cliked on Yes button to confirm delete record.");
		utility.waitUntilToastPresent(dashboard.toastMessage);

		if (dashboard.toastMessage.isDisplayed()) {
			String expactedToastMessage = dashboard.toastMessage.getText();
			utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
			enterLeaseNameinSearchBox(updatedLease);

			if (searchRecords.size() == 0) {
				Assert.assertEquals(expactedToastMessage, "We have successfully deleted the Lease.");
				log.info("'" + updatedLease + "'" + " Lease has been deleted successfully.");
			} else {

			}

		} else {
			throw new ApplicationException("Exception Occured", "Still record is not present.");
		}

	}

}

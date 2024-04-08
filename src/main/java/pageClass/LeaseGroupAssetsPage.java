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

public class LeaseGroupAssetsPage {
	public static Logger log = Logger.getLogger(LeaseGroupAssetsPage.class);

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;
	public String LeaseGroupName;
	public String updatedLeaseGroup;

	public LeaseGroupAssetsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
		commLocators = new commonLocatorsRepo(driver);
	}

	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//div[text()='Select company']")
	WebElement companyDropDown;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter Lease Group Name']")
	private WebElement inputLease_GroupName;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='button']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;
	@FindBy(xpath = "//h5[text()='Edit Lease Group']")
	private WebElement popupEditLeaseGroup;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//input[@placeholder='Enter Lease Group Name']")
	private WebElement inputEditLeaseGroupName;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement editLeaseGroup_SubmitButton;
	@FindBy(xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Lease Group']//button[text()='Yes']")
	private WebElement deleteConfirmYesButton;

	public void clickAddButtonAndVerifyCreateLeaseGroupHalfCardIsPresentOrNot() throws ApplicationException {
		try {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		}
		log.info("Wait until loading lease list.");
		commLocators.clickAddButton();
	}

	public void enterLeaseGroupName(String lease_group_name) throws ApplicationException {
		// TODO Auto-generated method stub
		LeaseGroupName = lease_group_name;

		if (inputLease_GroupName.isDisplayed()) {
			utility.ClearTextBox(inputLease_GroupName);
			log.info("Clear the Lease Group text box.");

			utility.SendValues(inputLease_GroupName, LeaseGroupName);
			log.info("Enter Lease Group name.");
		} else {
			throw new ApplicationException("Exception Occured", "Add lease group input field is not present.");
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

	public void clickAddButtonForCreateNewLeaseGroup() throws ApplicationException {

		if (submitBtn.isDisplayed()) {
			utility.Submit(submitBtn);
			log.info("Click on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Submit button is not present.");
		}

	}

	public void verifyTheLeaseGroupIsCreatedOrnot() throws ApplicationException, InterruptedException {

		utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
		enterLeaseGroupNameinSearchBox(LeaseGroupName);
		verifySearchedLeaseGroupIsExistsOrNot(LeaseGroupName);
		// utility.clearSearchBox();
	}

	public void enterLeaseGroupNameinSearchBox(String lease_group_name)
			throws ApplicationException, InterruptedException {
		commLocators.Search(lease_group_name);
	}

	public void verifySearchedLeaseGroupIsExistsOrNot(String lease_group_name) throws ApplicationException {
		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement filedsRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if (lease_group_name.equals(filedsRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void verifyTheLeaseGroupListAfterClearSearchBox() throws ApplicationException, InterruptedException {
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

	public void searchLeaseGroup(int flag) throws ApplicationException, InterruptedException {

		if (flag == 1) {
			enterLeaseGroupNameinSearchBox(LeaseGroupName);
			verifySearchedLeaseGroupIsExistsOrNot(LeaseGroupName);
		}
		if (flag == 2) {
			enterLeaseGroupNameinSearchBox(updatedLeaseGroup);
			verifySearchedLeaseGroupIsExistsOrNot(updatedLeaseGroup);
		}
	}

	public void clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot() throws ApplicationException {
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			if (commLocators.editAction.isDisplayed()) {
				commLocators.clickOnEditActionButton();
				if (popupEditLeaseGroup.isDisplayed()) {
					log.info("Edit Lease Group half screen present.");
				} else {
					throw new ApplicationException("Exception Occured", "Edit Lease Group half screen is not present.");
				}
			} else {
				throw new ApplicationException("Exception Occured", "Edit button is not present.");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void enterNewLeaseGroupName() throws ApplicationException {
		updatedLeaseGroup = LeaseGroupName.concat("Edited");

		if (inputEditLeaseGroupName.isDisplayed()) {
			utility.Submit(inputEditLeaseGroupName);
			log.info("Click on Lease Group name.");
			utility.WaitFor2Second();

			utility.ClearTextBox(inputEditLeaseGroupName);
			log.info("Clear text box.");
			utility.WaitFor2Second();

			utility.SendValues(inputEditLeaseGroupName, updatedLeaseGroup);
			log.info("Input upgreaded Lease Group name.");

		} else {
			throw new ApplicationException("Exception Occured", "Lease Group inputbox is not present.");
		}
	}

	public void clickOnSubmitAndVerifyThatTheLeaseGroupIsUpdateOrNot() throws ApplicationException {
		if (editLeaseGroup_SubmitButton.isDisplayed()) {
			utility.Submit(editLeaseGroup_SubmitButton);
			log.info("Click on Submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);

			if (dashboard.toastMessage.isDisplayed()) {
				Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully updated the Lease Group.");
				log.info("'" + updatedLeaseGroup + "'" + " Lease Group update successfully.");
			} else {
				throw new ApplicationException("Exception Occured", "Lease Group is not updated.");
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

	public void verifyTheLeaseGroupIsDeletedorNotAfterConfirm() throws ApplicationException, InterruptedException {

		utility.Submit(deleteConfirmYesButton);
		log.info("Cliked on Yes button to confirm delete record.");
		utility.waitUntilToastPresent(dashboard.toastMessage);

		if (dashboard.toastMessage.isDisplayed()) {
			String expactedToastMessage = dashboard.toastMessage.getText();
			utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
			enterLeaseGroupNameinSearchBox(updatedLeaseGroup);

			if (commLocators.noRow.isDisplayed()) {
				Assert.assertEquals(expactedToastMessage, "We have successfully deleted the Lease Group.");
				log.info("'" + updatedLeaseGroup + "'" + " Lease has been deleted successfully.");
			} else {

			}

		} else {
			throw new ApplicationException("Exception Occured", "Still record is not present.");
		}

	}

}

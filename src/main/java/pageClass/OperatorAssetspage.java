package pageClass;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.commonUtil.ApplicationException;
import com.commonUtil.Utility;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class OperatorAssetspage {
	public static Logger log = Logger.getLogger(LeaseAssetsPage.class);

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;
	public String OperatorName;
	public String updatedOperatorName;

	public OperatorAssetspage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
		commLocators = new commonLocatorsRepo(driver);
	}

	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter Operator Name']")
	private WebElement inputOperatorName;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Operator Phone Number']")
	private WebElement inputOperatorPhone;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Operator Email Address']")
	private WebElement inputOperatorEmail;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Operator Address']")
	private WebElement inputOperatorAddress;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='button']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//h5[text()='Edit Operator']")
	private WebElement popupEditOperator;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//input[@placeholder='Enter lease name']")
	private WebElement inputEditLeaseName;
	@FindBy(xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Operator']//button[text()='Yes']")
	private WebElement deleteConfirmYesButton;

	public void clickAddButtonAndVerifyCreateOperatorHalfCardIsPresentOrNot() throws ApplicationException {
		try {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		}
		log.info("Wait until loading operator list.");
		commLocators.clickAddButton();
	}

	public void enterOperatorName(String operatore_name) throws ApplicationException {
		OperatorName = operatore_name;

		if (inputOperatorName.isDisplayed()) {
			utility.ClearTextBox(inputOperatorName);
			log.info("Clear the operator text box.");

			utility.SendValues(inputOperatorName, OperatorName);
			log.info("Enter operator name.");
		} else {
			throw new ApplicationException("Exception Occured", "Add Operator input field is not present.");
		}
	}

	public void enterOperatorPhone() throws ApplicationException {

		if (inputOperatorPhone.isDisplayed()) {
			utility.ClearTextBox(inputOperatorPhone);
			log.info("Clear the operator phone text box.");

			utility.SendValues(inputOperatorPhone, "9876543210");
			log.info("Enter operator Value.");
		} else {
			throw new ApplicationException("Exception Occured", "Operator phone input field is not present.");
		}
	}

	public void enterOperatorEmail() throws ApplicationException {

		String rendomEmail = "test" + commonUtil.getRandomString(4) + "@openxcell.com";

		if (inputOperatorEmail.isDisplayed()) {
			utility.ClearTextBox(inputOperatorEmail);
			log.info("Clear the operator email text box.");

			utility.SendValues(inputOperatorEmail, rendomEmail);
			log.info("Enter email Value.");
		} else {
			throw new ApplicationException("Exception Occured", "Operator email input field is not present.");
		}
	}

	public void enterOperatorAddress() throws ApplicationException {

		if (inputOperatorAddress.isDisplayed()) {
			utility.ClearTextBox(inputOperatorAddress);
			log.info("Clear the operator address text box.");

			utility.SendValues(inputOperatorAddress, "7415 Belcher Oil City Road Belcher, LA 71004");
			log.info("Enter operator address.");
		} else {
			throw new ApplicationException("Exception Occured", "Operator address input field is not present.");
		}
	}

	public void selectStatus() throws ApplicationException {
		commLocators.selectValue("Select Your Status", "Status", cancelBtn);
		log.info("Status selected.");
	}

	public void clickAddButtonForCreateNewLease() throws ApplicationException {

		if (submitBtn.isDisplayed()) {
			utility.Submit(submitBtn);
			log.info("Click on Submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);

			String toast = dashboard.toastMessage.getText();
			TestBase.softAssert.assertEquals(toast, "We have successfully created the Operator.");

		} else {
			throw new ApplicationException("Exception Occured", "Submit button is not present.");
		}

	}

	public void verifyTheLeaseIsCreatedOrnot() throws ApplicationException, InterruptedException {

		enterOperatorNameinSearchBox(OperatorName);
		verifySearchedOperatorIsExistsOrNot(OperatorName);
		// utility.clearSearchBox();
	}

	public void enterOperatorNameinSearchBox(String operator_Name) throws ApplicationException, InterruptedException {
		commLocators.Search(operator_Name);
	}

	public void verifySearchedOperatorIsExistsOrNot(String operator_Name) throws ApplicationException {
		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement filedsRow = searchedRecord.findElement(By.xpath("//div[@class='MuiBox-root css-y60j5q']"));
				if (operator_Name.equals(filedsRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void verifyTheOperatorListAfterClearSearchBox() throws ApplicationException, InterruptedException {
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

	public void searchOperator(int flag) throws ApplicationException, InterruptedException {

		if (flag == 1) {
			enterOperatorNameinSearchBox(OperatorName);
			verifySearchedOperatorIsExistsOrNot(OperatorName);
		}
		if (flag == 2) {
			enterOperatorNameinSearchBox(updatedOperatorName);
			verifySearchedOperatorIsExistsOrNot(updatedOperatorName);
		}
	}

	public void clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot() throws ApplicationException {
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			if (commLocators.editAction.isDisplayed()) {
				commLocators.clickOnEditActionButton();
				if (popupEditOperator.isDisplayed()) {
					log.info("Edit operator half screen present.");
				} else {
					throw new ApplicationException("Exception Occured", "Edit operator half screen is not present.");
				}
			} else {
				throw new ApplicationException("Exception Occured", "Edit button is not present.");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void enterNewOperatorName() throws ApplicationException {
		updatedOperatorName = OperatorName.concat("Edited");

		if (inputOperatorName.isDisplayed()) {
			utility.Submit(inputOperatorName);
			log.info("Click on operator name.");
			utility.WaitFor2Second();

			utility.ClearTextBox(inputOperatorName);
			log.info("Clear text box.");
			utility.WaitFor2Second();

			utility.SendValues(inputOperatorName, updatedOperatorName);
			log.info("Input upgreaded operator name.");

		} else {
			throw new ApplicationException("Exception Occured", "Operator inputbox is not present.");
		}
	}

	public void clickOnSubmitAndVerifyThatTheOperatorIsUpdateOrNot() throws ApplicationException {
		if (submitBtn.isDisplayed()) {
			utility.Submit(submitBtn);
			log.info("Click on Submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);

			if (dashboard.toastMessage.isDisplayed()) {
				TestBase.softAssert.assertEquals(dashboard.toastMessage.getText(), "Operator updated successfully.");
				log.info("'" + updatedOperatorName + "'" + " Operator update successfully.");
			} else {
				throw new ApplicationException("Exception Occured", "Operator is not Updated.");
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

	public void verifyTheOperatorIsDeletedorNotAfterConfirm() throws ApplicationException, InterruptedException {

		utility.Submit(deleteConfirmYesButton);
		log.info("Cliked on Yes button to confirm delete record.");
		utility.waitUntilToastPresent(dashboard.toastMessage);

		if (dashboard.toastMessage.isDisplayed()) {
			String expactedToastMessage = dashboard.toastMessage.getText();
			utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
			enterOperatorNameinSearchBox(updatedOperatorName);

			if (commLocators.noRow.isDisplayed()) {
				TestBase.softAssert.assertEquals(expactedToastMessage, "Operator deleted successfully.");
				log.info("'" + updatedOperatorName + "'" + " Operator has been deleted successfully.");
			} else {

			}

		} else {
			throw new ApplicationException("Exception Occured", "Still record is not present.");
		}

	}

}

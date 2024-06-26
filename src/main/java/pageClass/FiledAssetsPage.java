package pageClass;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.commonUtil.ApplicationException;
import com.commonUtil.Utility;

import DriverManager.WebDriverManager;
import testBase.TestBase;

public class FiledAssetsPage {

	public String field_name;
	public String sonris_id;
	public String updatedField;
	public String updatedSonris_id;
	Utility utility;
	commonLocatorsRepo commLocators;
	DashBoardPage dashboard;
	public static Logger log = Logger.getLogger(FiledAssetsPage.class);

	public FiledAssetsPage() {
		PageFactory.initElements(WebDriverManager.getDriver(), this);
		utility = Utility.utility();
		commLocators = commonLocatorsRepo.LocatorsRepo();
		dashboard = DashBoardPage.dashBoardPage();
	}

	@FindBy(xpath = "//h5[text()='Add Field']/parent::div/following-sibling::div//button[@type='submit']")
	private WebElement addField_SubmitButton;
	@FindBy(xpath = "//h5[text()='Edit Field']")
	private WebElement popupEditField;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//input[@placeholder='Field Name']")
	private WebElement inputEditFieldName;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement editField_SubmitButton;
	@FindBy(xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Field']//button[text()='Yes']")
	private WebElement deleteConfirmYesButton;
	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;

	public void verifyTheScreen() throws ApplicationException, InterruptedException {
		// DashBoardPage dashboard = DashBoardPage.dashBoardPage();
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		String headerText = commLocators.screenHeader.getText();

		if (!headerText.equals("Field")) {
			dashboard.clickOnMenuItem("Field");
		}
	}

	public void clickAddButtonAndVerifyAddFieldHalfCardIsPresentOrNot() throws ApplicationException {
		// Utility utility = Utility.utility();
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		try {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		}

		log.info("Wait until loading field list.");
		commLocators.clickAddButton();
	}

	public void EnterFiledDetails(String field_name, String sonris_id) {
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();

		this.field_name = field_name;
		this.sonris_id = sonris_id;
		commLocators.input("Field Name", this.field_name);
		log.info("Enter Filed name.");
		commLocators.input("Enter ISONRIS Field ID", this.sonris_id); // First arg is Placeholder value,Second
																		// is
		// input-data
		log.info("Enter SONRIS Field ID");
	}

	public void clickAddButtonForCreateNewCompany() throws ApplicationException {
		// Utility utility = Utility.utility();

		if (addField_SubmitButton.isDisplayed()) {
			utility.Submit(addField_SubmitButton);
			log.info("Click on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Submit button field is not present.");
		}

	}

	public void verifyTheFleldIsCreatedOrnot() throws ApplicationException, InterruptedException {
		// Utility utility = Utility.utility();
		// DashBoardPage dashboard = DashBoardPage.dashBoardPage();
		utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
		enterFieldNameinSearchBox(field_name);
		verifySearchedFieldIsExistsOrNot(field_name);
		// utility.clearSearchBox();
	}

	public void enterFieldNameinSearchBox(String field_name) throws ApplicationException, InterruptedException {
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		commLocators.Search(field_name);
	}

	public void verifySearchedFieldIsExistsOrNot(String field_name) throws ApplicationException {
		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement filedsRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if (field_name.equals(filedsRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void verifyTheFieldListAfterClearSearchBox() throws ApplicationException, InterruptedException {
		// Utility utility = Utility.utility();
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		if (commLocators.inputSearch.isDisplayed()) {
			utility.clearSearchBox(commLocators.inputSearch);
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

	public void searchRecentAddedFieldForEdit() throws ApplicationException, InterruptedException {
		enterFieldNameinSearchBox(field_name);
		verifySearchedFieldIsExistsOrNot(field_name);
	}

	public void searchRecentAddedFieldForDelete() throws ApplicationException, InterruptedException {
		enterFieldNameinSearchBox(updatedField);
		verifySearchedFieldIsExistsOrNot(updatedField);
	}

	public void clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot() throws ApplicationException {
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			if (commLocators.editAction.isDisplayed()) {
				commLocators.clickOnEditActionButton();
				if (popupEditField.isDisplayed()) {
					log.info("Edit Field half screen present.");
				} else {
					throw new ApplicationException("Exception Occured", "Edit Field half screen is not present.");
				}
			} else {
				throw new ApplicationException("Exception Occured", "Edit button is not present.");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void enterNewFieldName() throws ApplicationException {
		// Utility utility = Utility.utility();
		updatedField = field_name.concat("Edited");
		updatedSonris_id = sonris_id.replace(sonris_id, "1234");

		if (inputEditFieldName.isDisplayed()) {
			utility.Submit(inputEditFieldName);
			log.info("Click on Field name.");
			utility.WaitFor2Second();

			utility.ClearTextBox(inputEditFieldName);
			log.info("Clear text box.");
			utility.WaitFor2Second();

			utility.SendValues(inputEditFieldName, updatedField);
			log.info("Input upgreaded Field name.");

		} else {
			throw new ApplicationException("Exception Occured", "Field inputbox is not present.");
		}
	}

	public void clickOnsubmitAndVerifyThatTheFieldIsUpdateOrNot() throws ApplicationException {
		// Utility utility = Utility.utility();
		// DashBoardPage dashboard = DashBoardPage.dashBoardPage();
		if (editField_SubmitButton.isDisplayed()) {
			utility.Submit(editField_SubmitButton);
			log.info("Click on Submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);

			if (dashboard.toastMessage.isDisplayed()) {
				TestBase.softAssert.assertEquals(dashboard.toastMessage.getText(),
						"We have successfully updated the Field.");
				log.info("'" + updatedField + "'" + " Field update successfully.");
			} else {
				throw new ApplicationException("Exception Occured", "Field is not Updated.");
			}
		} else {
			throw new ApplicationException("Exception Occured", "Update button in not present.");
		}
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

	public void verifyTheFieldIsDeletedorNotAfterConfirm() throws ApplicationException, InterruptedException {
		// Utility utility = Utility.utility();
		// DashBoardPage dashboard = DashBoardPage.dashBoardPage();
		// commonLocatorsRepo commLocators = new commonLocatorsRepo();
		utility.Submit(deleteConfirmYesButton);
		log.info("Cliked on Yes button to confirm delete record.");
		utility.waitUntilToastPresent(dashboard.toastMessage);

		if (dashboard.toastMessage.isDisplayed()) {
			String expactedToastMessage = dashboard.toastMessage.getText();
			utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
			enterFieldNameinSearchBox(updatedField);

			if (commLocators.noRow.isDisplayed()) {
				TestBase.softAssert.assertEquals(expactedToastMessage, "We have successfully deleted the Field.");
				log.info("'" + updatedField + "'" + " Field has been deleted successfully.");
			} else {

			}

		} else {
			throw new ApplicationException("Exception Occured", "Still record is not present.");
		}

	}

	public static FiledAssetsPage fieldAssetsPage() {
		return new FiledAssetsPage();
	}
}

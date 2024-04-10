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

import testBase.TestBase;

public class TankBatteryAssetsPage {
	public static Logger log = Logger.getLogger(TankBatteryAssetsPage.class);

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;
	public String TankBatteryname;
	public String updatedTankBattery;

	private static final String Lat = "32.61161640069667";
	private static final String Long = "-103.57233640836601";

	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter Tank Battery']")
	private WebElement inputTankBatteryName;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='button']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter Latitude']")
	private WebElement inputLat;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter Longitude']")
	private WebElement inputLong;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//h5[text()='Edit Tank Battery']")
	private WebElement popupTankBattery;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//input[@placeholder='Enter Tank Battery']")
	private WebElement inputEditTankBatteryName;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement editSublease_SubmitButton;
	@FindBy(xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Tank Battery']//button[text()='Yes']")
	private WebElement deleteConfirmYesButton;

	public TankBatteryAssetsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
		commLocators = new commonLocatorsRepo(driver);
	}

	public void clickAddButtonAndVerifyCreateTankBatteryHalfCardIsPresentOrNot() throws ApplicationException {
		try {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		}
		log.info("Wait until loading Sublease list.");
		commLocators.clickAddButton();
	}

	public void enterTankBatteryName(String TankBattery_name) throws ApplicationException {
		// TODO Auto-generated method stub
		TankBatteryname = TankBattery_name;

		if (inputTankBatteryName.isDisplayed()) {
			utility.ClearTextBox(inputTankBatteryName);
			log.info("Clear the Tank Battery text box.");

			utility.SendValues(inputTankBatteryName, TankBatteryname);
			log.info("Enter Tank Battery name.");
		} else {
			throw new ApplicationException("Exception Occured", "Add Tank Battery input field is not present.");
		}
	}

	public void selectLease() throws ApplicationException {
		commLocators.selectValue("Select Lease", "Lease", cancelBtn);
		log.info("Lease selected.");
	}

	/*
	 * public void selectField() throws ApplicationException {
	 * commLocators.selectValue("Select field", "Field", cancelBtn);
	 * log.info("Field selected."); }
	 */

	public void enterLatValue() throws ApplicationException {

		if (inputLat.isDisplayed()) {
			utility.ClearTextBox(inputLat);
			log.info("Clear the Latitude text box.");

			utility.SendValues(inputLat, Lat);
			log.info("Enter Latitude Value.");
		} else {
			throw new ApplicationException("Exception Occured", "Latitude input field is not present.");
		}
	}

	public void enterLongValue() throws ApplicationException {

		if (inputLong.isDisplayed()) {
			utility.ClearTextBox(inputLong);
			log.info("Clear the Longitude text box.");

			utility.SendValues(inputLong, Long);
			log.info("Enter Longitude value.");
		} else {
			throw new ApplicationException("Exception Occured", "Longitude input field is not present.");
		}
	}

	public void clickAddButtonForCreateNewTankBattery() throws ApplicationException {

		if (submitBtn.isDisplayed()) {
			utility.Submit(submitBtn);
			log.info("Click on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Submit button is not present.");
		}

	}

	public void verifyTheTankBatteryIsCreatedOrnot() throws ApplicationException, InterruptedException {

		utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
		enterTankBatteryNameinSearchBox(TankBatteryname);
		verifySearchedTankbatteryIsExistsOrNot(TankBatteryname);
		// utility.clearSearchBox();
	}

	public void enterTankBatteryNameinSearchBox(String tankBattery_Name)
			throws ApplicationException, InterruptedException {
		commLocators.Search(tankBattery_Name);
	}

	public void verifySearchedTankbatteryIsExistsOrNot(String tankBattery_Name) throws ApplicationException {
		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement filedsRow = searchedRecord.findElement(By.xpath("//div[@class='MuiBox-root css-y60j5q']"));
				if (tankBattery_Name.equals(filedsRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void verifyTheTankBatteryListAfterClearSearchBox() throws ApplicationException, InterruptedException {
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
			enterTankBatteryNameinSearchBox(TankBatteryname);
			verifySearchedTankbatteryIsExistsOrNot(TankBatteryname);
		}
		if (flag == 2) {
			enterTankBatteryNameinSearchBox(updatedTankBattery);
			verifySearchedTankbatteryIsExistsOrNot(updatedTankBattery);
		}
	}

	public void clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot() throws ApplicationException {
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			if (commLocators.editAction.isDisplayed()) {
				commLocators.clickOnEditActionButton();
				if (popupTankBattery.isDisplayed()) {
					log.info("Edit Tank Battery half screen present.");
				} else {
					throw new ApplicationException("Exception Occured",
							"Edit Tank Battery half screen is not present.");
				}
			} else {
				throw new ApplicationException("Exception Occured", "Edit button is not present.");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void enterTankBatteryName() throws ApplicationException {
		updatedTankBattery = TankBatteryname.concat("Edited");

		if (inputEditTankBatteryName.isDisplayed()) {
			utility.Submit(inputEditTankBatteryName);
			log.info("Click on Tank Battery name.");
			utility.WaitFor2Second();

			utility.ClearTextBox(inputEditTankBatteryName);
			log.info("Clear text box.");
			utility.WaitFor2Second();

			utility.SendValues(inputEditTankBatteryName, updatedTankBattery);
			log.info("Input upgreaded Tank Battery name.");

		} else {
			throw new ApplicationException("Exception Occured", "Tank Battery inputbox is not present.");
		}
	}

	public void clickOnSubmitAndVerifyThatTheTankBAtteryIsUpdateOrNot() throws ApplicationException {
		if (editSublease_SubmitButton.isDisplayed()) {
			utility.Submit(editSublease_SubmitButton);
			log.info("Click on Submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);

			if (dashboard.toastMessage.isDisplayed()) {
				TestBase.softAssert.assertEquals(dashboard.toastMessage.getText(),
						"We have successfully updated the Tank Battery.");
				log.info("'" + updatedTankBattery + "'" + " Tank Battery update successfully.");
			} else {
				throw new ApplicationException("Exception Occured", "Tank Battery is not Updated.");
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

	public void verifyTheTankBatteryIsDeletedorNotAfterConfirm() throws ApplicationException, InterruptedException {

		utility.Submit(deleteConfirmYesButton);
		log.info("Cliked on Yes button to confirm delete record.");
		utility.waitUntilToastPresent(dashboard.toastMessage);

		if (dashboard.toastMessage.isDisplayed()) {
			String expactedToastMessage = dashboard.toastMessage.getText();
			utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
			enterTankBatteryNameinSearchBox(updatedTankBattery);

			if (commLocators.noRow.isDisplayed()) {
				TestBase.softAssert.assertEquals(expactedToastMessage,
						"We have successfully deleted the Tank Battery.");
				log.info("'" + updatedTankBattery + "'" + " Tank Battery has been deleted successfully.");
			} else {

			}

		} else {
			throw new ApplicationException("Exception Occured", "Still record is not present.");
		}

	}
}

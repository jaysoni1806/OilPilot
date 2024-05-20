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

public class WellAssetsPage {

	public static Logger log = Logger.getLogger(WellAssetsPage.class);

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;

	public String Wellname;
	public String updatedWell;

	private static final String Lat = "32.61161640069667";
	private static final String Long = "-103.57233640836601";

	public WellAssetsPage() {
		driver = TestBase.driver;
		PageFactory.initElements(this.driver, this);
		utility = Utility.utility();
		dashboard = DashBoardPage.dashBoardPage();
		commLocators = commonLocatorsRepo.LocatorsRepo();
	}

	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;
	@FindBy(xpath = "//li[@class='MuiBreadcrumbs-li']/p[text()='Add Well']")
	public WebElement addWellScreen;
	@FindBy(xpath = "//div[contains(@class,'css-1b8fkut')]//a[@href='/assets/well/add/']")
	public WebElement addWellkbtn;
	@FindBy(xpath = "//input[@placeholder='Well Number']")
	public WebElement inputWellNumber;
	@FindBy(xpath = "//input[@placeholder='Well Name']")
	public WebElement inputWellName;
	@FindBy(xpath = "//input[@placeholder='Latitude' and not(contains(@name,'sonrisLatitude'))]")
	public WebElement inputLatitude;
	@FindBy(xpath = "//input[@placeholder='Longitude' and not(contains(@name,'sonrisLongitude'))]")
	public WebElement inputLongitude;
	@FindBy(xpath = "//input[@placeholder='API Number']")
	public WebElement inputAPINumber;
	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement btnSubmit;
	@FindBy(xpath = "//li[@class='MuiBreadcrumbs-li']/p[text()='Edit Well']")
	public WebElement editWellScreen;
	@FindBy(xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Well']//button[text()='Yes']")
	private WebElement deleteConfirmYesButton;

	public void clickAddButtonAndVerifyCreateWellSceenIsPresentOrNot() throws ApplicationException {
		try {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 30);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 30);
		}
		log.info("Wait until loading well list.");
		commLocators.clickAddButton(addWellkbtn, addWellScreen);
	}

	public void enterRequiredDetailsToAddWell(String well_name) throws ApplicationException, InterruptedException {
		Wellname = well_name;
		selectLeaseDropdown("QA");
		log.info("Lease selected.");
		selectTankBattery();
		log.info("Tank Battery selected.");
		inputWellNumber();
		log.info("Enter well number.");
		inputWellName(Wellname);
		log.info("Enter well name.");
		selectWellCategory();
		log.info("Well category selected.");
		inputLatitude();
		log.info("Enter latitude.");
		inputLongtitude();
		log.info("Enter longtitude.");
		inputAPINumber();
		log.info("Enter API number.");

	}

	public void selectLeaseDropdown(String well_param) throws ApplicationException {
		commLocators.SelectDropdownValueBasedOnSearchRecords(well_param, "Select Lease", "Lease");
		utility.WaitFor2Second();
	}

	private void selectTankBattery() throws ApplicationException {
		commLocators.selectDropdownValue("Associated tank battery", "Associated Tank Battery");
		utility.WaitFor2Second();
	}

	public void inputWellNumber() throws InterruptedException, ApplicationException {
		utility.clearTextBox(inputWellNumber);
		utility.SendValues(inputWellNumber, "1");
	}

	public void inputWellName(String well_name) throws InterruptedException, ApplicationException {
		utility.clearTextBox(inputWellName);
		utility.SendValues(inputWellName, well_name);
	}

	private void selectWellCategory() throws ApplicationException {
		commLocators.selectDropdownValue("Well Category", "Well Category");
	}

	public void inputLatitude() {
		utility.SendValues(inputLatitude, Lat);
	}

	public void inputLongtitude() {
		utility.SendValues(inputLongitude, Long);
	}

	public void inputAPINumber() {
		utility.SendValues(inputAPINumber, "1");
	}

	public void clickOnSubmitAndVerifyWellIsCreateOrNot() throws ApplicationException, InterruptedException {
		if (btnSubmit.isDisplayed()) {
			utility.Submit(btnSubmit);
			log.info("Clicked submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage, 50);

			if (dashboard.toastMessage.isDisplayed()) {
				TestBase.softAssert.assertEquals(dashboard.toastMessage.getText(),
						"We have successfully created the Well.");
				if (!searchRecords.isEmpty()) {
					enterWellNameinSearchBox(Wellname);
					verifySearchedWellIsExistsOrNot(Wellname);
				} else {
					utility.waitUntillListPresent(searchRecords);
					enterWellNameinSearchBox(Wellname);
					verifySearchedWellIsExistsOrNot(Wellname);
				}
				log.info("'" + Wellname + "'" + " well created successfully.");
			} else {
				throw new ApplicationException("Exception Occured", "Well is not created");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void clickEdit_actionUnderThePerent_actionandVerifyEditWellScreenIsPresentOrNot()
			throws ApplicationException {
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			log.info("Clicked on action button.");
			if (commLocators.editAction.isDisplayed()) {
				commLocators.clickOnEditActionButton();
				log.info("Clicked on edit action menu.");
				if (editWellScreen.isDisplayed()) {
					log.info("Edit well screen present.");
				} else {
					throw new ApplicationException("Exception Occured", "Edit well screen is not present.");
				}
			} else {
				throw new ApplicationException("Exception Occured", "Edit button is not present.");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void enterWellNameinSearchBox(String well_name) throws ApplicationException, InterruptedException {
		commLocators.Search(well_name);
	}

	public void verifySearchedWellIsExistsOrNot(String well_name) throws ApplicationException {
		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement filedsRow = searchedRecord
						.findElement(By.xpath("//div[@data-field='vName']//div[@class='MuiBox-root css-y60j5q']"));
				if (well_name.equals(filedsRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void enterNewWellName() throws InterruptedException, ApplicationException {
		utility.WaitFor2Second();
		updatedWell = Wellname.concat("Edited");
		inputWellName(updatedWell);
	}

	public void clickOnSubmitAndVerifyWellIsUpdatedOrNot() throws ApplicationException, InterruptedException {
		utility.WaitFor2Second();
		if (btnSubmit.isDisplayed()) {
			utility.Submit(btnSubmit);
			log.info("Clicked submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);

			if (dashboard.toastMessage.isDisplayed()) {
				TestBase.softAssert.assertEquals(dashboard.toastMessage.getText(),
						"We have successfully updated the Well.");
				enterWellNameinSearchBox(updatedWell);
				verifySearchedWellIsExistsOrNot(updatedWell);
				log.info("'" + Wellname + "'" + " tank is updated successfully with '" + updatedWell + "'.");
			} else {
				throw new ApplicationException("Exception Occured", "Tank is not updated");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}

	}

	public void clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot()
			throws ApplicationException {
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			log.info("Clicked on action button.");
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

	public void verifyTheWellIsDeletedorNotAfterConfirm() throws ApplicationException, InterruptedException {
		utility.Submit(deleteConfirmYesButton);
		log.info("Cliked on Yes button to confirm delete record.");
		utility.waitUntilToastPresent(dashboard.toastMessage);

		if (dashboard.toastMessage.isDisplayed()) {
			String expactedToastMessage = dashboard.toastMessage.getText();
			utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
			enterWellNameinSearchBox(updatedWell);

			if (commLocators.noRow.isDisplayed()) {
				TestBase.softAssert.assertEquals(expactedToastMessage, "We have successfully deleted the Well.");
				log.info("'" + updatedWell + "'" + " well has been deleted successfully.");
			} else {

			}

		} else {
			throw new ApplicationException("Exception Occured", "Still record is not present.");
		}

	}

	public void verifyTheWellListAfterClearSearchBox() throws ApplicationException, InterruptedException {
		if (commLocators.inputSearch.isDisplayed()) {
			utility.clearSearchBox(commLocators.inputSearch);
			log.info("Clear searchbox.");
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

	public static WellAssetsPage wellAssetsPage() {
		return new WellAssetsPage();
	}

}

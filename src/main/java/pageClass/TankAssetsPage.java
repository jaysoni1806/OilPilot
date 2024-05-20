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

public class TankAssetsPage {
	public static Logger log = Logger.getLogger(TankAssetsPage.class);

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;

	public String Tankname;
	public String updatedTank;

	private static final String Lat = "32.61161640069667";
	private static final String Long = "-103.57233640836601";

	public TankAssetsPage() {
		driver = TestBase.driver;
		PageFactory.initElements(this.driver, this);
		utility = Utility.utility();
		dashboard = DashBoardPage.dashBoardPage();
		commLocators = commonLocatorsRepo.LocatorsRepo();
	}

	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;
	@FindBy(xpath = "//li[@class='MuiBreadcrumbs-li']/p[text()='Add Tank']")
	public WebElement addTankScreen;
	@FindBy(xpath = "//div[contains(@class,'css-1b8fkut')]//a[@href='/assets/tanks/add/']")
	public WebElement addTankbtn;
	@FindBy(xpath = "//input[@placeholder='Tank Name']")
	public WebElement inputTankName;
	@FindBy(xpath = "//input[@placeholder='Max Capacity (bbl)']")
	public WebElement inputMaxCap;
	@FindBy(xpath = "//input[@placeholder='Enter Conversion Rate']")
	public WebElement inputConvRate;
	@FindBy(xpath = "//input[@placeholder='Enter Sequence Order']")
	public WebElement inputSeqOrd;
	@FindBy(xpath = "//input[@placeholder='Height (in)']")
	public WebElement inputHeight;
	@FindBy(xpath = "//input[@placeholder='Bottom (in)']")
	public WebElement inputBottom;
	@FindBy(xpath = "//input[@placeholder='Latitude']")
	public WebElement inputLatitude;
	@FindBy(xpath = "//input[@placeholder='Longitude']")
	public WebElement inputLongitude;
	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement btnSubmit;
	@FindBy(xpath = "//li[@class='MuiBreadcrumbs-li']/p[text()='Edit Tank']")
	public WebElement editTankScreen;
	@FindBy(xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Tank']//button[text()='Yes']")
	private WebElement deleteConfirmYesButton;

	public void clickAddButtonAndVerifyCreateTankSceenIsPresentOrNot() throws ApplicationException {
		try {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 30);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 30);
		}
		log.info("Wait until loading tank list.");
		commLocators.clickAddButton(addTankbtn, addTankScreen);
	}

	public void enterRequiredDetailsToAddTank(String tank_name) throws ApplicationException, InterruptedException {
		Tankname = tank_name;
		inputTankName(Tankname);
		selectStatusDropdown();
		selectAssociatedTankBatteryDropdown();
		inputMaxCap();
		inputConvRate();
		inputSequenceOrder();
		inputHeight();
		inputBottom();
		inputLatitude();
		inputLongtitude();
	}

	public void inputTankName(String tankname) throws InterruptedException, ApplicationException {
		utility.clearTextBox(inputTankName);
		utility.SendValues(inputTankName, tankname);
		log.info("Enter tank name.");
	}

	public void selectStatusDropdown() throws ApplicationException {
		commLocators.selectDropdownValue("Select Status", "Status");
		log.info("Select tank status");
	}

	public void selectAssociatedTankBatteryDropdown() throws ApplicationException {
		commLocators.selectDropdownValue("Associated Tank Battery", "Associated Tank Battery");
		log.info("Select associated tank battery.");
	}

	public void inputMaxCap() {
		utility.SendValues(inputMaxCap, "120");
		log.info("Enter max capacity.");
	}

	public void inputConvRate() {
		utility.SendValues(inputConvRate, "2");
		log.info("Enter conversion rate.");
	}

	public void inputSequenceOrder() {
		utility.SendValues(inputSeqOrd, "2");
		log.info("Enter sequence order.");
	}

	public void inputHeight() {
		utility.SendValues(inputHeight, "100");
		log.info("Enter height.");
	}

	public void inputBottom() {
		utility.SendValues(inputBottom, "20");
		log.info("Enter bottom.");
	}

	public void inputLatitude() {
		utility.SendValues(inputLatitude, Lat);
		log.info("Enter latitude.");
	}

	public void inputLongtitude() {
		utility.SendValues(inputLongitude, Long);
		log.info("Enter longtitude.");
	}

	public void clickOnSubmitAndVerifyTankIsCreateOrNot() throws ApplicationException, InterruptedException {
		if (btnSubmit.isDisplayed()) {
			utility.Submit(btnSubmit);
			log.info("Clicked submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage, 50);

			if (dashboard.toastMessage.isDisplayed()) {
				TestBase.softAssert.assertEquals(dashboard.toastMessage.getText(),
						"We have successfully created the Tank.");
				enterTankNameinSearchBox(Tankname);
				verifySearchedTankIsExistsOrNot(Tankname);
				log.info("'" + Tankname + "'" + " tank created successfully.");
			} else {
				throw new ApplicationException("Exception Occured", "Tank is not created");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void clickEdit_actionUnderThePerent_actionandVerifyEditTankScreenIsPresentOrNot()
			throws ApplicationException {
		if (commLocators.actionBtton.isDisplayed()) {
			commLocators.clickOnActionButton();
			log.info("Clicked on action button.");
			if (commLocators.editAction.isDisplayed()) {
				commLocators.clickOnEditActionButton();
				log.info("Clicked on edit action menu.");
				if (editTankScreen.isDisplayed()) {
					log.info("Edit tank screen present.");
				} else {
					throw new ApplicationException("Exception Occured", "Edit tank screen is not present.");
				}
			} else {
				throw new ApplicationException("Exception Occured", "Edit button is not present.");
			}

		} else {
			throw new ApplicationException("Exception Occured", "Action button is not present.");
		}
	}

	public void enterTankNameinSearchBox(String tank_name) throws ApplicationException, InterruptedException {
		commLocators.Search(tank_name);
	}

	public void verifySearchedTankIsExistsOrNot(String tank_name) throws ApplicationException {
		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement filedsRow = searchedRecord
						.findElement(By.xpath("//div[@data-field='vName']//div[@class='MuiBox-root css-y60j5q']"));
				if (tank_name.equals(filedsRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void enterNewTankName() throws InterruptedException, ApplicationException {
		updatedTank = Tankname.concat("Edited");
		inputTankName(updatedTank);
	}

	public void clickOnSubmitAndVerifyTankIsUpdatedOrNot() throws ApplicationException, InterruptedException {
		utility.WaitFor2Second();
		if (btnSubmit.isDisplayed()) {
			utility.Submit(btnSubmit);
			log.info("Clicked submit button.");
			utility.waitUntilToastPresent(dashboard.toastMessage);

			if (dashboard.toastMessage.isDisplayed()) {
				TestBase.softAssert.assertEquals(dashboard.toastMessage.getText(),
						"We have successfully updated the Tank.");
				enterTankNameinSearchBox(updatedTank);
				verifySearchedTankIsExistsOrNot(updatedTank);
				log.info("'" + Tankname + "'" + " tank is updated successfully with '" + updatedTank + "'.");
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

	public void verifyTheTankIsDeletedorNotAfterConfirm() throws ApplicationException, InterruptedException {
		utility.Submit(deleteConfirmYesButton);
		log.info("Cliked on Yes button to confirm delete record.");
		utility.waitUntilToastPresent(dashboard.toastMessage);

		if (dashboard.toastMessage.isDisplayed()) {
			String expactedToastMessage = dashboard.toastMessage.getText();
			utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
			enterTankNameinSearchBox(updatedTank);

			if (commLocators.noRow.isDisplayed()) {
				TestBase.softAssert.assertEquals(expactedToastMessage, "We have successfully deleted the Tank.");
				log.info("'" + updatedTank + "'" + " tank has been deleted successfully.");
			} else {

			}

		} else {
			throw new ApplicationException("Exception Occured", "Still record is not present.");
		}

	}

	public void verifyTheTankListAfterClearSearchBox() throws ApplicationException, InterruptedException {
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

	public static TankAssetsPage tankAssetsPage() {
		return new TankAssetsPage();
	}

}

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

public class SubLeaseAssetsPage {

	public static Logger log = Logger.getLogger(SubLeaseAssetsPage.class);

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;
	public String SubLeaseName;
	public String updatedSubLease;

	private static final String NRI = "25";
	private static final String TAXRATE = "0.20";

	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter sublease name']")
	private WebElement inputSebLeaseName;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='button']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter NRI']")
	private WebElement inputNRI;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter tax rate']")
	private WebElement inputTaxRate;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement submitBtn;

	public SubLeaseAssetsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
		commLocators = new commonLocatorsRepo(driver);
	}

	public void clickAddButtonAndVerifyCreateSubLeaseHalfCardIsPresentOrNot() throws ApplicationException {
		try {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		}
		log.info("Wait until loading Sublease list.");
		commLocators.clickAddButton();
	}

	public void enterLeaseName(String subLease_name) throws ApplicationException {
		// TODO Auto-generated method stub
		SubLeaseName = subLease_name;

		if (inputSebLeaseName.isDisplayed()) {
			utility.ClearTextBox(inputSebLeaseName);
			log.info("Clear the Sublease text box.");

			utility.SendValues(inputSebLeaseName, SubLeaseName);
			log.info("Enter Sublease name.");
		} else {
			throw new ApplicationException("Exception Occured", "Add Sublease input field is not present.");
		}
	}

	public void selectLease() throws ApplicationException {
		commLocators.selectValue("Select lease", "Lease", cancelBtn);
		log.info("Lease selected.");
	}

	public void selectField() throws ApplicationException {
		commLocators.selectValue("Select field", "Field", cancelBtn);
		log.info("Field selected.");
	}

	public void enterNRIValue() throws ApplicationException {

		if (inputNRI.isDisplayed()) {
			utility.ClearTextBox(inputNRI);
			log.info("Clear the NRI text box.");

			utility.SendValues(inputNRI, NRI);
			log.info("Enter NRI Value.");
		} else {
			throw new ApplicationException("Exception Occured", "NRI input field is not present.");
		}
	}

	public void enterTaxRateValue() throws ApplicationException {

		if (inputTaxRate.isDisplayed()) {
			utility.ClearTextBox(inputTaxRate);
			log.info("Clear the Tax Rate text box.");

			utility.SendValues(inputTaxRate, TAXRATE);
			log.info("Enter Tax Rate.");
		} else {
			throw new ApplicationException("Exception Occured", "Tax Rate input field is not present.");
		}
	}

	public void clickAddButtonForCreateNewSublease() throws ApplicationException {

		if (submitBtn.isDisplayed()) {
			utility.Submit(submitBtn);
			log.info("Click on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Submit button is not present.");
		}

	}

	public void verifyTheSubleaseIsCreatedOrnot() throws ApplicationException, InterruptedException {

		utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
		enterSubleaseNameinSearchBox(SubLeaseName);
		verifySearchedSubleaseIsExistsOrNot(SubLeaseName);
		// utility.clearSearchBox();
	}

	public void enterSubleaseNameinSearchBox(String Sublease_Name) throws ApplicationException, InterruptedException {
		commLocators.Search(Sublease_Name);
	}

	public void verifySearchedSubleaseIsExistsOrNot(String Sublease_Name) throws ApplicationException {
		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement filedsRow = searchedRecord.findElement(By.xpath("//div[@class='MuiBox-root css-y60j5q']"));
				if (Sublease_Name.equals(filedsRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

	public void verifyTheSubleaseListAfterClearSearchBox() throws ApplicationException, InterruptedException {
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

}

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

public class FiledAssetsOperations {
	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;
	public String field_name;
	public String sonris_id;
	public static Logger log = Logger.getLogger(FiledAssetsOperations.class);

	public FiledAssetsOperations(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
		commLocators = new commonLocatorsRepo(driver);
	}

	@FindBy(xpath = "//h5[text()='Add Field']/parent::div/following-sibling::div//button[@type='submit']")
	private WebElement addField_SubmitButton;

	public void verifyTheScreen() throws ApplicationException, InterruptedException {
		String headerText = commLocators.screenHeader.getText();

		if (!headerText.equals("Field")) {
			dashboard.clickOnMenuItem("Field");
		}
	}

	public void clickAddButtonAndVerifyAddFieldHalfCardIsPresentOrNot() throws ApplicationException {

		try {
			List<WebElement> searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			List<WebElement> searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		}

		log.info("Wait until loading field list.");
		commLocators.clickAddButton();
	}

	public void EnterFiledDetails(String field_name, String sonris_id) {
		this.field_name = field_name;
		this.sonris_id = sonris_id;
		commLocators.input("Field Name", this.field_name);
		log.info("Enter Filed name.");
		commLocators.input("Enter ISONRIS Field ID", this.sonris_id); // First arg is Placeholder value,Second is
																		// input-data
		log.info("Enter SONRIS Field ID");
	}

	public void clickAddButtonForCreateNewCompany() throws ApplicationException {

		if (addField_SubmitButton.isDisplayed()) {
			utility.Submit(addField_SubmitButton);
			log.info("Click on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Submit button field is not present.");
		}

	}

	public void verifyTheFleldIsCreatedOrnot() throws ApplicationException {

		utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
		enterCompanyNameinSearchBox(field_name);
		verifySearchedCompanyIsExistsOrNot(field_name);
		// utility.clearSearchBox();
	}

	public void enterCompanyNameinSearchBox(String field_name) throws ApplicationException {
		commLocators.Search(field_name);
	}

	public void verifySearchedCompanyIsExistsOrNot(String field_name) throws ApplicationException {
		List<WebElement> searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));

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

}

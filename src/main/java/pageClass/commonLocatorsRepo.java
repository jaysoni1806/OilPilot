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

import DriverManager.WebDriverManager;

public class commonLocatorsRepo {
	WebDriver driver;
	Utility utility;
	public static Logger log = Logger.getLogger(commonLocatorsRepo.class);

	public commonLocatorsRepo() {
		driver = WebDriverManager.getDriver();
		PageFactory.initElements(driver, this);
		utility = Utility.utility();
	}

	@FindBy(xpath = "//div[contains(@class,'MuiGrid-root ')]//h5[contains(@class,'MuiTypography-h5')]")
	public WebElement screenHeader;
	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement inputSearch;
	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]//div[@data-field='actions']/button")
	public WebElement actionBtton;
	@FindBy(xpath = "//ul[contains(@class,'MuiMenu-list')]/parent::div[not(contains(@style,'visibility'))]//li[text()='Edit']")
	public WebElement editAction;
	@FindBy(xpath = "//ul[contains(@class,'MuiMenu-list')]/parent::div[not(contains(@style,'visibility'))]//li[text()='Delete']")
	public WebElement deleteAction;
	@FindBy(xpath = "//h5[text()='Company']")
	public WebElement headerCompany;
	@FindBy(xpath = "//span[@role='progressbar']")
	public WebElement progrssbasCompany;
	@FindBy(xpath = "//div[contains(@class,'css-1b8fkut')]//button")
	public WebElement addBtton;
	@FindBy(xpath = "//h5[contains(text(),'Add')]")
	public WebElement popupAddAssets;
	@FindBy(xpath = "//span[@role='progressbar']")
	public static WebElement loaderSpinnitng;
	@FindBy(xpath = "//div[text()='No rows']")
	public WebElement noRow;

	public void input(String inputFiledplaceHolder, String inputFiledValue) {
		WebElement inputCompanyName = driver
				.findElement(By.xpath("//input[@placeholder='" + inputFiledplaceHolder + "']"));
		utility.SendValues(inputCompanyName, inputFiledValue);
	}

	public void clickOnActionButton() {
		utility.WaitUntilElementIsNotClickable(actionBtton, 5);
		log.info("Waiting until action button is clickable.");

		utility.Submit(actionBtton);
		log.info("Click action button.");
	}

	public void clickOnEditActionButton() {
		utility.WaitUntilElementIsNotClickable(editAction, 2);
		log.info("Waiting until edit button is clickable.");

		utility.Submit(editAction);
		log.info("Action menu presented, Cliked Edit action.");
	}

	public void clickOnDeleteActionButton() {
		utility.WaitUntilElementIsNotClickable(deleteAction, 2);
		log.info("Waiting until delete button is clickable.");

		utility.Submit(deleteAction);
		log.info("Action menu presented, Cliked delete action.");
	}

	public void Search(String searchContent) throws ApplicationException, InterruptedException {
		if (inputSearch.isDisplayed()) {
			utility.clearSearchBox(inputSearch);
			log.info("Clear searchbox.");

			utility.SendValues(inputSearch, searchContent);
			log.info("Entered value in searchbox.");
		} else {
			throw new ApplicationException("Exception Occured", "Search box is not present.");
		}
		utility.WaitFor2Second();
	}

	public void clickAddButton() throws ApplicationException {
		if (addBtton.isDisplayed()) {
			utility.Submit(addBtton);
			log.info("Click on Add button.");

			utility.WaitForASecond(popupAddAssets, 10);
			log.info("Half card is present.");
		} else {
			throw new ApplicationException("Exception Occured", "Add button is not Present or Not Clickable.");
		}
	}

	public void clickAddButton(WebElement webElement) throws ApplicationException {
		if (webElement.isDisplayed()) {
			utility.Submit(webElement);
			log.info("Click on Add button.");

			// utility.WaitForASecond(tank_page.addTankScreen, 10);
			log.info("Add screen presented.");
		} else {
			throw new ApplicationException("Exception Occured", "Add button is not Present or Not Clickable.");
		}
	}

	public void clickAddButton(WebElement addTankbtn, WebElement addTankScreen) throws ApplicationException {
		if (addTankbtn.isDisplayed()) {
			utility.Submit(addTankbtn);
			log.info("Click on Add button.");

			utility.WaitForASecond(addTankScreen, 10);
			log.info("Add screen presented.");
		} else {
			throw new ApplicationException("Exception Occured", "Add button is not Present or Not Clickable.");
		}
	}

	public static void waitTillLoaderDisappear() throws InterruptedException, ApplicationException {
		int count = 0;
		int maxWaitTime = 30;
		if (loaderSpinnitng.isDisplayed()) {
			do {
				Thread.sleep(1000 * 2);
				++count;
				System.out.println("Loader icon in position !!!");
			} while (loaderSpinnitng != null && count < maxWaitTime);
			if (count >= maxWaitTime && loaderSpinnitng != null) {
				throw new ApplicationException("waitTillLoaderDisappear",
						"Page is stuck on Loading for more than " + (count * 2) + "s !!!");
			}
		}

		System.out.println("Loader icon finished");
	}

	public void selectValue(String dropdowniTem, String dropdownName, WebElement cancelButton)
			throws ApplicationException {
		WebElement dropdownElement = driver.findElement(By.xpath(
				"//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='"
						+ dropdowniTem + "']"));
		if (dropdownElement.isDisplayed()) {
			utility.WaitUntilElementIsNotClickable(dropdownElement, 5);
			utility.Submit(dropdownElement);
			utility.WaitFor2Second();

			List<WebElement> dropdownList = driver
					.findElements(By.xpath("//ul[contains(@class,'MuiAutocomplete-listbox')]/li"));

			if (dropdownList.size() >= 2) {
				utility.Submit(dropdownList.get(0));
			} else {
				utility.Submit(cancelButton);
				throw new ApplicationException("Exception Occured", dropdownName + " list is null.");

			}
		} else {
			throw new ApplicationException("Exception Occured", dropdownName + " dropdown is not present.");
		}

	}

	public void selectDropdownValue(String dropdowniTem, String dropdownName) throws ApplicationException {
		WebElement dropdownElement = driver.findElement(By.xpath("//input[@placeholder='" + dropdowniTem + "']"));

		if (dropdownElement.isDisplayed()) {
			utility.WaitUntilElementIsNotClickable(dropdownElement, 5);
			utility.Submit(dropdownElement);
			utility.WaitFor2Second();

			List<WebElement> dropdownList = driver
					.findElements(By.xpath("//ul[contains(@class,'MuiAutocomplete-listbox')]/li"));
			if (dropdownList.size() >= 1) {
				utility.Submit(dropdownList.get(0));
			} else {
				throw new ApplicationException("Exception Occured", dropdownName + " list is null.");

			}
		} else {
			throw new ApplicationException("Exception Occured", dropdownName + " dropdown is not present.");
		}

	}

	public void SelectDropdownValueBasedOnSearchRecords(String well_param, String dropdowniTem, String dropdownName)
			throws ApplicationException {
		WebElement dropdownElement = driver.findElement(By.xpath("//input[@placeholder='" + dropdowniTem + "']"));

		if (dropdownElement.isDisplayed()) {
			utility.WaitUntilElementIsNotClickable(dropdownElement, 5);
			utility.Submit(dropdownElement);
			utility.SendValues(dropdownElement, well_param);
			utility.WaitFor2Second();

			List<WebElement> dropdownList = driver
					.findElements(By.xpath("//ul[contains(@class,'MuiAutocomplete-listbox')]/li"));
			if (dropdownList.size() >= 2) {
				utility.Submit(dropdownList.get(2));
			} else {
				throw new ApplicationException("Exception Occured", dropdownName + " list is null.");

			}
		} else {
			throw new ApplicationException("Exception Occured", dropdownName + " dropdown is not present.");
		}

	}

	public static commonLocatorsRepo LocatorsRepo()

	{
		return new commonLocatorsRepo();
	}

}

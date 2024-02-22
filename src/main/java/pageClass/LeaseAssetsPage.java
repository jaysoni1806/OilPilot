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

public class LeaseAssetsPage {
	public static Logger log = Logger.getLogger(LeaseAssetsPage.class);

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;
	private List<WebElement> searchRecords;

	public LeaseAssetsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
		commLocators = new commonLocatorsRepo(driver);
	}

	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//div[text()='Select company']")
	WebElement companyDropDown;

	public void clickAddButtonAndVerifyCreateLeaseHalfCardIsPresentOrNot() throws ApplicationException {
		searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
		utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		log.info("Wait until loading lease list.");
		commLocators.clickAddButton();
	}

	public void selectCompany() {
		utility.WaitUntilElementIsNotClickable(companyDropDown, 5);
		utility.Submit(companyDropDown);
		List<WebElement> companyList = driver.findElements(By.xpath(
				"//div[contains(@class,'MuiMenu-paper')][not(contains(@style,'visibility: hidden'))]//ul[contains(@class,'MuiMenu-list')]/li"));

		utility.WaitFor2Second();
		companyList.get(2).click();
	}

}

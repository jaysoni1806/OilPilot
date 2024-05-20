package pageClass;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.commonUtil.ApplicationException;
import com.commonUtil.Utility;

import DriverManager.WebDriverManager;

//import utils.UtilityMethods;

public class DashBoardPage {

	WebDriver driver;
	// commonLocatorsRepo commLocators;
	public static Logger log = Logger.getLogger(DashBoardPage.class);

	@FindBy(xpath = "//div[contains(@class,'MuiAvatar-circular')]")
	public WebElement dashboard;
	@FindBy(xpath = "//p[text()='Assets']")
	public WebElement asset;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]")
	public WebElement assets_CollapseEntered;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-hidden')]")
	public WebElement assets_CollapseHidden;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/company/']")
	public WebElement companyMenu;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/field/']")
	public WebElement fieldMenu;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/lease-group/']")
	public WebElement lease_groupMenu;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/lease/']")
	public WebElement leaseMenu;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/tank-battery/']")
	public WebElement tankbatteryMenu;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/tanks/']")
	public WebElement tankMenu;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/well/']")
	public WebElement wellMenu;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/operator/']")
	public WebElement operatorMenu;
	@FindBy(xpath = "//div[contains(@class,'react-hot-toast')]//div[@role='status']")
	public WebElement toastMessage;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-hidden')]")
	public List<WebElement> assets_CollapseHidden1;

	public DashBoardPage() {
		// driver = WebDriverManager.getDriver();
		PageFactory.initElements(WebDriverManager.getDriver(), this);
	}

	public void clickOnAssets() throws ApplicationException {
		Utility utility = Utility.utility();
		if (assets_CollapseHidden1.size() > 0) {

			if (asset.isDisplayed()) {
				utility.WaitUntilElementIsNotClickable(asset, 5);
				log.info("Waiting until Assets manu is clickable.");

				utility.Submit(asset);
				log.info("Click on Assets menu.");

			} else {
				throw new ApplicationException("Exception Occured", "Assets is not display.");
			}

		} else {
			log.info("Assets is already open.");
		}
	}

	public void clickOnMenuItem(String menuNAme) throws ApplicationException, InterruptedException {
		Utility utility = Utility.utility();
		try {
			if (assets_CollapseHidden1.size() == 0) {
				if (menuNAme.equals("Company")) {
					selectMenu(companyMenu, menuNAme);
				} else if (menuNAme.equals("Field")) {
					selectMenu(fieldMenu, menuNAme);
				} else if (menuNAme.equals("Lease Group")) {
					selectMenu(lease_groupMenu, menuNAme);
				} else if (menuNAme.equals("Lease")) {
					selectMenu(leaseMenu, menuNAme);
				} else if (menuNAme.equals("Tank Battery")) {
					selectMenu(tankbatteryMenu, menuNAme);
				} else if (menuNAme.equals("Tank")) {
					selectMenu(tankMenu, menuNAme);
				} else if (menuNAme.equals("Operator")) {
					selectMenu(operatorMenu, menuNAme);
				} else if (menuNAme.equals("Well")) {
					selectMenu(wellMenu, menuNAme);
				}
			} else {
				utility.Submit(asset);

			}
		} catch (Exception e) {
			log.info("Something not working for Assets-> " + e);
		}

	}

	public void selectMenu(WebElement MenuElement, String menuNAme) throws ApplicationException, InterruptedException {
		commonLocatorsRepo commLocators = new commonLocatorsRepo();
		Utility utility = Utility.utility();
		if (MenuElement.isDisplayed()) {

			utility.Submit(MenuElement);
			log.info("Click on " + menuNAme + " manu item.");

		} else if (assets_CollapseHidden1.size() > 0) {
			log.info("Assets menu has been collepse");
			clickOnAssets();
			log.info("Call click assets function again");
		}

		else {
			throw new ApplicationException("Exception Occured",
					commLocators.screenHeader.getText() + "Assets is not display.");
		}
	}

	public static DashBoardPage dashBoardPage() {
		return new DashBoardPage();
	}

}

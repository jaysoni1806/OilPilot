package pageClass;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.commonUtil.ApplicationException;
import com.commonUtil.Utility;

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

	public TankAssetsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
		commLocators = new commonLocatorsRepo(driver);

	}

	@FindBy(xpath = "//div[contains(@class,'MuiDataGrid-row')]")
	private List<WebElement> searchRecords;
	@FindBy(xpath = "//li[@class='MuiBreadcrumbs-li']/p[text()='Add Tank']")
	public WebElement addTankScreen;
	@FindBy(xpath = "//div[contains(@class,'css-1b8fkut')]//a[@href='/assets/tanks/add/']")
	public WebElement addTankbtn;
	@FindBy(xpath = "//input[@placeholder='Tank Name']")
	public WebElement inputTankName;

	public void clickAddButtonAndVerifyCreateTankSceenIsPresentOrNot() throws ApplicationException {
		try {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		}
		log.info("Wait until loading Tank list.");
		commLocators.clickAddButton(addTankbtn, addTankScreen);
	}

	public void enterRequiredDetailsToAddTank() {
		// TODO Auto-generated method stub

	}

}

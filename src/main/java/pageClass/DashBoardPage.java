package pageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.commonUtil.Utility;

//import utils.UtilityMethods;

public class DashBoardPage {

	WebDriver driver;
	Utility utility;
	@FindBy(xpath = "//div[contains(@class,'MuiAvatar-circular')]")
	public WebElement dashboard;
	@FindBy(xpath = "//p[text()='Assets']")
	public WebElement asset;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]")
	public WebElement assets_CollapseEntered;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-hidden')]")
	public WebElement assets_CollapseHidden;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/company/']")
	public WebElement list_Company;
	@FindBy(xpath = "//div[contains(@class,'react-hot-toast')]//div[@role='status']")
	public WebElement toastMessage;

	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
	}
}

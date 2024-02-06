package pageClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.commonUtil.Utility;

public class CompanyAssetsOperations {

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	String updatedComp; 
	
	public CompanyAssetsOperations(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
	}
	
	@FindBy (xpath = "//h5[text()='Company']") private WebElement headerCompany;
	@FindBy (xpath = "//div[contains(@class,'css-1b8fkut')]//button")private WebElement addBtton;
	@FindBy (xpath = "//h5[text()='Add Company']") private WebElement popupAddCompany;
	@FindBy (xpath = "//input[@placeholder='Company name']")private WebElement inputCompanyName;
	//@FindBy (xpath = "//input[@placeholder='Company name']/parent::div/parent::div/following-sibling::div/button[1]")private WebElement addCompny_SubmitButton;
	@FindBy (xpath = "//h5[text()='Add Company']/parent::div/following-sibling::div//button[@type='submit']")private WebElement addCompny_SubmitButton;
	@FindBy (xpath = "//input[@placeholder='Search']")private WebElement inputSearch;
	@FindBy (xpath = "//div[contains(@class,'MuiDataGrid-row')]//div[@data-field='actions']/button")private WebElement actionBtton;
	@FindBy (xpath = "//ul[contains(@class,'MuiMenu-list')]/parent::div[not(contains(@style,'visibility'))]//li[text()='Edit']")private WebElement editAction;
	@FindBy (xpath = "//ul[contains(@class,'MuiMenu-list')]/parent::div[not(contains(@style,'visibility'))]//li[text()='Delete']")private WebElement deleteAction;
	@FindBy (xpath = "//h5[text()='Edit Company']") private WebElement popupEditCompany;
	@FindBy (xpath = "//input[@placeholder='company name']")private WebElement inputEditCompanyName;
	@FindBy (xpath = "//h5[text()='Edit Company']/parent::div/following-sibling::div//button[@type='submit']")private WebElement editCompny_SubmitButton;
	@FindBy (xpath = "//div[@aria-describedby='alert-dialog-description-Delete confirmation for Company']//button[text()='Yes']")private WebElement deleteConfirmYesButton;
	
	
	
	
	public void createCompany(String CmpName) {	
		System.out.println("-> Test create company.");
		utility.WaitUntilElementIsNotClickable(dashboard.asset, 10);
		utility.Submit(dashboard.asset);
		System.out.println("- Click on Assets menu.");
		utility.WaitUntilElementIsNotClickable(dashboard.list_Company, 10);
		utility.Submit(dashboard.list_Company);
		System.out.println("- Click on Company list item.");
		
		utility.WaitUntilElementIsNotClickable(addBtton, 10);
		utility.Submit(addBtton);
		System.out.println("- Click on Company Add button.");
		
		utility.WaitForASecond(popupAddCompany, 10);
		System.out.println("- Add Company half screen present.");
		utility.ClearTextBox(inputCompanyName);
		System.out.println("- Clear text box.");
		utility.SendValues(inputCompanyName,CmpName);
		System.out.println("- Input Company name.");
		utility.WaitUntilElementIsNotClickable(addCompny_SubmitButton, 10);
		utility.Submit(addCompny_SubmitButton);
		System.out.println("- Click on Submit button.");
		
		utility.waitUntilToastPresent(dashboard.toastMessage);
		Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully created the Company.");
		System.out.println("- "+"'"+CmpName+"'"+" Company successfully created.");
		utility.WaitFor2Second();
		
	}

	public void searchCompany(String CmpName) {
		System.out.println("\n-> Test Search with recently added company. ");
		utility.ClearTextBox(inputSearch);
		System.out.println("- Clear searchbox.");
		utility.SendValues(inputSearch,CmpName);
		System.out.println("- Entered company name in searchbox.");
		utility.WaitFor2Second();
		
		List<WebElement> searchRecords =  driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
		if(searchRecords.size() > 0)
		{
			for(WebElement searchedRecord : searchRecords ) {
				WebElement companyRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if(CmpName.equals(companyRow.getText())) {
					System.out.println("- Record searched successfully.");
				}
			}
		}
		else {
			System.out.println("- No Record presented yet.");
		}
		clearSearchBox();
	}

	public void editCompany(String CmpName) {
		System.out.println("\n-> Test Edit for recently added company.");
		search(CmpName);
		System.out.println("- Received recently added company for Edit.");
		
		utility.WaitUntilElementIsNotClickable(actionBtton, 5);
		utility.Submit(actionBtton);
		System.out.println("- Cliked action button.");
		
		utility.WaitUntilElementIsNotClickable(editAction, 2);
		utility.Submit(editAction);
		System.out.println("- Action menu presented, Cliked Edit action.");
		
		utility.WaitForASecond(popupEditCompany, 10);
		System.out.println("- Edit Company half screen present.");
		
		updatedComp = CmpName.concat("Edited");
		
		utility.Submit(inputEditCompanyName);
		System.out.println("- Click on Company name.");
		utility.ClearTextBox(inputEditCompanyName);
		System.out.println("- Clear text box.");
		utility.SendValues(inputEditCompanyName,updatedComp);
		System.out.println("- Input upgreaded Company name.");
		utility.WaitUntilElementIsNotClickable(editCompny_SubmitButton, 10);
		utility.Submit(editCompny_SubmitButton);
		System.out.println("- Click on Submit button.");
		
		utility.waitUntilToastPresent(dashboard.toastMessage);
		Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully updated the Company.");
		System.out.println("- "+"'"+updatedComp+"'"+" Company update successfully created.");
		
		clearSearchBox();
		utility.WaitFor2Second();	
	}
	
	public void deleteCompany() {
		System.out.println("\n-> Test Delete for recently updated company.");
		search(updatedComp);
		System.out.println("- Received recently updated company for Delete.");
		
		utility.WaitUntilElementIsNotClickable(actionBtton, 5);
		utility.Submit(actionBtton);
		System.out.println("- Cliked action button.");
		
		utility.WaitUntilElementIsNotClickable(deleteAction, 2);
		utility.Submit(deleteAction);
		System.out.println("- Action menu presented, Cliked Delete action.");
		
		utility.WaitUntilElementIsNotClickable(deleteConfirmYesButton, 2);
		System.out.println("- Waiting for delete confirmation popup..");
		utility.Submit(deleteConfirmYesButton);
		System.out.println("- Cliked on Yes button to confirm delete record.");
		
		utility.waitUntilToastPresent(dashboard.toastMessage);
		Assert.assertEquals(dashboard.toastMessage.getText(), "We have successfully deleted the Company.");
		System.out.println("- "+"'"+updatedComp+"'"+" Company has been deleted successfully.");
		
		clearSearchBox();
		utility.WaitFor2Second();
		
	}
	
	public void clearSearchBox()
	{
		utility.Submit(inputSearch);
		utility.ClearTextBox(inputSearch);
		System.out.println("- Clear searchbox to retrive all records.");
	}
	
	public void search(String CmpName) {
		utility.ClearTextBox(inputSearch);
		
		utility.SendValues(inputSearch,CmpName);
		
		utility.WaitFor2Second();
		
		List<WebElement> searchRecords =  driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
		if(searchRecords.size() > 0)
		{
			for(WebElement searchedRecord : searchRecords ) {
				WebElement companyRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if(CmpName.equals(companyRow.getText())) {
					System.out.println("- Searching recently added company..");
				}
			}
		}
		else {
			System.out.println("- No Record presented yet.");
		}
	}

	

}

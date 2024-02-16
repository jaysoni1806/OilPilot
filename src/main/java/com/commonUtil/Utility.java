package com.commonUtil;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	WebDriver driver;
	public WebDriverWait wait;
	public Wait<WebDriver> fluentWait;

	public Utility(WebDriver driver) {
		this.driver = driver;
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
	}

	public void SendValues(WebElement element, String value) {
		// element.clear();
		element.sendKeys(value);
	}

	public void Submit(WebElement element) {
		element.click();
	}

	public void WaitForASecond(WebElement element, int second) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(second));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void WaitFor2Second() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clearSearchBox(WebElement element) {
		for (int i = element.getAttribute("value").length(); i >= 0; i--) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void ClearTextBox(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value ='';", element);
	}

	public void pageRefresh() {
		driver.navigate().refresh();
	}

	public void waitForSometime(WebElement element) {
		fluentWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilToastPresent(WebElement element) {
		fluentWait.until(ExpectedConditions.visibilityOf(element));
	}

	public void WaitUntilElementIsNotClickable(WebElement element, int second) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(second));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public boolean WaitUntilListOfElementIsVisible(List<WebElement> element, int second) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(second));
		return wait.until(ExpectedConditions.visibilityOfAllElements(element)) != null;
	}

	public boolean WaitUntilListOfElementIsNotVisible(List<WebElement> element, int second) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(second));
		return wait.until(ExpectedConditions.invisibilityOfAllElements(element));
	}

	public boolean WaitUntilElementVisibiltyGone(WebElement element, int second) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(second));
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/*
	 * public void waitToPageLoad() throws InterruptedException {
	 * Thread.sleep(2000); getFluentWait().until(driver -> { return
	 * ((JavascriptExecutor)
	 * driver).executeScript("return document.readyState").equals("complete"); });
	 * 
	 * }
	 */

	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				} catch (Exception e) {
					return Boolean.FALSE;
				}
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(javascriptDone);
	}
}

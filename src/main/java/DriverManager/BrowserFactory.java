package DriverManager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import testBase.TestBase;

public class BrowserFactory {

	WebDriver driver;
	String browserType;
	ChromeOptions options;

	public BrowserFactory(String bType) {
		browserType = bType;
	}

	public WebDriver getDriver() {

		if ("chrome".equalsIgnoreCase(browserType)) {
			options = new ChromeOptions();
			// options.addArguments("--headless=new");
			// driver = new ChromeDriver(options);
			driver = new ChromeDriver();
			driver.get(TestBase.props.getProperty("LOGIN_URL"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			TestBase.log.info("************************************************************************");
			TestBase.log.info("Chrome browser launched.");
		}
		return driver;
	}

}

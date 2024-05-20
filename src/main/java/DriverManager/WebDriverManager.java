package DriverManager;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {
	// create a generic thread-local object
	private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

	public static void setDriver(WebDriver driver) {
		drivers.set(driver);
	}

	public static WebDriver getDriver() {
		return drivers.get();
	}

	public static void removeDriver() {
		// remove thread-local value for the current thread
		drivers.remove();
	}

}

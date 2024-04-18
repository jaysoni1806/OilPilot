package com.commonUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

	public static WebDriver drivers;

	public static WebDriver getDriver() {
		return drivers = new ChromeDriver();
	}

}

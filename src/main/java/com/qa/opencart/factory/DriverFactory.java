package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {

	Properties prop;
	WebDriver driver;

	/*
	 * This is used to initialize driver on the basis of given browser
	 */

	public WebDriver initDriver(Properties prop) {
		// cross browser logic
		
		String browserName=prop.getProperty("browser");
		System.out.println("the browser name is:" + browserName);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;

		// error mesg from AppError class in errors folder
		default:
			System.out.println("plz pass right browser" + browserName);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
//		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.get(prop.getProperty("url"));
		// it gives driver
		return driver;
	}

	
	/***
	 * this method is used to initialize properties from .prop file
	 * return prop
	 * @return
	 */
	public Properties initProp() {
		// no direct hard core value, we have to pass values from config. prop hence the
		// code

	
			prop = new Properties();
			try {
				FileInputStream ip = new FileInputStream(AppConstant.CONFIG_FILE_PATH);
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return prop;

	
	}
}

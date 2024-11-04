package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;


public class DriverFactory {

	Properties prop;
	WebDriver driver;

	/*
	 * This is used to initialize driver on the basis of given browser
	 */

	//for parallel execution
	//initializing driver with threadlocal, it creates 1 local copy of driver for every threads..
	
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		// cross browser logic

		String browserName = prop.getProperty("browser");
		System.out.println("the browser name is:" + browserName);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();
			//setting the threadlocal driver
			tlDriver.set(new ChromeDriver()); 
			break;
		case "firefox":
			driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
			break;
		case "edge":
		//	driver= new EdgeDriver();
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
		//	driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		// error mesg from AppError class in errors folder
		default:
			System.out.println("plz pass right browser" + browserName);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
		}
		
		
		//getting the threadlocaldriver
		
		//driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();
	//	driver.manage().window().maximize();
		getDriver().manage().window().maximize();
//		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		//driver.get(prop.getProperty("url"));
		getDriver().get(prop.getProperty("url"));
		
		// it gives driver
		return getDriver();
	}

	
	//to get the thread local driver
	
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	
	
	
	
	/***
	 * this method is used to initialize properties from .prop file return prop
	 * 
	 * @return
	 */
	public Properties initProp() {
		// no direct hard core value, we have to pass values from config. prop hence the
		// code

		prop = new Properties();
		FileInputStream ip = null;

		// run using mvn cmdline maven clean install -Denv="qa"
		// -D is aused to take variable , env is the variable

		// file paths from different environment through maven command line

		// System class is used to read argument
		// first from - mvn clean install -Denv="qa" it reads pom.xml = has compileer
		// plugin and surefire plugin to run unit tests --- it to check .xml path then
		// it enters
		// testng_Regression.xml
		String envname = System.getProperty("env");
		System.out.print("running in environement===" + envname);

		if (envname == null) {
			try {
				ip = new FileInputStream(AppConstant.CONFIG_FILE_PATH);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				switch (envname.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream(AppConstant.CONFIG_FILE_PATH);
					break;

				case "stage":
					ip = new FileInputStream(AppConstant.STAGE_FILE_PATH);
					break;

				case "uat":
					ip = new FileInputStream(AppConstant.UAT_FILE_PATH);
					break;

				case "dev":
					ip = new FileInputStream(AppConstant.DEV_FILE_PATH);
					break;
				default:
					System.out.println("please pass the right env name" + envname);
					throw new FrameWorkException("WRONG ENV PASSED");

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}
	
	
	public static String getScreenshot(String methodName)
	{
		
		//TakesScereenshotAs ts= (TakeScreenshotAs)driver; //driver will be of type TakeScreenshotAs interface
		//just like JavaScriptExecutor   driver is of type JSExecutor here
		
		//take RHS
		
		File srcFile=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp location
	String path=System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
	File destinationFile=new File(path); //creating destination place
	try {
		FileHandler.copy(srcFile, destinationFile);   // copying file from destination to source
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return path;
	}
}

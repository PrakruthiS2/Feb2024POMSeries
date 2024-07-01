package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencar.util.ElementUtil;
import com.qa.opencar.util.TimeUtil;
import com.qa.opencart.constants.AppConstant;

public class AccountPage {

	// maintain page objects, public constructtor, page methods..

	private WebDriver driver;
	private ElementUtil eleutil;

//every page will have driver and element util
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// page objects
	private By logoutlink = By.xpath("//a[text()='Logout']");
	private By headers = By.xpath("//div[@id='content']/h2");
	private By search = By.name("search");
	private By searchIcon = By.xpath("//span/button[@type='button']");
	
	// accessing page objects through public layers

//	public String getAccountPageTitle() {
//		return driver.getTitle();
//	}
	// using eleutil

	public String getAccountPageTitle() {
		String title = eleutil.waitForTitleToBe(AppConstant.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_SHORT_DURATION);
		System.out.println("account page title is:" + title);
		return title;
	}

//	public String getAccountPageURl() {
//		// TODO Auto-generated method stub
//		String url = driver.getCurrentUrl();
//		System.out.println("login page url is:" + url);
//		return url;
//	}

	// using elementutil


	public String getaccountPagegetUrl() {
		String url = eleutil.waitForTitleContains(AppConstant.ACCOUNT_PAGE_FRACTION_URL,
				TimeUtil.DEFAULT_SHORT_DURATION);
		System.out.println("account page url is:" + url);
		return url;
	}
//	
//	public boolean isLogoutLinkVisible() {
//		return driver.findElement(logoutlink).isDisplayed();
//	}

	// using elementutil
	public boolean isLogoutLinkVisible() {
		return eleutil.isElementDisplayed(logoutlink);
	}

//	public List<String> getAccountPageHeaders() {
//		List<WebElement> headersList = driver.findElements(headers);
//		// creating list for storing header values
//		List<String> headerVal = new ArrayList<String>();
//		for (WebElement e : headersList) {
//			// grtting text of each element
//			String text = e.getText();
//			// adding element in the list of string
//			headerVal.add(text);
//		}
//
//		return headerVal;
//	}
	
	
	//usign elementutil
	
	public List<String> getAccountPageHeaders() {
	
		List<WebElement> headersList= eleutil.waitForPresenceOfElemenetsLocated(headers, TimeUtil.DEFAULT_SHORT_DURATION);
		// creating list for storing header values
		List<String> headerVal = new ArrayList<String>();
		for (WebElement e : headersList) {
			// grtting text of each element
			String text = e.getText();
			// adding element in the list of string
			headerVal.add(text);
		}

		return headerVal;
	}

	public boolean isSearchExist() {
		return driver.findElement(search).isDisplayed();
	}
	
	//using elementutil
	
	// using elementutil
//		public boolean isSearchExist()  {
//			return eleutil.isElementDisplayed(search);
//		}

	

//	public SearchResultsPage doSearch(String searchKey) {
//		System.out.println("searhing : " + searchKey);
//
//		if (isSearchExist()) {
//			driver.findElement(search).clear();
//			driver.findElement(search).sendKeys(searchKey);
//			driver.findElement(searchIcon).click();
//			return new SearchResultsPage(driver);
//		} else {
//			System.out.println("search field is not present on the page");
//			return null;
//		}
//
//	}

		
		//using elementutil
		

		public SearchResultsPage doSearch(String searchKey) {
			System.out.println("searching for products : " + searchKey);
	
			if (isSearchExist()) {
				eleutil.doSendKeys(search, searchKey);
				eleutil.doClick(searchIcon);
				return new SearchResultsPage(driver);
			} else {
				System.out.println("search field is not present on the page");
				return null;
			}
	
		}
		
		
		
}

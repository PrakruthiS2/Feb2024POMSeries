package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencar.util.ElementUtil;
import com.qa.opencar.util.TimeUtil;

public class SearchResultsPage {

	private WebDriver driver;
	//this constructor takes driver from accountpage class 
	
	private By searchresult= By.xpath("//div[@class='product-thumb']");
	
	
	private ElementUtil eleUtil;
	//every class has its own elementUTil
	public SearchResultsPage(WebDriver driver) {
	//
		//and pass it to the this class driver which is private;
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
	}

	
	
	public int getSearchResultsCount()
	{
		List <WebElement> resultsList=eleUtil.waitForPresenceOfElemenetsLocated(searchresult,TimeUtil.DEFAULT_MEDIUM_DURATION);
		System.out.println("product count"+resultsList.size());
		return resultsList.size();
	}
	
	
	
	public ProductInfoSearch selectProduct(String productName)

	
	{
		
		eleUtil.doClick(By.linkText(productName), TimeUtil.DEFAULT_MEDIUM_DURATION);
		return new ProductInfoSearch(driver); //bcoz after clicking it lands to new page- productinfo page hence  creating object of new page
		
	}
	
	
	
	
}

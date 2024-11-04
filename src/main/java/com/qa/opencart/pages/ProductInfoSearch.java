package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencar.util.ElementUtil;
import com.qa.opencar.util.TimeUtil;

public class ProductInfoSearch {

	private ElementUtil eleutil;
	private WebDriver driver;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImagesCount = By.cssSelector("div#content a.thumbnail");
	private By quantity=By.xpath("//input[@id='input-quantity']");
	private By addCart=By.xpath("//*[@id=button-cart]");
	private By productMetaData= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricedata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private Map<String,String> productMap; //initialising map
	
	
	
	
	public ProductInfoSearch(WebDriver driver) {

		this.driver = driver;// // to pass driver to this class
		eleutil = new ElementUtil(driver); // creating object of class elementutil
	}

	public String getProductHeader() {
		String header = eleutil.doGetText(productHeader);
		System.out.println("product header ===" + header);
		return header;
	}

	public int getProductImagesCount() {
		int imagesCount =
				eleutil.waitForVisibilityOfElemenetsLocated(productImagesCount, TimeUtil.DEFAULT_MEDIUM_DURATION).size();
		System.out.println("total images ==" + imagesCount);
		return imagesCount;
	}

	

	
	
	public Map<String, String> getProductInfo()	{
		productMap=new HashMap<String,String>();
		//putting values into map- user defined key, value
		productMap.put("productname",getProductHeader());
		productMap.put("productimages",String.valueOf(getProductImagesCount())); //type of getproductimage type of string
		getProductMetadata();
		getPriceData();
		return productMap;
	}
	
	
	
	private void getProductMetadata()
	{//top casting map with hashmap
		productMap= new HashMap<String,String>(); 
		List<WebElement> metalist=eleutil.getElements(productMetaData);
		for(WebElement e: metalist)
		{
			String metaData=e.getText();
		String meta[]=	metaData.split(":");
		String metaKey=meta[0].trim();
		String metaValue= meta[1].trim();
		productMap.put(metaKey, metaValue);
		}
	}
	
	
	private void getPriceData()
	{
		List<WebElement> priceList=eleutil.getElements(productPricedata);
		String productprice=priceList.get(0).getText();
		String exTaxprice=priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productprice);
		productMap.put("exTaxprice", exTaxprice);
		
		
	}


	
}

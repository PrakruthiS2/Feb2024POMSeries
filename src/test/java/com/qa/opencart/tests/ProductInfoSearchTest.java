
package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTesttestng;
import com.qa.opencart.errors.AppError;

public class ProductInfoSearchTest extends BaseTesttestng {

	
	@BeforeClass
	public void productinfoSetup()
	{
		accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
//	
	
//	@Test
//	public void productHeaderTest()
//	{
//		searchresultspage=	accPage.doSearch("macbook");
//		prodinfosearch=	searchresultspage.selectProduct("MacBook Pro");
//		Assert.assertEquals(prodinfosearch.getProductHeader(),"MacBook Pro",AppError.HEADER_NOT_FOUND);
//	
//	}
	
	
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][]
				{
			//searchkey, selectproduct
		//	{"macbook","Macbook Pro"},
			{"iMac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"canon","Canon EOS 5D"},
			
				};
	}
	
	@Test(dataProvider="getProductData")
	public void getproductHeaderTest(String searchkey,String productName) //holding params
	{
		searchresultspage=	accPage.doSearch(searchkey);
		prodinfosearch=	searchresultspage.selectProduct(productName);
		Assert.assertEquals(prodinfosearch.getProductHeader(),productName,AppError.HEADER_NOT_FOUND);
	
	}
	@DataProvider
	public Object[][] getProductImageData()
	{
		return new Object[][]
				{
		//	{"macbook","Macbook Pro",4},
			{"iMac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},{"canon","Canon EOS 5D",3},
			
				};
	}
	
	
	@Test(dataProvider="getProductImageData")
	public void getProductImageCount(String searchkey,String productName,int imagecount)  //searchkey,productname,imagecount comes from dataprovider
	{
		searchresultspage=	accPage.doSearch(searchkey);
		prodinfosearch=	searchresultspage.selectProduct(productName);
	//	int productimagecount=prodinfosearch.getProductImagesCount();
		Assert.assertEquals(prodinfosearch.getProductImagesCount(), imagecount,AppError.IMAGES_NOT_MATCHED);
	
	}
	
	
	
	@Test(dataProvider="getProductImageSheetData")
	public void getProductImageSCount(String searchkey,String productName,int imagecount)  //searchkey,productname,imagecount comes from dataprovider
	{
		searchresultspage=	accPage.doSearch(searchkey);
		prodinfosearch=	searchresultspage.selectProduct(productName);
	//	int productimagecount=prodinfosearch.getProductImagesCount();
		Assert.assertEquals(prodinfosearch.getProductImagesCount(), imagecount,AppError.IMAGES_NOT_MATCHED);
	
	}

	
	
	
	
	//test with multiple assertions(softassertion)
	@Test
	public void getProductInfoTest()
	{
	searchresultspage=	accPage.doSearch("iMac");
	prodinfosearch=	searchresultspage.selectProduct("iMac");
	Map<String,String> productInfoMap=prodinfosearch.getProductInfo();
	System.out.println(productInfoMap);
		//productInfoMap.get("keyvalue")
//	Assert.assertEquals(productInfoMap.get("Brand"), "Apple");                            //HardAssert
//	Assert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
//	Assert.assertEquals(productInfoMap.get("exTaxprice"),"$100.00");
//	Assert.assertEquals(productInfoMap.get("productPrice"),"$122.00");
//	
	
	
	//HardAssertion v/s SoftAssertion  or Assert v/s Verify
	//HardAssertion use Assert class directly, the moment assertion fails it will not conitnue to check the next
	//suppoae brand is apple11, it wont verify product code others. it will stop verification there
	//Assert methods (static)
	//single test assertion use HArdssertion
	
	
	
	
	//SoftAssertion uses SoftAssert class
	//Soft Assert methods are non static  // have created object of softassert in basetest
	
	softassert.assertEquals(productInfoMap.get("Brand"), "Apple");                            //SoftAssert
	softassert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
	softassert.assertEquals(productInfoMap.get("exTaxprice"),"$100.00");
	softassert.assertEquals(productInfoMap.get("productPrice"),"$122.00");
	
	softassert.assertAll();
	}
}

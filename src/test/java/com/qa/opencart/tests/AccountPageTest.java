package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTesttestng;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.errors.AppError;

public class AccountPageTest extends BaseTesttestng{

	@BeforeClass
	public void AcctSetUp()
	{
		//loginpage coming from BaseTesttesng bcoz we have ref there
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		//the RHS returning acctpage reference.		
	}
	
	@Test
	public void acctPAgeTittleTest()
	{
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstant.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	//@Test
//	public void acctPageURLTest()
//	{
//		Assert.assertTrue(accPage.getaccountPagegetUrl().contains(AppConstant.ACCOUNT_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
//	}
	
	@Test
	public void getAccountPageHeadersTest()
	{
		List<String> accPageheaders=accPage.getAccountPageHeaders();
	Assert.assertEquals(accPageheaders, AppConstant.ACCOUNT_PAGE_HEADER_LIST,AppError.LIST_NOTMATCHED);
	}
	
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return new Object[][] {
		{"macbook",3},
		{"iMac",1},
		{"samsung",2},
		{"airtel",0}
		}
		;
	}
	
	
	@Test(dataProvider="getSearchData")
	public void searchTest(String searchKey,int resultCount)
	{
		searchresultspage=	accPage.doSearch(searchKey);
		Assert.assertEquals(searchresultspage.getSearchResultsCount(),resultCount,AppError.NO_RESULTS_FOUND);	
	}
//	@Test
//	public void searchTest()
//	{
//		searchresultspage=	accPage.doSearch("macbook");
//		Assert.assertEquals(searchresultspage.getSearchResultsCount(),3,AppError.NO_RESULTS_FOUND);	
//	}
	
}

//the flow of the program when we run AccountPageTest is:
// AccountPageTest is extending BaseTest
//Inside basetest @BeforeTest will run first i.e setUp
//then after creating object of login page in basetest
// it comes back to accountpagetest loginpage.doLogin will run
//and then it is stored account page reference as we have creqtedd object of account page in login page as TDD approcah when once fater cliking login button it goes to account page so created object ref og account page
//Page chaingin  model
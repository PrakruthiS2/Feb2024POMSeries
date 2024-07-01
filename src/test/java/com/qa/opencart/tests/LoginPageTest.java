package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTesttestng;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.errors.AppError;


//test class should not have WebDriver API element
public class LoginPageTest extends BaseTesttestng{

	
	// we can add one more parameter in assert showing error
	@Test(priority=1)
	public void getTitleTest()
	{
		//inorder to access login page methods , we have to call object of loginpage 
		//so reference of login page will be created in base class
		String actTitle=loginpage.LoginPagegetTitle();
		Assert.assertEquals(actTitle,AppConstant.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	
//	@Test(priority=2)
//	public void loginPageURLTest() {
//		String actURL = loginpage.LoginPagegetUrl();
//		Assert.assertTrue(actURL.contains(AppConstant.LOGIN_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
//		
//	}
//	
	@Test(priority=3)
	public void forgotpwdlinktest()
	{
		boolean flag=loginpage.checkForgotPWdLinkExist();
		Assert.assertTrue(flag,AppError.ELEMENT_NOT_FOUND);
	}
	
	@Test(priority=4)
	public void doLoginTest()
	{
		//initially
	//String actpagetitle=loginpage.doLogin("prakruthisnadig@gmail.com", "prax@123");
	
	//after config.prop use prop.getproperty(), since prop is not available in this class use proptected prop in basetestngtest
	//	String actpagetitle=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
		//doLogin method is returning accountpage object so store it in account page ref variable
		accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	
		//initially 
	//	Assert.assertEquals(actpagetitle,"My Account",AppError.TITLE_NOT_FOUND);
	//	Assert.assertEquals(actpagetitle, AppConstant.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
//actual, expected, error
	Assert.assertEquals(accPage.getAccountPageTitle(),AppConstant.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
}

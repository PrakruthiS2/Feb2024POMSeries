package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencar.util.ElementUtil;
import com.qa.opencar.util.TimeUtil;
import com.qa.opencart.constants.AppConstant;

//page class shpuld not have assertions
//page class contains page objects and page methods- page objects can be accessed thorough public layers it is encapsulation
public class LoginPage {

	private WebDriver driver;
	//1)page objects :By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By Register=By.linkText("Register");
	
	//to use elementUtil for resuable methods
	private ElementUtil eleUtil;

	//2) public constructor and pass driver
	public LoginPage(WebDriver driver)
	{
		//the driver from driverfactory will be taken as parameter by the constructor
		//and passes it to the Login page private driver
		//somebody creates object of login page same driver will be given to them
		this.driver=driver;
		//create object of ElementUtil to access methods
		eleUtil= new ElementUtil(driver);
	}
	
	
	//3) page actions/methods
	//title
//	public String LoginPagegetTitle()
//	{
//		String title=driver.getTitle();
//		System.out.println("login page title is:" +title);
//		return title;
//	}
	
	
	//using elementUTil
	public String LoginPagegetTitle()
	{
	String title= eleUtil.waitForTitleToBe(AppConstant.LOGIN_PAGE_TITLE,TimeUtil.DEFAULT_SHORT_DURATION);
		System.out.println("login page title is:" +title);
		return title;
	}
	
	
	
//	//get url
//	public String LoginPagegetUrl()
//	{
//		String url=driver.getCurrentUrl();
//		System.out.println("login page url is:" +url);
//		return url;
//	}
//	
	//using elementUTil
	
	public String LoginPagegetUrl()
	{
		String url=eleUtil.waitForTitleContains(AppConstant.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_SHORT_DURATION);
		System.out.println("login page url is:" +url);
		return url;
	}
	
	
	
	
//	public boolean checkForgotPWdLinkExist()
//	{
//		return driver.findElement(forgotPwdLink).isDisplayed();
//		
//	}
	
	//usign eleUtil
	
	public boolean checkForgotPWdLinkExist()
	{
		return eleUtil.doIsDisplayed(forgotPwdLink);
		
	}
	
	public AccountPage doLogin(String username, String pwd) {
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//
//		return new AccountPage(driver);
//		String title = driver.getTitle();
//		System.out.println("Acc page title : " + title);
//		return title;

		
		//using eleUtil
	//sednKEys with wait
		eleUtil.doSendKeys(emailId, username, TimeUtil.DEFAULT_MEDIUM_DURATION);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountPage(driver);
	}
	
	
	
	
	
	
	//register page navigation starts from login page, after clicking on register button it goes to new register page hence creating object of register page
	
	
	public RegisterPage navigateToRegister()
	{
		eleUtil.doClick(Register, TimeUtil.DEFAULT_SHORT_DURATION);
		return new RegisterPage(driver);
	}
	
}

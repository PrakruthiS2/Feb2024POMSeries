package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencar.util.ElementUtil;
import com.qa.opencar.util.TimeUtil;
import com.qa.opencart.constants.AppConstant;

public class RegisterPage {

	// every page has one copy of driver, eleutil along with constructor
	private ElementUtil eleUtil;
	private WebDriver driver;

	public RegisterPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;// // to pass driver to this class
		eleUtil = new ElementUtil(driver); // creating object of class elementutil
	}
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");
	//this is to logout and register 2nd user
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public boolean userRegister(String firstName, String lastName, String email, String telephone, String password,
			String subscribe)
	{
		//this is used to differentiate locator and paramater name bcoz firstname locator and firstname paramter are same , 2nd paramter is firstname is a string
		eleUtil.doSendKeys(this.firstName, firstName,TimeUtil.DEFAULT_MEDIUM_DURATION);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}

		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);

		String successMesg = eleUtil.waitForElementVisible(successMessg, TimeUtil.DEFAULT_MEDIUM_DURATION).getText();

		System.out.println(successMesg);		
				
		if (successMesg.contains(AppConstant.USER_REGISTER_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else {
			return false;
		}
	}
	
}



package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoSearch;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTesttestng {

	DriverFactory df;
	protected Properties prop;
	WebDriver driver;
	//it is default now so childclass of baseclass cant access loginpage methods through obj
	// no selenium methods in test class
//	LoginPage loginpage;
	protected LoginPage loginpage; // bcoz defualt, private cant use used outside packageS, protected is exactly like public but non subclass from different package cant access
	protected AccountPage accPage;
	protected SearchResultsPage searchresultspage;
	protected ProductInfoSearch prodinfosearch;
	protected SoftAssert softassert;
	protected RegisterPage regpage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName)  //from parameters annotation browser is passed as a parameter
	{//creating obj  of driver factory to use initPRop method
		df= new DriverFactory();
		
		
		//inititally==== driver=df.initDriver("chrome");
		//creating obj of login page so that test class can use methods of login page
		//we cant create obj directly as we have public contr with driver
		
		
		prop=df.initProp();
		//browsername from xml parameter 
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName); //updates xml broswername to config. properties on the fly.
		}
		
		//// call method initprop() by using call by reference
		//let initDriver tells which property it wants to call
		driver=df.initDriver(prop);
		
		loginpage= new LoginPage(driver);
		//this driver will be given to Loginpage and in login page constr this. driver will give driver to private driver of that mrthod and driver can be used in the entire loginpage
		// goes to driverfactory and executes
		softassert= new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}

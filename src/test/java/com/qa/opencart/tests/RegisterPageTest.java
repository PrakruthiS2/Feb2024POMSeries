package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencar.util.CSVUtil;
import com.qa.opencar.util.ExcelUtil;
import com.qa.opencar.util.StringUtil;
import com.qa.opencart.base.BaseTesttestng;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.errors.AppError;

public class RegisterPageTest extends BaseTesttestng {

	@BeforeClass
	public void RegisterSetUp()
	{
		regpage=loginpage.navigateToRegister();
	}
	
	@DataProvider
	public Object[][] userregData()
	{
		return new Object[][] {
			{"Vikas", "paku",  "987654321", "vikas@123", "no"},
			{"mama", "pimpu", "987444321", "mama@123", "no"},
			{"sushi", "amma", "983354321", "vsushi@123", "no"}
		};
	}
	
	
	@DataProvider
	
	public Object[][] userRegDatafromSheet()
	{
		return ExcelUtil.getTestData(AppConstant.REGISTER_SHEET_NAME);
	}
	
	
	@Test(dataProvider="userRegDatafromSheet")
	public void userRegisterationTest(String firstName,String lastName, String number,String password,String subscribe)
	{
		
	
	Assert.assertTrue(regpage.userRegister(firstName,lastName,StringUtil.getRandomEmail(),number,password,subscribe),AppError.QUANTITY_NOT_FOUND);
	}

	
	
//	@DataProvider
//	
//	public Object[][] userDataSheetFromCSV()
//	{
//		return CSVUtil.csvData(AppConstant.REGISTER_SHEET_CSV);
//		
//	}
//	
//
//	@Test(dataProvider="userDataSheetFromCSV")
//	public void userRegisterationTestCSV(String firstName,String lastName, String number,String password,String subscribe)
//	{
//		
//	
//	Assert.assertTrue(regpage.userRegister(firstName,lastName,StringUtil.getRandomEmail(),number,password,subscribe),AppError.QUANTITY_NOT_FOUND);
//	}
//	
//	
//	




	
}

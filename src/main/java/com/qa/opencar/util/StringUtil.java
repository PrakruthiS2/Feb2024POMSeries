package com.qa.opencar.util;

public class StringUtil {

	
	public static String getRandomEmail()
	{
		String email= "user"+System.currentTimeMillis()+"@opencart.com";
		return email;
	}
}

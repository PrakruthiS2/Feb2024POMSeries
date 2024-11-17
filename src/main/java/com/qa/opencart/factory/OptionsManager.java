package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
//since if condition expects  boolean- using wrapper classto get property headless
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====Running tests in headless======");
			co.addArguments("--headless");

		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			co.setCapability("browserName", "chrome");
			co.setBrowserVersion(prop.getProperty("browserversion").trim());
			Map<String,Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			//key officially documented selenoid:options in selenium
			co.setCapability("selenoid:options", selenoidOptions);
			
		}

		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====Running tests in headless======");
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
		}
//		if(Boolean.parseBoolean(prop.getProperty("remote")))
//		{
//			fo.setCapability("browserName", "firefox");
//		//	fo.setCapability("enableVNC", true);
//		}


		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====Running tests in headless======");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			eo.addArguments("--inPrivate");
		}
//		if(Boolean.parseBoolean(prop.getProperty("remote")))
//		{
//			eo.setCapability("browserName", "edge");
//		//	eo.setCapability("enableVNC", true);
//		}

		return eo;
	}
}

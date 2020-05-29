package com.qa.manor.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	public ChromeOptions co;
	public Properties prop;
	public FirefoxOptions fo;
	
	
	
	//constructor with properties 
	public OptionsManager(Properties prop) {
	  this.prop= prop;
	}
	
	
	
	public ChromeOptions getChromeOptions() {
		//object 
		co= new ChromeOptions();
		
		if (prop.getProperty("incognito").equals("yes")) co.addArguments("--incognito");
		
		if (prop.getProperty("headless").equals("yes"))  co.addArguments("--headless");
		
		return co;
		
	}
	
	public FirefoxOptions getFirefoxOptions() {
		
		fo = new FirefoxOptions();
		
		if(prop.getProperty("headless").equals("yes")) fo.addArguments("--headless");
		
		return fo;
		
	}

}


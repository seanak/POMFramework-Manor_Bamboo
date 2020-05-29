package com.qa.manor.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	//public WebDriver driver;
	public Properties prop;
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized  WebDriver getDriver(){
		return tldriver.get();
		
	}
	
	public WebDriver init_driver(String browserName){
		highlightElement = prop.getProperty("highlight").equals("yes")? true:false;
		System.out.println("Browser name = "+ browserName);
		optionsManager = new OptionsManager(prop);
		
		
		if (browserName.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}else if (browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		}else if (browserName.equals("safari")){
			WebDriverManager.getInstance(SafariDriver.class).setup();
			
			tldriver.set(new SafariDriver());
			
		}else {
		
			System.out.println(browserName + "is not found please pass the write browser please pass the correct Browser");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		
		 return getDriver();
	}
	
	public Properties init_properties(){
		prop = new Properties();
		
		String path = "C:\\Users\\seana\\New Workspace\\seleniumBambooMay20\\src\\main\\java\\com\\qa\\manor\\config\\config.properties";
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		} catch (FileNotFoundException e) {
		System.out.println("some problem occured while getting path");
		} catch (IOException e) {
			System.out.println("some problem occured while loading property file");
		}
		
		return prop;
	}
	
	public String getScreenshot(){
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "C:/Users/seana/New Workspace/seleniumBambooMay20/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File ( path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Screenshot capture failed....(BaseaPage)");
		}
		
		return path;
	}

}

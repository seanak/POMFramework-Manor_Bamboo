package com.qa.manor.tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.manor.base.BasePage;
import com.qa.manor.listeners.TestAllureListener;
import com.qa.manor.pages.WelcomePage;
import com.qa.manor.util.Constant;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 101 : create Welcome page features")
@Feature("US - 501 : create test on welcome page Manor.ch, US - 502 : Using page chaining model to connect wlcome page go to login page ")
@Listeners({TestAllureListener.class})
public class WelcomePageTest {
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	WelcomePage welcomePage;
	Constant  constant;
	
	
	@BeforeMethod(alwaysRun= true)
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		welcomePage = new WelcomePage(driver);
		

	
	}
	
	@Test (priority = 1)
	@Description(" getting title of the Welcome page ")
	@Severity( SeverityLevel.NORMAL)
	public void getPageTitle_test(){
		String wPTitle = welcomePage.getPageTital();
		Assert.assertEquals(wPTitle,constant.wecomePageTitle);
	}
	
	@Test (priority = 2)
	@Description("checking manor logo ")
	@Severity( SeverityLevel.BLOCKER)
	public void verifyPageLogo_test(){
		Assert.assertTrue(welcomePage.verifyManorLogo());
	}
	
	@Test (priority = 3)
	@Description("checking manor logo ")
	@Severity( SeverityLevel.CRITICAL)
	public void verifySeachBar_test(){
		Assert.assertTrue(welcomePage.verifyWPSearchbar(), "search bar is available");;
		
	}
	
	@Test (priority = 4)
	@Description("page chaining model to connect WelcomePage to LoginPage ")
	@Severity( SeverityLevel.CRITICAL)
	public void goToLoginPage_test(){
		welcomePage.goToLoginPage();
	}
	
	
	
	@AfterMethod(alwaysRun= true)
	public void tearDown(){
		driver.quit();
		
	}

}

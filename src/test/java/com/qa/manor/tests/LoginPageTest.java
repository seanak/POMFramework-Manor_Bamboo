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
import com.qa.manor.pages.LoginPage;
import com.qa.manor.pages.WelcomePage;
import com.qa.manor.util.Constant;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 102 : create Login page features")
@Feature("US - 503 : create test on Login page manor.ch")
@Listeners({TestAllureListener.class})


public class LoginPageTest {
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	WelcomePage welcomePage;
	LoginPage loginPage;
	
	@BeforeMethod(alwaysRun= true)
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		welcomePage = new WelcomePage(driver);
		welcomePage.goToLoginPage();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginPage = new LoginPage(driver);
			
		
	}
	
	@Test (priority = 1,enabled = true)
	@Description(" getting title of the LoginPage ")
	@Severity( SeverityLevel.NORMAL)
	public void getLoginPageTitle_test(){
		String lPTitle = loginPage.getPageTitle();
		Assert.assertEquals(lPTitle , Constant.LoginPageTitle);
	}
	
	@Test (priority = 2,enabled = true)
	@Description("verifing forgot password link is available or not ")
	@Severity( SeverityLevel.NORMAL)
	public void verifyForgetPwd_test(){
		Assert.assertTrue(loginPage.verifyForgetPwd());
	}
	
	@Test (priority = 3)
	@Description("sending false value to Login and checking")
	@Severity( SeverityLevel.MINOR)
	public void doLogin_test() throws InterruptedException{
		
	loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	
	}
	@AfterMethod(alwaysRun= true)
	public void tearDown(){
		driver.quit();
		
	}
	
	

}

package com.qa.manor.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.manor.base.BasePage;
import com.qa.manor.util.Constant;
import com.qa.manor.util.ElementUtil;
import com.qa.manor.util.JavaScriptUtil;

public class LoginPage extends BasePage{
	WebDriver driver;
	Properties prop;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	

	//By locator
	By emailId = By.id("username");
	By pwd = By.id("password");
	By Anmelden = By.xpath("//*[@id='loginForm']/button");
	By passwortvergessen = By.xpath("//a [text()= 'Passwort vergessen?']");
	By errorMsg = By.xpath("//a [@href= '#login-container']");
	
	//Constructor

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		prop = new Properties();
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	//methods 
	
	public String getPageTitle(){
		elementUtil.waitForPageTitle(Constant.LoginPageTitle);
		String loginPageTitle =elementUtil.getPageTitle();
		//String loginPageTitle = driver.getTitle();
		System.out.println("Login Page Title is "+loginPageTitle);
		return loginPageTitle;
	}

	public boolean verifyForgetPwd(){
		elementUtil.WaitforVisibilityofElement(passwortvergessen);
		return elementUtil.doIsDisplayed(passwortvergessen);
		//return driver.findElement(passwortvergessen).isDisplayed();
	}
	
	public void doLogin(String username, String password){
		
		elementUtil.WaitforVisibilityofElement(emailId);
		elementUtil.SendKeys(emailId, username);
		elementUtil.SendKeys(pwd, password);
		elementUtil.doClick(Anmelden);
		/*
		driver.findElement(emailId).sendKeys(username);
		driver.findElement(pwd).sendKeys(password);
		driver.findElement(Anmelden).click();
		*/
	}
	
}

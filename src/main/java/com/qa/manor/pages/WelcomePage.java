package com.qa.manor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.manor.base.BasePage;
import com.qa.manor.util.Constant;
import com.qa.manor.util.ElementUtil;
import com.qa.manor.util.JavaScriptUtil;

public class WelcomePage extends BasePage{
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	//locators 
	
	By pageLogoImg = By.xpath("//div/img[@src='/_ui/assets/img/manor-logo-pos.svg']");
	By wpSearchbar = By.xpath("//input[@id='js-site-search-input']");
	By loginLink = By.xpath("//li//a[@href='/de/login']");
	
	//constructor 
	
	public WelcomePage(WebDriver driver){
		this.driver= driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
		
	}
	
	// methods 
	
	public String getPageTital(){
		String wptitle =elementUtil.getPageTitle();
		System.out.println("welcome page title is :-  "+wptitle);
		return wptitle;
	}
	
	public boolean verifyManorLogo(){
		return elementUtil.doIsDisplayed(pageLogoImg);
	}
	
	public boolean verifyWPSearchbar(){
		return elementUtil.doIsDisplayed(wpSearchbar);
	}
	
	public LoginPage goToLoginPage(){
		elementUtil.WaitforVisibilityofElement(loginLink);
		elementUtil.doClick(loginLink);
		elementUtil.waitForPageTitle(Constant.LoginPageTitle);
		String lpTitle = elementUtil.getPageTitle();
		System.out.println("loginPageTitle is :- "+lpTitle);
		return new LoginPage(driver);
	}
}

package com.qa.manor.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.manor.base.BasePage;

public class ElementUtil extends BasePage {
	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsutil;
	
	//constructor
	public ElementUtil(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Constant.defaultTimeOut);
		jsutil = new JavaScriptUtil(driver);
		
	}
	
	//wait
	public boolean WaitForPresenceofElement(By locator){
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return true;
		
	}
	
	public  boolean  WaitforVisibilityofElement(By locator){
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return false;
	
	}
	
	public void waitForPageTitle(String title){
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	
	//get element
	
	public WebElement findElement(By locator){
		WebElement element = null;
		try {	
			
		element = driver.findElement(locator);
		 
		if (highlightElement) {
			jsutil.flash(element);
			}
			
	
		}catch(Exception e){
			System.out.println("some exeption got occurerd while creating the web Element "+ locator);
			}
		return element;
	}
	
	public String getPageTitle(){
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception occured while getting title");
		}
		return null;
		
	}
	
	public void doClick(By locator){
		try {
			findElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception occured while clicking on Elemwnt "+ locator);
		}
		
	}
	
	public String SendKeys(By locator,String value){
		try {
			findElement(locator).sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception occured while sending the value to the fild "+locator);
		}
		return value;
	}
	
	public boolean doIsDisplayed(By locator){
		
		try {
			return findElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("soem exception occured while displaying the Element "+ locator);
		}
		return false;
	}
	
	public String getText(By locator){
		try {
			return findElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception occured while getiing the text");
		}
		return null;
		
	}
	
}

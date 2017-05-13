package com.ebaytest.utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommandUtils {

	public static WebElement waitUntilPresenceOfElementLocated(WebDriver driver,int timeOut, String xpathLocator){
		return (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathLocator)));
	}

	public static boolean waitUntilPresenceOfElementLocated(WebDriver driver,int timeOut, By locator){
		try {
			new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean verifyElementDisplayed(WebDriver driver, By locator){
		try {
			driver.findElement(locator).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean verifyElementPresent(WebDriver driver, By locator){
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean enterValToInputField(WebDriver driver, By locator, String inputString) {
		if (verifyElementDisplayed(driver, locator)) {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(inputString);
			return true;
		}
		return false;
	}

	public static String getElementstext(String xPathOfElements, WebDriver driver) throws Exception{
		String elementsText = new String();
		String jScript = "var result = document.evaluate(\""+xPathOfElements+"\", document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);"
				+ "var returnString = '';"
				+ "for (var i=0; i<result.snapshotLength; i++) {"
				+ "if( i < result.snapshotLength - 1){"
				+ "returnString = returnString + result.snapshotItem(i).textContent.trim()+'~';"
				+ "}"
				+ "else{"
				+ "returnString = returnString + result.snapshotItem(i).textContent.trim();"
				+ "}"
				+ "}"
				+ "return returnString;";
		if (driver instanceof JavascriptExecutor) {
			elementsText = (String) ((JavascriptExecutor) driver).executeScript(jScript);
			if (!StringUtils. isBlank(elementsText)) {
				return elementsText;
			}
			else {
				throw new Exception("No text found in specified locator : "+ xPathOfElements);
			}
		}
		throw new Exception("Webdriver is not an instance of JavascriptExecutor.");
	}

}

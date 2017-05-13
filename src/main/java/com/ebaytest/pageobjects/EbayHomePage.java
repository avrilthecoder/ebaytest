package com.ebaytest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ebaytest.base.BasePage;
import com.ebaytest.utils.CommandUtils;

public class EbayHomePage extends BasePage {
	private WebDriver driver;
	private By ebayHomeLink = By.xpath("//a[contains(@href,'www.ebay.com') and contains(text(),'eBay')]/img[@role='presentation']");
	private By ebaySearchBar = By.xpath("//label[text()='Enter your search keyword']/following-sibling::input[@type='text']");
	private By ebaySeachBtn = By.xpath("//input[contains(@class,'btn') and @value='Search']");
	
	public EbayHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		if (!CommandUtils.waitUntilPresenceOfElementLocated(driver, TIME_OUT, ebayHomeLink)) {
			System.err.println("Unable to load ebay Home page");
		}
		
	}

	public SearchResultPage searchInputProduct(String inputString) {
		CommandUtils.enterValToInputField(driver,ebaySearchBar,inputString);
		driver.findElement(ebaySeachBtn).click();
		return new SearchResultPage(driver);
	}
	

}

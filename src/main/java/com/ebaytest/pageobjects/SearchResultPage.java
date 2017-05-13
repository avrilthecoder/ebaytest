package com.ebaytest.pageobjects;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ebaytest.base.BasePage;
import com.ebaytest.base.SortType;
import com.ebaytest.utils.CommandUtils;
import com.ebaytest.utils.WrongPageException;

public class SearchResultPage extends BasePage {
	private WebDriver driver;
	private By sortToggleBtn = By.xpath("//div[@id='DashSortByContainer']//a[contains(@class,'dropdown-toggle')]");
	private By viewSearchResultLink = By.xpath("//div[@id='mainContent']//span[text()='view search results']");
	private By sortByPriceLowestFirst = By.xpath("//ul[@id='SortMenu']//a[contains(text(),'Price + Shipping: lowest first')]");
	private By sortByPriceHighestFirst = By.xpath("//ul[@id='SortMenu']//a[contains(text(),'Price + Shipping: highest first')]");
	private String xpathOfsearchResultItemsPriceList = "//div[@id='ResultSetItems']//li[contains(@id,'item')]//li[contains(@class,'lvprice')]/span";
	private By searchResultItemsPriceList = By.xpath(xpathOfsearchResultItemsPriceList);
	private String xpathOfsearchResultItemsNameList = "//div[@id='ResultSetItems']//li[contains(@id,'item')]//h3[contains(@class,'lvtitle')]/a";
	private By searchResultItemsNameList = By.xpath(xpathOfsearchResultItemsNameList);
	private By sortDrpDownContainer = By.id("SortMenu");

	public SearchResultPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		try {
			driver.findElement(viewSearchResultLink).click();
			if (!CommandUtils.verifyElementDisplayed(driver, sortToggleBtn)) {
				throw new WrongPageException("Unable to navigete to Search result page");
			}
		} catch (Exception e) {
			if (!CommandUtils.verifyElementDisplayed(driver, sortToggleBtn)) {
				throw new WrongPageException("Unable to navigete to Search result page");
			}
		}
	}

	public void sortByPrice(SortType sortTypeVal) {
		driver.findElement(sortToggleBtn).click();
		CommandUtils.waitUntilPresenceOfElementLocated(driver, TIME_OUT, sortDrpDownContainer);
		if (sortTypeVal.equals(SortType.PRICE_LOW_TO_HIGH)) {
			try {
				driver.findElement(sortByPriceLowestFirst).click();
			} catch (Exception e) {
				System.err.println("Sort by Price low to higt is not present or preslected.");
			}
		}
		else {
			driver.findElement(sortByPriceHighestFirst).click();
		}
	}

	public ArrayList<String> getAllItemAlongPriceList() {
		ArrayList<String> namePriceArray = new ArrayList<String>();
		try {
			String allName = CommandUtils.getElementstext(xpathOfsearchResultItemsNameList, driver);
			String allPriceVal = CommandUtils.getElementstext(xpathOfsearchResultItemsPriceList, driver);
			String[] allNameArray = allName.split("~");
			String[] allPriceArray = allPriceVal.split("~");
			if (allNameArray.length == allPriceArray.length) {
				for (int i = 0; i < allNameArray.length; i++) {
					namePriceArray.add(allNameArray[i] +" ~ "+allPriceArray[i]);
				}
			}
			else {
				System.err.println("Unequal name and price list");
			}
		} catch (Exception e) {
			System.err.println("error..." + e.getMessage());
		}
		return namePriceArray;
	}
	
	public ArrayList<String> getNumberOfItemAlongPriceList(int countOfResultRequired){
		ArrayList<String> allItemAlongPriceList = getAllItemAlongPriceList();
		ArrayList<String> finalResultRequired = new ArrayList<String>();
		for (int i = 0; i < countOfResultRequired; i++) {
			finalResultRequired.add(allItemAlongPriceList.get(i));
		}
		return finalResultRequired;
	}



}

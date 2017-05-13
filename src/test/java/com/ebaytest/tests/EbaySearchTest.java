package com.ebaytest.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ebaytest.base.SortType;
import com.ebaytest.base.TestBaseSetup;
import com.ebaytest.pageobjects.EbayHomePage;
import com.ebaytest.pageobjects.SearchResultPage;

public class EbaySearchTest extends TestBaseSetup{
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}
	
	@Parameters({ "searchProduct", "resultCount" })
	@Test
	public void verifyHomePage(String searchProduct, int resultCount) throws InterruptedException {
		EbayHomePage ebayHomePageObj = new EbayHomePage(driver);
		SearchResultPage searchResultPageObj = ebayHomePageObj.searchInputProduct(searchProduct);
		
		searchResultPageObj.sortByPrice(SortType.PRICE_LOW_TO_HIGH);
		ArrayList<String> nameAndPriceVlaueForGivenReults = searchResultPageObj.getNumberOfItemAlongPriceList(resultCount);
		System.out.println(nameAndPriceVlaueForGivenReults);
		
		searchResultPageObj.sortByPrice(SortType.PRICE_HIGH_TO_LOW);
		ArrayList<String> nameAndPriceVlaueForFirtGivenReults = searchResultPageObj.getNumberOfItemAlongPriceList(resultCount);
		System.out.println(nameAndPriceVlaueForFirtGivenReults);
	}
}

package com.ebaytest.base;

import org.openqa.selenium.WebDriver;

public class BasePage {
	private WebDriver driver;
	protected static final int TIME_OUT = 3000;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
}

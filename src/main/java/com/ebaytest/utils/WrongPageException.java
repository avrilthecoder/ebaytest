package com.ebaytest.utils;

public class WrongPageException extends RuntimeException {
	public WrongPageException(String errMessage) {
		super(errMessage);
	}

}

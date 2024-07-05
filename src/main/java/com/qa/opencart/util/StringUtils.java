package com.qa.opencart.util;

public class StringUtils {

	public static String getRandomeEmailId() {
		String emailId = "test"+System.currentTimeMillis()+"@opencart.com";
		return emailId;
	}
}

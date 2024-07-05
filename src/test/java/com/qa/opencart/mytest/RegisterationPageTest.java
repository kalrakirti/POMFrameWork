package com.qa.opencart.mytest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.error.AppError;
import com.qa.opencart.util.StringUtils;

public class RegisterationPageTest extends BaseTest{
	
	@BeforeClass
	public void registertrationSetup() {
		
		regPage = loginPage.navigateToRegisterPage();
	}

	
	@DataProvider
	public Object[][] userRegData() {
		
		return new Object[][] {
			{"Gill","Pill","2341567892","Gill@123","No"},

			{"Sill","Pill","2341567892","Gill@123","Yes"},

			{"Drill","Pill","2341567892","Gill@123","Yes"}
				
			};
	}
	
	@Test(dataProvider="userRegData")
	public void userRegisterationTest(String firstname,String lastname,String telephone,String pwd,String subscribe) {
	Assert.assertTrue(regPage.registerUser(firstname,lastname,StringUtils.getRandomeEmailId(),telephone,pwd, subscribe),AppError.REG_NOT_DONE);
	}
}

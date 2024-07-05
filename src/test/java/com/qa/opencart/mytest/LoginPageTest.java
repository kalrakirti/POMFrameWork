package com.qa.opencart.mytest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.error.AppError;
import com.qa.opencart.pages.AccountsPage;

public class LoginPageTest extends BaseTest{
	
	
	@Test(priority =1)
	public void loginPageTitleTest() {
		String actTitle =loginPage.getLoginTitle();
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
		
	}

	@Test(priority =2)
	public void loginPageUrlTest() {
		String actUrl =loginPage.getLoginUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_VALUE));//,AppError.URL_NOT_FOUND);
		}
	
	@Test(priority =3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.checkForgotPwdLinkExist(),AppError.ELEMENT_NOT_FOUND);
	}
	
	@Test(priority =4) 
	public void LoginPageLoginTest() {
		accountsPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		String actTitle =accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}

}

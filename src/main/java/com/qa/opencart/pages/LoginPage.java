package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import com.qa.opencart.util.TimeUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	// 1. Page object : By locators

	private By id = By.id("input-email");
	private By pass = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgot_pwd = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2. public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}

	// 3. public page actions
	public String getLoginTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE,TimeUtil.DEFAULT_SHORT_TIME);
		return title;
	}

	public String getLoginUrl() {
		String url =eleUtil.waitForURLToBe(AppConstants.LOGIN_PAGE_FRACTION_VALUE, TimeUtil.DEFAULT_SHORT_TIME);
		System.out.println(url);
		return url;
	}

	public Boolean checkForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgot_pwd);
		}

	public AccountsPage doLogin(String userName, String pwd) {
		
		eleUtil.doSendKeys(id,userName,TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(pass, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
//		return driver.getTitle();
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink,TimeUtil.DEFAULT_SHORT_TIME);
		return new RegisterPage(driver);
	}
}

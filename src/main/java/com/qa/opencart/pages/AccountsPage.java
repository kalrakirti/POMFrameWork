package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import com.qa.opencart.util.TimeUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By logoutLink = By.linkText("Logout");
	private By searchBtn = By.cssSelector("div#search button");

	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_SHORT_TIME);
		return title;
	}

	public String getAccountsPageUrl() {
		String url = eleUtil.waitForURLToBe(AppConstants.ACCOUNTS_PAGE_FRACTION_VALUE, TimeUtil.DEFAULT_SHORT_TIME);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}

	public List<String> getPageHeaders() {

		List<WebElement> elements = eleUtil.waitForVisibilityOfElemenetsLocated(headers, TimeUtil.DEFAULT_SHORT_TIME);
		List<String> headersValList = new ArrayList<String>();

		for (WebElement e : elements) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}

	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(search);

	}

	public SearchResultsPage doSearch(String searchKey) {
		if (isSearchExist()) {
			
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchBtn);
		}
		return new SearchResultsPage(driver);

	}

}

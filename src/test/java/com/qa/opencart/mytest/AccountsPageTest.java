package com.qa.opencart.mytest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.error.AppError;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
//		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void AccountsPageTitleTest() {
		String actTitle = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Test
	public void AccountsPageUrlTest() {
		String actTitle = accountsPage.getAccountsPageUrl();
		Assert.assertTrue(actTitle.contains(AppConstants.ACCOUNTS_PAGE_FRACTION_VALUE), AppError.TITLE_NOT_FOUND);
	}

	@Test
	public void accountsPageHeadersTest() {
		List<String> actPageHeaders = accountsPage.getPageHeaders();
		Assert.assertEquals(actPageHeaders, AppConstants.accountsPageHeaderList, AppError.LIST_IS_NOT_MATCHED);
	}

	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] { { "macbook", 3 }, { "imac", 1 }, { "samsung", 2 }, { "airtel", 0 } };

	}

	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey, int resultCount) {
		searchResultsPage = accountsPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.searchCount(), resultCount, AppError.COUNT_NOT_MATCHED);
//		Assert.assertEquals(searchResult.)
	}

}

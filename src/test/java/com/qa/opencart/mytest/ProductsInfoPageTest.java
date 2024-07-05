package com.qa.opencart.mytest;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.error.AppError;
import com.qa.opencart.pages.ProductInfoPage;

public class ProductsInfoPageTest  extends BaseTest{

private ProductInfoPage searchProduct;

@BeforeClass
public void productInfoPageSetup() {
	accountsPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	
}

@Test(dataProvider = "getSearchData")
public void productHeaderTest(String searchKey,String productName) {
	searchResultsPage =accountsPage.doSearch(searchKey);
	productInfoPage =searchResultsPage.searchProduct(productName);
	Assert.assertEquals(productInfoPage.getProductHeader(),productName,AppError.HEADER_NOT_FOUND);
}

@DataProvider
public Object[][] getSearchData() {
	return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" },
		{ "samsung", "Samsung SyncMaster 941BW" }, { "samsung", "Samsung Galaxy Tab 10.1" },
		{"canon","Canon EOS 5D"}
	};

}

@DataProvider
public Object[][] getProductImageData() {
	return new Object[][] { { "macbook", "MacBook Pro" ,4}, { "imac", "iMac" ,3},
		{ "samsung", "Samsung SyncMaster 941BW" ,1}, { "samsung", "Samsung Galaxy Tab 10.1" ,7},
		{"canon","Canon EOS 5D" ,3}
	};
}
@Test(dataProvider ="getProductImageData")
public void productIageTest(String key,String productName,int imageCount) {
	searchResultsPage =accountsPage.doSearch(key);
	productInfoPage =searchResultsPage.searchProduct(productName);
	Assert.assertEquals(productInfoPage.getProductImagesCount(),imageCount,AppError.IMAGES_COUNT_MISMATCH);
}

//Test with multiple assertions
@Test
public void productInfoTest() {
	searchResultsPage =accountsPage.doSearch("macbook");
	productInfoPage =searchResultsPage.searchProduct("MacBook Pro");
	Map<String, String> productInfoMap = productInfoPage.getProductInfoMap();
	System.out.println("-----product information-----");
	System.out.println(productInfoMap);
	softAssert.assertEquals(productInfoMap.get("productname"),"MacBook Pro");
	softAssert.assertEquals(productInfoMap.get("Brand"),"Apple");
	softAssert.assertEquals(productInfoMap.get("Product Code"),"Product 18");
	softAssert.assertEquals(productInfoMap.get("Reward Points"),"800");
	softAssert.assertEquals(productInfoMap.get("Availability"),"In Stock");
	softAssert.assertEquals(productInfoMap.get("productPrice"),"$2,000.00");
	softAssert.assertEquals(productInfoMap.get("ExTax"),"$2,000.00");
	softAssert.assertAll();
}


//Hard assertion has all static methods.
//Soft assertion has non static methods

//Assert vs Verify (Soft Assertion)
}

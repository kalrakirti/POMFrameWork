package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;
import com.qa.opencart.util.TimeUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	By searchResult = By.cssSelector("div.product-thumb");
	
	
	public int searchCount() {
		List<WebElement> result = eleUtil.waitForVisibilityOfElemenetsLocated(searchResult, TimeUtil.DEFAULT_SHORT_TIME);
		return result.size();
	}

	public ProductInfoPage searchProduct(String productName) {
		eleUtil.doClick(By.linkText(productName), TimeUtil.DEFAULT_SHORT_TIME);
		return new ProductInfoPage(driver);
	}
}

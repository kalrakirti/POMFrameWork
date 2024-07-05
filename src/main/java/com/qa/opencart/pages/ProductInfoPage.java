package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;
import com.qa.opencart.util.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By product = By.cssSelector("div#content h1");
	private By prodImagesCount = By.cssSelector("div#content a.thumbnail");
	private By productmetaData = By.xpath("(//div[@id ='content']//ul[@class='list-unstyled'])[1]/li");
	private By pricing = By.xpath("(//div[@id ='content']//ul[@class='list-unstyled'])[2]/li");
	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = eleUtil.doGetText(product);
		System.out.println("prod header is " + header);
		return header;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForVisibilityOfElemenetsLocated(prodImagesCount, TimeUtil.DEFAULT_SHORT_TIME)
				.size();
		System.out.println("Total number of images" + imagesCount);
		return imagesCount;
	}

	public Map<String,String> getProductInfoMap() {
		productMap = new TreeMap<String, String>();
		
//		productMap = new HashMap<String, String>();
//		productMap = new LinkedHashMap<String, String>();
			
		productMap.put("productname", getProductHeader());
		productMap.put("imagesCount",String.valueOf(getProductImagesCount()) );
		getProductMetaData();
		getPriceData();
		return productMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productmetaData);
		for (WebElement e : metaList) {
			String metaData = e.getText();
			String meta[] = metaData.split(":");
			String metakey = meta[0].trim();
			String metaVal = meta[1].trim();
			productMap.put(metakey, metaVal);
		}
	}

//	
//	$98.00
//	Ex Tax: $80.00

	private void getPriceData() {
		List<WebElement> priceList = eleUtil.getElements(pricing);
		String productPrice=priceList.get(0).getText();
		String productTax =priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice",productPrice);
		productMap.put("ExTax", productTax);
		
	}
}

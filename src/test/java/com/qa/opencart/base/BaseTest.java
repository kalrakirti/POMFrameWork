package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.RegisterationPage;
import com.qa.opencart.pages.SearchResultsPage;

 
public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected LoginPage loginPage;
	protected Properties prop;
	protected AccountsPage accountsPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage regPage;
	
	

	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setUp(String browser,@Optional String browserVersion,@Optional String testName) {
	df = new DriverFactory();
	prop= df.initProp();
	
	if(browser!= null){
		prop.setProperty("browserName", browser);
		prop.setProperty("browserversion", browserVersion);
		prop.setProperty("testname", testName);
	}
	
//	String  browser = prop.getProperty(browser);
//	df.initDriver(browser);
	driver =	df.initDriver(prop);
	loginPage= new LoginPage(driver);
	softAssert= new SoftAssert();
	
		}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

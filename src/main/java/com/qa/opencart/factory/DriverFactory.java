package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.error.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	
	
/**
 * This method is used ti initialize browser on basis of browser passed.
 * @param browserName
 */
	public WebDriver initDriver(Properties prop) {
		String browser=prop.getProperty("browserName");
		String ur =prop.getProperty("url");
		System.out.println("your browser is " + browser);
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println();
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
						}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(ur);
		return driver;
	}

	
	/**
	 * This methosd is used to intialize properties from .properties file.
	 * and return properties refrence prop.
	 * @return
	 */
	public Properties initProp()  {
		prop = new Properties();
		FileInputStream ip =null;
		
		//mvn clean install -Denv ="qa"
		String envName = System.getProperty("env");
		System.out.println("running the suite in env---- "+envName);
		if(envName == null) {
			System.out.println("env name is null , hence running in QA env ");
			try {
				ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
		
		try {
		switch (envName.trim().toLowerCase()) {
		case "qa":
			ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			break;
		case "dev":
			ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
			break;
		case "uat":
			ip = new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
			break;
		case "stage":
			ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
			break;
		case "prod":
			ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			break;
		default:
			System.out.println("Please pass the correct env name");
			throw new FrameWorkException("wrong env name ");
					}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}	
			return prop;
		
	}
}

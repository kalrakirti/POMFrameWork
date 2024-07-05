package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import com.qa.opencart.util.TimeUtil;

public class RegisterPage {

	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By myAccount = By.xpath("//a[@title='My Account']");
	private By login = By.linkText("Login");
	private By register = By.linkText("Register");
	private By firstName = By.cssSelector("#input-firstname");
	private By lastName = By.xpath("//input[@name='lastname']");
	private By email = By.xpath("//input[@type='email']");
	private By telephone = By.xpath("//input[@type='tel']");
	private By password = By.xpath("//input[@placeholder='Password']");
	private By confirmPassword = By.xpath("//input[@placeholder='Password Confirm']");
	private By radioYes = By.xpath("//label[@class='radio-inline']/input[@value='1']");
	private By radioNo = By.xpath("//label[normalize-space()='No']"); //// label[@class='radio-inline']/input[@value='0']
	private By termsAgree = By.xpath("//input[@name='agree']");
	private By continuebtn = By.xpath("//input[@value='Continue']");
	private By heading = By.xpath("//h1[contains(text(),'Your Account Has')]");
	private By para1 = By.xpath("//p[contains(text(),'Congratulations!')]");
	private By para2 = By.xpath("//p[contains(text(),'You can now')]");
	private By para3 = By.xpath("//p[contains(text(),'If you have ANY')]");
	private By para4 = By.xpath("//p[contains(text(),'A confirmation')]");
	private By continueLink = By.linkText("Continue");
	private By logout = By.linkText("Logout");
	
	public boolean registerUser(String firstName, String lastName,String email,String telephone,String password,String subscribe) {
				                                                                  
	    		                                                                                    
	    		eleUtil.doSendKeys(this.firstName, firstName, TimeUtil.DEFAULT_SHORT_TIME);
	    		eleUtil.doSendKeys(this.lastName, lastName);                                                                     
	    		eleUtil.doSendKeys(this.email, email);                                                      
       		    eleUtil.doSendKeys(this.telephone, telephone);                                                           
      		    eleUtil.doSendKeys(this.password, password);                                                               
        		eleUtil.doSendKeys(this.confirmPassword, password);                                                        
        		
        		if(subscribe.equalsIgnoreCase("yes")) {
        		eleUtil.doClick(radioYes);             
        		}else {
        		eleUtil.doClick(radioNo);}
        		
        		eleUtil.doClick(termsAgree);                                                                           
        		eleUtil.doClick(continuebtn);   
        		String succesMsg = eleUtil.doGetText(heading);  
        		
        		if(succesMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG)) {
        			eleUtil.doClick(logout);
        			eleUtil.doClick(register);
        			return true;
        		}else
        		{
        			return false;
        		}
//        		System.out.println(                                                                                  
//        		eleUtil.doGetText(para1) + eleUtil.doGetText(para2) + eleUtil.doGetText(para3) + eleUtil.doGetText(para4));
//                eleUtil.doClick(continueLink);
	}
}
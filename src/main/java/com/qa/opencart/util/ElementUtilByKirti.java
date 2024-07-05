package com.qa.opencart.util;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;

public class ElementUtilByKirti {

	private WebDriver driver;

	public ElementUtilByKirti(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By loc) {
		try {
			WebElement element = driver.findElement(loc);
			return element;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;
		}

	}

	private void nullCheck(String value) {
		if (value == null) {
			throw new ElementException("senKeys value is null " + value);
		}
	}

	public void doSendKeys(By locate, CharSequence key) {

//		nullCheck(key);
		getElement(locate).sendKeys(key);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {

		return driver.findElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attribute) {
		return getElement(locator).getAttribute(attribute);
	}

	public int getElementsCount(By locator) {
		return driver.findElements(locator).size();
	}

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);
	}

	public List<String> getElementsText(By locator) {

		List<WebElement> elelist = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();

		for (WebElement e : elelist) {
			String text = e.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	public List<String> getElememntAttList(By locator, String attName) {

		List<WebElement> imageList = getElements(locator);
		List<String> attValList = new ArrayList<String>();

		for (WebElement e : imageList) {
			String attVal = e.getAttribute(attName);
			if (attVal != null && attVal.length() != 0) {
				attValList.add(attVal);
				System.out.println(attValList);
			}
		}
		return attValList;
	}

	// Dropdown utilty for select dropdowns

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void doSelectByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public void doSelectByText(By locator, String Text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(Text);
	}

	public int getDropdownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		return getDropDownOptions(locator).size();
	}

	public List<String> getDropDownOptions(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		List<String> optionsTextList = new ArrayList<String>();

		for (WebElement e : options) {
			String text = e.getText();
			optionsTextList.add(text);
		}

		return optionsTextList;

	}

	public void selectValueFromDropDown(By locator, String optionText) {
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();

		for (WebElement e : options) {
			String text = e.getText();
			if (text.equals(optionText.trim())) {
				e.click();
				break;
			}
		}
	}

	public void selectValueFromDropDownWithoutSelectClass(By locator, String selectText) {

		List<WebElement> countryList = getElements(locator);
		System.out.println(countryList.size());

		for (WebElement e : countryList) {
			String text = e.getText();
			if (text.equals(selectText)) {
				e.click();
			}
		}

	}

//		-----------Action Utilities--------

	public void selectSubMenues(By parent, By subMenue) throws InterruptedException {

		Actions act = new Actions(driver);

		act.moveToElement(getElement(parent)).perform();
		Thread.sleep(2000);
		getElement(subMenue).click();

	}

	public void doDragAnadDrop(By src, By tgt) {
		Actions act = new Actions(driver);
		act.clickAndHold(getElement(src)).moveToElement(getElement(tgt)).release().perform();

	}

/**
 * This method is used to enter value in textfield with pause 
 * @param locator
 * @param value
 * @param pauseTime
 */
	public void doSendKeysWithAct(By locator, String value, long pauseTime) {

		Actions act = new Actions(driver);
		char ch[] = value.toCharArray();

		for (char c : ch) {
			act.sendKeys(driver.findElement(locator), String.valueOf(c)).pause(pauseTime).perform();
//		act.sendKeys(driver.findElement(search),c+"");

		}

	}
	/**
	 * This method is used to send text in textfield but wit a pause default(500)
	 * @param locator
	 * @param value
	 */
	public void doSendKeysWithAct(By locator, String value) {

		Actions act = new Actions(driver);
		char ch[] = value.toCharArray();

		for (char c : ch) {
			act.sendKeys(driver.findElement(locator), String.valueOf(c)).pause(500).perform();
//		act.sendKeys(driver.findElement(search),c+"");

		}

	}
	

	public void level4MenuSubmenuHandling(By level1, By level2, By level3, By level4)
			throws InterruptedException {

		driver.findElement(level1).click();
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(level2)).perform();
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(level3)).perform();
		Thread.sleep(2000);
		driver.findElement(level4).click();
	}

	public void level4MenuSubmenuHandlingusingMouseHover(By level1, By level2, By level3, By level4)
			throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(level1)).perform();
		Thread.sleep(2000);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(level2)).perform();
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(level3)).perform();
		Thread.sleep(2000);
		driver.findElement(level4).click();
	}

	public  void level4MenuSubmenuHandlingusingMouseHover(By level1, String level2, String level3, String level4)
			throws InterruptedException {

		driver.findElement(level1).click();
		Actions act = new Actions(driver);
		//act.moveToElement(driver.findElement(level1)).perform();
		Thread.sleep(2000);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.linkText(level2))).perform();
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.linkText(level3))).perform();
		Thread.sleep(2000);
		driver.findElement((By.linkText(level4))).click();
	}
	
//	------------------with wait---------------
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does notnecessarily mean that the element is visible
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public  WebElement waitForElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));// sel 4
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that isgreater than 0. *
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public  WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));// sel 4
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * 
	 * 
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param timeOut
	 * @param locator
	 * @return
	 */
	public  List<WebElement> waitForAllElementsPresent(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		// An expectation for checking that there is at least one element present on a
		// web page.
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a heightand width that is greater than 0.
	 * 
	 * @param timeOut
	 * @param locator
	 * @return
	 */

	public  List<WebElement> waitForAllElementsVisible(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		// An expectation for checking that there is at least one element present on a
		// web page.
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
}

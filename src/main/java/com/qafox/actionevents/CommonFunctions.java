package com.qafox.actionevents;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.qafox.utils.LogManager;
import com.qafox.drivers.DriverManager;


public class CommonFunctions extends DriverManager {

	public void moveAndClick(By locator) {
		WebElement ele = driver.findElement(locator);
		Actions action = new Actions(driver);
		action.moveToElement(ele).click().build().perform();
	}
	
	
	public void click(By locator, String locaterName) throws InterruptedException {
		Thread.sleep(1000);
		
		if(browser.equalsIgnoreCase("chrome")){
			moveAndClick(locator);
		}else{
			WebElement ele = driver.findElement(locator);
			ele.click();
		}
	}

	
	public void mouseHover(By element, String locaterName) throws Exception {
		boolean flag = false;
		try {
			Thread.sleep(1000);
			WebElement webelement = driver.findElement(element);
			Actions actions = new Actions(driver);
			actions.moveToElement(webelement);
			actions.build().perform();
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			if (flag) {
				LogManager.info("Able to mouseover on element " + locaterName);
			} else {
				LogManager.info("Unable to mouseover on element " + locaterName);
			}
		}

	}
	
	
	public void type(By locator, String input) throws InterruptedException {
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(input);
			flag = true;
		} catch (Exception en) {
			flag = false;
			en.printStackTrace();
			LogManager.error("Unable type text on text feild: " + locator);
		} finally {
			if (flag) {
				LogManager.info("Able to enter Input Data - " + input);
			} else {
				LogManager.error("Unable to enter input data - " + input);
			}
		}
		}
		
	
	
	public void clear(By locator) {
		boolean flag = false;
		try {
			driver.findElement(locator).clear();
			flag = true;
		} catch (Exception en) {
			en.printStackTrace();
			LogManager.error("Unable type text on text feild: " + locator);
		} finally {
			if (flag) {
				LogManager.info("Able to clear Input Data ");
			} else {
				LogManager.error("Unable to clear input data ");
			}

		}
	}

	
	public boolean isChecked(By locator, String locatorName) throws Exception {
		boolean bvalue = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isSelected()) {
				flag = true;
				bvalue = true;
			}
		} catch (Exception e) {
			bvalue = false;
			flag = false;
		} finally {
			if (flag) {
				LogManager.info("Able click on element" + locatorName);
			} else if (!flag) {
				LogManager.error("Unable click on element" + locatorName);
			}
		}
		return bvalue;

	}

	
	public String getText(By locator) {
		String content = "";
		try {
			content = driver.findElement(locator).getText();
			LogManager.info("Able to get text from webpage: " + content);
		} catch (Exception e) {
			LogManager.error("Unable to capture text from webpage" + locator);
		}
		return content;
	}
	
	
	public String getAttributeValue(By locaotor, String attributeValue) {
		String value = "";
		try {
			value = driver.findElement(locaotor).getAttribute(attributeValue);
			LogManager.info("Able to get the attribute value" + value);
		} catch (Exception e) {
			LogManager.error("Unable to get the attribute value");
		}
		return value;
	}

	
	public boolean waitForElementPresent(By by) throws Exception {
		boolean flag = false;
		try {
			for (int i = 0; i < 300; i++) {
				if (driver.findElement(by).isDisplayed()) {
					flag = true;
					return true;
				} else {
					Thread.sleep(100);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			if (!flag) {
				LogManager.info("Waiting for element (" + by + ") present");
			} else if (flag) {
				LogManager.error("Unable to find element: " + by);
			}
		}
		return flag;
	}

	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			LogManager.info("Able to find element ( " + by + ") on WebPage.");
			return true;
		} catch (Exception e) {
			LogManager.error("Unable to find element in webpage: " + by);
			return false;
		}
	}

	
	public boolean isEnabled(By element) {
		boolean flag = false;
		try {
			driver.findElement(element).isEnabled();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	
	public String getPageTitle() {
		String currTitle = "";
		try {
			currTitle = driver.getTitle();
			Assert.assertTrue(true, "Title Verified successfully");
			LogManager.info("Application tile is: " + currTitle);
		} catch (Exception e) {
			Assert.assertTrue(false, "Unable to verify the title");
			LogManager.error("Unable to get the title from Application!");
		}
		return currTitle;
	}
	
	
	public void waitForVisibilityOfElement(By locator) {
		boolean flag = false;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			if (flag) {
				LogManager.info("Able to find the element: " + locator);
			} else if (!flag) {
				LogManager.error("Unable to locate the element after waiting 1 Min 30 Secs");
			}
		}
	}
	
	
	public void selectByVisibileText(By element, String option) {
		try {
	moveAndClick(element);
			WebElement webelement = driver.findElement(element);
			Select select = new Select(webelement);
			select.selectByVisibleText(option);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	public void selectByIndex(By element, int index) {
		try {
			WebElement webelement = driver.findElement(element);
			Select select = new Select(webelement);
			select.selectByIndex(index);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void waitForPageLoad(By element) throws InterruptedException {
		for (int spin = 1; spin <= 100; spin++) {
			boolean isPresent = isElementPresent(element);
			if (isPresent) {
				Thread.sleep(1000);
			} else {
				LogManager.info("Page load has been completed!");
				break;
			}
		}
	}

	
	
	public void waitForAlertPresent() {
		try {
			wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			LogManager.info("Alert Message is displayed");
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.error("Alert message is NOT displayed!");
		}

	}

	
	public void acceptAlert() {

		driver.switchTo().alert().accept();
	}

	
	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	
	public boolean getFontWeight(By locator) {
		boolean flag = false;
		try {
			String value = driver.findElement(locator).getCssValue("font-weight");
			if (Integer.parseInt(value) >= 700) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;

	}

	
	public String getSelectedOption(By element) {
		WebElement selectElement = driver.findElement(element);
		Select select = new Select(selectElement);
		WebElement selectedOption = select.getFirstSelectedOption();
		String selectedValue = selectedOption.getText();
		return selectedValue;
	}
	
	
	public String generateRandomString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}
		
	public String generateRandomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	
	public void JSexeClick(By element) throws InterruptedException {
		if(browser.equalsIgnoreCase("chrome")){
			moveAndClick(element);
		}else{
			WebElement ele = driver.findElement(element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ele);
		}	
	}
	
	
	public void jseClick(By element) throws InterruptedException {
		WebElement ele = driver.findElement(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
}
}

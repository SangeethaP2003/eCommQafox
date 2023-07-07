package com.qafox.customer;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import com.qafox.business.Application;
import com.qafox.objectrepository.AccountPage;
import com.qafox.objectrepository.LoginPage;
import com.qafox.utils.DataProviders;
import com.qafox.utils.DateTimeUtil;


public class verifyLoginPage extends Application {
	
	SoftAssert sa = new SoftAssert();
	
	@Test( enabled=true, priority = 1)
	
	public void verifyNewCustomer() throws Exception {
		startTestCase("LoginPage.NewCustomer");
		launchApplication();
		
		waitForVisibilityOfElementLocated(LoginPage.login_bc_link);
		Assert.assertTrue(isElementPresent(LoginPage.login_bc_link)," Unable to navigate to the Login Page");
		logSuccess("Navigated to the Login Page");
		sa.assertTrue(isElementPresent(LoginPage.new_customer)," No information is displayed for new customer registration");
		Assert.assertTrue(isElementPresent(LoginPage.continue_btn)," No option for new customer registration");
		logSuccess("Option to Register New Customer is present");
		click(LoginPage.continue_btn, "Continue/ Register button");
		Assert.assertTrue(isElementPresent(LoginPage.register_bc_link), " Unable to navigate to the Registration Page");
		logSuccess("Navigated to the Registration Page");		
	
	}
	
	@Test (dataProvider = "get_data", enabled = true, priority = 0)
	
	public void verifyReturningCustomer(String email, String pwd) throws Exception {
		startTestCase("LoginPage.ReturningCustomer");
		launchApplication();
		
		waitForVisibilityOfElementLocated(LoginPage.login_bc_link);
		Assert.assertTrue(isElementPresent(LoginPage.login_bc_link)," Unable to navigate to the Login Page");
		logSuccess("Navigated to the Login Page");
		sa.assertTrue(isElementPresent(LoginPage.returning_customer),
				" No information is displayed for existing customer login");
		Assert.assertTrue(isElementPresent(LoginPage.email_address),
				" No option to enter email address");
		Assert.assertTrue(isElementPresent(LoginPage.password),
				" No option to enter password");
		Assert.assertTrue(isElementPresent(LoginPage.login_btn)," No option to login for existing customer registration");
		logSuccess("Option for existing customer login is present");
		
		login(email, pwd);
		Assert.assertTrue(isElementPresent(LoginPage.warning_msg), " Warning message is not displayed for invalid credentials ");
		logSuccess(getText(LoginPage.warning_msg)+ " - Warning message is displayed for invalid credentials");
		email = "gplayservices2505@gmail.com";		
		pwd = 	"just1234";
		login(email, pwd);
		Assert.assertFalse(isElementPresent(LoginPage.warning_msg), " Warning message is displayed for valid credentials ");
		logSuccess("Warning message is not displayed for valid credentials");
		Assert.assertTrue(isElementPresent(AccountPage.myAccount), " Account Operations are not displayed ");
		logSuccess("Account Operations are disaplyed");
		Assert.assertTrue(isElementPresent(AccountPage.myOrders), " Order details/ operations are not displayed ");
		logSuccess("Order opertions are displayed");	
		Assert.assertTrue(isElementPresent(AccountPage.logout_link), " No option to logout");
		logSuccess("Successfully logged in");
		click(AccountPage.logout_link, "Logout");
		Assert.assertTrue(isElementPresent(AccountPage.account_logout), " Account Logout information is not displayed ");
		logSuccess("Account Logout confirmation is displayed");
	}
	
	@Test (enabled = true)
	public void verifyShortcutMenus() throws Exception {
		startTestCase("LoginPage.EasyAccessMenus");
		launchApplication();
		
		waitForVisibilityOfElementLocated(LoginPage.login_bc_link);
		Assert.assertTrue(isElementPresent(LoginPage.login_bc_link)," Unable to navigate to the Login Page");
		logSuccess("Navigated to the Login Page");
		
		Assert.assertTrue(isElementPresent(LoginPage.short_menu_list), " Easy access menus are not displayed ");
		logSuccess("Easy access menus are displayed");
		
		List<WebElement> shortMenus = driver.findElements(LoginPage.short_menu_list);
		String[] menuList = {"Login", "Register", "Forgotten Password", "My Account", 
				"Address Book", "Wish List", "Order History", "Downloads", "Recurring payments", 
				"Reward Points", "Returns", "Transactions", "Newsletter" };
		logSuccess("Following easy access menus are displayed");
		for(int i=0; i<shortMenus.size(); i++) {
			Assert.assertEquals(shortMenus.get(i).getText(), menuList[i], " Easy access menu mismatch ");
			logSuccess(shortMenus.get(i).getText());
		}
		logSuccess("Verified the easy access menus");
	}
	
	@Test (enabled = true)
	
	public void sampleFailTest() throws Exception {
		
		startTestCase("LoginPage.FailTest");
		launchApplication();
		
		waitForVisibilityOfElementLocated(LoginPage.login_bc_link);
		Assert.assertTrue(isElementPresent(LoginPage.login_bc_link)," Unable to navigate to the Login Page");
		logSuccess("Navigated to the Login Page");
		
		click(LoginPage.login_btn, "Login Btn");
		
		Assert.assertTrue(isElementPresent(LoginPage.warning_msg), " Warning message is not displayed");
		String warningMsg = getText(LoginPage.warning_msg);
		String invalidMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertNotEquals(warningMsg, invalidMsg, " Improper warning message for empty credentials");
	}
	
	@DataProvider()
	public Object[][] get_data() throws Exception {
		return DataProviders.getTableArray("loginPage");
	
}
}

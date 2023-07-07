package com.qafox.objectrepository;

import org.openqa.selenium.By;

public class AccountPage {
	
	public static By myAccount = By.xpath("//h2[contains(.,'My Account')]");
	
	public static By myOrders = By.xpath("//h2[contains(.,'My Orders')]");
	
	public static By logout_link = By.xpath("//a[@class='list-group-item'][contains(text(),'Logout')]");
	
	public static By account_logout = By.xpath("//h1[contains(.,'Account Logout')]");
	
	

}

package com.qafox.objectrepository;

import org.openqa.selenium.By;

public class LoginPage {

	public static By qafox_link = By.xpath(".//a[contains(.,'Qafox.com')]");

	public static By login_bc_link = By.xpath(".//ul[@class='breadcrumb']//li//a[contains(text(),'Login')]");
	
	public static By register_bc_link = By.xpath("//ul[@class='breadcrumb']//li//a[contains(text(),'Register')]");
	
	public static By new_customer = By.xpath(".//h2[contains(text(),'New Customer')]");

	public static By continue_btn = By.xpath(".//a[@class='btn btn-primary']");

	public static By returning_customer = By.xpath(".//h2[contains(.,'Returning Customer')]");

	public static By email_address = By.id("input-email");

	public static By password = By.id("input-password");

	public static By forgot_pwd_link = By.xpath(".//div[@class='form-group']//a[contains(text(),'Forgotten Password')]");

	public static By login_btn = By.xpath(".//input[@type='submit']");
	
	public static By search_box = By.xpath(".//div[@id='search']//input");
	
	public static By search_icon = By.xpath(".//span[@class='input-group-btn']//button[@type='button']");

	public static By cart_total_items = By.id("cart-total");
	
	public static By short_menu_list = By.className("list-group-item");
	
	public static By search_bc_link = By.xpath(".//a[contains(.,'Search')]");
	
	public static By home_icon = By.xpath("//i[@class='fa fa-home']");
	
	public static By currency_dropdown = By.xpath("//span[contains(text(),'Currency')]");
	
	public static By currency_options = By.xpath("//ul[@class='dropdown-menu']");
	
	public static By phone_icon = By.xpath("//i[@class='fa fa-phone']");
	
	public static By user_icon = By.xpath("//i[@class='fa fa-user']");
	
	public static By wishlist_count = By.xpath("//span[contains(text(),'Wish List')]");
	
	public static By shopping_cart = By.xpath("//span[contains(text(),'Shopping Cart')]");
	
	public static By shoppingcart_bc_link = By.xpath("//a[contains(text(),'Shopping Cart')]");	
	
	public static By check_out = By.xpath("//span[contains(text(),'Checkout')]");
	
	public static By myaccount_login_menu = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']"
			+ "//li//a[contains(text(),'Login')]");
	
	public static By myaccount_register_menu = By.xpath("//li//a[contains(text(),'Register')]");
	
	public static By warning_msg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
}

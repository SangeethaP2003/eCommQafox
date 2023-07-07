package com.qafox.business;

import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import com.qafox.objectrepository.LoginPage;

import com.qafox.utils.ConfigManager;
import com.qafox.utils.LogManager;

import com.qafox.business.GetApplicationData;
import com.qafox.drivers.DriverManager;

import com.qafox.actionevents.CommonFunctions;

public class Application extends CommonFunctions {

	Robot robot;

	public void launchApplication() throws Exception {
		openURL(ConfigManager.getProperty("ecomURL"));
	}

	public void login(String email, String pwd) throws Exception {
		clear(LoginPage.email_address);
		clear(LoginPage.password);
		Thread.sleep(150);
		type(LoginPage.email_address, email);
		type(LoginPage.password, pwd);
		click(LoginPage.login_btn, "Login Btn");
		
	}
		public String chkFileDownload(String folder, String searchFile) throws Exception {

		//File file = new File(".\\FileDownloads\\Downloads");
		File file = new File(folder);
		File[] listFiles = new File(file.getCanonicalPath()).listFiles();
		//System.out.println("Searched file is: "+searchFile);
		String filePath = "Searched File is NOT found", fileName="";
		System.out.println(listFiles.length);
		for (int i = 0; i < listFiles.length; i++) {
			if (listFiles[i].isFile()) {
				fileName = listFiles[i].getName();
				System.out.println("Listed File is-"+fileName);
				if (fileName.startsWith(searchFile)) {
					filePath = file.getCanonicalPath() + "\\" + fileName;
					break;
				}
			}
		}
		return filePath;
	}
}

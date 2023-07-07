package com.qafox.drivers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.qafox.utils.ConfigManager;
import com.qafox.utils.LogManager;
import com.qafox.utils.TakeScreenShots;
import com.qafox.utils.zipFolder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DriverManager {


	public static ExtentReports extent;
	protected static WebDriverWait wait;
	public static String browser = null;
	public static ExtentTest logger;
	public static WebDriver driver = null;
	static String reportPath;
	public static int successCount = 0;
	public static int failCount = 0;
	public static int skippedCount = 0;
	public static boolean flag=false;
	/**
	 * @Description: This function is a driver function to open browser and
	 *               launch URL.
	 * @return : Void
	 * @throws :
	 *             InterruptedException
	 * @author RThalluru
	 * @throws IOException
	 */
	@BeforeSuite
	public void setUpDriver() throws InterruptedException, IOException {

		browser = ConfigManager.getProperty("browser");

		DOMConfigurator.configure("log4j.xml");
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".//Drivers/geckodriver.exe");
			File downloadFilepath = new File(".\\FileDownloads\\Downloads");
			String downloadPath = downloadFilepath.getCanonicalPath();
			File file = new File(downloadPath);
			for (File lstfile : file.listFiles()) {
				lstfile.delete();
			}


			File downloadFilepath1 = new File(".\\FailedTestsScreenshots");
			String downloadPath1 = downloadFilepath1.getCanonicalPath();
			File file1 = new File(downloadPath1);
			for (File lstfile1 : file1.listFiles()) {
				lstfile1.delete();
			}


			FirefoxOptions options = new FirefoxOptions();
			// Set Location to store files after downloading.
			options.addPreference("browser.download.dir", downloadFilepath.getCanonicalPath());
			options.addPreference("browser.download.folderList", 2);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;");
			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			options.addPreference("browser.download.manager.showWhenStarting", false);
			options.addPreference("pdfjs.disabled", true);
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setJavascriptEnabled(false);

			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".//Drivers/chromedriver.exe");
			File downloadFilepath = new File(".\\FileDownloads\\Downloads");
			String downloadPath = downloadFilepath.getCanonicalPath();
			File file = new File(downloadPath);
			for (File lstfile : file.listFiles()) {
				lstfile.delete();
			}

			File downloadFilepath1 = new File(".\\FailedTestsScreenshots");
			String downloadPath1 = downloadFilepath1.getCanonicalPath();
			File file1 = new File(downloadPath1);
			for (File lstfile1 : file1.listFiles()) {
				lstfile1.delete();
			}


			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadPath);
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", ".//Drivers/IEDriverServer.exe");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability("nativeEvents", false);
			ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			ieCapabilities.setCapability("disable-popup-blocking", true);
			ieCapabilities.setCapability("enablePersistentHover", true);
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(ieCapabilities);
		} else {
			driver = new HtmlUnitDriver(true);
		}



		maximizeBrowser();
		wait = new WebDriverWait(driver, 90);
		LogManager.info("Able launch " + browser);
		startReport();
	}



	public void setImplicitTimeOut() {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}


	public void logSuccess(String stepDetails) {
		logger.log(LogStatus.PASS, stepDetails);
	}


	public void startTestCase(String testName) {
		System.out.println("Executing: " + testName);
		logger = extent.startTest(testName);
	}


	public static void waitForVisibilityOfElementLocated(By element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}


	public void openURL(String url) {
		driver.get(url);
	}


	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}


	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		System.out.println("results -->" + result.getStatus());
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Failed Test Case:- " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case is failed due to error: " + result.getThrowable());
			String screenshotPath = TakeScreenShots.getScreenShot(driver, result.getName());
			System.out.println(screenshotPath);
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
			failCount++;

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped:- " + result.getName());
			skippedCount++;
		} else {
			logger.log(LogStatus.PASS, "Completed Run for:- " + result.getName());
			successCount++;
		}

		extent.endTest(logger);
	}


	public void startReport() throws UnknownHostException {
		try {
			com.qafox.utils.DateTimeUtil datetime = new com.qafox.utils.DateTimeUtil();
			reportPath = ".//AutomationReports/eCom Qafox Automation Report-" + datetime.getCurrentDateFormat("yyyyMMddHHmmss")
			+ ".html";
			extent = new ExtentReports(reportPath, true);
			extent.addSystemInfo("Host Name", datetime.getHostName())
			.addSystemInfo("OS Environment", datetime.osEnvironment())
			.addSystemInfo("Environment", ConfigManager.getProperty("ecomURL"))
			.addSystemInfo("User Name", System.getProperty("user.name"));
			File file = new File("./extent-config.xml");
			String path = file.getCanonicalPath();
			extent.loadConfig(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterSuite
	public static void tearDown() {
		try {
			driver.quit();			
			//zipFolder appZip = new zipFolder();
			//appZip.zipIt("ScreenShots.zip");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@AfterTest
	public void endReport() {

		extent.flush();
		extent.close();
	}
}

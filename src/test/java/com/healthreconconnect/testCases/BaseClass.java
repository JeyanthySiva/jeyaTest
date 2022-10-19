package com.healthreconconnect.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.healthreconconnect.pageObjects.LoginPage;
import com.healthreconconnect.utilities.ReadConfig;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();
	public String baseURL = readconfig.getApplicationURL();
	public String userName = readconfig.getUserName();
	public String password = readconfig.getPassword();
	public String DriverPath = readconfig.getChromeDriverPath();
	public static WebDriver driver;

	ExtentReports report = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("extentReport.html");

	@BeforeClass
	public void setup() {
		System.out.println("In Setup");
		System.setProperty("webdriver.chrome.driver", DriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Out Setup");
		System.out.println("In before Test");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("My Report");
		report.attachReporter(spark);
		System.out.println("Out before Test");

	}

	@AfterClass
	public void tearDown() {
		System.out.println("In Tear Down");
		report.flush();

		driver.quit();
		System.out.println("Out Tear Down");

	}

	@BeforeMethod
	public void appLogin() throws InterruptedException {
		ExtentTest test = report.createTest("Verify the Login").assignAuthor("Sumudu")
				.assignCategory("Functional Test Case").assignDevice("Windows");

		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		Thread.sleep(1000);

		lp.setPassword(password);
		Thread.sleep(1000);
		lp.clickSubmit();

		if (driver.getTitle().equals("META")) {
			AssertJUnit.assertTrue(true);

			test.pass("page title is verified " + driver.getTitle());

		} else {
			AssertJUnit.assertTrue(false);
			test.fail("page title is not verified " + driver.getTitle());
		}
		// ExtentTest test1 = extent.createTest("Verify the Login");
		// test.log(Status.PASS, "User Has Logged to META");
		// test.pass("Home page titele verified");

	}

	
}

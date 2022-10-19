package com.healthreconconnect.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;
import com.healthreconconnect.pageObjects.LoginPage;
import com.healthreconconnect.utilities.ReadConfig;

public class TC_LoginTest_001 {

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
		System.out.println("Out before Test");}
	
	@Test()
	public void LoginInvalidCredentials() throws InterruptedException {
		ExtentTest test = report.createTest("Verify error message is displayed for invalid credentials").assignAuthor("Jeyanthy")
				.assignCategory("Functional Test Case").assignDevice("Windows");

		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		Faker data=new Faker();
		
		lp.setUserName(data.internet().emailAddress());
		Thread.sleep(1000);

		lp.setPassword("123456789");
		Thread.sleep(1000);
		lp.clickSubmit();
		
		Thread.sleep(2000);
		String actualString = driver.findElement(By.xpath("(//p)[1]")).getText();
		String expectedString = "Request failed with status code 400";

		if (actualString.equals(expectedString)) {
			AssertJUnit.assertTrue(true);

			test.pass("Error message is displayed for invalid credential " + actualString);
			System.out.println("Logging Test Pass");

		} else {
			AssertJUnit.assertTrue(false);
			test.fail("Error message is not displayed for invalid credential " + actualString);
			System.out.println("Logging Test Fail");
		}
	}
	
	@Test
	public void LoginTest() throws InterruptedException {
		ExtentTest test = report.createTest("Verify the login with valid credentials").assignAuthor("Sumudu")
				.assignCategory("Functional Test Case").assignDevice("Windows");

		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		Thread.sleep(1000);

		lp.setPassword(password);
		Thread.sleep(1000);
		lp.clickSubmit();
		
		Thread.sleep(2000);
		String actualString = driver.findElement(By.xpath("//button[starts-with(text(),'Back')]")).getText();
		String expectedString = "BACK TO USER LOGIN";

		if (actualString.equals(expectedString)) {
			AssertJUnit.assertTrue(true);

			test.pass("Back to User Login Button verified " + actualString);
			System.out.println("Logging Test Pass");

		} else {
			AssertJUnit.assertTrue(false);
			test.fail("page title is not verified " + actualString);
			System.out.println("Logging Test Fail");
		}
		//ExtentTest test1 = extent.createTest("Verify the Login");
		//test.log(Status.PASS, "User Has Logged to META");
		//test.pass("Home page titele verified");

	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("In Tear Down");
		report.flush();

		driver.quit();
		System.out.println("Out Tear Down");

	}

}

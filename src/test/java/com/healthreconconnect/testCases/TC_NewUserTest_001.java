package com.healthreconconnect.testCases;

import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import com.healthreconconnect.pageObjects.NewUserPage;

public class TC_NewUserTest_001 extends BaseClass {

	@Test()
	public void NewUserTest() throws InterruptedException {
		ExtentTest test = report.createTest("Verify the creation of a new user").assignAuthor("Jeyanthy")
				.assignCategory("Functional Test Case").assignDevice("Windows");

		NewUserPage nup = new NewUserPage(driver);
		Faker data=new Faker();

		Thread.sleep(10000);

		// Click Meta Logo
		nup.clickLogoMeta();
		Thread.sleep(10000);

		// Click Users Tab
		nup.clickUsersTab();
		Thread.sleep(10000);

		// Click New User button
		nup.clickNewUsersButton();
		Thread.sleep(10000);

		// Fill in the modal data
		nup.setFirstName(data.name().firstName());
		Thread.sleep(1000);
		nup.setLastName(data.name().lastName());
		Thread.sleep(1000);
		nup.setUserName(data.internet().emailAddress());
		Thread.sleep(1000);
		nup.setPassword("123456789");
		Thread.sleep(1000);
		nup.setConfirmPassword("123456789");
		Thread.sleep(1000);
		nup.clickSubmit();
		Thread.sleep(1000);

		String expectedString = "All users";
		String actualString = driver.findElement(By.xpath("//*[@id='__next']/div/div/div[1]/div[1]/div/h6")).getText();
		
		if (actualString.equals(expectedString)) {
			AssertJUnit.assertTrue(true);

			test.pass("Table title verified " + actualString);
			System.out.println("Logging Test Pass");

		} else {
			AssertJUnit.assertTrue(false);
			test.fail("Table title is not verified " + actualString);
			System.out.println("Logging Test Fail");
		}

	}

}

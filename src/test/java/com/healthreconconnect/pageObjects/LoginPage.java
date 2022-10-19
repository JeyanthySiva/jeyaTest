package com.healthreconconnect.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);

	}

	@FindBy(xpath = "//input[@name='email']")
	WebElement txtUserName;

	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassword;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnSubmit;

	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);
	}

	public void setPassword(String pword) {
		txtPassword.sendKeys(pword);

	}

	public void clickSubmit() {
		btnSubmit.click();
	}

}

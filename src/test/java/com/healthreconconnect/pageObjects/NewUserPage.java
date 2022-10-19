package com.healthreconconnect.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewUserPage {

	WebDriver ldriver;

	public NewUserPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);

	}
	
	@FindBy(xpath = "//button[1]")
	WebElement logoMetaButton;
	
	@FindBy(xpath = "//button[contains(text(),'Users')]")
	WebElement usersTab;
	
	@FindBy(xpath = "//button[contains(text(),'New User')]")
	WebElement createNewUsersButton;
	
	@FindBy(xpath = "//*[@id='firstName']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//*[@id='lastName']")
	WebElement txtLastName;

	@FindBy(xpath = "//*[@id='username']")
	WebElement txtUserName;

	@FindBy(xpath = "//*[@id='password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//*[@id='confirmPassword']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath = "//*[contains(text(),'No')]")
	WebElement buttonRadioDisabled;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnSubmit;

	public void clickLogoMeta() {
		logoMetaButton.click();
	}
	
	public void clickUsersTab() {
		usersTab.click();
	}
	
	public void clickNewUsersButton() {
		createNewUsersButton.click();
	}
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);
	}

	public void setPassword(String pword) {
		txtPassword.sendKeys(pword);
	}
	
	public void setConfirmPassword(String cpword) {
		txtConfirmPassword.sendKeys(cpword);
	}

	public void clickSubmit() {
		btnSubmit.click();
	}

}

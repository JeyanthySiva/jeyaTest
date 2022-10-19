package com.healthreconconnect.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties prop;

	public ReadConfig() {
		File src = new File("./Configurations/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

		} catch (Exception e) {

		}

	}

	public String getApplicationURL() {

		String url = prop.getProperty("baseURL");
		return url;

	}

	public String getUserName() {
		String userName = prop.getProperty("userName");
		return userName;

	}

	public String getPassword() {
		String password = prop.getProperty("password");
		return password;

	}

	public String getChromeDriverPath() {

		String chromdriverPath = prop.getProperty("chromepath");
		return chromdriverPath;
	}

}

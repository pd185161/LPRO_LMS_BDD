/**
 * 
 */
package com.LMS.LMSAPIAutomation.Resources;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author sa185402
 *
 */
public class ReadProperties {
	
Properties prop;
	
	public ReadProperties() {
		prop = new Properties();
		try {
		File src = new File("./Configuration/EnvironmentVariables.properties");
		FileInputStream fis = new FileInputStream(src);
		prop.load(fis);
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public String getBaseURL() {
		return prop.getProperty("BaseUrl");
	}
	
	public String getServer() {
		return prop.getProperty("Server");
	}
	
	public String dB_Server() {
		return prop.getProperty("DB_Server");
	}
	
	public String dB_Name() {
		return prop.getProperty("DB_Name");
	}
	
	public String dB_UserName() {
		return prop.getProperty("DB_UserName");
	}
	
	public String dB_Password() {
		return prop.getProperty("DB_Password");
	}
	
	public String App_UserName() {
		return prop.getProperty("App_UserName");
	}
	
	public String App_Password() {
		return prop.getProperty("App_Password");
	}
	
	public String Manager_UserName() {
		return prop.getProperty("Manager_UserName");
	}
	
	public String Manager_Password() {
		return prop.getProperty("Manager_Password");
	}

	public String accessToken(){
		return prop.getProperty("Access_Token");
	}
}

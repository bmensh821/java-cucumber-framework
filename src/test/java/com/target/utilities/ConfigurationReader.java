package com.target.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

     //1- Create the Properties object (create object)
    //make it "private" so we are limiting access to the object
    //"static" is to make sure its created and loaded before everything else.
	 private static Properties properties;

	static {
		try {
			FileInputStream file = new FileInputStream("configuration.properties");
			properties = new Properties();
			properties.load(file);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load configuration.properties file!");
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	}

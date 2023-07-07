package com.qafox.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties prop = null;
	
	public static String getProperty(String str_Key) {
			return GetProperties().getProperty(str_Key);
	}

	
	private static Properties GetProperties()
	{
		if (prop == null)
		{
			prop = new Properties();
			FileInputStream fis;
			try {
				fis = new FileInputStream("config.properties");
				prop.load(fis);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return prop;
	}
}

package com.wdb007.baseservice.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Common {
	/**
	 * 获取配置文件
	 * 
	 * @param configFileName
	 * @param propertyName
	 * @return
	 * @throws IOException
	 */
	public static String getProperties(String configFileName, String propertyName) throws IOException {
		Properties prop = new Properties();
		configFileName = configFileName.contains(".properties") ? configFileName : configFileName + ".properties";
		InputStream fileStream = Object.class.getResourceAsStream("/"+configFileName);
		prop.load(fileStream);
		return prop.get(propertyName) != null ? (String) prop.get(propertyName) : "";
	}

	/**
	 * 获取默认配置文件
	 * 
	 * @param propertyName
	 * @return
	 * @throws IOException
	 */
	public static String getDefaultProperties(String propertyName) throws IOException {
		Properties prop = new Properties();
		InputStream fileStream = Object.class.getResourceAsStream("/application.properties");
		prop.load(fileStream);
		return prop.get(propertyName) != null ? (String) prop.get(propertyName) : "";
	}
}

package com.wdb007.baseservice.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class ConfigUtil 
{
	private static final String filterWords[] = new String[] { "作者:", "出版社:", "出版时间:", ":", "作　者：", "出版社：", "出版时间：","：" };
	
	 @Autowired
    private static Environment env;
	 
	 /**
	  * 获取配置信息
	  * @param configName
	  * @return
	  */
	public static String getConfigPropertity(String configName) {
		String proName=env.getProperty(configName);
		if (proName!=null&&proName!="") {
			return proName;
		}
		return null;
	}
	/**
	 * 获取年月
	 * @return
	 */
	public static String getYearMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date date=new Date();
	    return format.format(date);
	}
	/**
	 * 获取时间戳
	 * @return
	 */
	public static String getTimeStamp() {
		return Long.toString(System.currentTimeMillis()/1000);
	}
	/**
	 * 获取三位随机数
	 * @return
	 */
	public static String getRandomNumber() {
		return Integer.toString(new java.util.Random().nextInt(900)+100);
	}
	/**
	 * 移除过滤字符串
	 * 
	 * @param source
	 */
	public static String filterWords(String source) {
		for (String str : filterWords) {
			if (source.indexOf(str) > -1) {
				source = source.replace(str, "");
			}
		}
		return source;
	}
}

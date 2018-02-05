package com.wdb007.baseservice.utility;

import static org.mockito.Matchers.booleanThat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileScanner {

	/**
	 * 判断文件夹是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFolderExit(String filePath) {
		File file = new File(filePath);
		if (file.isDirectory() && file.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}
	public static File[] listDirectories(String filePath, long datetimestamp) {
		boolean isExistsDirectory = isFolderExit(filePath);
		if (isExistsDirectory) {
			File file = new File(filePath);
			File[] files = file.listFiles();
			return files;
		}
		return null;
	}
	/**
	 * 获取路径下第一级文件夹列表中符合条件的目录
	 * 
	 * @param filePath
	 * @param datetimestamp
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static File[] listDirectories(String filePath)
			throws NumberFormatException, IOException {
		Long nowTimeStamp = new Date().getTime();
		int date_range = Integer.parseInt(Common.getDefaultProperties("image-scan-interval").trim());
		System.out.println(nowTimeStamp);
		boolean isExistsDirectory = isFolderExit(filePath);
		if (isExistsDirectory) {
			File file = new File(filePath);
			File[] files = file.listFiles(item -> {
				boolean isDirectory = item.isDirectory();
				boolean isInRange = (nowTimeStamp - item.lastModified()) / (1000 * 60 * 60 * 24) <= date_range;
				return isDirectory && isInRange;
			});
			return files;
		}
		return null;
	}

	/**
	 * 获取文件夹内文件名列表
	 * 
	 * @param directoryPath
	 * @return
	 */
	public static String[] listFilePath(String directoryPath) {
		File file = new File(directoryPath);
		if (file.isDirectory() && file.list().length > 0) {
			return file.list();
		}
		return null;
	}

	/**
	 * 时间戳转换为日期
	 * 
	 * @param datetimestamp
	 * @return
	 */
	public String stampToData(long datetimestamp) {
		SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(datetimestamp);
		return dataformat.format(date);
	}

}

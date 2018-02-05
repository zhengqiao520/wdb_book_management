package com.wdb007.baseservice.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage {
	/**
	 * 下载图片 download("http://ui.51bi.com/opt/siteimg/images/fanbei0923/Mid_07.jpg",
	 * "51bi.gif","\\picture\\");
	 * 
	 * @param urlString
	 *            url地址
	 * @param filename
	 *            文件名称
	 * @param savePath
	 *            保存路径
	 * @throws Exception
	 */
	public static void downloadPicture(String urlString, String filename, String savePath){
		try {
		if (urlString.indexOf("http://") > -1 || urlString.indexOf("https://") > -1) {
			URL url = new URL(urlString);
			URLConnection con = url.openConnection();
			con.setConnectTimeout(5 * 1000);
			InputStream is = con.getInputStream();

			byte[] bs = new byte[1024];
			int len;
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			os.close();
			is.close();
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}

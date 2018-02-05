package com.wdb007.baseservice.service;

import java.text.ParseException;
import java.util.Map;
import org.jsoup.nodes.Document;
import com.wdb007.baseservice.model.customer.ExtBookInfoCustomer;

/**
 * 文档对象接口
 * 
 * @author tianzhi
 *
 */
public interface AnalyzerService {
	/**
	 * 创建文档对象
	 * 
	 * @param requestUrl
	 * @return
	 */
	Document createDocument(String requestUrl);

	/**
	 * 文轩网图书信息采集
	 * 
	 * @param isbn
	 * @return
	 * @throws InterruptedException
	 * @throws ParseException 
	 */
	ExtBookInfoCustomer syncBookFromWX(Map<String, String> map) throws InterruptedException, ParseException;
	
	/**
	 * 当当网采集图书信息
	 * @param map
	 * @return
	 * @throws InterruptedException
	 * @throws ParseException
	 */
    ExtBookInfoCustomer syncBookFromDD(Map<String,String> map) throws InterruptedException, ParseException;
}

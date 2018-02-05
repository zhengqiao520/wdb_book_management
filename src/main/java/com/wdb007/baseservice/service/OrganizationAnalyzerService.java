package com.wdb007.baseservice.service;

import java.util.List;

import org.jsoup.nodes.Document;
import java.util.Map;
import com.wdb007.baseservice.model.customer.Organization;
public interface OrganizationAnalyzerService {
   
	/**
	 * 获取所有区域板块
	 * @return
	 */
	List<String> getALLBoard();
    Document createDocument(String requestUrl);
    List<Organization> getOrganization();
    
}

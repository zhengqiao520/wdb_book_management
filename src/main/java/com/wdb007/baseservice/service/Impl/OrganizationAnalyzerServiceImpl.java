package com.wdb007.baseservice.service.Impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.*;
import org.springframework.stereotype.Service;

import com.wdb007.baseservice.model.customer.Organization;
import com.wdb007.baseservice.service.OrganizationAnalyzerService;

@Service
public class OrganizationAnalyzerServiceImpl implements OrganizationAnalyzerService {

	private static final String ORG_SH_CH70_G27761 = "http://www.dianping.com/shanghai/ch70/g27761";

	@Override
	public List<String> getALLBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document createDocument(String requestUrl) {
		Map<String, String> cookies = new HashMap<>();
		try {
			return Jsoup.connect(requestUrl).header("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
					.cookies(cookies).timeout(3000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Organization> getOrganization() {
		Document baseDocument = createDocument(ORG_SH_CH70_G27761);
		Elements elements = baseDocument.select("#J_boxList > ul>li");
		List<Organization> listOrganization = new ArrayList<>();
		for (Element el : elements) {
			try {
				Organization organization = new Organization();
				Element first_element = el.getElementsByTag("a").first();
				String shop_name = first_element.attr("title");
				String shop_url = MessageFormat.format("http:{0}", first_element.attr("href"));
				organization.setShopname(shop_name);
				organization.setShopWebSite(shop_url);
				organization.setShopWebSite(shop_url);
				
				Document shopDocument = createDocument(shop_url);
				String commonSelector = "#J_boxDetail > div >";
				if (null != shopDocument) {
					// 获取地址
					Elements addressElements = shopDocument.select(commonSelector + "div.shop-addr > span:eq(0)");
					String shop_address = addressElements != null ? addressElements.first().text().replaceAll("地址:", "")
							: null;
					organization.setAddress(shop_address);
					Elements shop_contract = shopDocument.select(commonSelector + "div.shopinfor");
					String shop_tel = shop_contract != null ? shop_contract.first().text().trim() : null;
					organization.setContractTel(shop_tel);
				}
				if (organization.getShopname() != "" && organization.getShopname() != null) {
					listOrganization.add(organization);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return listOrganization;
	}
}

package com.wdb007.baseservice.service.Impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;
import com.wdb007.baseservice.model.customer.ExtBookInfoCustomer;
import com.wdb007.baseservice.service.AnalyzerService;
import com.wdb007.baseservice.utility.ConfigUtil;

@Service
public class AnalyzerServiceImpl implements AnalyzerService {

	private static final String URL_WX = "http://search.winxuan.com/search?keyword={0}";
	private static final String URL_DD = "http://search.dangdang.com/?key={0}&act=input&category_path=01.00.00.00.00.00&type=01.00.00.00.00.00";

	@Override
	public Document createDocument(String requestUrl) {
		Map<String, String> cookies = new HashMap<>();
		// book.douban.com
		/*
		 * cookies.put("__utma",
		 * "81379588.1625906329.1478780180.1478780180.1478780180.1");
		 * cookies.put("__utmb", "81379588.1.10.1478780180"); cookies.put("__utmc",
		 * "81379588"); cookies.put("__utmz",
		 * "81379588.1478780180.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
		 * cookies.put("_pk_id.100001.3ac3",
		 * "b8e7b1931da4acd1.1478780181.1.1478780181.1478780181.");
		 * cookies.put("_pk_ses.100001.3ac3", "*"); // douban.com cookies.put("bid",
		 * "MvEsSVNL_Nc"); // read.douban.com cookies.put("_ga",
		 * "GA1.3.117318709.1478747468"); cookies.put("_pk_id.100001.a7dd",
		 * "ce6e6ea717cbd043.1478769904.1.1478769904.1478769904.");
		 * cookies.put("_pk_ref.100001.a7dd",
		 * "%5B%22%22%2C%22%22%2C1478769904%2C%22https%3A%2F%2Fbook.douban.com%2" +
		 * "Fsubject_search%3Fsearch_text%3D%25E6%258E%25A8%25E8%258D%2590%25E7%25B3%25BB%25E7%25BB%259F%25"
		 * + "E5%25AE%259E%25E8%25B7%25B5%26cat%3D1001%22%5D"); // www.douban.com
		 * cookies.put("_pk_id.100001.8cb4",
		 * "237bb6b49215ebbc.1478749116.2.1478774039.1478749120.");
		 * cookies.put("_pk_ref.100001.8cb4",
		 * "%5B%22%22%2C%22%22%2C1478773525%2C%22https%3A%2F%2Fwww.baidu." +
		 * "com%2Flink%3Furl%3DlQ4OMngm1b6fAWeomMO7xq6PNbBlxyhdnHqz9mIYN9-ycRbjZvFb1NQyQ7hqzvI46-WThP"
		 * +
		 * "6A_Qo7oTQNP-98pa%26wd%3D%26eqid%3Da24e155f0000e9610000000258244a0c%22%5D");
		 */
		try {
			return Jsoup.connect(requestUrl)
					.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)").cookies(cookies)
					.timeout(3000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ExtBookInfoCustomer syncBookFromWX(Map<String, String> map) {
		String isbn = map.get("isbn");
		String book_name = map.get("book_name");
		Map<String, String> hsBookinfo = new HashMap<String, String>();
		String searchrUrl = MessageFormat.format(URL_WX, isbn).trim();
		try {
			Document doc = createDocument(searchrUrl);
			if (doc != null) {
				Thread.sleep(1000);
				Elements selectElements = doc.select("#grid > li > ul > li > div");
				Element el = selectElements.first();
				if (el != null) {

					ExtBookInfoCustomer book = new ExtBookInfoCustomer();
					Element hrefElement = el.select("a").first();
					if (hrefElement != null) {
						book.setIsbnNo(isbn);
						book.setBookName(hrefElement.attr("title"));
						hsBookinfo.put("link", hrefElement.attr("abs:href").toString());
						Document targetDoc = createDocument(hsBookinfo.get("link"));
						if (targetDoc != null) {
							String rootPath = "#page > div.layout.grid-lt210rt990.J_Layout > div.col-main > div >";
							Thread.sleep(1000);
							// 获取图片路径
							Element imageElement = targetDoc
									.select(rootPath + "div:nth-child(1) > div > div > div.info-side").first();
							String imgSrc = imageElement.getElementsByTag("img").attr("src");
							book.setImgSrc(imgSrc);

							Element priceElement = el.select("div.price > span.price-n").first();
							String price = priceElement.text().replace("￥", "");
							book.setPrice(new BigDecimal(price));
							String selector = rootPath + "div:nth-child(3) > div > div > div.cont > ul>li";
							Elements extentElements = targetDoc.select(selector);
							for (int i = 0; i < extentElements.size(); i++) {
								Element element = extentElements.get(i);
								String orginText = element.text();
								String filterText = ConfigUtil.filterWords(orginText);
								if (orginText.indexOf("作　者：") > -1) {
									book.setAuthor(filterText);
								}
								if (orginText.indexOf("出版社：") > -1) {
									book.setPress(filterText);
								}
								if (orginText.indexOf("出版时间") > -1) {
									if (filterText.indexOf("无") > -1 || filterText == "") {
										book.setPublicationDate(null);
									} else {
										SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
										Date date = format.parse(filterText);
										book.setPublicationDate(date);
									}
								}
							}
							// 获取图书简介
							Elements contentTitle = targetDoc.getElementsByClass("unit book-introduce").select("h4");
							Element h4Element = null;
							for (Element element : contentTitle) {
								if (element.text().contains("内容简介")) {
									h4Element = element;
									break;
								}
							}
							if (null != h4Element) {
								Element contenElement = h4Element.parent().parent().nextElementSibling();
								String description = contenElement.child(0).text();
								book.setDescribe(description);
							}
							book.setCategory("T");
							book.setReadable("0");
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date = new Date();
							book.setCreateTime(date);
							book.setModifyTime(date);
						}
						if (book.getBookName() != null && book.getBookName() != "") {
							String fileName = MessageFormat.format("/book_cover/{0}/{1}", ConfigUtil.getYearMonth(),
									isbn);
							book.setImgurl(fileName + ".jpg");
						}
						return book;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ExtBookInfoCustomer syncBookFromDD(Map<String, String> map) throws InterruptedException, ParseException {
		String isbn = map.get("isbn");
		String book_name = map.get("book_name");
		Map<String, String> hsBookinfo = new HashMap<String, String>();
		String searchrUrl = MessageFormat.format(URL_DD, isbn).trim();
		Document doc = createDocument(searchrUrl);
		if (doc != null) {
			Elements selectElements = doc.select("#search_nature_rg>ul>li");
			ExtBookInfoCustomer book = new ExtBookInfoCustomer();
			for (Element el : selectElements) {
				Element hrefElement = el.select("a").first();
				if (hrefElement != null) {
					Element imageElement = el.select("li>a>img").first();
					book.setImgSrc(imageElement.attr("src"));
					book.setIsbnNo(isbn);
					book.setBookName(hrefElement.attr("title"));
					hsBookinfo.put("link", hrefElement.attr("abs:href").toString());
					Document targetDoc = createDocument(hsBookinfo.get("link"));
					// Document targetDoc=convertHtmlUnitToJsoup(hsBookinfo.get("link"));
					if (targetDoc != null) {
						Elements largePicsElements=targetDoc.select("#detailPic");
						if (largePicsElements!=null&&largePicsElements.size()>0) {
							book.setImgSrc(largePicsElements.first().attr("src"));
						}
						Element priceElement = targetDoc.select("#dd-price").first();
						String price = priceElement.text().replace("¥", "");
						book.setPrice(new BigDecimal(price));
						String selector = "body > div.product_wrapper > div.product_main.clearfix > div.show_info > div > div.sale_box_left > div.messbox_info > span";
						Elements extentElements = targetDoc.select(selector);
						for (int i = 0; i < extentElements.size(); i++) {
							Element element = extentElements.get(i);
							String orginText = element.text();
							String filterText = ConfigUtil.filterWords(orginText);
							if (orginText.indexOf("作　者：") > -1||orginText.indexOf("作者：")>-1||orginText.indexOf("作者:")>-1) {
								book.setAuthor(filterText);
							}
							if (orginText.indexOf("出版社:") > -1) {
								book.setPress(filterText);
							}
							if (orginText.indexOf("出版时间:") > -1) {
								if (filterText.indexOf("无") > -1 || filterText == "") {
									book.setPublicationDate(null);
								} else {
									SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
									String year = null;
									String month = "01";
									Date date = null;
									if (filterText.indexOf("年") > -1) {
										year = filterText.substring(0, filterText.indexOf("年"));
									}
									if (filterText.indexOf("月") > -1) {
										int startIndex = filterText.indexOf("年");
										int endIndex = filterText.indexOf("月");
										month = filterText.substring(startIndex+1, endIndex);
										String publishDate = MessageFormat.format("{0}-{1}-{2}", year, month, 01);
										date = formatDate.parse(publishDate);
									} else {
										date = formatDate.parse(new Date().toString());
									}
									book.setPublicationDate(date);
								}
							}
						}
						// 内容简介
						Element descripElement = targetDoc.select("#detail_describe > ul > li:nth-child(4)").first();
						if (null != descripElement) {
							String describe = descripElement.text();
							book.setDescribe(null);
						} else {
							Element contentElement = targetDoc.select("#content > div.descrip > span:nth-child(2)")
									.first();
							if (null != contentElement) {
								String describe = contentElement.text();
								book.setDescribe(describe);
							}
						}
						book.setCategory("T");
						book.setReadable("0");
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = new Date();
						book.setCreateTime(date);
						book.setModifyTime(date);
						if (book.getBookName() != null && book.getBookName() != "") {
							String fileName = MessageFormat.format("/book_cover/{0}/{1}", ConfigUtil.getYearMonth(),
									isbn);
							book.setImgurl(fileName + ".jpg");
						}
						return book;
					}
				}
			}
		}
		return null;
	}
}

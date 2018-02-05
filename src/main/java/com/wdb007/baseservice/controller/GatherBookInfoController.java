package com.wdb007.baseservice.controller;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.File;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mapstruct.MapMapping;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.wdb007.baseservice.model.BookInfo;
import com.wdb007.baseservice.model.GatherSourceTypeEnum;
import com.wdb007.baseservice.model.ResolveBookResult;
import com.wdb007.baseservice.model.customer.ExtBookInfoCustomer;
import com.wdb007.baseservice.service.AnalyzerService;
import com.wdb007.baseservice.service.BookInfoService;
import com.wdb007.baseservice.utility.ConfigUtil;
import com.wdb007.baseservice.utility.DownloadImage;
import com.wdb007.baseservice.utility.FileScanner;
import com.wdb007.baseservice.utility.GatherBookConfig;
import com.wdb007.baseservice.utility.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "图书信息采集", description = "从图书站点采集图书信息")
@RestController
@RequestMapping("/bookmgmt")
public class GatherBookInfoController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(GatherBookInfoController.class);
	@Autowired
	private AnalyzerService documentAnalyzer;
	@Autowired
	private GatherBookConfig gatherBookConfig;
	@Autowired
	private BookInfoService bookInfoService;

	/**
	 * 抓取图书信息
	 * 
	 * @param isbnMapList
	 *            [ { "isbn": "9787806795095", "book_name": "活了100万次的猫（精装）" }]
	 * @param GatherSourceTypeEnum
	 *            采集数据源
	 * @return
	 */
	@ApiOperation(value = "根据参数中提供isbn从图书站点采集图书信息", notes = "图书信息采集")
	@RequestMapping(value = "/resolveBookInfo", method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "body", name = "isbnMapList", dataType = "Model", value = "图书isbn，图书名键值对", required = true),
			@ApiImplicitParam(paramType = "query", name = "gatherSourceType", dataType = "String", value = "采集数据源", required = true) })
	@ResponseBody
	public BaseResponse<Object> resolveBookInfo(@RequestBody List<Map<String, String>> isbnMapList,
			GatherSourceTypeEnum gatherSourceType) {
		ResolveBookResult resolveBookResult = new ResolveBookResult();
		BaseResponse<Object> baseResponse = success();
		resolveBookResult = getResolveBookResult(isbnMapList, gatherSourceType);
		logger.info("采集成功-:{}", resolveBookResult.successList.toString());
		logger.info("已存在跳过采集-:{}", resolveBookResult.dbExistsList.toString());
		logger.info("采集失败-:{}", resolveBookResult.failList.toString());
		baseResponse.setDetail(resolveBookResult);
		return baseResponse;
	}

	/**
	 * 
	 * @param 根据isbn抓取图片信息
	 * @return
	 */
	@ApiOperation(value="根据isbn从站点采集图书封面图片")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "body", name = "isbnList", dataType = "array", value = "图书isbn列表", required = true),
	})
	@RequestMapping(value = "/gatherImagesByISBN", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<Object> gatherImagesByISBN(@RequestBody List<String> isbnList) {
		BaseResponse<Object> baseResponse = success();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (String isbn : isbnList) {
					Map<String, String> map = new HashMap<>();
					map.put("isbn", isbn);
					map.put("book_name", "");
					try {
						ExtBookInfoCustomer extBookInfo = documentAnalyzer.syncBookFromDD(map);
						if (extBookInfo == null) {
							extBookInfo = documentAnalyzer.syncBookFromWX(map);
							if (extBookInfo == null) {
								continue;
							}
						}
						if (extBookInfo.getImgSrc() != "" && extBookInfo.getImgSrc() != null) {
							String filename = isbn + ".jpg";
							String dbFileName = MessageFormat.format(gatherBookConfig.book_coverpath,
									ConfigUtil.getYearMonth(), filename);
							String fsFilePath = MessageFormat.format(gatherBookConfig.image_path,
									ConfigUtil.getYearMonth());
							extBookInfo.setImgurl(dbFileName);
							DownloadImage.downloadPicture(extBookInfo.getImgSrc(), filename, fsFilePath);
						}
					} catch (InterruptedException | ParseException e) {
						logger.error("抓取{}图片异常,异常信信息{}", isbn, e);
					}
				}
			}
		}).start();
		return baseResponse;
	}

	/**
	 * 采集数据库已建档图书isbn信息（因标签建档首先要验证图书信息，暂不启用）
	 * 
	 * @return
	 */
	@ApiOperation(value = "根据图书标签表ISBN从图书站点采集图书信息", notes = "图书信息采集")
	@RequestMapping(value = "/resolveBookInfoByISBN", method = RequestMethod.GET)
	public BaseResponse<Object> resolveBookInfo() {
		BaseResponse<Object> baseResponse = success();
		List<String> listIsbn = bookInfoService.getBookMappingIsbnList();
		ResolveBookResult resolveBookResult = new ResolveBookResult();
		resolveBookResult.dbExistsList = new ArrayList<String>();
		resolveBookResult.failList = new ArrayList<String>();
		resolveBookResult.successList = new ArrayList<String>();
		if (listIsbn.size() > 0) {
			for (String isbn : listIsbn) {
				Map<String, String> map = new HashMap<>();
				map.put("isbn", isbn);
				map.put("book_name", "");
				String book_name = map.get("book_name");
				try {
					ExtBookInfoCustomer extBookInfo = documentAnalyzer.syncBookFromWX(map);
					if (extBookInfo == null) {
						extBookInfo = documentAnalyzer.syncBookFromDD(map);
						if (extBookInfo == null) {
							continue;
						}
					}
					if (extBookInfo != null && extBookInfo.getBookName() != null && extBookInfo.getBookName() != "") {
						if (extBookInfo.getImgSrc() != "" && extBookInfo.getImgSrc() != null) {
							String filename = isbn + ".jpg";
							String dbFileName = MessageFormat.format(gatherBookConfig.book_coverpath,
									ConfigUtil.getYearMonth(), filename);
							String fsFilePath = MessageFormat.format(gatherBookConfig.image_path,
									ConfigUtil.getYearMonth());
							extBookInfo.setImgurl(dbFileName);
							BookInfo bookInfo = bookInfoService.selectBookInfoByIsbnNO(isbn);
							if (null == bookInfo) {
								ModelMapper modelMapper = new ModelMapper();
								BookInfo mapBookInfo = modelMapper.map(extBookInfo, BookInfo.class);
								if (book_name != null && book_name != "" && book_name.length() > 0) {
									mapBookInfo.setBookName(book_name);
								}
								mapBookInfo.setCreateTime(new Date());
								Integer res = bookInfoService.insertBookInfo(mapBookInfo);
								if (res > 0) {
									resolveBookResult.successList.add(isbn);
									DownloadImage.downloadPicture(extBookInfo.getImgSrc(), filename, fsFilePath);
								}
							} else {
								resolveBookResult.dbExistsList.add(isbn);
							}
						}
					} else {
						resolveBookResult.failList.add(isbn);
					}
				} catch (Exception e) {
					logger.error("解析錯誤" + e.getMessage());
					resolveBookResult.failList.add(isbn);
				}
			}
		}

		baseResponse.setDetail(resolveBookResult);
		return baseResponse;
	}

	private ResolveBookResult getResolveBookResult(List<Map<String, String>> isbnMapList,
			GatherSourceTypeEnum gatherSourceType) {
		ResolveBookResult resolveBookResult = new ResolveBookResult();
		if (isbnMapList.size() > 0) {
			resolveBookResult.dbExistsList = new ArrayList<String>();
			resolveBookResult.failList = new ArrayList<String>();
			resolveBookResult.successList = new ArrayList<String>();
			isbnMapList.forEach(map -> {
				String isbn = map.get("isbn");
				String book_name = map.get("book_name");
				try {
					ExtBookInfoCustomer extBookInfo = new ExtBookInfoCustomer();
					if (gatherSourceType.getSourceName().equals("文轩网")) {
						extBookInfo = documentAnalyzer.syncBookFromWX(map);
					} else if (gatherSourceType.getSourceName().equals("当当网")) {
						extBookInfo = documentAnalyzer.syncBookFromDD(map);
					}
					if (extBookInfo != null && extBookInfo.getBookName() != null && extBookInfo.getBookName() != "") {
						if (extBookInfo.getImgSrc() != "" && extBookInfo.getImgSrc() != null) {
							String filename = isbn + ".jpg";
							String dbFileName = MessageFormat.format(gatherBookConfig.book_coverpath,
									ConfigUtil.getYearMonth(), filename);
							String fsFilePath = MessageFormat.format(gatherBookConfig.image_path,
									ConfigUtil.getYearMonth());
							extBookInfo.setImgurl(dbFileName);
							BookInfo bookInfo = bookInfoService.selectBookInfoByIsbnNO(isbn);
							if (null == bookInfo) {
								ModelMapper modelMapper = new ModelMapper();
								BookInfo mapBookInfo = modelMapper.map(extBookInfo, BookInfo.class);
								if (book_name != null && book_name != "" && book_name.length() > 0) {
									mapBookInfo.setBookName(book_name);
								}
								Integer res = bookInfoService.insertBookInfo(mapBookInfo);
								if (res > 0) {
									resolveBookResult.successList.add(isbn);
									DownloadImage.downloadPicture(extBookInfo.getImgSrc(), filename, fsFilePath);
								}
							} else {
								resolveBookResult.dbExistsList.add(isbn);
							}
						}
					} else {
						resolveBookResult.failList.add(isbn);
					}
				} catch (Exception e) {
					logger.error("解析錯誤" + e.getMessage());
					resolveBookResult.failList.add(isbn);
				}
			});
		}
		return resolveBookResult;
	}

	/**
	 * 测试
	 * 
	 * @param filePath
	 * @return
	 */
	@ApiIgnore
	@RequestMapping(value = "/testFiles", method = RequestMethod.GET)
	public File[] testFileList(@RequestParam(name = "filePath", value = "filePath") String filePath) {
		File[] files = FileScanner.listDirectories(filePath, 0);
		List<String> listBooks = new ArrayList<>();
		for (File file : files) {
			String[] filePathList = FileScanner.listFilePath(file.getPath());
			if (filePathList != null && filePathList.length > 0) {

			}
		}
		return null;
	}
}

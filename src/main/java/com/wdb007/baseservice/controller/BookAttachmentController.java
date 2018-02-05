package com.wdb007.baseservice.controller;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.javascript.host.html.Image;
import com.wdb007.baseservice.model.BookImageAttach;
import com.wdb007.baseservice.model.BookInfo;
import com.wdb007.baseservice.service.BookImageAttachService;
import com.wdb007.baseservice.service.BookInfoService;
import com.wdb007.baseservice.service.BookRfidIsbnMappingService;
import com.wdb007.baseservice.utility.FileScanner;
import com.wdb007.baseservice.utility.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
@Api(value = "图书图片附件处理", description = "图书图片附件处理")
@RestController
@RequestMapping(value = "/book_attachment")
public class BookAttachmentController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(BookAttachmentController.class);
	@Autowired
	public BookImageAttachService bookImageAttachService;

	@Autowired
	public BookRfidIsbnMappingService bookRfidIsbnMappingService;

	@Autowired
	public BookInfoService bookInfoService;

	/**
	 * 根据传入图片目录路径扫描图书图片生成路径
	 * 
	 * @param filePath
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	@ApiOperation(value = "扫描路径内所有图片信息到图片附件表", notes = "扫描路径内所有图片信息到图片附件表")
	@RequestMapping(value = "/scanInitImageAttach", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "filePath", dataType = "string", value = "图片目录路径", required = true),
	})
	public JSONObject scanInitImageAttach(@RequestParam(name = "filePath", value = "filePath") String filePath)
			throws NumberFormatException, IOException {
		return initImageAttach(filePath);
	}

	/**
	 * 更新图书封面
	 * @param filePath
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	@ApiOperation(value = "扫描路径内所有图片封面信息到图书信息", notes = "扫描路径内所有图片信息到图片附件表")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "filePath", dataType = "string", value = "图片目录路径", required = true),
	})
	@RequestMapping(value = "setBookCoverFromFiles", method = RequestMethod.GET)
	public BaseResponse<Object> setBookCoverFromFiles(String filePath) throws NumberFormatException, IOException {
		long startTimeStamp = new Date().getTime();
		BaseResponse<Object> baseResponse=success();
		List<JSONObject> bookObjectList=new ArrayList<JSONObject>();
		File[] files = FileScanner.listDirectories(filePath);
		if(files.length==0) {
			baseResponse.setMessage("目录路径层级错误！");
			baseResponse.setDetail(bookObjectList);
			return baseResponse;
		}
		Arrays.asList(files).forEach(imageFile->{
			List<String> fileList = getFilePathList(imageFile.getPath());
			if (fileList.size() > 0 && null != fileList) {
				for (String file : fileList) {
					String isbn=file.split("\\.")[0];
					String newIsbn=isbn.replace("0_","").replace("1_","");
					BookInfo bookInfo = bookInfoService.selectBookInfoByIsbnNO(newIsbn);
					if (bookInfo != null) {
						JSONObject bookObject=new JSONObject();
						String img_url = MessageFormat.format("/book_cover/{0}/{1}", imageFile.getName(),file);
						bookInfo.setImgurl(img_url);
						bookInfo.setModifyTime(new Date());
						bookInfoService.updateBookInfoByPrimaryKeySelective(bookInfo);
						bookObject.put("isbn",bookInfo.getIsbnNo());
						bookObject.put("book_name",bookInfo.getBookName());
						bookObjectList.add(bookObject);
						logger.info("更新{}成功！",file);
					}
				}
			}
		});
		try {

		} catch (Exception e) {
			logger.info("更新图书封面信息异常:{}",e.getMessage());
			baseResponse=failed(e);
		}
		long endTimeStamp = new Date().getTime();
		String completeMsg=MessageFormat.format("更新图书封面完毕，耗时{0}",endTimeStamp-startTimeStamp);
		baseResponse.setMessage(completeMsg);
		baseResponse.setDetail(bookObjectList);
		logger.info(completeMsg);
		return baseResponse;
	}

	/**
	 * 定时根据传入图片目录路径扫描图书图片生成路径
	 * 
	 * @param filePath
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	@ApiOperation(value = "扫描路径内所有图片信息到图片附件表", notes = "扫描路径内所有图片信息到图片附件表")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "filePath", dataType = "string", value = "图片目录路径", required = true),
	})
	@RequestMapping(value = "/scanInitImageAttachTiming", method = RequestMethod.GET)
	public JSONObject initImageAttach(String filePath) throws NumberFormatException, IOException {
		long startTimeStamp = new Date().getTime();
		JSONObject resultJson = new JSONObject();
		File[] files = FileScanner.listDirectories(filePath);
		List<String> notfitList = new ArrayList<>();
		resultJson.put("scan_count", files.length);
		try {
			for (File file : files) {
				BookImageAttach bookImageAttach = new BookImageAttach();
				String full_isbn = file.getName();
				String prefix_1 = "1_";
				String prefix_0 = "0_";
				int starIndex = full_isbn.indexOf("_");
				int endIndex = full_isbn.lastIndexOf("_");

				if (!full_isbn.startsWith(prefix_1) && !full_isbn.startsWith(prefix_0)) {
					notfitList.add(full_isbn);
					logger.error("扫描目录{}不符合规则！", full_isbn);
					continue;
				}
				String isbn_type = full_isbn.startsWith(prefix_1) ? "1" : (full_isbn.startsWith(prefix_0) ? "0" : "-1");
				if (isbn_type.equals("-1")) {
					notfitList.add(full_isbn);
					logger.error("扫描目录{}不符合规则！", full_isbn);
					continue;
				}
				boolean isSingleBook = isbn_type.equals("0");

				String bookSeq = isSingleBook ? "1" : full_isbn.substring(full_isbn.lastIndexOf("_") + 1);
				/*
				 * String isbn = isbn_type.equals("0") ? full_isbn.replace("0_", "") :
				 * full_isbn.substring(starIndex + 1, endIndex);
				 */
				String isbn = full_isbn.substring(full_isbn.indexOf("_") + 1);
				bookImageAttach.setIsbn(isbn);
				List<String> fileList = getFilePathList(file.getPath());
				for (String item : fileList) {
					String img_url = MessageFormat.format("/book_imgs/{0}/{1}", file.getName(), item);
					bookImageAttach.setImage_url(img_url);
					// 查询isbn是否建档初始化
					/*
					 * BookRfidIsbnMapping bookRfidIsbnMapping =
					 * bookRfidIsbnMappingService.getMappingByIsbnAndSeq(isbn, isSingleBook ? "1" :
					 * bookSeq); if (bookRfidIsbnMapping != null) {
					 */
					// 查询图片附件是否存在记录
					List<BookImageAttach> bookAttach = bookImageAttachService.selectByImageUrl(img_url, isbn);
					if (bookAttach != null) {
						continue;
					} else {
						/*
						 * bookImageAttach.setIsbn(bookRfidIsbnMapping.getIsbn());
						 * bookImageAttach.setRfid_tag_id(bookRfidIsbnMapping.getRfid_tag_id());
						 */
						bookImageAttach.setCreate_time(new Date());
						/* 封面 */
						if (item.contains("front3d")) {
							bookImageAttach.setImage_type(1);
						}
						/* 内页顶部 */
						else if (item.contains("front_")) {
							bookImageAttach.setImage_type(2);
						}
						/* 内页详情 */
						else if (item.contains("inner_")) {
							bookImageAttach.setImage_type(3);
						} else {
							bookImageAttach.setImage_type(4);
						}
						bookImageAttachService.insertBookImageAttach(bookImageAttach);
					}
					/* } */
				}
			}
		} catch (Exception ee) {
			resultJson.put("scan_msg", ee.getMessage());
			logger.error("异常信息:" + ee.getMessage());
		}
		resultJson.put("notfit_Items", notfitList);
		long endTimeStamp = new Date().getTime();
		logger.info("图片附件同步结束,耗时{}秒", Math.round((endTimeStamp - startTimeStamp) / 1000));
		return resultJson;
	}

	
	// 获取文件夹内文件
	private List<String> getFilePathList(String directoryPath) {
		String[] filePathList = FileScanner.listFilePath(directoryPath);
		if (filePathList != null && filePathList.length > 0) {
			return Arrays.asList(filePathList);
		}
		return null;
	}
}

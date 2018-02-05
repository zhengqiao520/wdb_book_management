package com.wdb007.baseservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wdb007.baseservice.model.customer.BookInfoDetails;
import com.wdb007.baseservice.service.BookImageAttachService;
import com.wdb007.baseservice.service.BookInfoService;
import com.wdb007.baseservice.utility.response.BaseResponse;

@RestController
@RequestMapping(name="图书基本信息",value="/book_info")
public class BookInfoController extends BaseController {

	@Autowired
	public BookImageAttachService  bookImageAttachService;
	
	@Autowired
	public BookInfoService bookInfoService;
	
	/**
	 * 根据isbn或rfid标签获取建档图书基础信息
	 * @param isbn
	 * @param rfid
	 * @return
	 */
	@RequestMapping(value="/getBookInfoList",method=RequestMethod.GET)
	public List<BookInfoDetails> getBookDetailsByIsbnOrRFID(@RequestParam(name = "isbn", value = "isbn",required=false) String isbn,@RequestParam(name = "rfid", value = "rfid",required=false)String rfid){
		List<BookInfoDetails> listBookInfoDetails=bookImageAttachService.selectBookInfoDetailsByIsbnOrTag(isbn, rfid);
		return listBookInfoDetails;
	}
	
	/**
	 * 获取单本图书基本信息
	 * @param isbn
	 * @return
	 */
	@RequestMapping(value="getSingleBookInfo",method=RequestMethod.GET)
	public  BaseResponse<Object>  GetBookInfo(@RequestParam(name="isbn",required=true) String isbn){
		BaseResponse<Object> baseResponse = success();
		try {
			baseResponse.setDetail(bookInfoService.selectBookInfoByIsbnNO(isbn));
		}
		catch (Exception e) {
			baseResponse=failed(e);
		}
		return baseResponse;
	}
} 

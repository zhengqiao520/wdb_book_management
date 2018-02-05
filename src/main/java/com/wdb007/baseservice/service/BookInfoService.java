package com.wdb007.baseservice.service;

import java.util.List;

import com.wdb007.baseservice.model.BookInfo;

public interface BookInfoService {
	int insertBookInfo(BookInfo record);
	BookInfo selectBookInfoByIsbnNO(String isbnNo);
	int updateBookInfoByPrimaryKeySelective(BookInfo record);
	List<String> getBookMappingIsbnList();
}

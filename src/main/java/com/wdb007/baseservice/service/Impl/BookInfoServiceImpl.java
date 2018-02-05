package com.wdb007.baseservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wdb007.baseservice.service.*;
import com.wdb007.baseservice.mapper.BookInfoMapper;
import com.wdb007.baseservice.model.BookInfo;
@Service
public class BookInfoServiceImpl implements BookInfoService {
 
	@Autowired
	BookInfoMapper bookInfoMapper;

	@Override
	public int insertBookInfo(BookInfo record) {
		return  bookInfoMapper.insertBookInfo(record);
	}

	@Override
	public BookInfo selectBookInfoByIsbnNO(String isbnNo) {
		return bookInfoMapper.selectBookInfoByIsbnNO(isbnNo);
	}

	@Override
	public int updateBookInfoByPrimaryKeySelective(BookInfo record) {
		return bookInfoMapper.updateBookInfoByPrimaryKeySelective(record);
	}

	@Override
	public List<String> getBookMappingIsbnList() {
		return bookInfoMapper.getBookMappingIsbnList();
	}
	
}

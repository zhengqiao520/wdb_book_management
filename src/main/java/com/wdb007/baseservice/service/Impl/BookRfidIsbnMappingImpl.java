package com.wdb007.baseservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdb007.baseservice.mapper.BookRfidIsbnMappingMapper;
import com.wdb007.baseservice.model.BookRfidIsbnMapping;
import com.wdb007.baseservice.service.BookRfidIsbnMappingService;

@Service
public class BookRfidIsbnMappingImpl implements BookRfidIsbnMappingService {

	@Autowired
	public BookRfidIsbnMappingMapper bookRfidIsbnMappingMapper;
	
	@Override
	public BookRfidIsbnMapping getBookRfidIsbnMappingById(Long id) {
		return bookRfidIsbnMappingMapper.selectByPrimaryKey(id);
	}


	@Override
	public BookRfidIsbnMapping getMappingByIsbnAndSeq(String isbn,String isbn_seq) {
		 return bookRfidIsbnMappingMapper.selectIsbnMappingByIsbnAndSeq(isbn,isbn_seq);
	}

}

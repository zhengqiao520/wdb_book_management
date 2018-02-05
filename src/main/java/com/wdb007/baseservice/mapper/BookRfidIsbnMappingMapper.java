package com.wdb007.baseservice.mapper;

import java.util.Map;

import com.wdb007.baseservice.model.BookRfidIsbnMapping;

public interface BookRfidIsbnMappingMapper {

	int deleteByPrimaryKey(Long id);

	int insert(BookRfidIsbnMapping record);

	int insertSelective(BookRfidIsbnMapping record);

	BookRfidIsbnMapping selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(BookRfidIsbnMapping record);

	int updateByPrimaryKey(BookRfidIsbnMapping record);

	BookRfidIsbnMapping selectIsbnMappingByIsbnAndSeq(String isbn,String isbn_seq);
}
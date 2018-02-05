package com.wdb007.baseservice.mapper;

import com.wdb007.baseservice.model.BookInitUser;

public interface BookInitUserMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(BookInitUser record);

	int insertSelective(BookInitUser record);

	BookInitUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(BookInitUser record);

	int updateByPrimaryKey(BookInitUser record);

}
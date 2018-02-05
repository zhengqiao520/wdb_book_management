package com.wdb007.baseservice.mapper;

import com.wdb007.baseservice.model.BookInitMapping;

public interface BookInitMappingMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(BookInitMapping record);

    int insertSelective(BookInitMapping record);

    BookInitMapping selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(BookInitMapping record);

    int updateByPrimaryKey(BookInitMapping record);
}
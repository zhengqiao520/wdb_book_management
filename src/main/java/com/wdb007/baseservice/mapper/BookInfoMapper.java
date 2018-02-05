package com.wdb007.baseservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wdb007.baseservice.model.*;

public interface BookInfoMapper {

	int updateBookInfoByPrimaryKeySelective(BookInfo record);
	
	int insertBookInfo(BookInfo record);
	
	BookInfo selectBookInfoByIsbnNO(String isbnNo);
	
    @Select(value = { "select distinct isbn from book_init_mapping \r\n" + 
	"	where not exists(select 1 from book_info where book_init_mapping.isbn=book_info.isbn_no)" })
    List<String> getBookMappingIsbnList();
}



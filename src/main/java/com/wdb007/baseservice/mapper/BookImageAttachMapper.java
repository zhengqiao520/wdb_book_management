package com.wdb007.baseservice.mapper;

import java.util.List;

import com.wdb007.baseservice.model.BookImageAttach;
import com.wdb007.baseservice.model.customer.BookInfoDetails;

public interface BookImageAttachMapper {

	int deleteByPrimaryKey(Long id);

	int insert(BookImageAttach record);

	int insertSelective(BookImageAttach record);

	BookImageAttach selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(BookImageAttach record);

	int updateByPrimaryKeyWithBLOBs(BookImageAttach record);

	int updateByPrimaryKey(BookImageAttach record);

	List<BookImageAttach> selectByImageUrl(String img_url,String isbn);
    
    List<BookInfoDetails>  selectBookInfoDetailsByIsbnOrTag(String isbn,String rfid);
}
package com.wdb007.baseservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wdb007.baseservice.mapper.BookImageAttachMapper;
import com.wdb007.baseservice.model.BookImageAttach;
import com.wdb007.baseservice.model.customer.BookInfoDetails;
import com.wdb007.baseservice.service.BookImageAttachService;

@Service
public  class BookImageAttachServiceImpl implements BookImageAttachService {

	@Autowired
	public BookImageAttachMapper bookImageAttachMapper;

	@Override
	public int insertBookImageAttach(BookImageAttach record) {
		return bookImageAttachMapper.insert(record);
	}

	@Override
	public BookImageAttach getBookImageAttach(Long id) {
		return bookImageAttachMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BookImageAttach> selectByImageUrl(String img_url, String isbn) {
		List<BookImageAttach> listBookImageAttach=bookImageAttachMapper.selectByImageUrl(img_url, isbn);
		if (listBookImageAttach!=null&&listBookImageAttach.size()>0) {
			return listBookImageAttach;
		}
		return null;
	}

	@Override
	public List<BookInfoDetails> selectBookInfoDetailsByIsbnOrTag(String isbn, String rfid) {
		
		return bookImageAttachMapper.selectBookInfoDetailsByIsbnOrTag(isbn, rfid);
	}
}

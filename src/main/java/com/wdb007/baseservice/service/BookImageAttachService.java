package com.wdb007.baseservice.service;

import java.util.List;

import com.wdb007.baseservice.model.BookImageAttach;
import com.wdb007.baseservice.model.customer.BookInfoDetails;

public interface BookImageAttachService {
	int insertBookImageAttach(BookImageAttach record);

	BookImageAttach getBookImageAttach(Long id);

	List<BookImageAttach> selectByImageUrl(String img_url, String isbn);

	List<BookInfoDetails> selectBookInfoDetailsByIsbnOrTag(String isbn, String rfid);
}

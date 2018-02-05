package com.wdb007.baseservice.service;


import com.wdb007.baseservice.model.BookRfidIsbnMapping;

public interface BookRfidIsbnMappingService {
	BookRfidIsbnMapping getBookRfidIsbnMappingById(Long id);
	BookRfidIsbnMapping getMappingByIsbnAndSeq(String isbn,String isbn_seq);
}

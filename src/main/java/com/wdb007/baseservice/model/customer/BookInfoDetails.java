package com.wdb007.baseservice.model.customer;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wdb007.baseservice.controller.BookAttachmentController;
import com.wdb007.baseservice.model.BookImageAttach;

public class BookInfoDetails extends BookImageAttach{

	@JsonProperty("isbn_no")
	private String isbnNo;
	@JsonProperty("book_name")
	private String bookName;
	@JsonProperty("author")
	private String author;
	private String press;
	@JsonProperty("publication_date")
	private Date publicationDate;
	@JsonProperty("category")
	private String category;
	private BigDecimal price;
	private String readable;
	private String imgurl;
	private String describe;

	public String getIsbnNo() {
		return isbnNo;
	}

	public void setIsbnNo(String isbnNo) {
		this.isbnNo = isbnNo == null ? null : isbnNo.trim();
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName == null ? null : bookName.trim();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press == null ? null : press.trim();
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getReadable() {
		return readable;
	}

	public void setReadable(String readable) {
		this.readable = readable == null ? null : readable.trim();
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl == null ? null : imgurl.trim();
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe == null ? null : describe.trim();
	}
}

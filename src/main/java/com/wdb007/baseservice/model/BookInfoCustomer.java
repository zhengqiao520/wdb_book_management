package com.wdb007.baseservice.model;

import java.math.BigDecimal;
import java.util.Date;

public class BookInfoCustomer {

    private String isbnNo;


    private String bookName;


    private String author;


    private String press;


    private Date publicationDate;

 
    private String category;


    private BigDecimal price;


    private String readable;


    private String imgurl;


    private Date createTime;


    private Date modifyTime;


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


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }
}


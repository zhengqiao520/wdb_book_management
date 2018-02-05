package com.wdb007.baseservice.model;

import java.util.Date;

public class BookInitMapping {
    private Long ID;

    private String tag_id;

    private String isbn;

    private Integer status;

    private String account;

    private Integer tag_type;

    private Date create_time;

    private Date gather_time;

    private Date filing_time;

    private Integer isbn_type;

    private Integer isbn_sequence;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id == null ? null : tag_id.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getTag_type() {
        return tag_type;
    }

    public void setTag_type(Integer tag_type) {
        this.tag_type = tag_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getGather_time() {
        return gather_time;
    }

    public void setGather_time(Date gather_time) {
        this.gather_time = gather_time;
    }

    public Date getFiling_time() {
        return filing_time;
    }

    public void setFiling_time(Date filing_time) {
        this.filing_time = filing_time;
    }

    public Integer getIsbn_type() {
        return isbn_type;
    }

    public void setIsbn_type(Integer isbn_type) {
        this.isbn_type = isbn_type;
    }

    public Integer getIsbn_sequence() {
        return isbn_sequence;
    }

    public void setIsbn_sequence(Integer isbn_sequence) {
        this.isbn_sequence = isbn_sequence;
    }
}
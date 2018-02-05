package com.wdb007.baseservice.model;

public class BookRfidIsbnMapping {

	private Long id;
	private String isbn;
	private String rfid_tag_id;
	private Integer isbn_sequence;
	private Boolean isbn_type;
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn == null ? null : isbn.trim();
	}

	public String getRfid_tag_id() {
		return rfid_tag_id;
	}

	public void setRfid_tag_id(String rfid_tag_id) {
		this.rfid_tag_id = rfid_tag_id == null ? null : rfid_tag_id.trim();
	}

	public Integer getIsbn_sequence() {
		return isbn_sequence;
	}

	public void setIsbn_sequence(Integer isbn_sequence) {
		this.isbn_sequence = isbn_sequence;
	}

	public Boolean getIsbn_type() {
		return isbn_type;
	}

	public void setIsbn_type(Boolean isbn_type) {
		this.isbn_type = isbn_type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
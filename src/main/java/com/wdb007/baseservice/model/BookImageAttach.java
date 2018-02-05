package com.wdb007.baseservice.model;

import java.util.Date;

public class BookImageAttach {

    private Long id;

	private String rfid_tag_id;

	private String isbn;

	private Integer image_type;

	private Integer image_area;

	private String image_url;

	private String image_href;

	private Byte display_index;

	private Date create_time;

	private Date update_time;

	private String description;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRfid_tag_id() {
		return rfid_tag_id;
	}


	public void setRfid_tag_id(String rfid_tag_id) {
		this.rfid_tag_id = rfid_tag_id == null ? null : rfid_tag_id.trim();
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn == null ? null : isbn.trim();
	}


	public Integer getImage_type() {
		return image_type;
	}


	public void setImage_type(Integer image_type) {
		this.image_type = image_type;
	}


	public Integer getImage_area() {
		return image_area;
	}


	public void setImage_area(Integer image_area) {
		this.image_area = image_area;
	}


	public String getImage_url() {
		return image_url;
	}


	public void setImage_url(String image_url) {
		this.image_url = image_url == null ? null : image_url.trim();
	}


	public String getImage_href() {
		return image_href;
	}


	public void setImage_href(String image_href) {
		this.image_href = image_href == null ? null : image_href.trim();
	}


	public Byte getDisplay_index() {
		return display_index;
	}


	public void setDisplay_index(Byte display_index) {
		this.display_index = display_index;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public Date getUpdate_time() {
		return update_time;
	}


	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}
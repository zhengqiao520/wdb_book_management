package com.wdb007.baseservice.model.customer;

/**
 * 大众点评机构信息
 * 
 * @author tianzhi
 *
 */
public class Organization {

	/**
	 * 所在区域
	 */
	private String area;
	/**
	 * 商家地址
	 */
	private String address;
	/**
	 * 商家400电话
	 */
	private String contact400;
	/**
	 * 联系方式
	 */
	private String contractTel;
	/**
	 * 工作时间
	 */
	private String workTime;
	/**
	 * 商家名称
	 */
	private String shopname;
	
	private String shopWebSite;

	public String getShopWebSite() {
		return shopWebSite;
	}

	public void setShopWebSite(String shopWebSite) {
		this.shopWebSite = shopWebSite;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact400() {
		return contact400;
	}

	public void setContact400(String contact400) {
		this.contact400 = contact400;
	}

	public String getContractTel() {
		return contractTel;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
}

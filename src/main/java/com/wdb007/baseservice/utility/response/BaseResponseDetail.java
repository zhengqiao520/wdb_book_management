package com.wdb007.baseservice.utility.response;

/**
 * 
 * @ClassName: BaseResponseDetail.java
 * @Description:请求结果详情返回
 * @Time 2017年9月21日
 * @author: Tom
 */
public class BaseResponseDetail {
	private int detail_code;
	private String message;
	
	public int getDetail_code() {
		return detail_code;
	}
	public void setDetail_code(int detail_code) {
		this.detail_code = detail_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

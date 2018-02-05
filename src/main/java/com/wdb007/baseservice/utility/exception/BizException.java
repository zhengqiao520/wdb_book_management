package com.wdb007.baseservice.utility.exception;

import com.wdb007.baseservice.utility.constant.RspCodeEnum;

public class BizException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4127913427431926082L;

	public int code ;
	
	public String message ;
	
	private RspCodeEnum rspCodeEnum ;
	public BizException(RspCodeEnum rspCodeEnum) {
		this.rspCodeEnum = rspCodeEnum;
		code = rspCodeEnum.getRspCode();
		message = rspCodeEnum.getRspMsg();
	}
	
	public BizException(RspCodeEnum rspCodeEnum , Throwable t) {
		super(t);
		this.rspCodeEnum = rspCodeEnum;
		code = rspCodeEnum.getRspCode();
		message = rspCodeEnum.getRspMsg();
		
	}
	
	public BizException(RspCodeEnum rspCodeEnum, Throwable t, Object... args) {
		super(t);
		this.rspCodeEnum = rspCodeEnum;
		code = rspCodeEnum.getRspCode();
		message = rspCodeEnum.getRspMsg(args);
	}
	
	public int getErrorCode() {
		return this.code ;
	}
	
	public String getErrorMsg() {
		return this.message ;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
	public RspCodeEnum getRspCodeEnum() {
		return this.rspCodeEnum;
	}
}

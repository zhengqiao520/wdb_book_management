package com.wdb007.baseservice.utility.exception;

import com.wdb007.baseservice.utility.constant.RspCodeEnum;

public class SysException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -609299343497834112L;

	public int code;

	public String message;

	public SysException(RspCodeEnum rspCodeEnum, Throwable t) {
		super(t);
		code = rspCodeEnum.getRspCode();
		message = rspCodeEnum.getRspMsg();

	}

	public SysException(RspCodeEnum rspCodeEnum, Throwable t, Object... args) {
		super(t);
		code = rspCodeEnum.getRspCode();
		message = rspCodeEnum.getRspMsg(args);
	}

	public int getErrorCode() {
		return this.code;
	}

	public String getErrorMsg() {
		return this.message;
	}
}

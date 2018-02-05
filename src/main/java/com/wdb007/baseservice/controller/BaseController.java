package com.wdb007.baseservice.controller;

import com.wdb007.baseservice.utility.exception.BizException;
import com.wdb007.baseservice.utility.exception.SysException;
import com.wdb007.baseservice.utility.response.*;

public class BaseController {
	/**
	 * 默认返回一个成功的对象
	 * @return
	 */
	protected BaseResponse<Object> success() {
		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		baseResponse.setResult(1);
		baseResponse.setCode(0);
		baseResponse.setPermission(1);
		baseResponse.setMessage("操作成功");
		return baseResponse;
	}
	/**
	 * 操作异常
	 * @param e
	 * @return
	 */
	protected BaseResponse<Object> failed(Exception e) {
		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		baseResponse.setResult(0);
		if(e instanceof BizException) {
			BizException bizRuntimeException = (BizException) e;
			if(bizRuntimeException.getErrorCode() == 0) {
				baseResponse.setResult(1);
			}
			baseResponse.setCode(bizRuntimeException.getErrorCode());
			baseResponse.setMessage(bizRuntimeException.getErrorMsg());
		}else if(e instanceof SysException) {
			SysException sysRuntimeException = (SysException) e;
			baseResponse.setCode(sysRuntimeException.getErrorCode());
			baseResponse.setMessage(sysRuntimeException.getErrorMsg());
		}else {
			baseResponse.setCode(-99);
			baseResponse.setMessage("系统异常，请联系管理员");
		}
		return baseResponse;
	}
}

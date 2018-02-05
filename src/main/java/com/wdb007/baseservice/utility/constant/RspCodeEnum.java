package com.wdb007.baseservice.utility.constant;

public enum RspCodeEnum {
	
	
	/** 处理成功 */
	CODE_SUCCESS(0, "处理成功"),
	/** 系统异常 */
	CODE_SYS_EXCEPTION(-1, "系统异常"),
	CODE_SYS_ERROR(-99, "系统异常，请联系管理员"),
	/**发送短信验证码时异常*/
	/**手机短信发送成功*/
	SMS_SEND_SUCCESS(0, "发送成功") ,
	/** 手机号码不能为空 */
	MOBILE_IS_UNVALID(1, "手机号码校验非法"),
	/** 短信发送失败*/
	SMS_SEND_FAILED(2, "短信发送失败，请重新获取验证码"),
	
	/**获取用户最新信息时异常*/
	/** 用户不存在*/
	USER_NOT_FOUND(1, "用户不存在") ,
	/** 用户被锁定*/
	USER_IS_LOCKED(2, "用户被锁定，请联系管理员"),
	/** token不能为空*/
	TOKEN_IS_NULL(3, "token不能为空") ,
	
	/** 短信验证码验证超过五次，需要重新获取验证码 */
	VERIFY_CODE_FIVE_TIMES(15, "验证超过五次，请重新获取验证码"),
	/** 短信验证码错误 */
	VERIFY_CODE_IS_WRONG(16, "验证码错误"),
	/** 短信验证码失效 */
	VERIFY_CODE_IS_INVALID(17, "验证码失效,请重新获取验证码"),
	
	/** 未缴纳押金*/
	ACCOUNT_DEPOSIT_IS_NULL(4, "未缴纳押金，请先缴纳押金"),
	/** 账户余额不足 */
	ACCOUNT_IS_NOT_ENOUGH(5, "余额不足1元，请先充值"),
	/** 用户正在开柜，不能再次进行开柜操作 */
	USER_IS_OPENING_GRID(6, "用户正在开柜，不能再次进行开柜操作"),

	BORROWING_COUNT_OVER_10(7, "在借图书超过10本，开锁失败"),
	
	BORROWING_IS_NOT_IN_CURRENT_GRID(8, "在借图书不在当前书格中，开锁失败"),
	
	BOOKSHELF_IS_UNDER_DAMAGE(9, "当前书柜处理报修状态，不能使用"),
	
	BOOKSHELF_IS_NOT_EXISTS(20, "当前书柜不存在"),
	
	BOOKSHELF_IS_UNAVAILABLE(21, "当前书柜不可用"),
	
	BOOK_GRID_IS_NOT_EXISTS(10, "当前书格不存在"),
	
	BOOK_GRID_IS_UNDER_DAMAGE(11, "当前书格已报修"),
	
	BOOK_GRID_IS_UNDER_NON_CLOSED(12, "当前书格处于非关闭状态"),
	
	BOOK_GRID_IS_UNAVAILABLE(22, "当前书格不可用"),
	
	BOOK_GRID_IS_UNDER_NON_INVENTORY(23, "当前书格处于非盘点成功状态，不能进行再次盘点"),
	
	BOOK_GRID_FINALLY_USED_NON_OWN(24, "书格最后一次操作并非本人，不能进行再次盘点"),
	
	BOOK_GRID_LAST_USED_NOT_THIS(25, "您最后开启的并非是该书格，不能进行再次盘点"),
	
	BOOK_GRID_OVER_5_MINUTES(26, "距离你最后操作该书格超过5分钟，不能进行再次盘点"),
	/** 查询记录为空 */
	QUERY_LIST_IS_NULL(1, "查询记录为空 "),

	/** %s 不存在 */
	OBJ_NOT_FOUND(2, "对象不存在") ,

	BOOK_GRID_STATUS_IS_NON_OPENED(13, "书格当前状态为非打开状态，请刷新后再操作"),
	
	BOOK_GRID_STATUS_IS_NON_CLOSED(14, "书格当前状态为非关闭状态，请刷新后再操作"),
	/** 书格相关错误码 */
	
	ACTIVE_DATAVER_IS_INVALID(15, "datever字段不合法，目前只能为1"),
	
	ACTIVE_DEVICETOEKN_IS_NOT_EXISTS(16, "devicetoken不存在"),
	
	ACTIVE_DATA_IS_INVALID(30, "激活时传递的数据不正确，解密失败"),
	
	ACTIVE_DATA_IS_ARRAY(31, "激活时传递的数据不正确，解密成数组"),
	
	ACTIVE_DATA_CHECK_NOT_PASS(32, "参数[%s]不能为空"),
	
	CALL_ALIPAY_ERROR(60, "调用支付宝接口异常"),
	
	CALL_ALIPAY_REFUND_ERROR(61, "调用支付宝退款接口异常"),
	
	
	STAFF_LOGIN_NAME_OR_PWD_IS_EMPTY(4, "登陆用户名或密码不能为空"),
	STAFF_LOGIN_PWD_IS_INVALID(2, "密码不匹配"),
	STAFF_LOGIN_NAME_IS_INVALID(1, "账户名输入错误"),
	STAFF_TRANS_IS_NOT_EXISTS(3, "该条记录不存在"),
	
	HAS_NOT_PUTON_BOOK(40, "没有新的上架图书"),
	
	BOOK_INFO_NOT_EXISTS(2, "图书信息不存在");
	
	/** 返回码 */
	private int rspCode;

	/** 返回码描述 */
	private String rspMsg;

	RspCodeEnum() {
	}

	RspCodeEnum(int rspCode, String rspMsg) {
		this.rspCode = rspCode;
		this.rspMsg = rspMsg;
	}

	public int getRspCode() {
		return rspCode;
	}

	public void setRspCode(int rspCode) {
		this.rspCode = rspCode;
	}

	public String getRspMsg() {
		return rspMsg;
	}

	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}

	public String getRspMsg(Object... args) {
		return String.format(this.rspMsg, args);
	}
	
	public void setRspMessage(Object... args) {
		this.rspMsg = String.format(this.rspMsg, args);;
	}
}

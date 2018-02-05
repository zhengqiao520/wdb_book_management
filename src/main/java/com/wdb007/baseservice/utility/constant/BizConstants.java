package com.wdb007.baseservice.utility.constant;

public class BizConstants {
	/**
	 * 书格状态  0---请求打开
	 */
	public static final Integer GRID_LOCK_STATUS_0 = 0 ;
	/**
	 * 书格状态  1---已打开
	 */
	public static final Integer GRID_LOCK_STATUS_1 = 1 ;
	/**
	 * 书格状态  2---打开失败
	 */
	public static final Integer GRID_LOCK_STATUS_2 = 2 ;
	/**
	 * 书格状态  3---已关闭
	 */
	public static final Integer GRID_LOCK_STATUS_3 = 3 ;
	/**
	 * 书格状态 4---开始盘点
	 */
	public static final Integer GRID_LOCK_STATUS_4 = 4 ;
	/**
	 * 书格状态 5---盘点成功
	 */
	public static final Integer GRID_LOCK_STATUS_5 = 5 ;
	
	/**书格计费标准，超过10分钟收费*/
	public static final Integer BILLING_BASE = 600;
	
	public static final Integer FIVE_MINUTES_MILLS = 5 * 60 * 1000 ;
	
	/**图书实体表，图书状态 0---上架*/
	public static final Integer BOOK_BORROW_STATUS_0 = 0;
	
	/**图书实体表，图书状态 1---下架*/
	public static final Integer BOOK_BORROW_STATUS_1 = 1;
	
	/**图书实体表，图书状态 2---借阅*/
	public static final Integer BOOK_BORROW_STATUS_2 = 2;
	
	/**图书错还 0 --否*/
	public static final Integer BOOK_WRONG_BACK_0 = 0;
	
	/**图书错还 1 --是*/
	public static final Integer BOOK_WRONG_BACK_1 = 1;
	
	/**订单类型 10--图书订单*/
	public static final Integer ORDER_TYPE_10 = 10 ;
	
	/**订单状态 10--始状态*/
	public static final Integer ORDER_STATUS_10 = 10 ;
	
	/**订单状态 20--进行中*/
	public static final Integer ORDER_STATUS_20 = 20 ;
	
	/**订单状态 30--已完成*/
	public static final Integer ORDER_STATUS_30 = 30 ;
	
	/**借还流水表  交易状态 10 --初始状态*/
	public static final Integer ORDER_TRANS_STATUS_10 = 10;
	
	/**借还流水表  交易状态 20 --计费中*/
	public static final Integer ORDER_TRANS_STATUS_20 = 20;
	
	/**借还流水表  交易状态 21 --待支付*/
	public static final Integer ORDER_TRANS_STATUS_21 = 21;
	
	/**借还流水表  交易状态 22 --已支付*/
	public static final Integer ORDER_TRANS_STATUS_22 = 22;
	
	/**借还流水表  交易状态 23 --已支付*/
	public static final Integer ORDER_TRANS_STATUS_23 = 23;
	
	/**借还流水表  交易状态 30 --完成*/
	public static final Integer ORDER_TRANS_STATUS_30 = 30;
	
	/**用户充值记录表 交易状态: trans_status  0---初始化*/
	public static final Integer USER_CHARGE_STATUS_0 = 0 ;
	/**用户充值记录表 交易状态: trans_status  1---进行中*/
	public static final Integer USER_CHARGE_STATUS_1 = 1 ;
	/**用户充值记录表 交易状态: trans_status  2---完成*/
	public static final Integer USER_CHARGE_STATUS_2 = 2 ;
	/**用户充值记录表 交易状态: trans_status  3---交易取消*/
	public static final Integer USER_CHARGE_STATUS_3 = 3 ;
	/**用户充值记录表 交易状态: trans_status  4---交易失败*/
	public static final Integer USER_CHARGE_STATUS_4 = 4 ;
	/**用户充值记录表 交易状态: trans_status  5---交易关闭*/
	public static final Integer USER_CHARGE_STATUS_5 = 5 ;
	
	/**用户充值记录表 交易状态: trans_status  6---退款成功*/
	public static final Integer USER_CHARGE_STATUS_6 = 6 ;
	
	/**用户充值记录表 交易状态: trans_status  7---退款失败*/
	public static final Integer USER_CHARGE_STATUS_7 = 7 ;
	
	/**账户现金变动记录表  充值类型  10 ---- 微信*/
	public static final Integer ACCOUNT_CASH_CHARGE_TYPE_10 =  10 ;
	/**账户现金变动记录表  充值类型  20 ---- 支付宝*/
	public static final Integer ACCOUNT_CASH_CHARGE_TYPE_20 =  20 ;
	/**账户现金变动记录表  充值类型  30 ---- 系统赠送*/
	public static final Integer ACCOUNT_CASH_CHARGE_TYPE_30 =  30 ;
	/**账户现金变动记录表  充值类型  40 ---- 书格扣款*/
	public static final Integer ACCOUNT_CASH_CHARGE_TYPE_40 =  40 ;
	/**账户现金变动记录表  充值类型  50 ---- 租书扣款*/
	public static final Integer ACCOUNT_CASH_CHARGE_TYPE_50 =  50 ;
	
	/**账户现金变动记录表  操作类型  10 ---- 充值*/
	public static final Integer ACCOUNT_CASH_OPER_TYPE_10 =  10 ;
	/**账户现金变动记录表  操作类型  20 ---- 扣款*/
	public static final Integer ACCOUNT_CASH_OPER_TYPE_20 =  20 ;
	
	/**短信验证状态： 0 ---- 未验证*/
	public static final Integer SMS_STATUS_0 = 0 ;
	/**短信验证状态： 1 ---- 已验证*/
	public static final Integer SMS_STATUS_1 = 1 ;
	/**短信验证状态： 2 ---- 失效*/
	public static final Integer SMS_STATUS_2 = 2 ;
	/**用户状态 0----正常*/
	public static final Integer USER_INFO_STATUS_0 = 0 ;
	/**用户状态 1----冻结*/
	public static final Integer USER_INFO_STATUS_1 = 1 ;
	
	/**现金账户 0----正常*/
	public static final Integer ACCOUNT_CASH_STATUS_10 = 10 ;
	/**现金账户 1----冻结*/
	public static final Integer ACCOUNT_CASH_STATUS_20 = 20 ;
	
	/**用户登陆表--日志类型 0---注册*/
	public static final Integer USER_ACCESS_LOG_TYPE_0 = 0 ;
	/**用户登陆表--日志类型 1---登陆*/
	public static final Integer USER_ACCESS_LOG_TYPE_1 = 1 ;
	/**用户登陆表--日志类型 2---退出登陆*/
	public static final Integer USER_ACCESS_LOG_TYPE_2 = 2 ;
	
	public static final Integer ACCOUNT_CREDIT_CHANGE_TYPE_10 = 10 ;
	/**用户充值记录表--充值渠道 0--支付宝 */
	public static final Integer CHARGE_LOG_CHANNEL_0 = 0;
	/**用户充值记录表--充值渠道 1--微信 */
	public static final Integer CHARGE_LOG_CHANNEL_1 = 1;
	/**用户类型  0-普通用户*/
	public static final Integer GRID_TRANS_USER_TYPE_0 = 0;
	/**用户类型 1-运维人员*/
	public static final Integer GRID_TRANS_USER_TYPE_1 = 1;
	/**更换图书信息表 0-上架*/
	public static final Integer BOOK_CHANGE_RELATION_0 = 0 ;
	/**更换图书信息表 1-下架*/
	public static final Integer BOOK_CHANGE_RELATION_1 = 1;
	/**用户充值记录表 0-押金充值*/
	public static final Integer USER_CHARGE_LOG_CHARGE_TYPE_0 = 0;
	/**用户充值记录表 1-余额充值*/
	public static final Integer USER_CHARGE_LOG_CHARGE_TYPE_1 = 1;
}



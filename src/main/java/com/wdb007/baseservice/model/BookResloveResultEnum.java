package com.wdb007.baseservice.model;

/**
 * 图书解析结果枚举类型
 * @author tianzhi
 *
 */
public enum BookResloveResultEnum {
	 /*数据库记录已存在*/
     DBEXISTS, 
     /*解析成功*/
     SUCCESS, 
     /*解析失败*/
     FAIL, 
     /*未找到isbn*/
     NOTFOUNT 
}
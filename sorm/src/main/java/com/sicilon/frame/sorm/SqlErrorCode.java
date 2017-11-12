package com.sicilon.frame.sorm;
/**
 * Description: 用于sql出错使用
 * Author: zhang <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年5月12日 上午11:03:50.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class SqlErrorCode {
	public final static String INVALId_PK = "主键无效";
	public final static String NO_UPDATE_OR_INVALId_PK = "主键不可更新或者主键无效";
	public final static String NO_FIND_TABLENAME = "找不到注解表名，使用类名";
	public final static String NO_ASSIGNMENT = "赋值失败";
	public final static String NOGET_CACHE = "获取pojo&&column集合失败";
	public final static String CURRENT_NOEXISTENT = "当前字段在结果集中不存在";
	public final static String RESULT_SET = "结果集中";
	public final static String ATTRIBUTE_SET = "实体中的属性";
}

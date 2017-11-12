package com.sicilon.frame.sutils;
/**
 * Description: 分页工具类
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月15日 下午5:13:02.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class PageUtil {

	/**
	 * 验证分页数据有效性
	 * @param pageNum 当前页
	 * @param pageSize 页大小
	 * @return 真假值
	 */
	public static boolean validateNum(Integer pageNum,Integer pageSize){
		
		if(pageNum == null || pageNum == 0
				|| pageSize == null || pageSize == 0){
			return false;
		}
		return true;
	}
}

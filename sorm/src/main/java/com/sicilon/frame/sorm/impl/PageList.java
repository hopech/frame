package com.sicilon.frame.sorm.impl;

import java.util.List;

public class PageList<T> {
	/**
	 * 总条数
	 */
	private Integer count;
	/**
	 * 页面总数
	 */
	private Integer pageCount;
	/**
	 * 每页条数  默认10条
	 */
	private Integer pageSize;
	/**
	 * 当前页
	 */
	private Integer thisPage;
	/**
	 * 下一页
	 */
	private Integer nextPage;
	/**
	 * 上一页
	 */
	private Integer lastPage;
	/**
	 * 分页第一个数字
	 */
	private int firstNum;
	/**
	 * 分页第二个数字
	 */
	private int lastNum;
	/**
	 * 数据集
	 */
	private List<T> list;
	/**
	 * 总页数
	 */
	private Integer totalRecord;
	
	/**
	 * 计算第一个数字
	 * @param pageSize
	 * @param thisPage
	 *//*
	public static Integer sumFirst(Integer pageSize, Integer thisPage){
		if(thisPage>=1&&thisPage<=totalRecord){
			return firstNum = (thisPage-1)*pageSize;
		}
		return 0;
	}
	
	*//**
	 * 计算第二个数字
	 * @param pageSize
	 * @param thisPage
	 *//*
	public static Integer sumLast(Integer pageSize, Integer thisPage){
		if(thisPage<=totalRecord){
			if(thisPage*pageSize > count){
				return lastNum = count;
			}else{
				return lastNum = thisPage*pageSize;
			}
		}
		return 10;
	}
	
	*//**
	 * 计算总页数
	 * @param pageSize
	 * @param count
	 *//*
	public static void sumTotalRecord(Integer pageSize, Integer count){
		if(count % pageSize == 0)
			this.setTotalRecord(count / pageSize);
		this.setTotalRecord(count / pageSize + 1);
		this.setCount(count);
	}
	
	public static String Calculation(Integer thisPage, Integer pageSize, Integer count){
		sumTotalRecord(pageSize, count);
		Integer sumFirst = sumFirst(thisPage, pageSize);
		Integer sumLast = sumLast(thisPage, pageSize);
		//return sumFirst(pageSize, thisPage)+","+sumLast(pageSize, thisPage);
		return sumFirst(pageSize, thisPage)+","+pageSize;
	}*/
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize <= 0){
			pageSize = 10;
		}else{
			this.pageSize = pageSize;
		}
	}
	public Integer getThisPage() {
		return thisPage;
	}
	public void setThisPage(Integer thisPage) {
		if(thisPage <= 0){
			thisPage = 1;
		}else{
			this.thisPage = thisPage;
		}
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getLastPage() {
		return lastPage;
	}
	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}
	public int getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}
	public int getLastNum() {
		return lastNum;
	}
	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
}

package com.silion.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sicilon.frame.sutils.FileTypeUtil;


/** 
* @author : 张博
* @date 创建时间：2017年4月11日 上午9:03:40 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class FileTypeUtilTest {
	
	
	
	/**
	 * @Title: test_For_pictureType 
	 * @Description: 判断文件 图片 类型
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_fileType(){
		//文件路径
		String picture = "E:/趣图/1.jpg",picture1 = "E:/趣图/1459697062721",picture2 = "C:/Users/Administrator/Desktop/新建文件夹/营业执照.pdf";
		//本不存在
		String picture3 = "E:/趣图/1.text";
		assertEquals(FileTypeUtil.getFileType(picture), "jpg");
		assertEquals(FileTypeUtil.getFileType(picture1), "jpg");
		assertEquals(FileTypeUtil.getFileType(picture2), "pdf");
		assertEquals(FileTypeUtil.getFileType(picture3), "文件不存在");
		//logger.info("pictureType>>>>"+FileTypeUtil.getFileType(picture));//jpg
		//logger.info("pictureType>>>>"+FileTypeUtil.getFileType(picture1));//jpg
		//logger.info("pictureType>>>>"+FileTypeUtil.getFileType(picture2));//pdf
		//logger.info("pictureType>>>>"+FileTypeUtil.getFileType(picture3));//文件不存在
	}
	
	
	
	/*@Test
	public void test_For_getImageFileType(){
		String picture = "E:/趣图/1.agag";
		//FileTypeUtil.getImageFileType(picture);
	}*/
}

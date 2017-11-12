package com.silion.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;

import org.junit.Assert;
import org.junit.Test;

import com.sicilon.frame.sutils.DebugOut;
import com.sicilon.frame.sutils.FileUtil;


/**
 * 
 * description：文件操作类测试
 * ClassName: FileTest <br/> 
 * date: 2017年4月11日 下午1:04:35 <br/> 
 * @author chen
 */
public class FileTest {

	// 判断文件是否存在
	@Test
	public void isFileExist(){
		// 测试文件
		String fileName = "src/test/java/com/sicilon/myspringboot/test/util/fileTest.txt";
		Assert.assertTrue(FileUtil.isFileExist(fileName));
		
		// 测试文件路径不能用此方法
		String filePath = "src/test/java/com/sicilon/myspringboot/test/util/";
		Assert.assertFalse(FileUtil.isFileExist(filePath));
		
		// 测试文件路径用file类原生方法的exists
		Assert.assertTrue(new File(filePath).exists());
		
	}
	
	// 创建路径 
	@Test
	public void createPaths(){
		
		// 已经存在的路径,不做任何处理
		String path = "E://myself_dev/resource/api";
		FileUtil.createPaths(path);
		
		// 不存在的路径
		String path2 = "E://myself_dev2/resource/api/test";
		FileUtil.createPaths(path2);
	}
	
	// 创建文件的目录结构
	@Test
	public void createFilePath() throws IOException{
		File file = new File("E://myself_dev2/resource/api/test.txt");
		FileUtil.createFilePath(file);
	}
	
	// 清空目录下的文件
	@Test
	public void emptyDirectory(){
		File file = new File("src/test/java/com/sicilon/myspringboot/test/util/empty");
		try {
			Assert.assertTrue(FileUtil.emptyDirectory(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 删除指定目录及所有内容
	@Test
	public void deleteDirectory(){
		String path = "src/test/java/com/sicilon/myspringboot/test/util/empty";
		//FileUtil.deleteDirectory(path);
	}
	
	// 获取指定路径下的所有文件数组
	@Test
	public void listAll(){
		File file = new File("src/");
		File[] fileArray = FileUtil.listAll(file, new FileFilter() { // 自定义实现,
			
			@Override
			public String getDescription() {
				return "只接收java文件";
			}
			
			@Override
			public boolean accept(File f) {
				if(f.getName().endsWith(".java")){
					return true;
				}
				return false;
			}
		});
		
		for (File fa : fileArray) {
			DebugOut.println(fa.getName());
		}
	}
	
	// 从文件路径得到文件名。
	@Test
	public void getFileNameByPath(){
		String path = "/src/main/java";
		String actual_result = FileUtil.getFileNameByPath(path);
		String assert_result = "java";
		Assert.assertEquals(assert_result, actual_result);
	}
	
	// 从文件名得到以项目为根的绝对路径。
	@Test
	public void getPathByFileName(){
		String fileName = "test";
		String actual_result = FileUtil.getPathByFileName(fileName);
		DebugOut.println(actual_result);
	}
	
	// 获取文件的后缀名
	@Test
	public void getTypePart() {
		String fileName = "E://myself_dev2/resource/api/test.txt";
		String actual_result = FileUtil.getTypePart(fileName);
		String assert_result = "txt";
		Assert.assertEquals(assert_result, actual_result);
	}
	
	// 获取文件简易名称, 不携带后缀名
	@Test
	public void getFileSimpleName(){
		String fileName = "E://myself_dev2/resource/api/test.txt";
		String actual_result = FileUtil.getFileSimpleName(fileName);
		String assert_result = "test.txt";
		Assert.assertEquals(assert_result, actual_result);
	}
	
	// 获取文件的父路径
	@Test
	public void getParentPath(){
		String fileName = "E://myself_dev2/resource/api/test.txt";
		String actual_result =FileUtil.getParentPath(fileName);
		String assert_result = "E://myself_dev2/resource/api";
		Assert.assertEquals(assert_result, actual_result);
	}
	
}

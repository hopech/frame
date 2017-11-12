package com.silion.utils;

import org.junit.Test;

import com.sicilon.frame.sutils.ReflectUtil;


/**
 * 
 * description：反射工具类测试
 * ClassName: ReflectUtilTest <br/> 
 * date: 2017年4月12日 下午3:19:06 <br/> 
 * @author chen
 */
public class ReflectUtilTest {

	// 获取类中定义的属性
	@Test
	public void getField() throws ClassNotFoundException{
		String[] field = ReflectUtil.getField("com.sicilon.myspringboot.test.util.Student");
		for (String string : field) {
			System.out.println(string);
		}
	}
	
	// 获取类中的方法名称,包括父类的方法
	@Test
	public void getMethod() throws ClassNotFoundException{
		String[] methods = ReflectUtil.getMethod("com.sicilon.myspringboot.test.util.Student");
		for (String string : methods) {
			System.out.println(string);
		}
	}
	
	// 测试set方法
	@Test
	public void setter(){
		String fileName = "name";
		Student stu = new Student();
		ReflectUtil.setter(stu, fileName, "zhangsan", String.class);
		System.out.println(stu);
	}
	
	// 测试get方法
	@Test
	public void getter(){
		String fileName = "name";
		Student stu = new Student("zhangsan","lanqiu");
		ReflectUtil.getter(stu, fileName);
	}
}

package com.sicilon.frame.sutils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * description：反射Util ClassName: ReflectUtil <br/>
 * date: 2017年4月12日 上午11:49:20 <br/>
 * 
 * @author chen
 */
public class ReflectUtil {

	/**
	 * 获取类中定义的属性
	 * 
	 * @param className
	 *            类名称
	 * @return 属性名称数组
	 * @throws ClassNotFoundException
	 *             找不到指定的类异常
	 */
	public static String[] getField(String className) throws ClassNotFoundException {
		Class<?> classz = Class.forName(className);
		Field fields[] = classz.getFields();
		Field fieldz[] = classz.getDeclaredFields();
		Set<String> set = new HashSet<String>();
		Field arr[] = fields;
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			Field f = arr[i];
			set.add(f.getName());
		}

		arr = fieldz;
		len = arr.length;
		for (int i = 0; i < len; i++) {
			Field f = arr[i];
			set.add(f.getName());
		}

		return (String[]) set.toArray(new String[0]);
	}

	/**
	 * 获取类中的方法名称,包括父类的方法
	 * 
	 * @param className
	 *            类名称
	 * @return 类名称数组
	 * @throws ClassNotFoundException
	 *             找不到指定的类异常
	 */
	public static String[] getMethod(String className) throws ClassNotFoundException {
		Class<?> classz = Class.forName(className);
		Method methods[] = classz.getMethods();
		Set<String> set = new HashSet<String>();
		if (methods != null) {
			Method arr[] = methods;
			int len = arr.length;
			for (int i = 0; i < len; i++) {
				Method f = arr[i];
				set.add(f.getName());
			}
		}
		return (String[]) set.toArray(new String[0]);
	}

	/**
	 * 调用对象的set方法
	 * @param obj 对象
	 * @param att 属性名称
	 * @param value 要设置的值
	 * @param type 要设置的值的类型
	 */
	public static void setter(Object obj, String fileName, Object value, Class<?> type) {
		try {
			Method met = obj.getClass().
					getMethod((new StringBuilder()).append("set").append(initStr(fileName)).toString(),new Class[] { type });
			met.invoke(obj, new Object[] { value });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 调用对象的get方法
	 * @param obj 对象
	 * @param fileName
	 */
	public static void getter(Object obj, String fileName) {
		try {
			Method met = obj.getClass().getMethod((new StringBuilder()).append("get").append(initStr(fileName)).toString(),
					new Class[0]);
			System.out.println(met.invoke(obj, new Object[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将字段名称转换为 get或者set方法的名称
	 * @param old
	 * @return
	 */
	private static String initStr(String old) {
		String str = (new StringBuilder()).append(old.substring(0, 1).toUpperCase()).append(old.substring(1))
				.toString();
		return str;
	}
}

package reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.esotericsoftware.reflectasm.MethodAccess;

public class Xiaolv {

	public static Map<Class<?>, ClassCache> classChache = new HashMap<Class<?>, ClassCache>();

	@Test
	public void methodTest() {
		SomeClass s = new SomeClass();
		System.out.println(insert(s));
	}

	public String insert(Object object){
		/*
		 * 1.获取缓存class  insert into table (aaa) values ()
		 */
		ClassCache classCache = this.getClassCache(object.getClass());

		/*
		 * 2.获取其中的属性名称
		 */
		System.out.println(classCache.getFieldValuesAgen());
		return classCache.getFildNames();
		
	}

	public ClassCache getClassCache(Class<?> clazz){
		/*
		 * 如果缓存中有classChache返回,没有则构建
		 */
		ClassCache classCache = classChache.get(clazz);
		
		if(classCache !=null){
			return classCache;
		}
		return this.buildClaasChache(clazz);
	}

	public ClassCache buildClaasChache(Class<?> clazz) {
		ClassCache cache = new ClassCache();
		
		Map<String, Integer> methodIndex = new HashMap<String, Integer>(); //方法索引
		Map<String, String> getMethod = new HashMap<String, String>(); //get方法
		Map<String, String> setMethod = new HashMap<String, String>(); //set方法
		
		StringBuffer fieldNames = new StringBuffer();// 属性名
		StringBuffer fieldNameAgens = new StringBuffer();// 属性名占位符形式
		
		/*
		 * 1.设置methodAccess
		 */
		MethodAccess methodAccess = MethodAccess.get(clazz);
		cache.setMethodAccess(methodAccess);

		/*
		 * 2.设置属性名
		 */
		Field[] fields = clazz.getDeclaredFields();
		
		// 遍历属性数组
		for (Field field : fields) {
			String fieldName = field.getName();
			fieldNames.append(fieldName).append(","); //属性名
			fieldNameAgens.append(":").append(fieldName).append(",");//属性名占位符形式
			getMethod.put(fieldName, cache.getFieldMethodName(fieldName)); // get方法
			setMethod.put(fieldName, cache.setFieldMethodName(fieldName));// set方法
			//方法的索引
			methodIndex.put(cache.getFieldMethodName(fieldName), methodAccess.getIndex(cache.getFieldMethodName(fieldName)));
		}
		cache.setFildNames(fieldNames.deleteCharAt(fieldNames.length() - 1).toString());

		/*
		 * 3.设置属性名占位符
		 */
		cache.setFieldValuesAgen(fieldNameAgens.deleteCharAt(fieldNameAgens.length() - 1).toString());

		/*
		 * 4.设置方法索引
		 */
		cache.setMethodIndex(methodIndex);
		
		/*
		 * 5.设置getSet方法
		 */
		cache.setGetMethod(getMethod);
		cache.setSetMethod(setMethod);
		
		return cache;
	}
}

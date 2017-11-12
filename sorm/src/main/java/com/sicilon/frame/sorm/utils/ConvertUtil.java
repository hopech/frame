package com.sicilon.frame.sorm.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * description：类型转换工具类 
 * ClassName: ConvertUtil <br/> 
 * date: 2017年4月6日 上午10:11:07 <br/> 
 * @author chen
 */
public class ConvertUtil {
	
	/**
	 *  将bean转化成map，目前键值只存放String int Integer 类型
	 * @param bean 实体bean
	 * @return 实体bean对应的map类型
	 * @throws Exception 类型转换异常,反射异常
	 */
	public static Map<String,String> beanToMap(Object bean) throws Exception{
		Class<?> type = bean.getClass();
        Map<String,String> returnMap = new HashMap<String,String>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
        	PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                
                if(result == null){
                	continue;
                }
                if (descriptor.getPropertyType()== String.class || descriptor.getPropertyType()== Integer.class 
                		|| descriptor.getPropertyType()== int.class) {
                    returnMap.put(propertyName, String.valueOf(result));
                }else{
                	continue;
                }
            }
		}
        
        return returnMap;
	}
	
	/**
	 * 将obj泛型的map转换为序列化后的map
	 * @param map 字符串键对应实体值的map
	 * @return 序列化后的map
	 */
	public static Map<byte[], byte[]> objMapToSeriMap(Map<String, Object> map){
		Map<byte[], byte[]> byteMap = new HashMap<byte[], byte[]>();
		
		Set<String> keySets = map.keySet();
		for (String keySet : keySets) {
			byteMap.put(ObjectUtil.serialize(keySet), 
					ObjectUtil.serialize(map.get(keySet)));
		}
		return byteMap;
	}
	
	/**
	 * 将序列化后的map的转换为obj泛型的map 
	 * @param map 序列化后的map
	 * @return 反序列化后的map
	 */
	public static Map<String, Object> seriMapToObjMap(Map<byte[], byte[]> byteMap){
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		Set<byte[]> keySets = byteMap.keySet();
		for (byte[] keySet : keySets) {
			byte[] value = byteMap.get(keySet);
			strMap.put((String)ObjectUtil.unserialize(keySet),
					ObjectUtil.unserialize(value));
		}
		return strMap;
	}
	
	/**
	 * 字符串转boolean,默认值为false
	 * @param bool 字符串形式的boolean值
	 * @return boolean对象
	 */
	public static boolean stringToBoolean(String bool){

		if(StringUtil.equalsIgnoreCase(bool, "true")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * short类型对象转换byte类型对象
	 * @param number short值
	 * @return byte值
	 */
	public static byte[] shortToByte(short number)
	{
		int temp = number;
		byte b[] = new byte[2];
		for (int i = 0; i < b.length; i++)
		{
			b[i] = (new Integer(temp & 0xff)).byteValue();
			temp >>= 8;
		}

		return b;
	}

	/**
	 * byte类型对象转换short类型对象
	 * @param b byte值
	 * @return short值
	 */
	public static short byteToShort(byte b[])
	{
		short s = 0;
		short s0 = (short)(b[0] & 0xff);
		short s1 = (short)(b[1] & 0xff);
		s1 <<= 8;
		s = (short)(s0 | s1);
		return s;
	}

	/**
	 * 将int型变量转换成byte类型
	 * @param i int值
	 * @return byte值
	 */
	public static byte[] intToByte(int i)
	{
		byte bt[] = new byte[4];
		bt[0] = (byte)(0xff & i);
		bt[1] = (byte)((0xff00 & i) >> 8);
		bt[2] = (byte)((0xff0000 & i) >> 16);
		bt[3] = (byte)((0xff000000 & i) >> 24);
		return bt;
	}

	/**
	 * 将byte型变量转换成int类型
	 * @param bytes byte值
	 * @return int值
	 */
	public static int bytesToInt(byte bytes[])
	{
		int num = bytes[0] & 0xff;
		num |= bytes[1] << 8 & 0xff00;
		num |= bytes[2] << 16 & 0xff0000;
		num |= bytes[3] << 24 & 0xff000000;
		return num;
	}

	/**
	 * 将long型变量转换成int类型
	 * @param number long值
	 * @return byte[]值
	 */
	public static byte[] longToByte(long number)
	{
		long temp = number;
		byte b[] = new byte[8];
		for (int i = 0; i < b.length; i++)
		{
			b[i] = (new Long(temp & 255L)).byteValue();
			temp >>= 8;
		}

		return b;
	}

	/**
	 * 将byte型变量转换成long类型
	 * @param b byte[]值
	 * @return long值
	 */
	public static long byteToLong(byte b[])
	{
		long s = 0L;
		long s0 = b[0] & 0xff;
		long s1 = b[1] & 0xff;
		long s2 = b[2] & 0xff;
		long s3 = b[3] & 0xff;
		long s4 = b[4] & 0xff;
		long s5 = b[5] & 0xff;
		long s6 = b[6] & 0xff;
		long s7 = b[7] & 0xff;
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 32;
		s5 <<= 40;
		s6 <<= 48;
		s7 <<= 56;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}
	
}

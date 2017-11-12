package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.esotericsoftware.reflectasm.MethodAccess;


public class GetClassTest {

	private static Map<Class<?>, MethodAccess> accessMap = new HashMap<Class<?>, MethodAccess>();
	
	@Test
	public void getJdkClass(){
		for (int i = 0; i < 5; i++) {
			long begin = System.currentTimeMillis();
			
			for (int j = 0; j < 10000000; j++) {
				SomeClass s  = new SomeClass();
			}
			System.out.print(System.currentTimeMillis() - begin + " ");
		}
	}
	
	@Test
	public void getReflectClass() throws InstantiationException, IllegalAccessException{
		for (int i = 0; i < 5; i++) {
			long begin = System.currentTimeMillis();
			
			for (int j = 0; j < 10000000; j++) {
				SomeClass s = SomeClass.class.newInstance();
			}
			System.out.print(System.currentTimeMillis() - begin + " ");
		}
	}
	
	public static MethodAccess getMC(Class<?> clazz){
		MethodAccess methodAccess = accessMap.get(clazz);
		if(methodAccess != null){
			return accessMap.get(clazz);
		}
		MethodAccess result = MethodAccess.get(clazz);
		accessMap.put(clazz, result);
		return MethodAccess.get(clazz);
	}
	
	
	public static void byObj(Class<?> clazz,Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
//		Method method = clazz.getMethod("foo", String.class);
//		method.invoke(obj, "Unmi");
		MethodAccess access = getMC(clazz);
		access.invoke(obj, "foo", "Unmi");
	}
	
	public static void byClass(Class<?> clazz) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Object obj = clazz.newInstance();
		MethodAccess access = getMC(clazz);
		//MethodAccess access = MethodAccess.get(SomeClass.class);
		//Method method = clazz.getMethod("foo", String.class);
		//method.invoke(obj, "Unmi");
		//access.invoke(obj, getIndex(access), "Unmi");
		access.invoke(obj, "foo", "Unmi");
	}
	
	public static int getIndex(MethodAccess access){
		
		if(indexNum == null){
			int index = access.getIndex("foo");
			indexNum = index;
			return index;
		}
		return indexNum;
		
	}
	
	private static Integer indexNum;
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		for (int i = 0; i < 5; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < 100000000; j++) {
            	byClass(SomeClass.class);
            	
//            	SomeClass s = new SomeClass();
//            	byObj(SomeClass.class,s);
            	
//            	SomeClass s = new SomeClass();
//            	s.foo("ds");
            }
            System.out.print(System.currentTimeMillis() - begin +" ");
        }
	}
}

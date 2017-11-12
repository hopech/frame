package reflect;

import java.lang.reflect.Method;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

public class ReflectasmClient {
	public static void main(String[] args) throws Exception {
       // testJdkReflect();
       // testReflectAsm();
//		testJdkReflect2();
//		testReflectAsm2();
		testReflectAsm2();
//		testMethod();
    }
     
    public static void testJdkReflect() throws Exception {
        SomeClass someObject = new SomeClass();        
        for (int i = 0; i < 5; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < 100000000; j++) {
                Method method = SomeClass.class.getMethod("foo", String.class);
                method.invoke(someObject, "Unmi");
            }
            System.out.print(System.currentTimeMillis() - begin +" ");
        }
    }
 
    public static void testReflectAsm() {
        SomeClass someObject = new SomeClass();
        for (int i = 0; i < 5; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < 100000000; j++) {
                MethodAccess access = MethodAccess.get(SomeClass.class);
                access.invoke(someObject, "foo", "Unmi");
            }
            System.out.print(System.currentTimeMillis() - begin + " ");
        }
    }
    
    
    public static void testJdkReflect2() throws Exception {
        SomeClass someObject = new SomeClass();        
        Method method = SomeClass.class.getMethod("foo", String.class);
        for (int i = 0; i < 5; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < 100000000; j++) {
                method.invoke(someObject, "Unmi");
            }
            System.out.print(System.currentTimeMillis() - begin +" ");
        }
    }
    
    
    public static void testReflectAsm2() throws InstantiationException, IllegalAccessException {
    	
        SomeClass someObject = new SomeClass();
        MethodAccess access = MethodAccess.get(SomeClass.class);
        int fooIndex = access.getIndex("foo", String.class);//setName();

        
        
        for (int i = 0; i < 10; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < 100000000; j++) {
                access.invoke(someObject, fooIndex, "Unmi");
            }
            System.out.print(System.currentTimeMillis() - begin + " ");
        }
    }
    
    public static void testMethod() {
        SomeClass s = new SomeClass();
        for (int i = 0; i < 5; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < 100000000; j++) {
                s.foo("a");
            }
            System.out.print(System.currentTimeMillis() - begin + " ");
        }
    }
    
    
    
    
    
}

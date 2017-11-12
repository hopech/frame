/**
 * 
 */
package com.sicilon.frame.sutils;

/**
 * 
 * description：打印输出
 * ClassName: DebugOut <br/> 
 * date: 2017年4月13日 上午9:48:11 <br/> 
 * @author chen
 */
public class DebugOut {
	
	private static boolean DEBUG = true;
	
	public static void print(Object arg0){
		if (DEBUG)
			System.out.print(arg0.toString());
	}
	public static void println(Object arg0){
		if (DEBUG)
			System.out.println(arg0.toString());
	}

}

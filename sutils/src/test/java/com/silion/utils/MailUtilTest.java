package com.silion.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sicilon.frame.sutils.MailUtil;


public class MailUtilTest {
	
	
	
	
	
	/**
	 * 测试发送邮件
	 * 
	 * 规范 ：
	 *  	1.尽量标题和内容一致
	 *  	2.附件尽量打包
	 */
	@Test
	public void test_For_Mail(){
		String asserta = "发送成功";
		String mailbody = "404-小狗奔跑.zip,<font color=red>404-小狗奔跑.zip</font>";
		MailUtil themail = new MailUtil("smtp.126.com");
		themail.setNeedAuth(true);
		// 标题
		if (themail.setSubject("404-小狗奔跑.zip") == false){
			asserta = "发送失败";
			return;
		}
		// 邮件内容 支持html 如欢迎你,java</font>
		if (themail.setBody(mailbody) == false){
			asserta = "发送失败";
			return;
		}
		// 收件人邮箱
		if (themail.setTo("3134556380@qq.com") == false){
			asserta = "发送失败";
			return;
		}
		// 发件人邮箱

		if (themail.setFrom("sicilon@126.com") == false){
			asserta = "发送失败";
			return;
		}
		// 设置附件

		if(themail.addFileAffix("C:/Users/Administrator/Desktop/404-小狗奔跑.zip") == false){
			asserta = "发送失败";
			return; //附件在本地机子上的绝对路径
		}
		themail.setNamePass("sicilon@126.com", "sicilon0412"); // 用户名与密码,即您选择一个自己的电邮
		if (themail.sendout() == false){
			asserta = "发送失败";
			return ;
		}
		assertEquals(asserta, "发送成功");
	}
}

package com.sicilon.frame.sutils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


/**
 * @author zhangbo
 * <br/>
 * 规范 ：
 * 			<br/>
 *  	1.尽量标题和内容一致
 * 			<br/>
 *  	2.附件尽量打包
 */
@SuppressWarnings("unused")
public class MailUtil {

	private MimeMessage mimeMsg; // MIME邮件对象

	private Session session; // 邮件会话对象

	private Properties props; // 系统属性

	private boolean needAuth = true; // smtp是否需要认证

	private String username = ""; // smtp认证用户名和密码

	private String password = "";

	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成


	public MailUtil(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}

	public void setSmtpHost(String hostName) {
		if (props == null) {
			props = System.getProperties(); // 获得系统属性对象
		}			
		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	public boolean createMimeMessage() {
		try {
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		} catch (Exception e) {
			return false;
		}

		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart(); //mp 一个multipart对象
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void setNeedAuth(boolean need) {
		if (props == null)
			props = System.getProperties();
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * 设置发送的用户名 密码
	 * @param name 用户名
	 * @param pass 密码
	 */
	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}

	/**
	 * 标题
	 * @param mailSubject 设置标题的值
	 * @return
	 */
	public boolean setSubject(String mailSubject) {
		try {
			mimeMsg.setSubject(MimeUtility.encodeText(mailSubject, "utf-8", "B"));
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 邮箱内容
	 * @param mailBody 设置邮箱内容 
	 * @return
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			// 转换成中文格式
			bp.setContent(
			""
			+ mailBody, "text/html;charset=utf-8");
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 添加附件
	 * @param filename 附件的路径
	 * @return
	 */
	public boolean addFileAffix(String filename) {
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addMig(FileDataSource imgSoruce, String migID) {
		try {
			MimeBodyPart img = new MimeBodyPart();
			DataHandler dh = new DataHandler(imgSoruce);
			img.setDataHandler(dh);
			// 创建图片的一个表示用于显示在邮件中显示
			img.setContentID(migID);
			mp.addBodyPart(img);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发件人邮箱
	 * @param from 发件人邮箱
	 * @return
	 */
	public boolean setFrom(String from) {
		String nick = "";
		String name = "";// (String) Utils.getSystemProperty("MAIL_NICK");
		try {
			/*
			 * Resource resource = new
			 * ClassPathResource("mail/mail.properties"); Properties nickprops =
			 * PropertiesLoaderUtils.loadProperties(resource);
			 * name=nickprops.getProperty("mail.nickname");
			 * nick=MimeUtility.encodeText(name,"utf-8","B");
			 */
			mimeMsg.setFrom(new InternetAddress(nick + " <" + from + ">")); // 设置发信人

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 收件人邮箱
	 * @param to 收件人的邮箱
	 * @return 
	 */
	public boolean setTo(String to) {
		if (to == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC,
			InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发送邮件
	 * @return
	 */
	public boolean sendout() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), username, password);
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			// transport.send(mimeMsg);
			transport.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

}

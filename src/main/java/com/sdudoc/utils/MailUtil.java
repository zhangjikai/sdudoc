package com.sdudoc.utils;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sdudoc.bean.User;

public class MailUtil {

	/** 发送用户验证的邮件 */
	public static void sendAccountActiveEmail(User user) {

		String subject = "sdudoc邮箱验证提醒";
		String content = "感谢您于" + DocUtil.getDateTime() + "在sdudoc注册，复制以下链接，即可完成安全验证："
				+ "http://127.0.0.1:8080/sdudoc/activeUser.action?user.username=" + user.getUsername()
				+ "&user.checkCode=" + user.getCheckCode() + " 为保障您的帐号安全，请在24小时内点击该链接，您也可以将链接复制到浏览器地址栏访问。"
				+ "若您没有申请过验证邮箱 ，请您忽略此邮件，由此给您带来的不便请谅解。";

		// session.setDebug(true);

		String from = "sdu_doc@163.com"; // 发邮件的出发地（发件人的信箱）
		Session session = getMailSession();
		// 定义message
		MimeMessage message = new MimeMessage(session);
		try {
			// 设定发送邮件的地址
			message.setFrom(new InternetAddress(from));
			// 设定接受邮件的地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			// 设定邮件主题
			message.setSubject(subject);
			// 设定邮件内容
			BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
			mdp.setContent(content, "text/html;charset=utf8");// 给BodyPart对象设置内容和格式/编码方式
			Multipart mm = new MimeMultipart();// 新建一个MimeMultipart对象用来存放BodyPart对
			// 象(事实上可以存放多个)
			mm.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
			message.setContent(mm);// 把mm作为消息对象的内容
			// message.setText(content);
			message.saveChanges();
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 发送密码重置的邮件 */
	public static void sendResetPasswordEmail(User user) {
		String subject = "sdudoc密码重置提醒";
		String content = "您于" + DocUtil.getDateTime() + "在sdudoc找回密码，点击以下链接，进行密码重置："
				+ "http://127.0.0.1:8080/sdudoc/resetPasswordCheck.action?user.username=" + user.getUsername()
				+ "&user.checkCode=" + user.getCheckCode() + " 为保障您的帐号安全，请在24小时内点击该链接，您也可以将链接复制到浏览器地址栏访问。"
				+ "若您没有申请密码重置，请您忽略此邮件，由此给您带来的不便请谅解。";

		// session.setDebug(true);

		String from = "sdu_doc@163.com"; // 发邮件的出发地（发件人的信箱）
		Session session = getMailSession();
		// 定义message
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setSubject(subject);
			BodyPart mdp = new MimeBodyPart();
			mdp.setContent(content, "text/html;charset=utf8");
			Multipart mm = new MimeMultipart();
			mm.addBodyPart(mdp);
			message.setContent(mm);
			message.saveChanges();
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据用户名随机产生验证码，这里不能使用base64编码，因为使用base64编码可能会产生‘+’
	 * 等特殊字符，当在浏览器上传输时，‘+’就会变为空格
	 * @param username
	 * @return
	 */
	public static String genCheckCode(String username) {
		double random = new Random().nextDouble();
		String checkCode = username + random;
		return MD5.md5(checkCode);
	}

	private static Session getMailSession() {
		String host = "smtp.163.com"; // 发件人使用发邮件的电子信箱服务器
		Properties props = System.getProperties();
		// 设置邮件服务器
		props.put("mail.smtp.host", host);
		// 取得session
		props.put("mail.smtp.auth", "true"); // 这样才能通过验证
		MyAuthenticator myauth = new MyAuthenticator("sdu_doc@163.com", "sdudoc");
		Session session = Session.getDefaultInstance(props, myauth);
		return session;
	}

}

class MyAuthenticator extends Authenticator {
	private String strUser;
	private String strPwd;

	public MyAuthenticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}

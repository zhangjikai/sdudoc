package com.sdudoc.utils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.sdudoc.bean.User;

/**
 * 用来发送邮件，使用了spring中的线程池，使用户在注册完之后不处于阻塞状态
 * 
 * @author zhangjk
 *
 */
@Component("sendMailHelper")
public class SendMailHelper {

	@Autowired
	private TaskExecutor taskExecutor;

	public void sendMail(User user, int sendType) {
		taskExecutor.execute(new SendThread(user, sendType));
	}

	private class SendThread implements Runnable {
		User user;
		int sendType;

		public SendThread(User user, int sendType) {
			this.user = user;
			this.sendType = sendType;
		}

		@Override
		public void run() {
			switch (sendType) {
			case Constants.SEND_ACTIVE_EMAIL:
				MailUtil.sendAccountActiveEmail(user);
				break;
			case Constants.SEND_RESETPS_EMAIL:
				MailUtil.sendResetPasswordEmail(user);
				break;
			default:
				MailUtil.sendAccountActiveEmail(user);
			}
		}

	}
}

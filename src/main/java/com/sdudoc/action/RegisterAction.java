package com.sdudoc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sdudoc.annotation.MethodDesc;
import com.sdudoc.bean.User;
import com.sdudoc.service.UserService;
import com.sdudoc.utils.*;

@Controller
@ParentPackage("sdudoc")
@Namespace("/")
@Scope("prototype")
@Results({ @Result(name = BaseAction.DYNAMIC, location = "/${url}"),
		@Result(name = BaseAction.INPUT, location = "/register.jsp"),
		@Result(name = BaseAction.SUCCESS, location = "/index.jsp") })
public class RegisterAction extends BaseAction {

	private static final long serialVersionUID = -4012378423417012944L;

	private User user;
	
	private String repassword;
	
	private String random;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "sendMailHelper")
	private SendMailHelper sendMailHelper;

	@Action(value = "registerJsp")
	public String registerJsp() {
		url = "register.jsp";
		return DYNAMIC;
	}

	@Action(value = "resetPwJsp")
	public String resetPwJsp() {
		url = "resetPw.jsp";
		return DYNAMIC;
	}

	
	@Action(value = "register", results = { @Result(name = BaseAction.INPUT, location = "/register.jsp") })
	public String register() {
		if (DocUtil.checkNull(user)) {
			return INPUT;
		}
		String random1 = (String) session.get(Constants.RANDOM);
		if (random1 == null || !random1.equals(random)) {
			addFieldError("random", "验证码错误");
			return INPUT;
		}
		if (userService.getUserByName(user.getUsername()) != null) {
			addFieldError("user.username", "用户名已存在！！！");
			return INPUT;
		}

		/*if (userService.getUserByEmail(user.getEmail()) != null) {
			addFieldError("user.email", "邮箱已注册！！！");
			return INPUT;
		}*/
		user.setCheckCode(MailUtil.genCheckCode(user.getUsername()));
		user.setState(Constants.USER_NOACTICED);
		user.setGroup(Constants.USER_COMMON);
		user.setRegisterDate(new Date());
		userService.addUser(user);
		sendMailHelper.sendMail(user, Constants.SEND_ACTIVE_EMAIL);
		url = "handleSuccess.jsp?handleType=send_active_email";
		return DYNAMIC;
	}

	/** 重新发送激活邮件 */
	@Action("resendActiveEmail")
	public String resendActiveEmail() {
		url = "handleFailure.jsp?handleType=resend_active_email";
		if (DocUtil.checkNull(user) || DocUtil.checkNull(user.getUsername())) {
			return DYNAMIC;
		}

		User user2 = userService.getUserByName(user.getUsername());
		if (user2 == null || user2.getState() == Constants.USER_ACTIVE) {
			return DYNAMIC;
		}

		user2.setCheckCode(MailUtil.genCheckCode(user2.getUsername()));
		userService.updateUser(user2);
		sendMailHelper.sendMail(user2, Constants.SEND_ACTIVE_EMAIL);
		url = "handleSuccess.jsp?handleType=resend_active_email";
		return DYNAMIC;
	}

	@MethodDesc(description = "激活用户")
	@Action(value = "activeUser")
	public String activeUser() {
		url = "handleFailure.jsp?handleType=activeuser";
		if (DocUtil.checkNull(user) || DocUtil.checkNull(user.getUsername(), user.getCheckCode())
				|| !DocUtil.checkNotEquals("", user.getUsername(), "", user.getCheckCode())) {
			return DYNAMIC;
		}

		User user2 = userService.getUserByName(user.getUsername());
		if (DocUtil.checkNull(user2) || DocUtil.checkNotEquals(user.getCheckCode(), user2.getCheckCode())) {
			return DYNAMIC;
		}
		user2.setState(Constants.USER_ACTIVE);
		userService.updateUser(user2);
		url = "handleSuccess.jsp?handleType=activeuser";
		return DYNAMIC;
	}

	// ---------------------------找回密码---------------------------//
	@Action(value = "sendPassWordEmail")
	public String sendPassWordEmail() {
		url = "resetPw.jsp";
		if (DocUtil.checkNull(user) || DocUtil.checkNull(user.getUsername())
				|| !DocUtil.checkNotEquals("", user.getUsername())) {
			addFieldError("user.username", "用户名不能为空");
			return DYNAMIC;
		}

		User user2 = userService.getUserByName(user.getUsername());
		if (user2 == null) {
			addFieldError("user.username", "用户名不存在");
			return DYNAMIC;
		}

		user2.setCheckCode(MailUtil.genCheckCode(user2.getUsername()));
		session.put(Constants.USER_RESETPS_CHECKCODE, user2.getCheckCode());
		session.put(Constants.USER_NAME, user2.getUsername());
		System.out.println(session);
		sendMailHelper.sendMail(user2, Constants.SEND_RESETPS_EMAIL);
		url = "handleSuccess.jsp?handleType=sendPasswordEmail";
		return DYNAMIC;
	}

	@Action(value = "resetPasswordCheck")
	public String resetPasswordCheck() {
		url = "handleFailure.jsp?handleType=resetPw";
		if (DocUtil.checkNull(user) || DocUtil.checkNull(user.getUsername(), user.getCheckCode())
				|| !DocUtil.checkNotEquals("", user.getUsername(), "", user.getCheckCode())) {
			return DYNAMIC;
		}
		System.out.println("session:" + session);
		String username = (String) session.get(Constants.USER_NAME);
		String checkCode = (String) session.get(Constants.USER_RESETPS_CHECKCODE);
		System.out.println(username);
		System.out.println(checkCode);
		System.out.println("user:" + user.getUsername());
		System.out.println("user:" + user.getCheckCode());
		if (DocUtil.checkNull(username, checkCode)
				|| !DocUtil.checkEquals(username, user.getUsername(), checkCode, user.getCheckCode())) {
			return DYNAMIC;
		}
		url = "resetPw2.jsp";
		return DYNAMIC;
	}

	@Action(value = "resetPassword")
	public String resetPassword() {
		url = "handleFailure.jsp?handleType=resetPw";
		if (DocUtil.checkNull(user) || DocUtil.checkNull(user, user.getUsername(), user.getCheckCode())
				|| !DocUtil.checkNotEquals("", user.getUsername(), "", user.getCheckCode())) {
			return DYNAMIC;
		}
		String username = (String) session.get(Constants.USER_NAME);
		String checkCode = (String) session.get(Constants.USER_RESETPS_CHECKCODE);

		if (DocUtil.checkNull(username, checkCode)
				|| !DocUtil.checkEquals(username, user.getUsername(), checkCode, user.getCheckCode())) {
			return DYNAMIC;
		}
		User user2 = userService.getUserByName(username);
		user2.setPassword(MD5.md5_base64(user.getPassword()));
		userService.updateUser(user2);
		url = "handleSuccess.jsp?handleType=resetPw";
		return DYNAMIC;
	}

	/** 检查用户名是否存在，使用ajax调用 */
	@Action("checkUserExists")
	public String checkUserExists() throws IOException {
		String username = request.getParameter("username");
		if (username == null) {
			username = "";
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (userService.getUserByName(username) != null)
			pw.print("exists");
		else
			pw.print("nexists");
		pw.close();
		return SUCCESS;
	}

	/** 检查邮箱是否存在，使用ajax调用 */
	@Action("checkEmailExists")
	public String checkEmailExists() throws IOException {
		String email = request.getParameter("email");
		if (email == null) {
			email = "";
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (userService.getUserByEmail(email) != null)
			pw.print("exists");
		else
			pw.print("nexists");
		pw.close();
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}
}

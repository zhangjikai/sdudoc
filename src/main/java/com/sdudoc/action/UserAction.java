package com.sdudoc.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.*;
import org.springframework.stereotype.Controller;

import com.sdudoc.annotation.MethodDesc;
import com.sdudoc.annotation.Permission;
import com.sdudoc.bean.SysLog;
import com.sdudoc.bean.User;
import com.sdudoc.service.LogService;
import com.sdudoc.service.UserService;
import com.sdudoc.utils.Constants;
import com.sdudoc.utils.MD5;
import com.sdudoc.utils.Pager;

@Controller
@ParentPackage("sdudoc")
@Namespace("/user")
@Results({ @Result(name = BaseAction.DYNAMIC, location = "/${url}"),
		@Result(name = BaseAction.SUCCESS, location = "/index.jsp") })
public class UserAction extends BaseAction {

	private static final long serialVersionUID = -6029948243727453022L;

	@Resource(name = "logService")
	private LogService logService;
	@Resource(name = "userService")
	private UserService userService;

	/** 用户log的当前页码 */
	private int logPageNo = 1;
	/** 每页显示用户log的条数 */
	private int logPageSize = 10;
	/** 存储用户操作的log */
	private Pager<SysLog> logPager;
	
	private User user;

	private String oldPassword;

	private String newPassword;

	private String newPassword2;

	@Permission(Constants.USER_COMMON)
	@Action("userJsp")
	public String userJsp() {
		user = (User) session.get(Constants.USER);
		url = "user_msg.jsp";
		return DYNAMIC;
	}

	@Permission(Constants.USER_COMMON)
	@Action("updatePwJsp")
	public String updatePwJsp() {
		url = "user_uppw.jsp";
		return DYNAMIC;
	}

	@Permission(Constants.USER_COMMON)
	@Action("userLog")
	public String userLog() {
		if (logPageNo <= 0) {
			logPageNo = 1;
		}
		if (logPageSize <= 0) {
			logPageSize = 10;
		}
		User user = (User) session.get(Constants.USER);
		logPager = logService.listLogByUserPage(user.getId(), logPageNo, logPageSize);
		url = "user_logs.jsp";
		setLogMessage(user.getUsername());
		return DYNAMIC;
	}

	@MethodDesc(description = "修改密码")
	@Permission(Constants.USER_COMMON)
	@Action(value = "updatePw", results = { @Result(name = BaseAction.INPUT, location = "/user_uppw.jsp") })
	public String updatePw() {
		User user = (User) session.get(Constants.USER);
		if (user.getPassword().equals(MD5.md5_base64(oldPassword))) {
			user.setPassword(MD5.md5_base64(newPassword));
			userService.updateUser(user);
			url = "handleSuccess.jsp?handleType=updatePw";
			setLogMessage("成功");
		} else {
			addFieldError("oldPassword", "原密码错误");
			setLogMessage("失败");
			return INPUT;
		}
		return DYNAMIC;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getLogPageSize() {
		return logPageSize;
	}

	public void setLogPageSize(int logPageSize) {
		this.logPageSize = logPageSize;
	}

	public int getLogPageNo() {
		return logPageNo;
	}

	public void setLogPageNo(int logPageNo) {
		this.logPageNo = logPageNo;
	}

	public Pager<SysLog> getLogPager() {
		return logPager;
	}

	public void setLogPager(Pager<SysLog> logPager) {
		this.logPager = logPager;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
}

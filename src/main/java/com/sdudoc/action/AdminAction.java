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
import com.sdudoc.utils.Pager;

@Controller
@ParentPackage("sdudoc")
@Namespace("/admin")
@Results({ @Result(name = BaseAction.DYNAMIC, location = "/${url}"),
		@Result(name = BaseAction.SUCCESS, location = "/index.jsp") })
public class AdminAction extends BaseAction {

	private static final long serialVersionUID = 8619214125214963153L;
	@Resource(name = "logService")
	private LogService logService;
	@Resource(name = "userService")
	private UserService userService;

	/** 用户列表的当前页码 */
	private int userPageNo = 1;
	/** 每页显示用户的记录数 */
	private int userPageSize = 10;
	/** 存储用户列表 */
	private Pager<User> userPager;
	/** 选中的用户 Id*/
	private int selectedUserId;
	
	private String selectedUsername;

	private int logPageNo = 1;
	private int logPageSize = 10;

	private Pager<SysLog> logPager;

	@Permission(Constants.USER_ADMIN)
	@Action("listUsers")
	public String listUsers() {
		if (userPageNo <= 0)
			userPageNo = 1;
		if (userPageSize <= 0)
			userPageSize = 10;
		userPager = userService.listUsers(userPageNo, userPageSize);
		url = "admin_users.jsp";
		return DYNAMIC;
	}

	@Permission(Constants.USER_ADMIN)
	@Action("listUserLogs")
	public String listUserLogs() {
		if (logPageNo <= 0)
			logPageNo = 1;
		if (logPageSize <= 0)
			logPageSize = 10;
		logPager = logService.listLogByUserPage(selectedUserId, logPageNo, logPageSize);
		url = "admin_user_logs.jsp";
		return DYNAMIC;
	}

	@Permission(Constants.USER_ADMIN)
	@Action("listAllLogs")
	public String listAllLogs() {
		if (logPageNo <= 0)
			logPageNo = 1;
		if (logPageSize <= 0)
			logPageSize = 10;
		logPager = logService.listLogAllByPage(logPageNo, logPageSize);
		url = "admin_logs.jsp";
		return DYNAMIC;
	}
	
	@MethodDesc(description = "删除用户")
	@Permission(Constants.USER_ADMIN)
	@Action("delUserById")
	public String deleteUserById(){
		User userLogin = (User) session.get(Constants.USER);
		int group = userLogin.getGroup();
		boolean flag = userService.deleteUserById(selectedUserId, group);
		if(flag) {
			setLogMessage(selectedUsername + " 成功");
			url = "handleSuccess.jsp?handleType=deleteUser";
		} else {
			setLogMessage(selectedUsername + " 失败");
			url = "handleFailure.jsp?handleType=deleteUser";
		}
		return DYNAMIC;
	}

	public int getUserPageNo() {
		return userPageNo;
	}

	public void setUserPageNo(int userPageNo) {
		this.userPageNo = userPageNo;
	}

	public int getUserPageSize() {
		return userPageSize;
	}

	public void setUserPageSize(int userPageSize) {
		this.userPageSize = userPageSize;
	}

	public Pager<User> getUserPager() {
		return userPager;
	}

	public void setUserPager(Pager<User> userPager) {
		this.userPager = userPager;
	}

	public int getSelectedUserId() {
		return selectedUserId;
	}

	public void setSelectedUserId(int selectedUserId) {
		this.selectedUserId = selectedUserId;
	}

	public int getLogPageNo() {
		return logPageNo;
	}

	public void setLogPageNo(int logPageNo) {
		this.logPageNo = logPageNo;
	}

	public int getLogPageSize() {
		return logPageSize;
	}

	public void setLogPageSize(int logPageSize) {
		this.logPageSize = logPageSize;
	}

	public Pager<SysLog> getLogPager() {
		return logPager;
	}

	public void setLogPager(Pager<SysLog> logPager) {
		this.logPager = logPager;
	}

	public String getSelectedUsername() {
		return selectedUsername;
	}

	public void setSelectedUsername(String selectedUsername) {
		this.selectedUsername = selectedUsername;
	}

}

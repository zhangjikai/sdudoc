package com.sdudoc.utils;

public class Constants {

	/** 用户状态） */
	public static final int USER_NOACTICED = 0;
	public static final int USER_ACTIVE = 1;
	/** 用户类别，数字越大，权限越大，这里只有两类，普通用户和管理员 */
	public static final int USER_COMMON = 1;
	public static final int USER_ADMIN = 10;
	
	/** 发送邮件的类型 */
	public static final int SEND_ACTIVE_EMAIL = 20;
	public static final int SEND_RESETPS_EMAIL = 21;
	/** 文档展示类型 */
	public static final int SHOW_JPRG = 0;
	public static final int SHOW_PNG = 1;
	public static final int SHOW_HTML = 2;
	
	/** 用户操作类型 */
	public static final int OP_COMMON = 0;
	public static final int OP_CLICK = 1;
	
	/** 重置密码 */
	public static final String USER_RESETPS_CHECKCODE = "user_resetps_checkCode";
	public static final String USER_NAME = "username";
	/** 用户 */
	public static final String USER = "user";
	/** 验证码 */
	public static final String RANDOM = "random";
	/** cookie */
	public static final String COOKIE_USERNAME = "cookie_username";
	public static final String COOKIE_PASSWORD = "cookie_password";
	/** 没有用户权限 */
	public static final String NO_PERMISSION = "nopermission";
	
	
	
}

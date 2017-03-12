package com.sdudoc.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 用户信息
 * @author zhangjk
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -7573323256677751142L;

	/** 用户Id */
	private int id;
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
	/** 邮箱 */
	private String email;
	/** 注册时间 */
	private Date registerDate;
	/** 校验码（用于邮箱验证以及密码重置） */
	private String checkCode;
	/** 用户状态（是否进行邮箱验证） */
	private int state;
	/** 用户类型（普通用户/管理员） */
	private int group;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", length = 11, nullable = false, unique = true)
	public int getId() {
		return id;
	}

	@Column(name = "userName", length = 50, nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	@Column(name = "passWord", length = 50, nullable = false)
	public String getPassword() {
		return password;
	}

	@Column(name = "userEmail", length = 30, nullable = false)
	public String getEmail() {
		return email;
	}

	@Column(name = "registerDate")
	public Date getRegisterDate() {
		return registerDate;
	}

	@Column(name = "checkCode", length = 50)
	public String getCheckCode() {
		return checkCode;
	}

	@Column(name = "userState", nullable = false, columnDefinition = "int(2) default 0")
	public int getState() {
		return state;
	}

	@Column(name = "userGroup", nullable = false, columnDefinition = "int(2) default 1")
	public int getGroup() {
		return group;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setGroup(int group) {
		this.group = group;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", state="
				+ state + ", registerDate=" + registerDate + ", checkCode=" + checkCode + "]";
	}

}

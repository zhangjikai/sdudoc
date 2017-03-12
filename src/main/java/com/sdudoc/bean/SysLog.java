package com.sdudoc.bean;

import java.util.Date;

import javax.persistence.*;

/**
 * 记录系统中的用户的各种操作
 * @author zhangjk
 *
 */

@Entity
@Table(name = "log")
public class SysLog {

	private int id;
	/** 进行操作的用户 */
	private User user;
	/** 用户所进行的操作 */
	private String method;
	/** 进行操作时的IP */
	private String ip;
	/** 操作时间 */
	private Date time;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logId", length = 11, nullable = false, unique = true)
	public int getId() {
		return id;
	}

	/**
	 * 这里使用了关联映射，一对一外键单向关联,user表中的userid作为本字段的外键
	 * 使用延迟加载，需要在web.xml中配置open session in view 属性
	 * @return
	 */
	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	/*@Column(name = "userID", length = 11, nullable = false)
	public int getUserId() {
		return userId;
	}*/

	@Column(name = "method", length = 100)
	public String getMethod() {
		return method;
	}

	@Column(name = "ip", length = 20)
	public String getIp() {
		return ip;
	}

	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public void setUserId(int userId) {
		this.userId = userId;
	}*/
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}

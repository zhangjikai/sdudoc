package com.sdudoc.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 用户收藏
 * @author zhangjk
 *
 */
@Entity
@Table(name = "collect")
public class Collect implements Serializable {

	private static final long serialVersionUID = -8552636962437516470L;

	private int id;
	/** 用户Id */
	private int userId;
	/** 用户名 */
	private String username;
	/** 书籍Id */
	private int bookId;
	/** 书名 */
	private String bookTitle;
	/** 收藏时间 */
	private Date collectDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collectId", length = 11, nullable = false, unique = true)
	public int getId() {
		return id;
	}

	
	@Column(name = "userID", length = 11, nullable = false)
	public int getUserId() {
		return userId;
	}

	@Column(name = "userName", length = 50, nullable = false)
	public String getUsername() {
		return username;
	}

	@Column(name = "bookID", length = 11, nullable = false)
	public int getBookId() {
		return bookId;
	}

	@Column(name = "bookTitle", length = 50, nullable = false)
	public String getBookTitle() {
		return bookTitle;
	}

	@Column(name = "collectDate")
	public Date getCollectDate() {
		return collectDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}
}

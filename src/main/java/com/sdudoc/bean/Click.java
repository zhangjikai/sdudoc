package com.sdudoc.bean;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 保存用户的点击信息，包括点击的用户以及对应的书籍
 * 此信息加上用户收藏，共同完成推荐功能
 * @author zhangjk
 *
 */
@Entity
@Table(name = "click")
public class Click implements Serializable {
	private static final long serialVersionUID = 5575556453868680619L;

	private int id;

	/*private int userId;

	private int bookId;*/

	private User user;
	private Book book;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clickId", length = 11, nullable = false, unique = true)
	public int getId() {
		return id;
	}

	/*@Column(name = "userId", length = 11)
	public int getUserId() {
		return userId;
	}

	@Column(name = "bookId", length = 11)
	public int getBookId() {
		return bookId;
	}*/

	/**
	 * 不使用关联映射，使用关联映射保存Click信息的时候会将User的信息重新更新一遍
	 */
	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}

	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name="bookID")
	public Book getBook() {
		return book;
	}
	public void setId(int id) {
		this.id = id;
	}

	/*public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}*/

	public void setUser(User user) {
		this.user = user;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}

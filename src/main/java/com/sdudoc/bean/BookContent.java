package com.sdudoc.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "bookContent")
public class BookContent implements Serializable{
	
	private static final long serialVersionUID = 6038127732080163844L;
	/** 书籍编号*/
	private int bookID;
	/** 书籍内容*/
	private String bookContent;
	
	//主键生成方式由程序控制
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookID",length = 11,nullable = false,unique = true)
	public int getBookID() {
		return bookID;
	}
	
	@Column(name = "bookContent",nullable = false)
	public String getBookContent() {
		return bookContent;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}

	@Override
	public String toString() {
		return "BookContent [bookID=" + bookID + ", bookContent=" + bookContent
				+ "]";
	}
	
	
	
}

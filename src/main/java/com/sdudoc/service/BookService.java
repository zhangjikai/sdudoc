package com.sdudoc.service;

import java.util.List;

import com.sdudoc.bean.Book;
import com.sdudoc.utils.Pager;

public interface BookService {
	
	//分模块查看古籍阅读量，以体例及年代为例
	public Pager<Book> showBookByDynasty(String dynasty,int pageNo, int pageSize);
		
	public Pager<Book> showBookByStyle(String style,int pageNo, int pageSize);
	
	public Pager<Book> searchByTitle(String title,int pageNo,int pageSize);
	
	public Pager<Book> searchByAuthor(String author,int pageNo,int pageSize);
	
	public Pager<Book> showBookByClickTimes(int pageNo,int pageSize);
	
	public void addBook(Book book);
	
	public List checkDynasty();
	
	public List checkStyle();
	
	public Book getBookById(int bookId);
	
	public void updateClickTime(int bookId, int clickTime);
}

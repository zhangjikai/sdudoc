package com.sdudoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdudoc.bean.Book;
import com.sdudoc.dao.BookDao;
import com.sdudoc.service.BookService;
import com.sdudoc.utils.Pager;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Resource(name="bookDao")
	public BookDao bookDao;
	
	@Override
	public Pager<Book> showBookByDynasty(String dynasty, int pageNo,int pageSize) {
		System.out.println("---执行到BookServiceImpl");
		return bookDao.showBookByDynasty(dynasty, pageNo, pageSize);
	}

	@Override
	public Pager<Book> showBookByStyle(String style, int pageNo, int pageSize) {
		return bookDao.showBookByStyle(style, pageNo, pageSize);
	}

	@Override
	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	@Override
	public List checkDynasty() {
		return bookDao.checkDynasty();
	}

	@Override
	public List checkStyle() {
		return bookDao.checkStyle();
	}

	@Override
	public Book getBookById(int bookId) {
		return bookDao.getBookById(bookId);
	}

	@Override
	public void updateClickTime(int bookId, int clickTime) {
		bookDao.updateClickTime(bookId, clickTime);
	}
	
	public Pager<Book> searchByTitle(String title, int pageNo, int pageSize) {
		return bookDao.searchByTitle(title, pageNo, pageSize);
	}

	@Override
	public Pager<Book> searchByAuthor(String author, int pageNo, int pageSize) {
		return bookDao.searchByAuthor(author, pageNo, pageSize);
	}

	@Override
	public Pager<Book> showBookByClickTimes(int pageNo, int pageSize) {
		return bookDao.showBookByClickTimes(pageNo, pageSize);
	}
		
	
}

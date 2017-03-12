package com.sdudoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdudoc.bean.Book;
import com.sdudoc.dao.BookDao;
import com.sdudoc.dao.ClickDao;
import com.sdudoc.dao.CollectDao;
import com.sdudoc.service.RecommendService;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService{

	@Resource(name="clickDao")
	private ClickDao clickDao;
	
	@Resource(name="bookDao")
	private BookDao bookDao;
	
	@Resource(name="collectDao")
	private CollectDao collectDao;
	
	@Override
	public List<Book> getRecommeds(int userId) {
		List<String> collectDynasty = collectDao.getRecommend(userId);
		List<Book> collectBooks= new ArrayList<Book>();
		if(collectDynasty.size() > 0) {
			collectBooks = bookDao.showBookByDynasty(collectDynasty.get(0), 1, 2).getRecords();
		}
		
		System.out.println(collectBooks);
		
		List<String> clickDynasty = clickDao.getRecommends(userId);
		List<Book> clickBooks = new ArrayList<Book>();
		if(clickDynasty.size() > 0) {
			clickBooks = bookDao.showBookByDynasty(clickDynasty.get(0), 1, 2).getRecords();
		}
		
		/*System.out.println(clickBooks);*/
		for(Book book : clickBooks) {
			System.out.println("==========================");
			System.out.println(book);
			System.out.println("==========================");
		}
		
		List<Book> totalBooks= new ArrayList<Book>();
		boolean flag = true;
		for(Book book : collectBooks) {
			flag= true;
			for(Book book2 : totalBooks) {
				if(book.getBookID() == book2.getBookID()) {
					flag = false;
					break;
				}
			}
			if(flag) {
				totalBooks.add(book);
			}
		}
		
		for(Book book : clickBooks) {
			flag= true;
			for(Book book2 : totalBooks) {
				if(book.getBookID() == book2.getBookID()) {
					flag = false;
					break;
				}
			}
			if(flag) {
				totalBooks.add(book);
			}
		}
		
		if(totalBooks.size() < 4) {
			List<Book> books = bookDao.showBookByClickTimes(1, 4).getRecords();
			for(Book book : books) {
				flag= true;
				for(Book book2 : totalBooks) {
					if(book.getBookID() == book2.getBookID()) {
						flag = false;
						break;
					}
				}
				if(flag) {
					totalBooks.add(book);
				}
			}
		}
		
		System.out.println("bookSize:" + totalBooks.size());
		
		return totalBooks;
	}

	@Override
	public List<String> getCollect(int userId) {
		return collectDao.getRecommend(userId);
	}

}

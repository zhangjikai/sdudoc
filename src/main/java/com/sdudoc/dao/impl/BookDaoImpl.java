package com.sdudoc.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sdudoc.bean.Book;
import com.sdudoc.dao.BookDao;
import com.sdudoc.utils.Pager;

@SuppressWarnings("unchecked")
@Repository("bookDao")
public class BookDaoImpl implements BookDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Pager<Book> showBookByDynasty(String dynasty, int pageNo,int pageSize) {
		System.out.println("-----执行到BookDaoImpl");
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq("dynasty", dynasty));
		long recordTotal = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		criteria.setProjection(null);
		criteria.addOrder(Order.desc("clickTimes"));
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		List<Book> results = criteria.list();
		Pager<Book> page=new Pager<Book>(pageSize, pageNo, recordTotal, results);
		return page;
		
	}

	@Override
	public Pager<Book> showBookByStyle(String style, int pageNo, int pageSize) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq("bookStyle", style));
		long recordTotal = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		criteria.setProjection(null);
		criteria.addOrder(Order.desc("clickTimes"));
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		List<Book> results = criteria.list();
		Pager<Book> page=new Pager<Book>(pageSize, pageNo, recordTotal, results);
		return page;
	}
	
	@Override
	public void addBook(Book book){
		/*
		Book book =new Book();
		book.setAuthors("张飞");
		book.setBookClass("文");
		book.setBookPatterns("著");
		book.setBookPosition("高");
		book.setBookStyle("纪传体");
		book.setBookTitle("三国");
		book.setBookVersion("孤本");
		book.setClickTimes(10);
		book.setDynasty("唐");
		book.setRelatedBook("三国演义");
		book.setSummary("好书");
		book.setTotalChapter(12);
		book.setTotalVolume(123);
		Date date=new Date();
		book.setYear(date);
		System.out.println("添加书籍");
		Session session = sessionFactory.getCurrentSession();
		for(int i=0;i<100;i++){
			
			session.save(book);
		}
		*/
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
	}

	@Override
	public Pager<Book> searchByTitle(String title, int pageNo, int pageSize) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.like("bookTitle", "%"+title+"%"));
		long recordTotal = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		criteria.setProjection(null);
	//	criteria.addOrder(Order.desc("clickTimes"));
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		List<Book> results = criteria.list();
		System.out.println(results.size()+"-------数据多少");
		Pager<Book> page=new Pager<Book>(pageSize, pageNo, recordTotal, results);
		return page;
	}

	@Override
	public Pager<Book> searchByAuthor(String author, int pageNo, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.like("authors", "%"+author+"%"));
		long recordTotal = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		criteria.setProjection(null);
	//	criteria.addOrder(Order.desc("clickTimes"));
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		List<Book> results = criteria.list();
		Pager<Book> page=new Pager<Book>(pageSize, pageNo, recordTotal, results);
		return page;
	}

	@Override
	public List checkDynasty() {
		Session session = sessionFactory.getCurrentSession();
		String hql="select dynasty from Book group by dynasty";
		Query query=session.createQuery(hql);
		List dynastyList=query.list();
		return dynastyList;
	}

	@Override
	public List checkStyle() {
		Session session = sessionFactory.getCurrentSession();
		String hql="select bookStyle from Book group by bookStyle";
		Query query=session.createQuery(hql);
		List styleList=query.list();
		return styleList;
	}

	@Override
	public Book getBookById(int bookId) {
		Session session = sessionFactory.getCurrentSession();
		Book book = (Book) session.load(Book.class, bookId);
		return book;
	}

	@Override
	public void updateClickTime(int bookId, int clickTime) {
		String hql = "update Book set clickTimes=:clickTimes where bookID=:bookId";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger("clickTimes", clickTime);
		query.setInteger("bookId", bookId);
		query.executeUpdate();
		
	}
	public Pager<Book> showBookByClickTimes(int pageNo, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class);
		long recordTotal = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		criteria.setProjection(null);
		criteria.addOrder(Order.desc("clickTimes"));
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		List<Book> results = criteria.list();
		Pager<Book> page=new Pager<Book>(pageSize, pageNo, recordTotal, results);
		return page;
		
	}
	
}

package com.sdudoc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sdudoc.bean.Click;
import com.sdudoc.dao.ClickDao;

@SuppressWarnings("unchecked")
@Repository("clickDao")
public class ClickDaoImpl implements ClickDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addClick(Click click) {
		Session session = sessionFactory.getCurrentSession();
		session.save(click);
	}

	
	@Override
	public List<String> getRecommends(int userId) {
		String sql = "select b.dynasty  from Click c, Book b where c.book.bookID = b.bookID and c.user.id=:userId group by b.dynasty having" +
				" count(c.book.bookID) >= all (select count(c1.book.bookID) from Click c1, Book b1 where c1.book.bookID = b1.bookID and c1.user.id=:userId1 group by b1.dynasty)";
		Session session = sessionFactory.getCurrentSession(); 
		Query query = session.createQuery(sql);
		query.setInteger("userId", userId);
		query.setInteger("userId1", userId);
		List<String> lists = query.list();
		return lists;
	}
}

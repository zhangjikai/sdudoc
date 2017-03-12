package com.sdudoc.dao.impl;

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

import com.sdudoc.bean.Collect;
import com.sdudoc.dao.CollectDao;
import com.sdudoc.utils.Pager;

@SuppressWarnings("unchecked")
@Repository("collectDao")
public class CollectDaoImpl implements CollectDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addCollect(Collect collect) {
		Session session = sessionFactory.getCurrentSession();
		session.save(collect);
	}

	@Override
	public boolean deleteCollect(int collectId, int userId) {
		String sql = "delete from Collect where id=:id and userId=:userId";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setInteger("id", collectId);
		query.setInteger("userId", userId);
		int num = query.executeUpdate();
		if(num > 0)
			return true;
		return false;
	}
	
	@Override
	public Collect getCollectByBook$User(int bookId, int userId) {
		String sql = "from Collect where bookId=:bookId and userId=:userId";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setInteger("bookId", bookId);
		query.setInteger("userId", userId);
		List<Collect> collects = query.list();
		if(collects.size() == 0)
			return null;
		return collects.get(0);
	}

	@Override
	public Pager<Collect> listCollectsByUser(int userId, int pageNo, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Collect.class);
		criteria.add(Restrictions.eq("userId", userId));
		long recordTotal = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		criteria.setProjection(null);
		criteria.addOrder(Order.desc("collectDate"));
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		List<Collect> results = criteria.list();
		return new Pager<Collect>(pageSize, pageNo, recordTotal, results);
	}

	@Override
	public List<String> getRecommend(int userId) {
		String sql = "select b.dynasty from Collect c, Book b where c.bookId = b.bookID and c.userId=:userId group by b.dynasty having" +
				" count(c.bookId) >= all (select count(c1.bookId) from Collect c1, Book b1 where c1.bookId = b1.bookID and c1.userId=:userId1 group by b1.dynasty)";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setInteger("userId", userId);
		query.setInteger("userId1", userId);
		return query.list();
	}
}

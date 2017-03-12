package com.sdudoc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sdudoc.bean.User;
import com.sdudoc.dao.UserDao;
import com.sdudoc.utils.MD5;
import com.sdudoc.utils.Pager;

@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		user.setPassword(MD5.md5_base64(user.getPassword()));
		session.save(user);
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
	
	@Override
	public boolean deleteUserById(int userId, int group) {
		String sql = "delete from User where id=:id and group<:group";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setInteger("id", userId);
		query.setInteger("group", group);
		int  num = query.executeUpdate();
		if(num > 0)
			return true;
		return false;
	}

	
	@Override
	public User getUserByEmail(String email) {
		String sql = "from User where email=:email";
		//String sql = "select id from User where email=:email";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setString("email", email);
		List<User> users = query.list();
		if (users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public User getUserByName(String username) {
		String sql = "from User where username=:username";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setString("username", username);
		List<User> users = query.list();
		if (users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public User getUserByName$Pw(String username, String password) {
		String sql = "from User where username=:username and password=:password";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setString("username", username);
		query.setString("password", password);
		List<User> users = query.list();
		if(users.size() == 0) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public Pager<User> listUserByPage(int pageNo, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		long recordTotal = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		criteria.setProjection(null);
		criteria.addOrder(Order.desc("registerDate"));
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		List<User> results = criteria.list();
		return new Pager<User>(pageSize, pageNo, recordTotal, results);
	}
}

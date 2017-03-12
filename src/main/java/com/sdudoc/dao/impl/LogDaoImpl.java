package com.sdudoc.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import com.sdudoc.bean.SysLog;
import com.sdudoc.dao.LogDao;
import com.sdudoc.utils.Pager;

@Repository("logDao")
public class LogDaoImpl implements LogDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addLog(SysLog sysLog) {
		Session session = sessionFactory.getCurrentSession();
		session.save(sysLog);
	}

	@Override
	public void deleteLog(SysLog sysLog) {

	}

	@Override
	public void deleteLogDaysAgo(int days) {
		String sql = "delete from SysLog where time<:date";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -days);
		date = calendar.getTime(); 
		System.out.println(date);
		query.setDate("date", date);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysLog> listLogByUserId(int userId) {
		String sql = "from SysLog where user.id=:userId";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setInteger("userId", userId);
		List<SysLog> sysLogs = query.list();
		return sysLogs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SysLog> listLogByUserPage(int userId, int pageNo, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SysLog.class);
		criteria.add(Restrictions.eq("user.id", userId));
		long recordTotal = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		criteria.setProjection(null);
		criteria.addOrder(Order.desc("time"));
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		List<SysLog> results = criteria.list();
		return new Pager<SysLog>(pageSize, pageNo, recordTotal, results);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SysLog> listLogAllByPage(int pageNo, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SysLog.class);
		long recordTotal = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		criteria.setProjection(null);
		criteria.addOrder(Order.desc("time"));
		criteria.setFirstResult((pageNo - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		List<SysLog> results = criteria.list();
		return new Pager<SysLog>(pageSize, pageNo, recordTotal, results);
	}

}

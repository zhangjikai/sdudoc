package com.sdudoc.utils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	public static final ThreadLocal<Session> SESSIONMAP = new ThreadLocal<Session>();
	private static final SessionFactory sessionFactory;
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);

	static {
		try {
			LOGGER.debug("HibernateUtil.static - loading config");
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			ServiceRegistry sr = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(sr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Session getSession() {
		Session session = SESSIONMAP.get();
		if (session == null) {
			session = sessionFactory.openSession();
			SESSIONMAP.set(session);
		}
		return session;
	}

	public static void closeSession() {
		Session session = SESSIONMAP.get();
		SESSIONMAP.set(null);
		if (session != null) {
			session.close();
		}
	}

}

package com.execution;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_rev.model.Address;
import hibernate_rev.model.Employee;
import hibernate_rev.model.Task;

public class DbUtils {
	SessionFactory sessionFactory = null;
	Session session = null;
	Configuration configuration = null;

	public Configuration getConfiguaration() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Employee.class);
		configuration.addAnnotatedClass(Address.class);
		configuration.addAnnotatedClass(Task.class);
		// instead of using above use this one
//		configuration.addPackage("hibernate_rev.model"); // Replace with your package(s)
		return configuration;
	}

	public Session getSession() {
		configuration = this.getConfiguaration();
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.getCurrentSession();
		return session;
	}
	
	public static CriteriaBuilder getCriteriaBuilder(Session session) {
		return session.getCriteriaBuilder();
	}
}

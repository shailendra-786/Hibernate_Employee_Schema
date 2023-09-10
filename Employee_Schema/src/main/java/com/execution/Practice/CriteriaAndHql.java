package com.execution.Practice;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Session;

import com.execution.DbUtils;

import hibernate_rev.model.Employee;

public class CriteriaAndHql {
	CriteriaBuilder cb = null;
	List<Employee> listEmployee = null;

	public List<String> getSingleColumData(Session session) {
		cb = DbUtils.getCriteriaBuilder(session);

		CriteriaQuery<String> query = cb.createQuery(String.class);
		Root<Employee> root = query.from(Employee.class);

		Predicate p_in = root.get("first_name").in("Emma", "Michael");

		Predicate p_not_in = root.get("first_name").in("Emma", "Michael").not();

		Predicate p_like = cb.like(root.get("first_name"), "%Emma%");

		Predicate p_not_like = cb.like(root.get("first_name"), "%Emma%").not();

		Predicate p_and = cb.and(p_in, p_not_like);

		Predicate p_or = cb.or(p_in, p_not_like);

		Predicate p_equal = cb.equal(cb.upper(root.get("first_name")), "emma".toUpperCase());

		Predicate p_not_equal = cb.notEqual(cb.upper(root.get("first_name")), "emma".toUpperCase());

		query.orderBy(cb.desc(root.get("last_name")));

		query.select(root.get("first_name")).where(p_not_equal);

//		List<String> resultList = session.createQuery(query).setMaxResults(2).getResultList();
		List<String> resultList = session.createQuery(query).getResultList();

		return resultList;

	}

	public List<String> getSubquery(Session session) {
		cb = DbUtils.getCriteriaBuilder(session);
		CriteriaQuery<String> query = cb.createQuery(String.class);
		Root<Employee> root = query.from(Employee.class);

		Subquery<String> subquery = query.subquery(String.class);
		Root<Employee> subRoot = subquery.from(Employee.class);
		subquery.select(subRoot.get("first_name")).where(cb.notEqual(subRoot.get("first_name"), "Emma"));

		query.select(root.get("first_name")).where(cb.in(root.get("first_name")).value(subquery).not());


		List<String> resultList = session.createQuery(query).getResultList();

		return resultList;}
}

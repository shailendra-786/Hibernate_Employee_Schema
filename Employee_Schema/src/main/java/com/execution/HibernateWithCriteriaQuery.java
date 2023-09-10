package com.execution;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Session;

import hibernate_rev.model.Address;
import hibernate_rev.model.Employee;

public class HibernateWithCriteriaQuery {
	CriteriaBuilder criteriaBuilder = null;
	List<Employee> listEmployee = null;

	// Fetch all entities
	public List<Employee> getAllEmployee(Session session) {
		criteriaBuilder = DbUtils.getCriteriaBuilder(session);
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.select(root);

		listEmployee = session.createQuery(criteriaQuery).list();
		System.out.println("data is fetched " + listEmployee);
		return listEmployee;

	}

	// Fetch entities with a specific condition (WHERE clause):
	public List<Employee> getEmployeeWithWhere(Session session) {
		criteriaBuilder = DbUtils.getCriteriaBuilder(session);
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("first_name"), "John"));
		listEmployee = session.createQuery(criteriaQuery).getResultList();
		System.out.println("data is fetched " + listEmployee);
		return listEmployee;
	}

//	Fetch entities with sorting:
	public List<Employee> getEmployeeWithSort(Session session) {
		criteriaBuilder = DbUtils.getCriteriaBuilder(session);
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("first_name")));
		listEmployee = session.createQuery(criteriaQuery).list();

		return listEmployee;
	}

//	fetch entities with pagination:

	public List<Employee> getEmployeeWithPagination(Session session) {
		criteriaBuilder = DbUtils.getCriteriaBuilder(session);
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.select(root);

		TypedQuery<Employee> typedQuery = session.createQuery(criteriaQuery);
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(5);
		listEmployee = typedQuery.getResultList();

		return listEmployee;
	}

//	Fetch specific attributes (projection):

	public List<String> getEmployeeWithColumnName(Session session) {
		criteriaBuilder = DbUtils.getCriteriaBuilder(session);
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.select(root.get("first_name"));

		List<String> listColumn = session.createQuery(criteriaQuery).getResultList();

		return listColumn;
	}

//	Fetch entities using IN clause:
	public List<Employee> getEmployeeWithInOperator(Session session) {
		criteriaBuilder = DbUtils.getCriteriaBuilder(session);
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
//		criteriaQuery.select(criteriaBuilder.count(root)); for count
		criteriaQuery.select(root).where(root.get("first_name").in(Arrays.asList("John", "Jane")));
		listEmployee = session.createQuery(criteriaQuery).list();
		return listEmployee;
	}

	// like operator
	public List<Employee> getEmployeeWithLikeOperator(Session session) {
		criteriaBuilder = DbUtils.getCriteriaBuilder(session);
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.where(criteriaBuilder.like(root.get("first_name"), "J%"));
		// Fetch employees whose 'firstName' contains "john" (case-insensitive)
//		criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%john%"));

		listEmployee = session.createQuery(criteriaQuery).list();
		return listEmployee;
	}

	// how to fetch multiple column like select column,column from table
	public List<Tuple> getEmployeeWithCommonQuery(Session session) {
		criteriaBuilder = DbUtils.getCriteriaBuilder(session);
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.select(criteriaBuilder.tuple(root.get("first_name").alias("first_name"),
				root.get("last_name").alias("last_name")));

		List<Tuple> result = session.createQuery(criteriaQuery).getResultList();
		return result;
	}

	public List<String> getEmployeeNameByAddressState(Session session) {
		criteriaBuilder = DbUtils.getCriteriaBuilder(session);
		Subquery<String> subquery = criteriaBuilder.createQuery(String.class).subquery(String.class);
		Root<Address> subqueryRoot = subquery.from(Address.class);
		subquery.select(subqueryRoot.get("employee").get("employee_id"))
				.where(subqueryRoot.get("state").in(Arrays.asList("CA", "NY")));

		CriteriaQuery<String> mainQuery = criteriaBuilder.createQuery(String.class);
		Root<Employee> root = mainQuery.from(Employee.class);
		mainQuery.select(root.get("first_name")).where((root.get("employee_id").in(subquery)));

		List<String> employeeNames = session.createQuery(mainQuery).getResultList();
		System.out.println(employeeNames);

		return employeeNames;
	}

	// Assuming 'criteriaBuilder' is your CriteriaBuilder instance, and 'root' is
	// the Root object representing your entity

	// Constructing a query to fetch employees with both 'department' equal to 'IT'
	// and 'salary' greater than 50000
//	CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//	Predicate condition = criteriaBuilder.and(
//	    criteriaBuilder.equal(root.get("department"), "IT"),
//	    criteriaBuilder.greaterThan(root.get("salary"), 50000)
//	);
//	criteriaQuery.where(condition);
//
//	List<Employee> employees = entityManager.createQuery(criteriaQuery).getResultList();

	// Constructing a query to fetch employees with either 'department' equal to
	// 'IT' or 'HR'
//	CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//	Predicate condition = criteriaBuilder.or(
//	    criteriaBuilder.equal(root.get("department"), "IT"),
//	    criteriaBuilder.equal(root.get("department"), "HR")
//	);
//	criteriaQuery.where(condition);
//
//	List<Employee> employees = entityManager.createQuery(criteriaQuery).getResultList();

}

package com.execution;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;

import hibernate_rev.model.Address;
import hibernate_rev.model.Employee;
import hibernate_rev.model.Task;

public class UtilityHibernate {

	@Transactional
	public void saveEmpAndAddress(Session session) {
		try {
			Address address = new Address("Eastern Highway", "Thane", "Maharashtra", 400601);
			Employee employee = new Employee("Apoorva", "More", "Last date 15 oct");
			address.setEmployee(employee);
			employee.setAddress(address);
			session.save(employee);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Transactional
	public void getEmpAndSaveTask(Session session, Long employee_id) {
		Employee emp = (Employee) session.get(Employee.class, employee_id);
		Task task = new Task();
		task.setEmployee(emp);
		task.setTask_name("onetomany relationship");
		task.setDescription("employee to task many to one relationship");
		Task task1 = new Task();
		task1.setEmployee(emp);
		task1.setTask_name("onetomany relationship");
		task1.setDescription("task to employee many to one relationship");
		session.save(task);
		session.save(task1);
	}

	public void saveEmpAndAddressAndTask(Session session) {
		System.out.println("reach here ");
		try {
			Employee employee = new Employee("Sushil", "Nigam", "No idea");

			Address address = new Address("Khaler road", "Thane", "Maharashtra", 400606);
			address.setEmployee(employee);
			employee.setAddress(address);

			Task t1 = new Task("Titration", "deadline 2 hours");
			t1.setEmployee(employee);

			Task t2 = new Task("Do it for tomorrow", "deadline next day");
			t2.setEmployee(employee);

			List<Task> taskList = new ArrayList<>();
			taskList.add(t1);
			taskList.add(t2);

			employee.setTask(taskList);
			session.save(employee);

		} catch (Exception e) {
			System.out.println("Exception in saveEmpAndAddressAndTask" + e);
		}

	}

	public void deleteEmplyee(Session session, long employee_id) {
		try {
			Employee emp = (Employee) session.get(Employee.class, employee_id);
			session.delete(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Employee getEmplyee(Session session, long emp_id) {
		Employee emp = null;
		try {
//		 emp = (Employee) session.get(Employee.class, emp_id);
			emp = (Employee) session.load(Employee.class, emp_id);
			System.out.println(emp.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		return emp;
	}
	
	public List<Employee> getAllEmployee(Session session){
		List<Employee> listEmployee = session.createQuery("FROM Employee", Employee.class).list();
		return listEmployee;
	}
}

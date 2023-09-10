package hibernate_rev.shailu;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.execution.DbUtils;
import com.execution.UtilityHibernate;

import hibernate_rev.model.Employee;
// All about UtilityHibernate
public class AppMain {

	public static void main(String[] args) {

		Transaction transaction = null;
		DbUtils dbUtils = new DbUtils();
		Session session = dbUtils.getSession();
		try (session) { // java 1.9 features

			transaction = session.beginTransaction(); // when we used @Transactional no need to write boiler plate code

			System.out.println("ff");

			UtilityHibernate utility = new UtilityHibernate();
//			utility.getEmplyee(session, 7);
			List<Employee> emp =utility.getAllEmployee(session);
			for(Employee em:emp) {
				System.out.println(em);
			}
			System.out.println("session " + session);
//			utility.saveEmpAndAddress(session);
//			utility.getEmpAndSaveTask(session, 2L);
//			utility.saveEmpAndAddressAndTask(session);

			transaction.commit();
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
		} finally {

		}
		System.out.println("no idea");
	}

}

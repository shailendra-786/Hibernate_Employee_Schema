package hibernate_rev.shailu;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.execution.DbUtils;
import com.execution.HibernateWithCriteriaQuery;

import hibernate_rev.model.Employee;

// All about UtilityHibernate
public class AppMainCriteriaQuery {

	public static void main(String[] args) {
		Transaction transaction = null;
		DbUtils dbUtils = new DbUtils();
		Session session = dbUtils.getSession();
		try (session) { // java 1.9 features

			transaction = session.beginTransaction();

			HibernateWithCriteriaQuery hibernateWithCriteriaQuery = new HibernateWithCriteriaQuery();
			List<Employee> li = hibernateWithCriteriaQuery.getEmployeeWithLikeOperator(session);
			for(Employee l1 :li) {
				System.out.println(l1.getFirst_name());
			}
			
			for (String str : hibernateWithCriteriaQuery.getEmployeeNameByAddressState(session)) {
				System.out.println(str);
			}
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Main method Exception " + e);
		} finally {

		}
		System.out.println("no idea");
	}

}

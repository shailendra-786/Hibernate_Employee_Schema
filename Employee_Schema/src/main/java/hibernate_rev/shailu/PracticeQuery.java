package hibernate_rev.shailu;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.execution.DbUtils;
import com.execution.Practice.CriteriaAndHql;

// All about UtilityHibernate
public class PracticeQuery {

	public static void main(String[] args) {
		Transaction transaction = null;
		DbUtils dbUtils = new DbUtils();
		Session session = dbUtils.getSession();
		try (session) { // java 1.9 features

			transaction = session.beginTransaction();

		CriteriaAndHql cb = new CriteriaAndHql();
		System.out.println(cb.getSingleColumData(session).toString());
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Main method Exception " + e);
		} finally {

		}
		System.out.println("no idea");
	}

}

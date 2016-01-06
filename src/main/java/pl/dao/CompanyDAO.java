package pl.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pl.pojo.Company;
import pl.pojo.Worker;

public class CompanyDAO extends DAO {

	public static void persist(Company company) {

		try {
			beginTransaction();
			getSession().persist(company);
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("CompanyDao couldn't persist");
		}

	}

	public static void delate(Company Company) {
		try {
			beginTransaction();
			getSession().delete(Company);
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("CompanyDao couldn't delete");
		}
	}

	public static void updateOrUpdate(Company company) {
		try {
			beginTransaction();
			getSession().saveOrUpdate(company);
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("CompanyDao couldn't update");
		}
	}

	public static List<Company> getAll() {
		List<Company> companys = new ArrayList<Company>();
		try {
			beginTransaction();
			Query CompanyQuery = getSession().createQuery(
					"Select c from Company c");
			companys = (List<Company>) CompanyQuery.list();
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("CompanyDao couldn't get all Companys");
		}
		return companys;
	}
	public static Company load(Long id) {
		Company company=null;
		try {
			beginTransaction();
			company= (Company) getSession().load(Company.class, id);
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("CompanyDAO couldn't load company");
		}
		return company;
	}

	public static Company get(int index) {
		Company Company = null;
		try {
			beginTransaction();
			Company = (Company) getSession().get(Company.class,
					Long.valueOf(index));
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("CompanyDao couldn't get Company");
		}
		return Company;
	}
}

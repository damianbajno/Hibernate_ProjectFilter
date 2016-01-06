package pl.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.metadata.ClassMetadata;

import pl.pojo.Company;
import pl.pojo.Worker;

public class WorkerDAO extends DAO {

	public WorkerDAO() {
		super();
	}

	public static void companyChange(Worker worker, Company company) {
		try {
			beginTransaction();

			worker.setCompany(company);
			commitTransaction();
		} catch (HibernateException e) {
			System.out.println("WorkerDAO couldn't change worker company");
			rollback();
		}

	}

	public static void saveOrUpdate(Worker worker) {
		try {
			beginTransaction();
			getSession().saveOrUpdate(worker);
			commitTransaction();
		} catch (HibernateException e) {
			System.out.println("WorkerDAO couldn't saveOrUpdate worker.");
			rollback();
		}
	}

	public static void persist(Worker worker) {
		try {
			beginTransaction();
			getSession().persist(worker);
			commitTransaction();
		} catch (HibernateException e) {
			System.out.println("WorkerDAO couldn't persiste a worker.");
			rollback();
		}
	}

	public static Worker get(Long id) {
		Worker worker = null;
		try {
			beginTransaction();
			worker = (Worker) getSession().get(Worker.class, id);
			commitTransaction();
		} catch (HibernateException e) {
			System.out.println("WorkerDAO couldn't get worker.");
			rollback();
		}
		return worker;
	}

	public static void delateOrphan(Worker worker) {
		try {
			beginTransaction();
			worker.getCompany().getWorkers().remove(worker);
			getSession().delete(worker);
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("WorkerDAO couldn't delete worker Orphan");
		}
	}

	public static void delate(Worker worker) {
		try {
			beginTransaction();
			getSession().delete(worker);
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("WorkerDAO couldn't delete worker");
		}
	}

	public static Worker load(Long id) {
		Worker worker = null;
		try {
			beginTransaction();
			worker = (Worker) getSession().load(Worker.class, id);
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("WorkerDAO couldn't load worker");
		}
		return worker;
	}

	public static void update(Worker worker) {
		try {
			beginTransaction();
			getSession().update(worker);
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("WorkerDAO couldn't update worker");
		}
	}

	public static List<Worker> getAll() {
		List<Worker> workers = new ArrayList<Worker>();
		try {
			beginTransaction();
			Query workerQuery = getSession().createQuery(
					"Select w from Worker w");
			workers = (List<Worker>) workerQuery.list();
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("WorkerDAO couldn't get all workers");
		}
		return workers;

	}

}

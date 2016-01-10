package pl.dao;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import pl.pojo.Company;
import pl.pojo.Worker;

public class DAO {


     private static String Hbm2ddlProperty = "update";
     private static Configuration configuration;
     private static StandardServiceRegistryBuilder builder;
     private static SessionFactory sessionfactory;

     static {
	  createDaoSessionFactory();
     }



     protected DAO() {

     }



     protected static Session getSession() {
	  return sessionfactory.getCurrentSession();
     }



     protected static void beginTransaction() {
	  getSession().beginTransaction();
     }



     protected static void commitTransaction() {
	  getSession().getTransaction().commit();
     }



     protected static void rollback() {
	  try {
	       System.out.println("Transaction started rolling back");
	       getSession().getTransaction().rollback();
	  } catch (HibernateException e) {
	       System.out.println("Transaction can't roll back");
	       e.printStackTrace();
	  }
	  try {
	       System.out.println("Session started closing");
	       getSession().close();
	  } catch (HibernateException e) {
	       System.out.println("Session can't close");
	       e.printStackTrace();
	  }
     }



     public static void close() {
	  getSession().close();
     }



     protected static void configurationHbm2ddlCreate() {
	  Hbm2ddlProperty="create";
	  createDaoSessionFactory();
     }



     protected static void configurationHbm2ddlUpdate() {
	  Hbm2ddlProperty="update";
	  createDaoSessionFactory();
     }



     public static void createDaoSessionFactory() {
	  configuration = new Configuration().configure()
		    .addAnnotatedClass(Worker.class)
		    .addAnnotatedClass(Company.class)
		    .setProperty("hibernate.hbm2ddl.auto", Hbm2ddlProperty);
	  builder = new StandardServiceRegistryBuilder()
		    .applySettings(configuration.getProperties());
	  sessionfactory = configuration.buildSessionFactory(builder.build());
     }

}

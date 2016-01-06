package pl.main;

import java.awt.EventQueue;
import java.util.logging.Logger;

import pl.frameandpanel.WorkerFrame;

public class MainClass {

     public static final String APPLICATION_LOGGER_NAME = "HibernateLogger";
     
	public static void main(String[] args) {
		
	     	Logger loggerHibernate=Logger.getLogger(APPLICATION_LOGGER_NAME);
		loggerHibernate.info("Application started");
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				WorkerFrame workerFrame=new WorkerFrame();
				workerFrame.setDefaultSettings();
			}
		});
	}

}

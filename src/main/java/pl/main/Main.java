package pl.main;

import java.awt.EventQueue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.JFrame;

import pl.dao.CompanyDAO;
import pl.dao.WorkerDAO;
import pl.frameandpanel.WorkerFrame;
import pl.pojo.Company;
import pl.pojo.Worker;
import pl.tools.Generator;

public class Main {

	public static void main(String[] args) {
		Logger loggerHibernate=Logger.getLogger("HibernateLogger");
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				WorkerFrame workerFrame=new WorkerFrame();
				workerFrame.setDefaulteFrameSettings();
			}
		});
	}

}

package pl.frameandpanel;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import pl.dao.WorkerDAO;
import pl.main.MainClass;
import pl.pojo.Company;
import pl.pojo.Worker;
import pl.table.WorkerTableModel;
import pl.tools.GBC;

public class AddWorkerFrame extends JFrame {

     private static final String ADD_WORKER_FRAME_TITLE = "Dodaj Pracownika";
     private static final String EXIT_BUTTON_NAME = "Wyjdź";
     private static final String COMMIT_BUTTON_NAME = "Dodaj";
     
     private JButton commitButton = new JButton(COMMIT_BUTTON_NAME);
     private JButton exitButton = new JButton(EXIT_BUTTON_NAME);
     private JFrame mainFrame;

     private Logger logger = Logger.getLogger(MainClass.APPLICATION_LOGGER_NAME);



     public AddWorkerFrame(JFrame mainFrame) {
	  super();
     }



     public void makeFrame() {
	  setDefaultSettings();

	  setLayout(new GridBagLayout());

	  WorkerPanel workerPanel = new WorkerPanel();
	  CompanyPanel companyPanel = new CompanyPanel();

	  add(workerPanel.getPanel(), new GBC(0, 0).setFill(1));
	  add(companyPanel.getButtons(), new GBC(0, 1));
	  add(companyPanel.getPanel(), new GBC(0, 3));

	  JPanel panelButton = new JPanel();
	  panelButton.add(commitButton, new GBC(0, 0));
	  panelButton.add(exitButton, new GBC(1, 0));
	  add(panelButton, new GBC(0, 4).setInsets(5, 0, 5, 0));

	  commitButton.addActionListener(new ActionListener() {

	       @Override
	       public void actionPerformed(ActionEvent e) {
		    Worker worker = workerPanel.getWorker();
		    Company company = null;

		    company = companyPanel.getCompany();

		    if (worker != null && company != null) {
			 worker.setCompany(company);
			 logger.info("Connect worker with company");
		    }

		    if (worker != null) {
			 WorkerDAO.persist(worker);
			 workerPanel.clearTextFields();
			 companyPanel.clearTextFields();
			 logger.info("Save worker");
			 WorkerTableModel.add(worker);
		    }

	       }
	  });

	  
	  JFrame addWorkerFrame=this;

	  exitButton.addActionListener(new ActionListener() {
	       
	       @Override
	       public void actionPerformed(ActionEvent e) {
		    addWorkerFrame.dispose();
		    SwingUtilities.updateComponentTreeUI(mainFrame);
	       }
	  });
     }
     
     public void setDefaultSettings() {
	  setVisible(true);
	  setTitle(ADD_WORKER_FRAME_TITLE);
	  setSize(400, 400);
	  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     }

}
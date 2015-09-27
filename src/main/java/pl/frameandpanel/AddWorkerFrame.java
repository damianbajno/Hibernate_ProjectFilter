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
import pl.pojo.Company;
import pl.pojo.Worker;
import pl.table.WorkerTableModel;
import pl.tools.GBC;

public class AddWorkerFrame extends JFrame {

	private JButton commitButton = new JButton("Dodaj");
	private JButton exitButton = new JButton("Wyjdź");
	private JFrame mainFrame;
	private JFrame addWorkerFrame;

	private Logger logger = Logger.getLogger("HibernateLogger");

	public AddWorkerFrame(JFrame mainFrame) {
		super();
		this.mainFrame = mainFrame;
		this.addWorkerFrame = this;
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
					companyPanel.clearTextFilds();
					logger.info("Save worker");
					WorkerTableModel.add(worker);
				}

			}
		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addWorkerFrame.dispose();
				SwingUtilities.updateComponentTreeUI(mainFrame);
			}
		});
	}

	public void setDefaultSettings() {
		addWorkerFrame.setVisible(true);
		addWorkerFrame.setTitle("Dodaj Pracownika");
		addWorkerFrame.setSize(400, 400);
		addWorkerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
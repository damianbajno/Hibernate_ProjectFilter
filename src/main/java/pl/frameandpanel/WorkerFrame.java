package pl.frameandpanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import pl.dao.WorkerDAO;
import pl.main.MainClass;
import pl.pojo.Worker;
import pl.table.WorkerJTable;
import pl.table.WorkerTableModel;
import pl.tools.GBC;

public class WorkerFrame extends JFrame {

     private static final String EMPTY_WORKER_BUTTON_TITLE = "Dodaj pustą linię";
     private static final String WORKER_BUTTON_TITLE = "Dodaj Pracownika";
     private final String WORKER_FRAME_TITLE = "Pracownicy";

     private JFrame workerFrame;
     private WorkerJTable workerJTable = new WorkerJTable(this);
     private AddWorkerFrame addWorkerFrame;

     private Logger logger = Logger.getLogger(MainClass.APPLICATION_LOGGER_NAME);



     public WorkerFrame() {
	  this.workerFrame = this;
	  addTable();
	  addButtons();
     }



     private void addTable() {
	  JScrollPane workerPane = new JScrollPane(workerJTable);
	  workerFrame.add(workerPane, BorderLayout.CENTER);
     }



     public void addButtons() {
	  JPanel buttonPanel = new JPanel(new GridBagLayout());

	  JButton workerButton = new JButton(WORKER_BUTTON_TITLE);
	  JButton emptyWorker = new JButton(EMPTY_WORKER_BUTTON_TITLE);

	  workerFrame.add(buttonPanel, BorderLayout.SOUTH);

	  buttonPanel.add(workerButton, new GBC(0, 0));
	  buttonPanel.add(emptyWorker, new GBC(0, 1));

	  addActionAndKeyShortCutToButton(workerButton, KeyEvent.VK_W,
		    new WorkerButtonAction());
	  addActionAndKeyShortCutToButton(emptyWorker, KeyEvent.VK_E,
		    new EmptyWorkerButtonAction());

     }



     public void addActionAndKeyShortCutToButton(JButton button,
	       int keyEventLetter, AbstractAction action) {
	  button.addActionListener(action);
	  InputMap buttonInputMap = new ComponentInputMap(button);
	  buttonInputMap.put(
		    KeyStroke.getKeyStroke(keyEventLetter, KeyEvent.CTRL_MASK),
		    "action");
	  button.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, buttonInputMap);
	  button.getActionMap().put("action", action);
     }



     public void setDefaultSettings() {
	  workerFrame.setVisible(true);
	  workerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  workerFrame.setTitle(WORKER_FRAME_TITLE);
	  workerFrame.pack();
     }

     private class WorkerButtonAction extends AbstractAction {

	  @Override
	  public void actionPerformed(ActionEvent e) {
	       addWorkerFrame = new AddWorkerFrame(workerFrame);
	       addWorkerFrame.makeFrame();
	  }
     }

     private class EmptyWorkerButtonAction extends AbstractAction {

	  @Override
	  public void actionPerformed(ActionEvent e) {
	       Worker worker = new Worker("", "", 0);
	       WorkerDAO.persist(worker);
	       WorkerTableModel.add(worker);
	       SwingUtilities.updateComponentTreeUI(workerFrame);
	       logger.info("Add Empty Worker to table");

	  }

     }

}

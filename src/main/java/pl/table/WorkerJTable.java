package pl.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.logging.Logger;

import javax.persistence.JoinColumn;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellEditor;

import pl.dao.WorkerDAO;
import pl.frameandpanel.CompanyFrame;
import pl.frameandpanel.WorkerFrame;
import pl.main.MainClass;
import pl.mouselisteners.DeleteTableRowListener;
import pl.pojo.Company;
import pl.pojo.Worker;

public class WorkerJTable extends JTable {

	private WorkerTableModel workerTableModel = new WorkerTableModel();
	private WorkerJTable workerJTable;
	private CompanyFrame companyFrame;

	private Logger logger = Logger.getLogger(MainClass.APPLICATION_LOGGER_NAME);

	public WorkerJTable(WorkerFrame workerFrame) {
		this.workerJTable = this;

		addMouseListener(new DeleteTableRowListener(this, workerFrame));
		setModel(workerTableModel);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getColumn(this.companyNameIdColumn()).setCellEditor(
				new CompanyCellEditor());
	}

	public Worker getSelectedWorker() {
		return WorkerTableModel.getWorker(this.getSelectedRow());
	}

	private String companyNameIdColumn() {
		JoinColumn[] companyAnnotation = null;
		try {
			Field companyField = Worker.class.getDeclaredField("company");
			companyAnnotation = companyField
					.getAnnotationsByType(JoinColumn.class);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return companyAnnotation[0].name();
	}

	private class CompanyCellEditor extends AbstractCellEditor implements
			TableCellEditor, ActionListener {

		private JButton button = new JButton();
		String BUTTON_ACTION_COMMAND = "SetCompany";

		public CompanyCellEditor() {
		    button.setActionCommand(BUTTON_ACTION_COMMAND);
		    button.addActionListener(this);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			button.setBackground(Color.white);

			if (e.getActionCommand().equals(BUTTON_ACTION_COMMAND)) {
				companyFrame = new CompanyFrame(button);
				companyFrame.create();

				logger.info("Create CompanyFrame");
			}

		}

		@Override
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			logger.info("Save changes in database");
			button.setText(String.valueOf(value));
			logger.info("Click on a Id_Company Button");
			return button;
		}

		private Company company;
		private Worker worker;

		@Override
		public Object getCellEditorValue() {
			
		     	worker = null;
			company = null;
			logger.info("getCellEditorValue method");
			
			if (companyFrame.isCompanySelected()) {
			
			     	company = companyFrame.getSelectedCompany();
				worker = workerJTable.getSelectedWorker();
				worker.setCompany(company);
				WorkerDAO.update(worker);
				logger.info("Save changes in database");

			}
			
			return null;
		}
	}
}

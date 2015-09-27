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
import pl.mouselisteners.MouseListenerDeleteTableRow;
import pl.pojo.Company;
import pl.pojo.Worker;

public class WorkerJTable extends JTable {

	private WorkerTableModel workerTableModel = new WorkerTableModel();
	private WorkerJTable workerJTable;
	private CompanyFrame companyFrame;

	private Logger logger = Logger.getLogger("HibernateLogger");

	public WorkerJTable(WorkerFrame workerFrame) {
		this.workerJTable = this;

		addMouseListener(new MouseListenerDeleteTableRow(this, workerFrame));
		setModel(workerTableModel);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getColumn(this.nameOfCompanyIdColumn()).setCellEditor(
				new CompanyCellEditor());
	}

	public Worker getSelectedWorker() {
		return WorkerTableModel.getWorker(this.getSelectedRow());
	}

	private String nameOfCompanyIdColumn() {
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

		public CompanyCellEditor() {
			button.setActionCommand("SetCompany");
			button.addActionListener(this);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			button.setBackground(Color.white);

			if (e.getActionCommand().equals("SetCompany")) {
				companyFrame = new CompanyFrame(button);
				companyFrame.make();

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
			logger.info("get to CellEditorValues");
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

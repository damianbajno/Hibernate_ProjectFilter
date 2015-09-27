package pl.table;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import pl.dao.WorkerDAO;
import pl.pojo.Worker;
import pl.tools.Tools;

public class WorkerTableModel extends AbstractTableModel {

	private static List<Worker> workerList = WorkerDAO.getAll();
	private Logger logger = Logger.getLogger("HibernateLogger");

	public WorkerTableModel() {
		super();
		if (workerList.isEmpty())
			JOptionPane.showMessageDialog(null, "Tabela jest pusta");

	}

	public static Worker getWorker(int workerNumber) {
		return workerList.get(workerNumber);
	}

	public static void delete(Worker worker) {
		workerList.remove(worker);
	}

	public static void add(Worker worker) {
		workerList.add(worker);
	}

	@Override
	public int getRowCount() {
		return workerList.size();
	}

	@Override
	public int getColumnCount() {
		return new Worker().getClass().getDeclaredFields().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		Field[] declaredFields = Worker.class.getDeclaredFields();
		Column filedAnnotation = declaredFields[columnIndex]
				.getAnnotation(Column.class);
		if (filedAnnotation == null) {
			JoinColumn filedAnnotationJ = declaredFields[columnIndex]
					.getAnnotation(JoinColumn.class);
			return filedAnnotationJ.name();
		} else {
			return filedAnnotation.name();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex != 0)
			return true;
		else {
			return false;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		logger.info("Getting a column " + columnIndex + " class");
		Field[] workerFields = Worker.class.getDeclaredFields();
		return workerFields[columnIndex].getType();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Worker worker = workerList.get(rowIndex);
		logger.info("Getting worker table values at " + rowIndex);
		switch (columnIndex) {
		case 0:
			return worker.getIdWorker();
		case 1:
			return worker.getName();
		case 2:
			return worker.getSurName();
		case 3:
			if (worker.getAge() == 0)
				return 0;
			else
				return worker.getAge();
		case 4: {
			if (worker.isComapnyNull())
				return worker.getCompany().getCompanyId();
			else
				return 0;
		}
		default:
			return null;
		}

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		logger.info("Setting worker table values at row= " + rowIndex
				+ " column= " + columnIndex);

		Worker selectedWorker = workerList.get(rowIndex);

		switch (columnIndex) {
		case 1:
			if (Tools.isTextIfNotShowMessage(String.valueOf(aValue)))
				selectedWorker.setName((String) aValue);
			break;
		case 2:
			if (Tools.isTextIfNotShowMessage(String.valueOf(aValue)))
				selectedWorker.setSurName((String) aValue);
			break;
		case 3:
			if (Tools.isNumericIfNotShowMessage(String.valueOf(aValue)))
				selectedWorker.setAge((Integer) aValue);
			break;
		default:
			break;
		}

		WorkerDAO.update(selectedWorker);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}

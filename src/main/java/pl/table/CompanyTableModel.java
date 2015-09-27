package pl.table;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Column;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import pl.dao.CompanyDAO;
import pl.dao.WorkerDAO;
import pl.pojo.Company;
import pl.pojo.Worker;
import pl.tools.Tools;

public class CompanyTableModel implements TableModel {
	private static List<Company> companyList = CompanyDAO.getAll();

	public CompanyTableModel() {
		super();
		if (companyList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tabela jest pusta", null,
					JOptionPane.INFORMATION_MESSAGE, null);
		}
	}

	@Override
	public int getRowCount() {
		return companyList.size();
	}

	@Override
	public int getColumnCount() {
		return companyList.get(0).getClass().getDeclaredFields().length - 1;
	}

	@Override
	public String getColumnName(int columnIndex) {
		Field[] declaredFields = Company.class.getDeclaredFields();
		Column columnAnnotation = declaredFields[columnIndex].getAnnotation(Column.class);
		return columnAnnotation.name();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Field[] companyFields = Company.class.getDeclaredFields();
		return companyFields[columnIndex].getType();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Company company = companyList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return company.getCompanyId();
		case 1:
			return company.getName();
		case 2:
			return company.getSegment();
		case 3:
			return company.getTown();
		default:
			return null;
		}
		
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	public void refresh() {
		companyList = CompanyDAO.getAll();
	}

	public Company getCompany(int numberOfRow) {
		return companyList.get(numberOfRow);
	}

}

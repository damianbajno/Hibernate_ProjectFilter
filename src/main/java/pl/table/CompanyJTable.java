package pl.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import pl.pojo.Company;

public class CompanyJTable extends JTable {
	private CompanyTableModel companyTableModel;

	public CompanyJTable() {
		companyTableModel = new CompanyTableModel();
		setModel(companyTableModel);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);
	}

	public Company getSelectedCompany() {
		return companyTableModel.getCompany(this.getSelectedRow());
	}

	public boolean isCompanySelected() {
		if (this.getSelectedRow() != -1)
			return true;
		else
			return false;
	}

}

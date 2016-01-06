package pl.frameandpanel;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import pl.pojo.Company;
import pl.table.CompanyJTable;

public class CompanyFrame extends JFrame {
	
     	private static final String COMPANY_NAME_FRAME = "Firmy";
     	private static final String COMMIT_BUTTON_TITLE = "Commit";


	private CompanyJTable companyJTable = new CompanyJTable();
	private JTextField companyNameTextField = new JTextField();
	private JTextField segmentTextField = new JTextField();
	private JTextField townTextField = new JTextField();
	private JButton commitButton = new JButton(COMMIT_BUTTON_TITLE);
	private JButton chooseCompanyButton;

	public CompanyFrame() {
		super();
	}

	public CompanyFrame(JButton chooseCompanyButton) throws HeadlessException {
		super();
		this.chooseCompanyButton = chooseCompanyButton;
		commitButton.addActionListener(new ActionButton());
	}

	public CompanyFrame(JTextField nazwaFirmyTextField,
			JTextField segmentTextField, JTextField miastoTextField) {

		super();
		this.companyNameTextField = nazwaFirmyTextField;
		this.segmentTextField = segmentTextField;
		this.townTextField = miastoTextField;
		commitButton.addActionListener(new ActionTextFilds());
	}

	public void create() {

		setDefaultSettings();

		JScrollPane companyTablePane = new JScrollPane(companyJTable);
		add(companyTablePane, BorderLayout.CENTER);

		add(commitButton, BorderLayout.SOUTH);

	}

	private static final String SELECT_COMPANY_MESSAGE = "Select Company";

	private class ActionButton implements ActionListener {


	       @Override
		public void actionPerformed(ActionEvent e) {
			if (companyJTable.isCompanySelected()) {
				Company company = companyJTable.getSelectedCompany();
				chooseCompanyButton.setText(String.valueOf(company.getCompanyId()));
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, SELECT_COMPANY_MESSAGE);
			}
		}

	}

	private class ActionTextFilds implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if ((companyJTable.isCompanySelected())) {
				Company company = companyJTable.getSelectedCompany();
				companyNameTextField.setText(company.getName());
				segmentTextField.setText(company.getSegment());
				townTextField.setText(company.getTown());
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, SELECT_COMPANY_MESSAGE);
			}
		}
	}

	public void setDefaultSettings() {
		setVisible(true);
		setSize(400, 400);
		setTitle(COMPANY_NAME_FRAME);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public Company getSelectedCompany() {
		return companyJTable.getSelectedCompany();
	}

	
	public boolean isCompanySelected() {
		return companyJTable.isCompanySelected();
	}
}

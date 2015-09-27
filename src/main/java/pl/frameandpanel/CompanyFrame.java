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
	private static final String frameName = "Firmy";

	private CompanyJTable companyJTable = new CompanyJTable();
	private JTextField nazwaFirmyTextField = new JTextField();
	private JTextField segmentTextField = new JTextField();
	private JTextField miastoTextField = new JTextField();
	private JButton committeeButton = new JButton("Committee");
	private JButton chooseCompanyButton;

	public CompanyFrame() {
		super();
	}

	public CompanyFrame(JButton chooseCompanyButton) throws HeadlessException {
		super();
		this.chooseCompanyButton = chooseCompanyButton;
		committeeButton.addActionListener(new ActionButton());
	}

	public CompanyFrame(JTextField nazwaFirmyTextField,
			JTextField segmentTextField, JTextField miastoTextField) {

		super();
		this.nazwaFirmyTextField = nazwaFirmyTextField;
		this.segmentTextField = segmentTextField;
		this.miastoTextField = miastoTextField;
		committeeButton.addActionListener(new ActionTextFilds());
	}

	public void make() {

		setDefaultSettings();

		JScrollPane companyTablePane = new JScrollPane(companyJTable);
		add(companyTablePane, BorderLayout.CENTER);

		add(committeeButton, BorderLayout.SOUTH);

	}

	private class ActionButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (companyJTable.isCompanySelected()) {
				Company company = companyJTable.getSelectedCompany();
				chooseCompanyButton.setText(String.valueOf(company.getCompanyId()));
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Select Company");
			}
		}

	}

	private class ActionTextFilds implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if ((companyJTable.isCompanySelected())) {
				Company company = companyJTable.getSelectedCompany();
				nazwaFirmyTextField.setText(company.getName());
				segmentTextField.setText(company.getSegment());
				miastoTextField.setText(company.getTown());
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Select Company");
			}
		}
	}

	public void setDefaultSettings() {
		setVisible(true);
		setSize(400, 400);
		setTitle(frameName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public Company getSelectedCompany() {
		return companyJTable.getSelectedCompany();
	}

	
	public boolean isCompanySelected() {
		return companyJTable.isCompanySelected();
	}
}

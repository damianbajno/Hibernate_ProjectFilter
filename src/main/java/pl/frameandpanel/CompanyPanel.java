package pl.frameandpanel;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.pojo.Company;
import pl.tools.GBC;
import pl.tools.Tools;

public class CompanyPanel {

	private JLabel nazwaFirmyLabel = new JLabel("Nazwa firmy");
	private JLabel segmentLabel = new JLabel("Segment działalności");
	private JLabel miastoLabel = new JLabel("Miasto siedziby firmy");

	private JTextField nazwaFirmyTextField = new JTextField(20);
	private JTextField segmentTextField = new JTextField(20);
	private JTextField miastoTextField = new JTextField(20);

	private JCheckBox companyCheckBox = new JCheckBox(
			"Stwórz Firmę");
	private JButton getCompanyButton = new JButton("Wybierz Firmę");

	private Company company=null;
	private CompanyFrame chooseCompanyFromFrame=new CompanyFrame();

	public CompanyPanel() {
		defaulteFiledsSettings();
	}

	public JPanel getPanel() {
		JPanel companyPanel = new JPanel();
		companyPanel.setLayout(new GridBagLayout());
		companyPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(4), "Firma"));

		companyPanel.add(nazwaFirmyLabel, new GBC(0, 0).setAnchor(GBC.WEST)
				.setIpad(10, 10));
		companyPanel.add(nazwaFirmyTextField, new GBC(1, 0));

		companyPanel.add(segmentLabel, new GBC(0, 1).setAnchor(GBC.WEST)
				.setIpad(10, 10));
		companyPanel.add(segmentTextField, new GBC(1, 1));

		companyPanel.add(miastoLabel, new GBC(0, 2).setAnchor(GBC.WEST)
				.setIpad(10, 10));
		companyPanel.add(miastoTextField, new GBC(1, 2));
		return companyPanel;
	}

	public JPanel getButtons() {
		JPanel companyButtons = new JPanel();

		companyButtons.setLayout(new GridBagLayout());
		companyCheckBox.addActionListener(new CompanyCheckBoxListener());

		companyButtons.add(companyCheckBox, new GBC(0, 0)
				.setAnchor(GBC.WEST).setInsets(0, 0, 0, 20));
		getCompanyButton.addActionListener(new CompanyButtonListener());
		companyButtons.add(getCompanyButton,
				new GBC(1, 0).setInsets(5, 0, 5, 0));

		return companyButtons;
	}

	public Company getCompany() {
		if (companyCheckBox.isSelected()) {
			if (areFieldsCorrectlyFieled()) {
				settingCompanyFromTextFields();
			}
		} else {
			takeCompanyFromFrame();
		}
		return company;
	}

	private boolean areFieldsCorrectlyFieled() {
		return Tools.isText(nazwaFirmyTextField.getText())
				&& Tools.isText(nazwaFirmyTextField.getText())
				&& Tools.isText(segmentTextField.getText());
	}

	private void takeCompanyFromFrame() {
		if (chooseCompanyFromFrame.isCompanySelected()) {
			company = chooseCompanyFromFrame.getSelectedCompany();
		}
	}

	private void settingCompanyFromTextFields() {
		company = new Company();
		company.setName(nazwaFirmyTextField.getText());
		company.setTown(miastoTextField.getText());
		company.setSegment(segmentTextField.getText());
	}

	private void defaulteFiledsSettings() {
		companyCheckBox.setSelected(false);
		getCompanyButton.setEnabled(true);
		nazwaFirmyTextField.setEditable(false);
		segmentTextField.setEditable(false);
		miastoTextField.setEditable(false);
	}

	public void clearTextFilds() {
		nazwaFirmyTextField.setText("");
		segmentTextField.setText("");
		miastoTextField.setText("");
	}

	
	private class CompanyButtonListener implements ActionListener
	{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				companyCheckBox.setSelected(false);
				chooseCompanyFromFrame = new CompanyFrame(
						nazwaFirmyTextField, segmentTextField, miastoTextField);
				chooseCompanyFromFrame.make();
				
			}
	}
	
	private class CompanyCheckBoxListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (companyCheckBox.isSelected()) {
				setEditableTextFieldsAndButton(true);
			} else {
				setEditableTextFieldsAndButton(false);
			}

		}

		private void setEditableTextFieldsAndButton(boolean editable) {
			nazwaFirmyTextField.setEditable(editable);
			segmentTextField.setEditable(editable);
			miastoTextField.setEditable(editable);
			getCompanyButton.setEnabled(!editable);
		}
	}
}

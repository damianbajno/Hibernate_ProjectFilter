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

     private static final String COMPANY_CHECK_BOX_TITLE = "Stwórz Firmę";
     private static final String CHOOSE_COMPANY_BUTTON = "Wybierz Firmę";
     private static final String COMPANY_TITLE_PANEL = "Firma";

     private JLabel companyNameLabel = new JLabel("Nazwa firmy");
     private JLabel segmentLabel = new JLabel("Segment działalności");
     private JLabel townLabel = new JLabel("Miasto siedziby firmy");

     private JTextField companyNameTextField = new JTextField(20);
     private JTextField segmentTextField = new JTextField(20);
     private JTextField townTextField = new JTextField(20);

     private JCheckBox companyCheckBox = new JCheckBox(COMPANY_CHECK_BOX_TITLE);
     private JButton chooseCompanyButton = new JButton(CHOOSE_COMPANY_BUTTON);

     private Company company = null;
     private CompanyFrame companyFrame = new CompanyFrame();



     public CompanyPanel() {
	  defaultFieldsSettings();
     }



     public JPanel getPanel() {
	  JPanel companyPanel = new JPanel();
	  companyPanel.setLayout(new GridBagLayout());
	  companyPanel.setBorder(BorderFactory.createTitledBorder(
		    BorderFactory.createBevelBorder(4), COMPANY_TITLE_PANEL));

	  companyPanel.add(companyNameLabel, new GBC(0, 0).setAnchor(GBC.WEST)
		    .setIpad(10, 10));
	  companyPanel.add(companyNameTextField, new GBC(1, 0));

	  companyPanel.add(segmentLabel, new GBC(0, 1).setAnchor(GBC.WEST)
		    .setIpad(10, 10));
	  companyPanel.add(segmentTextField, new GBC(1, 1));

	  companyPanel.add(townLabel, new GBC(0, 2).setAnchor(GBC.WEST)
		    .setIpad(10, 10));
	  companyPanel.add(townTextField, new GBC(1, 2));
	  return companyPanel;
     }



     public JPanel getButtons() {
	  JPanel companyButtons = new JPanel();

	  companyButtons.setLayout(new GridBagLayout());
	  companyCheckBox.addActionListener(new CompanyCheckBoxListener());

	  companyButtons.add(companyCheckBox, new GBC(0, 0).setAnchor(GBC.WEST)
		    .setInsets(0, 0, 0, 20));
	  chooseCompanyButton.addActionListener(new CompanyButtonListener());
	  companyButtons.add(chooseCompanyButton,
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
	  return Tools.isText(companyNameTextField.getText())
		    && Tools.isText(companyNameTextField.getText())
		    && Tools.isText(segmentTextField.getText());
     }



     private void takeCompanyFromFrame() {
	  if (companyFrame.isCompanySelected()) {
	       company = companyFrame.getSelectedCompany();
	  }
     }



     private void settingCompanyFromTextFields() {
	  company = new Company();
	  company.setName(companyNameTextField.getText());
	  company.setTown(townTextField.getText());
	  company.setSegment(segmentTextField.getText());
     }



     private void defaultFieldsSettings() {
	  companyCheckBox.setSelected(false);
	  chooseCompanyButton.setEnabled(true);
	  companyNameTextField.setEditable(false);
	  segmentTextField.setEditable(false);
	  townTextField.setEditable(false);
     }



     public void clearTextFields() {
	  companyNameTextField.setText("");
	  segmentTextField.setText("");
	  townTextField.setText("");
     }

     private class CompanyButtonListener implements ActionListener {

	  @Override
	  public void actionPerformed(ActionEvent e) {
	       companyCheckBox.setSelected(false);
	       companyFrame = new CompanyFrame(companyNameTextField,
			 segmentTextField, townTextField);
	       companyFrame.create();

	  }
     }

     private class CompanyCheckBoxListener implements ActionListener {
	  @Override
	  public void actionPerformed(ActionEvent e) {
	       if (companyCheckBox.isSelected()) {
		    setEditableTextFieldsAndButton(true);
	       } else {
		    setEditableTextFieldsAndButton(false);
	       }

	  }



	  private void setEditableTextFieldsAndButton(boolean editable) {
	       companyNameTextField.setEditable(editable);
	       segmentTextField.setEditable(editable);
	       townTextField.setEditable(editable);
	       chooseCompanyButton.setEnabled(!editable);
	  }
     }
}

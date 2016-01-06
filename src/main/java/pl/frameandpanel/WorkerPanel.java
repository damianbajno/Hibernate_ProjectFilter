package pl.frameandpanel;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.pojo.Worker;
import pl.tools.GBC;
import pl.tools.Tools;

public class WorkerPanel {

     private static final String LABEL_AGE_NAME = "Wiek [lata]";
     private static final String LABEL_SURNAME_NAME = "Nazwisko";
     private static final String LABEL_NAME = "Imie";
     private static final String MESSAGE_FOR_UNCORRECTED_FILLED_FIELDS = "Nie uzupełniono wszystkich niezbędnych póll (Imie, Nazwisko, Wiek), lub wartości w polach są nieprawidłowe";
     private static final String WORKER_PANEL_TITLE = "Pracownik";

     private static final int TEXT_FILLED_WIDTH = 20;

     private JLabel nameLabel = new JLabel(LABEL_NAME);
     private JLabel surNameLabel = new JLabel(LABEL_SURNAME_NAME);
     private JLabel ageLabel = new JLabel(LABEL_AGE_NAME);

     private JTextField nameTextField = new JTextField(TEXT_FILLED_WIDTH);
     private JTextField surNameTextField = new JTextField(TEXT_FILLED_WIDTH);
     private JTextField ageTextField = new JTextField(TEXT_FILLED_WIDTH);

     private Worker worker = null;

     
     public JPanel getPanel() {
	  JPanel workerPanel = new JPanel();

	  workerPanel.setLayout(new GridBagLayout());
	  workerPanel.setBorder(BorderFactory.createTitledBorder(
		    BorderFactory.createBevelBorder(3), WORKER_PANEL_TITLE));

	  workerPanel.add(nameLabel,
		    new GBC(0, 0).setAnchor(GBC.WEST).setIpad(10, 10));
	  workerPanel.add(nameTextField, new GBC(1, 0));

	  workerPanel.add(surNameLabel, new GBC(0, 1).setAnchor(GBC.WEST)
		    .setIpad(10, 10));
	  workerPanel.add(surNameTextField, new GBC(1, 1));

	  workerPanel.add(ageLabel,
		    new GBC(0, 2).setAnchor(GBC.WEST).setIpad(10, 10));
	  workerPanel.add(ageTextField, new GBC(1, 2));

	  return workerPanel;
     }



     public Worker getWorker() {
	  if (areFieldsCorrectlyFilled()) {
	       createWorkerFromTextFields();
	       return worker;
	  }
	  return null;
     }



     public boolean areFieldsCorrectlyFilled() {
	  if (Tools.isText(nameTextField.getText())
		    && Tools.isText(surNameTextField.getText())) {
	       if (Tools.isNumericIfNotShowMessage(ageTextField.getText())) {
		    return true;
	       } else {
		    return false;
	       }
	  } else {
	       JOptionPane.showMessageDialog(null,
			 MESSAGE_FOR_UNCORRECTED_FILLED_FIELDS);
	       return false;
	  }
     }



     private void createWorkerFromTextFields() {
	  worker = new Worker();
	  worker.setName(nameTextField.getText());
	  worker.setSurName(surNameTextField.getText());
	  worker.setAge(Integer.parseInt(ageTextField.getText()));
     }



     public void clearTextFields() {
	  nameTextField.setText("");
	  surNameTextField.setText("");
	  ageTextField.setText("");
     }
}

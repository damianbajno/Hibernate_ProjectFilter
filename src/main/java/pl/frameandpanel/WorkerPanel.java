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

	private static final int textFildWidth = 20;
	private JLabel nameLabel = new JLabel("Imie");
	private JLabel surNameLabel = new JLabel("Nazwisko");
	private JLabel ageLabel = new JLabel("Wiek [lata]");

	private JTextField nameTextField = new JTextField(textFildWidth);
	private JTextField surNameTextField = new JTextField(textFildWidth);
	private JTextField ageTextField = new JTextField(textFildWidth);

	private Worker worker = null;

	private final String messageForUncorrectFilds = "Nie uzupełniono wszystkich niezbędnych póll (Imie, Nazwisko, Wiek), lub wartości w polach są nieprawidłowe";

	public WorkerPanel() {

	}

	public JPanel getPanel() {
		JPanel workerPanel = new JPanel();

		workerPanel.setLayout(new GridBagLayout());
		workerPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(3), "Pracownik"));

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
			JOptionPane.showMessageDialog(null, messageForUncorrectFilds);
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

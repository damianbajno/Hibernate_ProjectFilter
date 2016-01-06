package pl.mouselisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import pl.dao.WorkerDAO;
import pl.pojo.Worker;
import pl.table.WorkerJTable;
import pl.table.WorkerTableModel;

public class DeleteTableRowListener extends MouseAdapter {

	private WorkerJTable workerTable;
	private JFrame workerFrame;

	public DeleteTableRowListener(WorkerJTable workerTable,
			JFrame workerFrame) {
		super();
		this.workerTable = workerTable;
		this.workerFrame = workerFrame;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger())
			constructPopUpMenu(e);
		super.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger())
			constructPopUpMenu(e);
		super.mouseReleased(e);
	}

	public void constructPopUpMenu(MouseEvent e) {
		PopUpMenu popUpMenu = new PopUpMenu();
		popUpMenu.show(e.getComponent(), e.getX(), e.getY());
	}

	private class PopUpMenu extends JPopupMenu {

		private static final String DELETE_ROW_POPUP_TEXT = "Delete Row";
	       private JMenuItem startItem;

		public PopUpMenu() {
			startItem = new JMenuItem(DELETE_ROW_POPUP_TEXT);
			startItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (workerTable.getSelectedRow() != -1) {
						deleteWorkerAndUpdateTable();
					}
				}

				private void deleteWorkerAndUpdateTable() {
					Worker worker = workerTable.getSelectedWorker();
					WorkerDAO.delateOrphan(worker);
					WorkerTableModel.delete(worker);
					SwingUtilities.updateComponentTreeUI(workerFrame);
				}
			});
			
			add(startItem);
		}

	}
}
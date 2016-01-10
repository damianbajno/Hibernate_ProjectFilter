package pl.exception;

import javax.swing.JOptionPane;

public class TableNotEmptyException extends Throwable {
     private static final String TABLE_ISNT_EMPTY_MASSAGE = "Table isn't empty. Do you want to rewrite it??";
     private int confirmDialogOption;
     
     public TableNotEmptyException() {
	  confirmDialogOption = JOptionPane.showConfirmDialog(null, TABLE_ISNT_EMPTY_MASSAGE);
     }

     public int getConfirmDialogOption() {
          return confirmDialogOption;
     }


}

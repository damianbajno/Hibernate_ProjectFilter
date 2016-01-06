package pl.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Tools {

     public static String getValueBetweenTwoStrings(String text,
	       String befourString, String afterString) {

	  String compilePattern = befourString + "(\\w*)" + afterString;
	  Pattern pattern = Pattern.compile(compilePattern);
	  Matcher matcher = pattern.matcher(text);
	  
	  if (matcher.find()) {
	       return matcher.group(1);
	  }
	  
	  return null;
     }



     public static boolean isNumeric(String str) {
	  return str.matches("\\d+");
     }



     public static boolean isNumericIfNotShowMessage(String str) {
	  
	  if (str.matches("\\d+")) {
	       Integer age = Integer.valueOf(str);
	  
	       if (age < 120) {
		    return true;
	       } else {
		    JOptionPane.showMessageDialog(null,
			      "Wiek musi być poniżej 120 lat");
		    return false;
	       }
	  } else {
	       JOptionPane.showMessageDialog(null, "Wiek musi być liczbą");
	       return false;
	  }
     }



     public static boolean isText(String str) {
	  return str.matches("[a-zA-Z]+\\s*[a-zA-Z]*");
     }



     public static boolean isTextIfNotShowMessage(String str) {
	  System.out.println(str);
	  if (str.trim().matches("[a-zA-Z]+\\s*[-]*[a-zA-Z]*"))
	       return true;
	  else {
	       JOptionPane.showMessageDialog(null, "Wartość nie jest tekstem");
	       return false;
	  }
     }
}

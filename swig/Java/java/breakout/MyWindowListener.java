import java.awt.event.*;
import javax.swing.*;

public class MyWindowListener implements WindowListener {

	public void windowClosing(WindowEvent e){
	   int result = JOptionPane.showConfirmDialog(null, "Are you sure to exit", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

	   if(result == JOptionPane.YES_OPTION){
	           System.exit(0);
	   }else{
	           //Do nothing
	   }
	}

	public void windowOpened(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowActivated(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}

}
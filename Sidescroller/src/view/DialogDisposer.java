package view;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

/**
 * Subclass which disposes of main menu dialog box upon button selection. 
 * @author Alexia
 *
 */

public class DialogDisposer extends AbstractAction {
	        
	public DialogDisposer(String name) {
		super(name);
    }
	        
	@Override
    public void actionPerformed(ActionEvent e) {
        Component comp = (Component) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();  // here -- dispose of the JDialog
    }

}
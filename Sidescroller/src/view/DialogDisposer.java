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
	        
	/**
	 * Stores unique data to facilitate versioning of serialized data.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to set the string to be displayed on dialog box.
	 * @param name string to be displayed on dialog box.
	 */
	public DialogDisposer(String name) {
		super(name);
    }
	        
	@Override
	/**
	 * Disposes of dialog box after game mode is selected.
	 */
    public void actionPerformed(ActionEvent e) {
        Component comp = (Component) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();  // here -- dispose of the JDialog
    }

}
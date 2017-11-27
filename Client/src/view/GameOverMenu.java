package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * JPanel containing the Game Over menu dialog box.
 * @author Valerie
 *
 */
public class GameOverMenu extends JPanel{
	
	 /**
	 * Stores RGB value for the background color of the button panel.
	 */
	 /**
	  * Constructor for the start menu.
	  * Initializes labels and buttons. Sets layout of components on the screen. 
	  */
	 public GameOverMenu() {
	        JLabel gameoverLabel = new JLabel("GAME OVER");
	        gameoverLabel.setForeground(Color.WHITE);
	        gameoverLabel.setFont(new Font("Impact", Font.PLAIN, 50));
	        JPanel pausedPanel = new JPanel();
	        pausedPanel.setOpaque(false);
	        pausedPanel.add(gameoverLabel);
	        
	        
	        setBackground(Color.black);
	        int eb = 20;
	        setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
	        setLayout(new GridLayout(0, 1, 20, 20));
	        add(pausedPanel);
	        add(new JButton(new DialogDisposer("Restart")));
	        add(new JButton(new DialogDisposer("Back to main menu")));
	    }

	    // simple action -- all it does is to make the dialog no longer visible
	/**
	 * Subclass which disposes of main menu dialog box upon button selection. 
	 * @author 
	 *
	 */
    private static class DialogDisposer extends AbstractAction {
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
}

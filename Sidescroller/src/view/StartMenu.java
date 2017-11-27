package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.MultiPlayerButtonListener;
import controller.SinglePlayerButtonListener;

/**
 * JPanel containing the start menu dialog box.
 * @author Alexia
 *
 */
public class StartMenu extends JPanel{
	
	 private GameWindow gw;
	 private JLabel startLabel;
	
	 /**
	 * Stores RGB value for the background color of the button panel.
	 */
	 private static final Color BG = new Color(10, 50, 200);

	 /**
	  * Constructor for the start menu.
	  * Initializes labels and buttons. Sets layout of components on the screen. 
	  */
	 public StartMenu(GameWindow gw) {
		 	this.gw = gw;
	        this.startLabel = new JLabel("Start Game");
	        this.startLabel.setForeground(Color.WHITE);
	        JPanel pausedPanel = new JPanel();
	        pausedPanel.setOpaque(false);
	        pausedPanel.add(startLabel);

	        setBackground(BG);
	        int eb = 15;
	        setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
	        setLayout(new GridLayout(0, 1, 10, 10));
	        add(pausedPanel);
	        
	        JButton singlePlayerButton = new JButton(new DialogDisposer("Single Player"));
	        singlePlayerButton.addActionListener(new SinglePlayerButtonListener());
	        
	        JButton multiPlayerButton = new JButton(new DialogDisposer("Multi Player"));
	        multiPlayerButton.addActionListener(new MultiPlayerButtonListener(this.gw.getRecordButton()));
	        
	        add(singlePlayerButton);
	        add(multiPlayerButton);
	 }
	 
	 public void setStartLabel(String s) {
		 this.startLabel.setText(s);
	 }

	    // simple action -- all it does is to make the dialog no longer visible
	/**
	 * Subclass which disposes of main menu dialog box upon button selection. 
	 * @author Alexia
	 *
	 */
    private class DialogDisposer extends AbstractAction {
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

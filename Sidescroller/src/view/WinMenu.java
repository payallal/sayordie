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

import controller.MultiPlayerButtonListener;
import controller.SinglePlayerButtonListener;

/**
 * JPanel containing the win menu dialog box.
 * @author Valerie
 *
 */
public class WinMenu extends JPanel{
	
	 /**
	 * Stores RGB value for the background color of the button panel.
	 */
	 /**
	  * Constructor for the start menu.
	  * Initializes labels and buttons. Sets layout of components on the screen. 
	  */
	 public WinMenu() {
	        JLabel winLabel = new JLabel("YOU WON!");
	        winLabel.setForeground(Color.WHITE);
	        winLabel.setFont(new Font("Impact", Font.PLAIN, 50));
	        JPanel pausedPanel = new JPanel();
	        pausedPanel.setOpaque(false);
	        pausedPanel.add(winLabel);
	        
	        setBackground(Color.pink);
	        int eb = 20;
	        setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
	        setLayout(new GridLayout(0, 1, 20, 20));
	        add(pausedPanel);
	        add(new JButton(new DialogDisposer("Restart")));
	        add(new JButton(new DialogDisposer("Back to main menu")));
	        /*
	        JButton singlePlayerButton = new JButton(new DialogDisposer("Single Player"));
	        singlePlayerButton.addActionListener(new SinglePlayerButtonListener());
	        JButton multiPlayerButton = new JButton(new DialogDisposer("Multi Player"));
	        multiPlayerButton.addActionListener(new MultiPlayerButtonListener(this.gw.getRecordButton()));
	        add(singlePlayerButton);
	        add(multiPlayerButton);*/
	    }
}

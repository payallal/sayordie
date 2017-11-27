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

import controller.Controller;
import controller.MultiPlayerButtonListener;
import controller.SinglePlayerButtonListener;

/**
 * JPanel containing the start menu dialog box.
 * @author Alexia
 *
 */
public class StartMenu extends JPanel{

	 /**
	  * Constructor for the start menu.
	  * Initializes labels and buttons. Sets layout of components on the screen. 
	  */
	 public StartMenu() {
	        JLabel startLabel = new JLabel("START GAME");
	        startLabel.setForeground(Color.WHITE);
	        startLabel.setFont(new Font("Impact", Font.PLAIN, 50));
	        JPanel pausedPanel = new JPanel();
	        pausedPanel.setOpaque(false);
	        pausedPanel.add(startLabel);

	        setBackground(Color.black);
	        int eb = 15;
	        setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
	        setLayout(new GridLayout(0, 1, 10, 10));
	        add(pausedPanel);
	        JButton singlePlayerButton = new JButton(new DialogDisposer("Single Player"));
	        singlePlayerButton.addActionListener(new SinglePlayerButtonListener());
	        JButton multiPlayerButton = new JButton(new DialogDisposer("Multi Player"));
	        GameWindow gw = Controller.getSingleton().getGameWindow();
	        multiPlayerButton.addActionListener(new MultiPlayerButtonListener(gw.getRecordButton()));
	        add(singlePlayerButton);
	        add(multiPlayerButton);
	    }
}

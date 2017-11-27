package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class Menu extends JPanel{

	 /**
	  * Constructor for the start menu.
	  * Initializes labels and buttons. Sets layout of components on the screen. 
	  */
	 public Menu(String labelName, Color color, String buttonText1, String buttonText2, ActionListener aL1, ActionListener aL2) {
		 JLabel startLabel = new JLabel(labelName);
         startLabel.setForeground(Color.WHITE);
         startLabel.setFont(new Font("Impact", Font.PLAIN, 50));
         JPanel pausedPanel = new JPanel();
         pausedPanel.setOpaque(false);
         pausedPanel.add(startLabel);

         //set my color
         setBackground(color);
         int eb = 15;
         setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
         setLayout(new GridLayout(0, 1, 10, 10));
         add(pausedPanel);
        
         JButton singlePlayerButton = new JButton(new DialogDisposer(buttonText1));
         //in this case I pass the single player button listener, but it could be anything I want so I
         //can use this for other classes
         singlePlayerButton.addActionListener(aL1);
         JButton multiPlayerButton = new JButton(new DialogDisposer(buttonText2));
         GameWindow gw = Controller.getSingleton().getGameWindow();
         //in this case I pass the multiplayer button
         multiPlayerButton.addActionListener(aL2);
        
         add(singlePlayerButton);
         add(multiPlayerButton);
     }
}

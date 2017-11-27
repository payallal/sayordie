package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel containing the start menu dialog box.
 * @author Alexia
 *
 */
public class Menu extends JPanel{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
         //in this case I pass the multiplayer button
         multiPlayerButton.addActionListener(aL2);
        
         add(singlePlayerButton);
         add(multiPlayerButton);
     }
}

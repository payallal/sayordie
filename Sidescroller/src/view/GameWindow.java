package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import controller.Controller;
import multiplayer.GameServer;

/**
 * Represents game window on which game panel and game-play take place.
 * @author Roger
 *
 */
public class GameWindow extends JFrame{
	
	/**
	 * Stores unique identifier for the serialization of the class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Stores current instance of game panel.
	 * @see view.GamePanel
	 */
	private GamePanel gp;
	/**
	 * Stores JButton for recording.
	 */
	private JButton recordButton;
	/**
	 * Stores instance of the start menu.
	 * @see view.StartMenu
	 */
	private StartMenu menu;
	/**
	 * Stores instance of start panel.
	 * @see view.StartPanel
	 */
	private StartPanel sp;
	/**
	 * Stores intended width of screen in pixels.
	 */
	private final int width = 1280;
	/**
	 * Stores intended height of screen in pixels
	 */
	private final int height = 800;
	
	/**
	 * Constructor for game window. Initializes fields, creates window and adds JComponents to the window.
	 */
	public GameWindow() {
		//set layout
		this.setLayout(new BorderLayout());
		JLayeredPane layeredPane = new JLayeredPane();
	    Dimension panelDimension = new Dimension(this.width,this.height);
	    Dimension labelDimension = new Dimension(150,150);

	    this.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setPreferredSize(panelDimension);

		//set up game panel
		this.gp = new GamePanel(this.width, this.height);
		layeredPane.add(this.gp, JLayeredPane.DEFAULT_LAYER);
	    this.gp.setSize(layeredPane.getPreferredSize());
	    this.gp.setLocation(0, 0);
	    
	    //set up record label
		Icon recordGrey = new ImageIcon("img/ui/recordGrey.png");
		Icon recordDarkGrey = new ImageIcon("img/ui/recordDarkGrey.png");
		this.recordButton = new JButton();
		this.recordButton.setBorderPainted(false);
		this.recordButton.setBorder(null);
		this.recordButton.setMargin(new Insets(0, 0, 0, 0));
		this.recordButton.setContentAreaFilled(false);
		this.recordButton.setIcon(recordGrey);
		this.recordButton.setPressedIcon(recordDarkGrey);
		layeredPane.add(this.recordButton, new Integer(2));
		this.recordButton.setSize(labelDimension);
		this.recordButton.setLocation(this.width-150, 0);
		Controller.getSingleton().setRecordButton(this.recordButton);
		Controller.getSingleton().setRecordButtonMouseListener(this.recordButton);

		this.pack();
		this.setSize(this.width, this.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		Controller.getSingleton().setGameWindow(this);
	} 

	public static void main(String[] args) {
			GameWindow gw = new GameWindow();
			//Indicate to controller that this is server
			Controller.getSingleton().setServerFlag(true);
			new StartPanel(gw);
	}

	/**
	 * Getter method for the record button.
	 * @return JButton associated with recording.
	 */
	public JButton getRecordButton() {
		return this.recordButton;
	}
}


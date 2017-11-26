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

public class GameWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel gp;
	private JButton recordButton;
	private StartMenu menu;
	private StartPanel sp;
	private final int width = 1280;
	private final int height = 800;
	
	public GameWindow() {
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
		Controller.getSingleton().setrecordButton(this.recordButton);
		Controller.getSingleton().setrecordButtonMouseListener(this.recordButton);

		this.pack();
		this.setSize(this.width, this.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.sp = new StartPanel(this);
		Controller.getSingleton().setGameWindow(this);
	} 

	public static void main(String[] args) {
			GameWindow gw = new GameWindow();
	}

	public JButton getRecordButton() {
		return this.recordButton;
	}
}


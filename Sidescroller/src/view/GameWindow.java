package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

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
	private JLabel recordLabel;
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
		this.recordLabel = new JLabel(recordGrey);
		layeredPane.add(this.recordLabel, new Integer(2));
		this.recordLabel.setSize(labelDimension);
		this.recordLabel.setLocation(this.width-150, 0);
		Controller.getSingleton().setRecordLabel(this.recordLabel);
		Controller.getSingleton().setRecordLabelMouseListener(this.recordLabel);

		this.pack();
		this.setSize(this.width, this.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.sp = new StartPanel(this);
		Controller.getSingleton().setGameWindow(this);
	} 

	public static void main(String[] args) {
			GameWindow gw = new GameWindow();
			GameServer s;
			s = new GameServer(1664);
			Controller.getSingleton().setServer(s);
			s.acceptClientLoop();
		
	}

	public JLabel getRecordLabel() {
		return this.recordLabel;
	}
}


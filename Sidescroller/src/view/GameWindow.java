package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;

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
		this.gp = new GamePanel(this.width, this.height);
		this.recordButton = new JButton("Record Button");
		Controller.getSingleton().setRecordButtonListener(this.recordButton);
	    Dimension d = new Dimension(100,20);
	    this.recordButton.setPreferredSize(d);
		Container pane = this.getContentPane();
		pane.add(recordButton, BorderLayout.WEST);
		pane.add(gp, BorderLayout.CENTER);

		setSize(this.width, this.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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

	public JButton getRecordButton() {
		return this.recordButton;
	}
}


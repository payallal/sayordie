package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.Controller;
import controller.SingleButtonListener;
import multiplayer.GameServer;

public class GameWindow extends JFrame{
	
	private GamePanel gp;
	private JButton recordButton;
	private StartMenu menu;
	private StartPanel sp;
	
	public GameWindow() {
		//this.menu = new StartMenu();
		
		this.gp = new GamePanel();
		this.recordButton = new JButton("Record Button");
		Controller.getSingleton().setRecordButtonListener(this.recordButton);
	    Dimension d = new Dimension(100,20);
	    this.recordButton.setPreferredSize(d);
		//pane.add(new JButton(pause), BorderLayout.LINE_END);
		Container pane = this.getContentPane();
		pane.add(recordButton, BorderLayout.WEST);
	//	pane.add(menu,BorderLayout.CENTER);
		pane.add(gp, BorderLayout.CENTER);
		
		
		/*this.menu.singlePlayer.addActionListener({
		new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				this.remov
				
			}
		};
	}); */

		setSize(1024, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.sp = new StartPanel(this);
	} 

	public static void main(String[] args) {
			GameWindow gw = new GameWindow();
			GameServer s;
			s = new GameServer(1664);
			Controller.getSingleton().setServer(s);
			s.acceptClientLoop();
		
	}
}


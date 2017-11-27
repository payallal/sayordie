package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import multiplayer.GameServer;
import view.StartMenu;

public class MultiPlayerButtonListener implements ActionListener {
	
	private JButton recordButton;
	
	public MultiPlayerButtonListener (JButton b) {
		this.recordButton = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Controller.getSingleton().setMultiplayer(true);
		Controller.getSingleton().setTextOfInstruction("Connecting Client...");
		this.recordButton.setEnabled(false);
		GameServer s = new GameServer(1664);
		Controller.getSingleton().setServer(s);
		s.acceptClientLoop();
	}

}

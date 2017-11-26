package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import multiplayer.GameServer;
import view.StartMenu;

public class MultiPlayerButtonListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Controller.getSingleton().setMultiplayer(true);
		Controller.getSingleton().setTextOfInstruction("Connecting...");
		GameServer s = new GameServer(1664);
		Controller.getSingleton().setServer(s);
		s.acceptClientLoop();
	}

}

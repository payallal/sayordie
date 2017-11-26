package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import multiplayer.GameServer;
import view.StartMenu;
/**
 * Action listener that sets game to multiplayer mode.
 * @author Roger
 *
 */
public class MultiPlayerButtonListener implements ActionListener {
	/**
	 * Stores record JButton.
	 */
	private JButton recordButton;
	
	/**
	 * Constructor that sets the record button.
	 * @param b the record JButton to which the action listener is to be attached.
	 */
	public MultiPlayerButtonListener (JButton b) {
		this.recordButton = b;
	}
	
	@Override
	/**
	 * Initializes controller and game server to multiplayer mode.
	 */
	public void actionPerformed(ActionEvent e) {
		Controller.getSingleton().setMultiplayer(true);
		Controller.getSingleton().setTextOfInstruction("Connecting...");
		this.recordButton.setEnabled(false);
		GameServer s = new GameServer(1664);
		Controller.getSingleton().setServer(s);
		s.acceptClientLoop();
	}

}

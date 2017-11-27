package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import client.GameClient;
import multiplayer.GameServer;
import view.Menu;
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
	public MultiPlayerButtonListener () {
		this.recordButton = Controller.getSingleton().getGameWindow().getRecordButton();
	}
	
	@Override
	/**
	 * Initializes controller and game server to multiplayer mode.
	 */
	public void actionPerformed(ActionEvent e) {
		
		//set multiplayer to true, load our obstacles
		Controller c = Controller.getSingleton();
		c.setMultiplayer(true);
		c.loadFixedObstacles();
		
		//disenable record button
		this.recordButton.setEnabled(false);
		
		//set the words so that it is not random 
		if (c.getServerFlag()) {
			c.setTextOfInstruction("Connecting to Client...");
			GameServer server = new GameServer(1664);
			c.setServer(server);
			server.acceptClientLoop();
		}
		else {
			c.setTextOfInstruction("Connecting to Server...");
			GameClient client;
			try {
				client = new GameClient("127.0.0.1", 1664);
			} catch (Exception exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
				return;
			}	
			c.setClient(client);
			client.scanningServerLoop();
		}
	}
}

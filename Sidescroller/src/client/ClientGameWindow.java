package client;

import java.awt.Color;

import controller.Controller;
import controller.MultiPlayerButtonListener;
import controller.SinglePlayerButtonListener;
import model.Player;
import view.GameWindow;
import view.UIPanel;


/**
 * Represents game window on which game panel and game-play take place.
 * @author Roger
 *
 */
public class ClientGameWindow extends GameWindow{
	
	/**
	 * Constructor for game window. Initializes fields, creates window and adds JComponents to the window.
	 */
	public ClientGameWindow() {
		super();
		Player p = Controller.getSingleton().getPlayer();
		p.setCharCoord(p.getCharCoord().getX()+100, p.getCharCoord().getY());
	} 

	public static void main(String[] args) {
		ClientGameWindow gw = new ClientGameWindow();
		//Indicate to controller that this is server
		Controller.getSingleton().setServerFlag(false);
		new UIPanel(gw, "Start Game", Color.black, "Single Player", "Multi Player", new SinglePlayerButtonListener(), new MultiPlayerButtonListener());
	}
}
package controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.ClientGameWindow;
import view.GameWindow;
import view.UIPanel;
/**
 * Action listener to control the mode of the game based on main menu selections.
 * @author Roger
 *
 */
public class MainMenuButtonListener implements ActionListener {

	@Override
	/**
	 * Sets the game mode based on user selection.
	 */
	public void actionPerformed(ActionEvent e) {
		Controller.getSingleton().getGameWindow().dispose();
		Controller.getSingleton().resetController();
		GameWindow gw;
		if (Controller.getSingleton().getServerFlag()) {
			gw = new GameWindow();

		}
		else {
			gw = new ClientGameWindow();

		}
		new UIPanel(gw, "Start Game", Color.black, "Single Player", "Multi Player", new SinglePlayerButtonListener(), new MultiPlayerButtonListener());
	}

}

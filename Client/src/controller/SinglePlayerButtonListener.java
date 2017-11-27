package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener to set the game to single player mode.
 * @author Roger
 *
 */
public class SinglePlayerButtonListener implements ActionListener {

	@Override
	/**
	 * Sets game to single player mode.
	 */
	public void actionPerformed(ActionEvent e) {
		Controller.getSingleton().setMultiplayer(false);
	}
}

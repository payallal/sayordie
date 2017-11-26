package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SinglePlayerButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Controller.getSingleton().setMultiplayer(false);
	}
}

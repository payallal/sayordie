package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!Controller.getSingleton().getConnected()) {
			Controller.getSingleton().setTextOfInstruction("Connecting...");
		}
		Controller.getSingleton().record();
	}
}

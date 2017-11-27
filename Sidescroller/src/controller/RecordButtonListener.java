package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements features of the record button.
 * @author Roger
 *
 */
public class RecordButtonListener implements ActionListener {
	/**
	 * Instance of game controller.
	 */
	private Controller c = Controller.getSingleton();

	@Override
	/**
	 * Starts recording when the record button is pressed. 
	 */
	public void actionPerformed(ActionEvent e) {
		//disenable record button, enable again after something is understood
		this.c.getGameWindow().getRecordButton().setEnabled(false);
		if (!this.c.getConnected()) {
			this.c.setTextOfInstruction("Connecting Speech...");
		}
		this.c.record();
	}
}

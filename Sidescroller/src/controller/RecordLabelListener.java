package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RecordLabelListener implements MouseListener {
	private Controller c = Controller.getSingleton();
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.c.setRecordingIndicatorDarkGrey();
		if (!this.c.getConnected()) {
			this.c.setTextOfInstruction("Connecting...");
		}
		this.c.record();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.c.setRecordingIndicatorGrey();		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

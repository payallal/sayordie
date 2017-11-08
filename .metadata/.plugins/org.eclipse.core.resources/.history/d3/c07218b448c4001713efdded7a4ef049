package controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Player;
import view.GamePanel;

public class MyKeyAdapter extends KeyAdapter {
	
	private GamePanel gp;
	private Player player;
	
	public MyKeyAdapter(GamePanel gp) {
		this.gp = gp;
		this.player = gp.getPlayer();
	}
	
	public void keyPressed(KeyEvent kp) {
		
		if (kp.getKeyCode() == KeyEvent.VK_RIGHT && this.player.getMoveableRight() == true) {
			this.player.setDirection(2);
			this.player.setJumpRight(true);
		}
		if (kp.getKeyCode() == KeyEvent.VK_LEFT && this.player.getMoveableLeft() == true) {
			this.player.setDirection(3);
			this.player.setJumpRight(false);
		}
		
		if (kp.getKeyCode() == KeyEvent.VK_SPACE) {
			this.player.checkJump();
		}		

	}
	
	public void keyReleased(KeyEvent kp) {
		if (kp.getKeyCode() == KeyEvent.VK_LEFT || kp.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if (player.getDirection() == 2) {
				this.player.setCurrentSprite(this.player.getStillRightSprite());// if direction is right
			}
			if (player.getDirection() == 3) {
				this.player.setCurrentSprite(this.player.getStillLeftSprite());
			}
			this.player.setDirection(0); // set still image	
		}
	}
	
}
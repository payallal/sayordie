package controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import model.Enemy;
import model.Player;
import model.Player2;
import multiplayer.GameClient;
import view.GamePanel;

public class Controller implements ActionListener {
	
	//Singleton
	private static Controller controller = new Controller();
	
	//the controller does not have any field itself- it leaves that to the view and model classes to carry
	private GamePanel gp;
	private GameClient c;
	//for our convenience
	private Player player;
	private Player2 player2;
	private Timer timer;
		
	private Controller() {}
	
	public static Controller getSingleton() {
		return controller;
	}
	
	public void setGamePanel(GamePanel gp) {
		this.gp = gp;
		this.player = gp.getPlayer();
		this.player2 = gp.getPlayer2();
	}
	
	public void setClient(GameClient c) {
		this.c = c;
	}
	
	public GameClient getClient () {
		return this.c;
	}
	
	//set timer 
	public void setTimer(int i) {
		this.timer = new Timer(i, this);
		this.timer.start();
	}
	
	public void addKeyListenerToGamePanel() {
		this.gp.addKeyListener(new MyKeyAdapter());		
	}
	
	//this is called when timer reaches 30ms. Essentially the update function
	@Override
	public void actionPerformed(ActionEvent e) {
		this.checkBoundsToSetMoveable();
		this.checkMovement(this.player);
		this.checkMovement(this.player2);
		this.checkCollisions();
		this.gp.repaint();
	}
	
	//this is the function that checks the left right bounds to make sure player is not at the edge of the map
	//If player is at the edge, the we set moveable to false
	public void checkBoundsToSetMoveable() {
		if (this.gp.getBgX() >= this.gp.getBGMAX() - 800) {
			this.player.setMoveableRight(false);
		}
		else {
			this.player.setMoveableRight(true);
		}
		
		if (this.gp.getBgX() <= this.gp.getBGMIN()) {
			this.player.setMoveableLeft(false);
		}
		else {
			this.player.setMoveableLeft(true);
		}
	}
	
	public void checkMovement(Player p) {
		if (p.getDirection() == 2) {
			p.right();
		}
		if (p.getDirection() == 3) {
			p.left();
		}
		p.jump();
	}
	
	public void incrementBgX() {
		this.gp.setBgX(this.gp.getBgX() + 8);
		
		//for each enemy, have them move with the background
		for (Enemy e : this.gp.getEnemies()) {
			int newX = e.getCharCoord().getX() - 8;
			e.setCharCoord(newX, e.getCharCoord().getY());
		}
		this.player2.setCharCoord(this.player2.getCharCoord().getX()-8, this.player2.getCharCoord().getY());
	}
	
	public void decrementBgX() {
		this.gp.setBgX(this.gp.getBgX() - 8);
		
		//for each enemy, have them move with the background
		for (Enemy e : this.gp.getEnemies()) {
			int newX = e.getCharCoord().getX() + 8;
			e.setCharCoord(newX, e.getCharCoord().getY());
		}
		this.player2.setCharCoord(this.player2.getCharCoord().getX()+8, this.player2.getCharCoord().getY());
	}
	
	public void checkCollisions() {
		Rectangle playerRect = this.player.getBounds();
		for (Enemy e: this.gp.getEnemies()) {
			Rectangle enemyRect = e.getBounds();
			if (playerRect.intersects(enemyRect)) {
				System.out.println("Collision with Enemy Detected.");
			}
		}
		
		//Check for collision with player2
		Rectangle playerRect2 = this.player2.getBounds();
		if (playerRect.intersects(playerRect2)) {
			System.out.println("Collision with player2 Detected.");
		}
	}
	
	public Player getPlayer() {
		return this.gp.getPlayer();
	}
	
	public int getBgX() {
		return this.gp.getBgX();
	}
	
	public int getBGMAX() {
		return this.gp.getBGMAX();
	}
	
	public int getBGMIN() {
		return this.gp.getBGMIN();
	}
	
	/*all player2 movements here. This should work like MyKeyAdapter*/
	public void updatePlayer2Movement(boolean barray[]) {
		
		boolean left = barray[0];
		boolean right = barray[1];
		boolean jump = barray[2];
		boolean leftr = barray[3];
		boolean rightr = barray[4];
		
		if (right && this.player2.getMoveableRight() == true) {
			this.player2.setDirection(2);
			this.player2.setJumpRight(true);
		}
		
		if (left && this.player2.getMoveableLeft() == true) {
			System.out.println("In if left loop.");
			this.player2.setDirection(3);
			this.player2.setJumpRight(false);
		}
		
		if (jump) {
			this.player2.checkJump();
		}	
		
		if (leftr || rightr)
		{
			if (this.player2.getDirection() == 2) {
				this.player2.setCurrentSprite(this.player2.getStillRightSprite());// if direction is right
			}
			if (this.player2.getDirection() == 3) {
				this.player2.setCurrentSprite(this.player2.getStillLeftSprite());
			}
			this.player2.setDirection(0); // set still image	
		}
	}
}

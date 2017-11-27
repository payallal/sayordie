package controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import audio.AudioHandlerThread;
import audio.AudioPlayerThread;
import model.Coordinate;
import model.Enemy;
import model.Obstacle;

/*
import audio.AudioHandlerThread;
import audio.AudioInterpreterUtil;
import audio.AudioRecordingUtil;
*/

import model.Player;
import model.Player2;
import model.Sprite;
import multiplayer.GameClient;
import view.GamePanel;
import view.ClientGameWindow;

public class Controller implements ActionListener {
	
	//Singleton
	private static Controller controller = new Controller();
	
	
	//the controller does not have any field itself- it leaves that to the view and model classes to carry
	private ClientGameWindow gw;
	private GamePanel gp;
	private GameClient gc;
	//for our convenience
	private boolean gameInProgress = false;
	private boolean multiplayer = true;
	private Player player;
	private Player2 player2;
	private Timer timer;	
	private Obstacle nextObstacle;
	private boolean jumpOverObstacle = false;
	private boolean connected = false;
	private JLabel recordLabel;
	private Icon recordRed = new ImageIcon("img/ui/recordRed.png");
	private Icon recordGrey = new ImageIcon("img/ui/recordGrey.png");
	private Icon recordDarkGrey = new ImageIcon("img/ui/recordDarkGrey.png");
	
	private int newVelocity = 0;
	private final int obstacleDistance = 880;
	private final int accurateJumpDistance = 60;
	private final int playerMovementOffset = 8;
	private final int updateTime = 30;
	
	private Controller() {}
	
	public static Controller getSingleton() {
		return controller;
	}
	
	public void setGameWindow(ClientGameWindow gw) {
		this.gw = gw;
	}
	
	public void setGamePanel(GamePanel gp) {
		this.gp = gp;
		this.player = gp.getPlayer();
		if (this.multiplayer) {
			this.player2 = new Player2(new Coordinate(400, 530));
			this.gp.setPlayer2(this.player2);
		}
		else {
			this.player2 = gp.getPlayer2();
		}
	}
	
	public void setClient(GameClient gc) {
		this.gc = gc;
	}
	
	public GameClient getServer () {
		return this.gc;
	}
	
	//set timer 
	public void setTimer() {
		this.timer = new Timer(this.updateTime, this);
		this.timer.start();
	}
	
	//this is called when timer reaches 30ms. Essentially the update function
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.gameInProgress) {
			
			this.checkBoundsToSetMoveable();
			
			this.checkMovement(this.player);
			
			for (Enemy enemy: this.gp.getEnemies()) {
				this.checkMovement(enemy);
			}
			
			if (this.multiplayer && this.player2 != null) {
				this.checkMovement(this.player2);
			}
			
			this.checkCollisions();
			
			this.nextObstaclePreparation();

		}
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
		this.gp.setBgX(this.gp.getBgX() + this.playerMovementOffset);
		
		//for each sprite, have them move with the background
		for (Sprite s : this.gp.getBackgroundSprites()) {
			int newX = s.getCharCoord().getX() - this.playerMovementOffset;
			s.setCharCoord(newX, s.getCharCoord().getY());
		}
		
		if (this.multiplayer && this.player2 != null) {
			this.player2.setCharCoord(this.player2.getCharCoord().getX()-this.playerMovementOffset, this.player2.getCharCoord().getY());
		}
	}
	
	public void decrementBgX() {
		this.gp.setBgX(this.gp.getBgX() - this.playerMovementOffset);
		
		//for each sprite, have them move with the background
		for (Sprite s : this.gp.getBackgroundSprites()) {
			int newX = s.getCharCoord().getX() + this.playerMovementOffset;
			s.setCharCoord(newX, s.getCharCoord().getY());
		}
		
		if (this.multiplayer && this.player2 != null) {
			this.player2.setCharCoord(this.player2.getCharCoord().getX()+this.playerMovementOffset, this.player2.getCharCoord().getY());
		}
	}
	
	public void checkCollisions() {
		Rectangle playerRect = this.player.getBounds();
		for (Sprite s: this.gp.getHostiles()) {
			Rectangle hostileRect = s.getBounds();
			if (playerRect.intersects(hostileRect)) {
				this.player.die();
				this.setGameOver();
			}
		}
		
		//Check for collision with player2
		if (this.multiplayer && this.player2 != null) {
			Rectangle playerRect2 = this.player2.getBounds();
			if (playerRect.intersects(playerRect2)) {
				System.out.println("Collision with player2 Detected.");
			}
		}
	}
	
	public void nextObstaclePreparation() {
		//check range for obstacles, but we only do so if there is no current nextObstacle
		//remember to set nextobstacle as null when the obstacle is jumped over 
		if (this.nextObstacle == null) {
			for (Obstacle obstacle: this.gp.getObstacles()) {
				if ((obstacle.getCharCoord().getX() - this.player.getCharCoord().getX()) <= this.obstacleDistance) {
					this.nextObstacle = obstacle;
					//AudioPlayerThread apt = new AudioPlayerThread(this.nextObstacle);
					//apt.start();
					this.record();
					for (Enemy e: this.gp.getEnemies()) {
						e.setVelocity(e.getVelocity()+this.newVelocity);
						if (this.newVelocity >= 10) {
							this.newVelocity=0;
						}
						else {
							this.newVelocity+=5;
						}
					}
				}
			}
		}
		
		//if there is a next obstacle
		else {
			//if (!this.recording) {
			//	this.record();
			//  this.recording = true;
			//}
			
			//if we are jumping over the obstacle and the distance is right
			if (this.jumpOverObstacle && ((this.nextObstacle.getCharCoord().getX() - this.player.getCharCoord().getX()) <= this.accurateJumpDistance)) {
				this.player.checkJump();
				this.gp.getObstacles().remove(this.nextObstacle);
				this.nextObstacle = null;
				this.jumpOverObstacle = false;
				//this.recording = false;
			}
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
	
	public boolean getMultiplayer() {
		return this.multiplayer;
	}
	
	public void setMultiplayer(boolean b) {
		this.multiplayer = b;
	}
	
	/*all player2 movements here. This should work like MyKeyAdapter*/
	public void updatePlayer2Movement(boolean barray[]) {
		
		if (this.multiplayer && this.player2 != null) {
		
			boolean start = barray[0];
			boolean right = barray[1];
			boolean jump = barray[2];
			boolean stop = barray[3];
			
			if (start) {
				if (!this.player2.getGameStarted()) {
					this.player2.setGameStarted(true);
					this.player2.setCurrentSprite(this.player2.getStillRightSprite());
				}			
			}
			
			if (!this.player2.getGameStarted()) {
				return;
			}
			
			if (right && this.player2.getMoveableRight() == true) {
				this.player2.setDirection(2);
				this.player2.setJumpRight(true);
			}
			
			
			if (jump) {
				this.player2.checkJump();
			}	
			
			if (stop)
			{
				if (this.player2.getDirection() == 2) {
					this.player2.setCurrentSprite(this.player2.getStillRightSprite());// set still image	
				}

				this.player2.setDirection(0); //set direction back to still
			}
		}
	}
	
	public void setRecordLabel(JLabel recordLabel) {
		this.recordLabel = recordLabel;
	}
	
	public void setRecordLabelMouseListener(JLabel recordLabel) {
		recordLabel.addMouseListener(new RecordLabelListener());
	}
	
	public void setRecordingIndicatorRed() {
		this.recordLabel.setIcon(this.recordRed);
	}
	
	public void setRecordingIndicatorGrey() {
		this.recordLabel.setIcon(this.recordGrey);
	}
	
	public void setRecordingIndicatorDarkGrey() {
		this.recordLabel.setIcon(this.recordDarkGrey);
	}
	
	/*audio listening part*/
	public void record() {
		AudioHandlerThread aht = new AudioHandlerThread(1500);
		aht.start();
	}
	
	//this method is different from server
	public void convertStringToMovement(String s) {
		
		boolean[] barray = new boolean[4];
		Arrays.fill(barray, false);	
		
		if (s.contains("start") || s.contains("begin")) {
			if (!this.gameInProgress) {		
				
				this.gameInProgress = true;
				this.player.setCurrentSprite(this.player.getStillRightSprite());
				if (multiplayer) {
					//Tell client we have started game, and then wait for client to start game
					barray[0] = true; 
					this.gc.sendJSONToServer(barray);
					
					//we wait for player 2 to get started, disable the recording button
					this.setTextOfInstruction("Waiting for other player...");
					this.setTextOfWordSaid(s);
					//this.gw.getRecordButton().setEnabled(false);
					while(!player2.getGameStarted()) {}
					//this.gw.getRecordButton().setEnabled(true);

					//Once player 2 has joined, we start moving right
					this.setTextOfInstruction("Player 2 is in. BEGIN!");
					if (this.player.getMoveableRight()) {
						this.player.setDirection(2);
						this.player.setJumpRight(true);
					}
				
					//Start telling client that server player is moving right
					barray[1] = true; //set right
					this.gc.sendJSONToServer(barray);
				}
			}
		}
		
		//start or begin is the only command allowed if game is not in progress
		if (!this.gameInProgress) {
			return;
		}
		
		if (s.contains("walk") || s.contains("right")) {
			if (this.player.getMoveableRight()) {
				this.player.setDirection(2);
				this.player.setJumpRight(true);
			}
			
			for (Enemy e : this.gp.getEnemies()) {
				if (e.getMoveableRight()) {
					e.setDirection(2);
					e.setJumpRight(true);
				}
			}
		}
		
		if (this.nextObstacle != null) {
			if (s.contains(this.nextObstacle.getCaption().getText())) {
				this.jumpOverObstacle = true;
				//calm the enemy down
				for (Enemy enemy : this.gp.getEnemies()) {
					enemy.setVelocity(0);
				}
			}
		}
		
		if (s.contains("stop") && (this.player.getDirection() == 2)) {
			this.player.setCurrentSprite(this.player.getStillRightSprite());
			this.player.setDirection(0); 	

		}
	}
	
	public void setTextOfWordSaid(String wordSaid) {
		this.gp.setTextOfWordSaid(wordSaid);
	}
	
	public void setTextOfInstruction(String instruction) {
		this.gp.setTextOfInstruction(instruction);
	}
	
	
	public void setGameOver() {
		this.gameInProgress = false;
		this.setTextOfInstruction("GAME OVER.");
	}
	
	public void setNextObstacle(Obstacle o) {
		this.nextObstacle = o;
	}
	
	public boolean getConnected() {
		return this.connected;
	}
	
	public void setConnected(boolean b) {
		this.connected = b;
	}
	
	public void loadObstacles(ArrayList <Obstacle> obstacle, ArrayList<Coordinate> obstacleCoordinates, ArrayList<String> obstacleLibrary) {
		for (Coordinate coordinate: obstacleCoordinates) {
			//get random obstacle from obstacle library
			Random randomizer = new Random();
			String randomObstacleName = obstacleLibrary.get(randomizer.nextInt(obstacleLibrary.size()));
			obstacleLibrary.remove(randomObstacleName);
			//add obstacle to obstacle list
			obstacle.add(new Obstacle(coordinate, randomObstacleName));
		}
	}
}

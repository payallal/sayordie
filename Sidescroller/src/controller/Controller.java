package controller;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

import audio.AudioHandlerThread;
import audio.AudioPlayerThread;
import client.GameClient;
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
import multiplayer.GameServer;
import view.GamePanel;
import view.GameWindow;
import view.UIPanel;

/**
 * CLass which translates user input into onscreen actions.
 * @author Roger
 *
 */
public class Controller implements ActionListener {
	
	
	/**
	 * Singleton for game controller.
	 */
	private static Controller controller = new Controller();
	
	/**
	 * Server or client flag
	 */
	private boolean serverFlag;
	//the controller does not have any field itself- it leaves that to the view and model classes to carry
	/**
	 * Stores an instance of the game window.
	 * @see view.GameWindow
	 */
	private GameWindow gw;
	/**
	 * Stores an instance of the game panel.
	 * @see view.GamePanel
	 */
	private GamePanel gp;
	/**
	 * Stores an instance of the game server for multiplayer mode.
	 * @see multiplayer.GameServer
	 */
	private GameServer gs;
	/**
	 * Stores an instance of the game client for multiplayer mode.
	 * @see multiplayer.GameServer
	 */
	private GameClient gc;
	//for our convenience
	/**
	 * Stores boolean value that changes during runtime to indicate when the game has started.
	 */
	private boolean gameInProgress = false;
	/**
	 * Stores boolean value to indicate whether or not the game is in multiplayer mode.
	 */
	private boolean multiplayer = false;
	/**
	 * Stores information about an instance of the player that the user will control.
	 * @see model.Player
	 */
	private Player player;
	/**
	 * Stores information about the second player that will be controlled in multiplayer mode.
	 * @see model.Player2
	 */
	private Player2 player2;
	/**
	 * Stores an instance of the timer to be used to determine the times to activate the microphone.
	 */
	private Timer timer;	
	/**
	 * Stores the upcoming obstacle during runtime. Changes obstacle dependent on other conditions.
	 */
	private Obstacle nextObstacle;
	/**
	 * Stores boolean value to indicate if the player is currently jumping over an obstacle.
	 */
	private boolean jumpOverObstacle = false;
	/**
	 * 
	 */
	private boolean connected = false;
	/**
	 * Stores boolean value to indicate if the microphone is currently recording.
	 */
	private boolean recording = false;
	
	/**
	 * Stores the JButton which the user clicks to start the microphone.
	 */
	private JButton recordButton;
	/**
	 * Stores the image that is displayed when the microphone is activated.
	 */
	private Icon recordRed = new ImageIcon("img/ui/recordRed.png");
	/**
	 * Stores the image that is displayed when the microphone is inactive.
	 */
	private Icon recordGrey = new ImageIcon("img/ui/recordGrey.png");
	/**
	 * Stores the image that is displayed when the microphone button is pressed to give the visual effect of pressing the button.
	 */
	private Icon recordDarkGrey = new ImageIcon("img/ui/recordDarkGrey.png");
	/**
	 * Stores change in velocity of enemy sprite.
	 */
	private int newVelocity = 0;
	/**
	 * Stores the distance from the object at which the microphone is activated.
	 */
	private final int obstacleDistance = 990;
	/**
	 * Stores the distance from the object at which the microphone should start recording.
	 */
	private final int recordingDistance = 890;
	/**
	 * Stores distance to calculate jumping over obstacle.
	 */
	private final int accurateJumpDistance = 60;
	/**
	 * Stores sprite's offset to make sprite image move with the background.
	 */
	private final int playerMovementOffset = 8;
	/**
	 * Stores value to set timer in seconds.
	 */
	private final int updateTime = 30;
	
	/**
	 * Constructor for controller singleton.
	 */
	private Controller() {}
	
	/**
	 * Getter method for the controller.
	 * @return current instance of the controller.
	 */
	public static Controller getSingleton() {
		return controller;
	}
	
	/**
	 * Setter method to initialize the GameWindow field of the controller.
	 * @param gw an instance of game window
	 * @see view.GameWindow
	 */
	public void setGameWindow(GameWindow gw) {
		this.gw = gw;
	}
	
	/**
	 * Getter method to retrieve the game window stored in this class's GameWindow variable.
	 * @return instance of game window being used by controller
	 * @see view.GameWindow
	 */
	public GameWindow getGameWindow() {
		return this.gw;
	}
	
	public GamePanel getGamePanel() {
		return this.gp;
	}
	
	/**
	 * Initializes the GamePanel, Player and Player2 fields.
	 * @param gp instance of the current game panel.
	 */
	public void setGamePanel(GamePanel gp) {
		this.gp = gp;
		this.player = gp.getPlayer();
		this.player2 = gp.getPlayer2();
	}
	
	public boolean getServerFlag () {
		return this.serverFlag;
	}
	
	public void setServerFlag (boolean b) {
		this.serverFlag = b;
	}
	
	/**
	 * Setter method for the game server
	 * @param gs current instance of game server when in multiplayer mode.
	 * @see multiplayer.GameServer
	 */
	public void setServer(GameServer gs) {
		this.gs = gs;
	}
	
	/**
	 * Setter method for the game server
	 * @param gc current instance of game client when in multiplayer mode.
	 * @see client.GameClient
	 */
	public void setClient(GameClient gc) {
		this.gc = gc;
	}
	/**
	 * Getter method for the given instance of the game server.
	 * @return an instance of the game server.
	 * @see multiplayer.GameServer
	 */
	public GameServer getServer () {
		return this.gs;
	}
	
	/**
	 * Sets and starts timer.
	 */
	public void setTimer() {
		this.timer = new Timer(this.updateTime, this);
		this.timer.start();
	}
	
	//this is called when timer reaches 30ms. Essentially the update function
	@Override
	/**
	 * Update function which repaints screen and checks the various boolean values such as whether there is a collision.
	 * @param e an action event.
	 */
	public void actionPerformed(ActionEvent e) {
		if (this.gameInProgress) {
			
			this.checkBoundsToSetMoveable();
			
			this.checkMovement(this.player);
			
			for (Enemy enemy: this.gp.getEnemies()) {
				this.checkMovement(enemy);
			}
			
			if (player2 != null) {
				this.checkMovement(this.player2);
			}
			
			this.checkCollisions();
			
			this.nextObstaclePreparation();

		}
		this.gp.repaint();
	}

	/**
	 *  Checks the left right bounds to make sure player is not at the edge of the map.
	 *  If player is at the edge, then set moveable to false
	 */
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
	
	/**
	 * Moves the player in the indicated direction.
	 * @param p current instance of the player.
	 */
	public void checkMovement(Player p) {
		if (p.getDirection() == 2) {
			p.right();
		}
		if (p.getDirection() == 3) {
			p.left();
		}
		p.jump();
	}
	/**
	 * Increases the background X-coordinate by player offset.
	 */
	public void incrementBgX() {
		this.gp.setBgX(this.gp.getBgX() + this.playerMovementOffset);
		
		//for each sprite, have them move with the background
		for (Sprite s : this.gp.getBackgroundSprites()) {
			int newX = s.getCharCoord().getX() - this.playerMovementOffset;
			s.setCharCoord(newX, s.getCharCoord().getY());
		}
		
		if (this.player2 != null) {
			this.player2.setCharCoord(this.player2.getCharCoord().getX()-this.playerMovementOffset, this.player2.getCharCoord().getY());
		}
	}
	/**
	 * Decreases the background X-coordinate by player offset.
	 */
	public void decrementBgX() {
		this.gp.setBgX(this.gp.getBgX() - this.playerMovementOffset);
		
		//for each sprite, have them move with the background
		for (Sprite s : this.gp.getBackgroundSprites()) {
			int newX = s.getCharCoord().getX() + this.playerMovementOffset;
			s.setCharCoord(newX, s.getCharCoord().getY());
		}
		
		if (this.player2 != null) {
			this.player2.setCharCoord(this.player2.getCharCoord().getX()+this.playerMovementOffset, this.player2.getCharCoord().getY());
		}
	}
	
	/**
	 * Checks whether player collided with other sprites and ends gameplay if collision occurs. 
	 */
	public void checkCollisions() {
		Rectangle playerRect = this.player.getBounds();
		for (Sprite s: this.gp.getHostiles()) {
			Rectangle hostileRect = s.getBounds();
			if (playerRect.intersects(hostileRect)) {
				this.setGameOver();
			}
		}
		
		//Check for collision with player2
		if (this.player2 != null) {
			Rectangle playerRect2 = this.player2.getBounds();
			if (playerRect.intersects(playerRect2)) {
			}
		}
	}
	/**
	 * Resets controller fields after jumping over an obstacle.
	 * Changes enemy velocity, resets booleans and sets microphone.
	 */
	public void nextObstaclePreparation() {
		//check range for obstacles, but we only do so if there is no current nextObstacle
		//remember to set nextobstacle as null when the obstacle is jumped over 
		if (this.nextObstacle == null) {
			for (Obstacle obstacle: this.gp.getObstacles()) {
				if ((obstacle.getCharCoord().getX() - this.player.getCharCoord().getX()) <= this.obstacleDistance) {
					this.nextObstacle = obstacle;
					AudioPlayerThread apt = new AudioPlayerThread(this.nextObstacle);
					apt.start();
					//this.record();
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
			//if recording is within recording distance
			if (!this.recording && (this.nextObstacle.getCharCoord().getX() - this.player.getCharCoord().getX()) <= this.recordingDistance) {
				this.record();
				this.recording = true;
			}
			
			//if we are jumping over the obstacle and the distance is right
			if (this.jumpOverObstacle && ((this.nextObstacle.getCharCoord().getX() - this.player.getCharCoord().getX()) <= this.accurateJumpDistance)) {
				this.player.checkJump();
				this.gp.getObstacles().remove(this.nextObstacle);
				this.nextObstacle = null;
				this.jumpOverObstacle = false;
				this.recording = false;
			}
		}
	}
	/**
	 * Getter method for current instance of Player in this class.
	 * @return current instance of player.
	 */
	public Player getPlayer() {
		return this.gp.getPlayer();
	}
	/**
	 * Getter method for the current background image coordinates.
	 * @return the current background image coordinates.
	 */
	public int getBgX() {
		return this.gp.getBgX();
	}
	/**
	 * Getter method for the current maximum X-coordinate of background image.
	 * @return the current maximum X-coordinate of background image.
	 */
	public int getBGMAX() {
		return this.gp.getBGMAX();
	}
	/**
	 * Getter method for the current minimum X-coordinate of background image.
	 * @return the current minimum X-coordinate of background image.
	 */
	public int getBGMIN() {
		return this.gp.getBGMIN();
	}
	/**
	 * Getter method for the whether the game is in multiplayer mode.
	 * @return boolean value of whether the game is in multiplayer mode.
	 */
	public boolean getMultiplayer() {
		return this.multiplayer;
	}
	/**
	 * Setter method to change boolean value determining whether game is in multiplayer mode.
	 * @param b new boolean value for whether the game is in multiplayer mode.
	 */
	public void setMultiplayer(boolean b) {
		this.multiplayer = b;
	}
	/**
	 * Getter method for whether game is still in progress.
	 * @return boolean value of whether game is in progress.
	 */
	public boolean getGameInProgress() {
		return this.gameInProgress;
	}
	
	/**
	 * Controls all player2 movements.
	 * @param barray array of the boolean parameters necessary to determine character movement.
	 */
	public void updatePlayer2Movement(boolean barray[]) {
		
		if (this.player2 != null) {
		
			boolean start = barray[0];
			boolean right = barray[1];
			boolean jump = barray[2];
			boolean over = barray[3]; //game over
			
			if (over) {
				this.setGameWon();
			}
			
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
			
		}
	}
	/**
	 * Sets the record button to this class' instance of the record button
	 * @param recordButton field
	 */
	public void setRecordButton(JButton recordButton) {
		this.recordButton = recordButton;
	}
	/**
	 * Adds action listener to recod button.
	 * @param recordButton field of controller class.
	 */
	public void setRecordButtonMouseListener(JButton recordButton) {
		recordButton.addActionListener(new RecordButtonListener());
	}
	/**
	 * Changes the record icon to red image.
	 */
	public void setRecordingIndicatorRed() {
		this.recordButton.setIcon(this.recordRed);
	}
	/**
	 * Changes the record icon to grey image.
	 */
	public void setRecordingIndicatorGrey() {
		this.recordButton.setIcon(this.recordGrey);
	}
	/**
	 * Changes the record icon to dark grey image.
	 */
	public void setRecordingIndicatorDarkGrey() {
		this.recordButton.setIcon(this.recordDarkGrey);
	}
	
	/*audio listening part*/
	/**
	 * Handles audio by starting audio recording for set time period.
	 */
	public void record() {
		AudioHandlerThread aht = new AudioHandlerThread(1500);
		aht.start();
	}
	
	/**
	 * All entities start moving right, game actually begins
	 */
	public void beginGame() {
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
	
	/**
	 * Connects audio transcriptions from user to player actions. 
	 * @param s transcript from user speech.
	 */
	public void convertStringToMovement(String s) {
		
		boolean[] barray = new boolean[4];
		Arrays.fill(barray, false);	
		
		if (s.contains("start") || s.contains("begin")) {
			if (!this.gameInProgress) {		
				
				this.gameInProgress = true;
				this.player.setCurrentSprite(this.player.getStillRightSprite());
				
				//If this is a multiplayer game, we have to wait for the other player to get started
				if (multiplayer) {
					
					//Tell client we have started game, and then wait for client to start game
					barray[0] = true; 
					
					//Client and server send their array with new movement
					if (this.serverFlag) {
						this.gs.sendJSONToClients(barray);
					}
					else {
						this.gc.sendJSONToServer(barray);
					}
					
					//we wait for player 2 to get started, disable the recording button
					this.setTextOfInstruction("Waiting for other player...");
					this.setTextOfWordSaid(s);
					this.gw.getRecordButton().setEnabled(false);
					while(!player2.getGameStarted()) {}
					this.gw.getRecordButton().setEnabled(true);

					//Once player 2 has joined, we start moving right
					this.setTextOfInstruction("Player 2 is in. BEGIN!");
					if (this.player.getMoveableRight()) {
						this.player.setDirection(2);
						this.player.setJumpRight(true);
					}
				
					//Start telling client that server player is moving right
					barray[1] = true; //set right
					
					//Client and server send their array with new movement
					if (this.serverFlag) {
						this.gs.sendJSONToClients(barray);
					}
					else {
						this.gc.sendJSONToServer(barray);
					}
				}
				
				//When everything is ready we begin the game
				this.beginGame();
			}
		}
		
		//start or begin is the only command allowed if game is not in progress
		if (!this.gameInProgress) {
			return;
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
	/**
	 * Setter method to change onscreen text to the audio transcription from user.
	 * @param wordSaid audio transcript from user.
	 */
	public void setTextOfWordSaid(String wordSaid) {
		this.gp.setTextOfWordSaid(wordSaid);
	}
	/**
	 * Setter method to change instructions displayed on the screen.
	 * @param instruction text to be displayed
	 */
	public void setTextOfInstruction(String instruction) {
		this.gp.setTextOfInstruction(instruction);
	}
	
	/**
	 * Ends current gameplay and sets instruction text to game over text.
	 */
	public void setGameOver() {
		//player has died, player2 has won
		this.player.die();
		this.gameInProgress = false;

		this.setTextOfInstruction("GAME OVER.");

		//if multi-player quickly send instructions that this player has died - see setGameWon method
		if (this.multiplayer) {	
			//stop player2
			if (this.player2.getDirection() == 2) {
				this.player2.setCurrentSprite(this.player2.getStillRightSprite());// set still image	
			}			
			this.player2.setDirection(0);
			boolean[] barray = new boolean[4];
			Arrays.fill(barray, false);	
			barray[3] = true; //set the 3rd index to true because game is over
			if (this.serverFlag) {
				this.gs.sendJSONToClients(barray);
			}
			else {
				this.gc.sendJSONToServer(barray);
			}
		}
		
		new UIPanel(this.gw, "Game Over", Color.black, "Main Menu", "Replay", new MainMenuButtonListener(), new ReplayButtonListener());
	}
	
	public void setGameWon() {
		//player has won, player2 has died
		this.gameInProgress = false;
		if (this.multiplayer) {
			this.player2.die();
		}
		if (this.player.getDirection() == 2) {
			this.player.setCurrentSprite(this.player2.getStillRightSprite());// set still image	
		}		
		this.player.setDirection(0);
		this.setTextOfInstruction("GAME WON!");
		
		new UIPanel(this.gw, "You Won!", Color.pink, "Main Menu", "Replay", new MainMenuButtonListener(), new ReplayButtonListener());
	}
	
	/**
	 * Setter method to set the next obstacle that the player will encounter.
	 * @param o the next game obstacle 
	 */
	public void setNextObstacle(Obstacle o) {
		this.nextObstacle = o;
	}
	
	public boolean getConnected() {
		return this.connected;
	}
	
	public void setConnected(boolean b) {
		this.connected = b;
	}
	
	public void addAllNewObstacles(ArrayList<Obstacle> obstacles) {
		this.gp.getSprites().addAll(obstacles);
		this.gp.getHostiles().addAll(obstacles);
		this.gp.getBackgroundSprites().addAll(obstacles);
		
		//Add all captions from obstacles into words and bgsprites list
		for (Obstacle o : obstacles) {
			this.gp.getSpriteWords().add(o.getCaption());
			this.gp.getBackgroundSprites().add(o.getCaption());
		}
	}
	
	/**
	 * Loads game obstacles into array in a random order.
	 * @param obstacle an array list of game obstacle sprites. 
	 * @param obstacleCoordinates an array list of the coordinates of each game obstacle sprite.
	 * @param obstacleLibrary an array list of the strings associated with the game obstacles.
	 */
	public void loadRandomObstacles() {
		assert(!this.multiplayer);
		ArrayList <Obstacle> obstacles = this.gp.getObstacles();
		ArrayList<Coordinate> obstacleCoordinates = this.gp.getObstacleCoordinates();
		ArrayList<String> obstacleLibrary = this.gp.getObstacleLibrary();
		for (Coordinate coordinate: obstacleCoordinates) {
			//get random obstacle from obstacle library
			Random randomizer = new Random();
			String randomObstacleName = obstacleLibrary.get(randomizer.nextInt(obstacleLibrary.size()));
			obstacleLibrary.remove(randomObstacleName);
			//add obstacle to obstacle list
			obstacles.add(new Obstacle(coordinate, randomObstacleName));
		}
		this.addAllNewObstacles(obstacles);
	}
	/**
	 * Loads game obstacles into array in fixed order for multiplayer.
	 * @param obstacle an array list of game obstacle sprites. 
	 * @param obstacleCoordinates an array list of the coordinates of each game obstacle sprite.
	 * @param obstacleLibrary an array list of the strings associated with the game obstacles.
	 */
	public void loadFixedObstacles() {
		assert(this.multiplayer);
		ArrayList <Obstacle> obstacles = this.gp.getObstacles();
		ArrayList<Coordinate> obstacleCoordinates = this.gp.getObstacleCoordinates();
		ArrayList<String> obstacleLibrary = this.gp.getObstacleLibrary();
		int i = 1;
		for (Coordinate coordinate: obstacleCoordinates) {
			//get random obstacle from obstacle library
			String obstacleName = obstacleLibrary.get(i);
			//add obstacle to obstacle list
			obstacles.add(new Obstacle(coordinate, obstacleName));
			i++;
		}
		this.addAllNewObstacles(obstacles);
	}
	/**
	 * Initializes the second player in multiplayer mode.
	 */
	public void setPlayer2() {
		//if a thread is setting player 2, it must be because of multiplayer
		assert(this.multiplayer);
		if (this.serverFlag) {
			this.player2 = new Player2(new Coordinate(500, 530));
		}
		else {
			this.player2 = new Player2(new Coordinate(400, 530));
		}
		this.gp.setPlayer2(this.player2);
	}
	
}

package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import controller.Controller;
import model.Coordinate;
import model.Enemy;
import model.Obstacle;
import model.Player;
import model.Player2;
import model.Sprite;
import model.Word;

import java.util.*;
/**
 * JPanel on which game play takes place. 
 * @author Roger
 */
public class GamePanel extends JPanel {
	
	final private int BGMIN_X;
	final private int BGMAX_X;
	
	/**
	 * Stores background image to be displayed.
	 */
	private Image backgroundImage;
	/**
	 * Stores image of a red recording mic to be displayed when the microphone is active.
	 */
	private Image recordRed;
	/**
	 * Stores image of a grey recording mic to be displayed when the microphone is inactive.
	 */
	private Image recordGrey;
	/**
	 * Stores the recording mic to be displayed dependent on the current microphone state (ie. active or inactive).
	 * Serves as an intermediary to allow for toggling between states.
	 */
	private Image record;
	
	/**
	 * Stores information about the player that the user will control.
	 * @see model.Player.java
	 */
	private Player player;
	private Player2 player2;
	//probably need a list to keep track of all the enemies but later
	/**
	 * background offsets for when the main player moves left and right
	 */
	private int bgX;
	private int bgY;
	/**
	 * Stores instance of controller which moves sprite based on user input.
	 * @see controller.Controller
	 */
	private Controller controller;
	/**
	 * Stores width of game screen.
	 */
	private int width;
	/**
	 * Stores height of game screen.
	 */
	private int height;
	/**
	 * Stores arrays to keep track of enemies.
	 * @see model.Enemy
	 */
	private ArrayList<Enemy> enemies;
	
	//private ArrayList<Backgrounds> backgrounds;
	
	/**
	 * Stores arrays of game obstacles.
	 * @see model.Obstacle
	 */
	private ArrayList<Obstacle> obstacles;
	/**
	 * Stores the array of all hostile sprites, that is, the obstacles and enemy sprites.
	 * @see model.Sprite
	 */
	private ArrayList<Sprite> hostiles;
	/**
	 * Stores the array of all background sprites including text. These sprites don't interact with the user.
	 * @see model.Sprite
	 */
	private ArrayList<Sprite> backgroundSprites;
	/**
	 * Stores an array to keep track of all sprites except text;
	 *  @see model.Sprite
	 */
	private ArrayList<Sprite> sprites;
	/**
	 * Stores the array to keep track of all text.
	 * @see model.Word
	 */
	private ArrayList<Word> words;
	/**
	 * Stores text of word to be said by user. Empty at first.
	 * @see model.Word
	 */
	private Word textOfWordSaid;
	/**
	 * Stores in-game text instructions to be displayed on the screen.
	 * @see model.Word
	 */
	private Word textInstruction;
	
	/**
	 * Constructor the Game Panel. Initializes all the class fields and sets images.
	 * @param w width of the screen. Passed to the #width variable of the class.
	 * @param h height of the screen. Passed to the #height variable of the class.
	 */
	public GamePanel(int w, int h) {
		
		this.width = w;
		this.height = h;
		
		this.BGMIN_X = 1000;
		this.BGMAX_X = 10000;
		
		this.backgroundImage = new ImageIcon("img/background/bg.png").getImage();
		this.recordRed = new ImageIcon("img/ui/recordRed.png").getImage();
		this.recordGrey = new ImageIcon("img/ui/recordGrey.png").getImage();
		this.record = this.recordGrey;

		
		//Create player and player2
		this.player = new Player(new Coordinate(400,530));
		//this.player2 = new Player2(new Coordinate(650, 530));
		
		//Put the enemies in relevant array
		this.enemies = new ArrayList<Enemy>();
		this.enemies.add(new Enemy(new Coordinate(100, 530)));
		
		//Put the obstacles in relevant array
		this.obstacles = new ArrayList<Obstacle>();
		this.obstacles.add(new Obstacle(new Coordinate(3000, 645), "img/sprites/obstacles/flower.png", "flower"));
		
		//add all hostiles
		this.hostiles = new ArrayList<Sprite>();
		this.hostiles.addAll(this.enemies);
		this.hostiles.addAll(this.obstacles);
		
		//Add all sprites to sprites list
		this.sprites = new ArrayList<Sprite>();
		this.sprites.add(this.player);
		this.sprites.addAll(this.enemies);
		this.sprites.addAll(this.obstacles);
		
		//On Screen text
		this.words = new ArrayList<Word>();
		Word status = new Word("Status:", new Coordinate(this.width-600,60));
		this.textInstruction = new Word("", new Coordinate(this.width-400,60));
		Word whatYouSaid = new Word("What you said:", new Coordinate(this.width-600,90));
		this.textOfWordSaid = new Word("", new Coordinate(this.width-400,90));

		this.words.add(this.textOfWordSaid);
		this.words.add(this.textInstruction);
		this.words.add(status);
		this.words.add(whatYouSaid);
		
		//set background offsets
		this.bgX = 700; //695;
		this.bgY = 535;
		
		//Add all bg sprites
		this.backgroundSprites = new ArrayList<Sprite>();
		this.backgroundSprites.addAll(this.hostiles);
		
		//Add all captions from obstacles into words and bgsprites list
		for (Obstacle o : this.obstacles) {
			this.words.add(o.getCaption());
			this.backgroundSprites.add(o.getCaption());
		}
		
		for (Enemy e : this.enemies) {
			this.words.add(e.getCaption());
			this.backgroundSprites.add(e.getCaption());
		}
		
		//This is the part where the controller adds listeners
		this.controller = Controller.getSingleton();
		this.controller.setGamePanel(this);
		this.controller.setTimer(30);
		this.controller.addKeyListenerToGamePanel();
		
		setLayout(null);
	}
	/**
	 * Paint component which draws the images to the screen and updates the screen during gameplay.
	 * @param g Java swing graphics object.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		this.drawBackground(g2d);
		this.drawSprites(g2d);
		this.drawText(g2d);
	}
	/**
	 * Helper method used in paint component to draw the background image. Uses the offset to give the sidescrolling effect in the game.
	 * @see paintComponent()
	 * @param g2d
	 */
	public void drawBackground(Graphics2D g2d) {
		g2d.drawImage(this.backgroundImage, 700 - this.bgX, 0, null);
		g2d.drawImage(this.record, this.width-200,20,null);
	}
	/**
	 * Helper method used in the paint component to draw sprites over the background image at the current coordinates.
	 * @see paintComponent()
	 * @param g2d Java swing Graphics2D object.
	 */
	public void drawSprites(Graphics2D g2d) {
		for (Sprite s : this.sprites) {
			g2d.drawImage(s.getCurrentSprite(), s.getCharCoord().getX(), s.getCharCoord().getY(), null);
		}
	}
	/**
	 * Helper method used in the paint component to draw text over the background image at the current coordinates.
	 * @see paintComponent()
	 * @param g2d Java swing Graphics2D object.
	 */
	public void drawText(Graphics2D g2d) {
		g2d.setFont(new Font("Helvetica", Font.BOLD, 20)); 
		for (Word w : this.words) {
			g2d.drawString(w.getText(), w.getCharCoord().getX(), w.getCharCoord().getY());
		}
	}
	/**
	 * Getter method for the player.
	 * @return player field of this class.
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Getter method for player2 of this class.
	 * @return player2 field of this class.
	 */
	public Player2 getPlayer2() {
		return this.player2;
	}
	/**
	 * Getter method for the background offset.
	 * @return bgX (background offset) field of this class.
	 */
	public int getBgX() {
		return this.bgX;
	}
	
	public int getBGMAX() {
		return this.BGMAX_X;
	}
	
	public int getBGMIN() {
		return this.BGMIN_X;
	}
	
	/**
	 * Getter method for the array of enemies.
	 * @return enemies field of this class.
	 */
	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	/**
	 * Getter method for the array of obstacles.
	 * @return obstacles field of this class.
	 */
	public ArrayList<Obstacle> getObstacles() {
		return this.obstacles;
	}
	/**
	 * Getter method for the array of background sprites.
	 * @return backgroundSprites field of this class.
	 */
	public ArrayList<Sprite> getBackgroundSprites() {
		return this.backgroundSprites;
	}
	/**
	 * Getter method for the array of hostile sprites.
	 * @return hostiles field of this class.
	 */
	public ArrayList<Sprite> getHostiles() {
		return this.hostiles;
	}
	/**
	 * Getter method for the array of sprites.
	 * @return sprites field of this class.
	 */
	public ArrayList<Sprite> getSprites() {
		return this.sprites;
	}
	/**
	 * Setter method for the background offset.
	 * @param i the new integer value of the background offset bgX.
	 */
	public void setBgX(int i) {
		this.bgX = i;
	}
	/**
	 * Setter method for on-screen instruction text.
	 * @param instruction the string to be displayed.
	 */
	public void setTextOfInstruction(String instruction) {
		this.textInstruction.setText(instruction);
	}
	/**
	 * Setter method for transcriptions of user's speech. 
	 * @param wordSaid string with transcription of user's speech.
	 */
	public void setTextOfWordSaid(String wordSaid) {
		this.textOfWordSaid.setText(wordSaid);
	}
	/**
	 * Setter method to change the #record field to the red record icon.
	 */
	public void setRecordingIndicatorRed() {
		this.record = this.recordRed;
	}
	/**
	 * Setter method to change the #record field to the grey record icon.
	 */
	public void setRecordingIndicatorGrey() {
		this.record = this.recordGrey;
	}
	
}

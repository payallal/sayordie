package view;
import java.awt.*;

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

public class GamePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final private int BGMIN_X;
	final private int BGMAX_X;
	
	//creating images for single objects
	private Image backgroundImage;
	private Image recordRed;
	private Image recordGrey;
	private Image recordIndicator;
	
	//Player variable and player2 variable
	private Player player;
	private Player2 player2;
	//probably need a list to keep track of all the enemies but later
	//background offsets for when the main player moves left and right
	private int bgX;
	private int bgY;
	private Controller controller;
	
	private int width;
	private int height;
	
	//Arrays to keep track of various groups
	private ArrayList<Enemy> enemies;
	//private ArrayList<Backgrounds> backgrounds;
	private ArrayList<Obstacle> obstacles;
	
	//the array of all hostile sprites
	private ArrayList<Sprite> hostiles;

	//the array of all bg sprites (includes text)
	private ArrayList<Sprite> backgroundSprites;

	//the one array to keep track of all sprites except text;
	private ArrayList<Sprite> sprites;
	
	//the array to keep track of all text;
	private ArrayList<Word> words;
	
	//Text of word said by user. Empty at first
	private Word textOfWordSaid;
	private Word textInstruction;
	
	public GamePanel(int w, int h) {
		
		this.width = w;
		this.height = h;
		
		this.BGMIN_X = 1000;
		this.BGMAX_X = 10000;
		
		this.backgroundImage = new ImageIcon("img/background/bg.png").getImage();
		this.recordRed = new ImageIcon("img/ui/recordRed.png").getImage();
		this.recordGrey = new ImageIcon("img/ui/recordGrey.png").getImage();
		this.recordIndicator = this.recordGrey;

		//Get controller singleton
		this.controller = Controller.getSingleton();
		
		//Create player and player2
		this.player = new Player(new Coordinate(400,530));
		
		//If multiplayer is true then we create a player2
		if (controller.getMultiplayer()) {
			this.player2 = new Player2(new Coordinate(650, 530));
		}
		
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
		this.controller.setGamePanel(this);
		this.controller.setTimer(30);
		//this.controller.addKeyListenerToGamePanel();
		
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		this.drawBackground(g2d);
		this.drawSprites(g2d);
		this.drawText(g2d);
	}
	 
	public void drawBackground(Graphics2D g2d) {
		g2d.drawImage(this.backgroundImage, 700 - this.bgX, 0, null);
		g2d.drawImage(this.recordIndicator, this.width-200,20,null);
	}
	
	public void drawSprites(Graphics2D g2d) {
		for (Sprite s : this.sprites) {
			g2d.drawImage(s.getCurrentSprite(), s.getCharCoord().getX(), s.getCharCoord().getY(), null);
		}
	}
	
	public void drawText(Graphics2D g2d) {
		g2d.setFont(new Font("Helvetica", Font.BOLD, 20)); 
		for (Word w : this.words) {
			g2d.drawString(w.getText(), w.getCharCoord().getX(), w.getCharCoord().getY());
		}
	}

	public Player getPlayer() {
		return this.player;
	}
	
	
	public Player2 getPlayer2() {
		return this.player2;
	}
	
	public int getBgX() {
		return this.bgX;
	}
	
	public int getBGMAX() {
		return this.BGMAX_X;
	}
	
	public int getBGMIN() {
		return this.BGMIN_X;
	}
	
	
	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public ArrayList<Obstacle> getObstacles() {
		return this.obstacles;
	}
	
	public ArrayList<Sprite> getBackgroundSprites() {
		return this.backgroundSprites;
	}
	
	public ArrayList<Sprite> getHostiles() {
		return this.hostiles;
	}
	
	public ArrayList<Sprite> getSprites() {
		return this.sprites;
	}

	public void setBgX(int i) {
		this.bgX = i;
	}
	
	public void setTextOfInstruction(String instruction) {
		this.textInstruction.setText(instruction);
	}
	
	public void setTextOfWordSaid(String wordSaid) {
		this.textOfWordSaid.setText(wordSaid);
	}
	
	public void setRecordingIndicatorRed() {
		this.recordIndicator = this.recordRed;
	}
	
	public void setRecordingIndicatorGrey() {
		this.recordIndicator = this.recordGrey;
	}
	
}

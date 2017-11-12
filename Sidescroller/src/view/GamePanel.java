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

import java.util.*;

public class GamePanel extends JPanel {
	
	final private int BGMIN_X;
	final private int BGMAX_X;
	
	//creating images for single objects
	private Image backgroundImage;
	//Player variable and player2 variable
	private Player player;
	private Player2 player2;
	//probably need a list to keep track of all the enemies but later
	//background offsets for when the main player moves left and right
	private int bgX;
	private int bgY;
	private Controller controller;
	
	
	
	//Arrays to keep track of various groups
	private ArrayList<Enemy> enemies;
	//private ArrayList<Backgrounds> backgrounds;
	private ArrayList<Obstacle> obstacles;
	
	//the array of all hostile sprites
	private ArrayList<Sprite> hostiles;
	//the one array to keep track of all sprites;
	private ArrayList<Sprite> sprites;
	
	public GamePanel() {
		this.BGMIN_X = 1000;
		this.BGMAX_X = 10000;
		
		this.backgroundImage = new ImageIcon("img/background/bg.png").getImage();
		
		//Create player and player2
		this.player = new Player(new Coordinate(600,530));
		//this.player2 = new Player2(new Coordinate(650, 530));
		
		//Put the enemies in relevant array
		this.enemies = new ArrayList<Enemy>();
		
		//Put the obstacles in relevant array
		this.obstacles = new ArrayList<Obstacle>();
		this.obstacles.add(new Obstacle(new Coordinate(1200, 645), "img/sprites/flower.png", "flower"));
		
		//Add all hostile sprites
		this.hostiles = new ArrayList<Sprite>();
		this.hostiles.addAll(this.enemies);
		this.hostiles.addAll(this.obstacles);
		
		//Add all sprites to sprites list
		this.sprites = new ArrayList<Sprite>();
		this.sprites.add(this.player);
		this.sprites.addAll(this.enemies);
		this.sprites.addAll(this.obstacles);

		//set background offsets
		this.bgX = 695;
		this.bgY = 535;
		
		//This is the part where the controller adds listeners
		this.controller = Controller.getSingleton();
		this.controller.setGamePanel(this);
		this.controller.setTimer(30);
		this.controller.addKeyListenerToGamePanel();
		
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		requestFocus(); // get focus after changing card
		setFocusable(true);

		this.drawBackground(g2d);
		this.drawSprites(g2d);
	}
	 
	public void drawBackground(Graphics2D g2d) {
		g2d.drawImage(this.backgroundImage, 700 - this.bgX, 0, null);

	}
	
	public void drawSprites(Graphics2D g2d) {
		for (Sprite s : this.sprites) {
			g2d.drawImage(s.getCurrentSprite(), s.getCharCoord().getX(), s.getCharCoord().getY(), null);
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
	
	public ArrayList<Sprite> getHostiles() {
		return this.hostiles;
	}
	
	public ArrayList<Sprite> getSprites() {
		return this.sprites;
	}

	public void setBgX(int i) {
		this.bgX = i;
	}
	
}

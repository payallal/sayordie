package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;

import controller.Controller;
import controller.MyKeyAdapter;
import model.Character;
import model.Coordinate;
import model.Enemy;
import model.Player;
import model.Player2;

import java.awt.event.*;
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
	private Enemy enemy;
	//background offsets for when the main player moves left and right
	private int bgX;
	private int bgY;
	private Controller controller;
	
	//Arrays to keep track of various groups
	private ArrayList<Player> players;
	private ArrayList<Enemy> enemies;
	//private ArrayList<Backgrounds> backgrounds;
	
	public GamePanel() {
		this.BGMIN_X = 1000;
		this.BGMAX_X = 10000;
		
		this.backgroundImage = new ImageIcon("img/background/bkg5.png").getImage();
		
		//Create player and player2
		this.player = new Player(new Coordinate(600,615));
		this.player2 = new Player2(new Coordinate(650, 615));
		this.players = new ArrayList<Player>();
		this.players.add(this.player);
		this.players.add(this.player2);
		
		//Create enemies
		this.enemy = new Enemy(new Coordinate(900,615));	
		//Put the enemies in relevant array
		this.enemies = new ArrayList<Enemy>();
		this.enemies.add(this.enemy);

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
		this.drawEnemies(g2d);
		this.drawPlayers(g2d);
	}
	 
	public void drawBackground(Graphics2D g2d) {
		g2d.drawImage(this.backgroundImage, 700 - this.bgX, 0, null);

	}
	
	public void drawEnemies(Graphics2D g2d) {
		for (Enemy e : this.enemies) {
			g2d.drawImage(e.getStillLeftSprite(), e.getCharCoord().getX(), e.getCharCoord().getY(), null);
		}
	}
	
	public void drawPlayers(Graphics2D g2d) {
		for (Player p : this.players) {
			g2d.drawImage(p.getCurrentSprite(), p.getCharCoord().getX(), p.getCharCoord().getY(), null); // Drawing the character image
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

	public void setBgX(int i) {
		this.bgX = i;
	}
	
}

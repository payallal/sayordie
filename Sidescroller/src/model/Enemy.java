package model;
import javax.swing.ImageIcon;

/**
 * Represents the features of enemy characters. Extends Player class and automates movements.
 * @see model.Player
 * @author Roger
 *
 */
public class Enemy extends Player {
	/**
	 * Stores the value velocity at which the character will move.
	 */
	private int velocity;
	/**
	 * Stores the value of how much the character will accelerate.
	 */
	private double acceleration;
	
	/**
	 * Constructor for enemy class. Initializes velocity and acceleration.
	 * Sets the enemy sprite images and caption.
	 * @param p X and Y-coordinates at which the enemy character will be placed initially.
	 */
	public Enemy (Coordinate p) {
		super(p);
		this.velocity = 3;
		this.acceleration = 2;
		
		//Protected fields from parent Character class
		this.stillRightSprite = new ImageIcon("img/sprites/enemy/enemyStillRight.png").getImage(); 
		this.stillLeftSprite = new ImageIcon("img/sprites/enemy/enemyStillLeft.png").getImage(); 
		this.walkLeftSprite = new ImageIcon("img/sprites/enemy/enemyWalkLeft.png").getImage(); 
		this.walkRightSprite = new ImageIcon("img/sprites/enemy/enemyWalkRight.png").getImage();
		this.walkRightSprite2 = new ImageIcon("img/sprites/enemy/enemyWalkRight2.png").getImage();
		this.walkRightSprite3 = new ImageIcon("img/sprites/enemy/enemyWalkRight3.png").getImage();
		this.walkRightSprite4 = new ImageIcon("img/sprites/enemy/enemyWalkRight4.png").getImage();
		this.walkLeftSprite2 = new ImageIcon("img/sprites/enemy/enemyWalkLeft2.png").getImage();
		this.walkLeftSprite3 = new ImageIcon("img/sprites/enemy/enemyWalkLeft3.png").getImage();
		this.walkLeftSprite4 = new ImageIcon("img/sprites/enemy/enemyWalkLeft4.png").getImage();
		this.jumpRightSprite = new ImageIcon("img/sprites/enemy/enemyJumpRight.png").getImage(); 
		this.jumpLeftSprite = new ImageIcon("img/sprites/enemy/enemyJumpLeft.png").getImage();
		this.currentSprite = this.stillRightSprite;
	}
	/**
	 * Automates the movement of the enemy to the right by changing the X-coordinates based on acceleration and velocity variables.
	 * Animates the sprite during movement by toggling the sprite image.
	 */
	@Override
	public void right() {

		if (this.moveableRight == true) {
			//instead of moving the background, you move the character
			int newX = this.getCharCoord().getX()+this.velocity;
			int y = this.getCharCoord().getY();
			this.setCharCoord(newX, y);
			if (this.run % 3 == 0) {
				this.setCurrentSprite(this.walkRightSprite);
			}
			if (this.run % 5 == 0) {
				this.setCurrentSprite(this.walkRightSprite2);
			}
			if (this.run % 7 == 0) {
				this.setCurrentSprite(this.walkRightSprite2);
			}
			
			//Character will keep moving right, every 50th time run is being run, we'll add some acceleration
			if (this.run % 46 == 0) {
				this.velocity += this.acceleration;
			}
			this.run++;
		}
	}
	/**
	 * Setter method for the velocity variable.
	 * @param v new velocity value.
	 */
	public void setVelocity(int v) {
		this.velocity = v;
	}
	
	public int getVelocity() {
		return this.velocity;
	}
	
	public void setAcceleration(double d) {
		this.acceleration = d;
	}
	
	public double getAcceleration() {
		return this.acceleration;
	}
		
}

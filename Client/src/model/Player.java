package model;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Represents game sprite to be controlled by the user.
 * @author Roger
 *
 */
public class Player extends Sprite{
	
	/**
	 * Stores representations of the direction of the player where 0 = still, 1 = up, 2 = right, 3 = left and 4 = down.	
	 */
	private int direction;
	
	//These are protected because they will be used in the left right
	//method override by the player2 class (player 2 class inherits from player class)
	/**
	 * Stores boolean value true if the sprite can move to the right and false otherwise.
	 */
	protected boolean moveableRight;
	/**
	 * Stores boolean value true if the sprite can move to the left and false otherwise.
	 */
	protected boolean moveableLeft;
	/**
	 * Stores the change in distance in the x-axis for each time the sprite moves.
	 */
	protected int run;
	/**
	 * Stores boolean value true if the sprite can move to the downwards and false otherwise.
	 */
	private boolean moveableDown;
	/**
	 * Stores boolean value true if the sprite is in the process of jumping to the right and false otherwise.
	 */
	private boolean jumpRight;
	/**
	 * Stores boolean value true if the sprite is in the process of jumping and false otherwise.
	 */
	private boolean jump;
	/**
	 * Stores the velocity at which the sprite moves.
	 */
	private int velocity;
	/**
	 * Stores the acceleration of the sprite.
	 */
	private double acceleration;
	/**
	 * Stores the Y-coordinate where the ground is represented on the game screen.
	 */
	private int floor;
	
	/**
	 * Stores the image of the sprite standing still facing the right.
	 */
	protected Image stillRightSprite;
	/**
	 * Stores the image of the sprite standing still facing the left.
	 */
	protected Image stillLeftSprite;
	/**
	 * Stores the image of the sprite walking towards the left.
	 */
	protected Image walkLeftSprite;
	/**
	 * Stores the image of the sprite walking towards the right.
	 */
	protected Image walkRightSprite;
	/**
	 * Stores the image of the sprite jumping towards the right.
	 */
	protected Image jumpRightSprite;
	/**
	 * Stores the image of the sprite jumping towards the left.
	 */
	protected Image jumpLeftSprite;

	/**
	 * Stores the image of the sprite walking towards the right with different change in foot position for animation purposes.
	 */
	protected Image walkRightSprite2;
	/**
	 * Stores the image of the sprite walking towards the right with different change in foot position for animation purposes.
	 */
	protected Image walkRightSprite3;
	/**
	 * Stores the image of the sprite walking towards the right with different change in foot position for animation purposes.
	 */
	protected Image walkRightSprite4;
	
	/**
	 * Stores the image of the sprite walking towards the left with different change in foot position for animation purposes.
	 */
	protected Image walkLeftSprite2;
	/**
	 * Stores the image of the sprite walking towards the left with different change in foot position for animation purposes.
	 */
	protected Image walkLeftSprite3;
	/**
	 * Stores the image of the sprite walking towards the left with different change in foot position for animation purposes.
	 */
	protected Image walkLeftSprite4;
	
	/**
	 * Stores the image of the sprite when dead.
	 */
	private Image deadSprite;
	
	/**
	 * Constructor for the player class. Initializes fields and sets images to their relevant paths.
	 * @param p X and Y-coordinates at which the sprite will be placed initially.
	 */
	public Player(Coordinate p) {
		super(p);
		
		//Protected fields from parent Character class
		this.stillRightSprite = new ImageIcon("img/sprites/player/playerStillRight.png").getImage(); 
		this.stillLeftSprite = new ImageIcon("img/sprites/player/playerStillLeft.png").getImage(); 
		this.walkLeftSprite = new ImageIcon("img/sprites/player/playerWalkLeft.png").getImage(); 
		this.walkRightSprite = new ImageIcon("img/sprites/player/playerWalkRight.png").getImage();
		this.walkRightSprite2 = new ImageIcon("img/sprites/player/playerWalkRight2.png").getImage();
		this.walkRightSprite3 = new ImageIcon("img/sprites/player/playerWalkRight3.png").getImage();
		this.walkRightSprite4 = new ImageIcon("img/sprites/player/playerWalkRight4.png").getImage();
		this.walkLeftSprite2 = new ImageIcon("img/sprites/player/playerWalkLeft2.png").getImage();
		this.walkLeftSprite3 = new ImageIcon("img/sprites/player/playerWalkLeft3.png").getImage();
		this.walkLeftSprite4 = new ImageIcon("img/sprites/player/playerWalkLeft4.png").getImage();
		this.jumpRightSprite = new ImageIcon("img/sprites/player/playerJumpRight.png").getImage(); 
		this.jumpLeftSprite = new ImageIcon("img/sprites/player/playerJumpLeft.png").getImage();
		this.currentSprite = this.stillLeftSprite;
		this.deadSprite = new ImageIcon("img/sprites/player/playerDead.png").getImage();
		
		this.direction = 0;
		this.moveableLeft = true;
		this.moveableRight = true;
		this.moveableDown = false;
		this.jumpRight = true;
		this.jump = false;
		this.run = 0;
		
		//for falling
		this.velocity = 15;
		this.acceleration = 1;
		this.floor = p.getY();

	}
	
	/**
	 * Getter method for the image of the sprite when standing still and facing the right.
	 * @return the image of the sprite when standing still and facing the right.
	 */
	public Image getStillRightSprite() {
		return this.stillRightSprite;
	}
	
	/**
	 * Getter method for the image of the sprite when standing still and facing the left.
	 * @return the image of the sprite when standing still and facing the left.
	 */
	public Image getStillLeftSprite() {
		return this.stillLeftSprite;
	}
	/**
	 * Getter method for the image of the sprite when standing still and facing the right.
	 * @return the image of the sprite when standing still and facing the right.
	 */
	public Image getWalkLeftSprite() {
		return this.walkLeftSprite;
	}
	/**
	 * Getter method for the image of the sprite when walking towards the right.
	 * @return the image of the sprite when walking towards the right.
	 */
	public Image getWalkRightSprite() {
		return this.walkRightSprite;
	}
	/**
	 * Getter method for the image of the sprite when jumping towards the right.
	 * @return the image of the sprite when jumping towards the right.
	 */
	public Image getJumpRightSprite() {
		return this.jumpRightSprite;
	}
	/**
	 * Getter method for the image of the sprite when jumping towards the left.
	 * @return the image of the sprite when jumping towards the left.
	 */
	public Image getJumpLeftSprite() {
		return this.jumpLeftSprite;
	}
	
	/**
	 * Setter method to change the boolean value of the moveable right field.
	 * @param b new boolean value.
	 */
	public void setMoveableRight(boolean b) {
		this.moveableRight = b;
	}
	/**
	 * Setter method to change the boolean value of the moveable left field.
	 * @param b new boolean value.
	 */
	public void setMoveableLeft(boolean b) {
		this.moveableLeft = b;
	}
	/**
	 * Getter method to retrieve the boolean value of the moveable right field.
	 * @return boolean value, true if moveable right and false otherwise.
	 */
	public boolean getMoveableRight() {
		return this.moveableRight;
	}
	/**
	 * Getter method to retrieve the boolean value of the moveable left field.
	 * @return boolean value, true if moveable left and false otherwise.
	 */
	public boolean getMoveableLeft() {
		return this.moveableLeft;
	}
	/**
	 * Setter method to change the character's direction. 
	 * @param direction new direction. 0 = still, 1 = up, 2 = right, 3 = left and 4 = down.
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	 /**
	  * Getter method that returns the character's current direction
	  * @return current direction. 0 = still, 1 = up, 2 = right, 3 = left and 4 = down.
	  */
	public int getDirection() {
		return this.direction;
	}
	/**
	 * Getter method for the current boolean value of whether or not the character is in the process of jumping.
	 * @return boolean value true of character is jumping and false otherwise.
	 */
	public boolean isJump() {
		// TODO Auto-generated method stub
		return this.jump;
	}	
	/**
	 * Setter method to change the boolean value of the field that stores whether the character is jumping to the right.
	 * @param b new boolean value.
	 */
	public void setJumpRight(boolean b) {
		this.jumpRight = b;
	}
	/**
	 * Animates the sprite moving to the left and changes the sprite's x-coordinates if the user chooses to move left.
	 */
	public void left() {
		if (this.moveableLeft == true) {
			this.controller.decrementBgX(); // decrease xcoord while moving left
			if (this.run % 3 == 0) {
				this.currentSprite = this.walkLeftSprite;
			}
			if (this.run % 5 == 0) {
				this.currentSprite = this.walkLeftSprite2;
			}
			if (this.run % 7 == 0) {
				this.currentSprite = this.walkLeftSprite3;
			}

			this.run++;
		}
	}
	/**
	 * Animates the sprite moving to the right and changes the sprite's x-coordinates if the user chooses to move left.
	 */
	public void right() {
		if (this.moveableRight == true) {
			this.controller.incrementBgX(); // increasing xcoord while moving right
			if (this.run % 3 == 0) {
				this.currentSprite = this.walkRightSprite;
			}
			if (this.run % 5 == 0) {
				this.currentSprite = this.walkRightSprite2;
			}
			if (this.run % 7 == 0) {
				this.currentSprite = this.walkRightSprite3;
			}

			this.run++;
		}
	}
	/**
	 * Sets character to jump if the sprite is nt already in the process of jumping.
	 */
	public void checkJump() {
		// TODO Auto-generated method stub
		if (this.jump == false && this.getCharCoord().getY() == this.floor) {

			this.jump = true;
			this.moveableDown = true;
			
			if (this.direction == 2) {
				this.jumpRight = true;
			}
			else if (this.direction == 3) {
				this.jumpRight = false;
			}
		}
	}
	
	//Jump is really an air time check
	/**
	 * Animates the jumping motion of the character and moves the sprite. 
	 */
	public void jump() {
		
		if (this.moveableDown == true) {

			int playerY = this.getCharCoord().getY();			
			//jumping upwards
			if (this.jump == true &&  playerY >= this.floor-95) {
				if (this.jumpRight == true) {
					this.currentSprite = this.jumpRightSprite;
				}
				else {
					this.currentSprite = this.jumpLeftSprite;
				}
				this.velocity -= this.acceleration;
				playerY -= this.velocity;
				this.setCharCoord(this.getCharCoord().getX(), playerY);
				if (playerY < this.floor-95) {
					this.jump = false;
				}
			}
			
			//no longer jumping but still in the air
			if (this.jump == false && playerY < this.floor) {
				if (this.jumpRight == true) {
					this.currentSprite = this.jumpRightSprite;
				}
				else {
					this.currentSprite = this.jumpLeftSprite;
				}
				this.velocity += this.acceleration;
				playerY += this.velocity;
				this.setCharCoord(this.getCharCoord().getX(), playerY);
				
				if (playerY >= this.floor) {
					this.setCharCoord(this.getCharCoord().getX(), this.floor);
					this.moveableDown = false;
					if (this.jumpRight == true) {
						this.currentSprite = this.stillRightSprite;
					}
					else {
						this.currentSprite = this.stillLeftSprite;
					}
					this.velocity = 15;
				}
			}
		}
	}
	/**
	 * Getter method that returns the current image pf the sprite that is being displayed.
	 * @return current sprite image.
	 */
	public Image getCurrentSprite() {
		return this.currentSprite;
	}
	/**
	 * Setter method that changes the current image of the sprite being displayed.
	 * @param img the image to be displayed.
	 */
	public void setCurrentSprite(Image img) {
		this.currentSprite = img;
	}
	/**
	 * Changes the current image to the image of the sprite when dead. 
	 * Increase x coordinate so that character is lying on the ground.
	 */
	public void die() {
		this.currentSprite = this.deadSprite;
		Coordinate currentCoord = this.getCharCoord();
		this.setCharCoord(currentCoord.getX()-80, currentCoord.getY()+100);
	}
	
	
}

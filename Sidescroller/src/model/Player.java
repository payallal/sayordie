package model;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Player extends Sprite{
	
	// 0=still 1=up , 2=right , 3=left , 4=down	
	private int direction;
	
	//These are protected because they will be used in the left right
	//method override by the player2 class (player 2 class inherits from player class)
	protected boolean moveableRight;
	protected boolean moveableLeft;
	protected int run;

	private boolean moveableDown;
	private boolean jumpRight;
	private boolean jump;
	private int velocity;
	private double acceleration;
	private int floor;
	
	protected Image stillRightSprite;
	protected Image stillLeftSprite;
	protected Image walkLeftSprite;
	protected Image walkRightSprite;
	protected Image jumpRightSprite;
	protected Image jumpLeftSprite;

	protected Image walkRightSprite2;
	protected Image walkRightSprite3;
	protected Image walkRightSprite4;
	
	protected Image walkLeftSprite2;
	protected Image walkLeftSprite3;
	protected Image walkLeftSprite4;
	
	public Player(Coordinate p) {
		super(p);
		
		//Protected fields from parent Character class
		this.stillRightSprite = new ImageIcon("img/sprites/playerStillRight.png").getImage(); 
		this.stillLeftSprite = new ImageIcon("img/sprites/playerStillLeft.png").getImage(); 
		this.walkLeftSprite = new ImageIcon("img/sprites/playerWalkLeft.png").getImage(); 
		this.walkRightSprite = new ImageIcon("img/sprites/playerWalkRight.png").getImage();
		this.walkRightSprite2 = new ImageIcon("img/sprites/playerWalkRight2.png").getImage();
		this.walkRightSprite3 = new ImageIcon("img/sprites/playerWalkRight3.png").getImage();
		this.walkRightSprite4 = new ImageIcon("img/sprites/playerWalkRight4.png").getImage();
		this.walkLeftSprite2 = new ImageIcon("img/sprites/playerWalkLeft2.png").getImage();
		this.walkLeftSprite3 = new ImageIcon("img/sprites/playerWalkLeft3.png").getImage();
		this.walkLeftSprite4 = new ImageIcon("img/sprites/playerWalkLeft4.png").getImage();
		this.jumpRightSprite = new ImageIcon("img/sprites/playerJumpRight.png").getImage(); 
		this.jumpLeftSprite = new ImageIcon("img/sprites/playerJumpLeft.png").getImage();
		this.currentSprite = this.stillLeftSprite;
		
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
	
	public Image getStillRightSprite() {
		return this.stillRightSprite;
	}
	
	public Image getStillLeftSprite() {
		return this.stillLeftSprite;
	}
	
	public Image getWalkLeftSprite() {
		return this.walkLeftSprite;
	}
	
	public Image getWalkRightSprite() {
		return this.walkRightSprite;
	}
	
	public Image getJumpRightSprite() {
		return this.jumpRightSprite;
	}
	
	public Image getJumpLeftSprite() {
		return this.jumpLeftSprite;
	}
	
	public void setMoveableRight(boolean b) {
		this.moveableRight = b;
	}
	
	public void setMoveableLeft(boolean b) {
		this.moveableLeft = b;
	}
	
	public boolean getMoveableRight() {
		return this.moveableRight;
	}
	
	public boolean getMoveableLeft() {
		return this.moveableLeft;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return this.direction;
	}

	public boolean isJump() {
		// TODO Auto-generated method stub
		return this.jump;
	}	
	
	public void setJumpRight(boolean b) {
		this.jumpRight = b;
	}
	
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

	public void checkJump() {
		// TODO Auto-generated method stub
		if (this.jump == false && this.getCharCoord().getY() == this.floor) {
			System.out.println("j2");

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

	public Image getCurrentSprite() {
		return this.currentSprite;
	}
	
	public void setCurrentSprite(Image img) {
		this.currentSprite = img;
	}
	
	
}

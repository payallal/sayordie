package model;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Player extends Character{
	
	// 0=still 1=up , 2=right , 3=left , 4=down	
	private int direction;
	
	//These are protected because they will be used in the left right
	//method override by the player2 class (player 2 class inherits from player class)
	protected boolean moveableRight;
	protected boolean moveableLeft;
	protected int run;

	private Image currentSprite;
	private boolean moveableDown;
	private boolean jumpRight;
	private boolean jump;
	private int velocity;
	private double acceleration;
	
	public Player(Coordinate p) {
		super(p);
		
		//Protected fields from parent Character class
		this.stillRightSprite = new ImageIcon("img/sprites/rz_still_right.png").getImage(); 
		this.stillLeftSprite = new ImageIcon("img/sprites/rz_still_left.png").getImage(); 
		this.walkLeftSprite = new ImageIcon("img/sprites/rz_walk_left2.png").getImage(); 
		this.walkRightSprite = new ImageIcon("img/sprites/rz_walk_right2.png").getImage();
		this.jumpRightSprite = new ImageIcon("img/sprites/rz_right_jump.png").getImage(); 
		this.jumpLeftSprite = new ImageIcon("img/sprites/rz_left_jump.png").getImage();
		this.currentSprite = this.stillRightSprite;
		
		this.direction = 0;
		this.moveableLeft = true;
		this.moveableRight = true;
		this.moveableDown = false;
		this.jumpRight = true;
		this.jump = false;
		this.run = 0;
		this.velocity = 15;
		this.acceleration = 1;

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
		if (this.moveableLeft == true & this.controller.getBgX() > this.controller.getBGMIN()) {
			this.controller.decrementBgX(); // decrease xcoord while moving left
			if (this.run % 3 == 0 | this.run % 5 == 0) {
				this.currentSprite = this.stillLeftSprite;
			}
			else {
				this.currentSprite = this.walkLeftSprite;
			}
			this.run++;
		}
	}

	public void right() {
		if (this.moveableRight == true & this.controller.getBgX() < this.controller.getBGMAX() - 800) {
			this.controller.incrementBgX(); // increasing xcoord while moving right

			if (this.run % 3 == 0 | this.run % 5 == 0) {
				this.currentSprite = this.stillRightSprite;
			}
			else {
				this.currentSprite = this.walkRightSprite;
			}
			this.run++;
		}
	}

	public void checkJump() {
		// TODO Auto-generated method stub
		if (this.jump == false && this.getCharCoord().getY() == 615) {
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
			if (this.jump == true &&  playerY >= 520) {
				if (this.jumpRight == true) {
					this.currentSprite = this.jumpRightSprite;
				}
				else {
					this.currentSprite = this.jumpLeftSprite;
				}
				this.velocity -= this.acceleration;
				playerY -= this.velocity;
				this.setCharCoord(this.getCharCoord().getX(), playerY);
				if (playerY < 520) {
					this.jump = false;
				}
			}
			
			//no longer jumping but still in the air
			if (this.jump == false && playerY < 615) {
				if (this.jumpRight == true) {
					this.currentSprite = this.jumpRightSprite;
				}
				else {
					this.currentSprite = this.jumpLeftSprite;
				}
				this.velocity += this.acceleration;
				playerY += this.velocity;
				this.setCharCoord(this.getCharCoord().getX(), playerY);
				
				if (playerY >= 615) {
					this.setCharCoord(this.getCharCoord().getX(), 615);
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

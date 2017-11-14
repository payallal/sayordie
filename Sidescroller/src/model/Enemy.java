package model;
import javax.swing.ImageIcon;


public class Enemy extends Player {
	
	private Word caption;
	private int velocity;
	private double acceleration;
	
	public Enemy (Coordinate p) {
		super(p);
		this.velocity = 1;
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
		this.caption = new Word("'u suck fool'", this.getCaptionCoordinate(p,-20));
	}
	
	@Override
	public void right() {
		//System.out.println(this.moveableRight);
		//System.out.println(this.controller.getBgX() < this.controller.getBGMAX() - 800);

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
			if (this.run % 45 == 0) {
				this.velocity += this.acceleration;
			}
			this.run++;
		}
	}
	
	public Word getCaption() {
		return this.caption;
	}

	public void setVelocity(int v) {
		this.velocity = v;
	}
		
}

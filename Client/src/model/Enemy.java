package model;
import javax.swing.ImageIcon;


public class Enemy extends Player2 {
	
	public Enemy (Coordinate p) {
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
	}
		
}

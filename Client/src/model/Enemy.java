package model;
import javax.swing.ImageIcon;


public class Enemy extends Character {
	
	public Enemy (Coordinate p) {
		super(p);
		//Protected fields from parent Character class
		this.stillRightSprite = new ImageIcon("img/sprites/rz_still_right.png").getImage(); 
		this.stillLeftSprite = new ImageIcon("img/sprites/rz_still_left.png").getImage(); 
		this.walkLeftSprite = new ImageIcon("img/sprites/rz_walk_left2.png").getImage(); 
		this.walkRightSprite = new ImageIcon("img/sprites/rz_walk_right2.png").getImage();
		this.jumpRightSprite = new ImageIcon("img/sprites/rz_right_jump.png").getImage(); 
		this.jumpLeftSprite = new ImageIcon("img/sprites/rz_left_jump.png").getImage();
	}
		
}

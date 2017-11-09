package model;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Obstacle extends Sprite {
	
	private String wordDescriptor;
	public Obstacle (Coordinate p, String imgPath, String wordDescriptor) {
		super(p);
		this.currentSprite = new ImageIcon(imgPath).getImage(); 
		this.wordDescriptor = wordDescriptor;
	}
	
	//the get bounds needs to be a little less sensitive
	@Override
	public Rectangle getBounds() {
		int xOffset = this.getWidth()/2;
		int yOffset = this.getHeight()/2;
		return (new Rectangle(this.getCharCoord().getX()+xOffset, this.getCharCoord().getY()+yOffset, this.getWidth()-xOffset, this.getHeight()-yOffset));
	}
	
}

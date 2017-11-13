package model;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

//if obstacles move you have to make sure that the caption moves with character
public class Obstacle extends Sprite {
	
	private Word caption;
	
	public Obstacle (Coordinate p, String imgPath, String wordDescriptor) {
		super(p);
		this.currentSprite = new ImageIcon(imgPath).getImage(); 
		Coordinate captionCoord = new Coordinate(p.getX(),p.getY()-10);
		this.caption = new Word(wordDescriptor, captionCoord, "red");
	}
	
	//the get bounds needs to be a little less sensitive
	@Override
	public Rectangle getBounds() {
		int xOffset = this.getWidth()/2;
		int yOffset = this.getHeight()/2;
		return (new Rectangle(this.getCharCoord().getX()+xOffset, this.getCharCoord().getY()+yOffset, this.getWidth()-xOffset, this.getHeight()-yOffset));
	}
	
	public Word getCaption() {
		return this.caption;
	}
	
}

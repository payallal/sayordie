package model;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

//if obstacles move you have to make sure that the caption moves with character
/**
 * Represents obstacles in the game. Extends sprite class for basic features.
 * @author Roger
 *
 */
public class Obstacle extends Sprite {
	
	/**
	 * Text to be shown above obstacle image.
	 * @see model.Word
	 */
	private Word caption;
	
	/**
	 * Constructor for obstacles. Sets coordinates, sprite image and caption.
	 * @param p X and Y-coordinates at which the obstacle will be placed.
	 * @param imgPath the location of the image file of the obstacle.
	 * @param wordDescriptor word to be displayed as obstacle caption.
	 */
	public Obstacle (Coordinate p, String imgPath, String wordDescriptor) {
		super(p);
		this.currentSprite = new ImageIcon(imgPath).getImage(); 
		this.caption = new Word(wordDescriptor, this.getCaptionCoordinate(p,10));
	}
	
	//the get bounds needs to be a little less sensitive
	/**
	 * Created a rectangle around obstacle image for collision detection.
	 * @return Rectangle that surrounds the obstacle image.
	 */
	@Override
	public Rectangle getBounds() {
		int xOffset = this.getWidth()/2;
		int yOffset = this.getHeight()/2;
		return (new Rectangle(this.getCharCoord().getX()+xOffset, this.getCharCoord().getY()+yOffset, this.getWidth()-xOffset, this.getHeight()-yOffset));
	}
	/**
	 * Getter method for text to be shown above obstacle.
	 * @return caption shown above obstacle.
	 * @see model.Word
	 */
	public Word getCaption() {
		return this.caption;
	}
	
}

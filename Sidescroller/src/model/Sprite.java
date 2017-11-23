package model;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import controller.Controller;

/**
 * Represents basic features of all sprites such as coordinates and image.
 * @author Roger
 *
 */
public abstract class Sprite implements ImageObserver {

	/**
	 * Stores instance of controller which moves sprite based on user input.
	 * @see controller.Controller
	 */
	protected Controller controller;
	/**
	 * Stores current image of sprite to allow for animation.
	 */
	protected Image currentSprite;
	/**
	 * Stores current X and Y-Coordinates of sprite.
	 * @see model.Coordinate
	 */
	private Coordinate characterPosition;

	/**
	 * Constructor for sprite.
	 * Initializes the coordinate position and the controller.
	 * @param p X and Y-coordinates at which the sprite will be placed initially.
	 */
	public Sprite(Coordinate p) {
		this.characterPosition = p;
		this.controller = Controller.getSingleton();
	}
	
	/**
	 * Getter method for the coordinates of the sprite.
	 * @return current coordinates of the sprite
	 * @see model.Coordinate
	 */
	public Coordinate getCharCoord() {
		// TODO Auto-generated method stub
		return this.characterPosition;
	}
	
	/**
	 * Setter method for the coordinates of the sprite.
	 * @param x new X-coordinate value of sprite.
	 * @param y new Y-coordinate value of sprite.
	 */
	public void setCharCoord(int x, int y) {
		this.characterPosition.setX(x);
		this.characterPosition.setY(y);
	}
	/**
	 * Getter method for current image of the sprite.
	 * @return current image of the sprite.
	 */
	public Image getCurrentSprite() {
		return this.currentSprite;
	}
	
	/**
	 * Getter method for the width of the the current sprite image.
	 * @return width of sprite image.
	 */
	public int getWidth() {
		return this.currentSprite.getWidth(this);
	}
	
	/**
	 * Getter method for the height of the the current sprite image.
	 * @return height of sprite image.
	 */
	public int getHeight() {
		return this.currentSprite.getHeight(this);
	}
	/**
	 * Created a rectangle around sprite image for collision detection.
	 * @return Rectangle that surrounds the sprite image.
	 */
	public Rectangle getBounds() {
		return (new Rectangle(this.getCharCoord().getX(), this.getCharCoord().getY(), this.getWidth(), this.getHeight()));
	}

	/**
	 * 
	 */
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
	
	/**
	 * Getter method for the coordinates of the text above sprite.
	 * @param p X and Y-coordinates of the sprite.
	 * @param offset value to adjust positioning pf caption in the Y-axis.
	 * @return Coordinate at which the caption should be placed to be above the sprite given the offset.
	 */
	public Coordinate getCaptionCoordinate (Coordinate p, int offset) {
		 return new Coordinate(p.getX(),p.getY()-offset);
	}

}

package model;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import controller.Controller;

public abstract class Sprite implements ImageObserver {

	protected Controller controller;
	protected Image currentSprite;
	private Coordinate characterPosition;

	
	public Sprite(Coordinate p) {
		this.characterPosition = p;
		this.controller = Controller.getSingleton();
	}
	
	
	public Coordinate getCharCoord() {
		// TODO Auto-generated method stub
		return this.characterPosition;
	}
	
	public void setCharCoord(int x, int y) {
		this.characterPosition.setX(x);
		this.characterPosition.setY(y);
	}
	
	public Image getCurrentSprite() {
		return this.currentSprite;
	}
	
	
	public int getWidth() {
		return this.currentSprite.getWidth(this);
	}
	
	public int getHeight() {
		return this.currentSprite.getHeight(this);
	}
	
	public Rectangle getBounds() {
		return (new Rectangle(this.getCharCoord().getX(), this.getCharCoord().getY(), this.getWidth(), this.getHeight()));
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

}

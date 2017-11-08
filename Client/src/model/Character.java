package model;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import controller.Controller;

public abstract class Character implements ImageObserver {
	protected Image stillRightSprite;
	protected Image stillLeftSprite;
	protected Image walkLeftSprite;
	protected Image walkRightSprite;
	protected Image jumpRightSprite;
	protected Image jumpLeftSprite;
	protected Controller controller;
	private Coordinate characterPosition;

	
	public Character(Coordinate p) {
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
	
	public int getWidth() {
		return this.stillRightSprite.getWidth(this);
	}
	
	public int getHeight() {
		return this.stillRightSprite.getHeight(this);
	}
	
	public Rectangle getBounds() {
		return (new Rectangle(this.getCharCoord().getX(), this.getCharCoord().getY(), this.getWidth(), this.getHeight()));
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}

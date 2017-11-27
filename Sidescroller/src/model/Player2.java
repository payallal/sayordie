package model;

/**
 * Represents second player in multiplayer mode.
 * @author david
 *
 */
public class Player2 extends Player {
	/**
	 * Stores boolean value for whether the game is in progress.
	 */
	private boolean gameStarted;
	/**
	 * Constructor for the player2 class. Initializes fields and sets images to their relevant paths.
	 * @param p X and Y-coordinates at which the sprite will be placed initially.
	 */
	public Player2 (Coordinate p) {
		super(p);
		this.gameStarted = false;
		//we override this when we actually want new sprites
		//This should be Obama's sprite- for now we just inherit trump
		/*
		this.stillRightSprite = new ImageIcon("img/sprites/rz_still_right.png").getImage(); 
		this.stillLeftSprite = new ImageIcon("img/sprites/rz_still_left.png").getImage(); 
		this.walkLeftSprite = new ImageIcon("img/sprites/rz_walk_left2.png").getImage(); 
		this.walkRightSprite = new ImageIcon("img/sprites/rz_walk_right2.png").getImage();
		this.jumpRightSprite = new ImageIcon("img/sprites/rz_right_jump.png").getImage(); 
		this.jumpLeftSprite = new ImageIcon("img/sprites/rz_left_jump.png").getImage();
		*/
	}
		
	@Override
	/**
	 * Animates the sprite moving to the left and changes the sprite's x-coordinates if the user chooses to move left.
	 */
	public void left() {
		if (this.moveableLeft == true & this.controller.getBgX() > this.controller.getBGMIN()) {

			//Instead of moving the background, you move the character
			int newX = this.getCharCoord().getX()-8;
			int y = this.getCharCoord().getY();
			this.setCharCoord(newX, y);
			if (this.run % 3 == 0) {
				this.setCurrentSprite(this.walkLeftSprite);
			}
			if (this.run % 5 == 0) {
				this.setCurrentSprite(this.walkLeftSprite2);
			}
			if (this.run % 7 == 0) {
				this.setCurrentSprite(this.walkLeftSprite3);
			}

			this.run++;
		}
	}

	@Override
	/**
	 * Animates the sprite moving to the right and changes the sprite's x-coordinates if the user chooses to move left.
	 */
	public void right() {

		if (this.moveableRight == true & this.controller.getBgX() < this.controller.getBGMAX() - 800) {
			//instead of moving the background, you move the character
			int newX = this.getCharCoord().getX()+8;
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

			this.run++;
		}
	}
	
	//While loop in convertStringToMovement will check this continuously
	/**
	 * Getter function for the boolean value of whether the game is in progress.
	 * @return boolean value of whether the game is in progress.
	 */
	public synchronized boolean getGameStarted() {
		return this.gameStarted;
	}
	
	//Thread will set this to true asynchronously, so it MUST be synchronized with the above method
	/**
	 *  Setter function for the boolean value of whether the game is in progress.
	 * @param b the new boolean value of whether the game is in progress.
	 */
	public synchronized void setGameStarted(boolean b) {
		this.gameStarted = b;
	}
}

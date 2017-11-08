package model;
import javax.swing.ImageIcon;


public class Player2 extends Player {
	
	public Player2 (Coordinate p) {
		super(p);
		//Protected fields from parent Character class
		/*//we override this when we actually want new sprites
		this.stillRightSprite = new ImageIcon("img/sprites/rz_still_right.png").getImage(); 
		this.stillLeftSprite = new ImageIcon("img/sprites/rz_still_left.png").getImage(); 
		this.walkLeftSprite = new ImageIcon("img/sprites/rz_walk_left2.png").getImage(); 
		this.walkRightSprite = new ImageIcon("img/sprites/rz_walk_right2.png").getImage();
		this.jumpRightSprite = new ImageIcon("img/sprites/rz_right_jump.png").getImage(); 
		this.jumpLeftSprite = new ImageIcon("img/sprites/rz_left_jump.png").getImage();
		*/
	}
		
	@Override
	public void left() {
		if (this.moveableLeft == true & this.controller.getBgX() > this.controller.getBGMIN()) {

			//Instead of moving the background, you move the character
			int newX = this.getCharCoord().getX()-8;
			int y = this.getCharCoord().getY();
			this.setCharCoord(newX, y);
			if (this.run % 3 == 0 | this.run % 5 == 0) {
				this.setCurrentSprite(this.stillLeftSprite);
			}
			else {
				this.setCurrentSprite(this.walkLeftSprite);
			}
			this.run++;
		}
	}

	@Override
	public void right() {
		//System.out.println(this.moveableRight);
		//System.out.println(this.controller.getBgX() < this.controller.getBGMAX() - 800);

		if (this.moveableRight == true & this.controller.getBgX() < this.controller.getBGMAX() - 800) {
			//instead of moving the background, you move the character
			System.out.println("right");
			int newX = this.getCharCoord().getX()+8;
			int y = this.getCharCoord().getY();
			this.setCharCoord(newX, y);
			if (this.run % 3 == 0 | this.run % 5 == 0) {
				this.setCurrentSprite(this.stillRightSprite);
			}
			else {
				this.setCurrentSprite(this.walkRightSprite);
			}
			this.run++;
		}
	}
}

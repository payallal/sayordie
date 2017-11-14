package model;

public class Word extends Sprite{
	
	private String text;
	private Coordinate coord;
	
	public Word(String s, Coordinate coord) {
		super(coord);
		this.text = s;
		this.coord = coord;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return this.text;
	}
	
	public void setText(String s) {
		this.text = s;
	}
	
}

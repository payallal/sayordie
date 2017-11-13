package model;

public class Word extends Sprite{
	
	private String text;
	private Coordinate coord;
	private String color; 
	
	public Word(String s, Coordinate coord, String color) {
		super(coord);
		this.text = s;
		this.coord = coord;
		this.color = color;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return this.text;
	}
	
	public void setText(String s) {
		this.text = s;
	}
	
}

package ui;

import acm.graphics.GRect;
import java.awt.Color;
import game.SpaceGame;

public class Button extends GRect
{
	private String label;
	private ButtonLabel text;
	SpaceGame game;
	
	public Button(String s, Color c, double x, double y, double width, double height, SpaceGame game)
	{
		super(x, y, width, height);
		this.setColor(Color.GREEN);
		this.setFillColor(c);
		this.setFilled(true);
		label = s;
		game.add(this);
		text = new ButtonLabel(this);
		game.add(text);
		this.game = game;
	}
	
	boolean isClicked(double mouseX, double mouseY)
	{
		if (mouseX > this.getX() && mouseX < this.getX() + this.getWidth() && mouseY > this.getY() && mouseY < this.getY() + this.getHeight())
		{
			return true;
		}
		return false;
	} // is the button clicked?
	
	public String getLabel()
	{
		return label;
	} // for use with ButtonLabel.java only.
	
	public void removeText()
	{
		game.remove(text);
	} // added remove text method.
}

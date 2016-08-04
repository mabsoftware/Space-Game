package ui;

import acm.graphics.GLabel;
import java.awt.Color;
import game.SpaceGame;

public class Title extends GLabel
{
	public Title(String s, Color c, double x, double y, SpaceGame game)
	{
		super(s, x, y);
		this.setColor(c);
		this.setFont("fantasy-bold-48");
		this.setLocation(this.getX() - this.getWidth() / 2, this.getY() + 40);
		game.add(this);
	}
}

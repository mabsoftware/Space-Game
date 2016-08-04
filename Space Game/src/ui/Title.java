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
		game.add(this);
	}
}

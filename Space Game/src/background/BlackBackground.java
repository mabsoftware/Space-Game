package background;

import java.awt.Color;

import acm.graphics.GRect;

public class BlackBackground extends GRect
{
	public BlackBackground(double w, double h)
	{
		super(0, 0, w, h);
		this.setFillColor(Color.BLACK);
		this.setColor(Color.BLACK);
		this.setFilled(true);
	}
}

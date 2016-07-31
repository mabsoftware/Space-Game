package background;

import acm.graphics.GRect;
import java.awt.Color;

public class Star extends GRect
{
	public Star(double x, double y, double s)
	{
		super(x, y, s, s);
		this.setColor(Color.WHITE);
		this.setFillColor(Color.WHITE);
		this.setFilled(true);
	}
}

package player;

import acm.graphics.GLine;

public class Laser extends GLine
{
	
	private double myX;
	private double myY;
	private double myAngle;
	
	public Laser(double startX, double startY, double angle)
	{
		super(startX, startY, startX + 50, startY + 50);
		myX = startX;
		myY = startY;
		myAngle = angle;
	}
	
	
}


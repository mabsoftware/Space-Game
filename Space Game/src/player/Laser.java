package player;

import acm.graphics.GLine;

public class Laser extends GLine
{
	
	private double myX;
	private double myY;
	private double myAngle;
	private Laser laser;
	
	public Laser(double startX, double startY, double angle)
	{
		super(startX, startY, startX + (150 * Math.sin(angle)), startY + (150 * Math.cos(angle)));
		myX = startX;
		myY = startY;
		myAngle = angle;
	}
	
	public Laser moveLaser()
	{
		laser = new Laser(myX + (150 * Math.sin(myAngle)), myY + (150 * Math.cos(myAngle)), myAngle);
		return laser;
	}
}
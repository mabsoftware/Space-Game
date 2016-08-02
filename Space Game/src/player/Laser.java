package player;

import acm.graphics.GLine;

public class Laser extends GLine
{
	
	private double myX;
	private double myY;
	private double myAngle;
	private Laser laser;
	
	public Laser(Player p)
	{
		super(p.getX() * Math.cos(p.getAngle()) - p.getY() * Math.sin(p.getAngle()), p.getX() * Math.sin(p.getAngle()) + p.getY() * Math.cos(p.getAngle()), p.getX() * Math.cos(p.getAngle()) - p.getY() * Math.sin(p.getAngle()) - p.getX(), p.getX() * Math.sin(p.getAngle()) + p.getY() * Math.cos(p.getAngle()) - p.getY());
	}
}
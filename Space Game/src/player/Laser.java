package player;

import acm.graphics.GLine;

public class Laser extends GLine
{
	
	private double xUniverse;
	private double yUniverse;
	private double myAngle;
	
	public Laser(Player p)
	{
		super(p.getX() * Math.cos(p.getAngle()) - p.getY() * Math.sin(p.getAngle()), p.getX() * Math.sin(p.getAngle()) + p.getY() * Math.cos(p.getAngle()), p.getX() * Math.cos(p.getAngle()) - p.getY() * Math.sin(p.getAngle()) - p.getX(), p.getX() * Math.sin(p.getAngle()) + p.getY() * Math.cos(p.getAngle()) - p.getY());
	}

	public double getXUniverse() {
		return xUniverse;
	}

	public void setXUniverse(double xUniverse) {
		this.xUniverse = xUniverse;
	}

	public double getYUniverse() {
		return yUniverse;
	}

	public void setYUniverse(double yUniverse) {
		this.yUniverse = yUniverse;
	}
}
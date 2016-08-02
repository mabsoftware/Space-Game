package physics;

import acm.graphics.GOval;

public class Planet extends GOval
{
	private double xUniverse;
	private double yUniverse;
	
	public Planet(GravityObject obj)
	{
		super(obj.getXUniverse()-1000, obj.getYUniverse()-1000, obj.getWidth(), obj.getHeight());
	}
	
	public void update(GravityObject obj)
	{
		setXUniverse(obj.getXUniverse());
		setYUniverse(obj.getYUniverse());
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

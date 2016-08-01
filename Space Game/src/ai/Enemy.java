package ai;
//
import acm.graphics.GPolygon;

public class Enemy extends GPolygon
{
	
	private double angle;
	
	public Enemy(double startX, double startY)
	{
		super(startX, startY);
		angle = 0;
	}
	
	public void setAngle(double newAngle)
	{
		double rotateAngle = newAngle - angle;
		this.rotate(rotateAngle);
		angle = newAngle;
		while (angle >= 360)
		{
			angle -= 360;
		}
		while (angle < 0)
		{
			angle += 360;
		}
		
	} // rotates enemy counterclockwise.
	
	public double getAngle()
	{
		return angle;
	}
}

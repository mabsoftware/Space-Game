package physics;

import acm.graphics.GImage;
import java.awt.Image;
import physics.Vector;

public class GravityObject extends GImage
{
	private Vector myVector; // gravity objects move too.
	
	public GravityObject(Image image, double startX, double startY, double xVel, double yVel)
	{
		super(image, startX, startY);
		
		myVector.setXComponent(xVel);
		myVector.setYComponent(yVel); // set GravityObject's vector.
	}
}

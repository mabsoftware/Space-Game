package player;

import acm.graphics.GImage;
import java.awt.Image;
import physics.Vector;

public class Player extends GImage
{
	private Vector myVector;
	
	public Player(Image image, double startX, double startY, double xVel, double yVel)
	{
		super(image, startX, startY); // create player GImage.
		
		myVector.setXComponent(xVel);
		myVector.setYComponent(yVel); // set initial player vector.
	}
	
	public Vector getVector()
	{ return myVector; }
}

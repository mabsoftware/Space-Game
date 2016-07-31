package player;

import java.awt.Image;
import acm.graphics.GImage;
import physics.Vector;

public class Projectile extends GImage
{
	private double myX;
	private double myY;
	private final double missleSpeed = 5;
	private Vector myVector;
	private Image myImage;
	
	public Projectile(Image image, double startX, double startY, Player p)
	{
		super(image, startX, startY); // create a GImage.
		myImage = image;
		myX = startX;
		myY = startY; // create instance variables.
		
		myVector = p.getVector();
		myVector.multiplyByScalar(missleSpeed); // player's vector + missle's speed ( a scalar ).
	}
}

package player;

import java.awt.Image;
import acm.graphics.GImage;
import physics.Vector;

public class Projectile extends GImage
{
	private double myX;
	private double myY;
	private double missleXSpeed;
	private double missleYSpeed;
	private Vector myVector;
	private Image myImage;
	
	public Projectile(Image image, double startX, double startY, Player p)
	{
		super(image, startX, startY); // create a GImage.
		myImage = image;
		myX = startX;
		myY = startY; // create instance variables.
		missleXSpeed = 5;
		missleYSpeed = 0;
	}
}

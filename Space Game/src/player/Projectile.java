package player;

import java.awt.Image;
import acm.graphics.GImage;
import physics.Vector;

public class Projectile extends GImage
{
	private final double missleSpeed = 5;
	private Vector myVector;

	public Projectile(Image image, double startX, double startY, Player p)
	{
		super(image, startX, startY); // create a GImage.

		myVector = p.getVector();
		myVector.multiplyByScalar(missleSpeed); // player's vector + missle's speed ( a scalar ).
	}

	public Vector getVector()
	{
		return myVector;
	}
}

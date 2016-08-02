package player;

import java.awt.Image;
import acm.graphics.GImage;
import physics.Vector;

public class Projectile extends GImage
{
	private final double MISSILESPEED = 5;
	private Vector myVector;

	public Projectile(Player p)
	{
		super("assets/images/missile.png", p.getX() / 2, p.getY()); // create a GImage.

		myVector = new Vector(p.getVector().getXComponent(), p.getVector().getYComponent());
		myVector.multiplyByScalar(MISSILESPEED); // player's vector + missle's speed ( a scalar ).
	}

	public Vector getVector()
	{
		return myVector;
	}
}

package physics;

import java.awt.Image;

public class BlackHole extends GravityObject
{
	private static double multiplier = 100;
	
	public BlackHole(String image, double xUniverse, double yUniverse) // What are xu and yu? xuniverse and yuniverse
	{
		super(image, 0, 0, 0, multiplier, xUniverse, yUniverse); // black holes don't move in the game, and you can't see them.
		
	}
} // extends Gravity Object, exactly the same except invisible and way more gravity.

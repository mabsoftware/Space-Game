package physics;

import java.awt.Image;

public class BlackHole extends GravityObject
{
	private static double multiplier = 100;
	
	public BlackHole(String image, double startX, double startY, double xu, double yu) // What are xu and yu?
	{
		super(image, startX, startY, 0, 0, 0, multiplier, xu, yu); // black holes don't move in the game, and you can't see them.
		
	}
} // extends Gravity Object, exactly the same except invisible and way more gravity.

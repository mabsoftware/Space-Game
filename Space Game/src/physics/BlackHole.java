package physics;

import java.awt.Image;

public class BlackHole extends GravityObject
{
	private final double MULTIPLIER = 100;
	
	public BlackHole(String image, double startX, double startY, double MULTIPLIER)
	{
		super(image, startX, startY, 0, 0, 0, MULTIPLIER); // black holes don't move in the game, and you can't see them.
		
	}
} // extends Gravity Object, exactly the same except invisible and way more gravity.

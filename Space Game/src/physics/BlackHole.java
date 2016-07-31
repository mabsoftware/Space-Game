package physics;

import java.awt.Image;

public class BlackHole extends GravityObject
{
	private final double MULTIPLIER = 100;
	
	public BlackHole(String image, double startX, double startY)
	{
		super(image, startX, startY, 0, 0, 0); // black holes don't move in the game, and you can't see them.
		
	}
}

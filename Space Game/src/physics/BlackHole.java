package physics;

import java.awt.Image;

public class BlackHole extends GravityObject
{
	private final double MULTIPLIER = 100;
	
	public BlackHole(Image image, double startX, double startY)
	{
		super(image, startX, startY, 0, 0); // black holes don't move in the game.
		
	}
}

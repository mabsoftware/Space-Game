// UNUSED CLASS

package physics;

import java.awt.Image;
import game.SpaceGame;

public class BlackHole extends GravityObject
{
	private static double multiplier = 100;
	
	public BlackHole(String image, double xUniverse, double yUniverse, SpaceGame game)
	{
		//Calling the super class and setting the location of the black hole somewhere (randomly) in the universe
		super(image, 0, 0, 0, multiplier, xUniverse, yUniverse, game); // black holes don't move in the game, and you can't see them.
		
	}
} // extends Gravity Object, exactly the same except invisible and way more gravity.

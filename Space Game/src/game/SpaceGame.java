/* Space Game in Java
 * Written in 2016
 * Main method.
 * 4 Contributors.
 * New Comment.
 */

package game;

import acm.program.GraphicsProgram;

public class SpaceGame extends GraphicsProgram 
{
	private boolean running; // game state.
	
	public static void main(String args[])
	{
		new SpaceGame().start();
	}
	
	public void init()
	{
		this.setSize(320 * 2, 240 * 2);
		this.setTitle("Space Game");
		running = true;
	}
	
	public void run()
	{
		while (running)
		{
			// Game loop.
			// New Comment.
		}
	}
}

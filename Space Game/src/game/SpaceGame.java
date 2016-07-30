/* Space Game in Java
 * Written in 2016
 * Main method.
 */

package game;

import acm.program.GraphicsProgram;

public class SpaceGame extends GraphicsProgram 
{
	public static void main(String args[])
	{
		new SpaceGame().start();
	}
	
	public void init()
	{
		this.setSize(320 * 2, 240 * 2);
		this.setTitle("Space Game");
	}
	
	public void run()
	{
		
	}
}

/* Space Game in Java
 * Started 7/30/16.
 * Main method class.
 */
package game;

import acm.program.GraphicsProgram;
import player.Player;
import java.awt.event.KeyEvent;

public class SpaceGame extends GraphicsProgram 
{
	private boolean running; // game state.
	private Player player = new Player(100, 100, 0, 0);
	
	public static void main(String args[])
	{
		new SpaceGame().start();
	}
	
	public void init()
	{
		this.setSize(320 * 2, 240 * 2);
		this.setTitle("Space Game");
		running = true;
		
		this.add(player); // add the player to the window.
		
		this.addKeyListeners();
	}
	
	public void run()
	{
		while (running)
		{
			player.monitor();
			pause(100);
		} // game loop.
	}
	
	public void keyPressed(KeyEvent k)
	{
		System.out.println(k);
		if (k.getKeyChar() == 'a')
		{
			player.goLeft();
		}
		else if (k.getKeyChar() == 'd')
		{
			player.goRight();
		}
	}
	
	public void keyReleased(KeyEvent k)
	{
		System.out.println(k);
		if (k.getKeyChar() == 'a')
		{
			player.stopMovingLeft();
		}
		else if (k.getKeyChar() == 'd')
		{
			player.stopMovingRight();
		}
	}
}

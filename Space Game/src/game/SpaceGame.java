/* Space Game in Java
 * Started 7/30/16.
 * Main method class.
 */
package game;

import acm.program.GraphicsProgram;
import player.Player;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class SpaceGame extends GraphicsProgram 
{
	private boolean running; // game state.
	private Player player = new Player(100, 100, 0, 0); // create a new player.
	
	public static void main(String args[])
	{
		new SpaceGame().start(); // run the application.
	}
	
	public void init()
	{
		this.setSize(320 * 2, 240 * 2);
		this.setTitle("Space Game"); // set the size and title of the window.
		running = true; // running boolean for the game loop.
		
		this.setBackground(Color.BLACK);
		
		this.add(player); // add the player to the window.
		
		this.addKeyListeners(); // add some key listeners.
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

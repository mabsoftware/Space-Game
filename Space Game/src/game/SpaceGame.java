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
	private Player player;
	
	public static void main(String args[])
	{
		new SpaceGame().start(); // run the application.
	}
	
	public void init()
	{
		/////////////////////////////////////////////////////////////////////
		// The window and its attributes are initialized here.             //
		/////////////////////////////////////////////////////////////////////
		this.setSize(320 * 2, 240 * 2);
		this.setTitle("Space Game"); // set the size and title of the window.
		this.setBackground(Color.BLACK);
		/////////////////////////////////////////////////////////////////////
		// Loop variable initialized here.                                 //
		/////////////////////////////////////////////////////////////////////
		running = true; // running boolean for the game loop.
		
		/////////////////////////////////////////////////////////////////////
		// Players, AIs, Gravity Objects, and others initialized here.     //
		/////////////////////////////////////////////////////////////////////
		player = new Player(this.getWidth() / 2, this.getHeight() / 2, 0, 0);
		this.add(player); // add the player to the window.
		/////////////////////////////////////////////////////////////////////
		// User input and output is initialized here.                      //
		/////////////////////////////////////////////////////////////////////
		this.addKeyListeners(); // add some key listeners.
	}
	
	public void run()
	{
		//////////////////////////////////////////////////////////////////////
		// Game loop is here.                                               //
		//////////////////////////////////////////////////////////////////////
		while (running)
		{
			////////////////////////////////////////////////
			// Monitor all objects here.                  //
			////////////////////////////////////////////////
			player.monitor();
			
			///////////////////////
			// Clock tick here. ///
			///////////////////////
			pause(100);
		} // game loop.
	}
	
	//////////////////////////////////////////////
	// All user input is handled here.          //
	//////////////////////////////////////////////
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
	///////////////////////////////////////////////
}

/* Space Game in Java
 * Started 7/30/16.
 * Main method class.
 */
package game;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import player.Player;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.awt.Color;
import background.Background;
import background.Star;
import camera.Camera;

public class SpaceGame extends GraphicsProgram 
{
	private boolean running; // game state.
	private Player player;
	private Background background;
	private Camera camera;
	
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
		
		camera = new Camera(0, 0, 640, 480);
		/////////////////////////////////////////////////////////////////////
		// Loop variable initialized here.                                 //
		/////////////////////////////////////////////////////////////////////
		running = true; // running boolean for the game loop.
		
		/////////////////////////////////////////////////////////////////////
		// Players, AIs, Gravity Objects, and others initialized here.     //
		/////////////////////////////////////////////////////////////////////
		player = new Player(this.getWidth() / 2, this.getHeight() / 2, 0, 0);
		this.add(player); // add the player to the window.
		background = new Background(60, this.getWidth(), this.getHeight());
		for (Star star: background.getBackground())
		{
			this.add(star);
		}
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
			
			this.draw(this.iterator(), camera);
			
			///////////////////////
			// Clock tick here. ///
			///////////////////////
			pause(100);
		} // game loop.
	}
	
	//////////////////////////////////////////////
	// Decides whether to "draw" each element   //
	//////////////////////////////////////////////
	public void draw(Iterator i, Camera c)
	{
		while (i.hasNext())
		{
			GObject e = (GObject)i.next();
			e.setVisible(false);
			GPoint topLeft = new GPoint(e.getX(), e.getY());
			GPoint topRight = new GPoint(e.getX() + e.getWidth(), e.getY());
			GPoint bottomLeft = new GPoint(e.getX(), e.getY() + getHeight());
			GPoint bottomRight = new GPoint(e.getX() + e.getWidth(), e.getY() + e.getHeight());
			if (c.contains(topLeft) || c.contains(topRight) || c.contains(bottomLeft) || c.contains(bottomRight))
			{
				e.setVisible(true);
			}
		}
	}
	
	//////////////////////////////////////////////
	// All user input is handled here.          //
	//////////////////////////////////////////////
	public void keyPressed(KeyEvent k)
	{
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

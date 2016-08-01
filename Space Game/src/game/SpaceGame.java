/* Space Game in Java
 * Started 7/30/16.
 * Main method and main class.
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
import map.Map;
import physics.GravityObject;

public class SpaceGame extends GraphicsProgram 
{
	private boolean running; // game state.
	private Player player;
	private Background background;
	private Camera camera;
	private GravityObject[] gravityObjects;
	private int gravityIndex;
	private Map map;
	
	public static void main(String args[])
	{
		new SpaceGame().start(); // run the application.
	}
	
	public void init()
	{
		/////////////////////////////////////////////////////////////////////
		// The window and its attributes are initialized here.             //
		/////////////////////////////////////////////////////////////////////
		this.setSize(320 * 3, 240 * 3);
		this.setTitle("Space Game"); // set the size and title of the window.
		this.setBackground(Color.BLACK);
		gravityIndex = 0;
		map = new Map();
		
		camera = new Camera(0, 0, this.getWidth(), this.getHeight());
		/////////////////////////////////////////////////////////////////////
		// Loop variable and others initialized here.                      //
		/////////////////////////////////////////////////////////////////////
		running = true; // running boolean for the game loop.
		gravityObjects = new GravityObject[100];
		/////////////////////////////////////////////////////////////////////
		// Players, AIs, Gravity Objects, and others initialized here.     //
		/////////////////////////////////////////////////////////////////////
		//player = new Player("assets/images/player.png", this.getWidth() / 2, this.getHeight() / 2, 0, 0);
		//this.add(player); // add the player to the window.
		background = new Background(120, this.getWidth(), this.getHeight());
		for (Star star: background.getBackground())
		{
			this.add(star);
		}
		
		gravityObjects = new GravityObject[100];
		gravityIndex = 0;
		map = new Map();
		map.setMap(this);
		
		for (GravityObject g: gravityObjects)
		{
			if (g != null)
			{
				this.add(g);
			}
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
			player.move();
			player.adjustForGravity(gravityObjects);
			
			for (GravityObject g: gravityObjects)
			{
				if (g != null)
				{
					g.move();
				}
			}
			
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
	
	public void addGravityObject(String image, double startX, double startY, double radius, double xVel, double yVel, double multiplier)
	{
		gravityObjects[gravityIndex] = new GravityObject(image, startX, startY, radius, xVel, yVel, multiplier);
		gravityIndex++;
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
		else if (k.getKeyChar() == 'w')
		{
			player.increaseSpeed();
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

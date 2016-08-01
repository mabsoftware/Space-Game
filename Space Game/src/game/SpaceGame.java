
/* Space Game in Java
 * Started 7/30/16.
 * Main method and main class.
 */

package game;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import ai.Enemy;
import player.Player;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.awt.Color;
import background.Background;
import background.Star;
import map.Map;
import physics.GravityObject;

public class SpaceGame extends GraphicsProgram 
{
	private boolean running; // game state.
	private Player[] players;
	private Background background;
	private GravityObject[] gravityObjects;
	private int gravityIndex;
	private Map map;
	private double xUniverse;
	private double yUniverse;
	private Enemy[] enemies;
	
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
		this.setXUniverse(500);
		this.setYUniverse(500);
		
		/////////////////////////////////////////////////////////////////////
		// Loop variable and others initialized here.                      //
		/////////////////////////////////////////////////////////////////////
		running = true; // running boolean for the game loop.
		/////////////////////////////////////////////////////////////////////
		// Players, AIs, Gravity Objects, and others initialized here.     //
		/////////////////////////////////////////////////////////////////////
		players = new Player[1];
		enemies = new Enemy[1];
		players[0] = new Player("assets/images/player.png", this.getWidth() / 2, this.getHeight() / 2, 0, 0);
		this.add(players[0]); // add the player to the window.
		background = new Background(10000, 10000, 10000);
		for (Star star: background.getBackground())
		{
			this.add(star);
		}
		
		gravityObjects = new GravityObject[100];
		gravityIndex = 0;
		map = new Map();
		map.setMap(this);
		
		enemies[0] = new Enemy(this.getWidth() / 3, this.getHeight() / 3, players);
		this.add(enemies[0]);
		
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
		
		//stuff
	}
	
	public void run()
	{
		//////////////////////////////////////////////////////////////////////
		// Game loop is here.                                               //
		//////////////////////////////////////////////////////////////////////
		while (running)
		{
			this.draw();
			////////////////////////////////////////////////
			// Monitor all objects here.                  //
			////////////////////////////////////////////////
			players[0].monitor();
			players[0].move(this);
			players[0].adjustForGravity(gravityObjects);
			
			//GravityObject.handleGravityObjectInteractions(gravityObjects);
			
			//this.add(new BlackBackground(this.getWidth(), this.getHeight()));
		
			//background = new Background(120, this.getWidth(), this.getHeight());
			
			for (Star star: background.getBackground())
			{
				star.move(-players[0].getVector().getXComponent(), -players[0].getVector().getYComponent());
				
			} // now you can see yourself moving against a star background.
			
		
			
			for (GravityObject g: gravityObjects)
			{
				if (g != null)
				{
					g.move();
				}
			}
			
			///////////////////////
			// Clock tick here. ///
			///////////////////////
			pause(100);
		} // game loop.
	}
	
	//////////////////////////////////////////////
	// Decides whether to "draw" each element   //
	//////////////////////////////////////////////
	public void draw()
	{
		for (GravityObject obj : gravityObjects)
		{
			if (obj != null)
				obj.setLocation(obj.getXUniverse() - this.getXUniverse(), obj.getYUniverse() - this.getYUniverse());	
		}
	}
	
	public void addGravityObject(String image, double startX, double startY, 
			double radius, double xVel, double yVel, double multiplier, double xUniverse, double yUniverse)
	{
		gravityObjects[gravityIndex] = new GravityObject(image, startX, startY, 
				radius, xVel, yVel, multiplier, xUniverse, yUniverse);
		gravityIndex++;
	}
	
	//////////////////////////////////////////////
	// All user input is handled here.          //
	//////////////////////////////////////////////
	public void keyPressed(KeyEvent k)
	{
		if (k.getKeyChar() == 'a')
		{
			players[0].goLeft();
		}
		else if (k.getKeyChar() == 'd')
		{
			players[0].goRight();
		}
		else if (k.getKeyChar() == 'w')
		{
			players[0].increaseSpeed();
		}
		else if (k.getKeyChar() == 's')
		{
			players[0].decreaseSpeed();
		}
	}
	
	public void keyReleased(KeyEvent k)
	{
		if (k.getKeyChar() == 'a')
		{
			players[0].stopMovingLeft();
		}
		else if (k.getKeyChar() == 'd')
		{
			players[0].stopMovingRight();
		}
	}
	///////////////////////////////////////////////

	public double getXUniverse() 
	{
		return xUniverse;
	}

	public void setXUniverse(double xUniverse) 
	{
		this.xUniverse = xUniverse;
	}

	public double getYUniverse() 
	{
		return yUniverse;
	}

	public void setYUniverse(double yUniverse) 
	{
		this.yUniverse = yUniverse;
	}
}
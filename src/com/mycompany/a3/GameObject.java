package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * This class represents a game object in the game world
 * @author kevinestrada
 *
 */
public abstract class GameObject implements ICollider, IDrawable{
	private int size;
	private Point location;
	private int color;
	private GameWorld gw;
	Random rand = new Random();
	
	/**
	 * The constructor needs a point, size and color to be created, the size is randomly assigned in some objects
	 * @param location
	 * @param size
	 * @param color
	 */
	public GameObject(float x, float y,int size, int color, GameWorld gw) {
		this.color = color;
		this.location = new Point(x, y);
		this.size = 10 + rand.nextInt(50);
		this.gw = gw;
		
	}
	
	/**
	 * updates the color of the object
	 * @param color
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * returns the color of the object
	 * @return color
	 */
	public int getColor() {
		return this.color;
	}
	
	/**
	 * updates the location when invoked
	 * @param location
	 */
	public void setLocation(float x, float y) {
		this.location.setX(x); 
		this.location.setY(y);
	}
	
	/**
	 * returns the location when called
	 * @return
	 */
	public Point getLocation() {
		return this.location;
	}
	
	
	/**
	 * returns the size of the object
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * displays the object on the console
	 */
	public String toString() {
		String objString = "loc=" + this.location.getX() + ", " + this.location.getY() +
							" color=[" + ColorUtil.red(getColor()) + 
							", " + ColorUtil.green(getColor()) + 
							", " + ColorUtil.blue(getColor()) + 
							"] size= " + this.size;
		
		return objString;
	}
	
	@Override
	public boolean collidesWith(GameObject obj) {
		boolean collided = false;
		double ogObjX = this.getLocation().getX()  + (obj.getSize()/2);
		double ogObjY = this.getLocation().getY()  + (obj.getSize()/2);
		
		double otherObjX = obj.getLocation().getX();
		double otherObjY = obj.getLocation().getY();
		double dx = ogObjX - otherObjX;
		double dy = ogObjY - otherObjY;
		double distBetweenCentersSqrt = ((dx * dx) + (dy*dy));
		int thisRadius = this.getSize() / 2;
		int otherRadius = obj.getSize() / 2;
		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if(distBetweenCentersSqrt <= radiiSqr)
			collided = true;
		
		return collided;
	}
	
	/**
	 * used to create a food station in the constructor
	 * @return
	 */
	public GameWorld getWorld() {
		return gw;
	}
	
}

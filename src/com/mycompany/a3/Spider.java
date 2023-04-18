package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * The class is an object of MoveableGameObject
 * @author kevinestrada
 *
 */
public class Spider extends MoveableGameObject implements IDrawable{
	
	static Random r = new Random();
	private GameWorld gw;
	
	
	/**
	 * The constructor takes in 3 parameters, the constructor also sets random speed, size, and heading
	 * @param location
	 * @param size
	 * @param color
	 */
	static int spiderSize = 10 + r.nextInt(50);
	public Spider(float x, float y,int size, int color, GameWorld gw) {
		
		super(x, y,spiderSize, color, gw);
		setHeading(0 + r.nextInt(359));
		setSpeed(0 + r.nextInt(50));
		
	}
	
	@Override 
	public String toString () {
		String parentStr = super.toString();
		String childStr = "[Spider] ";
		return childStr + parentStr;
	}
	@Override
	public void setColor(int color) {}


	/**
	 * When a spider and ant have the same location
	 * it changes the color of the and decreases the speed of the ant
	 */
	@Override
	public void handleCollision(GameObject otherObj) {
		int decColor = 100;
		decColor -= 5;
		int newColor = ColorUtil.rgb(decColor, 255,255);
		if(this instanceof Spider && otherObj instanceof AntSingleton) {
			((AntSingleton)otherObj).setHealthLevel(((AntSingleton)otherObj).getHealthLevel() - 1);
			((AntSingleton)otherObj).setSpeed(((AntSingleton)otherObj).getSpeed() - 5);
		}
		((AntSingleton)otherObj).setColor(newColor);
		System.out.println("The ant has collided with the spider \n the color has chenged = "
							+ ((AntSingleton)otherObj).getColor() + "\n");
	
		
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLoc = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		int [] xPt = {xLoc , (xLoc - 15), (xLoc + 15), xLoc};
		int [] yPt = {(yLoc + 30), (yLoc - 30), (yLoc - 30), (yLoc + 30)};
		int nPt = 3;
		
		g.drawPolygon(xPt, yPt, nPt);
		
	}
	
}

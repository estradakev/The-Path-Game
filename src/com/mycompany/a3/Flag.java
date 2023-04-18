package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * This class is a child of FixedGameObject that has a waypoint
 * @author kevinestrada
 *
 */
public class Flag extends FixedGameObject implements IDrawable{
	
	private int sequenceNumber;
	private GameWorld gw;
	
	/**
	 * The constructor takes the parameters for the parent constructor but it takes a sequence number as well
	 * the color is set to yellow by default. The waypoint number is not allowed to be changed after the object is created
	 * @param location
	 * @param size
	 * @param sequenceNumber
	 */
	public Flag(float x, float y, int size, int sequenceNumber, GameWorld gw) {
		super(x, y, size, ColorUtil.YELLOW, gw);
		this.sequenceNumber = sequenceNumber;
		
	}
	
	/**
	 * The method returns the waypoint number 
	 * @return sequenceNumber
	 */
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
	
	
	/**
	 * The method is updated to display the new attributes in the class
	 */
	@Override
	public String toString() {
		String parentObj = super.toString();
		String childObj = "[Flag] " 
							+ "The seqNumber=" + getSequenceNumber() + " ,"; 
		return childObj + parentObj;
	}
	
	
	@Override
	public void handleCollision(GameObject obj) {
		if(this instanceof Flag && obj instanceof AntSingleton) {
			if( ((AntSingleton)obj).getLastFlagReached() - this.getSequenceNumber() == 1) {
				((AntSingleton)obj).setLastFlagReached(((AntSingleton)obj).getLastFlagReached() + 1);
				gw.winCheck();
			}
		}
		
	}


	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int sX = (int) Math.round((this.getLocation().getX() - 12) + pCmpRelPrnt.getX());
		int sY = (int) Math.round((this.getLocation().getY() - 12) + pCmpRelPrnt.getY());
		int xLoc = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLoc = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		int[] xPoints = {xLoc, (xLoc - 25), (xLoc+ 25), xLoc};
		int[] yPoints = {(yLoc + 35), (yLoc - 35), (yLoc - 35), (yLoc + 35)};
		int n = 3;
		
		if(isSelected()) {
			g.drawPolygon(xPoints, yPoints, n);
		}
		else {
			g.fillPolygon(xPoints, yPoints, n);
			
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.getSequenceNumber(), sX, sY);
		
	}
}

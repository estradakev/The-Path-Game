package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class FoodStation extends FixedGameObject implements IDrawable{
	
	private int capacity;
	private GameObjectCollection gw;
	/**
	 * The FoodStation is a child of the FixedGameObject the parameters needed in the constructor are for the parent constructor.
	 * The capcity attribute is set to the size of the object which is randomly generated
	 * @param location
	 * @param size
	 * @param color
	 */
	public FoodStation(float x, float y, int size, int color, GameWorld gw) {
		super(x, y, size, color, gw);
		this.capacity = getSize();
		this.gw = gw.getCollection();
	}
	/**
	 * it is used to generate new position for new food station 
	 * @param r
	 * @return random point
	 */
	public float generateRandNumPoint(Random r) {
		return Math.round(r.nextFloat() * 1000);
	}
	
	/**
	 * This method updates the capacity of the object 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * returns the capcity of the object
	 * @return capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * The method is updated to display the FoodStation attributes
	 */
	@Override
	public String toString() {
		String parentObj = super.toString();
		String childObj = "[Food Station] "
					       + "capacity=" + getCapacity() + ", ";
		return childObj + parentObj;
	}
	
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int sX = (int)Math.round((this.getLocation().getX() + 5) + pCmpRelPrnt.getX());
		int sY = (int)Math.round((this.getLocation().getY() + 5) + pCmpRelPrnt.getY());
		int xLoc = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLoc = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		if(this.isSelected()) {
			g.drawArc(xLoc, yLoc, this.getSize() + 15, this.getSize() + 15, 0, 360);
		}
		else {
			g.fillArc(xLoc, yLoc, this.getSize() + 15, this.getSize() + 15, 0, 360);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.getCapacity(), sX, sY);

		
	}

	@Override
	public void handleCollision(GameObject otherObj) {
		Random r = new Random();
		if(this instanceof FoodStation && otherObj instanceof AntSingleton) {
			if(this.getCapacity() != 0) {
				((AntSingleton)otherObj).setFoodLevel(this.getCapacity() + ((AntSingleton)otherObj).getFoodLevel());
				this.setCapacity(0);
				this.setColor(ColorUtil.rgb(0, 100, 0));
				gw.add(new FoodStation(generateRandNumPoint(r),generateRandNumPoint(r), this.getWorld().getFoodStationObj(), ColorUtil.GREEN, this.getWorld()));
			}
		}
		
	}


}


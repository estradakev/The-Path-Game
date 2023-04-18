package com.mycompany.a3;

import com.codename1.ui.geom.Point;

/**
 * This class is a child class of the GameObject class and does not allowed for movements 
 * @author kevinestrada
 *
 */
public abstract class FixedGameObject extends GameObject implements ISelectable{
	
	private boolean selected;
	/**
	 * The constructor takes the parameters for the parent constructor to create an object of FixedGameObject type
	 * @param location
	 * @param size
	 * @param color
	 */
	public FixedGameObject(float x, float y, int size, int color, GameWorld gw) {
		super(x, y, size, color, gw);
	}
	
	/*
	 * The method is updated to disallow updating the location
	 */
	@Override
	public void setLocation(float x, float y) {
		this.setLocation(this.getLocation().getX(), this.getLocation().getY());
	}
	
	
	public void setSelected(boolean b) {
		selected = b;
	}
	
	
	public boolean isSelected() {
		return selected;
	}
	
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int r = this.getSize() / 2;
		int top = (int)Math.round(this.getLocation().getY() - r + pCmpRelPrnt.getY());
		int bottom = top + this.getSize();
		int left = (int)Math.round(this.getLocation().getX() - r + pCmpRelPrnt.getX());
		int right = left + this.getSize();
		
		if((pCmpRelPrnt.getX() > left) && (pCmpRelPrnt.getX() < right) && (pCmpRelPrnt.getY() > top) && (pCmpRelPrnt.getY() < bottom))
			return true;
		return false;
		
	}

}

package com.mycompany.a3;

/**
 * The class is of type GameObject but it allows for movement for objects of this type
 * @author kevinestrada
 *
 */

public abstract class MoveableGameObject extends GameObject{
	
	private int speed;
	protected int heading;
	
	/**
	 * The class takes 3 parameters for the location, size, and color. The speed and heading are set to 0
	 * @param location
	 * @param size
	 * @param color
	 */
	public MoveableGameObject(float x, float y, int size, int color, GameWorld gw) {
		super(x, y, size, color, gw);
		this.speed = 0;
		this.heading =0;
	}
	
	/**
	 * The method updates speed as long as it is a positive int
	 * @param speed
	 */
	public void setSpeed(int speed) {
			this.speed = speed;
	}
	
	/**
	 * returns the speed of the object
	 * @return speed
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * updates the heading of the object
	 * @param heading
	 */
	public void setHeading(int newHeading) {

		this.heading = newHeading;	
		
	}
	
	/**
	 * returns the heading of the object
	 * @return heading
	 */
	public int getHeading() {
		return this.heading;
	}
	
	/**
	 * The method updates the x and y location according to the heading of the object 
	 * it also checks the bounds of the objects to stay inbound 
	 */
	public void move() {
			
		int originX = (int)MapView.getMapOrigin().getX();
		int originY = (int)MapView.getMapOrigin().getY();
		int objXPadding = 0; 
		int newHeading = 360 - this.getHeading();
		boolean headingCheck = (getHeading() != 0 || getHeading() != 180);
	
		float deltaY =  (this.getLocation().getY() + (float)Math.sin(Math.toRadians(90 - this.getHeading())) * getSpeed());
		float deltaX =  (this.getLocation().getX() + (float)Math.cos(Math.toRadians(90 - this.getHeading())) * getSpeed());
		if(this instanceof AntSingleton || this instanceof Spider)
			objXPadding = 50;
		
		
		if((originX + deltaX + objXPadding) >= (MapView.getMapWidth() + originX )&& headingCheck) 
			setHeading(newHeading);
		
		if(this instanceof Spider) {
			if(originX + deltaX <= originX + 10 && headingCheck) 
				this.setHeading(newHeading);
			
			else if(originX + deltaX <= originX && headingCheck)
				this.setHeading(newHeading);
		}
		
		if(originY + deltaY + objXPadding >= originY + MapView.getMapHeight()) {
			this.setHeading((newHeading + 180) % 360);
		}
		
		if(this instanceof Spider && (10 + originY) >= originY + deltaY) {
			this.setHeading((newHeading + 180) % 360);
		}
		else if(originY + deltaY <= originY){
			setHeading((newHeading + 180) %360);
		}
		this.setLocation(deltaX, deltaY);
	}
	
	/**
	 * The method is updated to display the new attributes on the console
	 */
	@Override
	public String toString() {
		String parentObj = super.toString();
		String childObj = " speed=" + getSpeed() + " heading=" + getHeading();
		
		
		return parentObj + childObj;
	}
	
	

}

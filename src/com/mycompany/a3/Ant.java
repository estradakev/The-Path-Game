package com.mycompany.a3;

/**
 * The Ant class extends from MoveableGameObject and it implements the ISteerable interface
 * @author kevinestrada
 *
 */

public abstract class Ant extends MoveableGameObject implements ISteerable{
	
	private int maximumSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private int steeringDirection;
	
	/**
	 * The parent constructor is called and the parameters are passed it also gives the other attributes a default
	 * @param location
	 * @param size
	 * @param color
	 */
	public Ant(float x, float y, int size, int color, GameWorld gw) {
		super(x, y, size, color, gw);
		this.maximumSpeed = 75;
		this.foodLevel = 50;
		this.foodConsumptionRate = 2;
		this.healthLevel = 10;
		this.lastFlagReached = 1;
		this.steeringDirection = 0;
		setSpeed(2);
	}

	/**
	 * Gives the maxSpeed when invoked
	 * @return maximumSpeed
	 */
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}
	
	/**
	 * updates the maximum speed
	 * @param maximumSpeed
	 */
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	/**
	 * returns the foodLevel of the object
	 * @return foodLevel
	 */
	public int getFoodLevel() {
		return foodLevel;
	}

	/**
	 * updates the object foodLevel
	 * @param foodLevel
	 */
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	/**
	 * returns the foodConsumptionRate
	 * @return foodConsumptionRate
	 */
	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}

	/**
	 * updates the foodConsumptionRate
	 * @param foodConsumptionRate
	 */
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}

	/**
	 * returns the healthLevel
	 * @return healthLevel
	 */
	public int getHealthLevel() {
		return healthLevel;
	}

	/**
	 * updates the healthLevel
	 * @param healthLevel
	 */
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}

	/**
	 * returns the last flag reached
	 * @return lastFlagReached
	 */
	public int getLastFlagReached() {
		return lastFlagReached;
	}

	/**
	 * updates the lastFlagReached
	 * @param lastFlagReached
	 */
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	/**
	 * updates the steeringDirection
	 * @param direction
	 */
	public void setSteeringDirections(int direction) {
		this.steeringDirection = direction;
	}
	
	/**
	 * returns the steeringDirection 
	 * @return steeringDirection
	 */
	public int getSteeringDirection() {
		return this.steeringDirection;
	}
		
	/**
	 * The toString method is updated to display the ant attributes
	 */
	@Override
	public String toString() {
		
		String parentObj = super.toString();
		String childObj = "[Ant] " 
							+ "maxSpeed=" + getMaximumSpeed() + ", foodConsumptionRate=" + getFoodConsumptionRate() + ", ";
	
		return childObj + parentObj;
	}

	/**
	 * The heading method gets implemented 
	 */
	@Override
	public void setHeading(int heading) {
		this.heading = heading;
	}
	

}
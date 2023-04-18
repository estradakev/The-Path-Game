package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Singleton class for the ant 
 * @author kevinestrada
 *
 */

public class AntSingleton extends Ant implements IDrawable{
	
	private static AntSingleton ant;
	private static Random r = new Random();
	private AntSingleton(GameWorld gw) {
		super(r.nextInt(1000), r.nextInt(1000), 45, ColorUtil.BLUE, gw);
		this.setMaximumSpeed(75);
		this.setFoodLevel(50);
		this.setFoodConsumptionRate(2);
		this.setHealthLevel(15);
		this.setLastFlagReached(1);
		this.setSteeringDirections(0);
		this.setSpeed(5);
	}
	
	public static AntSingleton getAnt(GameWorld gw) {
		if(ant == null) {
			ant = new AntSingleton(gw);
		}
		return ant;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		
		int locX = (int) this.getLocation().getX() + (int) pCmpRelPrnt.getX(); 
		int locY = (int) this.getLocation().getY() + (int) pCmpRelPrnt.getY();
		double walkPath = Math.toRadians(90 - super.getHeading());
		int walkPathX = (int) ((Math.cos(walkPath) * 50) + this.getLocation().getX() + pCmpRelPrnt.getX());
		int walkPathY = (int) ((Math.sin(walkPath) * 50) + this.getLocation().getY() + pCmpRelPrnt.getY());

		
		g.fillRect(locX, locY, this.getSize(), this.getSize());
		g.setColor(this.getColor());
		g.drawLine(locX, locY + 15, walkPathX, walkPathY);
		
		

	}

	@Override
	public void handleCollision(GameObject otherObj) {
		
	}

	
}
package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * food collision command is created and action performed is invoked gw foodCollision method is called
 * @author kevinestrada
 *
 */
public class FoodCollisionCommand extends Command{
	
	private GameWorld gw;
	public FoodCollisionCommand(GameWorld gameWorld) {
		super("Food Collision");
		this.gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.foodCollision();
		System.out.println("Food Collision command is invoked...");
	}
}

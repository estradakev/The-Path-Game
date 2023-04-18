package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * spider command is created when the action performed is invoked the gw spider collision is called
 * @author kevinestrada
 *
 */
public class SpiderCollisionCommand extends Command{

	private GameWorld gw;
	public SpiderCollisionCommand(GameWorld gameWorld) {
		super("Spider Collision");
		this.gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.spiderCollision();
		System.out.println("Spider collision command is invoked...");
	}
}

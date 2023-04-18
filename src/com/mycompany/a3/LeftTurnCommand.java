package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * left turn command is created when action performed is called gw calls the goLeft method
 * @author kevinestrada
 *
 */
public class LeftTurnCommand extends Command{

	private GameWorld gw;
	public LeftTurnCommand(GameWorld gameWorld) {
		super("Left Turn");
		this.gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.goLeft();
		System.out.println("Left turn command is invoked...");
	}
}

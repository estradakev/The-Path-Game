package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * right turn command is created when action performed is called gw calls the goRight method
 * @author kevinestrada
 *
 */
public class RightTurnCommand extends Command{
	
	private GameWorld gw;
	public RightTurnCommand(GameWorld gameWorld) {
		super("Turn Right");
		this.gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.goRight();
		System.out.println("Right turn command is invoked...");
	}
}

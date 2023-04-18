package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * break command is created and when action performed is invoked the gw antBreaking method is called 
 * @author kevinestrada
 *
 */
public class BreakCommand extends Command{
	private GameWorld gw;
	public BreakCommand(GameWorld gameWorld) {
		super("Break");
		this.gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Break command is invoked...");
		gw.antBreaking();
	}

	
}

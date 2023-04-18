package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * TickCommand, when clicked it it calls the gw tick function
 * @author kevinestrada
 *
 */
public class TickCommand extends Command{
	
	private GameWorld gw;
	public TickCommand(GameWorld gameWorld) {
		super("Tick");
		this.gw = gameWorld;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.tick();
		System.out.println("Tick command is invoked...");
	}
}

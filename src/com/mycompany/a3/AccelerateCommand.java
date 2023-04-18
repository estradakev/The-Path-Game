package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * the accelerate command is created and when action is performed it invokes the gw accelerate method
 * @author kevinestrada
 *
 */
public class AccelerateCommand extends Command{
	
	private GameWorld gw;
	public AccelerateCommand(GameWorld gameWorld) {
		super("Accelerate");
		this.gw = gameWorld;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.antAccelerate();
		System.out.println("Accelerate command is invoked...");
	}
}

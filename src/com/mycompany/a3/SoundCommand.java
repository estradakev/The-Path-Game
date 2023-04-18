package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * it calls the gw sound function and prints that the button has been clicked
 * @author kevinestrada
 *
 */
public class SoundCommand extends Command{
	private GameWorld gameWorld;
	public SoundCommand(GameWorld gameWorld) {
		super("Sound");
		this.gameWorld = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameWorld.setSound();
		System.out.println("Sound command is invoked...");
	}
}

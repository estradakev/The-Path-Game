package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

/**
 * flag collision command is created
 * when action performed is called 
 * it creates a text field
 * it makes sure you can only use number 1 through 9 
 * if it is it invokes gw flag collision method
 * @author kevinestrada
 *
 */
public class FlagCollisionCommand extends Command{
	
	private GameWorld gw; 
	public FlagCollisionCommand(GameWorld gameWorld) {
		super("Flag Collision");
		this.gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Command okCmd = new Command("Ok");
		TextField flagTf = new TextField();
		Command flgCmd = Dialog.show("Flag collision", flagTf, okCmd);
		
		
		
		try {
			String count = flagTf.getText();
			int cmdCheck = Integer.parseInt(count);
			if(cmdCheck > 0 && cmdCheck <10) {
				gw.flagCollision();
				System.out.println("Flag Collision command is invoked...");
			}
			else {
				String errString = "Please enter a number between 1-9";
				Dialog.show("Error", errString,"Back", null);
			}
		}catch(NumberFormatException exception){
			String errText = "Please enter a number between 1-9 and not a letter";
			Dialog.show("Error", errText, "Back", null);
		}
		
		
		
	}
}

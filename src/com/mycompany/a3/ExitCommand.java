package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

/**
 * exit command is created when action performed is called
 * it creates a dialog asking if you want to exit game 
 * @author kevinestrada
 *
 */
public class ExitCommand extends Command{
	
	public ExitCommand() {
		super("Exit");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Command exitCmd = new Command("Exit");
		Command cancelCmd = new Command("Cancel");
		
		Command[] cmds = new Command[] {exitCmd, cancelCmd};
		
		Command exitDialog = Dialog.show("Exit Game","Do you want to exit game?", cmds);
		
		if(exitDialog == exitCmd)
			Display.getInstance().exitApplication();
		System.out.println("Exit command is invoked...");
	}

}

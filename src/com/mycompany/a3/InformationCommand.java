package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * Information (about) command is created it displays my name, course and version of the game 
 * @author kevinestrada
 *
 */
public class InformationCommand extends Command{

	public InformationCommand() {
		super("About");
	}
	
	public void actionPerformed(ActionEvent e) {
		String info = "Name: Kevin Estrada,\n"
					+ "Course: Csc 133,\n"
					+ "Version: 2.0 \n";
		
		Dialog.show("About", info, "Back",null);
		System.out.println("Information command is invoked...");
	}
}

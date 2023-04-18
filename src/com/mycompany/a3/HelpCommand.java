package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * creates the command for help
 * @author kevinestrada
 *
 */
public class HelpCommand extends Command{

	public HelpCommand() {
		super("Help");
	}
	
	/**
	 * when invoked it shows the keybinding commands
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String keyInfo = "Keyboard Commands:\n"
					   + "a: accelerate\n"
					   + "b: break\n"
					   + "l: go left\n"
					   + "r: go right\n"
					   + "f: food collision\n"
					   + "t: tick world\n"
					   + "g: spider collision";
		
		Dialog.show("Keyboard Info", keyInfo, "Back", null);
		
		System.out.println("Help command is invoked...");
	}
}

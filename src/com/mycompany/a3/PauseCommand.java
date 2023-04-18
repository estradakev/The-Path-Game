package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command{
	private GameWorld gw;
	private Button btn;
	private Game game;

	public PauseCommand(GameWorld gw, Button btn, Game game) {
		super("Pause");
		this.gw = gw;
		this.btn = btn;
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!gw.isPaused()) {
			game.pausedView();
			btn.setText("Play");
		}
		else {
			game.pausedView();
			btn.setText("Pause");
		}
	}
}

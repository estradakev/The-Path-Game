package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

// COMMAND D DISPLAY THE CURRENT GAME STATE
/**
 * implements the interface it also makes labels for the scoreview and sets the layout
 * @author kevinestrada
 *
 */
public class ScoreView extends Container implements Observer{

	private Label timeVal;
	private Label livesValLbl;
	private Label flagVal;
	private Label foodVal;
	private Label healthVal;
	private Label soundVal;
	
	public ScoreView() {
		layout();
		timeLabel();
		livesLeftLabel();
		lastFlagReachedLabel();
		foodLevelLabel();
		healthLevelLabel();
		soundLabel();
		
	}
	/**
	 * sets the layout for the container
	 */
	public void layout() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
	}
	/**
	 * creates the time label and label for the value 
	 */
	public void timeLabel() {
		this.getAllStyles().setPadding(LEFT, 300);
		
		Label timeLbl = makeLabel("Time:");
		timeVal = makeLabel("0");
		
		this.add(timeLbl);
		this.add(timeVal);
	}
	/**
	 * creates the lives label and label for the value
	 */
	public void livesLeftLabel() {
		Label livesLbl = makeLabel("Lives Left:");
		livesValLbl = makeLabel("3");
		
		this.add(livesLbl);
		this.add(livesValLbl);
	}
	/**
	 * creates the last flag label and label for the value
	 */
	public void lastFlagReachedLabel() {

		Label lastFlagLbl = makeLabel("Last Flag Reached");
		flagVal = makeLabel("1");
		
		this.add(lastFlagLbl);
		this.add(flagVal);
	}
	/**
	 * creates the label for food level and for the value
	 */
	public void foodLevelLabel() {
		
		Label foodLbl = makeLabel("Food Level:");
		foodVal = makeLabel("50");
		foodVal.getAllStyles().setBgTransparency(255);
		
		this.add(foodLbl);
		this.add(foodVal);
	}
	/**
	 * creates the label for health level and for the value
	 */
	public void healthLevelLabel() {
		
		Label healthLbl = makeLabel("Health Level:");
		healthVal = makeLabel("10");
		
		this.add(healthLbl);
		this.add(healthVal);
	}
	/**
	 * creates the label for sound and for the value
	 */
	public void soundLabel() {
		Label soundLbl = makeLabel("Sound:");
		soundVal = makeLabel("OFF");
		
		this.add(soundLbl);
		this.add(soundVal);
	}
	/**
	 * creates label and returns a styled label
	 * @param txt
	 * @return lbl
	 */
	private Label makeLabel(String txt) {
		Label lbl = new Label(txt);
		
		lbl.getAllStyles().setBgColor(ColorUtil.WHITE);
		lbl.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		return lbl;
	}
	/**
	 * updates the text on the GUI side 
	 */
	@Override
	public void update(Observable o, Object arg) {
		GameWorld gameWorld = (GameWorld) arg;
		
		timeVal.setText(Integer.toString(gameWorld.getClock()));
		livesValLbl.setText("" + gameWorld.getLives());
		flagVal.setText("" + gameWorld.getLastFlag());
		foodVal.setText("" + gameWorld.getFood());
		healthVal.setText("" + gameWorld.getHealth());
		
		if(gameWorld.getSoundVal()) 
			soundVal.setText("ON");
		else 
			soundVal.setText("OFF");
		
		this.repaint();
		
	}
}

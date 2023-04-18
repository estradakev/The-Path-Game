package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;


public class Game extends Form implements Runnable{
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer clock;
	private PositionCommand posCmd;
	private PauseCommand pauseCmd;
	private AccelerateCommand aCmd;
	private InformationCommand iCmd;
	private HelpCommand hCmd;
	private ExitCommand eCmd;
	private SoundCommand sCmd;
	private BreakCommand breakCmd;
	private RightTurnCommand rightTCmd;
	private LeftTurnCommand leftTCmd;
	private Button breakBtn;
	private Button accelBtn;
	private Button rightBtn;
	private Button leftBtn;
	private Button positionBtn;
	private Button pauseBtn;
	private final int TICK = 20;
	
	
	public Game() {
		this.setLayout(new BorderLayout());
		gw = new GameWorld();
		gw.init();
		mv = new MapView(gw, this);
		sv = new ScoreView();
		clock = new UITimer(this);
		clock.schedule(TICK, true, this);
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		gw.setWorldHeight(mv.getHeight());
		gw.setWorldWidth(mv.getWidth());
		
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		
		sideMenu();
		leftView();
		rightView();
		bottomView();
		
		mv.setMapOrigin(new Point(mv.getX(), mv.getY()));
		MapView.setMapHeight(mv.getHeight());
		MapView.setMapWidth(mv.getWidth());
		
		
		this.show();
//		gw.createSounds();

		
	}
	
	/**
	 * creates the side manu container and contains 
	 * acceleration, information, helpm exit and sound buttons 
	 */
	private void sideMenu() {
		Toolbar toolBar = new Toolbar();
		this.setToolbar(toolBar);
		toolBar.setTitle("ThePath Game");
		
		//accelerate cmd
		aCmd = new AccelerateCommand(gw);
		toolBar.addCommandToSideMenu(aCmd);
		
		//information cmd
		iCmd = new InformationCommand();
		toolBar.addCommandToSideMenu(iCmd);
		
		//help cmd
		hCmd = new HelpCommand();
		toolBar.addCommandToSideMenu(hCmd);
		
		eCmd = new ExitCommand();
		toolBar.addCommandToSideMenu(eCmd);
		
		//sound cmd checkbox
		sCmd = new SoundCommand(gw);
		CheckBox myCheck = new CheckBox();
		myCheck.getUnselectedStyle().setBgTransparency(255);
		myCheck.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		myCheck.getUnselectedStyle().setBgColor(ColorUtil.WHITE);
		myCheck.setCommand(sCmd);
		toolBar.addComponentToSideMenu(myCheck);
		
		
	}
	
	
	//East side container with the break and turn right button 
	private void rightView() {
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.getAllStyles().setPadding(TOP, 450);
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		
		//Break button and command creation and binding
		breakCmd = new BreakCommand(gw);
		breakBtn = new Button(breakCmd);
		breakBtn = btnStyle(breakBtn);
		eastContainer.add(breakBtn);
		addKeyListener('b', breakCmd);
		
		//Right Command, Button created and binded
		rightTCmd = new RightTurnCommand(gw);
		rightBtn = new Button(rightTCmd);
		rightBtn = btnStyle(rightBtn);
		eastContainer.add(rightBtn);
		addKeyListener('r', rightTCmd);
		
		//add buttons to the right view
		this.add(BorderLayout.EAST, eastContainer);
	}
	
	//West side container with acceleration and left buttons
	private void leftView() {
		Container westContainer = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		westContainer.getAllStyles().setPadding(TOP, 450);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		
		//Acceleration button and command creation and binding
		aCmd = new AccelerateCommand(gw);
		accelBtn = new Button(aCmd);
		accelBtn = btnStyle(accelBtn);
		westContainer.add(accelBtn);
		addKeyListener('a', aCmd);
		
		//Left Command, Button created and binded
		leftTCmd = new LeftTurnCommand(gw);
		leftBtn = new Button(leftTCmd);
		leftBtn = btnStyle(leftBtn);
		westContainer.add(leftBtn);
		addKeyListener('l', leftTCmd);
		
		//add buttons to west view
		this.add(BorderLayout.WEST,westContainer);
	}
	
	/**
	 * creates the bottom container with 
	 * flag , spider, food collision buttons 
	 */
	private void bottomView() {
		Container southContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		southContainer.getAllStyles().setPadding(TOP, 3);
		southContainer.getAllStyles().setPadding(LEFT, 500);
		southContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		
		
		//Tick button,command creation and binding
		TickCommand tickCmd = new TickCommand(gw);

		addKeyListener('t', tickCmd);
		PositionCommand posCmd = new PositionCommand(gw);
		positionBtn = new Button(posCmd);
		positionBtn = btnStyle(positionBtn);
		positionBtn.getAllStyles().setMargin(LEFT, 350);
		southContainer.add(positionBtn);
		
		pauseBtn = new Button();
		pauseBtn = btnStyle(pauseBtn);
		PauseCommand pauseCmd = new PauseCommand(gw,pauseBtn,this);
		pauseBtn.setCommand(pauseCmd);
		southContainer.add(pauseBtn);
		
		
		//Add them to the bottom view
		this.add(BorderLayout.SOUTH, southContainer);
	}
	
	/**
	 *  disables and enables commands and buttons when pause is clicked
	 */
	public void pausedView() {
		if(!gw.isPaused()) {
			clock.cancel();
			gw.setPause(!gw.isPaused());
			removeKeyListener('a', aCmd);
			removeKeyListener('b', breakCmd);
			removeKeyListener('l', leftTCmd);
			removeKeyListener('r', rightTCmd);
			aCmd.setEnabled(false);
			breakCmd.setEnabled(false);
			leftTCmd.setEnabled(false);
			rightTCmd.setEnabled(false);
			accelBtn.setEnabled(false);
			accelBtn = disBtnStyle(accelBtn);
			breakBtn.setEnabled(false);
			breakBtn = disBtnStyle(breakBtn);
			leftBtn.setEnabled(false);
			leftBtn = disBtnStyle(leftBtn);
			rightBtn.setEnabled(false);
			rightBtn = disBtnStyle(rightBtn);
			positionBtn.setEnabled(true);
			positionBtn = btnStyle(positionBtn);
//			gw.soundOff();
			
		}
		else {
			clock.schedule(TICK, true, this);
			addKeyListener('a',aCmd);
			addKeyListener('b', breakCmd);
			addKeyListener('l', leftTCmd);
			addKeyListener('r', rightTCmd);
			aCmd.setEnabled(true);
			breakCmd.setEnabled(true);
			leftTCmd.setEnabled(true);
			rightTCmd.setEnabled(true);
			accelBtn.setEnabled(true);
			accelBtn = btnStyle(accelBtn);
			breakBtn.setEnabled(true);
			breakBtn = btnStyle(breakBtn);
			leftBtn.setEnabled(true);
			leftBtn = btnStyle(leftBtn);
			rightBtn.setEnabled(true);
			rightBtn = btnStyle(rightBtn);
			positionBtn.setEnabled(false);
			positionBtn = disBtnStyle(positionBtn);
//			gw.resumeSound();
			gw.setPause(!gw.isPaused());
		}
	}
		

	//Styles button
	private Button btnStyle(Button btn) {
		btn.getAllStyles().setBgTransparency(255);
		btn.getAllStyles().setFgColor(ColorUtil.WHITE);
		btn.getAllStyles().setBgColor(ColorUtil.BLUE);
		btn.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		btn.getAllStyles().setPadding(TOP, 4);
		btn.getAllStyles().setPadding(BOTTOM, 4);
		return btn;
	}
	//styles button to disable
	private Button disBtnStyle(Button btn) {
		btn.getAllStyles().setBgTransparency(255);
		btn.getAllStyles().setFgColor(ColorUtil.BLUE);
		btn.getAllStyles().setBgColor(ColorUtil.WHITE);
		btn.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		btn.getAllStyles().setPadding(TOP, 4);
		btn.getAllStyles().setPadding(BOTTOM, 4);
		return btn;
	}
	//This method calls the tick method from the gameWorld to make the gw tick
	@Override
	public void run() {
		gw.tick();
		
	}

	
	 
}

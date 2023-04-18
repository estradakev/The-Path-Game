package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point;

public class GameWorld extends Observable{
	
	/**
	 * The GameWorld class is the model of the entire project, handling movements, displaying objects as well as creating objects
	 * @author kevinestrada
	 */

	private final int FLAG_SIZE = 15;
	private int lives;
	private int clock;
	private int dangerHealth;
	private static int worldWidth;
	private static int worldHeight;
	private boolean sound; 
	private boolean pause;
	private boolean position;
	private BGSound bgSound;
	private AntSingleton antSingleton;
	private GameObjectCollection collection;
	private Sound spiderSound;
	private Sound flagSound;
	private Sound foodSound;

	Random r = new Random();

	/**
	 * These determine how many fixed objects will be created
	 */
	int flagObj = 4 + r.nextInt(5);
	int foodStationObj = 2 + r.nextInt(5);
	/**
	 * constructor of the GameWorld object
	 */
	public GameWorld() {
	}

	/**
	 * The method instantiates a game world, gives 3 lives and a clock at 0.
	 * An arrayList is created where objects are stored. 
	 * Only 1 ant is created, 3 spiders and foodSations as well as flags are randomly generated 
	 * it also sets the maxSpeed of the ant created
	 */
	public void init() {
		dangerHealth = 200;
		worldWidth = 1000;
		worldHeight = 1000;
		collection = new GameObjectCollection();
		antSingleton = AntSingleton.getAnt(this);
		collection.add(antSingleton.getAnt(this));
		this.lives = 3;
		this.clock = 0;
		sound = false;
		pause = false;
		position = false;

		collection.add(new Flag(antSingleton.getLocation().getX(), antSingleton.getLocation().getY(), FLAG_SIZE,1, this));
		

		for (int i = 2; i <= flagObj; i++) {
			collection.add(new Flag(generateRandNumPoint(), generateRandNumPoint(), FLAG_SIZE, i, this));
		}

		for (int i = 1; i <= foodStationObj; i++) {
			collection.add(new FoodStation(generateRandNumPoint(), generateRandNumPoint(), foodStationObj,
					ColorUtil.GREEN, this));
		}

		for (int i = 1; i <= 3; i++) {
			collection.add(new Spider(generateRandNumPoint(), generateRandNumPoint(), r.nextInt(5) + 5,
					ColorUtil.BLACK, this));
		}
		this.setChanged();
		this.notifyObservers(this);
		

	}
	/**
	 * This class is private and it only created a random point for the points needed to create an object
	 * @return Math.round(r.nextFloat() * 1000);
	 */
	public float generateRandNumPoint() {
		return Math.round(r.nextFloat() * 1000);
	}
	
	/**
	 * returns the players lives
	 * @return lives
	 */
	public int getLives() {
		return this.lives;
	}

	/**
	 * returns the status of the clock
	 * @return clock
	 */
	public int getClock() {
		return this.clock;
	}
	/**
	 * returns the last reached flag
	 * @return getLastFlagReached
	 */
	public int getLastFlag() {
		return antSingleton.getLastFlagReached();
	}
	/**
	 * gets the food level
	 * @return getFoodLevel
	 */
	public int getFood() {
		return antSingleton.getFoodLevel();
	}
	/**
	 * gets the health level
	 * @return healthLevel
	 */
	public int getHealth() {
		return antSingleton.getHealthLevel();
	}
	/**
	 * get the value of sound
	 * @return sound;
	 */
	public boolean getSoundVal() {
		return sound;
	}
	public GameObjectCollection getCollection() {
		return collection;
	}
	/**
	 * 
	 */
	public void setSound() {
		if(getSoundVal()) 
			this.sound = false;
		else
			this.sound = true;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void setPause(boolean val) {
		pause = val;
	}
	
	public void soundOff() {
		bgSound.pause();
	}
	public void resumeSound() {
		if(getSoundVal())
			bgSound.play();
	}

	public void createSounds() {
		flagSound = new Sound("flag.wav");
		foodSound = new Sound("foodstation.WAV");
		spiderSound = new Sound("spider.wav");
		bgSound = new BGSound("loop.wav");
		
	}
	
	public void setWorldWidth(int width) {
		worldWidth = width;
	}
	
	public void setWorldHeight(int height) {
		worldHeight = height;
	}
	
	public void clicked(Point clickedPoint, Point originPoint) {
		IIterator itrObj = collection.getIterator();
		while(itrObj.hasNext()) {
			if(itrObj.getNext() instanceof FixedGameObject) {
				FixedGameObject newPos = (FixedGameObject) itrObj.getIdx();
				if(position && newPos.isSelected()) {
					int newXLoc = (int) (clickedPoint.getX() - originPoint.getX()); 
					int newYLoc = (int) (clickedPoint.getY() - originPoint.getY());
					newPos.setLocation(newXLoc, newYLoc);
					newPos.setSelected(false);
					position = false;
				}else {
					if(newPos.contains(clickedPoint, originPoint)) {
						newPos.setSelected(true);
					}
					else
						newPos.setSelected(false);
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
		
	}
	/**
	 * This method increases the speed of the ant
	 * it checks the healthLevel depending on it it can only increase the speed accordingly
	 */
	public void antAccelerate() {
		int speed = antSingleton.getSpeed();
		int maxSpeed = antSingleton.getMaximumSpeed();

		if (speed != maxSpeed && maxSpeed != 0) {
			if (getHealth() >= 8) {
				speed += 5;
				antSingleton.setSpeed(speed);
			} else if (getHealth() >= 6) {
				speed += 3;
				antSingleton.setSpeed(speed);
			} else if (getHealth() >= 3) {
				speed += 1;
				antSingleton.setSpeed(speed);
			} else if (getHealth() < 3 && getHealth() >= 0) {
				System.out.println("Due to health level the speed cannot be increased");
			}
		}

		if (speed == maxSpeed)
			System.out.println("The ant speed is " + antSingleton.getSpeed() + ", it is at the highest it can go\n");
		else
			System.out.println("Speed is " + antSingleton.getSpeed() + "\n");
		
		this.setChanged();
		this.notifyObservers(this);

	}
	
	/**
	 * When method is invoked it decreases the speed of the ant by 5 if it is not at 0
	 */
	public void antBreaking() {
		int speed = antSingleton.getSpeed();

		if (speed >= 0 && (speed > 0)) {
			speed -= 5;
			antSingleton.setSpeed(speed);
			System.out.println("The ant speed is now " + antSingleton.getSpeed() + "\n\n");
		} else {
			System.out.println("Speed can not be lowered, current speed is " + antSingleton.getSpeed() + "\n\n");
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * Invoking this method will set a new steeringDirection decreasing it 5 each time it is called
	 */
	public void goLeft() {
		
		antSingleton.setSteeringDirections(antSingleton.getSteeringDirection() + 5);
		
		if(antSingleton.getSteeringDirection() > 40) {
			antSingleton.setSteeringDirections(40);
		}
		antSingleton.setHeading(antSingleton.getSteeringDirection() + antSingleton.getHeading());
		
		System.out.println("Ant has turned left by 5 degrees the new heading is " + antSingleton.getHeading() + "\n\n");
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * The steering Direction will increase it 5 each time it is invoked
	 */
	public void goRight() {
		antSingleton.setSteeringDirections(antSingleton.getSteeringDirection() - 5);
		if(antSingleton.getSteeringDirection() < -40) {
			antSingleton.setSteeringDirections(-40);
		}
		antSingleton.setHeading(antSingleton.getHeading() + antSingleton.getSteeringDirection());
		

		System.out.println("Ant has turned right by 5 degrees the new heading is " + antSingleton.getHeading() + "\n\n");
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * The method will cause the ant foodLevel to decrease each time as well as 
	 * cause the moveable objects in the game to move
	 * The clock is also increased by one
	 */
	public void tick() {
		int spiderNewHeading = 1 + r.nextInt(5);
		this.clock += 1;
		
		IIterator elements = collection.getIterator();
			
		
		if(antSingleton.getFoodLevel() > 0 && antSingleton.getHealthLevel()  < dangerHealth ) {
			antSingleton.move();
			antSingleton.setHealthLevel(antSingleton.getFoodLevel() - antSingleton.getFoodConsumptionRate());
		
		}
		while(elements.hasNext()) {
			GameObject gameObj = elements.getNext();
			
			if(gameObj instanceof Spider) {
				((Spider) gameObj).setHeading((((Spider) gameObj).getHeading()) - spiderNewHeading);
				((Spider) gameObj).move();
			}
			
		}


		this.setChanged();
		this.notifyObservers(this);
		System.out.println("Clock: " + this.clock + "\n\n");
	}
	
	/**
	 * The method displays the lives, clock, last reached flag, and the current health level
	 */
	public void display() {

		String curLives = "Current lives: " + getLives() + ", \n";
		String curClock = "Current clock: " + getClock() + ", \n";
		String reachedBase = "Highest base reached: " + antSingleton.getLastFlagReached() + ", \n";
		String foodLvl = "Ant food level: " + getFood() + ", \n";
		String healthLvl = "Ant health level: " + getHealth() + "\n";

		System.out.println(curLives + curClock + reachedBase + foodLvl + healthLvl);
		this.setChanged();
		notifyObservers(this);
	}

	
	/**
	 * Displays the objects that are stored in the arrayList as well as the stats
	 */
	public void mapGame() {
		IIterator elements = collection.getIterator();
		
		while(elements.hasNext()) {
			 
			System.out.println((elements.getNext()).toString());
		}
		System.out.println("\n\n");
	}

	
	/**
	 * When the ant collides with the foodStation it increases the healthLevel of it,
	 * the color of the station is changed and a new station is added to the game
	 */
	public void foodCollision() {
		int newColor = ColorUtil.rgb(0,100,0);
		IIterator elements = collection.getIterator();
		GameObject station = elements.getNext();
		
		while(elements.hasNext()) {
			station = elements.getNext();
			if(station instanceof FoodStation && ((FoodStation)station).getCapacity() != 0) {
				antSingleton.setFoodLevel((getFood()) + ((FoodStation)station).getCapacity());
				((FoodStation)station).setCapacity(0);
				((FoodStation)station).setColor(newColor);
				collection.add(new FoodStation(generateRandNumPoint(),generateRandNumPoint(), foodStationObj, ColorUtil.GREEN, this));
				System.out.println("ant: color=["
						+ ColorUtil.red(antSingleton.getColor()) +","
						+ ColorUtil.green(antSingleton.getColor()) + ","
						+ ColorUtil.blue(antSingleton.getColor()) + ","
						+ "]\n"
						+ "new foodStation has been added");
				break;
			}
			this.setChanged();
			notifyObservers(this);
		}
	}
	
	/**
	 * When a spider and ant have the same location
	 * it changes the color of the and decreases the speed of the ant
	 */
	public void spiderCollision() {
		lostCheck();
		int decColor = 100;
		decColor -= 5;
		int newColor = ColorUtil.rgb(decColor,255,255);
		IIterator elements = collection.getIterator();
		
		while(elements.hasNext()) {
			GameObject element = elements.getNext();
			if(element instanceof AntSingleton) {
				antSingleton.setHealthLevel(getHealth() - 1);
				antSingleton.setSpeed(antSingleton.getSpeed() - 5);
			}
		}
		
		antSingleton.setColor(newColor);
		System.out.println("The ant has collided with a spider\n new healthLevel=" 
						  + getHealth() + "\n new speed="  
						  + antSingleton.getSpeed() + "\n new color="
				  		  + antSingleton.getColor());
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * When the ant collides with a flag the lastFlagReached is increased by 1 if the base and last reached flag are different by 1
	 */
	public void flagCollision() {
		lostCheck();
		System.out.println("A flag collision has occured");
		IIterator elements = collection.getIterator();
		int lastFlag = antSingleton.getLastFlagReached();
		GameObject flag = elements.getNext();
		
		while(elements.hasNext()) {
			flag = elements.getNext();
			if(flag instanceof Flag && ((Flag) flag).getSequenceNumber() - lastFlag == 1) {
				antSingleton.setLastFlagReached(lastFlag + 1);
				break;
			}
			
		}
		winCheck();

		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * checks if the ant's last flag reached is the same as the last flag if true
	 * player wins game and exits game
	 */
	public void winCheck() {
			
		if(antSingleton.getLastFlagReached() == flagObj) {
			System.out.println("You win! total time: " + getClock());
			exit();
		}
		
	}
	/**
	 * checks if the foodLevel, speed or health = 0 
	 * if true it subtracts a life 
	 * if the player still has lives it resets the values of the ant 
	 */
	public void lostCheck() {
		if(getFood() == 0 || antSingleton.getSpeed() == 0 || getHealth() == 0) {
			lives -= 1;
			if(getLives() != 0) {
				System.out.println("You\'ve lost a life.");
				antSingleton.setMaximumSpeed(0);
				antSingleton.setFoodLevel(50);
				antSingleton.setFoodConsumptionRate(2);
				antSingleton.setSteeringDirections(0);
				antSingleton.setSpeed(2);
				antSingleton.setLocation(0,0);
			}
		}
		if(getLives() == 0 ) {
			System.out.println("You ran out of lives! Game Over");
			exit();
		}
	}
	
	/**
	 * This method closed the application
	 */
	public void exit() {
		System.exit(0);
	}
	/**
	 * returns the random size for the food station 
	 * @return foodStationObj
	 */
	public int getFoodStationObj() {
		return foodStationObj;
	}
	/**
	 * returns the pause value
	 * @return
	 */
	public boolean isPaused() {
		return pause;
	}
	/**
	 * inverses the position value
	 */
	public void positionSwitch() {
		if(position)
			position = false;
		else 
			position = true;
		
	}
	
	
	
}

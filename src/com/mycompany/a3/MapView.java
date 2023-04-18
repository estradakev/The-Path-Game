package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;

// COMMAND M DISPLAYS THE MAP OF THE GAME
public class MapView extends Container implements Observer{

	private static int height;
	private static int width;
	private GameWorld gameWorld;
	private static Point mapViewOrgn;
	private Game g;
	private GameObjectCollection collection;
	
	public MapView(GameWorld gameWorld, Game g) {

		this.gameWorld = (GameWorld) gameWorld;
		collection = gameWorld.getCollection();
		MapView.height = this.getHeight();
		MapView.width = this.getWidth();
		this.g = g;
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(255,0,0)));

		
	}
	/**
	 * sets the size of the container height
	 * @param newHeight
	 */
	public static void setMapHeight(int newHeight) {
		MapView.height = newHeight;
	}
	/**
	 * sets the size of the container width 
	 * @param newWidth
	 */
	public static void setMapWidth(int newWidth) {
		MapView.width = newWidth;
	}
	/**
	 * returns the height of the container
	 * @return height
	 */
	public static int getMapHeight(){
		return height;
	}
	
	/**
	 * returns the width of the container
	 * @return width
	 */
	public static int getMapWidth() {
		return width;
	}
	
	/**
	 * sets the origin point of the container
	 * @param originPoint
	 */
	public void setMapOrigin (Point originPoint) {
		MapView.mapViewOrgn = originPoint;
	}
	public static Point getMapOrigin() {
		return MapView.mapViewOrgn;
	}
	/**
	 * The Observer is implemented in the class
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator iterator = collection.getIterator();
			
		
		while(iterator.hasNext()) {
			GameObject gObjChecker = iterator.getNext();	
			gObjChecker.draw(g, pCmpRelPrnt);
			
		}
		
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		int absX = x - getParent().getAbsoluteX();
		int absY = y - getParent().getAbsoluteY();
		Point originPoint = new Point(absX, absY);
		Point clickedPoint = new Point(getX(), getY());
		
		if(gameWorld.isPaused())
			gameWorld.clicked(clickedPoint, originPoint);
	}
}

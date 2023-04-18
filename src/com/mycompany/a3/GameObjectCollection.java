package com.mycompany.a3;

import java.util.ArrayList;

/**
 * The collection of games is implemented as well as the Iterator
 * @author kevinestrada
 *
 */
public class GameObjectCollection implements ICollection{
	
	private ArrayList <GameObject> collection;
	
	public GameObjectCollection() {
		collection = new ArrayList <GameObject>();
	}
	
	
	/**
	 * adds a new elements to the collection
	 */
	@Override
	public void add(GameObject newObj) {
		
		collection.add(newObj);
	}
	
	/**
	 * returns a new instance of GameObjectIterator
	 */
	@Override
	public IIterator getIterator() {
		return new GameObjectIterator();
	}

	// ================================= Private Class (Iterator)==================================
	private class GameObjectIterator implements IIterator{
		
		private int currElementIdx;
		
		
		public GameObjectIterator() {
			currElementIdx = -1;
		}
		
		/**
		 * Checks if the collection has a next element
		 */
		@Override
		public boolean hasNext() {
			if(collection.size() <= 0) {
				return false;
			}
			if(currElementIdx == collection.size() - 1) {
				return false;
			}
			return true;
		}
		/**
		 * checks gets the collections next element 
		 */
		@Override
		public GameObject getNext() {
			currElementIdx++;
			return collection.get(currElementIdx);
		}

		@Override
		public GameObject getIdx() {
			return collection.get(currElementIdx);
		}
		
	}

}

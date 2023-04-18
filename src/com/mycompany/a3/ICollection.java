package com.mycompany.a3;

/**
 * Interface has iterator and add method to be implemented
 * @author kevinestrada
 *
 */
public interface ICollection {
	
	public void add(GameObject newObject);
	public IIterator getIterator();
}

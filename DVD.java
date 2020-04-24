
/**
	Class DVD represents a DVD for sale at a book store.
	This version of the class is a subclass of class BookStoreItem
	and inherits variables and methods from it.
	Author: James Hembree
	E-mail address: jshembree88@gmail.com
	Last changed: April 24, 2020
*/

public class DVD extends BookStoreItem {
	
	public DVD (String title, String author, double price) {
		super(title, author, price);	
	}
	
	public DVD () {
		//call the other constructor and pass it a generic  title, author and price
		this("title", "author", 0.0);
	}
	
	/*
	* method toString() returns a String representation of this object
	*/
	public String toString() {
		String DVD = "DVD:\n" + super.toString();
						
		return DVD;
	}
		
}

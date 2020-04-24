
/**
	Class CD represents a CD for sale at a book store.
	This version of the class is a subclass of class BookStoreItem
	and inherits variables and methods from it. 
	Author: James Hembree
	E-mail address: jshembree88@gmail.com
	Last changed: April 24, 2020
*/

public class CD extends BookStoreItem {
	
	public CD (String title, String author, double price) { 
		super(title, author, price);		
	}
	
	public CD () {
		//call the other constructor and pass it a generic  title, author, price
		this("title", "author", 0.0);
	}
		
	/*
	* method toString() returns a String representation of this object
	*/
	public String toString() {
		String cd = "CD:\n" + super.toString();
		
		return cd;
	}
		
}

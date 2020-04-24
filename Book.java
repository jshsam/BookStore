
/**
	Class Book represents a book for sale at a book store.
	This version of the class is a subclass of class BookStoreItem
	and inherits variables and methods from it. 
	Author: James Hembree
	E-mail address: jshembree88@gmail.com
	Last changed: April 24, 2020
*/

public class Book extends BookStoreItem {
	
	public Book (String title, String author, double price) {
		super(title, author, price);
	}
	
	public Book () {
		//call the other constructor and pass it a generic  title, author and price
		this("title", "author", 0.0);
	}
	
	public String toString() {
		String book;
		book = "Book:\n" + super.toString();  
	
		return book;
	}
		
}

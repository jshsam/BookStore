/**
	Class BookStoreItem represents an item for sale at a book store.
	This version of the class is abstract. This is in order to
		- prevent BookStoreItem objects from being created. Instead, only
		  Book, CD and Tape objects can be created
	Author: James Hembree
	E-mail address: jshembree88@gmail.com
	Last changed: April 24, 2020
*/
import java.io.Serializable;
import java.text.NumberFormat;

public abstract class BookStoreItem implements Serializable {
	
	protected String title;
	protected String author;
	protected double price;
	
	
	public BookStoreItem (String title, String author, double price) {
		setTitle(title);
		setAuthor(author);
		setPrice(price);
	}
	
	public BookStoreItem () {
		//call the other constructor and pass it a generic  title, author and price
		this("title", "author", 0.0);
	}
	
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	
	public void setTitle(String title) {
		
		//trim the title parameter to remove any white space at the ends of the
		//String. Then test if the remaining String contains at least one 
		//character. If so, title is valid.
		if ((title.trim()).length() > 0) {
			this.title = title;
		}
	}
	
	public void setAuthor(String author) {
		if ((author.trim()).length() > 0) {
			this.author = author;
		}
	}
	
	public void setPrice(double price) {
		if (price >= 0) {
			this.price = price;
		}
	}
	
	/*
	* method toString() returns a String representation of this Book object
	*/
	public String toString() {
		final NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String item = "Title: " + this.title +
						"\nAuthor: " + this.author +
						"\nPrice: " + formatter.format(this.price);
		return item;
	}
	
	public boolean equals(Object obj) {
		boolean equalItems;
		BookStoreItem otherItem;
		
		equalItems = false;
		otherItem = null;
		
		if (obj == null) {
			throw new NullPointerException();
		}
		else if (this == obj) {
			equalItems = true;
		}
		else {
			if (obj instanceof BookStoreItem) {
				otherItem = (BookStoreItem)obj;
				if (this.title.equals(otherItem.title) &&
						this.author.equals(otherItem.author) &&
						Math.abs(this.price - otherItem.price) < 0.01) {
					equalItems = true;
				}
			}
		}
		
		return equalItems;		
	}		
}

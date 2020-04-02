/**
	Class CD represents a CD for sale at a book store.
	This version of the class is a subclass of class BookStoreItem
	and inherits variables and methods from it. This class overrides
	the getSize() method that it inherits from BookStoreItem. This
	class also declares one variable named playingTime (declared here because
	other items, e.g., a Book, sold at a book store do not have a playing time.
	The getSize() method returns this variable's value, since the size of
	a CD is its playing time.
	Author: James Hembree
	E-mail address: jhembree0023@kctcs.edu
	Last changed: February 19, 2020
	Lab 01
*/

public class CD extends BookStoreItem {
	
	private int minutes;
	private int seconds;
		
	/*
	* The constructor
	* Contains code that initially sets up each Book object when it is
	* first created. Always has the same name as the class.
	*/
	public CD (String title, String author, double price) { //, int minutes, int seconds) {
		super(title, author, price);
		// if (minutes > 0 && seconds >= 0) {
		// 	this.minutes = minutes;
		// 	this.seconds = seconds;
		// }		
	}
	
	public CD () {
		//call the other constructor and pass it a generic  title, author, price and playingTime
		this("title", "author", 0.0);
	}

	public int getMinutes() {
		return this.minutes;
	}

	public int getSeconds() {
		return this.seconds;
	}
	
	public String getSize() {
		String size;
		
		size = this.minutes + " minutes, " + this.seconds + " seconds";
		
		return size;
	}
		
	/*
	* method toString() returns a String representation of this object
	*/
	public String toString() {
		String cd = "CD:\n" + super.toString();
						//"\nPlaying time: " + this.minutes + " minutes, " + this.seconds + " seconds";
		return cd;
	}
	
	public boolean equals(Object obj) {
		boolean equalCDs;
			
		equalCDs = super.equals(obj);
		
		if (equalCDs) {
			if (obj instanceof CD) {
				if (this.minutes != ((CD)obj).minutes || this.seconds != ((CD)obj).seconds) {
					equalCDs = false;
				}
			}
		}
		
		return equalCDs;
	}
		
}
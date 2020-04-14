

public class Book extends BookStoreItem {
	
	private int pages;
	
	public Book (String title, String author, double price) { //, int pages) {
		super(title, author, price);
	}
	
	public Book () {
		//call the other constructor and pass it a generic title, author and price
		this("title", "author", 0.0);
	}

	public int getPages() {
		return this.pages;
	}
	
	public String getSize() {
		String size;
		
		size = this.pages + " pages";
		
		return size;
	}
	
	public String toString() {
		String book;
		book = "Book:\n" + super.toString();  
		return book;
	}
	
	public boolean equals(Object obj) {
		boolean equalBooks;
			
		equalBooks = super.equals(obj);
		
		if (equalBooks) {
			if (obj instanceof Book) {
				if (this.pages != ((Book)obj).pages) {
					equalBooks = false;
				}
			}
		}
		
		return equalBooks;
	}
		
}

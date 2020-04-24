/**
	Class Catalog defines a catalog for a book store. An object of this class provides 
	the ability to
		- add BookStoreItems to the catalog
		- search for BookStoreItems in the catalog
		- save Catalog inventory to file
		- load Catalog inventory
		
	Author: James Hembree
	E-mail address: jshembree88@gmail.com
	Last changed: April 24, 2020
*/

import java.io.*;
import java.lang.ClassNotFoundException;

public class Catalog {
	
	private BookStoreItem[] inventory;
	private final int CAPACITY = 3;
	private final int BOOK_INT = 0;
	private final int CD_INT = 1;
	private final int DVD_INT = 2;
	private int count;
	
	public Catalog() {
		inventory = new BookStoreItem[CAPACITY];
		count = 0;
	}
	
	public void add(BookStoreItem newItem) {
		inventory[count] = newItem;
		count++;
	}
	
	public boolean isAvailable(String title) {
		
		boolean found = false;
		
		for (int i = 0;i < count && !found;i++) {
			if (title.equals(inventory[i].getTitle())) {
				found = true;
			}
		}
		
		return found;
	}
	
	public BookStoreItem getItem(String title) {
		
		BookStoreItem desiredItem = null;
		
		boolean found = false;
		
		for (int i = 0;i < count && !found;i++) {
			if (title.equals(inventory[i].getTitle())) {
				desiredItem = inventory[i];
				found = true;
			}
		}
		
		return desiredItem;
		
	}
	
	public BookStoreItem[] getList() {
		return inventory;
	}

	public void save(File file) throws FileNotFoundException, IOException {
		ObjectOutputStream outputStream;

		outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

		outputStream.writeInt(count);

		for (int i = 0;i < count;i++) {
			if (inventory[i] instanceof Book) {
				outputStream.writeInt(BOOK_INT);
				outputStream.writeObject(inventory[i]);
			}
			else if (inventory[i] instanceof CD) {
				outputStream.writeInt(CD_INT);
				outputStream.writeObject(inventory[i]);
			}
			else if (inventory[i] instanceof DVD) {
				outputStream.writeInt(DVD_INT);
				outputStream.writeObject(inventory[i]);
			}
		}
		if (outputStream != null) {
			outputStream.close();
		}
	}

	public void load(File file) throws ClassNotFoundException, FileNotFoundException, IOException {
		ObjectInputStream inputStream;
		Object nextObject;
		int itemType;

		for (int i = 0;i < inventory.length;i++) {
			inventory[i] = null;
		}

		inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));

		if (file.exists()) {
			count = inputStream.readInt();

			for (int i = 0;i < count;i++) {

				itemType = inputStream.readInt();
				nextObject = inputStream.readObject();

				switch (itemType) {
					case BOOK_INT:
						inventory[i] = (Book)nextObject;
						break;
					case CD_INT:
						inventory[i] = (CD)nextObject;
						break;
					case DVD_INT:
						inventory[i] = (DVD)nextObject;
						break;
				}
			}
		}
	
		if (inputStream != null) {
			inputStream.close();
		}
	}	
}		

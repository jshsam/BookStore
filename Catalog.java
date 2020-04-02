/**
	Class Catalog defines a catalog for a book store. An object of this class provides 
	the ability to
		- add BookStoreItems to the catalog
		- search for BookStoreItems in the catalog
		- save Catalog inventory to file
		- load Catalog inventory
		
	Author: James Hembree
	E-mail address: jhembree0023@kctcs.edu
	Last changed: February 19, 2020
	Lab 01
*/

import java.io.*;
import java.util.Scanner;

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

	public void save() throws IOException {
		PrintWriter out = null;

		out = new PrintWriter(new BufferedWriter(new FileWriter("catalog.txt")));

		for (int i = 0;i < count;i++) {
			if (inventory[i] instanceof Book) {
				out.println(BOOK_INT + ";" + inventory[i].getTitle() + ";" + inventory[i].getAuthor() + ";" +
								inventory[i].getPrice());
			}
			else if (inventory[i] instanceof CD) {
				out.println(CD_INT + ";" + inventory[i].getTitle() + ";" + inventory[i].getAuthor() + ";" +
								inventory[i].getPrice());
			}
			else if (inventory[i] instanceof DVD) {
				out.println(DVD_INT + ";" + inventory[i].getTitle() + ";" + inventory[i].getAuthor() + ";" +
								inventory[i].getPrice());
			}
		}
	
		out.close();
	}

	public void load() throws FileNotFoundException {
		Scanner fileScanner;
		File catalogFile;
		String line;
		int itemType;
		BookStoreItem newItem;
		String[] obj;
		double price;
		int size1;
		int size2;

		newItem = null;
		fileScanner = null;
		catalogFile = new File("catalog.txt");

		if (catalogFile.exists()) {
			fileScanner = new Scanner(catalogFile);
			count = 0;

			while (fileScanner.hasNextLine()) {
				line = fileScanner.nextLine();
				
				obj = line.split(";");

				itemType = Integer.parseInt(obj[0]);
				price = Double.parseDouble(obj[3]);

				switch (itemType) {
					case 0:
						newItem = new Book(obj[1], obj[2], price);
						break;
					case 1:
						newItem = new CD(obj[1], obj[2], price);
						break;
					case 2:
						newItem = new DVD(obj[1], obj[2], price);
						break;
				}
				
				add(newItem);
				
			}
		}
	
		if (fileScanner != null) {
			fileScanner.close();
		}

	}
	
}
		
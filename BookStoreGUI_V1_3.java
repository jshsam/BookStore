/**
	Class BookStoreGUI version 1.2 represents a book store.
	It gives the user the option to
		- add a bookstore item to the store's inventory
		- list all bookstore items in the store's inventory
		- search for a specific bookstore item
		
	Author: James Hembree
	E-mail address: jhembree0023@kctcs.edu
	Last changed: March 13, 2020
	Lab 02
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BookStoreGUI_V1_3 extends Application {

	private Label titleLabel;
	private Label authorLabel;
	private Label priceLabel;
	private TextField titleTextField;
	private TextField authorTextField;
	private TextField priceTextField;
	private TextArea outputTextArea;
	private RadioButton bookRadioButton;
	private RadioButton cdRadioButton;
	private RadioButton dvdRadioButton;
	private ToggleGroup itemTypeGroup;
	private Button loadButton;
	private Button saveButton;
	private Button addButton;
	private Button displayButton;
	private GridPane inputGridPane;
	private HBox buttonsHBox;
	private BorderPane mainPane;
	private Scene scene;
	

	static Catalog storeCatalog = new Catalog();
	static int count = 0;

	public void start(Stage primaryStage){

		createLabels();
		createTextFields();
		createItemTypeButtons();
		createOperationButtons();
		outputTextArea = new TextArea();
		createAndRegisterToggleGroup();

		loadButton.setOnAction(ae -> {
			load();
		});
		saveButton.setOnAction(ae -> {
			save();
		});
		addButton.setOnAction(ae -> {
			add();
		});
		displayButton.setOnAction(ae -> {
			display();
		});

		createInputGridPane();
		createButtonsHBox();
		createMainPane();
			
		scene = new Scene(mainPane, 500, 500);
		primaryStage.setTitle("BookStoreGUI");
		primaryStage.setScene(scene);
		primaryStage.show();
	}//end of start

	public void createLabels() {
		titleLabel = new Label("Title:");
		authorLabel = new Label("Author:");
		priceLabel = new Label("Price:");
	}

	public void createTextFields() {
		titleTextField = new TextField();
		authorTextField = new TextField();
		priceTextField = new TextField();
	}

	public void createItemTypeButtons() {
		bookRadioButton = new RadioButton("Book");
		cdRadioButton = new RadioButton("CD");
		dvdRadioButton = new RadioButton("DVD");
	}

	public void createOperationButtons() {
		loadButton = new Button("_Load Catalog");
		saveButton = new Button("_Save Catalog");
		addButton = new Button("_Add Item");
		displayButton = new Button("_Display Inventory");
	}

	public void createAndRegisterToggleGroup() {
		itemTypeGroup = new ToggleGroup();
		bookRadioButton.setToggleGroup(itemTypeGroup);
		cdRadioButton.setToggleGroup(itemTypeGroup);
		dvdRadioButton.setToggleGroup(itemTypeGroup);
	}

	public void createInputGridPane() {
		inputGridPane = new GridPane();
		inputGridPane.setHgap(10);
		inputGridPane.setVgap(10);
		inputGridPane.add(bookRadioButton, 5, 0);
		inputGridPane.add(cdRadioButton, 5, 1);
		inputGridPane.add(dvdRadioButton, 5, 2);
		inputGridPane.add(titleLabel, 15, 0);
		inputGridPane.add(titleTextField, 16, 0);
		inputGridPane.add(authorLabel, 15, 1);
		inputGridPane.add(authorTextField, 16, 1);
		inputGridPane.add(priceLabel, 15, 2);
		inputGridPane.add(priceTextField, 16, 2);
	}

	public void createButtonsHBox() {
		buttonsHBox = new HBox(10);
		buttonsHBox.setAlignment(Pos.CENTER);
		buttonsHBox.getChildren().add(loadButton);
		buttonsHBox.getChildren().add(saveButton);
		buttonsHBox.getChildren().add(addButton);
		buttonsHBox.getChildren().add(displayButton);
	}

	public void createMainPane() {
		mainPane = new BorderPane();
		mainPane.setTop(inputGridPane);
		mainPane.setCenter(outputTextArea);
		mainPane.setBottom(buttonsHBox);
	}

	private void load() {
		boolean isOpened = false;
		count = 0;

		try {
			storeCatalog.load();
			isOpened = true;
			for (BookStoreItem item : storeCatalog.getList()) {
				if (item != null) {
					count++; 
				}
			}
		}
		catch (FileNotFoundException fnfe) {
			outputTextArea.appendText("\nCould not load file! File not found!\n");
		}
		finally {
			if (isOpened) {
				outputTextArea.appendText("\nCatalog Loaded\n");
			}
		}
	}

	private void save() {
		boolean isSaved = false;

		try {
			storeCatalog.save();
			isSaved = true;
		}
		catch (IOException ioe) {
			outputTextArea.appendText("\nCould not write to file!\n");
		}
		finally {
			if (isSaved) {
				outputTextArea.appendText("\nCatalog Saved to file!\n");
			}
		}
	}

	private void add() {
		BookStoreItem newItem = null;
		String title;
		String author;
		double price;

		title = titleTextField.getText().trim();
		author = authorTextField.getText().trim();
		price = Double.parseDouble(priceTextField.getText().trim());

		

		if (count != storeCatalog.getList().length) {

			if (bookRadioButton.isSelected()) {
				newItem = new Book(title, author, price);
			}
			else if (cdRadioButton.isSelected()) {
				newItem = new CD(title, author, price);
			}
			else if (dvdRadioButton.isSelected()) {
				newItem = new DVD(title, author, price);
			}

			storeCatalog.add(newItem);
			count++;

			outputTextArea.appendText("\nItem was added successfully\n");
		}
		else {
			outputTextArea.appendText("\nInventory is full! Cannot add another item.\n");
		}
	}

	private void display() {
		BookStoreItem[] list;

		int itemNumber;

		list = storeCatalog.getList();

		itemNumber = 1;

		for (BookStoreItem item : list) {
			if (item != null) {
				outputTextArea.appendText("\nItem " +  itemNumber + ":\n" + item  + "\n");
				itemNumber++;
			}
		}
	}

}//end of class BookStoreGUI


	
	

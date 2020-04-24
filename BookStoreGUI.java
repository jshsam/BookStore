/**
	Class BookStoreGUI represents a book store.
	It gives the user the option to
		- add a bookstore item to the store's inventory
		- list all bookstore items in the store's inventory
		- search for a specific bookstore item
		- Save inventory
		- Load inventory
		- display Books, CDs, or DVDs in a separate tab
	Author: James Hembree
	E-mail address: jshembree88@gmail.com
	Last changed: April 24, 2020
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
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.SplitPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCombination;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.lang.ClassNotFoundException;
import java.util.Optional;

public class BookStoreGUI extends Application {

	private Label titleLabel;
	private Label authorLabel;
	private Label priceLabel;
	private TextField titleTextField;
	private TextField authorTextField;
	private TextField priceTextField;
	private TextArea outputTextArea;
	private TextArea booksTextArea;
	private TextArea cdsTextArea;
	private TextArea dvdsTextArea;
	private RadioButton bookRadioButton;
	private RadioButton cdRadioButton;
	private RadioButton dvdRadioButton;
	private ToggleGroup itemTypeGroup;
	private Button clearInputButton;
	private Button clearOutputButton;
	private Button exitButton;
	private MenuBar menuBar;
	private Menu catalogMenu;
	private Menu inventoryMenu;
	private Menu displayMenu;
	private MenuItem loadMenuItem;
	private MenuItem saveMenuItem;
	private MenuItem addItemMenuItem;
	private MenuItem displayMenuItem;
	private MenuItem searchMenuItem;
	private MenuItem booksMenuItem;
	private MenuItem cdsMenuItem;
	private MenuItem dvdsMenuItem;
	private Tab mainTab;
	private Tab booksTab;
	private Tab cdsTab;
	private Tab dvdsTab;
	private TabPane tabPane;
	private GridPane inputGridPane;
	private SplitPane bookStoreSplitPane;
	private BorderPane booksPane;
	private BorderPane cdsPane;
	private BorderPane dvdsPane;
	private VBox buttonsVBox;
	private BorderPane leftPane;
	private BorderPane mainPane;
	private Scene scene;
	private FileChooser fileChooser;
	

	static Catalog storeCatalog = new Catalog();
	static int count = 0;

	public void start(Stage primaryStage){

		createLabels();
		createTextFields();
		createItemTypeButtons();
		createOperationButtons();
		createTextArea();
		clearOutput();
		createAndRegisterToggleGroup();
		setUpMenu();

		loadMenuItem.setOnAction(ae -> {
			load(primaryStage);
		});
		saveMenuItem.setOnAction(ae -> {
			save(primaryStage);
		});
		addItemMenuItem.setOnAction(ae -> {
			add();
		});
		displayMenuItem.setOnAction(ae -> {
			display();
		});
		searchMenuItem.setOnAction(ae -> {
			search();
		});
		booksMenuItem.setOnAction(ae -> {
			createBooksTab();
		});
		cdsMenuItem.setOnAction(ae -> {
			createCdsTab();
		});
		dvdsMenuItem.setOnAction(ae -> {
			createDvdsTab();
		});
		bookRadioButton.setOnAction(ae -> {
			clearTextBoxes();
		});
		cdRadioButton.setOnAction(ae -> {
			clearTextBoxes();
		});
		dvdRadioButton.setOnAction(ae -> {
			clearTextBoxes();
		});
		clearInputButton.setOnAction(ae -> {
			clearInput();
		});
		clearOutputButton.setOnAction(ae -> {
			clearOutput();
		});
		exitButton.setOnAction(ae -> {
			System.exit(0);
		});

		createSplitPane();
		createInputGridPane();
		createButtonsVBox();
		createLeftPane();
		createTabPane();
		createMainPane();
		addPanes();
			
		scene = new Scene(mainPane, 600, 400);
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
		clearInputButton = new Button("Clear Input");
		clearOutputButton = new Button("Clear Output");
		exitButton = new Button("Exit");
	}

	public void createTextArea() {
		outputTextArea = new TextArea();
		outputTextArea.setEditable(false);
	}

	public void createAndRegisterToggleGroup() {
		itemTypeGroup = new ToggleGroup();
		bookRadioButton.setToggleGroup(itemTypeGroup);
		cdRadioButton.setToggleGroup(itemTypeGroup);
		dvdRadioButton.setToggleGroup(itemTypeGroup);
	}

	public void setUpMenu() {
		menuBar = new MenuBar();

		catalogMenu = new Menu("_Catalog");
		loadMenuItem = new MenuItem("Load");
		saveMenuItem = new MenuItem("Save");
		addItemMenuItem = new MenuItem("Add Item");

		loadMenuItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT + L"));
		saveMenuItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT + S"));
		addItemMenuItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT + A"));

		inventoryMenu = new Menu("_Inventory");
		displayMenuItem = new MenuItem("Display");
		searchMenuItem = new MenuItem("Search");

		displayMenuItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT + D"));
		searchMenuItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT + E"));

		displayMenu = new Menu("_Display");
		booksMenuItem = new MenuItem("Books");
		cdsMenuItem = new MenuItem("CDs");
		dvdsMenuItem = new MenuItem("DVDs");

		booksMenuItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT + B"));
		cdsMenuItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT + C"));
		dvdsMenuItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT + V"));

		catalogMenu.getItems().add(loadMenuItem);
		catalogMenu.getItems().add(saveMenuItem);
		catalogMenu.getItems().add(addItemMenuItem);

		inventoryMenu.getItems().add(displayMenuItem);
		inventoryMenu.getItems().add(searchMenuItem);

		displayMenu.getItems().add(booksMenuItem);
		displayMenu.getItems().add(cdsMenuItem);
		displayMenu.getItems().add(dvdsMenuItem);

		menuBar.getMenus().add(catalogMenu);
		menuBar.getMenus().add(inventoryMenu);
		menuBar.getMenus().add(displayMenu);
	}

	public void createSplitPane() {
		bookStoreSplitPane = new SplitPane();
		bookStoreSplitPane.setOrientation(Orientation.HORIZONTAL);
		bookStoreSplitPane.setDividerPosition(0, 0.75);
	}

	public void createInputGridPane() {
		inputGridPane = new GridPane();
		inputGridPane.setHgap(10);
		inputGridPane.setVgap(15);
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

	public void createButtonsVBox() {
		buttonsVBox = new VBox(35);
		buttonsVBox.setAlignment(Pos.CENTER);
		buttonsVBox.getChildren().add(clearInputButton);
		buttonsVBox.getChildren().add(clearOutputButton);
		buttonsVBox.getChildren().add(exitButton);
	}

	public void createLeftPane() {
		leftPane = new BorderPane();
		leftPane.setTop(inputGridPane);
		leftPane.setCenter(outputTextArea);
	}

	public void createTabPane() {
		tabPane = new TabPane();
		mainTab = new Tab("Store Catalog");
		mainTab.setClosable(false);
		mainTab.setContent(leftPane);
		tabPane.getTabs().add(mainTab);
	}

	public void createMainPane() {
		mainPane = new BorderPane();
		mainPane.setTop(menuBar);
		mainPane.setCenter(bookStoreSplitPane);
	}

	public void addPanes() {
		bookStoreSplitPane.getItems().add(tabPane);
		bookStoreSplitPane.getItems().add(buttonsVBox);
	}

	public void createBooksTab() {
		booksTab = new Tab("Books");
		booksPane = new BorderPane();
		booksTextArea = new TextArea();
		booksTextArea.setEditable(false);

		for (BookStoreItem item : storeCatalog.getList()) {
			if (item instanceof Book) {
				booksTextArea.appendText("\n" + item + "\n");
			}
		};

		booksPane.setCenter(booksTextArea);
		booksTab.setContent(booksPane);
		tabPane.getTabs().add(booksTab);
		tabPane.getSelectionModel().select(1);
	}

	public void createCdsTab() {
		cdsTab = new Tab("CDs");
		cdsPane = new BorderPane();
		cdsTextArea = new TextArea();
		cdsTextArea.setEditable(false);

		for (BookStoreItem item : storeCatalog.getList()) {
			if (item instanceof CD) {
				cdsTextArea.appendText("\n" + item + "\n");
			}
		};

		cdsPane.setCenter(cdsTextArea);
		cdsTab.setContent(cdsPane);
		tabPane.getTabs().add(cdsTab);
		tabPane.getSelectionModel().select(2);
	}

	public void createDvdsTab() {
		dvdsTab = new Tab("DVDs");
		dvdsPane = new BorderPane();
		dvdsTextArea = new TextArea();
		dvdsTextArea.setEditable(false);

		for (BookStoreItem item : storeCatalog.getList()) {
			if (item instanceof DVD) {
				dvdsTextArea.appendText("\n" + item + "\n");
			}
		};

		dvdsPane.setCenter(dvdsTextArea);
		dvdsTab.setContent(dvdsPane);
		tabPane.getTabs().add(dvdsTab);
		tabPane.getSelectionModel().select(3);
	}

	private void load(Stage primaryStage) {
		File inputFile;
		boolean isOpened = false;
		count = 0;

		inputFile = null;

		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DAT", "*.dat"));
		fileChooser.setTitle("Open");

		inputFile = fileChooser.showOpenDialog(primaryStage);

		if (inputFile != null) {
			try {
				storeCatalog.load(inputFile);
				isOpened = true;
				for (BookStoreItem item : storeCatalog.getList()) {
					if (item != null) {
						count++; 
					}
				}
			}
			catch (ClassNotFoundException cnfe) {
				outputTextArea.appendText("\nError: No Bookstore Items in the catalog file!\n");
			}
			catch (FileNotFoundException fnfe) {
				outputTextArea.appendText("\nCould not load file! File not found!\n");
			}
			catch (IOException ioe) {
				outputTextArea.appendText("\nCould not read from file!\n");
			}
			finally {
				if (isOpened) {
					outputTextArea.appendText("\nCatalog Loaded\n");
				}
			}
		}
	}

	private void save(Stage primaryStage) {
		boolean isSaved = false;
		File outputFile;

		outputFile = null;

		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DAT", "*.dat"));
		fileChooser.setTitle("Save as");

		outputFile = fileChooser.showSaveDialog(primaryStage);

		if (outputFile != null) {
			try {
				storeCatalog.save(outputFile);
				isSaved = true;
			}
			catch (FileNotFoundException fnfe) {
				outputTextArea.appendText("\nCould not find output file\n");
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

	private void search() {
		TextInputDialog searchDialog = new TextInputDialog("Title");
		searchDialog.setTitle("Search Dialog");
		searchDialog.setHeaderText("Search Inventory");
		searchDialog.setContentText("Please enter the title you wish to search:");

		Optional<String> result = searchDialog.showAndWait();
		result.ifPresent(title -> {
			BookStoreItem item = storeCatalog.getItem(title);
			if (item != null) {
				outputTextArea.appendText("\nFound item!\n\n" + item + "\n");
			}
			else {
				outputTextArea.appendText("\nCould not find " + title +"!");
			}
		});
		tabPane.getSelectionModel().select(0);
	}

	private void clearTextBoxes() {
		titleTextField.setText("");
		authorTextField.setText("");
		priceTextField.setText("");
	}

	private void clearInput() {
		bookRadioButton.setSelected(false);
		cdRadioButton.setSelected(false);
		dvdRadioButton.setSelected(false);
		titleTextField.setText("");
		authorTextField.setText("");
		priceTextField.setText("");
	}

	private void clearOutput() {
		outputTextArea.setText("------------------------ Welcome to the BookStore -------------------------\n");
	}

}//end of class BookStoreGUI

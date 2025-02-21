package org.openjfx.fx;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.openfx.handlers.SettingTabEventHandler;
import com.openfx.handlers.NewMenuItemEventHandler;
import com.openfx.handlers.SearchToolEventHandler;
import com.openfx.handlers.SqlQueryRunButtonSubmit;
import com.openfx.placeholders.ConnectionFileRelationHolder;
import com.openfx.placeholders.ConnectionPlaceHolder;
import com.openfx.placeholders.ImageItemsHolder;
import com.openfx.placeholders.SQLCellTextArea;
import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Menu_Items_FX extends Application {

	public NewMenuItemEventHandler newMenuItemEventHandler;
	public SettingTabEventHandler settingTabEventHandler;
	public SqlQueryRunButtonSubmit sqlQueryRunButtonSubmit;
	public Stage primaryStage;
	
	public Scene scene;

	public VBox rootPane;
	
	int sqlEditerCount=1;
	
	public Scene sceneDataBaseConnection;
	public Scene sceneForSettings;
	public BorderPane borderSelectDatabase ;
	public FlowPane selectDatabaseConnectionsflow;
	public FlowPane openConnectionsFlowPane;
	
	public boolean connectionDoubleClicked = false;
	
	public String currentConnectionSelected;
	public HashMap<ConnectionPlaceHolder,Connection> currentOpenConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> mySqlConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> postgreeSqlConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> sqliteConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> saphanarMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> databricksrMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> oracleConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> msSqlServerMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> duckDBConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> mariaDBConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	
	
	public static TreeItem<String> rootConnectionItem;
	public static TreeView<String> treeConnectionsView;
	
	public TabPane alltabbedEditors;
	public SplitPane editerTabSplitPane;
	public GridPane vBoxAreaSqlEditer1GridPane;
	public StackPane stackPaneAreaButtons;
	public HBox hboxQueryAreaButtons;
	public SQLCellTextArea sqlCellTextArea;
	public BorderPane plus_button_borderPane;
	public Button addNewQueryEditerButton;
	public TabPane sqlEditerResultTabPane;
	public Tab sqlEditerResultTab;
	public Tab sqlEditerConsoleTab;
	public TextArea sqlEditerTextAreaResult;
	public Tab sqlEditerTab;
	public ScrollPane sqlEditerscrollPane;

	public HashMap<File,String> openedFilesMap = new HashMap< File,String>();
	
	public TextArea foucesSqlEditerTextArea;
	public Button toolBarRunButton;

	public Dimension size ;
	
	public ListView<String> connectedDatabasesNames = new ListView<String>();
	public ListView<String> loadedTablesNames = new ListView<String>();
	public ListView<String> selectedTablesNames = new ListView<String>();
	public Tab dataSearchtabPane;
	
	public String whiteThemeCss = Menu_Items_FX.class.getResource("/whiteTheme.css").toExternalForm();
	public String darkThemeCss = Menu_Items_FX.class.getResource("/darkTheme.css").toExternalForm();
	
	public String selectedTheme = whiteThemeCss;
	
	public MenuBar createMenuBar() {
		
		for(Map.Entry<File,String> openedFileMap :  openedFilesMap.entrySet()) {
		
			openedFileMap.getKey();
			openedFileMap.getValue();
		}
		
		size = Toolkit.getDefaultToolkit().getScreenSize();
		
		MenuBar menuBar = new MenuBar();
		menuBar.setId("mainMenuBar");
		Menu fileMenu = new Menu();
		fileMenu.setText("File");
		Menu editMenu = new Menu();
		editMenu.setText("Edit");
		Menu databaseMenu = new Menu();
		databaseMenu.setText("Database");
		Menu toolsMenu = new Menu();  // SQL Editer in eclipse/DBeaver
		toolsMenu.setText("Tools");
		Menu viewMenu = new Menu();  // Window in eclipse/DBeaver
		viewMenu.setText("View");  
		Menu helpMenu = new Menu();  // SQL Editer in eclipse/DBeaver
		helpMenu.setText("Help"); 
		Menu windowMenu = new Menu();  // SQL Editer in eclipse/DBeaver
		windowMenu.setText("Window"); 
		
		// File Menu subitems
		MenuItem newMenuItem = new MenuItem          ("New										Ctrl+N");
		MenuItem openMenuItem = new MenuItem         ("Open									Ctrl+O");
		MenuItem saveFileMenuItem = new MenuItem     ("Save   									Ctrl+S");
		MenuItem saveAsFileMenuItem = new MenuItem   ("Save As							   	 Ctrl+Alt+S");
		MenuItem renameFileMenuItem = new MenuItem   ("Rename									Ctrl+R");
		MenuItem settingFileMenuItem = new MenuItem   ("Settings						          ");
		fileMenu.getItems().add(newMenuItem);
		fileMenu.getItems().add(openMenuItem);
		fileMenu.getItems().add(saveFileMenuItem);
		fileMenu.getItems().add(saveAsFileMenuItem);
		fileMenu.getItems().add(renameFileMenuItem);
		fileMenu.getItems().add(settingFileMenuItem);
		MenuItem closeFileMenuItem = new MenuItem    ("Close								       Ctrl+W");
		MenuItem closeAllFileMenuItem = new MenuItem ("Close All 							      Ctrl+Shft+W");
		SeparatorMenuItem fileSep1 = new SeparatorMenuItem();
		fileMenu.getItems().add(fileSep1);
		fileMenu.getItems().add(closeFileMenuItem);
		fileMenu.getItems().add(closeAllFileMenuItem);
		closeFileMenuItem.setOnAction(closeFileMenuItemEventHandler());
		closeAllFileMenuItem.setOnAction(closeAllFileMenuItemEventHandler());
		
		MenuItem printFileMenuItem = new MenuItem    ("Print										Ctrl+P");
		SeparatorMenuItem fileSep2 = new SeparatorMenuItem();
		fileMenu.getItems().add(fileSep2);
		fileMenu.getItems().add(printFileMenuItem);
		MenuItem recentFilesFileMenuItem = new MenuItem ("Recent 							      ");
		SeparatorMenuItem fileSep3 = new SeparatorMenuItem();
		fileMenu.getItems().add(fileSep3);
		fileMenu.getItems().add(recentFilesFileMenuItem);
		SeparatorMenuItem fileSep4 = new SeparatorMenuItem();
		fileMenu.getItems().add(fileSep4);
		MenuItem exitAppFileMenuItem = new MenuItem ("Exit										Alt+F4");
		fileMenu.getItems().add(exitAppFileMenuItem);

		
		newMenuItem.setOnAction(newFileMenuItemAction());
		openMenuItem.setOnAction(openFileMenuItemAction());
		saveFileMenuItem.setOnAction(saveFileMenuItemAction());
		saveAsFileMenuItem.setOnAction(saveAsFileMenuItemAction());
		renameFileMenuItem.setOnAction(renameFileEventHandler());
		settingFileMenuItem.setOnAction(new SettingTabEventHandler(this));
		
		//Database Menu subitems
		MenuItem newDatabseConnectionItem = new MenuItem("New Database Connection                  CTR+X");
		
		MenuItem connectToDatabaseConnectionItem = new MenuItem("Connect to a Database");
		MenuItem disconnectFromDatabaseConnectionItem = new MenuItem("Disconnect from a Database");
		MenuItem exitDatabaseConnectionItem = new MenuItem("Exit");
		databaseMenu.getItems().add(newDatabseConnectionItem);
		databaseMenu.getItems().add(connectToDatabaseConnectionItem);
		databaseMenu.getItems().add(disconnectFromDatabaseConnectionItem);
		databaseMenu.getItems().add(exitDatabaseConnectionItem);
		//Creating separator menu items
	    SeparatorMenuItem databaseSep1 = new SeparatorMenuItem();
	    //Adding separator objects to menu
	    databaseMenu.getItems().add(1, databaseSep1);
	    SeparatorMenuItem databaseSep2 = new SeparatorMenuItem();
	    databaseMenu.getItems().add(4, databaseSep2);
		
		newDatabseConnectionItem.setOnAction(new NewMenuItemEventHandler(this));
		
		
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(editMenu);
		menuBar.getMenus().add(databaseMenu);
		menuBar.getMenus().add(toolsMenu);
		menuBar.getMenus().add(viewMenu);
		menuBar.getMenus().add(helpMenu);
		menuBar.getMenus().add(windowMenu);
		
		return menuBar;
		
	}
    
	
//	private EventHandler<ActionEvent> settingFileMenuItem() {
//	    return new EventHandler<ActionEvent>() {
//	        @Override
//	        public void handle(ActionEvent event) {
//	            // Create a new instance of SettingTabEventHandler
//	            SettingTabEventHandler settingTab = new SettingTabEventHandler();
//
//	            // Create a new Stage for the settings window
//	            Stage settingsStage = new Stage();
//
//	            // Call the handle method of SettingTabEventHandler
//	            settingTab.settingsStage = settingsStage; // Pass the stage to the handler
//	            settingTab.handle(event); // Call the handler to display the settings UI
//	        }
//	    };
//	}

	private EventHandler<ActionEvent> newFileMenuItemAction() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {		
				
				createSQLEditer(null,null);
				
			}	
		};
	}

	private EventHandler<ActionEvent> newSQLEditerTabCreationAction(TreeItem<String> connectionTreeItem) {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {		
				
				createSQLEditer(connectionTreeItem,null);
					
			}	
		};
	}
	
	public static void main(String[] args) {
	
		
		//System.out.println("My Menu program");
		//launch(args);
		// https://www.codecentric.de/wissens-hub/blog/javafx-how-to-easily-implement-application-preloader-2/]
		LauncherImpl.launchApplication(Menu_Items_FX.class, StarterPreloader.class, args);
	}

	@Override
	public void init() throws Exception {
		
		// Load anything before the preparation of application start 
	    
	    System.out.println("Application Loading....");
	    
	    Thread.sleep(2000);
	}
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		
		
		this.primaryStage = primaryStage;
		size = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(size);

		
		Image imagenewDBConnection = new Image(getClass().getResourceAsStream("/graphics/newdatabaseconnection.png"));
		ImageView imagenewDBConnectionImageView = new ImageView(imagenewDBConnection);
		imagenewDBConnectionImageView.setFitHeight(15);
		imagenewDBConnectionImageView.setFitWidth(15);
		imagenewDBConnectionImageView.setPreserveRatio(true);
		
		rootPane = new VBox();
		rootPane.getChildren().add(createMenuBar());
		
		ToolBar toolBar = new ToolBar();
	//	toolBar.setStyle("-fx-background-color: DAE6F3;");
		toolBar.setId("maintoolBar");
		Button connectDB = new Button();
		//connectDB.setText("DB");
		connectDB.setGraphic(imagenewDBConnectionImageView);
		connectDB.setOnAction(new NewMenuItemEventHandler(this));
		//toolBarButton1.setGraphic(new ImageView(imageOk));
		toolBar.getItems().add(connectDB);
		toolBar.getItems().add(new Separator());
		Button toolBarSearch = new Button("Search");
		toolBarSearch.setOnAction(new SearchToolEventHandler(this));
		toolBar.getItems().add(toolBarSearch);
		toolBar.getItems().add(new Separator());
		toolBarRunButton = new Button("Run");
		 
		toolBarRunButton.setOnAction(new SqlQueryRunButtonSubmit(this));
		toolBar.getItems().add(toolBarRunButton);
//		
//		Button toolTheme = new Button("Theme");
//		toolTheme.setOnAction( new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				scene.getStylesheets().add(darkThemeCss);
//				sceneDataBaseConnection.getStylesheets().add(darkThemeCss);
//				
//			}
//		});
//		toolBar.getItems().add(toolTheme);
		
		
		rootPane.getChildren().add(toolBar);

		
		HBox contentHBox = new HBox();
		contentHBox.setMaxWidth(size.getWidth());
						
		SplitPane mainSplitPane = new SplitPane();
		mainSplitPane.setDividerPositions(0.21);
		mainSplitPane.setId("SplitPane");
		VBox vBoxleft  = new VBox();
		ScrollPane vBoxleftScrollPane = new ScrollPane();
		vBoxleftScrollPane.setFitToHeight(true);
		vBoxleftScrollPane.setFitToWidth(true);
		vBoxleftScrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		vBoxleftScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		vBoxleftScrollPane.setId("ScrollPane"); 
		
	    VBox vBoxright = new VBox();
	    vBoxright.setId("vBoxright");
	    TabPane vBoxleftTabPane = new TabPane();
	    vBoxleftTabPane.setId("vBoxleftTabPane");
	    Tab connectionExplorerTab = new Tab("Connection Explorer");
	    connectionExplorerTab.setId("connectionExplorerTab");
	    
	    Tab projectExplorerTab = new Tab("Project Explorer");
	   
		rootConnectionItem = new TreeItem<String>("Connections");
		treeConnectionsView = new TreeView<String>();
		treeConnectionsView.setId("treeConnectionsView");
		treeConnectionsView.setRoot(rootConnectionItem);
		treeConnectionsView.setShowRoot(false);
		//treeView.setMinSize(300, size.getHeight()-120);
//		treeConnectionsView.setPrefWidth(300);
//		treeConnectionsView.setMinWidth(100);
		treeConnectionsView.setMinHeight(size.getHeight()-160);	
		treeConnectionsView.setContextMenu(null);
		
		this.treeConnectionsView.getSelectionModel().selectedItemProperty().addListener(treeViewChangeListener());
		
		connectionExplorerTab.setContent(this.treeConnectionsView);
		
		vBoxleftScrollPane.setContent(vBoxleftTabPane);
		
		vBoxleftTabPane.getTabs().add(connectionExplorerTab); 
	//	vBoxleftTabPane.getTabs().add(projectExplorerTab);  enable from settings on new project
		vBoxleft.getChildren().add(vBoxleftScrollPane);
		
        alltabbedEditors = new TabPane();
        alltabbedEditors.setId("alltabbedEditors");
        alltabbedEditors.setMinSize(size.getWidth()-300, size.getHeight()-135);
        
        vBoxright.getChildren().add(alltabbedEditors);  
        mainSplitPane.getItems().addAll(vBoxleft,vBoxright);
        contentHBox.getChildren().add(mainSplitPane);
        rootPane.getChildren().add(contentHBox);
        rootPane.setId("rootPane");
        
        HBox statushbox = new HBox();
        //statushbox.setMinWidth(20);
        statushbox.setId("statushbox");
        statushbox.getChildren().add(new ProgressBar(70){{ setId("progressbar"); }});
        rootPane.getChildren().add(statushbox);
        
		scene = new Scene(rootPane);
		scene.getStylesheets().add(selectedTheme);
		
		primaryStage.setTitle("Menu Item Application \u0627 \u0628 \u0629 \u062A \u062B \u062C \u062D ");  
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
				
		primaryStage.setOnCloseRequest( new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				
				
				// Closing the application so release all the connections;
				System.out.println("On application close ");
				
				for(Entry<ConnectionPlaceHolder,Connection>  mySqlConnection: mySqlConnectionsMap.entrySet()) {
					
					System.out.println("Closing the MySQL connction : "+mySqlConnection.getKey().getConnectionName());
					try {
						mySqlConnection.getValue().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for(Entry<ConnectionPlaceHolder,Connection>  postgreeSQLConnection: postgreeSqlConnectionsMap.entrySet()) {
					
					System.out.println("Closing the Postgree SQL connction :"+postgreeSQLConnection.getKey().getConnectionName());
					try {
						postgreeSQLConnection.getValue().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		
		
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseevent) {
				System.out.println("mouse click detected "+mouseevent.getSource());	
				if(null != newMenuItemEventHandler) {
					newMenuItemEventHandler.connectionStage.requestFocus();
				}
				
			}
		});		
		
	}

	private ChangeListener<TreeItem<String>> treeViewChangeListener() {
		return new ChangeListener<TreeItem<String>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,TreeItem<String> newValue) {
				
				TreeItem<String> selectedItem = newValue;
                System.out.println("Selected Item : " + selectedItem + " Parent value "+selectedItem.getParent());
                
                System.out.println("Selected connection graphic "+selectedItem.getGraphic());
                
                ContextMenu databaseContextMenu = new ContextMenu();
                MenuItem contextMenuNewSqlEditer = new MenuItem("New SQL Editer");
                contextMenuNewSqlEditer.setOnAction(newSQLEditerTabCreationAction(selectedItem));
                MenuItem contextMenuRefresh = new MenuItem("Refresh");
                MenuItem contextMenuDelete = new MenuItem("Delete");

                databaseContextMenu.getItems().addAll(contextMenuNewSqlEditer,contextMenuRefresh,contextMenuDelete);
                if(selectedItem.getParent().getValue().equalsIgnoreCase("Connections")) { // This is a database selection
                	treeConnectionsView.setContextMenu(databaseContextMenu);
                }
                else {   // This is a Database children selection like tables procedures etc
                	treeConnectionsView.setContextMenu(null);
                }
			}	
		};
	}

	double oldYposition = 0;
	double newYposition = 0;
	private void createSQLEditer(TreeItem<String> connectionTreeItem,File selectedFile) {
		
		System.out.println("creating new Editer");
		
		int rowCount = 0;
		int buttonCount =1;
		String sqlEditerofTypeIdFromImage = null;
		
		if(connectionTreeItem != null) {
		// Type of connection for which the editer is created
			sqlEditerofTypeIdFromImage = ((ImageView)connectionTreeItem.getGraphic()).getId();
		}
		System.out.println("******Creating SQL editer of Database Type******"+sqlEditerofTypeIdFromImage);
		// Add a SplitPAne to the Tab and with TextArea and Result console(text area on error or Teble View on Success)
        editerTabSplitPane = new SplitPane();
        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
                      
        // SQL Editer as a GridPane under a scrollPane
        sqlEditerscrollPane = new ScrollPane();    
        sqlEditerscrollPane.setId("ScrollPane");
        vBoxAreaSqlEditer1GridPane = new GridPane();
       // vBoxAreaSqlEditer1GridPane.setPadding(new Insets(10,5,10,5));
        vBoxAreaSqlEditer1GridPane.setVgap(5);
        vBoxAreaSqlEditer1GridPane.setId("vBoxAreaSqlEditer1GridPane");
        // Box 1
        // A StackPane to hold textArea for querying and buttons on top right corner
        stackPaneAreaButtons = new StackPane();
        stackPaneAreaButtons.setPrefSize(size.getWidth()-100,100);
                
        // a HBox to hold buttons/labels for the text area
        hboxQueryAreaButtons = new HBox(7);
        hboxQueryAreaButtons.setId("hboxQueryAreaButtons");
//        hboxQueryAreaButtons.setMaxHeight(20);
//        hboxQueryAreaButtons.setMaxWidth(60);
        
        Label maximizeQueryAreaButton = new Label();
       // ImageView  maximizeImage = new ImageView(new Image(getClass().getResourceAsStream("/images/maximize-button.png")));
        ImageView  maximizeImage = new ImageView();
        maximizeImage.setFitHeight(10);
        maximizeImage.setFitWidth(10);
        maximizeImage.setId("maximizeButton");
        maximizeQueryAreaButton.setGraphic(maximizeImage);
        Label closureQueryAreaButton = new Label("X");
        closureQueryAreaButton.setId("queryButtons");
        
        Label dragQueryAreaButtonRight = new Label("_|");
        dragQueryAreaButtonRight.setId("queryButtons");
        dragQueryAreaButtonRight.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				scene.setCursor(Cursor.N_RESIZE);
			}
		});
        dragQueryAreaButtonRight.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				scene.setCursor(Cursor.DEFAULT);
			}
		});
        Label dragQueryAreaButtonLeft = new Label("|_");
        dragQueryAreaButtonLeft.setId("queryButtons");
        dragQueryAreaButtonLeft.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) { 
				scene.setCursor(Cursor.N_RESIZE);
			}
		});
        dragQueryAreaButtonLeft.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) { 
				scene.setCursor(Cursor.DEFAULT);
			}
		});
        dragQueryAreaButtonRight.setOnMouseDragEntered(onMouseDragEnteredSqlTextArea(rowCount));
        dragQueryAreaButtonRight.setOnMouseDragged(resizeSQLEditerStackPane(rowCount));  // point to the StackPane that needs to be resized whih has the textArea Editer
        dragQueryAreaButtonLeft.setOnMouseDragEntered(onMouseDragEnteredSqlTextArea(rowCount));
        dragQueryAreaButtonLeft.setOnMouseDragged(resizeSQLEditerStackPane(rowCount));  // point to the StackPane that needs to be resized whih has the textArea Editer
         hboxQueryAreaButtons.setSpacing(10);
       // hboxQueryAreaButtons.setPadding(new Insets(0,15,0,15));
        hboxQueryAreaButtons.getChildren().addAll(maximizeQueryAreaButton,closureQueryAreaButton);
        StackPane.setAlignment(hboxQueryAreaButtons,Pos.TOP_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonRight,Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonLeft,Pos.BOTTOM_LEFT);
        
        // Sql Query Text Editer
        sqlCellTextArea = SQLCellTextArea.newBuilder()
        		.build();
		sqlCellTextArea.setId("sqlEditerofTypeIdFromImage"); // this can also hold the database connection name to which it belongs
		sqlCellTextArea.setOnKeyPressed(sqlEditerTextAreaInputHandler(sqlCellTextArea));
		sqlCellTextArea.focusedProperty().addListener(SqlCellFocusChangeListener(sqlCellTextArea));
		stackPaneAreaButtons.getChildren().addAll(sqlCellTextArea,hboxQueryAreaButtons,dragQueryAreaButtonRight,dragQueryAreaButtonLeft);
	//	sqlEditerTextArea1.setPrefSize(size.getWidth()-320,120);
		vBoxAreaSqlEditer1GridPane.getChildren().add(stackPaneAreaButtons);
		GridPane.setConstraints(stackPaneAreaButtons, 0, rowCount++);   // column 0 row 0
		
		// Box 2
        // A StackPane to hold textArea for querying and buttons on top right corner
		stackPaneAreaButtons = new StackPane();
	    stackPaneAreaButtons.setPrefSize(size.getWidth()-100,100);
	    // a HBox to hold buttons/labels for the text area
        hboxQueryAreaButtons = new HBox(7);
        hboxQueryAreaButtons.setId("hboxQueryAreaButtons");
//        hboxQueryAreaButtons.setMaxHeight(20);
//        hboxQueryAreaButtons.setMaxWidth(60);
        
        maximizeQueryAreaButton = new Label();
       // maximizeImage = new ImageView(new Image(getClass().getResourceAsStream("/images/maximize-button.png")));
        maximizeImage = new ImageView();
        maximizeImage.setFitHeight(10);
        maximizeImage.setFitWidth(10);
        maximizeQueryAreaButton.setGraphic(maximizeImage);
        maximizeImage.setId("maximizeButton");
        closureQueryAreaButton = new Label("X");
        
        dragQueryAreaButtonRight = new Label("_|");
        dragQueryAreaButtonLeft = new Label("|_");
        dragQueryAreaButtonRight.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				scene.setCursor(Cursor.N_RESIZE);
			}
		});
        dragQueryAreaButtonLeft.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) { 
				scene.setCursor(Cursor.N_RESIZE);
			}
		});
        dragQueryAreaButtonRight.setOnMouseExited(new EventHandler<MouseEvent>() {
     			@Override
     			public void handle(MouseEvent event) {
     				scene.setCursor(Cursor.DEFAULT);
     			}
     	});
             dragQueryAreaButtonLeft.setOnMouseExited(new EventHandler<MouseEvent>() {
     			@Override
     			public void handle(MouseEvent event) { 
     				scene.setCursor(Cursor.DEFAULT);
     			}
     	});
        dragQueryAreaButtonRight.setOnMouseDragEntered(onMouseDragEnteredSqlTextArea(rowCount));
        dragQueryAreaButtonRight.setOnMouseDragged(resizeSQLEditerStackPane(rowCount));
        dragQueryAreaButtonLeft.setOnMouseDragEntered(onMouseDragEnteredSqlTextArea(rowCount));
        dragQueryAreaButtonLeft.setOnMouseDragged(resizeSQLEditerStackPane(rowCount));
        
        hboxQueryAreaButtons.setSpacing(10);
       // hboxQueryAreaButtons.setPadding(new Insets(0,15,0,15));
        hboxQueryAreaButtons.getChildren().addAll(maximizeQueryAreaButton,closureQueryAreaButton);
        StackPane.setAlignment(hboxQueryAreaButtons,Pos.TOP_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonRight,Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonLeft,Pos.BOTTOM_LEFT);
        
        sqlCellTextArea = SQLCellTextArea.newBuilder()
        		.build();
		sqlCellTextArea.setId("sqlEditerofTypeIdFromImage"); // this can also hold the database connection name to which it belongs
		sqlCellTextArea.setOnKeyPressed(sqlEditerTextAreaInputHandler(sqlCellTextArea));
		sqlCellTextArea.focusedProperty().addListener(SqlCellFocusChangeListener(sqlCellTextArea));
		stackPaneAreaButtons.getChildren().addAll(sqlCellTextArea,hboxQueryAreaButtons,dragQueryAreaButtonRight,dragQueryAreaButtonLeft);
		vBoxAreaSqlEditer1GridPane.getChildren().add(stackPaneAreaButtons);
		GridPane.setConstraints(stackPaneAreaButtons, 0, rowCount++);   // column 0 row 0
		

		plus_button_borderPane = new BorderPane();
		addNewQueryEditerButton = new Button("+");
	 	addNewQueryEditerButton.setId("ButtonId "+ buttonCount++);
	 	addNewQueryEditerButton.getStyleClass().add("dynamicButtonStyle");
	 	addNewQueryEditerButton.setOnAction(addNewQueryEditerButtonAction(size, vBoxAreaSqlEditer1GridPane,rowCount, buttonCount,sqlEditerofTypeIdFromImage));
		addNewQueryEditerButton.setFont(new Font(10));
//		addNewQueryEditerButton.setMinHeight(15);
//		addNewQueryEditerButton.setMaxWidth(50);
		plus_button_borderPane.setCenter(addNewQueryEditerButton);
		vBoxAreaSqlEditer1GridPane.getChildren().add(plus_button_borderPane);
		GridPane.setConstraints(plus_button_borderPane, 0, rowCount);   // column 0 row 0	

		sqlEditerscrollPane.setContent(vBoxAreaSqlEditer1GridPane);
		sqlEditerscrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		sqlEditerscrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
		sqlEditerscrollPane.fitToWidthProperty().set(true);
		
		// The TabbedPane holding the results
		
		sqlEditerResultTabPane = new TabPane();
		sqlEditerResultTabPane.setId("allResultConsoleTabPane");
        sqlEditerResultTabPane.setPrefWidth(size.getWidth()-320);  
        //sqlEditerResultTabPane.setMinHeight(100);  // This will not let the results tab go down further with atleat 100px left
        
        sqlEditerResultTab = new Tab("Result "+sqlEditerCount);
        sqlEditerResultTab.setId("allResultTabPane");
        
        sqlEditerConsoleTab = new Tab("Console "+sqlEditerCount);
        sqlEditerConsoleTab.setId("allResultTabPane");
        
        sqlEditerResultTabPane.getTabs().add(sqlEditerResultTab);
        sqlEditerResultTabPane.getTabs().add(sqlEditerConsoleTab);
        
        editerTabSplitPane.setDividerPositions(0.47);  // split pane divider moving a bit lower
        editerTabSplitPane.getItems().add(sqlEditerscrollPane); // Top half of query editer
        editerTabSplitPane.getItems().add(sqlEditerResultTabPane); // bottom half of query editer
        editerTabSplitPane.setId("SplitPane");
        // Save the File 
        editerTabSplitPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				final KeyCombination keyComb = KeyCombination.keyCombination("Ctrl+S");			
				if(keyComb.match(event)){				
					genericSaveFileFunction();
				}		
			}
		});
        
        ConnectionFileRelationHolder connectionFileRelationHolder;
        
        if(connectionTreeItem != null) {  // This is case of new file from a existing connection
        	
        	connectionFileRelationHolder = ConnectionFileRelationHolder.newBuilder().connectionName(connectionTreeItem.getValue())
        			.imageView((ImageView)connectionTreeItem.getGraphic())
        			.connectionFileName("untitled-"+sqlEditerCount++).build();       	
        	sqlEditerTab = new Tab( connectionFileRelationHolder.getFirstSeperator() + connectionFileRelationHolder.getConnectionName() + connectionFileRelationHolder.getSecondSeperator() +connectionFileRelationHolder.getconnectionFileName());    // Full editer Tab  // This will also contain the database name
        	
        	ImageView  connectionTypeImageView = ImageItemsHolder.contructImageView(connectionTreeItem.getGraphic());
            sqlEditerTab.setGraphic(connectionTypeImageView);
        }
        else if(selectedFile != null)   // This is case of opening an existing file

        	sqlEditerTab = new Tab("[ No DB ] " + selectedFile.getName() );    // Full editer Tab  // This will also contain the database name
        else    // This is case of  a new File
        	sqlEditerTab = new Tab("[ No DB ] " +"untitled-"+ sqlEditerCount++);    // Full editer Tab  // This will also contain the database name
        // ImageView  connectionTypeImageView = new ImageView(new Image(getClass().getResourceAsStream("/graphics/-----+"Logo.png")));
     
        sqlEditerTab.setContent(editerTabSplitPane);  
        sqlEditerTab.setId("sqlEditerTab");
        alltabbedEditors.getTabs().add(sqlEditerTab);  // multiple Editer Tab holder/pane
        
        // Bring focus to this newly created Tab
        SingleSelectionModel<Tab> singleSelectionModel =  alltabbedEditors.getSelectionModel();
        singleSelectionModel.select(sqlEditerTab);
        System.out.println( alltabbedEditors.getSelectionModel().getSelectedIndex());
		System.out.println( alltabbedEditors.getSelectionModel().getSelectedItem().getText() );
		
	}

	private ChangeListener<Boolean> SqlCellFocusChangeListener(SQLCellTextArea sqlCellTextArea) {
		return new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				if(newValue) {
					System.out.println("Focussed !!!!");
					foucesSqlEditerTextArea = sqlCellTextArea;
				}				
			}
		};
	}

	private EventHandler<MouseEvent> onMouseDragEnteredSqlTextArea(int rowCount) {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				oldYposition = event.getY();
				System.out.println("Old Y Position" +event.getY());
				StackPane stackPaneAreaButtonsCurrent =  (StackPane) vBoxAreaSqlEditer1GridPane.getChildren().get(rowCount);
				System.out.println("Current position of Area"+stackPaneAreaButtonsCurrent.getPrefHeight());
				TextArea todragTextArea = (TextArea)stackPaneAreaButtonsCurrent.getChildren().get(0);
				todragTextArea.requestFocus();
				todragTextArea.setId("todragTextArea");
			}
		};
	}

	private EventHandler<MouseEvent> resizeSQLEditerStackPane(int rowCount) {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				newYposition = event.getY();
				System.out.println("New Y Position" +newYposition);	
				
				StackPane stackPaneAreaButtonsCurrent =  (StackPane) vBoxAreaSqlEditer1GridPane.getChildren().get(rowCount);
				System.out.println("Current position of Area"+stackPaneAreaButtonsCurrent.getPrefHeight());
				TextArea todragTextArea = (TextArea)stackPaneAreaButtonsCurrent.getChildren().get(0);
				stackPaneAreaButtonsCurrent.setPrefHeight(stackPaneAreaButtonsCurrent.getPrefHeight() + (newYposition - oldYposition));
				System.out.println("Post position of Area"+stackPaneAreaButtonsCurrent.getPrefHeight());
				Cursor cursor_ = Cursor.DEFAULT; 
				scene.setCursor(cursor_);
			}
		};
	}

	private EventHandler<KeyEvent> sqlEditerTextAreaInputHandler(TextArea sqlEditerTextArea) {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent pressedEvent) {
				toolBarRunButton.setDisable(false);
				
				// Check if the keys pressed don't result in file change signal
				/*
					ESCAPE,TAB,CAPS,CLEAR,NUM_LOCK
				isModifierKey() - SHIFT,CONTROL,WINDOWS,ALT,ALT_GRAPH,CONTROL
				RIGHT,LEFT,UP,DOWN,DELETE,PRINTSCREEN,UNDEFINED,WINDOWS
				*/
				if( !(pressedEvent.getCode().isModifierKey() || pressedEvent.getCode().equals(KeyCode.ESCAPE) || pressedEvent.getCode().equals(KeyCode.CAPS) 
						|| pressedEvent.getCode().equals(KeyCode.RIGHT) || pressedEvent.getCode().equals(KeyCode.LEFT) || pressedEvent.getCode().equals(KeyCode.UP)
						|| pressedEvent.getCode().equals(KeyCode.DOWN) || pressedEvent.getCode().equals(KeyCode.DELETE) || pressedEvent.getCode().equals(KeyCode.PRINTSCREEN) 
						|| pressedEvent.getCode().equals(KeyCode.UNDEFINED) || pressedEvent.getCode().equals(KeyCode.HOME) || pressedEvent.getCode().equals(KeyCode.END)
						|| pressedEvent.getCode().equals(KeyCode.PAGE_UP)  || pressedEvent.getCode().equals(KeyCode.PAGE_DOWN)  || pressedEvent.getCode().equals(KeyCode.CLEAR)
						|| pressedEvent.getCode().equals(KeyCode.CLEAR)  || pressedEvent.getCode().equals(KeyCode.INSERT) ||  pressedEvent.getCode().equals(KeyCode.NUM_LOCK)) )
				{
					
					System.out.println("Setting focussed area !");
					foucesSqlEditerTextArea = sqlEditerTextArea;
					if(!alltabbedEditors.getSelectionModel().getSelectedItem().getText().startsWith("*")) {
						alltabbedEditors.getSelectionModel().getSelectedItem().setText("*" + alltabbedEditors.getSelectionModel().getSelectedItem().getText());
					}
				}
			}
		};
	}


	private void addnewSqlEditerCell(Dimension size,ActionEvent event,GridPane vBoxAreaSqlEditer1GridPane,int rowCount,int buttonCount,String sqlEditerofTypeIdFromImage) {
		
		// we need this local gridpane to distinguish between gridpanes from diffeent sql editers
		//System.out.println("Add new Cell clicked from "+ ((Button)event.getSource()).getId());
		
		 
		// Box 2
        // A StackPane to hold textArea for querying and buttons on top right corner
		stackPaneAreaButtons = new StackPane();
	    stackPaneAreaButtons.setPrefSize(size.getWidth()-100,100);
	    // a HBox to hold buttons/labels for the text area
        hboxQueryAreaButtons = new HBox(7);
        hboxQueryAreaButtons.setId("hboxQueryAreaButtons");
//        hboxQueryAreaButtons.setMaxHeight(20);
//        hboxQueryAreaButtons.setMaxWidth(60);
        
        
         Label maximizeQueryAreaButton = new Label();
        //ImageView maximizeImage = new ImageView(new Image(getClass().getResourceAsStream("/images/maximize-button.png")));
        ImageView maximizeImage = new ImageView();
        maximizeImage.setFitHeight(10);
        maximizeImage.setFitWidth(10);
        maximizeQueryAreaButton.setGraphic(maximizeImage);
        maximizeImage.setId("maximizeButton");
        Label closureQueryAreaButton = new Label("X");
        Label dragQueryAreaButtonRight = new Label("_|");
        dragQueryAreaButtonRight.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				scene.setCursor(Cursor.N_RESIZE);
			}
		});
        Label dragQueryAreaButtonLeft = new Label("|_");
        dragQueryAreaButtonLeft.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) { 
				scene.setCursor(Cursor.N_RESIZE);
			}
		});
        dragQueryAreaButtonRight.setOnMouseDragEntered(onMouseDragEnteredSqlTextArea(rowCount));
        dragQueryAreaButtonRight.setOnMouseDragged(resizeSQLEditerStackPane(rowCount));  // point to the StackPane that needs to be resized whih has the textArea Editer
        dragQueryAreaButtonLeft.setOnMouseDragEntered(onMouseDragEnteredSqlTextArea(rowCount));
        dragQueryAreaButtonLeft.setOnMouseDragged(resizeSQLEditerStackPane(rowCount));  
        
        hboxQueryAreaButtons.setSpacing(10);
       // hboxQueryAreaButtons.setPadding(new Insets(0,15,0,15));
        hboxQueryAreaButtons.getChildren().addAll(maximizeQueryAreaButton,closureQueryAreaButton);
        StackPane.setAlignment(hboxQueryAreaButtons,Pos.TOP_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonRight,Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonLeft,Pos.BOTTOM_LEFT);
        
        sqlCellTextArea = SQLCellTextArea.newBuilder()
        		.build();
    	sqlCellTextArea.setId("sqlEditerofTypeIdFromImage");
    	sqlCellTextArea.focusedProperty().addListener(SqlCellFocusChangeListener(sqlCellTextArea));
		sqlCellTextArea.setOnKeyPressed(sqlEditerTextAreaInputHandler(sqlCellTextArea));
		
		// Create a ScrollPane to display the TextArea with scrollbars
		/*
		 * ScrollPane scrollPaneTextArea = new ScrollPane(sqlEditerTextArea); // Set
		 * scroll policy to ensure both horizontal and vertical scrollbars appear as
		 * needed scrollPaneTextArea.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		 * scrollPaneTextArea.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		 */
        
		stackPaneAreaButtons.getChildren().addAll(sqlCellTextArea,hboxQueryAreaButtons,dragQueryAreaButtonRight,dragQueryAreaButtonLeft);
		vBoxAreaSqlEditer1GridPane.getChildren().add(stackPaneAreaButtons);
		GridPane.setConstraints(stackPaneAreaButtons, 0, rowCount++);   // column 0 row 0 

		
		// Remove previous plus button
		vBoxAreaSqlEditer1GridPane.getChildren().remove(plus_button_borderPane);
		
		plus_button_borderPane = new BorderPane();
		addNewQueryEditerButton = new Button("+");
		addNewQueryEditerButton.setId("ButtonId"+buttonCount++);
		addNewQueryEditerButton.applyCss(); // Force refresh
	 	addNewQueryEditerButton.getStyleClass().add("dynamicButtonStyle");
		
	 	addNewQueryEditerButton.setOnAction(addNewQueryEditerButtonAction(size, vBoxAreaSqlEditer1GridPane,rowCount, buttonCount,sqlEditerofTypeIdFromImage));
		addNewQueryEditerButton.setFont(new Font(10));
//		addNewQueryEditerButton.setMinHeight(15);
//		addNewQueryEditerButton.setMaxWidth(50);
		plus_button_borderPane.setCenter(addNewQueryEditerButton);
		vBoxAreaSqlEditer1GridPane.getChildren().add(plus_button_borderPane);
		GridPane.setConstraints(plus_button_borderPane, 0, buttonCount++);  
		
	}
	
	private EventHandler<ActionEvent> addNewQueryEditerButtonAction(Dimension size,GridPane vBoxAreaSqlEditer1GridPane,int rowCount, int buttonCount,String sqlEditerofTypeIdFromImage) {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Button Count "+buttonCount);
				addnewSqlEditerCell(size,event,vBoxAreaSqlEditer1GridPane,rowCount,buttonCount,sqlEditerofTypeIdFromImage);
			}
		};
	}
	
	private EventHandler<ActionEvent> openFileMenuItemAction() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			
				FileChooser fileChooser = new FileChooser();
				
				File selectedFile = fileChooser.showOpenDialog(primaryStage);
				System.out.println("Selected File path" + selectedFile);
				System.out.println("Selected File name" + selectedFile.getAbsolutePath());
				
				// Create a new tab for query editer
				oldYposition = 0;
				newYposition = 0;
				
				createSQLEditer(null,selectedFile);  // Just opening a file without DB connection Yet
				
				// add the opened file to openedFiles
				openedFilesMap.put(selectedFile,"[ No DB ] ");
			
				// Write to the newly opened file its contents	
				SplitPane tabSplitPane	= (SplitPane)  alltabbedEditors.getSelectionModel().getSelectedItem().getContent();
				System.out.println( tabSplitPane.getItems().get(0).toString()) ; // Top half of the tab where sql editer is present
				
				ScrollPane sqlEditerscrollPane = (ScrollPane) tabSplitPane.getItems().get(0);
				   
				GridPane tabEditergridPane =  (GridPane) sqlEditerscrollPane.getContent(); // will have stack panes which individually have textArea
				System.out.println( tabEditergridPane.getChildren().toString());
				 
				
			    BufferedReader reader;
			    StringBuilder readaEditerCell = new StringBuilder();
			    String readaLine = "";
				try {
					reader = new BufferedReader(new FileReader(selectedFile));
					
					ObservableList<Node> gridPaneList =  tabEditergridPane.getChildren();
					System.out.println("How may stackPanes added "+ gridPaneList.size());
					int sqlEditerIndex = 0;	
					
					int rowCount =  gridPaneList.size()-1;
					int buttonCount =  gridPaneList.size();
					String sqlEditerofTypeIdFromImage = "";
					
					 while(reader.ready()) {
						readaLine =  reader.readLine();
						if(!"#COMMAND#".equalsIgnoreCase(readaLine)) {
							readaEditerCell.append(readaLine+"\n");
						}
						else
						{
							// This will never run first as createSQLEditer call above will create 2 textaAreas as part of 2 stackpanes
							if(sqlEditerIndex >= gridPaneList.size()-1) {
								addnewSqlEditerCell(size, event, tabEditergridPane, rowCount, buttonCount, sqlEditerofTypeIdFromImage);
								rowCount++;buttonCount++;
								//addnewSqlEditerCell(Dimension size,ActionEvent event,GridPane vBoxAreaSqlEditer1GridPane,int rowCount,int buttonCount,String sqlEditerofTypeIdFromImage) 
							}
							Node node = gridPaneList.get(sqlEditerIndex++);  
							if(node instanceof StackPane) {	   
								TextArea lowestLevelTextArea = (TextArea) ((StackPane) node).getChildren().get(0);
								lowestLevelTextArea.setText(readaEditerCell.toString());	
								sqlEditerofTypeIdFromImage = lowestLevelTextArea.getId();
							}
							else if(node instanceof BorderPane) {  // The last node is BorderPane so before it is the latest added stackpane which contains the TextArea
								node = gridPaneList.get(gridPaneList.size()-2);
								TextArea lowestLevelTextArea = (TextArea) ((StackPane) node).getChildren().get(0);
								lowestLevelTextArea.setText(readaEditerCell.toString());	
								sqlEditerofTypeIdFromImage = lowestLevelTextArea.getId();
							}
							readaEditerCell = new StringBuilder();
						}
					}
					reader.close();
				    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
	}
	
	
	private EventHandler<ActionEvent> saveAsFileMenuItemAction() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				genericSaveAsFileFunction();		
			}
		};
	}
	
	
	
	private void genericSaveAsFileFunction() {
		
		String noConnectionConstant = "[ No DB ] ";
		boolean fileSaved = false;
		
		if(!fileSaved) {
			System.out.println("This is a call from save as !!!!");
				
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save");
		    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files","*.*")); 
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			System.out.println("Save File invoked");
			System.out.println("Selected File path" + selectedFile);
			System.out.println("Selected File name" + selectedFile.getAbsolutePath());

			openedFilesMap.put(selectedFile,noConnectionConstant);
			 					
			saveToFile(noConnectionConstant,selectedFile);
		}		
	}

	private EventHandler<ActionEvent> saveFileMenuItemAction() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				genericSaveFileFunction();		
			}
		};
	}
	
	
	
	public void genericSaveFileFunction() {
		
		String connectionConstant = " [ No DB ] ";
		boolean fileSaved = false;
		
		for(Map.Entry<File,String> openedFileMap :  openedFilesMap.entrySet()) {
			
			System.out.println("openedFileMap.getKey()"+openedFileMap.getKey());
			System.out.println("openedFileMap.getValue()"+openedFileMap.getValue());
			System.out.println("current Tab name "+alltabbedEditors.getSelectionModel().getSelectedItem().getText());
			
			String[] splittedTabName = alltabbedEditors.getSelectionModel().getSelectedItem().getText().split(" ");
			System.out.println("Only file name is :"+ splittedTabName[splittedTabName.length-1]);
			
			if(openedFileMap.getKey().getAbsolutePath().contains(splittedTabName[splittedTabName.length-1]))
					 // this will give the current tabs/file name
			{
				System.out.println("File with the name already opened");
				//System.out.println("The file is already saved to this location"+openedFile.getAbsolutePath());
				 					
				fileSaved = true;		
				saveToFile(openedFileMap.getValue(),openedFileMap.getKey());
			}
			else {
				System.out.println("File with this name is not alreay opened");
			}
		}
		
		if(!fileSaved) {
			System.out.println("The file is not yet saved !!!");
			
			Node imageViewNode =  alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic();
			
			if(imageViewNode != null)
				connectionConstant =  alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().split("##")[1];

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save");
		    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files","*.*")); 
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			System.out.println("Save File invoked");
			System.out.println("Selected File path" + selectedFile);
			System.out.println("Selected File name" + selectedFile.getAbsolutePath());

			openedFilesMap.put(selectedFile,connectionConstant);
				
			 					
			saveToFile(connectionConstant,selectedFile);
		}
		
	}
	
	private void saveToFile(String connectionName,File selectedFile) {

		StringBuilder fileToSaveFromSQLEditer = new StringBuilder();	
		SplitPane tabSplitPane	= (SplitPane)  alltabbedEditors.getSelectionModel().getSelectedItem().getContent();
		System.out.println( tabSplitPane.getItems().get(0).toString()) ; // Top half of the tab where sql editer is present
		  
		ScrollPane sqlEditerscrollPane = (ScrollPane) tabSplitPane.getItems().get(0);
		   
		GridPane tabEditergridPane =  (GridPane) sqlEditerscrollPane.getContent(); // will have stack panes which individually have textArea
		System.out.println( tabEditergridPane.getChildren().toString());
		 
		Node  getConnectionIdNode = this.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic();
		ObservableList<Node> gridPaneList =  tabEditergridPane.getChildren();
		for(Node node : gridPaneList) {
			if(node instanceof StackPane) {	   
				 TextArea lowestLevelTextArea = (TextArea) ((StackPane) node).getChildren().get(0);
				 fileToSaveFromSQLEditer.append(lowestLevelTextArea.getText());
				 fileToSaveFromSQLEditer.append("\n#COMMAND#\n");
			 }
		 }
		
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(selectedFile));
			writer.write(fileToSaveFromSQLEditer.toString());
		    writer.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(getConnectionIdNode != null)
			alltabbedEditors.getSelectionModel().getSelectedItem().setText(" [ "+getConnectionIdNode.getId().split("##")[1]+ " ] "+selectedFile.getName());
		else
			alltabbedEditors.getSelectionModel().getSelectedItem().setText(connectionName+selectedFile.getName());
	}
	
	private EventHandler<ActionEvent> renameFileEventHandler() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				renameFileFunction();		
			}
		};
	}
	
	private void  renameFileFunction() {
	
	   String noConnectionConstant = "[ No DB ] ";
	   System.out.println( "Current File name is" +alltabbedEditors.getSelectionModel().getSelectedItem().getText().replace("[ No DB ] ",""));
	   
	   System.out.println("Opend files :");
	   for(Map.Entry<File,String> openedFileMap :  openedFilesMap.entrySet()) {
		   
			System.out.println("openedFileMap.getKey()"+openedFileMap.getKey());
			System.out.println("openedFileMap.getValue()"+openedFileMap.getValue());
			
			if(openedFileMap.getKey().getAbsolutePath().contains(alltabbedEditors.getSelectionModel().getSelectedItem().getText().replace("[ No DB ] ",""))
					|| openedFileMap.getKey().getAbsolutePath().contains(alltabbedEditors.getSelectionModel().getSelectedItem().getText().replace("*[ No DB ] ","")) 
					|| openedFileMap.getKey().getAbsolutePath().contains(alltabbedEditors.getSelectionModel().getSelectedItem().getText().replace("*",""))) // this will give the current tabs/file name
			{
				System.out.println("File with the name already opened");
				//System.out.println("The file is already saved to this location"+openedFile.getAbsolutePath());
				 					
				
				System.out.println("Current File  location is " );
				System.out.println(openedFileMap.getKey().getAbsolutePath());
					
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Rename");
			    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files","*.*"));
			    System.out.println("getParentFile"+openedFileMap.getKey().getParentFile());
			    System.out.println(openedFileMap.getKey().getName());
			    fileChooser.setInitialDirectory(openedFileMap.getKey().getParentFile());
			    fileChooser.setInitialFileName(openedFileMap.getKey().getName());
				File selectedFile = fileChooser.showSaveDialog(primaryStage);
				System.out.println("Rename File invoked");
				System.out.println("Rename File name" + selectedFile.getAbsolutePath());

				openedFilesMap.put(selectedFile,noConnectionConstant);
					
				 					
				//saveToFile(noConnectionConstant,selectedFile);
				
				openedFileMap.getKey().renameTo(selectedFile);
				
			}
		}
		
	}
	
	private EventHandler<ActionEvent> closeFileMenuItemEventHandler() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for(Map.Entry<File,String> openedFileMap :  openedFilesMap.entrySet()) {
					   
				
					
					if(openedFileMap.getKey().getAbsolutePath().contains(alltabbedEditors.getSelectionModel().getSelectedItem().getText().replace("[ No DB ] ",""))
							|| openedFileMap.getKey().getAbsolutePath().contains(alltabbedEditors.getSelectionModel().getSelectedItem().getText().replace("*[ No DB ] ","")) 
							|| openedFileMap.getKey().getAbsolutePath().contains(alltabbedEditors.getSelectionModel().getSelectedItem().getText().replace("*",""))) // this will give the current tabs/file name
					{
						System.out.println("This is the file to be remoced from openedFileMap");
						System.out.println("openedFileMap.getKey()"+openedFileMap.getKey());
						System.out.println("openedFileMap.getValue()"+openedFileMap.getValue());
						openedFilesMap.remove(openedFileMap.getKey());
						break;
					}
				}
				alltabbedEditors.getTabs().remove(alltabbedEditors.getSelectionModel().getSelectedItem());
				
			}
		};
	}
	
	private EventHandler<ActionEvent> closeAllFileMenuItemEventHandler() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			
				openedFilesMap.clear();

				alltabbedEditors.getTabs().clear();
				System.out.println("Current Opened File "+openedFilesMap.size());
			}
		};
	}
	
}

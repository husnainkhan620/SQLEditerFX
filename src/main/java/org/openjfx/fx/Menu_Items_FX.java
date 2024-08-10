package org.openjfx.fx;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;

import com.openfx.handlers.NewMenuItemEventHandler;
import com.openfx.handlers.SearchToolEventHandler;
import com.openfx.handlers.SqlQueryRunButtonSubmit;
import com.openfx.placeholders.ConnectionPlaceHolder;
import com.sun.jdi.event.Event;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Menu_Items_FX extends Application {

	public Scene scene;

	public VBox rootPane;
	
	int sqlEditerCount=1;
	
	public JFXPanel fxPanel;
	public JFrame frame;
	public Scene sceneDataBaseConnection;
	public BorderPane borderSelectDatabase ;
	public FlowPane selectDatabaseConnectionsflow;
	
	public boolean connectionDoubleClicked = false;
	
	public String currentConnectionSelected;
	public HashMap<ConnectionPlaceHolder,Connection> mySqlConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> postgreeSqlConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> sqliteConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	public HashMap<ConnectionPlaceHolder,Connection> oracleConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	
	public HashMap<ConnectionPlaceHolder,Connection> duckDBConnectionsMap = new HashMap<ConnectionPlaceHolder, Connection>();
	
	
	public TreeItem<String> rootConnectionItem;
	public TreeView<String> treeConnectionsView;
	
	public TabPane alltabbedEditors;
	public SplitPane editerTabSplitPane;
	public GridPane vBoxAreaSqlEditer1GridPane;
	public StackPane stackPaneAreaButtons;
	public HBox hboxQueryAreaButtons;
	public TextArea sqlEditerTextArea;
	public BorderPane plus_button_borderPane;
	public Button addNewQueryEditerButton;
	public TabPane sqlEditerResultTabPane;
	public Tab sqlEditerResultTab;
	public Tab sqlEditerConsoleTab;
	public TextArea sqlEditerTextAreaResult;
	public Tab sqlEditerTab;
	public ScrollPane sqlEditerscrollPane;
	
	public TextArea foucesSqlEditerTextArea;
	public Button toolBarRunButton;

	public Dimension size ;
	
	public ListView<String> connectedDatabasesNames = new ListView<String>();
	public ListView<String> loadedTablesNames = new ListView<String>();
	public ListView<String> selectedTablesNames = new ListView<String>();
	public Tab dataSearchtabPane;
	
	public MenuBar createMenuBar() {
		
		size = Toolkit.getDefaultToolkit().getScreenSize();
		
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu();
		fileMenu.setText("File");
		Menu editMenu = new Menu();
		editMenu.setText("Edit");
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem openMenuItem = new MenuItem("Open");
		fileMenu.getItems().add(newMenuItem);
		fileMenu.getItems().add(openMenuItem);
		
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(editMenu);
		
		newMenuItem.setOnAction(newSQLEditerTabCreationAction(new TreeItem<String>("untitled")));
		
		return menuBar;
		
	}

	private EventHandler<ActionEvent> newSQLEditerTabCreationAction(TreeItem<String> connectionTreeItem) {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {		
				String sqlEditerofTypeIdFromImage = ((ImageView)connectionTreeItem.getGraphic()).getId();
				
				createSQLEditer(connectionTreeItem);
				
				
			}	
		};
	}
	
	public static void main(String[] args) {
	
		
		System.out.println("My Menu program");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		size = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(size);
		
		rootPane = new VBox();
		rootPane.getChildren().add(createMenuBar());
		
		Image imagedatabase = new Image(getClass().getResourceAsStream("/images/database.png"));
		ImageView imagedatabasenode = new ImageView(imagedatabase);
		imagedatabasenode.setFitHeight(15);
		imagedatabasenode.setFitWidth(15);
		imagedatabasenode.setPreserveRatio(true);
		
		Image imageSQLite = new Image(getClass().getResourceAsStream("/images/sqlite.png"));
		ImageView imageSQLitenode = new ImageView(imageSQLite);
		imageSQLitenode.setFitHeight(20);
		imageSQLitenode.setFitWidth(20);
		imageSQLitenode.setPreserveRatio(true);
		
		Image imageDatbaseTable = new Image(getClass().getResourceAsStream("/images/table.png"));
		ImageView imageDatbaseTablenode = new ImageView(imageDatbaseTable);
		imageDatbaseTablenode.setFitHeight(20);
		imageDatbaseTablenode.setFitWidth(20);
		imageDatbaseTablenode.setPreserveRatio(true);
		
		Image imageDatbaseTable1 = new Image(getClass().getResourceAsStream("/images/table.png"));
		ImageView imageDatbaseTablenode1 = new ImageView(imageDatbaseTable1);
		imageDatbaseTablenode1.setFitHeight(20);
		imageDatbaseTablenode1.setFitWidth(20);
		imageDatbaseTablenode1.setPreserveRatio(true);
		
		ToolBar toolBar = new ToolBar();
		toolBar.setStyle("-fx-background-color: DAE6F3;");
		Button connectDB = new Button();
		connectDB.setText("DB");
		connectDB.setOnAction(new NewMenuItemEventHandler(this));
		//toolBarButton1.setGraphic(new ImageView(imageOk));
		toolBar.getItems().add(connectDB);
		toolBar.getItems().add(new Separator());
		Button toolBarSearch = new Button("Search");
		toolBarSearch.setOnAction(new SearchToolEventHandler(this));
		toolBar.getItems().add(toolBarSearch);
		toolBar.getItems().add(new Separator());
		toolBarRunButton = new Button("Run");
		toolBarRunButton.setDisable(true);
		toolBarRunButton.setOnAction(new SqlQueryRunButtonSubmit(this));
		toolBar.getItems().add(toolBarRunButton);
		rootPane.getChildren().add(toolBar);

		
		HBox contentHBox = new HBox();
		
		SplitPane mainSplitPane = new SplitPane();
		mainSplitPane.setDividerPositions(0.21);
		VBox vBoxleft  = new VBox();
	    VBox vBoxright = new VBox();
	
		rootConnectionItem = new TreeItem<String>("Connections",imagedatabasenode);
		treeConnectionsView = new TreeView<String>();
		treeConnectionsView.setRoot(rootConnectionItem);
		treeConnectionsView.setShowRoot(false);
		//treeView.setMinSize(300, size.getHeight()-120);
		treeConnectionsView.setPrefWidth(300);
		treeConnectionsView.setMinWidth(100);
		treeConnectionsView.setMinHeight(size.getHeight()-120);
	
		treeConnectionsView.setContextMenu(null);
		
		this.treeConnectionsView.getSelectionModel().selectedItemProperty().addListener(treeViewChangeListener());
		
		vBoxleft.getChildren().add(treeConnectionsView);
		
        alltabbedEditors = new TabPane();
        alltabbedEditors.setMinSize(size.getWidth()-320, size.getHeight()-135);
        
        //createSQLEditer(size);
        
        
        vBoxright.getChildren().add(alltabbedEditors);  
        mainSplitPane.getItems().addAll(vBoxleft,vBoxright);
        contentHBox.getChildren().add(mainSplitPane);
        rootPane.getChildren().add(contentHBox);
	
		scene = new Scene(rootPane,size.getWidth(),size.getHeight()-70);
		
		primaryStage.setTitle("Menu Item Application \u0627 \u0628 \u0629 \u062A \u062B \u062C \u062D");
		primaryStage.setScene(scene);
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
			}
		});		
		
	}

	private ChangeListener<TreeItem<String>> treeViewChangeListener() {
		return new ChangeListener<TreeItem<String>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,TreeItem<String> newValue) {
				
				TreeItem<String> selectedItem = newValue;
                System.out.println("Selected Item : " + selectedItem + " Parent value "+selectedItem.getParent());
                 	
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
	private void createSQLEditer(TreeItem<String> connectionTreeItem) {
		
		System.out.println("creating new Editer");
		toolBarRunButton.setDisable(true);
		int rowCount = 0;
		int buttonCount =1;
		
		// Type of connection for which the editer is created
		String sqlEditerofTypeIdFromImage = ((ImageView)connectionTreeItem.getGraphic()).getId();
		
		System.out.println("******Creating SQL editer of Database Type******"+sqlEditerofTypeIdFromImage);
		
		// Add a SplitPAne to the Tab and with TextArea and Result console(text area on error or Teble View on Success)
        editerTabSplitPane = new SplitPane();
        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
        
        // SQL Editer as a GridPane under a scrollPane
        sqlEditerscrollPane = new ScrollPane();
        
        vBoxAreaSqlEditer1GridPane = new GridPane();
        vBoxAreaSqlEditer1GridPane.setPadding(new Insets(10,5,10,5));
        vBoxAreaSqlEditer1GridPane.setVgap(5);
        
        // Box 1
        // A StackPane to hold textArea for querying and buttons on top right corner
        stackPaneAreaButtons = new StackPane();
        stackPaneAreaButtons.setPrefSize(size.getWidth()-100,50);
        
        // a HBox to hold buttons/labels for the text area
        hboxQueryAreaButtons = new HBox(7);
        hboxQueryAreaButtons.setMaxHeight(20);
        hboxQueryAreaButtons.setMaxWidth(60);
        
        Label maximizeQueryAreaButton = new Label();
        ImageView  maximizeImage = new ImageView(new Image(getClass().getResourceAsStream("/images/maximize-button.png")));
        maximizeImage.setFitHeight(10);
        maximizeImage.setFitWidth(10);
        maximizeQueryAreaButton.setGraphic(maximizeImage);
        Label closureQueryAreaButton = new Label("X");
        
        Label dragQueryAreaButtonRight = new Label("_|");
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
        hboxQueryAreaButtons.setPadding(new Insets(0,15,0,15));
        hboxQueryAreaButtons.getChildren().addAll(maximizeQueryAreaButton,closureQueryAreaButton);
        StackPane.setAlignment(hboxQueryAreaButtons,Pos.TOP_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonRight,Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonLeft,Pos.BOTTOM_LEFT);
        
        // Sql Query Text Editer
		sqlEditerTextArea = new TextArea("");
		sqlEditerTextArea.setId(sqlEditerofTypeIdFromImage); // this can also hold the database connection name to which it belongs
		sqlEditerTextArea.setOnKeyPressed(sqlEditerTextAreaInputHandler(sqlEditerTextArea));
		stackPaneAreaButtons.getChildren().addAll(sqlEditerTextArea,hboxQueryAreaButtons,dragQueryAreaButtonRight,dragQueryAreaButtonLeft);
	//	sqlEditerTextArea1.setPrefSize(size.getWidth()-320,120);
		vBoxAreaSqlEditer1GridPane.getChildren().add(stackPaneAreaButtons);
		GridPane.setConstraints(stackPaneAreaButtons, 0, rowCount++);   // column 0 row 0
		
		// Box 2
        // A StackPane to hold textArea for querying and buttons on top right corner
		stackPaneAreaButtons = new StackPane();
	    stackPaneAreaButtons.setPrefSize(size.getWidth()-100,50);
	    // a HBox to hold buttons/labels for the text area
        hboxQueryAreaButtons = new HBox(7);
        hboxQueryAreaButtons.setMaxHeight(20);
        hboxQueryAreaButtons.setMaxWidth(60);
        
        maximizeQueryAreaButton = new Label();
        maximizeImage = new ImageView(new Image(getClass().getResourceAsStream("/images/maximize-button.png")));
        maximizeImage.setFitHeight(10);
        maximizeImage.setFitWidth(10);
        maximizeQueryAreaButton.setGraphic(maximizeImage);
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
        hboxQueryAreaButtons.setPadding(new Insets(0,15,0,15));
        hboxQueryAreaButtons.getChildren().addAll(maximizeQueryAreaButton,closureQueryAreaButton);
        StackPane.setAlignment(hboxQueryAreaButtons,Pos.TOP_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonRight,Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonLeft,Pos.BOTTOM_LEFT);
        
		sqlEditerTextArea = new TextArea("");
		sqlEditerTextArea.setId(sqlEditerofTypeIdFromImage); // this can also hold the database connection name to which it belongs
		sqlEditerTextArea.setOnKeyPressed(sqlEditerTextAreaInputHandler(sqlEditerTextArea));
		stackPaneAreaButtons.getChildren().addAll(sqlEditerTextArea,hboxQueryAreaButtons,dragQueryAreaButtonRight,dragQueryAreaButtonLeft);
		vBoxAreaSqlEditer1GridPane.getChildren().add(stackPaneAreaButtons);
		GridPane.setConstraints(stackPaneAreaButtons, 0, rowCount++);   // column 0 row 0
		

		plus_button_borderPane = new BorderPane();
		addNewQueryEditerButton = new Button("+");
	 	addNewQueryEditerButton.setId("Button Id "+buttonCount++);
	 	addNewQueryEditerButton.setOnAction(addNewQueryEditerButtonAction(size, vBoxAreaSqlEditer1GridPane,rowCount, buttonCount,sqlEditerofTypeIdFromImage));
		addNewQueryEditerButton.setFont(new Font(10));
		addNewQueryEditerButton.setMinHeight(15);
		addNewQueryEditerButton.setMaxWidth(50);
		plus_button_borderPane.setCenter(addNewQueryEditerButton);
		vBoxAreaSqlEditer1GridPane.getChildren().add(plus_button_borderPane);
		GridPane.setConstraints(plus_button_borderPane, 0, rowCount);   // column 0 row 0	

		sqlEditerscrollPane.setContent(vBoxAreaSqlEditer1GridPane);
		sqlEditerscrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		sqlEditerscrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
		sqlEditerscrollPane.fitToWidthProperty().set(true);
		
		// The TabbedPane holding the results
		
		sqlEditerResultTabPane = new TabPane();
        sqlEditerResultTabPane.setPrefWidth(size.getWidth()-320);  
        sqlEditerResultTabPane.setMinHeight(100);  // This will not let the results tab go down further with atleat 100px left
        
        sqlEditerResultTab = new Tab("Result "+sqlEditerCount);
     
        
        sqlEditerConsoleTab = new Tab("Console "+sqlEditerCount);
        
        sqlEditerResultTabPane.getTabs().add(sqlEditerResultTab);
        sqlEditerResultTabPane.getTabs().add(sqlEditerConsoleTab);
        
        editerTabSplitPane.setDividerPositions(0.47);  // split pane divider moving a bit lower
        editerTabSplitPane.getItems().add(sqlEditerscrollPane); // Top half of query editer
        editerTabSplitPane.getItems().add(sqlEditerResultTabPane); // bottom half of query editer
        
        sqlEditerTab = new Tab("["+connectionTreeItem.getValue() +"] "+sqlEditerCount++);    // Full editer Tab  // This will also contain the database name
        
        // ImageView  connectionTypeImageView = new ImageView(new Image(getClass().getResourceAsStream("/graphics/-----+"Logo.png")));
        ImageView  connectionTypeImageView = new ImageView(new Image(getClass().getResourceAsStream("/graphics/duckDBLogo.png")));
        connectionTypeImageView.setFitHeight(10);
        connectionTypeImageView.setFitWidth(10);
        sqlEditerTab.setGraphic(connectionTypeImageView);
        sqlEditerTab.setContent(editerTabSplitPane);  
        
        alltabbedEditors.getTabs().add(sqlEditerTab);  // multiple Editer Tab holder/pane
        
        // Bring focus to this newly created Tab
        SingleSelectionModel<Tab> singleSelectionModel =  alltabbedEditors.getSelectionModel();
        singleSelectionModel.select(sqlEditerTab);
        System.out.println( alltabbedEditors.getSelectionModel().getSelectedIndex());
		System.out.println( alltabbedEditors.getSelectionModel().getSelectedItem().getText() );
		
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
			public void handle(KeyEvent event) {
				toolBarRunButton.setDisable(false);
				System.out.println(sqlEditerTextArea.getText());
				foucesSqlEditerTextArea = sqlEditerTextArea;
			}
		};
	}


	private void addnewSqlEditerCell(Dimension size,ActionEvent event,GridPane vBoxAreaSqlEditer1GridPane,int rowCount,int buttonCount,String sqlEditerofTypeIdFromImage) {
		
		// we need this local gridpane to distinguish between gridpanes from diffeent sql editers
		System.out.println("Add new Cell clicked from "+ ((Button)event.getSource()).getId());
		
		toolBarRunButton.setDisable(true);
		// Box 2
        // A StackPane to hold textArea for querying and buttons on top right corner
		stackPaneAreaButtons = new StackPane();
	    stackPaneAreaButtons.setPrefSize(size.getWidth()-100,50);
	    // a HBox to hold buttons/labels for the text area
        hboxQueryAreaButtons = new HBox(7);
        hboxQueryAreaButtons.setMaxHeight(20);
        hboxQueryAreaButtons.setMaxWidth(60);
        
        Label maximizeQueryAreaButton = new Label();
        ImageView maximizeImage = new ImageView(new Image(getClass().getResourceAsStream("/images/maximize-button.png")));
        maximizeImage.setFitHeight(10);
        maximizeImage.setFitWidth(10);
        maximizeQueryAreaButton.setGraphic(maximizeImage);
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
        hboxQueryAreaButtons.setPadding(new Insets(0,15,0,15));
        hboxQueryAreaButtons.getChildren().addAll(maximizeQueryAreaButton,closureQueryAreaButton);
        StackPane.setAlignment(hboxQueryAreaButtons,Pos.TOP_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonRight,Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(dragQueryAreaButtonLeft,Pos.BOTTOM_LEFT);
        
		sqlEditerTextArea = new TextArea("");
		sqlEditerTextArea.setId(sqlEditerofTypeIdFromImage);
		sqlEditerTextArea.setOnKeyPressed(sqlEditerTextAreaInputHandler(sqlEditerTextArea));
		stackPaneAreaButtons.getChildren().addAll(sqlEditerTextArea,hboxQueryAreaButtons,dragQueryAreaButtonRight,dragQueryAreaButtonLeft);
		vBoxAreaSqlEditer1GridPane.getChildren().add(stackPaneAreaButtons);
		GridPane.setConstraints(stackPaneAreaButtons, 0, rowCount++);   // column 0 row 0 
		
		// Remove previous plus button
		vBoxAreaSqlEditer1GridPane.getChildren().remove(plus_button_borderPane);
		
		plus_button_borderPane = new BorderPane();
		addNewQueryEditerButton = new Button("+");
		addNewQueryEditerButton.setId("Button Id "+buttonCount++);
	 	addNewQueryEditerButton.setOnAction(addNewQueryEditerButtonAction(size, vBoxAreaSqlEditer1GridPane,rowCount, buttonCount,sqlEditerofTypeIdFromImage));
		addNewQueryEditerButton.setFont(new Font(10));
		addNewQueryEditerButton.setMinHeight(15);
		addNewQueryEditerButton.setMaxWidth(50);
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
	
}

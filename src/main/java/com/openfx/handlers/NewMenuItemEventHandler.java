package com.openfx.handlers;

import java.util.ArrayList;
import java.util.Calendar;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.ai.ConnectionsConstants;
import com.openfx.connections.DatabricksConnection;
import com.openfx.connections.DuckDBConnection;
import com.openfx.connections.MariaDBConnection;
import com.openfx.connections.MySqlConnection;
import com.openfx.connections.OracleConnection;
import com.openfx.connections.PostgreeSqlConnection;
import com.openfx.connections.SQLiteConnection;
import com.openfx.connections.SapHanaConnection;
import com.openfx.placeholders.ConnectionPlaceHolder;
import com.openfx.placeholders.HighLightRectangleHolder;
import com.openfx.placeholders.ImageItemsHolder;
import com.openfx.ui.DatabricksUI;
import com.openfx.ui.DuckDBUI;
import com.openfx.ui.MariaDBUI;
import com.openfx.ui.MssqlUI;
import com.openfx.ui.MySqlUI;
import com.openfx.ui.OracleUI;
import com.openfx.ui.PostgreeUI;
import com.openfx.ui.SQLiteUI;
import com.openfx.ui.SapHanaUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewMenuItemEventHandler implements  EventHandler<ActionEvent> {
	
	public Stage connectionStage;
	public Menu_Items_FX menu_Items_FX;
	
	public TabPane connectionDetailsTabs;
	public Tab connectionDetailsTab;
	public Text connectToDatabseText;
	public TextField jdbcConnectionName;
	public TextField jdbcConnectionPort;
	public TextField jdbcUrlTextField;
	public TextField jdbcUrlDatabaseNameField;
	public TextField jdbcAuthenticationUsernameTextField;
	public PasswordField jdbcAuthenticationPasswordField;
	public Button buttonTestConnection;
	public Button buttonBack;
	public Button buttonNext;
	public Button buttonFinish;
	public Button buttonCancel;
	
	public ArrayList<Rectangle> avaialbleHighRectangleConnections = new ArrayList<Rectangle>();  
	private HighLightRectangleHolder highLightRectangleHolder = new HighLightRectangleHolder(avaialbleHighRectangleConnections);
	
	
	double olddragX = 0.0;
	double olddragY = 0.0;
	double newdragX = 0.0;
	double newdragY = 0.0;
	boolean isYTopdrag = false;
	boolean isYBottomdrag = false;
	boolean isXLeftdrag = false;
	boolean isXRightdrag = false;
	boolean isResizeCmd = false;
	
	boolean isMaximized = false;
	
	public MySqlUI mySqlUI;
	public SQLiteUI sqLiteUI;
	public PostgreeUI postgreeUI;
	public SapHanaUI sapHanaUI;
	public DatabricksUI databricksUI;
	public OracleUI oracleUI;
	public MssqlUI mssqlUI;
	public DuckDBUI duckDBUI;
	public MariaDBUI mariaDBUI;
		
	public NewMenuItemEventHandler(Menu_Items_FX menu_Items_FX) {
		this.menu_Items_FX = menu_Items_FX;
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		/*
		if(fxPanel !=null & frame != null) {
			frame.setVisible(true);
			frame.toFront();
			return;
		}*/
		
	    // Used to put JavaFX components in      
		//fxPanel = new JFXPanel();
		
		//frame = new JFrame("Connect to a database");
		//frame.setContentPane(fxPanel);
		
		menu_Items_FX.newMenuItemEventHandler = this; // this class being referenced in Menu_Items_FX class(main class)
	
		menu_Items_FX.borderSelectDatabase = new BorderPane();
		    
		VBox vBoxTop = new VBox();
		
		BorderPane borderPaneProperties = new BorderPane(); 
		borderPaneProperties.setStyle("-fx-background-color : white");  // Top most 

		borderPaneProperties.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			
				olddragX = event.getSceneX();olddragY = event.getSceneY();
			//	System.out.println("PRessed X "+olddragX);
			//	System.out.println("PRessed Y "+olddragY);
			//	System.out.println("connection Stage X on pressed"+ connectionStage.getX());
			//	System.out.println("connection Stage Y on pressed"+ connectionStage.getY());

				
			}
		});
		
		borderPaneProperties.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				newdragX = event.getSceneX();newdragY = event.getSceneY();
				if(!isResizeCmd) {
					System.out.println("Normal drag accured ");
					connectionStage.setX(connectionStage.getX() + (newdragX - olddragX) );
					connectionStage.setY(connectionStage.getY() + (newdragY - olddragY) );
				}
				//System.out.println("connection Stage X on pressed"+ connectionStage.getX());
				//System.out.println("connection Stage Y on pressed"+ connectionStage.getY());

				
			}
		});
		
		
		Label connectToLabel = new Label("Connecto to Database");
		connectToLabel.setPadding(new Insets(0, 0, 2, 2));
		borderPaneProperties.setLeft(connectToLabel);
		
		/*
		HBox boxButtons = new HBox();
		Button middleButton = new Button();
	    ImageView  maximizeImage = new ImageView(new Image(getClass().getResourceAsStream("/images/maximize.png")));
	    maximizeImage.setFitWidth(12);
	    maximizeImage.setFitHeight(12);
	    Button minimizeButton = new Button();
	    minimizeButton.setStyle("-fx-background-color : transparent");
	    ImageView  minimizeImage = new ImageView(new Image(getClass().getResourceAsStream("/images/restore_down.png")));
	    minimizeImage.setFitWidth(15);
	    minimizeImage.setFitHeight(15);
	    minimizeButton.setGraphic(minimizeImage);
	    
	    middleButton.setGraphic(maximizeImage);
	    middleButton.setStyle("-fx-background-color : transparent");
	    middleButton.setMinWidth(30);
		boxButtons.getChildren().add(middleButton);
		
		Button closeButton = new Button("X");
		closeButton.setStyle("-fx-background-color : transparent");
		closeButton.setMinWidth(30);
		boxButtons.getChildren().add(closeButton);
		  */
		
	//	borderPaneProperties.setRight(boxButtons);
		  
		HBox hbox = addHBox();
		
		vBoxTop.getChildren().add(borderPaneProperties); // disabled the top bar above menu for now
		vBoxTop.getChildren().add(hbox);
		  
		menu_Items_FX.borderSelectDatabase.setTop(vBoxTop);
		menu_Items_FX.borderSelectDatabase.setLeft(addVBox());   
		  
		// Add a stack to the HBox in the top region
		addStackPane(hbox);  
	        
		menu_Items_FX.borderSelectDatabase.setCenter(addFlowPane());
	      
	    BorderPane borderPaneBottom = addHBoxBottom();
	    menu_Items_FX.borderSelectDatabase.setBottom(borderPaneBottom);
	    menu_Items_FX.sceneDataBaseConnection = new Scene(menu_Items_FX.borderSelectDatabase,800,600);
	    
	    
	    menu_Items_FX.sceneDataBaseConnection.getStylesheets().add(getClass().getResource("/layoutstyles.css").toExternalForm());
	    
	    connectionStage = new Stage();
	    connectionStage.initModality(Modality.APPLICATION_MODAL);
	    connectionStage.initOwner(menu_Items_FX.primaryStage.getScene().getWindow());
	    
	    connectionStage.setScene(menu_Items_FX.sceneDataBaseConnection);
	    // Commenting the below for now
	    //  connectionStage.initStyle(StageStyle.TRANSPARENT);  // remove the top head of the scene
	    connectionStage.show();
	    
	/*    closeButton.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				connectionStage.close();
			}
		});
	    */
//	    middleButton.addEventHandler(MouseEvent.MOUSE_PRESSED, middleButtonClicked(middleButton, minimizeImage,maximizeImage));
	    
	    menu_Items_FX.sceneDataBaseConnection.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// get the mouse co -ordinates
				//System.out.println("Mouse Co-or X"+event.getX());
				//System.out.println("Mouse Co-or Y" +event.getY());
			
				if(event.getX() < 10  )  {
					menu_Items_FX.sceneDataBaseConnection.setCursor(Cursor.W_RESIZE);
					isXLeftdrag = true;
					isXRightdrag = false;
					isResizeCmd = true;
				}
				else if((connectionStage .getWidth() - event.getX()) < 10) {
					menu_Items_FX.sceneDataBaseConnection.setCursor(Cursor.W_RESIZE);
					isXLeftdrag = false;
					isXRightdrag = true;
					isResizeCmd = true;
				}
				else if(event.getY() < 10 )
				{
					menu_Items_FX.sceneDataBaseConnection.setCursor(Cursor.N_RESIZE);
					isYTopdrag = true;
					isYBottomdrag = false;
					isResizeCmd = true;
				}
				else if((connectionStage .getHeight() - event.getY()) < 10 ) 
				{
					menu_Items_FX.sceneDataBaseConnection.setCursor(Cursor.N_RESIZE);
					isYTopdrag = false;
					isYBottomdrag = true;
					isResizeCmd = true;
				}
				else 
				{
					menu_Items_FX.sceneDataBaseConnection.setCursor(Cursor.DEFAULT);
					isXLeftdrag=false;
					isXRightdrag = false;
					isYTopdrag = false;
					isYBottomdrag = false;
					isResizeCmd = false;
				}	
			}
		});
	    
	    menu_Items_FX.sceneDataBaseConnection.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				if(event.getX() < 10 || (connectionStage .getWidth() - event.getX()) < 10 )  {
					olddragX = event.getSceneX();olddragY = event.getSceneY();
				
				}else if( (connectionStage .getHeight() - event.getY()) < 10 || event.getY() < 10 ) {
					olddragX = event.getSceneX();olddragY = event.getSceneY();
				
				}
			}
		});
		
	    
	    menu_Items_FX.sceneDataBaseConnection.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				newdragX = event.getSceneX();newdragY = event.getSceneY();
				if(isYTopdrag) {
					connectionStage.setHeight((connectionStage.getHeight() + (olddragY- newdragY) ));
					connectionStage.setY(connectionStage.getY() - (olddragY - newdragY) );   // minus bcoz as we go up it gets minus co ordinates asn vice-versa
				}	
				else if(isYBottomdrag) {
					connectionStage.setHeight(connectionStage.getHeight() + ( newdragY - olddragY ) );
		
				}	
				else if(isXLeftdrag) {
					connectionStage.setWidth((connectionStage.getWidth() + (olddragX- newdragX) ));
					connectionStage.setX(connectionStage.getX() - (olddragX - newdragX) );   // minus bcoz as we go up it gets minus co ordinates asn vice-versa
				}
				else if(isXRightdrag) {
					connectionStage.setWidth(connectionStage.getWidth() + ( newdragX - olddragX ) );
					
				}	
				
				isYTopdrag = false;
				isYBottomdrag = false;
				isXLeftdrag = false;
				isXRightdrag=false;
				isResizeCmd = false;
			}
		});

		
	}

	private EventHandler<MouseEvent> middleButtonClicked(Button middleButton, ImageView minimizeImage, ImageView maximizeImage) {
		return new EventHandler<MouseEvent>() {
	 			@Override
	 			public void handle(MouseEvent mouseEvent) {
	 				if(isMaximized) {
	 					connectionStage.setScene(menu_Items_FX.sceneDataBaseConnection);
	 					connectionStage.setMaximized(false);
	 					middleButton.setGraphic(maximizeImage);
	 					isMaximized = false;
	 				}else {
	 					connectionStage.setMaximized(true);
	 					connectionStage.setIconified(false);
	 					middleButton.setGraphic(minimizeImage);
	 					isMaximized = true;
	 				}
	 			}
	 	};
	}

	
	 public HBox addHBox() {

		 
	        HBox hbox = new HBox();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #336699;");
	// Use style class to set properties previously set above (with some changes)      
	        hbox.getStyleClass().add("hbox");
	        
	        connectToDatabseText = new Text("Select your database to connect");
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	    }
	  
	  
	  public BorderPane addHBoxBottom() {
		  
		  BorderPane borderPane = new BorderPane();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #336699;");
	// Use style class to set properties previously set above (with some changes)      
		    borderPane.getStyleClass().add("hbox");
	        
	        buttonTestConnection = new Button("Test Connection");
	        buttonTestConnection.setPrefSize(100, 20);
	        buttonTestConnection.setDisable(true);
	        buttonTestConnection.setOnMousePressed(onbuttonTestConnectionMousePressed());
	        
	        buttonBack = new Button("< Back");
	        buttonBack.setPrefSize(100, 20);
	        buttonBack.setDisable(true);
	        buttonNext = new Button(" Next >");
	        buttonNext.setPrefSize(100, 20);
	        buttonNext.setDisable(true);
	        buttonFinish = new Button("Finish");
	        buttonFinish.setPrefSize(100, 20);
	        buttonFinish.setDisable(true);
	        buttonCancel = new Button("Cancel");
	        buttonCancel.setPrefSize(100, 20);
	        
	        HBox hboxConnectionButtons = new HBox();
	        hboxConnectionButtons.setSpacing(5);
	        hboxConnectionButtons.getChildren().addAll(buttonBack,buttonNext,buttonFinish,buttonCancel); 
	      
	        borderPane.setLeft(buttonTestConnection);
	        
	        borderPane.setRight(hboxConnectionButtons);
	        	 
	        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
                @Override
	        	public void handle(ActionEvent event) {
					
					connectToDatabseText.setText(menu_Items_FX.currentConnectionSelected + " connection settings");
					menu_Items_FX.borderSelectDatabase.setCenter(addConnectionDetails());
					
					System.out.println("Selected Connection is "+menu_Items_FX.currentConnectionSelected);
					buttonNext.setDisable(true);
					buttonTestConnection.setDisable(true);
					buttonBack.setDisable(false);
				}
	        });

	        
	        buttonBack.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
				public void handle(ActionEvent event) {			
					connectToDatabseText.setText("Select your database to connect ");
					menu_Items_FX.currentConnectionSelected = "";
					menu_Items_FX.borderSelectDatabase.setCenter(menu_Items_FX.selectDatabaseConnectionsflow);
					buttonNext.setDisable(true);
					buttonTestConnection.setDisable(true);
					buttonBack.setDisable(true);
					buttonFinish.setDisable(true);
					
					// when backButton click clear previously selected Database
					for(Rectangle avaialbleHighRectangleConnection : avaialbleHighRectangleConnections) {	
						System.out.println( avaialbleHighRectangleConnection.getClass().getName());
						avaialbleHighRectangleConnection.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
					            new Stop[]{
					            new Stop(0,Color.WHITE),
					            new Stop(0.5, Color.WHITE),
					            new Stop(1,Color.WHITE),}));
						avaialbleHighRectangleConnection.setStroke(Color.WHITE);
						avaialbleHighRectangleConnection.setArcHeight(3.5);
						avaialbleHighRectangleConnection.setArcWidth(3.5);
					}
						
				}
			});

	        
	        buttonFinish.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
				public void handle(ActionEvent event) {
					
					System.out.println(" Finish pressed "+menu_Items_FX.currentConnectionSelected);

					if(menu_Items_FX.currentConnectionSelected.equals("MySql"))
					{
						createMySQLConnection();
						connectionStage.close();
					}
					
					if(menu_Items_FX.currentConnectionSelected.equals("PostgreeSql"))
					{
						createPostgreeConnection();
						connectionStage.close();
					}
					if(menu_Items_FX.currentConnectionSelected.equals("SQLite"))
					{
						createSQLiteConnection();
						connectionStage.close();
					}
					if(menu_Items_FX.currentConnectionSelected.equals("SapHana"))
					{
						createSapHanaConnection();
						connectionStage.close();
					}
					if(menu_Items_FX.currentConnectionSelected.equals("DataBricks"))
					{
						createDataBricksConnection();
						connectionStage.close();
					}
					if(menu_Items_FX.currentConnectionSelected.equals("oracle"))
					{
						createOracleConnection();
						connectionStage.close();
					}
					if(menu_Items_FX.currentConnectionSelected.equals("MSSQLSErver"))
					{
						// createMSSQLConnection();
						connectionStage.close();
					}
					if(menu_Items_FX.currentConnectionSelected.equals("DuckDB"))
					{
						createDuckDBConnection();
						connectionStage.close();
					}
					if(menu_Items_FX.currentConnectionSelected.equals("MariaDB"))
					{
						createMariaDBConnection();
						connectionStage.close();
					}
					menu_Items_FX.currentConnectionSelected = "";
				}
				
	
			});

	        
	        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
				public void handle(ActionEvent event) {
					
					connectionStage.close();
				}
	        });

	        
	        return borderPane;
	  }
	


	public VBox addVBox() {
	        
	        VBox vbox = new VBox();
//	        vbox.setPadding(new Insets(10)); // Set all sides to 10
//	        vbox.setSpacing(8);              // Gap between nodes
	// Use style classes to set properties previously set above        
	        vbox.getStyleClass().addAll("pane", "vbox");

	        Text title = new Text("Data");
	        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	        vbox.getChildren().add(title);
	        
	        Hyperlink options[] = new Hyperlink[] {
	            new Hyperlink("Sales"),
	            new Hyperlink("Marketing"),
	            new Hyperlink("Distribution"),
	            new Hyperlink("Costs")};

	        for (int i=0; i<4; i++) {
	            // Add offset to left side to indent from title
	            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
	            vbox.getChildren().add(options[i]);
	        }
	        
	        return vbox;
	    }
	  
	  public void addStackPane(HBox hb) {

	        StackPane stackpane = new StackPane();
	        Rectangle helpIcon = new Rectangle(30.0, 25.0);
	        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	            new Stop[]{
	            new Stop(0,Color.web("#4977A3")),
	            new Stop(0.5, Color.web("#B0C6DA")),
	            new Stop(1,Color.web("#9CB6CF")),}));
	        helpIcon.setStroke(Color.web("#D0E6FA"));
	        helpIcon.setArcHeight(3.5);
	        helpIcon.setArcWidth(3.5);
	        
	        Text helpText = new Text("?");
	        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	        helpText.setFill(Color.WHITE);
	        helpText.setStroke(Color.web("#7080A0")); 

	        stackpane.getChildren().addAll(helpIcon, helpText);
	        stackpane.setAlignment(Pos.CENTER_RIGHT);
	        // Add offset to right for question mark to compensate for RIGHT 
	        // alignment of all nodes
	        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));
	        
	        hb.getChildren().add(stackpane);
	        HBox.setHgrow(stackpane, Priority.ALWAYS);
	                
	    }

	  public FlowPane addFlowPane() {
	 
		  menu_Items_FX.currentConnectionSelected = "";

		  menu_Items_FX.selectDatabaseConnectionsflow = new FlowPane();
		  menu_Items_FX.selectDatabaseConnectionsflow.setPadding(new Insets(5, 0, 5, 0));
		  menu_Items_FX.selectDatabaseConnectionsflow.setVgap(10);
		  menu_Items_FX.selectDatabaseConnectionsflow.setHgap(20);
	        //  flow.setStyle("-fx-background-color: DAE6F3;");
	        //  Use style classes to set properties previously set above        
		  menu_Items_FX.selectDatabaseConnectionsflow.getStyleClass().addAll("databasesflowPane");
		  menu_Items_FX.selectDatabaseConnectionsflow.setPrefWrapLength(170); // preferred width allows for two columns

		  // 
		  StackPane stackPaneMySql = highLightRectangleHolder.getHighlightRectangleMySql(null);
		  StackPane stackPanePostgreeSql = highLightRectangleHolder.getHighlightRectanglePostgreeSql(null);
		  StackPane stackPaneSqlite =highLightRectangleHolder.getHighlightRectangleSqlite(null);
		  StackPane stackPanedatabricks =highLightRectangleHolder.getHighlightRectangleDatabricks(null);
		  StackPane stackPaneSapHana = highLightRectangleHolder.getHighlightRectanglesaphana(null);
		  StackPane stackPaneOracle = highLightRectangleHolder.getHighlightRectangleOracle(null);
		  StackPane stackPaneMssql = highLightRectangleHolder.getHighlightRectangleMSSQLServer(null);
		  StackPane stackPaneDuckDB = highLightRectangleHolder.getHighlightRectangleDuckDB(null);
		  StackPane stackPaneMariaDB = highLightRectangleHolder.getHighlightRectangleMariaDB(null);
		  
	      menu_Items_FX.selectDatabaseConnectionsflow.getChildren().add(stackPaneMySql);
    
	      menu_Items_FX.selectDatabaseConnectionsflow.getChildren().add(stackPanePostgreeSql);
     
		  menu_Items_FX.selectDatabaseConnectionsflow.getChildren().add(stackPaneSqlite);
	          
	      menu_Items_FX.selectDatabaseConnectionsflow.getChildren().add(stackPanedatabricks);
	        
	      menu_Items_FX.selectDatabaseConnectionsflow.getChildren().add(stackPaneSapHana);
	        
		  menu_Items_FX.selectDatabaseConnectionsflow.getChildren().add(stackPaneOracle);
	 
		  menu_Items_FX.selectDatabaseConnectionsflow.getChildren().add(stackPaneMssql);
	
		  menu_Items_FX.selectDatabaseConnectionsflow.getChildren().add(stackPaneDuckDB);

		  menu_Items_FX.selectDatabaseConnectionsflow.getChildren().add(stackPaneMariaDB);
		    
		    
		    
		    /* ****************************** */
		    // Actions on hovering over the database logo's 
	        
		  stackPaneMySql.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMySql.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPaneMySql.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMySql.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPaneMySql.setOnMouseExited(new HighLightRectangleMouseEventHandler((Rectangle)stackPaneMySql.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
	
		  stackPanePostgreeSql.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanePostgreeSql.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPanePostgreeSql.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanePostgreeSql.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPanePostgreeSql.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanePostgreeSql.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
			 
		  stackPaneSqlite.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSqlite.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPaneSqlite.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSqlite.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPaneSqlite.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSqlite.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
			   
		  stackPanedatabricks.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanedatabricks.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPanedatabricks.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanedatabricks.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPanedatabricks.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanedatabricks.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));

		  stackPaneSapHana.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSapHana.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPaneSapHana.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSapHana.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPaneSapHana.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSapHana.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));

		  stackPaneOracle.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneOracle.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPaneOracle.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneOracle.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPaneOracle.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneOracle.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
 
		  stackPaneMssql.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMssql.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		  stackPaneMssql.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMssql.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));	  
		  stackPaneMssql.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMssql.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));

		  stackPaneDuckDB.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneDuckDB.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));		 
		  stackPaneDuckDB.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneDuckDB.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));  
		  stackPaneDuckDB.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneDuckDB.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));
		
		  stackPaneMariaDB.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMariaDB.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));		 
		  stackPaneMariaDB.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMariaDB.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));  
		  stackPaneMariaDB.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMariaDB.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,this,null));

		        
	        BackgroundFill background_fill = new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ); 
	        // create Background 
	        Background background = new Background(background_fill); 
	        menu_Items_FX.selectDatabaseConnectionsflow.setBackground(background);
	        return menu_Items_FX.selectDatabaseConnectionsflow;
	    }
	  
	  private TabPane addConnectionDetails() {
		  
		  connectionDetailsTabs = new TabPane();
		  connectionDetailsTabs.getStyleClass().addAll("databasesflowPane");  // box for the connection tabbed pane
		  connectionDetailsTab = new Tab("Connection");
		  connectionDetailsTab.setClosable(false);
		  
		  connectionDetailsTab.setContent(addConnectionCredentials()); // will set fields to connectionDetailsTab
	  
		  Tab driverPropertiesTab = new Tab("Driver Properties");
		  driverPropertiesTab.setClosable(false);		  
		  
		  Tab sshDetailsTab = new Tab("SSH");
		  sshDetailsTab.setClosable(false);
		  
		  Tab proxyDetailsTab = new Tab("Proxy");
		  proxyDetailsTab.setClosable(false);
		  
		  Tab sslDetailsTab = new Tab("SSL");
		  sslDetailsTab.setClosable(false);
		  
		  
		  connectionDetailsTabs.getTabs().addAll(connectionDetailsTab,driverPropertiesTab,sshDetailsTab,proxyDetailsTab,sslDetailsTab);
		  
		  return connectionDetailsTabs;
	  }
	  
	  private VBox addConnectionCredentials() {
		  
		  VBox connectionDetailsVbox = null;
		  
		  if(menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(ConnectionsConstants.MySql)) {
			  
			  if(mySqlUI == null)
				  mySqlUI = new MySqlUI(menu_Items_FX,this);
			  
			  connectionDetailsVbox =  mySqlUI.addConnectionCredentials();  
			   
		  }
		 
		  if(menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(ConnectionsConstants.Sqlite)) {
			  
			  if(sqLiteUI == null)
				  sqLiteUI = new SQLiteUI(menu_Items_FX,this);
			  
			  connectionDetailsVbox =  sqLiteUI.addConnectionCredentials();  
			   
		  }
		  if(menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(ConnectionsConstants.PostgreeSql)) {
			  
			  if(postgreeUI == null)
				  postgreeUI = new PostgreeUI(menu_Items_FX,this);
			  
			  connectionDetailsVbox =  postgreeUI.addConnectionCredentials();  
			   
		  }
		  if(menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(ConnectionsConstants.SapHana)) {
	  
			  if(sapHanaUI == null)
				  sapHanaUI = new SapHanaUI(menu_Items_FX,this);
	  
			  connectionDetailsVbox =  sapHanaUI.addConnectionCredentials();  
	   
		  }
		 
		  if(menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(ConnectionsConstants.Databricks)) {
			  
			  if(databricksUI == null)
				  databricksUI = new DatabricksUI(menu_Items_FX,this);
	  
			  connectionDetailsVbox =  databricksUI.addConnectionCredentials();  
	   
		  }
		  if(menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(ConnectionsConstants.Oracle)) {
			  
			  if(oracleUI == null)
				  oracleUI = new OracleUI(menu_Items_FX,this);
	  
			  connectionDetailsVbox =  oracleUI.addConnectionCredentials();  
	   
		  }
		  if(menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(ConnectionsConstants.MSSQLSErver)) {
			  
			  if(mssqlUI == null)
				  mssqlUI = new MssqlUI(menu_Items_FX,this);
	  
			  connectionDetailsVbox =  mssqlUI.addConnectionCredentials();  
		  }
		  if(menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(ConnectionsConstants.DuckDB)) {
	  
			  if(duckDBUI == null)
				  duckDBUI = new DuckDBUI(menu_Items_FX,this);

			  connectionDetailsVbox =  duckDBUI.addConnectionCredentials(); 
		  }
		  if(menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(ConnectionsConstants.MariaDB)) {
			  
			  if(mariaDBUI == null)
				  mariaDBUI = new MariaDBUI(menu_Items_FX,this);
			  
			  connectionDetailsVbox =  mariaDBUI.addConnectionCredentials(); 
		  }
		 
		  
		 
		  return connectionDetailsVbox;
	  }

	public EventHandler<KeyEvent> onjdbcAuthenticationPasswordFieldPressed() {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				 
				//System.out.println(jdbcAuthenticationPasswordField.getText());
				
			}
		};
	}

	public EventHandler<KeyEvent> onjdbcAuthenticationUsernameTextFieldPressed() {		
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				 
			//	System.out.println(jdbcAuthenticationUsernameTextField.getText());
				
			}
		};
	}
	


	public EventHandler<KeyEvent> onjdbcUrlTextFieldKeyPressed() {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				 
				// System.out.println(jdbcUrlTextField.getText());
				if(jdbcUrlTextField.getText() != null && jdbcUrlTextField.getText().trim().length() > 0) {
					buttonFinish.setDisable(false);
					buttonTestConnection.setDisable(false);
				}
				else {
					buttonFinish.setDisable(true);
					buttonTestConnection.setDisable(true);
				}
			}
		};
	}
	
	private EventHandler<MouseEvent> onbuttonTestConnectionMousePressed() {
			return new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {		
					createMySQLConnection();
					connectionStage.close();

				}
			};
	}

	private void createMySQLConnection() {
		jdbcUrlTextField.getText();
		jdbcAuthenticationUsernameTextField.getText();
		jdbcAuthenticationPasswordField.getText();
		jdbcUrlDatabaseNameField.getText();
		// check if database name is provided  and then create connection accordingly and reflect accordingly
		
		// @TODO Add validation here
		
		try
		{
			MySqlConnection mySqlConnection;
			if(jdbcUrlDatabaseNameField.getText().isEmpty())
				mySqlConnection = new MySqlConnection(jdbcUrlTextField.getText().trim(), jdbcAuthenticationUsernameTextField.getText().trim(), 
				jdbcAuthenticationPasswordField.getText().trim(),jdbcConnectionPort.getText().trim());	
			else {
				mySqlConnection = new MySqlConnection(jdbcUrlTextField.getText().trim(), jdbcUrlDatabaseNameField.getText(),jdbcAuthenticationUsernameTextField.getText().trim(), 
						jdbcAuthenticationPasswordField.getText().trim(),jdbcConnectionPort.getText().trim());
			}
			System.out.println(mySqlConnection.getMySqlConnection());
			ConnectionPlaceHolder connectionPlaceHolder = new ConnectionPlaceHolder();
			connectionPlaceHolder.setConnectionType(mySqlConnection.getClass().getSimpleName());
			connectionPlaceHolder.setConnectionName(jdbcConnectionName.getText());
			connectionPlaceHolder.setConnectionCreationDate(Calendar.getInstance().getTime());
			
			// for time keeping purpose 
			menu_Items_FX.connectedDatabasesNames.getItems().add(jdbcConnectionName.getText());
			menu_Items_FX.mySqlConnectionsMap.put(connectionPlaceHolder, mySqlConnection.getMySqlConnection());
			menu_Items_FX.currentOpenConnectionsMap.put(connectionPlaceHolder, mySqlConnection.getMySqlConnection());
			
			reflectMySqlrootTreeView(connectionPlaceHolder,jdbcUrlDatabaseNameField.getText());
			
		}catch(Exception ex) {
			System.out.println("MySQL Connection failed");
		}
	}
	
	private void reflectMySqlrootTreeView(ConnectionPlaceHolder connectionPlaceHolder,String databaseName) {
		//put the connection on treeview display and add the connection to connectionName,connectionObject Datatype
				
		ImageView imageMySQLnode = ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName());

		ImageView imageDatbaseTablenode = ImageItemsHolder.getTableImage();
		
		TreeItem<String> mySQLTreeItem = new MySqlUI(menu_Items_FX,this).getmySqlTreeItem(connectionPlaceHolder,imageMySQLnode,imageDatbaseTablenode,databaseName);
		menu_Items_FX.rootConnectionItem.getChildren().add(mySQLTreeItem);
	}

	private void createPostgreeConnection()
	{
		
		jdbcUrlTextField.getText();
		jdbcAuthenticationUsernameTextField.getText();
		jdbcAuthenticationPasswordField.getText();
		
		// @TODO Add validation here
		
		try
		{
			// Postgree sql connection for a specific database only 
			
			PostgreeSqlConnection postgreeSQLConnection = new PostgreeSqlConnection(jdbcUrlTextField.getText().trim(), jdbcAuthenticationUsernameTextField.getText().trim(), 
				jdbcAuthenticationPasswordField.getText().trim(),true);		
			System.out.println(postgreeSQLConnection.getPostgreeSqlConnection());
			
			ConnectionPlaceHolder connectionPlaceHolder = new ConnectionPlaceHolder();
			connectionPlaceHolder.setConnectionName(jdbcConnectionName.getText());
			connectionPlaceHolder.setConnectionType(postgreeSQLConnection.getClass().getSimpleName());
			connectionPlaceHolder.setConnectionCreationDate(Calendar.getInstance().getTime());
			
			// for time keeping purpose 
			menu_Items_FX.connectedDatabasesNames.getItems().add(jdbcConnectionName.getText());
			menu_Items_FX.postgreeSqlConnectionsMap.put(connectionPlaceHolder, postgreeSQLConnection.getPostgreeSqlConnection());
			menu_Items_FX.currentOpenConnectionsMap.put(connectionPlaceHolder, postgreeSQLConnection.getPostgreeSqlConnection());
			
			reflectPostgreerootTreeView(connectionPlaceHolder);
			
		}catch(Exception ex) {
			System.out.println("Postgree Connection failed");
		}
	}
	
	private void reflectPostgreerootTreeView(ConnectionPlaceHolder connectionPlaceHolder) {
		
			    //put the connection on treeview display and add the connection to connectionName,connectionObject Datatype
				
				ImageView imagePostgreeSqlnode = ImageItemsHolder.getPostgreeSqlImage(connectionPlaceHolder.getConnectionName());

				ImageView imageDatbaseTablenode = ImageItemsHolder.getTableImage();
				
				
				TreeItem<String> postgreeSQLTreeItem = new TreeItem<String>(connectionPlaceHolder.getConnectionName(),imagePostgreeSqlnode);
				
				TreeItem<String> postgreeSQLTreeItemTables = new TreeItem<String>("Tables",imageDatbaseTablenode);
				postgreeSQLTreeItemTables.getChildren().add(new TreeItem<String>("Loading.."));
				
				TreeItem<String> postgreeSQLTreeItemViews = new TreeItem<String>("Views");
				postgreeSQLTreeItemViews.getChildren().add(new TreeItem<String>("Loading.."));
				
				TreeItem<String> postgreeSQLTreeItemProcedures = new TreeItem<String>("Procedures");
				postgreeSQLTreeItemProcedures.getChildren().add(new TreeItem<String>("Loading.."));
				
				postgreeSQLTreeItem.getChildren().add(postgreeSQLTreeItemTables);
				postgreeSQLTreeItem.getChildren().add(postgreeSQLTreeItemViews);
				postgreeSQLTreeItem.getChildren().add(postgreeSQLTreeItemProcedures);
				menu_Items_FX.rootConnectionItem.getChildren().add(postgreeSQLTreeItem);
	}
	
	private void createSQLiteConnection() {
	
		jdbcUrlTextField.getText();
		jdbcAuthenticationUsernameTextField.getText();
		jdbcAuthenticationPasswordField.getText();
		
		// @TODO Add validation here
		
		try
		{
			// Postgree sql connection for a specific database only 
			
			SQLiteConnection sQLiteConnection = new SQLiteConnection(jdbcUrlTextField.getText().trim());		
			System.out.println(sQLiteConnection.getSqliteConnection());
			
			ConnectionPlaceHolder connectionPlaceHolder = new ConnectionPlaceHolder();
			connectionPlaceHolder.setConnectionName(jdbcConnectionName.getText());
			connectionPlaceHolder.setConnectionType(sQLiteConnection.getClass().getSimpleName());
			connectionPlaceHolder.setConnectionCreationDate(Calendar.getInstance().getTime());
			
			// for time keeping purpose 
			menu_Items_FX.connectedDatabasesNames.getItems().add(jdbcConnectionName.getText());
			menu_Items_FX.sqliteConnectionsMap.put(connectionPlaceHolder, sQLiteConnection.getSqliteConnection());
			menu_Items_FX.currentOpenConnectionsMap.put(connectionPlaceHolder, sQLiteConnection.getSqliteConnection());
			
			
			reflectSQLiteTreeView(connectionPlaceHolder);
			
		}catch(Exception ex) {
			System.out.println("SQLite Connection failed");
		}
		
	}

	private void reflectSQLiteTreeView(ConnectionPlaceHolder connectionPlaceHolder) {
		//put the connection on treeview display and add the connection to connectionName,connectionObject Datatype
		
		ImageView imageSQLitenode = ImageItemsHolder.getSqliteImage(connectionPlaceHolder.getConnectionName());

		ImageView imageDatbaseTablenode = ImageItemsHolder.getTableImage();
		
		
		TreeItem<String> sQLiteTreeItem = new TreeItem<String>(connectionPlaceHolder.getConnectionName(),imageSQLitenode);
		
		TreeItem<String> sQLiteTreeItemLTreeItemTables = new TreeItem<String>("Tables",imageDatbaseTablenode);
		sQLiteTreeItemLTreeItemTables.getChildren().add(new TreeItem<String>("Loading.."));
		
		TreeItem<String> sQLiteTreeItemViews = new TreeItem<String>("Views");
		sQLiteTreeItemViews.getChildren().add(new TreeItem<String>("Loading.."));
		
		TreeItem<String> sQLiteTreeItemProcedures = new TreeItem<String>("Procedures");
		sQLiteTreeItemProcedures.getChildren().add(new TreeItem<String>("Loading.."));
		
		sQLiteTreeItem.getChildren().add(sQLiteTreeItemLTreeItemTables);
		sQLiteTreeItem.getChildren().add(sQLiteTreeItemViews);
		sQLiteTreeItem.getChildren().add(sQLiteTreeItemProcedures);
		menu_Items_FX.rootConnectionItem.getChildren().add(sQLiteTreeItem);
	}
	
	private void createSapHanaConnection() {
		
		jdbcUrlTextField.getText();
		jdbcAuthenticationUsernameTextField.getText();
		jdbcAuthenticationPasswordField.getText();
		
		// @TODO Add validation here
		
		try
		{
			// Postgree sql connection for a specific database only 
			
			SapHanaConnection sapHanaConnection = new SapHanaConnection(jdbcUrlTextField.getText().trim());		
			System.out.println(sapHanaConnection.getSapHanaConnection());
			
			ConnectionPlaceHolder connectionPlaceHolder = new ConnectionPlaceHolder();
			connectionPlaceHolder.setConnectionName(jdbcConnectionName.getText());
			connectionPlaceHolder.setConnectionType(sapHanaConnection.getClass().getSimpleName());
			connectionPlaceHolder.setConnectionCreationDate(Calendar.getInstance().getTime());
			
			// for time keeping purpose 
			menu_Items_FX.connectedDatabasesNames.getItems().add(jdbcConnectionName.getText());
			menu_Items_FX.sqliteConnectionsMap.put(connectionPlaceHolder, sapHanaConnection.getSapHanaConnection());
			menu_Items_FX.currentOpenConnectionsMap.put(connectionPlaceHolder, sapHanaConnection.getSapHanaConnection());
			
			reflectSapHanaTreeView(connectionPlaceHolder);
			
		}catch(Exception ex) {
			System.out.println("SapHana Connection failed");
		}
		
	}

	private void reflectSapHanaTreeView(ConnectionPlaceHolder connectionPlaceHolder) {
		
		//put the connection on treeview display and add the connection to connectionName,connectionObject Datatype
					
		ImageView imageSapHananode = ImageItemsHolder.getSaphanaImage(connectionPlaceHolder.getConnectionName());
		
		ImageView imageDatbaseTablenode = ImageItemsHolder.getTableImage();
						
		TreeItem<String> sapHanaTreeItem = new TreeItem<String>(connectionPlaceHolder.getConnectionName(),imageSapHananode);
				
		TreeItem<String> sapHanaTreeItemLTreeItemTables = new TreeItem<String>("Tables",imageDatbaseTablenode);
		sapHanaTreeItemLTreeItemTables.getChildren().add(new TreeItem<String>("Loading.."));
				
		TreeItem<String> sapHanaTreeItemViews = new TreeItem<String>("Views");
		sapHanaTreeItemViews.getChildren().add(new TreeItem<String>("Loading.."));
				
		TreeItem<String> sapHanaTreeItemProcedures = new TreeItem<String>("Procedures");
		sapHanaTreeItemProcedures.getChildren().add(new TreeItem<String>("Loading.."));
				
		sapHanaTreeItem.getChildren().add(sapHanaTreeItemLTreeItemTables);
		sapHanaTreeItem.getChildren().add(sapHanaTreeItemViews);
		sapHanaTreeItem.getChildren().add(sapHanaTreeItemProcedures);
		menu_Items_FX.rootConnectionItem.getChildren().add(sapHanaTreeItem);
		
	}

	private void createOracleConnection() {

		jdbcUrlTextField.getText();
		jdbcAuthenticationUsernameTextField.getText();
		jdbcAuthenticationPasswordField.getText();
		
		// @TODO Add validation here
		
		try
		{
			// Postgree sql connection for a specific database only 
			
			OracleConnection oracleConnection = new OracleConnection(jdbcUrlTextField.getText().trim(),
					jdbcAuthenticationUsernameTextField.getText().trim(),jdbcAuthenticationPasswordField.getText().trim());		
			System.out.println(oracleConnection.getOracleConnection());
			
			ConnectionPlaceHolder connectionPlaceHolder = new ConnectionPlaceHolder();
			connectionPlaceHolder.setConnectionName(jdbcConnectionName.getText());
			connectionPlaceHolder.setConnectionType(oracleConnection.getClass().getSimpleName());
			connectionPlaceHolder.setConnectionCreationDate(Calendar.getInstance().getTime());
			
			// for time keeping purpose 
			menu_Items_FX.connectedDatabasesNames.getItems().add(jdbcConnectionName.getText());
			menu_Items_FX.oracleConnectionsMap.put(connectionPlaceHolder, oracleConnection.getOracleConnection());
			menu_Items_FX.currentOpenConnectionsMap.put(connectionPlaceHolder,  oracleConnection.getOracleConnection());
			
			reflectOracleTreeView(connectionPlaceHolder);
			
		}catch(Exception ex) {
			System.out.println("Oracle Connection failed");
		}	
		
	}
	
	private void reflectOracleTreeView(ConnectionPlaceHolder connectionPlaceHolder) {
		//put the connection on treeview display and add the connection to connectionName,connectionObject Datatype
		ImageView imageOraclenode = ImageItemsHolder.getOracleImage(connectionPlaceHolder.getConnectionName());
		
		ImageView imageDatbaseTablenode = ImageItemsHolder.getTableImage();
		
								
		TreeItem<String> oracleTreeItem = new TreeItem<String>(connectionPlaceHolder.getConnectionName(),imageOraclenode);
						
		TreeItem<String> oracleTreeItemTables = new TreeItem<String>("Tables",imageDatbaseTablenode);
		oracleTreeItemTables.getChildren().add(new TreeItem<String>("Loading.."));
						
		TreeItem<String> oracleTreeItemViews = new TreeItem<String>("Views");
		oracleTreeItemViews.getChildren().add(new TreeItem<String>("Loading.."));
						
		TreeItem<String> oracleTreeItemProcedures = new TreeItem<String>("Procedures");
		oracleTreeItemProcedures.getChildren().add(new TreeItem<String>("Loading.."));
						
		oracleTreeItem.getChildren().add(oracleTreeItemTables);
		oracleTreeItem.getChildren().add(oracleTreeItemViews);
		oracleTreeItem.getChildren().add(oracleTreeItemViews);
		menu_Items_FX.rootConnectionItem.getChildren().add(oracleTreeItem);
	}
	
	

	private void createDataBricksConnection() {

		jdbcUrlTextField.getText();
		jdbcAuthenticationUsernameTextField.getText();
		jdbcAuthenticationPasswordField.getText();
		
		// @TODO Add validation here
		
		try
		{
			// Databricks sql connection for a specific database only 
			
			DatabricksConnection databricksConnection = new DatabricksConnection(jdbcUrlTextField.getText().trim());		
			System.out.println(databricksConnection.getDatabricksConnection());
			
			ConnectionPlaceHolder connectionPlaceHolder = new ConnectionPlaceHolder();
			connectionPlaceHolder.setConnectionName(jdbcConnectionName.getText());
			connectionPlaceHolder.setConnectionType(databricksConnection.getClass().getSimpleName());
			connectionPlaceHolder.setConnectionCreationDate(Calendar.getInstance().getTime());
			
			// for time keeping purpose 
			menu_Items_FX.connectedDatabasesNames.getItems().add(jdbcConnectionName.getText());
			menu_Items_FX.sqliteConnectionsMap.put(connectionPlaceHolder, databricksConnection.getDatabricksConnection());
			menu_Items_FX.currentOpenConnectionsMap.put(connectionPlaceHolder,  databricksConnection.getDatabricksConnection());
			
			reflectDatabricksTreeView(connectionPlaceHolder);
			
		}catch(Exception ex) {
			System.out.println("Databricks Connection failed");
		}
		
	}
	
	private void reflectDatabricksTreeView(ConnectionPlaceHolder connectionPlaceHolder) {
		
		//put the connection on treeview display and add the connection to connectionName,connectionObject Datatype
		
		ImageView imageDatabricksnode = ImageItemsHolder.getDatabricksImage(connectionPlaceHolder.getConnectionName());
		
		ImageView imageDatbaseTablenode = ImageItemsHolder.getTableImage();
		
		TreeItem<String> databricksTreeItem = new TreeItem<String>(connectionPlaceHolder.getConnectionName(),imageDatabricksnode);
						
		TreeItem<String> databricksTreeItemTables = new TreeItem<String>("Tables",imageDatbaseTablenode);
		databricksTreeItemTables.getChildren().add(new TreeItem<String>("Loading.."));
						
		TreeItem<String> databricksTreeItemViews = new TreeItem<String>("Views");
		databricksTreeItemViews.getChildren().add(new TreeItem<String>("Loading.."));
						
		TreeItem<String> databricksTreeItemProcedures = new TreeItem<String>("Procedures");
		databricksTreeItemProcedures.getChildren().add(new TreeItem<String>("Loading.."));
						
		databricksTreeItem.getChildren().add(databricksTreeItemTables);
		databricksTreeItem.getChildren().add(databricksTreeItemViews);
		databricksTreeItem.getChildren().add(databricksTreeItemProcedures);
		menu_Items_FX.rootConnectionItem.getChildren().add(databricksTreeItem);
		
	}
	
	private void createDuckDBConnection() {

		jdbcConnectionName.getText();
		jdbcUrlTextField.getText();
	//	jdbcAuthenticationUsernameTextField.getText();
	//	jdbcAuthenticationPasswordField.getText();
		
		// @TODO Add validation here
		
		try
		{
			// DuckDB sql connection for a specific database only 
			
			DuckDBConnection duckDBConnection = new DuckDBConnection(jdbcUrlTextField.getText().trim());		
			System.out.println(duckDBConnection.getDuckDBConnection());
			
			ConnectionPlaceHolder connectionPlaceHolder = new ConnectionPlaceHolder();
			connectionPlaceHolder.setConnectionName(jdbcConnectionName.getText());
			connectionPlaceHolder.setConnectionType(duckDBConnection.getClass().getSimpleName());
			connectionPlaceHolder.setConnectionCreationDate(Calendar.getInstance().getTime());
			
			// for time keeping purpose 
			menu_Items_FX.connectedDatabasesNames.getItems().add(jdbcConnectionName.getText());
			menu_Items_FX.duckDBConnectionsMap.put(connectionPlaceHolder, duckDBConnection.getDuckDBConnection());
			menu_Items_FX.currentOpenConnectionsMap.put(connectionPlaceHolder, duckDBConnection.getDuckDBConnection());
			
			reflectDuckDBTreeView(connectionPlaceHolder);
			
		}catch(Exception ex) {
			System.out.println("Duck DB Connection failed");
		}
		
	}
	
	private void reflectDuckDBTreeView(ConnectionPlaceHolder connectionPlaceHolder) {

		//put the connection on treeview display and add the connection to connectionName,connectionObject Datatype		
		ImageView imageDuckDBnode = ImageItemsHolder.getDuckDBImage(connectionPlaceHolder.getConnectionName());
		ImageView imageDatbaseTablenode = ImageItemsHolder.getTableImage();

		TreeItem<String> duckDBTreeItem = new DuckDBUI(menu_Items_FX,this).getDuckDBTreeItem(connectionPlaceHolder,imageDuckDBnode,imageDatbaseTablenode);

		menu_Items_FX.rootConnectionItem.getChildren().add(duckDBTreeItem);

	}
	private void createMariaDBConnection() {
		jdbcUrlTextField.getText();
		jdbcAuthenticationUsernameTextField.getText();
		jdbcAuthenticationPasswordField.getText();
		jdbcUrlDatabaseNameField.getText();
		// check if database name is provided  and then create connection accordingly and reflect accordingly
		
		// @TODO Add validation here
		
		try
		{
			MariaDBConnection mariaDBConnection;
			if(jdbcUrlDatabaseNameField.getText().isEmpty())
				mariaDBConnection = new MariaDBConnection(jdbcUrlTextField.getText().trim(), jdbcAuthenticationUsernameTextField.getText().trim(), 
				jdbcAuthenticationPasswordField.getText().trim(),jdbcConnectionPort.getText().trim());	
			else {
				mariaDBConnection = new MariaDBConnection(jdbcUrlTextField.getText().trim(), jdbcUrlDatabaseNameField.getText(),jdbcAuthenticationUsernameTextField.getText().trim(), 
						jdbcAuthenticationPasswordField.getText().trim(),jdbcConnectionPort.getText().trim());
			}
			System.out.println(mariaDBConnection.getMariaDBConnection());
			ConnectionPlaceHolder connectionPlaceHolder = new ConnectionPlaceHolder();
			connectionPlaceHolder.setConnectionType(mariaDBConnection.getClass().getSimpleName());
			connectionPlaceHolder.setConnectionName(jdbcConnectionName.getText());
			connectionPlaceHolder.setConnectionCreationDate(Calendar.getInstance().getTime());
			
			// for time keeping purpose 
			menu_Items_FX.connectedDatabasesNames.getItems().add(jdbcConnectionName.getText());
			menu_Items_FX.mariaDBConnectionsMap.put(connectionPlaceHolder, mariaDBConnection.getMariaDBConnection());
			menu_Items_FX.currentOpenConnectionsMap.put(connectionPlaceHolder, mariaDBConnection.getMariaDBConnection());
			
			reflectMariaDBrootTreeView(connectionPlaceHolder,jdbcUrlDatabaseNameField.getText());
			
		}catch(Exception ex) {
			System.out.println("MariaDB Connection failed");
		}
	}
	
	private void reflectMariaDBrootTreeView(ConnectionPlaceHolder connectionPlaceHolder,String databaseName) {
		//put the connection on treeview display and add the connection to connectionName,connectionObject Datatype
				
//		ImageView imageMariaDBnode = ImageItemsHolder.getMariaDBImage(connectionPlaceHolder.getConnectionName());
//
//		ImageView imageDatbaseTablenode = ImageItemsHolder.getTableImage();
//		
//		TreeItem<String> mariaDBTreeItem = new MariaDBUI(menu_Items_FX,this).getmariaDBTreeItem(connectionPlaceHolder,imageMariaDBnode,imageDatbaseTablenode,databaseName);
//		menu_Items_FX.rootConnectionItem.getChildren().add(mariaDBTreeItem);
		
		ImageView imageMariaDBnode = ImageItemsHolder.getMariaDBImage(connectionPlaceHolder.getConnectionName());
		ImageView imageDatbaseTablenode = ImageItemsHolder.getTableImage();

		TreeItem<String> mariaDBTreeItem = new MariaDBUI(menu_Items_FX,this).getmariaDBTreeItem(connectionPlaceHolder,imageMariaDBnode,imageDatbaseTablenode, databaseName);

		menu_Items_FX.rootConnectionItem.getChildren().add(mariaDBTreeItem);
	}
}

	
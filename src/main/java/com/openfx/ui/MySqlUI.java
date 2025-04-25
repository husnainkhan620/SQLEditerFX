package com.openfx.ui;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.Map.Entry;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.constants.MySQLConstants;
import com.openfx.erdiagram.ERModelApplication;
import com.openfx.ermodel.Attribute;
import com.openfx.ermodel.ERModel;
import com.openfx.ermodel.Entity;
import com.openfx.ermodel.KeyAttribute;
import com.openfx.ermodel.TableERModel;
import com.openfx.handlers.NewMenuItemEventHandler;
import com.openfx.placeholders.ConnectionPlaceHolder;
import com.openfx.placeholders.ImageItemsHolder;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
// import javafx.scene.web.HTMLEditor;
import javafx.util.Callback;

public class MySqlUI {
	
	public Menu_Items_FX menu_Items_FX;
	public NewMenuItemEventHandler newMenuItemEventHandler;
	
	private ConnectionPlaceHolder connectionPlaceHolder;
	private Connection currentConnection ;
	private String currentConnectionName;
	private Statement stmt ;
	public ResourceBundle resourceBundle;
	private ImageView imagemySqlnode;
	public Button refreshButton;
	public Button createTableButton;
	public Button ViewColumnsCreateButton;
	public Button viewDataTabCreateButton;
	public Button ViewColumnsDeleteButton;
	public Button particularTableColumnsButtons;
	public Button particularTableConstraintsButtons;
	public Button particularTableforeignKeysButtons;
	public Button particularTableReferencesButtons;
	public Button particularTableTriggersButtons;
	public Button particularIndexesColumnsButton;
	public Button particularTablepartitionsButtons;
	public Button particularTableIndexesButtons;
	public Button particularProceduresColumnsButtons;
	public Button particularFunctionsColumnsButtons;
	public Button viewTableButton;
	public Button createTableButtons;
	public Button deleteTableButton;
	public Button editTableButton;
	public Button refreshTableButton;
	public Button viewViewButton;
	public Button createViewButton;
	public Button editViewButton;
	public Button deleteViewButton;
	public Button refreshViewButton;
	public Button viewIndexButton;
	public Button createIndexButton;
	public Button editIndexButton;
	public Button deleteIndexButton;
	public Button refreshIndexButton;
	public Button viewProcedureButton;
	public Button createProcedureButton;
	public Button editProcedureButton;
	public Button deleteProcedureButton;
	public Button refreshProcedureButton;
	public Button viewFunctionButton;
	public Button createFunctionButton;
	public Button editFunctionButton;
	public Button deleteFunctionButton;
	public Button viewTriggersButton;
	public Button createTriggersButton;
	public Button editTriggersButton;
	public Button deleteTriggersButton;
	public Button refreshTriggersButton;
	public Button viewEventButton;
	public Button createEventButton;
	public Button editEventButton;
	public Button deleteEventButton;
	public Button refreshEventsButton;
	public Button moreChartsButton;
	public Button showBarGraphButton;
	public TabPane statusSystemVariablesTabpane;
    public Tab statusVariablesTab;
	public Tab systemVariablesTab;
	public Tab databaseDetails;
	public Tab databaseDetailsPropertiesTab;
	public Tab databaseDetailsTablesTab;
	public Tab databaseDetailsViewsTab;
	public Tab databaseDetailsIndexesTab;
	public Tab databaseDetailsProceduresTab;
	public Tab databaseDetailsFunctionsTab;
	public Tab databaseDetailsTriggersTab;
	public Tab databaseDetailsEventsTab;
	public Tab databaseERDiagram;
	public Tab databaseGrahicsStats;
	public Tab databaseAIPrompt;
	  
	public Tab globalSystemVariablesTab;
	public Tab sessionSystemVariablesTab;
	public Tab globalStatusVariablesTab;
	public Tab sessionStatusVariablesTab;
	  
	public TabPane systemVariablesTabPane;
	public TabPane statusVariablesTabPane;
	public VBox variablesSecondHalfDisplayVBox;
	  
	public VBox secondHalfDisplayVBox;
	
	public TableView performanceReportTableView;
	public Label particularPerformanceReportLabel;
	/*
	 * public Button refreshPerformanceButton = new Button("Refresh");
	 *  public Button exportPerformanceButton = new Button("Export"); 
	 * public Button performanceCopySelected = new Button("Copy Selected");
	 */
	
	public Button refreshPerformanceButton = new Button("Refresh");
	public Button exportPerformanceButton;
	public Button performanceCopySelected;
	
 	DecimalFormat decimalFormat = new DecimalFormat("#.##");
 	

	public Button performanceCopyQuery = new Button("Copy Query");
	public boolean shapePressed =false;
	public boolean shapeDragged = false;
	public boolean shapeReleased = false;
	public boolean mouseEnteredMainPaneWithDraggedItem = false;
	public StackPane selectedModelStackPane ;
	
	public Clipboard clipboard = Clipboard.getSystemClipboard();
    public ClipboardContent clipBoardcontent = new ClipboardContent();
	 
	public String actionTypes[] = {"BINARY LOGS","Server Logs","CHARACTER SET","COLLATION","ENGINES","ERRORS","EVENTS","OPEN TABLES","PLUGINS","PRIVILEGES","PROCESS LIST","PROFILES","REPLICAS","WARNINGS"};
	public  String actionTypeQuery[] = {"SHOW BINARY LOGS", "SHOW BINARY LOGS","SHOW CHARACTER SET","SHOW COLLATION","SHOW ENGINES","SHOW ERRORS","SHOW EVENTS IN mysql","SHOW OPEN TABLES","SHOW PLUGINS","SHOW PRIVILEGES",
			 "SHOW PROCESSLIST","SHOW PROFILES","SHOW REPLICAS","SHOW WARNINGS"};
	 
	public String performanceReportsTypes[] = {"Total Memory","Total Memory By Event","Total Memory By User","Total Memory By Host","Total Memory By Thread","Top File I/O Activity Report","Top I/O By File By Time","Top I/O By Event Category"
			 ,"Top I/O In Time By Event Categories","Top I/O Time By Uer/Thread","Analysis","With Errors or Warnings","With Full Table Scans","With Runtimes in 95th Percentile","With Sorting","With Temp Tables",
			 "Auto Increment Columns","Flattened Keys","Index Statistics","Object Overview","Redundant Indexes","Table Lock Waits","Table Statistics","Table Statics with Buffer","Tables With Full Table Scans","Unused Indexes",
			 "Global Waits By Time","Wait By User By Time","Wait By Host By Time","Wait Classes By Time","Wait Classes By Avg Time","Buffer Stats By Schema","Buffer Stats By Table","Lock Waits",
			 "User Summary","User File I/O Summary","User File I/O Type Summary","User Stages Summary","User Statement Time Summary","User Statement Type Summary",
			 "Host Summary","Host File I/O Summary","Host File I/O Type Summary","Host Stages Summary","Host Statement Time Summary","Host Statement Type Summary",
			 "Version","Session Info","Latest File I/O","System Config","Session SSL Status","Metrics","Process List","Check Lost Instrumentation"};
	 
	 public String performanceReportQueries[] = {"x$memory_global_total","x$memory_global_by_current_bytes","x$memory_by_user_by_current_bytes","x$memory_by_host_by_current_bytes","x$memory_by_thread_by_current_bytes","x$io_global_by_file_by_bytes","x$io_global_by_file_by_latency",
			 "x$io_global_by_wait_by_bytes","x$io_global_by_wait_by_latency","x$io_by_thread_by_latency","x$statement_analysis","statements_with_errors_or_warnings","statements_with_full_table_scans",
			 "x$statements_with_runtimes_in_95th_percentile","statements_with_sorting","statements_with_temp_tables","schema_auto_increment_columns","x$schema_flattened_keys","x$schema_index_statistics","schema_object_overview","schema_redundant_indexes","x$schema_table_lock_waits","x$schema_table_statistics","x$schema_table_statistics_with_buffer","x$schema_tables_with_full_table_scans","schema_unused_indexes",
			 "x$waits_global_by_latency","x$waits_by_user_by_latency"," x$waits_by_host_by_latency","x$wait_classes_global_by_latency","x$wait_classes_global_by_avg_latency","x$innodb_buffer_stats_by_schema","x$innodb_buffer_stats_by_table","x$innodb_lock_waits",
			 "x$user_summary","x$user_summary_by_file_io","x$user_summary_by_file_io_type","x$user_summary_by_stages","x$user_summary_by_statement_latency","x$user_summary_by_statement_type",
			 "x$host_summary","x$host_summary_by_file_io","x$host_summary_by_file_io_type","x$host_summary_by_stages","x$host_summary_by_statement_latency","x$host_summary_by_statement_type",
			 "version","x$session","x$latest_file_io","sys_config","session_ssl_status","metrics","processlist","ps_check_lost_instrumentation"};
	
	 
	 public TreeItem<String> mySqlTreeItemDatabases;
	 public TreeItem<String> mySqlTreeItemAdminister;
	 public TreeItem<String> mySqlTreeItemSystemInfo;
	 public TreeItem<String> mySqlTreeItemAdministration;
	 public TreeItem<String> mySqlTreeItemAdministerServerStatus;
	 public TreeItem<String> mySqlTreeItemAdministerClientConnectionss;
	 public TreeItem<String> mySqlTreeItemAdministerUserandPrivileges;
	 public TreeItem<String> mySqlTreeItemAdministerStatusandSystemVariables;
	 public TreeItem<String> mySqlTreeItemPerformance;
	 public TreeItem<String> mySqlTreeItemAdministerDashboard;
	 public TreeItem<String> mySqlTreeItemAdministerPerformanceReports;
	 public TreeItem<String> mySqlTreeItemServer;
	 public TreeItem<String> mySqlTreeItemAdministerServerLogs;
	 public TreeItem<String> mySqlTreeItemSystemInfoBinaryLogs;
	 public TreeItem<String> mySqlTreeItemSystemInfoCharacterSet;
	 public TreeItem<String> mySqlTreeItemSystemInfoCollation;
	 public TreeItem<String> mySqlTreeItemSystemInfoEngines;
	 public TreeItem<String> mySqlTreeItemSystemInfoErrors;
	 public TreeItem<String> mySqlTreeItemSystemInfoEvents;
	 public TreeItem<String> mySqlTreeItemSystemInfoOpenTables;
	 public TreeItem<String> mySqlTreeItemSystemInfoPlugins;
	 public TreeItem<String> mySqlTreeItemSystemInfoPreviliges;
	 public TreeItem<String> mySqlTreeItemSystemInfoProcessList;
	 public TreeItem<String> mySqlTreeItemSystemInfoProfiles;
	 public TreeItem<String> mySqlTreeItemSystemInfoReplicas;
	 public TreeItem<String> mySqlTreeItemSystemInfoSessionStatus;
	 public TreeItem<String> mySqlTreeItemSystemInfoGlobalStatus;
	 public TreeItem<String> mySqlTreeItemSystemInfoSessionVariables;
	 public TreeItem<String> mySqlTreeItemSystemInfoGlobalVariables;
	 public TreeItem<String> mySqlTreeItemSystemInfoWarnings;
	 	 
	 public Tab particularTablePropertiesTab;
	 public Tab particularTableDataTab;
	 public Tab particularTableERDiagramTab;
	 public Tab particularTableGraphVisualsTab; 
	 public Tab particularTableAiPromptTab; 
	 public Tab loginTab; 
	 public Tab accountLimitsTab; 
	 public Tab accountPrivilegesTab; 
	 public Tab schemaPrivilegesTab; 
	 
	 public Label l;
	 public Label userAccountsLabel;
	 
	 public Button addAccountButton;
	 public Button deletetButton;

	 
	public MySqlUI(Menu_Items_FX menu_Items_FX,NewMenuItemEventHandler newMenuItemEventHandler) {
		this.menu_Items_FX = menu_Items_FX;
		this.newMenuItemEventHandler = newMenuItemEventHandler;
	}

	public VBox addConnectionCredentials() {
		
		 VBox connectionDetailsVbox = new VBox();
		  
		  GridPane connectionUrlCredentialsGridPane = new GridPane();
		  connectionUrlCredentialsGridPane.setId("custom-gridpane");
//		  connectionUrlCredentialsGridPane.setPadding(new Insets(20,10,20,10));
//		  connectionUrlCredentialsGridPane.setVgap(5);
//		  connectionUrlCredentialsGridPane.setHgap(5);
		  Label jdbcUrlgeneralLabel = new Label("General");
		  GridPane.setConstraints(jdbcUrlgeneralLabel, 0, 0);   // column 0 row 0
		  Label jdbcConnectionNameLabel= new Label("Name");
		  GridPane.setConstraints(jdbcConnectionNameLabel, 0, 1);
		  newMenuItemEventHandler.jdbcConnectionName = new TextField("local");
		  newMenuItemEventHandler.jdbcConnectionName.getStyleClass().add("textfield");
		  newMenuItemEventHandler.jdbcConnectionName.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcConnectionName.setPrefWidth(400);
		 // jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcConnectionName, 1, 1);
		  Label jdbcPortUrl = new Label("Port:");
		  GridPane.setConstraints(jdbcPortUrl, 2, 1);
		  newMenuItemEventHandler.jdbcConnectionPort = new TextField("3306");
		  newMenuItemEventHandler.jdbcConnectionPort.getStyleClass().add("textfield");
		  newMenuItemEventHandler.jdbcConnectionPort.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcConnectionPort.setPrefWidth(40);
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcConnectionPort, 3, 1);
		  
		  Label jdbcUrlLabel = new Label("JDBC URL");
		  GridPane.setConstraints(jdbcUrlLabel, 0, 2);
		  newMenuItemEventHandler.jdbcUrlTextField = new TextField("jdbc:mysql://127.0.0.1");
		  newMenuItemEventHandler.jdbcUrlTextField.getStyleClass().add("textfield");
		  newMenuItemEventHandler.jdbcUrlTextField.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcUrlTextField.setPrefWidth(400);
		  newMenuItemEventHandler.jdbcUrlTextField.setOnKeyTyped(newMenuItemEventHandler.onjdbcUrlTextFieldKeyPressed() );
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcUrlTextField, 1, 2);

		  Label jdbcUrlDatabaseNameLabel = new Label("Database ");
		  GridPane.setConstraints(jdbcUrlDatabaseNameLabel, 0, 3);
		  newMenuItemEventHandler.jdbcUrlDatabaseNameField = new TextField();
		  newMenuItemEventHandler.jdbcUrlDatabaseNameField.getStyleClass().add("textfield");
		  newMenuItemEventHandler.jdbcUrlDatabaseNameField.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcUrlDatabaseNameField.setPrefWidth(200);  
		  newMenuItemEventHandler.jdbcUrlDatabaseNameField.setOnKeyTyped(newMenuItemEventHandler.onjdbcUrlTextFieldKeyPressed() );
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcUrlDatabaseNameField, 1, 3);

		  
		  connectionUrlCredentialsGridPane.getChildren().addAll(jdbcConnectionNameLabel,newMenuItemEventHandler.jdbcConnectionName,jdbcUrlgeneralLabel,jdbcUrlLabel,newMenuItemEventHandler.jdbcConnectionPort,jdbcPortUrl,newMenuItemEventHandler.jdbcUrlTextField
				  ,jdbcUrlDatabaseNameLabel,newMenuItemEventHandler.jdbcUrlDatabaseNameField);
		  connectionDetailsVbox.getChildren().add(connectionUrlCredentialsGridPane);
		  
		  
		  GridPane connectionUsernamePasswordCredentialsGridPane = new GridPane();
		  connectionUsernamePasswordCredentialsGridPane.setId("custom-gridpane");
//		  connectionUsernamePasswordCredentialsGridPane.setPadding(new Insets(20,10,20,10));
//		  connectionUsernamePasswordCredentialsGridPane.setVgap(5);
//		  connectionUsernamePasswordCredentialsGridPane.setHgap(5);
		  Label jdbcUrlAuthenticationLabel = new Label("Authentication");
		  GridPane.setConstraints(jdbcUrlAuthenticationLabel, 0, 0);   // column 0 row 0
		  Label jdbcAuthenticationUsername = new Label("Username :");
		  GridPane.setConstraints(jdbcAuthenticationUsername, 0, 1);   // column 0 row 0
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField = new TextField("root");
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField.getStyleClass().add("textfield");
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField.setPrefWidth(200);
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField.setOnKeyTyped(newMenuItemEventHandler.onjdbcAuthenticationUsernameTextFieldPressed());
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcAuthenticationUsernameTextField, 1, 1);   // column 0 row 0
		  
		  Label jdbcAuthenticationPassword = new Label("Password");
		  GridPane.setConstraints(jdbcAuthenticationPassword, 0, 2);   // column 0 row 0
		  newMenuItemEventHandler.jdbcAuthenticationPasswordField = new PasswordField();
		  newMenuItemEventHandler.jdbcAuthenticationPasswordField.getStyleClass().add("textfield");
		  newMenuItemEventHandler.jdbcAuthenticationPasswordField.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcAuthenticationPasswordField.setPrefWidth(200);
		  newMenuItemEventHandler.jdbcAuthenticationPasswordField.setOnKeyTyped(newMenuItemEventHandler.onjdbcAuthenticationPasswordFieldPressed());
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcAuthenticationPasswordField, 1, 2);   // column 0 row 0
		  
		  connectionUsernamePasswordCredentialsGridPane.getChildren().addAll(
				  jdbcUrlAuthenticationLabel,jdbcAuthenticationUsername,jdbcAuthenticationPassword,newMenuItemEventHandler.jdbcAuthenticationUsernameTextField,newMenuItemEventHandler.jdbcAuthenticationPasswordField);
		   
		  connectionDetailsVbox.getChildren().add(connectionUsernamePasswordCredentialsGridPane); 
		  
		return connectionDetailsVbox;
		
		
	}
	
	
	
	public TreeItem<String> getmySqlTreeItem(ConnectionPlaceHolder connectionPlaceHolder,ImageView imagemySqlnode,ImageView imagemySqlTablenode,String databseName){
	
		this.connectionPlaceHolder = connectionPlaceHolder;
		this.menu_Items_FX.treeConnectionsView.setCellFactory((TreeView<String> p) -> new MySQLTreecellImpl());
		this.imagemySqlnode = imagemySqlnode;
		
		// the root level item , that is the connection name
		TreeItem<String> mySqlTreeItem = new TreeItem<String>(connectionPlaceHolder.getConnectionName(),imagemySqlnode);
		
		//Databases
		mySqlTreeItemDatabases = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Databases"));
		
		TreeItem<String> loadingTreeItem = new TreeItem<String>("Loading..");
	//	mySqlTreeItemDatabases.getChildren().add(loadingTreeItem);
		threadsafeAddTreeItem(mySqlTreeItemDatabases, loadingTreeItem);
		
		mySqlTreeItemDatabases.expandedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					TreeItem bean = ((TreeItem)((BooleanProperty)observable).getBean()) ;
					String name = ((BooleanProperty)observable).getName();
					Boolean value = ((BooleanProperty)observable).getValue() ;
					System.out.println("observable : "+ "Bean : "+ bean.getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getValue() );		
				
						if(value) {
							// go to the connection and get the Databases
							System.out.println("Databases Expanded!!");
							System.out.println( connectionPlaceHolder.getConnectionName());
							for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.mySqlConnectionsMap.entrySet())
							{
								// MySQL##con12
								if(connectionPlaceHolder.getConnectionName().equalsIgnoreCase(entrySet.getKey().getConnectionName()))
								{
									System.out.println("Current Connection is :"+ entrySet.getKey().getConnectionName() + " of type "+entrySet.getValue());
									currentConnection = entrySet.getValue();
									currentConnectionName =  entrySet.getKey().getConnectionName();
									try {
										stmt = currentConnection.createStatement();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									// this thread will continue to look up the databases ans till then the view will show as loading
									// we have added this in thread as we don't want the expand to be held back by this method completion.(Loading... needs to be displayed)
									new Thread(new Runnable() {
									     @Override
									     public void run() {
									         
									    	try (ResultSet rs = stmt.executeQuery("show databases")) {
									    		try {
													Thread.sleep(1000);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
									    		Platform.runLater(() -> {
									    		    // Delay the modification slightly
									    			mySqlTreeItemDatabases.getChildren().remove(0);  // Remove the Loading.
									    		});
												
												while(rs.next()) {
													  System.out.println(rs.getString(1));
													  TreeItem<String> loadedDatabaseName = new TreeItem<String>(rs.getString(1));
													  
													 TreeItem<String> mySqlTreeItemTables = new TreeItem<String>("Tables",imagemySqlTablenode);
													 TreeItem<String> loadingTreeItemTables = new TreeItem<String>("Loading..");
													 //mySqlTreeItemTables.getChildren().add(loadingTreeItemTables);
													 threadsafeAddTreeItem(mySqlTreeItemTables, loadingTreeItemTables);	
													 
													 TreeItem<String> mySqlTreeItemViews = new TreeItem<String>("Views");
													 TreeItem<String> loadingTreeItemViews = new TreeItem<String>("Loading..");
													 //mySqlTreeItemViews.getChildren().add(loadingTreeItemViews);
													 threadsafeAddTreeItem(mySqlTreeItemViews, loadingTreeItemViews);	
													 
													 TreeItem<String> mySqlTreeItemIndexes = new TreeItem<String>("Indexes");
													 TreeItem<String> loadingTreeItemIndexes = new TreeItem<String>("Loading..");
													 //mySqlTreeItemIndexes.getChildren().add(loadingTreeItemIndexes);
													 threadsafeAddTreeItem(mySqlTreeItemIndexes, loadingTreeItemIndexes);
													 
													 TreeItem<String> mySqlTreeItemProcedures = new TreeItem<String>("Procedures");
													 TreeItem<String> loadingTreeItemProcedures = new TreeItem<String>("Loading..");
													 //mySqlTreeItemProcedures.getChildren().add(loadingTreeItemProcedures);
													 threadsafeAddTreeItem(mySqlTreeItemProcedures, loadingTreeItemProcedures);
													 
													 TreeItem<String> mySqlTreeItemFunctions = new TreeItem<String>("Functions");
													 TreeItem<String> loadingTreeItemFunctions = new TreeItem<String>("Loading..");
													 //mySqlTreeItemFunctions.getChildren().add(loadingTreeItemFunctions);
													 threadsafeAddTreeItem(mySqlTreeItemFunctions, loadingTreeItemFunctions);
													 
													 TreeItem<String> mySqlTreeItemTriggers = new TreeItem<String>("Triggers");
													 TreeItem<String> loadingTreeItemTriggers = new TreeItem<String>("Loading..");
													 //mySqlTreeItemTriggers.getChildren().add(loadingTreeItemTriggers);
													 threadsafeAddTreeItem(mySqlTreeItemTriggers, loadingTreeItemTriggers);
													 
													 TreeItem<String> mySqlTreeItemEvents = new TreeItem<String>("Events");
													 TreeItem<String> loadingTreeItemEvents = new TreeItem<String>("Loading..");
													 //mySqlTreeItemEvents.getChildren().add(loadingTreeItemEvents);
													 threadsafeAddTreeItem(mySqlTreeItemEvents, loadingTreeItemEvents);
													  
													 loadedDatabaseName.getChildren().add(mySqlTreeItemTables);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemViews);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemIndexes);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemProcedures);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemFunctions);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemTriggers);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemEvents);
													 
													 //Database expanded
													 loadedDatabaseName.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																
																System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																Boolean value = ((BooleanProperty)observable).getValue() ;
																if(value) {
																	System.out.println("Particular Database Expanded !!!"+loadedDatabaseName.getValue());

																	System.out.println("Create the Database Tabs heres");																}
																
																else {
																	System.out.println("Particular Databse Collapsed !!!"+loadedDatabaseName.getValue());
																}
															}
													 });
													 
													 // Tables 
													 mySqlTreeItemTables.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																	
																String name = ((BooleanProperty)observable).getName();
																Boolean value = ((BooleanProperty)observable).getValue() ;
																
																    if(value) {
																		System.out.println("Its aTables expansion!!!");
																		
																		// get the database name and display its tables 
																		TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																		
	
																		System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																		
																		new Thread(new Runnable() {
																		     @Override
																		     public void run() {
																		    	try  {
																		    		stmt.execute("use "+loadedDatabaseName.getValue());
																		    		ResultSet rs = currentConnection.createStatement().executeQuery("SHOW FULL TABLES IN "+ loadedDatabaseName.getValue() +" WHERE TABLE_TYPE LIKE 'BASE TABLE'");
																		    		try {
																						Thread.sleep(100);
																					} catch (InterruptedException e) {
																						// TODO Auto-generated catch block
																						e.printStackTrace();
																					}
																		    		
																		    		Platform.runLater(() -> {
																		    		    // Delay the modification slightly
																		    			mySqlTreeItemTables.getChildren().remove(0);  // Remove the Loading...
																		    		});
																		    		
																					while(rs.next()) {			
																						  
																						  TreeItem<String> loadedTableName = new TreeItem<String>(rs.getString(1));  // get the table one by one 
																						  loadedTableName.expandedProperty().addListener(new ChangeListener<Boolean>() {
																								@Override
																								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																									System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																										
																									String name = ((BooleanProperty)observable).getName();
																									Boolean value = ((BooleanProperty)observable).getValue() ;
																									if(value) {
																										 System.out.println("Loaded Table Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										
																									}else {
																										System.out.println("Loaded Table Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																									}
																								}
																						  }); 
																						  
																						  TreeItem<String> tableColumns = new TreeItem<String>("Columns");
																						  TreeItem<String> loadingTableNameColumns = new TreeItem<String>("Loading..");
																						  //tableColumns.getChildren().add(loadingTableNameColumns);
																						  threadsafeAddTreeItem(tableColumns, loadingTableNameColumns);
																						  tableColumns.expandedProperty().addListener(new ChangeListener<Boolean>() {
																								@Override
																								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																									System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																										
																									String name = ((BooleanProperty)observable).getName();
																									Boolean value = ((BooleanProperty)observable).getValue() ;
																									if(value) {
																										 System.out.println("Columns Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										// get the database name and display its tables 
																											TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																											
																											System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																											
																											new Thread(new Runnable() {
																											     @Override
																											     public void run() {
																											    	 try {
																											    		 
																											    		   stmt.execute("use "+loadedDatabaseName.getValue());
																											    		   ResultSet rs = stmt.executeQuery("desc "+loadedTableName.getValue());
																											    		   try {
																																Thread.sleep(100);
																															} catch (InterruptedException e) {
																																// TODO Auto-generated catch block
																																e.printStackTrace();
																															}
																											    		   Platform.runLater(() -> {
																												    		    // Delay the modification slightly
																											    			   tableColumns.getChildren().remove(0);  // Remove the Loading...
																												    		});
																											    		   
																															while(rs.next()) {
																																  System.out.println(rs.getString(1) + " , "+rs.getString(2));
																																  TreeItem<String> constraintsName = new TreeItem<String>(rs.getString(1) + " , "+rs.getString(2));
																																  //tableColumns.getChildren().add(constraintsName);
																																  threadsafeAddTreeItem(tableColumns, constraintsName);
																															}
																											    		   
																													     
																													    } catch (SQLException e) {
																															System.out.println("Error during columns expansion");
																															e.printStackTrace();
																														}
																											    	 }
																											}).start();
																										 
																									}else {
																										System.out.println("Columns Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										tableColumns.getChildren().clear();
																										//tableColumns.getChildren().add(loadingTableNameColumns);
																										threadsafeAddTreeItem(tableColumns, loadingTableNameColumns);
																									}
																								}
																						  });
																						  TreeItem<String> tableConstraints = new TreeItem<String>("Constaints");
																						  TreeItem<String> loadingTableNameConstraints = new TreeItem<String>("Loading..");
																						 // tableConstraints.getChildren().add(loadingTableNameConstraints);
																						  threadsafeAddTreeItem(tableConstraints, loadingTableNameConstraints);
																						  tableConstraints.expandedProperty().addListener(new ChangeListener<Boolean>() {
																								@Override
																								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																									System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																										
																									String name = ((BooleanProperty)observable).getName();
																									Boolean value = ((BooleanProperty)observable).getValue() ;
																									if(value) {
																										 System.out.println("Constraints Expanded!!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										 
																										 TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																											
																											System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																											
																											new Thread(new Runnable() {
																											     @Override
																											     public void run() {
																											        
																											    	 try {
																				
																											    		    stmt.execute("use "+loadedDatabaseName.getValue());
																											    		    ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE "+loadedTableName.getValue());		
																													        try {
																																Thread.sleep(100);
																															} catch (InterruptedException e) {
																																// TODO Auto-generated catch block
																																e.printStackTrace();
																															}
																													        
																													        Platform.runLater(() -> {
																													        tableConstraints.getChildren().remove(0);  // Remove the Loading...
																													        });
																													        
																															while(rs.next()) {
																																  System.out.println(rs.getString(2) );
																																  // break down the show table result and get the primary key details
																																  
																																  String mysqlQuerySplit[] = rs.getString(2).split("\n");
																																	
																																	for(int i=0;i<mysqlQuerySplit.length;i++) {
																																		
																																		if(mysqlQuerySplit[i].contains("PRIMARY KEY")) {
																																		
																																			System.out.println( mysqlQuerySplit[i]);
																																			TreeItem<String> foreignKeysName = new TreeItem<String>(mysqlQuerySplit[i]);
																																			//tableConstraints.getChildren().add(foreignKeysName);
																																			threadsafeAddTreeItem(tableConstraints, foreignKeysName);
																																		}
																																		
																																	} 	
																															}
																													        
																													    } catch (SQLException e) {
																															System.out.println("Error during constrains expansion");
																															e.printStackTrace();
																														}
																											    	 }
																											}).start();
																										 
																										 
																									}else {
																										System.out.println("Constraints Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										tableConstraints.getChildren().clear();
																										//tableConstraints.getChildren().add(loadingTableNameConstraints);
																										threadsafeAddTreeItem(tableConstraints, loadingTableNameConstraints);
																									}
																								}
																						  });
																						  TreeItem<String> tableForeignKeys = new TreeItem<String>("Foreign Keys");
																						  
																						  
																						  
																						  
																						  TreeItem<String> loadingTableNameForeignKeys = new TreeItem<String>("Loading..");
																						  //tableForeignKeys.getChildren().add(loadingTableNameForeignKeys);
																						  threadsafeAddTreeItem(tableForeignKeys, loadingTableNameForeignKeys);
																						  tableForeignKeys.expandedProperty().addListener(new ChangeListener<Boolean>() {
																								@Override
																								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																									System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																										
																									String name = ((BooleanProperty)observable).getName();
																									Boolean value = ((BooleanProperty)observable).getValue() ;
																									if(value) {
																										 System.out.println("Foreign Key Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue()); 
																										 
																										TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																											
																										System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																											
																											new Thread(new Runnable() {
																											     @Override
																											     public void run() {
																											        
																											    	 try {
																											    		 
																											    		    stmt.execute("use "+loadedDatabaseName.getValue());
																											    		    ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE "+loadedTableName.getValue());		
																													        try {
																																Thread.sleep(100);
																															} catch (InterruptedException e) {
																																// TODO Auto-generated catch block
																																e.printStackTrace();
																															}
																													        Platform.runLater(() -> {
																													        tableForeignKeys.getChildren().remove(0);  // Remove the Loading...
																													        });
																													        
																															while(rs.next()) {
																																  System.out.println(rs.getString(2) );
																																  // break down the show table result and get the foreigh key details
																																  
																																  String mysqlQuerySplit[] = rs.getString(2).split("\n");
																																	
																																	for(int i=0;i<mysqlQuerySplit.length;i++) {
																																		
																																		if(mysqlQuerySplit[i].contains("CONSTRAINT") || mysqlQuerySplit[i].contains("FOREIGN KEY")) {
																																		
																																			System.out.println( mysqlQuerySplit[i]);
																																			TreeItem<String> foreignKeysName = new TreeItem<String>(mysqlQuerySplit[i].replace("CONSTRAINT", ""));
																																			// tableForeignKeys.getChildren().add(foreignKeysName);
																																			 threadsafeAddTreeItem(tableForeignKeys, foreignKeysName);
																																		}
																																	}	 	
																															}
																													        
																													    } catch (SQLException e) {
																															System.out.println("Error during constrains expansion");
																															e.printStackTrace();
																														}
																											    	 }
																											}).start();
																										 
																									}else {
																										System.out.println("Foreign Key Collpapsed!!" + "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																									}
																								}
																						  });
																						  TreeItem<String> tableReferences = new TreeItem<String>("References");
																						  TreeItem<String> loadingTableNameReferences = new TreeItem<String>("Loading..");
																						 // tableReferences.getChildren().add(loadingTableNameReferences);
																						  threadsafeAddTreeItem(tableReferences, loadingTableNameReferences);
																						  tableReferences.expandedProperty().addListener(new ChangeListener<Boolean>() {
																								@Override
																								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																									System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																										
																									String name = ((BooleanProperty)observable).getName();
																									Boolean value = ((BooleanProperty)observable).getValue() ;
																									if(value) {
																										 System.out.println("References Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());																																												 
																										 TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());										
																										 System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																												
																										new Thread(new Runnable() {
																											@Override
																											public void run() {
																												        
																												try {
																												    		 
																												    	stmt.execute("use "+loadedDatabaseName.getValue());
																												    	ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE "+loadedTableName.getValue());		
																												    	try {
																															Thread.sleep(100);
																														} catch (InterruptedException e) {
																																	// TODO Auto-generated catch block
																																	e.printStackTrace();
																														}
																												    	 Platform.runLater(() -> {
																												    	tableReferences.getChildren().remove(0);  // Remove the Loading...
																												    	 });
																														while(rs.next()) {
																															System.out.println(rs.getString(2) );
																															// break down the show table result and get the foreigh key details
																																	  
																															String mysqlQuerySplit[] = rs.getString(2).split("\n");
																																		
																															for(int i=0;i<mysqlQuerySplit.length;i++) {
																																			
																															if(mysqlQuerySplit[i].contains("CONSTRAINT") || mysqlQuerySplit[i].contains("REFERENCES")) {
																																			
																																System.out.println( mysqlQuerySplit[i]);
																																TreeItem<String> foreignKeysName = new TreeItem<String>(mysqlQuerySplit[i].replace("CONSTRAINT", ""));
																																//tableReferences.getChildren().add(foreignKeysName);
																																threadsafeAddTreeItem(tableReferences, foreignKeysName);
																															}
																														}	 	
																													}
																														        
																												} catch (SQLException e) {
																														System.out.println("Error during references expansion");
																														e.printStackTrace();
																													}
																												}
																											}).start();
																									}else {
																										System.out.println("References Collpapsed!!" + "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										tableReferences.getChildren().clear();
																										//tableReferences.getChildren().add(loadingTableNameConstraints);
																										threadsafeAddTreeItem(tableReferences, loadingTableNameConstraints);
																									}
																								}
																						  });
																						  TreeItem<String> tableTriggers = new TreeItem<String>("Triggers");
																						  TreeItem<String> loadingTableNameTriggers = new TreeItem<String>("Loading..");
																						 // tableTriggers.getChildren().add(loadingTableNameTriggers);
																						  threadsafeAddTreeItem(tableTriggers, loadingTableNameTriggers);
																						  tableTriggers.expandedProperty().addListener(new ChangeListener<Boolean>() {
																								@Override
																								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																									System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																										
																									String name = ((BooleanProperty)observable).getName();
																									Boolean value = ((BooleanProperty)observable).getValue() ;
																									if(value) {
																										 System.out.println("Triggers Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										 TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());										
																										 System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																												
																										new Thread(new Runnable() {
																											@Override
																											public void run() {
																												        
																												try {
																												    		 
																												    	stmt.execute("use "+loadedDatabaseName.getValue());
																												    	ResultSet rs = stmt.executeQuery("SHOW TRIGGERS LIKE '"+loadedTableName.getValue()+"'");		
																												    	try {
																															Thread.sleep(100);
																														} catch (InterruptedException e) {
																																	// TODO Auto-generated catch block
																																	e.printStackTrace();
																														}
																												    	 Platform.runLater(() -> {
																												    	tableTriggers.getChildren().remove(0);  // Remove the Loading...
																												    	 });
																												    	 
																														while(rs.next()) {
																															System.out.println(rs.getString(1) );
																															// break down the show table result and get the foreigh key details	
																															TreeItem<String> triggerName = new TreeItem<String>(rs.getString(1));
																															//tableTriggers.getChildren().add(triggerName);
																															threadsafeAddTreeItem(tableTriggers, triggerName);
																															 	
																													}
																														        
																												} catch (SQLException e) {
																														System.out.println("Error during t expansion");
																														e.printStackTrace();
																												}
																											}
																											}).start();
																										 
																									}else {
																										System.out.println("Triggers Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										tableTriggers.getChildren().clear();
																										//tableTriggers.getChildren().add(loadingTableNameConstraints);
																										threadsafeAddTreeItem(tableTriggers, loadingTableNameConstraints);
																									}
																									
																								}
																						  });
																						  TreeItem<String> tableIndexes = new TreeItem<String>("Indexes");
																						  TreeItem<String> loadingTableNameIndexes = new TreeItem<String>("Loading..");
																						  //tableIndexes.getChildren().add(loadingTableNameIndexes);
																						  threadsafeAddTreeItem(tableIndexes, loadingTableNameIndexes);
																						  tableIndexes.expandedProperty().addListener(new ChangeListener<Boolean>() {
																								@Override
																								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																									System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																										
																									String name = ((BooleanProperty)observable).getName();
																									Boolean value = ((BooleanProperty)observable).getValue() ;
																									if(value) {
																										 System.out.println("Indexes Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										 TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());										
																										 System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																												
																										new Thread(new Runnable() {
																											@Override
																											public void run() {
																												        
																												try {
																												    		 
																												    	stmt.execute("use "+loadedDatabaseName.getValue());
																												    	ResultSet rs = stmt.executeQuery("SHOW INDEXES FROM "+loadedTableName.getValue()+" FROM "+loadedDatabaseName.getValue());		
																												    	try {
																															Thread.sleep(100);
																														} catch (InterruptedException e) {
																																	// TODO Auto-generated catch block
																																	e.printStackTrace();
																														}
																												    	Platform.runLater(() -> {
																												    	tableIndexes.getChildren().remove(0);  // Remove the Loading...
																												    	});
																												    	 
																														while(rs.next()) {
																															System.out.println(rs.getString(3)+ " , " + rs.getString(5));
																															// break down the show table result and get the foreigh key details	
																															TreeItem<String> indexName = new TreeItem<String>(rs.getString(3)+ " , " + rs.getString(5));
																															//tableIndexes.getChildren().add(indexName);
																															threadsafeAddTreeItem(tableIndexes, indexName);
																															 	
																													}
																													//stmt.close();
																														        
																												} catch (SQLException e) {
																														System.out.println("Error during indexes expansion");
																														e.printStackTrace();
																												}
																											}
																											}).start();
																										
																										
																									}else {
																										System.out.println("Indexes Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										tableIndexes.getChildren().clear();
																										//tableIndexes.getChildren().add(loadingTableNameIndexes);
																										threadsafeAddTreeItem(tableIndexes, loadingTableNameIndexes);
																									}
																								}
																						  });
																						  
																						  TreeItem<String> tablePartitions = new TreeItem<String>("Partitions");
																						  TreeItem<String> loadingTableNamePartitions = new TreeItem<String>("Loading..");
																						 // tablePartitions.getChildren().add(loadingTableNamePartitions);
																						  threadsafeAddTreeItem(tablePartitions, loadingTableNamePartitions);
																						  tablePartitions.expandedProperty().addListener(new ChangeListener<Boolean>() {
																								@Override
																								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																									System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																										
																									String name = ((BooleanProperty)observable).getName();
																									Boolean value = ((BooleanProperty)observable).getValue() ;
																									if(value) {
																										 System.out.println("Pratitions Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										 TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());										
																										 System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																												
																										new Thread(new Runnable() {
																											@Override
																											public void run() {
																												        
																												try {																												    		 
																												    	stmt.execute("use "+loadedDatabaseName.getValue());
																												    	ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE "+loadedTableName.getValue());		
																												    	/*!50100 PARTITION BY RANGE (year(`purchased`))\r\n"
																											 			+ "(PARTITION p0 VALUES LESS THAN (1985) ENGINE = InnoDB,\r\n"
																											 			+ " PARTITION p1 VALUES LESS THAN (1990) ENGINE = InnoDB,\r\n"
																											 			+ " PARTITION p2 VALUES LESS THAN (1995) ENGINE = InnoDB,\r\n"
																											 			+ " PARTITION p3 VALUES LESS THAN (2000) ENGINE = InnoDB,\r\n"
																											 			+ " PARTITION p4 VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */ 
																												    	try {
																															Thread.sleep(100);
																														} catch (InterruptedException e) {
																																	// TODO Auto-generated catch block
																																	e.printStackTrace();
																														}
																												        Platform.runLater(() -> {
																												    	tablePartitions.getChildren().remove(0);  // Remove the Loading...
																												        });
																												        
																														while(rs.next()) {
																															String createShowTableQuery = rs.getString(2);
																															System.out.println(createShowTableQuery);
																															// select first word after partition and not followed by 'BY' which will be the partition name
																															String[] arrayWithPartitionDescriptions = createShowTableQuery.split("\n");
																														 	for(int i=0;i<arrayWithPartitionDescriptions.length;i++)
																														 	{
																														 		if(arrayWithPartitionDescriptions[i].toUpperCase().contains("PARTITION")) {
																														 			if(arrayWithPartitionDescriptions[i].toUpperCase().contains("PARTITION BY") )
																														 				continue;
																														 			for(int j=0;j<arrayWithPartitionDescriptions[i].split(" ").length;j++) {
																														 				if(arrayWithPartitionDescriptions[i].split(" ")[j].contains("PARTITION") && (arrayWithPartitionDescriptions[i].split(" ")[j].replace("(","").length() == "PARTITION".length()) ) {
																														 					System.out.println(arrayWithPartitionDescriptions[i].split(" ")[j+1]);
																														 					TreeItem<String> partitionName = new TreeItem<String>(arrayWithPartitionDescriptions[i].split(" ")[j+1]);
																																			//tablePartitions.getChildren().add(partitionName);
																																			threadsafeAddTreeItem(tablePartitions, partitionName);
																														 				}
																														 			}
																														 		}
																														 	}
																													}
																													//stmt.close();
																														        
																												} catch (SQLException e) {
																														System.out.println("Error during partition expansion");
																														e.printStackTrace();
																												}
																											}
																											}).start();
																									}else {
																										System.out.println("Partitions Collpapsed!!"+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																										tablePartitions.getChildren().clear();
																										tablePartitions.getChildren().add(loadingTableNameIndexes);
																										threadsafeAddTreeItem(tablePartitions, loadingTableNameIndexes);
																									}
																								}
																						  });
																						  
																						  
																						  loadedTableName.getChildren().add(tableColumns);
																						  loadedTableName.getChildren().add(tableConstraints);
																						  loadedTableName.getChildren().add(tableForeignKeys);
																						  loadedTableName.getChildren().add(tableReferences);
																						  loadedTableName.getChildren().add(tableTriggers);
																						  loadedTableName.getChildren().add(tableIndexes);
																						  loadedTableName.getChildren().add(tablePartitions);
																						  
																						  //mySqlTreeItemTables.getChildren().add(loadedTableName);
																						  threadsafeAddTreeItem(mySqlTreeItemTables, loadedTableName);
																					}
																				} catch (SQLException e) {
																					// TODO Auto-generated catch block
																					e.printStackTrace();
																				}
																		     }
																		}).start();
																  }else {
																		System.out.println("Collapsed!!! Tables "+name);
																		mySqlTreeItemTables.getChildren().clear();
																		//mySqlTreeItemTables.getChildren().add(loadingTreeItemTables);
																		threadsafeAddTreeItem(mySqlTreeItemTables, loadingTreeItemTables);
																  }
															}
													 });
													 
													 // Views 
													 mySqlTreeItemViews.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																	
																String name = ((BooleanProperty)observable).getName();
																Boolean value = ((BooleanProperty)observable).getValue() ;
																
																 if(value) {
																	System.out.println("Its Views expansion!!!");
																	
																	// get the database name and display its tables 
																	TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																	

																	System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																	
																	new Thread(new Runnable() {
																	     @Override
																	     public void run() {
																	         
																	    	try  {
																	    		currentConnection.createStatement().execute("use "+loadedDatabaseName.getValue());
																	    		ResultSet rs = currentConnection.createStatement().executeQuery("SHOW FULL TABLES IN "+ loadedDatabaseName.getValue() +" WHERE TABLE_TYPE LIKE 'VIEW'");
																	    		try {
																					Thread.sleep(1000);
																				} catch (InterruptedException e) {
																					// TODO Auto-generated catch block
																					e.printStackTrace();
																				}
																	    		Platform.runLater(() -> {
																	    		mySqlTreeItemViews.getChildren().remove(0);  // Remove the Loading...
																	    		});
																	    		
																				while(rs.next()) {
																					  System.out.println(rs.getString(1));
																					  
																					  TreeItem<String> viewName = new TreeItem<String>(rs.getString(1));
																					  TreeItem<String> viewNameLoading = new TreeItem<String>("Loading");
																					  //viewName.getChildren().addAll(viewNameLoading);
																					  threadsafeAddTreeItem(viewName, viewNameLoading);
																					  
																					  viewName.expandedProperty().addListener(new ChangeListener<Boolean>() {
																							@Override
																							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																								System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																									
																								String name = ((BooleanProperty)observable).getName();
																								Boolean value = ((BooleanProperty)observable).getValue() ;
																								if(value) {
																									 System.out.println("Views Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Views Selected " +viewName.getValue());
																									// get the database name and display its tables 
																										TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																										
																										System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																										
																										new Thread(new Runnable() {
																										     @Override
																										     public void run() {
																										    	 try {
																										    		  // stmt.execute("use "+loadedDatabaseName.getValue());
																										    		   ResultSet rs = currentConnection.createStatement().executeQuery("desc "+viewName.getValue());
																										    		   
																										    		   Platform.runLater(() -> {
																										    		   viewName.getChildren().remove(0);  // Remove the Loading...
																										    		   });
																														while(rs.next()) {
																															  
																															  TreeItem<String> constraintsName = new TreeItem<String>(rs.getString(1)+","+rs.getString(2));
																															  //viewName.getChildren().add(constraintsName);
																															  threadsafeAddTreeItem(viewName, constraintsName);
																														}
  
																												    } catch (SQLException e) {
																														System.out.println("Error during columns expansion");
																														e.printStackTrace();
																													}
																										    	 }
																										}).start();
																									 
																								}else {
																									System.out.println("Views Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Views Selected " +viewName.getValue());
																									viewName.getChildren().clear();
																									//viewName.getChildren().add(viewNameLoading);
																									threadsafeAddTreeItem(viewName, viewNameLoading);
																								}
																							}
																					  });
																					  
																					  
																					  //mySqlTreeItemViews.getChildren().add(viewName);
																					  threadsafeAddTreeItem(mySqlTreeItemViews, viewName);
																				}
																				//stmt.close();
																			} catch (SQLException e) {
																				// TODO Auto-generated catch block
																				e.printStackTrace();
																			}
																	     }
																	}).start();
																 }
																 else {
																	   System.out.println("Collapsed!!! Views ");
																	   mySqlTreeItemViews.getChildren().clear();
																	  // mySqlTreeItemViews.getChildren().add(loadingTreeItemViews);
																	   threadsafeAddTreeItem(mySqlTreeItemViews, loadingTreeItemViews);
																 }
															}
													 });
													 
													 //Indexes
													  mySqlTreeItemIndexes.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																	
																String name = ((BooleanProperty)observable).getName();
																Boolean value = ((BooleanProperty)observable).getValue() ;
																if(value) {
																	 System.out.println("Indexes Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() );
																	 TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());										
																	 System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																			
																	new Thread(new Runnable() {
																		@Override
																		public void run() {
																			        
																			try {
																			    		 
																			    	stmt.execute("use "+loadedDatabaseName.getValue());
																			    	ResultSet rs = stmt.executeQuery("select table_name,index_name from information_schema.statistics where table_schema = '"+loadedDatabaseName.getValue()+"'");;		
																			    	try {
																						Thread.sleep(1000);
																					} catch (InterruptedException e) {
																						// TODO Auto-generated catch block
																						e.printStackTrace();
																					}
																			    	Platform.runLater(() -> {
																			    	mySqlTreeItemIndexes.getChildren().remove(0);  // Remove the Loading...
																			    	});
																			    	
																					while(rs.next()) {
																							
																						TreeItem<String> indexName = new TreeItem<String>(rs.getString(1)+ "." + rs.getString(2));
																						//mySqlTreeItemIndexes.getChildren().add(indexName);
																						threadsafeAddTreeItem(mySqlTreeItemIndexes, indexName);
																						 	
																				}
																				//stmt.close();
																					        
																			} catch (SQLException e) {
																					System.out.println("Error during indexes expansion");
																					e.printStackTrace();
																			}
																		}
																		}).start();
																	
																	
																}else {
																	System.out.println("Indexes Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() );
																	mySqlTreeItemIndexes.getChildren().clear();
																	//mySqlTreeItemIndexes.getChildren().add(loadingTreeItemIndexes);
																	threadsafeAddTreeItem(mySqlTreeItemIndexes, loadingTreeItemIndexes);
																}
															}
													  });
													 
													 
													 // Procedures 
													 mySqlTreeItemProcedures.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																	
																System.out.println("Its Procedures expansion!!!"); // from here fix procedures
																	
																String name = ((BooleanProperty)observable).getName();
																Boolean value = ((BooleanProperty)observable).getValue() ;
																	
																System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																
																if(value) {
																	
																	new Thread(new Runnable() {
																	     @Override
																	     public void run() {
																	         
																	    	try  {
																	    		stmt.execute("use "+loadedDatabaseName.getValue());
																	    		ResultSet rs = stmt.executeQuery(" SHOW PROCEDURE STATUS WHERE Db = '"+ loadedDatabaseName.getValue() +"'");
																	    		try {
																					Thread.sleep(1000);
																				} catch (InterruptedException e) {
																					// TODO Auto-generated catch block
																					e.printStackTrace();
																				}
																	    		Platform.runLater(() -> {
																	    		mySqlTreeItemProcedures.getChildren().remove(0);  // Remove the Loading...
																	    		});
																	    		
																				while(rs.next()) {
																					  
																					  TreeItem<String> procedureName = new TreeItem<String>(rs.getString(2));
																					  
																					  ResultSet rsProcedures = currentConnection.createStatement().executeQuery("select parameter_mode,parameter_name,data_type,character_maximum_length,dtd_identifier from information_schema.parameters where specific_name='"+ rs.getString(2) +"' and  specific_schema = '"+loadedDatabaseName.getValue()+"'");
																					  // Write logic to derive IN and OUT Paramaeters
																					  																																		  
																					  TreeItem<String> procedureParametersIN = new TreeItem<String>("Parameters [IN]");
																					  TreeItem<String> procedureParametersOUT = new TreeItem<String>("Parameters [OUT]");
																					  
																					  while(rsProcedures.next()){
																						  
																						  if(rsProcedures.getString(1).equals("IN")) {
																							  TreeItem<String> procedureParameterIN = new TreeItem<String>(rsProcedures.getString(2)+","+rsProcedures.getString(5));
																							  //procedureParametersIN.getChildren().add(procedureParameterIN);
																							  threadsafeAddTreeItem(procedureParametersIN, procedureParameterIN);
																						  }
																						  if(rsProcedures.getString(1).equals("OUT")) {
																							  TreeItem<String> procedureParameterOUT = new TreeItem<String>(rsProcedures.getString(2)+","+rsProcedures.getString(5));
																							  //procedureParametersOUT.getChildren().add(procedureParameterOUT);
																							  threadsafeAddTreeItem(procedureParametersOUT, procedureParameterOUT);
																						  }
																					  }
																					  
																					  procedureName.getChildren().addAll(procedureParametersIN,procedureParametersOUT);
																					
																					 // mySqlTreeItemProcedures.getChildren().add(procedureName);
																					  threadsafeAddTreeItem(mySqlTreeItemProcedures, procedureName);
																					
																				}
																				//stmt.close();
																			} catch (SQLException e) {
																				System.out.println("Error during procedures expansion");
																				e.printStackTrace();
																			}
																	     }
																	}).start();	
																}else {
																	System.out.println("Procedures Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() );
																	mySqlTreeItemProcedures.getChildren().clear();
																	//mySqlTreeItemProcedures.getChildren().add(loadingTreeItemProcedures);
																	threadsafeAddTreeItem(mySqlTreeItemProcedures, loadingTreeItemProcedures);
																}
															}
													 });
													 
													 //Functions
													 mySqlTreeItemFunctions.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {																	
																
																System.out.println("Its Functions expansion!!!"); // from here fix procedures
																
																String name = ((BooleanProperty)observable).getName();
																Boolean value = ((BooleanProperty)observable).getValue() ;

																System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																
																if(value) {
																
																	new Thread(new Runnable() {
																	     @Override
																	     public void run() {
																	         
																	    	try  {
																	    		
																	    		ResultSet rs = currentConnection.createStatement().executeQuery(" SHOW FUNCTION STATUS WHERE Db = '"+ loadedDatabaseName.getValue() +"'");
																	    		try {
																					Thread.sleep(1000);
																				} catch (InterruptedException e) {
																					// TODO Auto-generated catch block
																					e.printStackTrace();
																				}
																	    		Platform.runLater(() -> {
																	    		mySqlTreeItemFunctions.getChildren().remove(0);  // Remove the Loading...
																	    		});
																	    		
																	    		while(rs.next()) {
																					  
																					  TreeItem<String> functionName = new TreeItem<String>(rs.getString(2));
																					  
																					  ResultSet rsFunctions = currentConnection.createStatement().executeQuery("select parameter_mode,parameter_name,data_type,character_maximum_length,dtd_identifier from information_schema.parameters where specific_name='"+ rs.getString(2) +"' and  specific_schema = '"+loadedDatabaseName.getValue()+"'");
																					  // Write logic to derive IN and OUT Paramaeters
																					  																																		  
																					  TreeItem<String> functionsParametersIN = new TreeItem<String>("Parameters [IN]");
																					  TreeItem<String> functionsParametersOUT = new TreeItem<String>("Parameters [RETURN]");
																					  
																					  while(rsFunctions.next()){
																						  
																						  if(rsFunctions.getString(1) != null &&  rsFunctions.getString(1).equals("IN")) {
																							  TreeItem<String> procedureParameterIN = new TreeItem<String>(rsFunctions.getString(2)+","+rsFunctions.getString(5));
																							 // functionsParametersIN.getChildren().add(procedureParameterIN);
																							  threadsafeAddTreeItem(functionsParametersIN, procedureParameterIN);
																						  }
																						  if(rsFunctions.getString(1) == null ) {
																							  TreeItem<String> procedureParameterOUT = new TreeItem<String>("RETURN,"+rsFunctions.getString(5));
																							  //functionsParametersOUT.getChildren().add(procedureParameterOUT);
																							  threadsafeAddTreeItem(functionsParametersOUT, procedureParameterOUT);
																						  }
																					  }
																					  
																					  functionName.getChildren().addAll(functionsParametersIN,functionsParametersOUT);
																					
																					 // mySqlTreeItemFunctions.getChildren().add(functionName);
																					  threadsafeAddTreeItem(mySqlTreeItemFunctions, functionName);
																				}
																				//stmt.close();
																			} catch (SQLException e) {
																				// TODO Auto-generated catch block
																				e.printStackTrace();
																			}
																	     }
																	}).start();
															}else {
																System.out.println("Functions Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() );
																mySqlTreeItemFunctions.getChildren().clear();
																//mySqlTreeItemFunctions.getChildren().add(loadingTreeItemFunctions);
																 threadsafeAddTreeItem(mySqlTreeItemFunctions, loadingTreeItemFunctions);
															}
														}
													 });
													 
													 // Triggers
													 mySqlTreeItemTriggers.expandedProperty().addListener(new ChangeListener<Boolean>() {
														@Override
														public void changed(ObservableValue<? extends Boolean> observable,Boolean oldValue, Boolean newValue) {
															System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
															
															String name = ((BooleanProperty)observable).getName();
															Boolean value = ((BooleanProperty)observable).getValue() ;
															
															 if(value) {
																System.out.println("Its Triggers expansion!!!");																	
																// get the database name and display its tables 
																TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																
																new Thread(new Runnable() {
																     @Override
																     public void run() {
																         
																    	try  {
																    		
																    		ResultSet rs = currentConnection.createStatement().executeQuery(" select * from information_schema.triggers where trigger_schema = '"+loadedDatabaseName.getValue()+"'");
																    		try {
																				Thread.sleep(1000);
																			} catch (InterruptedException e) {
																				// TODO Auto-generated catch block
																				e.printStackTrace();
																			}
																    		Platform.runLater(() -> {
																    		mySqlTreeItemTriggers.getChildren().remove(0);  // Remove the Loading...
																    		});
																    		
																			while(rs.next()) {
																				  System.out.println(rs.getString(1));
																				  TreeItem<String> triggerName = new TreeItem<String>(rs.getString(3));
																				 // mySqlTreeItemTriggers.getChildren().add(triggerName);	
																				  threadsafeAddTreeItem(mySqlTreeItemTriggers, triggerName);
																			}
																			//stmt.close();
																		} catch (SQLException e) {
																			// TODO Auto-generated catch block
																			e.printStackTrace();
																		}
																     }
																}).start();
															 }
															 else {
																   System.out.println("Collapsed!!! Events ");
																   mySqlTreeItemTriggers.getChildren().clear();
																  // mySqlTreeItemTriggers.getChildren().add(loadingTreeItemTriggers);
																   threadsafeAddTreeItem(mySqlTreeItemTriggers, loadingTreeItemTriggers);
															 }															
														}
													
													 });
													 
													 // Events
													 mySqlTreeItemEvents.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																	
																String name = ((BooleanProperty)observable).getName();
																Boolean value = ((BooleanProperty)observable).getValue() ;
																
																 if(value) {
																	System.out.println("Its Events expansion!!!");																	
																	// get the database name and display its tables 
																	TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																	System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																	
																	new Thread(new Runnable() {
																	     @Override
																	     public void run() {
																	         
																	    	try  {
																	    		
																	    		ResultSet rs = currentConnection.createStatement().executeQuery("SHOW EVENTS FROM "+loadedDatabaseName.getValue());
																	    		try {
																					Thread.sleep(1000);
																				} catch (InterruptedException e) {
																					// TODO Auto-generated catch block
																					e.printStackTrace();
																				}
																	    		Platform.runLater(() -> {
																	    		mySqlTreeItemEvents.getChildren().remove(0);  // Remove the Loading...
																	    		});
																	    		
																				while(rs.next()) {
																					  System.out.println(rs.getString(1));
																					  TreeItem<String> ViewName = new TreeItem<String>(rs.getString(2));
																					  //mySqlTreeItemEvents.getChildren().add(ViewName);
																					   threadsafeAddTreeItem(mySqlTreeItemEvents, ViewName);
																					
																				}
																				//stmt.close();
																			} catch (SQLException e) {
																				// TODO Auto-generated catch block
																				e.printStackTrace();
																			}
																	     }
																	}).start();
																 }
																 else {
																	   System.out.println("Collapsed!!! Events ");
																	   mySqlTreeItemEvents.getChildren().clear();
																	  // mySqlTreeItemEvents.getChildren().add(loadingTreeItemViews);
																	   threadsafeAddTreeItem(mySqlTreeItemEvents, loadingTreeItemViews);
																 }
															}
													 });
													 
													 // ---------------END------------------
													 
													 // Add the Databse to the Databse tree
													 //mySqlTreeItemDatabases.getChildren().add(loadedDatabaseName);
													 threadsafeAddTreeItem(mySqlTreeItemDatabases, loadedDatabaseName);
												}
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
									     }
									}).start();
									
								}
							}
						}
						else {
							System.out.println("Databases Collapsed!!!" + name);
							mySqlTreeItemDatabases.getChildren().clear();  // Clear the list
							//mySqlTreeItemDatabases.getChildren().add(loadingTreeItem);
							 threadsafeAddTreeItem(mySqlTreeItemDatabases, loadingTreeItem);
						}
		}
		});
		
		// Check if this can be mage a generic code
		// Users
		/*
		TreeItem<String> mySqlTreeItemUsers = new TreeItem<String>("Users");
		TreeItem<String> loadingTreeItemUsers = new TreeItem<String>("Loading..");
		mySqlTreeItemUsers.getChildren().add(loadingTreeItemUsers);
		
		mySqlTreeItemUsers.expandedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					TreeItem bean = ((TreeItem)((BooleanProperty)observable).getBean()) ;
					String name = ((BooleanProperty)observable).getName();
					Boolean value = ((BooleanProperty)observable).getValue() ;
					System.out.println("observable : "+ "Bean : "+ bean.getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getValue() );		
				
						if(value) {
							// go to the connection and get the Databases
							System.out.println("Users Expanded!!");
							System.out.println( connectionPlaceHolder.getConnectionName());
							for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.mySqlConnectionsMap.entrySet())
							{
								// MySQL##con12
								if(connectionPlaceHolder.getConnectionName().equalsIgnoreCase(entrySet.getKey().getConnectionName()))
								{
									System.out.println("Current Connection is :"+ entrySet.getKey().getConnectionName() + " of type "+entrySet.getValue());
									Connection currentConnection = entrySet.getValue();
									
									try {
										stmt = currentConnection.createStatement();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									// this thread will continue to look up the databases ans till then the view will show as loading
									// we have added this in thread as we don't want the expand to be held back by this method completion.(Loading... needs to be displayed)
									new Thread(new Runnable() {
									     @Override
									     public void run() {
									         
									    	try (ResultSet rs = stmt.executeQuery("select * from mysql.user")) {
									    		
									    		mySqlTreeItemUsers.getChildren().remove(0);  // Remove the Loading...
												while(rs.next()) {
													  System.out.println(rs.getString(1));
													  String userAccount = "'" + rs.getString(2) + "'" + "@" + "'" + rs.getString(1) +"'" ; 
													  TreeItem<String> loadedUserName = new TreeItem<String>(rs.getString(2)+"@"+rs.getString(1));
													  TreeItem<String> loadedUserNameDetailsLoading = new TreeItem<String>("Loading..");
													  loadedUserName.getChildren().add(loadedUserNameDetailsLoading);
													  mySqlTreeItemUsers.getChildren().add(loadedUserName);
													  
													  loadedUserName.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																
																	System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																	
																	String name = ((BooleanProperty)observable).getName();
																	Boolean value = ((BooleanProperty)observable).getValue() ;
																	if(value) {
																		 System.out.println("Users Expanded!! "+ "User Selected "+loadedUserName.getValue() );
																		 
																		 System.out.println("Run "+ "show grants for " +userAccount);

																		 new Thread(new Runnable() {
																		     @Override
																		     public void run() {
																		         
																		    	try  {
																		    		stmt.execute("show grants for " +userAccount);
																		    		ResultSet rs = stmt.executeQuery("show grants for " +userAccount);
																		    		try {
																						Thread.sleep(100);
																					} catch (InterruptedException e) {
																						// TODO Auto-generated catch block
																						e.printStackTrace();
																					}
																		    		loadedUserName.getChildren().remove(0); // Remove the Loading...
																					while(rs.next()) {
																						  System.out.println(rs.getString(1));
																						  loadedUserName.getChildren().add(new TreeItem<String>(rs.getString(1)));
																						
																					}
																					//stmt.close();
																				} catch (SQLException e) {
																					// TODO Auto-generated catch block
																					e.printStackTrace();
																				}
																		     }
																		}).start();		
										 																		 
																	}else {
																		System.out.println("Users Collpapsed!! "+ "User Selected "+loadedUserName.getValue() );
																		  loadedUserName.getChildren().clear();
																		  loadedUserName.getChildren().add(loadedUserNameDetailsLoading);
																	}
																
																}
															});
												}	  			
									    	}catch(SQLException e) {
									    		
									    	//	System.out.println("Error Code is :"+ e.getErrorCode());
									    	//	System.out.println("SQL State is "+ e.getSQLState());
									    	//	System.out.println("Get Cause"+ e.getCause());
									    	//	System.out.println("Get Localized Message" +e.getLocalizedMessage());
									    	//	System.out.println("Get MEssage"+ e.getMessage());
									    		
									    		e.printStackTrace();
									    		
									    		String errorString = "SQL Error["+e.getErrorCode()+"]["+e.getSQLState()+"]:"+e.getLocalizedMessage()+"/n"+e.getMessage();
									    		System.out.println(errorString);
									    	}
									     }
									}).start();
								}
							}
						}else {
							System.out.println("Users Collapsed!!");
							mySqlTreeItemUsers.getChildren().clear();
							mySqlTreeItemUsers.getChildren().add(loadingTreeItemUsers);
						}
			}
		}); */
		 
		//Administrator
		mySqlTreeItemAdminister = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Administer"));
		
		mySqlTreeItemAdministration = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Administration"));
		//mySqlTreeItemAdminister.getChildren().add(mySqlTreeItemAdministration);
		threadsafeAddTreeItem(mySqlTreeItemAdminister, mySqlTreeItemAdministration);
		mySqlTreeItemAdministerServerStatus = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("ServerStatus"));
		//mySqlTreeItemAdministration.getChildren().add(mySqlTreeItemAdministerServerStatus);
		threadsafeAddTreeItem(mySqlTreeItemAdministration, mySqlTreeItemAdministerServerStatus);
		mySqlTreeItemAdministerClientConnectionss = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("ClientConnections"));
		//mySqlTreeItemAdministration.getChildren().add(mySqlTreeItemAdministerClientConnectionss);
		threadsafeAddTreeItem(mySqlTreeItemAdministration, mySqlTreeItemAdministerClientConnectionss);
		mySqlTreeItemAdministerUserandPrivileges = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("UsersAndPrivileges"));
		//mySqlTreeItemAdministration.getChildren().add(mySqlTreeItemAdministerUserandPrivileges);
		threadsafeAddTreeItem(mySqlTreeItemAdministration, mySqlTreeItemAdministerUserandPrivileges);
		mySqlTreeItemAdministerStatusandSystemVariables = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("StatusAndSystemVariables"));
		//mySqlTreeItemAdministration.getChildren().add(mySqlTreeItemAdministerStatusandSystemVariables);
		threadsafeAddTreeItem(mySqlTreeItemAdministration, mySqlTreeItemAdministerStatusandSystemVariables);
		
		mySqlTreeItemPerformance = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Performance"));
		//mySqlTreeItemAdminister.getChildren().add(mySqlTreeItemPerformance);
		threadsafeAddTreeItem(mySqlTreeItemAdminister, mySqlTreeItemPerformance);
		mySqlTreeItemAdministerDashboard = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Dashboard"));
		//mySqlTreeItemPerformance.getChildren().add(mySqlTreeItemAdministerDashboard);
		threadsafeAddTreeItem(mySqlTreeItemPerformance, mySqlTreeItemAdministerDashboard);
		mySqlTreeItemAdministerPerformanceReports = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("PerformanceReports"));
		//mySqlTreeItemPerformance.getChildren().add(mySqlTreeItemAdministerPerformanceReports);
		threadsafeAddTreeItem(mySqlTreeItemPerformance, mySqlTreeItemAdministerPerformanceReports);
		
		mySqlTreeItemServer = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Server"));
		//mySqlTreeItemAdminister.getChildren().add(mySqlTreeItemServer);
		threadsafeAddTreeItem(mySqlTreeItemAdminister, mySqlTreeItemServer);
		mySqlTreeItemAdministerServerLogs = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("ServerLogs"));
		//mySqlTreeItemServer.getChildren().add(mySqlTreeItemAdministerServerLogs);
	    threadsafeAddTreeItem(mySqlTreeItemServer, mySqlTreeItemAdministerServerLogs);
		
		mySqlTreeItemAdminister.expandedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					
				Boolean value = ((BooleanProperty)observable).getValue() ;
				System.out.println("Its Administer expansion!!!"); 								

					
				 }
		 });
		
		// System Info
		mySqlTreeItemSystemInfo = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("SystemInfo"));
	    mySqlTreeItemSystemInfoBinaryLogs = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("BinaryLogs"));  // This will show Binary Log EVENTS
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoBinaryLogs);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoBinaryLogs);
		 mySqlTreeItemSystemInfoCharacterSet = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("CharacterSet"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoCharacterSet);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoCharacterSet);
		mySqlTreeItemSystemInfoCollation = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Collation"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoCollation);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoCollation);
		mySqlTreeItemSystemInfoEngines = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Engines"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoEngines);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoEngines);
		mySqlTreeItemSystemInfoErrors = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Errors"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoErrors);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoErrors);
		mySqlTreeItemSystemInfoEvents = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Events"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoEvents);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoEvents);
		//TreeItem<String> mySqlTreeItemSystemInfoGrants = new TreeItem<String>("GRANTS");
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoGrants);
		mySqlTreeItemSystemInfoOpenTables = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("OpenTables"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoOpenTables);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoOpenTables);
		mySqlTreeItemSystemInfoPlugins = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Plugins"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoPlugins);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoPlugins);
		mySqlTreeItemSystemInfoPreviliges = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Previliges"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoPreviliges);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoPreviliges);
	    mySqlTreeItemSystemInfoProcessList = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("ProcessList"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoProcessList);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoProcessList);
		mySqlTreeItemSystemInfoProfiles = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Profiles"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoProfiles);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoProfiles);
		mySqlTreeItemSystemInfoReplicas = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Replicas"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoReplicas);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoReplicas);
		mySqlTreeItemSystemInfoSessionStatus = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("SessionStatus"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoSessionStatus);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoSessionStatus);
		mySqlTreeItemSystemInfoGlobalStatus = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("GlobalStatus"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoGlobalStatus);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoGlobalStatus);
		mySqlTreeItemSystemInfoSessionVariables = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("SessionVariables"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoSessionVariables);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoSessionVariables);
		mySqlTreeItemSystemInfoGlobalVariables = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("GlobalVariables"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoGlobalVariables);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoGlobalVariables);
		mySqlTreeItemSystemInfoWarnings = new TreeItem<String>(menu_Items_FX.resourceBundle.getString("Warnings"));
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoWarnings);
		threadsafeAddTreeItem(mySqlTreeItemSystemInfo, mySqlTreeItemSystemInfoWarnings);
		
		
		//mySqlTreeItem.getChildren().add(mySqlTreeItemDatabases);
	//	mySqlTreeItem.getChildren().add(mySqlTreeItemUsers);
		//mySqlTreeItem.getChildren().add(mySqlTreeItemAdminister);
		//mySqlTreeItem.getChildren().add(mySqlTreeItemSystemInfo);
				
		threadsafeAddTreeItem(mySqlTreeItem, mySqlTreeItemDatabases);
		threadsafeAddTreeItem(mySqlTreeItem, mySqlTreeItemAdminister);
		threadsafeAddTreeItem(mySqlTreeItem, mySqlTreeItemSystemInfo);
		
		return mySqlTreeItem; 
		
	}
	
	
		
	// This is inner class within the main class to capture when tree elements are double clicked.
	final class MySQLTreecellImpl extends TreeCell<String>{
		
		private Connection innercurrentConnection;
		public MySQLTreecellImpl() {
			 super();
			 for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.mySqlConnectionsMap.entrySet())
			{
					// MySQL##con12
					if(connectionPlaceHolder.getConnectionName().equalsIgnoreCase(entrySet.getKey().getConnectionName()))
					{
						System.out.println("Current Connection is :"+ entrySet.getKey().getConnectionName() + " of type "+entrySet.getValue());
						Connection currentConnection = entrySet.getValue();
						innercurrentConnection = currentConnection;		
						try {
								stmt = currentConnection.createStatement();
						} catch (SQLException e) {
									// TODO Auto-generated catch block
								e.printStackTrace();
						}
					}
			 }
			 setOnMouseClicked(event -> {
				 TreeItem<String> ti = getTreeItem();
				 System.out.println("Current Tree item value is -->" + getTreeItem().getValue());
				 System.out.println("Parent Tree item value is -->" + getTreeItem().getParent().getValue());
				 // We are adding refresh here below coz we want this to be resetted for every treeitem selected
				 refreshPerformanceButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							displayPerformanceTableView(performanceReportsTypes, performanceReportQueries,getTreeItem().getValue());
						}
					});
				
				 int parentIndex = 0;
				 TreeItem treeitem = getTreeItem();
				 System.out.println("-------------");
				 System.out.println(treeitem.getValue() );
				 while(treeitem.getParent() != null) {
					 treeitem = treeitem.getParent();
					 System.out.println(treeitem.getValue());
					 parentIndex++;
				 }
				 System.out.println("-------------");
				 
				 /*
				 -------------
				Tables
				sakila
				Databases
				local
				Connections
				-------------
				*/
				 
				 if(parentIndex >= 2 && getTreeItem().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("Databases")) && getTreeItem().getParent().getParent().getValue().equals(currentConnectionName)) {
					 
					 System.out.println("Schema name clicked!!!!");
					 
	 
				 }
				 if(parentIndex >= 3 && getTreeItem().getParent().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("Databases")) && getTreeItem().getParent().getParent().getParent().getValue().equals(currentConnectionName)) {
					 
					 System.out.println("Schema components name clicked!!!!");
	 
					 
				 }
				 if(parentIndex >= 4 && getTreeItem().getParent().getValue().equals("Tables") && getTreeItem().getParent().getParent().getParent().getParent().getValue().equals(currentConnectionName)) {
					 
					 System.out.println("Schema sub components likely clicked!!!!");

				 }
				 
				 if(event.getClickCount() == 2 && ( 
						 	(parentIndex >= 2 && getTreeItem().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("Databases")) && getTreeItem().getParent().getParent(). getValue().equals(currentConnectionName))  
						 || (parentIndex >= 3 && getTreeItem().getParent().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("Databases")) && getTreeItem().getParent().getParent().getParent().getValue().equals(currentConnectionName)) 
						 || ( (parentIndex >= 4 && getTreeItem().getParent().getValue().equals("Tables") || parentIndex >= 4 && getTreeItem().getParent().getValue().equals("Views") 
						      || parentIndex >= 4 && getTreeItem().getParent().getValue().equals("Indexes") || parentIndex >= 4 && getTreeItem().getParent().getValue().equals("Procedures")
						      || parentIndex >= 4 && getTreeItem().getParent().getValue().equals("Functions") || parentIndex >= 4 && getTreeItem().getParent().getValue().equals("Triggers")
						      || parentIndex >= 4 && getTreeItem().getParent().getValue().equals("Events")) 
						 && getTreeItem().getParent().getParent().getParent().getParent().getValue().equals(currentConnectionName))
						 ) 
					)
				 {
					 
					 // check if database tab is already opened.
					 if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
						 if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {
							 System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
						 }
					 }
					 
					 ObservableList<Tab> existingTabs = menu_Items_FX.alltabbedEditors.getTabs();
					 for(int i=0;i<existingTabs.size();i++) {
						 System.out.println("Existing Tab name : "+existingTabs.get(i).getText());
						 if(existingTabs.get(i).getGraphic() != null) {
							 if(existingTabs.get(i).getGraphic().getId().split("##")[1].equals(currentConnectionName) && existingTabs.get(i).getGraphic().getId().split("##")[0].equals("MySql") && existingTabs.get(i).getText().equals(getTreeItem().getParent().getValue()) ) { 
								 System.out.println(" Match Found : "+existingTabs.get(i).getGraphic());
								 Tab selectedSchemaTab = existingTabs.get(i);
								 TabPane selectedSchemaTabPane = (TabPane) selectedSchemaTab.getContent();
								 Tab selectedSchemaDetailsTab = selectedSchemaTabPane.getTabs().get(0);
								 TabPane selectedSchemaDetailsTabPane = (TabPane) selectedSchemaDetailsTab.getContent();
								 System.out.println("Selcted Schema Tab --> : "+ selectedSchemaDetailsTabPane.getSelectionModel().getSelectedItem().getText());
								 
								 if( getTreeItem().getValue().equals("Properies")) {
									 selectedSchemaDetailsTabPane.getSelectionModel().select(0);
								 }
								 if( getTreeItem().getValue().equals("Tables")) {
									 selectedSchemaDetailsTabPane.getSelectionModel().select(1);
								 }
								 if( getTreeItem().getValue().equals("Views")) {
									 selectedSchemaDetailsTabPane.getSelectionModel().select(2);
								 }
								 if( getTreeItem().getValue().equals("Indexes")) {
									 selectedSchemaDetailsTabPane.getSelectionModel().select(3);
								 }
								 if( getTreeItem().getValue().equals("Procedures")) {
									 selectedSchemaDetailsTabPane.getSelectionModel().select(4);
								 }
								 if( getTreeItem().getValue().equals("Functions")) {
									 selectedSchemaDetailsTabPane.getSelectionModel().select(5);
								 }
								 if( getTreeItem().getValue().equals("Triggers")) {
									 selectedSchemaDetailsTabPane.getSelectionModel().select(6);
								 }
								 if( getTreeItem().getValue().equals("Events")) {
									 selectedSchemaDetailsTabPane.getSelectionModel().select(7);
								 }
								 menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
								 return;
							 }
						 }
					 }
					 
					 // particular database is clicked
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Databases"))) {
							
					    if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
					    	  if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {
					    		  System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
					    	  }
					    	  ObservableList<Tab> existingDatabaseTabs = menu_Items_FX.alltabbedEditors.getTabs();  // sakila,EVENTS,PLUGINS,Server Logs.
					    	  for(int i=0;i<existingDatabaseTabs.size();i++) {
					    		  System.out.println("Existing Tab name : "+existingDatabaseTabs.get(i).getText());
					    		  System.out.println("getTreeItem().getValue() -->"+getTreeItem().getValue() +" existingDatabaseTabs.get(i).getText().split(\"-\")[0] " + existingDatabaseTabs.get(i).getText().split("-")[0]);
					    		  if( getTreeItem().getValue().equals(existingDatabaseTabs.get(i).getText().split("-")[0])){
					    			  menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
					    			  return;
					    		  }
					    	  }
					      } 									
							Tab mainDatabaseTab = databaseDoubleClickMethod(getTreeItem(),"Properties");
							menu_Items_FX.alltabbedEditors.getTabs().add(mainDatabaseTab);
					        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
					        singleSelectionModel.select(mainDatabaseTab);
			 
					        return;
					 }
					 // if Tables on a whole is clicked
					 if(getTreeItem().getValue().equalsIgnoreCase("Tables")) {
							
						Tab mainDatabaseTab = databaseDoubleClickMethod(getTreeItem().getParent(),"Tables");
    	            	menu_Items_FX.alltabbedEditors.getTabs().add(mainDatabaseTab);	    	            
    	            	SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
						singleSelectionModel.select(mainDatabaseTab);
    	            	return;
					 }
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase("Tables")) {
						
						 if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
					    	  if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {
					    		  System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
					    	  }
					    	  ObservableList<Tab> existingTableTabs = menu_Items_FX.alltabbedEditors.getTabs();  // sakila,EVENTS,PLUGINS,Server Logs.
					    	  for(int i=0;i<existingTableTabs.size();i++) {
					    		  System.out.println("Existing Tab name : "+existingTabs.get(i).getText());
					    		  System.out.println("getTreeItem().getValue() -->"+getTreeItem().getValue() +" existingTableTabs.get(i).getText().split(\"-\")[0] " + existingTableTabs.get(i).getText().split("-")[0]);
					    		  if( getTreeItem().getValue().equals(existingTableTabs.get(i).getText().split("-")[0])){
					    			  menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
					    			  return;
					    		  }
					    	  }
					      } 											
						 Tab particularTableTab = particularTableDoubleClickMethod(getTreeItem().getValue(),getTreeItem().getParent().getParent().getValue());    	            	 
    	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);
    	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
					     singleSelectionModel.select(particularTableTab);
					     return;
					 }
					// if Views on a whole is clicked
					 if(getTreeItem().getValue().equalsIgnoreCase("Views")) {
						 
						  Tab mainDatabaseTab = databaseDoubleClickMethod(getTreeItem().getParent(),"Views");
	    	              menu_Items_FX.alltabbedEditors.getTabs().add(mainDatabaseTab);	    	              
	    	              SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
						  singleSelectionModel.select(mainDatabaseTab);
	    	              return;
					  } 
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase("Views")) {							
						 Tab particularTableTab = particularViewDoubleClickMethod(getTreeItem().getValue(),getTreeItem().getParent().getParent().getValue());    	            	 
    	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);
    	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
					     singleSelectionModel.select(particularTableTab);
					     return;
					 }
					 // if Indexes on a whole is clicked
					 if(getTreeItem().getValue().equalsIgnoreCase("Indexes")) {
						 
						  Tab mainDatabaseTab = databaseDoubleClickMethod(getTreeItem().getParent(),"Indexes");
	    	              menu_Items_FX.alltabbedEditors.getTabs().add(mainDatabaseTab);	    	              
	    	              SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
						  singleSelectionModel.select(mainDatabaseTab);
	    	              return;
					 }
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase("Indexes")) {
							
						 Tab particularTableTab = particularIndexesDoubleClickMethod(getTreeItem().getValue(),getTreeItem().getParent().getParent().getValue());    	            	 
    	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);
    	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
					     singleSelectionModel.select(particularTableTab);
					     return;
					 }
					 // if Procedures on a whole clicked
					 if(getTreeItem().getValue().equalsIgnoreCase("Procedures")) {
						 
						  Tab mainDatabaseTab = databaseDoubleClickMethod(getTreeItem().getParent(),"Procedures");
	    	              menu_Items_FX.alltabbedEditors.getTabs().add(mainDatabaseTab);	    	             
	    	              SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
						  singleSelectionModel.select(mainDatabaseTab);
	    	              return;
					 }
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase("Procedures")) {
							
						 Tab particularTableTab = particularProcedureDoubleClickMethod(getTreeItem().getValue(),getTreeItem().getParent().getParent().getValue());    	            	 
    	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);
    	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
					     singleSelectionModel.select(particularTableTab);
					     return;
					 }
					 // if Functions on a whole clicked
					 if(getTreeItem().getValue().equalsIgnoreCase("Functions")) {
						 
						  Tab mainDatabaseTab = databaseDoubleClickMethod(getTreeItem().getParent(),"Functions");
	    	              menu_Items_FX.alltabbedEditors.getTabs().add(mainDatabaseTab);
	    	              SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
						  singleSelectionModel.select(mainDatabaseTab);
	    	              return;
					 }
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase("Functions")) {
							
						 Tab particularTableTab = particularFunctionsDoubleClickMethod(getTreeItem().getValue(),getTreeItem().getParent().getParent().getValue());    	            	 
    	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);

    	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
					     singleSelectionModel.select(particularTableTab);
					     return;
					 }
					 // if Triggers on a whole clicked
					 if(getTreeItem().getValue().equalsIgnoreCase("Triggers")) {
						 
						  Tab mainDatabaseTab = databaseDoubleClickMethod(getTreeItem().getParent(),"Triggers");
	    	              menu_Items_FX.alltabbedEditors.getTabs().add(mainDatabaseTab);
	    	              SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
						  singleSelectionModel.select(mainDatabaseTab);
	    	              return;
					 }
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase("Triggers")) {
							
						 Tab particularTableTab = particularTriggersDoubleClickMethod(getTreeItem().getValue(),getTreeItem().getParent().getParent().getValue());
    	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);

    	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
					     singleSelectionModel.select(particularTableTab);
					     return;
					 }
					// if Events on a whole clicked
					 if(getTreeItem().getValue().equals("Events")) {
						 
						  Tab mainDatabaseTab = databaseDoubleClickMethod(getTreeItem().getParent(),"Events");
	    	              menu_Items_FX.alltabbedEditors.getTabs().add(mainDatabaseTab);

	    	              SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
						  singleSelectionModel.select(mainDatabaseTab);
	    	              return;
					 }
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase("Events")) {
							
						 Tab particularTableTab = particularEventssDoubleClickMethod(getTreeItem().getValue(),getTreeItem().getParent().getParent().getValue());
    	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);

    	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
					     singleSelectionModel.select(particularTableTab);
					     return;
					 }
					 
				 }
				 if(event.getClickCount() == 2 && getTreeItem().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("SystemInfo")) ) { 
					 
					 System.out.println("System info componets like clciked!!!");
				 }
					 
				 if(event.getClickCount() == 2  &&  (getTreeItem().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("SystemInfo")) || getTreeItem().getValue().equals(menu_Items_FX.resourceBundle.getString("ServerLogs")))){
					 
					 if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
						 if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {																			   
							 System.out.println("Current Tab opened is---> " + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
						}
					 
					 
					 ObservableList<Tab> existingTabs = menu_Items_FX.alltabbedEditors.getTabs();  // sakila,EVENTS,PLUGINS,Server Logs.
					 for(int i=0;i<existingTabs.size();i++) {
						 System.out.println("Existing Tab name : "+existingTabs.get(i).getText());
						 System.out.println("getTreeItem().getValue() -->"+getTreeItem().getValue() +" existingTabs.get(i).getText().split(\"-\")[0] " + existingTabs.get(i).getText().split("-")[0]);						 
						 if( getTreeItem().getValue().equals(existingTabs.get(i).getText().split("-")[0])){
							 menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
							 return;
						 }
	   
					 }
					} 
					 
					 
					 for( int i=0;i<actionTypes.length;i++) {
						 // Also do a parent check else we can have other components like tables,views with these names
						 if(getTreeItem().getValue().equalsIgnoreCase(actionTypes[i])) {
							 int index = i;
						      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
								Platform.runLater(new Runnable() {
   
									  @Override
									  public void run() { 
									    try  {
									    	ResultSet rs = stmt.executeQuery(actionTypeQuery[index]);
									    	try {
												Thread.sleep(1000);
											} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
											}
									    	
									    	secondHalfDisplayVBox = new VBox();
											Tab mainDisplayTab = new Tab();
											mainDisplayTab.getStyleClass().add("Tabs");
											mainDisplayTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));
											mainDisplayTab.setOnClosed(new EventHandler<Event>() {
												@Override
												public void handle(Event event) {
													System.out.println("On Tab close request ");
													// sessionManagerTab = null;
													menu_Items_FX.alltabbedEditors.getTabs().remove(mainDisplayTab);
												}
											});
											
											Node genericNode = new SplitPane();
									        ((SplitPane) genericNode).setOrientation(Orientation.VERTICAL);
									    	((SplitPane) genericNode).setDividerPositions(0.35);  // split pane divider moving a bit lower
											
											TableView resultAsTableView = new TableView();
											VBox topHalfResultTableView = new VBox();
											if (getTreeItem().getValue().equalsIgnoreCase("BINARY LOGS") || getTreeItem().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("ServerLogs"))) {
												resultAsTableView = showResultSetInTheTableView(rs,getTreeItem().getValue());
												topHalfResultTableView.getChildren().addAll(addTopHBoxForInfo(getTreeItem().getValue()),resultAsTableView);
												((SplitPane) genericNode).getItems().addAll(topHalfResultTableView,secondHalfDisplayVBox); // Top half of query editer							      
										    
											}
											else if(getTreeItem().getValue().equals("EVENTS") && getTreeItem().getParent().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("SystemInfo"))) {
												System.out.println("Show events");
												ResultSet rsDatabases = stmt.executeQuery("SHOW DATABASES");
										    	while(rsDatabases.next()){
										    		System.out.println("Executing query "+"SHOW EVENTS FROM "+rsDatabases.getString(1));
										    		stmt = innercurrentConnection.createStatement();
										    		rs = stmt.executeQuery("SHOW EVENTS FROM "+rsDatabases.getString(1));
										    		resultAsTableView = showResultSetInTheTableView(rs,resultAsTableView);
										    	}
										    	topHalfResultTableView.getChildren().addAll(addTopHBoxForInfo(getTreeItem().getValue()),resultAsTableView);
										    	((SplitPane) genericNode).getItems().addAll(topHalfResultTableView,secondHalfDisplayVBox); // Top half of query editer
											}else {
												resultAsTableView = showResultSetInTheTableView(rs);
												resultAsTableView.setMinHeight(menu_Items_FX.size.getHeight() - 210);
												topHalfResultTableView.getChildren().addAll(addTopHBoxForInfo(getTreeItem().getValue()),resultAsTableView);
												genericNode = new VBox();
												((VBox)genericNode).getChildren().add(topHalfResultTableView);
											}
											
											mainDisplayTab.setText(getTreeItem().getValue() +"-"+ connectionPlaceHolder.getConnectionName());
											
									        mainDisplayTab.setContent(genericNode);
											menu_Items_FX.alltabbedEditors.getTabs().add(mainDisplayTab);

									        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
									        singleSelectionModel.select(mainDisplayTab);
									        
										} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
										}
									  }
									});
						 }		 
					 }
					return; 
				 }
				 
				 // This check is skeptical make it more error proof.
				 if(event.getClickCount() == 1 && ( getTreeItem().getParent().getValue().equals("Memory Usage") || getTreeItem().getParent().getValue().equals("Hot Spots for I/O")
						 || getTreeItem().getParent().getValue().equals("High Cost SQL Statements") || getTreeItem().getParent().getValue().equals("Database Schema Statistics") || getTreeItem().getParent().getValue().equals("Wait Times")
						 || getTreeItem().getParent().getValue().equals("InnoDB Statistics") || getTreeItem().getParent().getValue().equals("User Resource Utilization") || getTreeItem().getParent().getValue().equals("Host Resource Utilization") 
						 || getTreeItem().getParent().getValue().equals("Other Information")) &&  getTreeItem().getParent().getParent().getValue().equals("Reports")) {
					 
					 System.out.println("Perforemace Reports clicked");
					 displayPerformanceTableView(performanceReportsTypes, performanceReportQueries,getTreeItem().getValue());
					 
				 }
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Dashboard")) && getTreeItem().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("Performance")) && getTreeItem().getParent().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("Administer")) ) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
		  
					  if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
							 if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {
								 System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
							 }
							 ObservableList<Tab> existingTabs = menu_Items_FX.alltabbedEditors.getTabs();  // sakila,EVENTS,PLUGINS,Server Logs.
							 for(int i=0;i<existingTabs.size();i++) {
							 System.out.println("Existing Tab name : "+existingTabs.get(i).getText());
							 System.out.println("getTreeItem().getValue() -->"+getTreeItem().getValue() +" existingTabs.get(i).getText().split(\"-\")[0] " + existingTabs.get(i).getText().split("-")[0]);
							 if( getTreeItem().getValue().equals(existingTabs.get(i).getText().split("-")[0])){
							 menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
							 return;
							 }
							 
						 }
						 }										   
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	
									Tab sessionManagerTab = new Tab("Dashboard" +"-"+ connectionPlaceHolder.getConnectionName());	
									sessionManagerTab.getStyleClass().add("Tabs");
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									getDashBoardStats(null,sessionManagerTab,"showPieGraph");
								  
									menu_Items_FX.alltabbedEditors.getTabs().add(sessionManagerTab);
									sessionManagerTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));
							        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							        singleSelectionModel.select(sessionManagerTab);
							        
								} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							  }
							});		
		  
				 } 				 
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("PerformanceReports")) && getTreeItem().getParent().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Performance"))
						 && getTreeItem().getParent().getParent().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Administer")) ) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
		  
					  if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
				    	  if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {
				    		  System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
				    	  }
				    	  ObservableList<Tab> existingTabs = menu_Items_FX.alltabbedEditors.getTabs();  // sakila,EVENTS,PLUGINS,Server Logs.
				    	  for(int i=0;i<existingTabs.size();i++) {
				    		  System.out.println("Existing Tab name : "+existingTabs.get(i).getText());
				    		  System.out.println("getTreeItem().getValue() -->"+getTreeItem().getValue() +" existingTabs.get(i).getText().split(\"-\")[0] " + existingTabs.get(i).getText().split("-")[0]);
				    		  if( getTreeItem().getValue().equals(existingTabs.get(i).getText().split("-")[0])){
				    			  menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
				    			  return;
				    		  }
				    	  }
				      }												   
			
						Platform.runLater(new Runnable() {
		
							  @Override
							  public void run() { 
							    try  {	
							    	
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	
							    	System.out.println("Connection Name :"+ connectionName);
							    	
									Tab sessionManagerTab = new Tab("Performance Reports" +"-"+connectionPlaceHolder.getConnectionName());										
									sessionManagerTab.getStyleClass().add("Tabs");
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TreeView<String> performanceView = generatePerformnaceReports();
									
									// TableView resultAsTableView =  showResultSetInTheTableView(rsUsers);
									// resultAsTableView.setMinHeight(menu_Items_FX.size.getHeight() - 50);
									
									BorderPane performanceReportsBorderPane = new BorderPane();
									VBox vboxLeft = new VBox();
									vboxLeft.setId("ScrollPane");
									vboxLeft.setPadding(new Insets(5,5,5,5));
									vboxLeft.setSpacing(5);
									Label performanceReportsLabel = new Label("Reports");
									//userAccountsLabel.setTextFill(Color.BLUEVIOLET);
									vboxLeft.getChildren().add(performanceReportsLabel);
									vboxLeft.getChildren().add(performanceView);	
									
									VBox vBoxCenterTop = new VBox();									vBoxCenterTop.setId("vboxPerformanceRep");
//									vBoxCenterTop.setPadding(new Insets(5,5,5,5));
//									vBoxCenterTop.setSpacing(5);
									particularPerformanceReportLabel =  new Label("Nothing Selected");
									//Label particularPerformanceReportDescription =  new Label("Shows total memory allowed");
									//detailForAccountLabel.setTextFill(Color.BLUEVIOLET);
									
									
								    performanceReportTableView = new TableView();
									performanceReportTableView.setMinHeight(menu_Items_FX.size.getHeight() - 300);
									
									HBox hboxPerformanceButtons = new HBox();
									hboxPerformanceButtons.setSpacing(400);
									
									HBox hboxPerformanceButtonsLeft = new HBox();
									hboxPerformanceButtonsLeft.setId("hboxPerformanceButtonsLeftRight");
//									hboxPerformanceButtonsLeft.setSpacing(10);
//									hboxPerformanceButtonsLeft.setPadding(new Insets(10,10,10,10));  
									
									exportPerformanceButton = new Button(menu_Items_FX.resourceBundle.getString("Export"));
									performanceCopySelected = new Button(menu_Items_FX.resourceBundle.getString("CopySelected"));
									performanceCopyQuery = new Button(menu_Items_FX.resourceBundle.getString("CopySelected"));
									
									
									exportPerformanceButton.setId("buttons");
									performanceCopySelected.setId("buttons");
									performanceCopyQuery.setId("buttons");
									hboxPerformanceButtonsLeft.getChildren().addAll(exportPerformanceButton,performanceCopySelected,performanceCopyQuery);
									
									HBox hboxPerformanceButtonsRight = new HBox();
									hboxPerformanceButtonsRight.setId("hboxPerformanceButtonsLeftRight");
									hboxPerformanceButtonsRight.setPadding(new Insets(10,10,10,10));
									refreshPerformanceButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
									refreshPerformanceButton.setId("buttons");
									hboxPerformanceButtonsRight.getChildren().add(refreshPerformanceButton);
									
									hboxPerformanceButtons.getChildren().addAll(hboxPerformanceButtonsLeft,hboxPerformanceButtonsRight);
									
									vBoxCenterTop.getChildren().addAll(particularPerformanceReportLabel,performanceReportTableView,hboxPerformanceButtons);
									
									performanceReportsBorderPane.setTop(addTopHBoxForInfo("Performance Reports"));
									performanceReportsBorderPane.setLeft(vboxLeft);
									performanceReportsBorderPane.setCenter(vBoxCenterTop);

									
									sessionManagerTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));
									sessionManagerTab.setContent(performanceReportsBorderPane);
									menu_Items_FX.alltabbedEditors.getTabs().add(sessionManagerTab);

							        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							        singleSelectionModel.select(sessionManagerTab);
							        
								} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							  }

							});		
				 }
		  
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("ServerStatus")) && getTreeItem().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("Administration"))) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
					  if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
				    	  if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {
				    		  System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
				    	  }
				    	  ObservableList<Tab> existingTabs = menu_Items_FX.alltabbedEditors.getTabs();  // sakila,EVENTS,PLUGINS,Server Logs.
				    	  for(int i=0;i<existingTabs.size();i++) {
				    		  System.out.println("Existing Tab name : "+existingTabs.get(i).getText());
				    		  System.out.println("getTreeItem().getValue() -->"+getTreeItem().getValue() +" existingTabs.get(i).getText().split(\"-\")[0] " + existingTabs.get(i).getText().split("-")[0]);
				    		  if( getTreeItem().getValue().equals(existingTabs.get(i).getText().split("-")[0])){
				    			  menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
				    			  return;
				    		  }
				    	  }
				      }											   
					  Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	
							    	HashMap<String,String> allVariables = new HashMap<String,String>(); 
							    	ResultSet rsVariables = stmt.executeQuery(" SHOW VARIABLES");
							    	while(rsVariables.next()) {
							    		System.out.println(rsVariables.getString(1)+ " " +rsVariables.getString(2));
							    		allVariables.put(rsVariables.getString(1), rsVariables.getString(2));
							    	}
							    	
							    	HashMap<String,String> allStatus = new HashMap<String,String>();
							    	stmt = innercurrentConnection.createStatement();
							    	ResultSet rsStatus = stmt.executeQuery(" SHOW STATUS");
							    	while(rsStatus.next()) {
							    		System.out.println(rsStatus.getString(1)+ " " +rsStatus.getString(2));
							    		allStatus.put(rsStatus.getString(1), rsStatus.getString(2));
							    	}
							    	
							    	HashMap<String,String> allPlugins = new HashMap<String,String>();
							    	stmt = innercurrentConnection.createStatement();
							    	ResultSet rsPlugins = stmt.executeQuery(" SHOW PLUGINS");
							    	while(rsPlugins.next()) {
							    		System.out.println(rsPlugins.getString(1)+ " " +rsPlugins.getString(2));
							    		allPlugins.put(rsPlugins.getString(1), rsPlugins.getString(2));
							    	}
							    	
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	
							    	
									Tab sessionManagerTab = new Tab("Server Status" +"-"+connectionPlaceHolder.getConnectionName());
									sessionManagerTab.getStyleClass().add("Tabs");
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									sessionManagerTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));
									VBox serverStatusVBox = addServerStatus(allVariables,allStatus,allPlugins);
									
									sessionManagerTab.setContent(serverStatusVBox);
									menu_Items_FX.alltabbedEditors.getTabs().add(sessionManagerTab);

							        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							        singleSelectionModel.select(sessionManagerTab);
							        
								} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							  }
							});
				 }
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("ClientConnections")) && getTreeItem().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("Administration"))) { 
				      System.out.println("Double clicked on this item"+ getTreeItem().getValue());
					  if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
							 if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {
								 System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
							 }
							 ObservableList<Tab> existingTabs = menu_Items_FX.alltabbedEditors.getTabs();  // sakila,EVENTS,PLUGINS,Server Logs.
							 for(int i=0;i<existingTabs.size();i++) {
							 System.out.println("Existing Tab name : "+existingTabs.get(i).getText());
							 System.out.println("getTreeItem().getValue() -->"+getTreeItem().getValue() +" existingTabs.get(i).getText().split(\"-\")[0] " + existingTabs.get(i).getText().split("-")[0]);
							 if( getTreeItem().getValue().equals(existingTabs.get(i).getText().split("-")[0])){
							 menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
							 return;
							 }
							 
						 }
						}											
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	
							    	HashMap<String,String> allVariables = new HashMap<String,String>(); 
							    	ResultSet rsStatusVariables = stmt.executeQuery(" SHOW GLOBAL STATUS");
							    	while(rsStatusVariables.next()) {
							    		allVariables.put(rsStatusVariables.getString(1), rsStatusVariables.getString(2));
							    	}
							    	ResultSet rsGlobalVariables = innercurrentConnection.createStatement().executeQuery(" SHOW GLOBAL VARIABLES");
							    	while(rsGlobalVariables.next()) {
							    		allVariables.put(rsGlobalVariables.getString(1), rsGlobalVariables.getString(2));
							    	}
							    
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	
							    	HashMap<String,String> allStatus = new HashMap<String,String>();
							    	stmt = innercurrentConnection.createStatement();
							    	ResultSet rsThreads = stmt.executeQuery("SELECT COALESCE(th.PROCESSLIST_ID,0) as Id,COALESCE(th.PROCESSLIST_USER,'None') as User,COALESCE(th.PROCESSLIST_HOST,'None') as Host,COALESCE(th.PROCESSLIST_DB,'None') as DB,COALESCE(th.PROCESSLIST_COMMAND,'None') as Command,COALESCE(th.PROCESSLIST_TIME,0) as Time,COALESCE(th.PROCESSLIST_STATE,'None') as State,th.THREAD_ID as Thread,th.TYPE as Type,th.NAME as Name,COALESCE(th.PARENT_THREAD_ID,0) as ParentThread,th.INSTRUMENTED as Instrumented,COALESCE(th.PROCESSLIST_INFO,'None') as Info,"
							    			+ "COALESCE(attr.ATTR_VALUE,'None') as Program FROM performance_schema.threads th  LEFT OUTER JOIN performance_schema.session_connect_attrs attr ON th.processlist_id = attr.processlist_id AND (attr.attr_name IS NULL OR attr.attr_name = 'program_name') WHERE th.TYPE <> 'BACKGROUND' ");  // 
							    	
									Tab sessionManagerTab = new Tab("Client Connections" + "-" +connectionPlaceHolder.getConnectionName());	
									sessionManagerTab.getStyleClass().add("Tabs");
									sessionManagerTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView =  showResultSetInTheTableView(rsThreads);
									
									VBox clientConnectionsVBox = addclientConnectionThreadDetails(allVariables,resultAsTableView);
									
									
									sessionManagerTab.setContent(clientConnectionsVBox);
									menu_Items_FX.alltabbedEditors.getTabs().add(sessionManagerTab);

							        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							        singleSelectionModel.select(sessionManagerTab);
							        
								} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							  }

						
							});		
				 }
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("UsersAndPrivileges")) && getTreeItem().getParent().getValue().equals(menu_Items_FX.resourceBundle.getString("Administration"))) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());											   
					  if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
							 if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {
								 System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
							 }
							 ObservableList<Tab> existingTabs = menu_Items_FX.alltabbedEditors.getTabs();  // sakila,EVENTS,PLUGINS,Server Logs.
							 for(int i=0;i<existingTabs.size();i++) {
							 System.out.println("Existing Tab name : "+existingTabs.get(i).getText());
							 System.out.println("getTreeItem().getValue() -->"+getTreeItem().getValue() +" existingTabs.get(i).getText().split(\"-\")[0] " + existingTabs.get(i).getText().split("-")[0]);
							 if( getTreeItem().getValue().equals(existingTabs.get(i).getText().split("-")[0])){
							 menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
							 return;
							 }
							 
						 }
						 }																				
						Platform.runLater(new Runnable() {
							 
							@Override
							  public void run() { 
							    try  {	
							    	
							    	ResultSet rsUsers = stmt.executeQuery("select User,Host from mysql.user");
							    
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	
							    	System.out.println("Connection Name :"+ connectionName);
							    	
									Tab sessionManagerTab = new Tab("Users and Privileges" +"-"+ connectionPlaceHolder.getConnectionName());	
									sessionManagerTab.getStyleClass().add("Tabs");
									sessionManagerTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));																			 
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									
									TableView resultAsTableView =  showResultSetInTheTableView(rsUsers);
									resultAsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

									// Can move this to invidual invocations as each call of this will ha`ve custom usage based on selection 
									resultAsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
												
									//	private Labeled userAccessSchemaDescriptionLabel;

										@Override
										public void changed(ObservableValue<? extends HashMap<String, String>> observable,
												HashMap<String, String> oldValue, HashMap<String, String> newValue) {

											System.out.println("oldValue --->"+oldValue);
											System.out.println("newValue --->"+newValue.keySet().toString());
											for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
												
												System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
											}
																						
											TableViewSelectionModel  selectionModel = resultAsTableView.getSelectionModel();
									        ObservableList selectedCells = selectionModel.getSelectedCells();
									        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
									        Object val = tablePosition.getTableColumn().getCellData(newValue);
									        System.out.println("Selected Value" + val);
																		        	
									        	try {		
									        		
												ResultSet rsfullUserDetails = stmt.executeQuery("select * from mysql.user where user='"+newValue.get("User")+"' and host = '"+newValue.get("Host")+"'");
												
												System.out.println("select * from mysql.user where user='"+newValue.get("User")+"' and host = '"+newValue.get("Host")+"'");
												if(rsfullUserDetails.next()) {
													//userAccessSchemaDescriptionLabel.setText("The user "+rsfullUserDetails.getString(newValue.get("User"))+"@"+rsfullUserDetails.getString(newValue.get("Host"))+" will have the following access rights to the schema "+rsfullUserDetails.getString(newValue.get("Schema")));
													
													// Setting the login details below  
													accountLockedStatus.setText( rsfullUserDetails.getString("account_locked"));
													loginNameTextFeild.setText(rsfullUserDetails.getString("User"));
													authenticationTypeTextField.setText(rsfullUserDetails.getString("plugin"));
													authenticationStringTextField.setText(rsfullUserDetails.getString("authentication_string"));
													hostsMatchingTextField.setText(rsfullUserDetails.getString("Host"));
													passwordExpiredStatusLabel.setText(rsfullUserDetails.getString("password_expired"));
													passwordLastchangedDate.setText(rsfullUserDetails.getString("password_last_changed"));
													passwordTextField.setText("*********");
													confirmPasswordTextField.setText("**********");
													
													// setting the addAccountLimits below
													maxQueriesTextFeild.setText( rsfullUserDetails.getString("max_questions"));
													maxUpdatesTextFeild.setText( rsfullUserDetails.getString("max_updates"));
													maxConnectionsTextFeild.setText( rsfullUserDetails.getString("max_connections"));
													concurrentConnectionsTextFeild.setText( rsfullUserDetails.getString("max_user_connections"));
													
													// setting the addAccountPrivileges below
													alterPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Alter_priv").equals("Y"));											
													alterRoutinePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Alter_routine_priv").equals("Y"));
													createPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_priv").equals("Y"));
													createRolePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_role_priv").equals("Y"));
													createRoutinePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_routine_priv").equals("Y")); 
													createTableSpacePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_tablespace_priv").equals("Y"));
													createTemporaryTablesPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_tmp_table_priv").equals("Y")); 
													createUserPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_user_priv").equals("Y"));
													createViewPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_view_priv").equals("Y"));
													deletePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Delete_priv").equals("Y"));
													
													dropPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Drop_priv").equals("Y"));
													dropRolePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Drop_role_priv").equals("Y"));
													eventPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Event_priv").equals("Y"));
													executePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Execute_priv").equals("Y"));
													filePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("File_priv").equals("Y"));
													grantOptionPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Grant_priv").equals("Y"));
													indexPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Index_priv").equals("Y"));
													insertPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Insert_priv").equals("Y"));
													lockTablesPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Lock_tables_priv").equals("Y"));
													processPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Process_priv").equals("Y"));
													
													referencesPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("References_priv").equals("Y"));
													reloadPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Reload_priv").equals("Y"));
													replicationSlavePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Repl_slave_priv").equals("Y"));
													replicationClientPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Repl_client_priv").equals("Y")); 
													selectPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Select_priv").equals("Y"));
													showDatabasesPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Show_db_priv").equals("Y"));
													showViewPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Show_view_priv").equals("Y"));
													shutdowmPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Shutdown_priv").equals("Y"));
													superPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Super_priv").equals("Y")); 
													triggerPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Trigger_priv").equals("Y"));
													updatePrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Update_priv").equals("Y"));
													
												
													
												}
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
									        
									        //we're pulling schema privileges
									        System.out.println("select TABLE_SCHEMA,PRIVILEGE_TYPE from information_schema.SCHEMA_PRIVILEGES where grantee=\"'"+newValue.get("User")+"'@'"+newValue.get("Host")+"'\"");
											try(ResultSet rsAvailableSchemaPrivileges = stmt.executeQuery("select TABLE_SCHEMA,PRIVILEGE_TYPE from information_schema.SCHEMA_PRIVILEGES where grantee=\"'"+newValue.get("User")+"'@'"+newValue.get("Host")+"'\"")) {
																										
												
												HashMap<String,ArrayList> availableSchemasPrivilegesMap = new HashMap<String,ArrayList>();
												
												while(rsAvailableSchemaPrivileges.next()) {
													 if(availableSchemasPrivilegesMap.containsKey( rsAvailableSchemaPrivileges.getString(1))) {
														 availableSchemasPrivilegesMap.get(rsAvailableSchemaPrivileges.getString(1)).add(rsAvailableSchemaPrivileges.getString(2));
													 }else {
														 ArrayList<String> schemaPrivilegesList = new ArrayList<String>();
														 schemaPrivilegesList.add(rsAvailableSchemaPrivileges.getString(2));
														 availableSchemasPrivilegesMap.put(rsAvailableSchemaPrivileges.getString(1),schemaPrivilegesList);
													 }
												}
												System.out.println("Populated values"+availableSchemasPrivilegesMap.toString());
												
												
												schemaPriviligesMapdata.clear();
										        HashMap<String,String> schemaPreviligesHashMap ;
										        for (String schemaName : availableSchemasPrivilegesMap.keySet()) {
										        	schemaPreviligesHashMap = new HashMap<String,String>();
											        schemaPreviligesHashMap.put(schemaPriviligesColumnNames[0], schemaName);
											        schemaPreviligesHashMap.put(schemaPriviligesColumnNames[1], availableSchemasPrivilegesMap.get(schemaName).toString());
											        schemaPriviligesMapdata.add(schemaPreviligesHashMap);
											        
										        }
										        if(!schemaPreviligestableView.getItems().isEmpty()) {
										        	schemaPreviligestableView.getSelectionModel().select(0);
										       
										        	schemaValueHolder = ((HashMap) schemaPreviligestableView.getSelectionModel().getSelectedItem()).get("Schema").toString();
										        	String availablePriviliges = ((HashMap) schemaPreviligestableView.getSelectionModel().getSelectedItem()).get("Privileges").toString();
										        	System.out.println( "<--------->" +availablePriviliges);
										        	
										        	// setting the addSchemaPriviliges below
													selectSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("SELECT"));
													updateSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("UPDATE"));
													insertSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("INSERT"));
													showViewSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("SHOW VIEW"));
													deleteSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("DELETE"));
													executeSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("EXECUTE"));
													
													createSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("CREATE"));
													alterSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("ALTER"));	
													referencesSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("REFERENCES"));
													indexSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("INDEX"));
													createViewSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("CREATE VIEW"));
													createRoutineSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("CREATE ROUTINE")); 
													
													alterRoutineSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("ALTER ROUTINE"));
													eventSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("EVENT"));
													dropSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("DROP"));
													triggerSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("TRIGGER"));
													grantOptionSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("GRANT OPTION"));
													createTemporaryTablesSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("CREATE TEMPORARY TABLES")); 
													lockTablesSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("LOCK TABLES"));
										        	
										        }	
										        
											} catch (SQLException e) {
												e.printStackTrace();;
											}
										}	
										
									});

									BorderPane userAccountsBorderPane = new BorderPane();
									VBox vboxLeft = new VBox();
									vboxLeft.setId("vboxUserAcc");
//									vboxLeft.setPadding(new Insets(5,5,5,5));
//									vboxLeft.setSpacing(5);
									userAccountsLabel = new Label(menu_Items_FX.resourceBundle.getString("UserAccounts"));
									//userAccountsLabel.setTextFill(Color.BLUEVIOLET);
									vboxLeft.getChildren().add(userAccountsLabel);
									vboxLeft.getChildren().add(resultAsTableView);
									
									HBox hBox = new HBox();
									hBox.setId("hBoxUserAcc");
									//hBox.setPadding(new Insets(5,5,5,5));
									addAccountButton = new Button(menu_Items_FX.resourceBundle.getString("AddAccount"));
									addAccountButton.setId("buttons");
									addAccountButton.setMinWidth(80);
								    addAccountButton.setOnAction(new EventHandler<ActionEvent>() {
							            @Override
							            public void handle(ActionEvent e) {
							            	System.out.println("Adding new row....");
							            	HashMap<String, String> newUserData = new HashMap<>();
							                
							                newUserData.put("User", "newuser");
							                newUserData.put("Host", "%");
							                resultAsTableView.getItems().add(newUserData);
							                loginNameTextFeild.setText(newUserData.get("User"));
											hostsMatchingTextField.setText(newUserData.get("Host"));										
							            }
							        });
									deletetButton = new Button(menu_Items_FX.resourceBundle.getString("Delete"));
									deletetButton.setId("buttons");
									deletetButton.setMinWidth(80);
									refreshButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
									refreshButton.setId("buttons");
									refreshButton.setMinWidth(80);
									hBox.getChildren().add(addAccountButton);
									hBox.getChildren().add(deletetButton);
									hBox.getChildren().add(refreshButton);
									//hBox.setSpacing(20);
									vboxLeft.getChildren().add(hBox);
								
									VBox vBoxCenterTop = new VBox();
									vBoxCenterTop.setId("vboxUserAcc");
//									vBoxCenterTop.setPadding(new Insets(5,5,5,5));
//									vBoxCenterTop.setSpacing(5);
									Label detailForAccountLabel =  new Label();
									//detailForAccountLabel.setTextFill(Color.BLUEVIOLET);
									resultAsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
										
										@Override
										public void changed(ObservableValue<? extends HashMap<String, String>> observable,
												HashMap<String, String> oldValue, HashMap<String, String> newValue) {

											System.out.println("oldValue --->"+oldValue);
											System.out.println("newValue --->"+newValue.keySet().toString());
											for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
												
												System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
											}
											detailForAccountLabel.setText("Details for Account -"+newValue.get("User"));
										}
									});
									vBoxCenterTop.getChildren().add(detailForAccountLabel);
									TabPane accountDetailsTabs = new TabPane();
									accountDetailsTabs.getStyleClass().addAll("accountDetailsTabs");  // box for the connection tabbed pane
									loginTab = new Tab(menu_Items_FX.resourceBundle.getString("Login"));
									loginTab.getStyleClass().add("Tabs");
									loginTab.setClosable(false);  
									loginTab.setContent(addAccountLoginCredentials()); // pass the user details here as parameterand set them
									accountLimitsTab = new Tab(menu_Items_FX.resourceBundle.getString("AccountLimits"));
									accountLimitsTab.getStyleClass().add("Tabs");
									accountLimitsTab.setClosable(false);	
									accountLimitsTab.setContent(addAccountLimits());
									accountPrivilegesTab = new Tab(menu_Items_FX.resourceBundle.getString("AccountPrivileges"));
									accountPrivilegesTab.getStyleClass().add("Tabs");
									accountPrivilegesTab.setClosable(false);
									accountPrivilegesTab.setContent(addAccountPrivileges());
									schemaPrivilegesTab = new Tab(menu_Items_FX.resourceBundle.getString("SchemaPrivileges"));
									schemaPrivilegesTab.getStyleClass().add("Tabs");
									schemaPrivilegesTab.setClosable(false);
									schemaPrivilegesTab.setContent(addSchemaPrivileges(resultAsTableView,schemaPreviligestableView));
									
									accountDetailsTabs.getTabs().addAll(loginTab,accountLimitsTab,accountPrivilegesTab,schemaPrivilegesTab);
									vBoxCenterTop.getChildren().add(accountDetailsTabs);
									
									userAccountsBorderPane.setTop(addTopHBoxForInfo("Users and Privileges"));
									userAccountsBorderPane.setLeft(vboxLeft);
									userAccountsBorderPane.setCenter(vBoxCenterTop);
									
									sessionManagerTab.setContent(userAccountsBorderPane);
									menu_Items_FX.alltabbedEditors.getTabs().add(sessionManagerTab);

							        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							        singleSelectionModel.select(sessionManagerTab);
							        
								} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							  }
							});		
				 }
				 if(event.getClickCount() == 2 && (getTreeItem().getValue().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("StatusAndSystemVariables")) || getTreeItem().getValue().equalsIgnoreCase("SESSION STATUS")
						 || getTreeItem().getValue().equalsIgnoreCase("GLOBAL STATUS") || getTreeItem().getValue().equalsIgnoreCase("SESSION VARIABLES") || getTreeItem().getValue().equalsIgnoreCase("GLOBAL VARIABLES"))) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());											   
					  if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty()) {
							 if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null) {
								 System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
							 }
							 ObservableList<Tab> existingTabs = menu_Items_FX.alltabbedEditors.getTabs();  // sakila,EVENTS,PLUGINS,Server Logs.
							 for(int i=0;i<existingTabs.size();i++) {
							 System.out.println("Existing Tab name : "+existingTabs.get(i).getText());
							 System.out.println("getTreeItem().getValue() -->"+getTreeItem().getValue() +" existingTabs.get(i).getText().split(\"-\")[0] " + existingTabs.get(i).getText().split("-")[0]);
							 if( getTreeItem().getValue().equals(existingTabs.get(i).getText().split("-")[0])){
							 menu_Items_FX.alltabbedEditors.getSelectionModel().select(i);
							 return;
							 }
							 
						 }
						 }																					
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
		
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	System.out.println("Connection Name :"+ connectionName);
							    	
							    	BorderPane mainPopUpborderPane = new BorderPane();
									HBox topHbox = addTopHBoxForInfo("Server Variables");
									TabPane centerTabPane = addCenterTabbedPaneForVariables();
									HBox bottomHbox = addBottomHBoxForVariables();
									mainPopUpborderPane.setTop(topHbox);
									mainPopUpborderPane.setCenter(centerTabPane);;
								//	mainPopUpborderPane.setBottom(bottomHbox);
									
							    	
									Tab sessionManagerTab = new Tab("Status and System Variables " + connectionPlaceHolder.getConnectionName());	
									sessionManagerTab.getStyleClass().add("Tabs");
									sessionManagerTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));																			 
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									SplitPane editerTabSplitPane = new SplitPane();
									editerTabSplitPane.setId("#SplitPane");
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.70);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(mainPopUpborderPane); // Top half of query editer
							        variablesSecondHalfDisplayVBox = new VBox();
							        editerTabSplitPane.getItems().add(variablesSecondHalfDisplayVBox); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
									menu_Items_FX.alltabbedEditors.getTabs().add(sessionManagerTab);

							        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							        singleSelectionModel.select(sessionManagerTab);
							        
								} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							  }
							});		
				 }
				 

				 // -----------------------END------------------
			 });
		}
		
		@Override
		protected void updateItem(String item, boolean empty) {
		    super.updateItem(item, empty);

		    if (item == null || empty) {
		            setText(null);
		    } else {
		          setText(item);
		    }
		 }
		   
		   // reference https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/tree-view.htm
	}
	
	private TableView showResultSetInTheTableView(ResultSet rs)  throws SQLException{
			
		TableView tableView = new TableView();
		//tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the last empty column otherwise added
		tableView.setEditable(true);
        
		
		// Can move this to invidua; invocations as each call of this will ha`ve custom usage based on selection 
		/* tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {

			@Override
			public void changed(ObservableValue<? extends HashMap<String, String>> observable,
					HashMap<String, String> oldValue, HashMap<String, String> newValue) {

				System.out.println("oldValue --->"+oldValue);
				System.out.println("newValue --->"+newValue.keySet().toString());
				for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
					
					System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
				}
				
				TableViewSelectionModel  selectionModel = tableView.getSelectionModel();
		        ObservableList selectedCells = selectionModel.getSelectedCells();
		        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		        Object val = tablePosition.getTableColumn().getCellData(newValue);
		        System.out.println("Selected Value" + val);
				
			}	
		}); */

		if(rs.next()) {
	
	    	TableColumn<Map, String> tableColumnName;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	       
	        
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnLabel(i+1);
	        	columnTypes[i] =  md.getColumnType(i+1);	   
	        	
	        	tableColumnName = new TableColumn<>(columnNames[i]);
	        	tableColumnName.setMinWidth(150);
	        	tableColumnName.setCellValueFactory(new MapValueFactory<>(columnNames[i]));
	        	// Below code will do cell editing
	        	tableColumnName.setCellFactory( new Callback<TableColumn<Map,String>, TableCell<Map,String>>() {
					@Override
					public TableCell<Map, String> call(TableColumn<Map, String> param) {
						 return new EditingCell();
					}
				});
	        	
	        
	        	tableColumnName.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Map,String>>() {		
					@Override
					public void handle(CellEditEvent<Map, String> t) {
						System.out.println("Editing firstName..");
	                	System.out.println("Table View "+ t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	 t.getTableView().getItems().get(t.getTablePosition().getRow()).replace(t.getTableColumn(), t.getTableView().getItems().get(t.getTablePosition().getRow()).get(t.getTableColumn()));
	                	System.out.println("Table Position"+t.getTablePosition().getRow());
	                	System.out.println("Table Column"+t.getTableColumn());
					}
				});
	        	
	 	        tableView.getColumns().add(tableColumnName);
	        }		
		
	       do {
				  
	        	String[] columnValues = new String[md.getColumnCount()];
	        	tableRowValue = new HashMap<>();
	        	for (int i = 0; i < columnNames.length; i++) {
	        		columnValues[i] =  rs.getString(i+1); 
	        		
	            	tableRowValue.put(columnNames[i], columnValues[i]);
	    	        
	        	}	
	        	items.add(tableRowValue);
	         }	 while (rs.next());

	        
	        tableView.getItems().addAll(items);
		}
		
		return tableView;
}



	private TableView showResultSetInTheTableView(ResultSet rs,TableView tableView)  throws SQLException{
		
		//tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the last empty column otherwise added
		tableView.setEditable(true);
	
        /*
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {

			@Override
			public void changed(ObservableValue<? extends HashMap<String, String>> observable,
					HashMap<String, String> oldValue, HashMap<String, String> newValue) {

				System.out.println("oldValue --->"+oldValue);
				performanceCopySelected.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						
				        clipBoardcontent.putString(newValue.keySet().toString()+"\n"+newValue.values().toString());
				        clipboard.setContent(clipBoardcontent);
					}
				});
				for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
					
					System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
				}
				
				TableViewSelectionModel  selectionModel = tableView.getSelectionModel();
		        ObservableList selectedCells = selectionModel.getSelectedCells();
		        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		        Object val = tablePosition.getTableColumn().getCellData(newValue);
		        System.out.println("Selected Value" + val);
				
			}	
		});
		*/
		if(rs.next()) {
	
	    	TableColumn<Map, String> tableColumnName;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	       
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnLabel(i+1);
	        	columnTypes[i] =  md.getColumnType(i+1);	   
	        	
	        	tableColumnName = new TableColumn<>(columnNames[i]);
	        	tableColumnName.setMinWidth(150);
	        	tableColumnName.setCellValueFactory(new MapValueFactory<>(columnNames[i]));
	        	// Below code will do cell editing
	        	tableColumnName.setCellFactory( new Callback<TableColumn<Map,String>, TableCell<Map,String>>() {
					@Override
					public TableCell<Map, String> call(TableColumn<Map, String> param) {
						 return new EditingCell();
					}
				});
	        	
	        
	        	tableColumnName.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Map,String>>() {		
					@Override
					public void handle(CellEditEvent<Map, String> t) {
						System.out.println("Editing firstName..");
	                	System.out.println("Table View "+ t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	 t.getTableView().getItems().get(t.getTablePosition().getRow()).replace(t.getTableColumn(), t.getTableView().getItems().get(t.getTablePosition().getRow()).get(t.getTableColumn()));
	                	System.out.println("Table Position"+t.getTablePosition().getRow());
	                	System.out.println("Table Column"+t.getTableColumn());
					}
				}); 
	 	        tableView.getColumns().add(tableColumnName);
	        }		
		
	       do {
				  
	        	String[] columnValues = new String[md.getColumnCount()];
	        	tableRowValue = new HashMap<>();
	        	for (int i = 0; i < columnNames.length; i++) {
	        		columnValues[i] =  rs.getString(i+1); 
	        		
	            	tableRowValue.put(columnNames[i], columnValues[i]);
	    	        
	        	}	
	        	items.add(tableRowValue);
	         }	 while (rs.next());

	        
	        tableView.getItems().addAll(items);
	        
		}
		
		return tableView;
	}
	
	public Label hboxDescriptionLabel;
	public Label statusVariableLabel ;
	public Label variableNameLabel;
	public Button saveVariablebtn;
	
	private TableView showResultSetInTheTableView(ResultSet rs,String inputParam)  throws SQLException{
		
		TableView tableView = new TableView();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the last empty column otherwise added
		tableView.setEditable(true);
	
        
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {

			@Override
			public void changed(ObservableValue<? extends HashMap<String, String>> observable,
					HashMap<String, String> oldValue, HashMap<String, String> newValue) {

			
				System.out.println("oldValue --->"+oldValue);
				System.out.println("newValue --->"+newValue.keySet().toString());
				
				// Do the alignment here , along with is Editable entry by doing look up to MySQLConstants enum
				if(inputParam.equalsIgnoreCase("Status") || inputParam.equalsIgnoreCase("Variables")) {
					variablesSecondHalfDisplayVBox.getChildren().clear();
					
					System.out.println("Description: "+newValue.get("Description"));
					System.out.println("Value: "+newValue.get("Value"));
					System.out.println("Name: "+newValue.get("Variable_name"));
					
					HBox hboxDescriptionValue = new HBox();
					hboxDescriptionValue.setId("hboxDescriptionValue");
					//hboxDescriptionValue.setPadding(new Insets(10,10,10,30));
					
					HBox hboxDescriptionhBox= new HBox();
					hboxDescriptionhBox.setId("description-box");
//					hboxDescriptionhBox.setSpacing(10);
//					hboxDescriptionhBox.setPadding(new Insets(20,10,20,30));
					hboxDescriptionLabel = new Label(menu_Items_FX.resourceBundle.getString("Description"));
					hboxDescriptionLabel.setId("label");
					//hboxDescriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));					
					Label hboxDescriptionValueLabel = new Label(newValue.get("Description"));
					hboxDescriptionhBox.getChildren().addAll(hboxDescriptionLabel,hboxDescriptionValueLabel);
					
					
					Label statusVariableDescriptionLabel = new Label("Description: "+ newValue.get("Description"));
					statusVariableDescriptionLabel.setId("label");
					//statusVariableDescriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
					hboxDescriptionValue.getChildren().add(statusVariableDescriptionLabel);	
											
					HBox hboxVariableNameValue = new HBox();
					hboxVariableNameValue.setId("hboxVariableNameValue");
//					hboxVariableNameValue.setPadding(new Insets(10,10,10,30));
//					hboxVariableNameValue.setSpacing(100);
					
					HBox variableNamehBox= new HBox();
					variableNamehBox.setId("variableName-hbox");
//					variableNamehBox.setSpacing(10);
//					variableNamehBox.setPrefWidth(300);
					variableNameLabel = new Label(menu_Items_FX.resourceBundle.getString("Name"));
					variableNameLabel.setId("label");
					//variableNameLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
					Label variableNameValueLabel = new Label(newValue.get("Variable_name"));
					variableNamehBox.getChildren().addAll(variableNameLabel,variableNameValueLabel);
					
					HBox hboxVariableValue = new HBox();
					hboxVariableValue.setId("variableValue-hbox");
//					hboxVariableValue.setSpacing(10);
//					hboxVariableValue.setPrefWidth(200);
					statusVariableLabel = new Label(menu_Items_FX.resourceBundle.getString("Value"));
					TextField statusVariableValue = new TextField(newValue.get("Value"));
					statusVariableLabel.setId("label");
					statusVariableValue.getStyleClass().add("textfield");
					//statusVariableLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
					hboxVariableValue.getChildren().addAll(statusVariableLabel,statusVariableValue);																		
							
					HBox btnSaveVariablehBox = new HBox();
					btnSaveVariablehBox.setId("btnSaveVariablehBox");
//					btnSaveVariablehBox.setPrefWidth(150);
//					btnSaveVariablehBox.setAlignment(Pos.BOTTOM_RIGHT);
					//btnBox.setPadding(new Insets(10,100,10,0));
					
					saveVariablebtn = new Button();					
					saveVariablebtn.setId("buttons");					
					if("Y".equals( MySQLConstants.valueOf(newValue.get("Variable_name")).getIsEditable())) {
						saveVariablebtn.setText(menu_Items_FX.resourceBundle.getString("Save"));
						saveVariablebtn.setDisable(false);
					}else {
						saveVariablebtn.setText(menu_Items_FX.resourceBundle.getString("ReadOnly"));
						saveVariablebtn.setDisable(true);
					}
					
					GridPane gridPane = new GridPane();
					gridPane.setId("StatusVariablesGridPane");
					gridPane.add(hboxDescriptionhBox,0,0,2,3);
					gridPane.add(hboxVariableNameValue,0,4);
//					gridPane.setVgap(10);    // Vertical gap between rows
//			        gridPane.setHgap(10);
			        
					btnSaveVariablehBox.getChildren().add(saveVariablebtn);										
					hboxVariableNameValue.getChildren().addAll(variableNamehBox,hboxVariableValue,btnSaveVariablehBox);	

					variablesSecondHalfDisplayVBox.setId("variablesSecondHalfDisplayVBox");
					//variablesSecondHalfDisplayVBox.setPadding(new Insets(0, 40, 10, 0));
					variablesSecondHalfDisplayVBox.getChildren().add(gridPane);
				}
				if(inputParam.equalsIgnoreCase("BINARY LOGS") || inputParam.equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("ServerLogs"))) {
					secondHalfDisplayVBox.getChildren().clear();
					for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
						System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
					
						if("Log_name".equalsIgnoreCase(tableValues.getKey()))
							try {
								ResultSet resultSetbinlogEvents =   stmt.executeQuery("SHOW BINLOG EVENTS IN '"+tableValues.getValue()+"'");
								TableView tableView =  showResultSetInTheTableView(resultSetbinlogEvents);
								secondHalfDisplayVBox.getChildren().add(tableView);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
				
				TableViewSelectionModel  selectionModel = tableView.getSelectionModel();
		        ObservableList selectedCells = selectionModel.getSelectedCells();
		        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		        Object val = tablePosition.getTableColumn().getCellData(newValue);
		        System.out.println("Selected Value" + val);
			}	
		});
		if(rs.next()) {
	
	    	TableColumn<Map, String> tableColumnName = null;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	       
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnLabel(i+1);
	        	columnTypes[i] =  md.getColumnType(i+1);	   
	        	
	        	tableColumnName = new TableColumn<>(columnNames[i]);
	        	tableColumnName.setMinWidth(150);
	        	tableColumnName.setCellValueFactory(new MapValueFactory<>(columnNames[i]));
	        	// Below code will do cell editing
	        	tableColumnName.setCellFactory( new Callback<TableColumn<Map,String>, TableCell<Map,String>>() {
					@Override
					public TableCell<Map, String> call(TableColumn<Map, String> param) {
						 return new EditingCell();
					}
				});
	        		        
	        	tableColumnName.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Map,String>>() {		
					@Override
					public void handle(CellEditEvent<Map, String> t) {
						System.out.println("Editing firstName..");
	                	System.out.println("Table View "+ t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	 t.getTableView().getItems().get(t.getTablePosition().getRow()).replace(t.getTableColumn(), t.getTableView().getItems().get(t.getTablePosition().getRow()).get(t.getTableColumn()));
	                	System.out.println("Table Position"+t.getTablePosition().getRow());
	                	System.out.println("Table Column"+t.getTableColumn());
					}
				});
	 	        tableView.getColumns().add(tableColumnName);
	        }	
	        if(inputParam.equalsIgnoreCase("Status") || inputParam.equalsIgnoreCase("Variables")) {
	        	tableColumnName = new TableColumn<>("Description");
	        	tableColumnName.setCellValueFactory(new MapValueFactory<>("Description"));
	        	tableView.getColumns().add(tableColumnName);
	        }
        	// Below code will do cell editing
        	tableColumnName.setCellFactory( new Callback<TableColumn<Map,String>, TableCell<Map,String>>() {
				@Override
				public TableCell<Map, String> call(TableColumn<Map, String> param) {
					 return new EditingCell();
				}
			});    
        	tableColumnName.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Map,String>>() {		
				@Override
				public void handle(CellEditEvent<Map, String> t) {
					System.out.println("Editing firstName..");
                	System.out.println("Table View "+ t.getTableView().getItems().get(t.getTablePosition().getRow()));
                	 t.getTableView().getItems().get(t.getTablePosition().getRow()).replace(t.getTableColumn(), t.getTableView().getItems().get(t.getTablePosition().getRow()).get(t.getTableColumn()));
                	System.out.println("Table Position"+t.getTablePosition().getRow());
                	System.out.println("Table Column"+t.getTableColumn());
				}
			});
	       
		
	       do {
				  
	        	String[] columnValues = new String[md.getColumnCount()];
	        	tableRowValue = new HashMap<>();
	        	for (int i = 0; i < columnNames.length; i++) {
	        		columnValues[i] =  rs.getString(i+1); 
	        		
	            	tableRowValue.put(columnNames[i], columnValues[i]);
	    	        
	        	}	
	        	if(inputParam.equalsIgnoreCase("Status") || inputParam.equalsIgnoreCase("Variables")) {
	        	// look-up value . We are using try catch because if enum doesn't have a value it will throw exception 
	        		try {
	        			tableRowValue.put("Description",MySQLConstants.valueOf(rs.getString(1)).getDescription());
	        		}catch(Exception e) {
	        			System.out.print("Enum Not found for-->"+rs.getString(1));
	        		}
	        	}
	        	items.add(tableRowValue);
	         }	 while (rs.next());

	        
	        tableView.getItems().addAll(items);
		}
		
		return tableView;
	}

	private TableView showResultSetInTheTableViewDoubleClick(ResultSet rs,String sqlComponentForwhich,String databaseName)  throws SQLException{
		
		TableView tableView = new TableView();
		tableView.getStyleClass().add("table-view");
		tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);  //to ensure columns do not resize proportionally
        
		if(rs.next()) {
	
	    	TableColumn<Map, String> tableColumnName;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	           
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnLabel(i+1);
	        	columnTypes[i] =  md.getColumnType(i+1);	   
	        	
	        	tableColumnName = new TableColumn<>(columnNames[i]);
	        	tableColumnName.setPrefWidth(250);
	            tableColumnName.setMinWidth(150);							  
	        	tableColumnName.setCellValueFactory(new MapValueFactory<>(columnNames[i]));
	        		
	        	tableView.setRowFactory( tv -> { 
	        	    TableRow<HashMap> row = new TableRow<>();
	        	    row.setOnMouseClicked(event -> {
	        	        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	        	        	HashMap rowData = row.getItem();
	        	        	System.out.println("this.connectionPlaceHolder ->"+this.connectionPlaceHolder.toString());
	        	        	System.out.println("For --->"+sqlComponentForwhich);	
	        	            System.out.println("Double CLicked "+rowData.toString());
	        	            
	        	            if(sqlComponentForwhich.equalsIgnoreCase("Tables")) {
	        	            	 System.out.println("Pop up a new Tab for Tables from here ");
	        	            	 Tab particularTableTab = particularTableDoubleClickMethod(rowData.get("TABLE_NAME").toString(),databaseName);
	        	            	 
	        	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);

	        	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							     singleSelectionModel.select(particularTableTab);
	        	            }
	        	            if(sqlComponentForwhich.equalsIgnoreCase("Views")) {
	        	            	 System.out.println("Pop up a new Tab for Views from here ");
	        	            	 Tab particularTableTab = particularViewDoubleClickMethod(rowData.get("TABLE_NAME").toString(),databaseName);
	        	            	 
	        	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);

	        	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							     singleSelectionModel.select(particularTableTab);
	        	            }
	        	        }
	        	    });
	        	    row.setOnKeyPressed (event -> {
	        	        if (event.getCode() == KeyCode.ENTER && (! row.isEmpty()) ) {
	        	        	HashMap rowData = row.getItem();
	        	        	System.out.println("this.connectionPlaceHolder ->"+this.connectionPlaceHolder.toString());
	        	        	System.out.println("For --->"+sqlComponentForwhich);	
	        	            System.out.println("Double CLicked "+rowData.toString());
	        	            
	        	            if(sqlComponentForwhich.equalsIgnoreCase("Tables")) {
	        	            	 System.out.println("Pop up a new Tab for Tables from here ");
	        	            	 Tab particularTableTab = particularTableDoubleClickMethod(rowData.get("TABLE_NAME").toString(),databaseName);
	        	            	 
	        	            	 menu_Items_FX.alltabbedEditors.getTabs().add(particularTableTab);

	        	            	 SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							     singleSelectionModel.select(particularTableTab);
	        	            }

	        	        }
	        	    });
	        	    return row ;
	        	});
	        	
	          tableView.getColumns().add(tableColumnName);
	        }		
	       do {
	        	String[] columnValues = new String[md.getColumnCount()];
	        	tableRowValue = new HashMap<>();
	        	for (int i = 0; i < columnNames.length; i++) {
	        		columnValues[i] =  rs.getString(i+1); 	
	            	tableRowValue.put(columnNames[i], columnValues[i]);
	        	}	
	        	items.add(tableRowValue);
	         }	 while (rs.next());
	        tableView.getItems().addAll(items);
		}
		
		return tableView;
}
	private Tab particularTableDoubleClickMethod(String tableName,String databaseName) {
		
		Tab particularTableMainTab = new Tab(tableName);
		particularTableMainTab.getStyleClass().add("Tabs");
		particularTableMainTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));		
  
		VBox particularTableMainTabVBox = new VBox();
		//particularTableMainTabVBox.setSpacing(10);
		particularTableMainTabVBox.setId("connectionLabel");
		particularTableMainTabVBox.getChildren().add(addTopHBoxForInfo("Table "+tableName+" for Connection "+currentConnectionName));
		
		TabPane particularTableTabPane = new TabPane();
		particularTableTabPane.setId("Tabpane");
//		particularTableTabPane.setTabMinWidth(180);
//		particularTableTabPane.setTabMinHeight(30);
		particularTablePropertiesTab = new Tab(menu_Items_FX.resourceBundle.getString("Properties"));
		particularTablePropertiesTab.getStyleClass().add("Tabs");
		particularTablePropertiesTab.setClosable(false);
		particularTableDataTab = new Tab(menu_Items_FX.resourceBundle.getString("Data"));
		particularTableDataTab.getStyleClass().add("Tabs");
		particularTableDataTab.setClosable(false);
		particularTableERDiagramTab = new Tab(menu_Items_FX.resourceBundle.getString("ERDiagram"));
		particularTableERDiagramTab.getStyleClass().add("Tabs");
		particularTableERDiagramTab.setClosable(false);
		
		particularTableGraphVisualsTab = new Tab(menu_Items_FX.resourceBundle.getString("GraphVisuals"));
		particularTableGraphVisualsTab.getStyleClass().add("Tabs");
		particularTableGraphVisualsTab.setClosable(false);
		
		
	    particularTableAiPromptTab = new Tab(menu_Items_FX.resourceBundle.getString("AIPrompt"));
		particularTableAiPromptTab.getStyleClass().add("Tabs");
		particularTableAiPromptTab.setClosable(false);
		particularTableMainTabVBox.getChildren().addAll(particularTableTabPane)	;
		
		// Properties
		TabPane particularTablePropertiesTabbedPane = new TabPane();
		particularTablePropertiesTabbedPane.getStyleClass().add("Tabs");
		particularTablePropertiesTabbedPane.setId("TablePropertiesTabbedPane");
		particularTablePropertiesTabbedPane.setSide(Side.LEFT);
		particularTablePropertiesTabbedPane.setRotateGraphic(true);
//		particularTablePropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
//		particularTablePropertiesTabbedPane.setTabMaxHeight(200);
//		particularTablePropertiesTabbedPane.setTabMinWidth(42);
//		particularTablePropertiesTabbedPane.setTabMaxWidth(42);
		
	
		// Details
		Tab particularTabledetailsTab = new Tab();
		particularTabledetailsTab.getStyleClass().add("Tabs");
		particularTabledetailsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Details"));
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularTabledetailsTab.setGraphic(stp);
		particularTabledetailsTab = getParticularTableDetailsTab(tableName, databaseName,particularTabledetailsTab);
		

		// Columns
		Tab particularTablecolumnsTab = new Tab();
		particularTablecolumnsTab.getStyleClass().add("Tabs");
		particularTablecolumnsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Columns"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTablecolumnsTab.setGraphic(stp);
		
	
		Tab particularTableconstraintsTab = new Tab();
		particularTableconstraintsTab.getStyleClass().add("Tabs");
		particularTableconstraintsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Constraints"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTableconstraintsTab.setGraphic(stp);
		

		Tab particularTableforeignKeysTab = new Tab();
		particularTableforeignKeysTab.getStyleClass().add("Tabs");
		particularTableforeignKeysTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("ForeignKeys"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTableforeignKeysTab.setGraphic(stp);
		

		Tab particularTablereferencesTab = new Tab();
		particularTablereferencesTab.getStyleClass().add("Tabs");
		particularTablereferencesTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("References"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTablereferencesTab.setGraphic(stp);
		
		Tab particularTabletriggersTab = new Tab();
		particularTabletriggersTab.getStyleClass().add("Tabs");
		particularTabletriggersTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Triggers"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTabletriggersTab.setGraphic(stp);
		
		
		Tab particularTableindexesTab = new Tab();
		particularTableindexesTab.getStyleClass().add("Tabs");
		particularTableindexesTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Indexes"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTableindexesTab.setGraphic(stp);
		
		Tab particularTablepartitionsTab = new Tab();
		particularTablepartitionsTab.getStyleClass().add("Tabs");
		particularTablepartitionsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Partitions"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTablepartitionsTab.setGraphic(stp);

		Tab particularTableDDLTab = new Tab();
		particularTableDDLTab.getStyleClass().add("Tabs"); 
		particularTableDDLTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Source/DDL"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTableDDLTab.setGraphic(stp);
		
		particularTablePropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
			
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Columns"))) {
					 getParticularTableColumnsTab(tableName,databaseName,particularTablecolumnsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Constraints"))) {
					getParticularTableConstraintsTab(tableName,databaseName,particularTableconstraintsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("ForeignKeys"))) {
					getParticularTableForeignKeysTab(tableName,databaseName,particularTableforeignKeysTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("References"))) {
					getParticularTableReferencesTab(tableName,databaseName,particularTablereferencesTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Triggers"))) {
					getParticularTableTriggerTab(tableName,databaseName,particularTabletriggersTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Indexes"))) {
					getParticulatTableIndexesTab(tableName,databaseName,particularTableindexesTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Partitions"))) {
					getParticularTablePartitionTab(tableName,databaseName,particularTablepartitionsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Source/DDL"))) {
					getParticularTableSourceDDLTab(tableName,databaseName,particularTableDDLTab);
				}
			}
		});
		
		particularTableTabPane.getTabs().addAll(particularTablePropertiesTab,particularTableDataTab,particularTableERDiagramTab,particularTableGraphVisualsTab,particularTableAiPromptTab);
		
		particularTablePropertiesTabbedPane.getTabs().addAll(particularTabledetailsTab,particularTablecolumnsTab,particularTableconstraintsTab,particularTableforeignKeysTab,
				particularTablereferencesTab,particularTabletriggersTab,particularTableindexesTab,particularTablepartitionsTab,particularTableDDLTab);
		particularTablePropertiesTab.setContent(particularTablePropertiesTabbedPane);
		
		// Individual Tabs selected under the main table tab
		particularTableTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {

				System.out.println("Tab under Table Selected is "+newValue.getText());

				if(newValue.getText().equals(menu_Items_FX.resourceBundle.getString("Data"))) {
					System.out.println("Data Tab under Table selected");
					try {
						ResultSet rsTableData = stmt.executeQuery("SELECT * FROM " + databaseName + "." + tableName);
						System.out.println(("SELECT * FROM " + databaseName + "." + tableName));
												
						VBox particularTableDataTabVBox = new VBox();
						//particularTableDataTabVBox.setId("TableDataTabVBox");
						particularTableDataTabVBox.setId("TabVBox");
//						particularTableDataTabVBox.setSpacing(10);
//						particularTableDataTabVBox.setPadding(new Insets(2,2,2,2));
						TableView particularTableDatapartitionsView = showResultSetInTheTableView(rsTableData); 
						HBox particularTableDatapartitionsButtonsHBox = new HBox();
						particularTableDatapartitionsButtonsHBox.setId("DatapartitionsButtonsHBox");
						//particularTableDatapartitionsButtonsHBox.setPadding(new Insets(10,10,10,10));
//						particularTableDatapartitionsButtonsHBox.getChildren().add(new Button(menu_Items_FX.resourceBundle.getString("Create")){{ setId("buttons"); }});
						createTableButton =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
						createTableButton.setId("buttons");
						particularTableDatapartitionsButtonsHBox.getChildren().add(createTableButton);
						particularTableDataTabVBox.getChildren().addAll(particularTableDatapartitionsView,particularTableDatapartitionsButtonsHBox);
						particularTableDataTab.setContent(particularTableDataTabVBox);
						//particularTableDataTab.setContent(new TableView());
					}catch(SQLException e) {
						e.printStackTrace();
					}		
				}
				if(newValue.getText().equals(menu_Items_FX.resourceBundle.getString(menu_Items_FX.resourceBundle.getString("GraphVisuals")))) {
					System.out.println("Graph Visual Tab under Table selected");
					particularTableGraphVisualsTab.setContent(getGraphVisualsForParticulatTable(tableName,databaseName));
				}
			}

		});

				
		particularTableMainTab.setContent(particularTableMainTabVBox);
		
		return particularTableMainTab;
	}

	protected SplitPane getGraphVisualsForParticulatTable(String tableName,String databaseName) {
		
		SplitPane graphVisualsForParticularTableMainSplitPane = new SplitPane();
		graphVisualsForParticularTableMainSplitPane.setId("SplitPane");
		//graphVisualsForParticularTableMainSplitPane.setMinWidth(300);
		//graphVisualsForParticularTableMainSplitPane.setMaxWidth(350);
		graphVisualsForParticularTableMainSplitPane.setOrientation(Orientation.HORIZONTAL);
	    graphVisualsForParticularTableMainSplitPane.setDividerPositions(0.5);
		
	    
	    SplitPane graphVisualsForParticularTableMainSplitPaneLeftHalfPane = new SplitPane();
	    graphVisualsForParticularTableMainSplitPaneLeftHalfPane.setId("SplitPane");
	    graphVisualsForParticularTableMainSplitPaneLeftHalfPane.setMinWidth(250);
	    graphVisualsForParticularTableMainSplitPaneLeftHalfPane.setMaxWidth(350);
	    graphVisualsForParticularTableMainSplitPaneLeftHalfPane.setOrientation(Orientation.VERTICAL);
	    graphVisualsForParticularTableMainSplitPaneLeftHalfPane.setDividerPositions(0.5);
	    
	    CategoryAxis xAxis1    = new CategoryAxis();
	    xAxis1.setLabel("Tables");
	    NumberAxis yAxis1 = new NumberAxis();
	    yAxis1.setLabel("Size (MB)");
	     
	    
	    BarChart   barChart = new BarChart(xAxis1, yAxis1);
	    barChart.setTitle("Table disk Usage");
	    barChart.setTitleSide(Side.TOP);
	    barChart.setBarGap(2);
	    barChart.setCategoryGap(15);
	    barChart.setHorizontalGridLinesVisible(true);
	    barChart.setVerticalGridLinesVisible(true);
	     
	    XYChart.Series dataSeries1 = new XYChart.Series();
	    XYChart.Series dataSeries2 = new XYChart.Series();
	    XYChart.Series dataSeries3 = new XYChart.Series();
	    dataSeries1.setName("Index Size");
	    dataSeries2.setName("Data Size");
	    dataSeries3.setName("Total Size");
	    
	    String indexLength = "0";
	    String dataLength = "0";
	    try {
			ResultSet rsTable = stmt.executeQuery("select DATA_LENGTH,INDEX_LENGTH from information_schema.tables where table_schema = '"+ databaseName +"' and table_name= '"+tableName+"'");
			
			if(rsTable.next()) {
				dataLength = rsTable.getString(1);
				indexLength = rsTable.getString(2);
			}
	    }catch(Exception e) {
	    	e.printStackTrace();;
	    }
	  
	    dataSeries1.getData().add(new XYChart.Data(tableName,get2digitDoublePrecisionValue( Double.valueOf(indexLength)/1024/1024 )));
	    dataSeries2.getData().add(new XYChart.Data(tableName,get2digitDoublePrecisionValue( (Double.valueOf(dataLength))/1024/1024 )));
	    dataSeries3.getData().add(new XYChart.Data(tableName,get2digitDoublePrecisionValue((Double.valueOf(indexLength) + Double.valueOf(dataLength))/1024/1024 )));
	    barChart.getData().addAll(dataSeries1,dataSeries2,dataSeries3);
	    
	    for (final  XYChart.Series series : (ObservableList<XYChart.Series>) barChart.getData()) {
	         for (final XYChart.Data<String, Double> data : ( ObservableList<XYChart.Data<String, Double>>)series.getData()) {
	             Tooltip tooltip = new Tooltip();
	             tooltip.setText(data.getXValue().toString() +" "+ 
	                          data.getYValue().toString());
	             Tooltip.install(data.getNode(), tooltip);
	         }
	     }
	    
	    Double tableSize = get2digitDoublePrecisionValue( (Double.valueOf(indexLength) + Double.valueOf(dataLength))/1024/1024);
		ArrayList<InformationSchemaTable> tableDetailsInformationSchemaList = getSchemaTablesFromInformationSchema(databaseName);
		Double totalDiskUsage = getSchemaDiskUsage(tableDetailsInformationSchemaList);
		System.out.println("Schema Size zz:"+totalDiskUsage);
		System.out.println("Table Zise zz:"+tableSize);
		
	    PieChart tableDetailsPieChart = new PieChart();
	    tableDetailsPieChart.setId("chart");
		tableDetailsPieChart.setTitle("Table usage : "+ tableSize +" MB / "+totalDiskUsage+" MB");
		PieChart.Data slice ;
		tableDetailsPieChart.setMinWidth(300);
		
		tableDetailsPieChart.getData().add(new PieChart.Data(tableName +" "+(tableSize) + " MB",(tableSize)));
		tableDetailsPieChart.getData().add(new PieChart.Data("Other Tables"+" "+(totalDiskUsage - tableSize) + " MB", totalDiskUsage - tableSize)
				);
		
	    graphVisualsForParticularTableMainSplitPaneLeftHalfPane.getItems().add(barChart);
	    graphVisualsForParticularTableMainSplitPaneLeftHalfPane.getItems().add(tableDetailsPieChart);
	    
	    graphVisualsForParticularTableMainSplitPane.getItems().add(graphVisualsForParticularTableMainSplitPaneLeftHalfPane);
		graphVisualsForParticularTableMainSplitPane.getItems().add(new HBox());
		return graphVisualsForParticularTableMainSplitPane;
	}

	private Tab getParticularTableSourceDDLTab(String tableName,String databaseName,Tab particularTableDDLTab) {
		try {
			
			ResultSet rsTable = stmt.executeQuery("Show Create table "+databaseName+"."+tableName);
			
			VBox particularTableDDLTabVBox = new VBox();
			particularTableDDLTabVBox.setId("TabVBox");
			//particularTableDDLTabVBox.setSpacing(10);
			//particularTableDDLTabVBox.setPadding(new Insets(2,2,2,2));
			TextArea particularTableDDLTextArea = new TextArea("/***/");
			particularTableDDLTextArea.getStyleClass().add("text-area"); 
			if(rsTable.next()) {
				particularTableDDLTextArea = new TextArea(rsTable.getString(2));
			}
		
			particularTableDDLTextArea.setEditable(false);
			particularTableDDLTextArea.setWrapText(true);
			particularTableDDLTextArea.setMinHeight(menu_Items_FX.size.getHeight()-280);
			particularTableDDLTabVBox.getChildren().addAll(particularTableDDLTextArea);
			particularTableDDLTab.setContent(particularTableDDLTabVBox);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularTableDDLTab;
	}

	private Tab getParticularTablePartitionTab(String tableName,String databaseName,Tab particularTablepartitionsTab) {

		try {
			
			String selectedColumns = "partition_name,subpartition_name,PARTITION_ORDINAL_POSITION ,SUBPARTITION_ORDINAL_POSITION,PARTITION_METHOD ,SUBPARTITION_METHOD,PARTITION_EXPRESSION , SUBPARTITION_EXPRESSION,PARTITION_DESCRIPTION,TABLE_ROWS ,AVG_ROW_LENGTH ,DATA_LENGTH,CREATE_TIME,UPDATE_TIME";
			
			System.out.println("select "+ selectedColumns +" from information_schema.partitions where table_schema = '"+ databaseName +"' and table_name = '"+ tableName+"' ");
			ResultSet rsTable = stmt.executeQuery("select "+ selectedColumns +" from information_schema.partitions where table_schema = '"+ databaseName +"' and table_name = '"+ tableName+"' ");
			 
			VBox particularTablepartitionsTabVBox = new VBox();
			particularTablepartitionsTabVBox.setId("TabVBox");
//			particularTablepartitionsTabVBox.setSpacing(10);
//			particularTablepartitionsTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTablepartitionsView = showResultSetInTheTableView(rsTable); 
			HBox particularTablepartitionsButtonsHBox = new HBox();
			particularTablepartitionsButtonsHBox.setId("DatapartitionsButtonsHBox");
			//particularTablepartitionsButtonsHBox.setPadding(new Insets(10,10,10,10));
			//particularTablepartitionsButtonsHBox.getChildren().add(new Button("Create") {{ setId("buttons"); }});
			particularTablepartitionsButtons =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
			particularTablepartitionsButtons.setId("buttons");
			particularTablepartitionsButtonsHBox.getChildren().add(particularTablepartitionsButtons);
			particularTablepartitionsTabVBox.getChildren().addAll(particularTablepartitionsView,particularTablepartitionsButtonsHBox);
			particularTablepartitionsTab.setContent(particularTablepartitionsTabVBox);
		}catch(SQLException e) {
			e.printStackTrace();;
		}
		return particularTablepartitionsTab;
	}

	private Tab getParticulatTableIndexesTab(String tableName,String databaseName,Tab particularTableindexesTab) {

		try {
			
			String selectedColumns = "INDEX_NAME,COLUMN_NAME,INDEX_TYPE,NULLABLE,IS_VISIBLE,COMMENT,INDEX_COMMENT,COLLATION,CARDINALITY";
			
			System.out.println("select "+ selectedColumns +" from information_schema.statistics where table_schema = '"+ databaseName +"' and table_name = '"+ tableName+"' ");
			ResultSet rsTable = stmt.executeQuery("select "+ selectedColumns +" from information_schema.statistics where table_schema = '"+ databaseName +"' and table_name = '"+ tableName+"' ");
			
			VBox particularTableindexesTabVBox = new VBox();
			particularTableindexesTabVBox.setId("TabVBox");
//			particularTableindexesTabVBox.setSpacing(10);
//			particularTableindexesTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTableindexesView = showResultSetInTheTableView(rsTable); 
			HBox particularTableindexesButtonsHBox = new HBox();
			particularTableindexesButtonsHBox.setId("DatapartitionsButtonsHBox");
			//particularTableindexesButtonsHBox.setPadding(new Insets(10,10,10,10));
			//particularTableindexesButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
			particularTableIndexesButtons =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
			particularTableIndexesButtons.setId("buttons");
			particularTableindexesButtonsHBox.getChildren().add(particularTableindexesButtonsHBox);
			particularTableindexesTabVBox.getChildren().addAll(particularTableindexesView,particularTableindexesButtonsHBox);
			particularTableindexesTab.setContent(particularTableindexesTabVBox);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularTableindexesTab;
	}

	private Tab getParticularTableTriggerTab(String tableName,String databaseName,Tab particularTabletriggersTab) {

		try {
				String selectedColumns = "TRIGGER_NAME,EVENT_MANIPULATION,EVENT_OBJECT_TABLE,ACTION_ORDER,ACTION_ORIENTATION,ACTION_TIMING,CREATED";
			
				System.out.println("select "+ selectedColumns +" from information_schema.triggers where trigger_Schema = '"+ databaseName +"' and event_object_table = '"+ tableName+"' ");
				ResultSet rsTable = stmt.executeQuery("select "+ selectedColumns +" from information_schema.triggers where trigger_Schema = '"+ databaseName +"' and event_object_table = '"+ tableName+"'");
			
				VBox particularTabletriggersTabVBox = new VBox();
				particularTabletriggersTabVBox.setId("TabVBox");
//				particularTabletriggersTabVBox.setSpacing(10);
//				particularTabletriggersTabVBox.setPadding(new Insets(2,2,2,2));
				TableView particularTabletriggersView = showResultSetInTheTableView(rsTable);
				HBox particularTabletriggersButtonsHBox = new HBox();
				particularTabletriggersButtonsHBox.setId("DatapartitionsButtonsHBox");
				//particularTabletriggersButtonsHBox.setPadding(new Insets(10,10,10,10));
				//particularTabletriggersButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
				particularTableTriggersButtons =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
				particularTableTriggersButtons.setId("buttons");
				particularTabletriggersButtonsHBox.getChildren().add(particularTableTriggersButtons);
				particularTabletriggersTabVBox.getChildren().addAll(particularTabletriggersView,particularTabletriggersButtonsHBox);
				particularTabletriggersTab.setContent(particularTabletriggersTabVBox);
			}catch(SQLException e) {
			
			}
		return particularTabletriggersTab;
	}
	
	

	private Tab getParticularTableReferencesTab(String tableName,String databaseName,Tab particularTablereferencesTab) {

		try {			
			
			String selectedColumns = "CONSTRAINT_NAME,TABLE_NAME,REFERENCED_TABLE_NAME,UNIQUE_CONSTRAINT_NAME,UPDATE_RULE,DELETE_RULE";
			
			System.out.println("select "+ selectedColumns +" from information_schema.referential_constraints where  referenced_table_name = '"+ tableName+"' ");
			ResultSet rsTable = stmt.executeQuery("select "+ selectedColumns +" from information_schema.referential_constraints where  referenced_table_name = '"+ tableName+"'");
		
			VBox particularTablereferencesTabVBox = new VBox();
			particularTablereferencesTabVBox.setId("TabVBox");
//			particularTablereferencesTabVBox.setSpacing(10);
//			particularTablereferencesTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTablereferencesView = showResultSetInTheTableView(rsTable); 
			HBox particularTablereferencesButtonsHBox = new HBox();
			particularTablereferencesButtonsHBox.setId("DatapartitionsButtonsHBox");
			//particularTablereferencesButtonsHBox.setPadding(new Insets(10,10,10,10));
			//particularTablereferencesButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
			particularTableReferencesButtons =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
			particularTableReferencesButtons.setId("buttons");
			particularTablereferencesButtonsHBox.getChildren().add(particularTableReferencesButtons);
			particularTablereferencesTabVBox.getChildren().addAll(particularTablereferencesView,particularTablereferencesButtonsHBox);
			particularTablereferencesTab.setContent(particularTablereferencesTabVBox);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularTablereferencesTab;
	}

	private Tab getParticularTableForeignKeysTab(String tableName,String databaseName,Tab particularTableforeignKeysTab) {
		
		try {
			
			String selectedColumns = "CONSTRAINT_NAME,COLUMN_NAME,REFERENCED_COLUMN_NAME,REFERENCED_TABLE_NAME,REFERENCED_TABLE_SCHEMA,ORDINAL_POSITION,POSITION_IN_UNIQUE_CONSTRAINT";
			
			System.out.println("select "+ selectedColumns +" from information_schema.key_column_usage where table_schema = '"+ databaseName +"' and table_name='"+tableName+"' and referenced_column_name is not null ");
			ResultSet rsTable = stmt.executeQuery("select "+ selectedColumns +" from information_schema.key_column_usage where table_schema = '"+ databaseName +"' and table_name='"+tableName+"' and referenced_column_name is not null ");
		
			VBox particularTableforeignKeysTabVBox = new VBox();
			particularTableforeignKeysTabVBox.setId("TabVBox");
//			particularTableforeignKeysTabVBox.setSpacing(10);
//			particularTableforeignKeysTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTableforeignKeysView = showResultSetInTheTableView(rsTable);
			HBox particularTableforeignKeysButtonsHBox = new HBox();
			particularTableforeignKeysButtonsHBox.setId("DatapartitionsButtonsHBox");
			//particularTableforeignKeysButtonsHBox.setPadding(new Insets(10,10,10,10));
			//particularTableforeignKeysButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
			particularTableforeignKeysButtons =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
			particularTableforeignKeysButtons.setId("buttons");
			particularTableforeignKeysButtonsHBox.getChildren().add(particularTableforeignKeysButtons);
			particularTableforeignKeysTabVBox.getChildren().addAll(particularTableforeignKeysView,particularTableforeignKeysButtonsHBox);
			particularTableforeignKeysTab.setContent(particularTableforeignKeysTabVBox);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularTableforeignKeysTab;
	}

	private Tab getParticularTableConstraintsTab(String tableName,String databaseName,Tab particularTableconstraintsTab) {
	
		try {
			String selectedColumns = "CONSTRAINT_NAME,COLUMN_NAME,REFERENCED_COLUMN_NAME,REFERENCED_TABLE_SCHEMA,REFERENCED_TABLE_NAME,ORDINAL_POSITION,POSITION_IN_UNIQUE_CONSTRAINT";
			
			System.out.println("select "+ selectedColumns +" from information_schema.key_column_usage where table_schema = '"+ databaseName +"' and table_name='"+tableName+"'");
			ResultSet rsTable = stmt.executeQuery("select "+ selectedColumns +" from information_schema.key_column_usage where table_schema = '"+ databaseName +"' and table_name='"+tableName+"'");
			
			VBox particularTableconstraintsTabVBox = new VBox();
			particularTableconstraintsTabVBox.setId("TabVBox");
//			particularTableconstraintsTabVBox.setSpacing(10);
//			particularTableconstraintsTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTableconstraintsView = showResultSetInTheTableView(rsTable); 
			HBox particularTableconstraintsButtonsHBox = new HBox();
			particularTableconstraintsButtonsHBox.setId("DatapartitionsButtonsHBox");
			//particularTableconstraintsButtonsHBox.setPadding(new Insets(10,10,10,10));
			//particularTableconstraintsButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
			particularTableConstraintsButtons =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
			particularTableConstraintsButtons.setId("buttons");
			particularTableconstraintsButtonsHBox.getChildren().add(particularTableConstraintsButtons);
			particularTableconstraintsTabVBox.getChildren().addAll(particularTableconstraintsView,particularTableconstraintsButtonsHBox);
			particularTableconstraintsTab.setContent(particularTableconstraintsTabVBox);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return particularTableconstraintsTab;
	}

	private Tab getParticularTableColumnsTab(String tableName,String databaseName,Tab particularTablecolumnsTab) {
		
		try {
			
			String selectedColumns = "COLUMN_NAME,ORDINAL_POSITION,COLUMN_DEFAULT,IS_NULLABLE,DATA_TYPE,"
					+ "CHARACTER_MAXIMUM_LENGTH,CHARACTER_OCTET_LENGTH,NUMERIC_PRECISION,NUMERIC_SCALE,DATETIME_PRECISION,"
					+ "CHARACTER_SET_NAME,COLLATION_NAME,COLUMN_TYPE,COLUMN_KEY,EXTRA,PRIVILEGES,COLUMN_COMMENT,GENERATION_EXPRESSION";
			
			ResultSet rsTable = stmt.executeQuery("select "+ selectedColumns +" from information_schema.columns where table_schema = '"+ databaseName +"' and table_name='"+tableName+"'");
						
			VBox particularTablecolumnsTabVBox = new VBox();
			particularTablecolumnsTabVBox.setId("TabVBox");
			//particularTablecolumnsTabVBox.setSpacing(10);
			particularTablecolumnsTabVBox.setMinHeight(menu_Items_FX.size.getHeight()-300);
			//particularTablecolumnsTabVBox.setPadding(new Insets(2,2,2,2));

			TableView particularTableColumnsView = showResultSetInTheTableView(rsTable);								
				
			HBox particularTableColumnsButtonsHBox = new HBox();
			particularTableColumnsButtonsHBox.setId("DatapartitionsButtonsHBox");
			//particularTableColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
			//particularTableColumnsButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
			particularTableColumnsButtons =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
			particularTableColumnsButtons.setId("buttons");
			particularTableColumnsButtonsHBox.getChildren().add(particularTableColumnsButtons);
			particularTablecolumnsTabVBox.getChildren().addAll(particularTableColumnsView,particularTableColumnsButtonsHBox);
			particularTablecolumnsTab.setContent(particularTablecolumnsTabVBox);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularTablecolumnsTab;
	}

	
	private Tab getParticularTableDetailsTab(String tableName,String databaseName,Tab particularTabledetailsTab) {
		
		try {
			ResultSet rsTable = stmt.executeQuery("select * from information_schema.tables where table_schema = '"+ databaseName +"' and table_name= '"+tableName+"'");
			try {
				Thread.sleep(100);			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			while(rsTable.next()) {
			
				VBox particularTableDetailsVBox = new VBox();
				particularTableDetailsVBox.setId("TableDetailsVBox");
				//particularTableDetailsVBox.setSpacing(10);
				//particularTableDetailsVBox.setPadding(new Insets(2,2,2,2));
				//particularTableDetailsVBox.setPadding(new Insets(20,10,10,50));
				
				GridPane tableDetailGridPane = new GridPane();
				tableDetailGridPane.setVgap(8);
				tableDetailGridPane.setHgap(10);
				
				String lableName[] = {"Engine:","Version:","Table Rows:","Auto Increment","Data Length:","Index Length:","Max Data Length:","Avg Row Length","Row Format:",
						"Data Free:","Update Time:","Creation Time:","Table Collation:","Create Options:","Comment:"};
				
				String labelNameValue[] = {"ENGINE","VERSION","TABLE_ROWS","AUTO_INCREMENT","DATA_LENGTH","INDEX_LENGTH","MAX_DATA_LENGTH","AVG_ROW_LENGTH","ROW_FORMAT","DATA_FREE","UPDATE_TIME","CREATE_TIME",
						"TABLE_COLLATION","CREATE_OPTIONS","TABLE_COMMENT"};
				
				Double tableSize = 0d;
				
				for(int i =0;i<lableName.length;i++) {
					if(lableName[i].equals("Data Length:")) {
						tableSize = tableSize + rsTable.getInt(labelNameValue[i]);
					}
					if(lableName[i].equals("Index Length:")) {
						tableSize = tableSize + rsTable.getInt(labelNameValue[i]);
					}
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setId("labelNameValueLabel");
					//labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
					GridPane.setConstraints(labelNameValueLabel, 1, i);
					
					tableDetailGridPane.getChildren().addAll(labelName,labelNameValueLabel);
				}
				
				ArrayList<InformationSchemaTable> tableDetailsInformationSchemaList = getSchemaTablesFromInformationSchema(databaseName);
				Double totalDiskUsage = getSchemaDiskUsage(tableDetailsInformationSchemaList);
				tableSize =  tableSize/1024/1024;
				System.out.println("Schema Size:"+totalDiskUsage);
				System.out.println("Table Zise :"+tableSize);
				
				Label tableSizeLabelName = new Label("Table Size :");
				GridPane.setConstraints(tableSizeLabelName, 0, lableName.length);   // column 0 row 0
				Label tableSizeLabelNameValue= new Label(tableSize+" MB / "+totalDiskUsage+" MB");
				tableSizeLabelNameValue.setId("labelNameValueLabel");
				//tableSizeLabelNameValue.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
				GridPane.setConstraints(tableSizeLabelNameValue, 1, lableName.length);
				tableDetailGridPane.getChildren().addAll(tableSizeLabelName,tableSizeLabelNameValue);
				
				HBox tablesDetailAndGraphHBox = new HBox();
				tablesDetailAndGraphHBox.setSpacing(10);
				
				PieChart tableDetailsPieChart = new PieChart();
				tableDetailsPieChart.setId("chart");
				tableDetailsPieChart.setTitle("Table Size of "+get2digitDoublePrecisionValue( tableSize) +" MB in Schema Size of "+totalDiskUsage+" MB");
				PieChart.Data slice ;
				tableDetailsPieChart.setMinWidth(300);
				
				tableDetailsPieChart.getData().add(new PieChart.Data(tableName + " "+ get2digitDoublePrecisionValue(tableSize)+" MB",tableSize));
				tableDetailsPieChart.getData().add(new PieChart.Data("Other Tables"+ " "+ get2digitDoublePrecisionValue(totalDiskUsage - tableSize)+" MB", totalDiskUsage - tableSize));
				
				tablesDetailAndGraphHBox.getChildren().addAll(tableDetailGridPane,tableDetailsPieChart);
				particularTableDetailsVBox.getChildren().add(tablesDetailAndGraphHBox);
				particularTabledetailsTab.setContent(particularTableDetailsVBox);
				
			}
			
		}catch(SQLException e) {
			
		}
		return particularTabledetailsTab;
	}

	private Tab getParticularViewDetailsTab(String tableName,String databaseName,Tab particularViewdetailsTab) {
		
	
		try {
			ResultSet rsTable = stmt.executeQuery("select * from information_schema.views where table_schema = '"+ databaseName +"' and table_name= '"+tableName+"'");
			try {
				Thread.sleep(1000);			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			while(rsTable.next()) {
			
				VBox particularViewDetailsVBox = new VBox();
				particularViewDetailsVBox.setId("ViewDetailsVBox");
//				particularViewDetailsVBox.setSpacing(10);
//				particularViewDetailsVBox.setPadding(new Insets(2,2,2,2));
//				particularViewDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane viewDetailGridPane = new GridPane();
				viewDetailGridPane.setVgap(8);
				viewDetailGridPane.setHgap(10);
				
				String lableName[] = {"Check Option:","Is Updatable:","Definer:","Security Type","Character Set Client:","Collation Connection:"};
				
				String labelNameValue[] = {"CHECK_OPTION","IS_UPDATABLE","DEFINER","SECURITY_TYPE","CHARACTER_SET_CLIENT","COLLATION_CONNECTION"};
				
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setId("labelNameValueLabel");
					//labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
					GridPane.setConstraints(labelNameValueLabel, 1, i);
					
					viewDetailGridPane.getChildren().addAll(labelName,labelNameValueLabel);
				}
				
				particularViewDetailsVBox.getChildren().add(viewDetailGridPane);
				particularViewdetailsTab.setContent(particularViewDetailsVBox);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularViewdetailsTab;
	}

	private Tab getParticularViewColumnsTab(String tableName,String databaseName,Tab particularViewcolumnsTab) {
		
		try {
			
			String selectedColumns = "COLUMN_NAME,ORDINAL_POSITION,COLUMN_DEFAULT,IS_NULLABLE,DATA_TYPE,"
					+ "CHARACTER_MAXIMUM_LENGTH,CHARACTER_OCTET_LENGTH,NUMERIC_PRECISION,NUMERIC_SCALE,DATETIME_PRECISION,"
					+ "CHARACTER_SET_NAME,COLLATION_NAME,COLUMN_TYPE,COLUMN_KEY,EXTRA,PRIVILEGES,COLUMN_COMMENT,GENERATION_EXPRESSION";
			
			ResultSet rsTable = stmt.executeQuery("select "+ selectedColumns +" from information_schema.columns where table_schema = '"+ databaseName +"' and table_name='"+tableName+"'");
					
			VBox particularViewcolumnsTabVBox = (VBox)particularViewcolumnsTab.getContent();
			//particularViewcolumnsTabVBox.setId("ViewDetailsVBox");
			TableView particularViewColumnsView  = (TableView)particularViewcolumnsTabVBox.getChildren().get(0);
			particularViewColumnsView = showResultSetInTheTableView(rsTable,particularViewColumnsView);
			
			/*VBox particularTablecolumnsTabVBox = new VBox();
			particularTablecolumnsTabVBox.setSpacing(10);
			particularTablecolumnsTabVBox.setMinHeight(menu_Items_FX.size.getHeight()-300);
			particularTablecolumnsTabVBox.setPadding(new Insets(2,2,2,2));
			TableView 								
			HBox particularTableColumnsButtonsHBox = new HBox();
			particularTableColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
			particularTableColumnsButtonsHBox.getChildren().addAll(new Button("Create"),new Button("Delete"));
			particularTablecolumnsTabVBox.getChildren().addAll(particularTableColumnsView,particularTableColumnsButtonsHBox);
			particularTablecolumnsTab.setContent(particularTablecolumnsTabVBox);
			*/
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularViewcolumnsTab;
	}

	private Tab particularViewDoubleClickMethod(String viewName,String databaseName) {
		
		Tab particularViewMainTab = new Tab(viewName);
		particularViewMainTab.getStyleClass().add("Tabs");	
		particularViewMainTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));
		
		VBox particularViewMainTabVBox = new VBox();
		//particularViewMainTabVBox.setSpacing(10);
		particularViewMainTabVBox.setId("connectionLabel");
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularViewMainTabVBox.getChildren().add(addTopHBoxForInfo("View "+viewName+" for Connection "+currentConnectionName));
		
		
		TabPane particularViewTabPane = new TabPane();
		particularViewTabPane.setId("Tabpane");
//		particularViewTabPane.setTabMinWidth(180);
//		particularViewTabPane.setTabMinHeight(30);
		particularTablePropertiesTab = new Tab(menu_Items_FX.resourceBundle.getString("Properties"));
		particularTablePropertiesTab.getStyleClass().add("Tabs");
		particularTableDataTab = new Tab(menu_Items_FX.resourceBundle.getString("Data"));
		particularTableDataTab.getStyleClass().add("Tabs");
	    particularTableERDiagramTab = new Tab(menu_Items_FX.resourceBundle.getString("ERDiagram"));
		particularTableERDiagramTab.getStyleClass().add("Tabs");
		particularTableGraphVisualsTab = new Tab(menu_Items_FX.resourceBundle.getString("GraphVisuals"));
		particularTableGraphVisualsTab.getStyleClass().add("Tabs");
		particularTableAiPromptTab = new Tab(menu_Items_FX.resourceBundle.getString("AIPrompt"));
		particularTableAiPromptTab.getStyleClass().add("Tabs");
		particularViewMainTabVBox.getChildren().addAll(particularViewTabPane)	;
		
		// Properties
		TabPane particularViewPropertiesTabbedPane = new TabPane();
		particularViewPropertiesTabbedPane.getStyleClass().add("Tabs");
		particularViewPropertiesTabbedPane.setId("ViewPropertiesTabbedPane");
		particularViewPropertiesTabbedPane.setSide(Side.LEFT);
		particularViewPropertiesTabbedPane.setRotateGraphic(true);
//		particularViewPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
//		particularViewPropertiesTabbedPane.setTabMaxHeight(200);
//		particularViewPropertiesTabbedPane.setTabMinWidth(50);
//		particularViewPropertiesTabbedPane.setTabMaxWidth(50);
		
		//Details
		Tab particularViewdetailsTab = new Tab();
		particularViewdetailsTab.getStyleClass().add("Tabs");
		particularViewdetailsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Details"));
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularViewdetailsTab.setGraphic(stp);
		
		particularViewdetailsTab = getParticularViewDetailsTab(viewName,databaseName,particularViewdetailsTab);
		
		// Columns
		Tab particularViewcolumnsTab = new Tab();
		particularViewcolumnsTab.getStyleClass().add("Tabs");
		particularViewcolumnsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Columns"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularViewcolumnsTab.setGraphic(stp);
		
		VBox particularViewcolumnsTabVBox = new VBox();
		particularViewcolumnsTabVBox.setId("TabVBox");
		//particularViewcolumnsTabVBox.setSpacing(10);
		particularViewcolumnsTabVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
		//particularViewcolumnsTabVBox.setPadding(new Insets(2,2,2,2));
		TableView particularViewColumnsView = new TableView();								
		HBox particularViewColumnsButtonsHBox = new HBox();
		particularViewColumnsButtonsHBox.setId("DatapartitionsButtonsHBox");
		//particularViewColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
		//particularViewColumnsButtonsHBox.getChildren().addAll(new Button("Create") {{ setId("buttons");}},new Button("Delete") {{ setId("buttons"); }});
	    ViewColumnsCreateButton =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
	    ViewColumnsCreateButton.setId("buttons");
	    ViewColumnsDeleteButton =  new Button(menu_Items_FX.resourceBundle.getString("Delete"));
	    ViewColumnsDeleteButton.setId("buttons");
		particularViewColumnsButtonsHBox.getChildren().addAll(ViewColumnsCreateButton,ViewColumnsDeleteButton);
		particularViewcolumnsTabVBox.getChildren().addAll(particularViewColumnsView,particularViewColumnsButtonsHBox);
		particularViewcolumnsTab.setContent(particularViewcolumnsTabVBox);
		
		// Source/DDL
		Tab particularViewDDLTab = new Tab();
		particularViewDDLTab.getStyleClass().add("Tabs");
		particularViewDDLTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Source/DDL"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularViewDDLTab.setGraphic(stp);
		
		particularViewTabPane.getTabs().addAll(particularTablePropertiesTab,particularTableDataTab,particularTableERDiagramTab,particularTableGraphVisualsTab,particularTableAiPromptTab);
		
		particularViewPropertiesTabbedPane.getTabs().addAll(particularViewdetailsTab,particularViewcolumnsTab,particularViewDDLTab);
		particularTablePropertiesTab.setContent(particularViewPropertiesTabbedPane);

		particularViewPropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Columns"))) {
					particularViewColumnsView.getItems().clear();
					getParticularViewColumnsTab(viewName,databaseName,particularViewcolumnsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Source/DDL"))) {
					getParticularTableSourceDDLTab(viewName,databaseName,particularViewDDLTab);
				}
			}
		});
		//Data
		particularViewTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {

				System.out.println("Tab under Table Selected is "+newValue.getText());

				if(newValue.getText().equals(menu_Items_FX.resourceBundle.getString("Data"))) {
					System.out.println("Data Tab under Table selected");
					try {
						ResultSet rsTableData = stmt.executeQuery("SELECT * FROM " + databaseName + "." + viewName);
						System.out.println("SELECT * FROM " + databaseName + "." + viewName);
												
						VBox particularViewDataTabVBox = new VBox();
						particularViewDataTabVBox.setId("TabVBox");
						//particularViewDataTabVBox.setSpacing(10);
						//particularViewDataTabVBox.setPadding(new Insets(2,2,2,2));
						TableView particularViewDatapartitionsView = showResultSetInTheTableView(rsTableData); 
						HBox particularViewDatapartitionsButtonsHBox = new HBox();
						particularViewDatapartitionsButtonsHBox.setId("DatapartitionsButtonsHBox");
						//particularViewDatapartitionsButtonsHBox.setPadding(new Insets(10,10,10,10));
						//particularViewDatapartitionsButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
						viewDataTabCreateButton =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
						viewDataTabCreateButton.setId("buttons");
						particularViewDatapartitionsButtonsHBox.getChildren().add(viewDataTabCreateButton);
						particularViewDataTabVBox.getChildren().addAll(particularViewDatapartitionsView,particularViewDatapartitionsButtonsHBox);
						particularTableDataTab.setContent(particularViewDataTabVBox);
						//particularTableDataTab.setContent(new TableView());
					}catch(SQLException e) {
						e.printStackTrace();
					}		
				}
				
			}

		});	
		
		particularViewMainTab.setContent(particularViewMainTabVBox);
		
		return particularViewMainTab;
	}

	private Tab getParticularIndexColumnsTab(String indexesName,String databaseName,Tab particularIndexesColumnsTab){

		System.out.println("Index Combination name "+indexesName);
		String tableName = indexesName.split("\\.")[0];
		String indexName = indexesName.split("\\.")[1];

		String columnNames = "index_name,seq_in_index,column_name,table_name,table_schema,Index_type,collation,cardinality,nullable,non_unique,is_visible,comment,expression";
		
		try {
			System.out.println("select "+columnNames+" from information_schema.statistics where table_schema = '"+ databaseName +"' and table_name = '"+tableName +"' and index_name= '"+indexName+"'");
			ResultSet rsTable = stmt.executeQuery("select "+columnNames+" from information_schema.statistics where table_schema = '"+ databaseName +"' and table_name = '"+tableName +"' and index_name= '"+indexName+"'");
			try {
				Thread.sleep(1000);			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			VBox particularIndexcolumnsTabVBox = (VBox)particularIndexesColumnsTab.getContent();
			//particularIndexcolumnsTabVBox.setId("TabVBox");
			TableView particularIndexesColumnsView  = (TableView)particularIndexcolumnsTabVBox.getChildren().get(0);
			particularIndexesColumnsView = showResultSetInTheTableView(rsTable,particularIndexesColumnsView);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return particularIndexesColumnsTab; 
	}
	private Tab getParticularIndexDetailsTab(String indexesName,String databaseName,Tab particularIndexesdetailsTab) {
		
		try {
			
			System.out.println("Index Combination name "+indexesName);
			
			String tableName = indexesName.split("\\.")[0];
			String indexName = indexesName.split("\\.")[1];
			
			System.out.println("select * from information_schema.statistics where table_schema = '"+ databaseName +"' and index_name= '"+indexesName+"'");
			ResultSet rsTable = stmt.executeQuery("select * from information_schema.statistics where table_schema = '"+ databaseName +"' and table_name = '"+tableName +"' and index_name= '"+indexName+"'");
			try {
				Thread.sleep(1000);			
			}catch(Exception e) {
				e.printStackTrace();
			}
			String lableName[] = {"Index Name:","Seq In Index","Column Name:","Table Name:","Schema Name:","Index Type:","Collation:","Cardinality:","Nullable:","Non Unique:","Is Visible:","Comment:","Expression:"};
			String labelNameValue[] = {"index_name","seq_in_index","column_name","table_name","table_schema","Index_type","collation","cardinality","nullable","non_unique","is_visible","comment","expression"};
			
			while(rsTable.next()) {
			
				VBox particularIndexDetailsVBox = new VBox();
				particularIndexDetailsVBox.setId("ViewDetailsVBox");
//				particularIndexDetailsVBox.setSpacing(10);
//				particularIndexDetailsVBox.setPadding(new Insets(2,2,2,2));
//				particularIndexDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane indexDetailGridPane = new GridPane();
				indexDetailGridPane.setVgap(8);
				indexDetailGridPane.setHgap(10);
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setId("labelNameValueLabel");
					//labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
					GridPane.setConstraints(labelNameValueLabel, 1, i);
					
					indexDetailGridPane.getChildren().addAll(labelName,labelNameValueLabel);
				}
				particularIndexDetailsVBox.getChildren().add(indexDetailGridPane);
				particularIndexesdetailsTab.setContent(particularIndexDetailsVBox);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularIndexesdetailsTab;
	}
	

	private Tab particularIndexesDoubleClickMethod(String indexesName,String databaseName)	
	{
	    Tab particularIndexesMainTab = new Tab(indexesName);
	    particularIndexesMainTab.getStyleClass().add("Tabs");
		particularIndexesMainTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));
		VBox particularIndexesMainTabVBox = new VBox();
		//particularIndexesMainTabVBox.setSpacing(10);
		particularIndexesMainTabVBox.setId("connectionLabel");
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularIndexesMainTabVBox.getChildren().add(addTopHBoxForInfo("Indexes "+indexesName+" for Connection "+currentConnectionName));
		
		
		TabPane particularIndexesTabPane = new TabPane();
		particularIndexesTabPane.setId("Tabpane");
//		particularIndexesTabPane.setTabMinWidth(180);
//		particularIndexesTabPane.setTabMinHeight(30);
		particularTablePropertiesTab = new Tab(menu_Items_FX.resourceBundle.getString("Properties"));
		particularTablePropertiesTab.getStyleClass().add("Tabs");
		particularTablePropertiesTab.setClosable(false);
		particularIndexesMainTabVBox.getChildren().addAll(particularIndexesTabPane)	;
		
		// Properties
		TabPane particularIndexesPropertiesTabbedPane = new TabPane();
		particularIndexesPropertiesTabbedPane.getStyleClass().add("Tabs");
		particularIndexesPropertiesTabbedPane.setId("IndexesPropertiesTabbedPane");
		particularIndexesPropertiesTabbedPane.setSide(Side.LEFT);
		particularIndexesPropertiesTabbedPane.setRotateGraphic(true);
//		particularIndexesPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
//		particularIndexesPropertiesTabbedPane.setTabMaxHeight(200);
//		particularIndexesPropertiesTabbedPane.setTabMinWidth(50);
		
		Tab particularIndexesdetailsTab = new Tab();
		particularIndexesdetailsTab.getStyleClass().add("Tabs");
		particularIndexesdetailsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Details"));
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularIndexesdetailsTab.setGraphic(stp);
		
		particularIndexesdetailsTab = getParticularIndexDetailsTab(indexesName,databaseName,particularIndexesdetailsTab);
		
		Tab particularIndexescolumnsTab = new Tab();
		particularIndexescolumnsTab.getStyleClass().add("Tabs");
		particularIndexescolumnsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("IndexColumns"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularIndexescolumnsTab.setGraphic(stp);
		
		VBox particularIndexescolumnsTabVBox = new VBox();
		particularIndexescolumnsTabVBox.setId("TabVBox");
		//particularIndexescolumnsTabVBox.setSpacing(10);
		//particularIndexescolumnsTabVBox.setPadding(new Insets(2,2,2,2));
		particularIndexescolumnsTabVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);		
		TableView particularIndexesColumnsView = new TableView(); 
		HBox particularIndexesColumnsButtonsHBox = new HBox();
		particularIndexesColumnsButtonsHBox.setId("DatapartitionsButtonsHBox");
		//particularIndexesColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
		//particularIndexesColumnsButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
		particularIndexesColumnsButton =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
		particularIndexesColumnsButton.setId("buttons");
		particularIndexesColumnsButtonsHBox.getChildren().add(particularIndexesColumnsButton);
		particularIndexescolumnsTabVBox.getChildren().addAll(particularIndexesColumnsView,particularIndexesColumnsButtonsHBox);
		particularIndexescolumnsTab.setContent(particularIndexescolumnsTabVBox);
	
		particularIndexesTabPane.getTabs().addAll(particularTablePropertiesTab);

		particularIndexesPropertiesTabbedPane.getTabs().addAll(particularIndexesdetailsTab,particularIndexescolumnsTab);
		
		particularIndexesPropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("IndexColumns"))) {
					particularIndexesColumnsView.getItems().clear();
					getParticularIndexColumnsTab(indexesName,databaseName,particularIndexescolumnsTab);
				}
			}
		});
		
		particularTablePropertiesTab.setContent(particularIndexesPropertiesTabbedPane);
		
		particularIndexesMainTab.setContent(particularIndexesMainTabVBox);
		
		return particularIndexesMainTab;
	}

	private Tab getParticularProcedureDetailsTab(String procedureName,String databaseName,Tab particularProceduresdetailsTab) {

		try {	
			System.out.println("Procedure name "+procedureName);
	
			System.out.println("select routine_name,routine_body,sql_data_access,is_deterministic,security_type,definer,character_set_client,collation_connection,database_collation,created,last_altered  from information_schema.routines where  routine_type != 'FUNCTION' and  routine_schema = '"+databaseName+"'");
			ResultSet rsTable = stmt.executeQuery("select routine_name,routine_body,sql_data_access,is_deterministic,security_type,definer,character_set_client,collation_connection,database_collation,created,last_altered  from information_schema.routines where  routine_type != 'FUNCTION' and  routine_schema = '"+databaseName+"'");
			try {
				Thread.sleep(1000);			
			}catch(Exception e) {
				e.printStackTrace();
			}
			String lableName[] = {"Routine Name:","Routine Body:","Sql Data Access:","Is Deterministic:","Security Type:","Definer:","Character Set Client:","Collation Connection:","Database Collation:","Created:","Last Altered:"};
			String labelNameValue[] = {"routine_name","routine_body","sql_data_access","is_deterministic","security_type","definer","character_set_client","collation_connection","database_collation","created","last_altered"};
			
			while(rsTable.next()) {
			
				VBox particularProcedureDetailsVBox = new VBox();
				particularProcedureDetailsVBox.setId("ViewDetailsVBox");
//				particularProcedureDetailsVBox.setSpacing(10);
//				particularProcedureDetailsVBox.setPadding(new Insets(2,2,2,2));
//				particularProcedureDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane procedureDetailGridPane = new GridPane();
				procedureDetailGridPane.setVgap(8);
				procedureDetailGridPane.setHgap(10);
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setId("labelNameValueLabel");
					//labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
					GridPane.setConstraints(labelNameValueLabel, 1, i);
					
					procedureDetailGridPane.getChildren().addAll(labelName,labelNameValueLabel);
				}
				particularProcedureDetailsVBox.getChildren().add(procedureDetailGridPane);
				particularProceduresdetailsTab.setContent(particularProcedureDetailsVBox);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return particularProceduresdetailsTab;
	}
	

	protected Tab getParticularProceduresParametersTab(String proceduresName, String databaseName,Tab particularProcedurescolumnsTab) {
		
			
		   String columnNames = "specific_name,specific_schema ,ordinal_position,parameter_mode,parameter_name,data_type,dtd_identifier,character_maximum_length,numeric_precision,numeric_scale,datetime_precision,character_set_name,collation_name";
			
			try {
				System.out.println("select "+columnNames+" from information_schema.parameters where specific_schema = '"+ databaseName +"' and specific_name = '"+proceduresName +"'and routine_type = 'PROCEDURE'");
				ResultSet rsTable = stmt.executeQuery("select "+columnNames+" from information_schema.parameters where specific_schema = '"+ databaseName +"' and specific_name = '"+proceduresName +"'and routine_type = 'PROCEDURE'");
				try {
					Thread.sleep(1000);			
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				VBox particularProcedurescolumnsTabVBox = (VBox)particularProcedurescolumnsTab.getContent();
				//particularProcedurescolumnsTabVBox.setId("TabVBox");
				TableView particularProcedureColumnsView  = (TableView)particularProcedurescolumnsTabVBox.getChildren().get(0);
				particularProcedureColumnsView = showResultSetInTheTableView(rsTable,particularProcedureColumnsView);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		 
		return particularProcedurescolumnsTab;
		
	}
	
	private Tab getParticularProcedureSourceDDLTab(String proceduresName,String databaseName,Tab particularProcedureDDLTab) {
		try {
			
			ResultSet rsTable = stmt.executeQuery("Show Create Procedure "+databaseName+"."+proceduresName);
			
			VBox particularProcedureDDLTabVBox = new VBox();
			particularProcedureDDLTabVBox.setId("TabVBox");
			//particularProcedureDDLTabVBox.setSpacing(10);
			//particularProcedureDDLTabVBox.setPadding(new Insets(2,2,2,2));
			TextArea particularProcedureDDLTextArea = new TextArea("/***/");
			if(rsTable.next()) {
				particularProcedureDDLTextArea = new TextArea(rsTable.getString(3));
			}
		
			particularProcedureDDLTextArea.setEditable(false);
			particularProcedureDDLTextArea.setWrapText(true);
			particularProcedureDDLTextArea.setMinHeight(menu_Items_FX.size.getHeight()-280);
			particularProcedureDDLTabVBox.getChildren().addAll(particularProcedureDDLTextArea);
			particularProcedureDDLTab.setContent(particularProcedureDDLTabVBox);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularProcedureDDLTab;
	}
	
	
	public Tab particularProcedureDoubleClickMethod(String proceduresName,String databaseName) {
	    Tab particularProceduresMainTab = new Tab(proceduresName);
	    particularProceduresMainTab.getStyleClass().add("Tabs");
		particularProceduresMainTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));
	 
		VBox particularProceduresMainTabVBox = new VBox();
		//particularProceduresMainTabVBox.setSpacing(10);
		particularProceduresMainTabVBox.setId("connectionLabel");
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularProceduresMainTabVBox.getChildren().add(addTopHBoxForInfo("Procedures "+proceduresName+" for Connection "+currentConnectionName));
		
		
		TabPane particularProceduresTabPane = new TabPane();
		particularProceduresTabPane.setId("Tabpane");
//		particularProceduresTabPane.setTabMinWidth(180);
//		particularProceduresTabPane.setTabMinHeight(30);
		particularTablePropertiesTab = new Tab(menu_Items_FX.resourceBundle.getString("Properties"));
		particularTablePropertiesTab.getStyleClass().add("Tabs");
		particularTablePropertiesTab.setClosable(false);
		particularProceduresMainTabVBox.getChildren().addAll(particularProceduresTabPane)	;
		
		// Properties
		TabPane particularProceduresPropertiesTabbedPane = new TabPane();
		particularProceduresPropertiesTabbedPane.getStyleClass().add("Tabs");
		particularProceduresPropertiesTabbedPane.setId("IndexesPropertiesTabbedPane");
		particularProceduresPropertiesTabbedPane.setSide(Side.LEFT);
		particularProceduresPropertiesTabbedPane.setRotateGraphic(true);
//		particularProceduresPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
//		particularProceduresPropertiesTabbedPane.setTabMaxHeight(200);
//		particularProceduresPropertiesTabbedPane.setTabMinWidth(50);
		
		Tab particularProceduresdetailsTab = new Tab();
		particularProceduresdetailsTab.getStyleClass().add("Tabs");
		particularProceduresdetailsTab.setClosable(false);
	    l = new Label(menu_Items_FX.resourceBundle.getString("Details"));
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularProceduresdetailsTab.setGraphic(stp);
		
		particularProceduresdetailsTab = getParticularProcedureDetailsTab(proceduresName,databaseName , particularProceduresdetailsTab);
		
		Tab particularProcedurescolumnsTab = new Tab();
		particularProcedurescolumnsTab.getStyleClass().add("Tabs");
		particularProcedurescolumnsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("ProcedureParameters"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularProcedurescolumnsTab.setGraphic(stp);
		VBox particularProcedurescolumnsTabVBox = new VBox();
		particularProcedurescolumnsTabVBox.setId("TabVBox");
//		particularProcedurescolumnsTabVBox.setSpacing(10);
//		particularProcedurescolumnsTabVBox.setPadding(new Insets(2,2,2,2));
		particularProcedurescolumnsTabVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
		TableView particularProceduresColumnsView = new TableView(); 
		HBox particularProceduresColumnsButtonsHBox = new HBox();
		particularProceduresColumnsButtonsHBox.setId("DatapartitionsButtonsHBox");
		//particularProceduresColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
		//particularProceduresColumnsButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
		particularProceduresColumnsButtons =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
		particularProceduresColumnsButtons.setId("buttons");
		particularProceduresColumnsButtonsHBox.getChildren().add(particularProceduresColumnsButtons);
		particularProcedurescolumnsTabVBox.getChildren().addAll(particularProceduresColumnsView,particularProceduresColumnsButtonsHBox);
		particularProcedurescolumnsTab.setContent(particularProcedurescolumnsTabVBox);
		
		Tab particularProceduresDDLTab = new Tab();
		particularProceduresDDLTab.getStyleClass().add("Tabs");
		particularProceduresDDLTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Source/DDL"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularProceduresDDLTab.setGraphic(stp);
		VBox particularProceduresDDLTabVBox = new VBox();
		particularProceduresDDLTabVBox.setId("TabVBox");
//		particularProceduresDDLTabVBox.setSpacing(10);
//		particularProceduresDDLTabVBox.setPadding(new Insets(2,2,2,2));
	
		particularProceduresTabPane.getTabs().addAll(particularTablePropertiesTab);
		
		particularProceduresPropertiesTabbedPane.getTabs().addAll(particularProceduresdetailsTab,particularProcedurescolumnsTab,particularProceduresDDLTab);
		
		particularProceduresPropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("ProcedureParameters"))) {
					particularProceduresColumnsView.getItems().clear();
					getParticularProceduresParametersTab(proceduresName,databaseName,particularProcedurescolumnsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Source/DDL"))) {
					getParticularProcedureSourceDDLTab(proceduresName,databaseName,particularProceduresDDLTab);
				}
			}
		});
		
		particularTablePropertiesTab.setContent(particularProceduresPropertiesTabbedPane);
		particularProceduresMainTab.setContent(particularProceduresMainTabVBox);
		return particularProceduresMainTab;
	}

	private Tab getParticularFunctionDetailsTab(String functionName,String databaseName,Tab particularFunctionsdetailsTab) {

		try {	
			System.out.println("Function name "+functionName);
	
			System.out.println("select routine_name,routine_body,sql_data_access,is_deterministic,security_type,definer,character_set_client,collation_connection,database_collation,created,last_altered  from information_schema.routines where  routine_type != 'PROCEDURE' and  routine_schema = '"+databaseName+"'");
			ResultSet rsTable = stmt.executeQuery("select routine_name,routine_body,sql_data_access,is_deterministic,security_type,definer,character_set_client,collation_connection,database_collation,created,last_altered  from information_schema.routines where  routine_type != 'PROCEDURE' and  routine_schema = '"+databaseName+"'");
			try {
				Thread.sleep(1000);			
			}catch(Exception e) {
				e.printStackTrace();
			}
			String lableName[] = {"Routine Name:","Routine Body:","Sql Data Access:","Is Deterministic:","Security Type:","Definer:","Character Set Client:","Collation Connection:","Database Collation:","Created:","Last Altered:"};
			String labelNameValue[] = {"routine_name","routine_body","sql_data_access","is_deterministic","security_type","definer","character_set_client","collation_connection","database_collation","created","last_altered"};
			
			while(rsTable.next()) {
			
				VBox particularFunctionDetailsVBox = new VBox();
				//particularFunctionDetailsVBox.setId("TabVBox");
				particularFunctionDetailsVBox.setId("ViewDetailsVBox");
//				particularFunctionDetailsVBox.setSpacing(10);
//				particularFunctionDetailsVBox.setPadding(new Insets(2,2,2,2));
//				particularFunctionDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane functionDetailGridPane = new GridPane();
				functionDetailGridPane.setVgap(8);
				functionDetailGridPane.setHgap(10);
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setId("labelNameValueLabel");
					//labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
					GridPane.setConstraints(labelNameValueLabel, 1, i);
					
					functionDetailGridPane.getChildren().addAll(labelName,labelNameValueLabel);
				}
				particularFunctionDetailsVBox.getChildren().add(functionDetailGridPane);
				particularFunctionsdetailsTab.setContent(particularFunctionDetailsVBox);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return particularFunctionsdetailsTab;
	}
	
	protected Tab getParticularFunctionsParametersTab(String functionsName, String databaseName,Tab particularFunctionscolumnsTab) {
		
		
		   String columnNames = "specific_name,specific_schema ,ordinal_position,parameter_mode,parameter_name,data_type,dtd_identifier,character_maximum_length,numeric_precision,numeric_scale,datetime_precision,character_set_name,collation_name";
			
			try {
				System.out.println("select "+columnNames+" from information_schema.parameters where specific_schema = '"+ databaseName +"' and specific_name = '"+functionsName +"'and routine_type = 'FUNCTION'");
				ResultSet rsTable = stmt.executeQuery("select "+columnNames+" from information_schema.parameters where specific_schema = '"+ databaseName +"' and specific_name = '"+functionsName +"'and routine_type = 'FUNCTION'");
				try {
					Thread.sleep(1000);			
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				VBox particularFunctionscolumnsTabVBox = (VBox)particularFunctionscolumnsTab.getContent();
				//particularFunctionscolumnsTabVBox.setId("TabVBox");
				TableView particularFunctionColumnsView  = (TableView)particularFunctionscolumnsTabVBox.getChildren().get(0);
				particularFunctionColumnsView = showResultSetInTheTableView(rsTable,particularFunctionColumnsView);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		 
		return particularFunctionscolumnsTab;
		
	}
	
	private Tab getParticularFunctionSourceDDLTab(String functionsName,String databaseName,Tab particularFunctionDDLTab) {
		try {
			
			ResultSet rsTable = stmt.executeQuery("Show Create Function "+databaseName+"."+functionsName);
			
			VBox particularFunctionDDLTabVBox = new VBox();
			particularFunctionDDLTabVBox.setId("TabVBox");
//			particularFunctionDDLTabVBox.setSpacing(10);
//			particularFunctionDDLTabVBox.setPadding(new Insets(2,2,2,2));
			TextArea particularFunctionDDLTextArea = new TextArea("/***/");
			if(rsTable.next()) {
				particularFunctionDDLTextArea = new TextArea(rsTable.getString(3));
			}
		
			particularFunctionDDLTextArea.setEditable(false);
			particularFunctionDDLTextArea.setWrapText(true);
			particularFunctionDDLTextArea.setMinHeight(menu_Items_FX.size.getHeight()-280);
			particularFunctionDDLTabVBox.getChildren().addAll(particularFunctionDDLTextArea);
			particularFunctionDDLTab.setContent(particularFunctionDDLTabVBox);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularFunctionDDLTab;
	}
	
	
	public Tab particularFunctionsDoubleClickMethod(String functionsName,String databaseName) {
	    
		Tab particularFunctionsMainTab = new Tab(functionsName);
		particularFunctionsMainTab.getStyleClass().add("Tabs");
		particularFunctionsMainTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));																										   
		
		VBox particularFunctionsMainTabVBox = new VBox();
		//particularFunctionsMainTabVBox.setSpacing(10);
		particularFunctionsMainTabVBox.setId("connectionLabel");
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularFunctionsMainTabVBox.getChildren().add(addTopHBoxForInfo("Functions "+functionsName+" for Connection "+currentConnectionName));
		
		
		TabPane particularFunctionsTabPane = new TabPane();
		particularFunctionsTabPane.setId("Tabpane");
//		particularFunctionsTabPane.setTabMinWidth(180);
//		particularFunctionsTabPane.setTabMinHeight(30);
		particularTablePropertiesTab = new Tab(menu_Items_FX.resourceBundle.getString("Properties"));
		particularTablePropertiesTab.getStyleClass().add("Tabs");
		particularTablePropertiesTab.setClosable(false);
		particularFunctionsMainTabVBox.getChildren().addAll(particularFunctionsTabPane)	;
		
		// Properties
		TabPane particularFunctionsPropertiesTabbedPane = new TabPane();
		particularFunctionsPropertiesTabbedPane.getStyleClass().add("Tabs");
		particularFunctionsPropertiesTabbedPane.setId("IndexesPropertiesTabbedPane");
		particularFunctionsPropertiesTabbedPane.setSide(Side.LEFT);
		particularFunctionsPropertiesTabbedPane.setRotateGraphic(true);
//		particularFunctionsPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
//		particularFunctionsPropertiesTabbedPane.setTabMaxHeight(200);
//		particularFunctionsPropertiesTabbedPane.setTabMinWidth(50);
		
		Tab particularFunctionsdetailsTab = new Tab();
		particularFunctionsdetailsTab.getStyleClass().add("Tabs");
		particularFunctionsdetailsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Details"));
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularFunctionsdetailsTab.setGraphic(stp);
		
		particularFunctionsdetailsTab = getParticularFunctionDetailsTab(functionsName,databaseName , particularFunctionsdetailsTab);
		
		Tab particularFunctionscolumnsTab = new Tab();
		particularFunctionscolumnsTab.getStyleClass().add("Tabs");
		particularFunctionscolumnsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("FunctionParameters"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularFunctionscolumnsTab.setGraphic(stp);
		VBox particularFunctionscolumnsTabVBox = new VBox();
		particularFunctionscolumnsTabVBox.setId("TabVBox");
//		particularFunctionscolumnsTabVBox.setSpacing(10);
//		particularFunctionscolumnsTabVBox.setPadding(new Insets(2,2,2,2));
		particularFunctionscolumnsTabVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
		TableView particularFunctionsColumnsView = new TableView(); 
		HBox particularFunctionsColumnsButtonsHBox = new HBox();
		particularFunctionsColumnsButtonsHBox.setId("DatapartitionsButtonsHBox");
		//particularFunctionsColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
		//particularFunctionsColumnsButtonsHBox.getChildren().add(new Button("Create"){{ setId("buttons"); }});
		particularFunctionsColumnsButtons =  new Button(menu_Items_FX.resourceBundle.getString("Create"));
		particularFunctionsColumnsButtons.setId("buttons");
		particularFunctionsColumnsButtonsHBox.getChildren().add(particularFunctionsColumnsButtons);
		particularFunctionscolumnsTabVBox.getChildren().addAll(particularFunctionsColumnsView,particularFunctionsColumnsButtonsHBox);
		particularFunctionscolumnsTab.setContent(particularFunctionscolumnsTabVBox);
	
		Tab particularFunctionsDDLTab = new Tab();
		particularFunctionsDDLTab.getStyleClass().add("Tabs");
		particularFunctionsDDLTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Source/DDL"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularFunctionsDDLTab.setGraphic(stp);
		VBox particularFunctionsDDLTabVBox = new VBox();
		particularFunctionsDDLTabVBox.setId("TabVBox");
//		particularFunctionsDDLTabVBox.setSpacing(10);
//		particularFunctionsDDLTabVBox.setPadding(new Insets(2,2,2,2));
		
		TextArea particularFunctionsDDLTextArea = new TextArea("Souce DDL will come here \n Souce DDL will come here");
		particularFunctionsDDLTextArea.setEditable(false);		
		particularFunctionsDDLTextArea.setMinHeight(menu_Items_FX.size.getHeight()-280);
		particularFunctionsDDLTabVBox.getChildren().addAll(particularFunctionsDDLTextArea);
		particularFunctionsDDLTab.setContent(particularFunctionsDDLTabVBox);
		
		particularFunctionsTabPane.getTabs().addAll(particularTablePropertiesTab);
		
		particularFunctionsPropertiesTabbedPane.getTabs().addAll(particularFunctionsdetailsTab,particularFunctionscolumnsTab,particularFunctionsDDLTab);
		particularFunctionsPropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>(){
		
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("FunctionParameters"))) {
					particularFunctionsColumnsView.getItems().clear();
					getParticularFunctionsParametersTab(functionsName,databaseName,particularFunctionscolumnsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Source/DDL"))) {
					getParticularFunctionSourceDDLTab(functionsName,databaseName,particularFunctionsDDLTab);
				}
			}
			
		});
		
		particularTablePropertiesTab.setContent(particularFunctionsPropertiesTabbedPane);
		
		particularFunctionsMainTab.setContent(particularFunctionsMainTabVBox);
		
		return particularFunctionsMainTab;
	}
	
	private Tab getParticularTriggerDetailsTab(String triggerName,String databaseName,Tab particularTriggersdetailsTab) {

		try {	
			System.out.println("Trigger name "+triggerName);
	
			System.out.println("select trigger_name,trigger_schema,event_manipulation,event_object_table,action_order,action_condition,action_orientation,action_timing,action_reference_old_table,action_reference_new_table,definer,created,character_set_client,collation_connection,database_collation  from information_schema.triggers where trigger_schema = '"+databaseName+"'");
			ResultSet rsTable = stmt.executeQuery("select trigger_name,trigger_schema,event_manipulation,event_object_table,action_order,action_condition,action_orientation,action_timing,action_reference_old_table,action_reference_new_table,definer,created,character_set_client,collation_connection,database_collation from information_schema.triggers where  trigger_schema = '"+databaseName+"'");
			
			try {
				Thread.sleep(1000);			
			}catch(Exception e) {
				e.printStackTrace();
			}
			String lableName[] = {"Trigger Name:","Trigger Schema:","Event Manipulation:","Event Object Table:","Action Order:","Action Condition:","Action Orientation:","Action Timing:","Action Reference Old Table:","Action Reference New Table:","Definer:","created:","Character Set Client:","Collation Connection:","Database Collation:"};
			String labelNameValue[] = {"trigger_name","trigger_schema","event_manipulation","event_object_table","action_order","action_condition","action_orientation","action_timing","action_reference_old_table","action_reference_new_table","definer","created","character_set_client","collation_connection","database_collation"};
			
			while(rsTable.next()) {
			
				VBox particularTriggerDetailsVBox = new VBox();
				//particularTriggerDetailsVBox.setId("TabVBox");
				particularTriggerDetailsVBox.setId("ViewDetailsVBox");
//				particularTriggerDetailsVBox.setSpacing(10);
//				particularTriggerDetailsVBox.setPadding(new Insets(2,2,2,2));
//				particularTriggerDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane triggerDetailGridPane = new GridPane();
				triggerDetailGridPane.setVgap(8);
				triggerDetailGridPane.setHgap(10);
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setId("labelNameValueLabel");
					//labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
					GridPane.setConstraints(labelNameValueLabel, 1, i);
					
					triggerDetailGridPane.getChildren().addAll(labelName,labelNameValueLabel);
				}
				particularTriggerDetailsVBox.getChildren().add(triggerDetailGridPane);
				particularTriggersdetailsTab.setContent(particularTriggerDetailsVBox);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return particularTriggersdetailsTab;
	}
	
	private Tab getParticularTriggerSourceDDLTab(String triggersName,String databaseName,Tab particularTriggerDDLTab) {
		try {
			
			ResultSet rsTable = stmt.executeQuery("Show Create Trigger "+databaseName+"."+triggersName);
			
			VBox particularTriggerDDLTabVBox = new VBox();
			particularTriggerDDLTabVBox.setId("TabVBox");
//			particularTriggerDDLTabVBox.setSpacing(10);
//			particularTriggerDDLTabVBox.setPadding(new Insets(2,2,2,2));
			TextArea particularTriggerDDLTextArea = new TextArea("/***/");
			if(rsTable.next()) {
				particularTriggerDDLTextArea = new TextArea(rsTable.getString(3));
			}
		
			particularTriggerDDLTextArea.setEditable(false);
			particularTriggerDDLTextArea.setWrapText(true);
			particularTriggerDDLTextArea.setMinHeight(menu_Items_FX.size.getHeight()-280);
			particularTriggerDDLTabVBox.getChildren().addAll(particularTriggerDDLTextArea);
			particularTriggerDDLTab.setContent(particularTriggerDDLTabVBox);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularTriggerDDLTab;
	}
	
	public Tab particularTriggersDoubleClickMethod(String triggersName,String databaseName) {
	    Tab particularTriggersMainTab = new Tab(triggersName);
	    particularTriggersMainTab.getStyleClass().add("Tabs");
		particularTriggersMainTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));																											 
		
		VBox particularTriggersMainTabVBox = new VBox();
		//particularTriggersMainTabVBox.setSpacing(10);
		particularTriggersMainTabVBox.setId("connectionLabel");
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularTriggersMainTabVBox.getChildren().add(addTopHBoxForInfo("Triggers "+triggersName+" for Connection "+currentConnectionName));
		
		
		TabPane particularTriggersTabPane = new TabPane();
		particularTriggersTabPane.setId("Tabpane");
//		particularTriggersTabPane.setTabMinWidth(180);
//		particularTriggersTabPane.setTabMinHeight(30);
		particularTablePropertiesTab = new Tab(menu_Items_FX.resourceBundle.getString("Properties"));
		particularTablePropertiesTab.getStyleClass().add("Tabs");
		particularTablePropertiesTab.setClosable(false);
		particularTriggersMainTabVBox.getChildren().addAll(particularTriggersTabPane);
		
		// Properties
		TabPane particularTriggersPropertiesTabbedPane = new TabPane();
		particularTriggersPropertiesTabbedPane.getStyleClass().add("Tabs");
		particularTriggersPropertiesTabbedPane.setId("IndexesPropertiesTabbedPane");
		particularTriggersPropertiesTabbedPane.setSide(Side.LEFT);
		particularTriggersPropertiesTabbedPane.setRotateGraphic(true);
//		particularTriggersPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
//		particularTriggersPropertiesTabbedPane.setTabMaxHeight(200);
//		particularTriggersPropertiesTabbedPane.setTabMinWidth(50);
		
		Tab particularTriggersdetailsTab = new Tab();
		particularTriggersdetailsTab.getStyleClass().add("Tabs");
		particularTriggersdetailsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Details"));
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularTriggersdetailsTab.setGraphic(stp);
		
		particularTriggersdetailsTab = getParticularTriggerDetailsTab(triggersName,databaseName , particularTriggersdetailsTab);
		
//		VBox particularTableDetailsVBox = new VBox();
//		particularTableDetailsVBox.setSpacing(30);
//		particularTableDetailsVBox.setPadding(new Insets(10,10,10,10));
//		particularTableDetailsVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
//		particularTriggersdetailsTab.setContent(particularTableDetailsVBox);
		
		// use the column action_Statement from the above query and add the DDL/Source for the trigger selected		
		Tab particularTriggersDDLTab = new Tab();
		particularTriggersDDLTab.getStyleClass().add("Tabs");
		particularTriggersDDLTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Source/DDL"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTriggersDDLTab.setGraphic(stp);
		VBox particularTriggersDDLTabVBox = new VBox();
		particularTriggersDDLTabVBox.setId("TabVBox");
//		particularTriggersDDLTabVBox.setSpacing(10);
//		particularTriggersDDLTabVBox.setPadding(new Insets(2,2,2,2));
		TextArea particularTriggersDDLTextArea = new TextArea("Souce DDL will come here \n Souce DDL will come here");
		//particularTriggersDDLTextArea.setMinHeight(menu_Items_FX.size.getHeight()-280);
		particularTriggersDDLTextArea.setEditable(false);
		particularTriggersDDLTabVBox.getChildren().addAll(particularTriggersDDLTextArea);
		particularTriggersDDLTab.setContent(particularTriggersDDLTabVBox);
	
		particularTriggersTabPane.getTabs().addAll(particularTablePropertiesTab);
		
		particularTriggersPropertiesTabbedPane.getTabs().addAll(particularTriggersdetailsTab,particularTriggersDDLTab);
		
		particularTriggersPropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
//				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Procedure Parameters")) {
//					particularTriggersColumnsView.getItems().clear();
//					getParticularProceduresParametersTab(triggersName,databaseName,particularTriggerscolumnsTab);
//				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Source/DDL"))) {
					getParticularTriggerSourceDDLTab(triggersName,databaseName,particularTriggersDDLTab);
				}
			}
		});
		
		particularTablePropertiesTab.setContent(particularTriggersPropertiesTabbedPane);
		particularTriggersMainTab.setContent(particularTriggersMainTabVBox);
		return particularTriggersMainTab;
	}
	
	
	private Tab getParticularEventDetailsTab(String eventName, String databaseName, Tab particularEventsdetailsTab) {
	    try {
	        System.out.println("Event Name " + eventName);
	        String query = "SELECT event_name, event_schema, event_type, event_body, interval_value,interval_field, starts, ends, definer FROM information_schema.events WHERE event_schema = '" + databaseName + "' AND event_name = '" + eventName + "'";
			  System.out.println("SELECT event_name, event_schema, event_type, event_body, interval_value,interval_field, starts, ends, definer FROM information_schema.events WHERE event_schema = '" + databaseName + "' AND event_name = '" + eventName + "'");			   
	        ResultSet rsTable = stmt.executeQuery(query);

	        String labelName[] = {"Event Name:", "Event Schema:", "Event Type:", "Event Body:","Interval Value:", "Interval Field:", "Starts:", "Ends:", "Definer:"};
	      
	        String labelNameValue[] = {"event_name", "event_schema", "event_type", "event_body","interval_value", "interval_field", "starts", "ends", "definer"};
	        
	        while (rsTable.next()) {
	            VBox particularEventDetailsVBox = new VBox();
	            particularEventDetailsVBox.setId("TabVBox");
//	            particularEventDetailsVBox .setSpacing(10);
//	            particularEventDetailsVBox .setPadding(new Insets(20, 10, 10, 200));

	            GridPane eventDetailGridPane = new GridPane();
	            eventDetailGridPane.setVgap(8);
	            eventDetailGridPane.setHgap(10);

	            for (int i = 0; i < labelName.length; i++) {
	                Label label = new Label(labelName[i]);
	                GridPane.setConstraints(label, 0, i);

	                Label labelNameValueLabel = new Label(rsTable.getString(labelNameValue[i]));
	                labelNameValueLabel.setId("labelNameValueLabel");
	                //labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
	                GridPane.setConstraints(labelNameValueLabel, 1, i);

	                eventDetailGridPane.getChildren().addAll(label, labelNameValueLabel);
	            }

	            particularEventDetailsVBox .getChildren().add(eventDetailGridPane);
	            particularEventsdetailsTab.setContent(particularEventDetailsVBox );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return particularEventsdetailsTab;
	}
	
	private Tab getParticularEventSourceDDLTab(String eventsName,String databaseName,Tab particularEventDDLTab) {
		try {
			
			ResultSet rsTable = stmt.executeQuery("Show Create Event "+databaseName+"."+eventsName);
			
			VBox particularEventDDLTabVBox = new VBox();
			particularEventDDLTabVBox.setId("TabVBox");
//			particularEventDDLTabVBox.setSpacing(10);
//			particularEventDDLTabVBox.setPadding(new Insets(2,2,2,2));
			TextArea particularEventDDLTextArea = new TextArea("/***/");
			if(rsTable.next()) {
				particularEventDDLTextArea = new TextArea(rsTable.getString(3));
			}
		
			particularEventDDLTextArea.setEditable(false);
			particularEventDDLTextArea.setWrapText(true);
			particularEventDDLTextArea.setMinHeight(menu_Items_FX.size.getHeight()-280);
			particularEventDDLTabVBox.getChildren().addAll(particularEventDDLTextArea);
			particularEventDDLTab.setContent(particularEventDDLTabVBox);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return particularEventDDLTab;
	}
	
	public Tab particularEventssDoubleClickMethod(String eventsName, String databaseName) {
		Tab particularEventsMainTab = new Tab(eventsName);
		particularEventsMainTab.getStyleClass().add("Tabs");
		particularEventsMainTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));																										
		
		VBox particularEventsMainTabVBox = new VBox();
		//particularEventsMainTabVBox.setSpacing(10);
		particularEventsMainTabVBox.setId("connectionLabel");
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularEventsMainTabVBox.getChildren().add(addTopHBoxForInfo("Events "+eventsName+" for Connection "+currentConnectionName));
																																																																																																																																																																																								
		
		TabPane particularEventsTabPane = new TabPane();
		particularEventsTabPane.setId("Tabpane");
//		particularEventsTabPane.setTabMinWidth(180);
//		particularEventsTabPane.setTabMinHeight(30);
		particularTablePropertiesTab = new Tab(menu_Items_FX.resourceBundle.getString("Properties"));
		particularTablePropertiesTab.getStyleClass().add("Tabs");
		particularTablePropertiesTab.setClosable(false);	
		particularEventsMainTabVBox.getChildren().addAll(particularEventsTabPane)	;
		
		// Properties
		TabPane particularEventsPropertiesTabbedPane = new TabPane();
		particularEventsPropertiesTabbedPane.getStyleClass().add("Tabs");
		particularEventsPropertiesTabbedPane.setId("IndexesPropertiesTabbedPane");
		particularEventsPropertiesTabbedPane.setSide(Side.LEFT);
		particularEventsPropertiesTabbedPane.setRotateGraphic(true);
//		particularEventsPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
//		particularEventsPropertiesTabbedPane.setTabMaxHeight(200);
//		particularEventsPropertiesTabbedPane.setTabMinWidth(50);
		
		// use the information_Schema.events table to get the following details and display them in details
		// select event_name,event_Schema,event_type,event_body,interval_value,interval_field,starts,ends, definer from information_Schema.events where event_Schema=datbaseNam
		Tab particularEventsdetailsTab = new Tab();
		particularEventsdetailsTab.getStyleClass().add("Tabs");
		particularEventsdetailsTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Details"));
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularEventsdetailsTab.setGraphic(stp);
		particularEventsdetailsTab = getParticularEventDetailsTab(eventsName, databaseName,particularEventsdetailsTab);
		
//		VBox particularTableDetailsVBox = new VBox();
//		particularTableDetailsVBox.setSpacing(30);
//		particularTableDetailsVBox.setPadding(new Insets(10,10,10,10));
//		particularTableDetailsVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
//		particularEventsdetailsTab.setContent(particularTableDetailsVBox);
	
	
		// event_defination from the information_Schema.events will have this information.
		Tab particularEventsDDLTab = new Tab();
		particularEventsDDLTab.getStyleClass().add("Tabs");
		particularEventsDDLTab.setClosable(false);
		l = new Label(menu_Items_FX.resourceBundle.getString("Source/DDL"));
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularEventsDDLTab.setGraphic(stp);
		VBox particularEventsDDLTabVBox = new VBox();
		particularEventsDDLTabVBox.setId("TabVBox");
//		particularEventsDDLTabVBox.setSpacing(10);
//		particularEventsDDLTabVBox.setPadding(new Insets(2,2,2,2));
		TextArea particularEventsDDLTextArea = new TextArea("");
		particularEventsDDLTextArea.setMinHeight(menu_Items_FX.size.getHeight()-280);
		particularEventsDDLTextArea.setEditable(false);		
		particularEventsDDLTabVBox.getChildren().addAll(particularEventsDDLTextArea);
		particularEventsDDLTab.setContent(particularEventsDDLTabVBox);
		
		particularEventsTabPane.getTabs().addAll(particularTablePropertiesTab);
		
		particularEventsPropertiesTabbedPane.getTabs().addAll(particularEventsdetailsTab,particularEventsDDLTab);
		
		particularEventsPropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());

				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Source/DDL"))) {
					getParticularEventSourceDDLTab(eventsName,databaseName,particularEventsDDLTab);
				}
			}
		});
		
		particularTablePropertiesTab.setContent(particularEventsPropertiesTabbedPane);
		
		particularEventsMainTab.setContent(particularEventsMainTabVBox);
		
		return particularEventsMainTab;
	}
	
	
	private HBox addBottomHBoxForVariables() {

			 
	        HBox hbox = new HBox();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #334000;");
	// Use style class to set properties previously set above (with some changes)      
	        //hbox.getStyleClass().add("hbox");
	        
	        hbox.getStyleClass().add("hbox");

	        
	        return hbox;
	 }
	  
	 
	  
	  
	  private TabPane addCenterTabbedPaneForVariables() {
			 
		  statusSystemVariablesTabpane = new TabPane(); 
		  statusSystemVariablesTabpane.setId("statusSystemVariablesTabpane");
//		  statusSystemVariablesTabpane.setTabMinWidth(250);
//		  statusSystemVariablesTabpane.setTabMinHeight(20);
		
		  statusVariablesTab = new Tab("Status Variables");
		  statusVariablesTab.getStyleClass().add("Tabs");
		  statusVariablesTab.setClosable(false);
		  statusVariablesTab.setContent(getStatusVariables());
	  
		 
		  systemVariablesTab = new Tab("System Variables");
		  systemVariablesTab.getStyleClass().add("Tabs");
		  systemVariablesTab.setClosable(false);		  
		  systemVariablesTab.setContent(getSystemVariables()); 
		  
		  		  
		  statusSystemVariablesTabpane.getTabs().addAll(statusVariablesTab,systemVariablesTab);
			
		  statusSystemVariablesTabpane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					 // System.out.println("Old Tab Selected -->"+oldValue.getText());
					  System.out.println("New Tab Selected -->"+newValue.getText());
					  
					  if(newValue.getText().equalsIgnoreCase("Global System Variables")) {
						  globalStatusVariablesTab.setContent(getVariablesData("GlobalSystem"));
					  }
					  if(newValue.getText().equalsIgnoreCase("Session System Variables")) {
						  sessionStatusVariablesTab.setContent(getVariablesData("SessionSystem"));
					  }
					  
				}
		  });
		  
		  return statusSystemVariablesTabpane;
			
	  }

	  private HBox addTopHBoxForUserAndPrevileges() {

			 
	        HBox hbox = new HBox();
	        hbox.setId("hboxForconnectionDetails");
//	        hbox.setPadding(new Insets(10, 12, 10, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #2f4f4f;");        
	        Text connectToDatabseText = new Text("Users and Privileges");
	        connectToDatabseText.setId("connectDatabaseLabel");
//	        connectToDatabseText.setFont(Font.font("Verdana",20));
//	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	  }
	  
	  private HBox addTopHBoxForVariables() {

			 
	        HBox hbox = new HBox();
	        hbox.setId("hboxForconnectionDetails");
//	        hbox.setPadding(new Insets(10, 12, 10, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
	        //hbox.setStyle("-fx-background-color: #2f4f4f;");        
	        Text connectToDatabseText = new Text("Server Variables");
	        connectToDatabseText.setId("connectDatabaseLabel");
//	        connectToDatabseText.setFont(Font.font("Verdana",20));
//	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	  }
	  
	  private HBox addTopHBoxForInfo(String infoType) {
	     
		    HBox hbox = new HBox();
		    hbox.setId("hboxForconnectionDetails");
//	        hbox.setPadding(new Insets(10, 12, 10, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
	        //hbox.setStyle("-fx-background-color: #2f4f4f;");        
	        Text connectToDatabseText = new Text(infoType);
	        connectToDatabseText.setId("connectToDatabseText");
	       // connectToDatabseText.setFont(Font.font("Verdana",20));
	       // connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	  }
	  /*
	  private HBox addTopHBoxForBinaryLogs(String logType) {

			 
	        HBox hbox = new HBox();
	        hbox.setPadding(new Insets(10, 12, 10, 12));
	        hbox.setSpacing(10);   // Gap between nodes
	        hbox.setStyle("-fx-background-color: #2f4f4f;");        
	        Text connectToDatabseText = new Text(logType+" Logs");
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	  }*/
	  /*
	  private HBox addTopHBoxForClientConnections() {

			 
	        HBox hbox = new HBox();
	        hbox.setPadding(new Insets(10, 12, 10, 12));
	        hbox.setSpacing(10);   // Gap between nodes
	        hbox.setStyle("-fx-background-color: #2f4f4f;");        
	        Text connectToDatabseText = new Text("Client Connections");
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	  }*/
	  
	  private VBox getStatusVariables() {
		  
		  VBox connectionDetailsVbox = new VBox();
		  
		  statusVariablesTabPane = new TabPane();
		  statusVariablesTabPane.setId("statussystemVariablesTabPane");
//		  statusVariablesTabPane.setTabMinWidth(150);
//		  statusVariablesTabPane.setTabMinHeight(20);
		  
		  globalStatusVariablesTab = new Tab("Global Status Variables");
		  globalStatusVariablesTab.getStyleClass().add("Tabs");
		  globalStatusVariablesTab.setClosable(false);
		  globalStatusVariablesTab.setContent(getVariablesData("GlobalStatus"));  // This needs to be done coz we need to see data initially
		  
		  // *****************//

		  sessionStatusVariablesTab = new Tab("Session Status Variables");
		  sessionStatusVariablesTab.getStyleClass().add("Tabs");
		  sessionStatusVariablesTab.setClosable(false);
		  sessionStatusVariablesTab.setContent(getVariablesData("SessionStatus"));
		  
		  
		  statusVariablesTabPane.getTabs().addAll(globalStatusVariablesTab,sessionStatusVariablesTab);
		  
		  connectionDetailsVbox.getChildren().add(statusVariablesTabPane);
		  
		  statusVariablesTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					 // System.out.println("Old Tab Selected -->"+oldValue.getText());
					  System.out.println("New Tab Selected -->"+newValue.getText());
					  
					  if(newValue.getText().equalsIgnoreCase("Global Status Variables")) {
						  globalStatusVariablesTab.setContent(getVariablesData("GlobalStatus"));
					  }
					  if(newValue.getText().equalsIgnoreCase("Session Status Variables")) {
						  sessionStatusVariablesTab.setContent(getVariablesData("SessionStatus"));
					  }
					  
				}
		  });
		  
		  return connectionDetailsVbox;
	  }
	  
	  private VBox getSystemVariables() {
		  
		  VBox connectionDetailsVbox = new VBox();
		  
		  systemVariablesTabPane = new TabPane();
		  systemVariablesTabPane.setId("statussystemVariablesTabPane");
//		  systemVariablesTabPane.setTabMinWidth(150);
//		  systemVariablesTabPane.setTabMinHeight(20);
		  
		  globalSystemVariablesTab = new Tab("Global System Variables");
		  globalSystemVariablesTab.getStyleClass().add("Tabs");
		  globalSystemVariablesTab.setClosable(false);
		  globalSystemVariablesTab.setContent(getVariablesData("GlobalSystem"));

		  sessionSystemVariablesTab = new Tab("Session System Variables");
		  sessionSystemVariablesTab.getStyleClass().add("Tabs");
		  sessionSystemVariablesTab.setClosable(false);
		  sessionSystemVariablesTab.setContent(getVariablesData("SessionSystem"));
		    
		  systemVariablesTabPane.getTabs().addAll(globalSystemVariablesTab,sessionSystemVariablesTab);
		  
		  connectionDetailsVbox.getChildren().add(systemVariablesTabPane);
		  
		  systemVariablesTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					 // System.out.println("Old Tab Selected -->"+oldValue.getText());
					  System.out.println("New Tab Selected -->"+newValue.getText());
					  
					  if(newValue.getText().equalsIgnoreCase("Global System Variables")) {
						  globalSystemVariablesTab.setContent(getVariablesData("GlobalSystem"));
					  }
					  if(newValue.getText().equalsIgnoreCase("Session System Variables")) {
						  sessionSystemVariablesTab.setContent(getVariablesData("SessionSystem"));
					  }
					  
				}
		  });
		  
		  return connectionDetailsVbox;
		  

	  }	
      
	public Label searchDatabasesLabel;
	  
	private VBox getVariablesData(String variableType) {
		
	    VBox vBoxMain = new VBox();
		
		GridPane searchDatabasesGridPane= new GridPane();
		searchDatabasesGridPane.setId("searchDatabasesGridPane");
		//searchDatabasesGridPane.setPadding(new Insets(5,0,0,10));
		searchDatabasesLabel = new Label(menu_Items_FX.resourceBundle.getString("Find"));
		TextField searchDatabasesTextField = new TextField();
		searchDatabasesTextField.getStyleClass().add("textfield");
		refreshButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
		refreshButton.setId("buttons");
		Label placeholderLabel = new Label("");
		searchDatabasesGridPane.getChildren().addAll(searchDatabasesLabel,searchDatabasesTextField,refreshButton,placeholderLabel);
		GridPane.setConstraints(searchDatabasesLabel, 0, 0); // Column 0 row 0
		GridPane.setHalignment(searchDatabasesLabel,HPos.CENTER);
		searchDatabasesGridPane.getColumnConstraints().add(new ColumnConstraints(50));
		GridPane.setConstraints(searchDatabasesTextField, 1, 0); // Column 1 row 0
		searchDatabasesGridPane.getColumnConstraints().add(new ColumnConstraints(260));
		GridPane.setConstraints(placeholderLabel, 2, 0); // Column 2 row 0
		searchDatabasesGridPane.getColumnConstraints().add(new ColumnConstraints(600));
		GridPane.setConstraints(refreshButton, 3, 0); // Column 3 row 0
		
		
		vBoxMain.getChildren().add(searchDatabasesGridPane);
		
		refreshButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Refresing the variables ...");
				 statusVariablesTab.setContent(getStatusVariables());
				 systemVariablesTab.setContent(getSystemVariables()); 
				 variablesSecondHalfDisplayVBox.getChildren().clear();
			}
		});

		// *****************//
		
		//****************//
		//   2nd level
		
		GridPane gridPaneDatabasesLists = new GridPane();
		gridPaneDatabasesLists.setId("gridPaneDatabasesLists");
//		gridPaneDatabasesLists.setHgap(10);
//		gridPaneDatabasesLists.setPadding(new Insets(5,0,0,15));
		
		TableView resultAsTableView = new TableView();  
		if(variableType.equalsIgnoreCase("GlobalStatus")) {
			
			try {
				ResultSet rsGlobalVariables = stmt.executeQuery(" SHOW GLOBAL STATUS");
				resultAsTableView =  showResultSetInTheTableView(rsGlobalVariables,"Status");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(variableType.equalsIgnoreCase("SessionStatus")) {
			
			try {
				ResultSet rsGlobalVariables = stmt.executeQuery(" SHOW SESSION STATUS");
				resultAsTableView =  showResultSetInTheTableView(rsGlobalVariables,"Status");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(variableType.equalsIgnoreCase("GlobalSystem")) {
			
			try {
				ResultSet rsGlobalVariables = stmt.executeQuery(" SHOW GLOBAL VARIABLES");
				resultAsTableView =  showResultSetInTheTableView(rsGlobalVariables,"Variables");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(variableType.equalsIgnoreCase("SessionSystem")) {
			
			try {
				ResultSet rsGlobalVariables = stmt.executeQuery(" SHOW SESSION VARIABLES");
				resultAsTableView =  showResultSetInTheTableView(rsGlobalVariables,"Variables");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		gridPaneDatabasesLists.getChildren().add(resultAsTableView);
		GridPane.setConstraints(resultAsTableView, 0,0);
		gridPaneDatabasesLists.getColumnConstraints().add(new ColumnConstraints(1000)); // need to check this and fit ass per the screen/tab
		
		
		vBoxMain.getChildren().add(gridPaneDatabasesLists);
		
		//*******************//
		return vBoxMain;
	}		
	
	public Label accountLockedStatus;
	public Button accountlockUnLock;
	public TextField loginNameTextFeild;
	public TextField authenticationTypeTextField;
	public TextField authenticationStringTextField;
	public TextField hostsMatchingTextField;
	public Label passwordExpiredStatusLabel;
	public Label passwordLastchangedDate;
	public Label authenticationStringLabelDescription;
	public Label hostmatchingDescriptionLable;
	public Label passwordLastchangedLabel;
	public Label passwordDescriptionLabel;
	public Label confirmPasswordDescriptionLabel;
	public Label loginNameDescriptionLable;
	public Label authenticationTypeLabelDescription;
	public TextField passwordTextField;
	public TextField confirmPasswordTextField;
	public Button updatePasswordButton;
	public Button expirePasswordButton;
	public Button revertAccountChangesButton;
	public Button saveAccountChangesButton;
	
	public VBox addAccountLoginCredentials() {
		
		  VBox accountDetailsVbox = new VBox();
		  HBox accountLockedHBox = new HBox();
		  accountLockedHBox.setId("accountLockedHBox");
//		  accountLockedHBox.setPadding(new Insets(15,0,0,100));
//		  accountLockedHBox.setSpacing(20);
		  
		  Label accountLockedLabel = new Label("Account Locked :");
		  accountLockedStatus = new Label("Y/N"); // Look up the status	
		  accountlockUnLock = new Button(menu_Items_FX.resourceBundle.getString("Lock/Unlock"));
		  accountlockUnLock.setId("buttons");
		  accountLockedHBox.getChildren().addAll(accountLockedLabel,accountLockedStatus,accountlockUnLock);
		  accountDetailsVbox.getChildren().add(accountLockedHBox);
		  
		  GridPane accountDetailsGridPane = new GridPane();
		  accountDetailsGridPane.setId("accountDetailsGridPane");
//		  accountDetailsGridPane.setPadding(new Insets(0,10,20,10));
//		  accountDetailsGridPane.setVgap(10);
//		  accountDetailsGridPane.setHgap(10);
		  Label loginNameLabel = new Label("Login Name :"); 
		  GridPane.setConstraints(loginNameLabel, 0, 1);   // column 0 row 0
		  loginNameTextFeild = new TextField();
		  loginNameTextFeild.getStyleClass().add("textfield");
		  loginNameTextFeild.setPrefHeight(15);
		  loginNameTextFeild.setPrefWidth(200);
		 // jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		  GridPane.setConstraints(loginNameTextFeild, 1, 1);
		  loginNameDescriptionLable = new Label(menu_Items_FX.resourceBundle.getString("loginNameDescription"));
		  loginNameDescriptionLable.setId("addAccountLoginCredentialsLabels");
		 // loginNameDescriptionLable.setPrefWidth(300);
		  loginNameDescriptionLable.setWrapText(true);
		  GridPane.setConstraints(loginNameDescriptionLable, 2, 1);
		  accountDetailsGridPane.getChildren().addAll(loginNameLabel,loginNameTextFeild,loginNameDescriptionLable);
		  
		  Label authenticationTypeLabel = new Label("Authentication Type :");
		  GridPane.setConstraints(authenticationTypeLabel, 0, 2);
		  authenticationTypeTextField= new TextField();
		  authenticationTypeTextField.getStyleClass().add("textfield");
		  authenticationTypeTextField.setPrefHeight(15);
		  authenticationTypeTextField.setPrefWidth(200);
		  GridPane.setConstraints(authenticationTypeTextField, 1, 2);
		  authenticationTypeLabelDescription = new Label(menu_Items_FX.resourceBundle.getString("authenticationTypeDescription"));
		  authenticationTypeLabelDescription.setId("addAccountLoginCredentialsLabels");
		  //authenticationTypeLabelDescription.setPrefWidth(300);
		  authenticationTypeLabelDescription.setWrapText(true);
		  GridPane.setConstraints(authenticationTypeLabelDescription, 2, 2);
		  accountDetailsGridPane.getChildren().addAll(authenticationTypeLabel,authenticationTypeTextField,authenticationTypeLabelDescription);
		  
		  Label authenticationStringLabel = new Label("Authentication String :");
		  GridPane.setConstraints(authenticationStringLabel, 0, 3);
		  authenticationStringTextField= new TextField();
		  authenticationStringTextField.getStyleClass().add("textfield");
		  authenticationStringTextField.setPrefHeight(15);
		  authenticationStringTextField.setPrefWidth(200);
		  GridPane.setConstraints(authenticationStringTextField, 1, 3);
		  authenticationStringLabelDescription = new Label(menu_Items_FX.resourceBundle.getString("Authenticationpluginspecificparameters"));
		  authenticationStringLabelDescription.setId("addAccountLoginCredentialsLabels");
		  //authenticationStringLabelDescription.setPrefWidth(300);
		  authenticationStringLabelDescription.setWrapText(true);
		  GridPane.setConstraints(authenticationStringLabelDescription, 2, 3);
		  accountDetailsGridPane.getChildren().addAll(authenticationStringLabel,authenticationStringTextField,authenticationStringLabelDescription);
		  
		  
		  Label hostmatchingLabel = new Label("Hosts Matching :");
		  GridPane.setConstraints(hostmatchingLabel, 0, 4);
		  hostsMatchingTextField = new TextField();
		  hostsMatchingTextField.getStyleClass().add("textfield");
		  hostsMatchingTextField.setPrefHeight(15);
		  hostsMatchingTextField.setPrefWidth(100);  
		  GridPane.setConstraints(hostsMatchingTextField, 1, 4);
		  hostmatchingDescriptionLable = new Label(menu_Items_FX.resourceBundle.getString("%and_wildcardsmaybeused,%accessesfromanywhere"));
		  hostmatchingDescriptionLable.setId("addAccountLoginCredentialsLabels");
		 // hostmatchingDescriptionLable.setPrefWidth(300);
		  hostmatchingDescriptionLable.setWrapText(true);
		  GridPane.setConstraints(hostmatchingDescriptionLable, 2, 4);
		  accountDetailsGridPane.getChildren().addAll(hostmatchingLabel,hostsMatchingTextField,hostmatchingDescriptionLable);

		  Label passwordexpiredLabel = new Label("Password Expired:");
		  GridPane.setConstraints(passwordexpiredLabel, 0, 5);
		  passwordExpiredStatusLabel = new Label("Y/N");
		  GridPane.setConstraints(passwordExpiredStatusLabel, 1, 5);
		  
		  passwordLastchangedLabel = new Label(menu_Items_FX.resourceBundle.getString("PasswordLastChanged"));
		  GridPane.setConstraints(passwordLastchangedLabel, 2, 5);
		  passwordLastchangedDate = new Label("10/12/2024");
		  GridPane.setConstraints(passwordLastchangedDate, 3, 5);
		  accountDetailsGridPane.getChildren().addAll(passwordexpiredLabel,passwordExpiredStatusLabel,passwordLastchangedLabel,passwordLastchangedDate);
		  
		  Label passwordLabel = new Label("Password :");
		  GridPane.setConstraints(passwordLabel, 0, 6);
		  passwordTextField = new TextField();
		  passwordTextField.getStyleClass().add("textfield");
		  passwordTextField.setDisable(true);
		  passwordTextField.setPrefHeight(15);
		  passwordTextField.setPrefWidth(100);  
		  GridPane.setConstraints(passwordTextField, 1, 6);
		  passwordDescriptionLabel = new Label(menu_Items_FX.resourceBundle.getString("Enterthepasswordtoresetit.Followthepasswordrequiements"));
		  passwordDescriptionLabel.setId("addAccountLoginCredentialsLabels");
		  //passwordDescriptionLabel.setPrefWidth(300);
		  passwordDescriptionLabel.setWrapText(true);
		  GridPane.setConstraints(passwordDescriptionLabel, 2, 6);
		  accountDetailsGridPane.getChildren().addAll(passwordLabel,passwordTextField,passwordDescriptionLabel);
		  
		  Label confirmPasswordLabel = new Label("Confirm Password :");
		  GridPane.setConstraints(confirmPasswordLabel, 0, 7);
		  confirmPasswordTextField = new TextField();
		  confirmPasswordTextField.getStyleClass().add("textfield");
		  confirmPasswordTextField.setDisable(true);
		  confirmPasswordTextField.setPrefHeight(15);
		  confirmPasswordTextField.setPrefWidth(100);  
		  GridPane.setConstraints(confirmPasswordTextField, 1, 7);
		  confirmPasswordDescriptionLabel = new Label(menu_Items_FX.resourceBundle.getString("Enterthepasswordagaintoconfirm"));
		  confirmPasswordDescriptionLabel.setId("addAccountLoginCredentialsLabels");
		  // confirmPasswordDescriptionLabel.setPrefWidth(300);
		  confirmPasswordDescriptionLabel.setWrapText(true);
		  GridPane.setConstraints(confirmPasswordDescriptionLabel, 2, 7);
		  accountDetailsGridPane.getChildren().addAll(confirmPasswordLabel,confirmPasswordTextField,confirmPasswordDescriptionLabel);
		  
		  accountDetailsVbox.getChildren().add(accountDetailsGridPane);
		  
		  HBox accountButtonsHBox = new HBox();
		  accountButtonsHBox.setId("accountButtonsHBox");
//		  accountButtonsHBox.setPadding(new Insets(15,15,15,100));
//		  accountButtonsHBox.setSpacing(30);
		  updatePasswordButton = new Button(menu_Items_FX.resourceBundle.getString("UpdatePassword"));
		  updatePasswordButton.setId("buttons");
		  expirePasswordButton = new Button(menu_Items_FX.resourceBundle.getString("ExpirePassword"));
		  expirePasswordButton.setId("buttons");
		  revertAccountChangesButton = new Button(menu_Items_FX.resourceBundle.getString("Revert"));
		  revertAccountChangesButton.setId("buttons");
		  saveAccountChangesButton = new Button(menu_Items_FX.resourceBundle.getString("SaveButton"));
		  saveAccountChangesButton.setId("buttons");
		  
		  accountButtonsHBox.getChildren().addAll(updatePasswordButton,expirePasswordButton,revertAccountChangesButton,saveAccountChangesButton);
		  
		  accountDetailsVbox.getChildren().add(accountButtonsHBox);
		  
		return accountDetailsVbox;
		
		
	}
	
	public TextField maxQueriesTextFeild;
	public TextField maxUpdatesTextFeild;
	public TextField maxConnectionsTextFeild;
	public TextField concurrentConnectionsTextFeild;
	public Button revertAccountLimitsButton;
	public Button saveAccountLimitsButton;
    public Label maxQueriesDescription; 
    public Label maxUpdatesDescription; 
    public Label maxConnectionsDescription; 
    public Label concurrentConnectionsDescription; 
	
	public VBox addAccountLimits(){
		
		VBox accountLimitsVBox = new VBox();
		
		GridPane accountDetailsGridPane = new GridPane();
		accountDetailsGridPane.setId("accountDetailsGridPane");	
//		accountDetailsGridPane.setPadding(new Insets(0,10,20,10));
//		accountDetailsGridPane.setVgap(10);
//		accountDetailsGridPane.setHgap(10);
		
		Label maxQueriesLabel = new Label("Max Queries"); 
		GridPane.setConstraints(maxQueriesLabel, 0, 1);   // column 0 row 0
		maxQueriesTextFeild = new TextField();
		maxQueriesTextFeild.getStyleClass().add("textfield");
		maxQueriesTextFeild.setPrefHeight(15);
		maxQueriesTextFeild.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(maxQueriesTextFeild, 1, 1);
		maxQueriesDescription = new Label(menu_Items_FX.resourceBundle.getString("Thenumberofqueriestheaccountcanexecutewithinonehour"));
		maxQueriesDescription.setId("addAccountLoginCredentialsLabels");
		//maxQueriesDescription.setPrefWidth(300);
		maxQueriesDescription.setWrapText(true);
		GridPane.setConstraints(maxQueriesDescription, 2, 1);
		accountDetailsGridPane.getChildren().addAll(maxQueriesLabel,maxQueriesTextFeild,maxQueriesDescription);
		
		Label maxUpdatesLabel = new Label("Max Updates"); 
		GridPane.setConstraints(maxUpdatesLabel, 0, 2);   // column 0 row 0
		maxUpdatesTextFeild = new TextField();
		maxUpdatesTextFeild.getStyleClass().add("textfield");
		maxUpdatesTextFeild.setPrefHeight(15);
		maxUpdatesTextFeild.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(maxUpdatesTextFeild, 1, 2);
		maxUpdatesDescription = new Label(menu_Items_FX.resourceBundle.getString("Thenumberofupdatestheaccountcanexecutewithinonehour"));
		maxUpdatesDescription.setId("addAccountLoginCredentialsLabels");
		//maxUpdatesDescription.setPrefWidth(300);
		maxUpdatesDescription.setWrapText(true);
		GridPane.setConstraints(maxUpdatesDescription, 2, 2);
		accountDetailsGridPane.getChildren().addAll(maxUpdatesLabel,maxUpdatesTextFeild,maxUpdatesDescription);
		
		
		Label maxConnectionsLabel = new Label("Max Connections"); 
		GridPane.setConstraints(maxConnectionsLabel, 0, 3);   // column 0 row 0
		maxConnectionsTextFeild = new TextField();
		maxConnectionsTextFeild.getStyleClass().add("textfield");
		maxConnectionsTextFeild.setPrefHeight(15);
		maxConnectionsTextFeild.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(maxConnectionsTextFeild, 1, 3);
		maxConnectionsDescription = new Label(menu_Items_FX.resourceBundle.getString("Thenumberofupdatestheaccountcanexecutewithinonehour"));
		maxConnectionsDescription.setId("addAccountLoginCredentialsLabels");
		//maxConnectionsDescription.setPrefWidth(300);
		maxConnectionsDescription.setWrapText(true);
		GridPane.setConstraints(maxConnectionsDescription, 2, 3);
		accountDetailsGridPane.getChildren().addAll(maxConnectionsLabel,maxConnectionsTextFeild,maxConnectionsDescription);
		
		Label concurrentConnectionsLabel = new Label("Concurrent Connections"); 
		GridPane.setConstraints(concurrentConnectionsLabel, 0, 4);   // column 0 row 0
		concurrentConnectionsTextFeild = new TextField();
		concurrentConnectionsTextFeild.getStyleClass().add("textfield");
		concurrentConnectionsTextFeild.setPrefHeight(15);
		concurrentConnectionsTextFeild.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(concurrentConnectionsTextFeild, 1, 4);
		concurrentConnectionsDescription = new Label(menu_Items_FX.resourceBundle.getString("Thenumberofupdatestheaccountcanexecutewithinonehour"));
		concurrentConnectionsDescription.setId("addAccountLoginCredentialsLabels");
		//concurrentConnectionsDescription.setPrefWidth(300);
		concurrentConnectionsDescription.setWrapText(true);
		GridPane.setConstraints(maxConnectionsDescription, 2, 4);
		accountDetailsGridPane.getChildren().addAll(concurrentConnectionsLabel,concurrentConnectionsTextFeild,concurrentConnectionsDescription);
		
		accountLimitsVBox.getChildren().add(accountDetailsGridPane);
		
		HBox accountLimitsButtonshbox = new HBox();
		accountLimitsButtonshbox.setId("accountLimitsButtonshbox");
//		accountLimitsButtonshbox.setSpacing(30);
//		accountLimitsButtonshbox.setPadding(new Insets(20,10,10,500));
		revertAccountLimitsButton = new Button(menu_Items_FX.resourceBundle.getString("Revert"));
		revertAccountLimitsButton.setId("buttons");
		saveAccountLimitsButton = new Button(menu_Items_FX.resourceBundle.getString("SaveButton"));
		saveAccountLimitsButton.setId("buttons");
		accountLimitsButtonshbox.getChildren().addAll(revertAccountLimitsButton,saveAccountLimitsButton);
		  
		accountLimitsVBox.getChildren().add(accountLimitsButtonshbox);
		
		
		return accountLimitsVBox;
	}
	
	public Label globalPrivilegesLable;
	public CheckBox alterPrivilegeCheckBox;
	public CheckBox alterRoutinePrivilegeCheckBox;
	public CheckBox createPrivilegeCheckBox;
	public CheckBox createRolePrivilegeCheckBox;
	public CheckBox createRoutinePrivilegeCheckBox;
	public CheckBox createTableSpacePrivilegeCheckBox;
	public CheckBox createTemporaryTablesPrivilegeCheckBox;
	public CheckBox createUserPrivilegeCheckBox;
	public CheckBox createViewPrivilegeCheckBox;
	
	public CheckBox deletePrivilegeCheckBox;  
	public CheckBox dropPrivilegeCheckBox;
	public CheckBox dropRolePrivilegeCheckBox;
	public CheckBox eventPrivilegeCheckBox;
	public CheckBox executePrivilegeCheckBox; 
	public CheckBox filePrivilegeCheckBox;
	public CheckBox grantOptionPrivilegeCheckBox;
	public CheckBox indexPrivilegeCheckBox;
	public CheckBox insertPrivilegeCheckBox;
	public CheckBox lockTablesPrivilegeCheckBox; 
	public CheckBox processPrivilegeCheckBox;
	
	public CheckBox referencesPrivilegeCheckBox; 
	public CheckBox reloadPrivilegeCheckBox; 
	public CheckBox replicationSlavePrivilegeCheckBox; 
	public CheckBox replicationClientPrivilegeCheckBox; 
	public CheckBox selectPrivilegeCheckBox;
	public CheckBox showDatabasesPrivilegeCheckBox; 
	public CheckBox showViewPrivilegeCheckBox; 
	public CheckBox shutdowmPrivilegeCheckBox; 
	public CheckBox superPrivilegeCheckBox; 
	public CheckBox triggerPrivilegeCheckBox; 
	public CheckBox updatePrivilegeCheckBox; 
	
	public CheckBox selectSchemaPrivilegeCheckBox;
	public CheckBox updateSchemaPrivilegeCheckBox;
	public CheckBox insertSchemaPrivilegeCheckBox;
	public CheckBox showViewSchemaPrivilegeCheckBox;
	public CheckBox deleteSchemaPrivilegeCheckBox;
	public CheckBox executeSchemaPrivilegeCheckBox;
	public CheckBox createSchemaPrivilegeCheckBox;
	public CheckBox alterSchemaPrivilegeCheckBox;
	public CheckBox indexSchemaPrivilegeCheckBox;
	public CheckBox referencesSchemaPrivilegeCheckBox;
	public CheckBox createViewSchemaPrivilegeCheckBox;
	public CheckBox createRoutineSchemaPrivilegeCheckBox;
	public CheckBox alterRoutineSchemaPrivilegeCheckBox;
	public CheckBox eventSchemaPrivilegeCheckBox;
	public CheckBox dropSchemaPrivilegeCheckBox;
	public CheckBox triggerSchemaPrivilegeCheckBox;
	public CheckBox grantOptionSchemaPrivilegeCheckBox;
	public CheckBox lockTablesSchemaPrivilegeCheckBox;
	public CheckBox createTemporaryTablesSchemaPrivilegeCheckBox;

	public VBox addAccountPrivileges() {
		
		VBox accountPrivilegesVBox = new VBox();
		accountPrivilegesVBox.setId("accountPrivilegesVBox");
//		accountPrivilegesVBox.setSpacing(10);
//		accountPrivilegesVBox.setPadding(new Insets(10,10,10,10));
		
		globalPrivilegesLable = new Label(menu_Items_FX.resourceBundle.getString("GlobalPrivileges"));
		CheckBox selectAllCheckBox = new CheckBox("Select All");
		accountPrivilegesVBox.getChildren().addAll(globalPrivilegesLable,selectAllCheckBox);
		

		
		HBox globalPreviligeshbox = new HBox();
		globalPreviligeshbox.setId("globalPreviligeshbox");	
		//globalPreviligeshbox.setSpacing(50);
		
		VBox firstSetofPrivileges = new VBox();
		firstSetofPrivileges.setId("accountPrivilegesVBox");
//		firstSetofPrivileges.setSpacing(10);
//		firstSetofPrivileges.setPadding(new Insets(10,10,10,10));
		alterPrivilegeCheckBox = new CheckBox("ALTER");
		alterRoutinePrivilegeCheckBox = new CheckBox("ALTER ROUTINE");
		createPrivilegeCheckBox = new CheckBox("CREATE");
		createRolePrivilegeCheckBox = new CheckBox("CREATE ROLE");
		createRoutinePrivilegeCheckBox = new CheckBox("CREATE ROUTINE");
		createTableSpacePrivilegeCheckBox = new CheckBox("CREATE TABLESPACE");
		createTemporaryTablesPrivilegeCheckBox = new CheckBox("CREATE TEMPORARY TABLES");
		createUserPrivilegeCheckBox = new CheckBox("CREATE USER");
		createViewPrivilegeCheckBox = new CheckBox("CREATE VIEW");
		deletePrivilegeCheckBox =  new CheckBox("DELETE");  
		firstSetofPrivileges.getChildren().addAll(alterPrivilegeCheckBox,alterRoutinePrivilegeCheckBox,createPrivilegeCheckBox,createRolePrivilegeCheckBox,createRoutinePrivilegeCheckBox
				,createTableSpacePrivilegeCheckBox,createTemporaryTablesPrivilegeCheckBox,createUserPrivilegeCheckBox,createViewPrivilegeCheckBox,deletePrivilegeCheckBox);
		
		VBox secondSetofPrivileges = new VBox();
		secondSetofPrivileges.setId("accountPrivilegesVBox");
//		secondSetofPrivileges.setSpacing(10);
//		secondSetofPrivileges.setPadding(new Insets(10,10,10,10));
	
		dropPrivilegeCheckBox =  new CheckBox("DROP");
		dropRolePrivilegeCheckBox =  new CheckBox("DROP ROLE");
		eventPrivilegeCheckBox =  new CheckBox("EVENT");
		executePrivilegeCheckBox =  new CheckBox("EXECUTE"); 
		filePrivilegeCheckBox =  new CheckBox("FILE");
		grantOptionPrivilegeCheckBox =  new CheckBox("GRANT OPTION");
		indexPrivilegeCheckBox =  new CheckBox("INDEX");
		insertPrivilegeCheckBox =  new CheckBox("INSERT");
		lockTablesPrivilegeCheckBox =  new CheckBox("LOCK TABLES"); 
		processPrivilegeCheckBox =  new CheckBox("PROCESS");
		secondSetofPrivileges.getChildren().addAll(dropPrivilegeCheckBox,dropRolePrivilegeCheckBox,eventPrivilegeCheckBox,executePrivilegeCheckBox,filePrivilegeCheckBox
				,grantOptionPrivilegeCheckBox,indexPrivilegeCheckBox,insertPrivilegeCheckBox,lockTablesPrivilegeCheckBox,processPrivilegeCheckBox);
		
		VBox thirdSetofPrivileges = new VBox();
		thirdSetofPrivileges.setId("accountPrivilegesVBox");
//		thirdSetofPrivileges.setSpacing(10);
//		thirdSetofPrivileges.setPadding(new Insets(10,10,10,10));

		referencesPrivilegeCheckBox  =  new CheckBox("REFERENCES");
		reloadPrivilegeCheckBox  =  new CheckBox("RELOAD");
		replicationSlavePrivilegeCheckBox  =  new CheckBox("REPLICATION SLAVE");
		replicationClientPrivilegeCheckBox  =  new CheckBox("REPLICATION CLIENT"); 
		selectPrivilegeCheckBox  =  new CheckBox("SELECT");
		showDatabasesPrivilegeCheckBox  =  new CheckBox("SHOW DATABASES");
		showViewPrivilegeCheckBox  =  new CheckBox("SHOW VIEW");
		shutdowmPrivilegeCheckBox  =  new CheckBox("SHUT DOWN");
		superPrivilegeCheckBox  =  new CheckBox("SUPER"); 
		triggerPrivilegeCheckBox  =  new CheckBox("TRIGGER");
		updatePrivilegeCheckBox  =  new CheckBox("UPDATE");
		
		thirdSetofPrivileges.getChildren().addAll(referencesPrivilegeCheckBox,reloadPrivilegeCheckBox,replicationSlavePrivilegeCheckBox,replicationClientPrivilegeCheckBox,selectPrivilegeCheckBox,showDatabasesPrivilegeCheckBox,
				showViewPrivilegeCheckBox,shutdowmPrivilegeCheckBox,superPrivilegeCheckBox,triggerPrivilegeCheckBox,updatePrivilegeCheckBox);
		
		globalPreviligeshbox.getChildren().addAll(firstSetofPrivileges,secondSetofPrivileges,thirdSetofPrivileges);
		accountPrivilegesVBox.getChildren().add(globalPreviligeshbox);
		
		return accountPrivilegesVBox;
	}
	
	private TableView schemaPreviligestableView;
	private  Label userAccessSchemaDescriptionLabel;
	private String schemaValueHolder;
	
	public Button killQuerysButton;
	public Button killConnectionsButton;
    public Button addSchemaPrivilegesEntryButton;
    public Button deleteSchemaPrivilegesEntryButton;
    public Button reverPriviligestButton;
    public Button savePrivilegesButton;
    public Label allsSchemaSelectedDescriptionLabel;
    public Label schemaMatchingPatternDescriptionLabel;
    public Label selectedSchemaDescription;
	
	private String[] schemaPriviligesColumnNames = {"Schema","Privileges"};
	private ObservableList<HashMap<String,String>> schemaPriviligesMapdata =
            FXCollections.observableArrayList();

	public VBox addSchemaPrivileges(TableView resultAsTableView,TableView rsAvailableSchemaPrivileges) {
		
		
		VBox schemaPrivilegesVbox = new VBox();
		schemaPrivilegesVbox.setId("schemaPrivilegesVbox");
//		schemaPrivilegesVbox.setSpacing(5);
//		schemaPrivilegesVbox.setPadding(new Insets(5,10,00,20));
		
		Label selectSchemaDescription = new Label();
		//selectSchemaDescription.setFont(new Font(12));
		resultAsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
			
			@Override
			public void changed(ObservableValue<? extends HashMap<String, String>> observable,
					HashMap<String, String> oldValue, HashMap<String, String> newValue) {

				System.out.println("oldValue --->"+oldValue);
				System.out.println("newValue --->"+newValue.keySet().toString());
				for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
					
					System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
				}
				selectSchemaDescription.setText("Select the Schema/s for which the user "+newValue.get("User")+" will have the previleges you want to define");
			}
		});
		selectSchemaDescription.setTextFill(Color.BLUEVIOLET);
		schemaPrivilegesVbox.getChildren().add(selectSchemaDescription);
		final ToggleGroup group = new ToggleGroup();
		
		GridPane schemaPrivilegesGridPane = new GridPane();
		schemaPrivilegesGridPane.setId("schemaPrivilegesGridPane");	
//		schemaPrivilegesGridPane.setPadding(new Insets(5,10,5,10));
//		schemaPrivilegesGridPane.setVgap(10);
//		schemaPrivilegesGridPane.setHgap(10);
		
		RadioButton allSchemaRadioButton = new RadioButton("All Schema(%)");
		allSchemaRadioButton.setToggleGroup(group);
		allSchemaRadioButton.setSelected(true); 
		GridPane.setConstraints(allSchemaRadioButton, 0, 0);   // column 0 row 0
		Label allSchemasSelectedLabel = new Label();
		allSchemasSelectedLabel.setPrefHeight(15);
		allSchemasSelectedLabel.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(allSchemasSelectedLabel, 1, 0);
		allsSchemaSelectedDescriptionLabel = new Label(menu_Items_FX.resourceBundle.getString("Thisrulewillapplytoanyschemaname"));
		allsSchemaSelectedDescriptionLabel.setId("addAccountLoginCredentialsLabels");
		//allsSchemaSelectedDescriptionLabel.setPrefWidth(300);
		allsSchemaSelectedDescriptionLabel.setWrapText(true);
		GridPane.setConstraints(allsSchemaSelectedDescriptionLabel, 2, 0);
		schemaPrivilegesGridPane.getChildren().addAll(allSchemaRadioButton,allSchemasSelectedLabel,allsSchemaSelectedDescriptionLabel);
		
		RadioButton schemaMatchingPatternRadioButton = new RadioButton("Schemas matching pattern:");
		schemaMatchingPatternRadioButton.setToggleGroup(group);
		GridPane.setConstraints(schemaMatchingPatternRadioButton, 0, 1);   // column 0 row 0
		TextField schemaMatchingPatternTextField = new TextField();
		schemaMatchingPatternTextField.getStyleClass().add("textfield");
		schemaMatchingPatternTextField.setPrefHeight(15);
		schemaMatchingPatternTextField.setPrefWidth(150);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(schemaMatchingPatternTextField, 1, 1);
		schemaMatchingPatternDescriptionLabel = new Label(menu_Items_FX.resourceBundle.getString("Youmayuse%and_aswildcardsinapattern."));
		schemaMatchingPatternDescriptionLabel.setId("addAccountLoginCredentialsLabels");
		//schemaMatchingPatternDescriptionLabel.setPrefWidth(300);
		schemaMatchingPatternDescriptionLabel.setWrapText(true);
		GridPane.setConstraints(schemaMatchingPatternDescriptionLabel, 2, 1);
		schemaPrivilegesGridPane.getChildren().addAll(schemaMatchingPatternRadioButton,schemaMatchingPatternTextField,schemaMatchingPatternDescriptionLabel);
		
		
		RadioButton selectedSchemasRadioButton = new RadioButton("Selected Schemas");
		selectedSchemasRadioButton.setToggleGroup(group); 
		GridPane.setConstraints(selectedSchemasRadioButton, 0, 2);   // column 0 row 0
		ComboBox<String> selectedSchemascomboBox = new ComboBox();
		selectedSchemascomboBox.setId("themeDropdown");
		selectedSchemascomboBox.getItems().addAll("mysql","sys","sakila","information_schema","world","others"); // @TODO
		selectedSchemascomboBox.setPrefHeight(15);
		selectedSchemascomboBox.setPrefWidth(200);
		//selectedSchemascomboBox.setValue("mysql");
		selectedSchemascomboBox.setVisibleRowCount(5); // after this their will be scroll bar
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(selectedSchemascomboBox, 1, 2);
		selectedSchemaDescription = new Label(menu_Items_FX.resourceBundle.getString("Selectaspecificschemafortheruletoapplyto"));
		selectedSchemaDescription.setId("addAccountLoginCredentialsLabels");
		//selectedSchemaDescription.setPrefWidth(300);
		selectedSchemaDescription.setWrapText(true);
		GridPane.setConstraints(selectedSchemaDescription, 2, 2);
		schemaPrivilegesGridPane.getChildren().addAll(selectedSchemasRadioButton,selectedSchemascomboBox,selectedSchemaDescription);
		schemaPrivilegesVbox.getChildren().add(schemaPrivilegesGridPane);
		
		HBox addSchemaEntryHbox = new HBox();
		addSchemaEntryHbox.setId("addSchemaEntryHbox");
		//addSchemaEntryHbox.setPadding(new Insets(0,0,0,500));	
		addSchemaPrivilegesEntryButton = new Button(menu_Items_FX.resourceBundle.getString("AddSchemaEntry"));
		addSchemaPrivilegesEntryButton.setId("buttons");
		addSchemaEntryHbox.getChildren().add(addSchemaPrivilegesEntryButton);
		schemaPrivilegesVbox.getChildren().add(addSchemaEntryHbox);

		VBox existingSchemaPriviligesVBox = new VBox();
		existingSchemaPriviligesVBox.setId("existingSchemaPriviligesVBox");
//		existingSchemaPriviligesVBox.setMaxHeight(150);
//		existingSchemaPriviligesVBox.setPadding(new Insets(0,10,0,10));
		
		schemaPreviligestableView = new TableView();
		schemaPreviligestableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the last empty column otherwise added
		schemaPreviligestableView.setEditable(true);
        
		schemaPreviligestableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {

			@Override
			public void changed(ObservableValue<? extends HashMap<String, String>> observable,
					HashMap<String, String> oldValue, HashMap<String, String> newValue) {

				System.out.println("oldValue --->"+oldValue);
				System.out.println("newValue --->"+newValue);

				if(newValue != null) {
					String availablePriviliges = newValue.get("Privileges").toString();
					schemaValueHolder =newValue.get("Schema").toString();
		        	System.out.println( "<--------->" +availablePriviliges);
					// setting the addSchemaPriviliges below
					selectSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("SELECT"));
					updateSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("UPDATE"));
					insertSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("INSERT"));
					showViewSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("SHOW VIEW"));
					deleteSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("DELETE"));
					executeSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("EXECUTE"));
					
					createSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("CREATE"));
					alterSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("ALTER"));	
					referencesSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("REFERENCES"));
					indexSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("INDEX"));
					createViewSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("CREATE VIEW"));
					createRoutineSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("CREATE ROUTINE")); 
					
					alterRoutineSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("ALTER ROUTINE"));
					eventSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("EVENT"));
					dropSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("DROP"));
					triggerSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("TRIGGER"));
					grantOptionSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("GRANT OPTION"));
					createTemporaryTablesSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("CREATE TEMPORARY TABLES")); 
					lockTablesSchemaPrivilegeCheckBox.setSelected(availablePriviliges.contains("LOCK TABLES"));
					//userAccessSchemaDescriptionLabel.setText("The use "+ newValue.get("User")+"@"+newValue.get("Host")+" will have the following access rights to the schema 'mysql'");
				}
				
			}	
		});
		TableColumn tableColumnName;
		
		   
	    tableColumnName = new TableColumn<>(schemaPriviligesColumnNames[0]);
	    tableColumnName.setMinWidth(50);
	    tableColumnName.setCellValueFactory(new MapValueFactory<>(schemaPriviligesColumnNames[0]));
	    schemaPreviligestableView.getColumns().add(tableColumnName);

	    tableColumnName = new TableColumn<>(schemaPriviligesColumnNames[1]);
	    tableColumnName.setMinWidth(250);
	    tableColumnName.setCellValueFactory(new MapValueFactory<>(schemaPriviligesColumnNames[1]));
	    schemaPreviligestableView.getColumns().add(tableColumnName);
	    
	
      
        schemaPreviligestableView.setItems(schemaPriviligesMapdata);
        
        existingSchemaPriviligesVBox.getChildren().add(schemaPreviligestableView);
        
        HBox userAccessSchemaDescriptionHbox= new HBox();
        userAccessSchemaDescriptionHbox.setId("userAccessSchemaDescriptionHbox");
//        userAccessSchemaDescriptionHbox.setPadding(new Insets(5,5,5,5));
//        userAccessSchemaDescriptionHbox.setSpacing(50);
        userAccessSchemaDescriptionLabel = new Label();
        resultAsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {

		

			@Override
			public void changed(ObservableValue<? extends HashMap<String, String>> observable,
					HashMap<String, String> oldValue, HashMap<String, String> newValue) {

				System.out.println("oldValue --->"+oldValue);
				System.out.println("newValue --->"+newValue);
				for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
					
					System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
				}
				
				userAccessSchemaDescriptionLabel.setText("The user "+newValue.get("User")+"@"+newValue.get("Host")+" will have the following access rights to the schema "+schemaValueHolder);
			}
			
		});
        //userAccessSchemaDescriptionLabel.setText("The use "+ newValue.get("User")+"@"+newValue.get("Host")+" will have the following access rights to the schema"+newValue.get("Schema"));
        selectSchemaDescription.setTextFill(Color.BLUEVIOLET);
     	deleteSchemaPrivilegesEntryButton = new Button(menu_Items_FX.resourceBundle.getString("DeleteSchemaEntry"));
     	deleteSchemaPrivilegesEntryButton.setId("buttons");
     	deleteSchemaPrivilegesEntryButton.setDisable(true);  // enable it when one of the schema entry is selected
        userAccessSchemaDescriptionHbox.getChildren().addAll(userAccessSchemaDescriptionLabel,deleteSchemaPrivilegesEntryButton);
        
       
        HBox userAccessSchemaPriviligeshBox = new HBox();
        userAccessSchemaPriviligeshBox.setId("userAccessSchemaPriviligeshBox");
      //  userAccessSchemaPriviligeshBox.setSpacing(10);		
        
        VBox firstSetofSchemaPriviliges = new VBox();
        firstSetofSchemaPriviliges.setId("SetofSchemaPriviliges");
        //firstSetofSchemaPriviliges.setSpacing(5);
    	selectSchemaPrivilegeCheckBox  =  new CheckBox("SELECT");
		updateSchemaPrivilegeCheckBox  =  new CheckBox("UPDATE");
		insertSchemaPrivilegeCheckBox =  new CheckBox("INSERT");
		executeSchemaPrivilegeCheckBox =  new CheckBox("EXECUTE"); 
		showViewSchemaPrivilegeCheckBox  =  new CheckBox("SHOW VIEW");
		deleteSchemaPrivilegeCheckBox =  new CheckBox("DELETE");  
		firstSetofSchemaPriviliges.getChildren().addAll(selectSchemaPrivilegeCheckBox,updateSchemaPrivilegeCheckBox,insertSchemaPrivilegeCheckBox,executeSchemaPrivilegeCheckBox,showViewSchemaPrivilegeCheckBox,deleteSchemaPrivilegeCheckBox);
        
		VBox secondSetofSchemaPriviliges = new VBox();
		secondSetofSchemaPriviliges.setId("SetofSchemaPriviliges");
		//secondSetofSchemaPriviliges.setSpacing(5);
		createSchemaPrivilegeCheckBox = new CheckBox("CREATE");
		alterSchemaPrivilegeCheckBox = new CheckBox("ALTER");
		referencesSchemaPrivilegeCheckBox  =  new CheckBox("REFERENCES");
		indexSchemaPrivilegeCheckBox =  new CheckBox("INDEX");
		createViewSchemaPrivilegeCheckBox = new CheckBox("CREATE VIEW");
		createRoutineSchemaPrivilegeCheckBox = new CheckBox("CREATE ROUTINE");
		secondSetofSchemaPriviliges.getChildren().addAll(createSchemaPrivilegeCheckBox,alterSchemaPrivilegeCheckBox,referencesSchemaPrivilegeCheckBox,indexSchemaPrivilegeCheckBox,createViewSchemaPrivilegeCheckBox,createRoutineSchemaPrivilegeCheckBox);
		
		VBox thirdSetofSchemaPriviliges = new VBox();
		thirdSetofSchemaPriviliges.setId("SetofSchemaPriviliges");
		//thirdSetofSchemaPriviliges.setSpacing(5);
		alterRoutineSchemaPrivilegeCheckBox = new CheckBox("ALTER ROUTINE");
		eventSchemaPrivilegeCheckBox =  new CheckBox("EVENT");
		dropSchemaPrivilegeCheckBox =  new CheckBox("DROP");
		triggerSchemaPrivilegeCheckBox  =  new CheckBox("TRIGGER");
		grantOptionSchemaPrivilegeCheckBox =  new CheckBox("GRANT OPTION");
		createTemporaryTablesSchemaPrivilegeCheckBox = new CheckBox("CREATE TEMPORARY TABLES");
		lockTablesSchemaPrivilegeCheckBox =  new CheckBox("LOCK TABLES"); 
		thirdSetofSchemaPriviliges.getChildren().addAll(alterRoutineSchemaPrivilegeCheckBox,eventSchemaPrivilegeCheckBox,dropSchemaPrivilegeCheckBox,triggerSchemaPrivilegeCheckBox,grantOptionSchemaPrivilegeCheckBox,
				createTemporaryTablesSchemaPrivilegeCheckBox,lockTablesSchemaPrivilegeCheckBox);
		
		VBox fourthSegmentWithButtonsVbox = new VBox();
		fourthSegmentWithButtonsVbox.setId("SegmentWithButtonsVbox");
		//fourthSegmentWithButtonsVbox.setPadding(new Insets(130,10,10,20));
		reverPriviligestButton = new Button(menu_Items_FX.resourceBundle.getString("RevertPrivileges"));
		reverPriviligestButton.setId("buttons");
		reverPriviligestButton.setDisable(true); // enable them when one of the schema entry is selected
		fourthSegmentWithButtonsVbox.getChildren().add(reverPriviligestButton);
		
		VBox fifthSegmentWithButtonsVbox = new VBox();
		fifthSegmentWithButtonsVbox.setId("SegmentWithButtonsVbox");
		//fifthSegmentWithButtonsVbox.setPadding(new Insets(130,10,10,20));
		savePrivilegesButton = new Button(menu_Items_FX.resourceBundle.getString("SavePrivileges"));
		savePrivilegesButton.setId("buttons");
		savePrivilegesButton.setDisable(true); // enable them when one of the schema entry is selected
		fifthSegmentWithButtonsVbox.getChildren().add(savePrivilegesButton);
		
		 userAccessSchemaPriviligeshBox.getChildren().addAll(firstSetofSchemaPriviliges,secondSetofSchemaPriviliges,thirdSetofSchemaPriviliges,fourthSegmentWithButtonsVbox,fifthSegmentWithButtonsVbox);
		schemaPrivilegesVbox.getChildren().addAll(existingSchemaPriviligesVBox,userAccessSchemaDescriptionHbox,userAccessSchemaPriviligeshBox);  // main vbox
		
		return schemaPrivilegesVbox;
		
	}
	
	private VBox addclientConnectionThreadDetails(HashMap<String,String> allVariables,TableView tableView) {
			
		VBox clientConnectionsVBox = new VBox();
		clientConnectionsVBox.setId("clientConnectionsVBox");
		//clientConnectionsVBox.setSpacing(10);
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		clientConnectionsVBox.getChildren().add(addTopHBoxForInfo("Client Connections"));
		
		HBox clientConnectionsThreadsDescriptionFirstHBox = new HBox();
		clientConnectionsThreadsDescriptionFirstHBox.setId("clientConnectionsHBox");
//		clientConnectionsThreadsDescriptionFirstHBox.setSpacing(30);
//		clientConnectionsThreadsDescriptionFirstHBox.setPadding(new Insets(0,10,0,10));
		
		Label threadsConnectedLabel = new Label("Threads Connected : "+allVariables.get("Threads_connected"));
		threadsConnectedLabel.setId("labelNameValueLabel");
		//threadsConnectedLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label threadsRunningLabel = new Label("Threads Running : "+allVariables.get("Threads_running"));
		threadsRunningLabel.setId("labelNameValueLabel");
		//threadsRunningLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label threadsCreatedLabel = new Label("Threads Created : "+allVariables.get("Threads_created"));
		threadsCreatedLabel.setId("labelNameValueLabel");
		//threadsCreatedLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label threadsCachedLabel = new Label("Threads Cached : "+allVariables.get("Threads_cached"));
		threadsCachedLabel.setId("labelNameValueLabel");
		//threadsCachedLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label threadsrejectedLabel = new Label("Rejected : "+allVariables.get("Mysqlx_connections_rejected"));
		threadsrejectedLabel.setId("labelNameValueLabel");
		//threadsrejectedLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));

		clientConnectionsThreadsDescriptionFirstHBox.getChildren().addAll(threadsConnectedLabel,threadsRunningLabel,
				threadsCreatedLabel,threadsrejectedLabel);

		HBox clientConnectionsThreadsDescriptionSecondHBox = new HBox();
		clientConnectionsThreadsDescriptionSecondHBox.setId("clientConnectionsVBox");
//		clientConnectionsThreadsDescriptionSecondHBox.setSpacing(30);
//		clientConnectionsThreadsDescriptionSecondHBox.setPadding(new Insets(0,10,0,10));
		
		Integer connection_errors_accept = Integer.parseInt(allVariables.get("Connection_errors_accept"));
		Integer connection_errors_internal = Integer.parseInt(allVariables.get("Connection_errors_internal"));
		Integer connection_errors_max_connections = Integer.parseInt(allVariables.get("Connection_errors_max_connections"));
		Integer connection_errors_select = Integer.parseInt(allVariables.get("Connection_errors_select"));
		Integer connection_errors_tcpwrap = Integer.parseInt(allVariables.get("Connection_errors_tcpwrap"));
		Integer totalErrors = connection_errors_accept+connection_errors_internal+connection_errors_max_connections+connection_errors_select+connection_errors_tcpwrap;

		Label totalConnectionsLabel = new Label("Total Connections : "+allVariables.get("connections"));
		totalConnectionsLabel.setId("labelNameValueLabel");
		//totalConnectionsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label connectionsLimitLabel = new Label("Connections Limit : "+allVariables.get("max_connections"));
		connectionsLimitLabel.setId("labelNameValueLabel");
		//connectionsLimitLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label abortedClientsLabel = new Label("Aborted Clients : "+allVariables.get("Aborted_clients"));
		abortedClientsLabel.setId("labelNameValueLabel");
		//abortedClientsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label abortedConnectionsLabel = new Label("Aborted Connections  : "+allVariables.get("Aborted_connects"));
		abortedConnectionsLabel.setId("labelNameValueLabel");
		//abortedConnectionsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		
		HBox totalErrorsTooltopHBox = new HBox();
		totalErrorsTooltopHBox.setId("clientConnectionsVBox");
		//totalErrorsTooltopHBox.setSpacing(10);
		Label totalErrorsLabel = new Label("Errors : "+totalErrors);
		totalErrorsLabel.setId("labelNameValueLabel");
		//totalErrorsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
        ImageView  informationicon = new ImageView(new Image(getClass().getResourceAsStream("/images/information_icon.png")));
        informationicon.setFitHeight(10);
        informationicon.setFitWidth(10);
        Tooltip toolTip = new Tooltip();
        toolTip.setText("Connection_errors_accept: "+connection_errors_accept+"\nConnection_errors_internal : "+connection_errors_internal
        		+"\nConnection_errors_max_connections :"+connection_errors_max_connections+"\nConnection_errors_select :"+connection_errors_select
        		+"\nConnection_errors_tcpwrap :"+connection_errors_tcpwrap);
        Label errorinformationLabel = new Label();
        errorinformationLabel.setGraphic(informationicon);
        errorinformationLabel.setTooltip(toolTip);
		totalErrorsTooltopHBox.getChildren().addAll(totalErrorsLabel,errorinformationLabel);
        clientConnectionsThreadsDescriptionSecondHBox.getChildren().addAll(totalConnectionsLabel,connectionsLimitLabel,abortedClientsLabel,abortedConnectionsLabel,totalErrorsTooltopHBox);
		
        VBox processListTableViewVbox = new VBox();
        processListTableViewVbox.setId("processListTableViewVbox");
        processListTableViewVbox.getChildren().add(tableView);
        //processListTableViewVbox.setMaxHeight(350);
        //processListTableViewVbox.setPadding(new Insets(5,5,5,5));
        
        
        HBox clientConnectionsButtonsHBox = new HBox();
        clientConnectionsButtonsHBox.setId("clientConnectionsButtonsHBox");
//        clientConnectionsButtonsHBox.setSpacing(500);
//        clientConnectionsButtonsHBox.setPadding(new Insets(10,10,10,10));
        
        HBox clientConnectionRefreshButtonshbox = new HBox();
        clientConnectionRefreshButtonshbox.setId("clientConnectionsVBox");
        //clientConnectionRefreshButtonshbox.setSpacing(10);
        Label clientConnectionRefreshLabel = new Label("Refresh Rate :");
        ComboBox refreshRateComboBox = new ComboBox();
        refreshRateComboBox.setId("themeDropdown");
        refreshRateComboBox.getItems().addAll("Don't Refresh","0.5 Seconds","1 Seconds","2 Seconds","3 Seconds");
        refreshRateComboBox.setValue("Don't Refresh");
        clientConnectionRefreshButtonshbox.getChildren().addAll(clientConnectionRefreshLabel,refreshRateComboBox);
        
        HBox clientConnectionKillButtonshbox = new HBox();
        clientConnectionKillButtonshbox.setId("clientConnectionsVBox");
        //clientConnectionKillButtonshbox.setSpacing(10);
        killQuerysButton = new Button(menu_Items_FX.resourceBundle.getString("KillQuery(s)"));
        killQuerysButton.setId("buttons");
        killConnectionsButton = new Button(menu_Items_FX.resourceBundle.getString("KillConnection(s)"));
        killConnectionsButton.setId("buttons");
        refreshButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
        refreshButton.setId("buttons");
        clientConnectionKillButtonshbox.getChildren().addAll(killQuerysButton,killConnectionsButton,refreshButton);
        
        clientConnectionsButtonsHBox.getChildren().addAll(clientConnectionRefreshButtonshbox,clientConnectionKillButtonshbox);
        
        HBox clientConnectionsHideButtonsHBox = new HBox();
        clientConnectionsHideButtonsHBox.setId("clientConnectionsHideButtonsHBox");
//        clientConnectionsHideButtonsHBox.setSpacing(50);
//        clientConnectionsHideButtonsHBox.setPadding(new Insets(0,10,0,10));
        CheckBox hideSleepingConnectionsCheckBox = new CheckBox("Hide Sleeping Connections");
        hideSleepingConnectionsCheckBox.getStyleClass().add("check-box");
        CheckBox hideBackgroundThreadsCheckBox = new CheckBox("Hide Background Threads");
        hideBackgroundThreadsCheckBox.getStyleClass().add("check-box");
        hideBackgroundThreadsCheckBox.setSelected(true);
        clientConnectionsHideButtonsHBox.getChildren().addAll(hideSleepingConnectionsCheckBox,hideBackgroundThreadsCheckBox);
        
		clientConnectionsVBox.getChildren().addAll(clientConnectionsThreadsDescriptionFirstHBox,clientConnectionsThreadsDescriptionSecondHBox,
				processListTableViewVbox,clientConnectionsButtonsHBox,clientConnectionsHideButtonsHBox);
		
		return clientConnectionsVBox;
	}
	
	 /*private HBox addTopHBoxForServerStatus() {

		 
	        HBox hbox = new HBox();
	        hbox.setPadding(new Insets(10, 12, 10, 12));
	        hbox.setSpacing(10);   // Gap between nodes
	        hbox.setStyle("-fx-background-color: #2f4f4f;");        
	        Text connectToDatabseText = new Text("Server Status");
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	  } */
	 

	private VBox addServerStatus(HashMap<String,String> allVariables,HashMap<String,String> allStatus,HashMap<String,String> allPlugins) {
    	
		VBox serverStatusVBox = new VBox();
		serverStatusVBox.setId("serverStatusVBox");
		//serverStatusVBox.setSpacing(10);
		serverStatusVBox.getChildren().add(addTopHBoxForInfo("Server Status"));
		
		HBox serverRunningDescriptionHbox = new HBox();
		serverRunningDescriptionHbox.setId("serverRunningDescriptionHbox");
		//serverRunningDescriptionHbox.setPadding(new Insets(0,0,0,300));
		Label serverRunningDescriptionLabel = new Label("Server Status : Running");
		serverRunningDescriptionLabel.setId("serverRunningDescriptionLabel");
//		serverRunningDescriptionLabel.setTextFill(Color.GREEN);
//		serverRunningDescriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,18));
		serverRunningDescriptionHbox.getChildren().add(serverRunningDescriptionLabel);
		
		HBox instanceDetailsHBox = new HBox();
		instanceDetailsHBox.setId("instanceDetailsHBox");
//		instanceDetailsHBox.setSpacing(200);
//		instanceDetailsHBox.setPadding(new Insets(0,10,0,10));
		
		GridPane instanceDetailsGridPane = new GridPane();
		//instanceDetailsServerDirectoriesGridPane.setPadding(new Insets(20,10,20,10));
		instanceDetailsGridPane.setId("instanceDetailsGridPane");
//		instanceDetailsGridPane.setVgap(10);
//		instanceDetailsGridPane.setHgap(10);
		Label connectionDecriptionLabel = new Label(connectionPlaceHolder.getConnectionName() + " " + "Instance Details");
		connectionDecriptionLabel.setId("connectionDecriptionLabel");
//		connectionDecriptionLabel.setTextFill(Color.BLUEVIOLET);
//		connectionDecriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,16));
		GridPane.setConstraints(connectionDecriptionLabel, 0, 0);   // column 0 row 0
		Label hostDecriptionLabel = new Label("Host : " );
		GridPane.setConstraints(hostDecriptionLabel, 0, 1);
		Label hostValueLabel = new Label(allVariables.get("hostname"));
		hostValueLabel.setId("labelNameValueLabel");
		//hostValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(hostValueLabel, 1, 1);
		Label socketDecriptionLabel = new Label("Socket :");
		GridPane.setConstraints(socketDecriptionLabel, 0, 2);
		Label socketValueLabel = new Label(allVariables.get("socket"));
		socketValueLabel.setId("labelNameValueLabel");
		//socketValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(socketValueLabel, 1, 2);
		Label portDecriptionLabel = new Label("Port :");
		GridPane.setConstraints(portDecriptionLabel, 0, 3);
		Label portValueLabel = new Label(allVariables.get("port"));
		portValueLabel.setId("labelNameValueLabel");
		//portValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(portValueLabel, 1, 3);
		Label versionDecriptionLabel = new Label("Version :");
		GridPane.setConstraints(versionDecriptionLabel, 0, 4);
		Label versionValueLabel = new Label(allVariables.get("version"));
		versionValueLabel.setId("labelNameValueLabel");
		//versionValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(versionValueLabel, 1, 4);
		Label compiledForDescriptionLabel = new Label("Compiled For :");
		GridPane.setConstraints(compiledForDescriptionLabel, 0, 5);
		Label compiledForValueLabel = new Label(allVariables.get("version_compile_os") +"("+ allVariables.get("version_compile_machine") + ")");
		compiledForValueLabel.setId("labelNameValueLabel");
		//compiledForValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(compiledForValueLabel, 1, 5);
		Label runningSinceDescriptionLabel = new Label("Running Since :");
		GridPane.setConstraints(runningSinceDescriptionLabel, 0, 6);
		Label runningSinceValueLabel = new Label(allStatus.get("Uptime"));
		runningSinceValueLabel.setId("labelNameValueLabel");
		//runningSinceValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(runningSinceValueLabel, 1, 6);
			
		instanceDetailsGridPane.getChildren().addAll(connectionDecriptionLabel,hostDecriptionLabel,hostValueLabel,socketDecriptionLabel,socketValueLabel,
		portDecriptionLabel,portValueLabel,versionDecriptionLabel,versionValueLabel,compiledForDescriptionLabel,compiledForValueLabel,runningSinceDescriptionLabel,runningSinceValueLabel);
		
		GridPane serverDirectoriesGridPane = new GridPane();
		serverDirectoriesGridPane.setId("serverDirectoriesGridPane");
//		serverDirectoriesGridPane.setVgap(10);
//		serverDirectoriesGridPane.setHgap(10);
		Label serverDirectoriesLabel = new Label("Server Direcories");
		serverDirectoriesLabel.setId("connectionDecriptionLabel");
//		serverDirectoriesLabel.setTextFill(Color.BLUEVIOLET);
//		serverDirectoriesLabel.setFont(Font.font("System Regular",FontWeight.BOLD,16));
		GridPane.setConstraints(serverDirectoriesLabel, 0, 0);   // column 0 row 0
		Label baseDirecoryDescriptionLabel = new Label("Base Directory :" );
		GridPane.setConstraints(baseDirecoryDescriptionLabel, 0, 1); 
		Label baseDirecoryValueLabel = new Label(allVariables.get("basedir"));
		baseDirecoryValueLabel.setId("labelNameValueLabel");
		//baseDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(baseDirecoryValueLabel, 1, 1); 
		Label dataDirecoryDescriptionLabel = new Label("Data Directory :" );
		GridPane.setConstraints(dataDirecoryDescriptionLabel, 0, 2); 
		Label dataDirecoryValueLabel = new Label(allVariables.get("datadir"));
		dataDirecoryValueLabel.setId("labelNameValueLabel");
		//dataDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(dataDirecoryValueLabel, 1, 2); 
		Label pluginDirecoryDescriptionLabel = new Label("Plugin Directory :" );
		GridPane.setConstraints(pluginDirecoryDescriptionLabel, 0, 3); 
		Label pluginDirecoryValueLabel = new Label(allVariables.get("plugin_dir"));
		pluginDirecoryValueLabel.setId("labelNameValueLabel");
		//pluginDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(pluginDirecoryValueLabel, 1, 3); 
		Label tempDirecoryDescriptionLabel = new Label("Temp Directory :" );
		GridPane.setConstraints(tempDirecoryDescriptionLabel, 0, 4); 
		Label tempDirecoryValueLabel = new Label(allVariables.get("tmpdir"));
		tempDirecoryValueLabel.setId("labelNameValueLabel");
		//tempDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(tempDirecoryValueLabel, 1, 4); 
		Label errorLogDirecoryDescriptionLabel = new Label("Error Log :" );
		GridPane.setConstraints(errorLogDirecoryDescriptionLabel, 0, 5); 
		Label errorLogDirecoryValueLabel = new Label(allVariables.get("log_error"));
		errorLogDirecoryValueLabel.setId("labelNameValueLabel");
		//errorLogDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(errorLogDirecoryValueLabel, 1, 5); 
		Label generalLogDirecoryDescriptionLabel = new Label("General Log :" );
		GridPane.setConstraints(generalLogDirecoryDescriptionLabel, 0, 6); 
		Label generalLogDirecoryValueLabel = new Label(allVariables.get("general_log_file"));
		generalLogDirecoryValueLabel.setId("labelNameValueLabel");
		//generalLogDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(generalLogDirecoryValueLabel, 1, 6); 
		Label slowQueryLogDirecoryDescriptionLabel = new Label("Slow Query Log :" );
		GridPane.setConstraints(slowQueryLogDirecoryDescriptionLabel, 0, 7); 
		Label slowQueryLogDirecoryValueLabel = new Label(allVariables.get("slow_query_log_file"));
		slowQueryLogDirecoryValueLabel.setId("labelNameValueLabel");
		//slowQueryLogDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(slowQueryLogDirecoryValueLabel, 1, 7);
		
		serverDirectoriesGridPane.getChildren().addAll(serverDirectoriesLabel,baseDirecoryDescriptionLabel,baseDirecoryValueLabel,dataDirecoryDescriptionLabel,dataDirecoryValueLabel,
			pluginDirecoryDescriptionLabel,pluginDirecoryValueLabel,tempDirecoryDescriptionLabel,tempDirecoryValueLabel,
				errorLogDirecoryDescriptionLabel,errorLogDirecoryValueLabel,generalLogDirecoryDescriptionLabel,generalLogDirecoryValueLabel,slowQueryLogDirecoryDescriptionLabel,slowQueryLogDirecoryValueLabel);
		
		instanceDetailsHBox.getChildren().addAll(instanceDetailsGridPane,serverDirectoriesGridPane);
		
		HBox serverFeaturesDescriptionHbox = new HBox();
		serverFeaturesDescriptionHbox.setId("serverFeaturesDescriptionHbox");
		//serverFeaturesDescriptionHbox.setPadding(new Insets(0,0,0,300));
		Label serverFeaturesDescriptionLabel = new Label("Available Server Features");
		serverFeaturesDescriptionLabel.setId("serverFeaturesDescriptionLabel");
//		serverFeaturesDescriptionLabel.setTextFill(Color.BLUEVIOLET);
//		serverFeaturesDescriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,18));
		serverFeaturesDescriptionHbox.getChildren().add(serverFeaturesDescriptionLabel);
		
		HBox serverFeaturesHBox = new HBox();
		serverFeaturesHBox.setId("serverFeaturesHBox");
//		serverFeaturesHBox.setSpacing(200);
//		serverFeaturesHBox.setPadding(new Insets(0,10,00,100));
    	
    	
		GridPane serverFeaturesLeftHalfGridPane = new GridPane();
		serverFeaturesLeftHalfGridPane.setId("serverFeaturesLeftHalfGridPane");
//		serverFeaturesLeftHalfGridPane.setVgap(10);
//		serverFeaturesLeftHalfGridPane.setHgap(10);
		
		Label performanceSchemaDescriptionLabel = new Label("Performance Schema :" );
		GridPane.setConstraints(performanceSchemaDescriptionLabel, 0, 1); 
		Label performanceScheamLabel = new Label(String.valueOf(allPlugins.containsKey("PERFORMANCE_SCHEMA")));
		performanceScheamLabel.setId("labelNameValueLabel");
		//performanceScheamLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(performanceScheamLabel, 1, 1);
		Label threadPoolDescriptionLabel = new Label("Thread Pool :" );
		GridPane.setConstraints(threadPoolDescriptionLabel, 0, 2); 
		Label threadPoolValueLabel = new Label(String.valueOf(allVariables.containsKey("thread_pool_size")));
		threadPoolValueLabel.setId("labelNameValueLabel");
		//threadPoolValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(threadPoolValueLabel, 1, 2); 
		Label memcachedPluginlabel = new Label("Memcached Plugin : " );
		GridPane.setConstraints(memcachedPluginlabel, 0, 3); 
		Label memcachedPluginValuelabel = new Label(String.valueOf(allPlugins.containsKey("daemon_memcached")));
		memcachedPluginValuelabel.setId("labelNameValueLabel");
		//memcachedPluginValuelabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(memcachedPluginValuelabel, 1, 3);
		Label smsynReplicationPluginlabel = new Label("Semisync Replication Plugin : " );
		GridPane.setConstraints(smsynReplicationPluginlabel, 0, 4); 
		Label smsynReplicationPluginValuelabel = new Label(String.valueOf(allPlugins.containsKey("rpl_semi_sync_source")));
		smsynReplicationPluginValuelabel.setId("labelNameValueLabel");
		//smsynReplicationPluginValuelabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(smsynReplicationPluginValuelabel, 1, 4); 
		Label sslAvailabilitylabel = new Label("SSL Availability  : " );
		GridPane.setConstraints(sslAvailabilitylabel, 0, 5); 
		Label sslAvailabilityValuelabel = new Label(String.valueOf(allVariables.containsKey("ssl_ca")));
		sslAvailabilityValuelabel.setId("labelNameValueLabel");
		//sslAvailabilityValuelabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(sslAvailabilityValuelabel, 1, 5);
		Label federatedlabel = new Label("FEDERATED : " );
		GridPane.setConstraints(federatedlabel, 0, 6); 
		Label federatedValuelabel = new Label(String.valueOf(allPlugins.containsKey("FEDERATED")));
		GridPane.setConstraints(federatedValuelabel, 1, 6);
		federatedValuelabel.setId("labelNameValueLabel");
		//federatedValuelabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));

		serverFeaturesLeftHalfGridPane.getChildren().addAll(performanceSchemaDescriptionLabel,performanceScheamLabel,threadPoolDescriptionLabel,threadPoolValueLabel,
				memcachedPluginlabel,memcachedPluginValuelabel,smsynReplicationPluginlabel,smsynReplicationPluginValuelabel,sslAvailabilitylabel,sslAvailabilityValuelabel,
				federatedlabel,federatedValuelabel);
		
		GridPane serverFeaturesRigthtHalfGridPane = new GridPane();
		serverFeaturesRigthtHalfGridPane.setId("serverFeaturesRigthtHalfGridPane");
//		serverFeaturesRigthtHalfGridPane.setVgap(10);
//		serverFeaturesRigthtHalfGridPane.setHgap(10);
		
		Label windowAuthenticationDescriptionLabel = new Label("Window Authentication :" );
		GridPane.setConstraints(windowAuthenticationDescriptionLabel, 0, 1); 
		Label windowAuthenticationValueLabel = new Label(String.valueOf(allPlugins.containsKey("authentication_windows")));
		windowAuthenticationValueLabel.setId("labelNameValueLabel");
		//windowAuthenticationValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(windowAuthenticationValueLabel, 1, 1);
		Label passwordValidationDescriptionLabel = new Label("Password Validation : ");
		GridPane.setConstraints(passwordValidationDescriptionLabel, 0, 2); 
		Label passwordValidationValueLabel = new Label(String.valueOf(allVariables.containsKey("validate_password.policy")));
		passwordValidationValueLabel.setId("labelNameValueLabel");
		//passwordValidationValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(passwordValidationValueLabel, 1, 2); 
		Label auditLogDescriptionLabel = new Label("Audit Log : ");
		GridPane.setConstraints(auditLogDescriptionLabel, 0, 3); 
		Label auditLogValueLabel = new Label(String.valueOf(allVariables.containsKey("audit_log_file")));
		auditLogValueLabel.setId("labelNameValueLabel");
		//auditLogValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(auditLogValueLabel, 1, 3); 
		Label firewallDescriptionLabel = new Label("Firewall : ");
		GridPane.setConstraints(firewallDescriptionLabel, 0, 4); 
		Label firewallValueLabel = new Label(String.valueOf(allVariables.containsKey("mysql_firewall_mode")));
		firewallValueLabel.setId("labelNameValueLabel");
		//firewallValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(firewallValueLabel, 1, 4); 
		Label firewallTraceDescriptionLabel = new Label("Firewall  Trace: ");
		GridPane.setConstraints(firewallTraceDescriptionLabel, 0, 5); 
		Label firewallTraceValueLabel = new Label(String.valueOf(allVariables.containsKey("mysql_firewall_mode")));
		firewallTraceValueLabel.setId("labelNameValueLabel");
		//firewallTraceValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(firewallTraceValueLabel, 1, 5); 
		Label csvDescriptionLabel = new Label("CSV : ");
		GridPane.setConstraints(csvDescriptionLabel, 0, 6); 
		Label csvValueLabel = new Label(String.valueOf(allPlugins.containsKey("CSV")));
		csvValueLabel.setId("labelNameValueLabel");
		//csvValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(csvValueLabel, 1, 6);     
		
		serverFeaturesRigthtHalfGridPane.getChildren().addAll(windowAuthenticationDescriptionLabel,windowAuthenticationValueLabel,passwordValidationDescriptionLabel,passwordValidationValueLabel,
				auditLogDescriptionLabel,auditLogValueLabel,firewallDescriptionLabel,firewallValueLabel,firewallTraceDescriptionLabel,firewallTraceValueLabel,csvDescriptionLabel,csvValueLabel);
		
		
		serverFeaturesHBox.getChildren().addAll(serverFeaturesLeftHalfGridPane,serverFeaturesRigthtHalfGridPane);
		serverStatusVBox.getChildren().addAll(serverRunningDescriptionHbox,instanceDetailsHBox,serverFeaturesDescriptionHbox,serverFeaturesHBox);
		
		return serverStatusVBox;
	}
	
	private TreeView<String> generatePerformnaceReports() {
		
		
		
		TreeItem<String> rootReportsTreeItem =  new TreeItem<String>("Reports");		
		TreeView<String> performanceView = new TreeView<String>();
		performanceView.setCellFactory((TreeView<String> p) -> new MySQLTreecellImpl());
		performanceView.setRoot(rootReportsTreeItem);
		performanceView.setShowRoot(false);
		//treeView.setMinSize(300, size.getHeight()-120);
		//treeConnectionsView.setPrefWidth(300);
		//treeConnectionsView.setMinWidth(100);
		performanceView.setMinHeight(menu_Items_FX.size.getHeight()-200);
		performanceView.setContextMenu(null);
		
		TreeItem<String> memoryUsageTreeItem = new TreeItem<String>("Memory Usage");
		TreeItem<String> memoryUsageTreeItemTotalMemory = new TreeItem<String>("Total Memory");
		TreeItem<String> memoryUsageTreeItemTotalMemoryEvent = new TreeItem<String>("Total Memory By Event");
		TreeItem<String> memoryUsageTreeItemTotalMemoryUser = new TreeItem<String>("Total Memory By User");
		TreeItem<String> memoryUsageTreeItemTotalMemoryHost = new TreeItem<String>("Total Memory By Host");
		TreeItem<String> memoryUsageTreeItemTotalMemoryThread = new TreeItem<String>("Total Memory By Thread");
		
		//memoryUsageTreeItem.getChildren().addAll(memoryUsageTreeItemTotalMemory,memoryUsageTreeItemTotalMemoryEvent,memoryUsageTreeItemTotalMemoryUser,memoryUsageTreeItemTotalMemoryHost,memoryUsageTreeItemTotalMemoryThread);
		threadsafeAddTreeItem(memoryUsageTreeItem,memoryUsageTreeItemTotalMemory);
		threadsafeAddTreeItem(memoryUsageTreeItem,memoryUsageTreeItemTotalMemoryEvent);
		threadsafeAddTreeItem(memoryUsageTreeItem,memoryUsageTreeItemTotalMemoryUser);
		threadsafeAddTreeItem(memoryUsageTreeItem,memoryUsageTreeItemTotalMemoryHost);		
		threadsafeAddTreeItem(memoryUsageTreeItem,memoryUsageTreeItemTotalMemoryThread);
		
		TreeItem<String> hotspotsIOTreeItem = new TreeItem<String>("Hot Spots for I/O");
		TreeItem<String> hotspotsIOTreeItemActivityReport = new TreeItem<String>("Top File I/O Activity Report");
		TreeItem<String> hotspotsIOTreeItemFileTime = new TreeItem<String>("Top I/O By File By Time");
		TreeItem<String> hotspotsIOTreeItemEventCategory = new TreeItem<String>("Top I/O By Event Category");
		TreeItem<String> hotspotsIOTreeItemTimeEventCategories = new TreeItem<String>("Top I/O In Time By Event Categories");
		TreeItem<String> hotspotsIOTreeItemTimeThread = new TreeItem<String>("Top I/O Time By Uer/Thread");
		
		//hotspotsIOTreeItem.getChildren().addAll(hotspotsIOTreeItemActivityReport,hotspotsIOTreeItemFileTime,hotspotsIOTreeItemEventCategory,hotspotsIOTreeItemTimeEventCategories,hotspotsIOTreeItemTimeThread);
		threadsafeAddTreeItem(hotspotsIOTreeItem,hotspotsIOTreeItemActivityReport);
		threadsafeAddTreeItem(hotspotsIOTreeItem,hotspotsIOTreeItemFileTime);
		threadsafeAddTreeItem(hotspotsIOTreeItem,hotspotsIOTreeItemEventCategory);
		threadsafeAddTreeItem(hotspotsIOTreeItem,hotspotsIOTreeItemTimeEventCategories);
		threadsafeAddTreeItem(hotspotsIOTreeItem,hotspotsIOTreeItemTimeThread);
		
		TreeItem<String> highCostSQLTreeItem = new TreeItem<String>("High Cost SQL Statements");
		TreeItem<String> highCostSQLTreeItemStmtAnalysis = new TreeItem<String>("Analysis");
		TreeItem<String> highCostSQLTreeItemErrorsWarnings = new TreeItem<String>("With Errors or Warnings");
		TreeItem<String> highCostSQLTreeItemTableScans = new TreeItem<String>("With Full Table Scans");
		TreeItem<String> highCostSQLTreeItem95thPercentile = new TreeItem<String>("With Runtimes in 95th Percentile");
		TreeItem<String> highCostSQLTreeItemSorting = new TreeItem<String>("With Sorting");
		TreeItem<String> highCostSQLTreeItemTempTables = new TreeItem<String>("With Temp Tables");
		
		//highCostSQLTreeItem.getChildren().addAll(highCostSQLTreeItemStmtAnalysis,highCostSQLTreeItemErrorsWarnings,highCostSQLTreeItemTableScans,highCostSQLTreeItem95thPercentile,highCostSQLTreeItemSorting
		//		,highCostSQLTreeItemTempTables);
		threadsafeAddTreeItem(highCostSQLTreeItem,highCostSQLTreeItemStmtAnalysis);
		threadsafeAddTreeItem(highCostSQLTreeItem,highCostSQLTreeItemErrorsWarnings);
		threadsafeAddTreeItem(highCostSQLTreeItem,highCostSQLTreeItemTableScans);
		threadsafeAddTreeItem(highCostSQLTreeItem,highCostSQLTreeItem95thPercentile);
		threadsafeAddTreeItem(highCostSQLTreeItem,highCostSQLTreeItemSorting);
		threadsafeAddTreeItem(highCostSQLTreeItem,highCostSQLTreeItemTempTables);
		
		TreeItem<String> schemaStatisticsTreeItem = new TreeItem<String>("Database Schema Statistics");
		TreeItem<String> schemaStatisticsTreeItemAutoIncrement = new TreeItem<String>("Auto Increment Columns");
		TreeItem<String> schemaStatisticsTreeItemFlattenedKeys = new TreeItem<String>("Flattened Keys");
		TreeItem<String> schemaStatisticsTreeItemIndex = new TreeItem<String>("Index Statistics");
		TreeItem<String> schemaStatisticsTreeItemObject = new TreeItem<String>("Object Overview");
		TreeItem<String> schemaStatisticsTreeItemRedundantIndexes = new TreeItem<String>("Redundant Indexes");
		TreeItem<String> schemaStatisticsTreeItemTableLoackWaits = new TreeItem<String>("Table Lock Waits");
		TreeItem<String> schemaStatisticsTreeItemTableSatictics = new TreeItem<String>("Table Statistics");
		TreeItem<String> schemaStatisticsTreeItemTableStatwithBuffer = new TreeItem<String>("Table Statics with Buffer");
		TreeItem<String> schemaStatisticsTreeItemTableFullScans = new TreeItem<String>("Tables With Full Table Scans");
		TreeItem<String> schemaStatisticsTreeItemUnusedIndexes = new TreeItem<String>("Unused Indexes");
		
		//schemaStatisticsTreeItem.getChildren().addAll(schemaStatisticsTreeItemAutoIncrement,schemaStatisticsTreeItemFlattenedKeys,schemaStatisticsTreeItemIndex,schemaStatisticsTreeItemObject
		//		,schemaStatisticsTreeItemRedundantIndexes,schemaStatisticsTreeItemTableLoackWaits,schemaStatisticsTreeItemTableSatictics,schemaStatisticsTreeItemTableStatwithBuffer,schemaStatisticsTreeItemTableFullScans,schemaStatisticsTreeItemUnusedIndexes);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemAutoIncrement);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemFlattenedKeys);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemIndex);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemObject);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemRedundantIndexes);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemTableLoackWaits);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemTableSatictics);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemTableStatwithBuffer);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemTableFullScans);
		threadsafeAddTreeItem(schemaStatisticsTreeItem,schemaStatisticsTreeItemUnusedIndexes);

		
		TreeItem<String> waitTimesTreeItem = new TreeItem<String>("Wait Times");
		TreeItem<String> waitTimesTreeItemGlobalWait = new TreeItem<String>("Global Waits By Time ");
		TreeItem<String> waitTimesTreeItemUserTime = new TreeItem<String>("Wait By User By Time");
		TreeItem<String> waitTimesTreeItemHostTime = new TreeItem<String>("Wait By Host By Time");
		TreeItem<String> waitTimesTreeItemClassesTime = new TreeItem<String>("Wait Classes By Time");
		TreeItem<String> waitTimesTreeItemClassesAvgTime = new TreeItem<String>("Wait Classes By Avg Time");
		
		//waitTimesTreeItem.getChildren().addAll(waitTimesTreeItemGlobalWait,waitTimesTreeItemUserTime,waitTimesTreeItemHostTime,waitTimesTreeItemClassesTime,waitTimesTreeItemClassesAvgTime);
		threadsafeAddTreeItem(waitTimesTreeItem,waitTimesTreeItemGlobalWait);
		threadsafeAddTreeItem(waitTimesTreeItem,waitTimesTreeItemUserTime);
		threadsafeAddTreeItem(waitTimesTreeItem,waitTimesTreeItemHostTime);
		threadsafeAddTreeItem(waitTimesTreeItem,waitTimesTreeItemClassesTime);
		threadsafeAddTreeItem(waitTimesTreeItem,waitTimesTreeItemClassesAvgTime);
		
		TreeItem<String> innoDBStatisticsTreeItem = new TreeItem<String>("InnoDB Statistics");
		TreeItem<String> innoDBStatisticsTreeItemSchemaStats = new TreeItem<String>("Buffer Stats By Schema");
		TreeItem<String> innoDBStatisticsTreeItemTableStats = new TreeItem<String>("Buffer Stats By Table");
		TreeItem<String> innoDBStatisticsTreeItemLockWaits = new TreeItem<String>("Lock Waits");

		//innoDBStatisticsTreeItem.getChildren().addAll(innoDBStatisticsTreeItemSchemaStats,innoDBStatisticsTreeItemTableStats,innoDBStatisticsTreeItemLockWaits);
		threadsafeAddTreeItem(innoDBStatisticsTreeItem,innoDBStatisticsTreeItemSchemaStats);
		threadsafeAddTreeItem(innoDBStatisticsTreeItem,innoDBStatisticsTreeItemTableStats);
		threadsafeAddTreeItem(innoDBStatisticsTreeItem,innoDBStatisticsTreeItemLockWaits);
		
		TreeItem<String> userUtilizationTreeItem = new TreeItem<String>("User Resource Utilization");
		TreeItem<String> userUtilizationTreeItemSummary= new TreeItem<String>("User Summary");
		TreeItem<String> userUtilizationTreeItemIOStats= new TreeItem<String>("User File I/O Summary");
		TreeItem<String> userUtilizationTreeItemSummaryIOTypeStats= new TreeItem<String>("User File I/O Type Summary");
		TreeItem<String> userUtilizationTreeItemSummaryStageStats= new TreeItem<String>("User Stages Summary");
		TreeItem<String> userUtilizationTreeItemSummaryStmtTime= new TreeItem<String>("User Statement Time Summary");
		TreeItem<String> userUtilizationTreeItemSummaryStmtType= new TreeItem<String>("User Statement Type Summary");
		
		//userUtilizationTreeItem.getChildren().addAll(userUtilizationTreeItemSummary,userUtilizationTreeItemIOStats,userUtilizationTreeItemIOStats,userUtilizationTreeItemSummaryIOTypeStats,userUtilizationTreeItemSummaryStageStats
		//		,userUtilizationTreeItemSummaryStmtTime,userUtilizationTreeItemSummaryStmtType);
		threadsafeAddTreeItem(userUtilizationTreeItem,userUtilizationTreeItemSummary);
		threadsafeAddTreeItem(userUtilizationTreeItem,userUtilizationTreeItemIOStats);
		threadsafeAddTreeItem(userUtilizationTreeItem,userUtilizationTreeItemSummaryIOTypeStats);
		threadsafeAddTreeItem(userUtilizationTreeItem,userUtilizationTreeItemSummaryStageStats);
		threadsafeAddTreeItem(userUtilizationTreeItem,userUtilizationTreeItemSummaryStmtTime);
		threadsafeAddTreeItem(userUtilizationTreeItem,userUtilizationTreeItemSummaryStmtType);

		TreeItem<String> hostUtilizationTreeItem = new TreeItem<String>("Host Resource Utilization");
		TreeItem<String> hostUtilizationTreeItemSummary= new TreeItem<String>("Host Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryIOStats= new TreeItem<String>("Host File I/O Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryIOTypeStats= new TreeItem<String>("Host File I/O Type Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryStagesStmt= new TreeItem<String>("Host Stages Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryStmtTime= new TreeItem<String>("Host Statement Time Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryStmtType= new TreeItem<String>("Host Statement Type Summary");
		
		//hostUtilizationTreeItem.getChildren().addAll(hostUtilizationTreeItemSummary,hostUtilizationTreeItemSummaryIOStats,hostUtilizationTreeItemSummaryIOTypeStats,hostUtilizationTreeItemSummaryStagesStmt
        //				,hostUtilizationTreeItemSummaryStmtTime,hostUtilizationTreeItemSummaryStmtType);
		threadsafeAddTreeItem(hostUtilizationTreeItem,hostUtilizationTreeItemSummary);
		threadsafeAddTreeItem(hostUtilizationTreeItem,hostUtilizationTreeItemSummaryIOStats);
		threadsafeAddTreeItem(hostUtilizationTreeItem,hostUtilizationTreeItemSummaryIOTypeStats);
		threadsafeAddTreeItem(hostUtilizationTreeItem,hostUtilizationTreeItemSummaryStagesStmt);
		threadsafeAddTreeItem(hostUtilizationTreeItem,hostUtilizationTreeItemSummaryStmtTime);
		threadsafeAddTreeItem(hostUtilizationTreeItem,hostUtilizationTreeItemSummaryStmtType);
		
		TreeItem<String> otherInformationTreeItem = new TreeItem<String>("Other Information");
		TreeItem<String> versionTreeItem = new TreeItem<String>("Version");
		TreeItem<String> sessionTreeItem = new TreeItem<String>("Session Info");
		TreeItem<String> latestFileioTreeItem = new TreeItem<String>("Latest File I/O");
		TreeItem<String> systemConfigTreeItem = new TreeItem<String>("System Config");
		TreeItem<String> sessionSSLStatusConfigTreeItem = new TreeItem<String>("Session SSL Status");
		TreeItem<String> metricsTreeItem = new TreeItem<String>("Metrics");
		TreeItem<String> processListTreeItem = new TreeItem<String>("Process List");
		TreeItem<String> checkLostInstrumentationTreeItem = new TreeItem<String>("Check Lost Instrumentation"); 
		
		//otherInformationTreeItem.getChildren().addAll(versionTreeItem,sessionTreeItem,latestFileioTreeItem,systemConfigTreeItem,sessionSSLStatusConfigTreeItem,metricsTreeItem
		//		,processListTreeItem,checkLostInstrumentationTreeItem);
		threadsafeAddTreeItem(otherInformationTreeItem,versionTreeItem);
		threadsafeAddTreeItem(otherInformationTreeItem,sessionTreeItem);
		threadsafeAddTreeItem(otherInformationTreeItem,latestFileioTreeItem);
		threadsafeAddTreeItem(otherInformationTreeItem,systemConfigTreeItem);
		threadsafeAddTreeItem(otherInformationTreeItem,sessionSSLStatusConfigTreeItem);
		threadsafeAddTreeItem(otherInformationTreeItem,metricsTreeItem);
		threadsafeAddTreeItem(otherInformationTreeItem,processListTreeItem);
		threadsafeAddTreeItem(otherInformationTreeItem,checkLostInstrumentationTreeItem);
		
		//rootReportsTreeItem.getChildren().addAll(memoryUsageTreeItem,hotspotsIOTreeItem,highCostSQLTreeItem,schemaStatisticsTreeItem,waitTimesTreeItem,innoDBStatisticsTreeItem
		//		,userUtilizationTreeItem,hostUtilizationTreeItem,otherInformationTreeItem);
		threadsafeAddTreeItem(rootReportsTreeItem,memoryUsageTreeItem);
		threadsafeAddTreeItem(rootReportsTreeItem,hotspotsIOTreeItem);
		threadsafeAddTreeItem(rootReportsTreeItem,highCostSQLTreeItem);
		threadsafeAddTreeItem(rootReportsTreeItem,schemaStatisticsTreeItem);
		threadsafeAddTreeItem(rootReportsTreeItem,waitTimesTreeItem);
		threadsafeAddTreeItem(rootReportsTreeItem,innoDBStatisticsTreeItem);
		threadsafeAddTreeItem(rootReportsTreeItem,userUtilizationTreeItem);
		threadsafeAddTreeItem(rootReportsTreeItem,hostUtilizationTreeItem);
		threadsafeAddTreeItem(rootReportsTreeItem,otherInformationTreeItem);
		
		return performanceView;
	}

	private void displayPerformanceTableView(String[] performanceReportsTypes, String[] performanceReportQueries,String currentTreeItemSelected) {
		for( int i=0;i<performanceReportsTypes.length;i++) {
			 if(currentTreeItemSelected.equalsIgnoreCase(performanceReportsTypes[i])) {
				 int index = i;
			      System.out.println("clicked on this item"+ currentTreeItemSelected);
					Platform.runLater(new Runnable() {
						  @Override
						  public void run() { 
						    try  {
						    	
						    	ResultSet rs = stmt.executeQuery("Select * from sys."+performanceReportQueries[index]);
						    	
						    	String connectionName = connectionPlaceHolder.getConnectionName();
						    	
						    	System.out.println("Connection Name :"+ connectionName);
						    	particularPerformanceReportLabel.setText(performanceReportsTypes[index]);
						    	performanceReportTableView.getColumns().clear();
						    	performanceReportTableView.getItems().clear();
								showResultSetInTheTableView(rs,performanceReportTableView);
								
								performanceCopyQuery.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										 clipBoardcontent.putString("Select * from sys."+performanceReportQueries[index]);
									        clipboard.setContent(clipBoardcontent);
										
									}
								});
								 
							} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}
						  }
						});
			 }
		 }
	}

	private void getdatabaseTablesDisplayTab(TreeItem<String> loadedDatabaseName, Tab databaseDetailsTablesTab) {
		System.out.println("Tables Tab selected ");
			
		  try( ResultSet rs = currentConnection.createStatement().executeQuery("select table_name,engine,auto_increment,table_rows,data_length,index_length,create_time,update_time,create_options from information_schema.tables where table_comment != 'view' and table_schema='"+loadedDatabaseName.getValue()+"'"))
		  { 			 
			  SplitPane tableDetailsSplitPane = new SplitPane();
			  //tableDetailsSplitPane.setId("SplitPane");
			  tableDetailsSplitPane.setOrientation(Orientation.VERTICAL);
			  tableDetailsSplitPane.setDividerPositions(0.75); 
			  TableView tablesView = showResultSetInTheTableViewDoubleClick(rs,"Tables",loadedDatabaseName.getValue());
			  tablesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
					@Override
					public void changed(ObservableValue<? extends HashMap<String, String>> observable,
							HashMap<String, String> oldValue, HashMap<String, String> newValue) {
						System.out.println("From Local oldValue --->"+oldValue);
						System.out.println("From local newValue --->"+newValue.keySet().toString());
						for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
							System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
						}
						TableViewSelectionModel  selectionModel = tablesView.getSelectionModel();
				        ObservableList selectedCells = selectionModel.getSelectedCells();
				        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
				        Object val = tablePosition.getTableColumn().getCellData(newValue);
				        System.out.println("Selected Value" + val);

					}	
			  });
			  
			  HBox allButtonsHBox = new HBox();
			  allButtonsHBox.setId("allButtonsHBox");
//			  allButtonsHBox.setSpacing(100);
//			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox tableButtonsHbox = new HBox();
			  tableButtonsHbox.setId("ButtonsHbox");
//			  tableButtonsHbox.setSpacing(20);
//			  tableButtonsHbox.setPadding(new Insets(0,50,0,0));
			  
			  viewTableButton = new Button(menu_Items_FX.resourceBundle.getString("ViewTable"));
			  viewTableButton.setId("buttons");
			  createTableButtons = new Button(menu_Items_FX.resourceBundle.getString("CreateTable"));
			  createTableButtons.setId("buttons");
			  editTableButton = new Button(menu_Items_FX.resourceBundle.getString("EditTable"));
			  editTableButton.setId("buttons");
			  deleteTableButton = new Button(menu_Items_FX.resourceBundle.getString("DeleteTable"));
			  deleteTableButton.setId("buttons");
			  refreshTableButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
			  refreshTableButton.setId("buttons");
			  
			  createTableButtons.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					tableDetailsSplitPane.getItems().remove(tableButtonsHbox);
					tableDetailsSplitPane.setDividerPositions(0.99);
				}	
			  });
			  
			  tableButtonsHbox.getChildren().addAll(viewTableButton,createTableButtons,editTableButton,deleteTableButton);
			  allButtonsHBox.getChildren().addAll(tableButtonsHbox,refreshTableButton);
			  
			  tableDetailsSplitPane.getItems().addAll(tablesView,allButtonsHBox);
			  databaseDetailsTablesTab.setContent(tableDetailsSplitPane);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

	private void getdatabaseViewsDisplayTab(TreeItem<String> loadedDatabaseName, Tab databaseDetailsViewsTab) {
		System.out.println("Views Tab selected ");
		  	try(ResultSet rs =  currentConnection.createStatement().executeQuery("select table_name,check_option,is_updatable,definer,view_definition from information_schema.views where table_schema='"+loadedDatabaseName.getValue()+"'"))
		  	{		    
			  SplitPane viewDetailsSplitPane = new SplitPane();
			  viewDetailsSplitPane.setOrientation(Orientation.VERTICAL);
			  viewDetailsSplitPane.setDividerPositions(0.75); 
			  TableView tablesView = showResultSetInTheTableViewDoubleClick(rs,"Views",loadedDatabaseName.getValue());
			  tablesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
					@Override
					public void changed(ObservableValue<? extends HashMap<String, String>> observable,
							HashMap<String, String> oldValue, HashMap<String, String> newValue) {
						System.out.println("From Local oldValue --->"+oldValue);
						System.out.println("From local newValue --->"+newValue.keySet().toString());
						
						
						
						
						for(Map.Entry<String, String> tableValues : newValue.entrySet()) {	
							System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
						}
						TableViewSelectionModel  selectionModel = tablesView.getSelectionModel();
				        ObservableList selectedCells = selectionModel.getSelectedCells();
				        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
				        Object val = tablePosition.getTableColumn().getCellData(newValue);
				        System.out.println("Selected Value" + val);
				     
					}	
			  });
			  
			  HBox allButtonsHBox = new HBox();
			  allButtonsHBox.setId("allButtonsHBox");
//			  allButtonsHBox.setSpacing(100);
//			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  
			  HBox viewButtonsHbox = new HBox();
			  viewButtonsHbox.setId("ButtonsHbox");
//			  viewButtonsHbox.setSpacing(20);
//			  viewButtonsHbox.setPadding(new Insets(0,50,0,0));
			  
			  viewViewButton = new Button(menu_Items_FX.resourceBundle.getString("ViewView"));
			  viewViewButton.setId("buttons");
			  createViewButton = new Button(menu_Items_FX.resourceBundle.getString("CreateView"));
			  createViewButton.setId("buttons");
			  editViewButton = new Button(menu_Items_FX.resourceBundle.getString("EditView"));
			  editViewButton.setId("buttons");
			  deleteViewButton = new Button(menu_Items_FX.resourceBundle.getString("DeleteView"));
			  deleteViewButton.setId("buttons");
			  refreshViewButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
			  refreshViewButton.setId("buttons");
			  
			  createViewButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					viewDetailsSplitPane.getItems().remove(viewButtonsHbox);
					viewDetailsSplitPane.setDividerPositions(0.99);
				}	
			  });
			  
			  viewButtonsHbox.getChildren().addAll(viewViewButton,createViewButton,editViewButton,deleteViewButton);
			  allButtonsHBox.getChildren().addAll(viewButtonsHbox,refreshViewButton);
			  
			  viewDetailsSplitPane.getItems().addAll(tablesView,allButtonsHBox);

			  databaseDetailsViewsTab.setContent(viewDetailsSplitPane);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

	private void getdatabaseIndexesDisplayTab(TreeItem<String> loadedDatabaseName, Tab databaseDetailsIndexesTab) {
		System.out.println("Indexes Tab selected ");
		  	try( ResultSet rs =  currentConnection.createStatement().executeQuery("select  index_name,column_name,table_name,Index_type,packed,nullable,non_unique from information_schema.statistics where table_schema = '"+loadedDatabaseName.getValue()+"'")) 
		  	{		   
			  SplitPane indexesDetailsSplitPane = new SplitPane();
			  indexesDetailsSplitPane.setOrientation(Orientation.VERTICAL);
			  indexesDetailsSplitPane.setDividerPositions(0.75); 
			  TableView tablesView = showResultSetInTheTableViewDoubleClick(rs,"Indexes",loadedDatabaseName.getValue());
			  tablesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
					@Override
					public void changed(ObservableValue<? extends HashMap<String, String>> observable,
							HashMap<String, String> oldValue, HashMap<String, String> newValue) {

						System.out.println("From Local oldValue --->"+oldValue);
						System.out.println("From local newValue --->"+newValue.keySet().toString());
						for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
							
							System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
						}
						TableViewSelectionModel  selectionModel = tablesView.getSelectionModel();
				        ObservableList selectedCells = selectionModel.getSelectedCells();
				        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
				        Object val = tablePosition.getTableColumn().getCellData(newValue);
				        System.out.println("Selected Value" + val);
					}	
			  });
			  
			  HBox allButtonsHBox = new HBox();
			  allButtonsHBox.setId("allButtonsHBox");
//			  allButtonsHBox.setSpacing(300);
//			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox indexButtonsHbox = new HBox();
			  indexButtonsHbox.setId("ButtonsHbox");
			 // indexButtonsHbox.setSpacing(20);
			  
			  viewIndexButton = new Button(menu_Items_FX.resourceBundle.getString("ViewIndex"));
			  viewIndexButton.setId("buttons");
			  createIndexButton = new Button(menu_Items_FX.resourceBundle.getString("CreateIndex"));
			  createIndexButton.setId("buttons");
			  editIndexButton = new Button(menu_Items_FX.resourceBundle.getString("EditIndex"));
			  editIndexButton.setId("buttons");
			  deleteIndexButton = new Button(menu_Items_FX.resourceBundle.getString("DeleteIndex"));
			  deleteIndexButton.setId("buttons");
			  refreshIndexButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
			  refreshIndexButton.setId("buttons");
			  createIndexButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					indexesDetailsSplitPane.getItems().remove(indexButtonsHbox);
					indexesDetailsSplitPane.setDividerPositions(0.99);
				}	
			  });
			  
			  indexButtonsHbox.getChildren().addAll(viewIndexButton,createIndexButton,editIndexButton,deleteIndexButton);
			  allButtonsHBox.getChildren().addAll(indexButtonsHbox,refreshIndexButton);
			  
			  indexesDetailsSplitPane.getItems().addAll(tablesView,allButtonsHBox);

			  databaseDetailsIndexesTab.setContent(indexesDetailsSplitPane);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	private void getdatabaseProceduresDisplayTab(TreeItem<String> loadedDatabaseName,Tab databaseDetailsProceduresTab) {
		System.out.println("Procedures Tab selected ");
		  	try(ResultSet rs = currentConnection.createStatement().executeQuery("select  routine_name,definer,created,LAST_ALTERED,routine_comment from information_schema.routines where  routine_type != 'FUNCTION' and  routine_schema = '"+loadedDatabaseName.getValue()+"'"))
		  	{		    
			  SplitPane proceduresDetailsSplitPane = new SplitPane();
			  proceduresDetailsSplitPane.setOrientation(Orientation.VERTICAL);
			  proceduresDetailsSplitPane.setDividerPositions(0.75); 
			  TableView tablesView = showResultSetInTheTableViewDoubleClick(rs,"Procedures",loadedDatabaseName.getValue());
			  tablesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
					@Override
					public void changed(ObservableValue<? extends HashMap<String, String>> observable,
							HashMap<String, String> oldValue, HashMap<String, String> newValue) {

						System.out.println("From Local oldValue --->"+oldValue);
						System.out.println("From local newValue --->"+newValue.keySet().toString());
						for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
							
							System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
						}
						TableViewSelectionModel  selectionModel = tablesView.getSelectionModel();
				        ObservableList selectedCells = selectionModel.getSelectedCells();
				        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
				        Object val = tablePosition.getTableColumn().getCellData(newValue);
				        System.out.println("Selected Value" + val);
					}	
			  });
			  
			  HBox allButtonsHBox = new HBox();
			  allButtonsHBox.setId("allButtonsHBox");
//			  allButtonsHBox.setSpacing(300);
//			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox proceduresButtonsHbox = new HBox();
			  proceduresButtonsHbox.setId("ButtonsHbox");
			//  proceduresButtonsHbox.setSpacing(20);
			  
			  viewProcedureButton = new Button(menu_Items_FX.resourceBundle.getString("ViewProcedure"));
			  viewProcedureButton.setId("buttons");
			  createProcedureButton = new Button(menu_Items_FX.resourceBundle.getString("CreateProcedure"));
			  createProcedureButton.setId("buttons");
			  editProcedureButton = new Button(menu_Items_FX.resourceBundle.getString("EditProcedure"));
			  editProcedureButton.setId("buttons");
			  deleteProcedureButton = new Button(menu_Items_FX.resourceBundle.getString("DeleteProcedure"));
			  deleteProcedureButton.setId("buttons");
			  refreshProcedureButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
			  refreshProcedureButton.setId("buttons");
			  createProcedureButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					proceduresDetailsSplitPane.getItems().remove(proceduresButtonsHbox);
					proceduresDetailsSplitPane.setDividerPositions(0.99);
				}	
			  });
			  
			  proceduresButtonsHbox.getChildren().addAll(viewProcedureButton,createProcedureButton,editProcedureButton,deleteProcedureButton);
			  allButtonsHBox.getChildren().addAll(proceduresButtonsHbox,refreshProcedureButton);
			  
			  proceduresDetailsSplitPane.getItems().addAll(tablesView,allButtonsHBox);
			  
			  databaseDetailsProceduresTab.setContent(proceduresDetailsSplitPane);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

	private void getdatabaseFunctionsDisplayTab(TreeItem<String> loadedDatabaseName, Tab databaseDetailsFunctionsTab) {
		System.out.println("Functions Tab selected ");
		  	try(ResultSet rs = currentConnection.createStatement().executeQuery("select  routine_name,definer,created,LAST_ALTERED,routine_comment from information_schema.routines where  routine_type != 'PROCEDURE' and  routine_schema = '"+loadedDatabaseName.getValue()+"'")) 
		  	{		  
			  SplitPane functionsDetailsSplitPane = new SplitPane();
			  functionsDetailsSplitPane.setOrientation(Orientation.VERTICAL);
			  functionsDetailsSplitPane.setDividerPositions(0.75); 
			  TableView tablesView = showResultSetInTheTableViewDoubleClick(rs,"Functions",loadedDatabaseName.getValue());
			  tablesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
					@Override
					public void changed(ObservableValue<? extends HashMap<String, String>> observable,
							HashMap<String, String> oldValue, HashMap<String, String> newValue) {

						System.out.println("From Local oldValue --->"+oldValue);
						System.out.println("From local newValue --->"+newValue.keySet().toString());
						for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
							
							System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
						}
						TableViewSelectionModel  selectionModel = tablesView.getSelectionModel();
				        ObservableList selectedCells = selectionModel.getSelectedCells();
				        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
				        Object val = tablePosition.getTableColumn().getCellData(newValue);
				        System.out.println("Selected Value" + val);
					}	
			  });
			  
			  HBox allButtonsHBox = new HBox();
			  allButtonsHBox.setId("allButtonsHBox");
//			  allButtonsHBox.setSpacing(300);
//			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox functionsButtonsHbox = new HBox();
			  functionsButtonsHbox.setId("ButtonsHbox");
			 // functionsButtonsHbox.setSpacing(20);
			  
			  viewFunctionButton = new Button(menu_Items_FX.resourceBundle.getString("ViewFunction"));
			  viewFunctionButton.setId("buttons");
			  createFunctionButton = new Button(menu_Items_FX.resourceBundle.getString("CreateFunction"));
			  createFunctionButton.setId("buttons");
			  editFunctionButton = new Button(menu_Items_FX.resourceBundle.getString("EditFunction"));
			  editFunctionButton.setId("buttons");
			  deleteFunctionButton = new Button(menu_Items_FX.resourceBundle.getString("DeleteFunction"));
			  deleteFunctionButton.setId("buttons");
			  refreshProcedureButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
			  refreshProcedureButton.setId("buttons");
			  createFunctionButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					functionsDetailsSplitPane.getItems().remove(functionsButtonsHbox);
					functionsDetailsSplitPane.setDividerPositions(0.99);
				}	
			  });
			  
			  functionsButtonsHbox.getChildren().addAll(viewFunctionButton,createFunctionButton,editFunctionButton,deleteFunctionButton);
			  allButtonsHBox.getChildren().addAll(functionsButtonsHbox,refreshProcedureButton);
			  
			  functionsDetailsSplitPane.getItems().addAll(tablesView,allButtonsHBox);  
			  databaseDetailsFunctionsTab.setContent(functionsDetailsSplitPane);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

	private void getdatabaseTriggersDisplayTab(TreeItem<String> loadedDatabaseName, Tab databaseDetailsTriggersTab) {
			System.out.println("Triggers Tab selected ");
		  	try(ResultSet rs = currentConnection.createStatement().executeQuery("select trigger_name,trigger_Schema,event_manipulation,event_object_Schema,event_object_table,action_order,action_timing,definer,created from information_schema.triggers where trigger_Schema = '"+loadedDatabaseName.getValue()+"'"))
		  	{		  
		  		  
			  SplitPane triggersDetailsSplitPane = new SplitPane();
			  triggersDetailsSplitPane.setOrientation(Orientation.VERTICAL);
			  triggersDetailsSplitPane.setDividerPositions(0.75); 
			  TableView tablesView = showResultSetInTheTableViewDoubleClick(rs,"Triggers",loadedDatabaseName.getValue());
			  tablesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
					@Override
					public void changed(ObservableValue<? extends HashMap<String, String>> observable,
							HashMap<String, String> oldValue, HashMap<String, String> newValue) {

						System.out.println("From Local oldValue --->"+oldValue);
						System.out.println("From local newValue --->"+newValue.keySet().toString());
						for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
							
							System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
						}
						TableViewSelectionModel  selectionModel = tablesView.getSelectionModel();
				        ObservableList selectedCells = selectionModel.getSelectedCells();
				        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
				        Object val = tablePosition.getTableColumn().getCellData(newValue);
				        System.out.println("Selected Value" + val);
					}	
			  });
			  
			  HBox allButtonsHBox = new HBox();
			  allButtonsHBox.setId("allButtonsHBox");
//			  allButtonsHBox.setSpacing(300);
//			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox triggersButtonsHbox = new HBox();
			  triggersButtonsHbox.setId("ButtonsHbox");
			 // triggersButtonsHbox.setSpacing(20);
			  
			  viewTriggersButton = new Button(menu_Items_FX.resourceBundle.getString("ViewTrigger"));
			  viewTriggersButton.setId("buttons");
			  createTriggersButton = new Button(menu_Items_FX.resourceBundle.getString("CreateTrigger"));
			  createTriggersButton.setId("buttons");
			  editTriggersButton = new Button(menu_Items_FX.resourceBundle.getString("EditTrigger"));
			  editTriggersButton.setId("buttons");
			  deleteTriggersButton = new Button(menu_Items_FX.resourceBundle.getString("DeleteTrigger"));
			  deleteTriggersButton.setId("buttons");
			  refreshTriggersButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
			  refreshTriggersButton.setId("buttons");
			  createTriggersButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					triggersDetailsSplitPane.getItems().remove(triggersButtonsHbox);
					triggersDetailsSplitPane.setDividerPositions(0.99);
				}	
			  });
			  
			  triggersButtonsHbox.getChildren().addAll(viewTriggersButton,createTriggersButton,editTriggersButton,deleteTriggersButton);
			  allButtonsHBox.getChildren().addAll(triggersButtonsHbox,refreshTriggersButton);
			  
			  triggersDetailsSplitPane.getItems().addAll(tablesView,allButtonsHBox);  
			  databaseDetailsTriggersTab.setContent(triggersDetailsSplitPane);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	private Tab databaseDoubleClickMethod(TreeItem<String> loadedDatabaseName,String componentToFocus) {
		
		Tab mainDatabaseTab = new Tab();
		mainDatabaseTab.getStyleClass().add("Tabs");
		mainDatabaseTab.setText( loadedDatabaseName.getValue());
		mainDatabaseTab.setGraphic(ImageItemsHolder.getMySqlImage(connectionPlaceHolder.getConnectionName()));

		
		TabPane databaseTabPane = new TabPane();
		databaseTabPane.setId("databaseTabPane");
		databaseTabPane.getStyleClass().add("Tabs");
		//databaseTabPane.setTabMinWidth(200);	
		
		databaseDetails = new Tab(menu_Items_FX.resourceBundle.getString("Details"));
		databaseDetails.getStyleClass().add("Tabs");
		databaseDetails.setClosable(false);
		
		databaseDetailsPropertiesTab = new Tab(menu_Items_FX.resourceBundle.getString("Properties"));
		databaseDetailsPropertiesTab.getStyleClass().add("Tabs");
		databaseDetailsPropertiesTab.setClosable(false);
		databaseDetailsTablesTab = new Tab(menu_Items_FX.resourceBundle.getString("Tables"));
		databaseDetailsTablesTab.getStyleClass().add("Tabs");
		databaseDetailsTablesTab.setClosable(false);
		databaseDetailsViewsTab = new Tab(menu_Items_FX.resourceBundle.getString("Views"));
		databaseDetailsViewsTab.getStyleClass().add("Tabs");
		databaseDetailsViewsTab.setClosable(false);
		databaseDetailsIndexesTab = new Tab(menu_Items_FX.resourceBundle.getString("Indexes"));
		databaseDetailsIndexesTab.getStyleClass().add("Tabs");
		databaseDetailsIndexesTab.setClosable(false);
		databaseDetailsProceduresTab = new Tab(menu_Items_FX.resourceBundle.getString("Procedures"));
		databaseDetailsProceduresTab.getStyleClass().add("Tabs");
		databaseDetailsProceduresTab.setClosable(false);
		databaseDetailsFunctionsTab = new Tab(menu_Items_FX.resourceBundle.getString("Functions"));
		databaseDetailsFunctionsTab.getStyleClass().add("Tabs");
		databaseDetailsFunctionsTab.setClosable(false);
		databaseDetailsTriggersTab = new Tab(menu_Items_FX.resourceBundle.getString("Triggers"));
		databaseDetailsTriggersTab.getStyleClass().add("Tabs");
		databaseDetailsTriggersTab.setClosable(false);
		databaseDetailsEventsTab = new Tab(menu_Items_FX.resourceBundle.getString("Events"));
		databaseDetailsEventsTab.getStyleClass().add("Tabs");
		databaseDetailsEventsTab.setClosable(false);
		
		TabPane databaseDetailsTabPane = new TabPane();
		databaseDetailsTabPane.setId("databaseDetailsTabPane");
		databaseDetailsTabPane.getStyleClass().add("Tabs");
		//databaseDetailsTabPane.setTabMinWidth(100);
		databaseDetailsTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					 // System.out.println("Old Tab Selected -->"+oldValue.getText());
					  System.out.println("New Tab Selected -->"+newValue.getText());
					  
					  if(newValue.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Properties"))) {
						  System.out.println("Properties Tab selected ");
						  
						  getdatabasePropertiesDisplayTab(loadedDatabaseName,databaseDetailsPropertiesTab);
					  }
					  if(newValue.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Tables"))) {
						  getdatabaseTablesDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
					  }
					  if(newValue.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Views"))) {
						  getdatabaseViewsDisplayTab(loadedDatabaseName, databaseDetailsViewsTab);
					  }
					  
					  if(newValue.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Indexes"))) {
						  getdatabaseIndexesDisplayTab(loadedDatabaseName, databaseDetailsIndexesTab);
					  }
					  if(newValue.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Procedures"))) {
						  getdatabaseProceduresDisplayTab(loadedDatabaseName, databaseDetailsProceduresTab);
					  }
					  if(newValue.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Functions"))) {
						  getdatabaseFunctionsDisplayTab(loadedDatabaseName, databaseDetailsFunctionsTab);
					  }
					  if(newValue.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Triggers"))) {
						  getdatabaseTriggersDisplayTab(loadedDatabaseName, databaseDetailsTriggersTab);
					  }
					  if(newValue.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("Events"))) {
						  getdatabaseEventsDisplayTab(loadedDatabaseName, databaseDetailsEventsTab);
					  }
				}
		  });
		
	   databaseDetailsTabPane.getTabs().addAll(databaseDetailsPropertiesTab,databaseDetailsTablesTab,databaseDetailsViewsTab,databaseDetailsIndexesTab,
				databaseDetailsProceduresTab,databaseDetailsFunctionsTab,databaseDetailsTriggersTab,databaseDetailsEventsTab);
				 
	   if(componentToFocus.equals(menu_Items_FX.resourceBundle.getString("Tables"))) {
			 getdatabaseTablesDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(1);
	   }
	   if(componentToFocus.equals(menu_Items_FX.resourceBundle.getString("Views"))) {
		   getdatabaseViewsDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(2);
	   }
	   if(componentToFocus.equals(menu_Items_FX.resourceBundle.getString("Indexes"))) {
		   getdatabaseIndexesDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(3);
	   }
	   if(componentToFocus.equals(menu_Items_FX.resourceBundle.getString("Procedures"))) {
		   getdatabaseProceduresDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(4);
	   }	
	   if(componentToFocus.equals(menu_Items_FX.resourceBundle.getString("Functions"))) {
		    getdatabaseFunctionsDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(5);
	   }
	   if(componentToFocus.equals(menu_Items_FX.resourceBundle.getString("Triggers"))) {
		    getdatabaseTriggersDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(6);
	   }
	   if(componentToFocus.equals(menu_Items_FX.resourceBundle.getString("Events"))) {
		    getdatabaseEventsDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(7);
	   }
	   
	   databaseDetails.setContent(databaseDetailsTabPane);
		
	   databaseERDiagram = new Tab(menu_Items_FX.resourceBundle.getString("ERDiagram"));
	   databaseERDiagram.getStyleClass().add("Tabs");
	   databaseERDiagram.setClosable(false);

	   databaseGrahicsStats = new Tab(menu_Items_FX.resourceBundle.getString("GraphicsStats"));
	   databaseGrahicsStats.getStyleClass().add("Tabs");
	   databaseGrahicsStats.setClosable(false);
	   
	   databaseAIPrompt = new Tab(menu_Items_FX.resourceBundle.getString("AIPrompt"));
	   databaseAIPrompt.getStyleClass().add("Tabs");
	   databaseAIPrompt.setClosable(false);				
	   
	   databaseTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
		@Override
		public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
			
			System.out.println("Selected tab under Scehama ---->"+newValue.getText());
			
			if(newValue.getText().equals(menu_Items_FX.resourceBundle.getString("GraphicsStats"))) {
			//	getSchemaGraphicsStats(loadedDatabaseName,databaseGrahicsStats);
			}
			if(newValue.getText().equals("ER Diagram")) {
				  System.out.println("ER Diagram Selected");
				  getSchemaERModel(loadedDatabaseName,databaseERDiagram);
				
			}
		}

	   });
	   
	   databaseTabPane.getTabs().addAll(databaseDetails,databaseERDiagram,databaseGrahicsStats,databaseAIPrompt);
	   mainDatabaseTab.setContent(databaseTabPane);
       return mainDatabaseTab;
	}

	Stack<Runnable> undoStack;
	private void getSchemaERModel(TreeItem<String> loadedDatabaseName, Tab databaseERDiagram) {
		
		undoStack = new Stack<>();
		
		menu_Items_FX.vBoxleftTabPane.getTabs().add(menu_Items_FX.erDiagramExplorerTab);  		
		menu_Items_FX.vBoxleftTabPane.getSelectionModel().select(menu_Items_FX.erDiagramExplorerTab);
		
		ERModelApplication erModelApplication = new ERModelApplication();
		StackPane erModelstackPane = erModelApplication.drawERModel(loadedDatabaseName, currentConnection,undoStack);
		
		
		menu_Items_FX.erDiagramExplorerTab.setContent(createBirdsEyeView(erModelApplication,erModelstackPane,undoStack));
		
		databaseERDiagram.setContent(erModelstackPane);
		
	}
	
	Rectangle viewportRect ;
	ScrollPane scrollPane;
    private VBox createBirdsEyeView(ERModelApplication erModelApplication,StackPane stackPane,Stack<Runnable> undoStack ) {
        // Create a scaled snapshot of the main content
    	
    	Pane mainPane = erModelApplication.mainPane;
    	mainPane.setOnDragOver(e -> e.acceptTransferModes(javafx.scene.input.TransferMode.ANY));
    	
    	System.out.println(" --->" + mainPane.getBoundsInParent().getWidth());
    	 WritableImage writableImage = new WritableImage((int)erModelApplication.CANVAS_WIDTH,(int)
    			 erModelApplication.CANVAS_HEIGHT);
        
    	 mainPane.snapshot(null, writableImage);
        
        ImageView overviewImage = new ImageView(writableImage);
        overviewImage.setFitWidth(290);
        overviewImage.setFitHeight(220);
        
        // Create a viewport rectangle to show the visible area
        viewportRect = new Rectangle(29,22);
        viewportRect.setFill(Color.TRANSPARENT);
        viewportRect.setStroke(Color.RED);
        viewportRect.setStrokeWidth(2);
        
		
		final double[] offset = new double[2];
		
		 // When the mouse is pressed, record the offset between the mouse position and the TitledPane position
		
		
		viewportRect.setOnMouseEntered(event ->{
			viewportRect.setCursor(Cursor.MOVE);
			
		});
		viewportRect.setOnMouseExited(event ->{
			viewportRect.setCursor(Cursor.DEFAULT);
			
		});
		
		viewportRect.setOnMousePressed(event -> {
           offset[0] = event.getSceneX() - viewportRect.getLayoutX();
           offset[1] = event.getSceneY() - viewportRect.getLayoutY();
       });
       
		viewportRect.setOnMouseDragged(event -> { 

			mainPane.setScaleX(1);
	        mainPane.setScaleY(1);
	            
			double newX = event.getSceneX() - offset[0];
			double newY = event.getSceneY() - offset[1];

           // Restrict movement within the bounds of the mainPane
           if (newX >= 0 && ( newX + viewportRect.getWidth()) <= 290  )  {
        	   viewportRect.setLayoutX(newX);
           }
           if (newY >= 0 && ( newY + viewportRect.getHeight()) <= 220 ) {
        	   viewportRect.setLayoutY(newY);
           }
           
           System.out.println("Rectangle New X "+newX);
           System.out.println("Rectangle New Y "+newY);
           
           scrollPane =  (ScrollPane)stackPane.getChildren().get(0);
           scrollPane.setHvalue((newX )/ 290);
           scrollPane.setVvalue((newY)/ 220);
           
           
        });
		scrollPane =  (ScrollPane)stackPane.getChildren().get(0);
		Group group = new Group();
		group.getChildren().addAll(overviewImage,viewportRect);
		
		
		VBox vBox = new VBox();
        vBox.getChildren().add(group);
        
       FlowPane flowPane = new FlowPane(10,10);
       
      StackPane attributeStackPane = new StackPane();
      Ellipse attribute = new Ellipse(40,20);
      attribute.setStrokeWidth(1);
      attribute.setStroke(Color.BLACK);
      attribute.setFill(Color.WHITE);
      attributeStackPane.getChildren().addAll(attribute,new Text("Attribute"));
      attributeStackPane.setId("Attribute StackPane");
      flowPane.getChildren().add(attributeStackPane);
      enableShapeDragAndDrop(attributeStackPane,mainPane,erModelApplication); 	
      
      StackPane keyAttributeStackPane = new StackPane();
      Ellipse keyAttribute = new Ellipse(40,20);
      keyAttribute.setStrokeWidth(1);
      keyAttribute.setStroke(Color.BLACK);
      keyAttribute.setFill(Color.WHITE);
      Text text = new Text("Key Attribute");
      text.setUnderline(true);
      keyAttributeStackPane.getChildren().addAll(keyAttribute,text);
      keyAttributeStackPane.setId("KeyAttribute StackPane");
      flowPane.getChildren().add(keyAttributeStackPane);
      enableShapeDragAndDrop(keyAttributeStackPane,mainPane,erModelApplication); 	
      
      Ellipse outerEllipseMultivaluedAttribute = new Ellipse(45,25);
      outerEllipseMultivaluedAttribute.setStrokeWidth(1);
      outerEllipseMultivaluedAttribute.setStroke(Color.BLACK);
      outerEllipseMultivaluedAttribute.setFill(Color.WHITE);
	  Ellipse innerEllipseMultivaluedAttribute = new Ellipse(45 -4,25 -4);
	  innerEllipseMultivaluedAttribute.setStrokeWidth(1);
	  innerEllipseMultivaluedAttribute.setStroke(Color.BLACK);
	  innerEllipseMultivaluedAttribute.setFill(Color.WHITE);
	  StackPane multivaluedAttributeStackPane = new StackPane();
	  multivaluedAttributeStackPane.getChildren().addAll(outerEllipseMultivaluedAttribute,innerEllipseMultivaluedAttribute,new Text("Multivalued\n  Attribute"));
	  multivaluedAttributeStackPane.setId("MultivaluedAttribute StackPane");
	  flowPane.getChildren().add(multivaluedAttributeStackPane);
	  enableShapeDragAndDrop(multivaluedAttributeStackPane,mainPane,erModelApplication); 
	  
	  Ellipse derivedAttribute = new Ellipse(40,20);
	  derivedAttribute.getStrokeDashArray().addAll(10d);
	  derivedAttribute.setStrokeWidth(1);
	  derivedAttribute.setStroke(Color.BLACK);
	  derivedAttribute.setFill(Color.WHITE);
	  StackPane derivedAttributeStackPane = new StackPane();
	  derivedAttributeStackPane.getChildren().addAll(derivedAttribute,new Text("  Derived\nAttribute"));
	  derivedAttributeStackPane.setId("DerivedAttribute StackPane");
	  flowPane.getChildren().add(derivedAttributeStackPane);
	  enableShapeDragAndDrop(derivedAttributeStackPane,mainPane,erModelApplication);
	  
	  Ellipse outerEllipseWeakKeyAttribute = new Ellipse(45,25);
	  outerEllipseWeakKeyAttribute.setStrokeWidth(1);
	  outerEllipseWeakKeyAttribute.setStroke(Color.BLACK);
	  outerEllipseWeakKeyAttribute.setFill(Color.WHITE);
	  Ellipse innerEllipseWeakKeyAttribute = new Ellipse(45 -4,25 -4);
	  innerEllipseWeakKeyAttribute.setStrokeWidth(1);
	  innerEllipseWeakKeyAttribute.setStroke(Color.BLACK);
	  innerEllipseWeakKeyAttribute.setFill(Color.WHITE);
	  StackPane weakKeyAttributeStackPane = new StackPane();
	  Text weakKeyAttributetext = new Text(" WeakKey\nAttribute");
	  weakKeyAttributetext.setUnderline(true);
	  weakKeyAttributeStackPane.getChildren().addAll(outerEllipseWeakKeyAttribute,innerEllipseWeakKeyAttribute,weakKeyAttributetext);
	  weakKeyAttributeStackPane.setId("WeakKeyAttribute StackPane");
	  flowPane.getChildren().add(weakKeyAttributeStackPane);
	  enableShapeDragAndDrop(weakKeyAttributeStackPane,mainPane,erModelApplication);
	  
	  Rectangle entity = new Rectangle(80,40);
	  entity.setStrokeWidth(1); 
	  entity.setStroke(Color.BLACK);
	  entity.setFill(Color.WHITE);
	  StackPane entityStackPane = new StackPane();
	  entityStackPane.getChildren().addAll(entity,new Text("Entity"));
	  entityStackPane.setId("Entity StackPane");
	  flowPane.getChildren().add(entityStackPane);
	  enableShapeDragAndDrop(entityStackPane,mainPane,erModelApplication);
	  
	  
	  Rectangle weakEntityouterRectangle = new Rectangle(80,40);
	  weakEntityouterRectangle.setFill(Color.WHITE);
	  weakEntityouterRectangle.setStrokeWidth(1);
	  weakEntityouterRectangle.setStroke(Color.BLACK);
	  Rectangle weakEntityinnerRectangle = new Rectangle(80-6,40-6);
	  weakEntityinnerRectangle.setFill(Color.WHITE);
	  weakEntityinnerRectangle.setStrokeWidth(1);
	  weakEntityinnerRectangle.setStroke(Color.BLACK);
	  StackPane weakEntityStackPane = new StackPane();
	  weakEntityStackPane.getChildren().addAll(weakEntityouterRectangle,weakEntityinnerRectangle,new Text("  Weak \nEntity"));
	  weakEntityStackPane.setId("WeakEntity StackPane");
	  flowPane.getChildren().add(weakEntityStackPane);
	  enableShapeDragAndDrop(weakEntityStackPane,mainPane,erModelApplication);
	
	  Double[] rhombusSizevalues = new Double[]{100.0, 100.0, 65.0, 125.0, 100.0,150.0, 135.0,125.0,}; 
	  Polygon relationshipRhombus = new Polygon();
	  relationshipRhombus.setStrokeWidth(1);
	  relationshipRhombus.setStroke(Color.BLACK);
	  relationshipRhombus.setFill(Color.WHITE);   // change this  and relationshipmytextarea id themcss
	  relationshipRhombus.getPoints().addAll(rhombusSizevalues);
	  StackPane raltionshipRhombusStackPane = new StackPane();
	  raltionshipRhombusStackPane.getChildren().addAll(relationshipRhombus,new Text("Relationship"));
	  raltionshipRhombusStackPane.setId("Relationship StackPane");
	  flowPane.getChildren().add(raltionshipRhombusStackPane);	
	  
	  vBox.setSpacing(10);
      vBox.getChildren().add(flowPane);
      
      
      
      Button undoButton = new Button("Undo");
      undoButton.setOnAction(e -> {
    	  if (!undoStack.isEmpty()) {
              undoStack.pop().run();
          }
      });
      
      vBox.getChildren().addAll(undoButton);
      return vBox;
      
    }

  Shape shape ;
  private void enableShapeDragAndDrop(StackPane shapeStackPane,Pane mainPane,ERModelApplication erModelApplication){
	  
	  final double[] offset = new double[2];
	  
	  shapeStackPane.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	
	  shapeStackPane.setOnMousePressed(event -> {
   	
	  shapeStackPane.setBorder(new Border(new BorderStroke(Color.RED, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		if(viewportRect != null) {
			System.out.println( " viewportRect.getLayoutX() " + viewportRect.getLayoutX());
			System.out.println( " viewportRect.getLayoutY() " + viewportRect.getLayoutY());
		
		}
	    if(scrollPane != null) {
	    	System.out.println( "scrollPane.getHvalue() " + scrollPane.getHvalue() );
			System.out.println( "scrollPane.getVvalue() " + scrollPane.getVvalue() );
			
			System.out.println("erModelApplication.CANVAS_HEIGHT" + erModelApplication.CANVAS_HEIGHT);  
			System.out.println("erModelApplication.CANVAS_WIDTH " + erModelApplication.CANVAS_WIDTH);
			
			
	    }
	    
		
		 System.out.println("Who is Selected ? "+ shapeStackPane.getId().split(" ")[0] );
		 shape =  getSmallCopyOftheSelectedComponent(shapeStackPane.getId().split(" ")[0]);
		 shape.setLayoutX(scrollPane.getHvalue() * erModelApplication.CANVAS_WIDTH );
		 shape.setLayoutY(scrollPane.getVvalue() * erModelApplication.CANVAS_HEIGHT);
		 mainPane.getChildren().add(shape);
				

	    
       	offset[0] = event.getSceneX() ;
        offset[1] = event.getSceneY();
        shapePressed = true;
       
       });
  	  
	  shapeStackPane.setOnMouseDragged(event -> {
  		
		  double newX = event.getSceneX() - offset[0];
          double newY = event.getSceneY() - offset[1];

		shapeDragged = true;
		shape.setLayoutX(shape.getLayoutX() + newX);
		shape.setLayoutY( shape.getLayoutY() + newY);
  		
  		offset[0] = event.getSceneX() ;
        offset[1] = event.getSceneY();
      });
  	  
	  shapeStackPane.setOnMouseReleased(event -> {
  		 shapeReleased = true;
  		 selectedModelStackPane = shapeStackPane;
  		 shapeStackPane.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, 
 	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
  		 mainPane.getChildren().remove(shape);
  	  }); 
  	  
  	
  	  mainPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
  		@Override
  		public void handle(MouseEvent event) {
  			
  			System.out.println("Mouse Entered the mainPane shapePressed shapeDragged shapeReleased" +shapePressed + " "+ shapeDragged + " " + shapeReleased );
  			if(shapeDragged && shapePressed && shapeReleased) {
  				System.out.println("Shap dragged and released on mainpane");
  
	  			if(selectedModelStackPane != null)
	  			{
	  			  System.out.println("Who is dropped ? "+ selectedModelStackPane.getId().split(" ")[0] ); 
	  			  createERModelComponentOnMouseRelease(mainPane,erModelApplication,selectedModelStackPane.getId().split(" ")[0]);
	  			}	
	  		  shapePressed =false;
	  		  shapeDragged = false;
	  		  shapeReleased = false;
	  		}
  		}
  	});
    }
  
  private Shape getSmallCopyOftheSelectedComponent(String selectedERComponent) {

	  if(selectedERComponent.equalsIgnoreCase("Attribute")) {
		  
		  shape = new Ellipse(60,20);
		  shape.setStrokeWidth(2);
		  shape.setOpacity(0.5);
	  }
	  
	  if(selectedERComponent.equalsIgnoreCase("KeyAttribute")) {
		  
		  shape = new Ellipse(60,20);
		  shape.setStrokeWidth(2);
		  shape.setOpacity(0.5);
	  }
	  
	return shape;
}

private boolean createERModelComponentOnMouseRelease(Pane mainPane,ERModelApplication erModelApplication,String componentName) {
	  
	  if(componentName.equalsIgnoreCase("Attribute")) {
			  Attribute attribute = new Attribute("unknown", 60, 20, shape.getLayoutX(), shape.getLayoutY(), mainPane,undoStack);
			  erModelApplication.erModelList.add(attribute);
			  // Set the line based on the location of attribute
			  attribute.connectionLine.setEndX(shape.getLayoutX()+ (60/2));  // alraedy binded in Attribute
			  attribute.connectionLine.setEndY(shape.getLayoutY() + (20/2));
			  attribute.connectionLine.setStartX(shape.getLayoutX()+100);
			  attribute.connectionLine.setStartY(shape.getLayoutY()+200);
			  
			  attribute.connectionLineCircle.setFill(Color.BLACK);
			  attribute.connectionLineCircle.setStroke(Color.BLACK);
			  mainPane.getChildren().add(attribute.connectionLineCircle);
			  attribute.connectionLineCircle.setCenterX(attribute.connectionLine.getStartX());
			  attribute.connectionLineCircle.setCenterY(attribute.connectionLine.getStartY());
			  
			  attribute.connectionLineCircle.setOnMouseEntered(event -> {
				  ((Circle) event.getSource()).getScene().setCursor(Cursor.MOVE);
			  });
		  
			  attribute.connectionLineCircle.setOnMouseDragged(event -> {
				  System.out.println("Add circle at end");
				 
				  attribute.connectionLineCircle.setCenterX(event.getX());
				  attribute.connectionLineCircle.setCenterY(event.getY());
				  attribute.connectionLine.setStartX(event.getX());
				  attribute.connectionLine.setStartY(event.getY());
				  
				  
			  });
			  
			  attribute.connectionLineCircle.setOnMouseReleased(event -> {
				  System.out.println("Mose Released at :"+ event.getX());
				  System.out.println("Mose Released at :"+ event.getY());
				  
				  // iterate the modelList and check if its falls in any entity than bind it to this ,refer thecode in main class
				  
				  for(ERModel erModel : erModelApplication.erModelList) {
					  
					 if(erModel.stackPaneRectangle != null) {
							
							if(erModel instanceof Entity) {
								
								System.out.println(erModel.stackPaneRectangle.getBoundsInParent());
								double minX = erModel.stackPaneRectangle.getBoundsInParent().getMinX();
								double maxX = erModel.stackPaneRectangle.getBoundsInParent().getMaxX();
								double minY = erModel.stackPaneRectangle.getBoundsInParent().getMinY();
								double maxY = erModel.stackPaneRectangle.getBoundsInParent().getMaxY();
								
								if( ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
									
									System.out.println("Dropped inside the entity");
									System.out.println( ((Entity)erModel).textAreaRecatangle.getText());						
			
									attribute.connectionLine.startXProperty().bind(((Entity)erModel).stackPaneRectangle.layoutXProperty().add(((Entity)erModel).resizeRectangle.getWidth()/2) );
									attribute.connectionLine.startYProperty().bind(((Entity)erModel).stackPaneRectangle.layoutYProperty().add(((Entity)erModel).resizeRectangle.getHeight()/2) );
				
									mainPane.getChildren().remove(attribute.connectionLineCircle);
									mainPane.getChildren().remove(((Entity)erModel).stackPaneRectangle);
									mainPane.getChildren().add(((Entity)erModel).stackPaneRectangle);
									
									// Update the TableER Model which has this entity so that whan you move it this new once also gets effected
									for(TableERModel  tableERModel : erModelApplication.tableERModelMap.values()) {
										
										if(tableERModel.entity.stackPaneRectangle.equals(((Entity)erModel).stackPaneRectangle)){
											tableERModel.attributeArrayList.add(attribute);
										}
									}
								}
							}
							
							
						}
				  }
			  });
			  
			  attribute.connectionLineCircle.setOnMouseExited(event ->{
					 ((Circle) event.getSource()).getScene().setCursor(Cursor.DEFAULT);
					
				});
			  
	   }	  
	  if(componentName.equalsIgnoreCase("KeyAttribute")) {
		  
		  KeyAttribute  attribute = new KeyAttribute("unknown", 60, 20, shape.getLayoutX(), shape.getLayoutY(), mainPane,undoStack);
		  erModelApplication.erModelList.add(attribute);
		  // Set the line based on the location of attribute
		  attribute.connectionLine.setEndX(shape.getLayoutX()+ (60/2));  // alraedy binded in Attribute
		  attribute.connectionLine.setEndY(shape.getLayoutY() + (20/2));
		  attribute.connectionLine.setStartX(shape.getLayoutX()+100);
		  attribute.connectionLine.setStartY(shape.getLayoutY()+200);
		  
		  attribute.connectionLineCircle.setFill(Color.BLACK);
		  attribute.connectionLineCircle.setStroke(Color.BLACK);
		  mainPane.getChildren().add(attribute.connectionLineCircle);
		  attribute.connectionLineCircle.setCenterX(attribute.connectionLine.getStartX());
		  attribute.connectionLineCircle.setCenterY(attribute.connectionLine.getStartY());
		  
		  attribute.connectionLineCircle.setOnMouseEntered(event -> {
			  ((Circle) event.getSource()).getScene().setCursor(Cursor.MOVE);
		  });
	  
		  attribute.connectionLineCircle.setOnMouseDragged(event -> {
			  System.out.println("Add circle at end");
			 
			  attribute.connectionLineCircle.setCenterX(event.getX());
			  attribute.connectionLineCircle.setCenterY(event.getY());
			  attribute.connectionLine.setStartX(event.getX());
			  attribute.connectionLine.setStartY(event.getY());
			  
			  
		  });
		  
		  attribute.connectionLineCircle.setOnMouseReleased(event -> {
			  System.out.println("Mose Released at :"+ event.getX());
			  System.out.println("Mose Released at :"+ event.getY());
			  
			  // iterate the modelList and check if its falls in any entity than bind it to this ,refer thecode in main class
			  
			  for(ERModel erModel : erModelApplication.erModelList) {
				  
				 if(erModel.stackPaneRectangle != null) {
						
						if(erModel instanceof Entity) {
							
							System.out.println(erModel.stackPaneRectangle.getBoundsInParent());
							double minX = erModel.stackPaneRectangle.getBoundsInParent().getMinX();
							double maxX = erModel.stackPaneRectangle.getBoundsInParent().getMaxX();
							double minY = erModel.stackPaneRectangle.getBoundsInParent().getMinY();
							double maxY = erModel.stackPaneRectangle.getBoundsInParent().getMaxY();
							
							if( ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
								
								System.out.println("Dropped inside the entity");
								System.out.println( ((Entity)erModel).textAreaRecatangle.getText());						
		
								attribute.connectionLine.startXProperty().bind(((Entity)erModel).stackPaneRectangle.layoutXProperty().add(((Entity)erModel).resizeRectangle.getWidth()/2) );
								attribute.connectionLine.startYProperty().bind(((Entity)erModel).stackPaneRectangle.layoutYProperty().add(((Entity)erModel).resizeRectangle.getHeight()/2) );
			
								mainPane.getChildren().remove(attribute.connectionLineCircle);
								mainPane.getChildren().remove(((Entity)erModel).stackPaneRectangle);
								mainPane.getChildren().add(((Entity)erModel).stackPaneRectangle);
								
								// Update the TableER Model which has this entity so that whan you move it this new once also gets effected
								for(TableERModel  tableERModel : erModelApplication.tableERModelMap.values()) {
									
									if(tableERModel.entity.stackPaneRectangle.equals(((Entity)erModel).stackPaneRectangle)){
										tableERModel.keyAttributeArrayList.add(attribute);
									}
								}
							}
						}
									
					}
			  }
		  });
		  
		  attribute.connectionLineCircle.setOnMouseExited(event ->{
				 ((Circle) event.getSource()).getScene().setCursor(Cursor.DEFAULT);
				
		   });
		  
	  }
		  
	  
			  return true;
  }
    
    
	private Integer currentSchemaIndex;
	private Button dashBoardPieBarButton;
	private Tab topSchemasBySizesTab;
	private Tab memoryUsageTab;
	private Tab fileTableHotSpotsTab;
	private Tab highCostSqlStatementsTab ;
	private   Tab databaseScehamStatisticsTab;
	private   Tab userResourceUtilizationTab;
    private Tab hostResourceUtilizationTab;

	protected Tab getDashBoardStats(TreeItem<String> loadedDatabaseName,Tab databaseGrahicsStats,String showGraphType) {
		   
		   TabPane dashBoardMainTabPane = new TabPane();
		   dashBoardMainTabPane.getStyleClass().add("Tabs");
		
		   topSchemasBySizesTab = new Tab("Top Scehams by Sizes");
		   topSchemasBySizesTab.getStyleClass().add("Tabs");
	       memoryUsageTab = new Tab("Memory Usage");
	       memoryUsageTab.getStyleClass().add("Tabs");
	       fileTableHotSpotsTab = new Tab("Files/Tables Hot Spots");
	       fileTableHotSpotsTab.getStyleClass().add("Tabs");
		   highCostSqlStatementsTab = new Tab("High Cost SQL Statements");
		   highCostSqlStatementsTab.getStyleClass().add("Tabs");
		   databaseScehamStatisticsTab = new Tab("Databse Schema Statistics");
		   databaseScehamStatisticsTab.getStyleClass().add("Tabs");
		   userResourceUtilizationTab = new Tab("User Resource Utilization");
		   userResourceUtilizationTab.getStyleClass().add("Tabs");
	       hostResourceUtilizationTab = new Tab("Host Resource Utilization");
	       hostResourceUtilizationTab.getStyleClass().add("Tabs");
			 
	       dashBoardMainTabPane.getTabs().addAll(topSchemasBySizesTab,memoryUsageTab,fileTableHotSpotsTab,highCostSqlStatementsTab,databaseScehamStatisticsTab,userResourceUtilizationTab,
	    		   hostResourceUtilizationTab);
	       
		  
		   VBox dashBoardTabMainvBox = getTopSchemasBySizeVBox(loadedDatabaseName);
		   topSchemasBySizesTab.setContent(dashBoardTabMainvBox);
		   
		   dashBoardMainTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {				
				System.out.println("Current Tab Clicked in Performance Reports is  "+newValue.getText());
				if(newValue.getText().equals("Memory Usage")) {
					
					ArrayList<String> columnNamesForGraph =   getTopMemoryByUserHostColumnNames("User");	
					VBox memoryUsageTabMainvBox = getTopMemoryUsageByUsersVBox(loadedDatabaseName,columnNamesForGraph);
					memoryUsageTab.setContent(memoryUsageTabMainvBox);
				}
			}
		   });
		   
		   
		   databaseGrahicsStats.setContent(dashBoardMainTabPane);
		   return databaseGrahicsStats;
	}

	private ArrayList<String> getTopMemoryByUserHostColumnNames(String userOrHost){
		
		ArrayList<String> columnNames = new ArrayList<String>();
		
		if(userOrHost.equalsIgnoreCase("User")) {
			String sqlQuery = "Select * from sys.x$memory_by_user_by_current_bytes";
			try( ResultSet rs = stmt.executeQuery(sqlQuery)){	
				if(rs.next()) {
					Integer columnCount = rs.getMetaData().getColumnCount();
					for(int i=1;i<=columnCount;i++) {
						System.out.println(rs.getMetaData().getColumnName(i));
						columnNames.add(rs.getMetaData().getColumnName(i));
					}
				}
				}catch(Exception e) {
						e.printStackTrace();
			}
		}
		
		if(userOrHost.equalsIgnoreCase("Host")) {
			String sqlQuery = "Select * from sys.x$memory_by_host_by_current_bytes";
			try( ResultSet rs = stmt.executeQuery(sqlQuery)){	
				if(rs.next()) {
					Integer columnCount = rs.getMetaData().getColumnCount();
					for(int i=1;i<columnCount;i++) {
						columnNames.add(rs.getMetaData().getColumnName(i));
					}
				}
				}catch(Exception e) {
						e.printStackTrace();
			}
		}
		return columnNames;	
	}
	
	private ArrayList<TotalMemoryByUser> getTotalMemoryByUser(String columnName) {
		
		String sqlQuery = "Select user,"+columnName +" from sys.x$memory_by_user_by_current_bytes order by "+columnName;
		

		ArrayList<TotalMemoryByUser> totalMemoryByUserList = new ArrayList<TotalMemoryByUser>();
		TotalMemoryByUser totalMemoryByUser ;
		try( ResultSet rsSchemaSizes = stmt.executeQuery(sqlQuery)){	
			while(rsSchemaSizes.next()) {
				totalMemoryByUser = new TotalMemoryByUser();
				totalMemoryByUser.setUser(rsSchemaSizes.getString(1));
				totalMemoryByUser.setColumnValue(rsSchemaSizes.getString(2));;
				totalMemoryByUserList.add(totalMemoryByUser);
			}
			}catch(Exception e) {
					e.printStackTrace();
		}
		
		return totalMemoryByUserList;
		
	}

	private ArrayList<TotalMemoryByHost> getTotalMemoryByHost(String columnName) {
		
		String sqlQuery = "Select host,"+columnName +" from sys.x$memory_by_host_by_current_bytes order by "+columnName;
		
		ArrayList<TotalMemoryByHost> totalMemoryByHostList = new ArrayList<TotalMemoryByHost>();
		TotalMemoryByHost totalMemoryByHost ;
		try( ResultSet rsSchemaSizes = stmt.executeQuery(sqlQuery)){	
			while(rsSchemaSizes.next()) {
				totalMemoryByHost = new TotalMemoryByHost();
				totalMemoryByHost.setHost(rsSchemaSizes.getString(1));
				totalMemoryByHost.setColumnValue(rsSchemaSizes.getString(2));;
				totalMemoryByHostList.add(totalMemoryByHost);
			}
			}catch(Exception e) {
					e.printStackTrace();
		}
		
		return totalMemoryByHostList;
		
	}
	private String currentLoggedInUser;
	private String currentLoggedInHost;
	private String getCurrentLoggedInUserHost() {
		
		String sqlQuery = "Select user()";
		String loggedInUserHost ="";
		try { 
			ResultSet rs= stmt.executeQuery(sqlQuery);
			if(rs.next())
				loggedInUserHost = rs.getString(1);
			System.out.println("Currently Logged In user is ------------->"+loggedInUserHost);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		currentLoggedInUser = loggedInUserHost.split("@")[0];
		currentLoggedInHost = loggedInUserHost.split("@")[1];
		return loggedInUserHost;
	}
	
	private Integer currentUserIndex;
	private ChoiceBox totalMemoryByChoiceBox;
	private ChoiceBox memoryDistributionUserByChoiceBox;
	private VBox getTopMemoryUsageByUsersVBox(TreeItem<String> loadedDatabaseName,ArrayList<String> columnNamesForGraph) {
		
			if(currentLoggedInUser ==null || currentLoggedInHost == null) {
				getCurrentLoggedInUserHost();
			}
		
		   VBox dashBoardTabMainvBox = new VBox();
		   dashBoardTabMainvBox.setPadding(new Insets(10,0,20,10)); 
	
		   VBox graphDisplaySelectVBox = new VBox();
		   Label toalMemoryByLabel = new Label("Total Memory By");
		   totalMemoryByChoiceBox = new ChoiceBox();
		   totalMemoryByChoiceBox.getItems().add("User ");
		   totalMemoryByChoiceBox.getItems().add("Host");
		   totalMemoryByChoiceBox.getSelectionModel().select(0);
		   
	
		   
		   Label memoryDistributionByLabel = new Label("Memory Distribution By");
		   memoryDistributionUserByChoiceBox = new ChoiceBox();
		   for(int i=0;i<columnNamesForGraph.size();i++) {
			   memoryDistributionUserByChoiceBox.getItems().add(columnNamesForGraph.get(i));   
		   }
		   memoryDistributionUserByChoiceBox.getSelectionModel().select(columnNamesForGraph.size()-1);
		   graphDisplaySelectVBox.getChildren().addAll(toalMemoryByLabel,totalMemoryByChoiceBox,memoryDistributionByLabel,memoryDistributionUserByChoiceBox);
		   
		   
		   
		   ArrayList<TotalMemoryByUser> totalMemoryByUserList = getTotalMemoryByUser("total_allocated");
		   // Schema Size relative to other 
		   PieChart pieChartMemorySizes = new PieChart();
		   pieChartMemorySizes.setId("chart");
		   pieChartMemorySizes.setTitle("TOP MEMORY BY USER IN MB");
		   PieChart.Data slice ;
		   pieChartMemorySizes.setPrefSize(menu_Items_FX.size.getSize().getWidth()-450,menu_Items_FX.size.getSize().getHeight()-200);
		   currentUserIndex=0;
		   if(loadedDatabaseName != null) {  // this will be null in case of Performance Schema click
			   TotalMemoryByUser totalMemoryByUser = new TotalMemoryByUser();
			   totalMemoryByUser.setUser(currentLoggedInUser);
			   System.out.println("USer position ---->"+totalMemoryByUserList.indexOf(totalMemoryByUser));
			   currentUserIndex = totalMemoryByUserList.indexOf(totalMemoryByUser);
		   }
		   
		   getMemoryUsageUserPieChart(totalMemoryByUserList, pieChartMemorySizes,currentUserIndex);
		
		   CategoryAxis xAxis    = new CategoryAxis();
		   xAxis.setLabel("Databases");
		   NumberAxis yAxis = new NumberAxis();
		   yAxis.setLabel("Size (MB)");
		    
		   BarChart barChartMemorySizes = new BarChart(xAxis,yAxis);
		   barChartMemorySizes.setTitle("TOP MEMORY BY USER IN MB");
		   barChartMemorySizes.setTitleSide(Side.TOP);
		   barChartMemorySizes.setBarGap(5);
		   barChartMemorySizes.setHorizontalGridLinesVisible(false);
		   barChartMemorySizes.setVerticalGridLinesVisible(false);
		   barChartMemorySizes.setPrefWidth(totalMemoryByUserList.size()*120);  // This will cause the scroll bar
		     
		   getMemoryUsageUserBarChart(totalMemoryByUserList, barChartMemorySizes,currentUserIndex);
		   
		   ScrollPane barChartScrollPane = new ScrollPane();
		   barChartScrollPane.setId("ScrollPane");
		   barChartScrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		   barChartScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		   barChartScrollPane.setPrefHeight( menu_Items_FX.size.getSize().getHeight()-200);
		   barChartScrollPane.setContent(barChartMemorySizes);
		   
		 
		   memoryDistributionUserByChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					System.out.println("Selected memoryDistributionByChoiceBox choice is --->"+newValue);
					   ArrayList<TotalMemoryByUser> totalMemoryByUserList = getTotalMemoryByUser(newValue);
					   getMemoryUsageUserPieChart(totalMemoryByUserList, pieChartMemorySizes,currentUserIndex);
					   getMemoryUsageUserBarChart(totalMemoryByUserList, barChartMemorySizes,currentUserIndex);
				}
		   });
	
		   totalMemoryByChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					System.out.println("The Selected choice box value is "+newValue);
					if(newValue.equalsIgnoreCase("User")) {
						/*
						 ArrayList<TotalMemoryByUser> totalMemoryByUserList = getTotalMemoryByUser("total_allocated");
						 getMemoryUsageUserPieChart(totalMemoryByUserList, pieChartMemorySizes,currentUserIndex);
						 getMemoryUsageUserBarChart(totalMemoryByUserList, barChartMemorySizes,currentUserIndex);
						 */
						 getTopMemoryUsageByUsersVBox(loadedDatabaseName,columnNamesForGraph);
					}
					if(newValue.equalsIgnoreCase("Host")) {
						
						ArrayList<String> columnNamesForGraph =   getTopMemoryByUserHostColumnNames("Host");	
						VBox memoryUsageTabMainvBox = getTopMemoryUsageByHostsVBox(loadedDatabaseName,columnNamesForGraph);
						memoryUsageTab.setContent(memoryUsageTabMainvBox);
					}	
				}
			   });
		   
		   Button peiChartRotateLEftButton = new Button("Previous Schema");
		   peiChartRotateLEftButton.setId("buttons");
		   peiChartRotateLEftButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					currentUserIndex = currentUserIndex-1;
					getMemoryUsageUserPieChart  (totalMemoryByUserList, pieChartMemorySizes,currentUserIndex);
				}
		   });  
		   Button peiChartRotateRightButton = new Button("Next Schema");
		   peiChartRotateRightButton.setId("buttons");
			   peiChartRotateRightButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					currentUserIndex = currentUserIndex+1;
					getMemoryUsageUserPieChart(totalMemoryByUserList, pieChartMemorySizes,currentUserIndex);
				}
		  });
			   
		   HBox pieBarGraphHBox = new HBox(); 
		   pieBarGraphHBox.setId("pieBarGraphHBox");
//		   pieBarGraphHBox.setPadding(new Insets(0,0,0,0));
//		   pieBarGraphHBox.setSpacing(5); 
		   pieBarGraphHBox.getChildren().addAll(pieChartMemorySizes,graphDisplaySelectVBox);
		 
		   HBox dashBoardPieBarButtonHBox = new HBox();
		   dashBoardPieBarButtonHBox.setId("dashBoardPieBarButtonHBox");
		   //dashBoardPieBarButtonHBox.setPadding(new Insets(10,0,0,10));
		   dashBoardPieBarButton = new Button("Show As Bar Graph");
		   dashBoardPieBarButton.setId("buttons");
		   dashBoardPieBarButtonHBox.getChildren().addAll(dashBoardPieBarButton);
		   
		   HBox hBoxButtons = new HBox();
		   hBoxButtons.setId("ChartshBoxButtons");
//		   hBoxButtons.setSpacing(20);
//		   hBoxButtons.setPadding(new Insets(10,10,0,0));
		   
		   HBox pieChartRotationButtonHBox = new HBox();
		   pieChartRotationButtonHBox.setId("pieChartRotationButtonHBox");
//		   pieChartRotationButtonHBox.setSpacing(20);
//		   pieChartRotationButtonHBox.setPadding(new Insets(10,10,0,200));
		   pieChartRotationButtonHBox.getChildren().addAll(peiChartRotateLEftButton,peiChartRotateRightButton);
		   
		   hBoxButtons.getChildren().addAll(dashBoardPieBarButtonHBox,pieChartRotationButtonHBox);
		   
		   dashBoardPieBarButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(dashBoardPieBarButton.getText().equals("Show As Bar Graph")) {
					dashBoardPieBarButton.setText("Show As Pie Graph");
					 pieChartRotationButtonHBox.getChildren().clear();
					 pieBarGraphHBox.getChildren().clear();
					 pieBarGraphHBox.getChildren().addAll(barChartScrollPane,graphDisplaySelectVBox);
					// hBoxButtons.getChildren().addAll(dashBoardPieBarButtonHBox);
				}
				else if(dashBoardPieBarButton.getText().equals("Show As Pie Graph")) {
					dashBoardPieBarButton.setText("Show As Bar Graph");
					pieChartRotationButtonHBox.getChildren().clear();
					pieBarGraphHBox.getChildren().clear();
					pieBarGraphHBox.getChildren().addAll(pieChartMemorySizes,graphDisplaySelectVBox);
					pieChartRotationButtonHBox.getChildren().addAll(peiChartRotateLEftButton,peiChartRotateRightButton);
				}
			}
		   });    
		   dashBoardTabMainvBox.getChildren().addAll(pieBarGraphHBox);
		   dashBoardTabMainvBox.getChildren().addAll(hBoxButtons);
		   return dashBoardTabMainvBox;
	}

	private Integer currentHostIndex=0;
	private ChoiceBox memoryDistributionHostByChoiceBox;
	private VBox getTopMemoryUsageByHostsVBox(TreeItem<String> loadedDatabaseName,ArrayList<String> columnNamesForGraph) {
		
		if(currentLoggedInUser ==null || currentLoggedInHost == null) {
			getCurrentLoggedInUserHost();
		}
	
	   VBox dashBoardTabMainvBox = new VBox();
	   dashBoardTabMainvBox.setId("dashBoardTabMainvBox");
	   //dashBoardTabMainvBox.setPadding(new Insets(10,0,20,10)); 

	   VBox graphDisplaySelectVBox = new VBox();
	   Label toalMemoryByLabel = new Label("Total Memory By");
	   totalMemoryByChoiceBox = new ChoiceBox();
	   totalMemoryByChoiceBox.getStyleClass().add("choice-box");
	   totalMemoryByChoiceBox.getItems().add("User");
	   totalMemoryByChoiceBox.getItems().add("Host");
	   totalMemoryByChoiceBox.getSelectionModel().select(1);
	   
	   Label memoryDistributionByLabel = new Label("Memory Distribution By");
	   memoryDistributionHostByChoiceBox = new ChoiceBox();
	   memoryDistributionHostByChoiceBox.getStyleClass().add("choice-box");
	   for(int i=0;i<columnNamesForGraph.size();i++) {
		   memoryDistributionHostByChoiceBox.getItems().add(columnNamesForGraph.get(i));   
	   }
	   memoryDistributionHostByChoiceBox.getSelectionModel().select(columnNamesForGraph.size()-1);
	   graphDisplaySelectVBox.getChildren().addAll(toalMemoryByLabel,totalMemoryByChoiceBox,memoryDistributionByLabel,memoryDistributionHostByChoiceBox);
	   
	   ArrayList<TotalMemoryByHost> totalMemoryByHostList = getTotalMemoryByHost("total_allocated");
	   // Schema Size relative to other 
	   PieChart pieChartMemorySizes = new PieChart();
	   pieChartMemorySizes.setId("chart");
	   pieChartMemorySizes.setTitle("TOP MEMORY BY HOST IN MB");
	   PieChart.Data slice ;
	   pieChartMemorySizes.setPrefSize(menu_Items_FX.size.getSize().getWidth()-450,menu_Items_FX.size.getSize().getHeight()-200);
	   currentHostIndex=0;
	   if(loadedDatabaseName != null) {  // this will be null in case of Performance Schema click
		   TotalMemoryByHost totalMemoryByHost = new TotalMemoryByHost();
		   totalMemoryByHost.setHost (currentLoggedInUser);
		   System.out.println("Host position ---->"+totalMemoryByHostList.indexOf(totalMemoryByHost));
		   currentHostIndex = totalMemoryByHostList.indexOf(totalMemoryByHost);
	   }
	   
	   getMemoryUsageHostPieChart(totalMemoryByHostList, pieChartMemorySizes,currentHostIndex);
	
	   CategoryAxis xAxis    = new CategoryAxis();
	   xAxis.setLabel("Databases");
	   NumberAxis yAxis = new NumberAxis();
	   yAxis.setLabel("Size (MB)");
	    
	   BarChart barChartMemorySizes = new BarChart(xAxis,yAxis);
	   barChartMemorySizes.setTitle("TOP MEMORY BY HOST IN MB");
	   barChartMemorySizes.setTitleSide(Side.TOP);
	   barChartMemorySizes.setBarGap(5);
	   barChartMemorySizes.setHorizontalGridLinesVisible(false);
	   barChartMemorySizes.setVerticalGridLinesVisible(false);
	   barChartMemorySizes.setPrefWidth(totalMemoryByHostList.size()*120);  // This will cause the scroll bar
	     
	   getMemoryUsageHostBarChart(totalMemoryByHostList, barChartMemorySizes,currentHostIndex);
	   
	   ScrollPane barChartScrollPane = new ScrollPane();
	   barChartScrollPane.setId("ScrollPane");
	   barChartScrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
	   barChartScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
	   barChartScrollPane.setPrefHeight( menu_Items_FX.size.getSize().getHeight()-200);
	   barChartScrollPane.setContent(barChartMemorySizes);
	   
	 
	   memoryDistributionHostByChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("Selected memoryDistributionByChoiceBox choice is --->"+newValue);
				   ArrayList<TotalMemoryByHost> totalMemoryByHostList = getTotalMemoryByHost(newValue);
				   getMemoryUsageHostPieChart(totalMemoryByHostList, pieChartMemorySizes,currentHostIndex);
				   getMemoryUsageHostBarChart(totalMemoryByHostList, barChartMemorySizes,currentHostIndex);
			}
	   });

	   
	   totalMemoryByChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("The Selected choice box value is "+newValue);
				if(newValue.equalsIgnoreCase("User")) {
					/*   ArrayList<TotalMemoryByUser> totalMemoryByUserList = getTotalMemoryByUser("total_allocated");
					 getMemoryUsageUserPieChart(totalMemoryByUserList, pieChartMemorySizes,currentUserIndex);
					 getMemoryUsageUserBarChart(totalMemoryByUserList, barChartMemorySizes,currentUserIndex);
					 */

						ArrayList<String> columnNamesForGraph =   getTopMemoryByUserHostColumnNames("User");	
						VBox memoryUsageTabMainvBox = getTopMemoryUsageByUsersVBox(loadedDatabaseName,columnNamesForGraph);
						memoryUsageTab.setContent(memoryUsageTabMainvBox); 
				}
				if(newValue.equalsIgnoreCase("Host")) {
					ArrayList<String> columnNamesForGraph =   getTopMemoryByUserHostColumnNames("Host");	
					VBox memoryUsageTabMainvBox = getTopMemoryUsageByHostsVBox(loadedDatabaseName,columnNamesForGraph);
					memoryUsageTab.setContent(memoryUsageTabMainvBox);
				}	
			}
		   });
	   
	   Button peiChartRotateLEftButton = new Button("Previous Schema");
	   peiChartRotateLEftButton.setId("buttons");
	   peiChartRotateLEftButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentUserIndex = currentUserIndex-1;
				getMemoryUsageHostPieChart(totalMemoryByHostList, pieChartMemorySizes,currentHostIndex);
			}
	   });  
	   Button peiChartRotateRightButton = new Button("Next Schema");
	   peiChartRotateRightButton.setId("buttons");
		   peiChartRotateRightButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentUserIndex = currentUserIndex+1;
				getMemoryUsageHostPieChart(totalMemoryByHostList, pieChartMemorySizes,currentHostIndex);
			}
	  });
		   
	   HBox pieBarGraphHBox = new HBox(); 
	   pieBarGraphHBox.setId("pieBarGraphHBox");
//	   pieBarGraphHBox.setPadding(new Insets(0,0,0,0));
//	   pieBarGraphHBox.setSpacing(5); 
	   pieBarGraphHBox.getChildren().addAll(pieChartMemorySizes,graphDisplaySelectVBox);
	 
	   HBox dashBoardPieBarButtonHBox = new HBox();
	   dashBoardPieBarButtonHBox.setId("dashBoardPieBarButtonHBox");
	  // dashBoardPieBarButtonHBox.setPadding(new Insets(10,0,0,10));
	   dashBoardPieBarButton = new Button("Show As Bar Graph");
	   dashBoardPieBarButton.setId("buttons");
	   dashBoardPieBarButtonHBox.getChildren().addAll(dashBoardPieBarButton);
	   
	   HBox hBoxButtons = new HBox();
	  hBoxButtons.setId("ChartshBoxButtons");
//	   hBoxButtons.setSpacing(20);
//	   hBoxButtons.setPadding(new Insets(10,10,0,0));
	   
	   HBox pieChartRotationButtonHBox = new HBox();
	  pieChartRotationButtonHBox.setId("pieChartRotationButtonHBox");
//	   pieChartRotationButtonHBox.setSpacing(20);
//	   pieChartRotationButtonHBox.setPadding(new Insets(10,10,0,200));
	   pieChartRotationButtonHBox.getChildren().addAll(peiChartRotateLEftButton,peiChartRotateRightButton);
	   
	   hBoxButtons.getChildren().addAll(dashBoardPieBarButtonHBox,pieChartRotationButtonHBox);
	   
	   dashBoardPieBarButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			if(dashBoardPieBarButton.getText().equals("Show As Bar Graph")) {
				dashBoardPieBarButton.setText("Show As Pie Graph");
				 pieChartRotationButtonHBox.getChildren().clear();
				 pieBarGraphHBox.getChildren().clear();
				 pieBarGraphHBox.getChildren().addAll(barChartScrollPane,graphDisplaySelectVBox);
				// hBoxButtons.getChildren().addAll(dashBoardPieBarButtonHBox);
			}
			else if(dashBoardPieBarButton.getText().equals("Show As Pie Graph")) {
				dashBoardPieBarButton.setText("Show As Bar Graph");
				pieChartRotationButtonHBox.getChildren().clear();
				pieBarGraphHBox.getChildren().clear();
				pieBarGraphHBox.getChildren().addAll(pieChartMemorySizes,graphDisplaySelectVBox);
				pieChartRotationButtonHBox.getChildren().addAll(peiChartRotateLEftButton,peiChartRotateRightButton);
			}
		}
	   });    
	   dashBoardTabMainvBox.getChildren().addAll(pieBarGraphHBox);
	   dashBoardTabMainvBox.getChildren().addAll(hBoxButtons);
	   return dashBoardTabMainvBox;
   }
	
	
	private VBox getTopSchemasBySizeVBox(TreeItem<String> loadedDatabaseName) {
		VBox dashBoardTabMainvBox = new VBox();
		dashBoardTabMainvBox.setId("dashBoardTabMainvBox");
		//dashBoardTabMainvBox.setPadding(new Insets(10,0,20,10)); 
		   
		   ArrayList<InformationSchemaSizes> informationSchemaSizesList = getAllSchemaSizesinDatabase();
		
		   // Schema Size relative to other 
		   PieChart pieChartSchemaSizes = new PieChart();
		   pieChartSchemaSizes.setId("chart");
		   pieChartSchemaSizes.setTitle("TOP SCHEMAS BY SIZES IN MB");
		   PieChart.Data slice ;
		   pieChartSchemaSizes.setPrefSize(menu_Items_FX.size.getSize().getWidth()-450,menu_Items_FX.size.getSize().getHeight()-200);
		   currentSchemaIndex=0;
		   if(loadedDatabaseName != null) {  // this will be null in case of Performance Schema click
			   InformationSchemaSizes informationSchemaSizes = new InformationSchemaSizes();
			   informationSchemaSizes.setSchemaName(loadedDatabaseName.getValue());
			   System.out.println("Schema position ---->"+informationSchemaSizesList.indexOf(informationSchemaSizes));
			   currentSchemaIndex = informationSchemaSizesList.indexOf(informationSchemaSizes);
		   }
		   
		   getDashBoardPieChart(informationSchemaSizesList, pieChartSchemaSizes,currentSchemaIndex);
		
		   CategoryAxis xAxis    = new CategoryAxis();
		   xAxis.setLabel("Databases");
		   NumberAxis yAxis = new NumberAxis();
		   yAxis.setLabel("Size (MB)");
		    
		   BarChart barChartSchemaSizes = new BarChart(xAxis,yAxis);
		   barChartSchemaSizes.setTitle("TOP SCHEMAS BY SIZES IN MB");
		   barChartSchemaSizes.setTitleSide(Side.TOP);
		   barChartSchemaSizes.setBarGap(5);
		   barChartSchemaSizes.setHorizontalGridLinesVisible(false);
		   barChartSchemaSizes.setVerticalGridLinesVisible(false);
		   barChartSchemaSizes.setPrefWidth(informationSchemaSizesList.size()*120);  // This will cause the scroll bar
		     
		   getDashBoardBarChart(informationSchemaSizesList, barChartSchemaSizes,currentSchemaIndex);
		   
		   ScrollPane barChartScrollPane = new ScrollPane();
		   barChartScrollPane.setId("ScrollPane");
		   barChartScrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		   barChartScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		   barChartScrollPane.setPrefHeight( menu_Items_FX.size.getSize().getHeight()-200);
		   barChartScrollPane.setContent(barChartSchemaSizes);
		   
		   int totalNumberofSchemas =  informationSchemaSizesList.size(); 
	 
		   
		   Button peiChartRotateLEftButton = new Button("Previous Schema");
		   peiChartRotateLEftButton.setId("buttons");
		   peiChartRotateLEftButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					currentSchemaIndex = currentSchemaIndex-1;
					getDashBoardPieChart(informationSchemaSizesList, pieChartSchemaSizes,currentSchemaIndex);
				}
			   });  
		   Button peiChartRotateRightButton = new Button("Next Schema");
		   peiChartRotateRightButton.setId("buttons");
			   peiChartRotateRightButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					currentSchemaIndex = currentSchemaIndex+1;
					getDashBoardPieChart(informationSchemaSizesList, pieChartSchemaSizes,currentSchemaIndex);
				}
		  });
			   
		   HBox pieBarGraphHBox = new HBox();
		   pieBarGraphHBox.setId("pieBarGraphHBox");
		   //pieBarGraphHBox.setSpacing(10); 
		   pieBarGraphHBox.getChildren().addAll(pieChartSchemaSizes);
		 
		   HBox dashBoardPieBarButtonHBox = new HBox();
		   dashBoardPieBarButtonHBox.setId("dashBoardPieBarButtonHBox");
		   //dashBoardPieBarButtonHBox.setPadding(new Insets(10,0,0,10));
		   dashBoardPieBarButton = new Button("Show As Bar Graph");
		   dashBoardPieBarButton.setId("buttons");
		   dashBoardPieBarButtonHBox.getChildren().addAll(dashBoardPieBarButton);
		   
		   HBox hBoxButtons = new HBox();
		   hBoxButtons.setId("hBoxButtons");
//		   hBoxButtons.setSpacing(20);
//		   hBoxButtons.setPadding(new Insets(10,10,0,0));
		   
		   HBox pieChartRotationButtonHBox = new HBox();
		   pieChartRotationButtonHBox.setId("pieChartRotationButtonHBox");
//		   pieChartRotationButtonHBox.setSpacing(20);
//		   pieChartRotationButtonHBox.setPadding(new Insets(10,10,0,200));
		   pieChartRotationButtonHBox.getChildren().addAll(peiChartRotateLEftButton,peiChartRotateRightButton);
		   
		   hBoxButtons.getChildren().addAll(dashBoardPieBarButtonHBox,pieChartRotationButtonHBox);
		   
		   dashBoardPieBarButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(dashBoardPieBarButton.getText().equals("Show As Bar Graph")) {
					dashBoardPieBarButton.setText("Show As Pie Graph");
					 pieChartRotationButtonHBox.getChildren().clear();
					 pieBarGraphHBox.getChildren().clear();
					 pieBarGraphHBox.getChildren().add(barChartScrollPane);
					// hBoxButtons.getChildren().addAll(dashBoardPieBarButtonHBox);
				}
				else if(dashBoardPieBarButton.getText().equals("Show As Pie Graph")) {
					dashBoardPieBarButton.setText("Show As Bar Graph");
					pieChartRotationButtonHBox.getChildren().clear();
					pieBarGraphHBox.getChildren().clear();
					pieBarGraphHBox.getChildren().add(pieChartSchemaSizes);
					pieChartRotationButtonHBox.getChildren().addAll(peiChartRotateLEftButton,peiChartRotateRightButton);
				}
			}
		   });
		            
		   dashBoardTabMainvBox.getChildren().addAll(pieBarGraphHBox);
	 
		   dashBoardTabMainvBox.getChildren().addAll(hBoxButtons);
		return dashBoardTabMainvBox;
								 
	}

	private void getDashBoardPieChart(ArrayList<InformationSchemaSizes> informationSchemaSizesList, PieChart pieChartSchemaSizes,
			int currentSchemaIndex) {
		
		  pieChartSchemaSizes.getData().clear();
		   if(currentSchemaIndex-4 >= 0) {
		    	 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex-4).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-4).getDiskSize())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-4).getDiskSize())/1024/1024 )));
		   }
		   if(currentSchemaIndex-3 >= 0) {
		    	 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex-3).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-3).getDiskSize())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-3).getDiskSize())/1024/1024 )));
		   }
		   if(currentSchemaIndex-2 >= 0) {
		    	 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex-2).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-2).getDiskSize())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-2).getDiskSize())/1024/1024 )));
		   }
		   if(currentSchemaIndex-1 >= 0) {
		    	 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex-1).getSchemaName() +" "+get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-1).getDiskSize())/1024/1024)  + " MB", get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-1).getDiskSize())/1024/1024 )));
		   }
		   pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex-0).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-0).getDiskSize())/1024/1024 ) + " MB",get2digitDoublePrecisionValue( Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-0).getDiskSize())/1024/1024 )));
			 
		   if(currentSchemaIndex+1 <= informationSchemaSizesList.size()-1) {
				 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex+1).getSchemaName() +" "+get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+1).getDiskSize())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+1).getDiskSize())/1024/1024 )));
		   }
		   if(currentSchemaIndex+2 <= informationSchemaSizesList.size()-1) {
				 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex+2).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+2).getDiskSize())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+2).getDiskSize())/1024/1024 )));
		   }
		   if(currentSchemaIndex+3 <= informationSchemaSizesList.size()-1) {
				 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex+3).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+3).getDiskSize())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+3).getDiskSize())/1024/1024 )));
		   }
		   if(currentSchemaIndex+4 <= informationSchemaSizesList.size()-1) {
				 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex+4).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+4).getDiskSize())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+4).getDiskSize())/1024/1024 )));
		   }
		   if(currentSchemaIndex+5 <= informationSchemaSizesList.size()-1) {
				 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex+5).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+5).getDiskSize())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+5).getDiskSize())/1024/1024 )));
		   }
	}


	private void getMemoryUsageUserPieChart(ArrayList<TotalMemoryByUser> totalMemoryByUserList, PieChart pieChartMemorySizes,
			int currentUserIndex) {
		
		pieChartMemorySizes.getData().clear();
		   if(currentUserIndex-4 >= 0) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex-4).getUser() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex-4).getColumnValue()  )/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByUserList.get(currentUserIndex-4).getColumnValue() )/1024/1024 )));
		   }
		   if(currentUserIndex-3 >= 0) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex-3).getUser() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex-3).getColumnValue())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByUserList.get(currentUserIndex-3).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex-2 >= 0) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex-2).getUser() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex-2).getColumnValue())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByUserList.get(currentUserIndex-2).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex-1 >= 0) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex-1).getUser() +" "+get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex-1).getColumnValue())/1024/1024)  + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex-1).getColumnValue())/1024/1024 )));
		   }
		   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex-0).getUser() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex-0).getColumnValue())/1024/1024 ) + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByUserList.get(currentUserIndex-0).getColumnValue())/1024/1024 )));
			 
		   if(currentUserIndex+1 <= totalMemoryByUserList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex+1).getUser() +" "+get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+1).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+1).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex+2 <= totalMemoryByUserList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex+2).getUser() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+2).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+2).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex+3 <= totalMemoryByUserList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex+3).getUser() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+3).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+3).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex+4 <= totalMemoryByUserList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex+4).getUser() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+4).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+4).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex+5 <= totalMemoryByUserList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByUserList.get(currentUserIndex+5).getUser() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+5).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(currentUserIndex+5).getColumnValue())/1024/1024 )));
		   }
	}
	
	private void getMemoryUsageHostPieChart(ArrayList<TotalMemoryByHost> totalMemoryByHostList, PieChart pieChartMemorySizes,
			int currentUserIndex) {
		
		pieChartMemorySizes.getData().clear();
									   
	  
															
																																																																																		  
	   
		  
		   if(currentUserIndex-4 >= 0) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex-4).getHost() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex-4).getColumnValue()  )/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByHostList.get(currentUserIndex-4).getColumnValue() )/1024/1024 )));
		   }
		   if(currentUserIndex-3 >= 0) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex-3).getHost() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex-3).getColumnValue())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByHostList.get(currentUserIndex-3).getColumnValue())/1024/1024 )));
		
		   }
		   if(currentUserIndex-2 >= 0) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex-2).getHost() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex-2).getColumnValue())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByHostList.get(currentUserIndex-2).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex-1 >= 0) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex-1).getHost() +" "+get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex-1).getColumnValue())/1024/1024)  + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex-1).getColumnValue())/1024/1024 )));
		   }
		   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex-0).getHost() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex-0).getColumnValue())/1024/1024 ) + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByHostList.get(currentUserIndex-0).getColumnValue())/1024/1024 )));
			 
		   if(currentUserIndex+1 <= totalMemoryByHostList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex+1).getHost() +" "+get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+1).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+1).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex+2 <= totalMemoryByHostList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex+2).getHost() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+2).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+2).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex+3 <= totalMemoryByHostList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex+3).getHost() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+3).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+3).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex+4 <= totalMemoryByHostList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex+4).getHost() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+4).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+4).getColumnValue())/1024/1024 )));
		   }
		   if(currentUserIndex+5 <= totalMemoryByHostList.size()-1) {
			   pieChartMemorySizes.getData().add(new PieChart.Data(totalMemoryByHostList.get(currentUserIndex+5).getHost() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+5).getColumnValue())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByHostList.get(currentUserIndex+5).getColumnValue())/1024/1024 )));
		   }
	}

	
	private void getDashBoardBarChart(ArrayList<InformationSchemaSizes> informationSchemaSizesList, BarChart barChartSchemaSizes,
			int currentSchemaIndex) {
		
		 barChartSchemaSizes.getData().clear();
		
		 XYChart.Series dataSeries1 = new XYChart.Series();
	     dataSeries1.setName("Disk Size");
	     
	     for(int i=0;i<informationSchemaSizesList.size();i++) {
	    	  dataSeries1.getData().add(new XYChart.Data(informationSchemaSizesList.get(i).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(i).getDiskSize())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(informationSchemaSizesList.get(i).getDiskSize())/1024/1024 )));
	     }
		 barChartSchemaSizes.getData().addAll(dataSeries1);
	}
	
	private void getMemoryUsageUserBarChart(ArrayList<TotalMemoryByUser> totalMemoryByUserList, BarChart barChartMemoryUsage,
			int currentSchemaIndex) {
		
		barChartMemoryUsage.getData().clear();
		XYChart.Series dataSeries1 = new XYChart.Series();
	    dataSeries1.setName("Disk Size");
	     
	    for(int i=0;i<totalMemoryByUserList.size();i++) {
	    	  dataSeries1.getData().add(new XYChart.Data(totalMemoryByUserList.get(i).getUser() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserList.get(i).getColumnValue())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByUserList.get(i).getColumnValue())/1024/1024 )));
	    }
	    barChartMemoryUsage.getData().addAll(dataSeries1);
	}
	
	private void getMemoryUsageHostBarChart(ArrayList<TotalMemoryByHost> totalMemoryByUserHostList, BarChart barChartMemoryUsage,
			int currentSchemaIndex) {
		
		barChartMemoryUsage.getData().clear();
		XYChart.Series dataSeries1 = new XYChart.Series();
	    dataSeries1.setName("Disk Size");
	     
	    for(int i=0;i<totalMemoryByUserHostList.size();i++) {
	    	  dataSeries1.getData().add(new XYChart.Data(totalMemoryByUserHostList.get(i).getHost() +" "+ get2digitDoublePrecisionValue(Double.valueOf(totalMemoryByUserHostList.get(i).getColumnValue())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(totalMemoryByUserHostList.get(i).getColumnValue())/1024/1024 )));
	    }
	    barChartMemoryUsage.getData().addAll(dataSeries1);
	}

	
	protected void getdatabasePropertiesDisplayTab(TreeItem<String> loadedDatabaseName,	Tab databaseDetailsPropertiesTab) {
		
		VBox databasePropertiesVBox = new VBox();
		databasePropertiesVBox.setId("databasePropertiesVBox");
//		databasePropertiesVBox.setSpacing(5);
//		databasePropertiesVBox.setPadding(new Insets(10,0,0,80));
		
		GridPane databasePropertiesGridPane = new GridPane();
		databasePropertiesGridPane.setId("databasePropertiesGridPane");
//		databasePropertiesGridPane.setPadding(new Insets(10,0,0,0));
//		databasePropertiesGridPane.setVgap(10);
//		databasePropertiesGridPane.setHgap(50);
		
		
		Label defaultCharSetLabel = new Label("Default Character Set : "+"" );
		defaultCharSetLabel.setId("Label1");
		//defaultCharSetLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(defaultCharSetLabel, 0, 0);   // column 0 row 0
		
		Label defaultCollationLabel = new Label("Default Collation Set : "+"" );
		defaultCollationLabel.setId("Label1");
		//defaultCollationLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(defaultCollationLabel, 1, 0);   // column 0 row 0
		
		Label defaultSqlPathLabel = new Label("Sql Path : "+"" );
		defaultSqlPathLabel.setId("Label1");
		//defaultSqlPathLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(defaultSqlPathLabel, 2, 0);   // column 0 row 0
		
		Label defaultEncrytptionLabel = new Label("Default Encryption : "+"" );
		defaultEncrytptionLabel.setId("Label1");
		//defaultEncrytptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(defaultEncrytptionLabel, 3, 0);   // column 0 row 0

		
		ArrayList<InformationSchemaSizes> informationSchemaSizesList = getAllSchemaSizesinDatabase();
		getSchemaDefaultProperties(loadedDatabaseName.getValue(),defaultCharSetLabel,defaultCollationLabel,defaultSqlPathLabel,defaultEncrytptionLabel);

		ArrayList<InformationSchemaTable> tableDetailsInformationSchemaList = getSchemaTablesFromInformationSchema(loadedDatabaseName.getValue());
		
		Label totalDiskUsageLabel = new Label("Disk Usage : "+getSchemaDiskUsage(tableDetailsInformationSchemaList)+" MB");
		totalDiskUsageLabel.setId("Label1");
		//totalDiskUsageLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(totalDiskUsageLabel, 0, 1);   // column 0 row 0

		String totalTables = String.valueOf( tableDetailsInformationSchemaList.size());
		String totalViews = getSchemaViewsCount(loadedDatabaseName.getValue());
		String totalIndexes = getSchemaIndexesCount(loadedDatabaseName.getValue());
		String totalProcedures = getSchemaProceduresCount(loadedDatabaseName.getValue());
		String totalFunctions = getSchemaFunctionsCount(loadedDatabaseName.getValue());
		String totalTriggers = getSchemaTriggersCount(loadedDatabaseName.getValue());
		String totalEvents = getSchemaEventsCount(loadedDatabaseName.getValue());
		
		String schemaPropertiesNames [] = {"Total Tables","Total Views","Total Indexdes","Total Procedures","Total Functions","Total Triggers","Total Events"};
		String schemaPropertiesValues[] = {totalTables,totalViews,totalIndexes,totalProcedures,totalFunctions,totalTriggers,totalEvents};
		
		
		Label totalTabelsLabel = new Label(schemaPropertiesNames[0]+" : "+totalTables);
		totalTabelsLabel.setId("Label1");
		//totalTabelsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(totalTabelsLabel, 1, 1);   // column 0 row 0
	
		Label totalViewsLabel = new Label(schemaPropertiesNames[1]+" : "+totalViews);
		totalViewsLabel.setId("Label1");
		//totalViewsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(totalViewsLabel, 2, 1);   // column 0 row 0
	
		Label totalIndexesLabel = new Label(schemaPropertiesNames[2]+" : "+totalIndexes);
		totalIndexesLabel.setId("Label1");
		//totalIndexesLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(totalIndexesLabel, 3, 1);   // column 0 row 0
		
		Label totalProceduresLabel = new Label(schemaPropertiesNames[3]+" Procedures : "+totalProcedures);
		totalProceduresLabel.setId("Label1");
		//totalProceduresLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(totalProceduresLabel, 0, 2);   // column 0 row 0
		
		Label totalFunctionsLabel = new Label(schemaPropertiesNames[4]+" : "+totalFunctions);
		totalFunctionsLabel.setId("Label1");
		//totalFunctionsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(totalFunctionsLabel, 1, 2);   // column 0 row 0
		
		Label totalTriggersLabel = new Label(schemaPropertiesNames[5]+" : "+totalTriggers);
		totalTriggersLabel.setId("Label1");
		//totalTriggersLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(totalTriggersLabel, 2, 2);   // column 0 row 0
		
		Label totalEventsLabel = new Label(schemaPropertiesNames[6]+" : "+totalEvents);
		totalEventsLabel.setId("Label1");
		//totalEventsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,14));
		GridPane.setConstraints(totalEventsLabel, 3, 2);   // column 0 row 0

		
		databasePropertiesGridPane.getChildren().addAll(defaultCharSetLabel,defaultCollationLabel,defaultSqlPathLabel,defaultEncrytptionLabel,totalDiskUsageLabel,totalTabelsLabel,totalViewsLabel,totalIndexesLabel,totalProceduresLabel
				,totalFunctionsLabel,totalTriggersLabel,totalEventsLabel);
		
		HBox databasePropertiesSchemaChartHbox = new HBox();
		databasePropertiesSchemaChartHbox.setId("databasePropertiesSchemaChartHbox");
		//databasePropertiesSchemaChartHbox.setSpacing(5);

		VBox pieOrBarChartVBox = new VBox();
		pieOrBarChartVBox.setId("pieOrBarChartVBox");
		//pieOrBarChartVBox.setSpacing(5);
		
		PieChart pieChartSchemaSizes = new PieChart();
		pieChartSchemaSizes.setId("chart");
		pieChartSchemaSizes.setTitle("Database size relative to other databases");
		PieChart.Data slice ;
		pieChartSchemaSizes.setMinWidth(400);
		
		CategoryAxis xAxis    = new CategoryAxis();
	    xAxis.setLabel("Databases");
	    NumberAxis yAxis = new NumberAxis();
	    yAxis.setLabel("Size (MB)");
	     
	     BarChart barChartScemaSizes = new BarChart(xAxis, yAxis);
	     barChartScemaSizes.setTitle("Database size relative to other databases");
	     barChartScemaSizes.setTitleSide(Side.TOP);
	     barChartScemaSizes.setBarGap(2);
	     barChartScemaSizes.setCategoryGap(15);
	     barChartScemaSizes.setHorizontalGridLinesVisible(false);
	     barChartScemaSizes.setVerticalGridLinesVisible(false);
	     
	     XYChart.Series dataSeries1 = new XYChart.Series();
	     dataSeries1.setName("Disk Size");
	     int counter = 0;
	     
	     InformationSchemaSizes informationSchemaSizes = new InformationSchemaSizes();
	     informationSchemaSizes.setSchemaName(loadedDatabaseName.getValue());
	     System.out.println("Schema position ---->"+informationSchemaSizesList.indexOf(informationSchemaSizes));
	     int currentSchemaIndex = informationSchemaSizesList.indexOf(informationSchemaSizes);
	     
	     if(currentSchemaIndex-2 >= 0) {
	    	 dataSeries1.getData().add(new XYChart.Data(informationSchemaSizesList.get(currentSchemaIndex-2).getSchemaName(),get2digitDoublePrecisionValue( Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-2).getDiskSize())/1024/1024 )));
	    	 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex-2).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-2).getDiskSize())/1024/1024)  + " MB",get2digitDoublePrecisionValue( Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-2).getDiskSize())/1024/1024 )));
	     }
	     if(currentSchemaIndex-1 >= 0) {
	    	 dataSeries1.getData().add(new XYChart.Data(informationSchemaSizesList.get(currentSchemaIndex-1).getSchemaName(), get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-1).getDiskSize())/1024/1024 )));
	    	 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex-1).getSchemaName() +" "+get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-1).getDiskSize())/1024/1024)  + " MB", get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-1).getDiskSize())/1024/1024 )));
	     }
		 dataSeries1.getData().add(new XYChart.Data(informationSchemaSizesList.get(currentSchemaIndex-0).getSchemaName(),get2digitDoublePrecisionValue( Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-0).getDiskSize())/1024/1024 )));
		 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex-0).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-0).getDiskSize())/1024/1024 ) + " MB",get2digitDoublePrecisionValue( Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex-0).getDiskSize())/1024/1024 )));
		 
		 if(currentSchemaIndex+1 <= informationSchemaSizesList.size()-1) {
			 dataSeries1.getData().add(new XYChart.Data(informationSchemaSizesList.get(currentSchemaIndex+1).getSchemaName(),get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+1).getDiskSize())/1024/1024 )));
			 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex+1).getSchemaName() +" "+get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+1).getDiskSize())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+1).getDiskSize())/1024/1024 )));
		 }
		 if(currentSchemaIndex+2 <= informationSchemaSizesList.size()-1) {
			 dataSeries1.getData().add(new XYChart.Data(informationSchemaSizesList.get(currentSchemaIndex+2).getSchemaName() ,get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+2).getDiskSize())/1024/1024 )));
			 pieChartSchemaSizes.getData().add(new PieChart.Data(informationSchemaSizesList.get(currentSchemaIndex+2).getSchemaName() +" "+ get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+2).getDiskSize())/1024/1024 ) + " MB", get2digitDoublePrecisionValue(Double.valueOf(informationSchemaSizesList.get(currentSchemaIndex+2).getDiskSize())/1024/1024 )));
		 }
	     barChartScemaSizes.setMinWidth(400);
	     barChartScemaSizes.getData().addAll(dataSeries1);
	     for (final  XYChart.Series series : (ObservableList<XYChart.Series>) barChartScemaSizes.getData()) {
	         for (final XYChart.Data<String, Double> data : ( ObservableList<XYChart.Data<String, Double>>)series.getData()) {
	             Tooltip tooltip = new Tooltip();
	             tooltip.setText(data.getXValue().toString() +" "+ 
	                          data.getYValue().toString());
	             Tooltip.install(data.getNode(), tooltip);
	         }
	     }
	     
		Collections.sort(tableDetailsInformationSchemaList, (obj1, obj2) -> {
			  InformationSchemaTable a = (InformationSchemaTable) obj1;
			  InformationSchemaTable b = (InformationSchemaTable) obj2;
			  
			  Integer aSize =  Integer.valueOf(a.getDataLength()) + Integer.valueOf(a.getIndexLength());
			  Integer bSize =  Integer.valueOf(b.getDataLength()) + Integer.valueOf(b.getIndexLength());
			  
			  if ( aSize > bSize ) return -1;
			  if (aSize < bSize) return 1;
			  return 0;
		});
		
		VBox tableSizesBarChartVBox = new VBox();
		tableSizesBarChartVBox.setSpacing(5);
		
		 CategoryAxis xAxis1    = new CategoryAxis();
	     xAxis1.setLabel("Tables");
	     NumberAxis yAxis1 = new NumberAxis();
	     yAxis1.setLabel("Size (MB)");
	     
	     BarChart   barChart = new BarChart(xAxis1, yAxis1);
	     barChart.setTitle("Top five tables by disk space");
	     barChart.setTitleSide(Side.TOP);
	     barChart.setBarGap(2);
	     barChart.setCategoryGap(15);
	     barChart.setHorizontalGridLinesVisible(false);
	     barChart.setVerticalGridLinesVisible(false);
	     
	     dataSeries1 = new XYChart.Series();
	     XYChart.Series dataSeries2 = new XYChart.Series();
	     XYChart.Series dataSeries3 = new XYChart.Series();
	     dataSeries1.setName("Index Size");
	     dataSeries2.setName("Data Size");
	     dataSeries3.setName("Total Size");
	     counter = 0;
	     for(InformationSchemaTable tableValues : tableDetailsInformationSchemaList) {
	    	
	    	 dataSeries1.getData().add(new XYChart.Data(tableValues.getTable_name(),get2digitDoublePrecisionValue((Double.valueOf(tableValues.getIndexLength())/1024/1024 ))));
	    	 dataSeries2.getData().add(new XYChart.Data(tableValues.getTable_name(),get2digitDoublePrecisionValue((Double.valueOf(tableValues.getDataLength()))/1024/1024 )));
	    	 dataSeries3.getData().add(new XYChart.Data(tableValues.getTable_name(),get2digitDoublePrecisionValue( (Double.valueOf(tableValues.getDataLength()) + Double.valueOf(tableValues.getIndexLength()))/1024/1024 )));
	    	 counter++;
	    	 if(counter >= 5) break;
		}
	     

	     
	    barChart.setMinWidth(400);
	    barChart.getData().addAll(dataSeries1,dataSeries2,dataSeries3);

	     for (final  XYChart.Series series : (ObservableList<XYChart.Series>) barChart.getData()) {
	         for (final XYChart.Data<String, Double> data : ( ObservableList<XYChart.Data<String, Double>>)series.getData()) {
	             Tooltip tooltip = new Tooltip();
	             tooltip.setText(data.getXValue().toString() +" "+ 
	                          data.getYValue().toString());
	             Tooltip.install(data.getNode(), tooltip);
	         }
	     }
	     
	    HBox moreChartshbox = new HBox();
	    moreChartshbox.setId("moreChartshbox");
	   // moreChartshbox.setPadding( new Insets(0,0,0,400));
	    moreChartsButton = new Button(menu_Items_FX.resourceBundle.getString("MoreCharts"));
	    moreChartsButton.setId("buttons");
	    moreChartsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				((TabPane) menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getContent()).getSelectionModel().select(2);
			}
		});
	    moreChartshbox.getChildren().add(moreChartsButton);
	    
	    showBarGraphButton =  new Button(menu_Items_FX.resourceBundle.getString("BarGraph"));
	    showBarGraphButton.setId("buttons");
	    pieOrBarChartVBox.getChildren().addAll(pieChartSchemaSizes,showBarGraphButton);
	    
	    showBarGraphButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				 pieOrBarChartVBox.getChildren().clear();
				 if(showBarGraphButton.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("BarGraph"))) {
				 	showBarGraphButton.setText(menu_Items_FX.resourceBundle.getString("PieGraph"));
				 	pieOrBarChartVBox.getChildren().addAll(barChartScemaSizes,showBarGraphButton);
				 }
				 else if(showBarGraphButton.getText().equalsIgnoreCase(menu_Items_FX.resourceBundle.getString("PieGraph"))) {
					 showBarGraphButton.setText(menu_Items_FX.resourceBundle.getString("BarGraph"));
					 pieOrBarChartVBox.getChildren().addAll(pieChartSchemaSizes,showBarGraphButton);
				 }
			}
		});
	    
	    tableSizesBarChartVBox.getChildren().addAll(barChart,moreChartshbox);
	    
	    databasePropertiesSchemaChartHbox.getChildren().addAll(pieOrBarChartVBox,tableSizesBarChartVBox);
		
	    databasePropertiesVBox.getChildren().addAll(databasePropertiesGridPane,databasePropertiesSchemaChartHbox);
		databaseDetailsPropertiesTab.setContent(databasePropertiesVBox);;
	}

	private Double get2digitDoublePrecisionValue(Double inputValue) {
		
		return 	Double.valueOf(decimalFormat.format(inputValue));
	}
	private ArrayList<InformationSchemaSizes> getAllSchemaSizesinDatabase() {
		
		ArrayList<InformationSchemaSizes> informationSchemaSizesList = new ArrayList<InformationSchemaSizes>();
		InformationSchemaSizes informationSchemaSizes ;
		try( ResultSet rsSchemaSizes = stmt.executeQuery("select table_schema , sum( data_length + index_length ) as diskSize from information_schema.tables where table_schema not in ('information_schema','performance_schema')  group by table_schema order by diskSize desc ")){	
			while(rsSchemaSizes.next()) {
				informationSchemaSizes = new InformationSchemaSizes();
				informationSchemaSizes.setSchemaName(rsSchemaSizes.getString(1));
				informationSchemaSizes.setDiskSize(rsSchemaSizes.getString(2));;
				informationSchemaSizesList.add(informationSchemaSizes);
			}
			}catch(Exception e) {
					e.printStackTrace();
		}
		
		return informationSchemaSizesList;
		
	}

	private void getSchemaDefaultProperties(String schemaName,Label defaultCharSetLabel, Label defaultCollationLabel,
			Label defaultSqlPathLabel, Label defaultEncrytptionLabel) {
		
		String indexesCount = "0";
		try( ResultSet rsSchemaProperties = currentConnection.createStatement().executeQuery("select default_character_set_name,default_collation_name,sql_path,default_encryption as indexCount from information_schema.schemata where schema_name = '"+schemaName+"'")){	
			if(rsSchemaProperties.next()) {
				defaultCharSetLabel.setText(defaultCharSetLabel.getText() + rsSchemaProperties.getString(1));
				defaultCollationLabel.setText(defaultCollationLabel.getText() + rsSchemaProperties.getString(2));
				defaultSqlPathLabel.setText(defaultSqlPathLabel.getText() + rsSchemaProperties.getString(3));
				defaultEncrytptionLabel.setText(defaultEncrytptionLabel.getText() +  rsSchemaProperties.getString(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	private String getSchemaEventsCount(String schemaName) {	
		String eventssCount = "0";
		try( ResultSet rsEventsCountinSchema = currentConnection.createStatement().executeQuery("select count(*) as eventsCount from information_schema.events where event_schema = '"+schemaName+"'")){	
			if(rsEventsCountinSchema.next()) {
				eventssCount = rsEventsCountinSchema.getString(1);
				System.out.println("Total Procedures in "+schemaName+" : " +rsEventsCountinSchema.getString(1));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return eventssCount;	
	}

	private String getSchemaTriggersCount(String schemaName) {
		
		String triggersCount = "0";
		try( ResultSet rsTriggerssCountinSchema = currentConnection.createStatement().executeQuery("select count(*) as triggersCount from information_schema.triggers where trigger_Schema = '"+schemaName+"'")){	
			if(rsTriggerssCountinSchema.next()) {
				triggersCount = rsTriggerssCountinSchema.getString(1);
				System.out.println("Total Procedures in "+schemaName+" : " +rsTriggerssCountinSchema.getString(1));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return triggersCount;		

	}
	
	private String getSchemaFunctionsCount(String schemaName) {
		
		String functionsCount = "0";
		try( ResultSet rsFunctionsCountinSchema = currentConnection.createStatement().executeQuery("select count(*) as functionsCount from information_schema.routines where  routine_type != 'PROCEDURE' and  routine_schema = '"+schemaName+"'")){	
			if(rsFunctionsCountinSchema.next()) {
				functionsCount = rsFunctionsCountinSchema.getString(1);
				System.out.println("Total Procedures in "+schemaName+" : " +rsFunctionsCountinSchema.getString(1));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return functionsCount;		
	}

	private String getSchemaProceduresCount(String schemaName) {
		
		String proceduresCount = "0";
		try( ResultSet rsProcedureCountinSchema = currentConnection.createStatement().executeQuery("select count(*) as procedureCount from information_schema.routines where  routine_type != 'FUNCTION' and  routine_schema = '"+schemaName+"'")){	
			if(rsProcedureCountinSchema.next()) {
				proceduresCount = rsProcedureCountinSchema.getString(1);
				System.out.println("Total Procedures in "+schemaName+" : " +rsProcedureCountinSchema.getString(1));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return proceduresCount;		
				
	}

	private String getSchemaIndexesCount(String schemaName) {
		
		String indexesCount = "0";
		try( ResultSet rsIndexesCountinSchema = currentConnection.createStatement().executeQuery("select count(*) as indexCount from information_schema.statistics where table_schema = '"+schemaName+"'")){	
			if(rsIndexesCountinSchema.next()) {
				indexesCount = rsIndexesCountinSchema.getString(1);
				System.out.println("Total Indexes in "+schemaName+" : " +rsIndexesCountinSchema.getString(1));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return indexesCount;
	}

	private String getSchemaViewsCount(String schemaName) {
		
		String viewsCount = "0";
		try( ResultSet rsViewsCountinSchema = currentConnection.createStatement().executeQuery("select count(*) as viewsCount from information_schema.views where table_schema = '"+schemaName+"'")){
			
			if(rsViewsCountinSchema.next()) {
				viewsCount = rsViewsCountinSchema.getString(1);
				System.out.println("Total Views in "+schemaName+" : " +rsViewsCountinSchema.getString(1));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return viewsCount;
	}

	private ArrayList<InformationSchemaTable> getSchemaTablesFromInformationSchema(String schemaName) {
		
		ArrayList<InformationSchemaTable> tableDetailsInformationSchemaList = new ArrayList<InformationSchemaTable>(); 
		InformationSchemaTable informationSchemaTable ;
		try( ResultSet rsTablesInformationSchema = currentConnection.createStatement().executeQuery("select table_name,engine,version,table_rows,"
				+ "avg_row_length,data_length,max_data_length,index_length,data_free,auto_increment from information_schema.tables where table_schema = '"+schemaName+"' and table_type = 'BASE TABLE'")){
			
			while(rsTablesInformationSchema.next()) {
				informationSchemaTable = new InformationSchemaTable();
				informationSchemaTable.setTable_name(rsTablesInformationSchema.getString(1));
				informationSchemaTable.setEngine(rsTablesInformationSchema.getString(2));
				informationSchemaTable.setEngineVersion(rsTablesInformationSchema.getString(3));
				informationSchemaTable.setTableRows(rsTablesInformationSchema.getString(4));
				informationSchemaTable.setAvgRowLength(rsTablesInformationSchema.getString(5));
				informationSchemaTable.setDataLength(rsTablesInformationSchema.getString(6));		
				informationSchemaTable.setMaxDataLength(rsTablesInformationSchema.getString(7));
				informationSchemaTable.setIndexLength(rsTablesInformationSchema.getString(8));
				informationSchemaTable.setDataFree(rsTablesInformationSchema.getString(9));
				informationSchemaTable.setAutoIncrement(rsTablesInformationSchema.getString(10));	
				tableDetailsInformationSchemaList.add(informationSchemaTable);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return tableDetailsInformationSchemaList;
	}
	private Double getSchemaDiskUsage(ArrayList<InformationSchemaTable> tableDetailsInformationSchemaList) {
	    
		Double totalDiskSize = 0d;
		for(InformationSchemaTable informationSchematable : tableDetailsInformationSchemaList) {
			
			totalDiskSize = totalDiskSize + Double.valueOf(informationSchematable.getDataLength()) + Double.valueOf(informationSchematable.getIndexLength()) ;
		}
		
		if(totalDiskSize != 0)
			return get2digitDoublePrecisionValue(totalDiskSize/1024/1024);
		else
			return get2digitDoublePrecisionValue(totalDiskSize);
	}

	private void getdatabaseEventsDisplayTab(TreeItem<String> loadedDatabaseName, Tab databaseDetailsEventsTab) {
		System.out.println("Events Tab selected ");
		  	try(ResultSet rs =  currentConnection.createStatement() .executeQuery("select  event_name,definer,event_type,interval_value,interval_field,starts,created,last_altered,last_executed from information_Schema.events where event_schema ='"+loadedDatabaseName.getValue()+"'");) 
		  	{		  
			    
			  SplitPane eventsDetailsSplitPane = new SplitPane();
			  eventsDetailsSplitPane.setOrientation(Orientation.VERTICAL);
			  eventsDetailsSplitPane.setDividerPositions(0.75); 
			  TableView tablesView = showResultSetInTheTableViewDoubleClick(rs,"Events",loadedDatabaseName.getValue());
			  tablesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
					@Override
					public void changed(ObservableValue<? extends HashMap<String, String>> observable,
							HashMap<String, String> oldValue, HashMap<String, String> newValue) {

						System.out.println("From Local oldValue --->"+oldValue);
						System.out.println("From local newValue --->"+newValue.keySet().toString());
						for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
							
							System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
						}
						TableViewSelectionModel  selectionModel = tablesView.getSelectionModel();
				        ObservableList selectedCells = selectionModel.getSelectedCells();
				        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
				        Object val = tablePosition.getTableColumn().getCellData(newValue);
				        System.out.println("Selected Value" + val);
					}	
			  });
			  
			  HBox allButtonsHBox = new HBox();
			  allButtonsHBox.setId("allButtonsHBox");
//			  allButtonsHBox.setSpacing(300);
//			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox eventsButtonsHbox = new HBox();
			  eventsButtonsHbox.setId("eventsButtonsHbox");
			 // eventsButtonsHbox.setSpacing(20);
			  
			  viewEventButton = new Button(menu_Items_FX.resourceBundle.getString("ViewEvent"));
			  viewEventButton.setId("buttons");
			  createEventButton = new Button(menu_Items_FX.resourceBundle.getString("CreateEvent"));
			  createEventButton.setId("buttons");
			  editEventButton = new Button(menu_Items_FX.resourceBundle.getString("EditEvent"));
			  editEventButton.setId("buttons");
			  deleteEventButton = new Button(menu_Items_FX.resourceBundle.getString("DeleteEvent"));
			  deleteEventButton.setId("buttons");
			  refreshEventsButton = new Button(menu_Items_FX.resourceBundle.getString("Refresh"));
			  refreshEventsButton.setId("buttons");
			  createEventButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					eventsDetailsSplitPane.getItems().remove(eventsButtonsHbox);
					eventsDetailsSplitPane.setDividerPositions(0.99);
				}	
			  });
			  
			  
			  eventsButtonsHbox.getChildren().addAll(viewEventButton,createEventButton,editEventButton,deleteEventButton);
			  allButtonsHBox.getChildren().addAll(eventsButtonsHbox,refreshEventsButton);
			  
			  eventsDetailsSplitPane.getItems().addAll(tablesView,allButtonsHBox);  
			  databaseDetailsEventsTab.setContent(eventsDetailsSplitPane);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	private void threadsafeAddTreeItem(TreeItem<String> mainTreeItem,TreeItem<String> toAddTreeItem) {
		
		Platform.runLater(() -> {
		    // Delay the modification slightly
			mainTreeItem.getChildren().add(toAddTreeItem);
		});
	}
	 
}


class EditingCell extends TableCell<Map, String> {
	 
    private TextField textField;

    public EditingCell() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(null);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
        
        textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, 
                Boolean arg1, Boolean arg2) {
                    if (!arg2) {  // Comment out this part then it will be editable but never persisted
                       //  commitEdit(textField.getText());  for now no need to commit any cell value
                    }
            }
        });
    }
    
    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
    
}

class InformationSchemaTable {
	
	private String table_name;
	private String engine;
	private String engineVersion;
	private String tableRows;
	private String avgRowLength;
	private String dataLength;
	private String maxDataLength;
	private String indexLength;
	private String dataFree;
	private String autoIncrement;
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getEngineVersion() {
		return engineVersion;
	}
	public void setEngineVersion(String engineVersion) {
		this.engineVersion = engineVersion;
	}
	public String getTableRows() {
		return tableRows;
	}
	public void setTableRows(String tableRows) {
		this.tableRows = tableRows;
	}
	public String getAvgRowLength() {
		return avgRowLength;
	}
	public void setAvgRowLength(String avgRowLength) {
		this.avgRowLength = avgRowLength;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getMaxDataLength() {
		return maxDataLength;
	}
	public void setMaxDataLength(String maxDataLength) {
		this.maxDataLength = maxDataLength;
	}
	public String getIndexLength() {
		return indexLength;
	}
	public void setIndexLength(String indexLength) {
		this.indexLength = indexLength;
	}
	public String getDataFree() {
		return dataFree;
	}
	public void setDataFree(String dataFree) {
		this.dataFree = dataFree;
	}
	public String getAutoIncrement() {
		return autoIncrement;
	}
	public void setAutoIncrement(String autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if( ((InformationSchemaTable)obj).table_name.equals(this.table_name) ) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.table_name.length();
	}
	
}

class InformationSchemaSizes{
	
	private String schemaName;
	private String diskSize;
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getDiskSize() {
		return diskSize;
	}
	public void setDiskSize(String diskSize) {
		this.diskSize = diskSize;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if( ((InformationSchemaSizes)obj).schemaName.equals(this.schemaName) ) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.schemaName.length();
	}
}

class TotalMemoryByUser{
	
	private String user;
	private String columnValue;
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	@Override
	public boolean equals(Object obj) {
		
		if( ((TotalMemoryByUser)obj).columnValue.equals(this.columnValue) ) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.columnValue.length();
	}
}

class TotalMemoryByHost{
	
	private String host;
	private String columnValue;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	@Override
	public boolean equals(Object obj) {
		
		if( ((TotalMemoryByHost)obj).columnValue.equals(this.columnValue) ) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.columnValue.length();
	}
}


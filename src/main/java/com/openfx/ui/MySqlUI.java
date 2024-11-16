package com.openfx.ui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.constants.MySQLConstants;
import com.openfx.handlers.NewMenuItemEventHandler;
import com.openfx.placeholders.ConnectionPlaceHolder;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	private ImageView imagemySqlnode;
	private Statement stmt ;
	public Button refreshButton;
	public TabPane statusSystemVariablesTabpane;
    public Tab statusVariablesTab;
	public Tab systemVariablesTab;
	  
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
	public Button refreshPerformanceButton  = new Button("Refresh");
	public Button exportPerformanceButton = new Button("Export");
	public Button performanceCopySelected = new Button("Copy Selected");
	
	public Button performanceCopyQuery = new Button("Copy Query");
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
	
	 
	public MySqlUI(Menu_Items_FX menu_Items_FX,NewMenuItemEventHandler newMenuItemEventHandler) {
		this.menu_Items_FX = menu_Items_FX;
		this.newMenuItemEventHandler = newMenuItemEventHandler;
	}

	public VBox addConnectionCredentials() {
		
		 VBox connectionDetailsVbox = new VBox();
		  
		  GridPane connectionUrlCredentialsGridPane = new GridPane();
		  connectionUrlCredentialsGridPane.setPadding(new Insets(20,10,20,10));
		  connectionUrlCredentialsGridPane.setVgap(5);
		  connectionUrlCredentialsGridPane.setHgap(5);
		  Label jdbcUrlgeneralLabel = new Label("General"); 
		  GridPane.setConstraints(jdbcUrlgeneralLabel, 0, 0);   // column 0 row 0
		  Label jdbcConnectionNameLabel= new Label("Name");
		  GridPane.setConstraints(jdbcConnectionNameLabel, 0, 1);
		  newMenuItemEventHandler.jdbcConnectionName = new TextField("local");
		  newMenuItemEventHandler.jdbcConnectionName.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcConnectionName.setPrefWidth(400);
		 // jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcConnectionName, 1, 1);
		  Label jdbcPortUrl = new Label("Port:");
		  GridPane.setConstraints(jdbcPortUrl, 2, 1);
		  newMenuItemEventHandler.jdbcConnectionPort = new TextField("3306");
		  newMenuItemEventHandler.jdbcConnectionPort.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcConnectionPort.setPrefWidth(40);
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcConnectionPort, 3, 1);
		  
		  Label jdbcUrlLabel = new Label("JDBC URL");
		  GridPane.setConstraints(jdbcUrlLabel, 0, 2);
		  newMenuItemEventHandler.jdbcUrlTextField = new TextField("jdbc:mysql://127.0.0.1");
		  newMenuItemEventHandler.jdbcUrlTextField.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcUrlTextField.setPrefWidth(400);
		  newMenuItemEventHandler.jdbcUrlTextField.setOnKeyTyped(newMenuItemEventHandler.onjdbcUrlTextFieldKeyPressed() );
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcUrlTextField, 1, 2);

		  Label jdbcUrlDatabaseNameLabel = new Label("Database ");
		  GridPane.setConstraints(jdbcUrlDatabaseNameLabel, 0, 3);
		  newMenuItemEventHandler.jdbcUrlDatabaseNameField = new TextField();
		  newMenuItemEventHandler.jdbcUrlDatabaseNameField.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcUrlDatabaseNameField.setPrefWidth(200);  
		  newMenuItemEventHandler.jdbcUrlDatabaseNameField.setOnKeyTyped(newMenuItemEventHandler.onjdbcUrlTextFieldKeyPressed() );
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcUrlDatabaseNameField, 1, 3);

		  
		  connectionUrlCredentialsGridPane.getChildren().addAll(jdbcConnectionNameLabel,newMenuItemEventHandler.jdbcConnectionName,jdbcUrlgeneralLabel,jdbcUrlLabel,newMenuItemEventHandler.jdbcConnectionPort,jdbcPortUrl,newMenuItemEventHandler.jdbcUrlTextField
				  ,jdbcUrlDatabaseNameLabel,newMenuItemEventHandler.jdbcUrlDatabaseNameField);
		  connectionDetailsVbox.getChildren().add(connectionUrlCredentialsGridPane);
		  
		  
		  GridPane connectionUsernamePasswordCredentialsGridPane = new GridPane();
		  connectionUsernamePasswordCredentialsGridPane.setPadding(new Insets(20,10,20,10));
		  connectionUsernamePasswordCredentialsGridPane.setVgap(5);
		  connectionUsernamePasswordCredentialsGridPane.setHgap(5);
		  Label jdbcUrlAuthenticationLabel = new Label("Authentication");
		  GridPane.setConstraints(jdbcUrlAuthenticationLabel, 0, 0);   // column 0 row 0
		  Label jdbcAuthenticationUsername = new Label("Username :");
		  GridPane.setConstraints(jdbcAuthenticationUsername, 0, 1);   // column 0 row 0
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField = new TextField("root");
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField.setPrefHeight(15);
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField.setPrefWidth(200);
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField.setOnKeyTyped(newMenuItemEventHandler.onjdbcAuthenticationUsernameTextFieldPressed());
		  GridPane.setConstraints(newMenuItemEventHandler.jdbcAuthenticationUsernameTextField, 1, 1);   // column 0 row 0
		  
		  Label jdbcAuthenticationPassword = new Label("Password");
		  GridPane.setConstraints(jdbcAuthenticationPassword, 0, 2);   // column 0 row 0
		  newMenuItemEventHandler.jdbcAuthenticationPasswordField = new PasswordField();
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
		TreeItem<String> mySqlTreeItemDatabases = new TreeItem<String>("Databases");
		
		TreeItem<String> loadingTreeItem = new TreeItem<String>("Loading..");
		mySqlTreeItemDatabases.getChildren().add(loadingTreeItem);
		
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
												mySqlTreeItemDatabases.getChildren().remove(0);  // Remove the Loading...
												while(rs.next()) {
													  System.out.println(rs.getString(1));
													  TreeItem<String> loadedDatabaseName = new TreeItem<String>(rs.getString(1));
													  
													 TreeItem<String> mySqlTreeItemTables = new TreeItem<String>("Tables",imagemySqlTablenode);
													 TreeItem<String> loadingTreeItemTables = new TreeItem<String>("Loading..");
													 mySqlTreeItemTables.getChildren().add(loadingTreeItemTables);
														
													 TreeItem<String> mySqlTreeItemViews = new TreeItem<String>("Views");
													 TreeItem<String> loadingTreeItemViews = new TreeItem<String>("Loading..");
													 mySqlTreeItemViews.getChildren().add(loadingTreeItemViews);
													 
													 TreeItem<String> mySqlTreeItemIndexes = new TreeItem<String>("Indexes");
													 TreeItem<String> loadingTreeItemIndexes = new TreeItem<String>("Loading..");
													 mySqlTreeItemIndexes.getChildren().add(loadingTreeItemIndexes);
													 
													 TreeItem<String> mySqlTreeItemProcedures = new TreeItem<String>("Procedures");
													 TreeItem<String> loadingTreeItemProcedures = new TreeItem<String>("Loading..");
													 mySqlTreeItemProcedures.getChildren().add(loadingTreeItemProcedures);
													 
													 TreeItem<String> mySqlTreeItemFunctions = new TreeItem<String>("Functions");
													 TreeItem<String> loadingTreeItemFunctions = new TreeItem<String>("Loading..");
													 mySqlTreeItemFunctions.getChildren().add(loadingTreeItemFunctions);
													 
													 TreeItem<String> mySqlTreeItemTriggers = new TreeItem<String>("Triggers");
													 TreeItem<String> loadingTreeItemTriggers = new TreeItem<String>("Loading..");
													 mySqlTreeItemTriggers.getChildren().add(loadingTreeItemTriggers);
													 
													 TreeItem<String> mySqlTreeItemEvents = new TreeItem<String>("Events");
													 TreeItem<String> loadingTreeItemEvents = new TreeItem<String>("Loading..");
													 mySqlTreeItemEvents.getChildren().add(loadingTreeItemEvents);
													  
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
																		    		mySqlTreeItemTables.getChildren().remove(0);  // Remove the Loading...
																					while(rs.next()) {
																						  System.out.println(rs.getString(1));
																						  
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
																						  tableColumns.getChildren().add(loadingTableNameColumns);
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
																											    		   tableColumns.getChildren().remove(0);  // Remove the Loading...
																															while(rs.next()) {
																																  System.out.println(rs.getString(1) + " , "+rs.getString(2));
																																  TreeItem<String> constraintsName = new TreeItem<String>(rs.getString(1) + " , "+rs.getString(2));
																																  tableColumns.getChildren().add(constraintsName);
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
																										tableColumns.getChildren().add(loadingTableNameColumns);
																									}
																								}
																						  });
																						  TreeItem<String> tableConstraints = new TreeItem<String>("Constaints");
																						  TreeItem<String> loadingTableNameConstraints = new TreeItem<String>("Loading..");
																						  tableConstraints.getChildren().add(loadingTableNameConstraints);
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
																													        tableConstraints.getChildren().remove(0);  // Remove the Loading...
																															while(rs.next()) {
																																  System.out.println(rs.getString(2) );
																																  // break down the show table result and get the primary key details
																																  
																																  String mysqlQuerySplit[] = rs.getString(2).split("\n");
																																	
																																	for(int i=0;i<mysqlQuerySplit.length;i++) {
																																		
																																		if(mysqlQuerySplit[i].contains("PRIMARY KEY")) {
																																		
																																			System.out.println( mysqlQuerySplit[i]);
																																			TreeItem<String> foreignKeysName = new TreeItem<String>(mysqlQuerySplit[i]);
																																			tableConstraints.getChildren().add(foreignKeysName);																	  
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
																										tableConstraints.getChildren().add(loadingTableNameConstraints);
																									}
																								}
																						  });
																						  TreeItem<String> tableForeignKeys = new TreeItem<String>("Foreign Keys");
																						  
																						  
																						  
																						  
																						  TreeItem<String> loadingTableNameForeignKeys = new TreeItem<String>("Loading..");
																						  tableForeignKeys.getChildren().add(loadingTableNameForeignKeys);
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
																													        tableForeignKeys.getChildren().remove(0);  // Remove the Loading...
																															while(rs.next()) {
																																  System.out.println(rs.getString(2) );
																																  // break down the show table result and get the foreigh key details
																																  
																																  String mysqlQuerySplit[] = rs.getString(2).split("\n");
																																	
																																	for(int i=0;i<mysqlQuerySplit.length;i++) {
																																		
																																		if(mysqlQuerySplit[i].contains("CONSTRAINT") || mysqlQuerySplit[i].contains("FOREIGN KEY")) {
																																		
																																			System.out.println( mysqlQuerySplit[i]);
																																			TreeItem<String> foreignKeysName = new TreeItem<String>(mysqlQuerySplit[i].replace("CONSTRAINT", ""));
																																			 tableForeignKeys.getChildren().add(foreignKeysName);
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
																						  tableReferences.getChildren().add(loadingTableNameReferences);
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
																												    	tableReferences.getChildren().remove(0);  // Remove the Loading...
																														while(rs.next()) {
																															System.out.println(rs.getString(2) );
																															// break down the show table result and get the foreigh key details
																																	  
																															String mysqlQuerySplit[] = rs.getString(2).split("\n");
																																		
																															for(int i=0;i<mysqlQuerySplit.length;i++) {
																																			
																															if(mysqlQuerySplit[i].contains("CONSTRAINT") || mysqlQuerySplit[i].contains("REFERENCES")) {
																																			
																																System.out.println( mysqlQuerySplit[i]);
																																TreeItem<String> foreignKeysName = new TreeItem<String>(mysqlQuerySplit[i].replace("CONSTRAINT", ""));
																																tableReferences.getChildren().add(foreignKeysName);
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
																										tableReferences.getChildren().add(loadingTableNameConstraints);
																									}
																								}
																						  });
																						  TreeItem<String> tableTriggers = new TreeItem<String>("Triggers");
																						  TreeItem<String> loadingTableNameTriggers = new TreeItem<String>("Loading..");
																						  tableTriggers.getChildren().add(loadingTableNameTriggers);
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
																												    	tableTriggers.getChildren().remove(0);  // Remove the Loading...
																														while(rs.next()) {
																															System.out.println(rs.getString(1) );
																															// break down the show table result and get the foreigh key details	
																															TreeItem<String> triggerName = new TreeItem<String>(rs.getString(1));
																															tableTriggers.getChildren().add(triggerName);
																															
																															 	
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
																										tableTriggers.getChildren().add(loadingTableNameConstraints);
																									}
																									
																								}
																						  });
																						  TreeItem<String> tableIndexes = new TreeItem<String>("Indexes");
																						  TreeItem<String> loadingTableNameIndexes = new TreeItem<String>("Loading..");
																						  tableIndexes.getChildren().add(loadingTableNameIndexes);
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
																												    	tableIndexes.getChildren().remove(0);  // Remove the Loading...
																														while(rs.next()) {
																															System.out.println(rs.getString(3)+ " , " + rs.getString(5));
																															// break down the show table result and get the foreigh key details	
																															TreeItem<String> indexName = new TreeItem<String>(rs.getString(3)+ " , " + rs.getString(5));
																															tableIndexes.getChildren().add(indexName);
																															
																															 	
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
																										tableIndexes.getChildren().add(loadingTableNameIndexes);
																									}
																								}
																						  });
																						  
																						  TreeItem<String> tablePartitions = new TreeItem<String>("Partitions");
																						  TreeItem<String> loadingTableNamePartitions = new TreeItem<String>("Loading..");
																						  tablePartitions.getChildren().add(loadingTableNamePartitions);
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
																												    	tablePartitions.getChildren().remove(0);  // Remove the Loading...
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
																																			tablePartitions.getChildren().add(partitionName);
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
																						  
																						  mySqlTreeItemTables.getChildren().add(loadedTableName);
																						
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
																		mySqlTreeItemTables.getChildren().add(loadingTreeItemTables);
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
																	    		stmt.execute("use "+loadedDatabaseName.getValue());
																	    		ResultSet rs = stmt.executeQuery("SHOW FULL TABLES IN "+ loadedDatabaseName.getValue() +" WHERE TABLE_TYPE LIKE 'VIEW'");
																	    		try {
																					Thread.sleep(1000);
																				} catch (InterruptedException e) {
																					// TODO Auto-generated catch block
																					e.printStackTrace();
																				}
																	    		mySqlTreeItemViews.getChildren().remove(0);  // Remove the Loading...
																				while(rs.next()) {
																					  System.out.println(rs.getString(1));
																					  
																					  TreeItem<String> viewName = new TreeItem<String>(rs.getString(1));
																					  TreeItem<String> viewNameLoading = new TreeItem<String>("Loading");
																					  viewName.getChildren().addAll(viewNameLoading);
																					  
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
																										    		   viewName.getChildren().remove(0);  // Remove the Loading...
																														while(rs.next()) {
																															  
																															  TreeItem<String> constraintsName = new TreeItem<String>(rs.getString(1)+","+rs.getString(2));
																															  viewName.getChildren().add(constraintsName);
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
																									viewName.getChildren().add(viewNameLoading);
																								}
																							}
																					  });
																					  
																					  
																					  mySqlTreeItemViews.getChildren().add(viewName);
																					
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
																	   mySqlTreeItemViews.getChildren().add(loadingTreeItemViews);
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
																			    	mySqlTreeItemIndexes.getChildren().remove(0);  // Remove the Loading...
																					while(rs.next()) {
																							
																						TreeItem<String> indexName = new TreeItem<String>(rs.getString(1)+ "." + rs.getString(2));
																						mySqlTreeItemIndexes.getChildren().add(indexName);
																						
																						 	
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
																	mySqlTreeItemIndexes.getChildren().add(loadingTreeItemIndexes);
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
																	    		mySqlTreeItemProcedures.getChildren().remove(0);  // Remove the Loading...
																				while(rs.next()) {
																					  
																					  TreeItem<String> procedureName = new TreeItem<String>(rs.getString(2));
																					  
																					  ResultSet rsProcedures = currentConnection.createStatement().executeQuery("select parameter_mode,parameter_name,data_type,character_maximum_length,dtd_identifier from information_schema.parameters where specific_name='"+ rs.getString(2) +"' and  specific_schema = '"+loadedDatabaseName.getValue()+"'");
																					  // Write logic to derive IN and OUT Paramaeters
																					  																																		  
																					  TreeItem<String> procedureParametersIN = new TreeItem<String>("Parameters [IN]");
																					  TreeItem<String> procedureParametersOUT = new TreeItem<String>("Parameters [OUT]");
																					  
																					  while(rsProcedures.next()){
																						  
																						  if(rsProcedures.getString(1).equals("IN")) {
																							  TreeItem<String> procedureParameterIN = new TreeItem<String>(rsProcedures.getString(2)+","+rsProcedures.getString(5));
																							  procedureParametersIN.getChildren().add(procedureParameterIN);
																						  }
																						  if(rsProcedures.getString(1).equals("OUT")) {
																							  TreeItem<String> procedureParameterOUT = new TreeItem<String>(rsProcedures.getString(2)+","+rsProcedures.getString(5));
																							  procedureParametersOUT.getChildren().add(procedureParameterOUT);
																						  }
																					  }
																					  
																					  procedureName.getChildren().addAll(procedureParametersIN,procedureParametersOUT);
																					
																					  mySqlTreeItemProcedures.getChildren().add(procedureName);
																					
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
																	mySqlTreeItemProcedures.getChildren().add(loadingTreeItemProcedures);
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
																	    		mySqlTreeItemFunctions.getChildren().remove(0);  // Remove the Loading...
																	    		while(rs.next()) {
																					  
																					  TreeItem<String> functionName = new TreeItem<String>(rs.getString(2));
																					  
																					  ResultSet rsFunctions = currentConnection.createStatement().executeQuery("select parameter_mode,parameter_name,data_type,character_maximum_length,dtd_identifier from information_schema.parameters where specific_name='"+ rs.getString(2) +"' and  specific_schema = '"+loadedDatabaseName.getValue()+"'");
																					  // Write logic to derive IN and OUT Paramaeters
																					  																																		  
																					  TreeItem<String> functionsParametersIN = new TreeItem<String>("Parameters [IN]");
																					  TreeItem<String> functionsParametersOUT = new TreeItem<String>("Parameters [RETURN]");
																					  
																					  while(rsFunctions.next()){
																						  
																						  if(rsFunctions.getString(1) != null &&  rsFunctions.getString(1).equals("IN")) {
																							  TreeItem<String> procedureParameterIN = new TreeItem<String>(rsFunctions.getString(2)+","+rsFunctions.getString(5));
																							  functionsParametersIN.getChildren().add(procedureParameterIN);
																						  }
																						  if(rsFunctions.getString(1) == null ) {
																							  TreeItem<String> procedureParameterOUT = new TreeItem<String>("RETURN,"+rsFunctions.getString(5));
																							  functionsParametersOUT.getChildren().add(procedureParameterOUT);
																						  }
																					  }
																					  
																					  functionName.getChildren().addAll(functionsParametersIN,functionsParametersOUT);
																					
																					  mySqlTreeItemFunctions.getChildren().add(functionName);																					
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
																mySqlTreeItemFunctions.getChildren().add(loadingTreeItemFunctions);
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
																    		mySqlTreeItemTriggers.getChildren().remove(0);  // Remove the Loading...
																			while(rs.next()) {
																				  System.out.println(rs.getString(1));
																				  TreeItem<String> triggerName = new TreeItem<String>(rs.getString(3));
																				  mySqlTreeItemTriggers.getChildren().add(triggerName);																				
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
																   mySqlTreeItemTriggers.getChildren().add(loadingTreeItemTriggers);
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
																	    		mySqlTreeItemEvents.getChildren().remove(0);  // Remove the Loading...
																				while(rs.next()) {
																					  System.out.println(rs.getString(1));
																					  TreeItem<String> ViewName = new TreeItem<String>(rs.getString(2));
																					  mySqlTreeItemEvents.getChildren().add(ViewName);
																					
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
																	   mySqlTreeItemEvents.getChildren().add(loadingTreeItemViews);
																 }
															}
													 });
													 
													 // ---------------END------------------
													 
													 // Add the Databse to the Databse tree
													 mySqlTreeItemDatabases.getChildren().add(loadedDatabaseName);
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
							mySqlTreeItemDatabases.getChildren().add(loadingTreeItem);
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
		TreeItem<String> mySqlTreeItemAdminister = new TreeItem<String>("Administer");
		
		TreeItem<String> mySqlTreeItemAdministration = new TreeItem<String>("Administration");
		mySqlTreeItemAdminister.getChildren().add(mySqlTreeItemAdministration);
		TreeItem<String> mySqlTreeItemAdministerServerStatus = new TreeItem<String>("Server Status");
		mySqlTreeItemAdministration.getChildren().add(mySqlTreeItemAdministerServerStatus);
		TreeItem<String> mySqlTreeItemAdministerClientConnectionss = new TreeItem<String>("Client Connections");
		mySqlTreeItemAdministration.getChildren().add(mySqlTreeItemAdministerClientConnectionss);
		TreeItem<String> mySqlTreeItemAdministerUserandPrivileges = new TreeItem<String>("Users and Privileges");
		mySqlTreeItemAdministration.getChildren().add(mySqlTreeItemAdministerUserandPrivileges);
		TreeItem<String> mySqlTreeItemAdministerStatusandSystemVariables = new TreeItem<String>("Status and System Variables");
		mySqlTreeItemAdministration.getChildren().add(mySqlTreeItemAdministerStatusandSystemVariables);
		
		TreeItem<String> mySqlTreeItemPerformance = new TreeItem<String>("Performance");
		mySqlTreeItemAdminister.getChildren().add(mySqlTreeItemPerformance);
		TreeItem<String> mySqlTreeItemAdministerDashboard = new TreeItem<String>("Dashboard");
		mySqlTreeItemPerformance.getChildren().add(mySqlTreeItemAdministerDashboard);
		TreeItem<String> mySqlTreeItemAdministerPerformanceReports = new TreeItem<String>("Performance Reports");
		mySqlTreeItemPerformance.getChildren().add(mySqlTreeItemAdministerPerformanceReports);
		
		TreeItem<String> mySqlTreeItemServer = new TreeItem<String>("Server");
		mySqlTreeItemAdminister.getChildren().add(mySqlTreeItemServer);
		TreeItem<String> mySqlTreeItemAdministerServerLogs = new TreeItem<String>("Server Logs");
		mySqlTreeItemServer.getChildren().add(mySqlTreeItemAdministerServerLogs);
		
		mySqlTreeItemAdminister.expandedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					
				Boolean value = ((BooleanProperty)observable).getValue() ;
				System.out.println("Its Administer expansion!!!"); 								

					
				 }
		 });
		
		// System Info
		TreeItem<String> mySqlTreeItemSystemInfo = new TreeItem<String>("System Info");
		TreeItem<String> mySqlTreeItemSystemInfoBinaryLogs = new TreeItem<String>("BINARY LOGS");  // This will show Binary Log EVENTS
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoBinaryLogs);
		TreeItem<String> mySqlTreeItemSystemInfoCharacterSet = new TreeItem<String>("CHARACTER SET");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoCharacterSet);
		TreeItem<String> mySqlTreeItemSystemInfoCollation = new TreeItem<String>("COLLATION");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoCollation);
		TreeItem<String> mySqlTreeItemSystemInfoEngines = new TreeItem<String>("ENGINES");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoEngines);
		TreeItem<String> mySqlTreeItemSystemInfoErrors = new TreeItem<String>("ERRORS");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoErrors);
		TreeItem<String> mySqlTreeItemSystemInfoEvents = new TreeItem<String>("EVENTS");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoEvents);
		//TreeItem<String> mySqlTreeItemSystemInfoGrants = new TreeItem<String>("GRANTS");
		//mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoGrants);
		TreeItem<String> mySqlTreeItemSystemInfoOpenTables = new TreeItem<String>("OPEN TABLES");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoOpenTables);
		TreeItem<String> mySqlTreeItemSystemInfoPlugins = new TreeItem<String>("PLUGINS");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoPlugins);
		TreeItem<String> mySqlTreeItemSystemInfoPreviliges = new TreeItem<String>("PRIVILEGES");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoPreviliges);
		TreeItem<String> mySqlTreeItemSystemInfoProcessList = new TreeItem<String>("PROCESS LIST");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoProcessList);
		TreeItem<String> mySqlTreeItemSystemInfoProfiles = new TreeItem<String>("PROFILES");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoProfiles);
		TreeItem<String> mySqlTreeItemSystemInfoReplicas = new TreeItem<String>("REPLICAS");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoReplicas);
		TreeItem<String> mySqlTreeItemSystemInfoSessionStatus = new TreeItem<String>("SESSION STATUS");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoSessionStatus);
		TreeItem<String> mySqlTreeItemSystemInfoGlobalStatus = new TreeItem<String>("GLOBAL STATUS");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoGlobalStatus);
		TreeItem<String> mySqlTreeItemSystemInfoSessionVariables = new TreeItem<String>("SESSION VARIABLES");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoSessionVariables);
		TreeItem<String> mySqlTreeItemSystemInfoGlobalVariables = new TreeItem<String>("GLOBAL VARIABLES");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoGlobalVariables);
		TreeItem<String> mySqlTreeItemSystemInfoWarnings = new TreeItem<String>("WARNINGS");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoWarnings);
		
		
		mySqlTreeItem.getChildren().add(mySqlTreeItemDatabases);
	//	mySqlTreeItem.getChildren().add(mySqlTreeItemUsers);
		mySqlTreeItem.getChildren().add(mySqlTreeItemAdminister);
		mySqlTreeItem.getChildren().add(mySqlTreeItemSystemInfo);
				
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
				 
				 if(event.getClickCount() == 2) {
					 
					 // check if database tab is already opened.
					 if(!menu_Items_FX.alltabbedEditors.getTabs().isEmpty())
						 if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() != null)
							 System.out.println("Current Tab opened is---> "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().toString() + " and  "  + menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText());
					 
					 // particular database is clicked
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase("Databases")) {
							
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
    	            	return;
					 }
					 if(getTreeItem().getParent().getValue().equalsIgnoreCase("Tables")) {
						
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
				 
				 if(event.getClickCount() == 2) {
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
											if (getTreeItem().getValue().equalsIgnoreCase("BINARY LOGS") || getTreeItem().getValue().equalsIgnoreCase("Server Logs")) {
												resultAsTableView = showResultSetInTheTableView(rs,getTreeItem().getValue());
												topHalfResultTableView.getChildren().addAll(addTopHBoxForInfo(getTreeItem().getValue()),resultAsTableView);
												((SplitPane) genericNode).getItems().addAll(topHalfResultTableView,secondHalfDisplayVBox); // Top half of query editer							      
										    
											}
											else if(getTreeItem().getValue().equals("EVENTS") && getTreeItem().getParent().getValue().equalsIgnoreCase("System Info")) {
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
											
											mainDisplayTab.setText(getTreeItem().getValue() + connectionPlaceHolder.getConnectionName());
											
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
					 
				 }
				 
				 // This check is skeptical make it more error proof.
				 if(event.getClickCount() == 1) {
					 displayPerformanceTableView(performanceReportsTypes, performanceReportQueries,getTreeItem().getValue());
					 
				 }
				 /*
				 if(event.getClickCount() == 2 && (getTreeItem().getValue().equalsIgnoreCase("BINARY LOGS") || getTreeItem().getValue().equalsIgnoreCase("Server Logs"))) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	ResultSet rs = stmt.executeQuery("SHOW BINARY LOGS");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
							    	
							    	secondHalfDisplayVBox = new VBox();
									Tab sessionManagerTab = new Tab();
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs,"binaryLogs");
									VBox binaryLogsDescriptionTableView = new VBox();
									if (getTreeItem().getValue().equalsIgnoreCase("BINARY LOGS")) {
										sessionManagerTab.setText("BINARY LOGS " + connectionPlaceHolder.getConnectionName());
										binaryLogsDescriptionTableView.getChildren().addAll(addTopHBoxForBinaryLogs("Binary"),resultAsTableView);
									}
									else if (getTreeItem().getValue().equalsIgnoreCase("Server Logs")) {
										sessionManagerTab.setText("Server Logs " + connectionPlaceHolder.getConnectionName());
										binaryLogsDescriptionTableView.getChildren().addAll(addTopHBoxForBinaryLogs("Server"),resultAsTableView);
									}
									
									
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.37);  // split pane divider moving a bit lower
							    
							        editerTabSplitPane.getItems().addAll(binaryLogsDescriptionTableView); // Top half of query editer
							      
							        editerTabSplitPane.getItems().add(secondHalfDisplayVBox); // bottom half of query editer
							        
							        
									sessionManagerTab.setContent(editerTabSplitPane);
									menu_Items_FX.alltabbedEditors.getTabs().add(sessionManagerTab);

							        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							        singleSelectionModel.select(sessionManagerTab);
							        
								} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							  }
							});		
				 } *
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("CHARACTER SET")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	ResultSet rs = stmt.executeQuery("SHOW CHARACTER SET");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}

									Tab sessionManagerTab = new Tab("CHARACTER SET " + connectionPlaceHolder.getConnectionName());
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
									menu_Items_FX.alltabbedEditors.getTabs().add(sessionManagerTab);

							        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							        singleSelectionModel.select(sessionManagerTab);
							        
								} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							  }
							});		
				 }/*
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("COLLATION")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	ResultSet rs = stmt.executeQuery("SHOW COLLATION");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}

									Tab sessionManagerTab = new Tab("COLLATION " + connectionPlaceHolder.getConnectionName());
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("ENGINES")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	ResultSet rs = stmt.executeQuery("SHOW ENGINES");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("ENGINES " + connectionPlaceHolder.getConnectionName());
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});						
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 // This can be mage a generic code think about it.
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("ERRORS")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	ResultSet rs = stmt.executeQuery("SHOW ERRORS");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("ERRORS " + connectionPlaceHolder.getConnectionName());
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});						
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("EVENTS")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	TableView resultAsTableView = new TableView();
							    	ResultSet rsDatabases = stmt.executeQuery("SHOW DATABASES");
							    	while(rsDatabases.next()){
							    		System.out.println("Executing query "+"SHOW EVENTS FROM "+rsDatabases.getString(1));
							    		stmt = innercurrentConnection.createStatement();
							    		ResultSet rs = stmt.executeQuery("SHOW EVENTS FROM "+rsDatabases.getString(1));
							    		resultAsTableView = showResultSetInTheTableView(rs,resultAsTableView);
							    	}
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("EVENTS " + connectionPlaceHolder.getConnectionName());
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});						
								
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("GRANTS")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	TableView resultAsTableView = new TableView();
							    	ResultSet rsUserDetails = stmt.executeQuery("SELECT USER,HOST FROM MYSQL.USER");
							    	while(rsUserDetails.next()){
							    		System.out.println("Executing query "+"SHOW GRANTS FOR '"+rsUserDetails.getString(1)+"'@'"+rsUserDetails.getString(2)+"'");
							    		stmt = innercurrentConnection.createStatement();
							    		ResultSet rs = stmt.executeQuery("SHOW GRANTS FOR '"+rsUserDetails.getString(1)+"'@'"+rsUserDetails.getString(2)+"'");
							    		resultAsTableView = showResultSetInTheTableView(rs,resultAsTableView);
							    	}
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("GRANTS " + connectionPlaceHolder.getConnectionName());
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});						
								
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("OPEN TABLES")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	ResultSet rs = stmt.executeQuery("SHOW OPEN TABLES");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("OPEN TABLES " + connectionPlaceHolder.getConnectionName());
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});						
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("PLUGINS")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	ResultSet rs = stmt.executeQuery("SHOW PLUGINS");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("PLUGINS " + connectionPlaceHolder.getConnectionName());
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});						
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("PRIVILEGES")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	ResultSet rs = stmt.executeQuery("SHOW PRIVILEGES");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("PRIVILEGES " + connectionPlaceHolder.getConnectionName());
									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});						
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("PROCESS LIST")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	ResultSet rs = stmt.executeQuery(" SHOW PROCESSLIST");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("Session/Process List " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("PROFILES")) { // display individual profile  
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	ResultSet rs = stmt.executeQuery(" SHOW PROFILES");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("PROFILES " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("REPLICAS")) { // display individual replica
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	ResultSet rs = stmt.executeQuery(" SHOW REPLICAS");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("REPLICAS " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("SESSION STATUS")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	ResultSet rs = stmt.executeQuery(" SHOW SESSION STATUS");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("SESSION " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("GLOBAL STATUS")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	ResultSet rs = stmt.executeQuery(" SHOW GLOBAL STATUS");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("GLOBAL " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("SESSION VARIABLES")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	ResultSet rs = stmt.executeQuery(" SHOW SESSION VARIABLES");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("SESSION VARIABLES " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("GLOBAL VARIABLES")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	ResultSet rs = stmt.executeQuery(" SHOW GLOBAL VARIABLES");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("GLOBAL VARIABLES " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
									sessionManagerTab.setContent(editerTabSplitPane);
									menu_Items_FX.alltabbedEditors.getTabs().add(sessionManagerTab);

							        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
							        singleSelectionModel.select(sessionManagerTab);
							        
								} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
							  }
							});		
				 }*/
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Dashboard")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	
									Tab sessionManagerTab = new Tab("Dashboard " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									PieChart pieChart = new PieChart();

								    PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
								    PieChart.Data slice2 = new PieChart.Data("Phone"  , 67);
								    PieChart.Data slice3 = new PieChart.Data("Tablet" , 36);

								    pieChart.getData().add(slice1);
								    pieChart.getData().add(slice2);
								    pieChart.getData().add(slice3);
								    
								    
								    
								    NumberAxis xAxis = new NumberAxis();
							        xAxis.setLabel("No of employees");

							        NumberAxis yAxis = new NumberAxis();
							        yAxis.setLabel("Revenue per employee");

							        LineChart lineChart = new LineChart(xAxis, yAxis);

							        XYChart.Series dataSeries1 = new XYChart.Series();
							        dataSeries1.setName("2014");

							        dataSeries1.getData().add(new XYChart.Data( 1, 567));
							        dataSeries1.getData().add(new XYChart.Data( 5, 612));
							        dataSeries1.getData().add(new XYChart.Data(10, 800));
							        dataSeries1.getData().add(new XYChart.Data(20, 480));
							        dataSeries1.getData().add(new XYChart.Data(40, 810));
							        dataSeries1.getData().add(new XYChart.Data(60, 110));
							        dataSeries1.getData().add(new XYChart.Data(80, 850));

							        lineChart.getData().add(dataSeries1);
							        
							        CategoryAxis xAxis1    = new CategoryAxis();
							        xAxis.setLabel("Devices");

							        NumberAxis yAxis1 = new NumberAxis();
							        yAxis.setLabel("Visits");

							        BarChart     barChart = new BarChart(xAxis1, yAxis1);

							        XYChart.Series dataSeries2 = new XYChart.Series();
							        dataSeries1.setName("2014");

							        dataSeries2.getData().add(new XYChart.Data("Desktop", 567));
							        dataSeries2.getData().add(new XYChart.Data("Phone"  , 65));
							        dataSeries2.getData().add(new XYChart.Data("Tablet"  , 23));

							        barChart.getData().add(dataSeries2);
							        
								    VBox piechartvbox = new VBox();
								    piechartvbox.setSpacing(10);
								    piechartvbox.getChildren().addAll(pieChart,lineChart);
								    
								    sessionManagerTab.setContent(piechartvbox);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Performance Reports")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	
							    	System.out.println("Connection Name :"+ connectionName);
							    	
									Tab sessionManagerTab = new Tab("Performance Reports " + connectionPlaceHolder.getConnectionName());									
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
									vboxLeft.setPadding(new Insets(5,5,5,5));
									vboxLeft.setSpacing(5);
									Label performanceReportsLabel = new Label("Reports");
									//userAccountsLabel.setTextFill(Color.BLUEVIOLET);
									vboxLeft.getChildren().add(performanceReportsLabel);
									vboxLeft.getChildren().add(performanceView);
									
									VBox vBoxCenterTop = new VBox();
									vBoxCenterTop.setPadding(new Insets(5,5,5,5));
									vBoxCenterTop.setSpacing(5);
									particularPerformanceReportLabel =  new Label("Nothing Selected");
									//Label particularPerformanceReportDescription =  new Label("Shows total memory allowed");
									//detailForAccountLabel.setTextFill(Color.BLUEVIOLET);
									
									
								    performanceReportTableView = new TableView();
									performanceReportTableView.setMinHeight(menu_Items_FX.size.getHeight() - 300);
									
									HBox hboxPerformanceButtons = new HBox();
									hboxPerformanceButtons.setSpacing(400);
									
									HBox hboxPerformanceButtonsLeft = new HBox();
									hboxPerformanceButtonsLeft.setSpacing(10);
									hboxPerformanceButtonsLeft.setPadding(new Insets(10,10,10,10));
									
									
									hboxPerformanceButtonsLeft.getChildren().addAll(exportPerformanceButton,performanceCopySelected,performanceCopyQuery);
									
									HBox hboxPerformanceButtonsRight = new HBox();
									hboxPerformanceButtonsRight.setPadding(new Insets(10,10,10,10));
									
									hboxPerformanceButtonsRight.getChildren().add(refreshPerformanceButton);
									
									hboxPerformanceButtons.getChildren().addAll(hboxPerformanceButtonsLeft,hboxPerformanceButtonsRight);
									
									vBoxCenterTop.getChildren().addAll(particularPerformanceReportLabel,performanceReportTableView,hboxPerformanceButtons);
									
									performanceReportsBorderPane.setTop(addTopHBoxForInfo("Performance Reports"));
									performanceReportsBorderPane.setLeft(vboxLeft);
									performanceReportsBorderPane.setCenter(vBoxCenterTop);

									
									
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Server Status")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
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
							    	
							    	
									Tab sessionManagerTab = new Tab("SERVER STATUS " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Client Connections")) { 
				      System.out.println("Double clicked on this item"+ getTreeItem().getValue());
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
							    	
									Tab sessionManagerTab = new Tab("Client Connections " + connectionPlaceHolder.getConnectionName());									
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Users and Privileges")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	
							    	ResultSet rsUsers = stmt.executeQuery("select User,Host from mysql.user");
							    
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	
							    	System.out.println("Connection Name :"+ connectionName);
							    	
									Tab sessionManagerTab = new Tab("Users and Privileges " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									
									TableView resultAsTableView =  showResultSetInTheTableView(rsUsers);
									// Can move this to invidual invocations as each call of this will ha`ve custom usage based on selection 
									resultAsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {
									

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
												while(rsfullUserDetails.next()) {
													
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
													
													// setting the addSchemaPriviliges below
													selectSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Select_priv").equals("Y"));
													updateSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Update_priv").equals("Y"));
													insertSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Insert_priv").equals("Y"));
													showViewSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Show_view_priv").equals("Y"));
													deleteSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Delete_priv").equals("Y"));
													executeSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Execute_priv").equals("Y"));
													
													createSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_priv").equals("Y"));
													alterSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Alter_priv").equals("Y"));	
													referencesSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("References_priv").equals("Y"));
													indexSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Index_priv").equals("Y"));
													createViewSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_view_priv").equals("Y"));
													createRoutineSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_routine_priv").equals("Y")); 
													
													alterRoutineSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Alter_routine_priv").equals("Y"));
													eventSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Event_priv").equals("Y"));
													dropSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Drop_priv").equals("Y"));
													triggerSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Trigger_priv").equals("Y"));
													grantOptionSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Grant_priv").equals("Y"));
													createTemporaryTablesSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Create_tmp_table_priv").equals("Y")); 
													lockTablesSchemaPrivilegeCheckBox.setSelected(rsfullUserDetails.getString("Lock_tables_priv").equals("Y"));
													
												}
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
									        
										}	
									});

									BorderPane userAccountsBorderPane = new BorderPane();
									VBox vboxLeft = new VBox();
									vboxLeft.setPadding(new Insets(5,5,5,5));
									vboxLeft.setSpacing(5);
									Label userAccountsLabel = new Label("User Accounts");
									//userAccountsLabel.setTextFill(Color.BLUEVIOLET);
									vboxLeft.getChildren().add(userAccountsLabel);
									vboxLeft.getChildren().add(resultAsTableView);
									
									HBox hBox = new HBox();
									hBox.setPadding(new Insets(5,5,5,5));
									Button addAccountButton = new Button("Add Account");
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
									Button deletetButton = new Button("Delete");
									deletetButton.setMinWidth(80);
									Button refreshButton = new Button("Refresh");
									refreshButton.setMinWidth(80);
									hBox.getChildren().add(addAccountButton);
									hBox.getChildren().add(deletetButton);
									hBox.getChildren().add(refreshButton);
									hBox.setSpacing(20);
									vboxLeft.getChildren().add(hBox);
								
									VBox vBoxCenterTop = new VBox();
									vBoxCenterTop.setPadding(new Insets(5,5,5,5));
									vBoxCenterTop.setSpacing(5);
									Label detailForAccountLabel =  new Label("Details for Account - xxxx");
									//detailForAccountLabel.setTextFill(Color.BLUEVIOLET);
									vBoxCenterTop.getChildren().add(detailForAccountLabel);
									
									TabPane accountDetailsTabs = new TabPane();
									accountDetailsTabs.getStyleClass().addAll("databasesflowPane");  // box for the connection tabbed pane
									Tab loginTab = new Tab("Login");
									loginTab.setClosable(false);  
									loginTab.setContent(addAccountLoginCredentials()); // pass the user details here as parameterand set them
									Tab accountLimitsTab = new Tab("Account Limits");
									accountLimitsTab.setClosable(false);	
									accountLimitsTab.setContent(addAccountLimits());
									Tab accountPrivilegesTab = new Tab("Account Privileges");
									accountPrivilegesTab.setClosable(false);
									accountPrivilegesTab.setContent(addAccountPrivileges());
									Tab schemaPrivilegesTab = new Tab("Schema Privileges");
									schemaPrivilegesTab.setClosable(false);
									schemaPrivilegesTab.setContent(addSchemaPrivileges());
									
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
				 if(event.getClickCount() == 2 && (getTreeItem().getValue().equalsIgnoreCase("Status and System Variables") || getTreeItem().getValue().equalsIgnoreCase("SESSION STATUS")
						 || getTreeItem().getValue().equalsIgnoreCase("GLOBAL STATUS") || getTreeItem().getValue().equalsIgnoreCase("SESSION VARIABLES") || getTreeItem().getValue().equalsIgnoreCase("GLOBAL VARIABLES"))) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
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
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									SplitPane editerTabSplitPane = new SplitPane();
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
	
	    	System.out.println("First calumne "+rs.getString(1));
	    	TableColumn<Map, String> tableColumnName;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	       
	        
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnLabel(i+1);
	        	System.out.println("Column Name : "+columnNames[i]);
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
				System.out.println("newValue --->"+newValue.keySet().toString());
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
					hboxDescriptionValue.setPadding(new Insets(10,10,10,30));
					
					HBox hboxDescriptionhBox= new HBox();
					hboxDescriptionhBox.setSpacing(10);
					hboxDescriptionhBox.setPadding(new Insets(20,10,20,30));
					Label hboxDescriptionLabel = new Label("Description: ");
					hboxDescriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));					
					Label hboxDescriptionValueLabel = new Label(newValue.get("Description"));
					hboxDescriptionhBox.getChildren().addAll(hboxDescriptionLabel,hboxDescriptionValueLabel);
					
					
					Label statusVariableDescriptionLabel = new Label("Description: "+ newValue.get("Description"));
					statusVariableDescriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
					hboxDescriptionValue.getChildren().add(statusVariableDescriptionLabel);	
											
					HBox hboxVariableNameValue = new HBox();
					hboxVariableNameValue.setPadding(new Insets(10,10,10,30));
					hboxVariableNameValue.setSpacing(100);
					
					HBox variableNamehBox= new HBox();
					variableNamehBox.setSpacing(10);
					variableNamehBox.setPrefWidth(300);
					Label variableNameLabel = new Label("Name: ");
					variableNameLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
					Label variableNameValueLabel = new Label(newValue.get("Variable_name"));
					variableNamehBox.getChildren().addAll(variableNameLabel,variableNameValueLabel);
					
					HBox hboxVariableValue = new HBox();
					hboxVariableValue.setSpacing(10);
					hboxVariableValue.setPrefWidth(200);
					Label statusVariableLabel = new Label("Value:  ");
					TextField statusVariableValue = new TextField(newValue.get("Value"));
					statusVariableLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
					hboxVariableValue.getChildren().addAll(statusVariableLabel,statusVariableValue);																		
							
					HBox btnSaveVariablehBox = new HBox();	
					btnSaveVariablehBox.setPrefWidth(150);
					btnSaveVariablehBox.setAlignment(Pos.BOTTOM_RIGHT);
					//btnBox.setPadding(new Insets(10,100,10,0));
					
					Button saveVariablebtn = new Button();					
					
					if("Y".equals( MySQLConstants.valueOf(newValue.get("Variable_name")).getIsEditable())) {
						saveVariablebtn.setText("Save");
						saveVariablebtn.setDisable(false);
					}else {
						saveVariablebtn.setText("Read Only");
						saveVariablebtn.setDisable(true);
					}
					
					GridPane gridPane = new GridPane();
					gridPane.add(hboxDescriptionhBox,0,0,2,3);
					gridPane.add(hboxVariableNameValue,0,4);
					gridPane.setVgap(10);    // Vertical gap between rows
			        gridPane.setHgap(10);
			        
					btnSaveVariablehBox.getChildren().add(saveVariablebtn);										
					hboxVariableNameValue.getChildren().addAll(variableNamehBox,hboxVariableValue,btnSaveVariablehBox);	

					variablesSecondHalfDisplayVBox.setPadding(new Insets(0, 40, 10, 0));
					variablesSecondHalfDisplayVBox.getChildren().add(gridPane);
				}
				if(inputParam.equalsIgnoreCase("BINARY LOGS") || inputParam.equalsIgnoreCase("Server Logs")) {
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
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the last empty column otherwise added
        
		if(rs.next()) {
	
	    	System.out.println("First calumne "+rs.getString(1));
	    	TableColumn<Map, String> tableColumnName;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	           
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnLabel(i+1);
	        	System.out.println("Column Name : "+columnNames[i]);
	        	columnTypes[i] =  md.getColumnType(i+1);	   
	        	
	        	tableColumnName = new TableColumn<>(columnNames[i]);
	        	tableColumnName.setPrefWidth(150);
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
	        	            	 System.out.println("Pop up a new Tab for Tables from here ");
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
				
		VBox particularTableMainTabVBox = new VBox();
		particularTableMainTabVBox.setSpacing(10);
		particularTableMainTabVBox.getChildren().add(addTopHBoxForInfo("Table "+tableName+" for Connection "+currentConnectionName));
		
		TabPane particularTableTabPane = new TabPane();
		particularTableTabPane.setTabMinWidth(180);
		particularTableTabPane.setTabMinHeight(30);
		Tab particularTablePropertiesTab = new Tab("Properties");
		particularTablePropertiesTab.setClosable(false);
		Tab particularTableDataTab = new Tab("Data");
		particularTableDataTab.setClosable(false);
		Tab particularTableERDiagramTab = new Tab("ER Diagram");
		particularTableERDiagramTab.setClosable(false);
		Tab particularTableGraphVisualsTab = new Tab("Graph Visuals");
		particularTableGraphVisualsTab.setClosable(false);
		Tab particularTableAiPromptTab = new Tab("AI Prompt");
		particularTableAiPromptTab.setClosable(false);
		particularTableMainTabVBox.getChildren().addAll(particularTableTabPane)	;
		
		// Properties
		TabPane particularTablePropertiesTabbedPane = new TabPane();
		particularTablePropertiesTabbedPane.setSide(Side.LEFT);
		particularTablePropertiesTabbedPane.setRotateGraphic(true);
		particularTablePropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
		particularTablePropertiesTabbedPane.setTabMaxHeight(200);
		particularTablePropertiesTabbedPane.setTabMinWidth(42);
		particularTablePropertiesTabbedPane.setTabMaxWidth(42);
		
	
		// Details
		Tab particularTabledetailsTab = new Tab();
		particularTabledetailsTab.setClosable(false);
		Label l = new Label("Details");
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularTabledetailsTab.setGraphic(stp);
		particularTabledetailsTab = getParticularTableDetailsTab(tableName, databaseName,particularTabledetailsTab);
		

		// Columns
		Tab particularTablecolumnsTab = new Tab();
		particularTablecolumnsTab.setClosable(false);
		l = new Label("Columns");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTablecolumnsTab.setGraphic(stp);
		
	
		Tab particularTableconstraintsTab = new Tab();
		particularTableconstraintsTab.setClosable(false);
		l = new Label("Constraints");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTableconstraintsTab.setGraphic(stp);
		

		Tab particularTableforeignKeysTab = new Tab();
		particularTableforeignKeysTab.setClosable(false);
		l = new Label("Foreign Keys");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTableforeignKeysTab.setGraphic(stp);
		

		Tab particularTablereferencesTab = new Tab();
		particularTablereferencesTab.setClosable(false);
		l = new Label("References");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTablereferencesTab.setGraphic(stp);
		
		Tab particularTabletriggersTab = new Tab();
		particularTabletriggersTab.setClosable(false);
		l = new Label("Triggers");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTabletriggersTab.setGraphic(stp);
		
		
		Tab particularTableindexesTab = new Tab();
		particularTableindexesTab.setClosable(false);
		l = new Label("Indexes");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTableindexesTab.setGraphic(stp);
		
		Tab particularTablepartitionsTab = new Tab();
		particularTablepartitionsTab.setClosable(false);
		l = new Label("Partitions");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTablepartitionsTab.setGraphic(stp);

		Tab particularTableDDLTab = new Tab();
		particularTableDDLTab.setClosable(false);
		l = new Label("Source/DDL");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTableDDLTab.setGraphic(stp);
		
		particularTablePropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
			
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Columns")) {
					 getParticularTableColumnsTab(tableName,databaseName,particularTablecolumnsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Constraints")) {
					getParticularTableConstraintsTab(tableName,databaseName,particularTableconstraintsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Foreign Keys")) {
					getParticularTableForeignKeysTab(tableName,databaseName,particularTableforeignKeysTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("References")) {
					getParticularTableReferencesTab(tableName,databaseName,particularTablereferencesTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Triggers")) {
					getParticularTableTriggerTab(tableName,databaseName,particularTabletriggersTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Indexes")) {
					getParticulatTableIndexesTab(tableName,databaseName,particularTableindexesTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Partitions")) {
					getParticularTablePartitionTab(tableName,databaseName,particularTablepartitionsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Source/DDL")) {
					getParticularTableSourceDDLTab(tableName,databaseName,particularTableDDLTab);
				}
			}
		});
		
		particularTableTabPane.getTabs().addAll(particularTablePropertiesTab,particularTableDataTab,particularTableERDiagramTab,particularTableGraphVisualsTab,particularTableAiPromptTab);
		
		particularTablePropertiesTabbedPane.getTabs().addAll(particularTabledetailsTab,particularTablecolumnsTab,particularTableconstraintsTab,particularTableforeignKeysTab,
				particularTablereferencesTab,particularTabletriggersTab,particularTableindexesTab,particularTablepartitionsTab,particularTableDDLTab);
		particularTablePropertiesTab.setContent(particularTablePropertiesTabbedPane);
		
		// Data
		particularTableDataTab.setContent(new TableView());
		
		
		particularTableMainTab.setContent(particularTableMainTabVBox);
		
		return particularTableMainTab;
	}

	private Tab getParticularTableSourceDDLTab(String tableName,String databaseName,Tab particularTableDDLTab) {
		try {
			
			ResultSet rsTable = stmt.executeQuery("Show Create table "+databaseName+"."+tableName);
			
			VBox particularTableDDLTabVBox = new VBox();
			particularTableDDLTabVBox.setSpacing(10);
			particularTableDDLTabVBox.setPadding(new Insets(2,2,2,2));
			TextArea particularTableDDLTextArea = new TextArea("/***/");
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
			particularTablepartitionsTabVBox.setSpacing(10);
			particularTablepartitionsTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTablepartitionsView = showResultSetInTheTableView(rsTable); 
			HBox particularTablepartitionsButtonsHBox = new HBox();
			particularTablepartitionsButtonsHBox.setPadding(new Insets(10,10,10,10));
			particularTablepartitionsButtonsHBox.getChildren().add(new Button("Create"));
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
			particularTableindexesTabVBox.setSpacing(10);
			particularTableindexesTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTableindexesView = showResultSetInTheTableView(rsTable); 
			HBox particularTableindexesButtonsHBox = new HBox();
			particularTableindexesButtonsHBox.setPadding(new Insets(10,10,10,10));
			particularTableindexesButtonsHBox.getChildren().add(new Button("Create"));
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
				particularTabletriggersTabVBox.setSpacing(10);
				particularTabletriggersTabVBox.setPadding(new Insets(2,2,2,2));
				TableView particularTabletriggersView = showResultSetInTheTableView(rsTable);
				HBox particularTabletriggersButtonsHBox = new HBox();
				particularTabletriggersButtonsHBox.setPadding(new Insets(10,10,10,10));
				particularTabletriggersButtonsHBox.getChildren().add(new Button("Create"));
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
			particularTablereferencesTabVBox.setSpacing(10);
			particularTablereferencesTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTablereferencesView = showResultSetInTheTableView(rsTable); 
			HBox particularTablereferencesButtonsHBox = new HBox();
			particularTablereferencesButtonsHBox.setPadding(new Insets(10,10,10,10));
			particularTablereferencesButtonsHBox.getChildren().add(new Button("Create"));
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
			particularTableforeignKeysTabVBox.setSpacing(10);
			particularTableforeignKeysTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTableforeignKeysView = showResultSetInTheTableView(rsTable);
			HBox particularTableforeignKeysButtonsHBox = new HBox();
			particularTableforeignKeysButtonsHBox.setPadding(new Insets(10,10,10,10));
			particularTableforeignKeysButtonsHBox.getChildren().add(new Button("Create"));
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
			particularTableconstraintsTabVBox.setSpacing(10);
			particularTableconstraintsTabVBox.setPadding(new Insets(2,2,2,2));
			TableView particularTableconstraintsView = showResultSetInTheTableView(rsTable); 
			HBox particularTableconstraintsButtonsHBox = new HBox();
			particularTableconstraintsButtonsHBox.setPadding(new Insets(10,10,10,10));
			particularTableconstraintsButtonsHBox.getChildren().add(new Button("Create"));
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
			particularTablecolumnsTabVBox.setSpacing(10);
			particularTablecolumnsTabVBox.setMinHeight(menu_Items_FX.size.getHeight()-300);
			particularTablecolumnsTabVBox.setPadding(new Insets(2,2,2,2));

			TableView particularTableColumnsView = showResultSetInTheTableView(rsTable);								
				
			HBox particularTableColumnsButtonsHBox = new HBox();
			particularTableColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
			particularTableColumnsButtonsHBox.getChildren().add(new Button("Create"));
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
				particularTableDetailsVBox.setSpacing(10);
				particularTableDetailsVBox.setPadding(new Insets(2,2,2,2));
				particularTableDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane tableDetailGridPane = new GridPane();
				tableDetailGridPane.setVgap(8);
				tableDetailGridPane.setHgap(10);
				
				String lableName[] = {"Engine:","Version:","Table Rows:","Auto Increment","Data Length:","Index Length:","Max Data Length:","Avg Row Length","Row Format:",
						"Data Free:","Update Time:","Creation Time:","Table Collation:","Create Options:","Comment:"};
				
				String labelNameValue[] = {"ENGINE","VERSION","TABLE_ROWS","AUTO_INCREMENT","DATA_LENGTH","INDEX_LENGTH","MAX_DATA_LENGTH","AVG_ROW_LENGTH","ROW_FORMAT","DATA_FREE","UPDATE_TIME","CREATE_TIME",
						"TABLE_COLLATION","CREATE_OPTIONS","TABLE_COMMENT"};
				
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
					GridPane.setConstraints(labelNameValueLabel, 1, i);
					
					tableDetailGridPane.getChildren().addAll(labelName,labelNameValueLabel);
				}
				
						particularTableDetailsVBox.getChildren().add(tableDetailGridPane);
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
				particularViewDetailsVBox.setSpacing(10);
				particularViewDetailsVBox.setPadding(new Insets(2,2,2,2));
				particularViewDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane viewDetailGridPane = new GridPane();
				viewDetailGridPane.setVgap(8);
				viewDetailGridPane.setHgap(10);
				
				String lableName[] = {"Check Option:","Is Updatable:","Definer:","Security Type","Character Set Client:","Collation Connection:"};
				
				String labelNameValue[] = {"CHECK_OPTION","IS_UPDATABLE","DEFINER","SECURITY_TYPE","CHARACTER_SET_CLIENT","COLLATION_CONNECTION"};
				
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
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
		
		VBox particularViewMainTabVBox = new VBox();
		particularViewMainTabVBox.setSpacing(10);
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularViewMainTabVBox.getChildren().add(addTopHBoxForInfo("View "+viewName+" for Connection "+currentConnectionName));
		
		
		TabPane particularViewTabPane = new TabPane();
		particularViewTabPane.setTabMinWidth(180);
		particularViewTabPane.setTabMinHeight(30);
		Tab particularTablePropertiesTab = new Tab("Properties");
		Tab particularTableDataTab = new Tab("Data");
		Tab particularTableERDiagramTab = new Tab("ER Diagram");
		Tab particularTableGraphVisualsTab = new Tab("Graph Visuals");
		Tab particularTableAiPromptTab = new Tab("AI Prompt");
		particularViewMainTabVBox.getChildren().addAll(particularViewTabPane)	;
		
		// Properties
		TabPane particularViewPropertiesTabbedPane = new TabPane();
		particularViewPropertiesTabbedPane.setSide(Side.LEFT);
		particularViewPropertiesTabbedPane.setRotateGraphic(true);
		particularViewPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
		particularViewPropertiesTabbedPane.setTabMaxHeight(200);
		particularViewPropertiesTabbedPane.setTabMinWidth(50);
		particularViewPropertiesTabbedPane.setTabMaxWidth(50);
		
		//Details
		Tab particularViewdetailsTab = new Tab();
		particularViewdetailsTab.setClosable(false);
		Label l = new Label("Details");
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularViewdetailsTab.setGraphic(stp);
		
		particularViewdetailsTab = getParticularViewDetailsTab(viewName,databaseName,particularViewdetailsTab);
		
		// Columns
		Tab particularViewcolumnsTab = new Tab();
		particularViewcolumnsTab.setClosable(false);
		l = new Label("Columns");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularViewcolumnsTab.setGraphic(stp);
		VBox particularViewcolumnsTabVBox = new VBox();
		particularViewcolumnsTabVBox.setSpacing(10);
		particularViewcolumnsTabVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
		particularViewcolumnsTabVBox.setPadding(new Insets(2,2,2,2));
		TableView particularViewColumnsView = new TableView();								
		HBox particularViewColumnsButtonsHBox = new HBox();
		particularViewColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
		particularViewColumnsButtonsHBox.getChildren().addAll(new Button("Create"),new Button("Delete"));
		particularViewcolumnsTabVBox.getChildren().addAll(particularViewColumnsView,particularViewColumnsButtonsHBox);
		particularViewcolumnsTab.setContent(particularViewcolumnsTabVBox);
		
		// Source/DDL
		Tab particularViewDDLTab = new Tab();
		particularViewDDLTab.setClosable(false);
		l = new Label("Source/DDL");
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
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Columns")) {
					particularViewColumnsView.getItems().clear();
					getParticularViewColumnsTab(viewName,databaseName,particularViewcolumnsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Source/DDL")) {
					getParticularTableSourceDDLTab(viewName,databaseName,particularViewDDLTab);
				}
			}
		});
		// Data
		particularTableDataTab.setContent(new TableView());
		
		
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
				particularIndexDetailsVBox.setSpacing(10);
				particularIndexDetailsVBox.setPadding(new Insets(2,2,2,2));
				particularIndexDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane indexDetailGridPane = new GridPane();
				indexDetailGridPane.setVgap(8);
				indexDetailGridPane.setHgap(10);
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
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
		
		VBox particularIndexesMainTabVBox = new VBox();
		particularIndexesMainTabVBox.setSpacing(10);
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularIndexesMainTabVBox.getChildren().add(addTopHBoxForInfo("Indexes "+indexesName+" for Connection "+currentConnectionName));
		
		
		TabPane particularIndexesTabPane = new TabPane();
		particularIndexesTabPane.setTabMinWidth(180);
		particularIndexesTabPane.setTabMinHeight(30);
		Tab particularTablePropertiesTab = new Tab("Properties");
		particularTablePropertiesTab.setClosable(false);
		particularIndexesMainTabVBox.getChildren().addAll(particularIndexesTabPane)	;
		
		// Properties
		TabPane particularIndexesPropertiesTabbedPane = new TabPane();
		particularIndexesPropertiesTabbedPane.setSide(Side.LEFT);
		particularIndexesPropertiesTabbedPane.setRotateGraphic(true);
		particularIndexesPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
		particularIndexesPropertiesTabbedPane.setTabMaxHeight(200);
		particularIndexesPropertiesTabbedPane.setTabMinWidth(50);
		
		Tab particularIndexesdetailsTab = new Tab();
		particularIndexesdetailsTab.setClosable(false);
		Label l = new Label("Details");
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularIndexesdetailsTab.setGraphic(stp);
		
		particularIndexesdetailsTab = getParticularIndexDetailsTab(indexesName,databaseName,particularIndexesdetailsTab);
		
		Tab particularIndexescolumnsTab = new Tab();
		particularIndexescolumnsTab.setClosable(false);
		l = new Label("Index Columns");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularIndexescolumnsTab.setGraphic(stp);
		VBox particularIndexescolumnsTabVBox = new VBox();
		particularIndexescolumnsTabVBox.setSpacing(10);
		particularIndexescolumnsTabVBox.setPadding(new Insets(2,2,2,2));
		particularIndexescolumnsTabVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
		TableView particularIndexesColumnsView = new TableView(); 
		HBox particularIndexesColumnsButtonsHBox = new HBox();
		particularIndexesColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
		particularIndexesColumnsButtonsHBox.getChildren().add(new Button("Create"));
		particularIndexescolumnsTabVBox.getChildren().addAll(particularIndexesColumnsView,particularIndexesColumnsButtonsHBox);
		particularIndexescolumnsTab.setContent(particularIndexescolumnsTabVBox);
	
		particularIndexesTabPane.getTabs().addAll(particularTablePropertiesTab);

		particularIndexesPropertiesTabbedPane.getTabs().addAll(particularIndexesdetailsTab,particularIndexescolumnsTab);
		
		particularIndexesPropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Index Columns")) {
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
				particularProcedureDetailsVBox.setSpacing(10);
				particularProcedureDetailsVBox.setPadding(new Insets(2,2,2,2));
				particularProcedureDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane procedureDetailGridPane = new GridPane();
				procedureDetailGridPane.setVgap(8);
				procedureDetailGridPane.setHgap(10);
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
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
			particularProcedureDDLTabVBox.setSpacing(10);
			particularProcedureDDLTabVBox.setPadding(new Insets(2,2,2,2));
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
		
		VBox particularProceduresMainTabVBox = new VBox();
		particularProceduresMainTabVBox.setSpacing(10);
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularProceduresMainTabVBox.getChildren().add(addTopHBoxForInfo("Procedures "+proceduresName+" for Connection "+currentConnectionName));
		
		
		TabPane particularProceduresTabPane = new TabPane();
		particularProceduresTabPane.setTabMinWidth(180);
		particularProceduresTabPane.setTabMinHeight(30);
		Tab particularTablePropertiesTab = new Tab("Properties");
		particularTablePropertiesTab.setClosable(false);
		particularProceduresMainTabVBox.getChildren().addAll(particularProceduresTabPane)	;
		
		// Properties
		TabPane particularProceduresPropertiesTabbedPane = new TabPane();
		particularProceduresPropertiesTabbedPane.setSide(Side.LEFT);
		particularProceduresPropertiesTabbedPane.setRotateGraphic(true);
		particularProceduresPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
		particularProceduresPropertiesTabbedPane.setTabMaxHeight(200);
		particularProceduresPropertiesTabbedPane.setTabMinWidth(50);
		
		Tab particularProceduresdetailsTab = new Tab();
		particularProceduresdetailsTab.setClosable(false);
		Label l = new Label("Details");
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularProceduresdetailsTab.setGraphic(stp);
		
		particularProceduresdetailsTab = getParticularProcedureDetailsTab(proceduresName,databaseName , particularProceduresdetailsTab);
		
		Tab particularProcedurescolumnsTab = new Tab();
		particularProcedurescolumnsTab.setClosable(false);
		l = new Label("Procedure Parameters");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularProcedurescolumnsTab.setGraphic(stp);
		VBox particularProcedurescolumnsTabVBox = new VBox();
		particularProcedurescolumnsTabVBox.setSpacing(10);
		particularProcedurescolumnsTabVBox.setPadding(new Insets(2,2,2,2));
		particularProcedurescolumnsTabVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
		TableView particularProceduresColumnsView = new TableView(); 
		HBox particularProceduresColumnsButtonsHBox = new HBox();
		particularProceduresColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
		particularProceduresColumnsButtonsHBox.getChildren().add(new Button("Create"));
		particularProcedurescolumnsTabVBox.getChildren().addAll(particularProceduresColumnsView,particularProceduresColumnsButtonsHBox);
		particularProcedurescolumnsTab.setContent(particularProcedurescolumnsTabVBox);
		
		Tab particularProceduresDDLTab = new Tab();
		particularProceduresDDLTab.setClosable(false);
		l = new Label("Source/DDL");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularProceduresDDLTab.setGraphic(stp);
		VBox particularProceduresDDLTabVBox = new VBox();
		particularProceduresDDLTabVBox.setSpacing(10);
		particularProceduresDDLTabVBox.setPadding(new Insets(2,2,2,2));
	
		particularProceduresTabPane.getTabs().addAll(particularTablePropertiesTab);
		
		particularProceduresPropertiesTabbedPane.getTabs().addAll(particularProceduresdetailsTab,particularProcedurescolumnsTab,particularProceduresDDLTab);
		
		particularProceduresPropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Procedure Parameters")) {
					particularProceduresColumnsView.getItems().clear();
					getParticularProceduresParametersTab(proceduresName,databaseName,particularProcedurescolumnsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Source/DDL")) {
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
				particularFunctionDetailsVBox.setSpacing(10);
				particularFunctionDetailsVBox.setPadding(new Insets(2,2,2,2));
				particularFunctionDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane functionDetailGridPane = new GridPane();
				functionDetailGridPane.setVgap(8);
				functionDetailGridPane.setHgap(10);
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
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
			particularFunctionDDLTabVBox.setSpacing(10);
			particularFunctionDDLTabVBox.setPadding(new Insets(2,2,2,2));
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
		
		VBox particularFunctionsMainTabVBox = new VBox();
		particularFunctionsMainTabVBox.setSpacing(10);
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularFunctionsMainTabVBox.getChildren().add(addTopHBoxForInfo("Functions "+functionsName+" for Connection "+currentConnectionName));
		
		
		TabPane particularFunctionsTabPane = new TabPane();
		particularFunctionsTabPane.setTabMinWidth(180);
		particularFunctionsTabPane.setTabMinHeight(30);
		Tab particularTablePropertiesTab = new Tab("Properties");
		particularTablePropertiesTab.setClosable(false);
		particularFunctionsMainTabVBox.getChildren().addAll(particularFunctionsTabPane)	;
		
		// Properties
		TabPane particularFunctionsPropertiesTabbedPane = new TabPane();
		particularFunctionsPropertiesTabbedPane.setSide(Side.LEFT);
		particularFunctionsPropertiesTabbedPane.setRotateGraphic(true);
		particularFunctionsPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
		particularFunctionsPropertiesTabbedPane.setTabMaxHeight(200);
		particularFunctionsPropertiesTabbedPane.setTabMinWidth(50);
		
		Tab particularFunctionsdetailsTab = new Tab();
		particularFunctionsdetailsTab.setClosable(false);
		Label l = new Label("Details");
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularFunctionsdetailsTab.setGraphic(stp);
		
		particularFunctionsdetailsTab = getParticularFunctionDetailsTab(functionsName,databaseName , particularFunctionsdetailsTab);
		
		Tab particularFunctionscolumnsTab = new Tab();
		particularFunctionscolumnsTab.setClosable(false);
		l = new Label("Function Parameters");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularFunctionscolumnsTab.setGraphic(stp);
		VBox particularFunctionscolumnsTabVBox = new VBox();
		particularFunctionscolumnsTabVBox.setSpacing(10);
		particularFunctionscolumnsTabVBox.setPadding(new Insets(2,2,2,2));
		particularFunctionscolumnsTabVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
		TableView particularFunctionsColumnsView = new TableView(); 
		HBox particularFunctionsColumnsButtonsHBox = new HBox();
		particularFunctionsColumnsButtonsHBox.setPadding(new Insets(10,10,10,10));
		particularFunctionsColumnsButtonsHBox.getChildren().add(new Button("Create"));
		particularFunctionscolumnsTabVBox.getChildren().addAll(particularFunctionsColumnsView,particularFunctionsColumnsButtonsHBox);
		particularFunctionscolumnsTab.setContent(particularFunctionscolumnsTabVBox);
	
		Tab particularFunctionsDDLTab = new Tab();
		particularFunctionsDDLTab.setClosable(false);
		l = new Label("Source/DDL");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularFunctionsDDLTab.setGraphic(stp);
		VBox particularFunctionsDDLTabVBox = new VBox();
		particularFunctionsDDLTabVBox.setSpacing(10);
		particularFunctionsDDLTabVBox.setPadding(new Insets(2,2,2,2));
		
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
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Function Parameters")) {
					particularFunctionsColumnsView.getItems().clear();
					getParticularFunctionsParametersTab(functionsName,databaseName,particularFunctionscolumnsTab);
				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Source/DDL")) {
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
				particularTriggerDetailsVBox.setSpacing(10);
				particularTriggerDetailsVBox.setPadding(new Insets(2,2,2,2));
				particularTriggerDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane triggerDetailGridPane = new GridPane();
				triggerDetailGridPane.setVgap(8);
				triggerDetailGridPane.setHgap(10);
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
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
			particularTriggerDDLTabVBox.setSpacing(10);
			particularTriggerDDLTabVBox.setPadding(new Insets(2,2,2,2));
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
		
		VBox particularTriggersMainTabVBox = new VBox();
		particularTriggersMainTabVBox.setSpacing(10);
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularTriggersMainTabVBox.getChildren().add(addTopHBoxForInfo("Triggers "+triggersName+" for Connection "+currentConnectionName));
		
		
		TabPane particularTriggersTabPane = new TabPane();
		particularTriggersTabPane.setTabMinWidth(180);
		particularTriggersTabPane.setTabMinHeight(30);
		Tab particularTablePropertiesTab = new Tab("Properties");
		particularTablePropertiesTab.setClosable(false);
		particularTriggersMainTabVBox.getChildren().addAll(particularTriggersTabPane);
		
		// Properties
		TabPane particularTriggersPropertiesTabbedPane = new TabPane();
		particularTriggersPropertiesTabbedPane.setSide(Side.LEFT);
		particularTriggersPropertiesTabbedPane.setRotateGraphic(true);
		particularTriggersPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
		particularTriggersPropertiesTabbedPane.setTabMaxHeight(200);
		particularTriggersPropertiesTabbedPane.setTabMinWidth(50);
		
		Tab particularTriggersdetailsTab = new Tab();
		particularTriggersdetailsTab.setClosable(false);
		Label l = new Label("Details");
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
		particularTriggersDDLTab.setClosable(false);
		l = new Label("Source/DDL");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularTriggersDDLTab.setGraphic(stp);
		VBox particularTriggersDDLTabVBox = new VBox();
		particularTriggersDDLTabVBox.setSpacing(10);
		particularTriggersDDLTabVBox.setPadding(new Insets(2,2,2,2));
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
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Source/DDL")) {
					getParticularTriggerSourceDDLTab(triggersName,databaseName,particularTriggersDDLTab);
				}
			}
		});
		
		particularTablePropertiesTab.setContent(particularTriggersPropertiesTabbedPane);
		particularTriggersMainTab.setContent(particularTriggersMainTabVBox);
		return particularTriggersMainTab;
	}
	
	
	private Tab getParticularEventDetailsTab(String eventName,String databaseName,Tab particularEventsdetailsTab) {

		try {	
			System.out.println("Event name "+eventName);
	
			System.out.println("select event_name,event_Schema,event_type,event_body,interval_value,interval_field,starts,ends, definer from information_Schema.events where event_Schema= '"+databaseName+"'");
			ResultSet rsTable = stmt.executeQuery("select event_name,event_Schema,event_type,event_body,interval_value,interval_field,starts,ends, definer from information_Schema.events where event_Schema = '"+databaseName+"'");
			
			try {
				Thread.sleep(1000);			
			}catch(Exception e) {
				e.printStackTrace();
			}
			String lableName[] = {"Event Name:","Event Schema:","Event Type:","Event Body:","Interval Value:","Interval Field:","Starts:",",Ends:","definer:"};
			String labelNameValue[] = {"event_name","event_schema","event_type","event_body","interval_value","interval_field","starts",",ends","definer","action_reference_new_table","definer"};
			
			while(rsTable.next()) {
			
				VBox particularEventDetailsVBox = new VBox();
				particularEventDetailsVBox.setSpacing(10);
				particularEventDetailsVBox.setPadding(new Insets(2,2,2,2));
				particularEventDetailsVBox.setPadding(new Insets(20,10,10,200));
				
				GridPane eventDetailGridPane = new GridPane();
				eventDetailGridPane.setVgap(8);
				eventDetailGridPane.setHgap(10);
				
				for(int i =0;i<lableName.length;i++) {
					Label labelName = new Label(lableName[i]);
					GridPane.setConstraints(labelName, 0, i);   // column 0 row 0
					Label labelNameValueLabel= new Label(rsTable.getString(labelNameValue[i]));
					labelNameValueLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
					GridPane.setConstraints(labelNameValueLabel, 1, i);
					
					eventDetailGridPane.getChildren().addAll(labelName,labelNameValueLabel);
				}
				particularEventDetailsVBox.getChildren().add(eventDetailGridPane);
				particularEventsdetailsTab.setContent(particularEventDetailsVBox);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return particularEventsdetailsTab;
	}
	
	
	private Tab getParticularEventSourceDDLTab(String eventsName,String databaseName,Tab particularEventDDLTab) {
		try {
			
			ResultSet rsTable = stmt.executeQuery("Show Create Event "+databaseName+"."+eventsName);
			
			VBox particularEventDDLTabVBox = new VBox();
			particularEventDDLTabVBox.setSpacing(10);
			particularEventDDLTabVBox.setPadding(new Insets(2,2,2,2));
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
		
		VBox particularEventsMainTabVBox = new VBox();
		particularEventsMainTabVBox.setSpacing(10);
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		particularEventsMainTabVBox.getChildren().add(addTopHBoxForInfo("Events "+eventsName+" for Connection "+currentConnectionName));
		
		
		TabPane particularEventsTabPane = new TabPane();
		particularEventsTabPane.setTabMinWidth(180);
		particularEventsTabPane.setTabMinHeight(30);
		Tab particularTablePropertiesTab = new Tab("Properties");
		particularEventsMainTabVBox.getChildren().addAll(particularEventsTabPane);
		
		// Properties
		TabPane particularEventsPropertiesTabbedPane = new TabPane();
		particularEventsPropertiesTabbedPane.setSide(Side.LEFT);
		particularEventsPropertiesTabbedPane.setRotateGraphic(true);
		particularEventsPropertiesTabbedPane.setTabMinHeight(200); // Determines tab width. I know, its odd.
		particularEventsPropertiesTabbedPane.setTabMaxHeight(200);
		particularEventsPropertiesTabbedPane.setTabMinWidth(50);
		
		// use the information_Schema.events table to get the following details and display them in details
		// select event_name,event_Schema,event_type,event_body,interval_value,interval_field,starts,ends, definer from information_Schema.events where event_Schema=datbaseNam
		Tab particularEventsdetailsTab = new Tab();
		particularEventsdetailsTab.setClosable(false);
		Label l = new Label("Details");
		l.setRotate(90);
		StackPane stp = new StackPane(new Group(l));
		particularEventsdetailsTab.setGraphic(stp);
		
		particularEventsdetailsTab= getParticularEventDetailsTab(eventsName,databaseName,particularEventsdetailsTab);
//		
//		VBox particularTableDetailsVBox = new VBox();
//		particularTableDetailsVBox.setSpacing(30);
//		particularTableDetailsVBox.setPadding(new Insets(10,10,10,10));
//		particularTableDetailsVBox.setMinHeight(menu_Items_FX.size.getWidth()-300);
//		particularEventsdetailsTab.setContent(particularTableDetailsVBox);
//	
	
		// event_defination from the information_Schema.events will have this information.
		Tab particularEventsDDLTab = new Tab();
		particularEventsDDLTab.setClosable(false);
		l = new Label("Source/DDL");
		l.setRotate(90);
		stp = new StackPane(new Group(l));
		particularEventsDDLTab.setGraphic(stp);
		VBox particularEventsDDLTabVBox = new VBox();
		particularEventsDDLTabVBox.setSpacing(10);
		particularEventsDDLTabVBox.setPadding(new Insets(2,2,2,2));
		TextArea particularEventsDDLTextArea = new TextArea("");
//		particularEventsDDLTextArea.setMinHeight(menu_Items_FX.size.getHeight()-280);
		particularEventsDDLTextArea.setEditable(false);		
		particularEventsDDLTabVBox.getChildren().addAll(particularEventsDDLTextArea);
		particularEventsDDLTab.setContent(particularEventsDDLTabVBox);
		
		particularEventsTabPane.getTabs().addAll(particularTablePropertiesTab);
		
		particularEventsPropertiesTabbedPane.getTabs().addAll(particularEventsdetailsTab,particularEventsDDLTab);
		
		particularEventsPropertiesTabbedPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				System.out.println("Tab swithced"+ ((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText());
//				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Procedure Parameters")) {
//					particularTriggersColumnsView.getItems().clear();
//					getParticularProceduresParametersTab(triggersName,databaseName,particularTriggerscolumnsTab);
//				}
				if(((Label)((Group)((StackPane)newValue.getGraphic()).getChildren().get(0)).getChildren().get(0)).getText().equalsIgnoreCase("Source/DDL")) {
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
		  statusSystemVariablesTabpane.setTabMinWidth(250);
		  statusSystemVariablesTabpane.setTabMinHeight(20);
		
		  statusVariablesTab = new Tab("Status Variables");
		  statusVariablesTab.setClosable(false);
		  statusVariablesTab.setContent(getStatusVariables());
	  
		 
		  systemVariablesTab = new Tab("System Variables");
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
	        hbox.setPadding(new Insets(10, 12, 10, 12));
	        hbox.setSpacing(10);   // Gap between nodes
	        hbox.setStyle("-fx-background-color: #2f4f4f;");        
	        Text connectToDatabseText = new Text("Users and Privileges");
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	  }
	  
	  private HBox addTopHBoxForVariables() {

			 
	        HBox hbox = new HBox();
	        hbox.setPadding(new Insets(10, 12, 10, 12));
	        hbox.setSpacing(10);   // Gap between nodes
	        hbox.setStyle("-fx-background-color: #2f4f4f;");        
	        Text connectToDatabseText = new Text("Server Variables");
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	  }
	  
	  private HBox addTopHBoxForInfo(String infoType) {
	     
		  HBox hbox = new HBox();
	        hbox.setPadding(new Insets(10, 12, 10, 12));
	        hbox.setSpacing(10);   // Gap between nodes
	        hbox.setStyle("-fx-background-color: #2f4f4f;");        
	        Text connectToDatabseText = new Text(infoType);
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
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
		  statusVariablesTabPane.setTabMinWidth(150);
		  statusVariablesTabPane.setTabMinHeight(20);
		  
		  globalStatusVariablesTab = new Tab("Global Status Variables");
		  globalStatusVariablesTab.setClosable(false);
		  globalStatusVariablesTab.setContent(getVariablesData("GlobalStatus"));  // This needs to be done coz we need to see data initially
		  
		  // *****************//

		  sessionStatusVariablesTab = new Tab("Session Status Variables");
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
		  systemVariablesTabPane.setTabMinWidth(150);
		  systemVariablesTabPane.setTabMinHeight(20);
		  
		  globalSystemVariablesTab = new Tab("Global System Variables");
		  globalSystemVariablesTab.setClosable(false);
		  globalSystemVariablesTab.setContent(getVariablesData("GlobalSystem"));

		  sessionSystemVariablesTab = new Tab("Session System Variables");
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

	private VBox getVariablesData(String variableType) {
		
	    VBox vBoxMain = new VBox();
		
		GridPane searchDatabasesGridPane= new GridPane();
		searchDatabasesGridPane.setPadding(new Insets(5,0,0,10));
		Label searchDatabasesLabel = new Label("Find");
		TextField searchDatabasesTextField = new TextField();
		refreshButton = new Button("Refresh");
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
		gridPaneDatabasesLists.setHgap(10);
		gridPaneDatabasesLists.setPadding(new Insets(5,0,0,15));
		
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
	public TextField passwordTextField;
	public TextField confirmPasswordTextField;
	
	
	public VBox addAccountLoginCredentials() {
		
		  VBox accountDetailsVbox = new VBox();
		  HBox accountLockedHBox = new HBox();
		  accountLockedHBox.setPadding(new Insets(15,0,0,100));
		  accountLockedHBox.setSpacing(20);
		  
		  Label accountLockedLabel = new Label("Account Locked :");
		  accountLockedStatus = new Label("Y/N"); // Look up the status	
		  accountlockUnLock = new Button("Lock/Unlock");
		  accountLockedHBox.getChildren().addAll(accountLockedLabel,accountLockedStatus,accountlockUnLock);
		  accountDetailsVbox.getChildren().add(accountLockedHBox);
		  
		  GridPane accountDetailsGridPane = new GridPane();
		  accountDetailsGridPane.setPadding(new Insets(0,10,20,10));
		  accountDetailsGridPane.setVgap(10);
		  accountDetailsGridPane.setHgap(10);
		  Label loginNameLabel = new Label("Login Name :"); 
		  GridPane.setConstraints(loginNameLabel, 0, 1);   // column 0 row 0
		  loginNameTextFeild = new TextField();
		  loginNameTextFeild.setPrefHeight(15);
		  loginNameTextFeild.setPrefWidth(200);
		 // jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		  GridPane.setConstraints(loginNameTextFeild, 1, 1);
		  Label loginNameDescriptionLable = new Label("You may create multiple accounts with the same name to connect from different hosts. place holer place holder");
		  loginNameDescriptionLable.setPrefWidth(300);
		  loginNameDescriptionLable.setWrapText(true);
		  GridPane.setConstraints(loginNameDescriptionLable, 2, 1);
		  accountDetailsGridPane.getChildren().addAll(loginNameLabel,loginNameTextFeild,loginNameDescriptionLable);
		  
		  Label authenticationTypeLabel = new Label("Authentication Type :");
		  GridPane.setConstraints(authenticationTypeLabel, 0, 2);
		  authenticationTypeTextField= new TextField();
		  authenticationTypeTextField.setPrefHeight(15);
		  authenticationTypeTextField.setPrefWidth(200);
		  GridPane.setConstraints(authenticationTypeTextField, 1, 2);
		  Label authenticationTypeLabelDescription = new Label("For the standard password and/or host based authication,select 'standard'  place holer place holder  place holer place holder");
		  authenticationTypeLabelDescription.setPrefWidth(300);
		  authenticationTypeLabelDescription.setWrapText(true);
		  GridPane.setConstraints(authenticationTypeLabelDescription, 2, 2);
		  accountDetailsGridPane.getChildren().addAll(authenticationTypeLabel,authenticationTypeTextField,authenticationTypeLabelDescription);
		  
		  Label authenticationStringLabel = new Label("Authentication String :");
		  GridPane.setConstraints(authenticationStringLabel, 0, 3);
		  authenticationStringTextField= new TextField();
		  authenticationStringTextField.setPrefHeight(15);
		  authenticationStringTextField.setPrefWidth(200);
		  GridPane.setConstraints(authenticationStringTextField, 1, 3);
		  Label authenticationStringLabelDescription = new Label("Authentication plugin specific parameters");
		  authenticationStringLabelDescription.setPrefWidth(300);
		  authenticationStringLabelDescription.setWrapText(true);
		  GridPane.setConstraints(authenticationStringLabelDescription, 2, 3);
		  accountDetailsGridPane.getChildren().addAll(authenticationStringLabel,authenticationStringTextField,authenticationStringLabelDescription);
		  
		  
		  Label hostmatchingLabel = new Label("Hosts Matching :");
		  GridPane.setConstraints(hostmatchingLabel, 0, 4);
		  hostsMatchingTextField = new TextField();
		  hostsMatchingTextField.setPrefHeight(15);
		  hostsMatchingTextField.setPrefWidth(100);  
		  GridPane.setConstraints(hostsMatchingTextField, 1, 4);
		  Label hostmatchingDescriptionLable = new Label("% and _ wildcards may be used , % accesses from anywhere");
		  hostmatchingDescriptionLable.setPrefWidth(300);
		  hostmatchingDescriptionLable.setWrapText(true);
		  GridPane.setConstraints(hostmatchingDescriptionLable, 2, 4);
		  accountDetailsGridPane.getChildren().addAll(hostmatchingLabel,hostsMatchingTextField,hostmatchingDescriptionLable);

		  Label passwordexpiredLabel = new Label("Password Expired:");
		  GridPane.setConstraints(passwordexpiredLabel, 0, 5);
		  passwordExpiredStatusLabel = new Label("Y/N");
		  GridPane.setConstraints(passwordExpiredStatusLabel, 1, 5);
		  
		  Label passwordLastchangedLabel = new Label("Password Last Changed:");
		  GridPane.setConstraints(passwordLastchangedLabel, 2, 5);
		  passwordLastchangedDate = new Label("10/12/2024");
		  GridPane.setConstraints(passwordLastchangedDate, 3, 5);
		  accountDetailsGridPane.getChildren().addAll(passwordexpiredLabel,passwordExpiredStatusLabel,passwordLastchangedLabel,passwordLastchangedDate);
		  
		  Label passwordLabel = new Label("Password :");
		  GridPane.setConstraints(passwordLabel, 0, 6);
		  passwordTextField = new TextField();
		  passwordTextField.setDisable(true);
		  passwordTextField.setPrefHeight(15);
		  passwordTextField.setPrefWidth(100);  
		  GridPane.setConstraints(passwordTextField, 1, 6);
		  Label passwordDescriptionLabel = new Label("Enter the password to reset it. Follow the passord requiements");
		  passwordDescriptionLabel.setPrefWidth(300);
		  passwordDescriptionLabel.setWrapText(true);
		  GridPane.setConstraints(passwordDescriptionLabel, 2, 6);
		  accountDetailsGridPane.getChildren().addAll(passwordLabel,passwordTextField,passwordDescriptionLabel);
		  
		  Label confirmPasswordLabel = new Label("Confirm Password :");
		  GridPane.setConstraints(confirmPasswordLabel, 0, 7);
		  confirmPasswordTextField = new TextField();
		  confirmPasswordTextField.setDisable(true);
		  confirmPasswordTextField.setPrefHeight(15);
		  confirmPasswordTextField.setPrefWidth(100);  
		  GridPane.setConstraints(confirmPasswordTextField, 1, 7);
		  Label confirmPasswordDescriptionLabel = new Label("Enter the password again to confirm");
		  confirmPasswordDescriptionLabel.setPrefWidth(300);
		  confirmPasswordDescriptionLabel.setWrapText(true);
		  GridPane.setConstraints(confirmPasswordDescriptionLabel, 2, 7);
		  accountDetailsGridPane.getChildren().addAll(confirmPasswordLabel,confirmPasswordTextField,confirmPasswordDescriptionLabel);
		  
		  accountDetailsVbox.getChildren().add(accountDetailsGridPane);
		  
		  HBox accountButtonsHBox = new HBox();
		  accountButtonsHBox.setPadding(new Insets(15,15,15,100));
		  accountButtonsHBox.setSpacing(30);
		  Button updatePasswordButton = new Button("Update Password");
		  Button expirePasswordButton = new Button("Expire Password");
		  Button revertAccountChangesButton = new Button("Revert");
		  Button saveAccountChangesButton = new Button("Save");

		  accountButtonsHBox.getChildren().addAll(updatePasswordButton,expirePasswordButton,revertAccountChangesButton,saveAccountChangesButton);
		  
		  accountDetailsVbox.getChildren().add(accountButtonsHBox);
		  
		return accountDetailsVbox;
		
		
	}
	
	public TextField maxQueriesTextFeild;
	public TextField maxUpdatesTextFeild;
	public TextField maxConnectionsTextFeild;
	public TextField concurrentConnectionsTextFeild;
	
	public VBox addAccountLimits(){
		
		VBox accountLimitsVBox = new VBox();
		
		GridPane accountDetailsGridPane = new GridPane();
		accountDetailsGridPane.setPadding(new Insets(0,10,20,10));
		accountDetailsGridPane.setVgap(10);
		accountDetailsGridPane.setHgap(10);
		
		Label maxQueriesLabel = new Label("Max Queries"); 
		GridPane.setConstraints(maxQueriesLabel, 0, 1);   // column 0 row 0
		maxQueriesTextFeild = new TextField();
		maxQueriesTextFeild.setPrefHeight(15);
		maxQueriesTextFeild.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(maxQueriesTextFeild, 1, 1);
		Label maxQueriesDescription = new Label("The number of queries the account can execute within one hour");
		maxQueriesDescription.setPrefWidth(300);
		maxQueriesDescription.setWrapText(true);
		GridPane.setConstraints(maxQueriesDescription, 2, 1);
		accountDetailsGridPane.getChildren().addAll(maxQueriesLabel,maxQueriesTextFeild,maxQueriesDescription);
		
		Label maxUpdatesLabel = new Label("Max Updates"); 
		GridPane.setConstraints(maxUpdatesLabel, 0, 2);   // column 0 row 0
		maxUpdatesTextFeild = new TextField();
		maxUpdatesTextFeild.setPrefHeight(15);
		maxUpdatesTextFeild.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(maxUpdatesTextFeild, 1, 2);
		Label maxUpdatesDescription = new Label("The number of updates the account can execute within one hour");
		maxUpdatesDescription.setPrefWidth(300);
		maxUpdatesDescription.setWrapText(true);
		GridPane.setConstraints(maxUpdatesDescription, 2, 2);
		accountDetailsGridPane.getChildren().addAll(maxUpdatesLabel,maxUpdatesTextFeild,maxUpdatesDescription);
		
		
		Label maxConnectionsLabel = new Label("Max Connections"); 
		GridPane.setConstraints(maxConnectionsLabel, 0, 3);   // column 0 row 0
		maxConnectionsTextFeild = new TextField();
		maxConnectionsTextFeild.setPrefHeight(15);
		maxConnectionsTextFeild.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(maxConnectionsTextFeild, 1, 3);
		Label maxConnectionsDescription = new Label("The number of updates the account can execute within one hour");
		maxConnectionsDescription.setPrefWidth(300);
		maxConnectionsDescription.setWrapText(true);
		GridPane.setConstraints(maxConnectionsDescription, 2, 3);
		accountDetailsGridPane.getChildren().addAll(maxConnectionsLabel,maxConnectionsTextFeild,maxConnectionsDescription);
		
		Label concurrentConnectionsLabel = new Label("Concurrent Connections"); 
		GridPane.setConstraints(concurrentConnectionsLabel, 0, 4);   // column 0 row 0
		concurrentConnectionsTextFeild = new TextField();
		concurrentConnectionsTextFeild.setPrefHeight(15);
		concurrentConnectionsTextFeild.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(concurrentConnectionsTextFeild, 1, 4);
		Label concurrentConnectionsDescription = new Label("The number of updates the account can execute within one hour");
		concurrentConnectionsDescription.setPrefWidth(300);
		concurrentConnectionsDescription.setWrapText(true);
		GridPane.setConstraints(maxConnectionsDescription, 2, 4);
		accountDetailsGridPane.getChildren().addAll(concurrentConnectionsLabel,concurrentConnectionsTextFeild,concurrentConnectionsDescription);
		
		accountLimitsVBox.getChildren().add(accountDetailsGridPane);
		
		HBox accountLimitsButtonshbox = new HBox();
		accountLimitsButtonshbox.setSpacing(30);
		accountLimitsButtonshbox.setPadding(new Insets(20,10,10,500));
		Button revertAccountLimitsButton = new Button("Revert");
		Button saveAccountLimitsButton = new Button("Save");
		accountLimitsButtonshbox.getChildren().addAll(revertAccountLimitsButton,saveAccountLimitsButton);
		  
		accountLimitsVBox.getChildren().add(accountLimitsButtonshbox);
		
		
		return accountLimitsVBox;
	}
	
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
		accountPrivilegesVBox.setSpacing(10);
		accountPrivilegesVBox.setPadding(new Insets(10,10,10,10));
		
		Label globalPrivilegesLable = new Label("Global Privileges");
		CheckBox selectAllCheckBox = new CheckBox("Select All");
		accountPrivilegesVBox.getChildren().addAll(globalPrivilegesLable,selectAllCheckBox);
		

		
		HBox globalPreviligeshbox = new HBox();
		globalPreviligeshbox.setSpacing(50);
		
		VBox firstSetofPrivileges = new VBox();
		firstSetofPrivileges.setSpacing(10);
		firstSetofPrivileges.setPadding(new Insets(10,10,10,10));
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
		secondSetofPrivileges.setSpacing(10);
		secondSetofPrivileges.setPadding(new Insets(10,10,10,10));
	
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
		thirdSetofPrivileges.setSpacing(10);
		thirdSetofPrivileges.setPadding(new Insets(10,10,10,10));

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
	
	public VBox addSchemaPrivileges() {
		
		VBox schemaPrivilegesVbox = new VBox();
		schemaPrivilegesVbox.setSpacing(5);
		schemaPrivilegesVbox.setPadding(new Insets(5,10,00,20));
		
		Label selectSchemaDescription = new Label("Select the Schema/s for which the user 'khan' will have the previleges you want to define");
		//selectSchemaDescription.setFont(new Font(12));
		selectSchemaDescription.setTextFill(Color.BLUEVIOLET);
		schemaPrivilegesVbox.getChildren().add(selectSchemaDescription);	
		final ToggleGroup group = new ToggleGroup();
		
		GridPane schemaPrivilegesGridPane = new GridPane();
		schemaPrivilegesGridPane.setPadding(new Insets(5,10,5,10));
		schemaPrivilegesGridPane.setVgap(10);
		schemaPrivilegesGridPane.setHgap(10);
		
		RadioButton allSchemaRadioButton = new RadioButton("All Schema(%)");
		allSchemaRadioButton.setToggleGroup(group);
		allSchemaRadioButton.setSelected(true); 
		GridPane.setConstraints(allSchemaRadioButton, 0, 0);   // column 0 row 0
		Label allSchemasSelectedLabel = new Label();
		allSchemasSelectedLabel.setPrefHeight(15);
		allSchemasSelectedLabel.setPrefWidth(200);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(allSchemasSelectedLabel, 1, 0);
		Label allsSchemaSelectedDescriptionLabel = new Label("This rule will apply to any schema name");
		allsSchemaSelectedDescriptionLabel.setPrefWidth(300);
		allsSchemaSelectedDescriptionLabel.setWrapText(true);
		GridPane.setConstraints(allsSchemaSelectedDescriptionLabel, 2, 0);
		schemaPrivilegesGridPane.getChildren().addAll(allSchemaRadioButton,allSchemasSelectedLabel,allsSchemaSelectedDescriptionLabel);
		
		RadioButton schemaMatchingPatternRadioButton = new RadioButton("Schemas matching pattern:");
		schemaMatchingPatternRadioButton.setToggleGroup(group);
		GridPane.setConstraints(schemaMatchingPatternRadioButton, 0, 1);   // column 0 row 0
		TextField schemaMatchingPatternTextField = new TextField();
		schemaMatchingPatternTextField.setPrefHeight(15);
		schemaMatchingPatternTextField.setPrefWidth(150);
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(schemaMatchingPatternTextField, 1, 1);
		Label schemaMatchingPatternDescriptionLabel = new Label("You may use % and _ as wildcards in a pattern.");
		schemaMatchingPatternDescriptionLabel.setPrefWidth(300);
		schemaMatchingPatternDescriptionLabel.setWrapText(true);
		GridPane.setConstraints(schemaMatchingPatternDescriptionLabel, 2, 1);
		schemaPrivilegesGridPane.getChildren().addAll(schemaMatchingPatternRadioButton,schemaMatchingPatternTextField,schemaMatchingPatternDescriptionLabel);
		
		
		RadioButton selectedSchemasRadioButton = new RadioButton("Selected Schemas");
		selectedSchemasRadioButton.setToggleGroup(group); 
		GridPane.setConstraints(selectedSchemasRadioButton, 0, 2);   // column 0 row 0
		ComboBox<String> selectedSchemascomboBox = new ComboBox();
		selectedSchemascomboBox.getItems().addAll("mysql","sys","sakila","information_schema","world","others");
		selectedSchemascomboBox.setPrefHeight(15);
		selectedSchemascomboBox.setPrefWidth(200);
		//selectedSchemascomboBox.setValue("mysql");
		selectedSchemascomboBox.setVisibleRowCount(5); // after this their will be scroll bar
		// jdbcConnectionName.setOnKeyTyped(onjdbcUrlTextFieldKeyPressed() );
		GridPane.setConstraints(selectedSchemascomboBox, 1, 2);
		Label selectedSchemaDescription = new Label("Select a specific schema for the rule to apply to:");
		selectedSchemaDescription.setPrefWidth(300);
		selectedSchemaDescription.setWrapText(true);
		GridPane.setConstraints(selectedSchemaDescription, 2, 2);
		schemaPrivilegesGridPane.getChildren().addAll(selectedSchemasRadioButton,selectedSchemascomboBox,selectedSchemaDescription);
		schemaPrivilegesVbox.getChildren().add(schemaPrivilegesGridPane);
		
		HBox addSchemaEntryHbox = new HBox();
		addSchemaEntryHbox.setPadding(new Insets(0,0,0,500));	
		Button addSchemaPrivilegesEntryButton = new Button("Add Schema Entry");
		addSchemaEntryHbox.getChildren().add(addSchemaPrivilegesEntryButton);
		schemaPrivilegesVbox.getChildren().add(addSchemaEntryHbox);

		VBox existingSchemaPriviligesVBox = new VBox();
		existingSchemaPriviligesVBox.setMaxHeight(150);
		existingSchemaPriviligesVBox.setPadding(new Insets(0,10,0,10));
		
		TableView tableView = new TableView();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the last empty column otherwise added
		tableView.setEditable(true);
        
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {

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
		});
		TableColumn tableColumnName;
		String[] columnNames = {"Schema","Privileges"};
		   
	    tableColumnName = new TableColumn<>(columnNames[0]);
	    tableColumnName.setMinWidth(50);
	    tableColumnName.setCellValueFactory(new MapValueFactory<>(columnNames[0]));
	    tableView.getColumns().add(tableColumnName);

	    tableColumnName = new TableColumn<>(columnNames[1]);
	    tableColumnName.setMinWidth(250);
	    tableColumnName.setCellValueFactory(new MapValueFactory<>(columnNames[1]));
	    tableView.getColumns().add(tableColumnName);
	    
		ObservableList<HashMap<String,String>> mapdata =
		            FXCollections.observableArrayList();
        HashMap<String,String> schemaMap = new HashMap<String,String>();
        
        schemaMap.put(columnNames[0], "mysql");
        schemaMap.put(columnNames[1], "none");
        mapdata.add(schemaMap);
        
        schemaMap = new HashMap<String,String>();
        schemaMap.put(columnNames[0], "world");
        schemaMap.put(columnNames[1], "tocome");
        mapdata.add(schemaMap);
      
        tableView.setItems(mapdata);
        
        existingSchemaPriviligesVBox.getChildren().add(tableView);
        
        HBox userAccessSchemaDescriptionHbox= new HBox();
        userAccessSchemaDescriptionHbox.setPadding(new Insets(5,5,5,5));
        userAccessSchemaDescriptionHbox.setSpacing(50);
        Label userAccessSchemaDescriptionLabel = new Label("The use 'khan'@'localhost' will have the following access rights to the schema 'mysql'");
        selectSchemaDescription.setTextFill(Color.BLUEVIOLET);
     	Button deleteSchemaPrivilegesEntryButton = new Button("Delete Schema Entry");
     	deleteSchemaPrivilegesEntryButton.setDisable(true);  // enable it when one of the schema entry is selected
        userAccessSchemaDescriptionHbox.getChildren().addAll(userAccessSchemaDescriptionLabel,deleteSchemaPrivilegesEntryButton);
        
        
        HBox userAccessSchemaPriviligeshBox = new HBox();
        userAccessSchemaPriviligeshBox.setSpacing(10);		
        
        VBox firstSetofSchemaPriviliges = new VBox();
        firstSetofSchemaPriviliges.setSpacing(5);
    	selectSchemaPrivilegeCheckBox  =  new CheckBox("SELECT");
		updateSchemaPrivilegeCheckBox  =  new CheckBox("UPDATE");
		insertSchemaPrivilegeCheckBox =  new CheckBox("INSERT");
		executeSchemaPrivilegeCheckBox =  new CheckBox("EXECUTE"); 
		showViewSchemaPrivilegeCheckBox  =  new CheckBox("SHOW VIEW");
		deleteSchemaPrivilegeCheckBox =  new CheckBox("DELETE");  
		firstSetofSchemaPriviliges.getChildren().addAll(selectSchemaPrivilegeCheckBox,updateSchemaPrivilegeCheckBox,insertSchemaPrivilegeCheckBox,executeSchemaPrivilegeCheckBox,showViewSchemaPrivilegeCheckBox,deleteSchemaPrivilegeCheckBox);
        
		VBox secondSetofSchemaPriviliges = new VBox();
		secondSetofSchemaPriviliges.setSpacing(5);
		createSchemaPrivilegeCheckBox = new CheckBox("CREATE");
		alterSchemaPrivilegeCheckBox = new CheckBox("ALTER");
		referencesSchemaPrivilegeCheckBox  =  new CheckBox("REFERENCES");
		indexSchemaPrivilegeCheckBox =  new CheckBox("INDEX");
		createViewSchemaPrivilegeCheckBox = new CheckBox("CREATE VIEW");
		createRoutineSchemaPrivilegeCheckBox = new CheckBox("CREATE ROUTINE");
		secondSetofSchemaPriviliges.getChildren().addAll(createSchemaPrivilegeCheckBox,alterSchemaPrivilegeCheckBox,referencesSchemaPrivilegeCheckBox,indexSchemaPrivilegeCheckBox,createViewSchemaPrivilegeCheckBox,createRoutineSchemaPrivilegeCheckBox);
		
		VBox thirdSetofSchemaPriviliges = new VBox();
		thirdSetofSchemaPriviliges.setSpacing(5);
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
		fourthSegmentWithButtonsVbox.setPadding(new Insets(130,10,10,20));
		Button reverPriviligestButton = new Button("Revert Privileges");
		reverPriviligestButton.setDisable(true); // enable them when one of the schema entry is selected
		fourthSegmentWithButtonsVbox.getChildren().add(reverPriviligestButton);
		
		VBox fifthSegmentWithButtonsVbox = new VBox();
		fifthSegmentWithButtonsVbox.setPadding(new Insets(130,10,10,20));
		Button savePrivilegesButton = new Button("Save Privileges");
		savePrivilegesButton.setDisable(true); // enable them when one of the schema entry is selected
		fifthSegmentWithButtonsVbox.getChildren().add(savePrivilegesButton);
		
		 userAccessSchemaPriviligeshBox.getChildren().addAll(firstSetofSchemaPriviliges,secondSetofSchemaPriviliges,thirdSetofSchemaPriviliges,fourthSegmentWithButtonsVbox,fifthSegmentWithButtonsVbox);
		schemaPrivilegesVbox.getChildren().addAll(existingSchemaPriviligesVBox,userAccessSchemaDescriptionHbox,userAccessSchemaPriviligeshBox);  // main vbox
		
		return schemaPrivilegesVbox;
		
	}
	
	private VBox addclientConnectionThreadDetails(HashMap<String,String> allVariables,TableView tableView) {
			
		VBox clientConnectionsVBox = new VBox();
		clientConnectionsVBox.setSpacing(10);
		//clientConnectionsVBox.setPadding(new Insets(0,0,0,0));
		clientConnectionsVBox.getChildren().add(addTopHBoxForInfo("Client Connections"));
		
		HBox clientConnectionsThreadsDescriptionFirstHBox = new HBox();
		clientConnectionsThreadsDescriptionFirstHBox.setSpacing(30);
		clientConnectionsThreadsDescriptionFirstHBox.setPadding(new Insets(0,10,0,10));
		
		Label threadsConnectedLabel = new Label("Threads Connected : "+allVariables.get("Threads_connected"));
		threadsConnectedLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label threadsRunningLabel = new Label("Threads Running : "+allVariables.get("Threads_running"));
		threadsRunningLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label threadsCreatedLabel = new Label("Threads Created : "+allVariables.get("Threads_created"));
		threadsCreatedLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label threadsCachedLabel = new Label("Threads Cached : "+allVariables.get("Threads_cached"));
		threadsCachedLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label threadsrejectedLabel = new Label("Rejected : "+allVariables.get("Mysqlx_connections_rejected"));
		threadsrejectedLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));

		clientConnectionsThreadsDescriptionFirstHBox.getChildren().addAll(threadsConnectedLabel,threadsRunningLabel,
				threadsCreatedLabel,threadsrejectedLabel);

		HBox clientConnectionsThreadsDescriptionSecondHBox = new HBox();
		clientConnectionsThreadsDescriptionSecondHBox.setSpacing(30);
		clientConnectionsThreadsDescriptionSecondHBox.setPadding(new Insets(0,10,0,10));
		
		Integer connection_errors_accept = Integer.parseInt(allVariables.get("Connection_errors_accept"));
		Integer connection_errors_internal = Integer.parseInt(allVariables.get("Connection_errors_internal"));
		Integer connection_errors_max_connections = Integer.parseInt(allVariables.get("Connection_errors_max_connections"));
		Integer connection_errors_select = Integer.parseInt(allVariables.get("Connection_errors_select"));
		Integer connection_errors_tcpwrap = Integer.parseInt(allVariables.get("Connection_errors_tcpwrap"));
		Integer totalErrors = connection_errors_accept+connection_errors_internal+connection_errors_max_connections+connection_errors_select+connection_errors_tcpwrap;

		Label totalConnectionsLabel = new Label("Total Connections : "+allVariables.get("connections"));
		totalConnectionsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label connectionsLimitLabel = new Label("Connections Limit : "+allVariables.get("max_connections"));
		connectionsLimitLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label abortedClientsLabel = new Label("Aborted Clients : "+allVariables.get("Aborted_clients"));
		abortedClientsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		Label abortedConnectionsLabel = new Label("Aborted Connections  : "+allVariables.get("Aborted_connects"));
		abortedConnectionsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		
		HBox totalErrorsTooltopHBox = new HBox();
		totalErrorsTooltopHBox.setSpacing(10);
		Label totalErrorsLabel = new Label("Errors : "+totalErrors);
		totalErrorsLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
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
        processListTableViewVbox.getChildren().add(tableView);
        processListTableViewVbox.setMaxHeight(350);
        processListTableViewVbox.setPadding(new Insets(5,5,5,5));
        
        
        HBox clientConnectionsButtonsHBox = new HBox();
        clientConnectionsButtonsHBox.setSpacing(500);
        clientConnectionsButtonsHBox.setPadding(new Insets(10,10,10,10));
        
        HBox clientConnectionRefreshButtonshbox = new HBox();
        clientConnectionRefreshButtonshbox.setSpacing(10);
        Label clientConnectionRefreshLabel = new Label("Refresh Rate :");
        ComboBox refreshRateComboBox = new ComboBox();
        refreshRateComboBox.getItems().addAll("Don't Refresh","0.5 Seconds","1 Seconds","2 Seconds","3 Seconds");
        refreshRateComboBox.setValue("Don't Refresh");
        clientConnectionRefreshButtonshbox.getChildren().addAll(clientConnectionRefreshLabel,refreshRateComboBox);
        
        HBox clientConnectionKillButtonshbox = new HBox();
        clientConnectionKillButtonshbox.setSpacing(10);
        Button killQuerysButton = new Button("Kill Query(s)");
        Button killConnectionsButton = new Button("Kill Connection(s)");
        Button refreshButton = new Button("Refresh");
        clientConnectionKillButtonshbox.getChildren().addAll(killQuerysButton,killConnectionsButton,refreshButton);
        
        clientConnectionsButtonsHBox.getChildren().addAll(clientConnectionRefreshButtonshbox,clientConnectionKillButtonshbox);
        
        HBox clientConnectionsHideButtonsHBox = new HBox();
        clientConnectionsHideButtonsHBox.setSpacing(50);
        clientConnectionsHideButtonsHBox.setPadding(new Insets(0,10,0,10));
        CheckBox hideSleepingConnectionsCheckBox = new CheckBox("Hide Sleeping Connections");
        CheckBox hideBackgroundThreadsCheckBox = new CheckBox("Hide Background Threads");
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
		serverStatusVBox.setSpacing(10);
		serverStatusVBox.getChildren().add(addTopHBoxForInfo("Server Status"));
		
		HBox serverRunningDescriptionHbox = new HBox();
		serverRunningDescriptionHbox.setPadding(new Insets(0,0,0,300));
		Label serverRunningDescriptionLabel = new Label("Server Status : Running");
		serverRunningDescriptionLabel.setTextFill(Color.GREEN);
		serverRunningDescriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,18));
		serverRunningDescriptionHbox.getChildren().add(serverRunningDescriptionLabel);
		
		HBox instanceDetailsHBox = new HBox();
		instanceDetailsHBox.setSpacing(200);
		instanceDetailsHBox.setPadding(new Insets(0,10,0,10));
		
		GridPane instanceDetailsGridPane = new GridPane();
		//instanceDetailsServerDirectoriesGridPane.setPadding(new Insets(20,10,20,10));
		instanceDetailsGridPane.setVgap(10);
		instanceDetailsGridPane.setHgap(10);
		Label connectionDecriptionLabel = new Label(connectionPlaceHolder.getConnectionName() + " " + "Instance Details");
		connectionDecriptionLabel.setTextFill(Color.BLUEVIOLET);
		connectionDecriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,16));
		GridPane.setConstraints(connectionDecriptionLabel, 0, 0);   // column 0 row 0
		Label hostDecriptionLabel = new Label("Host : " );
		GridPane.setConstraints(hostDecriptionLabel, 0, 1);
		Label hostValueLabel = new Label(allVariables.get("hostname"));
		hostValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(hostValueLabel, 1, 1);
		Label socketDecriptionLabel = new Label("Socket :");
		GridPane.setConstraints(socketDecriptionLabel, 0, 2);
		Label socketValueLabel = new Label(allVariables.get("socket"));
		socketValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(socketValueLabel, 1, 2);
		Label portDecriptionLabel = new Label("Port :");
		GridPane.setConstraints(portDecriptionLabel, 0, 3);
		Label portValueLabel = new Label(allVariables.get("port"));
		portValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(portValueLabel, 1, 3);
		Label versionDecriptionLabel = new Label("Version :");
		GridPane.setConstraints(versionDecriptionLabel, 0, 4);
		Label versionValueLabel = new Label(allVariables.get("version"));
		versionValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(versionValueLabel, 1, 4);
		Label compiledForDescriptionLabel = new Label("Compiled For :");
		GridPane.setConstraints(compiledForDescriptionLabel, 0, 5);
		Label compiledForValueLabel = new Label(allVariables.get("version_compile_os") +"("+ allVariables.get("version_compile_machine") + ")");
		compiledForValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(compiledForValueLabel, 1, 5);
		Label runningSinceDescriptionLabel = new Label("Running Since :");
		GridPane.setConstraints(runningSinceDescriptionLabel, 0, 6);
		Label runningSinceValueLabel = new Label(allStatus.get("Uptime"));
		runningSinceValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(runningSinceValueLabel, 1, 6);
			
		instanceDetailsGridPane.getChildren().addAll(connectionDecriptionLabel,hostDecriptionLabel,hostValueLabel,socketDecriptionLabel,socketValueLabel,
		portDecriptionLabel,portValueLabel,versionDecriptionLabel,versionValueLabel,compiledForDescriptionLabel,compiledForValueLabel,runningSinceDescriptionLabel,runningSinceValueLabel);
		
		GridPane serverDirectoriesGridPane = new GridPane();
		serverDirectoriesGridPane.setVgap(10);
		serverDirectoriesGridPane.setHgap(10);
		Label serverDirectoriesLabel = new Label("Server Direcories");
		serverDirectoriesLabel.setTextFill(Color.BLUEVIOLET);
		serverDirectoriesLabel.setFont(Font.font("System Regular",FontWeight.BOLD,16));
		GridPane.setConstraints(serverDirectoriesLabel, 0, 0);   // column 0 row 0
		Label baseDirecoryDescriptionLabel = new Label("Base Directory :" );
		GridPane.setConstraints(baseDirecoryDescriptionLabel, 0, 1); 
		Label baseDirecoryValueLabel = new Label(allVariables.get("basedir"));
		baseDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(baseDirecoryValueLabel, 1, 1); 
		Label dataDirecoryDescriptionLabel = new Label("Data Directory :" );
		GridPane.setConstraints(dataDirecoryDescriptionLabel, 0, 2); 
		Label dataDirecoryValueLabel = new Label(allVariables.get("datadir"));
		dataDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(dataDirecoryValueLabel, 1, 2); 
		Label pluginDirecoryDescriptionLabel = new Label("Plugin Directory :" );
		GridPane.setConstraints(pluginDirecoryDescriptionLabel, 0, 3); 
		Label pluginDirecoryValueLabel = new Label(allVariables.get("plugin_dir"));
		pluginDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(pluginDirecoryValueLabel, 1, 3); 
		Label tempDirecoryDescriptionLabel = new Label("Temp Directory :" );
		GridPane.setConstraints(tempDirecoryDescriptionLabel, 0, 4); 
		Label tempDirecoryValueLabel = new Label(allVariables.get("tmpdir"));
		tempDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(tempDirecoryValueLabel, 1, 4); 
		Label errorLogDirecoryDescriptionLabel = new Label("Error Log :" );
		GridPane.setConstraints(errorLogDirecoryDescriptionLabel, 0, 5); 
		Label errorLogDirecoryValueLabel = new Label(allVariables.get("log_error"));
		errorLogDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(errorLogDirecoryValueLabel, 1, 5); 
		Label generalLogDirecoryDescriptionLabel = new Label("General Log :" );
		GridPane.setConstraints(generalLogDirecoryDescriptionLabel, 0, 6); 
		Label generalLogDirecoryValueLabel = new Label(allVariables.get("general_log_file"));
		generalLogDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(generalLogDirecoryValueLabel, 1, 6); 
		Label slowQueryLogDirecoryDescriptionLabel = new Label("Slow Query Log :" );
		GridPane.setConstraints(slowQueryLogDirecoryDescriptionLabel, 0, 7); 
		Label slowQueryLogDirecoryValueLabel = new Label(allVariables.get("slow_query_log_file"));
		slowQueryLogDirecoryValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(slowQueryLogDirecoryValueLabel, 1, 7);
		
		serverDirectoriesGridPane.getChildren().addAll(serverDirectoriesLabel,baseDirecoryDescriptionLabel,baseDirecoryValueLabel,dataDirecoryDescriptionLabel,dataDirecoryValueLabel,
			pluginDirecoryDescriptionLabel,pluginDirecoryValueLabel,tempDirecoryDescriptionLabel,tempDirecoryValueLabel,
				errorLogDirecoryDescriptionLabel,errorLogDirecoryValueLabel,generalLogDirecoryDescriptionLabel,generalLogDirecoryValueLabel,slowQueryLogDirecoryDescriptionLabel,slowQueryLogDirecoryValueLabel);
		
		instanceDetailsHBox.getChildren().addAll(instanceDetailsGridPane,serverDirectoriesGridPane);
		
		HBox serverFeaturesDescriptionHbox = new HBox();
		serverFeaturesDescriptionHbox.setPadding(new Insets(0,0,0,300));
		Label serverFeaturesDescriptionLabel = new Label("Available Server Features");
		serverFeaturesDescriptionLabel.setTextFill(Color.BLUEVIOLET);
		serverFeaturesDescriptionLabel.setFont(Font.font("System Regular",FontWeight.BOLD,18));
		serverFeaturesDescriptionHbox.getChildren().add(serverFeaturesDescriptionLabel);
		
		HBox serverFeaturesHBox = new HBox();
		serverFeaturesHBox.setSpacing(200);
		serverFeaturesHBox.setPadding(new Insets(0,10,00,100));
    	
    	
		GridPane serverFeaturesLeftHalfGridPane = new GridPane();
		serverFeaturesLeftHalfGridPane.setVgap(10);
		serverFeaturesLeftHalfGridPane.setHgap(10);
		
		Label performanceSchemaDescriptionLabel = new Label("Performance Schema :" );
		GridPane.setConstraints(performanceSchemaDescriptionLabel, 0, 1); 
		Label performanceScheamLabel = new Label(String.valueOf(allPlugins.containsKey("PERFORMANCE_SCHEMA")));
		performanceScheamLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(performanceScheamLabel, 1, 1);
		Label threadPoolDescriptionLabel = new Label("Thread Pool :" );
		GridPane.setConstraints(threadPoolDescriptionLabel, 0, 2); 
		Label threadPoolValueLabel = new Label(String.valueOf(allVariables.containsKey("thread_pool_size")));
		threadPoolValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(threadPoolValueLabel, 1, 2); 
		Label memcachedPluginlabel = new Label("Memcached Plugin : " );
		GridPane.setConstraints(memcachedPluginlabel, 0, 3); 
		Label memcachedPluginValuelabel = new Label(String.valueOf(allPlugins.containsKey("daemon_memcached")));
		memcachedPluginValuelabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(memcachedPluginValuelabel, 1, 3);
		Label smsynReplicationPluginlabel = new Label("Semisync Replication Plugin : " );
		GridPane.setConstraints(smsynReplicationPluginlabel, 0, 4); 
		Label smsynReplicationPluginValuelabel = new Label(String.valueOf(allPlugins.containsKey("rpl_semi_sync_source")));
		smsynReplicationPluginValuelabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(smsynReplicationPluginValuelabel, 1, 4); 
		Label sslAvailabilitylabel = new Label("SSL Availability  : " );
		GridPane.setConstraints(sslAvailabilitylabel, 0, 5); 
		Label sslAvailabilityValuelabel = new Label(String.valueOf(allVariables.containsKey("ssl_ca")));
		sslAvailabilityValuelabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(sslAvailabilityValuelabel, 1, 5);
		Label federatedlabel = new Label("FEDERATED : " );
		GridPane.setConstraints(federatedlabel, 0, 6); 
		Label federatedValuelabel = new Label(String.valueOf(allPlugins.containsKey("FEDERATED")));
		GridPane.setConstraints(federatedValuelabel, 1, 6);
		federatedValuelabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));

		serverFeaturesLeftHalfGridPane.getChildren().addAll(performanceSchemaDescriptionLabel,performanceScheamLabel,threadPoolDescriptionLabel,threadPoolValueLabel,
				memcachedPluginlabel,memcachedPluginValuelabel,smsynReplicationPluginlabel,smsynReplicationPluginValuelabel,sslAvailabilitylabel,sslAvailabilityValuelabel,
				federatedlabel,federatedValuelabel);
		
		GridPane serverFeaturesRigthtHalfGridPane = new GridPane();
		serverFeaturesRigthtHalfGridPane.setVgap(10);
		serverFeaturesRigthtHalfGridPane.setHgap(10);
		
		Label windowAuthenticationDescriptionLabel = new Label("Window Authentication :" );
		GridPane.setConstraints(windowAuthenticationDescriptionLabel, 0, 1); 
		Label windowAuthenticationValueLabel = new Label(String.valueOf(allPlugins.containsKey("authentication_windows")));
		windowAuthenticationValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(windowAuthenticationValueLabel, 1, 1);
		Label passwordValidationDescriptionLabel = new Label("Password Validation : ");
		GridPane.setConstraints(passwordValidationDescriptionLabel, 0, 2); 
		Label passwordValidationValueLabel = new Label(String.valueOf(allVariables.containsKey("validate_password.policy")));
		passwordValidationValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(passwordValidationValueLabel, 1, 2); 
		Label auditLogDescriptionLabel = new Label("Audit Log : ");
		GridPane.setConstraints(auditLogDescriptionLabel, 0, 3); 
		Label auditLogValueLabel = new Label(String.valueOf(allVariables.containsKey("audit_log_file")));
		auditLogValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(auditLogValueLabel, 1, 3); 
		Label firewallDescriptionLabel = new Label("Firewall : ");
		GridPane.setConstraints(firewallDescriptionLabel, 0, 4); 
		Label firewallValueLabel = new Label(String.valueOf(allVariables.containsKey("mysql_firewall_mode")));
		firewallValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(firewallValueLabel, 1, 4); 
		Label firewallTraceDescriptionLabel = new Label("Firewall  Trace: ");
		GridPane.setConstraints(firewallTraceDescriptionLabel, 0, 5); 
		Label firewallTraceValueLabel = new Label(String.valueOf(allVariables.containsKey("mysql_firewall_mode")));
		firewallTraceValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
		GridPane.setConstraints(firewallTraceValueLabel, 1, 5); 
		Label csvDescriptionLabel = new Label("CSV : ");
		GridPane.setConstraints(csvDescriptionLabel, 0, 6); 
		Label csvValueLabel = new Label(String.valueOf(allPlugins.containsKey("CSV")));
		csvValueLabel.setFont(Font.font("System Regular",FontWeight.BOLD,12));
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
		
		memoryUsageTreeItem.getChildren().addAll(memoryUsageTreeItemTotalMemory,memoryUsageTreeItemTotalMemoryEvent,memoryUsageTreeItemTotalMemoryUser,memoryUsageTreeItemTotalMemoryHost,memoryUsageTreeItemTotalMemoryThread);
		
		TreeItem<String> hotspotsIOTreeItem = new TreeItem<String>("Hot Spots for I/O");
		TreeItem<String> hotspotsIOTreeItemActivityReport = new TreeItem<String>("Top File I/O Activity Report");
		TreeItem<String> hotspotsIOTreeItemFileTime = new TreeItem<String>("Top I/O By File By Time");
		TreeItem<String> hotspotsIOTreeItemEventCategory = new TreeItem<String>("Top I/O By Event Category");
		TreeItem<String> hotspotsIOTreeItemTimeEventCategories = new TreeItem<String>("Top I/O In Time By Event Categories");
		TreeItem<String> hotspotsIOTreeItemTimeThread = new TreeItem<String>("Top I/O Time By Uer/Thread");
		
		hotspotsIOTreeItem.getChildren().addAll(hotspotsIOTreeItemActivityReport,hotspotsIOTreeItemFileTime,hotspotsIOTreeItemEventCategory,hotspotsIOTreeItemTimeEventCategories,hotspotsIOTreeItemTimeThread);
		
		TreeItem<String> highCostSQLTreeItem = new TreeItem<String>("High Cost SQL Statements");
		TreeItem<String> highCostSQLTreeItemStmtAnalysis = new TreeItem<String>("Analysis");
		TreeItem<String> highCostSQLTreeItemErrorsWarnings = new TreeItem<String>("With Errors or Warnings");
		TreeItem<String> highCostSQLTreeItemTableScans = new TreeItem<String>("With Full Table Scans");
		TreeItem<String> highCostSQLTreeItem95thPercentile = new TreeItem<String>("With Runtimes in 95th Percentile");
		TreeItem<String> highCostSQLTreeItemSorting = new TreeItem<String>("With Sorting");
		TreeItem<String> highCostSQLTreeItemTempTables = new TreeItem<String>("With Temp Tables");
		
		highCostSQLTreeItem.getChildren().addAll(highCostSQLTreeItemStmtAnalysis,highCostSQLTreeItemErrorsWarnings,highCostSQLTreeItemTableScans,highCostSQLTreeItem95thPercentile,highCostSQLTreeItemSorting
				,highCostSQLTreeItemTempTables);

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
		
		schemaStatisticsTreeItem.getChildren().addAll(schemaStatisticsTreeItemAutoIncrement,schemaStatisticsTreeItemFlattenedKeys,schemaStatisticsTreeItemIndex,schemaStatisticsTreeItemObject
				,schemaStatisticsTreeItemRedundantIndexes,schemaStatisticsTreeItemTableLoackWaits,schemaStatisticsTreeItemTableSatictics,schemaStatisticsTreeItemTableStatwithBuffer,schemaStatisticsTreeItemTableFullScans,schemaStatisticsTreeItemUnusedIndexes);
		
		TreeItem<String> waitTimesTreeItem = new TreeItem<String>("Wait Times");
		TreeItem<String> waitTimesTreeItemGlobalWait = new TreeItem<String>("Global Waits By Time ");
		TreeItem<String> waitTimesTreeItemUserTime = new TreeItem<String>("Wait By User By Time");
		TreeItem<String> waitTimesTreeItemHostTime = new TreeItem<String>("Wait By Host By Time");
		TreeItem<String> waitTimesTreeItemClassesTime = new TreeItem<String>("Wait Classes By Time");
		TreeItem<String> waitTimesTreeItemClassesAvgTime = new TreeItem<String>("Wait Classes By Avg Time");
		
		waitTimesTreeItem.getChildren().addAll(waitTimesTreeItemGlobalWait,waitTimesTreeItemUserTime,waitTimesTreeItemHostTime,waitTimesTreeItemClassesTime,waitTimesTreeItemClassesAvgTime);
		
		TreeItem<String> innoDBStatisticsTreeItem = new TreeItem<String>("InnoDB Statistics");
		TreeItem<String> innoDBStatisticsTreeItemSchemaStats = new TreeItem<String>("Buffer Stats By Schema");
		TreeItem<String> innoDBStatisticsTreeItemTableStats = new TreeItem<String>("Buffer Stats By Table");
		TreeItem<String> innoDBStatisticsTreeItemLockWaits = new TreeItem<String>("Lock Waits");

		innoDBStatisticsTreeItem.getChildren().addAll(innoDBStatisticsTreeItemSchemaStats,innoDBStatisticsTreeItemTableStats,innoDBStatisticsTreeItemLockWaits);

		TreeItem<String> userUtilizationTreeItem = new TreeItem<String>("User Resource Utilization");
		TreeItem<String> userUtilizationTreeItemSummary= new TreeItem<String>("User Summary");
		TreeItem<String> userUtilizationTreeItemIOStats= new TreeItem<String>("User File I/O Summary");
		TreeItem<String> userUtilizationTreeItemSummaryIOTypeStats= new TreeItem<String>("User File I/O Type Summary");
		TreeItem<String> userUtilizationTreeItemSummaryStageStats= new TreeItem<String>("User Stages Summary");
		TreeItem<String> userUtilizationTreeItemSummaryStmtTime= new TreeItem<String>("User Statement Time Summary");
		TreeItem<String> userUtilizationTreeItemSummaryStmtType= new TreeItem<String>("User Statement Type Summary");
		
		userUtilizationTreeItem.getChildren().addAll(userUtilizationTreeItemSummary,userUtilizationTreeItemIOStats,userUtilizationTreeItemIOStats,userUtilizationTreeItemSummaryIOTypeStats,userUtilizationTreeItemSummaryStageStats
				,userUtilizationTreeItemSummaryStmtTime,userUtilizationTreeItemSummaryStmtType);
		

		TreeItem<String> hostUtilizationTreeItem = new TreeItem<String>("Host Resource Utilization");
		TreeItem<String> hostUtilizationTreeItemSummary= new TreeItem<String>("Host Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryIOStats= new TreeItem<String>("Host File I/O Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryIOTypeStats= new TreeItem<String>("Host File I/O Type Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryStagesStmt= new TreeItem<String>("Host Stages Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryStmtTime= new TreeItem<String>("Host Statement Time Summary");
		TreeItem<String> hostUtilizationTreeItemSummaryStmtType= new TreeItem<String>("Host Statement Type Summary");
		
		hostUtilizationTreeItem.getChildren().addAll(hostUtilizationTreeItemSummary,hostUtilizationTreeItemSummaryIOStats,hostUtilizationTreeItemSummaryIOTypeStats,hostUtilizationTreeItemSummaryStagesStmt
				,hostUtilizationTreeItemSummaryStmtTime,hostUtilizationTreeItemSummaryStmtType);
		
		TreeItem<String> otherInformationTreeItem = new TreeItem<String>("Other Information");
		TreeItem<String> versionTreeItem = new TreeItem<String>("Version");
		TreeItem<String> sessionTreeItem = new TreeItem<String>("Session Info");
		TreeItem<String> latestFileioTreeItem = new TreeItem<String>("Latest File I/O");
		TreeItem<String> systemConfigTreeItem = new TreeItem<String>("System Config");
		TreeItem<String> sessionSSLStatusConfigTreeItem = new TreeItem<String>("Session SSL Status");
		TreeItem<String> metricsTreeItem = new TreeItem<String>("Metrics");
		TreeItem<String> processListTreeItem = new TreeItem<String>("Process List");
		TreeItem<String> checkLostInstrumentationTreeItem = new TreeItem<String>("Check Lost Instrumentation"); 
		
		otherInformationTreeItem.getChildren().addAll(versionTreeItem,sessionTreeItem,latestFileioTreeItem,systemConfigTreeItem,sessionSSLStatusConfigTreeItem,metricsTreeItem
				,processListTreeItem,checkLostInstrumentationTreeItem);
		
		rootReportsTreeItem.getChildren().addAll(memoryUsageTreeItem,hotspotsIOTreeItem,highCostSQLTreeItem,schemaStatisticsTreeItem,waitTimesTreeItem,innoDBStatisticsTreeItem
				,userUtilizationTreeItem,hostUtilizationTreeItem,otherInformationTreeItem);
		
		return performanceView;
	}

	private void displayPerformanceTableView(String[] performanceReportsTypes, String[] performanceReportQueries,String currentTreeItemSelected) {
		for( int i=0;i<performanceReportsTypes.length;i++) {
			 if(currentTreeItemSelected.equalsIgnoreCase(performanceReportsTypes[i])) {
				 int index = i;
			      System.out.println("Duble clicked on this item"+ currentTreeItemSelected);
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
			
		  try { 
			  ResultSet rs = stmt.executeQuery("select table_name,engine,auto_increment,table_rows,data_length,create_time,update_time,create_options from information_schema.tables where table_comment != 'view' and table_schema='"+loadedDatabaseName.getValue()+"'");
			  SplitPane tableDetailsSplitPane = new SplitPane();
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
			  allButtonsHBox.setSpacing(100);
			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox tableButtonsHbox = new HBox();
			  tableButtonsHbox.setSpacing(20);
			  tableButtonsHbox.setPadding(new Insets(0,50,0,0));
			  
			  Button viewTableButton = new Button("View Table");
			  Button createTableButton = new Button("Create Table");
			  Button editTableButton = new Button("Edit Table");
			  Button deleteTableButton = new Button("Delete Table");
			  
			  Button refreshTableButton = new Button("Refresh");
			  createTableButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					tableDetailsSplitPane.getItems().remove(tableButtonsHbox);
					tableDetailsSplitPane.setDividerPositions(0.99);
				}	
			  });
			  
			  tableButtonsHbox.getChildren().addAll(viewTableButton,createTableButton,editTableButton,deleteTableButton);
			  allButtonsHBox.getChildren().addAll(tableButtonsHbox,refreshTableButton);
			  
			  tableDetailsSplitPane.getItems().addAll(tablesView,allButtonsHBox);
			  databaseDetailsTablesTab.setContent(tableDetailsSplitPane);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

	private void getdatabaseViewsDisplayTab(TreeItem<String> loadedDatabaseName, Tab databaseDetailsViewsTab) {
		System.out.println("Views Tab selected ");
		  	try {		  
			  ResultSet rs = stmt.executeQuery("select table_name,check_option,is_updatable,definer,view_definition from information_schema.views where table_schema='"+loadedDatabaseName.getValue()+"'");
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
			  allButtonsHBox.setSpacing(100);
			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  
			  HBox viewButtonsHbox = new HBox();
			  viewButtonsHbox.setSpacing(20);
			  viewButtonsHbox.setPadding(new Insets(0,50,0,0));
			  
			  Button viewViewButton = new Button("View View");
			  Button createViewButton = new Button("Create View");
			  Button editViewButton = new Button("Edit View");
			  Button deleteViewButton = new Button("Delete View");
			  
			  Button refreshViewButton = new Button("Refresh");
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
		  	try {		  
			  ResultSet rs = stmt.executeQuery("select  index_name,column_name,table_name,Index_type,packed,nullable,non_unique from information_schema.statistics where table_schema = '"+loadedDatabaseName.getValue()+"'");
			  try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			  allButtonsHBox.setSpacing(300);
			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox indexButtonsHbox = new HBox();
			  indexButtonsHbox.setSpacing(20);
			  
			  Button viewIndexButton = new Button("View Index");
			  Button createIndexButton = new Button("Create Index");
			  Button editIndexButton = new Button("Edit Index");
			  Button deleteIndexButton = new Button("Delete Imdex");
			  
			  Button refreshIndexButton = new Button("Refresh");
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
		  	try {		  
			  ResultSet rs = stmt.executeQuery("select  routine_name,definer,created,LAST_ALTERED,routine_comment from information_schema.routines where  routine_type != 'FUNCTION' and  routine_schema = '"+loadedDatabaseName.getValue()+"'");
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
			  allButtonsHBox.setSpacing(300);
			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox proceduresButtonsHbox = new HBox();
			  proceduresButtonsHbox.setSpacing(20);
			  
			  Button viewProcedureButton = new Button("View Procedure");
			  Button createProcedureButton = new Button("Create Procedure");
			  Button editProcedureButton = new Button("Edit Procedure");
			  Button deleteProcedureButton = new Button("Delete Procedure");
			  
			  Button refreshProcedureButton = new Button("Refresh");
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
		  	try {		  
			  ResultSet rs = stmt.executeQuery("select  routine_name,definer,created,LAST_ALTERED,routine_comment from information_schema.routines where  routine_type != 'PROCEDURE' and  routine_schema = '"+loadedDatabaseName.getValue()+"'");
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
			  allButtonsHBox.setSpacing(300);
			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox functionsButtonsHbox = new HBox();
			  functionsButtonsHbox.setSpacing(20);
			  
			  Button viewFunctionButton = new Button("View Function");
			  Button createFunctionButton = new Button("Create Function");
			  Button editFunctionButton = new Button("Edit Function");
			  Button deleteFunctionButton = new Button("Delete Function");
			  
			  Button refreshProcedureButton = new Button("Refresh");
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
		  	try {		  
		  		
		  	  ResultSet rs = stmt.executeQuery("select trigger_name,trigger_Schema,event_manipulation,event_object_Schema,event_object_table,action_order,action_timing,definer,created from information_Schema.triggers where trigger_Schema = '"+loadedDatabaseName.getValue()+"'");
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
			  allButtonsHBox.setSpacing(300);
			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox triggersButtonsHbox = new HBox();
			  triggersButtonsHbox.setSpacing(20);
			  
			  Button viewTriggersButton = new Button("View Trigger");
			  Button createTriggersButton = new Button("Create Trigger");
			  Button editTriggersButton = new Button("Edit Trigger");
			  Button deleteTriggersButton = new Button("Delete Trigger");
			  
			  Button refreshTriggersButton = new Button("Refresh");
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
		mainDatabaseTab.setText( loadedDatabaseName.getValue());
		mainDatabaseTab.setGraphic(this.imagemySqlnode);
		
		TabPane databaseTabPane = new TabPane();
		databaseTabPane.setTabMinWidth(200);	
		
		Tab databaseDetails = new Tab("Details");
		databaseDetails.setClosable(false);
		
		Tab databaseDetailsPropertiesTab = new Tab("Properties");
		databaseDetailsPropertiesTab.setClosable(false);
		Tab databaseDetailsTablesTab = new Tab("Tables");
		databaseDetailsTablesTab.setClosable(false);
		Tab databaseDetailsViewsTab = new Tab("Views");
		databaseDetailsViewsTab.setClosable(false);
		Tab databaseDetailsIndexesTab = new Tab("Indexes");
		databaseDetailsViewsTab.setClosable(false);
		Tab databaseDetailsProceduresTab = new Tab("Procedures");
		databaseDetailsProceduresTab.setClosable(false);
		Tab databaseDetailsFunctionsTab = new Tab("Functions");
		databaseDetailsFunctionsTab.setClosable(false);
		Tab databaseDetailsTriggersTab = new Tab("Triggers");
		databaseDetailsTriggersTab.setClosable(false);
		Tab databaseDetailsEventsTab = new Tab("Events");
		databaseDetailsEventsTab.setClosable(false);
		
		TabPane databaseDetailsTabPane = new TabPane();
		databaseDetailsTabPane.setTabMinWidth(100);
		databaseDetailsTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					 // System.out.println("Old Tab Selected -->"+oldValue.getText());
					  System.out.println("New Tab Selected -->"+newValue.getText());
					  
					  if(newValue.getText().equalsIgnoreCase("Properties")) {
						  System.out.println("Properties Tab selected ");
					  }
					  if(newValue.getText().equalsIgnoreCase("Tables")) {
						  getdatabaseTablesDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
					  }
					  if(newValue.getText().equalsIgnoreCase("Views")) {
						  getdatabaseViewsDisplayTab(loadedDatabaseName, databaseDetailsViewsTab);
					  }
					  
					  if(newValue.getText().equalsIgnoreCase("Indexes")) {
						  getdatabaseIndexesDisplayTab(loadedDatabaseName, databaseDetailsIndexesTab);
					  }
					  if(newValue.getText().equalsIgnoreCase("Procedures")) {
						  getdatabaseProceduresDisplayTab(loadedDatabaseName, databaseDetailsProceduresTab);
					  }
					  if(newValue.getText().equalsIgnoreCase("Functions")) {
						  getdatabaseFunctionsDisplayTab(loadedDatabaseName, databaseDetailsFunctionsTab);
					  }
					  if(newValue.getText().equalsIgnoreCase("Triggers")) {
						  getdatabaseTriggersDisplayTab(loadedDatabaseName, databaseDetailsTriggersTab);
					  }
					  if(newValue.getText().equalsIgnoreCase("Events")) {
						  getdatabaseEventsDisplayTab(loadedDatabaseName, databaseDetailsEventsTab);
					  }
				}
		  });
		
	   databaseDetailsTabPane.getTabs().addAll(databaseDetailsPropertiesTab,databaseDetailsTablesTab,databaseDetailsViewsTab,databaseDetailsIndexesTab,
				databaseDetailsProceduresTab,databaseDetailsFunctionsTab,databaseDetailsTriggersTab,databaseDetailsEventsTab);
				 
	   if(componentToFocus.equals("Tables")) {
			 getdatabaseTablesDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(1);
	   }
	   if(componentToFocus.equals("Views")) {
		   getdatabaseViewsDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(2);
	   }
	   if(componentToFocus.equals("Indexes")) {
		   getdatabaseIndexesDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(3);
	   }
	   if(componentToFocus.equals("Procedures")) {
		   getdatabaseProceduresDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(4);
	   }	
	   if(componentToFocus.equals("Functions")) {
		    getdatabaseFunctionsDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(5);
	   }
	   if(componentToFocus.equals("Triggers")) {
		    getdatabaseTriggersDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(6);
	   }
	   if(componentToFocus.equals("Events")) {
		    getdatabaseEventsDisplayTab(loadedDatabaseName, databaseDetailsTablesTab);
			 databaseDetailsTabPane.getSelectionModel().select(7);
	   }
	   
	   databaseDetails.setContent(databaseDetailsTabPane);
		
	   Tab databaseERDiagram = new Tab("ER Diagram");
	   databaseERDiagram.setClosable(false);
	   Tab databaseGrahicsStats = new Tab("Graphics Stats");
	   databaseGrahicsStats.setClosable(false);
	   Tab databaseAIPrompt = new Tab("AI Prompt");
	   databaseAIPrompt.setClosable(false);				
	   databaseTabPane.getTabs().addAll(databaseDetails,databaseERDiagram,databaseGrahicsStats,databaseAIPrompt);
	   mainDatabaseTab.setContent(databaseTabPane);
       return mainDatabaseTab;
	}

	private void getdatabaseEventsDisplayTab(TreeItem<String> loadedDatabaseName, Tab databaseDetailsEventsTab) {
		System.out.println("Events Tab selected ");
		  	try {		  
			  ResultSet rs = stmt.executeQuery("select  event_name,definer,event_type,interval_value,interval_field,starts,created,last_altered,last_executed from information_Schema.events where event_schema ='"+loadedDatabaseName.getValue()+"'");
			  
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
			  allButtonsHBox.setSpacing(300);
			  allButtonsHBox.setPadding(new Insets(10,10,0,100));
			  
			  HBox eventsButtonsHbox = new HBox();
			  eventsButtonsHbox.setSpacing(20);
			  
			  Button viewEventButton = new Button("View Event");
			  Button createEventButton = new Button("Create Event");
			  Button editEventButton = new Button("Edit Event");
			  Button deleteEventButton = new Button("Delete Event");
			  
			  Button refreshEventsButton = new Button("Refresh");
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



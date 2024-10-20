package com.openfx.ui;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openjfx.fx.Menu_Items_FX;
import org.openjfx.fx.TableSample3.Person;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
// import javafx.scene.web.HTMLEditor;
import javafx.util.Callback;

public class MySqlUI {
	
	public Menu_Items_FX menu_Items_FX;
	public NewMenuItemEventHandler newMenuItemEventHandler;
	
	private ConnectionPlaceHolder connectionPlaceHolder;
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
	public VBox secondHalfDisplayVBox;
	  
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
													 
													 TreeItem<String> mySqlTreeItemProcedures = new TreeItem<String>("Procedures");
													 TreeItem<String> loadingTreeItemProcedures = new TreeItem<String>("Loading..");
													 mySqlTreeItemProcedures.getChildren().add(loadingTreeItemProcedures);
													 
													 TreeItem<String> mySqlTreeItemFunctions = new TreeItem<String>("Functions");
													 TreeItem<String> loadingTreeItemFunctions = new TreeItem<String>("Loading..");
													 mySqlTreeItemFunctions.getChildren().add(loadingTreeItemFunctions);
													 
													 TreeItem<String> mySqlTreeItemEvents = new TreeItem<String>("Events");
													 TreeItem<String> loadingTreeItemEvents = new TreeItem<String>("Loading..");
													 mySqlTreeItemEvents.getChildren().add(loadingTreeItemEvents);
													  
													 loadedDatabaseName.getChildren().add(mySqlTreeItemTables);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemViews);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemProcedures);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemFunctions);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemEvents);
													 
													 //Database expanded
													 loadedDatabaseName.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																
																System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																Boolean value = ((BooleanProperty)observable).getValue() ;
																if(value) {
																	System.out.println("Particular Databse Expanded !!!"+loadedDatabaseName.getValue());
																}
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
																		    		ResultSet rs = stmt.executeQuery("SHOW FULL TABLES IN "+ loadedDatabaseName.getValue() +" WHERE TABLE_TYPE LIKE 'BASE TABLE'");
																		    		try {
																						Thread.sleep(1000);
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
																					  
																					  TreeItem<String> ViewName = new TreeItem<String>(rs.getString(1));
																					  
																					  mySqlTreeItemViews.getChildren().add(ViewName);
																					
																				}
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
													 
													 // Procedures 
													 mySqlTreeItemProcedures.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																	
																	System.out.println("Its Procedures expansion!!!"); // from here fix procedures
																	
																	// get the database name and display its tables 
																	TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																	

																	System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																	
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
																					  System.out.println(rs.getString(1));
																					  System.out.println(rs.getString(2));
																					  mySqlTreeItemProcedures.getChildren().add(new TreeItem<String>(rs.getString(2)));
																					
																				}
																			} catch (SQLException e) {
																				// TODO Auto-generated catch block
																				e.printStackTrace();
																			}
																	     }
																	}).start();		
															}
													 });
													 
													 //Functions
													 mySqlTreeItemFunctions.expandedProperty().addListener(new ChangeListener<Boolean>() {
															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
														
																System.out.println("observable : "+ "Bean : "+ ((TreeItem<String>)((BooleanProperty)observable).getBean()).getValue()     +" Name : "+((BooleanProperty)observable).getName() +" Value :" +((BooleanProperty)observable).getName() );
																
																System.out.println("Its Functions expansion!!!"); // from here fix procedures
																
																// get the database name and display its tables 
																TreeItem<String> currentDatabasebean = ((TreeItem<String>)((BooleanProperty)observable).getBean());
																

																System.out.println("Current DatabaseSelected Name "+loadedDatabaseName.getValue());
																
																new Thread(new Runnable() {
																     @Override
																     public void run() {
																         
																    	try  {
																    		stmt.execute("use "+loadedDatabaseName.getValue());
																    		ResultSet rs = stmt.executeQuery(" SHOW FUNCTION STATUS WHERE Db = '"+ loadedDatabaseName.getValue() +"'");
																    		try {
																				Thread.sleep(1000);
																			} catch (InterruptedException e) {
																				// TODO Auto-generated catch block
																				e.printStackTrace();
																			}
																    		mySqlTreeItemFunctions.getChildren().remove(0);  // Remove the Loading...
																			while(rs.next()) {
																				  System.out.println(rs.getString(1));
																				  System.out.println(rs.getString(2));
																				  mySqlTreeItemFunctions.getChildren().add(new TreeItem<String>(rs.getString(2)));
																				
																			}
																		} catch (SQLException e) {
																			// TODO Auto-generated catch block
																			e.printStackTrace();
																		}
																     }
																}).start();		
																
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
																	    		stmt.execute("use "+loadedDatabaseName.getValue());
																	    		ResultSet rs = stmt.executeQuery("SHOW EVENTS FROM "+loadedDatabaseName.getValue());
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
									    		/* try {
													Thread.sleep(1000);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} */
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
									    		/*
									    		System.out.println("Error Code is :"+ e.getErrorCode());
									    		System.out.println("SQL State is "+ e.getSQLState());
									    		System.out.println("Get Cause"+ e.getCause());
									    		System.out.println("Get Localized Message" +e.getLocalizedMessage());
									    		System.out.println("Get MEssage"+ e.getMessage());
									    		*/
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
		});
								
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
		TreeItem<String> mySqlTreeItemSystemInfoGrants = new TreeItem<String>("GRANTS");
		mySqlTreeItemSystemInfo.getChildren().add(mySqlTreeItemSystemInfoGrants);
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
		mySqlTreeItem.getChildren().add(mySqlTreeItemUsers);
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Session Manager")) {   // Session Manager is double clicked
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
							    	
							    //	 HTMLEditor htmlEditor = new HTMLEditor();

							         VBox vBox = new VBox();
							         
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(vBox); // bottom half of query editer
							        
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("BINARY LOGS")) {   
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	ResultSet rs = stmt.executeQuery(" SHOW BINARY LOGS");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}

									Tab sessionManagerTab = new Tab("BINARY LOGS " + connectionPlaceHolder.getConnectionName());
									
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
				 }
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
				 }
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("WARNINGS")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	ResultSet rs = stmt.executeQuery(" SHOW WARNINGS");
							    	try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
									}
									Tab sessionManagerTab = new Tab("WARNINGS " + connectionPlaceHolder.getConnectionName());									
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Server Status")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {
							    	
							    	HashMap<String,String> allVariables = new HashMap<String,String>(); 
							    	ResultSet rsVariables = stmt.executeQuery(" SHOW VARIABLES");
							    	while(rsVariables.next()) {
							    		allVariables.put(rsVariables.getString(1), rsVariables.getString(2));
							    	}
							    	
							    	HashMap<String,String> allStatus = new HashMap<String,String>();
							    	stmt = innercurrentConnection.createStatement();
							    	ResultSet rsStatus = stmt.executeQuery(" SHOW STATUS");
							    	while(rsStatus.next()) {
							    		allStatus.put(rsStatus.getString(1), rsStatus.getString(2));
							    	}
							    	
							    	HashMap<String,String> allPlugins = new HashMap<String,String>();
							    	stmt = innercurrentConnection.createStatement();
							    	ResultSet rsPlugins = stmt.executeQuery(" SHOW PLUGINS");
							    	while(rsPlugins.next()) {
							    		allPlugins.put(rsPlugins.getString(1), rsPlugins.getString(2));
							    	}
							    	
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	String hostName = "";
							    	String socket = "comes from ini";
							    	String port = "";
							    	String version = "";
							    	String compliledFor = "";
							    	String configurationFile = "Use Files Path to find this in the System installation";
							    	String runningSince = "";
							    	System.out.println("Connection Name :"+ connectionName);
							    	System.out.println("Host Name :"+ allVariables.get("hostname"));
							    	System.out.println("Socket :"+ allVariables.get("socket"));
							    	System.out.println("Port :"+ allVariables.get("port"));
							    	System.out.println("Version :"+ allVariables.get("version") + "("+ allVariables.get("version_comment") + ")");
							    	System.out.println("Compiled For :"+ allVariables.get("version_compile_os") +"("+ allVariables.get("version_compile_machine") + ")");
							    	System.out.println("Confuguration File :"+ configurationFile);
							    	System.out.println("Running Since :"+ allStatus.get("Uptime"));
							    	
							    	
							    	System.out.println("--------Avaialable Server Features-----START--------");
							    	Boolean performanceSchema = false;   // check under plugins PERFORMANCE_SCHEMA ACTIVE
							    	Boolean windowsAuthentication = false;  // check under plugins  authentication_windows  ACTIVE
							    	Boolean threadPool = false;   // is for enterprice version can confirm using "thread_pool_size" in variables
							    	Boolean passwordValidation = false;  // check in variables 'validate_password.policy';
							    	Boolean memcachedPlugin = false;  //daemon_memcached variable/PLUGINS ,  plugin is only supported on Linux, Solaris, and macOS platforms. Other operating systems are not supported.
							    	Boolean auditLog = false;  // If the audit log plugin is enabled, it exposes several system variables that permit control over logging audit_log_file
							    	Boolean semisyncReplicationPulgin = false;  // rpl_semi_sync_source plugin or rpl_semi_sync_source_enabled/rpl_semi_sync_replica_enabled variables
							    	Boolean firewall = false;  // mysql_firewall_mode entry in global variables;
							    	Boolean sslAvaialblity = false;  // ssl_ca variable existance
							    	Boolean firewallTrace = false; // mysql_firewall_mode entry in global variables;
							    	Boolean csv = false; //  check under plugins  CSV  ACTIVE  https://dev.mysql.com/doc/refman/8.4/en/csv-storage-engine.html
							      	Boolean federated = false; //  check under plugins  FEDERATED  ACTIVE  
							    	
							    	System.out.println("Performance Schema : "+ allPlugins.containsKey("PERFORMANCE_SCHEMA"));
							    	System.out.println("Window Authentication : "+ allPlugins.containsKey("authentication_windows"));
							    	System.out.println("Thread Pool : "+ allVariables.containsKey("thread_pool_size"));
							    	System.out.println("Password Validation : "+ allVariables.containsKey("validate_password.policy"));
							    	System.out.println("Thread Pool : "+ allVariables.containsKey("thread_pool_size"));
							    	System.out.println("Memcached Plugin : "+ allPlugins.containsKey("daemon_memcached"));
							    	System.out.println("Audit Log : "+ allVariables.containsKey("audit_log_file"));
							    	System.out.println("Semisync Replication Plugin : "+ allPlugins.containsKey("rpl_semi_sync_source") +" : "+ allPlugins.containsKey("rpl_semi_sync_replica_enabled"));
							    	System.out.println("Firewall  : "+ allVariables.containsKey("mysql_firewall_mode"));
							    	System.out.println("SSL Availability  : "+ allVariables.containsKey("ssl_ca"));
							    	System.out.println("Firewall  Trace: "+ allVariables.containsKey("mysql_firewall_mode"));
							    	System.out.println("CSV : "+ allPlugins.containsKey("CSV"));
							    	System.out.println("FEDERATED : "+ allPlugins.containsKey("FEDERATED"));
							    	System.out.println("--------Avaialable Server Features-----END--------");
							    	
							    	File file = new File(allVariables.get("basedir"));
								 	System.out.println("Total Space : "+file.getTotalSpace()/(1024*1024*1024)+" GB");
							        System.out.println("Free Space : "+file.getFreeSpace()/(1024*1024*1024)+" GB");							         
							        System.out.println("Usable Space : "+file.getUsableSpace()/(1024*1024*1024)+" GB");
							        String diskSpaceInDataDirectory = file.getUsableSpace()/(1024*1024*1024)+" GB" + " of " + file.getTotalSpace()/(1024*1024*1024)+" GB "+ "available";
							        
							    	System.out.println("------Server Directories------START----");
							    	System.out.println("Base Directory :"+allVariables.get("basedir"));
							    	System.out.println("Data Directory :"+allVariables.get("datadir"));
							    	System.out.println("Disk Space In Data Directory :"+diskSpaceInDataDirectory);
							    	System.out.println("Pluins Directory :"+allVariables.get("plugin_dir"));
							    	System.out.println("Temp Directory :"+allVariables.get("tmpdir"));
							    	System.out.println("Error Log :"+allVariables.get("log_error"));
							    	System.out.println("General Log :"+allVariables.get("general_log"));
							    	System.out.println("General Log File:"+allVariables.get("general_log_file"));
							    	System.out.println("Slow Query Log :"+allVariables.get("slow_query_log_file"));
							    	System.out.println("------Server Directories------END----");
							    	
							    	System.out.println("------Replica------START----");
							    	if(Integer.parseInt(allVariables.get("server_id")) > 1)
							    		System.out.println("Replica : "+allVariables.get("server_id"));
							    	else
							    		System.out.println("This is not a Replica : ");
							    	System.out.println("------Replica------END----");
							    	
							    	System.out.println("------Authentication------START----");
							    	String sha256PasswordPrivateKey = "";
							    	String sha256PasswordPublicKey = "";
							    	System.out.println("SHA 256 Password Private Key : "+allVariables.get("sha256_password_private_key_path"));
							    	System.out.println("SHA 256 Password Public Key : "+allVariables.get("sha256_password_public_key_path"));
							    	System.out.println("------Authentication------END----");
							    	
							    	System.out.println("------Security SSL------START----");
							    	String sslCA = "";
							    	String sslCAPath = "";
							    	String sslCACert = "";
							    	String sslCipher = "";
							    	String sslCRL = "";
							    	String sslCRLPath = "";
							    	String sslKey = "";
							    	System.out.println("SSL CA : "+allVariables.get("ssl_ca"));
							    	System.out.println("SSL CA Path : "+allVariables.get("ssl_capath"));
							    	System.out.println("SSL CA Cert : "+allVariables.get("ssl_cert"));
							    	System.out.println("SSL Cipher : "+allVariables.get("ssl_cipher"));
							    	System.out.println("SSL CRL : "+allVariables.get("ssl_crl"));
							    	System.out.println("SSL CRL Path : "+allVariables.get("ssl_crlpath"));
							    	System.out.println("SSL Key : "+allVariables.get("ssl_key"));
							    	System.out.println("------Security SSL------END----");
							    	
									Tab sessionManagerTab = new Tab("SERVER STATUS " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView<String> resultAsTableView =   new TableView<String>();   //showResultSetInTheTableView(rs);
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Client Connections")) { 
				      System.out.println("Double clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
							    	
							    	HashMap<String,String> allVariables = new HashMap<String,String>(); 
							    	ResultSet rsVariables = stmt.executeQuery(" SHOW VARIABLES");
							    	while(rsVariables.next()) {
							    		allVariables.put(rsVariables.getString(1), rsVariables.getString(2));
							    	}
							    
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	
							    	System.out.println("Connection Name :"+ connectionName);
							    	System.out.println("Threads Connected :"+ allVariables.get("Threads_connected"));
							    	System.out.println("Threads Running :"+ allVariables.get("Threads_running"));
							    	System.out.println("Threads Created :"+ allVariables.get("Threads_created"));
							    	System.out.println("Threads Cached :"+ allVariables.get("Threads_cached"));
							    	System.out.println("Rejected :"+ allVariables.get("Mysqlx_connections_rejected"));
							    	System.out.println("Total Connections :"+ allVariables.get("connections"));
							    	System.out.println("Connections Limit :"+ allVariables.get("max_connections"));
							    	System.out.println("Aborted Clients :"+ allVariables.get("Aborted_clients"));
							    	System.out.println("Aborted Connections :"+ allVariables.get("Aborted_connects"));
							    	System.out.println("Errors :"+ "Need to add below and hihgligt in pop-up");
							    	System.out.println("Connection_errors_accept :"+ allVariables.get("Connection_errors_accept"));
							    	System.out.println("Connection_errors_internal :"+ allVariables.get("Connection_errors_internal"));
							    	System.out.println("Connection_errors_max_connections :"+ allVariables.get("Connection_errors_max_connections"));
							    	System.out.println("Connection_errors_select :"+ allVariables.get("Connection_errors_select"));
							    	System.out.println("Connection_errors_tcpwrap :"+ allVariables.get("Connection_errors_tcpwrap"));
							    	
							    	System.out.println("Hide Sleeping threads...");
							    	System.out.println("Hide BackGround threads...");
							    	System.out.println("Hide ForeGround threads...");
							    	
							    	HashMap<String,String> allStatus = new HashMap<String,String>();
							    	stmt = innercurrentConnection.createStatement();
							    	ResultSet rsThreads = stmt.executeQuery("SELECT th.PROCESSLIST_ID,th.PROCESSLIST_USER,th.PROCESSLIST_HOST,th.PROCESSLIST_DB,th.PROCESSLIST_COMMAND,th.PROCESSLIST_TIME,th.PROCESSLIST_STATE,th.THREAD_ID,th.TYPE,th.NAME,th.PARENT_THREAD_ID,th.INSTRUMENTED,th.PROCESSLIST_INFO,\r\n"
							    			+ "attr.ATTR_NAME,attr.ATTR_VALUE FROM performance_schema.threads th  LEFT OUTER JOIN performance_schema.session_connect_attrs attr ON th.processlist_id = attr.processlist_id AND (attr.attr_name IS NULL OR attr.attr_name = 'program_name') WHERE th.TYPE <> 'BACKGROUND'");
							    	
							    	
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
									resultAsTableView.setMaxWidth(250);
									
									BorderPane userAccountsBorderPane = new BorderPane();
									VBox vboxLeft = new VBox();
									vboxLeft.getChildren().add(resultAsTableView);
									vboxLeft.getChildren().add(new Button("Add Account"));
									
									userAccountsBorderPane.setTop(addTopHBoxForUserAndPrevileges());
									userAccountsBorderPane.setLeft(vboxLeft);
									
									/*
									SplitPane editerTabSplitPane = new SplitPane();
							        editerTabSplitPane.setOrientation(Orientation.VERTICAL);
							    	editerTabSplitPane.setDividerPositions(0.67);  // split pane divider moving a bit lower
							    	
							        editerTabSplitPane.getItems().add(resultAsTableView); // Top half of query editer
							        editerTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
							        */
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Status and System Variables")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
		
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	System.out.println("Connection Name :"+ connectionName);
							    	
							    	BorderPane mainPopUpborderPane = new BorderPane();
									HBox topHbox = addTopHBoxForVariables();
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
							        secondHalfDisplayVBox = new VBox();
							        editerTabSplitPane.getItems().add(secondHalfDisplayVBox); // bottom half of query editer
							        
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
				 if(event.getClickCount() == 2 && getTreeItem().getValue().equalsIgnoreCase("Server Logs")) { 
				      System.out.println("Duble clicked on this item"+ getTreeItem().getValue());
						Platform.runLater(new Runnable() {
							  @Override
							  public void run() { 
							    try  {	
	
							    	
							    	ResultSet rsbinarLogs = stmt.executeQuery("show binary logs");
							    	
							    	HashMap<String,String> sessionVariables = new HashMap<String,String>(); 
							    	ResultSet rsVariables = stmt.executeQuery(" SHOW SESSION VARIABLES");
							    	while(rsVariables.next()) {
							    		sessionVariables.put(rsVariables.getString(1), rsVariables.getString(2));
							    	}
							    	
							    	/*
							    	general_log - OFF
							    	general_log_file - hostname.log

							    	log_error - /var/log/mysql/error.log
							    	slow_query_log - ON def 
							    	slow_query_log_file - /var/lib/mysql/husnain-slow.log
									
									log_bin - ON def
							    	log_bin_basename - 
							    	log_bin_index - 
							    	relay_log - hostname-relay-bin
							    	relay_log_basename  - /var/lib/mysql/husnain-relay-bin 
							    	relay_log_index -
							    	relay_log_info_file - 
									*/
							    	
							    	System.out.println("general_log :"+ sessionVariables.get("general_log"));
							    	System.out.println("general_log_file :"+ sessionVariables.get("general_log_file"));
							    	System.out.println("log_error :"+ sessionVariables.get("log_error"));
							    	System.out.println("slow_query_log :"+ sessionVariables.get("slow_query_log"));
							    	System.out.println("slow_query_log_file :"+ sessionVariables.get("slow_query_log_file"));
							    	System.out.println("log_bin :"+ sessionVariables.get("log_bin"));
							    	System.out.println("log_bin_basename :"+ sessionVariables.get("log_bin_basename"));
							    	System.out.println("log_bin_index :"+ sessionVariables.get("log_bin_index"));
							    	System.out.println("relay_log :"+ sessionVariables.get("relay_log"));
							    	System.out.println("relay_log_basename :"+ sessionVariables.get("relay_log_basename"));
							    	System.out.println("relay_log_index :"+ sessionVariables.get("relay_log_index"));
							    	System.out.println("relay_log_info_file :"+ sessionVariables.get("relay_log_info_file"));
							    	
							    								    	
							    	String connectionName = connectionPlaceHolder.getConnectionName();
							    	System.out.println("Connection Name :"+ connectionName);
							    	
									Tab sessionManagerTab = new Tab("Server Logs " + connectionPlaceHolder.getConnectionName());									
									sessionManagerTab.setOnClosed(new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											System.out.println("On Tab close request ");
											// sessionManagerTab = null;
											menu_Items_FX.alltabbedEditors.getTabs().remove(sessionManagerTab);
										}
									});
									
									TableView resultAsTableView = showResultSetInTheTableView(rsbinarLogs);
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

		if(rs.next()) {
	
	    	
	    	TableColumn<Map, String> tableColumnName;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	       
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnName(i+1);
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
		
		if(rs.next()) {
	
	    	TableColumn<Map, String> tableColumnName;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	       
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnName(i+1);
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
	
	private TableView showResultSetInTheTableView(ResultSet rs,String variables)  throws SQLException{
		
		TableView tableView = new TableView();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the last empty column otherwise added
		tableView.setEditable(true);
	
        
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HashMap<String,String>>() {

			@Override
			public void changed(ObservableValue<? extends HashMap<String, String>> observable,
					HashMap<String, String> oldValue, HashMap<String, String> newValue) {

				secondHalfDisplayVBox.getChildren().clear();
				System.out.println("oldValue --->"+oldValue);
				System.out.println("newValue --->"+newValue.keySet().toString());
				
				// Do the alignment here , along with is Editable entry by doing look up to MySQLConstants enum
				
				for(Map.Entry<String, String> tableValues : newValue.entrySet()) {
					System.out.println( tableValues.getKey()+ " "+ tableValues.getValue());
					
					
					secondHalfDisplayVBox.getChildren().add(new Label(tableValues.getKey()+ " "+ tableValues.getValue()));
				}
				
				
				TableViewSelectionModel  selectionModel = tableView.getSelectionModel();
		        ObservableList selectedCells = selectionModel.getSelectedCells();
		        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		        Object val = tablePosition.getTableColumn().getCellData(newValue);
		        System.out.println("Selected Value" + val);
				
			}	
		});
		if(rs.next()) {
	
	    	TableColumn<Map, String> tableColumnName;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	       
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnName(i+1);
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
	    	tableColumnName = new TableColumn<>("Description");
        	tableColumnName.setCellValueFactory(new MapValueFactory<>("Description"));
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
		
	       do {
				  
	        	String[] columnValues = new String[md.getColumnCount()];
	        	tableRowValue = new HashMap<>();
	        	for (int i = 0; i < columnNames.length; i++) {
	        		columnValues[i] =  rs.getString(i+1); 
	        		
	            	tableRowValue.put(columnNames[i], columnValues[i]);
	    	        
	        	}	
	        	// look-up value . We are using try catch because if enum doesn't have a value it will throw exception 
	        	try {
	        		tableRowValue.put("Description",MySQLConstants.valueOf(rs.getString(1)).getDescription());
	        	}catch(Exception e) {
	        		System.out.print("Enum Not found for-->"+rs.getString(1));
	        	}
	        	items.add(tableRowValue);
	         }	 while (rs.next());

	        
	        tableView.getItems().addAll(items);
		}
		
		return tableView;
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
	        Text connectToDatabseText = new Text("Users and Previleges");
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
				 secondHalfDisplayVBox.getChildren().clear();
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

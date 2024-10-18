package com.openfx.ui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map.Entry;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.handlers.NewMenuItemEventHandler;
import com.openfx.placeholders.ConnectionPlaceHolder;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MySqlUI {
	
	public Menu_Items_FX menu_Items_FX;
	public NewMenuItemEventHandler newMenuItemEventHandler;
	
	private Statement stmt ;
	
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
		  newMenuItemEventHandler.jdbcConnectionName = new TextField();
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
		  newMenuItemEventHandler.jdbcUrlTextField = new TextField();
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
		  newMenuItemEventHandler.jdbcAuthenticationUsernameTextField = new TextField();
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
													  
													 loadedDatabaseName.getChildren().add(mySqlTreeItemTables);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemViews);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemProcedures);
													 loadedDatabaseName.getChildren().add(mySqlTreeItemFunctions);
													 
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
																																  // break down the show table result and get the foreigh key details
																																  
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
																																			TreeItem<String> foreignKeysName = new TreeItem<String>(mysqlQuerySplit[i]);
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
																									}else {
																										System.out.println("References Collpapsed!!" + "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
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
																									}else {
																										System.out.println("Triggers Collpapsed!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
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
																									System.out.println("is expanded "+value);
																											
																									if(value) {
																										 System.out.println("Indexes Expanded!! "+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
																									     
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
																													        tableIndexes.getChildren().remove(0);  // Remove the Loading...
																															while(rs.next()) {
																																  System.out.println(rs.getString(2) );
																																  // break down the show table result and get the foreigh key details
																																  
																																String mysqlQuerySplit[] = rs.getString(2).split("\n");
																															
																																	for(int i=0;i<mysqlQuerySplit.length;i++) {
																																		
																																		if(mysqlQuerySplit[i].contains("KEY")) {
																																		
																																			System.out.println( mysqlQuerySplit[i]);
																																			TreeItem<String> IndexName = new TreeItem<String>(mysqlQuerySplit[i]);
																																			tableIndexes.getChildren().add(IndexName);
																																			  
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
																									}else {
																										System.out.println("Partitions Collpapsed!!"+ "Database Selected "+loadedDatabaseName.getValue() +" Tables Selected " +loadedTableName.getValue());
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
																
																System.out.println("Its Procedures expansion!!!"); // from here fix procedures
																
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
							System.out.println("Collapsed!!!" + name);
							mySqlTreeItemDatabases.getChildren().clear();  // Clear the list
							mySqlTreeItemDatabases.getChildren().add(loadingTreeItem);
						}
		}
		});
		
		
		
		/*
		mySqlTreeItemDatabases.addEventHandler(TreeItem.branchCollapsedEvent(), event -> {
			
			System.out.println("Databases Collapsed !!!");
			mySqlTreeItemDatabases.getChildren().clear();
			mySqlTreeItemDatabases.getChildren().add(loadingTreeItem);
		});
		*/
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
		
		// System Info
		TreeItem<String> mySqlTreeItemSystemInfo = new TreeItem<String>("System Info");
		
		// Tables
		TreeItem<String> mySqlTreeItemTables = new TreeItem<String>("Tables",imagemySqlTablenode);
		TreeItem<String> loadingTreeItemTables = new TreeItem<String>("Loading..");
		mySqlTreeItemTables.getChildren().add(loadingTreeItemTables);
		
		mySqlTreeItemTables.addEventHandler(TreeItem.branchExpandedEvent(), event -> {
			
			System.out.println("Tables Expanded !!!");
			
			// go to the connection and get the tables
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
					         
					    	 // by default connect to the first database
					    	 System.out.println("use "+mySqlTreeItemDatabases.getChildren().get(0).getValue());
							 try{	 
								 stmt.execute("use "+mySqlTreeItemDatabases.getChildren().get(0).getValue());  
							 }catch (Exception e) {
								e.printStackTrace();
							 }
							 
							
							System.out.println("show tables in "+mySqlTreeItemDatabases.getChildren().get(0).getValue());
					    	try (ResultSet rs = stmt.executeQuery("show tables in "+mySqlTreeItemDatabases.getChildren().get(0).getValue())) { // connect to the first database
					    	
					    		mySqlTreeItemTables.getChildren().remove(0);  // Remove the Loading...
								while(rs.next()) {
									  System.out.println(rs.getString(1));
									  mySqlTreeItemTables.getChildren().add(new TreeItem<String>(rs.getString(1)));
										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					     }
					}).start();
					
				}
			}
			
		});
		mySqlTreeItemTables.addEventHandler(TreeItem.branchCollapsedEvent(), event -> {
			
			System.out.println("Tables Collapsed !!!");
			mySqlTreeItemTables.getChildren().clear();
			mySqlTreeItemTables.getChildren().add(loadingTreeItemTables);
		});
	
					
		// Views
		TreeItem<String> mySqlTreeItemViews = new TreeItem<String>("Views");
		mySqlTreeItemViews.addEventHandler(TreeItem.branchExpandedEvent(), event -> {
			
			System.out.println("Views Expanded !!!");
		
		});
		mySqlTreeItemViews.addEventHandler(TreeItem.branchCollapsedEvent(), event -> {
			
			System.out.println("Views Collapsed !!!");
		});
		mySqlTreeItemViews.getChildren().add(new TreeItem<String>("Loading.."));
		
		// Procedures 
		TreeItem<String> mysqlTreeItemProcedures = new TreeItem<String>("Procedures");
		mysqlTreeItemProcedures.addEventHandler(TreeItem.branchExpandedEvent(), event -> {
			
			System.out.println("Procedures Expanded !!!");
		});
		mysqlTreeItemProcedures.addEventHandler(TreeItem.branchCollapsedEvent(), event -> {
			
			System.out.println("Procedures Collapsed !!!");
			
		});
		mysqlTreeItemProcedures.getChildren().add(new TreeItem<String>("Loading.."));
		
		
		mySqlTreeItem.getChildren().add(mySqlTreeItemDatabases);
		mySqlTreeItem.getChildren().add(mySqlTreeItemUsers);
		mySqlTreeItem.getChildren().add(mySqlTreeItemAdminister);
		mySqlTreeItem.getChildren().add(mySqlTreeItemSystemInfo);
		
		/*
		mySqlTreeItem.getChildren().add(mySqlTreeItemTables);
		mySqlTreeItem.getChildren().add(mySqlTreeItemViews);
		mySqlTreeItem.getChildren().add(mysqlTreeItemProcedures);
		*/
		return mySqlTreeItem; 
		
	}

}

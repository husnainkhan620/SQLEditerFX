package com.openfx.handlers;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.placeholders.ConnectionPlaceHolder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

public class SqlQueryRunButtonSubmit  implements EventHandler<ActionEvent>{

	private String[] sqlConstantsWithoutResultSet = {"USE","ALTER","CREATE","CONNECT"};
	
	private Menu_Items_FX menu_Items_FX;
	public SqlQueryRunButtonSubmit(Menu_Items_FX menu_Items_FX) {
		
		this.menu_Items_FX = menu_Items_FX;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		System.out.println("Current Contents to run ");
		if(menu_Items_FX.foucesSqlEditerTextArea != null)
			System.out.println(menu_Items_FX.foucesSqlEditerTextArea.getText());
		else {
			System.out.println("Nothing to run ");
		}
		
		
		// IF No DB is yet present to run the query 
		// Display a pop up to say so and ask them to connect to DB either a new one or exisitng ones
		if(menu_Items_FX.foucesSqlEditerTextArea != null) {
			System.out.println(menu_Items_FX.foucesSqlEditerTextArea.getId());
			if(menu_Items_FX.foucesSqlEditerTextArea.getId() == null) {
				 
				Stage connectionStage = new Stage();
				BorderPane mainPopUpborderPane = new BorderPane();
				 
				HBox topHbox = addTopHBox();
				
				GridPane centerGridPane = addCenterGridPane();
				
				HBox bottomHbox = addBottomHBox();
				
				
				mainPopUpborderPane.setTop(topHbox);
				
				mainPopUpborderPane.setCenter(centerGridPane);;
				mainPopUpborderPane.setBottom(bottomHbox);
				
				Scene scene = new Scene(mainPopUpborderPane,500,500);  
				scene.getStylesheets().add(getClass().getResource("/layoutstyles.css").toExternalForm());  
				
				connectionStage.initModality(Modality.APPLICATION_MODAL);
				connectionStage.initOwner(menu_Items_FX.primaryStage.getScene().getWindow());
				connectionStage.setTitle("No DataBase Connection ");   
				connectionStage.setScene(scene);
				 // Commenting the below for now
				 //  connectionStage.initStyle(StageStyle.TRANSPARENT);  // remove the top head of the scene
				    
				connectionStage.show();
				return;
			}
		}
		 
		// Move this to a seperate button handler to connect to DB from pop up
		// Check if the file or TextArea to run is associated with any DB 
		if(menu_Items_FX.foucesSqlEditerTextArea != null) {
			System.out.println(menu_Items_FX.foucesSqlEditerTextArea.getId());
			if(menu_Items_FX.foucesSqlEditerTextArea.getId() == null) {
				System.out.println("No Database connection to run this !!!!!");
				new NewMenuItemEventHandler(menu_Items_FX).handle(event);
			 
				
				//change the existingtab name to remove No DB
				menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setText(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText().replace("[ No DB ] ", ""));
				
				System.out.println("Size is "+menu_Items_FX.rootConnectionItem.getChildren().size());
				
				// This should give us the latest added connection
				TreeItem<String> duckDBTreeItem = menu_Items_FX.rootConnectionItem.getChildren().get(menu_Items_FX.rootConnectionItem.getChildren().size()-1);
				System.out.println("duckDBTreeItem "+duckDBTreeItem);
				menu_Items_FX.foucesSqlEditerTextArea.setId( duckDBTreeItem.getGraphic().getId());
				
				Tab sqlEditerTab = menu_Items_FX.alltabbedEditors.getTabs().get(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedIndex());
	            sqlEditerTab.setGraphic(duckDBTreeItem.getGraphic());
				return;
			}
		}
		else {
			System.out.println("Nothing to run ");
			return;
		}
		
		
		/*
        System.out.println( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedIndex());
		System.out.println( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText() );
		menu_Items_FX.toolBarRunButton.setDisable(true);	
		
	   SplitPane tabSplitPane	= (SplitPane)  menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getContent();
	   System.out.println( tabSplitPane.getItems().get(0).toString()) ; // Top half of the tab where sql editer is present
	  
	   ScrollPane sqlEditerscrollPane = (ScrollPane) tabSplitPane.getItems().get(0);
	   
	   GridPane tabEditergridPane =  (GridPane) sqlEditerscrollPane.getContent(); // will have stack panes which individually have textArea
	   System.out.println( tabEditergridPane.getChildren().toString());
	   
	   ObservableList<Node> gridPaneList =  tabEditergridPane.getChildren();
	   for(Node node : gridPaneList) {
		   if(node instanceof StackPane) {	   
			   TextArea lowestLevelTextArea = (TextArea) ((StackPane) node).getChildren().get(0);
			   System.out.println(lowestLevelTextArea.getText());
		   }
	   }
	   */
	 
	   
		System.out.println("Database Type incurred from SQL Editer Text Area :"+menu_Items_FX.foucesSqlEditerTextArea.getId()); // This is set for a database connection check in NewMenuItem
		
		if(menu_Items_FX.foucesSqlEditerTextArea.getId().equalsIgnoreCase("MySQL")) {
			//System.out.println("Connected MySQl databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.mySqlConnectionsMap.entrySet())
			{
				System.out.println( ((ConnectionPlaceHolder)entrySet.getKey()).getConnectionName() +" "+entrySet.getValue());
				// This check becoz the script tab name will always have connection name in front of it
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText().contains(((ConnectionPlaceHolder)entrySet.getKey()).getConnectionName())) {
					System.out.println("Likely to run on this connection"+entrySet.getValue());
					
					runMySQLScript(entrySet.getValue());
				}
			}
		}
		else if(menu_Items_FX.foucesSqlEditerTextArea.getId().equalsIgnoreCase("PostgreeSql")) {
			//System.out.println("Connected Postgree databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.postgreeSqlConnectionsMap.entrySet())
			{
				System.out.println( ((ConnectionPlaceHolder)entrySet.getKey()).getConnectionName() +" "+entrySet.getValue());
				// This check becoz the script tab name will always have connection name in front of it
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText().contains(((ConnectionPlaceHolder)entrySet.getKey()).getConnectionName())) {
					System.out.println("Likely to run on this connection"+entrySet.getValue());
					
					runPostgreeSqlScript(entrySet.getValue());
				}
				
			}
		}
		else if(menu_Items_FX.foucesSqlEditerTextArea.getId().equalsIgnoreCase("Sqlite")) {
			//System.out.println("Connected SQLite databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.sqliteConnectionsMap.entrySet())
			{
				// This check becoz the script tab name will always have connection name in front of it
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText().contains(((ConnectionPlaceHolder)entrySet.getKey()).getConnectionName())) {
					System.out.println("Likely to run on this connection"+entrySet.getValue());
					
					runSQLiteSqlScript(entrySet.getValue());
				}
				
			}
		}
		else if(menu_Items_FX.foucesSqlEditerTextArea.getId().equalsIgnoreCase("oracle")) {
			//System.out.println("Connected Oracle databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.oracleConnectionsMap.entrySet())
			{
				// This check becoz the script tab name will always have connection name in front of it
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText().contains(((ConnectionPlaceHolder)entrySet.getKey()).getConnectionName())) {
					System.out.println("Likely to run on this connection"+entrySet.getValue());
					
					runSQLiteSqlScript(entrySet.getValue());
				}
				
			}
		}
		else if(menu_Items_FX.foucesSqlEditerTextArea.getId().equalsIgnoreCase("duckDB")) {  // comes from reflectDuckDBTreeView
			//System.out.println("Connected Oracle databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.duckDBConnectionsMap.entrySet())
			{
				// This check becoz the script tab name will always have connection name in front of it
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText().contains(((ConnectionPlaceHolder)entrySet.getKey()).getConnectionName())) {
					System.out.println("Likely to run on this connection"+entrySet.getValue());
					
					runDuckDBSqlScript(entrySet.getValue());
				}
				
			}
		}
	}



	private void runPostgreeSqlScript(Connection postgreeSqlConnection) {
		String sqlQueryTextToRun = menu_Items_FX.foucesSqlEditerTextArea.getText();
		boolean sqlQueryTextToRunWithoutResultSet = false;
		boolean resultStatus = false;
		
		for(int i=0;i<sqlConstantsWithoutResultSet.length;i++)
		{
			sqlQueryTextToRunWithoutResultSet = sqlQueryTextToRun.trim().toUpperCase().startsWith(sqlConstantsWithoutResultSet[i]) ?  true : false;
			if(sqlQueryTextToRunWithoutResultSet)
				break;
		}
		try {
			
			Statement stmt =  postgreeSqlConnection.createStatement();
			
			System.out.println("------"+sqlQueryTextToRunWithoutResultSet);
			if(sqlQueryTextToRunWithoutResultSet) {
				resultStatus =  stmt.execute(sqlQueryTextToRun);
				showResultSetInTheConsole(resultStatus);
			}
			else
			{
				ResultSet rs =   stmt.executeQuery(sqlQueryTextToRun);
				while(rs.next()) {
					showResultSetInTheTableView(rs);
				}
			}	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		
	}

	 private HBox addTopHBox() {

		 
	        HBox hbox = new HBox();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #336699;");
	// Use style class to set properties previously set above (with some changes)      
	        hbox.getStyleClass().add("hbox");
	        
	        Text connectToDatabseText = new Text("Connect to a Database");
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	    }
	 
	 private GridPane addCenterGridPane() {
		 
		 GridPane gridPane = new GridPane();
		 
		 //Setting the padding  
	     gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	      
	     //Setting the vertical and horizontal gaps between the columns 
	     
	     gridPane.setVgap(50); 
	     gridPane.setHgap(5);
	      
	     gridPane.setAlignment(Pos.CENTER);
	     
	     Label exisingConnection = new Label("Connect to existing Databases");
	     Label newConnection = new Label("Connect to a new Database");
	     
	     gridPane.add(exisingConnection,0,0);   // row 0 column 0
	     gridPane.add(newConnection,0,1);
	     
		 return gridPane;
		 
	 }
	 
	 private HBox addBottomHBox() {

		 
	        HBox hbox = new HBox();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #334000;");
	// Use style class to set properties previously set above (with some changes)      
	        //hbox.getStyleClass().add("hbox");
	        
	        hbox.getStyleClass().add("hbox");

	        
	        return hbox;
	    }
	 
	 
	private void runMySQLScript(Connection mySQLConnection) {
		String sqlQueryTextToRun = menu_Items_FX.foucesSqlEditerTextArea.getText();
		
		boolean sqlQueryTextToRunWithoutResultSet = false;
		boolean resultStatus = false;
		
		for(int i=0;i<sqlConstantsWithoutResultSet.length;i++)
		{
			sqlQueryTextToRunWithoutResultSet = sqlQueryTextToRun.trim().toUpperCase().startsWith(sqlConstantsWithoutResultSet[i]) ?  true : false;
			if(sqlQueryTextToRunWithoutResultSet)
				break;
		}
		try {
			
			Statement stmt =  mySQLConnection.createStatement();
			
			if(sqlQueryTextToRunWithoutResultSet) {
				resultStatus =  stmt.execute(sqlQueryTextToRun);
				showResultSetInTheConsole(resultStatus);
			}
			else
			{
				ResultSet rs =   stmt.executeQuery(sqlQueryTextToRun);
				while(rs.next()) {
					showResultSetInTheTableView(rs);
				}
			}	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}

	private void runSQLiteSqlScript(Connection sqliteConnection) {
		
		String sqlQueryTextToRun = menu_Items_FX.foucesSqlEditerTextArea.getText();
		
		boolean sqlQueryTextToRunWithoutResultSet = false;
		boolean resultStatus = false;
		
		for(int i=0;i<sqlConstantsWithoutResultSet.length;i++)
		{
			sqlQueryTextToRunWithoutResultSet = sqlQueryTextToRun.trim().toUpperCase().startsWith(sqlConstantsWithoutResultSet[i]) ?  true : false;
			if(sqlQueryTextToRunWithoutResultSet)
				break;
		}
		try {
			
			Statement stmt =  sqliteConnection.createStatement();
			
			if(sqlQueryTextToRunWithoutResultSet) {
				resultStatus =  stmt.execute(sqlQueryTextToRun);
				showResultSetInTheConsole(resultStatus);
			}
			else
			{
				ResultSet rs =   stmt.executeQuery(sqlQueryTextToRun);
				while(rs.next()) {
					showResultSetInTheTableView(rs);
				}
			}	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
	
	private void runOracleSqlScript(Connection oracleConnection) {
		
		String sqlQueryTextToRun = menu_Items_FX.foucesSqlEditerTextArea.getText();
		
		boolean sqlQueryTextToRunWithoutResultSet = false;
		boolean resultStatus = false;
		
		for(int i=0;i<sqlConstantsWithoutResultSet.length;i++)
		{
			sqlQueryTextToRunWithoutResultSet = sqlQueryTextToRun.trim().toUpperCase().startsWith(sqlConstantsWithoutResultSet[i]) ?  true : false;
			if(sqlQueryTextToRunWithoutResultSet)
				break;
		}
		try {
			
			Statement stmt =  oracleConnection.createStatement();
			
			if(sqlQueryTextToRunWithoutResultSet) {
				resultStatus =  stmt.execute(sqlQueryTextToRun);
				showResultSetInTheConsole(resultStatus);
			}
			else
			{
				ResultSet rs =   stmt.executeQuery(sqlQueryTextToRun);
				while(rs.next()) {
					showResultSetInTheTableView(rs);
				}
			}	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
	
	private void runDuckDBSqlScript(Connection duckDBConnection) {
		
		String sqlQueryTextToRun = menu_Items_FX.foucesSqlEditerTextArea.getText();
		
		boolean sqlQueryTextToRunWithoutResultSet = false;
		boolean resultStatus = false;
		
		for(int i=0;i<sqlConstantsWithoutResultSet.length;i++)
		{
			sqlQueryTextToRunWithoutResultSet = sqlQueryTextToRun.trim().toUpperCase().startsWith(sqlConstantsWithoutResultSet[i]) ?  true : false;
			if(sqlQueryTextToRunWithoutResultSet)
				break;
		}
		try {
			
			Statement stmt =  duckDBConnection.createStatement();
			
			if(sqlQueryTextToRunWithoutResultSet) {
				resultStatus =  stmt.execute(sqlQueryTextToRun);
				showResultSetInTheConsole(resultStatus);
			}
			else
			{
				ResultSet rs =   stmt.executeQuery(sqlQueryTextToRun);
				while(rs.next()) {
					showResultSetInTheTableView(rs);
				}
			}	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
	
	
	private void showResultSetInTheConsole(boolean resultStatus) {
		
	     SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
	        
	     System.out.println("Current Tab index "+ singleSelectionModel.getSelectedIndex());
	     Tab currentSelectedEditerTab =  menu_Items_FX.alltabbedEditors.getTabs().get(singleSelectionModel.getSelectedIndex());
	     SplitPane currentEditerTabsplitPAne = ((SplitPane)currentSelectedEditerTab.getContent());
	     TabPane secondHalfofQueryEditerTab = ((TabPane)currentEditerTabsplitPAne.getItems().get(1));  // Second Half of the current editer tab is a Tabbed pane of result
	        
	     Tab currentSelectedConsoleTab =  secondHalfofQueryEditerTab.getTabs().get(1); // The Second tab is Console tab for now
	     secondHalfofQueryEditerTab.getSelectionModel().select(currentSelectedConsoleTab);
	     currentSelectedConsoleTab.setContent(new TextArea(String.valueOf(resultStatus)));
		
	}

	private void showResultSetInTheTableView(ResultSet rs)  throws SQLException{
	
		
	    	TableView tableView = new TableView();
	    	TableColumn<Map, String> tableColumnName;
	    	Map<String, Object> tableRowValue;
	    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
	    	
			ResultSetMetaData md = rs.getMetaData();
	        String[] columnNames = new String[md.getColumnCount()];
	        Integer[] columnTypes = new Integer[md.getColumnCount()];
	       
	        for (int i = 0; i < columnNames.length; i++) {
	        	columnNames[i] = md.getColumnName(i+1);
	        	columnTypes[i] = md.getColumnType(i+1);	   
	        	
	        	tableColumnName = new TableColumn<>(columnNames[i]);
	        	tableColumnName.setCellValueFactory(new MapValueFactory<>(columnNames[i]));
	 	        
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
	        
	        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
	        
	        System.out.println("Current Tab index "+ singleSelectionModel.getSelectedIndex());
	        Tab currentSelectedEditerTab =  menu_Items_FX.alltabbedEditors.getTabs().get(singleSelectionModel.getSelectedIndex());
	        SplitPane currentEditerTabsplitPAne = ((SplitPane)currentSelectedEditerTab.getContent());
	        TabPane secondHalfofQueryEditerTab = ((TabPane)currentEditerTabsplitPAne.getItems().get(1));  // Second Half of the current editer tab is a Tabbed pane of result
	        
	        Tab currentSelectedResultTab =  secondHalfofQueryEditerTab.getTabs().get(0); // The first tab is Result tab for now
	        
	        secondHalfofQueryEditerTab.getSelectionModel().select(currentSelectedResultTab);  //
	        currentSelectedResultTab.setContent(tableView);
	        
		
	}

	
	
}

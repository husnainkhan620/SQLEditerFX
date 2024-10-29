package com.openfx.handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.ai.ConnectionsConstants;
import com.openfx.placeholders.ConnectionPlaceHolder;
import com.openfx.placeholders.HighLightRectangleHolder;
import com.openfx.placeholders.ImageItemsHolder;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SqlQueryRunButtonSubmit  implements EventHandler<ActionEvent>{

	private String[] sqlConstantsWithoutResultSet = {"USE","ALTER","CREATE","CONNECT"};
	
	public ArrayList<Rectangle> avaialbleHighRectangleConnections = new ArrayList<Rectangle>() ;  
	private HighLightRectangleHolder highLightRectangleHolder = new HighLightRectangleHolder(avaialbleHighRectangleConnections);
	
	private Menu_Items_FX menu_Items_FX;
	public Button connectExistingConnection;
	private Stage connectionStage;
	
	public SqlQueryRunButtonSubmit(Menu_Items_FX menu_Items_FX) {
		
		this.menu_Items_FX = menu_Items_FX;
		this.menu_Items_FX.sqlQueryRunButtonSubmit =this;
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
		if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() == null) {
			System.out.println("Graphic present ? "+ menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic());
			if( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic() == null) {
				 
				connectionStage = new Stage();
				BorderPane mainPopUpborderPane = new BorderPane();
				 
				HBox topHbox = addTopHBox();
				
				SplitPane centerSplitPane = addCenterSplitPane();
				
				HBox bottomHbox = addBottomHBox();
				
				mainPopUpborderPane.setTop(topHbox);
				
				mainPopUpborderPane.setCenter(centerSplitPane);;
				mainPopUpborderPane.setBottom(bottomHbox);
				
				Scene scene = new Scene(mainPopUpborderPane,550,500);  
				scene.getStylesheets().add(getClass().getResource("/layoutstyles.css").toExternalForm());  
				
				connectionStage.initModality(Modality.APPLICATION_MODAL);
				connectionStage.initOwner(menu_Items_FX.primaryStage.getScene().getWindow());
				connectionStage.setTitle("No DataBase Connection ");   
				connectionStage.setScene(scene);
				    
				connectionStage.show();
				return;
			}
		}
		 
		/*
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
		*/
		
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
	 
		genericQueryRun();
	}

	private void genericQueryRun() {
		
		System.out.println("Database Type incurred from SQL Editer Tab Area :"+menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId()); // This is set for a database connection check in NewMenuItem
		
		if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().contains(ConnectionsConstants.MySql)) {
			//System.out.println("Connected MySQl databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.mySqlConnectionsMap.entrySet())
			{
				// MySQL##con12
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().split("##")[1].equalsIgnoreCase(entrySet.getKey().getConnectionName()))
				{
					System.out.println("Likely to run on this connection"+menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId());
					runMySQLScript(entrySet.getValue());
					continue;
				}
			}
			
		}
		else if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().contains(ConnectionsConstants.PostgreeSql)) {
			//System.out.println("Connected Postgree databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.postgreeSqlConnectionsMap.entrySet())
			{
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().split("##")[1].equalsIgnoreCase(entrySet.getKey().getConnectionName()))
				{
					System.out.println("Likely to run on this connection "+menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId());
					runPostgreeSqlScript(entrySet.getValue());
					continue;
				}
				
			}
		}
		else if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().contains(ConnectionsConstants.Sqlite)) {   // will have to change this names as this check is very generic
			//System.out.println("Connected SQLite databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.sqliteConnectionsMap.entrySet())
			{
				//  SQLite##muck1
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().split("##")[1].equalsIgnoreCase(entrySet.getKey().getConnectionName()))
				{
					System.out.println("Likely to run on this connection "+menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId());
					runSQLiteSqlScript(entrySet.getValue());
					continue;
				}
				
			}
		}
		else if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().contains(ConnectionsConstants.SapHana)) {
			//System.out.println("Connected Oracle databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.saphanarMap.entrySet())
			{

				//  Oracle##muck1
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().split("##")[1].equalsIgnoreCase(entrySet.getKey().getConnectionName()))
				{
					System.out.println("Likely to run on this connection "+menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId());
					runOracleSqlScript(entrySet.getValue());
					continue;
				}
				
			}
		}
		else if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().contains(ConnectionsConstants.Oracle)) {
			//System.out.println("Connected Oracle databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.oracleConnectionsMap.entrySet())
			{

				//  Oracle##muck1
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().split("##")[1].equalsIgnoreCase(entrySet.getKey().getConnectionName()))
				{
					System.out.println("Likely to run on this connection "+menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId());
					runOracleSqlScript(entrySet.getValue());
					continue;
				}
				
			}
		}
	
		else if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().contains(ConnectionsConstants.Databricks)) {
			//System.out.println("Connected Oracle databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.databricksrMap.entrySet())
			{

				//  Oracle##muck1
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().split("##")[1].equalsIgnoreCase(entrySet.getKey().getConnectionName()))
				{
					System.out.println("Likely to run on this connection "+menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId() );
					runOracleSqlScript(entrySet.getValue());
					continue;
				}
				
			}
		}
		else if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().contains(ConnectionsConstants.MSSQLSErver)) {
			//System.out.println("Connected Oracle databases");
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.msSqlServerMap.entrySet())
			{

				//  Oracle##muck1
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().split("##")[1].equalsIgnoreCase(entrySet.getKey().getConnectionName()))
				{
					System.out.println("Likely to run on this connection "+menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId());
					runOracleSqlScript(entrySet.getValue());
					continue;
				}
				
			}
		}
		else if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().contains(ConnectionsConstants.DuckDB)) {  // comes from reflectDuckDBTreeView
			
			for(Entry<ConnectionPlaceHolder,Connection>  entrySet :  menu_Items_FX.duckDBConnectionsMap.entrySet())
			{
				
				//  DuckDB##muck1
				if(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId().split("##")[1].equalsIgnoreCase(entrySet.getKey().getConnectionName()))
				{
					System.out.println("Likely to run on this connection "+menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getGraphic().getId());
					runDuckDBSqlScript(entrySet.getValue());
					continue;
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
				//while(rs.next()) {
					showResultSetInTheTableView(rs);
				//}
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
				//while(rs.next()) {
					showResultSetInTheTableView(rs);
				//}
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
				//while(rs.next()) {
					showResultSetInTheTableView(rs);
				//}
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
				// while(rs.next()) {
					showResultSetInTheTableView(rs);
				//}
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
				//while(rs.next()) {
					showResultSetInTheTableView(rs);
				//}
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
			tableView.setEditable(true);
			tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the last empty column otherwise added
	        
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
		        	columnTypes[i] = md.getColumnType(i+1);	   
		        	
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
			
	        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
	        
	        System.out.println("Current Tab index "+ singleSelectionModel.getSelectedIndex());
	        Tab currentSelectedEditerTab =  menu_Items_FX.alltabbedEditors.getTabs().get(singleSelectionModel.getSelectedIndex());
	        SplitPane currentEditerTabsplitPAne = ((SplitPane)currentSelectedEditerTab.getContent());
	        TabPane secondHalfofQueryEditerTab = ((TabPane)currentEditerTabsplitPAne.getItems().get(1));  // Second Half of the current editer tab is a Tabbed pane of result
	        
	        Tab currentSelectedResultTab =  secondHalfofQueryEditerTab.getTabs().get(0); // The first tab is Result tab for now
	        
	        secondHalfofQueryEditerTab.getSelectionModel().select(currentSelectedResultTab);  //
	        currentSelectedResultTab.setContent(tableView);
	}
	
	private SplitPane addCenterSplitPane() {
		 
		 SplitPane splitPaneCenter = new SplitPane();
		
		 VBox topControlVBox = new VBox();
		 
		 BorderPane borderPaneTop = new BorderPane();
		 
		 HBox topControlHBox = new HBox();
		 topControlHBox.setPadding(new Insets(10,0,10,0));   // top  right bottom left
	     Text connectToDatabseText = new Text("Existing Connections");
	     connectToDatabseText.setFont(Font.font("Verdana",20));
	     connectToDatabseText.setFill(Color.BLACK);
	     connectToDatabseText.setTextAlignment(TextAlignment.CENTER);
	     topControlHBox.getChildren().addAll(connectToDatabseText);
	     
	     HBox topControlHBoxforButton = new HBox();
	     topControlHBoxforButton.setPadding(new Insets(10,10,10,0));   // top  right bottom left
	     connectExistingConnection = new Button("Connect");
	     connectExistingConnection.setDisable(true);
	     connectExistingConnection.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				System.out.println("Connect pressed !!!");
				
				menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setText(menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText().replace("No DB",menu_Items_FX.currentConnectionSelected.split("##")[1]));
				
				setGraphicNodeForDatabase();
				genericQueryRun();
			
				connectionStage.close();
				
			}
		 });
	     topControlHBoxforButton.getChildren().add(connectExistingConnection);
	     borderPaneTop.setLeft(topControlHBox);
	     borderPaneTop.setRight(topControlHBoxforButton);
	     
	     ScrollPane scrollPaneTop = new ScrollPane();
	     scrollPaneTop.setFitToWidth(true);
	     scrollPaneTop.setFitToHeight(true);
	     
	     FlowPane openConnectionsFlowPane = new FlowPane();
	     openConnectionsFlowPane.setPadding(new Insets(10, 0, 10, 10));
	     openConnectionsFlowPane.setVgap(10);
	     openConnectionsFlowPane.setHgap(20);
	     
	     // pull the existing open database connections.
	     System.out.println("Opened Connections \n");
	     for(Map.Entry<ConnectionPlaceHolder, Connection> entry : menu_Items_FX.currentOpenConnectionsMap.entrySet()) {
	    	 
	    	 System.out.println("Connection Name : "+entry.getKey().getConnectionName()+" ConnectionType : "+entry.getKey().getConnectionType());
	
			  
	    	 if( entry.getKey().getConnectionType().equals("MySqlConnection")) {
	    		 StackPane stackPaneMySql = highLightRectangleHolder.getHighlightRectangleMySql( entry.getKey().getConnectionName());
	    		 openConnectionsFlowPane.getChildren().add(stackPaneMySql);
	    		 stackPaneMySql.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMySql.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneMySql));
	    		 stackPaneMySql.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMySql.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneMySql));
	    		 stackPaneMySql.setOnMouseExited(new HighLightRectangleMouseEventHandler((Rectangle)stackPaneMySql.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneMySql));
	    	 }
	    	 
	    	 if( entry.getKey().getConnectionType().equals("PostgreeSqlConnection")) {
	    		 StackPane stackPanePostgreeSql = highLightRectangleHolder.getHighlightRectanglePostgreeSql( entry.getKey().getConnectionName());
	    		 openConnectionsFlowPane.getChildren().add(stackPanePostgreeSql);
	    		 stackPanePostgreeSql.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanePostgreeSql.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPanePostgreeSql));
	    		 stackPanePostgreeSql.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanePostgreeSql.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPanePostgreeSql));
	    		 stackPanePostgreeSql.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanePostgreeSql.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPanePostgreeSql));
	    			 
	    	 }
	   
	    	 if( entry.getKey().getConnectionType().equals("SQLiteConnection")) {
	    		 StackPane stackPaneSqlite = highLightRectangleHolder.getHighlightRectangleSqlite(entry.getKey().getConnectionName());
	    		 openConnectionsFlowPane.getChildren().add(stackPaneSqlite);
	    		 stackPaneSqlite.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSqlite.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneSqlite));
	    		 stackPaneSqlite.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSqlite.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneSqlite));
	    		 stackPaneSqlite.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSqlite.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneSqlite));

	    	 }
	    	 
	    	 if( entry.getKey().getConnectionType().equals("OracleConnection")) {
	    		 StackPane stackPaneOracle = highLightRectangleHolder.getHighlightRectangleOracle(entry.getKey().getConnectionName());
	    		 openConnectionsFlowPane.getChildren().add(stackPaneOracle);
	    		  stackPaneOracle.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneOracle.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneOracle));
	    		  stackPaneOracle.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneOracle.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneOracle));
	    		  stackPaneOracle.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneOracle.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneOracle));
	    	 }
	    	 
	    	 if( entry.getKey().getConnectionType().equals("MSSQLServerConnection")) {
				  StackPane stackPaneMssql = highLightRectangleHolder.getHighlightRectangleMSSQLServer(entry.getKey().getConnectionName());
	    		  openConnectionsFlowPane.getChildren().add(stackPaneMssql);
	    		  stackPaneMssql.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMssql.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneMssql));
	    		  stackPaneMssql.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMssql.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneMssql));	  
	    		  stackPaneMssql.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneMssql.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneMssql));
	    	 }
	    	 
	    	 if( entry.getKey().getConnectionType().equals("SapHanaConnection")) {
	    		  StackPane stackPaneSapHana = highLightRectangleHolder.getHighlightRectanglesaphana(entry.getKey().getConnectionName());
	    		  openConnectionsFlowPane.getChildren().add(stackPaneSapHana);
	    		  stackPaneSapHana.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSapHana.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneSapHana));
	    		  stackPaneSapHana.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSapHana.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneSapHana));
	    		  stackPaneSapHana.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneSapHana.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneSapHana));

	    	 }
	    	 
	    	 if( entry.getKey().getConnectionType().equals("DatabricksConnection")) {
	    		 StackPane stackPanedatabricks = highLightRectangleHolder.getHighlightRectangleDatabricks(null);
	    		 openConnectionsFlowPane.getChildren().add(stackPanedatabricks);
	    		 stackPanedatabricks.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanedatabricks.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,null,stackPanedatabricks));
	    		 stackPanedatabricks.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanedatabricks.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,null,stackPanedatabricks));
	    		 stackPanedatabricks.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPanedatabricks.getChildren().get(0),avaialbleHighRectangleConnections,menu_Items_FX,null,stackPanedatabricks));

	    	 }
	    	 
	    	 if( entry.getKey().getConnectionType().equals("DuckDBConnection")) {
	    		  StackPane stackPaneDuckDB = highLightRectangleHolder.getHighlightRectangleDuckDB(entry.getKey().getConnectionName());
	    		  openConnectionsFlowPane.getChildren().add(stackPaneDuckDB);
	    		  stackPaneDuckDB.setOnMouseEntered(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneDuckDB.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneDuckDB));		 
	    		  stackPaneDuckDB.setOnMouseClicked(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneDuckDB.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneDuckDB));  
	    		  stackPaneDuckDB.setOnMouseExited(new  HighLightRectangleMouseEventHandler((Rectangle)stackPaneDuckDB.getChildren().get(0),avaialbleHighRectangleConnections,this.menu_Items_FX,null,stackPaneDuckDB));

	    	 }
	     }
		
		
	     
	     BackgroundFill background_fill = new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ); 
	     // create Background 
	     Background background = new Background(background_fill); 
	     openConnectionsFlowPane.setBackground(background);
	  
	     scrollPaneTop.setContent(openConnectionsFlowPane);
	     
	     topControlVBox.getChildren().add(borderPaneTop);
	     topControlVBox.getChildren().add(scrollPaneTop);
	     
	     HBox bottomControl = new HBox();
	     bottomControl.setPadding(new Insets(10,0,0,0));   // top  right bottom left
	     Text newConnection = new Text("New Database");
	     newConnection.setFont(Font.font("Verdana",20));
	     newConnection.setFill(Color.BLACK);
	     bottomControl.getChildren().addAll(newConnection);

	        
	     splitPaneCenter.setDividerPosition(0, 0.7);
	     
	     splitPaneCenter.setOrientation(Orientation.VERTICAL);
	     
	     splitPaneCenter.getItems().addAll(topControlVBox,bottomControl);

	     
		 return splitPaneCenter;
	 }

	private void setGraphicNodeForDatabase() {
		
		if(menu_Items_FX.currentConnectionSelected.split("##")[0].equalsIgnoreCase(ConnectionsConstants.MySql)){
			
			menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setGraphic(ImageItemsHolder.getMySqlImage(menu_Items_FX.currentConnectionSelected.split("##")[1]));
		}
		
		if(menu_Items_FX.currentConnectionSelected.split("##")[0].equalsIgnoreCase(ConnectionsConstants.PostgreeSql)){
			
			menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setGraphic(ImageItemsHolder.getPostgreeSqlImage(menu_Items_FX.currentConnectionSelected.split("##")[1]));
		}
		
		if(menu_Items_FX.currentConnectionSelected.split("##")[0].equalsIgnoreCase(ConnectionsConstants.Sqlite)){
			
			menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setGraphic(ImageItemsHolder.getSqliteImage(menu_Items_FX.currentConnectionSelected.split("##")[1]));
		}
		
		if(menu_Items_FX.currentConnectionSelected.split("##")[0].equalsIgnoreCase(ConnectionsConstants.SapHana)){
			
			menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setGraphic(ImageItemsHolder.getSaphanaImage(menu_Items_FX.currentConnectionSelected.split("##")[1]));
		}
		
		if(menu_Items_FX.currentConnectionSelected.split("##")[0].equalsIgnoreCase(ConnectionsConstants.Oracle)){
			
			menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setGraphic(ImageItemsHolder.getOracleImage(menu_Items_FX.currentConnectionSelected.split("##")[1]));
		}
		
		if(menu_Items_FX.currentConnectionSelected.split("##")[0].equalsIgnoreCase(ConnectionsConstants.MSSQLSErver)){
			
			menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setGraphic(ImageItemsHolder.getMSSQLServerImage(menu_Items_FX.currentConnectionSelected.split("##")[1]));
		}
		
		if(menu_Items_FX.currentConnectionSelected.split("##")[0].equalsIgnoreCase(ConnectionsConstants.Databricks)){
			
			menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setGraphic(ImageItemsHolder.getDatabricksImage(menu_Items_FX.currentConnectionSelected.split("##")[1]));
		}
		
		if(menu_Items_FX.currentConnectionSelected.split("##")[0].equalsIgnoreCase(ConnectionsConstants.DuckDB)){
			
			menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().setGraphic(ImageItemsHolder.getDuckDBImage(menu_Items_FX.currentConnectionSelected.split("##")[1]));
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

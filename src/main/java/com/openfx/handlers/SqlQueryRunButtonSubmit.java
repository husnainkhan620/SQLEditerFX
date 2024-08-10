package com.openfx.handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.placeholders.ConnectionPlaceHolder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.MapValueFactory;

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
			return;
		}
		
        System.out.println( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedIndex());
		System.out.println( menu_Items_FX.alltabbedEditors.getSelectionModel().getSelectedItem().getText() );
		menu_Items_FX.toolBarRunButton.setDisable(true);	
		
		
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

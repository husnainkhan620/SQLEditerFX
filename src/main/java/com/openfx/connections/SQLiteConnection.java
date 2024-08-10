package com.openfx.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class SQLiteConnection {

	Connection sqliteConnection;
	String url;
	String userName;
	String password;
	
	public SQLiteConnection(String url) throws Exception{
		
		Class.forName("org.sqlite.JDBC"); 
		
		this.url = url;
		this.userName="husnain";
		this.password="password";
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", userName);
		connectionProperties.put("password",password);
		
		try {
			
			sqliteConnection = DriverManager.getConnection(url);
			Statement stmt = sqliteConnection.createStatement();
			
			System.out.println("Connection to SQLite has been established.");  
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Connection getSqliteConnection() {
		return sqliteConnection;
	}

	public void setSqliteConnection(Connection sqliteConnection) {
		this.sqliteConnection = sqliteConnection;
	}
	
	
	public static void main(String[] args)  throws Exception{
		
		SQLiteConnection sqLiteConnection = new SQLiteConnection( "jdbc:sqlite:G:/sqlite/chinook.db");
		

	}



}

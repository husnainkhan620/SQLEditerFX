package com.openfx.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DuckDBConnection {

	Connection duckDBConnection;
	String url;
	String userName;
	String password;
	
	public DuckDBConnection(String url) {
		
		
		this.url = url;
		this.userName="husnain";
		this.password="password";
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", userName);
		connectionProperties.put("password",password);
		
		try {
			
			duckDBConnection = DriverManager.getConnection(url);
			Statement stmt = duckDBConnection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from weather;");
			
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			
			System.out.println("Connection to Duck DB has been established.");  
		//	duckDBConnection.close();
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		
		String url = "jdbc:duckdb:C:/Users/KOUSER/Downloads/duckdb_cli-windows-amd64/my_database.duckdb";
		DuckDBConnection duckDBConnection = new DuckDBConnection(url);

	}



	public Connection getDuckDBConnection() {
		
		return duckDBConnection;
	}

}

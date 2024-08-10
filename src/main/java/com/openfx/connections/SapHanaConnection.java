package com.openfx.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class SapHanaConnection {

	Connection sapHanaConnection;
	String url;
	String userName;
	String password;
	
	public SapHanaConnection(String url) throws Exception{
		
		Class.forName("org.sqlite.JDBC"); 
		
		this.url = url;
		this.userName="husnain";
		this.password="password";
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", userName);
		connectionProperties.put("password",password);
		
		try {
			
			sapHanaConnection = DriverManager.getConnection(url);
			Statement stmt = sapHanaConnection.createStatement();
			
			System.out.println("Connection to SapHana has been established.");  
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Connection getSapHanaConnection() {
		return sapHanaConnection;
	}

	public void setSapHanaConnection(Connection sapHanaConnection) {
		this.sapHanaConnection = sapHanaConnection;
	}




	public static void main(String[] args) throws Exception{
		
		SapHanaConnection sapHanaConnection = new SapHanaConnection( "jdbc:sqlite:G:/sqlite/chinook.db");
		

	}

}

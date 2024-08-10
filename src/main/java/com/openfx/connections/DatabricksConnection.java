package com.openfx.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DatabricksConnection {

	Connection databricksConnection;
	String url;
	String userName;
	String password;
	
	public DatabricksConnection(String url) throws Exception{
		Class.forName("org.sqlite.JDBC"); 
		
		this.url = url;
		this.userName="husnain";
		this.password="password";
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", userName);
		connectionProperties.put("password",password);
		
		try {
			
			databricksConnection = DriverManager.getConnection(url);
			Statement stmt = databricksConnection.createStatement();
			
			System.out.println("Connection to Databricks has been established.");  
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public Connection getDatabricksConnection() {
		return databricksConnection;
	}

	public void setDatabricksConnection(Connection databricksConnection) {
		this.databricksConnection = databricksConnection;
	}



	public static void main(String[] args) throws Exception {
		
		DatabricksConnection databricksConnection = new DatabricksConnection("");

	}

}

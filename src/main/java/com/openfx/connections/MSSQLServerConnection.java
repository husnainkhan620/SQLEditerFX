package com.openfx.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class MSSQLServerConnection {

	
	Connection mssqlServerConnection;
	String url;
	String userName;
	String password;
	
	public MSSQLServerConnection(String url) throws Exception{
		Class.forName("org.sqlite.JDBC"); 
		
		this.url = url;
		this.userName="husnain";
		this.password="password";
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", userName);
		connectionProperties.put("password",password);
		
		try {
			
			mssqlServerConnection = DriverManager.getConnection(url);
			Statement stmt = mssqlServerConnection.createStatement();
			
			System.out.println("Connection to MS SQL Server has been established.");  
			
			mssqlServerConnection.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public static void main(String[] args) throws Exception {

		// ServerName - LAPTOP-N9PBQRE8
		
		// Create a variable for the connection string.
		// https://stackoverflow.com/questions/61117080/no-mssql-jdbc-auth-8-2-1-x64-in-java-library-path
           String connectionUrl = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=AdventureWorksLT2019;integratedSecurity=true";
        
        // String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorksLT2019;encrypt=false;user=steve;password=steve";

        MSSQLServerConnection mssqlServerConnection = new MSSQLServerConnection(connectionUrl);
        
	}

}


/* 
 * The JDBC driver class of SQL Server is com.microsoft.sqlserver.jdbc.SQLServerDriver, so to register this driver, use the following statement:
	
	DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
	Or:

	
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	However, that is not required since JDBC 4.0 (JDK 6.0) because the driver manager can detect and 
	load the driver class automatically as long as a suitable JDBC driver present in the classpath. 
 * */
 

package com.openfx.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

public class MariaDBConnection {

	Connection mariaDBConnection;
	String url;
	String userName;
	String password;
	
	
	public MariaDBConnection(String url,String databaseName,String userName,String password,String port) throws Exception {
		
		this.url = url;   // 127.0.0.1 == localhost
		this.userName = userName;
		this.password = password;
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", userName);
		connectionProperties.put("password",password);
		
		try  
		{
			mariaDBConnection = DriverManager.getConnection(url+":"+port+"/"+databaseName,connectionProperties);
			Statement stmt = mariaDBConnection.createStatement();
				
			
			 
			 try (ResultSet rs = stmt.executeQuery("show tables in "+databaseName)) {
				 
				  	while(rs.next())
				  		
				  		System.out.println(rs.getString(1));
			 }
						 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		

	}
	
	public MariaDBConnection(String url,String userName,String password,String port) throws Exception{
		
		//Class.forName("com.mysql.cj.jdbc.Driver");
		
		this.url = url;   // 127.0.0.1 == localhost
		this.userName = userName;
		this.password = password;
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", userName);
		connectionProperties.put("password",password);
		
		try  
		{
			mariaDBConnection = DriverManager.getConnection(url+":"+port,connectionProperties);
			Statement stmt = mariaDBConnection.createStatement();
				
			 try (ResultSet rs = stmt.executeQuery("show databases")) {
				  	while(rs.next())
				  		System.out.println(rs.getString(1));
				  		System.out.println();			  	
			 
			 }
		
			/* try (ResultSet rs = stmt.executeQuery("select * from "+selectedTable+" limit 1")) {
				    ResultSetMetaData md = rs.getMetaData();
			        String[] columns = new String[md.getColumnCount()];
			        for (int i = 0; i < columns.length; i++) {
			          columns[i] = md.getColumnName(i + 1);
			          //System.out.println(columns[i]);
			        }
			        
			        while (rs.next()) {
			            System.out.print("Row " + rs.getRow() + "=[");
			            for (int i = 0; i < columns.length; i++) {
			              if (i != 0) {
			                System.out.print(", ");
			              }
			              System.out.print(columns[i] + "='" + rs.getObject(i + 1) + "'");
			            }
			            System.out.println(")]");
			          }			*/ 				
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}
	
	public Connection getMariaDBConnection() {
		return mariaDBConnection;
	}

	public void setMariaDBConnection(Connection mariaDBConnection) {
		this.mariaDBConnection = mariaDBConnection;
	}

	public static void main(String[] args) throws Exception {
	
		                                                    //jdbc:mariadb://localhost:3306/yourDatabase
		MariaDBConnection mariaDBConnection = new MariaDBConnection("jdbc:mariadb://127.0.0.1:3307", "root", "root","3307");
		
	}
	
}

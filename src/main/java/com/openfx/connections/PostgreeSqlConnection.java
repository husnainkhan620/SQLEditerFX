package com.openfx.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class PostgreeSqlConnection {

	Connection postgreeSqlConnection;
	String url;
	String userName;
	String password;
	
	public PostgreeSqlConnection(String url,String userName,String password,Boolean rootConnection) throws Exception{
		
		System.out.println("Connect to Postgree "+url);
		System.out.println(url+","+userName+","+password);
		
		Class.forName("org.postgresql.Driver");
		
		this.url = url;   // 127.0.0.1 == localhost  //jdbc:postgresql://localhost:5432/sales
		this.userName = userName;
		this.password = password;
		
		try  
		{
			postgreeSqlConnection = DriverManager.getConnection(url,userName,password);
			Statement stmt = postgreeSqlConnection.createStatement();
			
			System.out.println(stmt);
			
			// show databases
			 try (ResultSet rs = stmt.executeQuery(" SELECT datname FROM pg_catalog.pg_database where datistemplate = false")) {
				  	while(rs.next())
				  		System.out.println(rs.getString(1));
				  		System.out.println();			  	
			 }
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getPostgreeSqlConnection() {
		return postgreeSqlConnection;
	}

	public void setPostgreeSqlConnection(Connection postgreeSqlConnection) {
		this.postgreeSqlConnection = postgreeSqlConnection;
	}

	public PostgreeSqlConnection(String url,String userName,String password) throws Exception{
		
		System.out.println("Connect to Postgree "+url);
		System.out.println(url+","+userName+","+password);
		
		Class.forName("org.postgresql.Driver");
		
		this.url = url;   // 127.0.0.1 == localhost  //jdbc:postgresql://localhost:5432/sales
		this.userName = userName;
		this.password = password;
		
		try  
		{
			postgreeSqlConnection = DriverManager.getConnection(url,userName,password);
			Statement stmt = postgreeSqlConnection.createStatement();
			
			System.out.println(stmt);
			
			 
			String selectedTable = "accounts";
			 
			 try (ResultSet rs = stmt.executeQuery("select * from "+selectedTable+" limit 1")) {
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
			          }			 				
			 }
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	
	public static void main(String[] args) throws Exception {
	
		PostgreeSqlConnection myPostgreeConnectionRoot = new PostgreeSqlConnection("jdbc:postgresql://localhost:5432/", "postgres", "root",true);
		
		PostgreeSqlConnection myPostgreeConnectionSpecific = new PostgreeSqlConnection("jdbc:postgresql://localhost:5432/ecomprods", "postgres", "root");
		// jdbc:postgresql://localhost:5432/sales
	}
	
}


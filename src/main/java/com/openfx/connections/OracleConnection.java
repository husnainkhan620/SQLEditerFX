package com.openfx.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class OracleConnection {

	Connection oracleConnection;
	String url;
	String userName;
	String password;
	
	public OracleConnection(String url,String userName,String password)  throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		
		this.url = url;
		this.userName=userName;  //sys as sysdba
		this.password=password; // root
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", userName);
		connectionProperties.put("password",password);
		
		try {
			
			oracleConnection = DriverManager.getConnection(url,connectionProperties);
			Statement stmt = oracleConnection.createStatement();
			
			System.out.println("Connection to Oracle has been established.");  
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Connection getOracleConnection() {
		return oracleConnection;
	}


	public void setOracleConnection(Connection oracleConnection) {
		this.oracleConnection = oracleConnection;
	}


	public static void main(String[] args) throws Exception{
		// 
		OracleConnection oracleConnection = new OracleConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1","sys as sysdba","root");
		
		//
		//OracleConnection oracleConnection = new OracleConnection("jdbc:oracle:thin:@localhost:1521:xe");
		
		
	}
	

}

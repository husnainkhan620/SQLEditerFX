package com.openfx.constants;

public enum MySQLConstants {
	
	
	activate_all_roles_on_login("The server activates all roles granted to each account at login time","Y"),
	admin_address("The IP address on which to listen for TCP/IP connections on the administrative network interface","N"),
	admin_port("The TCP/IP port number to use for connections on the administrative network interface ","N");
	// Continue to add all descriptions from here
	
	private String description;
	private String isEditable;
	
	MySQLConstants(String description,String editable) {
		this.description = description;
		this.isEditable = editable;
	}
	
	
	public String getDescription() {
		return this.description;
	}
	
	public String getIsEditable() {
		return this.isEditable;
	}
	

}

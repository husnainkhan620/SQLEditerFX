package com.openfx.placeholders;

import java.util.Date;

public class ConnectionPlaceHolder {

	private String connectionName;
	private String connectionType;
	private Date connectionCreationDate;
	
	
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	public String getConnectionName() {
		return connectionName;
	}
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
	public Date getConnectionCreationDate() {
		return connectionCreationDate;
	}
	public void setConnectionCreationDate(Date connectionCreationDate) {
		this.connectionCreationDate = connectionCreationDate;
	}
	
	
}

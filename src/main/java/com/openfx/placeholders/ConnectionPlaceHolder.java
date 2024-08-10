package com.openfx.placeholders;

import java.sql.Connection;
import java.util.Date;

public class ConnectionPlaceHolder {

	private String connectionName;
	private Date connectionCreationDate;
	
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

package com.openfx.placeholders;

import javafx.scene.control.TextArea;

public class SQLCellTextArea extends TextArea {
	
	public SQLCellTextArea(String parameter) {
		super(parameter);
	}
	
	private Integer sqlcellTextAreaNumber;

	
	public SQLCellTextArea(Builder builder) {
		this.sqlcellTextAreaNumber = builder.sqlcellTextAreaNumber;
	}

	public Integer getSqlcellTextAreaNumber() {
		return this.sqlcellTextAreaNumber;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private Integer sqlcellTextAreaNumber;
		
		private Builder() {}
		
		public Builder sqlcellTextAreaNumber(Integer sqlcellTextAreaNumber) {
		
			this.sqlcellTextAreaNumber = sqlcellTextAreaNumber;
			return this;
		}
		
		public SQLCellTextArea build() {
			
			return new SQLCellTextArea(this);
		}
	}
	
}

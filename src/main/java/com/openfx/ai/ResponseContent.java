package com.openfx.ai;

public class ResponseContent {
	
	private String summary;
	private String query;
	
	public String getSummary() {
		return this.summary;
	}

	public String getQuery() {
		return this.query;
	}
	
	public ResponseContent(Builder builder) {
		this.summary = builder.summary;
		this.query = builder.query;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	
	public static class Builder{
		
		private String summary;
		private String query;
	
		private Builder() {}
		
		public Builder summary(String summary) {
			this.summary = summary;
			return this;
		}
		
		public Builder query(String query) {
			this.query = query;
			return this;
		}
		
		public ResponseContent build() {
			return new ResponseContent(this);
		}
		
	}
}

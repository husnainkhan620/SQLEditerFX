package com.openfx.ai;

public class Message {
	
	
	private String role;
	private String content;
	
	public String getRole() {
		return this.role;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public Message(Builder builder) {
		this.role = builder.role;
		this.content = builder.content;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder{
		
		private String role;
		private String content;
		
		private Builder() {}
		
		public Builder role(String role) {
			this.role = role;
			return this;
		}
		
		public Builder content(String content) {
			this.content = content;
			return this;
		}
		
		public Message build() {
			return new Message(this);
		}
		
		
	}
	

}

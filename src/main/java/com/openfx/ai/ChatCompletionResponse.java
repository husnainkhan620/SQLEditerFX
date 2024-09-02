package com.openfx.ai;

import java.util.ArrayList;

public class ChatCompletionResponse {

	private String id;
	private String object;
	private Integer created;
	private String model;
	private String system_fingerprint;
	private ArrayList<Choice> choices;
	private Usage usage;
	
	public String getId() { return this.id;}
	
	public String getObject() { return this.object; }
	
	public Integer getCreated() { return this.created; }
	
	public String getModel() { return this.model;}
	
	public String getSystemFingerprint() { return this.system_fingerprint;}
	
	public ArrayList<Choice> getChoice(){ return this.choices; }
	
	public Usage getUsage() { return this.usage ;}
	
	public ChatCompletionResponse(Builder builder) {
		this.id = builder.id;
		this.object = builder.object;
		this.created = builder.created;
		this.model = builder.model;
		this.system_fingerprint = builder.system_fingerprint;
		this.choices = builder.choices;
		this.usage = builder.usage;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder{
		
		private String id;
		private String object;
		private Integer created;
		private String model;
		private String system_fingerprint;
		private ArrayList<Choice> choices;
		private Usage usage;
		
		private Builder() {}
		
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		public Builder objects(String objects) {
			this.object = objects;
			return this;
		}
		
		public Builder created(Integer created) {
			this.created = created;
			return this;
		}
		
		public Builder model(String model) {
			this.model=model;
			return this;
		}
		
		public Builder system_fingerprint(String system_fingerprint) {
			this.system_fingerprint=system_fingerprint;
			return this;
		}
		
		public Builder choices(ArrayList<Choice> choices) {
			this.choices = choices;
			return this;
		}
				
		public Builder Usage(Usage usage) {
			this.usage = usage;
			return this;
		}
		
		public ChatCompletionResponse build() {
			return new ChatCompletionResponse(this);
		}
	}
}

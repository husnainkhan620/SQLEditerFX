package com.openfx.ai;

public class Choice {
	
	private Integer index;
	private Message message;
	private String logprobs;
	private String finish_reason;
	
	
	public Integer getIndex() { return this.index; }
	
	public Message getMessage() { return this.message; }
	
	public String getlogprobs() { return this.logprobs;}
	
	public String getFinishReason() { return this.finish_reason; }
	
	public Choice(Builder builder) {
		this.index = builder.index;
		this.message = builder.message;
		this.logprobs = builder.logprobs;
		this.finish_reason = builder.finish_reason;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder{
		
		private Integer index;
		private Message message;
		private String logprobs;
		private String finish_reason;
		
		private Builder() {}
		
		public Builder index(Integer index) {
			this.index = index;
			return this;
		}
		
		public Builder message(Message message) {
			this.message=message;
			return this;
		}
		
		public Builder logprobs(String logprobs) {
			this.logprobs = logprobs;
			return this;
		}
		
		public Builder finish_reason(String finish_reason) {
			this.finish_reason = finish_reason;
			return this;
		}
		
		public Choice build() {
			return new Choice(this);
		}
		
	}
	
	
}

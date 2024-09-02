package com.openfx.ai;

public class Usage {
	
	private Integer prompt_tokens;
	private Integer completion_tokens;
	private Integer total_tokens;
	
	
	public Integer getPromptTokens() { return this.prompt_tokens; }
	
	public Integer getCompletionTokens() { return this.completion_tokens;}
	
	public Integer getTotalTokens() { return this.total_tokens;}
	
	public Usage(Builder builder) {
		this.prompt_tokens = builder.prompt_tokens;
		this.completion_tokens = builder.completion_tokens;
		this.total_tokens = builder.total_tokens;
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder{
		
		private Integer prompt_tokens;
		private Integer completion_tokens;
		private Integer total_tokens;
		
		private Builder() {}
		
		public Builder prompt_tokens(Integer prompt_tokens) {
			this.prompt_tokens = prompt_tokens;
			return this;
		}
		
		public Builder completion_tokens(Integer completion_tokens) {
			this.completion_tokens =completion_tokens;
			return this;
		}
		
		public Builder total_tokens(Integer total_tokens) {
			this.total_tokens = total_tokens;
			return this;
		}
		
		public Usage build() {
			return new Usage(this);
		}
		
	}
	

}

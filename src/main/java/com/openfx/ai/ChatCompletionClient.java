package com.openfx.ai;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.Gson;

public class ChatCompletionClient {

	private Gson gson ;
	
	public Gson getGson() {
		if(this.gson == null) this.gson = new Gson();
		return this.gson;
	}
	
	public ChatCompletionClient(Builder builder) {
		this.gson = builder.gson;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	
	public static class Builder{
		
		private Gson gson;
		
		public Builder gson() {
			this.gson  = new Gson();
			return this;
		}
		
		public ChatCompletionClient build() {
			return new ChatCompletionClient(this);
		}
		
		
	}
	

	
	public ChatCompletionResponse sendChatCompletionRequest(ChatCompletionRequest chatCompletionRequest) {
		
		HttpClient httpClient = HttpClient.newBuilder()
				.version(Version.HTTP_2)
				.connectTimeout(Duration.ofMinutes(10))
				.build();
		
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer sk-tJpq7VTQiZyxnLHEYDpfDkC0r-wNGw0e1Op85vT46ST3BlbkFJ13Vu0w56c2jJO1HPXpnl7GMQFghIrru9Gpo0K6KLIA" )
	            .POST(BodyPublishers.ofString(new Gson().toJson(chatCompletionRequest)))
	            .uri(URI.create("https://api.openai.com/v1/chat/completions"))
	            .build(); 
	            
			HttpResponse<String> response = null;; 
	            try {
					response = httpClient.send(httpRequest,
					HttpResponse.BodyHandlers.ofString());
					
					System.out.println("Status code: " + response.statusCode());                            
			        System.out.println("Headers: " + response.headers().allValues("content-type"));
			        System.out.println("Body: " + response.body());
			         
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	            
	            ChatCompletionResponse chatCompletionResponse = null;
	            if(response != null)
	            	chatCompletionResponse = getGson().fromJson(response.body(), ChatCompletionResponse.class);
	            
	            return chatCompletionResponse;
	            
	            
	}
	
	public static void main(String args[]) {
		
		
	}
}

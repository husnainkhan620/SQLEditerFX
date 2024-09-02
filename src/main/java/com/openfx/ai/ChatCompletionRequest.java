package com.openfx.ai;

import java.util.ArrayList;

import com.google.gson.Gson;

public class ChatCompletionRequest {

	/*
	curl https://api.openai.com/v1/chat/completions \
		  -H "Content-Type: application/json" \
		  -H "Authorization: Bearer $OPENAI_API_KEY" \
		  -d '{
		    "model": "gpt-4o-mini",
		    "messages": [
		      {
		        "role": "system",
		        "content": "You are a helpful assistant."
		      },
		      {
		        "role": "user",
		        "content": "Hello!"
		      }
		    ]
		  }'
	*/
	
	private String model;
	private ArrayList<Message> messages;
	
	
	public ChatCompletionRequest(Builder builder) {
		this.model = builder.model;
		this.messages = builder.messages;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	
	public String getModel() {
		return this.model;
	}
	
	public ArrayList<Message> getMessage(){
		return this.messages;
	}
	
	
	public static class Builder{
		
		private String model;
		private ArrayList<Message> messages;
		
		private Builder() {}
		
		public Builder model(String model) {
			this.model = model;
			return this;
		}
		
		public Builder messages( ArrayList<Message> messages) {
			this.messages = messages;
			return this;
		}
		
		public ChatCompletionRequest build() {
			return new ChatCompletionRequest(this);
		}
	}
	
	
	
	public static void main(String[] args) {
		
		String systemMessage = "You are a helpful,cheerfull database assitant.Use the following database schema  when creating your answers. -actor(actor_id,first_name,last_name,last_update) -customer(customer_id,store_id,first_name,last_name,email,address_id,active,create_date,last_update) -film(film_id ,title,description ,release_year ,language_id ,original_language_id ,rental_duration ,rental_rate ,length ,replacement_cost ,rating ,special_features ,last_update ) -film_actor(actor_id,film_id,last_update) Include column name headers in the query results. Always provide your answer in the JSON format as below: {''summary'': ''your-summary'',''query'':''your query''} Output ONLY JSON.  In the preceding JSON reponse,substitute ''your-query'' with MySQL server Query to retrive the requested data. In the preceding JSON response,substitute ''your-summary'' with the summary of the query. Always include all columns in the table.";
		
		Message message1 = Message.newBuilder()
				.role("system")
				.content(systemMessage)
				.build();
		
		String userMEssage = "get the actor details who has done the most films";
		Message message2 = Message.newBuilder()
				.role("user")
				.content(userMEssage)
				.build();
		
		ArrayList<Message> messages = new ArrayList<Message>();
		
		messages.add(message1);
		messages.add(message2);
		
		ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.newBuilder()
				.model("gpt-4o-mini")
				.messages(messages)
				.build();
		
		Gson gson = new Gson();
		
		System.out.println(gson.toJson(chatCompletionRequest));
		
		
	

	}

}

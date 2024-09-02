package com.openfx.ai;

import java.util.ArrayList;

public class HttpClientTest {

	public static void main(String[] args) {
	
		Person person = Person.newInstance()
				.setId(1)
				.setName("Name1")
				.setAddress("RR Road")
				.build();
	
		System.out.println(person.getName());
		
		// For authentication https://www.baeldung.com/java-httpclient-basic-auth
		/*
		HttpClient httpClient = HttpClient.newBuilder()
				
				 * .authenticator(new Authenticator() {
				 * 
				 * @Override protected PasswordAuthentication getPasswordAuthentication() {
				 * return new PasswordAuthentication("buzz", "buzz".toCharArray()); } })
				 
		.version(HttpClient.Version.HTTP_2)
		.connectTimeout(Duration.ofSeconds(10))
		.build();
		
		
		
		 try {
	            HttpRequest request = HttpRequest.newBuilder()
	            .header("Content-Type", "application/json")
	             .header("Authorization", "Basic "+ Base64.getEncoder().encodeToString(("buzz" + ":" + "buzz").getBytes()) )
	            .GET()
	            .uri(URI.create("http://localhost:8080/findAllProducts"))
	            .build(); 
	            
	            HttpResponse<String> response = httpClient.send(request,
	            HttpResponse.BodyHandlers.ofString()); 

	         System.out.println("Status code: " + response.statusCode());                            
	         System.out.println("Headers: " + response.headers().allValues("content-type"));
	         System.out.println("Body: " + response.body());
	      } catch (IOException | InterruptedException e) {
	         e.printStackTrace();
	      }
		
		 */
		
		 
		 String systemMessage = "You are a helpful,cheerfull database assitant."
		 		+ "Use the following database schema  when creating your answers."
		 		+ " -actor(actor_id,first_name,last_name,last_update)"
		 		+ " -customer(customer_id,store_id,first_name,last_name,email,address_id,active,create_date,last_update) "
		 		+ "-film(film_id ,title,description ,release_year ,language_id ,original_language_id ,rental_duration ,rental_rate ,length ,replacement_cost ,rating ,special_features ,last_update ) "
		 		+ "-film_actor(actor_id,film_id,last_update) "
		 		+ "Include column name headers in the query results. "
		 		+ "Always provide your answer in the JSON format as below: {\"summary\": \"your-summary\",\"query\":\"your-query\"}"
		 		+ "Do not inculde '''json in the answer"
		 		+ " Output ONLY JSON.  "
		 		+ "In the preceding JSON reponse,substitute ''your-query'' with MySQL server Query to retrive the requested data."
		 		+ " In the preceding JSON response,substitute ''your-summary'' with the summary of the query."
		 		+ " Always include all columns in the table.";
			
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
			
			ChatCompletionClient chatCompletionClient   = ChatCompletionClient.newBuilder().build();
			 
			ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.newBuilder()
					.model("gpt-4o-mini")
					.messages(messages)
					.build();
			
		  
		   ChatCompletionResponse chatCompletionResponse =  chatCompletionClient.sendChatCompletionRequest(chatCompletionRequest);
		 
		   System.out.println(chatCompletionResponse.getChoice().get(0).getMessage().getContent());
		   
		   ResponseContent content = chatCompletionClient.getGson().fromJson(chatCompletionResponse.getChoice().get(0).getMessage().getContent(), ResponseContent.class);
		   
		   System.out.println(content.getSummary());
		   System.out.println(content.getQuery());
	}

}

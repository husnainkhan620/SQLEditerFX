package com.openfx.ai;

public class Person {

	private int id;
	private String name;
	private String address;
	
	public String getName() {
		return this.name;
	}
	
	public Person(Builder builder) {
		
		this.id = builder.id;
		this.name = builder.name;
		this.address = builder.address;
		
	}
	
	public static Builder newInstance() {
		return new Builder();
	}
	
	public static class Builder{
		
		private int id;
		private String name;
		private String address;
		
		private Builder() {}
		
		public Builder setId(int id) {
			this.id = id;
			return this;
		}
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}
		
		public Person build() {
			
			return new Person(this);
		}
		
		
	}
	
	
	public static void main(String[] args) {
		Person person = Person.newInstance()
				.setId(0)
				.setName("Ali")
				.setAddress("RR Road")
				.build();
		
		System.out.println(person.name);
	}

}

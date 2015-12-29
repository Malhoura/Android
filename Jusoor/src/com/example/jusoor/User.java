package com.example.jusoor;

public class User {
	String username, emailaddress, password;
	
	public User(String username, String emailaddress, String password){
		this.username = username;
		this.emailaddress = emailaddress;
		this.password = password;
	}
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
		this.emailaddress = "";
	}
	
	
}

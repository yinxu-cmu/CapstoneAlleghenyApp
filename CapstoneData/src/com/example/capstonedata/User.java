package com.example.capstonedata;

public class User {
	
	private String username = "";
	private String password = "";
	
	/**
	 * Constructor that initiates the username and password values
	 * @param username
	 * @param password
	 */
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	/**
	 *Default constructor 
	 */
	public User(){}
	
	/**
	 * Sets the user's username
	 * @param username
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * Sets the user's password
	 * @param password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * Gets the user's username
	 * @return
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * Gets the user's password
	 * @return
	 */
	public String getPassword(){
		return password;
	}

}

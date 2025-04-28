package com.cafeapp.model;

//import javax.servlet.http.Part;

public class UserModel {
	
	private int id;
	private String name;
	private String email;
	private String username;
	private String password;
	private String phoneNumber;
	private String imagePath;
	public UserModel(String name, String email, String username, String password, String phoneNumber,
			String imagePath) {
	
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.imagePath = imagePath;
	}
	
	
	public UserModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public UserModel(String name, String email, String phoneNumber, String imagePath) {
		super();
		this.name = name;
		this.email = email;
		
		this.phoneNumber = phoneNumber;
		this.imagePath = imagePath;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
	

}

package com.cafeapp.model;

/**
 * @author Pranav_Sharma LMU ID: 23048577 The UserModel class represents a user
 *         in the system. It contains details such as user ID, name, email,
 *         username, password, phone number, and profile image path.
 */
public class UserModel {

	// Unique identifier for the user
	private int id;

	// Full name of the user
	private String name;

	// Email address of the user
	private String email;

	// Username for the user account
	private String username;

	// Encrypted password for the user account
	private String password;

	// Phone number of the user
	private String phoneNumber;

	// Path to the user's profile image
	private String imagePath;

	/**
	 * Default constructor for creating an empty UserModel object.
	 */
	public UserModel() {
	}

	/**
	 * Constructs a UserModel object with the specified details of the user.
	 * 
	 * @param name        the full name of the user
	 * @param email       the email address of the user
	 * @param username    the username of the user
	 * @param password    the password of the user
	 * @param phoneNumber the phone number of the user
	 * @param imagePath   the path to the user's profile image
	 */
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

	/**
	 * Constructs a UserModel object with the specified username and password for
	 * login.
	 * 
	 * @param username the username of the user
	 * @param password the password of the user
	 */
	public UserModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * Constructs a UserModel object with the specified name, email, phone number,
	 * and image path. This constructor is used for creating a new user profile.
	 * 
	 * @param name        the full name of the user
	 * @param email       the email address of the user
	 * @param phoneNumber the phone number of the user
	 * @param imagePath   the path to the user's profile image
	 */
	public UserModel(String name, String email, String phoneNumber, String imagePath) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.imagePath = imagePath;
	}

	/**
	 * Gets the user ID.
	 * 
	 * @return the user ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the user ID.
	 * 
	 * @param id the user ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the full name of the user.
	 * 
	 * @return the full name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the full name of the user.
	 * 
	 * @param name the full name of the user to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email address of the user.
	 * 
	 * @return the email address of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the user.
	 * 
	 * @param email the email address of the user to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the username of the user.
	 * 
	 * @return the username of the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 * 
	 * @param username the username of the user to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password of the user.
	 * 
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 * 
	 * @param password the password of the user to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the phone number of the user.
	 * 
	 * @return the phone number of the user
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number of the user.
	 * 
	 * @param phoneNumber the phone number of the user to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the path to the user's profile image.
	 * 
	 * @return the path to the user's profile image
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the path to the user's profile image.
	 * 
	 * @param imagePath the path to the user's profile image to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}

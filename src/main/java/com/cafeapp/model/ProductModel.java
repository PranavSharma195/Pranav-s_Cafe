package com.cafeapp.model;

/**
 * @author Pranav_Sharma LMU ID: 23048577 The ProductModel class represents a
 *         product in the cafe's menu. It contains details about the product
 *         such as its ID, name, price, category, description, and calorie
 *         count.
 */
public class ProductModel {

	// Unique identifier for the product
	private int id;

	// Name of the product
	private String name;

	// Price of the product
	private double price;

	// Category to which the product belongs (e.g., beverages, snacks, etc.)
	private String category;

	// Description of the product
	private String description;

	// Calorie count for the product
	private int calories;

	/**
	 * Constructs a ProductModel object with the specified details of the product.
	 * 
	 * @param id          the unique identifier for the product
	 * @param name        the name of the product
	 * @param price       the price of the product
	 * @param category    the category of the product
	 * @param description the description of the product
	 * @param calories    the calorie count of the product
	 */
	public ProductModel(int id, String name, double price, String category, String description, int calories) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.description = description;
		this.calories = calories;
	}

	/**
	 * Default constructor for creating an empty ProductModel object.
	 */
	public ProductModel() {
	}

	/**
	 * Gets the product ID.
	 * 
	 * @return the product ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the product ID.
	 * 
	 * @param id the product ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name of the product.
	 * 
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the product.
	 * 
	 * @param name the name of the product to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the price of the product.
	 * 
	 * @return the price of the product
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the product.
	 * 
	 * @param price the price of the product to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the category of the product.
	 * 
	 * @return the category of the product
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category of the product.
	 * 
	 * @param category the category of the product to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the description of the product.
	 * 
	 * @return the description of the product
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the product.
	 * 
	 * @param description the description of the product to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the calorie count of the product.
	 * 
	 * @return the calorie count of the product
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * Sets the calorie count of the product.
	 * 
	 * @param calories the calorie count of the product to set
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}
}

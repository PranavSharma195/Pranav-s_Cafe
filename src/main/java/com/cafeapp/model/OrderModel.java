package com.cafeapp.model;

/**
 * @author Pranav_Sharma LMU ID: 23048577 The OrderModel class represents an
 *         order placed by a user for a specific product. It contains the
 *         details of the order such as the order ID, product ID, user ID, and
 *         quantity.
 */
public class OrderModel {

	// Unique identifier for the order
	private int orderid;

	// Product ID associated with the order
	private int productid;

	// User ID of the user placing the order
	private int userid;

	// Quantity of the product ordered
	private int quantity;

	/**
	 * Constructs an OrderModel object with the specified product ID and user ID.
	 * This constructor is typically used when placing a new order without
	 * specifying quantity.
	 * 
	 * @param productid the ID of the product being ordered
	 * @param userid    the ID of the user placing the order
	 */
	public OrderModel(int productid, int userid) {
		super();
		this.productid = productid;
		this.userid = userid;
	}

	/**
	 * Gets the order ID.
	 * 
	 * @return the order ID
	 */
	public int getOrderid() {
		return orderid;
	}

	/**
	 * Sets the order ID.
	 * 
	 * @param orderid the order ID to set
	 */
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	/**
	 * Gets the product ID associated with the order.
	 * 
	 * @return the product ID
	 */
	public int getProductid() {
		return productid;
	}

	/**
	 * Sets the product ID associated with the order.
	 * 
	 * @param productid the product ID to set
	 */
	public void setProductid(int productid) {
		this.productid = productid;
	}

	/**
	 * Gets the user ID of the user placing the order.
	 * 
	 * @return the user ID
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * Sets the user ID of the user placing the order.
	 * 
	 * @param userid the user ID to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * Gets the quantity of the product ordered.
	 * 
	 * @return the quantity of the product ordered
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the product ordered.
	 * 
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

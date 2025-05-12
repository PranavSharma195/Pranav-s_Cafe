package com.cafeapp.model;

public class OrderModel {
private int orderid;
private int productid;
private int userid;
private int quantity;
public OrderModel(int productid, int userid) {
	super();
	this.productid = productid;
	this.userid = userid;
}

public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public int getProductid() {
	return productid;
}
public void setProductid(int productid) {
	this.productid = productid;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

}

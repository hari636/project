package com.img3.orderTracking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderitems")
@IdClass(OrderItemCK.class)
public class OrderItem {

	@Id
	private int orderid;

	@ManyToOne
	@MapsId("orderid")
	@JoinColumn(name = "orderid")
	
	private Order order;

	@Id
	private int prodid;

	@ManyToOne
	@MapsId("prodid")
	@JoinColumn(name = "prodid")
	
	private Product product;

	private int quantity;

	private double price;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		if (quantity != 0 || quantity!=null) {
			this.quantity = quantity;
		} else {
			this.quantity = 1;
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
package com.img3.orderTracking.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;

	@Column(name = "order_date")
	private Date orderDate;


	private char status;

	@Column(name = "delivery_date")
	private Date delivDate;
	
	private int custid;

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "custid", insertable = false, updatable = false)
	@JsonIgnore
	private Customer customer;

	@OneToMany(mappedBy = "order")
	@JsonIgnore
	private List<OrderItem> orderItem;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getDelivDate() {
		return delivDate;
	}

	public void setDelivDate(Date delivDate) {
		if (this.status == 'd')
			this.delivDate = delivDate;
		else
			this.delivDate = null;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

}
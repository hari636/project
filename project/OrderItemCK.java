package com.img3.orderTracking.entities;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemCK implements Serializable {

	private int orderid;

	private int prodid;

	public OrderItemCK() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderid, prodid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemCK other = (OrderItemCK) obj;
		return orderid == other.orderid && prodid == other.prodid;
	}
}
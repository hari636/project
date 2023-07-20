package com.img3.orderTracking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.img3.orderTracking.entities.OrderItem;
import com.img3.orderTracking.entities.OrderItemCK;

public interface OrderItemsRepo extends JpaRepository<OrderItem, OrderItemCK> {
//6
	@Query("select product.prodname, quantity, price from OrderItem where orderid = :orderid")
	public List<String> findItemsByOrderid(@Param("orderid") int orderid);

//8
	@Query("Select product.prodname, order.customer.custname, quantity, price, order.orderDate from OrderItem where prodid = :prodid")

	public List<String> getItemsOfOrder(@Param("prodid") int prodid);

	public List<OrderItem>getByOrderid(int orderid);
	
}
package com.img3.orderTracking.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.img3.orderTracking.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {
//3
	public List<Order> findByCustomer_Custid(int custid);

//4
	public List<Order> getOrdersByOrderDateGreaterThan(Date date);

//5
	public List<Order> getOrderByStatus(char status);

//	9.2
	@Query("select max(o.orderid) from Order o")
	public int getLastOrderid();



}
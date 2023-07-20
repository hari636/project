package com.img3.orderTracking.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.img3.orderTracking.entities.Order;
import com.img3.orderTracking.repo.OrderRepo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
//@CrossOrigin
public class OrderController {

	@Autowired
	public OrderRepo ordRepo;

//	3-- List of orders by custid
	@GetMapping("/orders/custid/{custid}")
	@Operation(summary = "Orders by custid", description = "Get orders of input custid")
	public List<Order> getOrdersByCustid(@PathVariable("custid") int custid) {
		return ordRepo.findByCustomer_Custid(custid);
	}

//	4-- List of orders after given date
	@GetMapping("/orders/afterdate")
	@Operation(summary = "Orders after date", description = "Get orders after input date in yyyy-mm-dd format")
	public List<Order> getOrdersAfterDate(//
			@RequestParam("date") Date date) { // yyyy-mm-dd
//			try (@RequestParam("date") Date date){
//											}catch(Exception ex) {
//												throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Enter date in yyyy-mm-dd format")
//											}) { 
		return ordRepo.getOrdersByOrderDateGreaterThan(date);
	}

//	5-- List of orders with given status
	@GetMapping("/orders/status/{status}")
	@Operation(summary = "Order by status", description = "Get orders of input status: n = new, d = delivered, c = cancelled")
	public List<Order> getOrderByStatus(@PathVariable("status") char status) {
		if (status == 'n' || status == 'd' || status == 'c')
			return ordRepo.getOrderByStatus(status);
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter 'n'/'d'/'c' only");
	}



}
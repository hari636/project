package com.img3.orderTracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.img3.orderTracking.entities.Customer;
import com.img3.orderTracking.repo.CustomerRepo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
//@CrossOrigin
public class CustomerController {

	@Autowired
	public CustomerRepo custRepo;

//	1-- List of Customers
	@GetMapping("/customers")
	@Operation(summary = "Get all customers", description = "Get details of all customers")
	public List<Customer> getCustomers() {
		return custRepo.findAll();
	}
}
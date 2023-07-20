package com.img3.orderTracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.img3.orderTracking.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
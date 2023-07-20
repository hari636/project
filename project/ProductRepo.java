package com.img3.orderTracking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.img3.orderTracking.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
//2
	public List<Product> getProductsByProdnameContaining(String str);

//	9.3 --To get the price of product being added 
	@Query("select p.price from Product p where p.prodid = :prodid")
	public double getPriceByProdid(int prodid);
}
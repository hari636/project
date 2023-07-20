package com.img3.orderTracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.img3.orderTracking.entities.Product;
import com.img3.orderTracking.repo.ProductRepo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
//@CrossOrigin
public class ProductController {

	@Autowired
	public ProductRepo prodRepo;

//	2-- List of Products by pagination
	@GetMapping("/prods/pagination")
	@Operation(summary = "Paginated product details", description = "Get all products by pagination. Enter page number starting page as 0 to display and required no' of rows in each page")
	public Page<Product> getProds(@RequestParam("pagenum") int pagenum, @RequestParam("rows") int rows) {
		return prodRepo.findAll(PageRequest.of(pagenum, rows));
	}

//	7-- List of products where name matches given string
	@GetMapping("/prod/string/{str}")
	@Operation(summary = "String matched products", description = "Enter a string to compare with product name")
	public List<Product> getByString(@PathVariable("str") String str) {
		return prodRepo.getProductsByProdnameContaining(str);
	}
}
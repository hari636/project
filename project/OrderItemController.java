package com.img3.orderTracking.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.img3.orderTracking.entities.Order;
import com.img3.orderTracking.entities.OrderItem;
import com.img3.orderTracking.repo.OrderItemsRepo;
import com.img3.orderTracking.repo.OrderRepo;
import com.img3.orderTracking.repo.ProductRepo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
//@CrossOrigin
public class OrderItemController {

	@Autowired
	public OrderItemsRepo oiRepo;

//	6-- List of ordered items in a given order - prodname, quantity, price
	@GetMapping("/itemsdetails/order")
	@Operation(summary = "Order - Product details", description = "Products name, quantities and prices of input orderid for an ordered item")
	public List<String> getItemsOfOrder(@RequestParam("orderid") int orderid) {
		return oiRepo.findItemsByOrderid(orderid);
	}

//	8-- List of order items by given product - product name, customer name, quantity, price and order date
	@GetMapping("/itemsofprodid/{prodid}")
	@Operation(summary = "Order details for given product", description = "Products name, customer id, quantity, price and order date of order items for input prodid")
	public List<String> getItemsByOrder(@PathVariable("prodid") int prodid) {
		return oiRepo.getItemsOfOrder(prodid);
	}

//	9.2 --Add new order by taking custid and list of products and quantities ordered by the input custid

//	@Parameter(schema = @Schema(implementation = Order.class)) @RequestParam int custid,
//	@Parameter(schema = @Schema(implementation = OrderItem.class)) @RequestParam("prod") List<Integer> prodid,
//	@RequestParam("quantity") List<Integer> quantity

	@Autowired
	OrderRepo ordRepo; // For orders insertion

	@Autowired
	ProductRepo prod; // For product price

	@PostMapping("/addneworder")
	@Operation(summary = "Add an order and order details", description = "Enter customer id, products' ids and corresponding quantities")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid data passed"),
			@ApiResponse(responseCode = "200", description = "Inserted new Order details and Items of the Order") })

	public void addOrder(@RequestParam("custid") int custid, @RequestParam("prodid") List<Integer> prodids,
			@RequestParam(value = "quantities", defaultValue = "1") List<Integer> quantities) {
//			

		LocalDate date = LocalDate.now();

		Order order = new Order();
		order.setCustid(custid);
		order.setOrderDate(Date.valueOf(date));
//		order.setDelivDate(null); //Default is already null
		order.setStatus('n'); // new order

		ordRepo.save(order); // Save order to generate new orderid

		int lastOrderid = ordRepo.getLastOrderid();
		List<OrderItem> loi = new ArrayList<>();

		for (int i = 0; i < prodids.size(); i++) {
			OrderItem oi = new OrderItem();
			oi.setOrderid(lastOrderid); // Items belong to same order // Delivery Date is always null for new products
			oi.setProdid(prodids.get(i));
			if (quantities.get(i) == 0 || quantities.get(i) == null)
				oi.setQuantity(1);
			else
				oi.setQuantity(quantities.get(i));
			
			oi.setPrice(prod.getPriceByProdid(prodids.get(i)) * 0.95);

			loi.add(oi);
		}

		oiRepo.saveAll(loi); // Save the whole list of ordered items
	}

	// 10
	@GetMapping("/orders/orderid/{orderid}")
	public List<OrderItem> getByOrderid(@PathVariable("orderid") int orderid) {
		return oiRepo.getByOrderid(orderid);
	}

}
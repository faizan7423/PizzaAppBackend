package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CartDTO;
import com.serviceImpl.CartServiceImpl;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartServiceImpl cartServiceImpl;

	@PostMapping("/add")
	public CartDTO addToCart(@RequestParam int customerId, @RequestParam int pizzaId, @RequestParam int toppingsId) {
		return cartServiceImpl.addToCart(customerId, pizzaId, toppingsId);
	}

	@DeleteMapping("/deletePizza")
	public String deletePizzaCart(@RequestParam int customerId, @RequestParam int pizzaId) {
		cartServiceImpl.deleteProduct(customerId, pizzaId);
		return "deleted successfully";
	}
}

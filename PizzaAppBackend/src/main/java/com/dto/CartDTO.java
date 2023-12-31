package com.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {

	private int id;
	private List<PizzaDTO> pizzas;
	private UserDTO customer;
	private int totalQuantity;
	private double totalPrice;
	private List<ToppingsDTO> toppings;

}

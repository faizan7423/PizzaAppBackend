package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orderedCart")
public class OrderedCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany
	@JoinTable(name = "deletedcart_pizza", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
	private List<Pizza> pizzas = new ArrayList<Pizza>();

	// Toppings added here but not implemented in OrderedCartServiceImpl

	@ManyToMany
	@JoinTable(name = "orderedcart_toppings", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "toppings_id"))
	private List<Toppings> toppings = new ArrayList<Toppings>();

	@OneToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;

	private int totalQuantity;
	private double totalPrice;
	private int orderid;

}

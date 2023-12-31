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

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany
	@JoinTable(name = "cart_pizza", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
	private List<Pizza> pizzas = new ArrayList<Pizza>();

	@OneToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;

	private int totalQuantity;
	private double totalPrice;

	@ManyToMany
	@JoinTable(name = "cart_toppings", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "toppings_id"))
	private List<Toppings> toppings;

	public Cart() {
		this.toppings = new ArrayList<>();
	}

}

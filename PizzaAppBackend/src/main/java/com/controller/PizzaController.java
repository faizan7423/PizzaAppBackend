package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.PizzaDTO;
import com.serviceImpl.PizzaServiceImpl;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

	@Autowired
	private PizzaServiceImpl pizzaServiceImpl;

	@PostMapping("/addPizza")
	public PizzaDTO addNewPizza(@RequestBody PizzaDTO pizzaDTO) {
		return pizzaServiceImpl.addPizza(pizzaDTO);
	}

	@GetMapping("/getPizzaById/{id}")
	public PizzaDTO getPizzaById(@PathVariable(value = "id") int id) {
		return pizzaServiceImpl.getById(id);
	}

	@PutMapping("/updatePizza/{no}")
	public String updatePizza(@PathVariable(value = "no") int no, @RequestBody PizzaDTO product) {

		return pizzaServiceImpl.updatePizza(no, product);
	}

	@DeleteMapping("/deletePizzaById/{no}")
	public boolean deletePizza(@PathVariable(value = "no") int no) {
		pizzaServiceImpl.deletePizza(no);

		return true;
	}

}

package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ToppingsDTO;
import com.serviceImpl.ToppingsServiceImpl;

@RestController
@RequestMapping("/toppings")
public class ToppingsController {

	@Autowired
	private ToppingsServiceImpl toppingsServiceImpl;

	@PostMapping("/addToppings")
	public ToppingsDTO addNewToppings(@Valid @RequestBody ToppingsDTO toppingsDTO) {
		return toppingsServiceImpl.addToppings(toppingsDTO);
	}

	@PutMapping("/updateToppings/{no}")
	public String updateToppings(@PathVariable(value = "no") int no, @RequestBody ToppingsDTO toppingsDTO) {
		toppingsServiceImpl.updateToppings(no, toppingsDTO);
		return "Updated Successfully";
	}

	@DeleteMapping("/deleteToppings/{no}")
	public String deleteToppings(@PathVariable(value = "no") int no) {
		toppingsServiceImpl.removeToppings(no);
		return " Deleted Successfully";
	}

	@GetMapping("/getToppingsUsingId/{id}")
	public ToppingsDTO getToppingsById(@PathVariable(value = "id") int id) {
		return toppingsServiceImpl.searchToppingsById(id);
	}

}

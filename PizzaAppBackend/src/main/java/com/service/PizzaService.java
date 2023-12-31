package com.service;

import java.util.List;

import com.dto.PizzaDTO;

public interface PizzaService {

	public PizzaDTO getById(int id);

	public String updatePizza(int id, PizzaDTO pizzaDTO);

	public boolean deletePizza(int no);

	public PizzaDTO addPizza(PizzaDTO pizzaDTO);

	public List<PizzaDTO> findAll();

}

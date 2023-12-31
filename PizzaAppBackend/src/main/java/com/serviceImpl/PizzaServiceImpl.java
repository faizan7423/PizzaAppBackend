package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.PizzaNotFoundException;
import com.dto.PizzaDTO;
import com.entity.Pizza;
import com.repository.PizzaRepository;
import com.service.PizzaService;

@Service
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	public PizzaDTO addPizza(PizzaDTO pizzaDTO) {
		Pizza pizza = new Pizza();
		pizza.setCategory(pizzaDTO.getCategory());
		pizza.setPizzaId(pizzaDTO.getPizzaId());
		pizza.setPizzaImage(pizzaDTO.getPizzaImage());
		pizza.setPizzaName(pizzaDTO.getPizzaName());
		pizza.setPizzaPrice(pizzaDTO.getPizzaPrice());
		pizza.setQuantity(pizzaDTO.getQuantity());

		pizzaRepository.save(pizza);
		return pizzaDTO;
	}

	public PizzaDTO getById(int id) {
		Pizza pizza = pizzaRepository.findById(id).get();

		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setCategory(pizza.getCategory());
		pizzaDTO.setPizzaId(pizza.getPizzaId());
		pizzaDTO.setPizzaImage(pizza.getPizzaImage());
		pizzaDTO.setPizzaName(pizza.getPizzaName());
		pizzaDTO.setPizzaPrice(pizza.getPizzaPrice());
		pizzaDTO.setQuantity(pizza.getQuantity());

		return pizzaDTO;

	}

	public String updatePizza(int id, PizzaDTO pizzaDTO) {

		Pizza pizza;
		try {
			pizza = pizzaRepository.findById(id).orElseThrow(() -> new PizzaNotFoundException());

			if (pizza.getPizzaName() != null)
				pizza.setPizzaName(pizzaDTO.getPizzaName());
			if (pizza.getCategory() != null)
				pizza.setCategory(pizzaDTO.getCategory());
			if (pizza.getPizzaImage() != null)
				pizza.setPizzaImage(pizzaDTO.getPizzaImage());
			if (pizza.getPizzaPrice() != 0)
				pizza.setPizzaPrice(pizzaDTO.getPizzaPrice());
			if (pizza.getQuantity() != 0)
				pizza.setQuantity(pizzaDTO.getQuantity());

			pizzaRepository.save(pizza);
		} catch (PizzaNotFoundException e) {
			System.out.println(e);
			return "Pizza data not updated";
		}
		return "Pizza updated Successfully";

	}

	public boolean deletePizza(int id) {

		pizzaRepository.deleteById(id);
		return true;

	}

	public List<PizzaDTO> findAll() {
		List<Pizza> pizzas = pizzaRepository.findAll();
		List<PizzaDTO> pizzaDTOs = new ArrayList<PizzaDTO>();
		for (Pizza pizza : pizzas) {
			PizzaDTO pizzaDTO = new PizzaDTO();
			pizzaDTO.setCategory(pizza.getCategory());
			pizzaDTO.setPizzaId(pizza.getPizzaId());
			pizzaDTO.setPizzaImage(pizza.getPizzaImage());
			pizzaDTO.setPizzaName(pizza.getPizzaName());
			pizzaDTO.setPizzaPrice(pizza.getPizzaPrice());
			pizzaDTO.setQuantity(pizza.getQuantity());

			pizzaDTOs.add(pizzaDTO);
		}
		return pizzaDTOs;

	}

}

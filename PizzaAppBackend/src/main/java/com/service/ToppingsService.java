package com.service;

import com.dto.ToppingsDTO;

public interface ToppingsService {

	public ToppingsDTO addToppings(ToppingsDTO toppings);

	public String updateToppings(int toppingsId, ToppingsDTO toppingsDTO);

	public String removeToppings(int toppingsId);

	public ToppingsDTO searchToppingsById(int toppingsId);

}

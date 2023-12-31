package com.service;

import com.dto.CartDTO;

public interface CartService {

	public CartDTO addToCart(int customerId, int pizzaId, int toppingsId);

	public String deleteProduct(int custid, int prodid);

}

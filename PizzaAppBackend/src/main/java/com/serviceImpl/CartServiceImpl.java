package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.AddToCartNotFoundException;
import com.advices.CustomerNotFoundException;
import com.advices.ToppigsNotFoundException;
import com.dto.CartDTO;
import com.dto.PizzaDTO;
import com.dto.ToppingsDTO;
import com.dto.UserDTO;
import com.entity.Cart;
import com.entity.CustomerEntity;
import com.entity.Pizza;
import com.entity.Toppings;
import com.repository.CartRepository;
import com.repository.CustomerRepository;
import com.repository.PizzaRepository;
import com.repository.ToppingsRepository;
import com.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ToppingsRepository toppingsRepository;

	public CartDTO addToCart(int customerId, int pizzaId, int toppingsId) {
		double total;
		int quantity;
		double toppingsTotal;
		CartDTO cartDTO = new CartDTO();

		CustomerEntity customer;
		try {
			customer = customerRepository.findById(customerId).orElseThrow(() -> new AddToCartNotFoundException());

			Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new AddToCartNotFoundException());
			
			//Finding the toppings based on id
			Toppings toppings = toppingsRepository.findById(toppingsId).orElseThrow(() -> new ToppigsNotFoundException());
			
			System.out.println(pizza);

			Cart cart = customer.getCart();

			if (cart == null) {
				cart = new Cart();
				customer.setCart(cart);

				cart.setCustomer(customer);

			}
			
			

			total = cart.getTotalPrice();

			quantity = cart.getTotalQuantity();

			quantity = quantity + 1;
			
			//getting toppings price 
			toppingsTotal = toppings.getToppingsPrice();
			// toppings price added here with total

			total = pizza.getPizzaPrice() + total + toppingsTotal;
			cart.setTotalPrice(total);
			cart.setTotalQuantity(quantity);
			cart.getPizzas().add(pizza);
			cart.getToppings().add(toppings);
			System.out.println(cart.getPizzas());

			cartRepository.save(cart);

			UserDTO userDTO = new UserDTO();

			userDTO.setAddress(customer.getAddress());
			userDTO.setEmail(customer.getEmail());
			userDTO.setName(customer.getName());
			userDTO.setNumber(customer.getNumber());
			userDTO.setPassword(customer.getPassword());
			userDTO.setRole(customer.getRole());
			userDTO.setId(customer.getId());
			userDTO.setUsername(customer.getUsername());

			cartDTO.setCustomer(userDTO);

			List<PizzaDTO> pizzaDTOs = new ArrayList<>();
			for (Pizza product_i : cart.getPizzas()) {
				PizzaDTO pizzaDTO = new PizzaDTO();
				pizzaDTO.setCategory(product_i.getCategory());
				pizzaDTO.setPizzaId(product_i.getPizzaId());
				pizzaDTO.setPizzaImage(product_i.getPizzaImage());
				pizzaDTO.setPizzaName(product_i.getPizzaName());
				pizzaDTO.setPizzaPrice(product_i.getPizzaPrice());
				pizzaDTO.setQuantity(product_i.getQuantity());

				pizzaDTOs.add(pizzaDTO);
			}
			
			// converting toppings from cart to cartDTO
			List<ToppingsDTO> toppingsDTOs = new ArrayList<>();
			for (Toppings t : cart.getToppings()) {
				ToppingsDTO tpd = new ToppingsDTO();
				tpd.setToppingsId(t.getToppingsId());
				tpd.setToppingsName(t.getToppingsName());
				tpd.setToppingsPrice(t.getToppingsPrice());
				toppingsDTOs.add(tpd);
			}
			cartDTO.setPizzas(pizzaDTOs);
			cartDTO.setTotalPrice(total);
			cartDTO.setTotalQuantity(quantity);
			cartDTO.setToppings(toppingsDTOs);

		} catch (AddToCartNotFoundException | ToppigsNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return cartDTO;

	}

	public String deleteProduct(int custid, int prodid) {

		CustomerEntity customer;
		try {
			customer = customerRepository.findById(custid).orElseThrow(() -> new CustomerNotFoundException());

			Cart cart = customer.getCart();

			List<Pizza> pizzas = cart.getPizzas();
			int quantity;
			double totalprice;
			quantity = cart.getTotalQuantity();
			totalprice = cart.getTotalPrice();
			for (Pizza pizza : pizzas) {
				if (pizza.getPizzaId() == prodid) {
					quantity = quantity - 1;
					totalprice = totalprice - pizza.getPizzaPrice();
					cartRepository.deleteByCartProduct(prodid);
				}
			}
			cart.setTotalPrice(totalprice);
			cart.setTotalQuantity(quantity);
			cartRepository.save(cart);

			return "deleted Successfully";
		} catch (CustomerNotFoundException e) {
			System.out.println(e);
			return "Customer not Found";
		}

	}
}
package com.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.CustomerNotFoundException;
import com.dto.CartDTO;
import com.dto.OrdersDTO;
import com.dto.PizzaDTO;
import com.dto.UserDTO;
import com.entity.Cart;
import com.entity.CustomerEntity;
import com.entity.OrderedCart;
import com.entity.Orders;
import com.entity.Pizza;
import com.entity.Status;
import com.repository.CartRepository;
import com.repository.OrderedCartRepository;
import com.repository.OrdersRepository;
import com.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private OrderedCartRepository orderedCartRepository;

	public OrdersDTO addOrders(int customerId) {

		Cart cart;
		OrdersDTO ordersDTO = new OrdersDTO();
		try {
			cart = cartRepository.findByCustomerId(customerId).orElseThrow(() -> new CustomerNotFoundException());

			if (cart != null) {
				CartDTO cartDTO = mapCartToDTO(cart);

				OrderedCart orderedCart = new OrderedCart();

				List<Pizza> pizzas = cart.getPizzas();

				List<Pizza> pizzas2 = new ArrayList<Pizza>();

				for (Pizza pizza : pizzas) {

					Pizza pizza3 = new Pizza();

					pizza3.setCategory(pizza.getCategory());
					pizza3.setPizzaId(pizza.getPizzaId());
					pizza3.setPizzaImage(pizza.getPizzaImage());
					pizza3.setPizzaName(pizza.getPizzaName());
					pizza3.setPizzaPrice(pizza.getPizzaPrice());
					pizza3.setQuantity(pizza.getQuantity());

					pizzas2.add(pizza3);

				}

				CustomerEntity customer = cart.getCustomer();
				Orders order = new Orders();

				order.setCart(cart);
				order.setDate(LocalDateTime.now());
				order.setStatus(Status.pending);

				ordersRepository.save(order);

				orderedCart.setPizzas(pizzas2);
				orderedCart.setCustomer(customer);
				orderedCart.setOrderid(order.getOrderId());
				orderedCart.setTotalPrice(cart.getTotalPrice());
				orderedCart.setTotalQuantity(cart.getTotalQuantity());

				orderedCartRepository.save(orderedCart);

				cart.getPizzas().clear();
				cart.setTotalPrice(0);
				cart.setTotalQuantity(0);

				cartRepository.save(cart);

				ordersDTO.setOrderId(order.getOrderId());
				ordersDTO.setCart(cartDTO);
				ordersDTO.setDate(LocalDateTime.now());
				ordersDTO.setStatus(Status.pending);

			}
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return ordersDTO;
	}

	public OrdersDTO getById(int id) {

		Orders order = ordersRepository.findById(id).get();
		OrderedCart cart = orderedCartRepository.findByOrderId(id);

		OrdersDTO orderDTO = new OrdersDTO();

		orderDTO.setCart(mapCartToDTO(cart));

		orderDTO.setDate(order.getDate());
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setStatus(order.getStatus());

		return orderDTO;
	}

	public List<OrdersDTO> findAll() {

		List<Orders> orders = ordersRepository.findAll();

		List<OrdersDTO> ordersDTOs = new ArrayList<OrdersDTO>();
		List<OrderedCart> carts = orderedCartRepository.findAll();
		for (Orders order : orders) {
			OrdersDTO orderDTO = new OrdersDTO();
			for (OrderedCart cart : carts) {
				if (order.getOrderId() == cart.getOrderid())
					orderDTO.setCart(mapCartToDTO(cart));
			}
			orderDTO.setDate(order.getDate());
			orderDTO.setOrderId(order.getOrderId());
			orderDTO.setStatus(order.getStatus());

			ordersDTOs.add(orderDTO);

		}

		return ordersDTOs;
	}

	public String deleteOrders(int orderId) {

		OrderedCart cart = orderedCartRepository.findByOrderId(orderId);

		cart.getPizzas().clear();
		;

		ordersRepository.deleteById(orderId);
		orderedCartRepository.deleteByOrderId(orderId);
		return "Deleted Successfully";
	}

	public List<OrdersDTO> getOrderCustomerId(int customerId) {

		List<OrderedCart> carts = orderedCartRepository.findByCustomerId(customerId);

		List<Orders> orders = ordersRepository.findAll();

		List<OrdersDTO> ordersDTOs = new ArrayList<OrdersDTO>();

		for (Orders order : orders) {
			for (OrderedCart cart : carts) {
				if (order.getOrderId() == cart.getOrderid()) {
					OrdersDTO orderDTO = new OrdersDTO();
					orderDTO.setDate(order.getDate());
					orderDTO.setOrderId(order.getOrderId());
					orderDTO.setStatus(order.getStatus());
					orderDTO.setCart(mapCartToDTO(cart));

					ordersDTOs.add(orderDTO);

				}
			}
		}
		return ordersDTOs;

	}

	private CartDTO mapCartToDTO(Cart cart) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setId(cart.getId());
		cartDTO.setCustomer(mapCustomerToDTO(cart.getCustomer()));
		cartDTO.setPizzas(mapPizzasToDTO(cart.getPizzas()));
		cartDTO.setTotalPrice(cart.getTotalPrice());
		cartDTO.setTotalQuantity(cart.getTotalQuantity());
		return cartDTO;
	}

	private UserDTO mapCustomerToDTO(CustomerEntity customer) {
		UserDTO customerDTO = new UserDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setNumber(customer.getNumber());
		customerDTO.setUsername(customer.getUsername());
		customerDTO.setRole(customer.getRole());
		customerDTO.setName(customer.getName());
		return customerDTO;
	}

	private List<PizzaDTO> mapPizzasToDTO(List<Pizza> pizzas) {
		return pizzas.stream().map(product -> {
			PizzaDTO pizzaDTO = new PizzaDTO();
			pizzaDTO.setCategory(product.getCategory());
			pizzaDTO.setPizzaId(product.getPizzaId());
			pizzaDTO.setPizzaImage(product.getPizzaImage());
			pizzaDTO.setPizzaName(product.getPizzaName());
			pizzaDTO.setPizzaPrice(product.getPizzaPrice());
			pizzaDTO.setQuantity(product.getQuantity());
			return pizzaDTO;
		}).collect(Collectors.toList());
	}

	private CartDTO mapCartToDTO(OrderedCart cart) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setId(cart.getId());
		cartDTO.setCustomer(mapCustomerToDTO(cart.getCustomer()));
		cartDTO.setPizzas(mapPizzasToDTO(cart.getPizzas()));
		cartDTO.setTotalPrice(cart.getTotalPrice());
		cartDTO.setTotalQuantity(cart.getTotalQuantity());
		return cartDTO;
	}

}

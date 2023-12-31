package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.CustomerNotFoundException;
import com.dto.CartDTO;
import com.dto.PizzaDTO;
import com.dto.UserDTO;
import com.entity.Cart;
import com.entity.CustomerEntity;
import com.entity.Pizza;
import com.repository.CartRepository;
import com.repository.CustomerRepository;
import com.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CartRepository cartRepository;

	public UserDTO registerCustomer(UserDTO userDTO) {
		CustomerEntity customer = new CustomerEntity();
		customer.setUsername(userDTO.getUsername());
		customer.setAddress(userDTO.getAddress());
		customer.setEmail(userDTO.getEmail());
		customer.setName(userDTO.getName());
		customer.setNumber(userDTO.getNumber());
		customer.setPassword(userDTO.getPassword());
		customer.setRole(userDTO.getRole());
		customer.setId(userDTO.getId());

		customerRepository.save(customer);
		return userDTO;
	}

	// Read all Users
	public List<UserDTO> readAllCustomers() {
		List<CustomerEntity> customers = customerRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (CustomerEntity customer : customers) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(customer.getUsername());
			userDTO.setAddress(customer.getAddress());
			userDTO.setRole(customer.getRole());
			userDTO.setEmail(customer.getEmail());
			userDTO.setNumber(customer.getNumber());
			userDTO.setName(customer.getName());
			userDTO.setId(customer.getId());
			userDTOs.add(userDTO);

		}

		return userDTOs;
	}

	// Update User
	public String updateCustomer(int id, UserDTO user) {

		CustomerEntity customer;
		try {
			customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException());

			if (user.getUsername() != null)
				customer.setUsername(user.getUsername());

			if (user.getRole() != null)
				customer.setRole(user.getRole());

			if (user.getNumber() != 0)
				customer.setNumber(user.getNumber());

			if (user.getAddress() != null)
				customer.setAddress(user.getAddress());

			if (user.getEmail() != null)
				customer.setEmail(user.getEmail());

			if (user.getName() != null)
				customer.setName(user.getName());

			customerRepository.save(customer);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Customer data not updated";

		}

		return "Customer Updated Successfully";

	}

	// Delete user
	public boolean deleteCustomer(int id) {

		customerRepository.deleteById(id);

		return true;
	}

	public List<UserDTO> getByEmail(String email) {
		List<CustomerEntity> customers = customerRepository.findByEmail(email);

		List<UserDTO> userDTOs = new ArrayList<UserDTO>();

		for (CustomerEntity customer : customers) {
			UserDTO dtoUser = new UserDTO();
			dtoUser.setName(customer.getName());
			dtoUser.setAddress(customer.getAddress());
			dtoUser.setEmail(customer.getEmail());
			dtoUser.setNumber(customer.getNumber());
			dtoUser.setRole(customer.getRole());
			dtoUser.setUsername(customer.getUsername());
			dtoUser.setId(customer.getId());
			userDTOs.add(dtoUser);
		}

		return userDTOs;
	}

	public CartDTO getCartByCustomer(int customerId) {

		Optional<Cart> cart = cartRepository.findByCustomerId(customerId);
		if (cart.isPresent()) {
			CartDTO cartDTO = mapCartToDTO(cart.get());
			return cartDTO;
		}
		return null; // Or throw an exception for not found
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
		customerDTO.setPassword(customer.getPassword());
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

}

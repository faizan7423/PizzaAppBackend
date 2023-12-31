package com.controller;

import java.util.List;

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

import com.dto.CartDTO;
import com.dto.UserDTO;
import com.serviceImpl.CustomerServiceImpl;

@RestController
@RequestMapping("/user")
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@PostMapping("/registerCustomer")
	public UserDTO addCustomer(@Valid @RequestBody UserDTO userDTO) {
		return customerServiceImpl.registerCustomer(userDTO);
	}

	@GetMapping("/allCustomers")
	public List<UserDTO> readAllCustomers() {
		return customerServiceImpl.readAllCustomers();
	}

	@PutMapping("/updateCustomer/{no}")
	public String updateCustomer(@Valid @PathVariable(value = "no") int no, @RequestBody UserDTO user) {

		return customerServiceImpl.updateCustomer(no, user);
	}

	@DeleteMapping("/deleteCustomer/{no}")
	public boolean deleteCustomer(@PathVariable(value = "no") int no) {
		customerServiceImpl.deleteCustomer(no);
		return true;
	}

	@GetMapping("/CustomerByEmail/{email}")
	public List<UserDTO> getCustomerByEmail(@PathVariable(value = "email") String email) {
		return customerServiceImpl.getByEmail(email);
	}

	@GetMapping("/getCart/{custid}")
	public CartDTO getCart(@PathVariable(value = "custid") int custid) {
		return customerServiceImpl.getCartByCustomer(custid);
	}

}

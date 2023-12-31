package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDTO;
import com.serviceImpl.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminServiceImpl adminServiceImpl;

	@PostMapping("/registerAdmin")
	public UserDTO addAdmin(@Valid @RequestBody UserDTO adminDTO) {
		return adminServiceImpl.addAdmin(adminDTO);
	}

	@PutMapping("/updateAdmin/{no}")
	public String updateAdmin(@Valid @PathVariable(value = "no") int no, @RequestBody UserDTO admin) {

		return adminServiceImpl.updateAdmin(no, admin);
	}

	@GetMapping("/adminByEmail/{email}")
	public UserDTO getAdminByEmail(@PathVariable(value = "email") String email) {
		return adminServiceImpl.getAdminByEmail(email);
	}

}

package com.entity;

import javax.persistence.Entity;

@Entity
public class AdminEntity extends UserEntity {
	public AdminEntity(int id, String username, String password, String email, String role, String name, Long number,
			String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.name = name;
		this.number = number;
		this.address = address;
	}

	public AdminEntity() {
		// TODO Auto-generated constructor stub
	}
}

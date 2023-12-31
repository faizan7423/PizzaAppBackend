package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Inheritance
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userstable")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column
	@NotNull
	@NotEmpty(message = "username should not be empty")
	protected String username;
	@Column
	@Size(min = 5, message = "minimum 5 character is required")
	protected String password;
	@Column
	@Email
	protected String email;
	@Column
	@NotNull
	@NotEmpty(message = "username should be empty")
	protected String role;
	@Column
	@NotNull
	@NotEmpty(message = "username should be empty")
	protected String name;
	@Column
	@Size(min = 10, max = 10, message = "number must be valid")
	protected Long number;
	@Column
	@NotNull
	@NotEmpty(message = "username should be empty")
	protected String address;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
	protected Cart cart;

	public UserEntity(int id, String username, String password, String email, String role, String name, Long number,
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

}

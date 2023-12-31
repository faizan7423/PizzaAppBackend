package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "toppings")
public class Toppings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer toppingsId;
	@Column
	@NotNull
	@NotEmpty(message = "toppings should not be empty")
	private String toppingsName;
	@Column
	@NotNull
	@NotEmpty(message = "toppings price should not be empty")
	private Double toppingsPrice;
}

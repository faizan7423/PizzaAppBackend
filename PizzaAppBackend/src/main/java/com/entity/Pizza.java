package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pizza")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pizzaId;
	@Column
	@NotNull
	@NotEmpty(message = "pizzaName should not be empty")
	private String pizzaName;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@Column
	@NotNull
	@NotEmpty(message = "pizzaPrice should not be empty")
	private double pizzaPrice;
	@Column
	private String pizzaImage;
	@Column
	private int quantity;

}

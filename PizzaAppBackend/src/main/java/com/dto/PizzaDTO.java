package com.dto;

import com.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO {

	private int pizzaId;
	private String pizzaName;
	private double pizzaPrice;
	private String pizzaImage;
	private int quantity;
	private Category category;

}

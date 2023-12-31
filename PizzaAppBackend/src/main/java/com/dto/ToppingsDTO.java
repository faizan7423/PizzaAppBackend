package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToppingsDTO {

	private Integer toppingsId;
	private String toppingsName;
	private Double toppingsPrice;

}

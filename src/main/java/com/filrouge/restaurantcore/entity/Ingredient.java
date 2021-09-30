package com.filrouge.restaurantcore.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity Ingrdient
 * 
 * @author Hermann
 *
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ingredient")
public class Ingredient extends AbstractEntity {

//	public Ingredient() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * name
	 */
	@Field("name")
	private String name;

	/**
	 * price of ingredient
	 */
	@Field("purchasePrice")
	private BigDecimal purchasePrice;

}

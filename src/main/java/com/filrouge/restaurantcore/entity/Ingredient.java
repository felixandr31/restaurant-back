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
 * @author Hermann
 *
 */

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ingredient")
public class Ingredient extends AbstractEntity {
	
	/**
	 *  name
	 */
	@Field("name")
	private String name;
	
	/**
	 * price of ingredient
	 */
	@Field("price")
	private BigDecimal price;

}

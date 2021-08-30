package com.filrouge.restaurantcore.entity;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@Document("recipe")
public class Recipe extends AbstractEntity{
	
	/**
	 * the list of ingredients
	 */

	 @JsonIgnore
	 private List<Ingredient> Ingredients;
	 
	 /**
	  * the craftion price
	  */
	 
	 @Field("craftingPrice")
	 private BigInteger craftingPrice;
	 
	 /**
	  * the selling price
	  */
	 @Field("sellingPrice")
	 private BigInteger sellingPrice;
}

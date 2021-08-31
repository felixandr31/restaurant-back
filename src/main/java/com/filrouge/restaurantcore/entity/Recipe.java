package com.filrouge.restaurantcore.entity;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Entity Recipe
 * @author formation
 *
 */

@Data
@Builder

@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@Document("recipe")
public class Recipe extends AbstractEntity{
	
	/**
	 * the name of recipe
	 */
	@Field("recipe")
	@NonNull
	private String name;
	 
	 /**
	  * the craftion price
	  */
	@NonNull
	 @Field("craftingPrice")
	 private BigInteger craftingPrice;
	 
	 /**
	  * the selling price
	  */
	 @Field("sellingPrice")
	 private BigInteger sellingPrice;
	 
	 /**
		 * the Map of ingredients
		 */

		@DBRef
		 private Map<String, Integer> ingredients;
	 
	 /**
		 * constructor NoArgs
		 */
		
		public Recipe() {
			this.ingredients = new HashMap<String, Integer>(0);
			
		}
		
}

package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.RecipeDto;


public class RecipeValidator {
	
	public static List<String> validate(final RecipeDto dto) {
		final List<String> errors = new ArrayList<String>();
		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("Name is required");
			errors.add("Crafting price is required");
			errors.add("Selling price is required");
			

			return errors;
		}
		
	

		if (!StringUtils.hasLength(dto.getName())) {
			errors.add("Name is required");
		}
		if (dto.getCraftingPrice() != null && !StringUtils.hasLength(dto.getCraftingPrice().toString())) {
			errors.add("CraftingPrice");
		}
		if (dto.getSellingPrice() != null && !StringUtils.hasLength(dto.getSellingPrice().toString())) {
			errors.add("SellingPrice");
		}
	
	   return errors;
	
	}
	
}

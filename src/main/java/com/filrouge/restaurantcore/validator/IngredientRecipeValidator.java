package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.IngredientRecipeDto;


public class IngredientRecipeValidator {
	
	public static List<String> validate(final IngredientRecipeDto dto) {
		final List<String> errors = new ArrayList<String>();
		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("Quantity is required");
			errors.addAll(IngredientValidator.validate(null));

			return errors;
		}
		
		if (dto.getQuantity() != null && !StringUtils.hasLength(dto.getQuantity().toString())) {
			errors.add("Quantity is required");
		}
		errors.addAll(IngredientValidator.validate(dto.getIngredient()));
		return errors;
		
	}

}

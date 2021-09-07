package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.IngredientRecipeDto;

/**
 * Services de validation des données des ingredientRecipes.
 * 
 * @author FAndre
 *
 */
public class IngredientRecipeValidator {
	
	/**
	 * Validation du DTO.
	 * 
	 * @param dto le DTO
	 * @return les erreurs pour chaque attribut.
	 */
	public static List<String> validate(final IngredientRecipeDto dto) {
		final List<String> errors = new ArrayList<String>();
		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("quantity is required");
			errors.add("ingredient is required");

			return errors;
		}

		if (dto.getQuantity() != null && !StringUtils.hasLength(dto.getQuantity().toString())) {
			errors.add("quantity is required");
		}

		if (dto.getIngredient() != null && !StringUtils.hasLength(dto.getIngredient().toString())) {
			errors.add("quantity is required");
		}

		return errors;
	}

}

package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.IngredientRecipeDto;

<<<<<<< HEAD

public class IngredientRecipeValidator {
	
=======
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
>>>>>>> d98e6705b0ce00c8b2b8bb3cda30bef15d59e7f5
	public static List<String> validate(final IngredientRecipeDto dto) {
		final List<String> errors = new ArrayList<String>();
		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
<<<<<<< HEAD
			errors.add("Quantity is required");
			errors.addAll(IngredientValidator.validate(null));

			return errors;
		}
		
		if (dto.getQuantity() != null && !StringUtils.hasLength(dto.getQuantity().toString())) {
			errors.add("Quantity is required");
		}
		errors.addAll(IngredientValidator.validate(dto.getIngredient()));
		return errors;
		
=======
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
>>>>>>> d98e6705b0ce00c8b2b8bb3cda30bef15d59e7f5
	}

}

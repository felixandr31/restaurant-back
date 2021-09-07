package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.IngredientDto;

/**
 * Services de validation des données des ingredients.
 * 
 * @author sslimani
 *
 */
public class IngredientValidator {
	/**
	 * Validation du DTO.
	 * 
	 * @param dto le DTO
	 * @return les erreurs pour chaque attribut.
	 */
	public static List<String> validate(final IngredientDto dto) {
		final List<String> errors = new ArrayList<String>();
		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("name is required");
			errors.add("purchasePrice is required");

			return errors;
		}

		if (dto.getName() != null && !StringUtils.hasLength(dto.getName().toString())) {
			errors.add("quantity is required");
		}

		if (dto.getPurchasePrice() != null && !StringUtils.hasLength(dto.getPurchasePrice().toString())) {
			errors.add("quantity is required");
		}

		return errors;
	}
}

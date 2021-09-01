package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;


import com.filrouge.restaurantcore.dto.StockDto;

/**
 * Services de validation des données de l'adresse.
 * 
 * @author sslimani
 *
 */
public class StockValidator {
	/**
	 * Validation du DTO.
	 * 
	 * @param dto le DTO
	 * @return les erreurs pour chaque attribut.
	 */
	public static List<String> validate(final StockDto dto) {
		final List<String> errors = new ArrayList<String>();
		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("quantity is required");
			errors.addAll(IngredientValidator.validate(null));

			return errors;
		}
		
		errors.addAll(IngredientValidator.validate(dto.getIngredient()));

		if (dto.getQuantity() != null && !StringUtils.hasLength(dto.getQuantity().toString())) {
			errors.add("quantity is required");
		}

		return errors;
	}
}

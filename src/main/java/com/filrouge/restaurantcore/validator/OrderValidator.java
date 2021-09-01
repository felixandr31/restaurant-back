package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.OrderDto;

/**
 * Services de validation des données de l'adresse.
 * 
 * @author sslimani
 *
 */
public class OrderValidator {
	/**
	 * Validation du DTO.
	 * 
	 * @param dto le DTO
	 * @return les erreurs pour chaque attribut.
	 */
	public static List<String> validate(final OrderDto dto) {
		final List<String> errors = new ArrayList<String>();
		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("item is required");
			errors.add("quatity is required");

			return errors;
		}
		if (dto.getItem() != null && !StringUtils.hasLength(dto.getItem().toString())) {
			errors.add("item is required");
		}
		if (dto.getQuantity() != null && !StringUtils.hasLength(dto.getQuantity().toString())) {
			errors.add("quatity is required");
		}

		return errors;
	}
}

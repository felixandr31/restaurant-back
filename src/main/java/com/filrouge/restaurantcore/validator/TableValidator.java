package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.TableDto;

/**
 * Services de validation des données de l'adresse.
 * 
 * @author sslimani
 *
 */
public class TableValidator {
	/**
	 * Validation du DTO.
	 * 
	 * @param dto le DTO
	 * @return les erreurs pour chaque attribut.
	 */
	public static List<String> validate(final TableDto dto) {
		final List<String> errors = new ArrayList<String>();
		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("capacity is required");
			errors.add("name is required");

			return errors;
		}
		if (dto.getCapacity() != null && !StringUtils.hasLength(dto.getCapacity().toString())) {
			errors.add("capacity is required");
		}
		if (dto.getName() != null && !StringUtils.hasLength(dto.getName().toString())) {
			errors.add("name is required");
		}

		return errors;
	}
}

package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.RoleDto;

import com.filrouge.restaurantcore.util.MessagesUtil;

public class RoleValidator {
private static final MessagesUtil MESSAGE_UTILS = MessagesUtil.getInstance("message");
	
	/**
	 * DTO validation
	 * @param dto the DTO
	 * @return errors for each attribute.
	 */
	
	public static List<String> validate(final RoleDto dto) {
		final List<String> errors = new ArrayList<String>();

		//TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add(MESSAGE_UTILS.getMessage("message.validator.role.name"));
			
			return errors;
		}

		if (!StringUtils.hasLength(dto.getName())) {
			errors.add(MESSAGE_UTILS.getMessage("message.validator.role.name.required"));
		
		}
		
		return errors;
	}

}

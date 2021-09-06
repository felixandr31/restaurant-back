package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IIngredientRepository;
import com.filrouge.restaurantcore.dto.IngredientDto;
import com.filrouge.restaurantcore.dto.RoleDto;
import com.filrouge.restaurantcore.service.IIngredientService;

@Service
public class IngredientServiceImpl implements IIngredientService {
	
	// DAOs
		private IIngredientRepository ingredientRepository;
		
		
		
		/**
		 * Constructor.
		 * @param ingredientRepository the roles DTO.
		 */
		public IngredientServiceImpl(IIngredientRepository ingredientRepository) {
			super();
			this.ingredientRepository = ingredientRepository;
		}

	@Override
	public List<IngredientDto> findAll() {
		return ingredientRepository.findAll().stream().map(IngredientDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public IngredientDto save(IngredientDto dto) {
		return IngredientDto.fromEntity(ingredientRepository.save(IngredientDto.toEntity(dto)));
	}

}

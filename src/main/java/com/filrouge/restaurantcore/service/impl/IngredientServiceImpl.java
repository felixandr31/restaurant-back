package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IIngredientRepository;
import com.filrouge.restaurantcore.dto.IngredientDto;
import com.filrouge.restaurantcore.dto.RoleDto;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.entity.Ingredient;
import com.filrouge.restaurantcore.entity.Role;
import com.filrouge.restaurantcore.entity.User;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IIngredientService;
import com.filrouge.restaurantcore.validator.IngredientValidator;

@Service
public class IngredientServiceImpl implements IIngredientService {

	// DAOs
	private IIngredientRepository ingredientRepository;

	/**
	 * Constructor.
	 * 
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
	public Optional<IngredientDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(ingredientRepository.findById(id).map(IngredientDto::fromEntity))
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun Ingredient avec l'ID = " + id + " n' est trouve dans la BDD",
						ErrorCodes.ROLE_NOT_FOUND));
	}

	@Override
	public Optional<IngredientDto> findByName(String name) {
		Optional<Ingredient> ingredientFind = ingredientRepository.findByName(name);
		return ingredientFind.map(IngredientDto::fromEntity);
	}

	@Override
	public IngredientDto save(IngredientDto dto) {
		Optional<Ingredient> ing = ingredientRepository.findByName(dto.getName());
		boolean isIngredient = ing.isPresent();
		if (isIngredient) {
			 IngredientDto emptyIng = IngredientDto.fromEntity(new Ingredient());
			return emptyIng;
		} else {
			return IngredientDto.fromEntity(ingredientRepository.save(IngredientDto.toEntity(dto)));
		}
		
	}

	@Override
	public IngredientDto update(IngredientDto ingredientDto) {
		List<String> errors = IngredientValidator.validate(ingredientDto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le ROLE n'est pas valide", ErrorCodes.ROLE_NOT_VALID, errors);
		}

		Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientDto.getId());

		if (!optionalIngredient.isPresent()) {
			throw new InvalidEntityException("Le ROLE n'existe pas", ErrorCodes.ROLE_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		Ingredient toUpdate = optionalIngredient.get();
		toUpdate.setName(ingredientDto.getName());

		return IngredientDto.fromEntity(ingredientRepository.save(toUpdate));
	}

	@Override
	public void deleteIngredientById(String id) {
		if (id == null) {
			return;
		}
		ingredientRepository.deleteById(id);
	}

}

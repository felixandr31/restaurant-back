package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IRestaurantRepository;
import com.filrouge.restaurantcore.dto.AddressDto;
import com.filrouge.restaurantcore.dto.RestaurantDto;
import com.filrouge.restaurantcore.entity.Restaurant;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IRestaurantService;
import com.filrouge.restaurantcore.validator.RestaurantValidator;

/**
 * Services métier de gestion des rôles.
 * 
 * @author sslimani
 *
 */
@Service
public class RestaurantServiceImpl implements IRestaurantService {

	// DAOs
	private IRestaurantRepository restaurantRepository;

	/**
	 * Constructeur.
	 * 
	 * @param roleRepository le DAO des rôles.
	 */
	public RestaurantServiceImpl(IRestaurantRepository restaurantRepository) {
		super();
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public RestaurantDto save(RestaurantDto dto) {
		List<String> errors = RestaurantValidator.validate(dto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("Le restaurant n'est pas valide", ErrorCodes.RESTAURANT_NOT_VALID, errors);
		}
		return RestaurantDto.fromEntity(restaurantRepository.save(RestaurantDto.toEntity(dto)));
	}

	@Override
	public Optional<RestaurantDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(restaurantRepository.findById(id).map(RestaurantDto::fromEntity))
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun Administrator avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorCodes.RESTAURANT_NOT_FOUND));
	}

	@Override
	public RestaurantDto update(RestaurantDto dto) {
		List<String> errors = RestaurantValidator.validate(dto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("Le restaurant n'est pas valide", ErrorCodes.RESTAURANT_NOT_VALID, errors);
		}

		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(dto.getId());

		if (!optionalRestaurant.isPresent()) {
			throw new InvalidEntityException("Le restaurant n'existe pas", ErrorCodes.RESTAURANT_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		Restaurant toUpdate = optionalRestaurant.get();
		toUpdate.setAddress(AddressDto.toEntity(dto.getAddress()));
		toUpdate.setName(dto.getName());
		toUpdate.setStars(dto.getStars());

		return RestaurantDto.fromEntity(restaurantRepository.save(toUpdate));
	}

	@Override
	public List<RestaurantDto> findAll() {
		return restaurantRepository.findAll().stream().map(RestaurantDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			return;
		}
		restaurantRepository.deleteById(id);
	}
}
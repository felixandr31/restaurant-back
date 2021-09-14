package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IRecipeRepository;
import com.filrouge.restaurantcore.dao.IRestaurantRepository;
import com.filrouge.restaurantcore.dao.IStockRepository;
import com.filrouge.restaurantcore.dao.ITableRepository;
import com.filrouge.restaurantcore.dao.IUserRepository;
import com.filrouge.restaurantcore.dto.AddressDto;
import com.filrouge.restaurantcore.dto.RestaurantDto;
import com.filrouge.restaurantcore.entity.Recipe;
import com.filrouge.restaurantcore.entity.Restaurant;
import com.filrouge.restaurantcore.entity.Stock;
import com.filrouge.restaurantcore.entity.Table;
import com.filrouge.restaurantcore.entity.User;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IRestaurantService;
import com.filrouge.restaurantcore.validator.RestaurantValidator;

/**
 * Services métier de gestion des restaurants.
 * 
 * @author sslimani
 *
 */
@Service
public class RestaurantServiceImpl implements IRestaurantService {
	// private static final MessagesUtil MESSAGE_UTILS =
	// MessagesUtil.getInstance("message");
	// DAOs
	private IRestaurantRepository restaurantRepository;
	private IUserRepository userRepository;
	private IStockRepository stockRepository;
	private ITableRepository tableRepository;
	private IRecipeRepository recipeRepository;

	/**
	 * Constructeur.
	 * 
	 * @param roleRepository le DAO des restaurants.
	 */
	public RestaurantServiceImpl(IRestaurantRepository restaurantRepository, IUserRepository userRepository,
			IStockRepository stockRepository, ITableRepository tableRepository, IRecipeRepository recipeRepository) {
		super();
		this.restaurantRepository = restaurantRepository;
		this.userRepository = userRepository;
		this.stockRepository = stockRepository;
		this.tableRepository = tableRepository;
		this.recipeRepository = recipeRepository;
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
						"Aucun Restaurant avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorCodes.RESTAURANT_NOT_FOUND));
	}

//    @Override
//	public List<RestaurantDto> findByUserId(String id) {
//
//	return restaurantRepository.findByUserId(id).stream().map(RestaurantDto::fromEntity)
//				.collect(Collectors.toList());
//	}

	@Override
	public RestaurantDto addUsers(String id, final Set<String> employeeIds) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

		if (!optionalRestaurant.isPresent()) {
			throw new InvalidEntityException("Le restaurant n'existe pas", ErrorCodes.RESTAURANT_NOT_FOUND);
		}
		Restaurant toUpdate = optionalRestaurant.get();

		// Recherche des entités employee existantes
		List<User> employeesToAdd = employeeIds.stream().map(employeeId -> userRepository.findById(employeeId))
				.map(Optional::get).collect(Collectors.toList());

		toUpdate.getEmployees().addAll(employeesToAdd);

		return RestaurantDto.fromEntity(restaurantRepository.save(toUpdate));
	}

	@Override
	public RestaurantDto addStocks(String id, final Set<String> stockIds) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

//		if (!optionalRestaurant.isPresent()) {
//			throw new InvalidEntityException("L'admininistrateur n'existe pas", ErrorCodes.ADMINISTRATOR_NOT_VALID);
//		}
		Restaurant toUpdate = optionalRestaurant.get();

		// Recherche des entités role existantes
		List<Stock> stocksToAdd = stockIds.stream().map(stockId -> stockRepository.findById(stockId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		toUpdate.getStocks().addAll(stocksToAdd);

		return RestaurantDto.fromEntity(restaurantRepository.save(toUpdate));
	}

	@Override
	public RestaurantDto removeUsers(String id, Set<String> userIds) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

//		if (!optionalRestaurant.isPresent()) {
//			throw new InvalidEntityException(MESSAGE_UTILS.getMessage("message.validator.restaurant.update"),
//					ErrorCodes.CLIENT_NOT_VALID);
//		}
		Restaurant toUpdate = optionalRestaurant.get();

		// Finding existing user entities
		Set<User> usersToRemove = userIds.stream().map(userId -> userRepository.findById(userId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getEmployees().removeAll(usersToRemove);

		return RestaurantDto.fromEntity(restaurantRepository.save(toUpdate));
	}

	@Override
	public RestaurantDto removeStocks(String id, Set<String> stockIds) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

//		if (!optionalRestaurant.isPresent()) {
//			throw new InvalidEntityException(MESSAGE_UTILS.getMessage("message.validator.restaurant.update"),
//					ErrorCodes.CLIENT_NOT_VALID);
//		}
		Restaurant toUpdate = optionalRestaurant.get();

		// Finding existing user entities
		Set<Stock> stocksToRemove = stockIds.stream().map(stockId -> stockRepository.findById(stockId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getStocks().removeAll(stocksToRemove);

		return RestaurantDto.fromEntity(restaurantRepository.save(toUpdate));
	}

	@Override
	public RestaurantDto addTables(String id, final Set<String> tableIds) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

//		if (!optionalRestaurant.isPresent()) {
//			throw new InvalidEntityException("L'admininistrateur n'existe pas", ErrorCodes.ADMINISTRATOR_NOT_VALID);
//		}
		Restaurant toUpdate = optionalRestaurant.get();

		// Recherche des entités role existantes
		Set<Table> tablesToAdd = tableIds.stream().map(tableId -> tableRepository.findById(tableId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getTables().addAll(tablesToAdd);

		return RestaurantDto.fromEntity(restaurantRepository.save(toUpdate));
	}

	@Override
	public RestaurantDto removeTables(String id, Set<String> tableIds) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

		Restaurant toUpdate = optionalRestaurant.get();

		// Finding existing user entities
		Set<Table> tablesToRemove = tableIds.stream().map(tableId -> tableRepository.findById(tableId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getTables().removeAll(tablesToRemove);

		return RestaurantDto.fromEntity(restaurantRepository.save(toUpdate));
	}

	@Override
	public RestaurantDto addRecipes(String id, final Set<String> recipeIds) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

//		if (!optionalRestaurant.isPresent()) {
//			throw new InvalidEntityException("L'admininistrateur n'existe pas", ErrorCodes.ADMINISTRATOR_NOT_VALID);
//		}
		Restaurant toUpdate = optionalRestaurant.get();

		// Recherche des entités role existantes
		Set<Recipe> recipesToAdd = recipeIds.stream().map(recipeId -> recipeRepository.findById(recipeId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getRecipes().addAll(recipesToAdd);

		return RestaurantDto.fromEntity(restaurantRepository.save(toUpdate));
	}

	@Override
	public RestaurantDto removeRecipes(String id, Set<String> recipeIds) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

		Restaurant toUpdate = optionalRestaurant.get();

		// Finding existing user entities
		Set<Recipe> recipesToRemove = recipeIds.stream().map(recipeId -> recipeRepository.findById(recipeId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getRecipes().removeAll(recipesToRemove);

		return RestaurantDto.fromEntity(restaurantRepository.save(toUpdate));
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

	@Override
	public List<RestaurantDto> findByName(String name) {
		List<Restaurant> restaurantfind = restaurantRepository.findByName(name);
		return restaurantfind.stream().map(RestaurantDto::fromEntity).collect(Collectors.toList());
	}

}

package com.filrouge.restaurantcore.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.filrouge.restaurantcore.entity.Purchase;
import com.filrouge.restaurantcore.entity.Recipe;
import com.filrouge.restaurantcore.entity.Restaurant;
import com.filrouge.restaurantcore.entity.Table;
import com.filrouge.restaurantcore.entity.User;

import lombok.Builder;
import lombok.Data;

/**
 * Transfert Object from restaurant (DTO).
 * 
 * @author sslimani
 *
 */
@Data
@Builder
public class RestaurantDto {
     
	/**
	 * Id
	 */
	private String id;
	
	/**
	 * Name.
	 */
	private String name;

	/**
	 * Informations of l'adresses dans un sous document.
	 */
	private AddressDto address;

	/**
	 * Stars.
	 */
	private Float stars;

	/**
	 * Coordinates.
	 */
	private CoordinatesDto coordinates;

	/**
	 * Budget
	 */
	private BigDecimal budget;

	/**
	 * Stock
	 */
	private StockDto stock;

	// @Builder.Default permet de surcharger la construction de la collection de
	// lombok
	@Builder.Default
	private List<TableDto> tables = new ArrayList<TableDto>(0);
	@Builder.Default
	private List<UserDto> employees = new ArrayList<UserDto>(0);
	@Builder.Default
	private List<PurchaseDto> purchases = new ArrayList<PurchaseDto>(0);
	@Builder.Default
	private List<RecipeDto> recipes = new ArrayList<RecipeDto>(0);

	/**
	 * Transform entity into DTO.
	 * 
	 * @param entity l'entité
	 * @return le DTO
	 */
	public static RestaurantDto fromEntity(Restaurant entity) {
		if (entity == null) {
			return null;
		}

		final List<TableDto> tablesDTO = new ArrayList<TableDto>(entity.getTables().size());
		for (final Table table : entity.getTables()) {
			tablesDTO.add(TableDto.fromEntity(table));
		}

		final List<UserDto> employeesDTO = new ArrayList<UserDto>(entity.getEmployees().size());
		for (final User employee : entity.getEmployees()) {
			employeesDTO.add(UserDto.fromEntity(employee));
		}

		final List<PurchaseDto> purchasesDTO = new ArrayList<PurchaseDto>(entity.getPurchases().size());
		for (final Purchase purchase : entity.getPurchases()) {
			purchasesDTO.add(PurchaseDto.fromEntity(purchase));
		}

		final List<RecipeDto> recipesDTO = new ArrayList<RecipeDto>(entity.getRecipes().size());
		for (final Recipe recipe : entity.getRecipes()) {
			recipesDTO.add(RecipeDto.fromEntity(recipe));
		}

		return RestaurantDto.builder().name(entity.getName()).address(AddressDto.fromEntity(entity.getAddress()))
				.stars(entity.getStars()).budget(entity.getBudget())
				.coordinates(CoordinatesDto.fromEntity(entity.getCoordinates()))
				.stock(StockDto.fromEntity(entity.getStock())).tables(tablesDTO).employees(employeesDTO)
				.purchases(purchasesDTO).recipes(recipesDTO).build();
	}

	/**
	 * Transform l DTO into entity.
	 * 
	 * @param dto le DTO
	 * @return l'entité
	 */
	public static Restaurant toEntity(RestaurantDto dto) {
		if (dto == null) {
			return null;
		}

		final Restaurant restaurant = new Restaurant();
		restaurant.setName(dto.getName());
		restaurant.setAddress(AddressDto.toEntity(dto.getAddress()));
		restaurant.setStars(dto.getStars());
		restaurant.setCoordinates(CoordinatesDto.toEntity(dto.getCoordinates()));
		restaurant.setStock(StockDto.toEntity(dto.getStock()));
		restaurant.setBudget(dto.getBudget());

		final List<User> employees = dto.getEmployees().stream().map(UserDto::toEntity).collect(Collectors.toList());
		restaurant.setEmployees(employees);

		final List<Table> tables = dto.getTables().stream().map(TableDto::toEntity).collect(Collectors.toList());
		restaurant.setTables(tables);

		final List<Purchase> purchases = dto.getPurchases().stream().map(PurchaseDto::toEntity)
				.collect(Collectors.toList());
		restaurant.setPurchases(purchases);

		final List<Recipe> recipes = dto.getRecipes().stream().map(RecipeDto::toEntity).collect(Collectors.toList());
		restaurant.setRecipes(recipes);

		return restaurant;
	}
}

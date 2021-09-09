package com.filrouge.restaurantcore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filrouge.restaurantcore.entity.Restaurant;

public interface IRestaurantRepository extends MongoRepository<Restaurant, String> {

	List<Restaurant> findByName(String name);

	Optional<Restaurant> findById(String id);

	//List<Restaurant> findByUserId(String id);
}

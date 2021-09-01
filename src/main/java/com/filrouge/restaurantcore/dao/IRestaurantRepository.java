package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filrouge.restaurantcore.entity.Restaurant;



public interface IRestaurantRepository extends MongoRepository<Restaurant, Long> {

}

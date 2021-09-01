package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.filrouge.restaurantcore.entity.Coordinates;

public interface ICoordinatesRepository extends MongoRepository<Coordinates, String> {

}

package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filrouge.restaurantcore.entity.Stock;


/**
 * Personalized DAO services for Stock.
 * @author sslimani
 *
 */
public interface IStockRepository extends MongoRepository<Stock, String>  {

}

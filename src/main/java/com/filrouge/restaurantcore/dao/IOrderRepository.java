package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filrouge.restaurantcore.entity.Order;

/**
 * Personalized DAO services for Order.
 * @author Hermann
 *
 */

public interface IOrderRepository extends MongoRepository<Order, String>{
	//The key is of type String for an identifier using the @MongoId annotation (FieldType.OBJECT_ID)
    //The key is automatically generated by MongoDB

}

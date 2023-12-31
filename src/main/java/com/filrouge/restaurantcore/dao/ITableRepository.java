package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filrouge.restaurantcore.entity.Table;

/**
 * /**
 * Personalized DAO services for Table.
 * @author Hermann
 *
 */

public interface ITableRepository extends MongoRepository<Table, String> {
	//The key is of type String for an identifier using the @MongoId annotation (FieldType.OBJECT_ID)
    //The key is automatically generated by MongoDB

}

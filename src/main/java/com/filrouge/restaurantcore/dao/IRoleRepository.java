package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.filrouge.restaurantcore.entity.Role;

/**
 * Personalized DAO services for Role.
 * @author Hermann
 *
 */

public interface IRoleRepository extends MongoRepository<Role, String>{
	//The key is of type String for an identifier using the @MongoId annotation (FieldType.OBJECT_ID)
    //The key is automatically generated by MongoDB
	

}

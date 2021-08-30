package com.filrouge.restaurantcore.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Document(collection = "user")
public class User extends AbstractEntity {
	
	/**
	 * first name of user
	 */
	@Field("firstname")
	private String firstName;
	
	/**
	 * last name of user
	 */
	@Field("laststname")
	private String lastName;
	
	/**
	 * email of user
	 */
	@Field("email")
	private String email;
	
	/**
	 * roles associated with the user
	 */
	
	@DBRef
	private List<Role>Roles;
	

}

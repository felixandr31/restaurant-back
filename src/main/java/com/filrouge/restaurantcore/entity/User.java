package com.filrouge.restaurantcore.entity;

import java.util.ArrayList;

import java.util.List;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity User
 * 
 * @author formation
 *
 */

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "user")
public class User extends AbstractEntity {

	/**
	 * first name of user
	 */
	@Field("firstname")
	@NonNull
	private String firstName;

	/**
	 * last name of user
	 */
	@Field("lastname")
	@NonNull
	private String lastName;

	/**
	 * password of user
	 */
	@Field("password")
	@NonNull
	private String password;

	/**
	 * email of user
	 */
	@Field("email")
	@NonNull
	private String email;

	/**
	 * roles associated with the user
	 */

	@DBRef
	private List<Role> roles;
	
	/**
	 * Restaurant de l'utilisateur
	 */
	//@DBRef
	//private Restaurant restaurant;

	/**
	 * Friends associated at User
	 */

	@DBRef
	private List<User> friends;

	/**
	 * constructor NoArgs
	 */

	public User() {
		this.roles = new ArrayList<Role>(0);
		this.friends = new ArrayList<User>(0);
	}

}

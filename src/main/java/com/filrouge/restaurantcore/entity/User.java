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
	 * 
	 */
	@Field("restaurantId")
	private String restaurantId;

	/**
	 * email of user
	 */
	@Field("email")
	@NonNull
	private String email;

	@DBRef
	private List<Restaurant> restaurants;

	/**
	 * roles associated with the user
	 */

	@DBRef
	private List<Role> roles;

	/**
	 * Restaurant de l'utilisateur
	 */
	

	/**
	 * Friends associated at User
	 */

	@DBRef
	private List<User> friends;
	
	private List<String> bookings;

	/**
	 * constructor NoArgs
	 */

	public User() {
		this.roles = new ArrayList<Role>(0);
		this.friends = new ArrayList<User>(0);
		this.restaurants = new ArrayList<Restaurant>(0);
		this.bookings = new ArrayList<String>(0);}

}

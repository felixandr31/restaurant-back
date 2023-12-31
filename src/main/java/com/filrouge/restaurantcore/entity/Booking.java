package com.filrouge.restaurantcore.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Booking Entity
 * 
 * @author sslimani
 *
 */

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "booking")
@Data
public class Booking extends AbstractEntity {

	/**
	 * Day.
	 */
	@Field("day")
	private Date day;

	/**
	 * Hour.
	 */
	@Field("hour")
	private String hour;

	@DBRef
	private List<User> clients;

	/**
	 * Informations of tables.
	 */
	@DBRef
	private Table table;

	/**
	 * Informations of orders.
	 */
	@DBRef
	private List<Order> orders;
	
	/**
	 * Check status for order
	 */
	@Field("isOrdered")
	private boolean isOrdered;
	
	/**
	 * Check served status
	 */
	@Field("isServed")
	private boolean isServed;
	
	/**
	 * Check paid status
	 */
	@Field("isPayed")
	private boolean isPayed;
	

	public Booking() {
		this.clients = new ArrayList<User>(0);
		this.orders = new ArrayList<Order>(0);
	}
}

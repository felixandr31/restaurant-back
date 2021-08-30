package com.filrouge.restaurantcore.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Document(collection = "AbstractEntity")
public abstract class AbstractEntity {

	/**
	 * ID
	 */
	@MongoId(FieldType.OBJECT_ID)
	private String id;

	/**
	 * creation date
	 */
	@CreatedDate
	@Field("createdDate")
	@JsonIgnore
	private Instant createDate;

	/**
	 * the last update date
	 */
	@LastModifiedDate
	@Field("LastModifiedDate")
	@JsonIgnore
	private Instant LastModifiedDate;

}


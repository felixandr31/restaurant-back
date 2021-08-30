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

/**
 * 
 * @author Safia
 *
 */
@Data
@Document(collection = "AbstractEntity")
public class AbstractEntity {
	/**
	 * L'identifiant.
	 */
	@MongoId(FieldType.OBJECT_ID)
	private String id;

	/**
	 * La date de création.
	 */
	@CreatedDate
	@Field("createdDate")
	@JsonIgnore
	private Instant createDate;

	/**
	 * La dernière date de mise à jour.
	 */
	@LastModifiedDate
	@Field("LastModifiedDate")
	@JsonIgnore
	private Instant LastModifiedDate;
}
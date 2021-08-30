package com.filrouge.restaurantcore.entity;

import org.springframework.data.mongodb.core.index.Indexed;
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
@Document(collection = "Role")
public class Role extends AbstractEntity{
	
	/**
	 * associated roles
	 */
	@Field("rolename")
	@Indexed(unique = true)
	private String RoleName;

}

package com.filrouge.restaurantcore.dto;


import com.filrouge.restaurantcore.entity.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableDto {
	
	 private String id;
	 
	 private String name;
	 
	 private Integer capacity;
	 
	 /**
		 * Transform the entity into a DTO.
		 * 
		 * @param entity 
		 * @return DTO of entity
		 */
		public static TableDto fromEntity(Table entity) {
			if(entity == null) {
				//TODO throw an exception
				return null;
			}
			return TableDto.builder().id(entity.getId()).name(entity.getName()).capacity(entity.getCapacity()).build();
					
		}
		
		
		/**
		 * Transform the DTO into entity.
		 * 
		 * @param dto 
		 * @return entity of DTO
		 */
		
		public static Table fromEntity(TableDto dto) {
			if(dto == null) {
				//TODO throw an exception
				return null;
				
			}
			final Table table = new Table();
			
			table.setId(dto.getId());
			table.setCapacity(dto.getCapacity());
			table.setName(dto.getName());
			return table;
		}

		
		
			

}

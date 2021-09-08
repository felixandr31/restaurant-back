package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;

import com.filrouge.restaurantcore.dto.AddressDto;



public interface IAddressService {
	
	/**
	 * Research of all Addresss.
	 * 
	 * @return the DTOs of the Address.
	 */
	List<AddressDto> findAll();
	
	/**
	 * 
	 * Search an Address by ID
	 * 
	 * @param ID identifier
	 * @return the DTO found according to its identifier.
	 */
	Optional<AddressDto> findById(String id);
	
	
	/**
	 * Création of a Address.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the Address
	 */
	AddressDto save(AddressDto dto);
	
	
	/**
	 * Removal of an Address. 
	 * @param id The Address identifier.
     * @return the Address's DTO.
	 * 
	 */
	void deleteAddressById(String id);
	
	/**
	 * Mise à jour du Address
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	AddressDto update(AddressDto addressDto);
	

}

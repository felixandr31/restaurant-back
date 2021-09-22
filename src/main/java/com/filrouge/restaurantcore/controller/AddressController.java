package com.filrouge.restaurantcore.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.restaurantcore.dto.AddressDto;
import com.filrouge.restaurantcore.service.IAddressService;

/**
 * Services REST de gestion des addresss.
 * 
 * @author FAndre
 *
 */
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200", "http://192.168.20.109:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/address/*")
public class AddressController {
	
	@Autowired
	IAddressService addressService;
	
	
	/**
	 * Search all Addresss.
	 * 
	 * @return Addresss of the DTO.
	 */
	@GetMapping(value = "/addresses")
	public ResponseEntity<Collection<AddressDto>> findAll() {
		Collection<AddressDto> addresss = addressService.findAll();
		return new ResponseEntity<Collection<AddressDto>>(addresss, HttpStatus.OK);
	}
	
	@GetMapping(value = "/addresses/{id}")
	public ResponseEntity<Optional<AddressDto>> findById(@PathVariable String id) {
		Optional<AddressDto> addresss = addressService.findById(id);
		return new ResponseEntity<Optional<AddressDto>>(addresss, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<AddressDto> save(@RequestBody AddressDto addressDto) {
		AddressDto addressCreated = addressService.save(addressDto);
		return new ResponseEntity<AddressDto>(addressCreated, HttpStatus.CREATED);
	}
	
	 @DeleteMapping("/delete/{id}")
	 public void deleteAddressById(@PathVariable("id") String id){
	        this.addressService.deleteAddressById(id);
	    }
	 
	 /**
		 * Mise à jour d'un administrateur (sans ces associations)
		 * @param id l'identifiant du User
		 * @param userDto les données à mettre à jour
		 * @return l'administrateur mis à jour
		 */
		@PutMapping("/update/{id}")
		@Transactional
		public ResponseEntity<AddressDto> update(@PathVariable String id,
				@RequestBody AddressDto addressDto) {

			addressDto.setId(id);
			AddressDto addressUpdate = addressService.update(addressDto);
			return new ResponseEntity<AddressDto>(addressUpdate, HttpStatus.CREATED);
		}
	 

}

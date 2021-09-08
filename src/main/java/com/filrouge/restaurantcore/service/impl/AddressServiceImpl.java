package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IAddressRepository;
import com.filrouge.restaurantcore.dto.AddressDto;
import com.filrouge.restaurantcore.entity.Address;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IAddressService;
import com.filrouge.restaurantcore.validator.AddressValidator;

@Service
public class AddressServiceImpl implements IAddressService{

	// DAOs
	private IAddressRepository addressRepository;

	/**
	 * Constructor.
	 * 
	 * @param addressRepository the roles DTO.
	 */
	public AddressServiceImpl(IAddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}
	
	@Override
	public List<AddressDto> findAll() {
		return addressRepository.findAll().stream().map(AddressDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public Optional<AddressDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(addressRepository.findById(id).map(AddressDto::fromEntity))
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun Address avec l'ID = " + id + " n' est trouve dans la BDD",
						ErrorCodes.ROLE_NOT_FOUND));
	}

	@Override
	public AddressDto save(AddressDto dto) {
		return AddressDto.fromEntity(addressRepository.save(AddressDto.toEntity(dto)));
	}

	@Override
	public AddressDto update(AddressDto addressDto) {
		List<String> errors = AddressValidator.validate(addressDto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le ROLE n'est pas valide", ErrorCodes.ROLE_NOT_VALID, errors);
		}

		Optional<Address> optionalAddress = addressRepository.findById(addressDto.getId());

		if (!optionalAddress.isPresent()) {
			throw new InvalidEntityException("Le ROLE n'existe pas", ErrorCodes.ROLE_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		Address toUpdate = optionalAddress.get();
		toUpdate.setStreetName(addressDto.getStreetName());
		toUpdate.setCity(addressDto.getCity());
		toUpdate.setZipCode(addressDto.getZipCode());
		toUpdate.setCountry(addressDto.getCountry());

		return AddressDto.fromEntity(addressRepository.save(toUpdate));
	}

	@Override
	public void deleteAddressById(String id) {
		if (id == null) {
			return;
		}
		addressRepository.deleteById(id);
	}

}

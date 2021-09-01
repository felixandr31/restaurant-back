package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IRoleRepository;
import com.filrouge.restaurantcore.dto.RoleDto;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;

import com.filrouge.restaurantcore.service.IRoleService;


@Service
public class RoleServiceImpl implements IRoleService {

	// DAOs
	private IRoleRepository roleRepository;

	/**
	 * Constructor.
	 * @param roleRepository the roles DTO.
	 */
	public RoleServiceImpl(IRoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	@Override
	public RoleDto save(RoleDto dto) {
		
		return RoleDto.fromEntity(roleRepository.save(RoleDto.toEntity(dto)));
	}

	@Override
	public Optional<RoleDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(roleRepository.findById(id).map(RoleDto::fromEntity)).orElseThrow(() -> new EntityNotFoundException(
				"Aucun Role avec l'ID = " + id + " n' est trouve dans la BDD", ErrorCodes.ROLE_NOT_FOUND));
	}

	@Override
	public List<RoleDto> findAll() {
		return roleRepository.findAll().stream().map(RoleDto::fromEntity).collect(Collectors.toList());

	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			return;
		}
		roleRepository.deleteById(id);
	}
}

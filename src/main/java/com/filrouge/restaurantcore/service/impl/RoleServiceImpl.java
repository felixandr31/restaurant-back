package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IRoleRepository;
import com.filrouge.restaurantcore.dto.RoleDto;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.entity.Role;
import com.filrouge.restaurantcore.entity.User;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IRoleService;
import com.filrouge.restaurantcore.validator.RoleValidator;




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
	public void deleteRoleById(String id) {
		if (id == null) {
			return;
		}
		roleRepository.deleteById(id);
	}

	@Override
	public List<RoleDto> findByName(String name) {
		List<Role> roleFind = roleRepository.findByName(name);
		return roleFind.stream().map(RoleDto::fromEntity).collect(Collectors.toList());
	}
	
	@Override
	public RoleDto update(final RoleDto dto) {
		List<String> errors = RoleValidator.validate(dto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le ROLE n'est pas valide", ErrorCodes.ROLE_NOT_VALID,
					errors);
		}

		Optional<Role> optionalRole = roleRepository.findById(dto.getId());

		if (!optionalRole.isPresent()) {
			throw new InvalidEntityException("Le ROLE n'existe pas", ErrorCodes.ROLE_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		Role toUpdate = optionalRole.get();
		toUpdate.setName(dto.getName());

		return RoleDto.fromEntity(roleRepository.save(toUpdate));
	}
}

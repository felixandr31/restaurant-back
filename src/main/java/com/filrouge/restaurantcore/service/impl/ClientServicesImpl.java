package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IRoleRepository;
import com.filrouge.restaurantcore.dao.IUserRepository;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.entity.Role;
import com.filrouge.restaurantcore.entity.User;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IClientService;
import com.filrouge.restaurantcore.util.MessagesUtil;
import com.filrouge.restaurantcore.validator.UserValidator;

@Service
public class ClientServicesImpl implements IClientService {

	private static final MessagesUtil MESSAGE_UTILS = MessagesUtil.getInstance("message");

	// DAOs

	private IUserRepository userRepository;
	private IRoleRepository roleRepository;

	/**
	 * Constructor
	 * 
	 * @param userepository The DTO of user
	 */

	public ClientServicesImpl(IUserRepository userRepository, IRoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDto save(final UserDto dto) {

		UserDto userDto = UserDto.fromEntity(userRepository.save(UserDto.toEntity(dto)));
		if (dto.getRoles().size() > 0) {

			Set<String> roleIds = dto.getRoles().stream().map(role -> role.getId()).collect(Collectors.toSet());

			userDto = addRoles(dto.getId(), roleIds);
		}
		return userDto;

	}

	@Override
	public UserDto update(UserDto dto) {
		List<String> errors = UserValidator.validate(dto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException(MESSAGE_UTILS.getMessage("message.validator.client"),
					ErrorCodes.CLIENT_NOT_FOUND, errors);
		}
		return UserDto.fromEntity(userRepository.save(UserDto.toEntity(dto)));
	}

	@Override
	public Optional<UserDto> findById(String id) {

		if (id == null) {
			return null;
		}
		return Optional.of(userRepository.findById(id).map(UserDto::fromEntity)).orElseThrow(
				() -> new EntityNotFoundException("Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorCodes.CLIENT_NOT_FOUND));
	}

	@Override
	public List<UserDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream().map(UserDto::fromEntity).collect(Collectors.toList());

	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			return;
		}
		userRepository.deleteById(id);

	}

	@Override
	public UserDto addRoles(String id, final Set<String> roleIds) {
		Optional<User> optionalClient = userRepository.findById(id);

		if (!optionalClient.isPresent()) {
			throw new InvalidEntityException(MESSAGE_UTILS.getMessage("message.validator.client.update"),
					ErrorCodes.CLIENT_NOT_VALID);
		}
		User toUpdateClient = optionalClient.get();

		// Finding existing role entities
		List<Role> rolesToAdd = roleIds.stream().map(roleId -> roleRepository.findById(roleId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		toUpdateClient.getRoles().addAll(rolesToAdd);

		return UserDto.fromEntity(userRepository.save(toUpdateClient));
	}

	@Override
	public UserDto removeRoles(String id, Set<String> roleIds) {
		Optional<User> optionalClient = userRepository.findById(id);

		if (!optionalClient.isPresent()) {
			throw new InvalidEntityException(MESSAGE_UTILS.getMessage("message.validator.client.update"),
					ErrorCodes.CLIENT_NOT_VALID);
		}
		User toUpdate = optionalClient.get();

		// Finding existing role entities
		List<Role> rolesToRemove = roleIds.stream().map(roleId -> roleRepository.findById(roleId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		toUpdate.getRoles().removeAll(rolesToRemove);

		return UserDto.fromEntity(userRepository.save(toUpdate));
	}

}

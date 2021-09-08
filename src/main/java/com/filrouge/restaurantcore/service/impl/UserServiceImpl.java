package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IRoleRepository;
import com.filrouge.restaurantcore.dao.IUserRepository;
import com.filrouge.restaurantcore.dto.RestaurantDto;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.entity.Role;
import com.filrouge.restaurantcore.entity.User;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IUserService;
import com.filrouge.restaurantcore.util.MessagesUtil;
import com.filrouge.restaurantcore.validator.UserValidator;

@Service
public class UserServiceImpl implements IUserService {

	private static final MessagesUtil MESSAGE_UTILS = MessagesUtil.getInstance("message");

	// DAOs

	private IUserRepository userRepository;
	private IRoleRepository roleRepository;

	/**
	 * Constructor
	 * 
	 * @param userepository The DTO of user
	 */

	public UserServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDto save(final UserDto dto) {

		// TODO initialiser un User avec un role client par defaut
		// findByName name = client

		return UserDto.fromEntity(userRepository.save(UserDto.toEntity(dto)));

	}

	@Override
	public Optional<UserDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(userRepository.findById(id).map(UserDto::fromEntity))
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun User avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorCodes.USER_NOT_FOUND));
	}
	@Override
	public List<UserDto> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream().map(UserDto::fromEntity).collect(Collectors.toList());

	}

	@Override
	public void deleteUserById(String id) {
		if (id == null) {
			return;
		}
		userRepository.deleteById(id);

	}

	@Override
	public UserDto addRoles(String id, final Set<String> roleIds) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new InvalidEntityException(MESSAGE_UTILS.getMessage("message.validator.client.update"),
					ErrorCodes.CLIENT_NOT_VALID);
		}
		User toUpdateUser = optionalUser.get();

		// Finding existing role entities
		List<Role> rolesToAdd = roleIds.stream().map(roleId -> roleRepository.findById(roleId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		toUpdateUser.getRoles().addAll(rolesToAdd);

		return UserDto.fromEntity(userRepository.save(toUpdateUser));
	}

	@Override
	public UserDto removeRoles(String id, Set<String> roleIds) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new InvalidEntityException(MESSAGE_UTILS.getMessage("message.validator.client.update"),
					ErrorCodes.CLIENT_NOT_VALID);
		}
		User toUpdate = optionalUser.get();

		// Finding existing role entities
		List<Role> rolesToRemove = roleIds.stream().map(roleId -> roleRepository.findById(roleId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		toUpdate.getRoles().removeAll(rolesToRemove);

		return UserDto.fromEntity(userRepository.save(toUpdate));
	}

	@Override
	public List<UserDto> findAll() {
		return userRepository.findAll().stream().map(UserDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public UserDto addFriends(String id, final Set<String> friendIds) {
		Optional<User> optionalFriend = userRepository.findById(id);

		if (!optionalFriend.isPresent()) {
			throw new InvalidEntityException(MESSAGE_UTILS.getMessage("message.validator.client.update"),
					ErrorCodes.CLIENT_NOT_VALID);
		}

		User toUpdateFriend = optionalFriend.get();

		// Finding existing role entities
		List<User> friendsToAdd = friendIds.stream().map(friendId -> userRepository.findById(friendId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		toUpdateFriend.getFriends().addAll(friendsToAdd);

		return UserDto.fromEntity(userRepository.save(toUpdateFriend));
	}

	@Override
	public UserDto removeFriends(String id, Set<String> friendIds) {
		Optional<User> optionalFriend = userRepository.findById(id);

		if (!optionalFriend.isPresent()) {
			throw new InvalidEntityException(MESSAGE_UTILS.getMessage("message.validator.client.update"),
					ErrorCodes.CLIENT_NOT_VALID);
		}
		User toUpdate = optionalFriend.get();

		// Finding existing role entities
		List<User> friendsToRemove = friendIds.stream().map(friendId -> userRepository.findById(friendId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		toUpdate.getFriends().removeAll(friendsToRemove);

		return UserDto.fromEntity(userRepository.save(toUpdate));
	}

	@Override
	public UserDto update(final UserDto dto) {
		List<String> errors = UserValidator.validate(dto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le User n'est pas valide", ErrorCodes.USER_NOT_VALID, errors);
		}

		Optional<User> optionalUser = userRepository.findById(dto.getId());

		if (!optionalUser.isPresent()) {
			throw new InvalidEntityException("Le User n'existe pas", ErrorCodes.USER_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		User toUpdate = optionalUser.get();
		toUpdate.setFirstName(dto.getFirstName());
		toUpdate.setLastName(dto.getLastName());
		toUpdate.setEmail(dto.getEmail());

		return UserDto.fromEntity(userRepository.save(toUpdate));
	}

	@Override
	public List<UserDto> findByEmail(String email) {
		List<User> userFind = userRepository.findByEmail(email);
		return userFind.stream().map(UserDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<UserDto> findByLastNameAndPassword(String lastName, String password) {
		List<User> userFind = userRepository.findByLastNameAndPassword(lastName, password);
		return userFind.stream().map(UserDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<UserDto> findByFirstNameAndLastName(String firstName, String lastName) {
		List<User> userFind = userRepository.findByFirstNameAndLastName(firstName, lastName);
		return userFind.stream().map(UserDto::fromEntity).collect(Collectors.toList());
	}
	
	



}

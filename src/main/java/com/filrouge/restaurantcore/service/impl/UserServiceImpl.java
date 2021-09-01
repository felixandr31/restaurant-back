package com.filrouge.restaurantcore.service.impl;



import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IUserRepository;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.service.IUserService;


/**
 * User management services
 * @author Hermann
 *
 */

@Service
public class UserServiceImpl implements IUserService {
	
	private IUserRepository userRepository;
	
	/**
	 * Constructeur
	 * @param userepository The DTO of user
	 */

	public UserServiceImpl(IUserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDto save(final UserDto dto) {

		return UserDto.fromEntity(userRepository.save(UserDto.toEntity(dto)));
	}

}

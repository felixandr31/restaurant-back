package com.filrouge.restaurantcore.service.auth;


/**

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.entity.auth.ExtendedUser;
import com.filrouge.restaurantcore.service.IUserService;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

  @Autowired
  private IUserService service;


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDto user = service.findByEmail(email);

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

    return new ExtendedUser(user.getEmail(), user.getPassword(), user.getRestaurant().getId(), authorities);
  }
}
*/

package com.filrouge.restaurantcore.dto.auth;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

  private String accessToken;

}

package com.filrouge.restaurantcore.dto;




import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangerPasswordDto {

  private String id;

  private String password;

  private String confirmPassword;

}

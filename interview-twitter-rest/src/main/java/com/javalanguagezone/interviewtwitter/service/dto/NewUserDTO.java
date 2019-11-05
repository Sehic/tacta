package com.javalanguagezone.interviewtwitter.service.dto;

import com.javalanguagezone.interviewtwitter.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO {

  private String username;
  private String firstName;
  private String lastName;
  private String password;

  public NewUserDTO(User user) {
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.username = user.getUsername();
  }

}

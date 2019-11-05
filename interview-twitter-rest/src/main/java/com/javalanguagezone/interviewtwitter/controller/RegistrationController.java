package com.javalanguagezone.interviewtwitter.controller;

import com.javalanguagezone.interviewtwitter.service.UserService;
import com.javalanguagezone.interviewtwitter.service.dto.NewUserDTO;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("public")
@RestController
public class RegistrationController {

  private UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  @ResponseStatus(CREATED)
  public NewUserDTO registerUser(@RequestBody NewUserDTO newUserDTO) {
    return userService.registerUser(newUserDTO);
  }

  @GetMapping("/check/exist/{username}")
  public Boolean checkUsernameExist(@PathVariable(value = "username") String username) {
    return userService.checkExistByUsername(username);
  }
}

package com.javalanguagezone.interviewtwitter.controller;

import com.javalanguagezone.interviewtwitter.domain.User;
import com.javalanguagezone.interviewtwitter.repository.UserRepository;
import com.javalanguagezone.interviewtwitter.service.dto.NewUserDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class RegistrationControllerIntegrationTest extends RestIntegrationTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void createNewUser_userIsSaved() {
    NewUserDTO newUserDTO = new NewUserDTO("testUsername", "testFirstName", "testLastName", "testPassword");
    ResponseEntity<NewUserDTO> response = doCreateNewUserRequest(newUserDTO);
    assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    NewUserDTO userDTO = response.getBody();
    User fromDb = userRepository.findOneByUsername(userDTO.getUsername());
    assertThat(fromDb, notNullValue());
    assertThat(fromDb.getFirstName(), equalTo(userDTO.getFirstName()));
    assertThat(fromDb.getLastName(), equalTo(userDTO.getLastName()));
  }

  @Test
  public void checkUserExist_userExist() {
    ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/public/check/exist/rogerkver", Boolean.class);
    Boolean tmp = response.getBody();
    assertThat(tmp, is(true));
  }

  @Test
  public void checkUserExist_userNotExist() {
    ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/public/check/exist/test", Boolean.class);
    Boolean tmp = response.getBody();
    assertThat(tmp, is(false));
  }

  private ResponseEntity<NewUserDTO> doCreateNewUserRequest(NewUserDTO newUserDTO) {
    return testRestTemplate.postForEntity("/public/register", newUserDTO, NewUserDTO.class);
  }
}

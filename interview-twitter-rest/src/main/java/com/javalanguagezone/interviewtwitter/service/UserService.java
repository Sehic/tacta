package com.javalanguagezone.interviewtwitter.service;

import com.javalanguagezone.interviewtwitter.domain.User;
import com.javalanguagezone.interviewtwitter.repository.TweetRepository;
import com.javalanguagezone.interviewtwitter.repository.UserRepository;
import com.javalanguagezone.interviewtwitter.service.dto.NewUserDTO;
import com.javalanguagezone.interviewtwitter.service.dto.ProfileDto;
import com.javalanguagezone.interviewtwitter.service.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class UserService implements UserDetailsService {

  private UserRepository userRepository;
  private TweetRepository tweetRepository;

  public UserService(UserRepository userRepository, TweetRepository tweetRepository) {
    this.userRepository = userRepository;
    this.tweetRepository = tweetRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getUser(username);
    if(user == null)
      throw new UsernameNotFoundException(username);
    return user;
  }

  @Transactional
  public Collection<UserDTO> getUsersFollowing(Principal principal) {
    User user = getUser(principal.getName());
    return convertUsersToDTOs(user.getFollowing());
  }

  @Transactional
  public Collection<UserDTO> getUsersFollowers(Principal principal) {
    User user = getUser(principal.getName());
    return convertUsersToDTOs(user.getFollowers());
  }

  public ProfileDto getUserProfile(Principal principal) {
    User user = getUser(principal.getName());
    return new ProfileDto(user.getUsername(), user.getFullName(), tweetRepository.findAllByAuthor(user).size(), user.getFollowers().size(), user.getFollowing().size());
  }

  public NewUserDTO registerUser(NewUserDTO newUserDTO) {
    User user = new User(newUserDTO.getUsername(), newUserDTO.getPassword(), newUserDTO.getFirstName(), newUserDTO.getLastName());
    if(!user.isValid() || userRepository.existsByUsername(newUserDTO.getUsername()))
      throw new InvalidUserException(newUserDTO.getUsername());
    User saved = userRepository.save(user);
    return new NewUserDTO(saved);
  }

  public Boolean checkExistByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  private User getUser(String username) {
    return userRepository.findOneByUsername(username);
  }

  private List<UserDTO> convertUsersToDTOs(Set<User> users) {
    return users.stream().map(UserDTO::new).collect(toList());
  }

  public static class InvalidUserException extends RuntimeException {

    private InvalidUserException(String username) {
      super("'" +  username + "'");
    }
  }
}

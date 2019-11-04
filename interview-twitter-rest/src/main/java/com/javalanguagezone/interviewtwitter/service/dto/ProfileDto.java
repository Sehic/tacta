package com.javalanguagezone.interviewtwitter.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileDto {

  private Integer numberOfTweets;
  private Integer numberOfFollowers;
  private Integer numberOfFollowing;

}

package com.thejavinhos.tvchannel.service;


import com.thejavinhos.tvchannel.entity.LoginForm;
import com.thejavinhos.tvchannel.entity.User;
import com.thejavinhos.tvchannel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User saveUser(User user) {
    return userRepository.save(user);
  }
}

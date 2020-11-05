package com.thejavinhos.tvchannel.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class teste {

  public static void main(String[] args) {
    String password = "123";
    System.out.println(new BCryptPasswordEncoder().encode(password));
  }

}

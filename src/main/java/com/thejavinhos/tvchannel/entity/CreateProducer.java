package com.thejavinhos.tvchannel.entity;

public class CreateProducer {

  private String username;
  private String password;
  private String name;

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

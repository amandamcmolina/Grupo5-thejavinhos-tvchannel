package com.thejavinhos.tvchannel.entity;

public class ReturnProducer {

  private int id;
  private String username;
  private String name;
  
  public String getUsername() {
    return username;
  }

  public ReturnProducer(){

  }

  public ReturnProducer(int id, String username, String name) {
    this.id = id;
    this.name = name;
    this.username = username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

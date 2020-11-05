package com.thejavinhos.tvchannel.entity;

import javax.persistence.Column;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CreateActor {

  private String username;
  private String password;
  private String gender;
  private double payment;
  private String genreWork;


  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public double getPayment() {
    return payment;
  }

  public void setPayment(double payment) {
    this.payment = payment;
  }

  public String getGenreWork() {
    return genreWork;
  }

  public void setGenreWork(String genreWork) {
    this.genreWork = genreWork;
  }

}

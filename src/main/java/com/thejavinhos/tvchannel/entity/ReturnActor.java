package com.thejavinhos.tvchannel.entity;

public class ReturnActor {

  private String username;
  private String gender;
  private double payment;
  private String genreWork;

  public ReturnActor(){

  }

  public ReturnActor(String username, String gender, double payment, String genreWork) {
    this.username = username;
    this.gender = gender;
    this.payment = payment;
    this.genreWork = genreWork;
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

package com.thejavinhos.tvchannel.entity;

public class ReturnActor {

  private int id;
  private String username;
  private String name;
  private String gender;
  private double payment;
  private String genreWork;
  private int qtdReserves;

  public ReturnActor() {

  }

  public ReturnActor(int id, String username, String name, String gender, double payment,
      String genreWork, int qtdReserves) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.gender = gender;
    this.payment = payment;
    this.genreWork = genreWork;
    this.qtdReserves = qtdReserves;
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

  public int getQtdReserves() {
    return qtdReserves;
  }

  public void setQtdReserves(int qtdReserves) {
    this.qtdReserves = qtdReserves;
  }
}

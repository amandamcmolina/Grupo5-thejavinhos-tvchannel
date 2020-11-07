package com.thejavinhos.tvchannel.entity;

import javax.persistence.*;

//@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Actor extends User {


  @Column(name = "gender")
  private String gender;

  @Column(name = "payment")
  private double payment;

  @Column(name = "genre_work")
  private String genreWork;


  private int qtdReserves;

  public int getQtdReserves() {
    return qtdReserves;
  }

  public void setQtdReserves(int qtdReserves) {
    this.qtdReserves = qtdReserves;
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

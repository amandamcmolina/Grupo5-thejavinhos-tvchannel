package com.thejavinhos.tvchannel.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import java.util.Date;

public class ReserveRequest {

//  private String usernameProducer;
  private String usernameActor;
  private LocalDate begin;
  private LocalDate end;

//  public String getUsernameProducer() {
//    return usernameProducer;
//  }
//
//  public void setUsernameProducer(String usernameProducer) {
//    this.usernameProducer = usernameProducer;
//  }

  public String getUsernameActor() {
    return usernameActor;
  }

  public void setUsernameActor(String usernameActor) {
    this.usernameActor = usernameActor;
  }

  public LocalDate getBegin() {
    return begin;
  }

  public void setBegin(LocalDate begin) {
    this.begin = begin;
  }

  public LocalDate getEnd() {
    return end;
  }

  public void setEnd(LocalDate end) {
    this.end = end;
  }
}

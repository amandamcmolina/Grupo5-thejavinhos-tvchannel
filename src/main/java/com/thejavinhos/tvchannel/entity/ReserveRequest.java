package com.thejavinhos.tvchannel.entity;

import java.time.LocalDate;

public class ReserveRequest {

  private String actorUsername;
  private LocalDate begin;
  private LocalDate end;

  public String getActorUsername() {
    return actorUsername;
  }

  public void setActorUsername(String actorUsername) {
    this.actorUsername = actorUsername;
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

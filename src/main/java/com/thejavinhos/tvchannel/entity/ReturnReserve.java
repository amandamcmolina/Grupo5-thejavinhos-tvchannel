package com.thejavinhos.tvchannel.entity;

import java.time.LocalDate;
import javax.persistence.Column;

public class ReturnReserve {

  private int id;
  private ReturnActor actor;
  private ReturnProducer producer;
  private LocalDate dateReserveBegin;
  private LocalDate dateReserveEnd;


  public ReturnReserve(int id, ReturnActor actor,
      ReturnProducer producer, LocalDate dateReserveBegin, LocalDate dateReserveEnd) {
    this.id = id;
    this.actor = actor;
    this.producer = producer;
    this.dateReserveBegin = dateReserveBegin;
    this.dateReserveEnd = dateReserveEnd;
  }


  public LocalDate getDateReserveBegin() {
    return dateReserveBegin;
  }

  public void setDateReserveBegin(LocalDate dateReserveBegin) {
    this.dateReserveBegin = dateReserveBegin;
  }

  public LocalDate getDateReserveEnd() {
    return dateReserveEnd;
  }

  public void setDateReserveEnd(LocalDate dateReserveEnd) {
    this.dateReserveEnd = dateReserveEnd;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ReturnActor getActor() {
    return actor;
  }

  public void setActor(ReturnActor actor) {
    this.actor = actor;
  }

  public ReturnProducer getProducer() {
    return producer;
  }

  public void setProducer(ReturnProducer producer) {
    this.producer = producer;
  }
}

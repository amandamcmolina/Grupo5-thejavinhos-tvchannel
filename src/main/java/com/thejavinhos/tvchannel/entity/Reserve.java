package com.thejavinhos.tvchannel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_actor", referencedColumnName="id")
    private Actor actor;//TODO: Verificar relacionamento

    @ManyToOne
    @JoinColumn(name="id_producer", referencedColumnName="id")
    private Producer producer;

    @Column(name = "date_reserve_begin")
    private Date dateReserveBegin;

    @Column(name = "date_reserve_end")
    private Date dateReserveEnd;

    public Actor getActor() {
        return actor;
    }

    public Producer getProducer() {
        return producer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Date getDateReserveBegin() {
        return dateReserveBegin;
    }

    public void setDateReserveBegin(Date dateReserveBegin) {
        this.dateReserveBegin = dateReserveBegin;
    }

    public Date getDateReserveEnd() {
        return dateReserveEnd;
    }

    public void setDateReserveEnd(Date dateReserveEnd) {
        this.dateReserveEnd = dateReserveEnd;
    }
}

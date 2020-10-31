package com.thejavinhos.tvchannel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_actor", referencedColumnName="id")
    private Actor id_actor;//TODO: Verificar relacionamento

    @ManyToOne
    @JoinColumn(name="id_producer", referencedColumnName="id")
    private Producer id_producer;

    @Column(name = "date_reserve_begin")
    private Date dateReserveBegin;

    @Column(name = "date_reserve_end")
    private Date dateReserveEnd;

    public Actor getId_actor() {
        return id_actor;
    }

    public Producer getId_producer() {
        return id_producer;
    }

}

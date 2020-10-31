package com.thejavinhos.tvchannel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "password")
    private int password;
    @Column(name = "username")
    private String username;

    @Column(name = "payment")
    private double payment;

    @Column(name = "available")
    private boolean available;

    @Column(name = "genre_work")
    private String genreWork;

//    @OneToMany(mappedBy = "actor")
//    private List<Reserve> reservas;



}

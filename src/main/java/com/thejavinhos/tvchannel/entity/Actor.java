package com.thejavinhos.tvchannel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

//@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getGenreWork() {
        return genreWork;
    }

    public void setGenreWork(String genreWork) {
        this.genreWork = genreWork;
    }
}

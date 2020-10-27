package com.thejavinhos.tvchannel.entity;

import javax.persistence.*;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "genre")
    private String genre;
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
    private int reservationTotal = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


//    @Override
//    public boolean login(String username, int password) {
//        return false;
//    }
}

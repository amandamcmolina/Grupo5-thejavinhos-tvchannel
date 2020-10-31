package com.thejavinhos.tvchannel.entity;

import javax.persistence.*;

//@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Actor extends User{


    @Column(name = "gender")
    private String gender;

    @Column(name = "payment")
    private double payment;

    @Column(name = "available")
    private boolean available;

    @Column(name = "genrer_work")
    private String genrerWork;

    @Column(name = "is_admin")
    private boolean isAdmin = false;




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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getGenrerWork() {
        return genrerWork;
    }

    public void setGenrerWork(String genreWork) {
        this.genrerWork = genreWork;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIs(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

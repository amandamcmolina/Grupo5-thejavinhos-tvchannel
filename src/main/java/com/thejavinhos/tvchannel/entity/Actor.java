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
public class Actor extends User{


    @Column(name = "gender")
    private String gender;

    @Column(name = "payment")
    private double payment;

    @Column(name = "available")
    private boolean available;

    @Column(name = "gender_work")
    private String genderWork;

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

    public String getGenderWork() {
        return genderWork;
    }

    public void setGenderWork(String genreWork) {
        this.genderWork = genreWork;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIs(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

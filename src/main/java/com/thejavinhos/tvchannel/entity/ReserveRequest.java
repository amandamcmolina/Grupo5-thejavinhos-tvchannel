package com.thejavinhos.tvchannel.entity;

import javax.persistence.Column;
import java.util.Date;

public class ReserveRequest {

    private String usernameProducer;
    private String usernameActor;
    private Date begin;
    private Date end;

    public String getUsernameProducer() {
        return usernameProducer;
    }

    public void setUsernameProducer(String usernameProducer) {
        this.usernameProducer = usernameProducer;
    }

    public String getUsernameActor() {
        return usernameActor;
    }

    public void setUsernameActor(String usernameActor) {
        this.usernameActor = usernameActor;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}

package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ActorService {

    @Autowired
    private  ActorRepository actorRepository;

    public Actor saveActor(Actor actor) {
            return actorRepository.save(actor);


    }

    public Actor reservationDates(Actor actor, int id) {
        var inicial = actor.getDateReserveBegin();
        var end = actor.getDateReserveEnd();
       var dates =  actor.datas(inicial,end);
        return (Actor) actorRepository.findById(id).map(a ->{ ;
            a.setReservations(dates);
             return actorRepository.save(a);

        }).orElseThrow();

    }
}

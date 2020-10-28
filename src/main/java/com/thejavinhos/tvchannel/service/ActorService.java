package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return (Actor) actorRepository.findById(id).map(a ->{
            if(a.getReservations().contains(inicial) || a.getReservations().contains(end)){
                return "Indisponivel";
            }
            a.setReservations(dates);
            return actorRepository.save(a);

        }).orElseThrow();
    }

    public List<Actor> listAllActors() {
        return (List<Actor>) actorRepository.findAll();
    }
}

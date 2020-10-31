package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    public Actor saveActor(Actor actor) {
        if (actorRepository.findByUsername(actor.getUsername()) == null) {
            return actorRepository.save(actor);
        } else {
            throw new IllegalArgumentException("Actor already exists");
        }
    }


    public List<Actor> listAllActors() {
        return (List<Actor>) actorRepository.findAll();
    }

    public Actor listActor(String username) {
        var actor = actorRepository.findByUsername(username);
        return actor;
//        List <Reserve> actorList= reserveRepository.findAllByActorId(actor.getId());
//        return actorRepository.findById(id).orElseThrow();
    }


    public List<Actor> searchActor(int quantity, String genreWork, Date begin, Double amount) {
        var reserves = reserveRepository.findAll();
        var actors = actorRepository.findAll();
        var rc = reserves.stream()
                .filter(a -> !begin.before(a.getDateReserveBegin()) && !begin.after(a.getDateReserveEnd()))
                .collect(Collectors.toList());
        var ac = new ArrayList();
        rc.forEach(reserve -> {
            ac.add(reserve.getActor());
        });
        actors.removeAll(ac);
        var ag = actors.stream().filter(actor -> actor.getGenreWork() == genreWork).collect(Collectors.toList());
        var lista = new ArrayList();
        for (var i = 0; i < quantity; i++) {
            lista.add(ag.get(i));
        }
        return lista;
    }
}

package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Actor saveActor(Actor actor) {
        if(actorRepository.findByUsername(actor.getUsername()) == null){
            return actorRepository.save(actor);
        }else{
            throw new IllegalArgumentException("Actor already exists");
        }
    }



    public List<Actor> listAllActors() {
        return (List<Actor>) actorRepository.findAll();
    }

    public Actor listActor(int id) {
        return actorRepository.findById(id).orElseThrow();
    }




}

package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActorService {

    @Autowired
    private  ActorRepository actorRepository;

    public Actor saveActor(Actor actor) {
            return actorRepository.save(actor);


    }
}

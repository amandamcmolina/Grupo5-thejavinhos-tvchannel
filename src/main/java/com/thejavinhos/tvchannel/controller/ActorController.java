package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.service.ActorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/actor")
    public ResponseEntity<Actor> createNewActor(@RequestBody Actor actor){
        return ResponseEntity.ok(actorService.saveActor(actor));
    }
}

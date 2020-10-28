package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;


    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor){
        return ResponseEntity.ok(actorService.saveActor(actor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> reservationActor(@PathVariable int id, @RequestBody Actor actor){
        return ResponseEntity.ok(actorService.reservationDates(actor , id));
    }
}

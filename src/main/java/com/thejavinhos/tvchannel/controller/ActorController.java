package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;


    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor){
        return ResponseEntity.ok(actorService.saveActor(actor));
    }

    @GetMapping
    public ResponseEntity<List<Actor>> listAll(){
        return ResponseEntity.ok(actorService.listAllActors());
    }



    @GetMapping("/{id}")
    public ResponseEntity<Actor> listNyId(@PathVariable int id){
        return ResponseEntity.ok(actorService.listActor(id));
    }


}

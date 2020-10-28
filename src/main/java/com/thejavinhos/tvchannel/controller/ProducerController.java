package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.service.ActorService;
import com.thejavinhos.tvchannel.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ActorService actorService;


    @PostMapping
    public ResponseEntity<Producer> createActor(@RequestBody Producer producer){
        return ResponseEntity.ok(producerService.saveProducer(producer));
    }

}

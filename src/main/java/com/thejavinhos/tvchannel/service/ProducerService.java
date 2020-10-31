package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {
    @Autowired
    private ProducerRepository producerRepository;

    public Producer saveProducer(Producer producer) {
        if(producerRepository.findByUsername(producer.getUsername()) == null){
            return producerRepository.save(producer);
        }else{
            throw new IllegalArgumentException("Producer already exists");
        }
    }
}

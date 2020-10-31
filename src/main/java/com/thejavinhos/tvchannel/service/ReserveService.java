package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private ActorRepository actorRepository;



    public Reserve createReserve(Reserve reserve){
        return reserveRepository.save(reserve);
    }



    public List<Reserve> ator(int id){
        var actor = actorRepository.findById(id).get();
        return reserveRepository.findAllByIdActor(actor);
    }


    public List<Reserve> listAll(){
        return (List<Reserve>) reserveRepository.findAll();
    }

    public Reserve listById(int id){
        return reserveRepository.findById(id).orElseThrow();
    }
}

package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.entity.ReserveRequest;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.ProducerRepository;
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

    @Autowired
    private ProducerRepository producerRepository;


    public Reserve createReserve(ReserveRequest reserve){
        Date begin = reserve.getBegin();
        Date end = reserve.getEnd();
        var actor = actorRepository.findByUsername(reserve.getUsernameActor());
        var producer = producerRepository.findByUsername(reserve.getUsernameProducer());
        List <Reserve> actorList= reserveRepository.findAllByActorId(actor.getId());
        for(Reserve reserva: actorList){
            Date beginAtual = reserva.getDateReserveBegin();
            Date endAtual = reserva.getDateReserveEnd();

          System.out.println(beginAtual.compareTo(begin));
          System.out.println(begin.before(beginAtual));

            if(
                 begin.before(beginAtual) && end.after(endAtual)
              || begin.after(beginAtual) && begin.before(endAtual)
              || end.after(beginAtual) && end.before(endAtual)
              || beginAtual.compareTo(begin) == 0 || beginAtual.compareTo(end) == 0
              || begin.compareTo(endAtual) == 0 || end.compareTo(beginAtual) == 0){
              throw new IllegalArgumentException("The reserve already exists");
            }
        }

        return reserveRepository.save(buildReserve(actor, producer, begin, end));
    }

    public Reserve buildReserve(Actor actor, Producer producer, Date begin, Date end){
        Reserve newReserve = new Reserve();
        newReserve.setActor(actor);
        newReserve.setProducer(producer);
        newReserve.setDateReserveBegin(begin);
        newReserve.setDateReserveEnd(end);

        return newReserve;
    }



//    public List<Reserve> ator(int id){
//        var actor = actorRepository.findById(id).get();
//        return reserveRepository.findAllByIdActor(actor);
//    }


//    public List<Reserve> listAll(){
//        return (List<Reserve>) reserveRepository.findAll();
//    }

//    public Reserve listById(int id){
//        return reserveRepository.findById(id).orElseThrow();
//    }
}
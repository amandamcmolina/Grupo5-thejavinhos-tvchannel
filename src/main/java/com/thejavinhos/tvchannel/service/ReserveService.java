package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.entity.ReserveRequest;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.ProducerRepository;
import com.thejavinhos.tvchannel.repository.ReserveRepository;
import java.time.LocalDate;
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


  public Reserve createReserve(ReserveRequest reserve) {
    if (actorRepository.findByUsername(reserve.getUsernameActor()) == null
        || producerRepository.findByUsername(reserve.getUsernameProducer()) == null) {
      throw new IllegalArgumentException("You need to pass a valid user or produce");
    }
    LocalDate begin = reserve.getBegin();
    LocalDate end = reserve.getEnd();
    var actor = actorRepository.findByUsername(reserve.getUsernameActor());
    var producer = producerRepository.findByUsername(reserve.getUsernameProducer());
    List<Reserve> actorList = reserveRepository.findAllByActorId(actor.getId());
    for (Reserve reserva : actorList) {
      LocalDate beginAtual = reserva.getDateReserveBegin();
      LocalDate endAtual = reserva.getDateReserveEnd();

      System.out.println(beginAtual.compareTo(begin));
      System.out.println(begin.isBefore(beginAtual));

      if (
          begin.isBefore(beginAtual) && end.isAfter(endAtual)
              || begin.isAfter(beginAtual) && begin.isBefore(endAtual)
              || end.isAfter(beginAtual) && end.isBefore(endAtual)
              || beginAtual.compareTo(begin) == 0 || beginAtual.compareTo(end) == 0
              || begin.compareTo(endAtual) == 0 || end.compareTo(beginAtual) == 0) {
        throw new IllegalArgumentException("The reserve already exists");
      }

      actor.setContador(actor.getContador() + 1);
    }

    return reserveRepository.save(buildReserve(actor, producer, begin, end));
  }

  public Reserve buildReserve(Actor actor, Producer producer, LocalDate begin, LocalDate end) {
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


  public List<Reserve> listAll() {
    return (List<Reserve>) reserveRepository.findAll();
  }

//    public Reserve listById(int id){
//        return reserveRepository.findById(id).orElseThrow();
//    }
}
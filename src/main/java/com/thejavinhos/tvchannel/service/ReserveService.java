package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.entity.ReserveRequest;
import com.thejavinhos.tvchannel.entity.ReturnActor;
import com.thejavinhos.tvchannel.entity.ReturnProducer;
import com.thejavinhos.tvchannel.entity.ReturnReserve;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.ProducerRepository;
import com.thejavinhos.tvchannel.repository.ReserveRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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


  public ReturnReserve createReserve(ReserveRequest reserve) {
    Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String usernameLogado;
    usernameLogado = ((UserDetails)auth).getUsername();

    if (actorRepository.findByUsername(reserve.getUsernameActor().toLowerCase()) == null
        || producerRepository.findByUsername(usernameLogado) == null
        || reserve.getBegin() == null || reserve.getEnd() == null) {
      throw new IllegalArgumentException("You need to pass a valid user or producer");
    }
    if (reserve.getBegin().isBefore(LocalDate.now()) || reserve.getEnd()
        .isBefore(reserve.getBegin())) {
      throw new IllegalArgumentException("Check the date");
    }
    LocalDate begin = reserve.getBegin();
    LocalDate end = reserve.getEnd();
    var actor = actorRepository.findByUsername(reserve.getUsernameActor());
    var producer = producerRepository.findByUsername(usernameLogado);
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

    Reserve savedReserve = buildReserve(actor, producer, begin, end);
    reserveRepository.save(savedReserve);

    ReturnActor returnActor = new ReturnActor(actor.getId(), actor.getUsername(), actor.getName(),
        actor.getGender(), actor.getPayment(), actor.getGenreWork());
    ReturnProducer returnProducer = new ReturnProducer(producer.getId(), producer.getUsername(),
        producer.getName());
    ReturnReserve returnReserve = new ReturnReserve(savedReserve.getId(), returnActor,
        returnProducer, begin, end);

    return returnReserve;
  }

  public Reserve buildReserve(Actor actor, Producer producer, LocalDate begin, LocalDate end) {

    Reserve newReserve = new Reserve();
    newReserve.setActor(actor);
    newReserve.setProducer(producer);
    newReserve.setDateReserveBegin(begin);
    newReserve.setDateReserveEnd(end);

    return newReserve;
  }

  public List<Reserve> listAll() {
    return (List<Reserve>) reserveRepository.findAll();
  }

}
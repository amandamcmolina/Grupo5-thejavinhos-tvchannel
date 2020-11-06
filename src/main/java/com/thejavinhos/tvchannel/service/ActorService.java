package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.CreateActor;
import com.thejavinhos.tvchannel.entity.Perfil;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.entity.ReturnActor;
import com.thejavinhos.tvchannel.entity.ReturnProducer;
import com.thejavinhos.tvchannel.entity.ReturnReserve;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.PerfilRepository;
import com.thejavinhos.tvchannel.repository.ReserveRepository;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ActorService {

  @Autowired
  private ActorRepository actorRepository;

  @Autowired
  private ReserveRepository reserveRepository;

  @Autowired
  private PerfilRepository perfilRepository;

  public ReturnActor saveActor(CreateActor actor) {
    if (actorRepository.findByUsername(actor.getUsername().toLowerCase()) == null) {

      List<Perfil> roles = new ArrayList<>();
      Optional<Perfil> byId = Optional
          .ofNullable(perfilRepository.findByRole("ROLE_USER")); // linha id role // UM PERFIL
      if (byId.isPresent()) {
        roles.add(byId.get());
      }

      Actor actorFinal = new Actor();
      actorFinal.setUsername(actor.getUsername().toLowerCase());
      actorFinal.setName(actor.getName());
      actorFinal.setPerfis(roles);
      actorFinal.setPayment(actor.getPayment());
      actorFinal.setGender(actor.getGender().toLowerCase());
      actorFinal.setPassword(actor.getPassword());
      actorFinal.setGenreWork(actor.getGenreWork().toLowerCase());
      actorFinal.setName(actor.getName());
      actorRepository.save(actorFinal);
      ReturnActor returnActor = new ReturnActor(actorFinal.getId(), actorFinal.getUsername(),
          actorFinal.getName(),
          actorFinal.getGender(), actorFinal.getPayment(), actorFinal.getGenreWork());
      return returnActor;
    } else {
      throw new IllegalArgumentException("Actor already exists");
    }
  }


  public List<ReturnReserve> reserveList(String username) {
    var actor = actorRepository.findByUsername(username);
    if (actor == null) {
      throw new IndexOutOfBoundsException("this username does not exist");
    }
    List<Reserve> reserveList = reserveRepository.findAllByActorId(actor.getId());
    ReturnActor returnActor = new ReturnActor(actor.getId(), actor.getUsername(), actor.getName(),
        actor.getGender(),
        actor.getPayment(), actor.getGenreWork());
    List reservesActor = new ArrayList();
    reserveList.forEach(reserve -> {
      ReturnProducer returnProducer = new ReturnProducer();
      returnProducer.setUsername(reserve.getProducer().getUsername());
      ReturnReserve eachReserve = new ReturnReserve(reserve.getId(), returnActor, returnProducer,
          reserve.getDateReserveBegin(), reserve.getDateReserveEnd());
      reservesActor.add(eachReserve);
    });
    return reservesActor;
  }


  public List<ReturnActor> searchActor(Integer quantity, String genreWork, LocalDate begin,
      Double amount) {

    var reserves = reserveRepository.findAll();
    var actors = actorRepository.findAll();

    return (List<ReturnActor>) search_filter(actors, quantity, genreWork, begin, amount);
  }


  public List<ReturnActor> searchActorFilter(Integer quantity, String genreWork, LocalDate begin,
      Double amount, String filter) {

    List<Actor> actors;

    if (filter.equals("asc")) {
      actors = actorRepository.findAllByOrderByPaymentAsc();
    } else if (filter.equals("desc")) {
      actors = actorRepository.findAllByOrderByPaymentDesc();
    } else if (filter.equals("reserves")) {
      actors = actorRepository.findAllByOrderByContadorDesc();
    } else {
      actors = actorRepository.findAll();
    }

    return (List<ReturnActor>) search_filter(actors, quantity, genreWork, begin, amount);
  }


  public List<? extends Object> search_filter(List<Actor> actors, Integer quantity,
      String genreWork, LocalDate begin, Double amount) {
    var reserves = reserveRepository.findAll();

    if (actors.isEmpty()) {
      throw new IndexOutOfBoundsException("There are not registered actors");
    }

    if (genreWork.isBlank() || amount == null || begin == null || quantity == null) {
      throw new IndexOutOfBoundsException("You must fill all blanks");
    }

    var rc = reserves.stream()
        .filter(a -> begin.isAfter(a.getDateReserveBegin()) && begin.isBefore(a.getDateReserveEnd())
            || begin.compareTo(a.getDateReserveBegin()) == 0
            || begin.compareTo(a.getDateReserveEnd()) == 0)
        .collect(Collectors.toList());
    var ac = new ArrayList();
    rc.forEach(reserve -> {
      ac.add(reserve.getActor());
    });
    actors.removeAll(ac);

    List<Actor> actorsGenre = actors.stream()
        .filter(actor -> actor.getGenreWork().toLowerCase().equals(genreWork))
        .collect(Collectors.toList());

    if (actorsGenre.isEmpty()) {
      throw new IndexOutOfBoundsException("There are not actors with this genre");
    }

    actorsGenre.removeIf(a -> a.getPayment() > amount);

    if (actorsGenre.isEmpty()) {
      throw new IndexOutOfBoundsException("There are no actors available in this amount");
    }

    if (quantity < actorsGenre.size()) {
      actorsGenre.subList(0, quantity);
    }

    List<ReturnActor> returnActors = new ArrayList<>();
    actorsGenre.forEach(actor -> {
      ReturnActor eachReturnActor = new ReturnActor();
      eachReturnActor.setName(actor.getName());
      eachReturnActor.setId(actor.getId());
      eachReturnActor.setGender(actor.getGender());
      eachReturnActor.setGenreWork(actor.getGenreWork());
      eachReturnActor.setPayment(actor.getPayment());
      eachReturnActor.setUsername(actor.getUsername());
      returnActors.add(eachReturnActor);
    });

    return returnActors;
  }
}

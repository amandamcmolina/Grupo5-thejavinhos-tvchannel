package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.CreateActor;
import com.thejavinhos.tvchannel.entity.Perfil;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.PerfilRepository;
import com.thejavinhos.tvchannel.repository.ReserveRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

    public Actor saveActor(CreateActor actor) {
        if (actorRepository.findByUsername(actor.getUsername()) == null) {
          List<Perfil> roles = new ArrayList<>();
          Optional<Perfil> byId = Optional.ofNullable(perfilRepository.findByRole("ROLE_USER")); // linha id role // UM PERFIL
          if (byId.isPresent()) {
            roles.add(byId.get());
          }
          Actor actorFinal = new Actor();
          actorFinal.setUsername(actor.getUsername());
          actorFinal.setPerfis(roles);
          actorFinal.setPayment(actor.getPayment());
          actorFinal.setGender(actor.getGender());
          actorFinal.setPassword(actor.getPassword());
            return actorRepository.save(actorFinal);
        } else {
            throw new IllegalArgumentException("Actor already exists");
        }
    }


//    public List<Actor> listAllActors() {
//        return (List<Actor>) actorRepository.findAll();
//    }

    public List<Reserve> reserveList(String username) {
        var actor = actorRepository.findByUsername(username);
        List <Reserve> actorList= reserveRepository.findAllByActorId(actor.getId());
        return actorList;
    }


  public List<Actor> searchActor(Integer quantity, String genreWork, LocalDate begin,
      Double amount) {

    var reserves = reserveRepository.findAll();
    var actors = actorRepository.findAll();

    return search_filter(actors, quantity, genreWork, begin, amount);
  }


  //PESQUISAR COM FILTRO
  public List<Actor> searchActorFilter(Integer quantity, String genreWork, LocalDate begin,
      Double amount, String filter) {

    List<Actor> actors;

    if(filter.equals("asc")){
      actors = actorRepository.findAllByOrderByPaymentAsc();
    }else if (filter.equals("desc")){
      actors = actorRepository.findAllByOrderByPaymentDesc();
    }else if(filter.equals("actorR")){
      actors = actorRepository.findAllByOrderByContadorDesc();
    }
    else{
      actors = actorRepository.findAll();
    }

    return search_filter(actors, quantity, genreWork, begin, amount);
  }


  //RESULTADOS GERAIS DA PESQUISA
  public List<Actor> search_filter(List<Actor> actors, Integer quantity, String genreWork, LocalDate begin, Double amount){
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

    if (quantity >= actorsGenre.size()) {
      return actorsGenre;
    } else if (quantity < actorsGenre.size()) {
      System.out.println("There are only " + actorsGenre.size() + " actors with this filter");
      return actorsGenre.subList(0, quantity);
    }
    return actorsGenre;
  }
}

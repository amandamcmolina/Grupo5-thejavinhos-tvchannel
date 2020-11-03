package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Perfil;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.PerfilRepository;
import com.thejavinhos.tvchannel.repository.ProducerRepository;
import com.thejavinhos.tvchannel.repository.ReserveRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

  @Autowired
  private ProducerRepository producerRepository;

  @Autowired
  private PerfilRepository perfilRepository;

  @Autowired
  private ReserveRepository reserveRepository;

  public Producer saveProducer(Producer producer) {
    if (producerRepository.findByUsername(producer.getUsername()) == null) {
      List<Perfil> roles = new ArrayList<>();
      Optional<Perfil> byId = Optional.ofNullable(perfilRepository.findByRole("ROLE_ADMIN"));
      perfilRepository.findAll();
      System.out.println(perfilRepository.findAll());
      if (byId.isPresent()) {
        roles.add(byId.get());
      }
      producer.setPerfis(roles);
      return producerRepository.save(producer);
    } else {
      throw new IllegalArgumentException("Producer already exists");
    }
  }

  public List<Reserve> reserveList(String username) {
    var actor = producerRepository.findByUsername(username);
    List<Reserve> actorList = reserveRepository.findAllByActorId(actor.getId());
    return actorList;
  }
}

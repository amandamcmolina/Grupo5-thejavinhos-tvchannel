package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.CreateProducer;
import com.thejavinhos.tvchannel.entity.Perfil;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.entity.ReturnActor;
import com.thejavinhos.tvchannel.entity.ReturnProducer;
import com.thejavinhos.tvchannel.entity.ReturnReserve;
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

  public Producer saveProducer(CreateProducer producer) {
    if (producerRepository.findByUsername(producer.getUsername()) == null) {
      List<Perfil> roles = new ArrayList<>();
      Optional<Perfil> byId = Optional.ofNullable(perfilRepository.findByRole("ROLE_ADMIN"));
      perfilRepository.findAll();
      if (byId.isPresent()) {
        roles.add(byId.get());
      }
      Producer finalProducer = new Producer();
      finalProducer.setPassword(producer.getPassword());
      finalProducer.setUsername(producer.getUsername());
      finalProducer.setPerfis(roles);
      return producerRepository.save(finalProducer);
    } else {
      throw new IllegalArgumentException("Producer already exists");
    }
  }

  public List<ReturnReserve> reserveList(String username) {
    var producer = producerRepository.findByUsername(username);
    List<Reserve> reserveList = reserveRepository.findAllByProducerId(producer.getId());
    ReturnProducer returnProducer = new ReturnProducer(producer.getUsername());
    List<ReturnReserve> reserves = new ArrayList<>();
    reserveList.forEach(reserve -> {
      ReturnActor eachActor = new ReturnActor(reserve.getActor().getUsername(),
                              reserve.getActor().getGender(), reserve.getActor().getPayment(),
                              reserve.getActor().getGenreWork());
      ReturnReserve eachReserve = new ReturnReserve(reserve.getId(), eachActor, returnProducer,
                                                    reserve.getDateReserveBegin(), reserve.getDateReserveEnd());
      reserves.add(eachReserve);
    });
    return reserves;

  }
}

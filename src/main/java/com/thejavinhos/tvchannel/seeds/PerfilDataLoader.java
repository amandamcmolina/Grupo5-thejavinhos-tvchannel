package com.thejavinhos.tvchannel.seeds;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Perfil;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.PerfilRepository;
import com.thejavinhos.tvchannel.repository.ProducerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PerfilDataLoader implements CommandLineRunner {

  @Autowired
  private PerfilRepository perfilRepository;

  @Autowired
  private ActorRepository actorRepository;

  @Autowired
  private ProducerRepository producerRepository;

  @Override
  public void run(String... args) throws Exception {
    loadUserData();
  }

  private void loadUserData() {
    if (perfilRepository.count() == 0) {
      Perfil role = new Perfil();
      role.setRole("ROLE_ADMIN");
      Perfil roleS = new Perfil();
      roleS.setRole("ROLE_USER");
      perfilRepository.save(role);
      perfilRepository.save(roleS);


      List<Perfil> roles = new ArrayList<>();
      Optional<Perfil> byId = Optional
          .ofNullable(perfilRepository.findByRole("ROLE_USER")); // linha id role // UM PERFIL
      System.out.println("byId: " + byId);
      if (byId.isPresent()) {
        roles.add(byId.get());
      }

      Actor actor= new Actor();

      actor.setName("Fábio");
      actor.setGenreWork("drama");
      actor.setGender("masculino");
      actor.setPayment(300);
      actor.setUsername("fabio");
      actor.setPassword("123");

      actorRepository.save(actor);
      actor.setPerfis(roles);

      Actor actor2= new Actor();
      actor2.setName("Joana");
      actor2.setUsername("joana");
      actor2.setGenreWork("drama");
      actor2.setGender("feminino");
      actor2.setPayment(400);
      actor2.setPassword("123");
      actorRepository.save(actor2);
      actor2.setPerfis(roles);


      Actor actor3= new Actor();
      actor3.setName("Ana");
      actor3.setUsername("ana");
      actor3.setGenreWork("comedia");
      actor3.setGender("feminino");
      actor3.setPayment(600);
      actor3.setPassword("123");
      actorRepository.save(actor3);
      actor3.setPerfis(roles);


      Actor actor4= new Actor();
      actor4.setName("Roberto");
      actor4.setUsername("roberto");
      actor4.setGenreWork("comedia");
      actor4.setGender("masculino");
      actor4.setPayment(200);
      actor4.setPassword("123");
      actorRepository.save(actor4);
      actor4.setPerfis(roles);


      Producer producer1= new Producer();
      producer1.setName("Carlos");
      producer1.setUsername("carlos");
      producer1.setPassword("123");
      producerRepository.save(producer1);
      actor4.setPerfis(roles);
    }
  }

}

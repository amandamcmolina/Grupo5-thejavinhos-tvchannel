package com.thejavinhos.tvchannel.entity;

import com.thejavinhos.tvchannel.repository.PerfilRepository;
import javax.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PerfilDataLoader implements CommandLineRunner {

  @Autowired
  private PerfilRepository perfilRepository;

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
    }
  }

}

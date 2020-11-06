package com.thejavinhos.tvchannel.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil implements GrantedAuthority, Serializable {

  @Id
  private String role;

  @ManyToMany(mappedBy = "perfis", fetch = FetchType.EAGER)
  private List<User> users = new ArrayList<>();

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String getAuthority() {
    return role;
  }

}

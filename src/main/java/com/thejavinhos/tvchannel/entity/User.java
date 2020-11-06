package com.thejavinhos.tvchannel.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;


  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(name = "users_perfis",
      joinColumns = @JoinColumn(name = "user_id",
          referencedColumnName = "id",
          nullable = false,
          updatable = false),
      inverseJoinColumns = @JoinColumn(name = "role",
          referencedColumnName = "role",
          nullable = false,
          updatable = false))
  private List<Perfil> perfis = new ArrayList<>();


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.perfis;
  }


  @Override
  public String getPassword() {
    return this.password;
  }

  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = new BCryptPasswordEncoder().encode(password);
  }


  public void setPerfis(List<Perfil> perfis) {
    this.perfis = perfis;
  }

  public List<Perfil> getPerfis() {
    return perfis;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

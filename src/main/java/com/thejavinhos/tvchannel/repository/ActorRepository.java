package com.thejavinhos.tvchannel.repository;

import com.thejavinhos.tvchannel.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

  Actor findByUsername(String username);

  List<Actor> findAllByOrderByPaymentAsc();

  List<Actor> findAllByOrderByPaymentDesc();

  List<Actor> findAllByOrderByQtdReservesDesc();


}

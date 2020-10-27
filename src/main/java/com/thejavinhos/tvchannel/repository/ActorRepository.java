package com.thejavinhos.tvchannel.repository;

import com.thejavinhos.tvchannel.entity.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {

}

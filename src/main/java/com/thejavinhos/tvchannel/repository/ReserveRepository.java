package com.thejavinhos.tvchannel.repository;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Reserve;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReserveRepository extends CrudRepository<Reserve, Integer> {

    List<Reserve> findAllByIdActor(Actor actor);
}

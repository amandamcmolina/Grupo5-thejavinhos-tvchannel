package com.thejavinhos.tvchannel.repository;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Reserve;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReserveRepository extends CrudRepository<Reserve, Integer> {


    List<Reserve> findAllByActorId(int id);

//    @Query("SELECT * FROM reserve WHERE id_actor = :actor.id")
//    List<Reserve> findAllByIdActor(Integer idActor);



//    @Query("SELECT * FROM reserve WHERE id_actor = ?1")
//    List<Reserve> findAllByIdActor(int id);

}

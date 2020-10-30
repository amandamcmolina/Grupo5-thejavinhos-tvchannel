package com.thejavinhos.tvchannel.service;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.repository.ActorRepository;
import com.thejavinhos.tvchannel.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;



    public Reserve createReserve(Reserve reserve){
        var inicial = reserve.getDateReserveBegin();
        var end = reserve.getDateReserveEnd();
        var id_actor = reserve.getActor();
        var dates = reserve.datas(inicial, end);

            reserve.setReservations(incrementList(reserve.getReservations(), dates));
            return reserveRepository.save(reserve);
    }


    public Reserve create(Reserve reserve){

        if(reserve.getReservations().isEmpty()){
            createReserve(reserve);
        }else{

            reservationDates(reserve, reserve.getActor());
        }
            return reserve;
    }





    public Reserve reservationDates(Reserve reserve, int id) {
        var inicial = reserve.getDateReserveBegin();
        var end = reserve.getDateReserveEnd();
        var dates = reserve.datas(inicial, end);
        return (Reserve) reserveRepository.findById(id).map(a -> {
            if (a.getReservations().contains(inicial) || a.getReservations().contains(end)) {
                throw new IllegalArgumentException("Data indisponivel");
            }

            a.setReservations(incrementList(a.getReservations(), dates));
            return reserveRepository.save(a);

        }).orElseThrow();
    }


    public List<Date> incrementList(List<Date> currentDates, List<Date> newDates) {
        return Stream.of(currentDates, newDates)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    public List<Reserve> listAll(){
        return (List<Reserve>) reserveRepository.findAll();
    }

    public Reserve listById(int id){
        return reserveRepository.findById(id).orElseThrow();
    }
}

package com.thejavinhos.tvchannel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @JoinColumn(name = "actor_id", referencedColumnName = "id")
    private int actor;


    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private int producer;

    @ElementCollection
    @Column(name = "reservations")
    private List<Date> reservations = new ArrayList();

    private Date dateReserveBegin;
    private Date dateReserveEnd;






    public List<Date> datas(Date dataInicial, Date dataFinal){
        List<Date> datas = new ArrayList<Date>();
        datas.add(dataInicial);
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(dataInicial);
        while(inicio.getTime().before(dataFinal)){
            inicio.add(Calendar.DAY_OF_YEAR,1);
            datas.add(inicio.getTime());
        }
        return datas;
    }
}

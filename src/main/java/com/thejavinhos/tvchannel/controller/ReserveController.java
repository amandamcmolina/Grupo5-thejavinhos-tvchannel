package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.MyUserDetails;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.entity.ReserveRequest;
import com.thejavinhos.tvchannel.service.ActorService;
import com.thejavinhos.tvchannel.service.ReserveService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @GetMapping
    private ResponseEntity <List<Reserve>> list(){
        return ResponseEntity.ok(reserveService.listAll());
    }

//    @GetMapping("/{id}")
//    private ResponseEntity<List<Reserve>> listbyReservesById(@PathVariable int id){
//        return ResponseEntity.ok(reserveService.ator(id));
////        return ResponseEntity.ok();
//    }

    @PostMapping
    @CacheEvict(value = "reservas", allEntries = true)
    private ResponseEntity<Reserve> create(@RequestBody ReserveRequest reserve){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null &&
            auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
              return  ResponseEntity.ok(reserveService.createReserve(reserve));
          }

        throw new IllegalArgumentException("You are not a admin");

    }

}

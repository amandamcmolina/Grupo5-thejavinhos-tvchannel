package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.MyUserDetails;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.entity.ReserveRequest;
import com.thejavinhos.tvchannel.entity.ReturnReserve;
import com.thejavinhos.tvchannel.service.ActorService;
import com.thejavinhos.tvchannel.service.ReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value="API REST Reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

//    @GetMapping
//    @ApiOperation(value= "Return reseves's list")
//    private ResponseEntity <List<Reserve>> list(){
//        return ResponseEntity.ok(reserveService.listAll());
//    }


    @PostMapping
    @CacheEvict(value = "reservas", allEntries = true)
    @ApiOperation(value= "create new Reserve")
    private ResponseEntity<ReturnReserve> create(@RequestBody ReserveRequest reserve){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null &&
            auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
              return  ResponseEntity.ok(reserveService.createReserve(reserve));
          }

        throw new IllegalArgumentException("You are not a admin");

    }

}

package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.service.ActorService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/actors")
public class
ActorController {

  @Autowired
  private ActorService actorService;


  @PostMapping
  public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
    return ResponseEntity.ok(actorService.saveActor(actor));
  }

  @GetMapping
  public ResponseEntity<List<Actor>> listAll() {
    return ResponseEntity.ok(actorService.listAllActors());
  }


  @GetMapping("/{username}")
  public ResponseEntity<List<Reserve>> listById(@PathVariable String username) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null &&
        auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))){
      return ResponseEntity.ok(actorService.reserveList(username));
    }

    throw new IllegalArgumentException("You are not a user");

  }

  @GetMapping("/search")
  public ResponseEntity<List<Actor>> search(
      @RequestParam("quantity") Integer quantity,
      @RequestParam("genreWork") String genreWork,
      @RequestParam("begin") String begin,
      @RequestParam("amount") Double amount,
      @RequestParam(required = false) String orderByamount) throws ParseException {
    LocalDate date;
    if(!begin.isBlank()){
      DateTimeFormatter formatar = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      date = LocalDate.parse(begin, formatar);
    }else{
      date = null;
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null &&
        auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
      if(orderByamount == null){
        return ResponseEntity.ok(actorService.searchActor(quantity, genreWork, date, amount));
      }else{
        return ResponseEntity.ok(actorService.searchActorFilter(quantity, genreWork, date, amount, orderByamount));
      }
    }

    throw new IllegalArgumentException("You are not a admin");

  }
}

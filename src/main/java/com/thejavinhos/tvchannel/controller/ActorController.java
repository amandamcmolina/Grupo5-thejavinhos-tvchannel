package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.service.ActorService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    return ResponseEntity.ok(actorService.reserveList(username));
  }

  @GetMapping("/search")
  public ResponseEntity<List<Actor>> search(
      @RequestParam("quantity") int quantity,
      @RequestParam("genreWork") String genreWork,
      @RequestParam("begin") String begin,
      @RequestParam("amount") Double amount) throws ParseException {

    var date = new SimpleDateFormat("yyyy-MM-dd").parse(begin);
    return ResponseEntity.ok(actorService.searchActor(quantity, genreWork, date, amount));

  }
}

package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Producer;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.service.ActorService;
import com.thejavinhos.tvchannel.service.ProducerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producer")
public class ProducerController {

  @Autowired
  private ProducerService producerService;

  @PostMapping
  public ResponseEntity<Producer> createProducer(@RequestBody Producer producer) {
    return ResponseEntity.ok(producerService.saveProducer(producer));
  }

  @GetMapping("/{username}")
  public ResponseEntity<List<Reserve>> listById(@PathVariable String username) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null &&
        auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
      return ResponseEntity.ok(producerService.reserveList(username));
    }

    throw new IllegalArgumentException("You are not a admin");

  }


}
